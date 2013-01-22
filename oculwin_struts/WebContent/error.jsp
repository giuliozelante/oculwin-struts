<%@ page isErrorPage="true" import="java.io.*" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:errors/>
<%if(exception!=null)exception.printStackTrace(new java.io.PrintWriter(out));%>