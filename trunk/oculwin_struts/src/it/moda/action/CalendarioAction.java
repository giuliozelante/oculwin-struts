package it.moda.action;

import it.moda.dao.CalendarioDAO;
import it.moda.form.CalendarioForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CalendarioAction extends DispatchAction{


	public ActionForward load(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {

		
		CalendarioForm calendarioForm = (CalendarioForm) form;
		CalendarioDAO calendarioDAO = new CalendarioDAO(calendarioForm.getPaginator(),7);
		calendarioForm.setPaginator(calendarioDAO.getPaginator("CALENDARIO"));
		calendarioForm.setListCalendario(calendarioDAO.findAll());

		request.setAttribute("listCalendario", calendarioForm.getListCalendario());

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
