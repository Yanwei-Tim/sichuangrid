<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="container container_24">
<input type="hidden" name="mode" value="${param.mode}"/>
<input type="hidden" name="companyPlaceId" id="companyPlaceId" value="${companyPlaceId}"/>
<input type="hidden" name="modulKey" id="modulKey" value="${modeName}"/>
<input type="hidden" name="cid" id="cid" value="${id}"/>
<input type="hidden" name="type" id="type" value="${modulKey}"/>
<pop:JugePermissionTag ename="newWhetherProductionKey">
<div id="newWhetherProductionKey" style="display: none">
		<table width="200" class="tablelist">
		  <tr>
		    <td class="title" colspan="2">
		    	<label>
		    		<input type="hidden" id="proKeyIsKeyTypeValue" value="${companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.isKeyType}"/>
		    		<input type="checkbox" disabled="disabled" id="proKeyIsKeyType" class="typeInfoContext" fateson="KEYPOPULATION" contextdiv="keyPopulationContext"/>
		    		是否安全生产重点
		    	</label>
		    </td>
		  </tr>
		  <tr>
		     <td class="title"><label>隐患情况</label></td>
			 <td class="content"><span id="">${companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.hiddangerInfo}</span></td>
		  </tr>
		   <tr>
		     <td class="title"><label>隐患整改情况</label></td>
			 <td class="content"><span id="">${companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.correctionInfo}</span></td>
		  </tr>
		  <tr>
		    <td class="title"><label>附件</label>
		    	<select id="proKeyAttachFiles" name="companyPlaceBusinessVO.proKeyAttachFiles" multiple="multiple" style="width:200px;display:none"></select>
		    </td>
		    <td class="content" id="custom-queue_proKey"></td>
		  </tr>
		</table>
	<hr width="99%" style="margin-top: 10px;"></hr>
</div>
</pop:JugePermissionTag>

<pop:JugePermissionTag ename="newWhetherFireSafetyKey">
<div id="newWhetherFireSafetyKey" style="display: none" >
	<table width="200" class="tablelist">
		  <tr>
		    <td class="title" colspan="2">
		    	<label>
			    	<input type="hidden" id="fireSafetyKeyIsKeyTypeValue" value="${companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.isKeyType}"/>
		    		<input type="checkbox" disabled="disabled" id="fireSafetyKeyIsKeyType" name="companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.isKeyType" class="typeInfoContext" fateson="KEYPOPULATION" contextdiv="keyPopulationContext"/>
		    		是否消防安全重点
		    	</label>
		    </td>
		  </tr>
		  <tr>
		     <td class="title"><label>隐患情况</label></td>
			 <td class="content"><span id="">${companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.hiddangerInfo}</span></td>
		  </tr>
		   <tr>
		     <td class="title"><label>隐患整改情况</label></td>
			 <td class="content"><span id="">${companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.correctionInfo}</span></td>
		  </tr>
		  <tr>
		    <td class="title"><label>附件</label>
				<select id="fireSafetyKeyAttachFiles" name="companyPlaceBusinessVO.fireSafetyKeyAttachFiles" multiple="multiple" style="width:200px;display:none"></select>
		    </td>
		    <td class="content" id="custom-queue_fireSafetyKey"></td>
		  </tr>
	</table>
<hr width="99%" style="margin-top: 10px;"></hr>
</div>
</pop:JugePermissionTag>

