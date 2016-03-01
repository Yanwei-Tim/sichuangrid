<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="partyOrgActivityList"> </table>
		<div id="partyOrgActivityListPager"></div>
	</div>
	<div id="partyOrgActivityDialog"></div>
	<div id="noticeDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="partyOrgActivityManagement">
		<span id="title"><s:property value="#request.name"/></span>
	</pop:JugePermissionTag>
</div>

<script type="text/javascript">
	var dialogWidth=400;
	var dialogHeight=300;
	var isgridBol = false;

	// ------------------------------ party org activity search list and page layout start  ------------------------------
	$(document).ready(function() {
		function formatterAttachFile(el,options,rowData){
			if(rowData.activityAttachFile.length>0){
				return "<div style='clear:both' align='center'><a href='javascript:;' id='orgActivity_"+rowData.id+"' style='color:#666666' class='popUpMore' orgActivityId='"+rowData.id+"' >附件>></a></div>";
			}
			return "";
		}

		$("#partyOrgActivityList").jqGridFunction({
			datatype: "local",
			colModel:[
		    	{name:"id", index:"id", hidden:true,frozen : true},
	  			{name:"activitySubject", index:"activitySubject",label:"活动主题",width:100, frozen : true},
	  			{name:"activityAddr", index:"activityAddr", label:"活动地点", width:100, align:"center"},
	  			{name:"activityDate", index:'activityDate', label:'活动时间', width:100, frozen : true},
	  		  	{name:"organizers", index:'organizers', label:'组织者', width:80, frozen : true},
	  		  	{name:"participants", index:'participants', label:'参与者', width:80, frozen : true},
	  		  	{name:"organization.orgName", index:"orgInternalCode", label:"所属区域(网格)", width:170, hidden:true},
	  		  	{name:"activeContent", index:'activeContent', label:'活动内容', width:80 ,hidden:true},
	  		  	{name:"activityAttachFile",  formatter:formatterAttachFile, label:'附件', width:150, frozen : true}
		  	]
		});
	  	jQuery("#partyOrgActivityList").jqGrid('setFrozenColumns');

		if(getCurrentOrgId()!="" && getCurrentOrgId() != null) {
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
	})

	// serarch list
	function onOrgChanged(orgId,isgrid){
		isgridBol = isgrid;
		$("#partyOrgActivityList").setGridParam({
			url:"${path}/baseinfo/partyOrgActivityManage/partyOrgActivityList.action",
			datatype: "json",
			page:1
		});
		$("#partyOrgActivityList").setPostData({
			"orgId":orgId,
	   	});
		$("#partyOrgActivityList").trigger("reloadGrid");
	}

</script>