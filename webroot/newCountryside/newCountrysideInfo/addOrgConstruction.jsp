<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<style type="text/css">
	.select_width{
		width: 156px !important;
	}
	.div_width{
		width: 35px;
	}
	.span_none{
		display: none;
	}
</style>
<div id="dialog-form" class="container container_24">
<form id="maintainFormOfOrgConstruction" method="post" action="${path }/baseinfo/orgConstructionManage/maintainOrgConstruction.action"  >
<pop:token />
<input type="hidden" name="orgConstruction.organization.id" value="${organization.id }"/>
<input type="hidden" name="dataType" value="${dataType }"/>
<input type="hidden" name="orgConstruction.newVillageBasic.id" id="newVillageBasicId" value="${newVillageBasic.id }"/>
<input type="hidden" name="orgConstruction.id" id="commonServiceInfoId" value="${orgConstruction.id }"/>


<div id="newVillageDialog" style="height:300px;">
<span style="font-weight: bold; ">基层组织建设</span>
<div class='clearLine'>&nbsp;</div>

<div class="grid_6 lable-right" style="margin-left: 104px;">
	<label class="form-lbl">阵地建设：<input type="radio" name="orgConstruction.isPositionBuilding" id="notPositionBuilding" value="0" <s:if test="'view'.equals(mode)">disabled</s:if>/>否 <input type="radio" name="orgConstruction.isPositionBuilding" id="yesPositionBuilding" value="1" <s:if test="'view'.equals(mode)">disabled</s:if>/>是</label>
</div>
<div class='clearLine'>&nbsp;</div>
<s:if test="0==isPlaning">
<div class="grid_6 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">党员人数(人)：</label>
</div>
<div class="grid_6">
	<input type="text" name="orgConstruction.partyMembers" id="" value="${orgConstruction.partyMembers }" class="form-txt {posNumWiPot:true,max:999999999,messages:{digits:'请输入0-999999999范围内最大两位小数的数字'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
</s:if>
<div class="grid_6 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">三务公开群众满意度(%)：</label>
</div>
<div class="grid_6">
	<input type="text" name="orgConstruction.threeSatisfaction" id="" value="${orgConstruction.threeSatisfaction }" class="form-txt {posNumWiPot:true,max:100,messages:{digits:'请输入0-100范围内最大两位小数的数字'}}"  maxlength="5" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_6 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">基层党组织和农村党员调查满意度(%)：</label>
</div>
<div class="grid_6">
	<input type="text" name="orgConstruction.surveySatisfaction" id=""  value="${orgConstruction.surveySatisfaction }" class="form-txt {posNumWiPot:true,max:100,messages:{digits:'请输入0-100范围内最大两位小数的数字'}}"  maxlength="5" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
</div>
 <s:if test="!'view'.equals(mode)">
 <input type="submit" value="保存" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left: 910px;"/>
 </form>
 </s:if>
 </div>
<script>
function dealPositionBuilding(){
	if("${orgConstruction.isPositionBuilding}"==0){
		$("#notPositionBuilding").attr("checked","checked");
	}else{
		$("#yesPositionBuilding").attr("checked","checked");
	}
}
	
	$(document).ready(function(){
		dealPositionBuilding();
		$("#maintainFormOfOrgConstruction").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data.id){
							 $("#commonServiceInfoId").val(data.id);
							 $.messageBox({message:"基层组织信息保存成功!"});
						}else{
							 $.messageBox({message:data,level:'error'});
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
			},
			messages:{
			}
		});
	});

</script>