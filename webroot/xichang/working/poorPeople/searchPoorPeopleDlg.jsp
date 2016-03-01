<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="search-condition-form" title="困难群众台账信息查询" class="container container_24" style="height: 100%">
<form id="searchPoorPeopleForm" method="post" action="">
       	<input type="hidden" id="organization" name="poorPeople.organization.id" value="${param.orgId}"/>
       	<input type="hidden" id="conditionOccurOrgId" name="poorPeople.occurOrg.id"  />
   		<div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">编号：</label>
			</div>
			<div class="grid_20 form-left">
		    	<input type="text" id="serialNumber" name="poorPeople.serialNumber" maxlength="30" value="" class="form-txt" />
			</div>
<!-- 			<div class="grid_3 lable-right"> -->
<!-- 	  			<label class="form-lbl">发生网格：</label> -->
<!-- 	  		</div> -->
<!--     		<div class="grid_5 form-left"> -->
<!-- 				 <input type="text" id="issueOccurOrgSelector" name="selectOrgName"  value="请点击此处选择" readonly="readonly" class="form-txt"  style="color: grey;" />  -->
<!--     		</div> -->
    		<div class='clearLine'></div>
    		<div class="grid_4 lable-right">
				<label class="form-lbl">姓名：</label>
			</div>
			<div class="grid_8 form-left">
		    	<input type="text" id="name" name="poorPeople.name" maxlength="50" value="" class="form-txt" />
			</div>
    		<div class="grid_4 lable-right">
				<label class="form-lbl">身份证号：</label>
			</div>
			<div class="grid_8 form-left">
				<input type="text" id="idCardNo" name="poorPeople.idCardNo" maxlength="18" value="" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">性别：</label>
			</div>
			<div class="grid_8 lable-right">
		   		<select name="poorPeople.gender.id" class="form-txt">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${poorPeople.gender.id}"/></select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">是否党员：</label>
			</div>
			<div class="grid_8 lable-right">
				<select id="isPartyMember" name="poorPeople.isPartyMember" class="form-txt">
					<option>全部</option>
					<option value="false" <s:if test="!poorPeople.isPartyMember">selected</s:if>>否</option>
	 				<option value="true" <s:if test="poorPeople.isPartyMember">selected</s:if>>是</option>
		 		</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<!-- 
			<div class="grid_4 lable-right">
				<label class="form-lbl">评价反馈：</label>
			</div>
			<div class="grid_8 lable-right">
				<select id="" name="" class="form-txt" >
	 				<option value="true" <s:if test="poorPeople.isPartyMember">selected</s:if>>有</option>
	 				<option value="false" <s:if test="!poorPeople.isPartyMember">selected</s:if>>无</option>
		 		</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">记录情况：</label>
			</div>
			<div class="grid_8 lable-right">
				<select id="" name="" class="form-txt" >
 					<option value="-1">全部</option>
 					<option value="0">无</option>
	 				<option value="1">有</option>
		 		</select>
			</div>
			 -->
			 <div class="grid_4 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_7 lable-right">
				 <input type="text" id="occurOrgSelector" name="selectOrgName"  value="请点击此处选择" readonly="readonly" class="form-txt"  style="color: grey;" /> 
    		</div>
			<div class='clearLine'>&nbsp;</div>
	</div>
  </form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("ul.zc-sf li label").click(function(){
		$(this).parent().find("input").click();
	})
	

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