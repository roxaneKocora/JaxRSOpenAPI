package fr.istic.taa.jaxrs.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="userinfo",urlPatterns={"/UserInfo"})
public class UserInfo extends HttpServlet {
		
	UserDao uDao ;
	
	@Override
	public void init() throws ServletException   {
		super.init();
		this.uDao = new UserDao();
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
	
		PrintWriter out = response.getWriter();
		
		User u = new User();
		u.setNom(request.getParameter("name"));
		uDao.save(u);
	
		out.println("<HTML>\n<BODY>\n" +
					"<H1>Recapitulatif des informations</H1>\n" +
					"<UL>\n" +			
			" <LI>Nom: "
					+ request.getParameter("name") + "\n" +
					" <LI>Prenom: "
					+ request.getParameter("firstname") + "\n" +
					" <LI>Age: "
					+ request.getParameter("age") + "\n" +
					"</UL>\n" +				
			"</BODY></HTML>");
	}
}
