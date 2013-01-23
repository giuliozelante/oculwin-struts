package it.moda.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public class BaseException extends ExceptionHandler {
	
	private static Logger log = Logger.getLogger(BaseException.class);
	
	@Override
	public ActionForward execute(Exception e, ExceptionConfig ec,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		log.error(e.getMessage(), e);
		request.setAttribute("exception", e);
	    
		return super.execute(e, ec, mapping, form, request, response);
	}
}
