<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input id="adanonValue"	type="hidden" name="" value="${population.adanon}" />
<input id="isUndergoTreatmentValue"	type="hidden" name="" value="${population.undergoTreatment}" />
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="druggyPopulationOrgName">${population.organization.orgName}</td>
    <td rowspan="7">
    <c:choose>
	    <c:when test="${null!=population.imgUrl && not empty population.imgUrl}">
	     	<img id="druggyImg" name="population.imgUrl" src="" width="148px"/>
	    </c:when>
	    <c:otherwise>
	    	<img id="img" name="population.imgUrl" src="${resource_path }/resource/images/head.png" width="148px"/>
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
    <td class="content"><span><s:date name="population.birthday" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${population.mobileNumber }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${population.telephone }</span></td>
  </tr>
   <tr>
   	  <td class="title"><label>关注程度</label></td>
	 <td  colspan="3" class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}"/></span></td>
  </tr>
  <tr>
     <td class="title"><label>首吸时间 </label></td>
	 <td class="content"><span id=""><s:date name="population.drugFristDate" format="yyyy-MM-dd" /></span></td>
	 <td class="title"><label>查获日期 </label></td>
	 <td class="content"><span id=""><s:date name="population.ferretOutDate" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
   	 <td class="title"><label>吸毒状态</label></td>
	 <td class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CONDITION" defaultValue="${population.detoxicateCondition.id}" /></span></td>
     <td class="title"><label>吸毒原因</label></td>
	 <td class="content" colspan="2"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DRUG_REASON" defaultValue="${population.drugReason.id}" /></span></td>
  </tr>
  <tr>
   	 <td class="title"><label>戒毒情况</label></td>
	 <td colspan="4" class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CASE" defaultValue="${population.detoxicateCase.id}" /></span></td>
	 <%-- 
	 <td class="title"><label>毒品来源</label></td>
	 <td colspan="2" class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DRUG_SOURCE" defaultValue="${population.drugSource.id}" /></span></td>
	  --%>
  </tr>
   <tr>
   	 <td class="title"><label>管控现状 </label></td>
	 <td colspan="4" class="content"><span id="">${population.controlActuality}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>滥用毒品种类</label></td>
	 <td colspan="4" class="content"><span id="">${population.drugType}</span></td>
  </tr>
   <tr>
   	 <td class="title"><label>是否服美沙酮戒毒</label></td>
   	 <td colspan="8" class="content"><span id="tagAdanon"></span></td>
  </tr>
  <tr>
   	 <td class="title"><label>目前是否在接受康复治疗</label></td>
   	 <td colspan="8" class="content"><span id="isRehabilitationTherapy"></span></td>
  </tr>
</table>

<script>
$(document).ready(function(){
	adanonFormatter();
	isRehabilitationTherapy();
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#druggyImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		//$(".shadow").show();
	}
});

function adanonFormatter(){
	var str = "";
	if($("#adanonValue").val()=="true"||$("#adanonValue").val()==true)
		str += "是";
	if($("#adanonValue").val() =="false"||$("#adanonValue").val()==false)
		str += "否";
	$("#tagAdanon").html(str);
}

function isRehabilitationTherapy(){
	var str = "";
	var flg = $("#isUndergoTreatmentValue").val();
	if(flg=="true"||flg==true)
		str += "是";
	if(flg =="false"||flg==false)
		str += "否";
	$("#isRehabilitationTherapy").html(str);
}

</script>
