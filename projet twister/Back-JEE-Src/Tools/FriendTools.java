package Tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.util.JSON;

import BD.Database;


public class FriendTools {
	public static JSONObject friendAjout(String key, int id_friend) {
		JSONObject o = new JSONObject();
		o= ErrorsTools.serviceRefused("Ajout ami refusé", 10);
		try {

			if (UserTools.userConnected(key)) {

				Connection c = Database.getMySQLConnection();
				Statement st = c.createStatement();

				String req = "INSERT INTO friendship(idUser1, idUser2) VALUES ('" + UserTools.getIdUser_key(key) + "','"
						+ id_friend + "');";
				int rs = st.executeUpdate(req);
				if (rs > 0) {
					o= ErrorsTools.serviceAccepted();
				}
				st.close();
				c.close();
				return o;
			}
		} catch (SQLException e) {
			System.err.println("Exception " + e.toString());
		}

		return o;
	}

	public static JSONObject friendRemove(String key, int id_friend) {
		try {

			if (UserTools.userConnected(key) && (friendExist(key, id_friend))) {
				JSONObject o = new JSONObject();

				Connection c = Database.getMySQLConnection();
				Statement st = c.createStatement();

				String req = "DELETE FROM friendship WHERE idUser1='" + UserTools.getIdUser_key(key) + "' AND idUser2='"
						+ id_friend + "';";
				int rs = st.executeUpdate(req);
				if (rs > 0) {
					o.put("SUPRESSION AMI REUSSI", 1);
				}
				st.close();
				c.close();
				return o;
			}
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());
		} catch (JSONException e) {

			System.out.println("Exception " + e.toString());
		}

		return ErrorsTools.serviceRefused("Suppression ami refusée", 30);
	}

	public static boolean friendExist(String key, int id_friend) {
		boolean b = false;
		try {

			if (UserTools.userConnected(key)) {

				Connection c = Database.getMySQLConnection();
				Statement st = c.createStatement();

				String req = "SELECT * FROM friendship WHERE idUser1='" + UserTools.getIdUser_key(key)
						+ "' AND idUser2='" + id_friend + "';";
				ResultSet rs = st.executeQuery(req);
				if (rs.next()) {
					b = true;
				}
				st.close();
				c.close();
			}
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());
		}

		return b;
	}

	public static JSONObject friendList(String key, int id_user) {
		
		JSONObject o = new JSONObject();
		try {

			if (UserTools.userConnected(key)) {

				Connection c = Database.getMySQLConnection();
				Statement st = c.createStatement();

				String req = "SELECT nomUser,prenomUser,loginUser,idUser FROM user as u ,friendship as f WHERE f.idUser1='"
						+ id_user + "' AND u.idUser=f.idUser2;";
				ResultSet rs = st.executeQuery(req);
				ArrayList<JSONObject> liste = new ArrayList<JSONObject>();

				while (rs.next()) {
					JSONObject a = new JSONObject();
					a.put("nom", rs.getString(1));
					a.put("prenom", rs.getString(2));
					a.put("login", rs.getString(3));
					a.put("iduser",rs.getInt(4));
					//System.out.println(a);
					
					liste.add(a);		
				}

				o.put("amis",liste);
				st.close();
				c.close();
			}
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());
		} catch (JSONException e) {
			System.out.println("Exception " + e.toString());
		}

		return o;

	}

	public static JSONObject rechercher(String key, String chaine) {
		
		JSONObject o = new JSONObject();
		try {

			if (UserTools.userConnected(key)) {

				Connection c = Database.getMySQLConnection();
				Statement st = c.createStatement();

				String req = "SELECT  loginUser ,  nomUser ,  prenomUser,idUser FROM  user WHERE loginUser LIKE  '%"+chaine+"%' OR nomUser LIKE  '%"+chaine+"%'"
						+ "	OR prenomUser LIKE  '%"+chaine+"%' ;";
				ResultSet rs = st.executeQuery(req);
				ArrayList<JSONObject> liste = new ArrayList<JSONObject>();

				while (rs.next()) {
					JSONObject a = new JSONObject();
					a.put("login", rs.getString(1));
					a.put("nom", rs.getString(2));
					a.put("prenom", rs.getString(3));
					a.put("iduser",rs.getInt(4));
					//System.out.println(a);
					
					liste.add(a);		
				}

				o.put("res",liste);
				st.close();
				c.close();
			}
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());
		} catch (JSONException e) {
			System.out.println("Exception " + e.toString());
		}

		return o;

	}

}
