package br.com.model;

public class Pacote {
	private String pac_nome;
	private Double valor;
	private int id_pac;

	public int getId_pac() {
		return this.id_pac;
	}

	public void setId_pac(int id_pac) {
		this.id_pac = id_pac;
	}

	public String getPac_nome() {
		return this.pac_nome;
	}

	public void setPac_nome(String pac_nome) {
		this.pac_nome = pac_nome;
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
