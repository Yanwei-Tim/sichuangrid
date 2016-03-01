<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
		<input id="provinceValue"	type="hidden" name="" value="${unsettledPopulation.province}" />
		<input id="cityValue"	type="hidden" name="" value="${unsettledPopulation.city}" />
		<input id="districtValue"	type="hidden" name="" value="${unsettledPopulation.district}" />
<body>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="commonPopulationOrgName">${unsettledPopulation.organization.orgName}</td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="population.imgUrl" value="${unsettledPopulation.imgUrl}"/>
    <c:choose>
    <c:when test='${null!=unsettledPopulation.imgUrl && not empty unsettledPopulation.imgUrl}'>
		<img id="unsettledPopulationImg" name="unsettledPopulation.imgUrl" src="" width="148px"/>
	</c:when>
	<c:otherwise>
		<img id="img" name="unsettledPopulation.imgUrl" src="${path }/resource/images/head.png" width="148px"/>
	</c:otherwise>
	</c:choose>
	</td>
  </tr>
  <tr>
    <td class="title"><label>姓 名</label></td>
    <td class="content"><span>${unsettledPopulation.name}</span></td>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span id="idCardNo"></span></td>
  </tr>
  <tr>
    <td class="title"><label>曾用名/别名</label></td>
    <td class="content"><span>${unsettledPopulation.usedName}</span></td>
    <td class="title"><label>性别</label></td>
   <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${unsettledPopulation.gender.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>民族</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${unsettledPopulation.nation.id}" /></span></td>
    <td class="title"><label>出生日期</label></td>
    <td class="content"><span><fmt:formatDate value="${unsettledPopulation.birthday}" type="date" pattern="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${unsettledPopulation.mobileNumber }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${unsettledPopulation.telephone }</span></td>
  </tr>
  <tr>
    <td class="title"><label>政治面貌</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${unsettledPopulation.politicalBackground.id}" /></span></td>
    <td class="title"><label>文化程度</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${unsettledPopulation.schooling.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>身高</label></td>
    <td class="content"><span>${unsettledPopulation.stature }(CM)</span></td>
    <td class="title"><label>职业</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${unsettledPopulation.career.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>工作单位或就读学校</label></td>
    <td class="content"><span>${unsettledPopulation.workUnit }</span></td>
    <td class="title"><label>婚姻状况</label></td>
    <td colspan="2" class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${unsettledPopulation.maritalState.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>持证种类</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CERTIFICATEHOLD_TYPE" defaultValue="${unsettledPopulation.certificateType.id}" /></span></td>
    <td class="title"><label>持证编号</label></td>
    <td colspan="2" class="content"><span>${unsettledPopulation.certificateNo}</span></td>
  </tr>
  <tr>
    <td class="title"><label>未落户时间</label></td>
    <td class="content"><span><fmt:formatDate value="${unsettledPopulation.unSettedTime}" type="date" pattern="yyyy-MM-dd" />
				</span></td>
    <td class="title"><label>未落户原因</label></td>
    <td colspan="2" class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@UNSETTEDREASON" defaultValue="${unsettledPopulation.unSettedReason.id}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>是否死亡</label></td>
    <td colspan="4" class="content"><span><c:choose><c:when test='${true==unsettledPopulation.death }'>是</c:when><c:otherwise>否</c:otherwise></c:choose> </span></td>
  </tr>
  <s:if test="unsettledPopulation.logoutDetail.logout==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis">
  	 <tr>
    <td class="title"><label>注销时间</label></td>
    <td class="content"><span><fmt:formatDate value="${unsettledPopulation.logoutDetail.logoutDate}" type="date" pattern="yyyy-MM-dd" /></span></td>
     <td class="title"><label>注销原因</label></td>
    <td class="content" colspan="2"><span>${unsettledPopulation.logoutDetail.logoutReason}</span></td>
  </tr>
  </s:if>
  <tr>
    <td class="title"><label>血型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" defaultValue="${unsettledPopulation.bloodType.id}" /></span></td>
    <td class="title"><label>电子邮箱</label></td>
    <td colspan="2" class="content"><span>${unsettledPopulation.email}</span></td>
  </tr>
  <tr>
    <td class="title"><label>宗教信仰</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@FAITH" defaultValue="${unsettledPopulation.faith.id}" /></span></td>
    <td class="title"><label>户籍地址</label></td>
    <td colspan="2" class="content"><span id="nativePlace"></span></td>
  </tr>
  <tr>
    <td class="title"><label>户籍地详址</label></td>
    <td class="content" colspan="4"><span>${unsettledPopulation.nativePlaceAddress}</span></td>
  </tr>
  <tr>
  <td class="title"><label><c:choose><c:when test="${unsettledPopulation.isHaveHouse=='true' }">常住地址</c:when><c:otherwise>有无住所</c:otherwise></c:choose></label></td>
    <td colspan='<c:choose><c:when test="${unsettledPopulation.isHaveHouse=='false' }">1</c:when><c:otherwise>4</c:otherwise></c:choose>' class="content">
    <span><c:choose><c:when test="${unsettledPopulation.isHaveHouse==null }">未知 </c:when><c:when test="${unsettledPopulation.isHaveHouse=='false' }">无住所</c:when><c:otherwise>${unsettledPopulation.currentAddress}</c:otherwise></c:choose></span>
    </td>
    <c:if test="${unsettledPopulation.isHaveHouse=='false' }">
    <td class="title"><label>无住所原因</label></td>
    <td colspan="2" class="content"><span>${unsettledPopulation.noHouseReason}</span></td>
    </c:if>
  </tr>
  <tr>
    <td class="title"><label>临时居所</label></td>
    <td colspan="4" class="content"><span>${unsettledPopulation.otherAddress}</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="4" class="content">${unsettledPopulation.remark}</td>
  </tr>
</table>
</body>
<script>
$(document).ready(function(){
nativePlaceFormatter();
var idCardNo = '${unsettledPopulation.idCardNo}';
if(idCardNo!=null&&idCardNo!=""){
	idCardNoSub=idCardNo.substring(0,10)+"****"+idCardNo.substring(14);
	$("#idCardNo").text(idCardNoSub);
	<pop:JugePermissionTag ename="isUnsettledPopulationNotHidCard">
	   $("#idCardNo").text(idCardNo);
	</pop:JugePermissionTag>
}else{
	$("#idCardNo").text(idCardNo);
}
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
	$("#unsettledPopulationImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
}
});

function nativePlaceFormatter(){
	var str = "";
	if($("#provinceValue").val() != null &&$("#provinceValue").val()!="")
		str += $("#provinceValue").val();
	if($("#cityValue").val() != null &&$("#cityValue").val()!="")
		str += $("#cityValue").val();
	if($("#districtValue").val() != null &&$("#districtValue").val()!="")
		str += $("#districtValue").val();
	$("#nativePlace").html(str);
}

</script>	
