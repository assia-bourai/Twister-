package ServletMessage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.Message;

import Tools.*;

public class Search extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String key = request.getParameter("key");
		int id_user = 0;
		try {
			id_user = Integer.parseInt(request.getParameter("id_user"));
		} catch (Exception e) {
			System.err.println("Erreur parseInt");
		}

		response.setContentType("json");
		PrintWriter out = response.getWriter();
		out.println(Message.listMessage(key, id_user)); 

	}

}
