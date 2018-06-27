package com.sus.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CidadeDbUtil {

	private DataSource dataSource;

	public CidadeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Cidade> getCidades() throws Exception {
		
		List<Cidade> cidades = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from cidade order by nm_Cidade";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int codCidade = myRs.getInt("cd_Cidade");
				String sgEstado = myRs.getString("sg_Estado");
				String nomeCidade = myRs.getString("nm_Cidade");
				
				// create new cidade object
				Cidade tempCidade = new Cidade(codCidade, sgEstado, nomeCidade);
				
				// add it to the list of students
				cidades.add(tempCidade);				
			}
			
			return cidades;		
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

	//não há adição de cidade via aplicação
	public void addCidade(Cidade cidade) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into cidade "
					   + "(nm_Cidade) "
					   + "values (?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the cidade
			myStmt.setString(1, cidade.getNomeCidade());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Cidade getCidade(String paramCidadeId) throws Exception {

		Cidade cidade = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int localCidadeId;
		
		try {
			// convert student id to int
			localCidadeId = Integer.parseInt(paramCidadeId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from cidade where cd_Cidade=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localCidadeId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String sgEstado = myRs.getString("sg_Estado");
				String nomeCidade = myRs.getString("nm_Cidade");
				
				// use the studentId during construction
				cidade = new Cidade(localCidadeId, sgEstado, nomeCidade);
			}
			else {
				throw new Exception("Não foi possível encontrar cidade: " + localCidadeId);
			}				
			
			return cidade;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	//não há atualização via aplicação
	public void updateCidade(Cidade cidade) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update cidade "
						+ "set nm_Cidade=? "
						+ "where cd_Cidade=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, cidade.getNomeCidade());
			myStmt.setString(2, cidade.getSgEstado());
			
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	//não há deleção via aplicação
	public void deleteCidade(String cidadeId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int localCidadeId = Integer.parseInt(cidadeId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from cidade where cd_Cidade=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localCidadeId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















