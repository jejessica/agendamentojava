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
 * Servlet implementation class CidadeControllerServlet
 */
@WebServlet("/CidadeControllerServlet")
public class CidadeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CidadeDbUtil CidadeDbUtil;
	
	@Resource(name="jdbc/sus_agendamento")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			CidadeDbUtil = new CidadeDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	public CidadeDbUtil getDbUtil() {
		return this.CidadeDbUtil;
	}
	
	public DataSource getDataSource() {
		return this.dataSource;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String theCommand = request.getParameter("command");
			
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
			
			case "LIST":
				listCidade(request, response);
				break;
				
			default:
				listCidade(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteCidade(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String localCidadeId = request.getParameter("cidadeId");
		
		CidadeDbUtil.deleteCidade(localCidadeId);
		
		listCidade(request, response);
	}

	private void updateCidade(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		String sgEstado = request.getParameter("cidadeId");
		String nomeCidade = request.getParameter("nomeCidade");
				
		Cidade cidade = new Cidade(sgEstado, nomeCidade);

		CidadeDbUtil.updateCidade(cidade);
		
		listCidade(request, response);
		
	}

	private void loadCidade(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
	
		String localCidadeId = request.getParameter("cidadeId");
		
		Cidade cidade = CidadeDbUtil.getCidade(localCidadeId);
		
		request.setAttribute("CIDADE", cidade);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/atualiza-cidade.jsp");
		dispatcher.forward(request, response);		
	}

	private void addCidade(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nomeCidade = request.getParameter("nomeCidade");	
		
		listCidade(request, response);
	}

	//LISTA 
	private void listCidade(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		List<Cidade> cidades = CidadeDbUtil.getCidades();
		
		request.setAttribute("CIDADE_LIST", cidades);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-cidades.jsp");
		dispatcher.forward(request, response);
	}

}













