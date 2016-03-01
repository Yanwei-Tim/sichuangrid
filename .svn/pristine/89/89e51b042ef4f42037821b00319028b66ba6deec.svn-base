<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<jsp:include page="/common/orgSelectedComponent.jsp"/>     
<div id="nav" class="ui-corner-all">
		<select id="type" style="float:right;">
			<option value="1">基本统计报表1</option>
			<option value="2">基本统计报表2</option>
		</select>
		<select name="year" id="year" onchange="" style="float:left;">
			<option selected="selected" value="2015">2015</option>
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="month" id="month" onchange="">
        <option selected="selected" value="5">5</option>
        <option  value="4">4</option>
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
if($("#currentOrgId").attr("orglevelinternalid") <= "<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>"){
	$("#type option:last").remove();
}
var grid = null;
var sortName="";
var sortColumn="";
/* function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
} */
function onOrgChanged(){
	$.ajax({
		url:"${path}/baseInfo/baseSituationStatementNew/baseSituationStatementList.action?orgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&statisticsType="+$("#type").val(),
		success:function(data){
			if(data == null){
				$.messageBox({
					message: "基本情况报表查询统计失败",
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

	var columns = [		
				{name:"organization.orgName",caption:"区域",width:100,mode:"string"}, 
				{name:"togetherOrganizeCount",caption:"综治组织",width:100,mode:"string"},
				{name:"preventionCount",caption:"群防群治组织人数",width:100,mode:"string"},
				{name:"floatingPersionCount",caption:"流动人口",width:60,mode:"string"},
				{name:"rentalHouseCount",caption:"出租房",width:50,mode:"string"},
				{name:"orgName",caption:"特殊人群",width:100,mode:"string",children:[
					{name:"positivePersionCount",caption:"刑释人员",width:100,mode:"string"},
					{name:"rectificativePersonCount",caption:"矫正人员",width:100,mode:"string"},
					{name:"mentalPatientPersionCount",caption:"严重精神障碍患者",width:100,mode:"string"},
					{name:"druggyPersionCount",caption:"吸毒人员",width:100,mode:"string"},
					{name:"idleYouthCount",caption:"重点青少年",width:100,mode:"string"}
				]},
				{name:"newSecurityCount",caption:"治安重点单位场所",width:100,mode:"string"},
				{name:"doneIssueCount",caption:"事件处理统计",width:100,mode:"string"} 
	   		];

	/* $.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	}); */
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
			url:"${path}/baseInfo/baseSituationStatementNew/basesituationStatementSort.action?orgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&statisticsType="+$("#type").val()+"&sortName="+name+"&sort="+sort,
			success:function(data){
				if(data == null){
					$.messageBox({
						message: "基本情况报表排序失败",
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
	onOrgChanged();
	$("#searchList").click(function(){
		onOrgChanged();
	});

	$("#type").change(function(){
		onOrgChanged();
	});
	
	/* $("#year").change(function(){
		$("#month").empty();
		getmonth();
	}); */
	$(".print").click(function(){
		$("#zongkuangPrint").createDialog({
			width:1160,
			height:480,
			title:document.title,
			url:'${path}/statAnalyse/baseSituationNew/baseSituationStatementStatisticsPrint.jsp?parentOrgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=${type}&moduleName="+document.title+"&sortName="+sortName+"&sort="+sortColumn,
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
				url:"${path}/baseInfo/baseSituationStatementNew/baseSituationStatementReportGeneration.action?year="+$("#year").val()+"&month="+$("#month").val(),
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
});
</script>