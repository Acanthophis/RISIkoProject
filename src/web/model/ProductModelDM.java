package web.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class ProductModelDM {

	public Prodotto getItemByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prodotto product = new Prodotto();
		String selectSQL = "SELECT * FROM prodotto WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, id);
			
			System.out.println("getItemByKey:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {				
				product.setId(rs.getString("id"));
				product.setPrezzo(rs.getFloat("prezzo"));
				product.setNome(rs.getString("nome"));
				product.setCategoria(rs.getString("categoria"));
				product.setNgiocatori(rs.getInt("nGiocatori"));
				product.setDateRel(rs.getString("dataUscita"));
				product.setDescrp(rs.getString("descrizione"));
			}
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		return product;
	}

	public ArrayList<Prodotto> getCollection(String order) throws SQLException {
		System.out.println("ciao1");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Prodotto> products = new ArrayList<Prodotto>();
		
		String selectSQL = "SELECT * FROM prodotto";
		
	/*	if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}*/
		
		try {
			System.out.println("ciao1");
			connection = DriverManagerConnectionPool.getConnection();
			System.out.println("ciao2");
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("getCollection:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Prodotto product = new Prodotto();	
				product.setId(rs.getString("id"));
				product.setPrezzo(rs.getFloat("prezzo"));
				product.setNome(rs.getString("nome"));
				product.setCategoria(rs.getString("categoria"));
				product.setNgiocatori(rs.getInt("nGiocatori"));
				product.setDateRel(rs.getString("dataUscita"));
				product.setDescrp(rs.getString("descrizione"));
				products.add(product);
			}
		} 
		catch (SQLException e) {
			System.out.println("errore");
		}
			finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return products;
	}

	public void doSave(Prodotto item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			String insertSQL = "INSERT INTO Prodotto (id, nome, prezzo, descrizione, dataUscita, categoria, nGiocatori) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, item.getId());
			preparedStatement.setString(2, item.getNome());
			preparedStatement.setFloat(3, item.getPrezzo());
			preparedStatement.setString(4, item.getDescrp());
			preparedStatement.setString(5, item.getDateRel());
			preparedStatement.setString(6, item.getCategoria());
			preparedStatement.setInt(7, item.getNgiocatori());
			System.out.println("doSave: "+ preparedStatement.toString());
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

	public void doUpdate(Prodotto item) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String insertSQL = "UPDATE Prodotto"
				+ "SET prezzo = ?"
				+ " WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setFloat(1, item.getPrezzo());
			preparedStatement.setString(2, item.getId());
			
			System.out.println("doUpdate: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public boolean doDelete(String item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		try {
			String deleteSQL = "DELETE FROM Prodotto WHERE id = ?";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, item);

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
}
