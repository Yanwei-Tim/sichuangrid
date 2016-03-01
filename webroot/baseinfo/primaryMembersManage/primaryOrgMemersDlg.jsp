<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="viewPrimaryOrgMemer" class="container container_24">
	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
		    <input type="text" value="请输入姓名" name="searchText" id="searchText" 
		    maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名':this.value;"
		    onfocus="value=(this.value=='请输入姓名')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a id="fastSearchButton" href="javascript:;"><span>检索</span></a>
		<pop:JugePermissionTag ename="addPromaryOrgMember${param.name==null?'':param.name}">
			<a id="addMember" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<input id="isHaveJob0" value="0" type="hidden"/>
	</div>
	<div id="addMemberDialog"></div>
	<div id="tabs">
		<ul>				
			<li><a id="hava"  href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryOrgMemberList&primaryMemberVo.primaryOrgId=${PrimaryMemberVo.primaryOrgId}&primaryMemberVo.org.id=${primaryMemberVo.org.id}&primaryMemberVo.isHaveJob=0&name=${param.name}&primaryMemberVo.primaryOrgName=${primaryMemberVo.primaryOrgName}&primaryMemberVo.isFourLevelPlatform=${primaryMemberVo.isFourLevelPlatform }&primaryMemberVo.displayLevel=<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>'>现有成员</a></li>
			<li><a id="nothava" href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryOrgMemberList&primaryMemberVo.primaryOrgId=${PrimaryMemberVo.primaryOrgId}&primaryMemberVo.org.id=${primaryMemberVo.org.id}&primaryMemberVo.isHaveJob=1&name=${param.name}&primaryMemberVo.primaryOrgName=${primaryMemberVo.primaryOrgName}&primaryMemberVo.isFourLevelPlatform=${primaryMemberVo.isFourLevelPlatform }&primaryMemberVo.displayLevel=<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>'>卸任成员</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
var name = "${param.name==null?'':param.name}";
var detailName = "${primaryOrgDetailName}";
$("#refreshSearchKey").click(function(){$("#searchText").attr("value","请输入姓名");});
$(function() {
	$("#tabs").tabs({cache:false});
	$("#addMember").click(function(event){
		$("#addMemberDialog").createDialog({
			width:680,
			height:550,
			title:"新增组织成员", 
			url:"${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=add&primaryMembers.isHaveJob=0&orgId="+currentOrgId+"&internalId="+internalId+"&isFourLevelPlatform="+${primaryMemberVo.isFourLevelPlatform}+"&primaryOrgId="+${primaryMemberVo.primaryOrgId}+"&primaryOrgDetailName="+encodeURIComponent(detailName),
			buttons: {
					"保存并继续" : function(event){
						$("#isSubmit").val("false");
			   			$("#primaryMembersForm").submit();
					},
					
					"保存并关闭" : function(event){
						$("#isSubmit").val("true");
						$("#primaryMembersForm").submit();	
						 //$("#addMemberDialog").dialog('close');
					},
					"关闭" : function(event){
			   			 $("#addMemberDialog").dialog('close');
					}
				}
		});
	});
});

$("#hava").click(function (event){
	$("#isHaveJob0").val("0");
});
$("#nothava").click(function (event){
	$("#isHaveJob0").val("1");
});
	
	
</script>