package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Contas;
import connection.SingleConnection;

public class DaoContas {

	private Connection connection;
	
	public DaoContas() {

		connection = SingleConnection.getConnection();
 
	}
	
	public void salvar(Contas contas) {
		
		String sql = "insert into contas(nome, valor ) values (?, ?)";
		try {
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, contas.getNome());
			statement.setDouble(2, contas.getValor());
			statement.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
				
			} catch (Exception e2) {
				
			}
		}
	}
	
	public List<Contas> listar() throws Exception {
		List<Contas> listar = new ArrayList<Contas>();

		String sql = "select * from contas";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Contas contas = new Contas();
			contas.setId(resultSet.getLong("id"));
			contas.setNome(resultSet.getString("nome"));
			contas.setValor(resultSet.getDouble("valor"));

			
			listar.add(contas);
		}

		return listar;
	}
	
}
