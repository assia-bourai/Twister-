package Tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorsTools {
	public static JSONObject serviceRefused(String message, int codeErreur) {
		JSONObject o = new JSONObject();
		try {
		
			o.put("code",codeErreur);
			o.put("Message", message);
		
			
		} catch (JSONException e) {
			System.err.println("Erreur sur "+e);
		}
			return o;
		
		
		
	}
	public static JSONObject serviceAccepted() {
		return new JSONObject();		
	}
	public static JSONObject serviceAccepted(int id, String key,String login) {
		JSONObject o = new JSONObject();
		try {
		
			o.put("id",id );
			o.put("login", login);
			o.put("key", key);
		} catch (JSONException e) {
			System.err.println("Erreur sur "+e);
		}
			return o;
		
				
	}

}
