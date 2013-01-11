<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
				<html:form action="/calendario" method="get">
					<html:hidden property="firstTime"/>
					<div class="agendaTableContainer">
						<table class="simpletablestyle">
							<thead>
								<tr>
									<th scope="col" style="width: 20%"><bean:message key="table.header.data" /></th>
									<th scope="col" style="width: 1%"><bean:message key="table.header.off" /></th>
									<th scope="col" style="width: 30%"><bean:message key="table.header.avvisi" /></th>
									<th scope="col" style="width: 3%"><bean:message key="table.header.impronta" /></th>
									<th scope="col" style="width: 3%"><bean:message key="table.header.rinnovo" /></th>
									<th scope="col" style="width: 3%"><bean:message key="table.header.modifica" /></th>
									<th scope="col" style="width: 3%"><bean:message key="table.header.baby" /></th>
									<th scope="col" style="width: 10%">&#160;</th>
									<th scope="col" style="width: 3%"><bean:message key="table.header.num.impronta" /></th>
									<th scope="col" style="width: 3%"><bean:message key="table.header.num.rinnovo" /></th>
									<th scope="col" style="width: 3%"><bean:message key="table.header.num.modifica" /></th>
									<th scope="col" style="width: 3%"><bean:message key="table.header.num.baby" /></th>
									<th scope="col" style="width: 3%"><bean:message key="table.header.num.altro" /></th>
								</tr>
							</thead>
							<tbody>
								<nested:iterate property="listCalendario" id="day">
									<tr>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="values"/>'">
											
											<nested:hidden property="id" />
											<fmt:formatDate value="${day.data}" dateStyle="full" />
											<!-- <nested:write property="data" /> -->
										</td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="values"/>'"><nested:checkbox property="festivo" disabled="true" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="values"/>'"><nested:write property="message" /></td>
										<!-- =IIf((([Max_age_R]-[Num_app]>0 Or IsNull([Max_age_R]-[Num_app])) And [festivo]=0);"DISP";"COMP") -->
										<c:choose>
											<c:when test="${(day.maxAgeR-day.totaleI) > 0 && !day.festivo }">
												<td style="background: #0f0">DISP.<!-- ${day.maxAgeR} ${day.totaleI}  ${day.festivo} --></td>
											</c:when>
											<c:otherwise>
												<td style="background: #F00">COMP.</td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${(day.maxAgeRr-day.totaleRr) > 0 && !day.festivo }">
												<td style="background: #0f0">DISP.</td>
											</c:when>
											<c:otherwise>
												<td style="background: #F00">COMP.</td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${(day.maxAgeC2-day.totaleM) > 0 && !day.festivo }">
												<td style="background: #0f0">DISP.</td>
											</c:when>
											<c:otherwise>
												<td style="background: #F00">COMP.</td>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${(day.maxAgeB-day.totaleB) > 0 && !day.festivo }">
												<td style="background: #0f0">DISP.</td>
											</c:when>
											<c:otherwise>
												<td style="background: #F00">COMP.</td>
											</c:otherwise>
										</c:choose>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="values"/>'">&#160;</td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="values"/>'"><nested:write property="totaleI" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="values"/>'"><nested:write property="totaleRr" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="values"/>'"><nested:write property="totaleM" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="values"/>'"><nested:write property="totaleB" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="values"/>'"><nested:write property="totaleR" /></td>
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
												<html:link href="/oculwin_struts/calendario.do?method=load&paginator.page=0" property="values">&lt;&lt;</html:link>
												<a href="/oculwin_struts/calendario.do?method=load&paginator.page=${calendarioForm.paginator.page-1}&firstTime=${calendarioForm.firstTime}">&lt;</a>
											</nested:notEqual>${calendarioForm.paginator.page+1}
											<nested:notEqual property="page" value="${numPages}">
												<a href="/oculwin_struts/calendario.do?method=load&paginator.page=${calendarioForm.paginator.page+1}&firstTime=${calendarioForm.firstTime}">&gt;</a>
												<a href="/oculwin_struts/calendario.do?method=load&paginator.page=${calendarioForm.paginator.numPages}&firstTime=${calendarioForm.firstTime}">&gt;&gt;</a>
											</nested:notEqual>
										</nested:nest></th>
								</tr>
							</tfoot>
						</table>
					</div>
					<nested:notEmpty property="appuntamenti">
					<br />
					<h1>Appuntamenti</h1>
					<div class="agendaTableContainer">
						<table class="simpletablestyle">
							<thead>
								<tr>
									<th scope="col" style="width: 20%"><bean:message key="table.header.appuntamenti.paziente" /></th>
									<th scope="col" style="width: 10%"><bean:message key="table.header.appuntamenti.datanascita" /></th>
									<th scope="col" style="width: 5%"><bean:message key="table.header.appuntamenti.ora" /></th>
									<th scope="col" style="width: 10%"><bean:message key="table.header.appuntamenti.telefono" /></th>
									<th scope="col" style="width: 10%"><bean:message key="table.header.appuntamenti.tipoappuntamento" /></th>
									<th scope="col" style="width: 45%"><bean:message key="table.header.appuntamenti.note" /></th>
								</tr>
							</thead>
							<tbody>
								<nested:iterate property="appuntamenti" id="app">
									<tr>
										<td><nested:write property="pden" /><html:select property="pazienti" /></td>
										<td><fmt:formatDate value="${app.pnascita}" dateStyle="long" /></td>
										<td><nested:write property="ora" /></td>
										<td><nested:write property="ptel" /></td>
										<td><nested:write property="tiOpeAge" /></td>
										<td><nested:write property="note" /></td>
									</tr>
								</nested:iterate>
							</tbody>
						</table>
					</div>
					</nested:notEmpty>
				</html:form>