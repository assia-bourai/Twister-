package Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import BD.Database;
import Tools.*;

public class Message {

	public static JSONObject addMessage(String key, String texte) {
		JSONObject o = new JSONObject();

		if ((key == null) || (texte == null))
			return ErrorsTools.serviceRefused("Paramètres manquants", 41);

		try {
			o = MessageTools.messageAdd(key, texte);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return o;
	}

	public static JSONObject deleteMessage(String key, String id_message) {
		if ((key == null) || (id_message == null))
			return ErrorsTools.serviceRefused("Paramètres manquants", 50);

		return MessageTools.messageDelete(key, id_message);
	}

	public static JSONObject listMessage(String key, int id) {
		if ((key == null) && (id == 0)) 
			return ErrorsTools.serviceRefused("Paramètres manquants", 60);
		if ((key != null) && (id == 0)) { // on est sur la page principale 
			JSONObject o = new JSONObject();
			try {
				o.put("", MessageTools.listMessageAccueil(key));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return o;

		}
		return MessageTools.messageList(key, id); // on est sur une page profil 
	}

}
