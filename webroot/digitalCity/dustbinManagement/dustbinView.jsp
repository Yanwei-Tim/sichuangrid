<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="dangerousChemicalsUnitOrgName">${location.organization.orgName}</td>
     <td class="imagesTX" rowspan="7">
     <input id="_imgUrl" type="hidden" name="location.imgUrl" value="${location.imgUrl}"/>
     <c:choose>
	    <c:when test="${null!=location.imgUrl && not empty (location.imgUrl) }">
			<img id="locatigUrl" name="location.imgUrl" src="${path }${location.imgUrl}?11" width="148px"  />
		</c:when>
		<c:otherwise>
			<img id="img" name="location.imgUrl" src="${path }/resource/images/locationHead.png?11" width="148px"  />
		</c:otherwise>
     </c:choose>
	</td>
  </tr>
  <tr>
    <td class="title"><label>部件标识码</label></td>
    <td class="content">${location.dustbinCode}</td>
    <td class="title">
    <c:if test="${true==location.hasVideo}">
    	<label >是否有视频流</label>
    </c:if>
    </td>
    <td class="content"><span ><c:if test="${true==location.hasVideo }">是</c:if></td>
  </tr>
  <tr>
    <td class="title"><label>部件类型</label></td>
    <td class="content"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_TYPE" defaultValue="${location.partType.id}" /></td>
    <td class="title"><label>部件名称</label></td>
    <td class="content"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME" defaultValue="${location.partName.id}" /></td>
  </tr>
  <tr>
    <td class="title"><label>地址</label></td>
    <td class="content" colspan="3">${location.address}</td>
  </tr>
  <tr>
    <td class="title"><label>主管部门名称</label></td>
    <td class="content" colspan="3">${location.deptName}</td>
  </tr>
  <tr>
    <td class="title"><label>权属部门名称</label></td>
    <td class="content" colspan="3">${location.ownershipUnitName}</td>
  </tr>
 <tr>
    <td class="title"><label>护养部门名称</label></td>
    <td class="content" colspan="3">${location.maintenanceUnitName}</td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content"  colspan="4">${location.remark}</td>
  </tr>
  <c:if test="${location.isEmphasis }">
  <tr>
  	 <td class="title"><label>取消关注时间</label></td>
	 <td class="content"><span id=""><fmt:formatDate value="${location.logOutTime}" type="date" pattern="yyyy-MM-dd" /></td>	
	 <td class="title"><label>取消关注原因</label></td>
	 <td colspan="2" class="content"><span id="">${location.logOutReason}</td>
  </tr>
  </c:if>
   </table>
   
   <script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#locatigUrl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>