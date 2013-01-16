<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html:xhtml />
				<div id="loading" style="display: none;">
					<iframe id="slct_blck" src="about:blank"></iframe>
					<div class="center">
						<html:img src="/oculwin_struts/gfx/loader.gif" alt="loading" />
					</div>
				</div>
				<html:form action="/calendario" method="get">
					<html:hidden property="firstTime"/>
					<html:hidden property="data"/>
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
								<nested:iterate property="listCalendario" id="day" indexId="i">
								<c:choose>
									<c:when test="${i%2 == 0 }">
										<c:set var="row" value="odd"/>
									</c:when>
									<c:otherwise>
										<c:set var="row" value="even"/>
									</c:otherwise>
								</c:choose>
									<tr class="${row}">
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="baseMap"/>'">
											
											<nested:hidden property="id" />
											<fmt:formatDate value="${day.data}" dateStyle="full" />
											<!-- <nested:write property="data" /> -->
										</td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="baseMap"/>'"><nested:checkbox property="festivo" disabled="true" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="baseMap"/>'"><nested:write property="message" /></td>
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
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="baseMap"/>'">&#160;</td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="baseMap"/>'"><nested:write property="totaleI" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="baseMap"/>'"><nested:write property="totaleRr" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="baseMap"/>'"><nested:write property="totaleM" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="baseMap"/>'"><nested:write property="totaleB" /></td>
										<td onclick="document.location='<html:rewrite href="/oculwin_struts/calendario.do?method=loadAgendaDettaglio&data=${day.data}" name="calendarioForm" property="baseMap"/>'"><nested:write property="totaleR" /></td>
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
												<html:link href="/oculwin_struts/calendario.do?method=load&paginator.page=0" property="baseMap">&lt;&lt;</html:link>
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
					<div class="agendaTableContainer" id="divAppuntamenti">
						<table class="simpletablestyle">
							<thead>
								<tr>
									<th scope="col" style="width: 20%"><bean:message key="table.header.appuntamenti.paziente" /></th>
									<th scope="col" style="width: 10%"><bean:message key="table.header.appuntamenti.datanascita" /></th>
									<th scope="col" style="width: 5%"><bean:message key="table.header.appuntamenti.ora" /></th>
									<th scope="col" style="width: 10%"><bean:message key="table.header.appuntamenti.telefono" /></th>
									<th scope="col" style="width: 10%"><bean:message key="table.header.appuntamenti.tipoappuntamento" /></th>
									<th scope="col" style="width: 54%"><bean:message key="table.header.appuntamenti.note" /></th>
									<th scope="col" style="width: 1%">&#160;</th>
								</tr>
							</thead>
							<tbody>
								<nested:iterate property="appuntamenti" id="app" indexId="i">
							<c:choose>
								<c:when test="${i%2 == 0 }">
									<c:set var="row" value="odd"/>
								</c:when>
								<c:otherwise>
									<c:set var="row" value="even"/>
								</c:otherwise>
							</c:choose>
									<tr class="${row}">
										<td onclick="javascript:fillPazientiList(${i},this);" onkeypress="javascript:fillPazientiList(${i},this);">
											<span id="spanPden${i}"><nested:write property="pden" /></span>
										</td>
										<td onclick="javascript:fillPazientiList(${i},this);" onkeypress="javascript:fillPazientiList(${i},this);">
											<span id="spanPnascita${i}"><fmt:formatDate value="${app.datePnascita}" dateStyle="long" /></span>
										</td>
										<td onclick="javascript:fillPazientiList(${i},this);" onkeypress="javascript:fillPazientiList(${i},this);">
											<span id="spanOra${i}">
												<nested:write property="ora" />
											</span>
										</td>
										<td onclick="javascript:fillPazientiList(${i},this);" onkeypress="javascript:fillPazientiList(${i},this);"><nested:write property="ptel" /></td>
										<td onclick="javascript:fillPazientiList(${i},this);" onkeypress="javascript:fillPazientiList(${i},this);"><nested:write property="tiOpeAge" /></td>
										<td onclick="javascript:fillPazientiList(${i},this);" onkeypress="javascript:fillPazientiList(${i},this);"><nested:write property="note" /></td>
										<td><a href="javascript:void(0);" onclick="deleteAppuntamento(${i})"><html:img src="/oculwin_struts/gfx/delete.png" alt="delete" /></a></td>
									</tr>
									<!-- Hidden Row with Form Data -->
									<tr class="${row}" style="display: none;">
										<td>
											<nested:hidden property="pden" styleId="pden${i}"/>
											<nested:hidden property="pgAge" styleId="pgAge${i}"/>
											<div id="pazientiHiddenDiv${i}">
												<select name="pazienti" id="pazienti${i}" onchange="javascript:assignPaziente(this,${i});"></select>
											</div>
										</td>
										<td>
											<nested:text property="pnascita" styleId="pnascita${i}" readonly="true"/>
										</td>
										<td id="tdOra${i}" onclick="javascript:openSelectOra(${i},this)" onkeypress="javascript:openSelectOra(${i},this)">
											<div id="oraHiddenDiv${i}">
												<nested:select property="ora" styleId="ora${i}" onchange="javascript:assignOra(${i},this)">
													<html:option value="08:00">08:00</html:option>
													<html:option value="08:30">08:30</html:option>
													<html:option value="09:00">09:00</html:option>
													<html:option value="09:30">09:30</html:option>
													<html:option value="10:00">10:00</html:option>
													<html:option value="10:30">10:30</html:option>
													<html:option value="11:00">11:00</html:option>
													<html:option value="11:30">11:30</html:option>
													<html:option value="12:00">12:00</html:option>
													<html:option value="12:30">12:30</html:option>
													<html:option value="13:00">13:00</html:option>
													<html:option value="13:30">13:30</html:option>
													<html:option value="14:00">14:00</html:option>
													<html:option value="14:30">14:30</html:option>
													<html:option value="15:00">15:00</html:option>
													<html:option value="15:30">15:30</html:option>
													<html:option value="16:00">16:00</html:option>
													<html:option value="16:30">16:30</html:option>
													<html:option value="17:00">17:00</html:option>
												</nested:select>
											</div>
										</td>
										<td><nested:text property="ptel" styleId="ptel${i}"/></td>
										<td>
											<nested:select property="tiOpeAge" styleId="tiOpeAge${i}">
												<html:option value="A">Altro</html:option>
												<html:option value="I">Impronta</html:option>
												<html:option value="R">Rinnovo</html:option>
												<html:option value="M">Modelli</html:option>
												<html:option value="C">Controllo</html:option>
												<html:option value="L">Lucidatura</html:option>
											</nested:select>
										</td>
										<td><nested:select property="note" styleId="note${i}">
												<html:option value="IMPRONTA">IMPRONTA</html:option>
												<html:option value="RINNOVO">RINNOVO</html:option>
												<html:option value="RINNOVO IN GIORNATA">RINNOVO IN GIORNATA</html:option>
												<html:option value="CONSEGNA">CONSEGNA</html:option>
												<html:option value="CONTROLLO">CONTROLLO</html:option>
												<html:option value="MODIFICA">MODIFICA</html:option>
												<html:option value="LUCIDATURA">LUCIDATURA</html:option>
												<html:option value="SOSTITUZIONE">SOSTITUZIONE</html:option>
												<html:option value="SPEDIZIONE">SPEDIZIONE</html:option>
											</nested:select>
										</td>
										<td><a href="javascript:void(0);" onclick="saveChangesAppuntamento(${i})"><img src="/oculwin_struts/gfx/save_16.gif" alt="salva" style="visibility: hidden;"/></a></td>
									</tr>
								</nested:iterate>
							</tbody>
							<tfoot>
								<tr>
									<th colspan="7">
										<div style="width: 100%;text-align: center;white-space: nowrap;position: relative;">
											<div style="margin:0 auto;">
											<input type="button" onclick="saveAll()" value="<bean:message key="button.label.saveAll"/>" style="visibility: hidden"/>
											&nbsp;
											<html:reset onclick="resetAll()" style="visibility:hidden"><bean:message key="button.label.reset"/></html:reset>
											</div>
											
											<div style="position: absolute;white-space: nowrap;right:0;bottom: 2px;">
												<a href="javascript:void(0);" onclick="insertNewAppuntamento()">
													<html:img src="/oculwin_struts/gfx/plus_16.png" style="vertical-align: middle;"/>
													Aggiungi Nuovo Appuntamento
												</a>
											</div>
											</div>
									</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<div id="pazientiHiddenDiv" style="display: none;">
						<select name="pazienti" id="pazienti">
						</select>
					</div>
					
					</nested:notEmpty>
				</html:form>