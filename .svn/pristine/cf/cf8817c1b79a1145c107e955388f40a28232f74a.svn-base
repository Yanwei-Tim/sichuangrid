<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="organizationOrgName">${(location.organization.orgName)!}</td>
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
  	<td class="title"><label>负责人</label></td>
    <td class="content"><span>${(location.manager)!}</span></td>
    <td class="title" ><label>备案登记号码</label></td>
    <td class="content"><span>${(location.registrationNumber)!}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>开业时间</label></td> 
    <td class="content"><span><@s.date name="location.openingDate" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>领取执照时间</label></td>
    <td class="content"><span><@s.date name="location.licenseDate" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>场所等级</label></td>
    <td class="content"><span>${(location.placeLevel)! }</span></td>
    <td class="title"><label>公共场所类别</label></td>
    <td class="content"><span>${(location.category)! }</span></td>
  </tr>
  <tr>
    <td class="title"><label>总面积&nbsp;(m<sup>2</sup>)</label></td>
    <td class="content"><span>${(location.totalArea)! }</span></td>
    <td class="title"><label>营业面积&nbsp;(m<sup>2</sup>) </label></td>
	<td class="content"><span>${(location.operationArea)! }</span></td>
  </tr> 
  <tr>
  	<td class="title"><label>包间数&nbsp;(间)</label></td>
	<td class="content"><span>${(location.privateRoomCount)! }</span></td>
   	<td class="title"><label>核定人数&nbsp;(人)</label></td>
   	<td class="content"><span>${(location.vouchedPeopleCount)! }</span></td>
  </tr> 
  <tr>	
   	 <td class="title"><label>小件寄存处</label></td>
   	 <td class="content"><span id=""><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM" defaultValue="${(location.cloakroom.id)!}" /></span></td>	 
     <td class="title"><label>通道口</label></td>
     <td  class="content"><span>${(location.passageway)! }</span></td>	
  </tr> 
   <tr>		
   	 <td class="title"><label>建筑物结构 </label></td>
	 <td class="content"><span id="">${(location.buildingStructure)!}</span></td>	
	 <td class="title"><label>内部结构</label></td>
	 <td  class="content"><span>${(location.innerStructure)! }</span></td>
  </tr> 
  <tr>
  	 <td class="title"><label>关注程度</label></td>
	 <td class="content" colspan="3"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id)!}"/></span></td>
  </tr>

 
  <tr>
  	<td class="title"><label>周围环境</label></td>
	 <td colspan="3" class="content"><span id="">${(location.surrounding)!}</span></td>	 
  </tr>
   <tr>		
   	 <td class="title"><label>备注</label></td>
   	 <td colspan="3" class="content"><span id=""><pre>${(location.remark)!}</pre></span></td>	 
  </tr> 
</table>

<script>
$(document).ready(function(){
	isEmpFormatter();
});

function isEmpFormatter(){
	var str = "";
	if($("#IsEmpValue").val()=="true"||$("#IsEmpValue").val()==true)
		str += "是";
	if($("#IsEmpValue").val() =="false"||$("#IsEmpValue").val()==false)
		str += "否";
	$("#tagIsEmp").html(str);
}
</script>
