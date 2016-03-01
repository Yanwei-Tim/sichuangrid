<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
		<input id="isTrestValue"	type="hidden" name="" value="${population.treat }" />
		<input id="isUndergoTreatment"	type="hidden" name="" value="${population.undergoTreatment }" />
		<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<body>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="druggyPopulationOrgName">${population.organization.orgName}</td>
    <td rowspan="7">
    <c:choose>
    <c:when test='${null!=population.imgUrl && not empty population.imgUrl}'>
		<img id="druggyPopulationImgs" name="population.imgUrl" src="" width="148px"/>
	</c:when>
	<c:otherwise>
		<img id="img" name="population.imgUrl" src="${path }/resource/images/head.png" width="148px"/>
	</c:otherwise>
	</c:choose>
	</td>
  </tr>
  <tr>
    <td class="title"><label>姓 名</label></td>
    <td class="content"><span>${population.name}</span></td>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span>${population.idCardNo}</span></td>
  </tr>
  <tr>
    <td class="title"><label>曾用名/别名</label></td>
    <td class="content"><span>${population.usedName}</span></td>
    <td class="title"><label>性别</label></td>
   <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${population.gender.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>民族</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${population.nation.id}" /></span></td>
    <td class="title"><label>出生日期</label></td>
    <td class="content"><span><fmt:formatDate value="${population.birthday}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${population.mobileNumber }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${population.telephone }</span></td>
  </tr>


  <tr>
	 <td class="title"><label>危险程度</label></td>
	 <td colspan="3" class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@MENTALPATIENT_DANGEROUS_LEVEL" defaultValue="${population.dangerLevel.id}"/></span></td>
  </tr>
  <tr>
	 <td class="title"><label>患病名称 </label></td>
	 <td class="content"><span >${population.psychosisName}</span></td>
	 <td class="title"><label>精神病类型</label></td>
	 <td class="content"><span><pop:PropertyDictViewTag defaultValue="${population.psychosisType.id }" name="@com.tianque.domain.property.PropertyTypes@PSYCHOSIS_TYPES"/></span></td>
  </tr>
  <tr>
  	 <td class="title"><label>是否接受过治疗</label></td>
	 <td colspan="4" class="content"><span id="tagIsTreated"></span></td>
  </tr>
 <tr>
  	 <td class="title"><label>康复机构</label></td>
	 <td colspan="4" class="content"><span>${population.cureDepartment }</span></td>
 </tr>
 
 <tr>
  	 <td class="title"><label>目前是否在接受治疗</label></td>
	 <td colspan="4" class="content"><span id="tagIsTreating"></span></td>
 </tr>
 
 <tr>
  	 <td class="title"><label>发病时间</label></td>
	 <td colspan="1" class="content"><span><fmt:formatDate value="${population.diseaseTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
	 <td class="title"><label>治疗状态</label></td>
	 <td colspan="2" class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@TREATMENTSTATE" defaultValue="${population.treatmentState.id}"/></span></td>
  </tr>
  <tr>
  	  <td class="title"><label>康复时间</label></td>
	 <td colspan="4" class="content"><span><span><fmt:formatDate value="${population.recoveryTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
</table>

</body>

<script>
$(document).ready(function(){
	treatFormatter();
	treatingFormatter();
	
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#druggyPopulationImgs").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});

function treatFormatter(){
	var str = "";
	if($("#isTrestValue").val()=="true"||$("#adanonValue").val()==true)
		str += "是";
	if($("#isTrestValue").val() =="false"||$("#adanonValue").val()==false)
		str += "否";
	$("#tagIsTreated").html(str);
}

function treatingFormatter(){
	var str="";
	if($("#isUndergoTreatment").val()=="true" || $("#isUndergoTreatment").val()==true){
		str += "是";
	}
	if($("#isUndergoTreatment").val()=="false" || $("#isUndergoTreatment").val()==false){
		str += "否";
	}
	$("#tagIsTreating").html(str);
}

</script>
