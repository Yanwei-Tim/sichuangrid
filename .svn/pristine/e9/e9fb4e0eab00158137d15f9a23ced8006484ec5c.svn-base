<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>
<jsp:include page="/common/orgSelectedComponent.jsp"/>     
<style>
.SigmaReport{
  overflow: auto !important;
}
</style>
<div id="nav" class="ui-corner-all">
		<select id="type" style="float:right;">
			<option value="1" name="citySelect">网格化服务管理工作情况通报表1</option>
			<option value="2" name="districtSelect">网格化服务管理工作情况通报表2</option>
			<option value="3" name="currentLevelSelect">网格化服务管理工作情况通报表(本层级)</option>
		</select>
		<select name="year" id="year" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="month" id="month" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="searchList" href="javascript:void(0)"><span>查询</span></a>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')||
		   @com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
		<a id="createBaseSituationStatistic" hidden="true" href="javascript:void(0)"><span>生成报表</span></a>
		</s:if>


</div>
<div class="content" >
	<div id="gridbox" class="SigmaReport"></div>
</div>
<div id="${type}PrintDlg"></div>

<script type="text/javascript">
<%-- if(<%=AnalyseUtilNew.IS_NEWANALYSE_JOB%>){
	var basesituationstatementlist_url = "${path}/baseInfo/baseSituationStatementNew/baseSituationStatementList.action?orgId=";
	var basesituationstatementsort_url = "${path}/baseInfo/baseSituationStatementNew/basesituationStatementSort.action?orgId=";
	var basesituationstatementreportgeneration_url = "${path}/baseInfo/baseSituationStatementNew/baseSituationStatementReportGeneration.action?year=";
}else{ --%>
	var basesituationstatementlist_url = "${path}/baseInfo/baseSituationStatement/baseSituationStatementList.action?orgId=";
	var basesituationstatementsort_url = "${path}/baseInfo/baseSituationStatement/basesituationStatementSort.action?orgId=";
	var basesituationstatementreportgeneration_url = "${path}/baseInfo/baseSituationStatement/baseSituationStatementReportGeneration.action?year=";
//}
if($("#currentOrgId").attr("orglevelinternalid") <= "<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"){
	$("#type option[name='districtSelect']").remove();
}
if($("#currentOrgId").attr("orglevelinternalid") >= "<s:property value='@com.tianque.domain.property.OrganizationLevel@PROVINCE'/>"){
	$("#type option[name='currentLevelSelect']").remove();
}
var grid = null;
var sortName="";
var sortColumn="";
 function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
} 
function onOrgChanged(){
	$.ajax({
		url:basesituationstatementlist_url+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&statisticsType="+$("#type").val(),
		success:function(data){
			if(data == null){
				$.messageBox({
					message: "网格化服务管理工作情况通报表（二）查询统计失败",
					level: "error"
				});
				return;
			}
			grid.bindData(data);
			sortName = "";
			sortColumn="";
		}
	})
}


