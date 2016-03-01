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
	    <td colspan="3" class="content">${population.organization.orgName}</td>
	    <td rowspan="7" class="imagesTX">
	    <c:choose>
		    <c:when test='${null!=population.imgUrl && not empty population.imgUrl}'>
				<img id="populationOrgImg" name="population.imgUrl" src="" width="148px"/>
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
  		<td class="content" colspan="3"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" /></span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>人员类型</label></td>
	    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POSITIVEINFO" defaultValue="${population.positiveInfoType.id}" /></span></td>
	    <td class="title"><label>原罪名(错)</label></td>
	    <td class="content"><span>${population.caseReason }</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>原判刑期</label></td>
	    <td class="content"><span>${population.imprisonmentDate}</span></td>
	    <td class="title"><label>劳教(服刑)场所</label></td>
	    <td class="content" colspan="2"><span>${population.laborEduAddress }</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>解教(刑释)日期</label></td>
	    <td class="content"><span><fmt:formatDate value="${population.releaseOrBackDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
	    <td class="title"><label>是否累犯</label></td>
	    <td class="content" colspan="2">
	    	 <span><c:if test='${population.isRepeat}' >是	</c:if><c:if test='${!population.isRepeat}'>否</c:if></span>
	    </td>
  	</tr>
  	<tr>
	    <td class="title"><label>原职业 </label></td>
	    <td class="content">
		    <span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${population.agoProfession.id}" /></span>
		</td>
	 	<td class="title"><label>年度是否重犯</label></td>
	    <td class="content" colspan="2">
	    	<span><c:if test='${population.isCrime}' >是</c:if><c:if test='${!population.isCrime}'>否</c:if></span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>重犯日期 </label></td>
	    <td class="content">
		    <span><fmt:formatDate value="${population.crimeDate}" type="date" pattern="yyyy-MM-dd" /></span>
		</td>
		<td class="title"><label>安置日期 </label></td>
	    <td class="content" colspan="2">
		    <span><fmt:formatDate value="${population.resettlementDate}" type="date" pattern="yyyy-MM-dd" /></span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>犯罪行为</label></td>
	    <td class="content" colspan="4">
		    <span>${population.criminalBehavior }</span>
		</td>
	</tr>
	<tr>
		<td class="title"><label>未安置原因</label></td>
	    <td class="content" colspan="4">
		    <span><pre>${population.noResettlementReason }</pre></span>
		</td>
	</tr>
</table>
<script type="text/javascript">
	$(document).ready(
			function() {
				if (null != $("#_imgUrl").val() && $("#_imgUrl").val() != "") {//添加随机数保证IE下的图片查看没问题
					$("#populationOrgImg").attr("src",
							$("#_imgUrl").val() + "?r=" + Math.random());
				}
	});
</script>
