package com.sus.web.jdbc;

public class Especialidade {

	private int codEspecialidade;
	private String nomeEspecialidade;

	public Especialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}

	public Especialidade(int codEspecialidade, String nomeEspecialidade) {
		this.codEspecialidade = codEspecialidade;
		this.nomeEspecialidade = nomeEspecialidade;
	}


	public int getCodEspecialidade() {
		return codEspecialidade;
	}

	public void setCodEspecialidade(int codEspecialidade) {
		this.codEspecialidade = codEspecialidade;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}

	@Override
	public String toString() {
		return "Especialidade [codEspecialidade=" + codEspecialidade + 
				", nomeEspecialidade=" + nomeEspecialidade + "]";
	}	
}
