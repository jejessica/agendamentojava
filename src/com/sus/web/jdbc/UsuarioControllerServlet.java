package com.sus.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class UsuarioControllerServlet
 */
@WebServlet("/UsuarioControllerServlet")
public class UsuarioControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDbUtil UsuarioDbUtil;
	
	@Resource(name="jdbc/sus_agendamento")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			UsuarioDbUtil = new UsuarioDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	//AVALIA PARAM COMMAND
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listUsuario(request, response);
				break;
				
			case "ADD":
				addUsuario(request, response);
				break;
				
			case "CADASTRO":
				formUsuario(request, response);
				
			case "LOAD":
				loadUsuario(request, response);
				break;
				
			case "UPDATE":
				updateUsuario(request, response);
				break;
			
			case "DELETE":
				deleteUsuario(request, response);
				break;
				
			default:
				listUsuario(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void formUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception{

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-usuario.jsp");
		dispatcher.forward(request, response);

}
	
	//APAGA
	private void deleteUsuario(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String localUsuarioId = request.getParameter("usuarioId");		
		UsuarioDbUtil.deleteUsuario(localUsuarioId);
		
		response.sendRedirect("./UsuarioControllerServlet");
	}

	//ATUALIZA
	private void updateUsuario(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student info from form data
		int codUsuario = Integer.parseInt(request.getParameter("usuarioId"));
		String nomeUsuario = request.getParameter("nomeUsuario");
		String loginUsuario = request.getParameter("loginUsuario");
		String senhaUsuario = request.getParameter("senhaUsuario");
		String dsUsuario = request.getParameter("dsUsuario");
		
				
		// create a new student object
		Usuario usuario = new Usuario(codUsuario, nomeUsuario, loginUsuario, senhaUsuario, dsUsuario);
		
		// perform update on database
		UsuarioDbUtil.updateUsuario(usuario);
		
		//listUsuario(request, response);
		response.sendRedirect("./UsuarioControllerServlet");
		
	}

	//CARREGA
	private void loadUsuario(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		String localUsuarioId = request.getParameter("usuarioId");
		
		Usuario usuario = UsuarioDbUtil.getUsuario(localUsuarioId);
		
		request.setAttribute("USUARIO", usuario);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/atualiza-usuario.jsp");
		dispatcher.forward(request, response);		
	}

	//ADCIONA
	private void addUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		//int codUsuario = Integer.parseInt(request.getParameter("usuarioId"));
		String nomeUsuario = request.getParameter("nomeUsuario");
		String loginUsuario = request.getParameter("loginUsuario");
		String senhaUsuario = request.getParameter("senhaUsuario");
		String dsUsuario = request.getParameter("dsUsuario");

		
		// cria novo objeto usuario
		Usuario usuario = new Usuario(nomeUsuario, loginUsuario, senhaUsuario, dsUsuario);
		
		UsuarioDbUtil.addUsuario(usuario);

		response.sendRedirect("./UsuarioControllerServlet");
	}

	//LISTA 
	private void listUsuario(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Usuario> usuarios = UsuarioDbUtil.getUsuarios();
		
		// add students to the request
		request.setAttribute("USUARIO_LIST", usuarios);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-usuarios.jsp");
		dispatcher.forward(request, response);
	}

}













