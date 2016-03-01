<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="dangerousChemicalsUnitOrgName">${(location.organization.orgName)!}</td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="location.imgUrl" value="${(location.imgUrl)!}"/>
    
    <@s.if test='null!=location.imgUrl && !"".equals(location.imgUrl)'>
		<img id="locUrl" name="location.imgUrl" src="${path }${(location.imgUrl)!}" width="148px"  />
	</@s.if>
	<@s.else>
		<img id="img" name="location.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"  />
	</@s.else></td>
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
    <td class="title"><label>负责人</label></td>
    <td class="content"><span>${(location.superintendent)!}</span></td>
    <td class="title"><label>联系电话</label></td>
    <td class="content"><span>${(location.telephone)! }</span></td>
  </tr>
  <tr>
    <td class="title"><label>单位类别</label></td>
    <td class="content"><span>${(location.unitType)!}</span></td>
    <td class="title"><label>货物类别</label></td>
    <td class="content"><span>${(location.commodityType)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>存储设备</label></td>
    <td  colspan="3" class="content"><span>${(location.storageDevice)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>关注程度</label></td>
	<td colspan="3" class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id)!}"/></span></td>     
  </tr>
  <tr>
    <td class="title"><label>副本许可范围</label></td>
    <td colspan="4" class="content"><span>${(location.copyScope)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="4" class="content"><span><pre>${(location.remark)!}</pre></span></td>
  </tr>
</table>
<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#locUrl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>