package web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.model.Carrello;
import web.model.CartModelDM;
import web.model.Casella;
import web.model.UserModelDM;
import web.model.Utente;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UserModelDM userDM = new UserModelDM();

	
	
    public UserServlet() {
        super();
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		try {
			if(action!=null) {
				if(action.equalsIgnoreCase("regUser")) {
					String name = request.getParameter("name");
					String surname = request.getParameter("surname");
					String email = request.getParameter("email");
					String id = request.getParameter("id");
					String pswd = request.getParameter("password");
					Utente user = new Utente();
					user.setName(name);
					user.setId(id);
					user.setEmail(email);
					user.setSurname(surname);
					user.setPsw(pswd);
					user.setSaldo(0);
					userDM.doSave(user);

					response.sendRedirect("LoginPage.jsp");
				}
				else if(action.equalsIgnoreCase("logout")){
					
					HttpSession session = request.getSession(false);
					session.invalidate();
					Cookie cookies[] = request.getCookies();
					for(int i = 0; i < cookies.length; i++) {
						cookies[i].setMaxAge(0);
						cookies[i].setPath("/");
						response.addCookie(cookies[i]);
					}
					response.sendRedirect("Home.jsp");
				}
				else if(action.equalsIgnoreCase("login")){
					String id = request.getParameter("username");
					String pswd = request.getParameter("password");
					//prendo user dal dm;
					Utente user = userDM.getItemByKey(id);
					System.out.println(user);
					if(user.getId()==null) {
						response.sendRedirect("CredentialError.jsp");
					}
					else {
						CartModelDM cartmodel = new CartModelDM();
						Carrello cart = cartmodel.getCart(user.getId());
						if(user.getId().equalsIgnoreCase(id) && user.getPsw().equals(pswd)) {
							
								HttpSession session = request.getSession(true);
								if(user.getRole().equals("administrator")) {
									ArrayList<Utente> users = userDM.getCollection("");
									session.setAttribute("users", users);
								}
								session.setAttribute("IdRISIko", id);
								session.setAttribute("cart", cart);
								session.setAttribute("role", user.getRole());
								Cookie ck0 = new Cookie("IdRISIko", user.getId());
								Cookie ck1 = new Cookie("Name", user.getName());
								Cookie ck2 = new Cookie("EMail", user.getEmail());
								Cookie ck3 = new Cookie("Surname", user.getSurname());
								Cookie ck4 = new Cookie("Saldo", String.valueOf(user.getSaldo()));
								Cookie ck5 = new Cookie("Role", user.getRole());
								response.addCookie(ck0);
								response.addCookie(ck1);
								response.addCookie(ck2);
								response.addCookie(ck3);
								response.addCookie(ck4);
								response.addCookie(ck5);
								response.sendRedirect("Home.jsp");
							}
							else if(!user.getId().equals(id) || !user.getPsw().equals(pswd)){
								response.sendRedirect("CredentialError.jsp");
							}
							else if(user.getId() == null) {
								response.sendRedirect("CredentialError.jsp");
							}
					}
				}else if(action.equalsIgnoreCase("viewUsers")){
					ArrayList<Utente> users = userDM.getCollection("asc");
					HttpSession session = request.getSession(false);
					session.setAttribute("users", users);
					response.sendRedirect("AdministratorPage.jsp");
					
				}else if(action.equalsIgnoreCase("LVUP")) {
					String id = request.getParameter("user");
					userDM.lvUp(id);
					//aggiorno anche cookie
					Cookie cks[] = request.getCookies();
					for(int i = 0; i < cks.length; i++) {
						if(cks[i].getName().equals("Role")){
							String newRole = "administrator";
							cks[i].setMaxAge(0);
							cks[i].setPath("/");
							Cookie newCookie = new Cookie("Role", newRole);
							response.addCookie(newCookie);
						}
					}
					response.sendRedirect("Home.jsp");
					
				}else if(action.equalsIgnoreCase("LVDOWN")) {
					String id = request.getParameter("user");
					userDM.lvDown(id);
					Cookie cks[] = request.getCookies();
					for(int i = 0; i < cks.length; i++) {
						if(cks[i].getName().equals("Role")){
							String newRole = "user";
							cks[i].setMaxAge(0);
							cks[i].setPath("/");
							Cookie newCookie = new Cookie("Role", newRole);
							response.addCookie(newCookie);
						}
					}
					response.sendRedirect("Home.jsp");
				}
				
				else if(action.equalsIgnoreCase("ricarica")){
					String saldoRicarica = request.getParameter("box");
					Float saldoRicaricaFloat = Float.parseFloat(saldoRicarica);
					
					HttpSession session = request.getSession(false);
					String id = (String) session.getAttribute("IdRISIko");
					Cookie cks[] = request.getCookies();
					for(int i = 0;i < cks.length; i++) {
						if(cks[i].getName().equals("Saldo")){
							String saldoInCookie = cks[i].getValue();
							float saldoInCookieFloat = Float.parseFloat(saldoInCookie);
							float newSaldoInCookie = saldoInCookieFloat + saldoRicaricaFloat;
							userDM.Ricarica(id,newSaldoInCookie);
					/*
					 * setto il nuovo valore del cookie saldo;
					 */
							String newSaldoInCookieString = String.valueOf(newSaldoInCookie);
							cks[i].setMaxAge(0);
							cks[i].setPath("/");
							Cookie newCookie = new Cookie("Saldo", newSaldoInCookieString);
							response.addCookie(newCookie);
							}
					}
					response.sendRedirect("MyCart.jsp");
					
					
				}else if(action.equalsIgnoreCase("email")) {
					String areatxt = request.getParameter("emailzone");
					ArrayList<Utente> admins = userDM.getAllAdmin();
					HttpSession session = request.getSession(false);
					for(int i = 0; i < admins.size();i++) 
						userDM.sendEmail(areatxt, admins.get(i),(String) session.getAttribute("IdRISIko"));
					response.sendRedirect("EmailRingraziamenti.jsp");
					
				}else if( action.equalsIgnoreCase("viewEmail")) {
					HttpSession session = request.getSession(false);
					Casella casella = new Casella();
					casella = userDM.getAllEmails((String)session.getAttribute("IdRISIko"));
					session.setAttribute("casella", casella);
					response.sendRedirect("viewEmail.jsp");
					
				}else if((action.equals("responseEmail"))) {
					HttpSession session = request.getSession(false);
					String destinatarioEmail = request.getParameter("destinatario");
					Utente utenteDestinatario = userDM.getItemByKey(destinatarioEmail);
					String mittente = (String) session.getAttribute("IdRISIko");
					String mexArea = request.getParameter("emailzone");	
					userDM.sendEmail(mexArea,utenteDestinatario, mittente);
					response.sendRedirect("viewEmail.jsp");
					
				}else if(action.equalsIgnoreCase("deleteEmail")){
					
					HttpSession session = request.getSession(false);
					String mittente = (String) session.getAttribute("IdRISIko");
					userDM.deleteEmail(mittente);
					Casella casella = new Casella();
					casella = userDM.getAllEmails(mittente);
					session.removeAttribute("casella");
					session.setAttribute("casella", casella);
					response.sendRedirect("viewEmail.jsp");
					
				}
				
		
			}} catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}
}
