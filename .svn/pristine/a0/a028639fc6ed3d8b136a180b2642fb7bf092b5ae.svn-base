<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/serviceTeamManage/typeFormatter.jsp"%>
<div class="container container_24">
	<s:action name="ajaxOrganization" var="findById" namespace="/sysadmin/orgManage" executeResult="false">
		<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getOrganization().id"></s:param>
	</s:action>
	<input type="hidden" id="currOrgId" name="currOrgId" value="<s:property value="#findById.organization.id"/>" />	
	
	
	<div class="grid_4 lable-right">
  		<label class="form-lb1">所属区域：</label>
  	</div>
	<div class="grid_16">
		<input type="text" id="OrgName" readonly="readonly" value='<s:property value="#findById.organization.orgName" escape="false"/>' />
	</div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_4 lable-right">
		<label class="form-lb1">组织大类：</label>
   	</div>
   	<div class="grid_16">
   		<select id="_teamClazz" name="searchServiceTeamMemberVo.teamClazzId" class="form-select" >
	   			<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ"  
	   			reletionId="_teamTypeId" reletionName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" id="_teamClazz"/>
			</select>
   	</div>
   	<div class='clearLine'>&nbsp;</div>

	<div class="grid_4 lable-right">
		 <label class="form-lb1">组织类别：</label>
   	</div>
   	<div class="grid_16">
   		<select id="_teamTypeId" name="searchServiceTeamMemberVo.teamTypeId" class="form-select"></select>
   	</div>
   		
   	<div class='clearLine'>&nbsp;</div>	
   	<div class="grid_4 lable-right">
		 <label class="form-lb1">组织名称：</label>
   	</div>
   	<div class="grid_16">
   		<input type="text" id="_teamName" class="form-txt" />
   	</div>
   	<div class='clearLine'>&nbsp;</div>
   		
	<div class="grid_6"></div>
	<div class="grid_6">
		<input type="button" value="查询" id="search_button"/>
	</div>
	<div class="grid_6">
		<input type="button" value="重置" id="reset_button"/>
	</div>
	<div class="grid_6"></div>

	<div class='clearLine'></div>
	<div style="width: 100%;margin-top:6px">
		<table id="teamMemberList"> </table>
		<div id="teamMemberListPager"></div>
	</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="position" domainName="@com.tianque.domain.property.PropertyTypes@COMPOSITEDUTY" />
$(document).ready(function(){
	$("#OrgName").treeSelect({
		inputName:"currOrgId",
		listWidth:260,
		maxHeight:300,
		rootId:'<s:if test="#findById.organization.orgType.internalId==@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"><s:property value="#findById.organization.parentOrg.id"/></s:if><s:else><s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/></s:else>',
		onSelect:function(){
			if(typeof(onOrgChanged) != 'undefined'){
				onOrgChanged.call(null,$("#currOrgId").attr("value"));
			}
		}
	});
	
	$("#teamMemberList").jqGridFunction({
		datatype: "local",
		colNames:['id','teamId','职务','姓名','性别','身份证号码','联系手机','组织名称','组织类别'],
	   	colModel:[
	        {name:'id',index:'id',hidden:true},
	        {name:'teamId',index:'teamId',hidden:true, width:10},
	        {name:'position.displayName',sortable:true,width:70},
	        {name:'name',sortable:false, width:80},
	        {name:'gender',sortable:true, width:30,formatter:genderFormatter},
	   		{name:'idCardNo',sortable:false, width:100},
      		{name:'mobile',sortable:false, width:90},
      		{name:'teamName',sortable:false, width:120},
      		{name:'teamClazz',sortable:false,hidden:true,width:100,formatter:teamClazzFormatter}
		],
		height:260,
	    multiselect:true
	});
	
	$("#search_button").click(function(){
		var listParam = {
			"searchServiceTeamMemberVo.orgId":$("#currOrgId").val(),
			"searchServiceTeamMemberVo.teamClazzId":$("#_teamClazz").val(),
			"searchServiceTeamMemberVo.teamTypeId":$("#_teamTypeId").val(),
			"searchServiceTeamMemberVo.teamName":$("#_teamName").val(),
			"searchServiceTeamMemberVo.members":$("#_serviceMembers").val()
		}
		$("#teamMemberList").setGridParam({
			url:'${path}/baseinfo/searchServiceTeamMember/pageTeamMembersBySearchServiceTeamMemberVo.action',
			datatype: "json",
			page:1
		});
		$("#teamMemberList").setPostData(listParam);
		$("#teamMemberList").trigger("reloadGrid");
	});
	
	$("#reset_button").click(function(){
		$("#currOrgId").val('<s:property value="#findById.organization.id"/>');
		$("#OrgName").val('<s:property value="#findById.organization.orgName" escape="false"/>');
		$("#_teamClazz").val($("#_teamClazz option:eq(0)").val());
		$("#_teamTypeId").val($("#_teamTypeId option:eq(0)").val());
		$("#teamMemberList").jqGrid("clearGridData");
	});
})
</script>