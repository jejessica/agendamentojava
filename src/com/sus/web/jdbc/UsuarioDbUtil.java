package com.sus.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UsuarioDbUtil {

	private DataSource dataSource;

	public UsuarioDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Usuario> getUsuarios() throws Exception {
		
		List<Usuario> usuarios = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from usuario order by cd_Usuario";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				/*CREATE TABLE Usuario(
	cd_Usuario INT,
	nm_Usuario VARCHAR(20),
    nm_Login VARCHAR(10),
    ds_Senha VARCHAR(10),
    ds_Usuario VARCHAR(10),
    PRIMARY KEY (cd_Usuario)
);
			*/
				
				// retrieve data from result set row
				int codUsuario = myRs.getInt("cd_Usuario");
				String nomeUsuario = myRs.getString("nm_Usuario");
				String loginUsuario = myRs.getString("nm_Login");
				String senhaUsuario = myRs.getString("ds_Senha");
				String dsUsuario = myRs.getString("ds_Usuario");
				
				// create new usuario object
				Usuario tempUsuario = new Usuario(codUsuario, nomeUsuario, loginUsuario, senhaUsuario, dsUsuario);
				
				// add it to the list of students
				usuarios.add(tempUsuario);				
			}
			
			return usuarios;		
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

	public void addUsuario(Usuario usuario) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			
			/*
			CREATE TABLE Usuario(
	cd_Usuario INT,
	nm_Usuario VARCHAR(20),
    nm_Login VARCHAR(10),
    ds_Senha VARCHAR(10),
    ds_Usuario VARCHAR(10),
    PRIMARY KEY (cd_Usuario)
);*/
			
			String sql = "insert into usuario "
					   + "(nm_Usuario, nm_Login, ds_Senha, ds_Usuario) "
					   + "values (?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			/*/*(int codUsuario, String nomeUsuario, int rgUsuario, int cpfUsuario, 
			String enderecoUsuario, String cidadeUsuario, String bairroUsuario, String estadoUsuario,
			String emailUsuario, String telefoneUsuario, String  datanascUsuario)*/
			
			//myStmt.setInt(1, usuario.getCodUsuario());
			myStmt.setString(1, usuario.getNomeUsuario());
			myStmt.setString(2, usuario.getLoginUsuario());
			myStmt.setString(3, usuario.getSenhaUsuario());
			myStmt.setString(4, usuario.getDsUsuario());

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Usuario getUsuario(String paramUsuarioId) throws Exception {

		Usuario usuario = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int localUsuarioId;
		
		try {
			// convert usuario id to int
			localUsuarioId = Integer.parseInt(paramUsuarioId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from usuario where cd_Usuario=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localUsuarioId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				int codUsuario = myRs.getInt("cd_Usuario");
				String nomeUsuario = myRs.getString("nm_Usuario");
				String loginUsuario = myRs.getString("nm_Login");
				String senhaUsuario = myRs.getString("ds_Senha");
				String dsUsuario = myRs.getString("ds_Usuario");
				
				
				// use the studentId during construction
				usuario = new Usuario(codUsuario, nomeUsuario, loginUsuario, senhaUsuario, dsUsuario);
				
			}
			else {
				throw new Exception("Não foi possível encontrar usuario: " + localUsuarioId);
			}				
			
			return usuario;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateUsuario(Usuario usuario) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update usuario "
						+ "set nm_Usuario=?, nm_Login=?, ds_Senha=?, ds_Usuario=?"					
						+ "where cd_Usuario=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params			
			myStmt.setString(1, usuario.getNomeUsuario());
			myStmt.setString(2, usuario.getLoginUsuario());
			myStmt.setString(3, usuario.getSenhaUsuario());
			myStmt.setString(4, usuario.getDsUsuario());

			myStmt.setInt(5, usuario.getCodUsuario());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteUsuario(String usuarioId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int localUsuarioId = Integer.parseInt(usuarioId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from usuario where cd_Usuario=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, localUsuarioId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















