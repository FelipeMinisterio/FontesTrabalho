package br.com.controller;

import br.com.DAO.PacoteDAO;
import br.com.model.Pacote;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public class PacoteBean {
	private Pacote pacote = new Pacote();

	public Pacote getPacote() {
		return this.pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	public void insert(String nome, double valor, int id_cadpac)
			throws ClassNotFoundException, SQLException, IOException {
		this.pacote = new PacoteDAO().inserir(nome, valor, id_cadpac);
		System.out.println(this.pacote.getPac_nome());
		FacesContext.getCurrentInstance().getExternalContext().redirect("Perfil.xhtml");
	}

	public void procura(int id_cadpac) throws ClassNotFoundException, SQLException {
		this.pacote = new PacoteDAO().seleciona(id_cadpac);
		System.out.println();
	}
}
