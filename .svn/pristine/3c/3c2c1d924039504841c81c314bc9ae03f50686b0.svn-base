<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<form id="maintainFormOfCommon" method="post" action="${path }/baseinfo/commonServiceInfoManage/maintainCommonServiceInfo.action"  >
<pop:token />
<input type="hidden" name="commonServiceInfo.organization.id" value="${organization.id }"/>
<input type="hidden" name="dataType" value="${dataType }"/>
<input type="hidden" name="commonServiceInfo.newVillageBasic.id" id="newVillageBasicId" value="${newVillageBasic.id }"/>
<input type="hidden" name="commonServiceInfo.id" id="commonServiceInfoId" value="${commonServiceInfo.id }"/>

<div id="commonServiceInfoDialog" style="height:400px;">
<span style="font-weight: bold; ">教育</span>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">村小(所)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.villageSchool" id="" value="${commonServiceInfo.villageSchool }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">幼儿园(所)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.kindergarten" id="" value="${commonServiceInfo.kindergarten }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">中学(所)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.highSchool" id="" value="${commonServiceInfo.highSchool }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<s:if test="0==isPlaning">
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">九年义务教育适龄人数(人)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.compulsoryEducationCount" id="" value="${commonServiceInfo.compulsoryEducationCount }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_5 lable-right" style="margin-left: 10px;">
	<label class="form-lbl">九年义务教育适龄已入学(人)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.inCompulsoryEducationCount" id="" value="${commonServiceInfo.inCompulsoryEducationCount }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
</s:if>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold; ">医疗卫生</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">新农合参保人数(人)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.insuredNumber" id="" value="${commonServiceInfo.insuredNumber }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<s:if test="0==isPlaning">
<div class="grid_6 lable-right" style="margin-left: 30px;">
	<label class="form-lbl">是否应保尽保：
	<input type="radio" name="commonServiceInfo.hasBuyInsurance" id="noBuyInsurance" value="0" checked="checked" <s:if test="'view'.equals(mode)">disabled</s:if>/>否 
	<input name="commonServiceInfo.hasBuyInsurance" id="hasBuyInsurance" type="radio" style="margin-left:20px;" value="1" <s:if test="'view'.equals(mode)">disabled</s:if>/>是
	</label>
</div>
<div class='clearLine'>&nbsp;</div>
</s:if>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">卫生室(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.clinic" id="" value="${commonServiceInfo.clinic }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold; ">文体</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">村级公共服务活动中心(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.socialWorkCenter" style="width:35%" id="" value="${commonServiceInfo.socialWorkCenter }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>个
	<input type="text" name="commonServiceInfo.socialWorkCenterArea" style="width:40%" id="" value="<fmt:formatNumber value='${commonServiceInfo.socialWorkCenterArea }' pattern='#0.00'/>" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据,且小数位最大为2位'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>平方米
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">图书室(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.library" id="" value="${commonServiceInfo.library }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">健身广场(平方米)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.fitnessSquare" id="" value="<fmt:formatNumber value='${commonServiceInfo.fitnessSquare }' pattern='#0.00'/>" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据'}}"  maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">健身器材(套)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.fitnessEquipment" id="" value="${commonServiceInfo.fitnessEquipment }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">文化活动室(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.entertainmentRoom" id="" value="${commonServiceInfo.entertainmentRoom }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">农家超市(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.farmerSupermarket" id="" value="${commonServiceInfo.farmerSupermarket }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<s:if test="0==isPlaning">
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">组织开展文体活动(次)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.recreationalActivities" id="" value="${commonServiceInfo.recreationalActivities }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">参加活动人数(人)：</label>
</div>
<div class="grid_6">
	<input type="text" name="commonServiceInfo.recreationalActivitiesPeople" id="" value="${commonServiceInfo.recreationalActivitiesPeople }" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}"  maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
</s:if>

 </div>
 <s:if test="!'view'.equals(mode)">
  <input type="submit" value="保存" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left: 900px;"/>
 </form>
 </s:if>
 </div>
<script>
function dataHasBuyInsurance(){
	var val = "${commonServiceInfo.hasBuyInsurance}";
	if(val==0){
		$("#noBuyInsurance").attr("checked","checked");
	}else{
		$("#hasBuyInsurance").attr("checked","checked");
	}
}

	$(document).ready(function(){
		dataHasBuyInsurance()
		$("#maintainFormOfCommon").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data.id){
							 $("#commonServiceInfoId").val(data.id);
							 $.messageBox({message:"公共服务信息保存成功!"});
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