package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database {
	private static DataSource datasource;
	public static Database database = null;

	public Database(String jndiname) throws SQLException {
		try {
			datasource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiname);

		} catch (NamingException e) {
			System.out.println("Exception " + e.toString());
			throw new SQLException(jndiname + "is missig in JNDI ! :" + e.getMessage());

		}
	}

	public Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

	public static Connection getMySQLConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (DBStatic.mysql_pooling == false) {
				return (DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/" + DBStatic.mysql_db,
						DBStatic.mysql_username, DBStatic.mysql_password));
			} 

				if (database == null) {
					database = new Database("jdbc/db");
				}
				return (database.getConnection());
			
		} catch (ClassNotFoundException e) {
			System.out.println("Execption " + e.toString());
		} catch (SQLException e) {
			System.out.println("Execption " + e.toString());
		}
		return null;
	}

	public static MongoDatabase getMongoConnection() {
		MongoClient mongo = MongoClients.create("mongodb://" + DBStatic.mongodb_host);
		return mongo.getDatabase(DBStatic.mongodb_db);

	}

}
