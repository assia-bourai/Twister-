package Services;

import Tools.*;
import org.json.JSONObject;

public class Friend {
	public static JSONObject addFriend(String key, int id) {
		if ((key == null) || (id == 0))
			return ErrorsTools.serviceRefused("Param�tres manquants", 11);

		if (FriendTools.friendExist(key, id))
			return ErrorsTools.serviceRefused("Amis d�j� dans la liste", 12);

		return FriendTools.friendAjout(key, id);
	}

	public static JSONObject listFriend(String key, int id) {
		if ((key == null) || (id == 0))
			return ErrorsTools.serviceRefused("Param�tres manquants", 21);

		return FriendTools.friendList(key, id);
	}

	public static JSONObject removeFriend(String key, int id) {
		if (key == null)
			return ErrorsTools.serviceRefused("Param�tres manquants", 30);

		boolean exist = FriendTools.friendExist(key, id);
		if (!exist)
			return ErrorsTools.serviceRefused("Vous n'�tes pas amis", 32);

		return FriendTools.friendRemove(key, id);
	}
	public static JSONObject rechercher(String key,String chaine) {
		if((key==null)||(chaine==null))
			return ErrorsTools.serviceRefused("Param�tres manquants", 40);
		return FriendTools.rechercher(key,chaine);
	}
}
