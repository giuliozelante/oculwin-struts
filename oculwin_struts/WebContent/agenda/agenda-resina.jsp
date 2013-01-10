
<%
	try {
%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html:html>
<head>
<title>OculWin - Sistema Informativo Contabile Integrato</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet"
	href="/oculwin_struts/css/screen.css" />
<!--[if !IE 7]>
	<style type="text/css">
		#wrap {display:table;height:100%}
	</style><![endif]-->
</head>
<body>
	<div id="container">
		<div id="main">
			<div id="header">
				<img src="/oculwin_struts/gfx/testata.jpg" />
			</div>
			<div id="navigation">
				<strong>Agenda</strong>
				<hr />
				<ul>
					<li><html:link page="/calendario.do?method=load">Agenda Resina</html:link></li>
				</ul>
			</div>
			<div id="content">
				<h1>Agenda Resina</h1>
				<html:form action="/calendario" method="get">
					<div class="agendaTableContainer">
						<table class="simpletablestyle">
							<thead>
								<tr>
									<th scope="col"><bean:message key="table.header.data" /></th>
									<th scope="col"><bean:message key="table.header.off" /></th>
									<th scope="col"><bean:message key="table.header.avvisi" /></th>
									<th scope="col"><bean:message key="table.header.impronta" /></th>
									<th scope="col"><bean:message key="table.header.rinnovo" /></th>
									<th scope="col"><bean:message key="table.header.modifica" /></th>
									<th scope="col"><bean:message key="table.header.baby" /></th>
									<th scope="col">&#160;</th>
									<th scope="col"><bean:message key="table.header.num.impronta" /></th>
									<th scope="col"><bean:message key="table.header.num.rinnovo" /></th>
									<th scope="col"><bean:message key="table.header.num.modifica" /></th>
									<th scope="col"><bean:message key="table.header.num.baby" /></th>
									<th scope="col"><bean:message key="table.header.num.altro" /></th>
								</tr>
							</thead>
							<tbody>
								<nested:iterate property="listCalendario" id="day">
									<tr>
										<td><nested:hidden property="id" /><fmt:formatDate value="${day.data}" dateStyle="full" /></td>
										<td><nested:checkbox property="festivo" disabled="true" /></td>
										<td><nested:write property="message" /></td>
										<td><nested:write property="maxAgeR" /></td>
										<td><nested:write property="maxAgeRr" /></td>
										<td><nested:write property="maxAgeC2" /></td>
										<td><nested:write property="maxAgeB" /></td>
										<td>&#160;</td>
										<td><nested:write property="totaleI" /></td>
										<td><nested:write property="totaleRr" /></td>
										<td><nested:write property="totaleM" /></td>
										<td><nested:write property="totaleB" /></td>
										<td><nested:write property="totaleR" /></td>
									</tr>
								</nested:iterate>
							</tbody>
							<tfoot>
								<tr>
									<th colspan="13" style="text-align: center">
										<nested:nest property="paginator">
											<!--page: ${calendarioForm.paginator.page} -->
											<!--rowNums: ${calendarioForm.paginator.rowNums} -->
											<!--totRows: ${calendarioForm.paginator.totRows} -->
											<!--numPages: ${calendarioForm.paginator.numPages} -->
											<nested:notEqual property="page" value="0">
												<a href="/oculwin_struts/calendario.do?method=load&paginator.page=0">&lt;&lt;</a>
												<a href="/oculwin_struts/calendario.do?method=load&paginator.page=${calendarioForm.paginator.page-1}">&lt;</a>
											</nested:notEqual>${calendarioForm.paginator.page+1}
											<nested:notEqual property="page" value="${numPages}">
												<a href="/oculwin_struts/calendario.do?method=load&paginator.page=${calendarioForm.paginator.page+1}">&gt;</a>
												<a href="/oculwin_struts/calendario.do?method=load&paginator.page=${calendarioForm.paginator.numPages}">&gt;&gt;</a>
											</nested:notEqual>
										</nested:nest></th>
								</tr>
							</tfoot>
						</table>
						<!-- 
			<h:dataTable var="_appuntamento" value="#{appuntamenti}"
				rendered="#{not empty appuntamenti}" styleClass="simpletablestyle">
				<h:column>
					<f:facet name="header"><bean:message key="table.header.data" /></f:facet>
					<h:outputText value="#{_appuntamento.data}">
				        <f:convertDateTime dateStyle="full" type="date" />
				    </h:outputText>
           	 	</h:column>
				<h:column>
					<f:facet name="header"><bean:message key="table.header.off" /></f:facet>
					<h:selectBooleanCheckbox value="#{_appuntamento.festivo}"
						disabled="true" />
				</h:column>
				<h:column>
					<f:facet name="header"><bean:message key="table.header.avvisi" /></f:facet>
                	#{_appuntamento.message}
            	</h:column>
				<h:column>
					<f:facet name="header"><bean:message key="table.header.impronta" /></f:facet>
                	#{_appuntamento.maxAgeR}
            	</h:column>
            	<h:column>
					<f:facet name="header"><bean:message key="table.header.rinnovo" /></f:facet>
                	#{_appuntamento.maxAgeRr}
            	</h:column>
            	<h:column>
					<f:facet name="header"><bean:message key="table.header.modifica" /></f:facet>
                	#{_appuntamento.maxAgeC2}
            	</h:column>
            	<h:column>
					<f:facet name="header"><bean:message key="table.header.baby" /></f:facet>
                	#{_appuntamento.maxAgeB}
            	</h:column>
           		<h:column>
					<f:facet name="header">&#160;</f:facet>
                	&#160;
            	</h:column>
            	<h:column>
					<f:facet name="header"><bean:message key="table.header.num.impronta" /></f:facet>
                	#{_appuntamento.totaleI}
            	</h:column>
            	<h:column>
					<f:facet name="header"><bean:message key="table.header.num.rinnovo" /></f:facet>
                	#{_appuntamento.totaleRr}
            	</h:column>
            	<h:column>
					<f:facet name="header"><bean:message key="table.header.num.modifica" /></f:facet>
                	#{_appuntamento.totaleM}
            	</h:column>
            	<h:column>
					<f:facet name="header"><bean:message key="table.header.num.baby" /></f:facet>
                	#{_appuntamento.totaleB}
            	</h:column>
            	<h:column>
					<f:facet name="header"><bean:message key="table.header.num.altro" /></f:facet>
                	#{_appuntamento.totaleR}
            	</h:column>
				<f:facet name="footer">

				</f:facet>
			</h:dataTable>
			 -->
					</div>
				</html:form>
			</div>
		</div>
	</div>
	<div id="footer">
		Copyright &copy;
		<script type="text/javascript">
			var d = new Date();
			document.write(d.getFullYear());
		</script>
		- Layout and graphics are exclusive properties of Mo.Da. s.r.l.
	</div>
</body>
</html:html>
<%}catch(Exception e){out.print(e.getStackTrace());} 
%>
