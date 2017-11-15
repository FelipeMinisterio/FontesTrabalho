package br.com.DAO;

import br.com.model.Pacote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacoteDAO {
	Pacote pac = new Pacote();

	public Pacote inserir(String nome, double valor, int id_cadpac) throws SQLException, ClassNotFoundException {
		Pacote pac = new Pacote();
		String sql = "INSERT INTO pacote(pac_nome,valor,cad_pac) VALUES(?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, nome);
			pstm.setDouble(2, valor);
			pstm.setInt(3, id_cadpac);
			pstm.execute();
			pac.setPac_nome(nome);
			pac.setId_pac(id_cadpac);
			pac.setValor(Double.valueOf(valor));
		} catch (Exception e) {
			e.getMessage();
		}
		con.close();
		return pac;
	}

	public Pacote seleciona(int id_cadpac) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * from pacote(pac_nome,valor,cad_pac) WHERE cad_pac = ?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, id_cadpac);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				this.pac.setPac_nome(rs.getString("pac_nome"));
				this.pac.setValor(Double.valueOf(rs.getDouble("valor")));
				this.pac.setId_pac(rs.getInt("cad_pac"));

				return this.pac;
			}
		} catch (Exception e) {
			e.getMessage();

			con.close();
		}
		return null;
	}
}
