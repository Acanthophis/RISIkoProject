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
import javax.websocket.Session;

import web.model.Carrello;
import web.model.CartModelDM;
import web.model.Prodotto;
import web.model.ProdottoInTheCart;
import web.model.ProductModelDM;
import web.model.UserModelDM;
import web.model.Utente;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		CartModelDM modelCart = new CartModelDM();
		try {
			if(action!=null) {
				if(action.equalsIgnoreCase("addToCart")) {
					if(session.getAttribute("IdRISIko") != null) {
						modelCart.addToCart(request.getParameter("idProdotto"),request.getParameter("idUtente"), request.getParameter("quantita"));
						Carrello carrello = modelCart.getCart((String)session.getAttribute("IdRISIko"));
						session.removeAttribute("cart");
						session.setAttribute("cart", carrello);
						response.sendRedirect("Prodotti.jsp");
					}
					else {
						response.sendRedirect("UMustLogIn.jsp");
					}
					
				}
				else if(action.equalsIgnoreCase("deleteFromCart")) {
					modelCart.doDelete((String)session.getAttribute("IdRISIko"),request.getParameter("idProdotto"));
					session.removeAttribute("cart");
					Carrello carrello = modelCart.getCart((String) session.getAttribute("IdRISIko"));
					session.setAttribute("cart", carrello);
					response.sendRedirect("MyCart.jsp");	
				}
				else if(action.equalsIgnoreCase("viewAllProductsInCart")) {
						
					response.sendRedirect("MyCart.jsp");	
				}else if(action.equalsIgnoreCase("salda")) {
					Carrello cart = (Carrello)session.getAttribute("cart");
					if(cart==null) {
						response.sendRedirect("CredentialError.jsp");
					}
					else {
					float saldo = 0;
					if(cart.getSize() > 0) {
						System.out.println(cart.getSize());
						
						UserModelDM userDM = new UserModelDM();
						String id = (String)session.getAttribute("IdRISIko");
						Utente userPagante = new Utente();
						userPagante = (Utente)userDM.getItemByKey(id);
						for(int i = 0; i < cart.getSize();i++) {
							saldo += cart.getEl(i).getPrezzo()*cart.getEl(i).getQuantity();
						}
						if(userPagante.getSaldo() >= saldo) {
							userDM.Paga(id,userPagante.getSaldo() - saldo);
							//chiamare merodo cart dm per cancellare carrea dal db;
							modelCart.svuotaCart(id);
							//levare roba dal carrello di java che sta nella session.
							Carrello cartInSession = (Carrello)session.getAttribute("cart");
							cartInSession.svuotaCarrello();
							
							Cookie cks[] = request.getCookies();
							for(int i = 0; i < cks.length; i++) {
								if(cks[i].getName().equals("Saldo")) {
									/*preso il saldo dal cookie dell utente lo setto a float
									 * gli sottraggo il saldo da pagare che sta in userDm
									 * e lo rimetto nel cookie;
									 */
									String saldoUser = cks[i].getValue();
									
									float saldoUserFloatFromCookie = Float.parseFloat(saldoUser);
									saldoUserFloatFromCookie -= saldo;
									saldoUser = String.valueOf(saldoUserFloatFromCookie);
									cks[i].setMaxAge(0);
									cks[i].setPath("/");
									Cookie newCookie = new Cookie("Saldo", saldoUser);
									response.addCookie(newCookie);
 								}
							}
							
							
							session.removeAttribute("cart");
							session.setAttribute("cart", cartInSession);
							response.sendRedirect("PagamentoEffettuato.jsp");
						}
						else {
							response.sendRedirect("MyAccount.jsp");
						}
					}else {
						response.sendRedirect("MyAccount.jsp");
					}
					}
				}
			}
			
		}
		catch(SQLException e) {
			System.out.println("Error: "+ e.getMessage());
			request.setAttribute("error", e.getMessage());
		}
		
		
		
		
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
