package it.moda.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {

	public void init() throws ServletException {
		String prefix =  getServletContext().getRealPath("/");
		String file = getInitParameter("log4j-init-file");
		// if the log4j-init-file is not set, then no point in trying
		if(file != null) {
			PropertyConfigurator.configure(prefix+file);
		}
		getServletContext().log("logging to: "+prefix+file);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}
}