<pop:JugePermissionTag ename="newWhetherSecurityKey">
<div id="newWhetherSecurityKey" style="display: none">
	<table width="200" class="tablelist">
	  <tr>
	    <td class="title" colspan="4">
	    	<label>		
		    	<input type="hidden" id="securityKeyIsKeyTypeValue" value="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.isKeyType}"/>
	    		<input type="checkbox" disabled="disabled" name="companyPlaceBusinessVO.securityKeyPlaceBusiness.isKeyType" id="securityKeyIsKeyType" class="typeInfoContext" fateson="KEYPOPULATION" contextdiv="keyPopulationContext"/>
				是否治安重点
			</label>
		</td>
	  </tr>
	   <tr>
	    <td class="title"><label>挂牌整治</label></td>
	    <td class="content"><span id="renovateType"></span></td>
	    <td class="title"><label>挂牌等级</label></td>
	    <td class="content"><span id="listedLeveType"></span></td>
	  </tr>
	  <tr>
	     <td class="title"><label>隐患情况</label></td>
		 <td colspan="3" class="content"><span id="">${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.hiddangerInfo}</span></td>
	  </tr>
	   <tr>
	    <td class="title"><label>整改时限</label></td>
	    <td class="content">
	    	<fmt:formatDate value="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.correctionBeiginDate}" type="date" pattern="yyyy-MM-dd" /> 至  <fmt:formatDate value="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.correctionEndDate}" type="date" pattern="yyyy-MM-dd" />
	    </td>
	    <td class="title"><label>效果评估</label></td>
	    <td class="content">
	    	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@EFFECT_EVALUATION_TYPE" defaultValue="${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.effectassessment.id}" /></span>
		</td>
	  </tr>
	  <tr>
	     <td class="title"><label>隐患整改情况</label></td>
		 <td colspan="3" class="content"><span id="">${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.correctionInfo}</span></td>
	  </tr>
	  <tr>
	    <td class="title"><label>附件</label>
	    	<select id="securityKeyChangeAttachFiles" name="companyPlaceBusinessVO.securityKeyChangeAttachFiles" multiple="multiple" style="width:200px;display:none"></select>
	    </td>
	    <td colspan="3" class="content" id="custom-queue_securityKey"></td>
	  </tr>
	</table>
<hr width="99%" style="margin-top: 10px;"></hr>
</div>
</pop:JugePermissionTag>

<pop:JugePermissionTag ename="newWhetherPollutionSource">
<div id="newWhetherPollutionSource" style="display: none">
	<table width="200" class="tablelist">
	  <tr>
	    <td class="title" colspan="2">
	    	<label>
		    	<input type="hidden" id="pollSourceIsKeyTypeValue" value="${companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.isKeyType}"/>
	    		<input type="checkbox" disabled="disabled" name="companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.isKeyType" id="pollSourceIsKeyType" class="typeInfoContext" fateson="KEYPOPULATION" contextdiv="keyPopulationContext"/>
	    		是否污染源
	    	</label>
	    </td>
	  </tr>
	  <tr>
	     <td class="title"><label>污染类型</label></td>
		 <td class="content">
		 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POLLUTION_TYPE" defaultValue="${companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.pollutionType.id}" /></span>
		 </td>
	  </tr>
	   <tr>
	     <td class="title"><label>污染情况</label></td>
		 <td class="content"><span id="">${companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.pollutionInfo}</span></td>
	  </tr>
	  <tr>
	    <td class="title"><label>附件</label>
	    	<select id="pollSourceAttachFiles" name="companyPlaceBusinessVO.pollSourceAttachFiles" multiple="multiple" style="width:200px;display:none"></select>
	    </td>
	    <td class="content" id="custom-queue_pollSource"></td>
	  </tr>
	</table>
