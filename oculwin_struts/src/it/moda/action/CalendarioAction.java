package it.moda.action;

import it.moda.bean.AgendaDettaglioBean;
import it.moda.bean.PazienteBean;
import it.moda.dao.CalendarioDAO;
import it.moda.dto.CalendarioDTO;
import it.moda.form.CalendarioForm;
import it.moda.utils.Utils;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CalendarioAction extends DispatchAction{
	private static Logger log = Logger.getLogger(CalendarioAction.class);

	public void loadListaCalendario(CalendarioForm calendarioForm,HttpServletRequest request) throws SQLException{
		//		for(Map.Entry entry : request.getParameterMap().entrySet())
		//			calendarioForm.setValue((String)entry.getKey(),entry.getValue());
		CalendarioDAO calendarioDAO = new CalendarioDAO(calendarioForm.getPaginator(),5);
		calendarioForm.setPaginator(calendarioDAO.getPaginator("CALENDARIO"));
		calendarioForm.setListCalendario(calendarioDAO.findAll(calendarioForm.isFirstTime()));
		if(calendarioForm.isFirstTime()){
			calendarioForm.setFirstTime(false);
			calendarioForm.setValue("firstTime", calendarioForm.isFirstTime());
			calendarioForm.setPaginator(calendarioDAO.getPaginator());
		}

	}

	public ActionForward load(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {


		CalendarioForm calendarioForm = (CalendarioForm) form;

		loadListaCalendario(calendarioForm,request);

		return (mapping.findForward("success"));

	}

	public ActionForward loadAgendaDettaglio(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		CalendarioForm calendarioForm = (CalendarioForm) form;
		loadListaCalendario(calendarioForm,request);
		CalendarioDAO calendarioDAO = new CalendarioDAO();
		calendarioForm.setAppuntamenti(calendarioDAO.findByData(Utils.parseDate(calendarioForm.getData())));

		//		calendarioForm.setPazienti(calendarioDAO.fillPazientiList());

		return (mapping.findForward("success"));
	}

	public ActionForward fillPazientiList(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		CalendarioForm calendarioForm = (CalendarioForm) form;
		CalendarioDAO calendarioDAO = new CalendarioDAO();
		calendarioForm.setPazienti(calendarioDAO.fillPazientiList());


		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//	    out.println("<select name=\"pazienti\" id=\"pazienti\" onselect=\"assignPaziente(this.value,"+request.getParameter("index")+")\">\n");
		out.println("<option value=\"\"> --- SELEZIONA UN PAZIENTE --- </option>");
		for (Iterator i = calendarioForm.getPazienti().iterator(); i.hasNext(); ){
			PazienteBean paziente = (PazienteBean)i.next();
			out.println("<option value=\""+paziente.getPden()+"|"+paziente.getDatePnascita()+"|"+paziente.getPnascita()+"\">"+paziente.getPden()+" | "+paziente.getPnascita()+"</option>");
		}
		//	    out.println("</select>\n");
		out.flush();
		return null;

	}


	public ActionForward edit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		return (mapping.findForward("success"));

	}
	public ActionForward saveAll(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		CalendarioForm calendarioForm = (CalendarioForm) form;

		ActionErrors errors = calendarioForm.validate(mapping, request);
		if(errors.isEmpty()){
			CalendarioDAO calendarioDAO = new CalendarioDAO();
			calendarioDAO.saveAllAppointments(Utils.parseDate(calendarioForm.getData()), calendarioForm.getAppuntamenti());
		}
		saveErrors(request, errors);
		loadListaCalendario(calendarioForm,request);
		return (mapping.findForward("success"));

	}
	public ActionForward deleteAppuntamento(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		CalendarioForm calendarioForm = (CalendarioForm) form;
		CalendarioDAO calendarioDAO = new CalendarioDAO();
		AgendaDettaglioBean appuntamentoBean = new AgendaDettaglioBean();
		appuntamentoBean = (AgendaDettaglioBean)calendarioForm.getAppuntamenti().get(Integer.parseInt(request.getParameter("id")));

		CalendarioDTO calendarioDTO = calendarioDAO.deleteAppuntamento(Utils.parseDate(calendarioForm.getData()),appuntamentoBean);

		JAXBContext jc = JAXBContext.newInstance(CalendarioDTO.class);
		String xml = Utils.marshall(jc, calendarioDTO);

		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		out.println(xml);
		out.flush();

		return (null);

	}


}
