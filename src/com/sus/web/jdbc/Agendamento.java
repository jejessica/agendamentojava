package com.sus.web.jdbc;
 
public class Agendamento {
		
	public Agendamento(int codAgendamento, String dtAgendamento, int pacienteAgendamento, int medicoAgendamento, int especialidadeAgendamento,
			String horaAgendamento, String statusAgendamento) {
		 
		this.codAgendamento = codAgendamento;
		this.dtAgendamento = dtAgendamento;
		this.horaAgendamento = horaAgendamento;
		this.pacienteAgendamento = pacienteAgendamento;
		this.medicoAgendamento = medicoAgendamento;
		this.especialidadeAgendamento = especialidadeAgendamento;
		this.statusAgendamento = statusAgendamento;
		
	}
	
	public Agendamento(int codAgendamento, String dtAgendamento, String horaAgendamento, int medicoAgendamento, int especialidadeAgendamento,  int pacienteAgendamento, 
			 String statusAgendamento) {
		 
		this.codAgendamento = codAgendamento;
		this.dtAgendamento = dtAgendamento;
		this.horaAgendamento = horaAgendamento;
		this.pacienteAgendamento = pacienteAgendamento;
		this.medicoAgendamento = medicoAgendamento;
		this.especialidadeAgendamento = especialidadeAgendamento;
		this.statusAgendamento = statusAgendamento;
		
	}
	
	public Agendamento(String dtAgendamento, String horaAgendamento, int medicoAgendamento, int especialidadeAgendamento,
			int pacienteAgendamento, String statusAgendamento) {
	 
		this.dtAgendamento = dtAgendamento;
		this.horaAgendamento = horaAgendamento;
		this.pacienteAgendamento = pacienteAgendamento;
		this.medicoAgendamento = medicoAgendamento;
		this.especialidadeAgendamento = especialidadeAgendamento;
		this.statusAgendamento = statusAgendamento;
	}
	
	private int codAgendamento;
	private String dtAgendamento; 
	private String horaAgendamento;
	private int pacienteAgendamento;
	private int medicoAgendamento;
	private int especialidadeAgendamento;
	private String statusAgendamento;
	
	
	public int getEspecialidadeAgendamento() {
		return especialidadeAgendamento;
	}
	public void setEspecialidadeAgendamento(int especialidadeAgendamento) {
		this.especialidadeAgendamento = especialidadeAgendamento;
	}
	public int getCodAgendamento() {
		return codAgendamento;
	}
	public void setCodAgendamento(int codAgendamento) {
		this.codAgendamento = codAgendamento;
	}
	public String getDtAgendamento() {
		return dtAgendamento;
	}
	public void setDtAgendamento(String dtAgendamento) {
		this.dtAgendamento = dtAgendamento;
	}
	public int getPacienteAgendamento() {
		return pacienteAgendamento;
	}
	public void setPacienteAgendamento(int pacienteAgendamento) {
		this.pacienteAgendamento = pacienteAgendamento;
	}
	public int getMedicoAgendamento() {
		return medicoAgendamento;
	}
	public void setMedicoAgendamento(int medicoAgendamento) {
		this.medicoAgendamento = medicoAgendamento;
	}
	

	public String getHoraAgendamento() {
		return horaAgendamento;
	}
	public void setHoraAgendamento(String horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}
	public String getStatusAgendamento() {
		return statusAgendamento;
	}
	public void setStatusAgendamento(String statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}
	@Override
	public String toString() {
		return "Agendamento [dtAgendamento=" + dtAgendamento + ", pacienteAgendamento=" + pacienteAgendamento + ", medicoAgendamento="
				+ medicoAgendamento + ", codAgendamento=" + codAgendamento + "]";
	}
	
}
