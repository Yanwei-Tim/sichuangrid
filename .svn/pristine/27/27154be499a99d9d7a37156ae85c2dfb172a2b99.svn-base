<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="otherLocaleOrgName">${otherLocale.organization.orgName}</td>
    <td class="imagesTX" rowspan="6">
    <s:if test='null!=otherLocale.imgUrl && !"".equals(otherLocale.imgUrl)'>
		<img id="img" name="otherLocale.imgUrl" src="${path }${otherLocale.imgUrl}" width="148px"/>
	</s:if>
	<s:else>
		<img id="img" name="otherLocale.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"/>
	</s:else></td>
  </tr> 
   <tr>
    <td class="title"><label>名称</label></td>
    <td colspan="3" class="content" id="name">${otherLocale.name}</td>
  </tr> 
   <tr>
    <td class="title"><label>地址</label></td>
    <td class="content" id="address">${otherLocale.address}</td>
    <td class="title"><label>场所类型</label></td>
    <td class="content"><span><pop:PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@OTHER_LOCALE_TYPE"
				defaultValue="${otherLocale.type.id}"/></span></td>
  </tr>
  <tr>
    <td class="title"><label>从业人员数</label></td>
    <td colspan="3" class="content" id="practitionerNumber">${otherLocale.practitionerNumber}</td>
  </tr> 
   <tr>
    <td class="title"><label>联系人</label></td>
    <td class="content" id="contactPerson">${otherLocale.contactPerson}</td>
    <td class="title"><label>关注程度</label></td>
	<td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${otherLocale.attentionExtent.id}"/></span></td>
  </tr> 
   <tr>
    <td class="title"><label>联系电话</label></td>
    <td class="content" id="telephone">${otherLocale.telephone}</td>
    <td class="title"><label>联系手机</label></td>
    <td class="content" id="mobileNumber">${otherLocale.mobileNumber}</td>
  </tr> 
   <s:if test='otherLocale.isEmphasis==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis'>
    <td class="title"><label>注销时间</label></td>
    <td class="content"><span><s:date name="otherLocale.logOutTime" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>注销原因</label></td>
    <td class="content"><span>${otherLocale.logOutReason}</span></td>
   </s:if>
   <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="3" class="content" id="remark">${otherLocale.remark}</td>
  </tr> 
  
</table>

<script type="text/javascript">
$(function (){
	var img=$("#img");
	var src=img.attr("src");
	if(src){
		img.attr("src",src+"?r="+Math.random());
	} 
});
</script>
