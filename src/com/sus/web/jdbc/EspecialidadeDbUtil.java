package com.sus.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EspecialidadeDbUtil {

	private DataSource dataSource;

	public EspecialidadeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Especialidade> getEspecialidades() throws Exception {
		
		List<Especialidade> especialidades = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from especialidade order by cd_Especialidade";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int codEspecialidade = myRs.getInt("cd_Especialidade");
				String nomeEspecialidade = myRs.getString("nm_Especialidade");
				
				// create new especialidade object
				Especialidade tempEspecialidade = new Especialidade(codEspecialidade, nomeEspecialidade);
				
				// add it to the list of students
				especialidades.add(tempEspecialidade);				
			}
			
			return especialidades;		
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

	public void addEspecialidade(Especialidade especialidade) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into especialidade "
					   + "(nm_Especialidade) "
					   + "values (?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the especialidade
			myStmt.setString(1, especialidade.getNomeEspecialidade());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Especialidade getEspecialidade(String paramEspecialidadeId) throws Exception {

		Especialidade especialidade = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int localEspecialidadeId;
		
		try {
			// convert student id to int
			localEspecialidadeId = Integer.parseInt(paramEspecialidadeId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from especialidade where cd_Especialidade=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localEspecialidadeId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String nomeEspecialidade = myRs.getString("nm_Especialidade");
				
				// use the studentId during construction
				especialidade = new Especialidade(localEspecialidadeId, nomeEspecialidade);
			}
			else {
				throw new Exception("Não foi possível encontrar especialidade: " + localEspecialidadeId);
			}				
			
			return especialidade;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateEspecialidade(Especialidade especialidade) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update especialidade "
						+ "set nm_Especialidade=? "
						+ "where cd_Especialidade=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, especialidade.getNomeEspecialidade());
			myStmt.setInt(2, especialidade.getCodEspecialidade());
			
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteEspecialidade(String especialidadeId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int localEspecialidadeId = Integer.parseInt(especialidadeId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from especialidade where cd_Especialidade=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localEspecialidadeId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















