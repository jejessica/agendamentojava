package com.sus.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EstadoDbUtil {

	private DataSource dataSource;

	public EstadoDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Estado> getEstados() throws Exception {
		
		List<Estado> estados = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from estado order by sg_Estado";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				String sgEstado = myRs.getString("sg_Estado");
				String nomeEstado = myRs.getString("nm_Estado");
				
				// create new estado object
				Estado tempEstado = new Estado(sgEstado, nomeEstado);
				
				// add it to the list of students
				estados.add(tempEstado);				
			}
			
			return estados;		
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

	public void addEstado(Estado estado) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into estado "
					   + "(nm_Estado) "
					   + "values (?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the estado
			myStmt.setString(1, estado.getNomeEstado());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Estado getEstado(String paramEstadoId) throws Exception {

		Estado estado = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String localEstadoId;
		
		try {
			// convert student id to int
			localEstadoId = paramEstadoId;
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from estado where sg_Estado=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, localEstadoId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String nomeEstado = myRs.getString("nm_Estado");
				
				// use the studentId during construction
				estado = new Estado(localEstadoId, nomeEstado);
			}
			else {
				throw new Exception("Não foi possível encontrar estado: " + localEstadoId);
			}				
			
			return estado;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateEstado(Estado estado) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update estado "
						+ "set nm_Estado=? "
						+ "where cd_Estado=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, estado.getNomeEstado());
			myStmt.setString(2, estado.getSgEstado());
			
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteEstado(String estadoId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int localEstadoId = Integer.parseInt(estadoId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from estado where cd_Estado=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localEstadoId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















