<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<body>
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="inflowingPopulationOrgName">${population.organization.orgName}</td>
    <td class="imagesTX"  rowspan="7">
    <c:choose>
    <c:when test='${null!=population.imgUrl && not empty population.imgUrl}'>
		<img id="inflowingPopImg" name="population.imgUrl" src="" width="148px"/>
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
    <td class="content"><span><fmt:formatDate value="${population.birthday}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${population.mobileNumber }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${population.telephone }</span></td>
  </tr>
  <tr>
  	<td class="title"><label>关注程度</label></td>
  	<td class="content" colspan="3"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" /></span></td>
  </tr>
  <tr>
  	 <td class="title"><label>是否有残疾证</label></td>
    <td class="content"><span> <c:choose><c:when test='${population.hadDisabilityCard||population.hadDisabilityCard==true }'>是</c:when><c:otherwise>否</c:otherwise></c:choose></span></td>
   <td class="title"><label>残疾证号</label></td>
   <td class="content"><span>${population.disabilityCardNo }</span></td>
  </tr>
  <tr>
    <td class="title"><label>残疾类别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DISABILITY_TYPE" defaultValue="${population.disabilityType.id}"/></span></td>
 	 <td class="title"><label>残疾等级</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DISABILITY_STATUS" defaultValue="${population.disability.id}"/></span></td>
  </tr>
 <%--   <tr>
    <td class="title"><label>残疾证号</label></td>
    <td class="content"><span>${population.disabilityCardNo }</span></td>
    <td class="title"><label>残疾等级</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DISABILITY_STATUS" defaultValue="${population.disability.id}" /></span></td>
  </tr> --%>
  <tr>
  <td class="title"><label>残疾时间</label></td>
    <td class="content"><span><fmt:formatDate value="${population.disabilityDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
  	<td class="title"><label>残疾原因</label></td>
    <td class="content" colspan="2"><span>${population.disabilityReason }</span></td>

  </tr>
   <tr>
    <td class="title"><label>技能特长</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SKILLS_AND_SPECIALITIES" defaultValue="${population.skillProfile.id}" /></span></td>
  	 <td class="title"><label>劳动能力</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" defaultValue="${population.workProfile.id}" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>监护人</label></td>
    <td class="content" colspan="4"><span>${population.guardianName}</span></td>
  </tr>
</table>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				if (null != $("#_imgUrl").val() && $("#_imgUrl").val() != "") {//添加随机数保证IE下的图片查看没问题
					$("#inflowingPopImg").attr("src",
							$("#_imgUrl").val() + "?r=" + Math.random());
				}
	});
</script>
