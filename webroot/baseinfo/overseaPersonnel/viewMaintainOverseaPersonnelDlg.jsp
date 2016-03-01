<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<body>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="overseaPersonnelOrgName">${overseaPersonnel.organization.orgName}</td>
    <td class="imagesTX" rowspan="5">
    <input id="_imgUrl" type="hidden" name="population.imgUrl" value="${overseaPersonnel.imgUrl}"/>
    <c:choose>
    <c:when test='${null!=overseaPersonnel.imgUrl && not empty overseaPersonnel.imgUrl}'>
		<img id="overseaPersonneImg" name="overseaPersonnel.imgUrl" src="" width="148px"/>
	</c:when>
	<c:otherwise>
		<img id="img" name="overseaPersonnel.imgUrl" src="${path }/resource/images/head.png" width="148px"/>
	</c:otherwise>
	</c:choose>
	</td>
  </tr>
  <tr>
    <td class="title"><label>英文名</label></td>
  	<td class="content"><span>${overseaPersonnel.englishName}</span></td>
  	<td class="title"><label>中文名</label></td>
  	<td class="content"><span>${overseaPersonnel.name}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>性别</label></td>
	<td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${overseaPersonnel.gender.id}" /></span></td>
	<td class="title"><label>出生日期</label></td>
	<td class="content"><span><fmt:formatDate value="${overseaPersonnel.birthday}" type="date" pattern="yyyy-MM-dd" /></span></td>		
  </tr>
   <tr>
  	<td class="title"><label>联系手机</label></td>
  	<td class="content"><span>${overseaPersonnel.mobileNumber }</span></td>
  	<td class="title"><label>固定手机</label></td>
  	<td class="content"><span>${overseaPersonnel.telephone}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>国籍</label></td>
  	<td class="content"><span>${overseaPersonnel.nationality}</span></td>
  	<td class="title"><label>证件种类</label></td>
  	<td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CERTIFICATE_TYPE" defaultValue="${overseaPersonnel.certificateType.id}" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>证件号码</label></td>
  	<td class="content"><span>${overseaPersonnel.certificateNo}</span></td>
  	<td class="title"><label>证件有效期</label></td>
  	<td class="content" colspan="2">从
	  	<fmt:formatDate value="${overseaPersonnel.certificateStrartDay}" type="date" pattern="yyyy-MM-dd" />
	  	到
	  	<fmt:formatDate value="${overseaPersonnel.certificateEndDay}" type="date" pattern="yyyy-MM-dd" />
	</td>
  </tr>
  <tr>
  	<td class="title"><label>邮箱</label></td>
  	<td class="content"><span>${overseaPersonnel.mail}</span></td>
  	<td class="title"><label>职业</label></td>
  	<td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PROFESSION_TYPE" defaultValue="${overseaPersonnel.profession.id}" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>抵达日期</label></td>
  	<td class="content"><span><fmt:formatDate value="${overseaPersonnel.arrivalTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
  	<td class="title" ><label>离开日期</label></td>
  	<td class="content" colspan="2"><span><fmt:formatDate value="${overseaPersonnel.leaveTime}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
 	<td class="title" ><label>工作单位或就读学校</label></td>
  	<td class="content" colspan="4"><span>${overseaPersonnel.workUnit }</span></td>
  </tr>
  <tr>
    <td class="title"><label>流入原因</label></td>
  	<td class="content" colspan="4"><span>${overseaPersonnel.inflowReason}</span></td>
  </tr>
  <s:if test="overseaPersonnel.logoutDetail.logout==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis">
  	 <tr>
        <td class="title"><label>注销时间</label></td>
	  	<td class="content" ><span><fmt:formatDate value="${overseaPersonnel.logoutDetail.logoutDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
        <td class="title"><label>注销原因</label></td>
        <td class="content" colspan="2"><span>${overseaPersonnel.logoutDetail.logoutReason}</span></td>
    </tr>
  </s:if>
  
  
   <tr>
  
<%--update by  fateson    简单的判断: 1 ：有房屋地址  0 ：没有房屋地址  null：未知 --%>
    <td class="title"><label><c:choose><c:when test="${overseaPersonnel.isHaveHouse=='true'}">常住地址</c:when><c:otherwise>有无住所</c:otherwise></c:choose></label></td>
    <td colspan='<c:choose><c:when test="${overseaPersonnel.isHaveHouse=='false'}">1</c:when><c:otherwise>4</c:otherwise></c:choose>' class="content">
    <span><c:choose><c:when test="${overseaPersonnel.isHaveHouse==null }">未知 </c:when><c:when test="${overseaPersonnel.isHaveHouse=='false' }">无住所</c:when><c:otherwise>${overseaPersonnel.currentAddress}</c:otherwise></c:choose></span>
    </td>
    <c:if test="${overseaPersonnel.isHaveHouse=='false'}">
    <td class="title"><label>无住所原因</label></td>
    <td colspan="2" class="content"><span>${overseaPersonnel.noHouseReason}</span></td>
    </c:if>
  </tr>
  
  
  
<!--   <tr> -->
<!--   	<td class="title"><label>户籍详址</label></td> -->
<%--   	<td class="content" colspan="4"><span>${overseaPersonnel.nativePlaceAddress}</span></td> --%>
<!--   </tr> -->
 

  <tr>
  	<td class="title"><label>备注</label></td>
  	<td class="content" colspan="5">${overseaPersonnel.remark}</td>
  </tr>
</table>
</body>
<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#overseaPersonneImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});

</script>	

