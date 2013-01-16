var elem;
var i;
function fillPazientiList(index,element) {
	elem=element;
//	i=index;
//	$(element).removeAttr('onclick');
//	$(element).removeAttr('onkeypress');
	$("#loading").show();
	$(element.parentNode).hide();
	$(element.parentNode).next("tr").show();
//	$(element).children("#spanPden"+index).html("");
	if($("#pazienti").children().length==0){
		$.get("calendario.do?method=fillPazientiList",function(response){
			$("#loading").hide();
			$("#pazienti").html(response);
			$("#pazienti"+index).html($("#pazienti").html());
		});
	}else{
		$("#loading").hide();
		$("#pazienti"+index).html($("#pazienti").html());
	}
}
//var fillPazienti=function fillPazientiList() {
//	$(element).removeAttr('onclick');
//	$(element).removeAttr('onkeypress');
//	$(element).children("div").show();
//	$(element).children("#spanPden"+i).html("");
//	if($("#pazienti").children().length==0){
//		$.get("calendario.do?method=fillPazientiList",function(response){
//			$("#pazienti").html(response);
//			$("#pazienti"+i).html($("#pazienti").html());
//		});
//	}else{
//		$("#pazienti"+i).html($("#pazienti").html());
//	}
//};
function assignPaziente(element,index){
//	element.parentNode.parentNode.style.background="#f00";
//	element.parentNode.parentNode.nextElementSibling.style.background="#f00";
	var pden=$(element).val().split("|")[0];
	var pnascita=$(element).val().split("|")[1];
	var formattedPnascita=$(element).val().split("|")[2];
	
//	$(element).append("<option value=\""+pden+"\">"+pden+"</option>");
//	$(element option:eq(3)).attr('selected', 'selected')
	
	$("#spanPden"+index).html(pden);
	$("#pden"+index).val(pden);
	
	$("#spanPnascita"+index).html(formattedPnascita);
	$("#pnascita"+index).val(pnascita);
	
//	$(elem).show();
//	$(elem).next("tr").hide();

	//alert($("#spanPden"+index).html());
	
//	elem.onclick=fillPazienti;
//	elem.onkeypress=fillPazienti;
	
//	$(elem).click(function() {
//		fillPazientiList(index,elem);
//	});
//	$(elem).keypress(function() {
//		fillPazientiList(index,elem);
//	});
	
}
function openSelectOra(index,obj){
}
function assignOra(index,obj){
	$("#ora"+index).html($(obj).val);
//	obj.parentNode.parentNode.style.background="#f00";
}
function deleteAppuntamento(index){
	$("#loading").show();
	$('img[alt="loading"]').hide();
	if(confirm("Sei sicuro di voler eliminare l'appuntamento selezionato?"))
		var str = $('#calendarioForm').serialize();
		$.get("calendario.do?method=deleteAppuntamento&id="+index+"&"+str,function(response){
			xmlDoc = $.parseXML( response );
		    $xml = $( xmlDoc );
		    $id = $xml.find("id");
		    $elemId = $('#divCalendario table tr').find('input[name$="id"]').find('input[value="'+id+'"]').closest("tr");
		    $elemId.find('input[name$="festivo"]').val( $xml.find('festivo'));
		    $elemId.find('td:eq(2)').html( $xml.find('message'));
//			this.data = data;
//			this.festivo = festivo;
//			this.message = message;
//			this.maxAgeC1 = maxAgeC1;
//			this.maxAgeC2 = maxAgeC2;
//			this.maxAgeRr = maxAgeRr;
//			this.maxAgeR = maxAgeR;
//			this.eliminato = eliminato;
//			this.totaleR = totaleR;
//			this.totaleRr = totaleRr;
//			this.totaleC8 = totaleC8;
//			this.totaleC11 = totaleC11;
//			this.totaleI = totaleI;
//			this.totaleM = totaleM;
//			this.maxAgeB = maxAgeB;
//			this.totaleB = totaleB;
//		    $title = $xml.find( "title" );
		});
//		alert("OK");
	$('img[alt="loading"]').show();
	$("#loading").hide();
}

/** ----------------------Functions Assigned at page LOAD ---------------------*/
$(document).ready(function(){
	$('div#divAppuntamenti select').on("change",function() {
		$(this).closest('td').css("background","#CFB");
		$(this).closest('td').next().find('input[name$="pnascita"]').closest('td').css("background","#CFB");
		$(this).closest('td').nextAll().find('img[alt="salva"]').css("visibility","visible");
		$('div#divAppuntamenti tfoot input').css("visibility","visible");
	});
	$('div#divAppuntamenti input[type="text"]').on("keyup",function() {
		$(this).closest('td').css("background","#CFB");
		$(this).closest('td').nextAll().find('img[alt="salva"]').css("visibility","visible");
		$('div#divAppuntamenti tfoot input').css("visibility","visible");
	});
});
