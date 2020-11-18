package Test;
import org.json.JSONException;
import org.json.JSONObject;
import Services.Message;
import Tools.*;
public class TestMessage {

	public static void main(String[] args) throws JSONException {
		
		System.out.println(MessageTools.listMessageAccueil("95034a93-7532-494f-863f-ec79bdc070fd"));
		

/*
 * http://localhost:8080/FINALSERVER//message/add?key=0f3b5ea6-90f9-45ab-baa5-6aa0399620f0&texte=premier%20test%20avec%20l%20user%20la%20que%20j%20ai%20creer%20pour%20tester%20xD
 * {
 * "OK":
 * 		{"idUser":"23","message":"premier test avec l user la que j ai creer pour tester xD",
 * 		"Heure":"2019-02-21 18:10:04.762","idMessage":"23_Thu-Feb-21-18:10:04-CET-2019","author":"beza"}
 * }
 * 
 * {
 * "OK":
 * 		{"idUser":"23","message":"deuxieme test svp hihihi","Heure":"2019-02-21 18:10:44.014",
 * 		"idMessage":"23_Thu-Feb-21-18:10:44-CET-2019","author":"beza"}
 * }
 * 
 * {
 * "OK":
 * 		{"idUser":"23","message":"euh et bein le troisieme voila j en ai creer trois pour supprimerle 
 * 		deuxieme tout simplement laboud nehrech chouia","Heure":"2019-02-21 18:11:44.066",
 * 		"idMessage":"23_Thu-Feb-21-18:11:44-CET-2019","author":"beza"}
 * }
 * 
 * http://localhost:8080/FINALSERVER//message/list?key=0f3b5ea6-90f9-45ab-baa5-6aa0399620f0&id_user=23
 * {
 * "message n°0":
 * 				{"idUser":"23","idMessage":"23_Thu-Feb-21-18:10:04-CET-2019",
 * 				"author":"beza","_id":"5c6edb6c1a7c074236ef6e49",
 * 				"message":"premier test avec l user la que j ai creer pour tester xD",
 * 				"Heure":"Thu Feb 21 18:10:04 CET 2019"},
 * "message n°2":
 * 				{"idUser":"23","idMessage":"23_Thu-Feb-21-18:11:44-CET-2019",
 * 				"author":"beza","_id":"5c6edbd01a7c074236ef6e4d",
 * 				"message":"euh et bein le troisieme voila j en ai creer trois pour supprimerle deuxieme tout simplement laboud nehrech chouia",
 * 				"Heure":"Thu Feb 21 18:11:44 CET 2019"},
 * "message n°1":
 * 				{"idUser":"23","idMessage":"23_Thu-Feb-21-18:10:44-CET-2019","author":"beza","_id":"5c6edb941a7c074236ef6e4b",
 * 				"message":"deuxieme test svp hihihi","Heure":"Thu Feb 21 18:10:44 CET 2019"}
 * }
 * 
 * 
 * ##APRES SUPPRESION DU DEUXIEME MESSAGE###
 * http://localhost:8080/FINALSERVER//message/delete?key=0f3b5ea6-90f9-45ab-baa5-6aa0399620f0&id_texte=23_Thu-Feb-21-18:10:44-CET-2019
 * {"Resultat Suppression":"OK"}
 * 
 */
		
	}

}
