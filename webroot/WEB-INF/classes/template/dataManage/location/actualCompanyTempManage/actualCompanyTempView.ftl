<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${(location.organization.orgName)!}</td>
  </tr>
  <tr>
  	<td class="title"><label>单位名称</label></td>
  	<td colspan="3" class="content"><span>${(location.name)!}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>单位地址</label></td>
  	<td colspan="3" class="content"><span>${(location.address)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>单位类别</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" defaultValue="${(location.companyType.id)!}" /></span></td>
    <td class="title"><label>是否重点单位</label></td>
    <td class="content"><span><@s.if test="location.isKey">是</@s.if><@s.else>否</@s.else></span></td>
  </tr>
  <tr>
    <td class="title"><label>法人代表</label></td>
    <td class="content"><span>${(location.manager)!}</span></td>
    <td class="title"><label>身份证号码</label></td>
   <td class="content"><span>${(location.idCardNo)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>单位电话</label></td>
    <td class="content"><span>${(location.telephone)!}</span></td>
    <td class="title"><label>营业执照号码</label></td>
    <td class="content"><span>${(location.businessLicenseNo)! }</span></td>
  </tr>
  <tr>
    <td class="title"><label>单位传真</label></td>
    <td class="content"><span>${(location.fax)!}</span></td>
    <td class="title"><label>组织机构代码</label></td>
    <td class="content"><span>${(location.orgCode)! }</span></td>
  </tr>
  <tr>
    <td class="title"><label>经济性质</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" defaultValue="${(location.economicNature.id)!}" /></span></td>
  	<td class="title"><label>注册资本(万元)</label></td>
    <td class="content" colspan="2"><span>${(location.registeredCapital)! }</span></td>
  </tr>
  <tr>
     <td class="title"><label>有效期至</label></td>
	 <td class="content"><span id=""><@s.date name="location.expiryDate" format="yyyy-MM-dd" /></span></td>		
	 <td class="title"><label>注册日期 </label></td>
	 <td colspan="2" class="content"><span id=""><@s.date name="location.registrationDate" format="yyyy-MM-dd" /></span></td>				 
  </tr> 
  <tr>
    <td class="title"><label>经营范围</label></td>
    <td class="content" colspan="4"><span>${(location.businessScope)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>注册地址</label></td>
    <td class="content" colspan="4"><span>${(location.registrationAddress)! }</span></td>
  </tr>
  <tr>
    <td class="title"><label>从业人数 </label></td>
    <td class="content"><span>${(location.employeesNum)! }</span></td>
    <td class="title"><label>主管部门</label></td>
    <td class="content" colspan="2"><span>${(location.competentDepartment)! }</span></td>
  </tr>
  <tr>
   	 <td class="title"><label>管理级别</label></td>
	 <td class="content" ><span id="" ><@pop.PropertyDictViewTag  name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL" defaultValue="${(location.supervisoryLevel.id)!}" /></span></td>
     <td class="title"><label>管理部门</label></td>
	 <td  colspan="2" class="content"><span id="">${(location.supervisoryDepartment)!}</span></td>	 
  </tr> 
  <tr>		
   	 <td class="title"><label>消防等级</label></td>
	 <td class="content"><span id=""><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" defaultValue="${(location.fireFightingLevel.id)!}" /></span></td>	
	 <td class="title"><label>治安负责人</label></td>
	 <td colspan="2" class="content"><span id="">${(location.securityChief)!}</span></td>	 
  </tr> 
   <tr>		
   	 <td class="title"><label>备注 </label></td>
	 <td colspan="4" class="content"><span id=""><pre>${(location.remark)!}</pre></span></td>	
  </tr> 
</table>

<script>
$(document).ready(function(){
	
});
</script>