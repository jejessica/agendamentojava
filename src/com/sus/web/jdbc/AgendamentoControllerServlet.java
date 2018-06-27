package com.sus.web.jdbc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/AgendamentoControllerServlet")
public class AgendamentoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AgendamentoDbUtil AgendamentoDbUtil;
	private MedicoDbUtil MedicoDbUtil;
	private EspecialidadeDbUtil EspecialidadeDbUtil;
	private PacienteDbUtil PacienteDbUtil;
	
	@Resource(name="jdbc/sus_agendamento")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			AgendamentoDbUtil = new AgendamentoDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	public AgendamentoDbUtil getDbUtil() {
		return this.AgendamentoDbUtil;
	}
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");
			
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
				switch (theCommand) {
				
				case "LIST":
					listAgendamento(request, response);
					break;
					
		//		case "RELOAD_LISTAGENDAMENTO":
		//			reloadListaAgendamento(request, response);
		//			break;
					
				case "ADD":
					addAgendamento(request, response);
					break;
					
				case "LOAD":
					loadAgendamento(request, response);
					break;
					
				case "UPDATE":
					updateAgendamento(request, response);
					break;
				
				case "DELETE":
					deleteAgendamento(request, response);
					break;
					
					
					
				default:
					listAgendamento(request, response);
				}
			
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String theCommand = request.getParameter("command");
			
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			switch (theCommand) {
			
			case "LIST":
				listAgendamento(request, response);
				break;
				
			case "RELOAD_LISTAGENDAMENTO":
				reloadListaAgendamento(request, response);
				break;
				
			case "ADD":
				addAgendamento(request, response);
				break;
				
			case "CADASTRO":
				formAgendamento(request, response);
				break;
				
			case "LOAD":
				loadAgendamento(request, response);
				break;
				
			case "UPDATE":
				updateAgendamento(request, response);
				break;
			
			case "DELETE":
				deleteAgendamento(request, response);
				break;
				
			default:
				listAgendamento(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	
	private void reloadListaAgendamento(HttpServletRequest request, HttpServletResponse response) throws Exception{

		String nomePesquisa = request.getParameter("nomePesquisa");

		AgendamentoDbUtil = new AgendamentoDbUtil(dataSource);	
		List<Agendamento> listAgendamentoFilter = AgendamentoDbUtil.getAgendamentosFilter(nomePesquisa);
		
		request.setAttribute("AGENDAMENTO_LIST", listAgendamentoFilter);
		request.setAttribute("NOMEPESQUISA", nomePesquisa);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-agendamentos.jsp");
		dispatcher.forward(request, response);
		
	/*	EspecialidadeDbUtil = new EspecialidadeDbUtil(dataSource);	
		List<Especialidade> listEsp = EspecialidadeDbUtil.getEspecialidades();
			
		request.setAttribute("ESPECIALIDADES_FORM", listEsp);
		
		MedicoDbUtil = new MedicoDbUtil(dataSource);	
		List<Medico> listMed = MedicoDbUtil.getMedicos();
			
		request.setAttribute("MEDICOS_FORM", listMed);
		
		PacienteDbUtil = new PacienteDbUtil(dataSource);	
		List<Paciente> listPac = PacienteDbUtil.getPacientes();
			
		request.setAttribute("PACIENTES_FORM", listPac); */


	}
	
	private void getDadosParaForm(HttpServletRequest request, HttpServletResponse response) throws Exception{

		EspecialidadeDbUtil = new EspecialidadeDbUtil(dataSource);	
		List<Especialidade> listEsp = EspecialidadeDbUtil.getEspecialidades();
			
		request.setAttribute("ESPECIALIDADES_FORM", listEsp);
		
		MedicoDbUtil = new MedicoDbUtil(dataSource);	
		List<Medico> listMed = MedicoDbUtil.getMedicos();
			
		request.setAttribute("MEDICOS_FORM", listMed);
		
		PacienteDbUtil = new PacienteDbUtil(dataSource);	
		List<Paciente> listPac = PacienteDbUtil.getPacientes();
			
		request.setAttribute("PACIENTES_FORM", listPac);


	}
	
	private void formAgendamento(HttpServletRequest request, HttpServletResponse response) throws Exception{

		RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro-agendamento.jsp");
		dispatcher.forward(request, response);
}
	
	//APAGA
	private void deleteAgendamento(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String localAgendamentoId = request.getParameter("AgendamentoId");
		
		AgendamentoDbUtil.deleteAgendamento(localAgendamentoId);
		
		response.sendRedirect("./AgendamentoControllerServlet");
			}

	//ATUALIZA
	private void updateAgendamento(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		int codAgendamento = Integer.parseInt(request.getParameter("AgendamentoId"));
		String dtAgendamento = request.getParameter("dtAgendamento");
		
		//String substring(int, int)
		//2018-06-20
		//0123456789
		String auxData;
		auxData = dtAgendamento.substring(8,10) + "-" + dtAgendamento.substring(5, 7) + "-" + dtAgendamento.substring(0,4);
		dtAgendamento = auxData;
				
				
		String horaAgendamento = request.getParameter("horaAgendamento");
		
		String  codPacient = (request.getParameter("pacienteAgendamento")==null?"3":request.getParameter("pacienteAgendamento")); 
		int codPaciente = Integer.parseInt(codPacient);		
		
		String  codMedic= (request.getParameter("medicoAgendamento")==null?"4":request.getParameter("medicoAgendamento"));
		int codMedico = Integer.parseInt(codMedic);
		
		String  codEsp = (request.getParameter("especialidadeAgendamento")==null?"5":request.getParameter("especialidadeAgendamento"));
		int codEspecialidade = Integer.parseInt(codEsp);
		
		String statusAgendamento = request.getParameter("statusAgendamento");
		
		//int codAgendamento = Integer.parseInt(request.getParameter("AgendamentoId"));
		
		Agendamento Agendamento = new Agendamento(codAgendamento, dtAgendamento,horaAgendamento, codMedico, codEspecialidade,  codPaciente, statusAgendamento);
			
		AgendamentoDbUtil.updateAgendamento(Agendamento);
		
		response.sendRedirect("./AgendamentoControllerServlet");
		
	}

	//CARREGA
	private void loadAgendamento(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		String localAgendamentoId = request.getParameter("AgendamentoId");
		
		Agendamento Agendamento = AgendamentoDbUtil.getAgendamento(localAgendamentoId);
		
		request.setAttribute("AGENDAMENTO", Agendamento);
		
		getDadosParaForm(request, response);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/atualiza-agendamento.jsp");
		dispatcher.forward(request, response);
		
	}

	//ADCIONA
	private void addAgendamento(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String dtAgendamento = request.getParameter("dtAgendamento")==null?"0":request.getParameter("dtAgendamento");
		
		//String substring(int, int)
		//2018-06-20
		//0123456789
		String auxData;
		auxData = dtAgendamento.substring(8,10) + "-" + dtAgendamento.substring(5, 7) + "-" + dtAgendamento.substring(0,4);
		dtAgendamento = auxData;
		
		String horaAgendamento = request.getParameter("horaAgendamento")==null?"1":request.getParameter("horaAgendamento");
		
		//String  codPacient = (request.getParameter("pacienteAgendamento")==null?"2":request.getParameter("pacienteAgendamento")); 
		int codPaciente = Integer.parseInt(request.getParameter("pacienteAgendamento"));
		
		String  codMedic= (request.getParameter("medicoAgendamento")==null?"3":request.getParameter("medicoAgendamento")); 
		int codMedico = Integer.parseInt(codMedic);
		
		String  codEsp = (request.getParameter("especialidadeAgendamento")==null?"4":request.getParameter("especialidadeAgendamento")); 
		int codEspecialidade = Integer.parseInt(codEsp);
		
		String statusAgendamento = ("Aberto");
		
		Agendamento Agendamento = new Agendamento(dtAgendamento,horaAgendamento, codMedico, codEspecialidade, codPaciente, statusAgendamento);
		
		AgendamentoDbUtil.addAgendamento(Agendamento);
				
		response.sendRedirect("./AgendamentoControllerServlet");
	}

	//LISTA 
	private void listAgendamento(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		List<Agendamento> Agendamentos = AgendamentoDbUtil.getAgendamentos();
		
		request.setAttribute("AGENDAMENTO_LIST", Agendamentos);
				
		
		//seta parametros com list de especialidade 
		getDadosParaForm(request, response);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-agendamentos.jsp");
		dispatcher.forward(request, response);
		
	}

}













