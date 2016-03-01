<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<form id="maintainForm" name="maintainForm" method="post" action="${path}/supervisorManage/supervisorInfoManage/emptyOperation.action">
	<pop:token />
		<input id="population" type="hidden" name="population.populationId" value="<s:property value='#parameters["population.id"]'/>" />
		<input id="locationId" type="hidden" name="population.locationId" value="<s:property value='#parameters["location.id"]'/>"/>
		<input id="name" type="hidden" name="population.name" value="<s:property value='#parameters["location.name"]'/>"/>
		<input id="populationType" type="hidden" name="population.populationType" value="${population.populationType}" />
		<input id="locationType" type="hidden" name="population.locationType" value="${population.locationType}"/>
		<input id="supervisorName" type="hidden" name="supervisorName" value="${supervisorName}" />
	</form>
	<div class="ui-corner-all" id="nav">
		<a id="addSupervisor" href="javascript:;"><span>新增</span></a>
		<a id="deleteSupervisor" href="javascript:void(0)"><span>批量移除</span></a>
   		<a id="reloadSupervisor"  href="javascript:void(0)"><span>刷新</span></a>
		<a id="exportSupervisor" href="javascript:void(0)"><span>导出</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width:98%;margin:0 auto;">
		<table id="supervisorInfoList"> </table>
		<div id="supervisorInfoListPager"></div>
	</div>

</div>

