package br.com.controller;

import br.com.DAO.CadastroDAO;
import br.com.model.Cadastro;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;

@SessionScoped
@ManagedBean
public class CadastroBean {
	private Cadastro cliente = new Cadastro();
	private boolean skip;

	public void redirecionar(String html) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(html);
	}

	public void insert() throws ClassNotFoundException, SQLException, IOException {
		new CadastroDAO().inserir(this.cliente);
		FacesContext.getCurrentInstance().getExternalContext().redirect("Pagamento.xhtml");
		FacesContext.getCurrentInstance().getAttributes().get(Integer.valueOf(this.cliente.getId()));
	}

	public void login(ActionEvent event)throws ClassNotFoundException, SQLException, IOException {
		this.cliente = new CadastroDAO().login(this.cliente.getEmail(), this.cliente.getSenha());
	}

	public void list()/*    */ throws ClassNotFoundException, SQLException {
		new CadastroDAO().lista();
	}

	public boolean isSkip() {
		return this.skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Cadastro getCliente() {
		return this.cliente;
	}

	public void setCliente(Cadastro cliente) {
		this.cliente = cliente;
	}

	public String onFlowProcess(FlowEvent event) {
		if (this.skip) {
			this.skip = false;
			return "confirm";
		}
		return event.getNewStep();
	}
	
	
}
