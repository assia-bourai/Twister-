package Services;

import Tools.*;

import java.util.ArrayList;
import java.util.UUID;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

public class User {

	public static JSONObject CreateUser(String prenom, String nom, String login, String password, String email) {
		if ((prenom == null) || (nom == null) || (password == null) || (login == null) || (email == null)) {

			return ErrorsTools.serviceRefused("Paramètres manquants", 70);
		}

		if ((UserTools.userExist(nom, prenom, email))) {
			return ErrorsTools.serviceRefused("Utilisateur avec Les mêmes informations déjà existant", 71);
		}
		if (UserTools.loginExist(login)) {
			return ErrorsTools.serviceRefused("Login déjà utilisé", 72);
		}
		int x = UserTools.createUser(prenom, nom, login, password, email);
		if (x > 0) {
			return ErrorsTools.serviceAccepted();
		} else {
			return ErrorsTools.serviceRefused("Erreur à la creation veuillez réessayez", 73);
		}
	}

	public static JSONObject Login(String login, String password) {
		if ((password == null) || (login == null)) {

			return ErrorsTools.serviceRefused("Paramètres manquants",80);
		} 

		if (!(UserTools.checkPassword(login, password))) {
			return ErrorsTools.serviceRefused("Login ou Password incorrectes", 81);
		}
		JSONObject o = new JSONObject();
		String key = UUID.randomUUID().toString();
		int id = UserTools.getIdUser(login);
		if (UserTools.connectUser(id, key) == 0) {
			return ErrorsTools.serviceRefused("Connexion échouée, veuillez réessayer", 84);
		} else {
			
			ArrayList<Document> msg = new ArrayList<Document>();
			try {
				
				o.put("CONNEXION REUSSIE",ErrorsTools.serviceAccepted(id, key, login));
				msg=MessageTools.listMessageAccueil(key);// lister les message de tout son reseau apres la connexion
				System.out.println(msg.toString());
				o.put("",msg);
				return o;
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			return o;
		}

	}

	public static JSONObject Logout(String key) {
		if (key == null) {
			return ErrorsTools.serviceRefused("Paramètres manquants", 90);
		}
		if (UserTools.deconnectUser(key) == 0) {
			return ErrorsTools.serviceRefused("Déconnexion échouée", 91);
		} else {
			return ErrorsTools.serviceAccepted();
		}
	}
}
