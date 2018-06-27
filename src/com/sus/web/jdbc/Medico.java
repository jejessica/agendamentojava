package com.sus.web.jdbc;

public class Medico {

	/*	nm_Medico VARCHAR(50),
	crm_Medico INT,   
    cd_Especialidade INT,
	cd_RG INT(11),
    cd_CPF INT(11),     
	ds_Endereco VARCHAR (50),
    nm_Cidade VARCHAR(20),
    nm_Bairro VARCHAR(20),
	sg_Estado CHAR(2),
	cd_Telefone VARCHAR(20),
    ds_Email VARCHAR(50),
    dt_Nascimento DATE,
	*/
	
	private int codMedico;
	private String crmMedico;
	private String nomeMedico;
	private int especialidadeMedico;
	private String rgMedico;
	private String cpfMedico;
	private String enderecoMedico;
	private int cidadeMedico;
	private String bairroMedico;
	private String estadoMedico;
	private String emailMedico;
	private String telefoneMedico;
	private String datanascMedico;	
	
	
	
	
	public Medico(int codMedico, String crmMedico, String nomeMedico, int especialidadeMedico, String rgMedico, String cpfMedico,
			String enderecoMedico, int cidadeMedico, String bairroMedico, String estadoMedico, String emailMedico,
			String telefoneMedico, String datanascMedico) {
		this.codMedico = codMedico;
		this.crmMedico = crmMedico;
		this.nomeMedico = nomeMedico;
		this.especialidadeMedico = especialidadeMedico;
		this.rgMedico = rgMedico;
		this.cpfMedico = cpfMedico;
		this.enderecoMedico = enderecoMedico;
		this.cidadeMedico = cidadeMedico;
		this.bairroMedico = bairroMedico;
		this.estadoMedico = estadoMedico;
		this.emailMedico = emailMedico;
		this.telefoneMedico = telefoneMedico;
		this.datanascMedico = datanascMedico;
	}

	public Medico(String crmMedico, String nomeMedico, int especialidadeMedico, String rgMedico, String cpfMedico,
			String enderecoMedico, int cidadeMedico, String bairroMedico, String estadoMedico, String emailMedico,
			String telefoneMedico, String datanascMedico) {
		this.crmMedico = crmMedico;
		this.nomeMedico = nomeMedico;
		this.especialidadeMedico = especialidadeMedico;
		this.rgMedico = rgMedico;
		this.cpfMedico = cpfMedico;
		this.enderecoMedico = enderecoMedico;
		this.cidadeMedico = cidadeMedico;
		this.bairroMedico = bairroMedico;
		this.estadoMedico = estadoMedico;
		this.emailMedico = emailMedico;
		this.telefoneMedico = telefoneMedico;
		this.datanascMedico = datanascMedico;
	}

	
	
	public int getCodMedico() {
		return codMedico;
	}

	public void setCodMedico(int codMedico) {
		this.codMedico = codMedico;
	}

	public String getCrmMedico() {
		return crmMedico;
	}

	public void setCrmMedico(String crmMedico) {
		this.crmMedico = crmMedico;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public int getEspecialidadeMedico() {
		return especialidadeMedico;
	}

	public void setEspecialidadeMedico(int especialidadeMedico) {
		this.especialidadeMedico = especialidadeMedico;
	}

	public String getRgMedico() {
		return rgMedico;
	}

	public void setRgMedico(String rgMedico) {
		this.rgMedico = rgMedico;
	}

	public String getCpfMedico() {
		return cpfMedico;
	}

	public void setCpfMedico(String cpfMedico) {
		this.cpfMedico = cpfMedico;
	}

	public String getEnderecoMedico() {
		return enderecoMedico;
	}

	public void setEnderecoMedico(String enderecoMedico) {
		this.enderecoMedico = enderecoMedico;
	}

	public int getCidadeMedico() {
		return cidadeMedico;
	}

	public void setCidadeMedico(int cidadeMedico) {
		this.cidadeMedico = cidadeMedico;
	}

	public String getBairroMedico() {
		return bairroMedico;
	}

	public void setBairroMedico(String bairroMedico) {
		this.bairroMedico = bairroMedico;
	}

	public String getEstadoMedico() {
		return estadoMedico;
	}

	public void setEstadoMedico(String estadoMedico) {
		this.estadoMedico = estadoMedico;
	}

	public String getEmailMedico() {
		return emailMedico;
	}

	public void setEmailMedico(String emailMedico) {
		this.emailMedico = emailMedico;
	}

	public String getTelefoneMedico() {
		return telefoneMedico;
	}

	public void setTelefoneMedico(String telefoneMedico) {
		this.telefoneMedico = telefoneMedico;
	}

	public String getDatanascMedico() {
		return datanascMedico;
	}

	public void setDatanascMedico(String datanascMedico) {
		this.datanascMedico = datanascMedico;
	}

	@Override
	public String toString() {
		return "Medico [codMedico=" + codMedico + ", crmMedico=" + crmMedico + ", nomeMedico=" + nomeMedico
				+ ", especialidadeMedico=" + especialidadeMedico + ", rgMedico=" + rgMedico + ", cpfMedico=" + cpfMedico
				+ ", enderecoMedico=" + enderecoMedico + ", cidadeMedico=" + cidadeMedico + ", bairroMedico="
				+ bairroMedico + ", estadoMedico=" + estadoMedico + ", emailMedico=" + emailMedico + ", telefoneMedico="
				+ telefoneMedico + ", datanascMedico=" + datanascMedico + "]";
	}
	
	


	
	
	
	
}
