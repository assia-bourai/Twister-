package ServletFriend;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Services.*;

public class RemoveFriend extends HttpServlet{
	public RemoveFriend() {
		super();
	}
	//AJOUTER DES VERIFICATION APRES 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		int id =  Integer.parseInt(request.getParameter("id"));
		response.setContentType("json");
		PrintWriter out = response.getWriter();
		out.println(Friend.removeFriend(key, id));
		
}

}
