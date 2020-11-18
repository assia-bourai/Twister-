package Tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import BD.*;
import javax.security.auth.login.LoginException;

import org.json.JSONObject;

public class UserTools {
	public static boolean userExist(String nom, String prenom, String email) {
		boolean b = false;
		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			String req = "SELECT * FROM user WHERE nomUser='"+nom+"' AND prenomUser='"+prenom+"' AND mailUser='"+email+"';";
			ResultSet rs = st.executeQuery(req);
			if (rs.next()) {
				b = true;
			}
			st.close();
			c.close();
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());
		}
		return b;
	}
	public static boolean userExistId(int id) {
		boolean b = false;
		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			String req = "SELECT * FROM user WHERE idUser='"+id+"';";
			ResultSet rs = st.executeQuery(req);
			if (rs.next()) {
				b = true;
			}
			st.close();
			c.close();
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());
		}
		return b;
	}
	public static boolean userConnected(String key) {
		boolean b = false;
		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			Timestamp t=new Timestamp(System.currentTimeMillis());
			String req = "SELECT FinSession FROM session WHERE keySession='" + key+ "';";
			ResultSet rs = st.executeQuery(req);
			if (rs.next()) {
				if(t.before(rs.getTimestamp(1))) {
					t.setHours(t.getHours()+1);
					String req2 = "UPDATE session SET FinSession='" + t + "' WHERE keySession='"+key+"';";
					int queryupdate= st.executeUpdate(req2);
					if (queryupdate>0) {
										b=true;	
					}

				}else {
					int x= deconnectUser(key);
					if (x>0) {
						return false;
					}
					
				}
			}
			st.close();
			c.close();

		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());
		}
		return b;
	}

	public static boolean loginExist(String login) {
		boolean b = false;
		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			String req = "SELECT loginUser FROM user WHERE loginUser='" + login + "';";
			ResultSet rs = st.executeQuery(req);
			if (rs.next()) {
				b = true;
			}
			st.close();
			c.close();
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());
		}
		return b;
	}

	public static boolean checkPassword(String login, String password) {
		boolean b = false;
		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();

			String req = "SELECT * FROM user WHERE loginUser='"+login+"' AND passwordUser='" + password+ "';";
			ResultSet rs = st.executeQuery(req);
			if (rs.next()) {
				b = true;
			}

			st.close();
			c.close();

		} catch (SQLException e) {

			System.out.println("Exception " + e.toString());
		}
		return b;
	}

	public static int createUser(String prenom, String nom, String login, String password, String email) {
		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();

			String req = "INSERT INTO user(loginUser, nomUser, prenomUser, passwordUser,mailUser) VALUES('" + login
					+ "','" + nom + "','" + prenom + "','" + password + "','" + email + "');";

			int rs = st.executeUpdate(req);
			return rs;
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());

		}
		return 0;
	}

	public static int connectUser(int id, String key) {
		try {
			Timestamp t=new Timestamp(System.currentTimeMillis());
			t.setHours(t.getHours()+1);
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();

			String req = "INSERT INTO session(idUser,keySession,FinSession)  VALUES(" + id + ",'" + key + "','"+t+"');";

			int rs = st.executeUpdate(req);
			return rs;
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());

		}
		return 0;
	}

	public static int getIdUser(String login) {

		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();

			String req = "SELECT idUser FROM user WHERE loginUser='" + login + "';";
			ResultSet rs = st.executeQuery(req);
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());

		}

		return 0;
	}
	public static String getLoginUser(int id) {

		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();

			String req = "SELECT loginUser FROM user WHERE idUser='" + id + "';";
			ResultSet rs = st.executeQuery(req);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());

		}

		return null;
	}
	public static int getIdUser_key(String key) {

		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();

			String req = "SELECT idUser FROM session WHERE keySession='" +key+ "';";
			ResultSet rs = st.executeQuery(req);
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());

		}

		return 0;
	}

	public static int deconnectUser(String key) {
		try {
			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();

			String req = "DELETE FROM session WHERE keySession='"+key+"';";
			int rs = st.executeUpdate(req);
			return rs;
		} catch (SQLException e) {
			System.out.println("Exception " + e.toString());

		}
		return 0;
	}

	
}
