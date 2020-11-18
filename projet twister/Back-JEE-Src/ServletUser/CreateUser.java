package ServletUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Services.*;


public class CreateUser extends HttpServlet{
	
	public CreateUser() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String login= req.getParameter("login");
		String password =req.getParameter("password");
		String email =req.getParameter("email");
		JSONObject resultCreate= new JSONObject();
		resultCreate =  User.CreateUser(prenom, nom, login, password,email);
		resp.setContentType("text/JSON");
		PrintWriter out = resp.getWriter();
		out.print(resultCreate);
		
	}

}