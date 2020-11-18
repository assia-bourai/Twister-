package ServletMessage;

import Services.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMessage extends HttpServlet {

	///// AJOUTER DES VERIFICATION APRES
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String key = request.getParameter("key");
		String id_texte = request.getParameter("id_texte");
		response.setContentType("json");

		PrintWriter out = response.getWriter();
		out.println(Message.deleteMessage(key, id_texte));

	}
}
