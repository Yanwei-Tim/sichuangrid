<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table width="200" class="tablelist">
	 <tr>
	    <td class="title"><label>所属网格</label></td>
	    <td colspan="3" class="content" id="enterpriseOrgName">${enterprise.organization.orgName}</td>
	    <td class="imagesTX" rowspan="7">
	    <input id="_imgUrl" type="hidden" name="enterprise.imgUrl" value="${enterprise.imgUrl}"/>
	    <s:if test='null!=enterprise.imgUrl && !"".equals(enterprise.imgUrl)'>
			<img id="enterImg" name="enterprise.imgUrl" src="${path }${enterprise.imgUrl}" width="148px"/>
		</s:if>
		<s:else>
			<img id="img" name="enterprise.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"/>
		</s:else></td>
	</tr>
	 <tr>
	    <td class="title"><label>场所名称</label></td>
	    <td colspan="3" class="content"><span>${enterprise.name}</span></td>
  	 </tr>
  	 <tr>
	    <td class="title"><label>场所地址</label></td>
	    <td colspan="3" class="content"><span>${enterprise.address}</span></td>
  	 </tr>
	 <tr>
	    <td class="title"><label>场所类型</label></td>
		<td class="content"><span><pop:PropertyDictViewTag
			name="@com.tianque.domain.property.PropertyTypes@SECURITY_TYPE"
			defaultValue="${enterprise.type.id}"/></span></td>
	    <td class="title"><label>负责人</label></td>
	    <td class="content"><span>${enterprise.legalPerson}</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>联系电话</label></td>
	    <td class="content"><span>${enterprise.telephone}</span></td>
	    <td class="title"><label>手机号码</label></td>
	    <td class="content"><span>${enterprise.mobileNumber}</span></td>
  	</tr>
    <s:if test='enterprise.isEmphasis==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis'>
	    <td class="title"><label>注销时间</label></td>
	    <td class="content"><span><s:date name="enterprise.logOutTime" format="yyyy-MM-dd" /></span></td>
	    <td class="title"><label>注销原因</label></td>
	    <td class="content"><span>${enterprise.logOutReason}</span></td>
	</s:if>
  	<tr>
  		<td class="title"><label>是否存在隐患</label></td>
	    <td class="content">
	 		<s:if test="enterprise.riskEnterprise==1">是</s:if><s:if test="enterprise.riskEnterprise==0">否</s:if> 
	    </td>
	    <td class="title"><label>关注程度</label></td>
	    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${enterprise.attentionExtent.id}"/></span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>挂牌整治</label></td>
	    <td colspan="3" class="content"><span id="renovateType"></span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>隐患情况</label></td>
	    <td colspan="4" class="content"><span>${enterprise.hiddenCases}</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>隐患整改情况</label></td>
	    <td colspan="4" class="content"><span>${enterprise.hiddenRectification}</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>备注</label></td>
	    <td colspan="4" class="content">${enterprise.remark}</td>
  	</tr>
  	
</table>

<script type="text/javascript">
$(function (){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#enterImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
renovateTypeFormatter();
function renovateTypeFormatter(){
	var renovateType = ${enterprise.renovateType};
	var renovateTypeName = "";
	if(renovateType=='<s:property value="@com.tianque.domain.property.RenovateType@RENOVATE_TYPE_PROVINCE"/>'){
		renovateTypeName = '<s:property  escape="false" value="@com.tianque.domain.property.RenovateType@PROVINCE_NAME"/>';
	}else if(renovateType=='<s:property value="@com.tianque.domain.property.RenovateType@RENOVATE_TYPE_CITY"/>'){
		renovateTypeName = '<s:property  escape="false"  value="@com.tianque.domain.property.RenovateType@CITY_NAME"/>';
	}else if(renovateType=='<s:property value="@com.tianque.domain.property.RenovateType@RENOVATE_TYPE_COUNTY"/>'){
		renovateTypeName = '<s:property  escape="false"  value="@com.tianque.domain.property.RenovateType@COUNTY_NAME"/>';
	}
	$("#renovateType").text(renovateTypeName);
}
</script>