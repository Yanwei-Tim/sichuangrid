<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<table width="200" class="tablelist">
	 <tr>
	    <td class="title"><label>所属网格</label></td>
	    <td colspan="3" class="content" id="locationOrgName">${location.organization.orgName}</td>
	</tr>
	<@s.if test="'enterpriseKey'.equals(keyType) ">
	  	<tr>
		    <td class="title"><label>企业名称</label></td>
		    <td class="content"><span>${enterprise.name}</span></td>
		    <td class="title"><label>工商执照号</label></td>
		    <td class="content"><span>${enterprise.businessLicense}</span></td>
	  	</tr>
  	</@s.if>
		 <tr>
		    <td class="title"><label>企业名称</label></td>
		    <td colspan="3" class="content"><span>${(location.name)!}</span></td>
	  	 </tr>
	  	 <tr>
		    <td class="title"><label>企业地址</label></td>
		    <td colspan="3" class="content"><span>${(location.address)!}</span></td>
	  	 </tr>
		 <tr>
		    <td class="title"><label>企业类型</label></td>
			<td class="content"><span><@pop.PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@ENTERPRISE_TYPE"
				defaultValue="${(location.type.id)!}"/></span></td>
			<td class="title"><label>工商执照号</label></td>
		    <td class="content"><span>${(location.businessLicense)!}</span></td>
	  	</tr>
	 <tr>
	    <td class="title"><label>法人代表</label></td>
	    <td class="content"><span>${(location.manager)!}</span></td>
	    <td class="title"><label>法人联系电话</label></td>
	    <td class="content"><span>${(location.telephone)!}</span></td>
  	</tr>
  	<tr>
  	<td class="title"><label>所属行业</label></td>
	    <td class="content"><span><@pop.PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@BUSINESSTYPE"
				defaultValue="${(location.industry.id)!}"/></span></td>
	    <td class="title"><label>法人手机号码</label></td>
	    <td class="content"><span>${(location.mobileNumber)!}</span></td>	   
  	</tr>
  	<tr>
  	 	<td class="title"><label>员工数</label></td>
	    <td class="content"><span>${(location.employeeAmount)!}</span></td>
	    <td class="title"><label>党员数</label></td>
	    <td class="content"><span>${(location.partyMemberAmount)!}</span></td>
  	</tr>
  	<tr>
  		<td class="title"><label>面积</label></td>
	    <td class="content"><span>${(location.area)!}</span><font size="1">M</font><font size="1"><sup>2</sup></font> </td>
	    <td class="title"><label>企业联系电话</label></td>
	    <td class="content"><span>${(location.enterpriseTelephone)!}</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>注册资金</label></td>
	    <td class="content"><span>${(location.registeredCapital)!}(万元)</span></td>
	    <td class="title"><label>企业传真号码</label></td>
	    <td class="content"><span>${(location.fax)!}</span></td>
  	</tr>
  	<tr>
  		<td class="title"><label>是否为危化企业</label></td>
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
	    <td colspan="3" class="content"><span>${(location.hiddenRectification)!}</span></td>
  	</tr>
  	<tr>
	    <td class="title"><label>备注</label></td>
	    <td colspan="3" class="content"><span><pre>${(location.remark)!}<pre></span></td>
  	</tr>
</table>