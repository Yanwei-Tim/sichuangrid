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
<form id="maintainFormOfEnvironmental" method="post" action="${path }/baseinfo/environmentalReformManage/maintainEnvironmentalReform.action"  >
<pop:token />
<input type="hidden" name="environmentalReform.organization.id" value="${organization.id }"/>
<input type="hidden" name="dataType" value="${dataType }"/>
<input type="hidden" name="environmentalReform.newVillageBasic.id" id="newVillageBasicId" value="${newVillageBasic.id }"/>
<input type="hidden" name="environmentalReform.id" id="commonServiceInfoId" value="${environmentalReform.id }"/>

<div id="newVillageDialog" style="height:300px;">
<span style="font-weight: bold; ">环境改造</span>
<div class='clearLine'>&nbsp;</div>


<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >垃圾池(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="environmentalReform.garbageTank" id="" value="${environmentalReform.garbageTank }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" <s:if test="'view'.equals(mode)">disabled</s:if>  maxlength="8"/>
</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >公厕(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="environmentalReform.toilets" id="" value="${environmentalReform.toilets }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" <s:if test="'view'.equals(mode)">disabled</s:if> maxlength="8"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >污水处理设施(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="environmentalReform.treatmentFacilities" id="" value="${environmentalReform.treatmentFacilities }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" <s:if test="'view'.equals(mode)">disabled</s:if> maxlength="8"/>
</div>
<s:if test="0==isPlaning">
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >生活污水处理户数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="environmentalReform.sanitarySewage" id="" value="${environmentalReform.sanitarySewage }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" <s:if test="'view'.equals(mode)">disabled</s:if> maxlength="8"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_8 lable-left" style="margin-left: 63px;">
	<label class="form-lbl">是否生活垃圾处理全覆盖：
	<input type="radio" name="environmentalReform.garbageDisposal" id="noGarbageDisposal" value="0" checked="checked" <s:if test="'view'.equals(mode)">disabled</s:if>/>否 
	<input name="environmentalReform.garbageDisposal" id="yesGarbageDisposal" type="radio" style="margin-left:20px;" value="1" <s:if test="'view'.equals(mode)">disabled</s:if>/>是
	</label>
</div>
<div class="grid_2"></div>
<div class="grid_8 lable-left" style="margin-left: 63px;">
	<label class="form-lbl">是否面源污染有效治理：
	<input type="radio" name="environmentalReform.treatmentPollution" id="noTreatmentPollution" value="0" checked="checked" <s:if test="'view'.equals(mode)">disabled</s:if>/>否 
	<input name="environmentalReform.treatmentPollution" id="yesTreatmentPollution" type="radio" style="margin-left:20px;" value="1" <s:if test="'view'.equals(mode)">disabled</s:if>/>是
	</label>
</div>
</s:if>
 </div>
 <s:if test="!'view'.equals(mode)">
  <input type="submit" value="保存" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left: 910px;"/>
 </form>
 </s:if>
 </div>
<script>
function garbageDisposal(){
	var val = "${environmentalReform.garbageDisposal}";
	if(val==0){
		$("#noGarbageDisposal").attr("checked","checked");
	}else{
		$("#yesGarbageDisposal").attr("checked","checked");
	}
}
function treatmentPollution(){
	var val = "${environmentalReform.treatmentPollution}";
	if(val==0){
		$("#noTreatmentPollution").attr("checked","checked");
	}else{
		$("#yesTreatmentPollution").attr("checked","checked");
	}
}
	$(document).ready(function(){
		garbageDisposal();
		treatmentPollution();
		$("#maintainFormOfEnvironmental").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data.id){
							 $("#commonServiceInfoId").val(data.id)
							 $.messageBox({message:"环境改造信息保存成功!"});
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