<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="search-condition-form" title="稳定工作台账信息查询" class="container container_24">
<form id="searchSteadyWorkForm" method="post" action="">
       	<input type="hidden" id="orgId" name="organization.id" value="${param.orgId}"/>
       	<input type="hidden" id="state" name="state" value="${param.state}"/>
       	<input type="hidden" id="conditionOccurOrgId" name="searchVo.occurOrg.id"  />
       	<input id="keyId" name="searchVo.targeOrgId" type="hidden" value="${keyId}" />
       		<c:if test="${param.tag eq 'support' }"><input type="hidden" id="isSupported" name="searchVo.isSupported" value="1" /></c:if>
        <c:if test="${param.tag eq 'need' }"><input type="hidden" id="isSupported" name="searchVo.isSupported" value="0" /></c:if>
        <c:if test="${param.tag eq 'notice' }"><input type="hidden" id="isSupported" name="searchVo.isSupported" value="2" /></c:if>
       	<input name="searchVo.issueType" type="hidden" value="3" />
       	
		
   		<div> 
			<div class="grid_4 lable-right">
				<label class="form-lbl">编号：</label>
			</div>
			<div class="grid_12 form-left">
		    		    	<input type="text" name="searchVo.serialNumber"  id="serialNumber" maxlength="15" value="${searchVo.serialNumber}" class='form-txt' />
			</div>
    		<div class='clearLine'></div>
    		<div class="grid_4 lable-right">
				<label class="form-lbl">姓名：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="name" name="searchVo.name" maxlength="50" value="" class="form-txt" />
			</div>
			<div class='clearLine'>&nbsp;</div>
    		<div class="grid_4 lable-right">
				<label class="form-lbl">身份证号：</label>
			</div>
			<div class="grid_12 form-left">
				<input type="text" id="idCardNo" name="searchVo.idCardNo" maxlength="18" value="" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">性别：</label>
			</div>
			<div class="grid_12 lable-right" >
			<select name="searchVo.gender.id" id="genderId" class='form-txt' >
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@GENDER"  defaultValue="${searchVo.gender.id}" />
			</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_12 lable-right">
				 <input type="text" id="occurOrgSelector" name="selectOrgName"  value="请点击此处选择" readonly="readonly" class="form-txt"  style="color: grey;" /> 
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
		inputName:"searchVo.occurOrg.id"
	});
}

</script>