package com.sus.web.jdbc;

public class Usuario {

	/*CREATE TABLE Usuario(
	cd_Usuario INT,
	nm_Usuario VARCHAR(20),
    nm_Login VARCHAR(10),
    ds_Senha VARCHAR(10),
    ds_Usuario VARCHAR(10),
    PRIMARY KEY (cd_Usuario)
);
	*/
	
	private int codUsuario;
	private String nomeUsuario;
	private String loginUsuario;
	private String senhaUsuario;
	private String dsUsuario;
	

	public Usuario(int codUsuario, String nomeUsuario, String loginUsuario, String senhaUsuario, String dsUsuario) {
		this.codUsuario = codUsuario;
		this.nomeUsuario = nomeUsuario;
		this.loginUsuario = loginUsuario;
		this.senhaUsuario = senhaUsuario;
		this.dsUsuario = dsUsuario;
	}


	

	public Usuario(String nomeUsuario, String loginUsuario, String senhaUsuario, String dsUsuario) {
		this.nomeUsuario = nomeUsuario;
		this.loginUsuario = loginUsuario;
		this.senhaUsuario = senhaUsuario;
		this.dsUsuario = dsUsuario;
	}



	public int getCodUsuario() {
		return codUsuario;
	}




	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}




	public String getNomeUsuario() {
		return nomeUsuario;
	}




	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}




	public String getLoginUsuario() {
		return loginUsuario;
	}




	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}




	public String getSenhaUsuario() {
		return senhaUsuario;
	}




	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}




	public String getDsUsuario() {
		return dsUsuario;
	}




	public void setDsUsuario(String dsUsuario) {
		this.dsUsuario = dsUsuario;
	}




	@Override
	public String toString() {
		return "Usuario [codUsuario=" + codUsuario + ", nomeUsuario=" + nomeUsuario + ", loginUsuario=" + loginUsuario
				+ ", senhaUsuario=" + senhaUsuario + ", dsUsuario=" + dsUsuario + "]";
	}	
	
	
	

	
	
	
	
}