</div>
<pop:JugePermissionTag ename="contaminationInfoManagement">
	<div id="contaminationInfoDiv" style="display: none">
		<table width="200" class="tablelist">
			<tr>
		     <td class="title"><label>污染物情况</label></td>
			 <td class="content" colspan="3">
			 	<label>
		    		<input type="hidden" id="isWasteWaterValue" value="${companyPlaceBusinessVO.contaminationInfo.isWasteWater}"/>
	    			<input type="checkbox" disabled="disabled" name="companyPlaceBusinessVO.contaminationInfo.isWasteWater" id="isWasteWater" class="typeInfoContext"/>
	    			废水
		    	</label>
		    	<label>
			    	<input type="hidden" id="isWasteGasValue" value="${companyPlaceBusinessVO.contaminationInfo.isWasteGas}"/>
		    		<input type="checkbox" disabled="disabled" name="companyPlaceBusinessVO.contaminationInfo.isWasteGas" id="isWasteGas" class="typeInfoContext"/>
		    		废气
		    	</label>
		    	<label>
			    	<input type="hidden" id="isWasteSolidValue" value="${companyPlaceBusinessVO.contaminationInfo.isWasteSolid}"/>
		    		<input type="checkbox" disabled="disabled" name="companyPlaceBusinessVO.contaminationInfo.isWasteSolid" id="isWasteSolid" class="typeInfoContext"/>
		    		固废
		    	</label>
		    	<label>
			    	<input type="hidden" id="isRadioactionValue" value="${companyPlaceBusinessVO.contaminationInfo.isRadioaction}"/>
		    		<input type="checkbox" disabled="disabled" name="companyPlaceBusinessVO.contaminationInfo.isRadioaction" id="isRadioaction" class="typeInfoContext" />
		    		放射性
		    	</label>
		    	<label>
			    	<input type="hidden" id="isNoiseValue" value="${companyPlaceBusinessVO.contaminationInfo.isNoise}"/>
		    		<input type="checkbox" disabled="disabled" name="companyPlaceBusinessVO.contaminationInfo.isNoise" id="isNoise" class="typeInfoContext" />
		    		噪音
		    	</label>
		    	<label>
			    	<input type="hidden" id="isHeavyMetalValue" value="${companyPlaceBusinessVO.contaminationInfo.isHeavyMetal}"/>
		    		<input type="checkbox" disabled="disabled" name="companyPlaceBusinessVO.contaminationInfo.isHeavyMetal" id="isHeavyMetal" class="typeInfoContext" />
		    		重金属
		    	</label>
			 </td>
		  </tr>
		  <tr id="wasteWaterDiv" style="display:none;">
		  	 <td class="title"><label>废水污染物名称</label></td>
		 	 <td class="content"><span id="">${companyPlaceBusinessVO.contaminationInfo.wasteWaterName}</span></td>
		     <td class="title"><label>废水类型</label></td>
			 <td class="content">
			 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@WASTEWATER_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.wasteWaterType.id}" /></span>
			 </td>
	  	 </tr>
	  	 
	  	 <tr id="wasteGasDiv" style="display: none">
		  	 <td class="title"><label>废气污染物名称</label></td>
		 	 <td class="content"><span id="">${companyPlaceBusinessVO.contaminationInfo.wasteGasName}</span></td>
		     <td class="title"><label>废气类型</label></td>
			 <td class="content">
			 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@WASTEGAS_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.wasteGasType.id}" /></span>
			 </td>
	  	 </tr>
	  	 <tr id="wasteSolidDiv" style="display: none">
		  	 <td class="title"><label>固废污染物名称</label></td>
		 	 <td class="content" ><span id="">${companyPlaceBusinessVO.contaminationInfo.wasteSolidName}</span></td>
		     <td class="title"><label>固废类型</label></td>
			 <td class="content">
			 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@WASTESOLID_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.wasteSolidType.id}" /></span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv" style="display: none">
		  	 <td class="title"><label>放射性废物名称</label></td>
		 	 <td class="content"><span id="">${companyPlaceBusinessVO.contaminationInfo.radioactionName}</span></td>
		     <td class="title"><label>放射物类型</label></td>
			 <td class="content">
			 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RADIOACTION_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.radioactionType.id}" /></span>
			 </td>
	  	 </tr>
	  	 <tr id="noiseDiv" style="display: none">
		  	 <td class="title"><label>噪音测点名称</label></td>
		 	 <td class="content"><span id="">${companyPlaceBusinessVO.contaminationInfo.noiseName}</span></td>
		  	 <td class="title"><label>噪音类型</label></td>
		 	 <td class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NOISE_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.noiseType.id}" /></span></td>
	  	 </tr>
	  	 <tr id="noiseNatureDiv" style="display: none">
	  	 	<td class="title"><label>噪声源性质</label></td>
			 <td class="content" colspan="3">
			 	<span id="">${companyPlaceBusinessVO.contaminationInfo.noiseNature}</span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>所属街办</label></td>
		 	 <td class="content"><span id="">${companyPlaceBusinessVO.contaminationInfo.townOrg.orgName}</span></td>
		     <td class="title"><label>所属流域</label></td>
			 <td class="content">
			 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BASIN" defaultValue="${companyPlaceBusinessVO.contaminationInfo.basin.id}"/></span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>产业类型</label></td>
		 	 <td class="content">			 	
		 	 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INDUSTRY_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.industryType.id}"/></span>
		 	 </td>
		     <td class="title"><label>行业类别</label></td>
			 <td class="content">
				<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@TRADE_TYPE_SMALL" defaultValue="${companyPlaceBusinessVO.contaminationInfo.tradeTypeSmall.id}"/></span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>营业面积</label></td>
		 	 <td class="content">			 	
		 	 	<span id="">${companyPlaceBusinessVO.contaminationInfo.area}</span>
		 	 </td>
		     <td class="title"><label>是否环评</label></td>
			 <td class="content">
				<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ASSESSMENT_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.assessmentType.id}"/></span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>开业日期</label></td>
		 	 <td class="content">			 	
		 	 	<span id=""><fmt:formatDate value="${companyPlaceBusinessVO.contaminationInfo.startBusinessDate}" type="date" pattern="yyyy-MM-dd" /></span>
		 	 </td>
		     <td class="title"><label>是否居民楼下</label></td>
			 <td class="content">
				<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LOWASICS_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.lowAsicsType.id}"/></span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>关注程度</label></td>
		 	 <td class="content">			 	
		 	 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CONCERN_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.concernType.id}"/></span>
		 	 </td>
		     <td class="title"><label>隶属关系</label></td>
			 <td class="content">
				<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RELATIONSHIP_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.relationshipType.id}"/></span>
			 </td>
	  	 </tr>
	  	<tr id="radioactionDiv">
		  	 <td class="title"><label>单位类别</label></td>
		 	 <td class="content">			 	
		 	 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@UNIT_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.unitType.id}"/></span>
		 	 </td>
		     <td class="title"><label>企业规模</label></td>
			 <td class="content">
				<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SCALE_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.scaleType.id}"/></span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>重点行业</label></td>
		 	 <td class="content">			 	
		 	 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@KEYINDUSTRY_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.keyIndustryType.id}"/></span>
		 	 </td>
		     <td class="title"><label>监管类别</label></td>
			 <td class="content">
				<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SUPERVISE_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.superviseType.id}"/></span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>污染源类别</label></td>
		 	 <td class="content">			 	
		 	 	<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CONTAMINATION_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.contaminationType.id}"/></span>
		 	 </td>
		     <td class="title"><label>行政许可</label></td>
			 <td class="content">
				<span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LICENSING_TYPE" defaultValue="${companyPlaceBusinessVO.contaminationInfo.licensingType.id}"/></span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>行政处罚时间</label></td>
		 	 <td class="content">			 	
		 	 	<span id=""><fmt:formatDate value="${companyPlaceBusinessVO.contaminationInfo.penaltyDate}" type="date" pattern="yyyy-MM-dd" /></span>
		 	 </td>
		     <td class="title"><label>行政处罚金额</label></td>
			 <td class="content">
				<span id="">${companyPlaceBusinessVO.contaminationInfo.penaltyAmount}</span>
			 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>行政处罚原因</label></td>
		 	 <td class="content" colspan="3">			 	
		 	 	<span id="">${companyPlaceBusinessVO.contaminationInfo.penaltyReason}</span>
		 	 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>行政处罚执行情况</label></td>
		 	 <td class="content" colspan="3">			 	
		 	 	<span id="">${companyPlaceBusinessVO.contaminationInfo.penaltyExecute}</span>
		 	 </td>
	  	 </tr>
	  	 <tr id="radioactionDiv">
		  	 <td class="title"><label>中心位置</label></td>
		 	 <td class="content" colspan="3">			 	
		 	 	<span id="openLayersMap"></span>
		 	 </td>
	  	 </tr>
		</table> 
	</div>
