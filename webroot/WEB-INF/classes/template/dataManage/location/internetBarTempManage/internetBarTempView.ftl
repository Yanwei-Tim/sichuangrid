<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="organizationOrgName">${(location.organization.orgName)!}</td>
    <td class="imagesTX" rowspan="6">
    <input id="_imgUrl" type="hidden" name="location.imgUrl" value="${(location.imgUrl)!}"/>
    <@s.if test='null!=location.imgUrl && !"".equals(location.imgUrl)'>
		<img id="local" name="location.imgUrl" src="${path}${(location.imgUrl)!}" width="148px"  />
	</@s.if>
	<@s.else>
		<img id="img" name="location.imgUrl" src="${path}/resource/images/locationHead.png" width="148px"  />
	</@s.else></td>
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
     <td class="title"><label>联系电话</label></td>
    <td class="content"><span>${(location.mobile)!}</span></td>
  </tr>
  <tr>
   	<td class="title"><label>现使用IP</label></td>
    <td class="content"><span>${(location.useIP)!}</span></td>
    <td class="title"><label>接入方式</label></td>
    <td class="content"><span>${(location.internetAccessType)!}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>网络接入服务商</label></td>
    <td class="content"><span>${(location.netAccessProviders)!}</span></td>
    <td class="title"><label>所在地派出所名称</label></td>
    <td class="content"><span>${(location.stationPolice)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>网吧编号</label></td>
    <td class="content"><span>${(location.barCode)!}</span></td>
     <td class="title"><label>营业面积</label></td>
    <td class="content" colspan="2"><span>${(location.operationArea)!}</span></td>
  </tr>
   <tr>		
   	 <td class="title"><label>网络文化经营许可证号</label></td>
   	 <td class="content"><span><pre>${(location.netCultureLicenceNo)!}</pre></span></td>	 
   	 <td class="title"><label>网络安全审核意见书号</label></td>
   	 <td colspan="2" class="content"><span><pre>${(location.netSecurityAuditNo)!}</pre></span></td>	 
  </tr> 
   <tr>		
   	 <td class="title"><label>消防审核意见书号</label></td>
   	 <td class="content"><span><pre>${(location.fireAuditDocumentNo)!}</pre></span></td>
   	 <td class="title"><label>关注程度</label></td>
	 <td colspan="2" class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id)!}"/></span></td>
  </tr> 
  <tr>		
   	 <td class="title"><label>备注</label></td>
   	 <td colspan="4" class="content"><span id=""><pre>${(location.remark)!}</pre></span></td>	 
  </tr> 
</table>

<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#local").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
	
});

</script>