<script type="text/javascript">
//doAction("${dailogName}",${population.id});
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
var title=$("#supervisorName").val();
function reloadSupervisor(){
	$("#supervisorInfoList").setGridParam({
		url:'${path}/supervisorManage/supervisorInfoManage/findSupervisorforList.action',
		datatype: "json",
		page:1
	});
	var postData={};
	if($("#locationType").val()==null || $("#locationType").val()==''){
		postData={
			"population.populationId":$("#population").val(),
			"population.populationType":$("#populationType").val()
		};
	}else{
		postData={
			"population.populationId":$("#locationId").val(),
			"population.populationType":$("#locationType").val()
			};
	}
	$("#supervisorInfoList").setPostData(postData);
	$("#supervisorInfoList").trigger("reloadGrid");
}
function nameFormatter(el, options, rowData){
	return "<a href='javascript:viewSupervisor("+rowData.memberId+")'><U><font color='#6633FF'>"+rowData.name+"</font></U></a>";
}
function operateFormatter(el,options,rowData){
	return "<a href='javascript:deleteOperator("+rowData.memberId+")'><span>移除</span></a>";
}
function deleteOperator(){
	var objectId='';
	var objectType='';
	if($("#locationType").val()==null || $("#locationType").val()==''){
		objectId=$("#population").val();
		objectType=$("#populationType").val();
	}else{
		objectId=$("#locationId").val();
		objectType=$("#locationType").val();
	}
	var selectedIds = $("#supervisorInfoList").jqGrid("getGridParam", "selarrrow");
	var str="确定要移除吗?";
	$.confirm({
		title:"确认移除",
		message:str,
		okFunc: function() {
			for(var i=0;i<selectedIds.length;i++){
				selectedId = selectedIds[i];
				var rowdata =  $("#supervisorInfoList").getRowData(selectedId);
				$.ajax({
					url:"${path}/supervisorManage/supervisorInfoManage/deleteSupervisors.action?population.memberId="+rowdata.memberId+'&population.teamId='+rowdata.teamId+
					'&population.populationId='+objectId+'&population.populationType='+objectType,
					success:function(data){
						$("#supervisorInfoList").trigger("reloadGrid");
					    $.messageBox({message:"已经成功移除该"+title+"信息!"});
					}
				});
			}
		}
	});
}
function viewSupervisor(rowid) {
	$("#supervisorMaintanceDialog").createDialog({
		width: 620,
		height: 260,
		title:'查看'+title,
		url:'${path}/supervisorManage/supervisorInfoManage/dispatchOperateSupervisor.action?mode=view&organizationId='+getCurrentOrgId()+'&population.memberId='+rowid,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
$(document).ready(function(){
	$("#supervisorInfoList").jqGridFunction({
		datatype: "local",
		sortname:'memberId',
	   	colModel:[
	        {name:"memberId",index:"memberId",hidden:true,hidedlg:true},
	        {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter,width:50,align:"center"},
	        {name:"name",index:'name',label:'姓名',width:80,formatter:nameFormatter},
	        {name:"gender",index:'gender',label:'性别',width:50,align:'center',formatter:genderFormatter},
	        {name:'idCardNo',index:'idCardNo',label:'身份证号码',width:170},
      		{name:'teamName',label:'管理服务团队名称',sortable:false, width:200},
	   		{name:'mobile',label:'联系手机',sortable:false,hidden:true,width:100},
	        {name:'organization.orgName',label:'所属网格',sortable:false,hidden:true,width:150},
      		{name:'teamId',hidden:true,hidedlg:true}
		],
		height:380,
	  	onSelectAll:function(aRowids,status){ },
	    loadComplete: afterLoad,
	    multiselect:true,
	    onSelectRow:selectRowData,
	    ondblClickRow:viewSupervisor,
	    rowNum:15
	});
	function selectRowData(){
		var selectedCounts = getActualjqGridMultiSelectCount("supervisorInfoList");
		var count = $("#supervisorInfoList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("supervisorInfoList", true);
		}else{
			jqGridMultiSelectState("supervisorInfoList", false);
		}
	}
	reloadSupervisor();

	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#supervisorInfoList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	//页面加载完成之后a,b,c
	function afterLoad(){
		checkExport();
	}

	function checkExport(){
		if($("#supervisorInfoList").getGridParam("records")>0
				|| $("#supervisorInfoList").getGridParam("selrow")!=null){
			$("#exportSupervisor").buttonEnable();
		}
	}
	$("#addSupervisor").click(function(event){
		var objectId='';
		var objectType='';
		if($("#locationType").val()==null || $("#locationType").val()==''){
			objectId=$("#population").val();
			objectType=$("#populationType").val();
		}else{
			objectId=$("#locationId").val();
			objectType=$("#locationType").val();
		}
		$("#supervisorMaintanceDialog").createDialog({
				width: 600,
				height: 430,
				title: '新增'+title,
				url:'${path}/supervisorManage/supervisorInfoManage/dispatchOperateSupervisor.action?mode=add&organizationId='+USER_ORG_ID+'&population.populationId='+objectId
				+'&population.populationType='+objectType,
				buttons: {
			   		"确定" : function(event){
						addSupervisor();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
	});
	function addSupervisor(){
		var objectId='';
		var objectType='';
		if($("#locationType").val()==null || $("#locationType").val()==''){
			objectId=$("#population").val();
			objectType=$("#populationType").val();
		}else{
			objectId=$("#locationId").val();
			objectType=$("#locationType").val();
		}
		var selectedIds = $("#supervisorList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null){
			$.messageBox({level:'warn',message:"没有"+title+"可以新增！"});
			return;
			}
		var temp=0;
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		var data =  $("#supervisorList").getRowData(selectedId);
		temp=i;
		$.ajax({
			async:false,
			url: "${path}/supervisorManage/supervisorInfoManage/addSupervisor.action?population.populationId="+objectId
			+'&population.memberId='+data.memberId+'&population.teamId='+data.teamId+'&population.populationType='+objectType,
			success:function(datas){
				if(datas){
				$.messageBox({message:"成功新增"+title+"信息!"});
					}
				if(temp==(selectedIds.length-1)){
				$("#supervisorMaintanceDialog").dialog("close");
				$("#supervisorInfoList").trigger("reloadGrid");
					}
			}
		});
		}
	}

	$("#deleteSupervisor").click(function(){
		var selectedIds = $("#supervisorInfoList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null){
			$.messageBox({level:'warn',message:"没有"+title+"可以删除！"});
			return;
		}
		deleteOperator();
	});


	$("#reloadSupervisor").click(function(event){
		reloadSupervisor();
	});

	$("#viewSupervisor").click(function(event){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewSupervisor(selectedId);
	});

	$("#exportSupervisor").click(function(event){
		if ($("#supervisorInfoList").getGridParam("records")>0){
			var postData = $("#supervisorInfoList").getPostData();
			$("#supervisorInfoList").setPostData($.extend(postData,{"supervisorName":$("#supervisorName").val()}));
			$("#supervisorMaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出'+title+'信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'supervisorInfoList',
					downloadUrl:'${path}/supervisorManage/supervisorInfoManage/downloadSupervisor.action'
				},
				buttons: {
		   			"导出" : function(event){
						$("#exceldownload").submit();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({level:'warn',message:"没有可导出的数据！"});
			return;
		}
	});

	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
	         	success: function(data){
	         		if($("#locationType").val()==null || $("#locationType").val()==''){
		        		doAction("${dailogName}",data.populationId);
	         		}else{
	         			doLocationAction("${dailogName}",data.locationId,null,data.name);
	         		}
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
			});
		},
		rules: {
			},
		messages: {
			}
	});
});

</script>

