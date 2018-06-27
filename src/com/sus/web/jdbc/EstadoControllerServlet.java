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
 * Servlet implementation class EstadoControllerServlet
 */
@WebServlet("/EstadoControllerServlet")
public class EstadoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EstadoDbUtil EstadoDbUtil;
	
	@Resource(name="jdbc/sus_agendamento")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our especialidad db util ... and pass in the conn pool / datasource
		try {
			EstadoDbUtil = new EstadoDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	
	//get DbUtil
	public EstadoDbUtil getDbUtil() {
		return this.EstadoDbUtil;
	}
	
	//get datasource
	public DataSource getDataSource() {
		return this.dataSource;
	}
	

	//AVALIA PARAM COMMAND
	//metodo get
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
				listEstado(request, response);
				break;
				
		//	case "ADD":
		//		addEstado(request, response);
		//		break;
				
		//	case "LOAD":
		//		loadEstado(request, response);
		//		break;
				
		//	case "UPDATE":
		//		updateEstado(request, response);
		//		break;
			
		//	case "DELETE":
		//		deleteEstado(request, response);
		//		break;
				
			default:
				listEstado(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	//APAGA
	private void deleteEstado(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student id from form data
		String localEstadoId = request.getParameter("estadoId");
		
		// delete student from database
		EstadoDbUtil.deleteEstado(localEstadoId);
		
		// send them back to "list students" page
		listEstado(request, response);
	}

	//ATUALIZA
	private void updateEstado(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read estado info from form data
		
		String sgEstado = request.getParameter("estadoId");
		String nomeEstado = request.getParameter("nomeEstado");
				
		// create a new student object
		Estado estado = new Estado(sgEstado, nomeEstado);
		
		// perform update on database
		EstadoDbUtil.updateEstado(estado);
		
		// send them back to the "list students" page
		listEstado(request, response);
		
	}

	//CARREGA
	private void loadEstado(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read estado id from form data
		String localEstadoId = request.getParameter("estadoId");
		
		// get estado from database (db util)
		Estado estado = EstadoDbUtil.getEstado(localEstadoId);
		
		// place student in the request attribute
		request.setAttribute("ESTADO", estado);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/atualiza-estado.jsp");
		dispatcher.forward(request, response);		
	}

	//ADCIONA
	private void addEstado(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// le informações de estado do form recebido
		//String codEstado = request.getParameter("codEstado");
		String nomeEstado = request.getParameter("nomeEstado");	
		
		// cria novo objeto estado
		Estado estado = new Estado(nomeEstado);
		
		// add the student to the database
		EstadoDbUtil.addEstado(estado);
				
		// send back to main page (the student list)
		listEstado(request, response);
	}

	//LISTA 
	private void listEstado(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Estado> estados = EstadoDbUtil.getEstados();
		
		// add students to the request
		request.setAttribute("ESTADO_LIST", estados);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-estados.jsp");
		dispatcher.forward(request, response);
	}

}













