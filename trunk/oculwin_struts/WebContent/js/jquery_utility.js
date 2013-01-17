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
	$.ajax({
		type: "GET",
		url: "calendario.do?method=deleteAppuntamento&id="+index+"&"+str,
		dataType: "xml",
		success:function(xml){
/**----------------------CalendarioDTO xml Example -------------------------
*		    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
*		    <calendarioDTO>
*		        <data>2013-01-17T00:00:00+01:00</data>
*		        <eliminato>false</eliminato>
*		        <festivo>false</festivo>
*		        <id>4080</id>
*		        <maxAgeB>0</maxAgeB>
*		        <maxAgeC1>3</maxAgeC1>
*		        <maxAgeC2>3</maxAgeC2>
*		        <maxAgeR>6</maxAgeR>
*		        <maxAgeRr>6</maxAgeRr>
*		        <totaleB>0</totaleB>
*		        <totaleC11>0</totaleC11>
*		        <totaleC8>0</totaleC8>
*		        <totaleI>0</totaleI>
*		        <totaleM>0</totaleM>
*		        <totaleR>1</totaleR>
*		        <totaleRr>0</totaleRr>
*		    </calendarioDTO>
*/
		    
/**---------------------Aggiorno il calendario------------------*/
		   updateCalendario(xml);
/**---------------------Elimino la riga selezionata-------------------*/
		    $('#divAppuntamenti table').find('tr:eq('+index+1+')').remove();
		    $tr = $('#divAppuntamenti table tr');
		    $tr.each(function( i ) {
		    	if(i!=0||i!=$tr.length)
		    		(i-1)%2==0?$(this).removeClass().addClass('even'):$(this).removeClass().addClass('odd');
	    	});
		}});
//		alert("OK");
	$('img[alt="loading"]').show();
	$("#loading").hide();
}

function updateCalendario(xml){
	$id = $(xml).find("id").text();
	//$elemId = $('#divCalendario table tr').next().find('input[value="'+$id+'"]').closest("tr");
    $tr = $('input[value="'+id+'"]').closest("tr");
    $tr.find('input[name$="festivo"]').val( $(xml).find('festivo').text());
    $tr.find('td:eq(2)').html( $(xml).find('message').text());
//    ${(day.maxAgeR-day.totaleI) > 0 && !day.festivo }?"DISP":"COMP"
    if((parseInt($(xml).find('maxAgeR').text())-parseInt($(xml).find('totaleI').text())) > 0 && !parseInt($(xml).find('festivo').text())){
    	$tr.find('td:eq(3)').html("DISP.");
    	$tr.find('td:eq(3)').removeClass().addClass("disp");
    }else{
    	$tr.find('td:eq(3)').html("COMP.");
    	$tr.find('td:eq(3)').removeClass().addClass("comp");
    }
//    ${(day.maxAgeRr-day.totaleRr) > 0 && !day.festivo }?"DISP":"COMP"
    if((parseInt($(xml).find('maxAgeRr').text())-parseInt($(xml).find('totaleRr').text())) > 0 && !parseInt($(xml).find('festivo').text())){
    	$tr.find('td:eq(4)').html("DISP.");
    	$tr.find('td:eq(4)').removeClass().addClass("disp");
    }else{
    	$tr.find('td:eq(4)').html("COMP.");
    	$tr.find('td:eq(4)').removeClass().addClass("comp");
    }
//    ${(day.maxAgeC2-day.totaleM) > 0 && !day.festivo }
    if((parseInt($(xml).find('maxAgeC2').text())-parseInt($(xml).find('totaleM').text())) > 0 && !parseInt($(xml).find('festivo').text())){
    	$tr.find('td:eq(5)').html("DISP.");
    	$tr.find('td:eq(5)').removeClass().addClass("disp");
    }else{
    	$tr.find('td:eq(5)').html("COMP.");
    	$tr.find('td:eq(5)').removeClass().addClass("comp");
    }
//    ${(day.maxAgeB-day.totaleB) > 0 && !day.festivo }
    if((parseInt($(xml).find('maxAgeB').text())-parseInt($(xml).find('totaleB').text())) > 0 && !parseInt($(xml).find('festivo').text())){
    	$tr.find('td:eq(6)').html("DISP.");
    	$tr.find('td:eq(6)').removeClass().addClass("disp");
    }else{
    	$tr.find('td:eq(6)').html("COMP.");
    	$tr.find('td:eq(6)').removeClass().addClass("comp");
    }
    $tr.find('td:eq(8)').html($(xml).find('totaleI').text());
    $tr.find('td:eq(9)').html($(xml).find('totaleRr').text());
    $tr.find('td:eq(10)').html($(xml).find('totaleM').text());
    $tr.find('td:eq(11)').html($(xml).find('totaleB').text());
    $tr.find('td:eq(12)').html($(xml).find('totaleR').text());
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
	 // Initially, hide them all
	 hideAllMessages();
	 
	 // Show message
//	 for(var i=0;i<myMessages.length;i++)
//	 {
//		showMessage(myMessages[i]);
//	 }
	 
	 // When message is clicked, hide it
	 $('.message').click(function(){			  
			  $(this).animate({top: -$(this).outerHeight()}, 500);
	  });	
});

/**----------------------------Alert Messages--------------------------*/
var myMessages = ['info','warning','error','success'];
function hideAllMessages()
{
		 var messagesHeights = new Array(); // this array will store height for each
	 
		 for (i=0; i<myMessages.length; i++)
		 {
				  messagesHeights[i] = $('.' + myMessages[i]).outerHeight(); // fill array
				  $('.' + myMessages[i]).css('top', -messagesHeights[i]); //move element outside viewport	  
		 }
}
function showMessage(type)
{
	$('.'+ type +'-trigger').click(function(){
		  hideAllMessages();				  
		  $('.'+type).animate({top:"0"}, 500);
	});
}