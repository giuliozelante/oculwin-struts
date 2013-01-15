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
function assignPaziente(valore,index){
	$("#spanPden"+index).html(valore.split("|")[0]);
	$("#pden"+index).val(valore.split("|")[0]);
	
	$("#spanPnascita"+index+"0").html(valore.split("|")[2]);
	$("#spanPnascita"+index+"1").html(valore.split("|")[2]);
	$("#pnascita"+index).val(valore.split("|")[1]);
	
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
}
$(document).ready(function(){
	$("div#divAppuntamenti tbody tr").hover(function() {
		$(this).first("td").show();
	});
});