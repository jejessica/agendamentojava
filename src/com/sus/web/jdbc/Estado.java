package com.sus.web.jdbc;

public class Estado {

	
	private String sgEstado;
	private String nomeEstado;
	
	public Estado(String sgEstado, String nomeEstado) {

		this.sgEstado = sgEstado;
		this.nomeEstado = nomeEstado;
	}

	public Estado(String nomeEstado) {

		this.nomeEstado = nomeEstado;
	}

	public String getSgEstado() {
		return sgEstado;
	}

	public void setSgEstado(String sgEstado) {
		this.sgEstado = sgEstado;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	@Override
	public String toString() {
		return "Estado [sgEstado=" + sgEstado + ", nomeEstado=" + nomeEstado + "]";
	}

	

	
	
	

	
	
	
	
}
