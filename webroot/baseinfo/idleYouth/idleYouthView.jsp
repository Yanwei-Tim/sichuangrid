<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<style>
.aaaa{width:98%;margin:0 1%;}
.aaaa li{float:left;}
</style>
<body>
<input type="hidden" id="isStayInSchool" name="" value="${population.stayInSchool}" />
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="idleYouthPopulationOrgName">${population.organization.orgName}</td>
    <td rowspan="7">
    <c:choose>
    <c:when test='${null!=population.imgUrl && not empty population.imgUrl}'>
		<img id="idleYouthImg" name="population.imgUrl" src="" width="148px"/>
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
  <tr >
     <td class="title" ><label>关注程度</label></td>
      <td class="content" colspan="1"><span ><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" /></span></td>
      <td class="title"><label>工作情况</label></td>
    <td class="content"><span>${population.workCondition }</span></td>
  </tr>
  <tr >
     <td class="title" ><label>监护人</label></td>
      <td class="content" colspan="1"><span >${population.guardianName}</span></td>
      <td class="title"><label>联系方式</label></td>
    <td class="content"><span>${population.guardianTelephone }</span></td>
  </tr>
  <tr >
     <td class="title" ><label>帮扶情况</label></td>
      <td class="content" colspan="3"><span ><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@HELPING_SITUATION_TYPE" defaultValue="${population.helpingSituation.id}" /></span></td>
   </tr>
  <c:choose>
  <c:when test='${null!=staffType && not empty staffType}'>
  	<tr >
     <td class="title" ><label>人员类型</label></td>
      <td class="content" colspan="3"><span >${staffType}</span></td>
   </tr>
  </c:when><c:otherwise>
  	<tr >
     <td class="title" ><label>人员类型</label></td>
      <td class="content" colspan="3">  
	  <c:forEach items="${population.staffType }" var="sta">
	  ${sta.displayName } ,	
	  </c:forEach>
  </td>
  </tr>
  </c:otherwise>
  </c:choose>
  <c:choose>
  <c:when test='${population.stayInSchool && not empty population.stayInSchool }'>
  	<tr >
		<td class="title" ><label>是否在校住宿</label></td>
		<td class="content"><span id="stayInSchool1"></span> </td>
		<td class="title" ><label>学校名称</label></td>
		<td class="content" colspan="2"><span >${population.schoolName}</span></td>
   </tr>
  </c:when><c:otherwise>
	<tr >
		<td class="title" ><label>是否在校住宿</label></td>
		<td class="content" colspan="4"><span id="stayInSchool2" ></span> </td>
	</tr>
  </c:otherwise>
  </c:choose>
</table>

</body>

<script>
function isStayInSchoolFormatter(){
	if($("#isStayInSchool").val() == true || $("#isStayInSchool").val() == 'true'){
		$("#stayInSchool1").html("是");
	} else {
		$("#stayInSchool2").html("否");
	}
}
$(document).ready(function(){
	isStayInSchoolFormatter();
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#idleYouthImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});

</script>
