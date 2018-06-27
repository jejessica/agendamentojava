package com.sus.web.jdbc;

public class Cidade {

	private int codCidade;
	private String sgEstado;
	private String nomeCidade;
	
	public Cidade(int codCidade, String sgEstado, String nomeCidade) {
		this.codCidade = codCidade;
		this.sgEstado = sgEstado;
		this.nomeCidade = nomeCidade;
	}

	public Cidade(String sgEstado, String nomeCidade) {
		this.sgEstado = sgEstado;
		this.nomeCidade = nomeCidade;
	}

	public String getSgEstado() {
		return sgEstado;
	}

	public void setSgEstado(String sgEstado) {
		this.sgEstado = sgEstado;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	
	
	public int getCodCidade() {
		return codCidade;
	}

	public void setCodCidade(int codCidade) {
		this.codCidade = codCidade;
	}

	@Override
	public String toString() {
		return "Cidade [codCidade=" + codCidade + ", sgEstado=" + sgEstado + ", nomeCidade=" + nomeCidade + "]";
	}

	

	

	
	
	

	
	
	
	
}
