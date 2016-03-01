<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<jsp:include page="/includes/baseInclude.jsp" />

<div id="nav" class="ui-corner-all">
		<select id="type" style="float:right;">
			<option value="1">党委部门报表1</option>
			<option value="2">党委部门报表2</option>
		</select>
		<input type="hidden" id="type" value="<s:property value="@com.tianque.service.util.PopulationType@DEPARTMENTPARTY"/>"/>
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
if($("#currentOrgId").attr("orglevelinternalid") <= <s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT' />){
	$("#type option:last").remove();
}
var grid = null;

function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
}

function onOrgChanged(){
	switch($("#type").val()){
	case '1':
		getDepartmentPartyStatisticList(null);
		break;
	case '2':
		getDepartmentPartyStatisticList(2);
		break;
	}
	enableOrDisableColumn(1);
}
function getDepartmentPartyStatisticList(orgLevelDistance){
	var url = "${path}/baseInfo/departmentPartyManageNew/departmentPartyStatisticList.action?orgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val();
	if(orgLevelDistance != null){
		url = url + "&orgLevelDistance="+orgLevelDistance;
	}
	$.ajax({
		url:url,
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
}

$(document).ready(function(){
	var columns = [		
	   			{name:"org.orgName",caption:"区域",width:120,mode:"string"},	
	   			{name:"organizationName",caption:"组织名称",width:80,align:'center',children:[
	   				{name:"general",caption:"综治委",width:110,mode:"string",children:[
	   					{name:"zongzhiweiCount",caption:"队伍数量",width:55,mode:"string"},
	   					{name:"zongzhiweiMemberCount",caption:"人数",width:55,mode:"string"}
	   				 ]},	
	   				{name:"general",caption:"综治办",width:110,mode:"string",children:[
	   				    {name:"zongzhibanCount",caption:"队伍数量",width:55,mode:"string"},
	   				    {name:"zongzhibanMemberCount",caption:"人数",width:55,mode:"string"}
	   				 ]}
	   			]}
	   		];
	
	if(document.title!='总况'){
		$("#createStatistic").show();
	}

	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForYearToSpecial.action",
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
			url:"${path}/baseInfo/departmentPartyManageNew/createHistoryStatistic.action?orgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type="+$("#type").val(),
			success:function(responseData){

				$.messageBox({message:"历史报表生成成功"});
			}
		});

	});
	
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
	onTypeChanged();
	$(".print").click(function(){
		var url = "${path}/statAnalyse/baseInfoStat/departmentPartyNew/departmentPartyPrint.jsp?parentOrgId="+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type="+$("#type").val()+"&moduleName="+document.title;
		if($("#type").val() == '2'|| $("#type").val() == 2){
			url = url + "&orgLevelDistance=2";
		}
		$("#zongkuangPrint").createDialog({
			width:1160,
			height:480,
			title:document.title,
			url:url,
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
function onTypeChanged() {
	$("#type").change(function(){
		onOrgChanged();
	});
}
</script>
