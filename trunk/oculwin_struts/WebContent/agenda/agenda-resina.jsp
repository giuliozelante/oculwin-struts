<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html:html>
<head>
	<title>OculWin - Sistema Informativo Contabile Integrato</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="/oculwin_struts/css/screen.css" />
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
					<li><a href="/oculwin_struts/agenda/agenda-resina.jsp">Agenda Resina</a></li>
				</ul>
			</div>
			<div id="content">
		<h1>Agenda Resina</h1>
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
						<tr>
							<td>
								<h:commandLink action="#{agendaDettaglio(_appuntamento.data)}">
									<h:outputText value="#{_appuntamento.data}">
										<f:convertDateTime dateStyle="full" type="date" />
									</h:outputText>
								</h:commandLink>
							</td>
							<td><h:selectBooleanCheckbox
									value="#{_appuntamento.festivo}" disabled="true" />
							</td>
							<td>#{_appuntamento.message}</td>
							<td>#{_appuntamento.maxAgeR}</td>
							<td>#{_appuntamento.maxAgeRr}</td>
							<td>#{_appuntamento.maxAgeC2}</td>
							<td>#{_appuntamento.maxAgeB}</td>
							<td>&#160;</td>
							<td>#{_appuntamento.totaleI}</td>
							<td>#{_appuntamento.totaleRr}</td>
							<td>#{_appuntamento.totaleM}</td>
							<td>#{_appuntamento.totaleB}</td>
							<td>#{_appuntamento.totaleR}</td>
						</tr>
				</tbody>
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
