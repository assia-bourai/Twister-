package Test;

import org.json.JSONObject;
import Services.Friend;

public class TestFriend {

	public static void main(String[] args) {
		System.out.println(Friend.rechercher("95034a93-7532-494f-863f-ec79bdc070fd","as"));
/*
 * http://localhost:8080/FINALSERVER//friend/add?key=0f3b5ea6-90f9-45ab-baa5-6aa0399620f0&id=20
 * {"AJOUT AMI REUSSI":1}
 * 
 * http://localhost:8080/FINALSERVER//friend/list?key=0f3b5ea6-90f9-45ab-baa5-6aa0399620f0&id=23
 * {"ami :":{"login":"nacimaghout@123456","nom":"ghout","prenom":"nacime"}}
 * 
 * http://localhost:8080/FINALSERVER//friend/remove?key=0f3b5ea6-90f9-45ab-baa5-6aa0399620f0&id=20
 * {"SUPRESSION AMI REUSSI":1}
 * 
 * ##AFFICHAGE LSIT FRIEND APRES SUPRESSION###
 * {}
 * 
 */
		
		
	}

}
