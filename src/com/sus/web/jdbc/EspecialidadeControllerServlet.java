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
 * Servlet implementation class EspecialidadeControllerServlet
 */
@WebServlet("/EspecialidadeControllerServlet")
public class EspecialidadeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EspecialidadeDbUtil EspecialidadeDbUtil;
	
	@Resource(name="jdbc/sus_agendamento")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our especialidad db util ... and pass in the conn pool / datasource
		try {
			EspecialidadeDbUtil = new EspecialidadeDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	//get DbUtil
	public EspecialidadeDbUtil getDbUtil() {
		return this.EspecialidadeDbUtil;
	}
	
	//get datasource
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	
	
	//metodo post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
				switch (theCommand) {
				
				case "LIST":
					listEspecialidade(request, response);
					break;
				
				case "CADASTRO":
					addEspecialidade(request, response);
					break;
					
				case "ADD":
					addEspecialidade(request, response);
					break;
					
				case "LOAD":
					loadEspecialidade(request, response);
					break;
					
				case "UPDATE":
					updateEspecialidade(request, response);
					break;
				
				case "DELETE":
					deleteEspecialidade(request, response);
					break;
					
				default:
					listEspecialidade(request, response);
				}
			
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
		
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
				listEspecialidade(request, response);
				break;
				
			case "CADASTRO":
				formEspecialidade(request, response);
				break;
				
			case "ADD":
				addEspecialidade(request, response);
				break;
				
			case "LOAD":
				loadEspecialidade(request, response);
				break;
				
			case "UPDATE":
				updateEspecialidade(request, response);
				break;
			
			case "DELETE":
				deleteEspecialidade(request, response);
				break;
				
			default:
				listEspecialidade(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	
	private void formEspecialidade(HttpServletRequest request, HttpServletResponse response) throws Exception{

	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-especialidade.jsp");
		dispatcher.forward(request, response);

}
	
	//APAGA
	private void deleteEspecialidade(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student id from form data
		String localEspecialidadeId = request.getParameter("especialidadeId");
		
		// delete student from database
		EspecialidadeDbUtil.deleteEspecialidade(localEspecialidadeId);
		
		// send them back to "list students" page
		response.sendRedirect("./EspecialidadeControllerServlet");
		//listEspecialidade(request, response);
	}

	//ATUALIZA
	private void updateEspecialidade(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read especialidade info from form data
		
		int codEspecialidade = Integer.parseInt(request.getParameter("especialidadeId"));
		String nomeEspecialidade = request.getParameter("nomeEspecialidade");
				
		// create a new student object
		Especialidade especialidade = new Especialidade(codEspecialidade, nomeEspecialidade);
		
		// perform update on database
		EspecialidadeDbUtil.updateEspecialidade(especialidade);
		
		// send them back to the "list students" page
		response.sendRedirect("./EspecialidadeControllerServlet");
		//listEspecialidade(request, response);
		
	}

	//CARREGA
	private void loadEspecialidade(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read especialidade id from form data
		String localEspecialidadeId = request.getParameter("especialidadeId");
		
		// get especialidade from database (db util)
		Especialidade especialidade = EspecialidadeDbUtil.getEspecialidade(localEspecialidadeId);
		
		// place student in the request attribute
		request.setAttribute("ESPECIALIDADE", especialidade);
		
		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/atualiza-especialidade.jsp");
		dispatcher.forward(request, response);
		
	}

	//ADCIONA
	private void addEspecialidade(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// le informações de especialidade do form recebido
		//String codEspecialidade = request.getParameter("codEspecialidade");
		String nomeEspecialidade = request.getParameter("nomeEspecialidade");	
		
		// cria novo objeto especialidade
		Especialidade especialidade = new Especialidade(nomeEspecialidade);
		
		// add the student to the database
		EspecialidadeDbUtil.addEspecialidade(especialidade);
				
		// send back to main page (the student list)
		//listEspecialidade(request, response);
		response.sendRedirect("./EspecialidadeControllerServlet");
	}

	//LISTA 
	private void listEspecialidade(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get students from db util
		List<Especialidade> especialidades = EspecialidadeDbUtil.getEspecialidades();
		
		// add students to the request
		request.setAttribute("ESPECIALIDADE_LIST", especialidades);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-especialidades.jsp");
		dispatcher.forward(request, response);
		//response.sendRedirect("./EspecialidadeControllerServlet?command=LIST");
		
	}

}













