<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<% String teamId = request.getParameter("teamId"); %>
<% String onDuty = request.getParameter("onDuty"); %>
<div class="container container_24" id="viewTeamMember">
	<input id="teamId" name="teamId" type="hidden" value="<%=teamId %>"/>
	<input type="hidden" id="_teamClazzId" value="${teamClazzId}"/>
	<input type="hidden" id="_onDuty" value="<%=onDuty %>"/>
	<div class='clearLine'></div>
	<div style="width: 100%;margin-top:6px">
		<table id="viewTeamMemberList"> </table>
		<div id="viewTeamMemberListPager"></div>
	</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="serviceTeamMemberBase.gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />


function positionFormat(el,options,rowData){
	var content = '';
	if(rowData["position"] && rowData["position"].id){
		$.ajax({
			url:"${path}/baseinfo/serviceTeamMemberManage/findPropertyDicts.action?teamClazzId="+$("#_teamClazzId").val(),
			cache: false,
			async: false,
			success:function(dicts){
				if(null != dicts) {
					$.each(dicts, function(ind, val){
						if(val.id == rowData["position"].id){
							content = val.displayName;
						}
					});
				}
			}
		});
	}else{
		content=rowData["position"];
	}
	return content;
}

$(document).ready(function(){
	$("#viewTeamMemberList").jqGridFunction({
		datatype: "local",
		colNames:['id','orgId','姓名','性别','身份证号码','职务','联系手机','固定电话','备注'],
	   	colModel:[
	   	    {name:"id",index:"id",hidden:true},
	        {name:"serviceTeamMemberBase.orgId",index:"orgId",hidden:true},
	        {name:'serviceTeamMemberBase.name',index:"name",sortable:false,width:100,align:"center"},
	        {name:'serviceTeamMemberBase.gender',index:'gender',sortable:false,width:50,formatter:genderFormatter,align:"center"},
	        {name:'serviceTeamMemberBase.idCardNo',index:'idCardNo',sortable:false,width:180,align:"center"},
	        {name:"position",index:"position",align:"center",width:100,formatter:positionFormat},
	        {name:'serviceTeamMemberBase.mobile',width:100,index:'mobile',sortable:false,width:120,align:"center"},
	        {name:'serviceTeamMemberBase.homePhone',index:'homePhone',sortable:false,width:150,hidden:true},
	        {name:'serviceTeamMemberBase.remark',index:'remark',sortable:false,width:200,hidden:true}
		],
		height:220,
		showColModelButton:false
	});
	$("#viewTeamMemberList").setGridParam({
		url:"${path}/baseinfo/serviceTeamMemberManage/findServiceTeamMemberInTeam.action",
		datatype: "json",
		page:1
	});
	$("#viewTeamMemberList").setPostData({
		"teamId":$("#teamId").val(),
		"onDuty":$("#_onDuty").val()
   	});
	$("#viewTeamMemberList").trigger("reloadGrid");
	$("#viewTeamMemberList").jqGrid('setFrozenColumns');
});
</script>