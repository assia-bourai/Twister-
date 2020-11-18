package ServletUser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Services.*;

public class Login extends HttpServlet{
	public Login() {
		super();
	}

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String login= req.getParameter("login");
		String password =req.getParameter("password");
		
		JSONObject resultCreate= new JSONObject();
		resultCreate =  User.Login(login, password);
		resp.setContentType("text/JSON");
		PrintWriter out = resp.getWriter();
		out.print(resultCreate);
		
	}

}

