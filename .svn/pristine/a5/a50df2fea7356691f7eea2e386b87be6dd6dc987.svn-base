<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${(population.organization.orgName)!}</td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="population.imgUrl" value="${(population.imgUrl)!}"/>
    
    	<@s.if test='null!=population.imgUrl && !"".equals(population.imgUrl)'>
			<img id="populationgUrl" name="population.imgUrl" src="${path }${population.imgUrl}" width="148px"  />
		</@s.if>
		<@s.else>
			<img id="img" name="population.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"  />
		</@s.else>
	</td>
  </tr>
  <tr>
    <td class="title"><label>部件标识码</label></td>
    <td class="content"><span>${(population.dustbinCode)!}</span></td>
    <td class="title"><label>地址</label></td>
     <td class="content"><span>${(population.address)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>部件类型</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_TYPE" defaultValue="${(population.partType.id)!}" /></span></td>
    <td class="title"><label>部件名称</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME" defaultValue="${(population.partName.id)!}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>主管部门名称</label></td>
    <td class="content"><span>${(population.deptName)!}</span></td>
    <td class="title"><label>是否有视频流</label></td>
    <td class="content"><span><@s.if test='true==population.hasVideo'>是</@s.if><@s.else>否</@s.else> </span></td>
  </tr>
  <tr>
    <td class="title"><label>权属部门名称</label></td>
    <td class="content" colspan="3"><span>${(population.ownershipUnitName)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>养护部门名称</label></td>
    <td class="content" colspan="3"><span>${(population.maintenanceUnitName)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content"  colspan="3"><span><pre>${(population.remark)!}</pre></span></td>
  </tr>
</table>
<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#populationgUrl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>