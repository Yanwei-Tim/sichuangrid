<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="7" class="content" id="orgName">${(population.organization.orgName)!}</td>
  </tr>
  <tr>
    <td class="title"><label>楼宇名称</label></td>
    <td class="content" colspan="7"><span>${(population.buildingname)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>楼宇类型</label></td>
    <td class="content" colspan="3"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BUILDDATAS_TYPE" defaultValue="${(population.type.id)!}" /></span></td>
    <td class="title"><label>是否有物管</label></td>
    <td class="content" colspan="3"><span><#if population.isPropertyManagement??>是<#else>否</#if></span></td>
  </tr>
  <tr>
    <td class="title"><label>楼宇地址</label></td>
    <td class="content" colspan="7"><span>${(population.buildingaddress)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>业主</label></td>
    <td class="content" colspan="3"><span>${(population.owner)!}</span></td>
     <td class="title"><label>负责人</label></td>
    <td class="content" colspan="3"><span>${(population.responsibleperson)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>联系电话</label></td>
    <td class="content" colspan="3"><span>${(population.phone)!}</span></td>
     <td class="title"><label>建筑结构</label></td>
    <td class="content" colspan="7"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" defaultValue="${(population.buildingstructures.id)!}" /></span></td>
  </tr>

</table>