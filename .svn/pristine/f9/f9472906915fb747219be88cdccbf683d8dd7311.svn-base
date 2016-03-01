<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<div style="width:100%; height: 455px;">
	<input type="hidden" id="locationId" value="${location.id}" />
	<table id="praiseScenicSpotList"></table>
	<div id="praiseScenicSpotListPager"></div>
</div>
<script type="text/javascript">
	<pop:formatterProperty name="feedbackType" domainName="@com.tianque.domain.property.PropertyTypes@PRAISESCENICSPOT_TYPES" />
	var gridOption={
			colModel:[
		      {name:"id", index:"id",hidden:true,sortable:false, frozen :true},
		      {name:"scenicSpotId",index:"scenicSpotId",hidden:true,sortable:false},
		      {name:'feedbackType',index:'feedbackType',label:"反馈类型",sortable:true,width:80,align:'center',formatter:feedbackTypeFormatter},
		      {name:"feedbackPerson",index:"feedbackPerson",label:"反馈人",width:100,sortable:false},
		      {name:'feedbackTime', sortable:true, label:'反馈时间', width:100, align:'center'},
		      {name:"feedbackPersonTelephone",index:"feedbackPersonTelephone",label:"反馈人电话",width:100},
		      {name:"introduction",index:"introduction",label:"情况描述",width:405}
			]
		};
	$(function(){
		$("#praiseScenicSpotList").jqGridFunction({
			mtype:'post',
			datatype: "local",
			colModel: gridOption.colModel,
			showColModelButton:false,
	    	loadComplete: function(){}
		});
		jQuery("#praiseScenicSpotList").jqGrid('setFrozenColumns');
		function onLoad(){
			var initParam = {
				"praiseScenicSpot.scenicSpotId": $("#locationId").val()
			}
			$("#praiseScenicSpotList").setGridParam({
				url:"${path}/baseinfo/scenicSpotManage/findPraiseScenicSpotList.action",
				datatype: "json",
				page:1
			});
			$("#praiseScenicSpotList").setPostData(initParam);
			$("#praiseScenicSpotList").trigger("reloadGrid");
		}
		onLoad();
	});
</script>