</pop:JugePermissionTag>
</pop:JugePermissionTag>
</div>


<script type="text/javascript">
$(function(){
	$(".form-txt").attr("disabled","disabled");
 	$(".shadow").hide();
 	$("#fileToUpload").attr("disabled","disabled");
 	$("#deleteHeaderImage").hide();
 	$(".bgc").hide();
 	if($(".ui-button-text").text()=="保存关闭"){
 		$(".ui-button-text").eq(0).parent().remove();
 	}
 	
 	fillBuniess();
	fillFile();
	fillKeyPlaceByIdAndType();
});

function fillBuniess(){
	var modulKey = $("#modulKey").val();
	if(modulKey == 'NEWSAFETYPRODUCTIONKEY'){
		$("#proKeyIsKeyType").attr("checked", true);
		$("#newWhetherProductionKey").css('display','block');
	}else if(modulKey == 'NEWFIRESAFETYKEY'){
		$("#fireSafetyKeyIsKeyType").attr("checked", true);
		$("#newWhetherFireSafetyKey").css('display','block');
	}else if(modulKey == 'NEWSECURITYKEY'){
		$("#securityKeyIsKeyType").attr("checked", true);
		$("#newWhetherSecurityKey").css('display','block');
	}else if(modulKey == 'POLLUTIONSOURCE'){
		$("#pollSourceIsKeyType").attr("checked", true);
		$("#newWhetherPollutionSource").css('display','block');
		<pop:JugePermissionTag ename="contaminationInfoManagement">
			$("#contaminationInfoDiv").css('display','block');
			fillcontaminationInfo();
		</pop:JugePermissionTag>
	}else{
		if($("#proKeyIsKeyTypeValue").val() != ''){
			$("#proKeyIsKeyType").attr("checked", true);
			$("#newWhetherProductionKey").css('display','block');
		}
		if($("#fireSafetyKeyIsKeyTypeValue").val() != ''){
			$("#fireSafetyKeyIsKeyType").attr("checked", true);
			$("#newWhetherFireSafetyKey").css('display','block');
		}
		if($("#securityKeyIsKeyTypeValue").val() != ''){
			$("#securityKeyIsKeyType").attr("checked", true);
			$("#newWhetherSecurityKey").css('display','block');
		}
		if($("#pollSourceIsKeyTypeValue").val() != ''){
			$("#pollSourceIsKeyType").attr("checked", true);
			$("#newWhetherPollutionSource").css('display','block');
			<pop:JugePermissionTag ename="contaminationInfoManagement">
				$("#contaminationInfoDiv").css('display','block');
				fillcontaminationInfo();
			</pop:JugePermissionTag>
		}
	}
}

