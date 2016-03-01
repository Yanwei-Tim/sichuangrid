<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="floatingPopulation" class="container container_24">
	<form action="#" method="post" id="maintainForm">
	<pop:token />
		<div id="tabDialog">
			<ul>
				<li><a href="${path }/baseinfo/commonPopulation/tabDialog/commonActualPopulationDlg.jsp">基础信息</a></li>
<%--  				<li><a href="${path }/baseinfo/houseInfoForPopulation/dispathHouseInfoForPopulation.action?mode=add&dailogName=floatingPopulationDialog&contextId=db3d2aa2-91b4-4974-bc55-9783582f25b0">住房信息</a></li> --%>
			</ul>
		</div>
	</form>
</div>
<script>
$(function() {
	$( "#tabDialog" ).tabs({cache:true,disabled: [1, 2,3]});
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
		},
		ignore:":hidden",
		rules:{
		},
		messages:{
		}
	});
	$("#addTab").click(function(event){
		if(clickDisabled("add")){
			return;
		}
		$("#floatingPopulationDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:"新增流动人口信息",
			url:'/baseinfo/floatingPopulation/floatingPopulationTabDlg.jsp',
			buttons:[
				{
					text : "上一步", 
					disabled: true ,
					click : function(event){
						if($("#maintainForm").valid()){
			   				if(($( "#tabDialog" ).tabs( "option","selected")-1)==0){
			   					var buttons = $(this).dialog( "option", "buttons" );
				   				buttons[0].disabled=true;
				   				buttons[1].disabled=false;
				   				$(this).dialog("option","buttons",buttons);
			   				}
			   				$( "#tabDialog" ).tabs( "select" , $( "#tabDialog" ).tabs( "option","selected")-1);
						}
				   	}
		   		},
				{
					text : "下一步" ,
					click : function(event){
						if($("#maintainForm").valid()){
			   				if(($( "#tabDialog" ).tabs( "option","selected")+2)==$( "#tabDialog" ).tabs( "length" )){
			   					var buttons = $(this).dialog( "option", "buttons" );
			   					buttons[0].disabled=false;
				   				buttons[1].disabled=true;
				   				$(this).dialog("option","buttons",buttons);
			   				}
							$( "#tabDialog" ).tabs( "enable" , $( "#tabDialog" ).tabs( "option","selected")+1);
			   				$( "#tabDialog" ).tabs( "select" , $( "#tabDialog" ).tabs( "option","selected")+1);
						}
			   		}
				},
				{
					text : "保存并继续",
					disabled: true ,
					click : function(event){
			      		$("#mForm").submit();
			   		}
				},
			   	{
					text : "保存并关闭",
					disabled: true ,
					click : function(){
				        $(this).dialog("close");
				   	}
			   	}
			]
		});
	  });
});
</script>