package com.sus.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MedicoDbUtil {

	private DataSource dataSource;

	public MedicoDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Medico> getMedicos() throws Exception {
		
		List<Medico> medicos = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from medico order by nm_Medico";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				/*nm_Medico VARCHAR(50),
	crm_Medico INT,   
    cd_Especialidade VARCHAR(10),
	cd_RG INT(11),
    cd_CPF INT(11),     
	ds_Endereco VARCHAR (50),
    nm_Cidade VARCHAR(20),
    nm_Bairro VARCHAR(20),
	sg_Estado CHAR(2),
	cd_Telefone VARCHAR(20),
    ds_Email VARCHAR(50),
    dt_Nascimento DATE,*/
				
				// retrieve data from result set row
				int codMedico = myRs.getInt("cd_Medico");
				String crmMedico = myRs.getString("crm_Medico");
				String nomeMedico = myRs.getString("nm_Medico");
				int especialidadeMedico = myRs.getInt("cd_Especialidade");
				String rgMedico = myRs.getString("cd_RG");
				String cpfMedico = myRs.getString("cd_CPF");
				String enderecoMedico = myRs.getString("ds_Endereco");
				int cidadeMedico = myRs.getInt("nm_Cidade");
				String bairroMedico = myRs.getString("nm_Bairro");
				String estadoMedico = myRs.getString("sg_Estado");
				String emailMedico = myRs.getString("ds_Email");
				String telefoneMedico = myRs.getString("cd_Telefone");
				String datanascMedico = myRs.getString("dt_Nascimento");
				
				// create new medico object with codigo
				Medico tempMedico = new Medico(codMedico, crmMedico, nomeMedico, especialidadeMedico, rgMedico, cpfMedico, 
						enderecoMedico, cidadeMedico, bairroMedico, estadoMedico,
					    emailMedico, telefoneMedico, datanascMedico);
				
				// add it to the list of medico
				medicos.add(tempMedico);				
			}
			
			return medicos;		
		}
		finally {
			// close JDBC objects
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
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addMedico(Medico medico) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			
			/*
			nm_Medico VARCHAR(50),
	crm_Medico INT,   
    cd_Especialidade VARCHAR(10),
	cd_RG INT(11),
    cd_CPF INT(11),     
	ds_Endereco VARCHAR (50),
    nm_Cidade VARCHAR(20),
    nm_Bairro VARCHAR(20),
	sg_Estado CHAR(2),
	cd_Telefone VARCHAR(20),
    ds_Email VARCHAR(50),
    dt_Nascimento DATE,*/
			
			String sql = "insert into medico "
					   + "(crm_Medico, nm_Medico, cd_Especialidade, cd_RG, cd_CPF, ds_Endereco, "
					   + "nm_Cidade, nm_Bairro, sg_Estado, ds_Email, cd_Telefone, dt_Nascimento) "
					   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			/*/*(int codMedico, String nomeMedico, int rgMedico, int cpfMedico, 
			String enderecoMedico, String cidadeMedico, String bairroMedico, String estadoMedico,
			String emailMedico, String telefoneMedico, String  datanascMedico)*/
			
			//myStmt.setInt(1, medico.getCodMedico());
			myStmt.setString(1, medico.getCrmMedico());
			myStmt.setString(2, medico.getNomeMedico());
			myStmt.setInt(3, medico.getEspecialidadeMedico());
			myStmt.setString(4, medico.getRgMedico());
			myStmt.setString(5, medico.getCpfMedico());
			myStmt.setString(6, medico.getEnderecoMedico());
			myStmt.setInt(7, medico.getCidadeMedico());
			myStmt.setString(8, medico.getBairroMedico());
			myStmt.setString(9, medico.getEstadoMedico());
			myStmt.setString(10, medico.getEmailMedico());
			myStmt.setString(11, medico.getTelefoneMedico());
			myStmt.setString(12, medico.getDatanascMedico());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Medico getMedico(String paramMedicoId) throws Exception {

		Medico medico = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int localMedicoId; //este é o codMedico
		
		try {
			// convert medico id to int
			localMedicoId = Integer.parseInt(paramMedicoId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from medico where cd_Medico=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localMedicoId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String crmMedico = myRs.getString("crm_Medico");
				String nomeMedico = myRs.getString("nm_Medico");
				int especialidadeMedico = myRs.getInt("cd_Especialidade");
				String rgMedico = myRs.getString("cd_RG");
				String cpfMedico = myRs.getString("cd_CPF");
				String enderecoMedico = myRs.getString("ds_Endereco");
				int cidadeMedico = myRs.getInt("nm_Cidade");
				String bairroMedico = myRs.getString("nm_Bairro");
				String estadoMedico = myRs.getString("sg_Estado");
				String emailMedico = myRs.getString("ds_Email");
				String telefoneMedico = myRs.getString("cd_Telefone");
				String datanascMedico = myRs.getString("dt_Nascimento");
				
				
				// use the studentId during construction
				medico = new Medico(localMedicoId, crmMedico, nomeMedico, especialidadeMedico, rgMedico, cpfMedico, 
						enderecoMedico, cidadeMedico, bairroMedico, estadoMedico,
						emailMedico, telefoneMedico, datanascMedico);
			}
			else {
				throw new Exception("Não foi possível encontrar medico: " + localMedicoId);
			}				
			
			return medico;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateMedico(Medico medico) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update medico "
						+ "set crm_Medico=?, nm_Medico=?, cd_Especialidade=?, cd_RG=?, cd_CPF=?, ds_Endereco=?, nm_Cidade=?, "
						+ "nm_Bairro=?, sg_Estado=?, ds_Email=?, cd_Telefone=?, dt_Nascimento=?"					
						+ "where cd_Medico=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params		
			myStmt.setString(1, medico.getCrmMedico());
			myStmt.setString(2, medico.getNomeMedico());
			myStmt.setInt(3, medico.getEspecialidadeMedico());
			myStmt.setString(4, medico.getRgMedico());
			myStmt.setString(5, medico.getCpfMedico());
			myStmt.setString(6, medico.getEnderecoMedico());
			myStmt.setInt(7, medico.getCidadeMedico());
			myStmt.setString(8, medico.getBairroMedico());
			myStmt.setString(9, medico.getEstadoMedico());
			myStmt.setString(10, medico.getEmailMedico());
			myStmt.setString(11, medico.getTelefoneMedico());
			myStmt.setString(12, medico.getDatanascMedico());
			myStmt.setInt(13, medico.getCodMedico());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteMedico(String medicoId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int localMedicoId = Integer.parseInt(medicoId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from medico where cd_Medico=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localMedicoId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















