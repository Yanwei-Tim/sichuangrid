<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="floatingPopulationPrint">
<style type="text/css">
		*{
			padding:0;
			margin:0;
			font-size:12px;
			font-family: Arial;
		}
		.mod_print{
			margin:0 auto;
			width:200mm;
			height:70mm;
		}
		.mod_print h1{
			font-size:25px;
			text-align:center;
			line-height:3em;
		}
		.mod_print .b table{
			width:100%;
			line-height:2em;
			border-collapse:collapse;
		}
		.mod_print .b table td,
		.mod_print .b table th{
			padding:0.3em 0.5em;
			border:1px solid #333;
		}
		.mod_print .b .jqgfirstrow td,
		.mod_print .b .jqgfirstrow th{
			padding-top:0;
			padding-bottom:0;
			border:0 none;
			border-right:1px solid #ccc;
			border-left:1px solid #ccc;
		}
		
		.mod_print .b table th{
			text-align:right;
		}
		.mod_print .b table .th{
			text-align:center;
			font-weight:bold;
		}
		.mod_print h1{
			font-size:22px;
			text-align:center;
			line-height:3em;
		}
		.mod_print .printtitle{
			display: none;
			font-size: 20px;
			font-weight:bold;
			text-align: center;
		}
		.mod_print .tablelist .title{background:none !important;}
		
		.mod_print .ui-th-column,
		.mod_print .ui-jqgrid .ui-jqgrid-htable th.ui-th-column,
		.mod_print .mod_print .b table td,
		.mod_print .mod_print .b table th,
		.mod_print .ui-jqgrid .ui-jqgrid-htable th div{
			height:auto;
			overflow:inherit;
			word-break: break-all;
			word-wrap:break-word;
			white-space: normal;
		
		}
		.ui-th-column, .ui-jqgrid .ui-jqgrid-htable th.ui-th-column{
			height:auto;
			overflow:inherit;
			word-break: break-all;
			word-wrap:break-word;
			white-space: normal;
		}
		
		.ui-jqgrid tr.jqgrow td{
			height:auto;
			overflow:inherit;
			word-break: break-all;
			word-wrap:break-word;
			white-space: normal;
		}
</style>
	<div class="mod_print" id="printArea">
	  <h1>${population.name}信息</h1>
	  
	</div>
</div>

<div style="display: none" id="printApendReuslt"></div>



<script type="text/javascript">
$(document).ready(function(){
		var titleDivs='';
		$('input:checkbox:checked[name="printOptions"]').each(function(){
			 var index=$('input[name="printOptions"]').index($(this));
			 titleDivs+='<div class="printtitle" id="printReusltName'+index+'">'+$("#printLabelTitle"+index).text()+'</div><div class="b" id="printReuslt'+index+'"></div><br/>';
	    });
	    $("#printArea").html(titleDivs);
		$('input:checkbox:checked[name="printOptions"]').each(function(){
	                  //  alert($(this).val());
	          //printReuslt(index,$(this).val());
	          var index=$('input[name="printOptions"]').index($(this));
			  $("#printReuslt"+index).load($(this).val());
	          $("#printReusltName"+index).css("display","block"); 
	     });
		 $("#printOptionsDialog").dialog("close");
	});
	
	function printReuslt(index,optionsValue){
	
		//$("#printArea").append('<div class="printtitle">住房信息</div><div class="b" id="printReuslt'+index+'"></div><br/>');
		$("#printReuslt"+index).load(optionsValue);
	
		/*
		if(optionsValue==1){
			$("#printReuslt1").load("${path}/baseinfo/floatingPopulationManage/viewCommonPopulation.action?population.id=${population.id}");
			//$("#printApendReuslt").load("${path}/baseinfo/floatingPopulationManage/viewCommonPopulation.action?population.id=${population.id}");
			//$("#printReuslt1").append($("#printApendReuslt").html());
		}else if(optionsValue==2){
			$("#printReuslt2").load("${path}/baseinfo/houseInfoForPopulation/viewHouseInfoForFloatingPopulation.action?population.id=${population.id}");
		}else if(optionsValue==3){
			$("#printReuslt3").load("${path}/baseinfo/floatingPopulationManage/viewInflowingPopulation.action?population.id=${population.id}");
		}else if(optionsValue==4){
			$("#printReuslt4").load("${path}/baseinfo/familyInfo/detailGroupFamily.action?groupFamilyHasPopulation.populationId=${population.id}&groupFamilyHasPopulation.populationType=<s:property value='@com.tianque.service.util.PopulationType@FLOATING_POPULATION'/>");
		}
		$("#printReusltName"+optionsValue).css("display","block");
		*/
}
</script>