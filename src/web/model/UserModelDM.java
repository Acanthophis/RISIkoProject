package web.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class UserModelDM{

	
	public Utente getItemByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Utente user = new Utente();
		String selectSQL = "SELECT * FROM utente WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, id);
			
			System.out.println("getItemByKey:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {				
				user.setId(rs.getString("id"));
				user.setName(rs.getString("nome"));
				user.setSurname(rs.getString("cognome"));
				user.setPsw(rs.getString("pswd"));
				user.setEmail(rs.getString("email"));
				user.setSaldo(rs.getFloat("saldo"));
				user.setRole(rs.getString("role"));
			}
	
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		return user;
	}
	

	
	public ArrayList<Utente> getCollection(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatament = null;
		
		ArrayList<Utente> users = new ArrayList<Utente>();
		
		String selectSQL = "SELECT * FROM Utente";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatament = connection.prepareStatement(selectSQL);
			
			System.out.println("getCollection:" + preparedStatament.toString());
			
			ResultSet rs = preparedStatament.executeQuery();
			
			while(rs.next()) {
				Utente user = new Utente();	
				user.setId(rs.getString("id"));
				user.setName(rs.getString("nome"));
				user.setSurname(rs.getString("cognome"));
				user.setPsw(rs.getString("pswd"));
				user.setEmail(rs.getString("email"));
				user.setSaldo(rs.getFloat("saldo"));
				user.setRole(rs.getString("role"));
				users.add(user);
			}
		} finally {
			try {
				if(preparedStatament != null)
					preparedStatament.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return users;
	}	


	public void doSave(Utente item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			String insertSQL = "INSERT INTO Utente (id, pswd, nome, cognome, email, saldo, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
			connection = DriverManagerConnectionPool.getConnection();
			System.out.println("opesce mammat");
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, item.getId());
			preparedStatement.setString(2, item.getPsw());
			preparedStatement.setString(3, item.getName());
			preparedStatement.setString(4, item.getSurname());
			preparedStatement.setString(7, "user");
			preparedStatement.setFloat(6, item.getSaldo());
			preparedStatement.setString(5, item.getEmail());
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

	
	
	public boolean doDelete(String item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		try {
			String deleteSQL = "DELETE FROM Utente WHERE id = ?";
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
	
	
	public void lvUp(String id) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String deleteSQL = "UPDATE Utente "+"SET role = ?"+" WHERE id = ?";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(2, id);
			preparedStatement.setString(1, "administrator");
			System.out.println("doUpdate: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
public void lvDown(String id) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String deleteSQL = "UPDATE Utente "+"SET role = ?"+" WHERE id = ?";
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(2, id);
			preparedStatement.setString(1, "user");//il role non so che colonna sia
			System.out.println("doUpdate: "+ preparedStatement.toString());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

public void Paga(String id,float saldoPagare) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	try {
		
		String paySQL = "UPDATE Utente "+"SET saldo = ?"+" WHERE id = ?";
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(paySQL);
		preparedStatement.setFloat(1, saldoPagare);
		preparedStatement.setString(2, id);
		System.out.println("doUpdate: "+ preparedStatement.toString());
		
		preparedStatement.executeUpdate();
		connection.commit();
		
	}finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		}finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}	
}

public void Ricarica(String id , float ric) throws SQLException {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
try {
		
		String ricSQL = "UPDATE Utente "+"SET saldo = ?"+" WHERE id = ?";
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(ricSQL);
		preparedStatement.setFloat(1, ric);
		preparedStatement.setString(2, id);
		System.out.println("doRicarica: "+ preparedStatement.toString());
		
		preparedStatement.executeUpdate();
		connection.commit();
		
	}finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		}finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}	
	
	
	
}

public ArrayList<Utente> getAllAdmin() throws SQLException{
	ArrayList<Utente> admins  = new ArrayList<>();

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	try {
		String adminSQL = "Select * From utente where role = 'administrator'";
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(adminSQL);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			Utente user = new Utente();	
			user.setId(rs.getString("id"));
			user.setName(rs.getString("nome"));
			user.setSurname(rs.getString("cognome"));
			user.setPsw(rs.getString("pswd"));
			user.setEmail(rs.getString("email"));
			user.setSaldo(rs.getFloat("saldo"));
			user.setRole(rs.getString("role"));
			admins.add(user);
		}
		
		
	}finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		}finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	return admins;
	}
	

public void sendEmail(String txt , Utente adminRicevente , String idMittente) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	String adminId = adminRicevente.getId();
	try {
		System.out.println("eccomi");
		String emailSQL = "INSERT INTO Casella (idProprietario , messaggio , idMittente) Values (?,?,?)";
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(emailSQL);
		preparedStatement.setString(2, txt);
		preparedStatement.setString(1, adminId);
		preparedStatement.setString(3, idMittente);
		System.out.println("doUpdate: "+ preparedStatement.toString());
		preparedStatement.executeUpdate();
		connection.commit();
		
	}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

public Casella getAllEmails(String idProprietario) throws SQLException{
	Casella casella = new Casella();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	try {
		String caselleSQL = "Select messaggio,idMittente from casella where idProprietario = ?";
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(caselleSQL);
		preparedStatement.setString(1, idProprietario);
		System.out.println("getAllEmails: "+ preparedStatement.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			Messaggio mex = new Messaggio();
			mex.setMittente(rs.getString(2));
			mex.setDestinatario(idProprietario);
			mex.setMessage(rs.getString(1));
			casella.addMessaggio(mex);
		}
		
	}finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		}finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	
	}
	return casella;
	
}

public void deleteEmail(String idPr) throws SQLException{
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	try {
		String deleteEmailSQL = "DELETE FROM Casella WHERE idProprietario = ?";
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement(deleteEmailSQL);
		preparedStatement.setString(1, idPr);
		System.out.println("deleteEmail: "+ preparedStatement.toString());
		preparedStatement.executeUpdate();
		connection.commit();
		
	}finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		}finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	
	}
	
	
	
}
	
	
	
	
}
