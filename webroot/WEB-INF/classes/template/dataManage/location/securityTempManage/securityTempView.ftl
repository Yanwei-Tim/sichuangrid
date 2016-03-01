<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<table width="200" class="tablelist">
	 <tr>
	    <td class="title"><label>所属网格</label></td>
	    <td colspan="3" class="content" id="enterpriseOrgName">${(location.organization.orgName)!}</td>
	</tr>
	 <tr>
	    <td class="title"><label>场所名称</label></td>
	    <td colspan="3" class="content"><span>${(location.name)!}</span></td>
  	 </tr>
  	 <tr>
	    <td class="title"><label>场所地址</label></td>
	    <td colspan="3" class="content"><span>${(location.address)!}</span></td>
  	 </tr>
	 <tr>
	    <td class="title"><label>场所类型</label></td>
		<td class="content"><span><@pop.PropertyDictViewTag
			name="@com.tianque.domain.property.PropertyTypes@SECURITY_TYPE"
			defaultValue="${(location.type.id)!}"/></span></td>
	    <td class="title"><label>负责人</label></td>
	    <td class="content"><span>${(location.manager)!}</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>联系电话</label></td>
	    <td class="content"><span>${(location.telephone)!}</span></td>
	    <td class="title"><label>手机号码</label></td>
	    <td class="content"><span>${(location.mobileNumber)!}</span></td>
  	</tr>
  	<tr>
  		<td class="title"><label>是否存在隐患</label></td>
	    <td class="content">
	 		<@s.if test="location.riskEnterprise==1">是</@s.if><@s.if test="location.riskEnterprise==0">否</@s.if> 
	    </td>
	    <td class="title"><label>关注程度</label></td>
	    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id)!}"/></span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>隐患情况</label></td>
	    <td colspan="3" class="content"><span>${(location.hiddenCases)!}</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>隐患整改情况</label></td>
	    <td colspan="4" class="content"><span>${(location.hiddenRectification)!}</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>备注</label></td>
	    <td colspan="4" class="content"><span><pre>${(location.remark)!}<pre></span></td>
  	</tr>
  	
</table>