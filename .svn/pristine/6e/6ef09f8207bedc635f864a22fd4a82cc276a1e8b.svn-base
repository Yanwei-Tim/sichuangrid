<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<jsp:include page="/includes/baseInclude.jsp" />

<%
	String type = request.getParameter("type");
	request.setAttribute("type",type);

%>


<div id="nav" class="ui-corner-all">
		<select name="year" id="year" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="month" id="month" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="searchList" href="javascript:void(0)"><span>查询</span></a>

		<!--
		<s:if test='!"all_attention_population".equals(type)'>
		-->
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')||
		   @com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
			<a hidden="true" id="createStatistic" href="javascript:void(0)"><span>生成报表</span></a>
		</s:if>
		<!--
		</s:if>
		-->

</div>
<div class="content" >
	<div id="gridbox" class="SigmaReport"></div>
</div>
<div id="${type}PrintDlg"></div>

<script type="text/javascript">
var grid = null;

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
		url:'${path}/baseInfo/primaryOrganizationStat/primaryOrganizationStatisticList.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val(),
		success:function(data){
			if(data == null || (data[0]!= null &&data[0].org == null)){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			grid.bindData(data);
		}
	})
	enableOrDisableColumn(2);
}

$(document).ready(function(){

	var columns = [		
	   			{name:"org.orgName",caption:"区域",width:120,mode:"string"},	
	   			{name:"organizationName",caption:"组织名称",width:80,align:'center',children:[
	   				{name:"general",caption:"党委部门",width:110,mode:"string",children:[
	   					{name:"partyCommitteeCount",caption:"队伍数量",width:55,mode:"string"},
	   					{name:"partyCommitteeMemberCount",caption:"人数",width:55,mode:"string"}
	   				 ]},	
	   				{name:"general",caption:"基层党委",width:110,mode:"string",children:[
	   				    {name:"partyTeamCount",caption:"队伍数量",width:55,mode:"string"},
	   				    {name:"partyTeamMemberCount",caption:"人数",width:55,mode:"string"}
	   				 ]},	
	   				{name:"general",caption:"社会组织",width:110,mode:"string",children:[
	   				    {name:"socialOrganizationCount",caption:"队伍数量",width:110,mode:"string"}
	   				 ]},	
	   				{name:"general",caption:"非公有制经济组织",width:110,mode:"string",children:[
	   				    {name:"newEconomicOrganizationsCount",caption:"队伍数量",width:110,mode:"string"}
	   				 ]},	
	   				{name:"general",caption:"基层自治组织",width:110,mode:"string",children:[
	   				    {name:"autonomyTeamCount",caption:"队伍数量",width:55,mode:"string"},
	   				    {name:"autonmoyTeamMemberCount",caption:"人数",width:55,mode:"string"}
	   				 ]},	
	   				{name:"general",caption:"群防群治组织",width:110,mode:"string",children:[
	   				    {name:"massesTeamCount",caption:"队伍数量",width:55,mode:"string"},
	   				    {name:"massesTeamMemberCount",caption:"人数",width:55,mode:"string"}
	   				 ]},	
	   				{name:"general",caption:"社会志愿者队伍",width:110,mode:"string",children:[
	   				    {name:"postulantTeamCount",caption:"队伍数量",width:55,mode:"string"},
	   				    {name:"postulantTeamMemberCount",caption:"人数",width:55,mode:"string"}
	   				 ]},	
	   				{name:"general",caption:"政府部门",width:110,mode:"string",children:[
	   				    {name:"governmentDepartmentsCount",caption:"队伍数量",width:55,mode:"string"},
	   				    {name:"governmentDepartmentsMemberCount",caption:"人数",width:55,mode:"string"}
	   				]},
	   				{name:"general",caption:"群团组织",width:110,mode:"string",children:[
	   				    {name:"massOrganizationCount",caption:"队伍数量",width:55,mode:"string"},
	   				    {name:"massOrganizationMemberCount",caption:"人数",width:55,mode:"string"}
	   				]}
	   			]}
	   		];
	
	if(document.title!='总况'){
		$("#createStatistic").show();
	}

	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			//setTimeout('getmonth()',350);
			getmonth();
		}
	});
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox",context,columns);
	$("#gridbox").css({"overflow": "auto", "height": document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216)});
// 	setTimeout('onOrgChanged()',350);
	onOrgChanged();
	$("#searchList").click(function(){
		onOrgChanged();
	});

	$("#createStatistic").click(function(){
		$.ajax({
			url:'${path}/baseInfo/primaryOrganizationStat/createHistoryStatistic.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=primaryorganizations",

			success:function(responseData){

				$.messageBox({message:"历史报表生成成功"});
			}
		});

	});
	
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	$(".print").click(function(){
		$("#zongkuangPrint").createDialog({
			width:1160,
			height:480,
			title:document.title,
			url:'${path}/statAnalyse/baseInfoStat/organizationStat/primaryOrganizationPrint.jsp?parentOrgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=${type}&moduleName="+document.title,
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
	function print(){
		$("#gridbox12").printArea();
		$("#zongkuangPrint").dialog("close");
	}
});
</script>
