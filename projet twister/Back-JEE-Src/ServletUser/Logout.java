package ServletUser;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import Services.*;



public class Logout extends HttpServlet {
	public Logout() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			try {
				
				String key= request.getParameter("key");
				response.setContentType("json");
				PrintWriter out = response.getWriter();
				out.println(User.Logout(key));
			} catch (Exception e) {
				System.err.println(e.toString());
			}
			
		}
	}

