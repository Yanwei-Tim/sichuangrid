<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String partyModule = request.getParameter("partyModule");
	request.setAttribute("moduleName", partyModule.substring(0,1).toUpperCase()
			+ partyModule.substring(1)); 
	String partyOrganizationId = request.getParameter("partyOrganizationId");
	request.setAttribute("partyOrganizationId", partyOrganizationId); 
	
	request.setAttribute("partyOrganizationType",request.getParameter("partyOrganizationType"));
	request.setAttribute("partyOrganizationName",request.getParameter("partyOrganizationName"));
%>

<div class="content" >
	<div class="ui-corner-all" id="nav" style="position:relative;">
		<c:if test="${partyOrganizationId!=null}">
		<pop:JugePermissionTag ename="add${moduleName }Activity">
			<a id="addActivityRecord" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="update${moduleName }Activity">
			<a id="updateActivityRecord" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		</c:if>
		<pop:JugePermissionTag ename="view${moduleName }Activity">
			<a id="viewActivityRecord" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<c:if test="${partyOrganizationId!=null}">
		<pop:JugePermissionTag ename="delete${moduleName }Activity">
			<a id="deleteActivityRecord" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		</c:if>
		<pop:JugePermissionTag ename="search${moduleName }Activity">
			<a id="searchActivityRecord" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reloadActivityRecord"  href="javascript:void(0)"><span>刷新</span></a>
		<c:if test="${partyOrganizationId!=null}">
		<pop:JugePermissionTag ename="down${moduleName }Activity">
			<%-- <a id="exportActivityRecord" href="javascript:void(0)"><span>导出</span></a> --%>
		</pop:JugePermissionTag>
		</c:if>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="activityRecordList"> </table>
		<div id="activityRecordListPager"></div>
	</div>
	<div id="activityRecordDialog"></div>