function fillcontaminationInfo(){
	if($("#isWasteWaterValue").val() != ''){
		$("#isWasteWater").attr("checked", true);
		$("#wasteWaterDiv").show();
	}
	if($("#isWasteSolidValue").val() != ''){
		$("#isWasteSolid").attr("checked", true);
		$("#wasteSolidDiv").show();
	}
	if($("#isRadioactionValue").val() != ''){
		$("#isRadioaction").attr("checked", true);
		$("#radioactionDiv").show();
	}
	if($("#isNoiseValue").val() != ''){
		$("#isNoise").attr("checked", true);
		$("#noiseDiv").show();
		$("#noiseNatureDiv").show();
	}
	if($("#isWasteGasValue").val() != ''){
		$("#isWasteGas").attr("checked", true);
		$("#wasteGasDiv").show();
	}
	if($("#isHeavyMetalValue").val() != ''){
		$("#isHeavyMetal").attr("checked", true);
	}
}

var _imgUrl = $("#_imgUrl").val();
if(null!=_imgUrl && _imgUrl!=""){
	$("#headerImg").attr("src",_imgUrl+"?r="+Math.random());
	$(".shadow").show();
}

function fillFile(){
	<s:if test="companyPlaceBusinessVO!=null">
		<s:if test="companyPlaceBusinessVO.proKeyCompanyPlaceBusiness !=null && companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.placeAnnexFiles!=null  && companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.placeAnnexFiles.size>0">
			<s:iterator value="companyPlaceBusinessVO.proKeyCompanyPlaceBusiness.placeAnnexFiles">
		        $("#custom-queue_proKey").addUploadFileValue({
			        id:"<s:property value='id'/>",
			        filename:"<s:property value='fileName'/>",
			    	link:"${path}/baseinfo/companyPlaceBusinessManage/downloadCompanyPlaceAnnexAttachFile.action?companyPlaceAnnex.id=<s:property value='id'/>",
			        showCloseButton:false
		        });
		        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#proKeyAttachFiles"));
		     </s:iterator>
	     </s:if>
		<s:if test="companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness !=null && companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.placeAnnexFiles!=null  && companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.placeAnnexFiles.size>0">
			<s:iterator value="companyPlaceBusinessVO.fireSafetyKeyCompanyPlaceBusiness.placeAnnexFiles">
		        $("#custom-queue_fireSafetyKey").addUploadFileValue({
			        id:"<s:property value='id'/>",
			        filename:"<s:property value='fileName'/>",
			    	link:"${path}/baseinfo/companyPlaceBusinessManage/downloadCompanyPlaceAnnexAttachFile.action?companyPlaceAnnex.id=<s:property value='id'/>",
			    	showCloseButton:false
		        });
		        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#fireSafetyKeyAttachFiles"));
		     </s:iterator>
	     </s:if>
		<s:if test="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness !=null && companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.placeAnnexFiles!=null  && companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.placeAnnexFiles.size>0">
			<s:iterator value="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.placeAnnexFiles">
		        $("#custom-queue_securityKey").addUploadFileValue({
			        id:"<s:property value='id'/>",
			        filename:"<s:property value='fileName'/>",
			    	link:"${path}/baseinfo/companyPlaceBusinessManage/downloadCompanyPlaceAnnexAttachFile.action?companyPlaceAnnex.id=<s:property value='id'/>",
			    	showCloseButton:false
				});
		        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#securityKeyAttachFiles"));
		     </s:iterator>
	     </s:if>
		<s:if test="companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness !=null && companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.placeAnnexFiles!=null  && companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.placeAnnexFiles.size>0">
			<s:iterator value="companyPlaceBusinessVO.pollSourceCompanyPlaceBusiness.placeAnnexFiles">
		        $("#custom-queue_pollSource").addUploadFileValue({
			        id:"<s:property value='id'/>",
			        filename:"<s:property value='fileName'/>",
			    	link:"${path}/baseinfo/companyPlaceBusinessManage/downloadCompanyPlaceAnnexAttachFile.action?companyPlaceAnnex.id=<s:property value='id'/>",
			    	showCloseButton:false
				});
		        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#pollSourceAttachFiles"));
		     </s:iterator>
	     </s:if>
	</s:if>
}

