<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="dangerousPopulationOrgName">${population.organization.orgName}</td>
    <td rowspan="7">
    <c:choose>
	    <c:when test="${null!=population.imgUrl && not empty population.imgUrl}">
	     	<img id="dangerousImg" name="population.imgUrl" src="" width="148px"/>
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
    <td  class="content" colspan="3"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>有效期</label></td>
    <td class="content" ><span><s:date name="population.periodOfValidityStart" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>至</label></td>
    <td class="content" colspan="1"><span><s:date name="population.periodOfValidityEnd" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>危险从业类型</label></td>
    <td  class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@DANGEROUS_WORKING_TYPE" defaultValue="${population.dangerousWorkingType.id}" /></span></td>
   <td class="title"><label>从业资格证书号</label></td>
    <td class="content" colspan="3"><span>${population.workingCertificateNo}</span></td>
  </tr>
    <tr>
    <td class="title"><label>从业资格证书</label></td>
    <td class="content" colspan="4"><span>${population.workingCertificate}</span></td>
  </tr>
  <tr>
 	<td class="title"><label>工作单位或就读学校</label></td>
 	<td  class="content"><span>${population.workUnit}</span></td>
 	<td class="title"><label>所在企业法人代表</label></td>
    <td colspan="2" class="content"><span>${population.legalPerson}</span></td>
  </tr>
  <tr>
    <td class="title"><label>企业法人联系手机</label></td>
    <td class="content"><span>${population.legalPersonMobileNumber}</span></td>
    <td class="title"><label>企业法人联系电话</label></td>
    <td colspan="2" class="content"><span>${population.legalPersonTelephone}</span></td>
  </tr>

</table>


<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#dangerousImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});


</script>
