package com.sus.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import java.text.*;


public class AgendamentoDbUtil {

	private DataSource dataSource;

	public AgendamentoDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
public List<Agendamento> getAgendamentosFilter(String nomePesquisa) throws Exception {
		
		List<Agendamento> agendamentos = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myConn = dataSource.getConnection();
						
		//	nomePesquisa = ("'%" + nomePesquisa + "%'");
			String sql = "select * from agendamento where nm_Paciente like '%" + nomePesquisa +  "%' order by dt_Agendamento";
		
			
			myStmt = myConn.prepareStatement(sql);
			
			//myStmt.setString(1, nomePesquisa);
			//myStmt.setString(2, agendamento.getHoraAgendamento());
						
			myRs = myStmt.executeQuery(sql);
				
			while (myRs.next()) {
								
				int codAgendamento = myRs.getInt("cd_Agendamento");
				String dtAgendamento = myRs.getString("dt_Agendamento");
				String horaAgendamento = myRs.getString("dt_Hora");
				int codPaciente = myRs.getInt("nm_Paciente");
				int codMedico = myRs.getInt("cd_Medico");
				int codEspecialidade = myRs.getInt("cd_Especialidade");	
				String statusAgendamento = myRs.getString("ds_Status");
								
				Agendamento tempAgendamento = new Agendamento(codAgendamento, dtAgendamento, codPaciente, codMedico, codEspecialidade,
						horaAgendamento, statusAgendamento);
								
				agendamentos.add(tempAgendamento);				
			}
			
			return agendamentos;		
		}
		finally {
			
			close(myConn, myStmt, myRs);
		}		
	}

	public List<Agendamento> getAgendamentos() throws Exception {
		
		List<Agendamento> agendamentos = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			myConn = dataSource.getConnection();
						
			String sql = "select * from agendamento order by dt_Agendamento";
			
			myStmt = myConn.createStatement();
						
			myRs = myStmt.executeQuery(sql);
				
			while (myRs.next()) {
								
				int codAgendamento = myRs.getInt("cd_Agendamento");
				String dtAgendamento = myRs.getString("dt_Agendamento");
				String horaAgendamento = myRs.getString("dt_Hora");
				int codPaciente = myRs.getInt("nm_Paciente");
				int codMedico = myRs.getInt("cd_Medico");
				int codEspecialidade = myRs.getInt("cd_Especialidade");	
				String statusAgendamento = myRs.getString("ds_Status");
								
				Agendamento tempAgendamento = new Agendamento(codAgendamento, dtAgendamento, codPaciente, codMedico, codEspecialidade,
						horaAgendamento, statusAgendamento);
								
				agendamentos.add(tempAgendamento);				
			}
			
			return agendamentos;		
		}
		finally {
			
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();  
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	
	//não há adição de agendamento via aplicação
	public void addAgendamento(Agendamento agendamento) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
	
		try {
			
			myConn = dataSource.getConnection();
						
			String sql = "insert into agendamento "
					   + "(dt_Agendamento, dt_Hora, nm_Paciente, cd_Medico, cd_Especialidade, ds_Status) "
					   + "values (?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
						
			myStmt.setString(1, agendamento.getDtAgendamento());
			myStmt.setString(2, agendamento.getHoraAgendamento());
			myStmt.setInt(3, agendamento.getPacienteAgendamento());
			myStmt.setInt(4, agendamento.getMedicoAgendamento());
			myStmt.setInt(5, agendamento.getEspecialidadeAgendamento());
			myStmt.setString(6, agendamento.getStatusAgendamento());
			
			myStmt.execute();
		}
		finally {
			
			close(myConn, myStmt, null);
		}
	}

	public Agendamento getAgendamento(String paramAgendamentoId) throws Exception {

		Agendamento agendamento = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int localAgendamentoId;
		
		try {
			
			localAgendamentoId = Integer.parseInt(paramAgendamentoId);
						
			myConn = dataSource.getConnection();
						
			String sql = "select * from agendamento where cd_Agendamento=?";
						
			myStmt = myConn.prepareStatement(sql);
						
			myStmt.setInt(1, localAgendamentoId);
						
			myRs = myStmt.executeQuery();
						
			if (myRs.next()) {
				String dtAgendamento = myRs.getString("dt_Agendamento");
				
				//String substring(int, int)
				//20-06-2018
				//0123456789
				String auxData;
				auxData = dtAgendamento.substring(6,10) + "-" + dtAgendamento.substring(3, 5) + "-" + dtAgendamento.substring(0,2);
				dtAgendamento = auxData;
				
				
				String horaAgendamento = myRs.getString("dt_Hora");
				int codPaciente = myRs.getInt("nm_Paciente");
				int codMedico = myRs.getInt("cd_Medico");
				int codEspecialidade = myRs.getInt("cd_Especialidade");
				String statusAgendamento = myRs.getString("ds_Status");
								
				agendamento = new Agendamento(localAgendamentoId, dtAgendamento, codPaciente, codMedico, codEspecialidade,
						horaAgendamento, statusAgendamento);
			}
			else {
				throw new Exception("Não foi possível encontrar agendamento: " + localAgendamentoId);
			}				
			
			return agendamento;
		}
		finally {
			
			close(myConn, myStmt, myRs);
		}
	}

	//não há atualização via aplicação
	public void updateAgendamento(Agendamento agendamento) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = dataSource.getConnection();
			
			String sql = "update agendamento "
						+ "set dt_Agendamento=?, dt_Hora = ?, nm_Paciente=?, cd_Medico=?, cd_Especialidade=?, ds_Status=? "
						+ "where cd_Agendamento=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			
			myStmt.setString(1, agendamento.getDtAgendamento());
			myStmt.setString(2, agendamento.getHoraAgendamento());
			myStmt.setInt(3, agendamento.getPacienteAgendamento());
			myStmt.setInt(4, agendamento.getMedicoAgendamento());
			myStmt.setInt(5, agendamento.getEspecialidadeAgendamento());
			myStmt.setString(6, agendamento.getStatusAgendamento());
			
			myStmt.setInt(7, agendamento.getCodAgendamento());

			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}

	public void deleteAgendamento(String agendamentoId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			int localAgendamentoId = Integer.parseInt(agendamentoId);
			
			myConn = dataSource.getConnection();
			
			String sql = "delete from agendamento where cd_Agendamento=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, localAgendamentoId);
			
			myStmt.execute();
		}
		finally {
			
			close(myConn, myStmt, null);
		}	
	}
}















