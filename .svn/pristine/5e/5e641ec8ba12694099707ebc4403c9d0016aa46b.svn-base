<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input id="_imgUrl" type="hidden" name="population.imgUrl"
			value="${population.imgUrl}" />
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="druggyPopulationOrgName">${population.organization.orgName}</td>
    <td rowspan="7" class="imagesTX">
    <c:choose>
    <c:when test='${null!=population.imgUrl && not empty population.imgUrl}'>
		<img id="unemployedPeopleImg" name="population.imgUrl" src="" width="148px"/>
	</c:when>
	<c:otherwise>
		<img id="img" name="population.imgUrl" src="${resource_path}/resource/images/head.png" width="148px"/>
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
     <td class="title"><label>关注程度 </label></td>
	 <td class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" /></span></td>
  	  <td class="title"><label>人员类型 </label></td>
	 <td class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@UNEMPLOYEDPEOPLETYPE" defaultValue="${population.unemployedPeopleType.id}" /></span></td>
  </tr>
  <tr>
	 <td class="title"><label>登记证号 </label></td>
	 <td  class="content"><span id="">${population.registerNumber}</span></td>
	  <td class="title"><label>失业时间</label></td>
	 <td class="content"><span id=""><fmt:formatDate value="${population.unemploymentDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
     <td class="title"><label>失业原因</label></td>
	 <td  class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@UNEMPLOYMENTREASON" defaultValue="${population.unemploymentReason.id}" /></span></td>
	  <td class="title"><label>参加工作时间</label></td>
	 <td class="content" colspan="2"><span id=""><fmt:formatDate value="${population.upEnterWorkDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
	 <td class="title"><label>原单位名称</label></td>
	 <td class="content"><span id="">${population.originalWorkUnit}</span></td>
	 <td class="title"><label>原单位类型 </label></td>
	 <td class="content" colspan="2"><span id="">${population.originalWorkUnitType}</span></td>
  </tr>
   <tr>
   	 <td class="title"><label>职称</label></td>
   	 <td  class="content"><span id="">${population.title}</span></td>
   	 <td class="title"><label>技术等级</label></td>
	 <td  colspan="2" class="content"><span id=""><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@TECHNOLOGYLEVEL" defaultValue="${population.technologyLevel.id}" /></span></td>

  </tr>
  <tr>
   	 <td class="title"><label>技能特长</label></td>
   	 <td colspan="4" class="content" ><span id="">${population.specialtySkills}</span></td>
  </tr>

	<tr >
     <td class="title" ><label>拟就业意向</label></td>
	 <td class="content" colspan="4"  >
	 <c:choose>
		 <c:when test='${null!=intentions && not empty intentions}'>
		<span id="">${intentions}</span> </c:when>
		<c:otherwise>
			<span>
		  <c:forEach items="${population.employmentIntention }" var="eml">
		  ${eml.displayName } ,	
		  </c:forEach>
		  </span>
		</c:otherwise>
	  </c:choose>
	  </td>

  </tr>
  

  <tr >
     <td class="title" ><label>拟参加培训意向</label></td>
	 <td class="content" colspan="4"  >
	 <c:choose>
	 <c:when test='${null!=trainings && not empty trainings}'>
	 <span id="">${trainings}</span></c:when><c:otherwise><span>
	 <c:forEach items="${population.trainingIntention }" var="train">
	  ${train.displayName } ,	
	  </c:forEach>
	  </span>
	  </c:otherwise>
	  </c:choose></td>
	 <%-- <pop:PropertyDictMultiCheckbox name="trainingIntentionIds" column="3"
		domainName="@com.tianque.domain.property.PropertyTypes@TRAININGINTENTION"
		listVal="${population.trainingIntention}" readOnly="true"/> --%>
  </tr>

  <tr>
    <td class="title"><label>参加过的培训项目及时间</label></td>
    <td class="content" colspan="4"><span>${population.participatedPrograms}</span></td>
  </tr>
</table>
<script type="text/javascript">
	$(document).ready(
			function() {
				if (null != $("#_imgUrl").val() && $("#_imgUrl").val() != "") {//添加随机数保证IE下的图片查看没问题
					$("#unemployedPeopleImg").attr("src",
							$("#_imgUrl").val() + "?r=" + Math.random());
				}
	});
</script>