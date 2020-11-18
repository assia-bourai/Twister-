package Tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.DB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import BD.Database;
import Services.User;

public class MessageTools {

	public static String retourdate() {
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		java.util.Date date_du_jour = calendar.getTime();
		String datej = "" + date_du_jour;
		datej = datej.replace(' ', '-');
		return datej;

	}

	public static JSONObject messageAdd(String key, String text) throws JSONException {

		JSONObject o = new JSONObject();
		if (UserTools.userConnected(key)) {
			Document doc = new Document();
			String id = UserTools.getIdUser_key(key) + "";

			doc.append("idMessage", "" + id + "_" + retourdate());
			doc.append("idUser", id);
			doc.append("Heure", new Timestamp(System.currentTimeMillis()));
			doc.append("author", UserTools.getLoginUser(UserTools.getIdUser_key(key)));
			doc.append("message", text);
			System.out.println(doc);
			o.put("OK", doc);
			MongoDatabase mdb = Database.getMongoConnection();
			MongoCollection<Document> message = mdb.getCollection("message");
			message.insertOne(doc);
		} else {
			return ErrorsTools.serviceRefused("Utilisateur non connecté (session expirée)", 40);
		}

		return o;
	}

	public static ArrayList<Document> listMessageAccueil(String key) {
		if(UserTools.userConnected(key)) {
		ArrayList<Document> al;
		al = new ArrayList<Document>();// liste des messages correspondants aux id dans alf
		ArrayList<String> alf = new ArrayList<String>();// alf = une liste qui récuère les id (amis + user lui mm )
		Connection c = Database.getMySQLConnection();  
		Statement st;
		try { // récupérer les id de la bdd  mysql
			st = c.createStatement();
			String req = "SELECT idUser2 FROM friendship as f WHERE f.idUser1='" + UserTools.getIdUser_key(key) + "';";
			ResultSet rs = st.executeQuery(req);
			while (rs.next()) {
				alf.add(rs.getInt(1) + "");
			}
			System.out.println(alf);
			alf.add(""+UserTools.getIdUser_key(key));
			System.out.println(alf.toString());
			st.close();
			c.close();
			
			// partie mongoDB -> récupérer les messages correspondant aux id dans alf 
			Document doc = new Document(); 
			Document sort = new Document();
			sort.append("Heure", -1); // spécifier l'ordre dans lequel les messages seront listés 
			Document query = new Document(); 
			doc.append("$in", alf);// liste des id amis et user 
			// doc.append("$sort",sort);
			query.append("idUser", doc); // la clause du where Where id IN alf

			MongoDatabase mdb = Database.getMongoConnection();
			MongoCollection<Document> message = mdb.getCollection("message");
			MongoCursor<Document> cursor = message.find(query).sort(sort).iterator();

			System.out.println(cursor.hasNext());
			while (cursor.hasNext()) {
				al.add(cursor.next());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return al;
		}else {
			return new ArrayList<Document>();
		}
	}

	public static JSONObject messageDelete(String key, String id_message) {

		JSONObject o = new JSONObject();
		if (UserTools.userConnected(key)) {
			String id = UserTools.getIdUser_key(key) + "";
			Document doc = new Document();
			MongoDatabase mdb = Database.getMongoConnection();
			MongoCollection<Document> message = mdb.getCollection("message");
			doc.append("idUser", id);
			doc.append("idMessage", id_message);

			MongoCursor<Document> cursor = message.find(doc).iterator();
			if (cursor.hasNext()) {
				message.deleteOne(doc);

				o = ErrorsTools.serviceAccepted();

			} else {
				o = ErrorsTools.serviceRefused("Message non trouvé", 51);
			}

		}

		return o;
	}

	public static JSONObject messageList(String key, int id) {
		JSONObject o = new JSONObject();
		if ((UserTools.userConnected(key)) && (UserTools.userExistId(id))) {
			MongoDatabase mdb = Database.getMongoConnection();
			MongoCollection<Document> message = mdb.getCollection("message");

			Document doc = new Document();
			Document sort = new Document();
			sort.append("Heure", -1); // spécifier l'ordre dans lequel les messages seront listés 
			String iduser = "" + id;
			doc.append("idUser", iduser);

			MongoCursor<Document> cursor = message.find(doc).sort(sort).iterator();
			ArrayList<Document> al = new ArrayList<Document>();
			while (cursor.hasNext()) {
				al.add(cursor.next());
			}
			try {
				o.put("",al);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return o;
	}

}
