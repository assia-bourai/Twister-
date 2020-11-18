package Test;



import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.mysql.jdbc.util.TimezoneDump;

import BD.Database;
import Services.Friend;
import Services.Message;
import Services.User;
import Tools.UserTools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Boff {
	public static void main(String[] args){
	Timestamp t=new Timestamp(System.currentTimeMillis());
	
	//	t.setHours(t.getHours()+1);
		System.out.println(t);
		
		JSONObject o = new JSONObject();
		o = Message.listMessage("c9c09c1e-3022-41a3-8269-4c3c033ded08", 15);
		System.out.println(o.toString());
		
		
		
		
System.out.println(UserTools.userConnected("84dc9487-8f0a-4bc1-914c-7e790000b8d2"));

		
		/*
		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();

					String req2 = "UPDATE session SET FinSession='" + t + "' WHERE keySession='84dc9487-8f0a-4bc1-914c-7e790000b8d2';";
					int queryupdate= st.executeUpdate(req2);
					System.out.println(queryupdate);
			
			st.close();
			c.close();

		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());
		}
		
		
		*/
		
		
		
		
		
		
		
		
		

	
	
	
	/*User.Logout("352be6ef-6c67-4ae9-9f21-4bd38e380777");
		
		
		
		
		ArrayList<JSONObject> al;
		al= new ArrayList<JSONObject>();// message
		ArrayList<String> alf= new ArrayList<String>();//list ami
		Connection c = Database.getMySQLConnection();
		Statement st;
		try {
			st = c.createStatement();
			String req = "SELECT idUser2 FROM friendship as f WHERE f.idUser1='"+UserTools.getIdUser_key("1c15ff4b-d3fb-4abd-946a-a82ce1404ac3") + "';";
			ResultSet rs = st.executeQuery(req);
			if (rs.next()) {
				alf.add(rs.getInt(1)+"");
			}
			System.out.println(alf.toString());
			st.close();
			c.close();
			
	
			alf.add(23+"");
			Document doc = new Document();
			Document sort= new Document();
			sort.append("Heure",1);
			Document query= new Document();
			doc.append("$in",alf);
			//doc.append("$sort",sort);
			query.append("idUser",doc);

			
			
			MongoDatabase mdb = Database.getMongoConnection();
			MongoCollection<Document> message = mdb.getCollection("message");
			MongoCursor<Document> cursor = message.find(query).sort(sort).iterator();
			
			System.out.println(cursor.hasNext());
			while (cursor.hasNext()) {
					System.out.println("message n°"+ cursor.next());
			}
			


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}

}
