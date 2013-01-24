<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:html xhtml="true" lang="true">
<head>
<title>OculWin - Sistema Informativo Contabile Integrato - <tiles:getAsString name="title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="/oculwin_struts/css/screen.css" />
<!--[if !IE 7]>
	<style type="text/css">
		#wrap {display:table;height:100%}
	</style><![endif]-->
	<script type="text/javascript" src="/oculwin_struts/js/utility.js">
	<!--// AJAX utilities -->
	</script>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js">
	<!--// jquery -->
	</script>
	<script src="/oculwin_struts/js/jquery.validate.js"></script>
	<script src="/oculwin_struts/js/jquery_utility.js">
	<!--// JQuery utilities -->
	</script>
</head>
<body>
		<div id="loading" style="display: none;">
			<iframe id="slct_blck" src="about:blank"></iframe>
			<div class="center">
				<html:img src="/oculwin_struts/gfx/loader.gif" alt="loading" />
			</div>
		</div>
	<!-- 
	<div class="info message">
		 <h3></h3>
		 <p></p>
		</div>
		
		<div class="error message">
			 <h3>E' avvenuto un'errore</h3>
			 <p></p>
		</div>
		
		<div class="warning message">
			 <h3>Attenzione!</h3>
			 <p></p>
		</div>
		
		<div class="success message">
			 <h3>Ce l'hai fatta!</h3>
			 <p></p>
		</div>
	-->
	<div id="wrap">
		<div id="header">
			<img src="/oculwin_struts/gfx/testata.jpg" alt="testata" />
		</div>
		<div id="main">
			<div id="navigation">
				<strong>Agenda</strong>
				<hr />
				<ul>
					<li><html:link page="/calendario.do?method=load">Agenda Resina</html:link></li>
				</ul>
			</div>
			<div id="content">
				<h1><tiles:getAsString name="title"/></h1>
				<html:errors />
				<tiles:insert name="content" />
			</div>
		</div>
	</div>
	<div id="footer">
		Copyright &copy;
		<script type="text/javascript">
		<!--//
			var d = new Date();
			document.write(d.getFullYear());
		//-->
		</script>
		- Layout and graphics are exclusive properties of Mo.Da. s.r.l.
	</div>
</body>
</html:html>