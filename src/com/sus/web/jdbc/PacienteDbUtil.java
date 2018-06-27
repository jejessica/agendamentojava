package com.sus.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PacienteDbUtil {

	private DataSource dataSource;

	public PacienteDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Paciente> getPacientes() throws Exception {
		
		List<Paciente> pacientes = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "SELECT * FROM paciente order by nm_Paciente";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				/*(int codPaciente, String nomePaciente, int rgPaciente, int cpfPaciente, 
			String enderecoPaciente, String cidadePaciente, String bairroPaciente, String estadoPaciente,
			String emailPaciente, String telefonePaciente, String  datanascPaciente)
			
	cd_Paciente INT NOT NULL auto_increment, 
    nm_Paciente VARCHAR(50),
    cd_CPF INT(11),
    cd_RG INT(11),
    ds_Endereco VARCHAR(50),
	sg_Estado CHAR(2),
    nm_Cidade VARCHAR(20),
    nm_Bairro VARCHAR(20),
    cd_Telefone VARCHAR(20),
    ds_Email VARCHAR(50),
    dt_Nascimento DATE,
			*/
				
				// retrieve data from result set row
				int codPaciente = myRs.getInt("cd_Paciente");
				String nomePaciente = myRs.getString("nm_Paciente");
				String rgPaciente = myRs.getString("cd_RG");
				String cpfPaciente = myRs.getString("cd_CPF");
				String enderecoPaciente = myRs.getString("ds_Endereco");
				int cidadePaciente = myRs.getInt("nm_Cidade");
				String bairroPaciente = myRs.getString("nm_Bairro");
				String estadoPaciente = myRs.getString("sg_Estado");
				String emailPaciente = myRs.getString("ds_Email");
				String telefonePaciente = myRs.getString("cd_Telefone");
				String datanascPaciente = myRs.getString("dt_Nascimento");
				String sexoPaciente = myRs.getString("ds_Sexo");
				String cartaoPaciente = myRs.getString("nm_Cartao");
				
				// create new paciente object
				Paciente tempPaciente = new Paciente(codPaciente, nomePaciente, rgPaciente, cpfPaciente, 
						enderecoPaciente, cidadePaciente, bairroPaciente, estadoPaciente,
					    emailPaciente, telefonePaciente, datanascPaciente, sexoPaciente, cartaoPaciente);
				
				// add it to the list of students
				pacientes.add(tempPaciente);				
			}
			
			return pacientes;		
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

	public void addPaciente(Paciente paciente) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			
			/*
			cd_Paciente INT NOT NULL auto_increment, 
    nm_Paciente VARCHAR(50),
    cd_CPF INT(11),
    cd_RG INT(11),
    ds_Endereco VARCHAR(50),
	sg_Estado CHAR(2),
    nm_Cidade VARCHAR(20),
    nm_Bairro VARCHAR(20),
    cd_Telefone VARCHAR(20),
    ds_Email VARCHAR(50),
    dt_Nascimento DATE,*/
			
			String sql = "insert into paciente "
					   + "(nm_Paciente, cd_RG, cd_CPF, ds_Endereco, nm_Cidade, "
					   + "nm_Bairro, sg_Estado, ds_Email, cd_Telefone, dt_Nascimento,"
					   + "ds_Sexo, nm_Cartao) "
					   + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			/*/*(int codPaciente, String nomePaciente, int rgPaciente, int cpfPaciente, 
			String enderecoPaciente, String cidadePaciente, String bairroPaciente, String estadoPaciente,
			String emailPaciente, String telefonePaciente, String  datanascPaciente)*/
			
			//myStmt.setInt(1, paciente.getCodPaciente());
			myStmt.setString(1, paciente.getNomePaciente());
			myStmt.setString(2, paciente.getRgPaciente());
			myStmt.setString(3, paciente.getCpfPaciente());
			myStmt.setString(4, paciente.getEnderecoPaciente());
			myStmt.setInt(5, paciente.getCidadePaciente());
			myStmt.setString(6, paciente.getBairroPaciente());
			myStmt.setString(7, paciente.getEstadoPaciente());
			myStmt.setString(8, paciente.getEmailPaciente());
			myStmt.setString(9, paciente.getTelefonePaciente());
			myStmt.setString(10, paciente.getDatanascPaciente());
			myStmt.setString(11, paciente.getSexoPaciente());
			myStmt.setString(12, paciente.getCartaoPaciente());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Paciente getPaciente(String paramPacienteId) throws Exception {

		Paciente paciente = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int localPacienteId;
		
		try {
			// convert paciente id to int
			localPacienteId = Integer.parseInt(paramPacienteId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from paciente where cd_Paciente=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localPacienteId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				int codPaciente = myRs.getInt("cd_Paciente");
				String nomePaciente = myRs.getString("nm_Paciente");
				String rgPaciente = myRs.getString("cd_RG");
				String cpfPaciente = myRs.getString("cd_CPF");
				String enderecoPaciente = myRs.getString("ds_Endereco");
				int cidadePaciente = myRs.getInt("nm_Cidade");
				String bairroPaciente = myRs.getString("nm_Bairro");
				String estadoPaciente = myRs.getString("sg_Estado");
				String emailPaciente = myRs.getString("ds_Email");
				String telefonePaciente = myRs.getString("cd_Telefone");
				String datanascPaciente = myRs.getString("dt_Nascimento");
				
				//String substring(int, int)
				//20-06-2018
				//0123456789
				String auxData;
				auxData = datanascPaciente.substring(6,10) + "-" + datanascPaciente.substring(3, 5) + "-" + datanascPaciente.substring(0,2);
				datanascPaciente = auxData;
				
				String sexoPaciente = myRs.getString("ds_Sexo");
				String cartaoPaciente = myRs.getString("nm_Cartao");
				
				
				// use the studentId during construction
				paciente = new Paciente(codPaciente, nomePaciente, rgPaciente, cpfPaciente, 
						enderecoPaciente, cidadePaciente, bairroPaciente, estadoPaciente,
						emailPaciente, telefonePaciente, datanascPaciente, sexoPaciente, cartaoPaciente);
			}
			else {
				throw new Exception("Não foi possível encontrar paciente: " + localPacienteId);
			}				
			
			return paciente;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updatePaciente(Paciente paciente) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update paciente "
						+ "set nm_Paciente=?, cd_RG=?, cd_CPF=?, ds_Endereco=?, nm_Cidade=?, "
						+ "nm_Bairro=?, sg_Estado=?, ds_Email=?, cd_Telefone=?, dt_Nascimento=?,"
						+ "ds_Sexo=?, nm_Cartao=?"					
						+ "where cd_Paciente=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params			
			myStmt.setString(1, paciente.getNomePaciente());
			myStmt.setString(2, paciente.getRgPaciente());
			myStmt.setString(3, paciente.getCpfPaciente());
			myStmt.setString(4, paciente.getEnderecoPaciente());
			myStmt.setInt(5, paciente.getCidadePaciente());
			myStmt.setString(6, paciente.getBairroPaciente());
			myStmt.setString(7, paciente.getEstadoPaciente());
			myStmt.setString(8, paciente.getEmailPaciente());
			myStmt.setString(9, paciente.getTelefonePaciente());
			myStmt.setString(10, paciente.getDatanascPaciente());
			myStmt.setString(11, paciente.getSexoPaciente());
			myStmt.setString(12, paciente.getCartaoPaciente());
			
			myStmt.setInt(13, paciente.getCodPaciente());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deletePaciente(String pacienteId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int localPacienteId = Integer.parseInt(pacienteId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from paciente where cd_Paciente=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localPacienteId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