$(document).ready(function(){
	var columns = null;
	if($("#currentOrgId").attr("orglevelinternalid") >= "<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"){
		if($("#currentOrgId").attr("orglevelinternalid") == "<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"){
			columns = getColumnHasFunc();
		}else{
			columns = getColumnHaveCounty();
		}
	}else{
		columns = getColumnNoCounty();
	}
	 $.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	}); 
	$.gridboxHeight();
	var context = {};
	var sortFun = function(name,caption,sort){
		if(name==null || name==""){
			$.messageBox({message: "排序字段未获得，请重新选择",level:"warn"});
			return;
		}
		sortName = name;
		sortColumn=sort;
		$.ajax({
			url:basesituationstatementsort_url+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&statisticsType="+$("#type").val()+"&sortName="+name+"&sort="+sort,
			success:function(data){
				if(data == null){
					$.messageBox({
						message: "网格化服务管理工作情况通报表（二）排序失败",
						level: "error"
					});
					return;
				}
				grid.bindData(data);
			}
			
		});
	};
	grid = new SigmaReport("gridbox",context,columns,null,null,null,null,sortFun);
	$("#gridbox").css({"overflow": "auto", "height": document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216)});
	
	$("#title_gridbox").children().remove();
	$("#title_gridbox").html("网格化服务管理工作情况通报表（二）<a href='javascript:;' class='print' title='打印'></a>");
	
	onOrgChanged();
	$("#searchList").click(function(){
		onOrgChanged();
	});

	$("#type").change(function(){
		onOrgChanged();
	});
	
	 $("#year").change(function(){
		$("#month").empty();
		getmonth();
	}); 
	$(".print").click(function(){
		$("#zongkuangPrint").createDialog({
			width:980,
			height:480,
			title:document.title,
			url:'${path}/statAnalyse/baseSituation/specialCrowdReportsPrint.jsp?columns='+columns+'&parentOrgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=${type}&moduleName="+document.title+"&sortName="+sortName+"&sort="+sortColumn,
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#zongkuangPrint").dialog("close");
			   }
			}
		});
	});
	
	$("#createBaseSituationStatistic").click(function(){
		var nowDate=new Date(); 
		var nowYear=nowDate.getFullYear();
		var nowMonth=nowDate.getMonth()+1;
			if($("#year").val()==nowYear && $("#month").val()==nowMonth){
				$.messageBox({level:'warn',message:"不能生成当月报表"});
				return;
			}
			$.ajax({
				url:basesituationstatementreportgeneration_url+$("#year").val()+"&month="+$("#month").val(),
				success:function(responseData){
					if(responseData=='true' || responseData==true ){
						$.messageBox({message:"报表生成成功"});
					}else{
						$.messageBox({level:'error',message:responseData});
					}
				}
			});
	});
	function print(){
		$("#zongkuangPrint").printArea();
		$("#zongkuangPrint").dialog("close");
	}

	$('#title_gridbox,#head_gridbox,#body_gridbox').width( $('#tHead_gridbox').width() )

});

