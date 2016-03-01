<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="dialog-form" title="新村建设信息维护" class="container container_24">
 <s:if test="!'view'.equals(mode)">
<form id="maintainFormOfAdd" method="post"action="${path }/baseinfo/newVillageManage/maintainNewVillage.action"  >
<pop:token />
</s:if>
<input type="hidden" name="newVillage.organization.id" value="${organization.id }"/>
<input type="hidden" name="dataType" value="${dataType }"/>
<input type="hidden" name="newVillage.newVillageBasic.id" id="newVillageBasicId" value="${newVillageBasic.id }"/>
<input type="hidden" name="newVillage.id" id="newVillageId" value="${newVillage.id }"/>

<div id="newVillageDialog" style="height:400px;">
<span style="font-weight: bold; ">聚居点情况</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">聚居点数量(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.settlementsNumber" id="settlementsNumber" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}}" value="${newVillage.settlementsNumber }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">涉及农户数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.involvingFarmers" id="involvingFarmers" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}}" value="${newVillage.involvingFarmers }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">其中：</label>
	<label class="form-lbl">新建数量(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.settlementsBuild" id="settlementsBuild" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}}" value="${newVillage.settlementsBuild }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">改造数量(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.settlementsReform" id="settlementsReform" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}}" value="${newVillage.settlementsReform }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">保护数量(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.settlementsProtect" id="settlementsProtect" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}}" value="${newVillage.settlementsProtect }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<s:if test="0==isPlaning">
<div class='clearLine'>&nbsp;</div>
<div class="grid_10 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">是否通过新建、改造、保护等形式推进新村建设全覆盖：
	<input type="radio" name="newVillage.isAllStanding" id="noAllStanding" value="0" checked="checked" <s:if test="'view'.equals(mode)">disabled</s:if>/>否 
	<input name="newVillage.isAllStanding" id="isAllStanding" type="radio" style="margin-left:20px;" value="1" <s:if test="'view'.equals(mode)">disabled</s:if>/>是
	</label>
</div>
</s:if>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold;">住房情况</span>
<div class='clearLine'>&nbsp;</div>
<s:if test="0==isPlaning">
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">房屋总数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.houseCount" id="houseCount" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}} " value="${newVillage.houseCount }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">其中：</label>
	<label class="form-lbl">无房户(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.noHouseCount" id="noHouseCount" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}} " value="${newVillage.noHouseCount }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">危房户(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.dangerousHouseCount" id="dangerousHouseCount" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}} " value="${newVillage.dangerousHouseCount }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">住房困难户(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.housingDifficultCount" id="housingDifficultCount" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}} " value="${newVillage.housingDifficultCount }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
</s:if>
<div class="grid_4 lable-left" style="margin-left: 30px;">
	<label class="form-lbl">人均安全住房面积(平方米)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.capitaHousingArea" id="capitaHousingArea" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据,且小数位最大为2位'}}" value="<fmt:formatNumber value='${newVillage.capitaHousingArea}' pattern='#0.00'/>" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">廉租房数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.lowRentHousing" id="lowRentHousing" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}} " value="${newVillage.lowRentHousing }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
    <label class="form-lbl">其中：</label>
	<label class="form-lbl">入住廉租房(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.inLowRentHousing" id="inLowRentHousing" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}} " value="${newVillage.inLowRentHousing }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">涉及人数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="newVillage.numberInvolved" id="numberInvolved" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的正整数'}} " value="${newVillage.numberInvolved }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
 </div>
 <s:if test="!'view'.equals(mode)">
 <input type="submit" value="保存" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left: 900px;"/>
 </form>
 </s:if>
 </div>
 
<script>
function dataIsAllStanding(){
	var val = "${newVillage.isAllStanding}";
	if(val==0){
		$("#noAllStanding").attr("checked","checked");
	}else{
		$("#isAllStanding").attr("checked","checked");
	}
}
$(document).ready(function(){
	dataIsAllStanding();
	$("#maintainFormOfAdd").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				async:false,
				success:function(data){
					if(data.id){
						 $("#newVillageId").val(data.id);
						 $.messageBox({message:"新村建设信息保存成功!"});
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