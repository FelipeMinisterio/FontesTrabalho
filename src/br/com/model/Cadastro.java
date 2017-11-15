package br.com.model;

public class Cadastro {
	private int id;
	private String nome;
	private String sobrenome;
	private String email;
	private String CPF;
	private String numerocartao;
	private String dataval;
	private String numseg;
	private String senha;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCPF() {
		return this.CPF;
	}

	public void setCPF(String cPF) {
		this.CPF = cPF;
	}

	public String getNumerocartao() {
		return this.numerocartao;
	}

	public void setNumerocartao(String numerocartao) {
		this.numerocartao = numerocartao;
	}

	public String getDataval() {
		return this.dataval;
	}

	public void setDataval(String dataval) {
		this.dataval = dataval;
	}

	public String getNumseg() {
		return this.numseg;
	}

	public void setNumseg(String numseg) {
		this.numseg = numseg;
	}
}