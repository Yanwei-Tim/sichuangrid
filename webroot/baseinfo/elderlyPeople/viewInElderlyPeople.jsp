<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
<body>
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}"/>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="inflowingPopulationOrgName">${population.organization.orgName}</td>
    <td class="imagesTX"  rowspan="7">
    <c:choose>
	    <c:when test="${null!=population.imgUrl && not empty population.imgUrl}">
	     	<img id="inflowingImg" name="population.imgUrl" src="" width="148px"/>
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
    <td class="content" colspan="3"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>老年人证号</label></td>
    <td class="content"><span>${population.elderPeopleId}</span></td>
    <td class="title"><label>人员类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SPECIAL_PERSON" defaultValue="${population.peopleType.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>当前状况</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CURRENT_STATUS" defaultValue="${population.currentStatus.id}" /></span></td>
    <td class="title"><label>居住情况</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LIVE_STATUS" defaultValue="${population.liveStatus.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>配偶情况</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SPOUSE_STATUS" defaultValue="${population.spouseStatus.id}" /></span></td>
    <td class="title"><label>经济来源</label></td>
    <td class="content" colspan="2"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@INCOME_SOURCE" defaultValue="${population.incomeSource.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>参加工作日期</label></td>
    <td class="content"><span><s:date name="population.enterWorkDate" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>离退休单位</label></td>
    <td class="content" colspan="2"><span>${population.retireUnitAndDuty }</span></td>
  </tr>
  <tr>
    <td class="title"><label>离退休日期</label></td>
    <td class="content"><span><s:date name="population.retireDate" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>原单位职务</label></td>
    <td class="content" colspan="2"><span>${population.zhiwu }</span></td>
  </tr>
 
  <tr>
    <td class="title"><label>退休金</label></td>
    <td class="content"  colspan="5"><span>${population.pensionStringValue}(元)</span></td>
  </tr>
  
</table>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				if (null != $("#_imgUrl").val() && $("#_imgUrl").val() != "") {//添加随机数保证IE下的图片查看没问题
					$("#inflowingImg").attr("src",
							$("#_imgUrl").val() + "?r=" + Math.random());
				}
	});
</script>
