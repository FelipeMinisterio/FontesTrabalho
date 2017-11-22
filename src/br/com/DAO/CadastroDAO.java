package br.com.DAO;

import br.com.model.Cadastro;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import com.mysql.jdbc.Statement;

public class CadastroDAO {
	public void inserir(Cadastro cadastro)throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO cadastro(nome,sobrenome,email,cpf,numerocartao,dataval,numseg,senha) VALUES(?,?,?,?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, cadastro.getNome());
			pstm.setString(2, cadastro.getSobrenome());
			pstm.setString(3, cadastro.getEmail());
			pstm.setString(4, cadastro.getCPF());
			pstm.setString(5, cadastro.getNumerocartao());
			pstm.setString(6, cadastro.getDataval());
			pstm.setString(7, cadastro.getNumseg());
			pstm.setString(8, cadastro.getSenha());
			pstm.execute();

			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				int lastId = rs.getInt(1);
				cadastro.setId(lastId);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		con.close();
	}

	public Cadastro login(String email, String senha) throws SQLException, ClassNotFoundException, IOException {
		RequestContext context = RequestContext.getCurrentInstance();
		boolean loggedIn = false;
		FacesMessage message = null;

		Connection con = null;
		PreparedStatement pstm = null;

		String sql = "SELECT * FROM cadastro,pacote WHERE email= ? && senha= ?";
		try {
			Cadastro cad = new Cadastro();
			con = ConnectionFactory.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, email);
			pstm.setString(2, senha);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				cad.setNome(rs.getString("nome"));
				cad.setCPF(rs.getString("CPF"));
				cad.setEmail(rs.getString("email"));
				cad.setId(rs.getInt("id_cad"));
				cad.setNumerocartao(rs.getString("numerocartao"));
				cad.setSobrenome(rs.getString("sobrenome"));
				cad.setDataval(rs.getString("dataval"));
				cad.setNumseg(rs.getString("numseg"));
				loggedIn = true;
				System.out.println("Login Sucessful!");
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", cad.getNome());
				FacesContext.getCurrentInstance().getExternalContext().redirect("Filmes.xhtml");
				Cadastro localCadastro1 = cad;
				return localCadastro1;
			}
			System.out.println("Login Error!");
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Credenciais Inválidas");
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
		} finally {
			FacesContext.getCurrentInstance().addMessage(null, message);
			context.addCallbackParam("loggedIn", Boolean.valueOf(loggedIn));
			con.close();
		}
		return null;
	}

	public String excluir(Cadastro cadastro)throws SQLException, ClassNotFoundException {
		String sql = "delete from cadastro where id=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cadastro.getId());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Excluído com sucesso.";
			}
			return "Erro ao excluir.";
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			con.close();
		}
	}

	public List<Cadastro> lista() throws SQLException, ClassNotFoundException {
		String sql = "select * from cadastro";
		Connection con = ConnectionFactory.getConnection();
		List<Cadastro> lista = new ArrayList<Cadastro>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cadastro cd = new Cadastro();
					cd.setNome(rs.getString("nome"));
					cd.setCPF(rs.getString("CPF"));
					cd.setEmail(rs.getString("email"));
					cd.setId(rs.getInt("id_cad"));
					cd.setNumerocartao(rs.getString("numerocartao"));
					cd.setSobrenome(rs.getString("sobrenome"));
					cd.setDataval(rs.getString("dataval"));
					cd.setNumseg(rs.getString("numseg"));
					lista.add(cd);
				}
				return lista;
			}
			return null;
		} catch (SQLException e) {
			return null;
		} finally {
			con.close();
		}
	}
}