</div>
<script type="text/javascript">
	var dialogWidth=800;
	var dialogHeight=600;
	var partyOrganizationType='${partyOrganizationType }';
	var partyOrganizationId='${partyOrganizationId}';
	var partyOrganizationName='${partyOrganizationName }';
	var title="";
	var tableHeightParameter;
	//根据初始化一些页面参数
	function initActivityRecordParameter(){
		if(partyOrganizationType=="TOWN_PARTY_ORG"){
			title='街道社区党组织活动'
		}else if(partyOrganizationType=="NEW_PARTY_ORG"){
			title='两新组织活动'
		}else if(partyOrganizationType=="PARTYORGINFOS"){
// 			title='区域联建情况'
			title='工作动态情况'
		}
		if(partyOrganizationType=="PARTYORGINFOS"){
			tableHeightParameter=320;
		}else{
			if(partyOrganizationId==null || partyOrganizationId=="" || typeof(partyOrganizationId)=="undefined"){
				tableHeightParameter=200;
			}else{
				tableHeightParameter=220
			}
		}
		
	}
	
	$(document).ready(function() {
		initActivityRecordParameter();
		initActivityButtonsEnable();
		function initActivityButtonsEnable(){
			if($("#currentOrgId").val()!=USER_ORG_ID){
				$("#addActivityRecord,#updateActivityRecord,#deleteActivityRecord").hide();
			}
		}
		
		 //生成组织活动列表
		$("#activityRecordList").jqGridFunction({
			mtype:'post',
			datatype: "local",
			colModel:[
		    	{name:"id", index:"id", hidden:true,frozen : true},
		    	{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		    	{name:"organizationName", index:"organizationName",label:"所属组织",width:200, frozen : true,sortable:true},
	  			{name:"activityTheme", index:"activityTheme",label:"活动主题",width:200, frozen : true,sortable:true},
	  			{name:"activityPlace", index:"activityPlace", label:"活动地点", width:200, align:"center",sortable:true},
	  			{name:"activityDate", index:'activityDate', label:'活动时间', width:100, frozen : true,formatter:'date',formatoptions:{newformat: 'Y-m-d'},sortable:true},
	  		  	{name:"organizer", index:'organizer', label:'组织者', width:80, frozen : true,sortable:true},
	  		  	{name:"participant", index:'participant', label:'参与者', width:80, frozen : true,sortable:true},
	  			{name:"activityRecordFiles", formatter:formatterActivityRecordsAttachFiles, label:'附件', width:150, frozen : true},
	  		  	{name:"organization.orgName", index:"orgInternalCode", label:"所属区域(网格)", width:170,sortable:true},
	  		  	{name:"details", index:'details', label:'活动内容', width:80 ,hidden:true}
		  	],
		  	multiselect:true,
		  	onSelectAll:toggleActivityButtonState,
	    	loadComplete: function(){afterLoad();showActivityRecordAttachFile();},
			<pop:JugePermissionTag ename="view${moduleName }Activity">
	        	ondblClickRow: viewActivityRecord,
			</pop:JugePermissionTag>
			onSelectRow:toggleActivityButtonState,
			height:$(".ui-layout-center").height()-tableHeightParameter
		});
		jQuery("#activityRecordList").jqGrid('setFrozenColumns');
		 
		 $("#addActivityRecord").click(function(){
			 if(partyOrganizationId==null||partyOrganizationId==""||typeof(partyOrganizationId)=="undefined"){
				 $.messageBox({level:'warn',message:"请先新增组织！"});
				 return;
			 }
			 $("#activityRecordDialog").createDialog({
					width:650,
					height:450,
					title:'新增'+title+'记录',
					url:"${path}/partyBuilding/activityRecordManage/dispatchOperate.action?mode=add&orgId="+getCurrentOrgId()+"&partyOrganizationType="+partyOrganizationType+"&partyOrganizationName="+encodeURIComponent(partyOrganizationName)+"&partyOrganizationId="+partyOrganizationId,
					buttons: {
				   		"保存" : function(event){
				   			$("#addActivityRecordForm").submit();
			   			},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
					 
		 });
		 
		 $("#updateActivityRecord").click(function(){
			 var selectedIds = $("#activityRecordList").jqGrid("getGridParam", "selarrrow");
				if(selectedIds==null || selectedIds.length>1){
					$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
					return;
				}
				var selectedId = getActivitySelectedIdLast();
				if(selectedId==null){
					$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
					 return;
				}
				var rowData=  $("#activityRecordList").getRowData(selectedIds);
			 $("#activityRecordDialog").createDialog({
					width:650,
					height:450,
					title:'修改'+title+'记录',
					url:'${path}/partyBuilding/activityRecordManage/dispatchOperateByEncrypt.action?mode=edit&orgId='+getCurrentOrgId()+'&partyOrganizationType='+partyOrganizationType+'&id='+rowData.encryptId,
					buttons: {
				   		"保存" : function(event){
				   			$("#addActivityRecordForm").submit();
			   			},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
		 });
		 
		$("#viewActivityRecord").click(function(){
			 var selectedIds = $("#activityRecordList").jqGrid("getGridParam", "selarrrow");
				if(selectedIds==null || selectedIds.length>1){
					$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
					return;
				}
				var selectedId = getActivitySelectedIdLast();
				if(selectedId==null){
					$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
					 return;
				}
				
				viewActivityRecord(selectedId);
		 });
		
		$("#deleteActivityRecord").click(function(){
			var rowIds = $("#activityRecordList").jqGrid("getGridParam", "selarrrow");
			if(rowIds.length ==0){
				$.messageBox({level:"warn",message:'没有选中任何记录，可供删除！'});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("activityRecord",rowIds,"encryptId");
			var str="确定要删除吗?一经删除将无法恢复";
			$.confirm({
				title:"确认删除",
				message:str,
				okFunc: function() {
					$.ajax({
						url:"${path}/partyBuilding/activityRecordManage/deleteActivityRecordByEncrypt.action",
						type:'post',
						data:{
							'deleteIds':encryptIds
						},	
						success:function(data){
							$.messageBox({message:"已经成功删除该信息!"});
								$("#activityRecordList").trigger("reloadGrid");
						}
					});
				}
			});
			 
		 });
		 
		$("#searchActivityRecord").click(function(){
			 $("#activityRecordDialog").createDialog({
					width:650,
					height:350,
					title:'查询'+title+'记录-请输入查询条件',
					url:'${path}/partyBuilding/activityRecordManage/dispatchOperate.action?mode=search&orgId='+getCurrentOrgId()+'&partyOrganizationType='+partyOrganizationType+"&partyOrganizationId="+partyOrganizationId,
					buttons: {
				   		"查询" : function(event){
				   				searchActivityRecord();
				   				$(this).dialog("close");
			   			},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
		 });
		
		
		$("#exportActivityRecord").click(function(){
				 
				 
		});
		 
		$("#reloadActivityRecord").click(function() {
			if(getCurrentOrgId()!="" && getCurrentOrgId() != null) {
				onOrgChanged(getCurrentOrgId());
			}
		}).click();
		
	});
	
	//改变组织机构树时加载列表
	function onOrgChanged(orgId){
		$("#activityRecordList").setGridParam({
			url:"${path}/partyBuilding/activityRecordManage/activityRecordList.action",
			datatype: "json",
			page:1
		});
		$("#activityRecordList").setPostData({
			"orgId":getCurrentOrgId(),
			"partyOrganizationType":partyOrganizationType,
			"partyOrganizationId":partyOrganizationId
	   	});
		$("#activityRecordList").trigger("reloadGrid");
		
	}
	//附件显示	
		function formatterActivityRecordsAttachFiles(el,options,rowData){
			if(rowData.activityRecordFiles.length>0){
				//return "<div style='clear:both' align='center'><a href='javascript:;' id='orgActivity_"+rowData.id+"' style='color:#666666' class='popUpMore' orgActivityId='"+rowData.id+"' >附件>></a></div>";
				$("#activityRecordList").data(String(rowData.id), rowData.activityRecordFiles);
				return "<div style='clear:both' align='center'><a href='javascript:;' id='activityRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' activityRecordId='"+rowData.id+"' >附件>></a></div>";
			
			}
			return "";
		}
	
	
	function toggleActivityButtonState(){
	  	var selectedIds=$("#activityRecordList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	}
	
	function afterLoad(){
		
	}
	
	function parseObj(strData) {
		return (new Function("return " + strData))();
	}
	
	function searchActivityRecord(){
		var formdata = $("#searchActivityRecordForm").serialize();
		if (formdata != '') {
			formdata = formdata.replace(/=/g, '":"');
			formdata = formdata.replace(/&/g, '","');
			formdata += '"';
		}
		formdata = decodeURIComponent('{"' + formdata + '}');
		search(parseObj(formdata));
	}
	function search(postData){
		$("#activityRecordList").setGridParam({
			url:'${path}/partyBuilding/activityRecordManage/searchActivityRecord.action',
			datatype: "json",
			page:1
		});
		$("#activityRecordList").setPostData($.extend(postData,{type:"chlid"}));
		$("#activityRecordList").trigger("reloadGrid");
	}
	
	function viewActivityRecord(selectedId){
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		var rowData=  $("#activityRecordList").getRowData(selectedId);
		$("#activityRecordDialog").createDialog({
			width:650,
			height:450,
			title:'查看'+title+'记录',
			url:'${path}/partyBuilding/activityRecordManage/dispatchOperateByEncrypt.action?mode=view&orgId='+getCurrentOrgId()+'&partyOrganizationType='+partyOrganizationType+'&id='+rowData.encryptId,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	 
	
	function getActivitySelectedIdLast() {
		var selectedId;
		var selectedIds = $("#activityRecordList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	} 
	
	function showActivityRecordAttachFile(){
			$.each($(".popUpMore"), function(i, n){
				$.ajax({
					url:"${path}/partyBuilding/activityRecordManage/getActivityRecordById.action?activityRecords.id="+$(n).attr("activityRecordId")+"&partyOrganizationType="+partyOrganizationType,
					success:function(data){
						var popMoreData = [];
						for(var j = 0; j < data.activityRecordFiles.length; j++){
							popMoreData[j]={
									id:data.activityRecordFiles[j].id, 
									url:'${path}/partyBuilding/activityRecordManage/downloadActivityRecordsAttachFiles.action?activityRecordsAttachFiles.id='+data.activityRecordFiles[j].id, 
									text:data.activityRecordFiles[j].fileName,
									clickFun:function(){}
								};
						}
						$(n).popUpMore({data: popMoreData});
					}
				});
			}); 
		}
</script>