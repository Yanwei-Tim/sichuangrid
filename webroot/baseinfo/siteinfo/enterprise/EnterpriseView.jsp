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
			<img id="enterpriseImg" name="enterprise.imgUrl" src="${path }${enterprise.imgUrl}" width="148px"/>
		</s:if>
		<s:else>
			<img id="img" name="enterprise.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"/>
		</s:else></td>
	</tr>

		 <tr>
		    <td class="title"><label>企业名称</label></td>
		    <td colspan="3" class="content"><span>${enterprise.name}</span></td>
	  	 </tr>
	  	 <tr>
		    <td class="title"><label>企业地址</label></td>
		    <td colspan="3" class="content"><span>${enterprise.address}</span></td>
	  	 </tr>
		 <tr>
		    <td class="title"><label>企业类型</label></td>
			<td class="content"><span><pop:PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@ENTERPRISE_TYPE"
				defaultValue="${enterprise.type.id}"/></span></td>
			<td class="title"><label>工商执照号</label></td>
		    <td class="content"><span>${enterprise.businessLicense}</span></td>
	  	</tr>
	 <tr>
	    <td class="title"><label>法人代表</label></td>
	    <td class="content"><span>${enterprise.legalPerson}</span></td>
	    <td class="title"><label>法人联系电话</label></td>
	    <td class="content"><span>${enterprise.telephone}</span></td>
  	</tr>
  	<tr>
  	<td class="title"><label>所属行业</label></td>
	    <td class="content"><span><pop:PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@BUSINESSTYPE"
				defaultValue="${enterprise.industry.id}"/></span></td>
	    <td class="title"><label>法人手机号码</label></td>
	    <td class="content"><span>${enterprise.mobileNumber}</span></td>	   
  	</tr>
  	<tr>
  	 	<td class="title"><label>员工数</label></td>
	    <td class="content"><span>${enterprise.employeeAmount}</span></td>
	    <td class="title"><label>党员数</label></td>
	    <td class="content"><span>${enterprise.partyMemberAmount}</span></td>
  	</tr>
  	<tr>
  		<td class="title"><label>面积</label></td>
	    <td class="content"><span>${enterprise.areaString}</span><font size="1">M</font><font size="1"><sup>2</sup></font> </td>
	    <td class="title"><label>企业联系电话</label></td>
	    <td colspan="2" class="content"><span>${enterprise.enterpriseTelephone}</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>注册资金</label></td>
	    <td class="content"><span>${enterprise.registeredCapitalString}(万元)</span></td>
	    <td class="title"><label>企业传真号码</label></td>
	    <td colspan="2" class="content"><span>${enterprise.fax}</span></td>
  	</tr>
    <s:if test='enterprise.isEmphasis==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis'>
	    <td class="title"><label>取消关注时间</label></td>
	    <td class="content"><span><s:date name="enterprise.logOutTime" format="yyyy-MM-dd" /></span></td>
	    <td class="title"><label>取消关注原因</label></td>
	    <td colspan="2" class="content"><span>${enterprise.logOutReason}</span></td>
	</s:if>
  	<tr>
  		<td class="title"><label>是否为危化企业</label></td>
	    <td class="content">
	 		<s:if test="enterprise.riskEnterprise==1">是</s:if><s:if test="enterprise.riskEnterprise==0">否</s:if> 
	    </td>
	    <td class="title"><label>关注程度</label></td>
	    <td  colspan="2" class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${enterprise.attentionExtent.id}"/></span></td>
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
		$("#enterpriseImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>