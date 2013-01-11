package it.moda.action;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import it.moda.dao.CalendarioDAO;
import it.moda.dto.CalendarioDTO;
import it.moda.form.CalendarioForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CalendarioAction extends DispatchAction{
	private static Logger log = Logger.getLogger(CalendarioAction.class);

	public void loadListaCalendario(CalendarioForm calendarioForm,HttpServletRequest request){
		for(Map.Entry entry : request.getParameterMap().entrySet())
			calendarioForm.setValue((String)entry.getKey(),entry.getValue());
		CalendarioDAO calendarioDAO = new CalendarioDAO(calendarioForm.getPaginator(),7);
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
		java.text.SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		calendarioForm.setAppuntamenti(calendarioDAO.findByData(sdf.parse(request.getParameter("data"))));
		calendarioForm.setPazienti(calendarioDAO.fillPazientiList());
		
		return (mapping.findForward("success"));
	}
	


	public ActionForward edit(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		return (mapping.findForward("success"));

	}
	public ActionForward save(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		return (mapping.findForward("success"));

	}
	public ActionForward delete(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		return (mapping.findForward("success"));

	}


}
