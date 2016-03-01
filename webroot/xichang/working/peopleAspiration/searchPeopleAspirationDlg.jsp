<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="search-condition-form" title="民生诉求台账信息查询" class="container container_24" style="overflow:hidden;">
<form id="searchPeopleAspirationForm" method="post" action="">
       	<input type="hidden" id="organization" name="searchPeopleAspirationsVo.orgId" value="${param.orgId}"/>
       	<input type="hidden" id="orgId" name="organization.id" value="${param.orgId}"/>
       	<input type="hidden" id="conditionOccurOrgId" name="searchPeopleAspirationsVo.occurOrg.id"  />
       	<input type="hidden" id="displayLevel" name="searchPeopleAspirationsVo.displayLevel" value="${param.displayLevel}"/>
		
<!--         <input id="conditionOccurOrgId" name="searchIssueVo.occurOrg.id" type="hidden" /> -->
<%-- 		<input id="keyId" name="searchIssueVo.targeOrgId" type="hidden" value="${keyId}" /> --%>
   		<div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">编号：</label>
			</div>
			<div class="grid_12 form-left">
		    	<input type="text" id="serialNumber" name="searchPeopleAspirationsVo.serialNumber" maxlength="30" value="" class="form-txt" />
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
			<div class="grid_7 form-left">
		    	<input type="text" id="name" name="searchPeopleAspirationsVo.name" maxlength="50" value="" class="form-txt" />
			</div>
    		<div class="grid_4 lable-right">
				<label class="form-lbl">身份证号：</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="idCardNo" name="searchPeopleAspirationsVo.idCardNo" maxlength="18" value="" class="form-txt" />
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">性别：</label>
			</div>
			<div class="grid_7 lable-right">
				<pop:PropertyDictSelectTag id="genderId" name="searchPeopleAspirationsVo.genderId"
					domainName="@com.tianque.domain.property.PropertyTypes@GENDER" cls="form-select"></pop:PropertyDictSelectTag>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">是否党员：</label>
			</div>
			<div class="grid_7 lable-right">
				<select id="isPartyMember" name="" class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
					<option value>全部</option>
					<option value="false" <s:if test="!searchPeopleAspirationsVo.isPartyMember">selected</s:if>>否</option>
	 				<option value="true" <s:if test="searchPeopleAspirationsVo.isPartyMember">selected</s:if>>是</option>
		 		</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
		<!--
			<div class="grid_4 lable-right">
				<label class="form-lbl">评价反馈：</label>
			</div>
			<div class="grid_7 lable-right">
				<select id="" name="" class="form-txt" <s:if test='"view".equals(mode)'>disabled</s:if>>
		 				<option value="true" <s:if test="searchPeopleAspirationsVo.isPartyMember">selected</s:if>>有</option>
		 				<option value="false" <s:if test="!searchPeopleAspirationsVo.isPartyMember">selected</s:if>>无</option>
		 		</select>
			</div>
		-->
			<div class="grid_4 lable-right">
				<label class="form-lbl">记录情况：</label>
			</div>
			<div class="grid_7 lable-right">
				<select id="hasAccountLog" name="" class="form-select" <s:if test='"view".equals(mode)'>disabled</s:if>>
	 				<s:elseif test="#parameters.type==null || !'done'.equals(#parameters.type[0])"> 
	 					<option value>全部</option>
	 					<option value="false" <s:if test="!searchPeopleAspirationsVo.hasAccountLog">selected</s:if>>无</option>
	 				</s:elseif>
	 				<option value="true" <s:if test="searchPeopleAspirationsVo.hasAccountLog">selected</s:if>>有</option>
		 		</select>
			</div>
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">发生网格：</label>
	  		</div>
    		<div class="grid_7 lable-right">
				 <input type="text" id="occurOrgSelector" name="selectOrgName"  value="请点击此处选择" readonly="readonly" class="form-txt"  style="color: grey;" /> 
    		</div>
    		<div class='clearLine'></div>
	</div>
  </form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("ul.zc-sf li label").click(function(){
		$(this).parent().find("input").click();
	})
	
	$("#hasAccountLog,#isPartyMember").change(function(){
		var value = $(this).val();
		if(value==null || value=="" || value=="全部"){
			 $(this).attr("name","")
		}else{
			$(this).attr("name","searchPeopleAspirationsVo."+this.id)
		}
	}).change();
	

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
		inputName:"searchPeopleAspirationsVo.occurOrg.id"
	});
}

</script>