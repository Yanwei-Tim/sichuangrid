<%@page import="com.tianque.service.util.PopulationType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String populationType=request.getParameter("type");
request.setAttribute("type",
		populationType);
request.setAttribute("populationTypeName",
		PopulationType.getCnameByPopulationType(populationType)==null?"总况":PopulationType.getCnameByPopulationType(populationType));
%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="maxSelected${type}" style="padding-left: 10px">
	<div id="maxdate${type}" style="float: right; display: none;">
		<select id="maxYear${type}" onchange="">
		</select> <label>年</label>
		<select id="maxMonth${type}" onchange="">
		</select> <label>月</label>
	</div>

	<div class="maxColumn">
		<h3 class="maxPanelTitle">${populationTypeName}区域分布图</h3>
		<div id="maxColumn${type}" class="SigmaReport"></div>
	</div>
	
	<div class="maxTrendLine">
		<h3 class="maxPanelTitle">${populationTypeName}趋势图</h3>
		<div id="maxTrendLine${type}" class="SigmaReport"></div>
	</div>
	<div class="maxSimpleList">
		<div id="maxSimpleList${type}" class="SigmaReport"></div>
	</div>

<%-- 	<div id="pies${type}" class="SigmaReport listBox" style="width: 300px;"></div>
 --%>

 </div>
<script type="text/javascript">
$(function() {
	var orgId=<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>;
	function getmonth(){
		$.ajax({
			async: false,
			url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#maxYear${type}").val(),
			success:function(responseData){
				for(var i = 0;i<responseData.length;i++){
					$("#maxMonth${type}").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
				}
				columnChart();
				trendChart();
				listChart();
			}
		});
	}

	function columnChart(){
		var chartCount=$("#maxColumn${type}").columnChart({
			url: "${path}/baseInfo/statisticManage/getStatisticColumn.action?type=${type}&orgId="+orgId,
			moduleName:"${populationTypeName}",
			textx:-150,
			quantity:'个数'
		},{title:false,yAxis:{title:{text:''}}});
	}

	function trendChart(){
		$("#maxTrendLine${type}").lineChart({
			url: "${path}/baseInfoStat/tendencyChart/showTendencyChartForPositiveinfo.action?type=${type}&organization.id="+orgId,
			moduleName:"${populationTypeName}"
		});
	}

	function listChart(){
		$.ajax({
			url:"${path}/baseInfo/statisticManage/getBaseInfoStatisticList.action?orgId="+orgId+"&year="+$("#maxYear${type}").val()+"&month="+$("#maxMonth${type}").val()+"&type=${type}",
			success:function(data){
				var context = {};
				var
				columns = [
						{name:"orgName",caption:"区域",width:120,mode:"string"},
						{name:"general",caption:"总况",children:[
							{name:"baseinfoStatisticDetailVo[index].typeName",caption:"类型",width:170,mode:"string"},
							{name:"baseinfoStatisticDetailVo[index].sum",caption:"数量",width:80,mode:"number",format:"#"}
						]}
					];
				var grid = new SigmaReport("maxSimpleList${type}",context,columns,'SigmaReport','','${populationTypeName}',false);
				grid.bindData(data);
			}
		})
	}

/* 	function pieChart(){
		$("#pies${type}").pieChart({
			url:"${path}/baseInfo/statisticManage/getStatisticPie.action?orgId="+orgId+"&year="+$("#maxYears${type}").val()+"&month="+$("#maxMonth${type}").val()+"&type=${type}",
			moduleName:"${populationTypeName}",
			onClick:function(event){
				setOptionsWhenShowInfo(event.point.name,getCurrentOrgId());
				showInfo(url, title, width, height,$("#maxYears${type}").val(),$("#maxMonth${type}").val());
			}
		});
	} */
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#maxYear${type}").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	});

	$("#maxYear${type}").change(function(){
		$("#maxMonth${type}").empty();
		getmonth();
	});


});


</script>