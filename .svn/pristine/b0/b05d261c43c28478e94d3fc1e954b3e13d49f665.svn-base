<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="druggyPopulationOrgName">${population.organization.orgName}</td>
    <td rowspan="7">
    <c:choose>
    <c:when test='${null!=population.imgUrl && not empty population.imgUrl}'>
		<img id="druggysImg" name="population.imgUrl" src="" width="148px"/>
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
   	  <td class="title"><label>关注程度</label></td>
	 <td  class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}"/></span></td>
      <td class="title"><label>关注原因 </label></td>
	 <td class="content"    ><span id="">${population.attentionReason }</span></td>
  </tr>
  
  <tr>
   	  <td class="title"><label>工作情况</label></td>
	 <td colspan='3' class="content"><span id="">${population.workCondition }</span></td>
  </tr>
  
</table>

<script>
$(document).ready(function(){
	adanonFormatter();
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#druggysImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
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

</script>
