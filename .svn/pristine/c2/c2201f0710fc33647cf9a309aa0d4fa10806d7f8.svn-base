<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div style="width:988px;">
		<div id="gridbox" class="SigmaReport" style="height:310px;width:900px;float:left;">
		</div>
</div>

<script>
var org=getCurrentOrgId();
var grid = null;
function onOrgChanged(orgId,isgrid){
			org=orgId;
			loadAjax();
	}
$(document).ready(function(){
			var context = {};
			var columns = [
			{name:"organization.orgName",caption:"区域",width:120,mode:"string"},
			{name:"total",caption:"总数",width:100,mode:"number",format:"#"},
			{name:"general",caption:"已落实负责人",children:[
				{name:"practicalPlace",caption:"场所数量",width:100,mode:"number",format:"#"},
				{name:"practicalPlace/total",caption:"比率",width:80,mode:"number",format:"#.00%"}
			]},
			{name:"helpInfo",caption:"未落实负责人",children:[
				{name:"noPracticalPlace",caption:"场所数量",width:100,mode:"number",format:"#"},
				{name:"noPracticalPlace/total",caption:"比率",width:80,mode:"number",format:"#.00%"}
			]}

		];
		grid = new SigmaReport("gridbox",context,columns);
		loadAjax();
	});
function loadAjax(){
	$.ajax({
		async: false,
		url: "${path}/baseinfo/statAnalysePlace/findStatAnalysePlace.action?orgId="+org,
		type:"json",
		success:function(responseData){
			grid.bindData(responseData);
		}
	});
}


</script>
