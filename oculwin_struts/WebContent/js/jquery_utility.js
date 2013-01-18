var elem;
var i;
var newRowNum = 0;
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
	var pden=$(element).val().split("|")[0];
	var pnascita=$(element).val().split("|")[1];
	var formattedPnascita=$(element).val().split("|")[2];
	
	$("#spanPden"+index).html(pden);
	$("#pden"+index).val(pden);
	
	$("#spanPnascita"+index).html(formattedPnascita);
	$("#pnascita"+index).val(pnascita);
	
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
			$('#divAppuntamenti tfoot').find('[onclick*="insertNewAppuntamento('+newRowNum+')"]').attr('onclick',$('[onclick*="insertNewAppuntamento('+newRowNum+')"]').attr('onclick').replace(newRowNum,newRowNum-1));
			newRowNum--;
		}});
//		alert("OK");
	$('img[alt="loading"]').show();
	$("#loading").hide();
	

	
}

function updateCalendario(xml){
	$id = $(xml).find("id").text();
	//$elemId = $('#divCalendario table tr').next().find('input[value="'+$id+'"]').closest("tr");
    $tr = $('input[value="'+$id+'"]').closest("tr");
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
/**-----------------------------Inserisci Nuovo Appuntamento----------------------*/
function insertNewAppuntamento(index){
	$newRow=$($('.newRow')[newRowNum]);
	
	fillPazientiList(index,$newRow);
	$anotherNewRow=$newRow.clone();
	$newRow.after($anotherNewRow);
	
	if(index%2==0)
		$anotherNewRow.removeClass('odd').addClass('even');
	else
		$anotherNewRow.removeClass('even').addClass('odd');
	
	$anotherNewRow.find($('[id$="'+index+'"]')).each(function(i){
		$(this).attr('id',$(this).attr('id').replace(index,index+1));
	});
	$anotherNewRow.find($('[name*="'+index+'"]')).each(function(i){
		$(this).attr('name',$(this).attr('name').replace(index,index+1));
	});
	$anotherNewRow.find($('[onclick*="'+index+'"]')).each(function(i){
		$(this).attr('onclick',$(this).attr('onclick').replace(index,index+1));
	});
	
	$anotherNewRow.find($('select[onchange*="'+index+'"]')).each(function(i){
		$(this).attr('onchange',$(this).attr('onchange').replace(index,index+1));
	});
	
	$anotherNewRow.find($('[onkeypress*="'+index+'"]')).each(function(i){
		$(this).attr('onkeypress',$(this).attr('onkeypress').replace(index,index+1));
	});

	 $('#divAppuntamenti tfoot').find('[onclick*="insertNewAppuntamento('+index+')"]').attr('onclick',$('[onclick*="insertNewAppuntamento('+index+')"]').attr('onclick').replace(index,index+1));
	
	$newRow.show();
	
	newRowNum++;
}

function resetAll(){
	var deletedNewRows = 0;
	$('div#divAppuntamenti td').attr("style","");
	$('div#divAppuntamenti img[alt="salva"]').css("visibility","hidden");
	$('div#divAppuntamenti tr.hiddenTr').css("display",'none');
	$('div#divAppuntamenti tr:not(.hiddenTr)').show();
	$('div#divAppuntamenti tr.newRow').each(function(i){
		if(i!=0){$(this).remove();deletedNewRows++;}
	});
	if(deletedNewRows>0){
		$('#divAppuntamenti tfoot').find('[onclick*="insertNewAppuntamento('+newRowNum+')"]').attr('onclick',$('[onclick*="insertNewAppuntamento('+newRowNum+')"]').attr('onclick').replace(newRowNum,newRowNum-1));
		newRowNum--;
	}
}

/** ----------------------Functions Assigned at page LOAD ---------------------*/
var fun=new Array();

function dataChanged(element){
	element.closest('td').css("background","#CFB");
	element.closest('td').nextAll().find('img[alt="salva"]').css("visibility","visible");
	$('div#divAppuntamenti tfoot input').css("visibility","visible");
	$('a').not($('tfoot a')).click(function(event){
		if(confirm("Hai effettuato delle modifiche, sicuro di voler uscire?"))
			return true;
		else
			return false;
	});
	$('td').not($('div#divAppuntamenti td')).each(function(i){
		 // your button
        var td = $(this); 

        // original click handler
        var clickhandler = td.attr("onclick");
        td.attr("onclick", "return false;");
        td.attr("onkeypress", "return false;");

        // new click handler
        td.click(function(){
		if(confirm("Hai effettuato delle modifiche, sicuro di voler uscire?"))
			eval(clickhandler);
		else
			return false;
        });
        td.keypress(function(){
    		if(confirm("Hai effettuato delle modifiche, sicuro di voler uscire?"))
    			eval(clickhandler);
    		else
    			return false;
            });
		
	});
}
$(document).ready(function(){
	//fun=$('div#divCalendario td').attr('onclick');
//	$('div#divCalendario td').each(function(i){
//		fun[i]=$(this).attr('onclick');
//		$(this).attr('onclick','');
		//$(this).on('click',eval(fun[i]));
//	});
	//$('div#divCalendario td').on('click',fun);
	$('div#divAppuntamenti').on("change",'select',function() {
		dataChanged($(this));
	});
	$('div#divAppuntamenti').on("keyup", 'input[type="text"]',function() {
		dataChanged($(this));
	});
	newRowNum = $('div#divAppuntamenti tbody tr').length-1;
});

	/*
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
	  */	

//function alertExitingWithoutSaving(action){
//	
//}

/**----------------------------Alert Messages--------------------------
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
*/