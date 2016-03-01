<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="search-condition-form" title="困难群众台账信息查询" class="container container_24" style="height: 100%">
<form id="searchPoorPeopleForm" method="post" action="">
       	<input type="hidden" id="organization" name="poorPeople.organization.id" value="${param.orgId}"/>
       	<input type="hidden" id="conditionOccurOrgId" name="poorPeople.occurOrg.id"  />
       	<input id="keyId" name="poorPeople.targeOrgId" type="hidden" value="${keyId}" />
       	<c:if test="${param.tag eq 'support' }"><input type="hidden" id="isSupported" name="poorPeople.isSupported" value="1" /></c:if>
        <c:if test="${param.tag eq 'need' }"><input type="hidden" id="isSupported" name="poorPeople.isSupported" value="0" /></c:if>
        <c:if test="${param.tag eq 'notice' }"><input type="hidden" id="isSupported" name="poorPeople.isSupported" value="2" /></c:if>
       	<input name="poorPeople.issueType" type="hidden" value="2" />
       	
   		<div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">编号：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="serialNumber" name="poorPeople.serialNumber" maxlength="50" value="" class="form-txt" />
			</div>
    		<div class='clearLine'></div>
    		<div class="grid_4 lable-right">
				<label class="form-lbl">姓名：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="name" name="poorPeople.name" maxlength="50" value="" class="form-txt" />
			</div>
			<div class='clearLine'></div>
    		<div class="grid_4 lable-right">
				<label class="form-lbl">身份证号：</label>
			</div>
			<div class="grid_12 form-left">
				<input type="text" id="idCardNo" name="poorPeople.idCardNo" maxlength="18" value="" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">性别：</label>
			</div>
			<div class="grid_12 lable-right">
		   		<select name="poorPeople.gender.id" class="form-txt">
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@GENDER" defaultValue="${poorPeople.gender.id}"/></select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			 <div class="grid_4 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_12 lable-right">
				 <input type="text" id="occurOrgSelector"  value="请点击此处选择" readonly="readonly" class="form-txt"  style="color: grey;" /> 
    		</div>
			<div class='clearLine'>&nbsp;</div>
	</div>
  </form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#orgName").click(function(event){
		$("#noticeDialog").createDialog({
			width: 300,
			height: 350,
			title:'请选择一个部门',
			url:'${path}/common/organizationSelector.jsp',
			buttons: {
				"确定" : function(){
					closeDialog();
				},
				"取消" : function(){
					$("#noticeDialog").dialog("close");
				}
			}
		});
	});
	$("#occurOrgSelector").one("click", function(){
		initOccurOrgSelector();
	});
});



function setZone(selectOrgId,selectOrgName){
	$("#conditionOccurOrgId").val(selectOrgId);
	$("#orgName").val(selectOrgName);
}

function initOccurOrgSelector(){
	var tree=$("#occurOrgSelector").treeSelect({
		inputName:"poorPeople.occurOrg.id"
	});
}

</script>