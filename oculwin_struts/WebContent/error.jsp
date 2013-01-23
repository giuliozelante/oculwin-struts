<%@ page isErrorPage="true" import="java.io.*" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%if(request.getAttribute("exception")!=null)((Exception)request.getAttribute("exception")).printStackTrace(new java.io.PrintWriter(out));%>