<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<input type="hidden" id="estateInformation-id" value="${estateInformation.id}" />
<div class="content" >
    <table id="inhabitantList"></table>
    <div id="inhabitantListPager"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#inhabitantList").jqGridFunction({
		height: 100,
		width:500,
		url:"${path}/baseinfo/estateInformationManage/findInhabitants.action",
		postData:{
			"estateInformation.id":$("#estateInformation-id").val()
		},
		datatype: "json",
		colModel:[
	    	{name:"id", index:"c.id", hidden:true },
	    	{name:"name",index:"a.name", label:"姓名",width:80,align:"center"},
	    	{name:"idCardNo", index:"a.idCardNo",label:"身份证号码",width:150,align:"center"}
	  	],
	  	sortname:"c.id",
	  	showColModelButton:false,
	  	<pop:JugePermissionTag ename="viewEstateInformation">
    	    ondblClickRow: doubleClickTable
	    </pop:JugePermissionTag>
	});

	function doubleClickTable(){
		var selectedId = $("#inhabitantList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		$("#estateInformationDialog").createDialog({
			width:900,
			height:550,
			title:"查看房主信息",
			url:"${path}/baseinfo/inhabitantManage/dispatchOperate.action?mode=view&mode2=view&inhabitantConnect.id="+selectedId,
			buttons: {
			   "打印" : function(){
				inhabitantPrint(selectedId);
		  	   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
		function inhabitantPrint(selectedId){
			$("#estateInformationDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"打印房主信息",
				url:"${path}/baseinfo/inhabitantManage/residentFamilyPrint.action?mode=houseOwnerInfoPrint&inhabitantConnect.id="+selectedId,
				buttons: {
				   "打印" : function(){
					$("#houseOwnerInfoPrint").printArea();
		        	$(this).dialog("close");
			  	 },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}
	}
})
</script>