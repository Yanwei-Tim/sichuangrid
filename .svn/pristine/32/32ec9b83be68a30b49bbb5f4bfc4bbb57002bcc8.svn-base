<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="dialog-form" class="container container_24">
<form id="maintainFormOfInfrastructure" method="post" action="${path }/baseinfo/infrastructureManage/maintainInfrastructure.action"  >
<pop:token />
<input type="hidden" name="infrastructure.organization.id" value="${organization.id }"/>
<input type="hidden" name="dataType" value="${dataType }"/>
<input type="hidden" name="infrastructure.newVillageBasic.id" id="newVillageBasicId" value="${newVillageBasic.id }"/>
<input type="hidden" name="infrastructure.id" id="commonServiceInfoId" value="${infrastructure.id }"/>


<div id="newVillageDialog" style="height:600px;">
<span style="font-weight: bold; ">道路</span>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">村社道路(公里)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.villageRoad" id="" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据'}}" value="<fmt:formatNumber value='${infrastructure.villageRoad}' pattern='#0.00'/>" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">其中：</label>
	<label class="form-lbl">硬化路(公里)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.villageHardenedRoad" id="" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据'}}" value="<fmt:formatNumber value='${infrastructure.villageHardenedRoad}' pattern='#0.00'/>" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >泥结石路(公里)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.villageMudKnotRoad" id="" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据'}}" value="<fmt:formatNumber value='${infrastructure.villageMudKnotRoad}' pattern='#0.00'/>" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<!-- <div class="grid_8 lable-left" style="margin-left: 50px;"> -->
<!-- 	<label class="form-lbl" ><font style="color:red;">说明：包含新建和整治道路总长度。</font></label> -->
<!-- </div> -->
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold; ">水利</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">塘湖堰池(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.weirPoolNum" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.weirPoolNum}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<!-- <div class="grid_8 lable-left" style="margin-left: 50px;"> -->
<!-- 	<label class="form-lbl" ><font style="color:red;">说明：唐库堰池年度规划数量包含新建和整治数量总和。</font></label> -->
<!-- </div> -->
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">其中：</label>
	<label class="form-lbl">塘(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.pond" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.pond}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">库(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.library" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.library}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">堰(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.weir" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.weir}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">池(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.pool" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.pool}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >水渠(公里)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.canalLength" id="" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据'}}" value="<fmt:formatNumber value='${infrastructure.canalLength}' pattern='#0.00'/>" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >生活饮用水户数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.drinkingWaterNum" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.drinkingWaterNum}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold; ">电力</span>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right" style="margin-left: 63px;">
	<label class="form-lbl">农村电网改造：<input type="radio" name="infrastructure.isPowerGrid" id="notPowerGrid" value="0" <s:if test="'view'.equals(mode)">disabled</s:if>/>否  <input name="infrastructure.isPowerGrid" id="yesPowerGrid" type="radio" value="1" <s:if test="'view'.equals(mode)">disabled</s:if>/>是  </label>
</div>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold; ">清洁能源</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >户用沼气池(口)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.biogasNum" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.biogasNum}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >太阳能(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.solarEnergy" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.solarEnergy}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >天燃气(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.naturalGas" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.naturalGas}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold; ">三建四改</span>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >涉及户数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.involveHouseCount" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.involveHouseCount}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold; ">农村信息化</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right" style="margin-left: 63px;">
	<label class="form-lbl">宽带乡村：<input type="radio" name="infrastructure.isBroadbandVillage" id="notBroadbandVillage" value="0" <s:if test="'view'.equals(mode)">disabled</s:if>/>否  <input type="radio" name="infrastructure.isBroadbandVillage" id="yesBroadbandVillage" value="1" <s:if test="'view'.equals(mode)">disabled</s:if>/>是</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">有线电视(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.cableTvCount" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.cableTvCount}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">宽带(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="infrastructure.broadbandCount" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999的整数'}}" value="${infrastructure.broadbandCount}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

 </div>
 <s:if test="!'view'.equals(mode)">
  <input type="submit" value="保存" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left: 900px;"/>
 </form>
 </s:if>
 </div>
 
<script>
function dealPowerGrid(){
	if("${infrastructure.isPowerGrid}"==0){
		$("#notPowerGrid").attr("checked","checked");
	}else{
		$("#yesPowerGrid").attr("checked","checked");
	}
}
function dealBroadbandVillage(){
	if("${infrastructure.isBroadbandVillage}"==0){
		$("#notBroadbandVillage").attr("checked","checked");
	}else{
		$("#yesBroadbandVillage").attr("checked","checked");
	}
}

	$(document).ready(function(){
		dealPowerGrid();
		dealBroadbandVillage();
		
		$("#maintainFormOfInfrastructure").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data.id){
							 $("#commonServiceInfoId").val(data.id);
							 $.messageBox({message:"基础设施建设信息保存成功!"});
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