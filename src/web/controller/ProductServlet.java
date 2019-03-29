package web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.model.Prodotto;
import web.model.ProductModelDM;


@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ProductModelDM model = new ProductModelDM();

    public ProductServlet() {
        super();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		try {
			if(action!=null) {
				if(action.equalsIgnoreCase("viewProduct")) {
					Prodotto prod = model.getItemByKey("12345678");
					request.setAttribute("product", prod);
					//response.sendRedirect("Prodotti.jsp");
					
				}
				else if(action.equalsIgnoreCase("viewAllProducts")) {
					ArrayList<Prodotto> products = new ArrayList<Prodotto>();
					products = model.getCollection("asc");
					HttpSession session = request.getSession(); 
					session.removeAttribute("products");
					session.setAttribute("products", products);
					response.sendRedirect("Prodotti.jsp");

				}
				else if(action.equalsIgnoreCase("Delete")) {
					String id = request.getParameter("idProdotto");
					model.doDelete(id);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Prodotti.jsp");
					dispatcher.include(request, response);
				}
				else if(action.equalsIgnoreCase("Insert")) {
					String id = request.getParameter("idProdotto");
					String nome = request.getParameter("nomeProdotto");
					float prezzo = Float.parseFloat(request.getParameter("prezzoProdotto"));
					String description = request.getParameter("descrizioneProdotto");
					String dateRelease = request.getParameter("dataUscitaProdotto");
					String categoria = request.getParameter("categoriaProdotto");
					int nGiocatori = Integer.parseInt(request.getParameter("nGiocatoriProdotto"));
					
					Prodotto item = new Prodotto();
					item.setId(id);
					item.setNome(nome);
					item.setPrezzo(prezzo);
					item.setDescrp(description);
					item.setDateRel(dateRelease);
					item.setCategoria(categoria);
					item.setNgiocatori(nGiocatori);
					model.doSave(item);
					response.sendRedirect("Prodotti.jsp");
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