function getColumnNoCounty(){
	var columns = [		
					{name:"organization.orgName",caption:"地区",width:80,mode:"string"}, 
// 						{name:"general",caption:"流动人口",width:120,mode:"string",children:[
// 	                           {name:"floatingPersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
// 	                           {name:"currentFloatingPersionCount",caption:"本月变量",width:50,mode:"string"}
// 	                     ]},
						{name:"general",caption:"刑释人员",width:120,mode:"string",children:[
						     {name:"positivePersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
						     {name:"currentPositivePersionCount",caption:"本月变量",width:50,mode:"string"}
						]},
						{name:"general",caption:"矫正人员",width:120,mode:"string",children:[
							{name:"rectificativePersonCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentRectificativePersonCount",caption:"本月变量",width:50,mode:"string"}                                                                              
						]},
						{name:"general",caption:"严重精神障碍患者",width:120,mode:"string",children:[
							{name:"mentalPatientPersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentMentalPatientPersionCount",caption:"本月变量",width:50,mode:"string"}                                                                              
						]},
						{name:"general",caption:"吸毒人员",width:120,mode:"string",children:[
							{name:"druggyPersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentDruggyPersionCount",caption:"本月变量",width:50,mode:"string"}                                                                       
						]},
						{name:"general",caption:"重点青少年",width:120,mode:"string",children:[
							{name:"idleYouthCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentIdleYouthCount",caption:"本月变量",width:50,mode:"string"}                                                                    
						]},
						{name:"specialCrowdCount",caption:"特殊人群本月服务走访量",width:70,mode:"string"},
// 						15.6.9新增加的数据列，由覆盖率报表迁移过来
						{name:"agencyOfOpinionCount",caption:"本月社情民意收集数量",width:40,mode:"string",format:"#"},
						{name:"issueDealCount",caption:"本月事件处理总量",width:40,mode:"string",format:"#"}
// 						{name:"general",caption:"群防群治组织人数",width:120,mode:"string",children:[
// 						    {name:"preventionCount",caption:"截止到上月总数量",width:70,mode:"string"},
// 						    {name:"currentPreventionCount",caption:"本月净变量",width:50,mode:"string"}                                                                    
// 						]}
		   		];
	return columns;
}
function getColumnHaveCounty(){
	var columns = [		
					{name:"organization.orgName",caption:"地区",width:80,mode:"string"}, 
					{name:"countyCount",caption:"区县数量",width:60,mode:"string"}, 
					{name:"functionDepartmentCount",caption:"职能部门加入情况",width:60,mode:"string"}, 
// 						{name:"general",caption:"流动人口",width:120,mode:"string",children:[
// 	                           {name:"floatingPersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
// 	                           {name:"currentFloatingPersionCount",caption:"本月变量",width:50,mode:"string"}
// 	                     ]},
						{name:"general",caption:"刑释人员",width:120,mode:"string",children:[
						     {name:"positivePersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
						     {name:"currentPositivePersionCount",caption:"本月变量",width:50,mode:"string"}
						]},
						{name:"general",caption:"矫正人员",width:120,mode:"string",children:[
							{name:"rectificativePersonCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentRectificativePersonCount",caption:"本月变量",width:50,mode:"string"}                                                                              
						]},
						{name:"general",caption:"严重精神障碍患者",width:120,mode:"string",children:[
							{name:"mentalPatientPersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentMentalPatientPersionCount",caption:"本月变量",width:50,mode:"string"}                                                                              
						]},
						{name:"general",caption:"吸毒人员",width:120,mode:"string",children:[
							{name:"druggyPersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentDruggyPersionCount",caption:"本月变量",width:50,mode:"string"}                                                                       
						]},
						{name:"general",caption:"重点青少年",width:120,mode:"string",children:[
							{name:"idleYouthCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentIdleYouthCount",caption:"本月变量",width:50,mode:"string"}                                                                    
						]},
						{name:"specialCrowdCount",caption:"特殊人群本月服务走访量",width:70,mode:"string"},
// 						15.6.9新增加的数据列，由覆盖率报表迁移过来
						{name:"agencyOfOpinionCount",caption:"本月社情民意收集数量",width:40,mode:"string",format:"#"},
						{name:"issueDealCount",caption:"本月事件处理总量",width:40,mode:"string",format:"#"}
// 						{name:"general",caption:"群防群治组织人数",width:120,mode:"string",children:[
// 						    {name:"preventionCount",caption:"截止到上月总数量",width:70,mode:"string"},
// 						    {name:"currentPreventionCount",caption:"本月净变量",width:50,mode:"string"}                                                                    
// 						]}
		   		];
	return columns;
}

function getColumnHasFunc(){
	var columns = [		
					{name:"organization.orgName",caption:"地区",width:80,mode:"string"}, 
					{name:"functionDepartmentCount",caption:"职能部门加入情况",width:60,mode:"string"}, 
// 						{name:"general",caption:"流动人口",width:120,mode:"string",children:[
// 	                           {name:"floatingPersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
// 	                           {name:"currentFloatingPersionCount",caption:"本月变量",width:50,mode:"string"}
// 	                     ]},
						{name:"general",caption:"刑释人员",width:120,mode:"string",children:[
						     {name:"positivePersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
						     {name:"currentPositivePersionCount",caption:"本月变量",width:50,mode:"string"}
						]},
						{name:"general",caption:"矫正人员",width:120,mode:"string",children:[
							{name:"rectificativePersonCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentRectificativePersonCount",caption:"本月变量",width:50,mode:"string"}                                                                              
						]},
						{name:"general",caption:"严重精神障碍患者",width:120,mode:"string",children:[
							{name:"mentalPatientPersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentMentalPatientPersionCount",caption:"本月变量",width:50,mode:"string"}                                                                              
						]},
						{name:"general",caption:"吸毒人员",width:120,mode:"string",children:[
							{name:"druggyPersionCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentDruggyPersionCount",caption:"本月变量",width:50,mode:"string"}                                                                       
						]},
						{name:"general",caption:"重点青少年",width:120,mode:"string",children:[
							{name:"idleYouthCount",caption:"截止到上月总数量",width:70,mode:"string"},
							{name:"currentIdleYouthCount",caption:"本月变量",width:50,mode:"string"}                                                                    
						]},
						{name:"specialCrowdCount",caption:"特殊人群本月服务走访量",width:70,mode:"string"},
// 						15.6.9新增加的数据列，由覆盖率报表迁移过来
						{name:"agencyOfOpinionCount",caption:"本月社情民意收集数量",width:40,mode:"string",format:"#"},
						{name:"issueDealCount",caption:"本月事件处理总量",width:40,mode:"string",format:"#"}
// 						{name:"general",caption:"群防群治组织人数",width:120,mode:"string",children:[
// 						    {name:"preventionCount",caption:"截止到上月总数量",width:70,mode:"string"},
// 						    {name:"currentPreventionCount",caption:"本月净变量",width:50,mode:"string"}                                                                    
// 						]}
		   		];
	return columns;
}
</script>