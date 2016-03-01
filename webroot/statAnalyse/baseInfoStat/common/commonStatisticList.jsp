<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
		url:'${path}/baseInfo/statisticManage/getBaseInfoStatisticList.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=${type}",
		success:function(data){

			grid.bindData(data);
		}
	})
	enableOrDisableColumn(2);
}

$(document).ready(function(){

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
	//setTimeout('onOrgChanged()',350);
	onOrgChanged();
	$("#searchList").click(function(){
		onOrgChanged();
	});

	$("#createStatistic").click(function(){
		$.ajax({
			url:'/baseInfo/statisticManage/createHistoryStatistic.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=${type}",

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
		$("#${type}PrintDlg").createDialog({
			width:680,
			height:480,
			title:document.title,
			url:'${path}/statAnalyse/baseInfoStat/common/commonStatisticPrint.jsp?parentOrgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val()+"&type=${type}&moduleName="+document.title,
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#${type}PrintDlg").dialog("close");
			   }
			}
		});
	});
	function print(){
		$("#${type}Print").printArea();
		$("#${type}PrintDlg").dialog("close");
	}
})

</script>