<s:if test="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.listedCorrection != null">
renovateTypeFormatter();
function renovateTypeFormatter(){
	var renovateType = ${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.listedCorrection};
	var renovateTypeName = "";
	if(renovateType=='<s:property value="@com.tianque.domain.property.RenovateType@RENOVATE_TYPE_PROVINCE"/>'){
		renovateTypeName = '<s:property  escape="false" value="@com.tianque.domain.property.RenovateType@PROVINCE_NAME"/>';
	}else if(renovateType=='<s:property value="@com.tianque.domain.property.RenovateType@RENOVATE_TYPE_CITY"/>'){
		renovateTypeName = '<s:property  escape="false"  value="@com.tianque.domain.property.RenovateType@CITY_NAME"/>';
	}else if(renovateType=='<s:property value="@com.tianque.domain.property.RenovateType@RENOVATE_TYPE_COUNTY"/>'){
		renovateTypeName = '<s:property  escape="false"  value="@com.tianque.domain.property.RenovateType@COUNTY_NAME"/>';
	}
	$("#renovateType").text(renovateTypeName);
}
</s:if>
<s:if test="companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.listedLeve != null">
listedLeveFormatter();
function listedLeveFormatter(){
	var listedLeveType = ${companyPlaceBusinessVO.securityKeyCompanyPlaceBusiness.listedLeve};
	var listedLeveTypeName = "";
	if(listedLeveType=='<s:property value="@com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_RED_VALUE"/>'){
		listedLeveTypeName = '<s:property  escape="false" value="@com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_RED_NAME"/>';
	}else if(listedLeveType=='<s:property value="@com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_YELLOW_VALUE"/>'){
		listedLeveTypeName = '<s:property  escape="false"  value="@com.tianque.baseInfo.companyPlace.constant.ListingType@LISTING_YELLOW_NAME"/>';
	}
	$("#listedLeveType").text(listedLeveTypeName);
}
</s:if>

//根据ID和TYPE查询对应的keyplace信息
function fillKeyPlaceByIdAndType(){
	$.ajax({
		url:PATH+"/baseinfo/companyPlaceBusinessManage/getKeyPlaceByIdAndType.action",
		data:{
			"id": $("#cid").val(),
			"type": "<s:property value='@com.tianque.baseInfo.companyPlace.constacts.ModulTypes@POLLUTIONSOURCE_KEY'/>"
		}, 
		success:function(responseData){
			if(responseData != null && responseData.openLayersMapInfo != null){
				$("#openLayersMap").html(responseData.openLayersMapInfo.centerLon+" , "+responseData.openLayersMapInfo.centerLat);
			}
		}
			
	});
}
</script>