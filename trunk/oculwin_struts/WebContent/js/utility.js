/** (C) HTML.IT - insieme di funzioni ed oggetti utili per interagire con ajax */

/** FUNZIONI */

	// funzione per prendere un elemento con id univoco
		function prendiElementoDaId(id_elemento) {
			var elemento;
			if(document.getElementById)
				elemento = document.getElementById(id_elemento);
			else
				elemento = document.all[id_elemento];
			return elemento;
		};
	
	// funzione per assegnare un oggetto XMLHttpRequest
		function assegnaXMLHttpRequest() {
			var
				XHR = null,
				browserUtente = navigator.userAgent.toUpperCase();
			if(typeof(XMLHttpRequest) === "function" || typeof(XMLHttpRequest) === "object")
				XHR = new XMLHttpRequest();
			else if(window.ActiveXObject && browserUtente.indexOf("MSIE 4") < 0) {
				if(browserUtente.indexOf("MSIE 5") < 0)
					XHR = new ActiveXObject("Msxml2.XMLHTTP");
				else
					XHR = new ActiveXObject("Microsoft.XMLHTTP");
			}
			return XHR;
		};



/** OGGETTI / ARRAY */

	// oggetto di verifica stato
		var readyState = {
			INATTIVO:	0,
			INIZIALIZZATO:	1,
			RICHIESTA:	2,
			RISPOSTA:	3,
			COMPLETATO:	4
		};

	// array descrittivo dei codici restituiti dal server
	// [la scelta dell' array è per evitare problemi con vecchi browsers]
		var statusText = new Array();
		statusText[100] = "Continue";
		statusText[101] = "Switching Protocols";
		statusText[200] = "OK";
		statusText[201] = "Created";
		statusText[202] = "Accepted";
		statusText[203] = "Non-Authoritative Information";
		statusText[204] = "No Content";
		statusText[205] = "Reset Content";
		statusText[206] = "Partial Content";
		statusText[300] = "Multiple Choices";
		statusText[301] = "Moved Permanently";
		statusText[302] = "Found";
		statusText[303] = "See Other";
		statusText[304] = "Not Modified";
		statusText[305] = "Use Proxy";
		statusText[306] = "(unused, but reserved)";
		statusText[307] = "Temporary Redirect";
		statusText[400] = "Bad Request";
		statusText[401] = "Unauthorized";
		statusText[402] = "Payment Required";
		statusText[403] = "Forbidden";
		statusText[404] = "Not Found";
		statusText[405] = "Method Not Allowed";
		statusText[406] = "Not Acceptable";
		statusText[407] = "Proxy Authentication Required";
		statusText[408] = "Request Timeout";
		statusText[409] = "Conflict";
		statusText[410] = "Gone";
		statusText[411] = "Length Required";
		statusText[412] = "Precondition Failed";
		statusText[413] = "Request Entity Too Large";
		statusText[414] = "Request-URI Too Long";
		statusText[415] = "Unsupported Media Type";
		statusText[416] = "Requested Range Not Satisfiable";
		statusText[417] = "Expectation Failed";
		statusText[500] = "Internal Server Error";
		statusText[501] = "Not Implemented";
		statusText[502] = "Bad Gateway";
		statusText[503] = "Service Unavailable";
		statusText[504] = "Gateway Timeout";
		statusText[505] = "HTTP Version Not Supported";
		statusText[509] = "Bandwidth Limit Exceeded";
/** ----------------------------------------------------------------------*/
var fun;
function assignPaziente(valore,index){
	document.getElementById("pden"+index).innerHTML=valore.split("|")[0];
	document.getElementsByName("appuntamenti["+index+"].pden")[0].value=valore.split("|")[0];
	
	document.getElementById("pnascita"+index).innerHTML=valore.split("|")[2];
	document.getElementsByName("appuntamenti["+index+"].pnascita")[0].value=valore.split("|")[1];
	
	document.getElementById("tdPaziente"+index).onclick=function(){document.getElementById('pazienti'+index).style.display='block';};
	//document.getElementById("pazienti"+index).style.display='none';
	
}

function createRequestObject() {
	var ro;
	var browser = navigator.appName;
	if(browser == "Microsoft Internet Explorer"){
		ro = new ActiveXObject("Microsoft.XMLHTTP");
	}else{
		ro = new XMLHttpRequest();
	}
	return ro;
}

var http = createRequestObject();

function sndReq(Search,filter,page) {
	
	/*var FValue=filter.value;
	for(var i=0;i<filter.length;i++){
		if(filter.item(i).checked){
			FValue=filter.item(i).value;
		}
	}*/
	http.open('get', 'search.php?search='+Search.value+'&filter='+filter+'&page='+page, true);
	http.setRequestHeader('Content-Type',  "text/xml");
	http.onreadystatechange = handleResponse;
	http.send(null);
}

function fillPazientiList(i,element) {
	
	/*var FValue=filter.value;
	for(var i=0;i<filter.length;i++){
		if(filter.item(i).checked){
			FValue=filter.item(i).value;
		}
	}*/
	fun=element;
	element.onclick=function(){javascript:void(0);};
	document.getElementById("pden"+i).innerHTML="";
	document.getElementById('pazientiHiddenDiv'+i).style.display='block';
	if(document.getElementById('pazienti').innerHTML.trim()==''){
		http.open('get', 'calendario.do?method=fillPazientiList', true);
		http.setRequestHeader('Content-Type',  "text/xml");
		http.onreadystatechange = function foo() {
			if((http.readyState == 4)&&(http.status == 200)){
				var response = http.responseText;
				document.getElementById('pazienti').innerHTML = response;
				document.getElementById('pazienti'+i).innerHTML=document.getElementById('pazienti').innerHTML;
			}
		};//handleResponse;
		http.send(null);
	}
	else{
		document.getElementById('pazienti'+i).innerHTML=document.getElementById('pazienti').innerHTML;
	}
}

function handleResponse() {
	if((http.readyState == 4)&&(http.status == 200)){
		var response = http.responseText;
		//var update = new Array();

	//	if(response.indexOf('|' != -1)) {
			//update = response.split('|');
			//var node=response.getElementsByTagName('result');
			//alert(node);
			//for(var i=0;i<node.length;i++){
			document.getElementById('result').innerHTML = response;
		//}
	}
}
var innerHTML;
function viewNewsContent(id){
	innerHTML=document.getElementById("result").innerHTML;
        content=document.getElementById(id).value;
	innerHTML=content+'<br /><center><input type="button" value="close" onclick="resetState()"/></center>';
}
function resetState(){
	document.getElementById("result").innerHTML=innerHTML;
}