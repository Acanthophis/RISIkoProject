package web.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.interceptors.ResultSetScannerInterceptor;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class CartModelDM{

	public Carrello getCart(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT idProdotto, quantita FROM CARREA WHERE idUtente = ?";
		ProductModelDM model = new ProductModelDM();
		Carrello cart = new Carrello();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, id);
			
			System.out.println("getItemByKey:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while(rs.next()) {	
				ProdottoInTheCart prodCart = new ProdottoInTheCart();
				Prodotto prod= model.getItemByKey(rs.getString(1));
				prodCart.setNome(prod.getNome());
				prodCart.setPrezzo(prod.getPrezzo());
				prodCart.setCategoria(prod.getCategoria());
				prodCart.setNgiocatori(prod.getNgiocatori());
				prodCart.setDateRel(prod.getDateRel());
				prodCart.setDescrp(prod.getDescrp());
				prodCart.setId(prod.getId());
				prodCart.setQuantity(rs.getInt("quantita"));
				cart.addElement(prodCart);
			}		
		}
		finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		return cart;
	}



	public void addToCart(String idUtente, String idProdotto, String quantit) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int quantita = Integer.parseInt(quantit);
		try {
			
			String insertSQL = "select quantita from carrea where idUtente = ? && idProdotto = ?;";
		
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(2, idUtente);
			preparedStatement.setString(1, idProdotto);
			System.out.println("doSave: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				updateToCart(idUtente, idProdotto, rs.getInt("quantita") + quantita);
				System.out.println(rs.getInt("quantita"));
			}
			else {
				insertToCart(idUtente, idProdotto, quantita);
			}
		}
		 finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
	}

	public boolean doDelete(String idUtente, String idProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		try {
			String deleteSQL = "DELETE FROM Carrea WHERE idUtente = ? && idProdotto = ?";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, idUtente);
			preparedStatement.setString(2, idProdotto);

			System.out.println("doDelete: "+ preparedStatement.toString());
			result = preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

/*
Connection con = null;
Statement st = null;
ResultSet rs = null;
InputStreamReader keyIS;
BufferedReader keyBR;

keyIS = new InputStreamReader(System.in);
keyBR = new BufferedReader(keyIS);


try {
	con = DBConnectionPool.getConnection();
	st = con.createStatement();
	
	rs = st.executeQuery("SELECT * FROM Militare WHERE NOT EXISTS (SELECT * FROM Partecipa WHERE militare.matricola = partecipa.matricolaMilitare);");
	printResult(rs);
	selectWhatUWant("guarnigione");
	System.out.println("Inserisci la matricola di un Militare ");
	int matricolaMilitare =Integer.parseInt(keyBR.readLine());
	
	System.out.println("Inserisci il codice di una Guarnigione");
	int codiceGuarnigione =Integer.parseInt(keyBR.readLine());
	
	int n = st.executeUpdate("INSERT INTO partecipa " + "VALUES(" + matricolaMilitare + ", " + codiceGuarnigione + ");");
	if (n > 0) {
		System.out.println("Query OK!");
	} else {
		System.out.println("Impossibile inserire il record");
	}
} catch (SQLException e) {
	printSQLException(e);
} catch (IOException e) {
	e.printStackTrace();
} finally {
	try {
		if (st != null)
			st.close();
		DBConnectionPool.releaseConnection(con);
	} catch (SQLException e) {
		printSQLException(e);
	}
}

}
*/


	private void insertToCart(String idUtente, String idProdotto, int quantita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {	
			String insertSQL = "INSERT INTO CARREA (idUtente, idProdotto, quantita) VALUES (?, ?, ?)";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(2, idUtente);
			preparedStatement.setString(1, idProdotto);
			preparedStatement.setInt(3, quantita);
			System.out.println("doInsert: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();

		connection.commit();
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	private void updateToCart(String idUtente, String idProdotto, int quantita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {	
			String insertSQL = "UPDATE CARREA SET quantita = ? WHERE idUtente = ? && idProdotto = ?";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(3, idUtente);
			preparedStatement.setString(2, idProdotto);
			preparedStatement.setInt(1, quantita);
			System.out.println("Update: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public void svuotaCart(String idUtente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			String deleteCartSQL = "DELETE FROM CARREA WHERE idUtente = ?";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteCartSQL);
			preparedStatement.setString(1, idUtente);
			System.out.println("DeleteCart: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();
			
			
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	
	
}