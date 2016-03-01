<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="otherLocaleOrgName">${(location.organization.orgName)!}</td>
    <td class="imagesTX" rowspan="6">
    <input id="_imgUrl" type="hidden" name="location.imgUrl" value="${(location.imgUrl)!}"/>
    
    <@s.if test='null!=location.imgUrl && !"".equals(location.imgUrl)'>
		<img id="limgUrl" name="location.imgUrl" src="${path }${(location.imgUrl)!}" width="148px"  />
	</@s.if>
	<@s.else>
		<img id="img" name="location.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"  />
	</@s.else></td>
  </tr> 
   <tr>
    <td class="title"><label>名称</label></td>
    <td colspan="3" class="content" id="name">${(location.name)!}</td>
  </tr> 
   <tr>
    <td class="title"><label>地址</label></td>
    <td class="content" id="address">${(location.address)!}</td>
    <td class="title"><label>场所类型</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@OTHER_LOCALE_TYPE"
				defaultValue="${(location.type.id)!}"/></span></td>
  </tr>
  <tr>
    <td class="title"><label>从业人员数</label></td>
    <td colspan="3" class="content" id="practitionerNumber">${(location.practitionerNumber)!}</td>
  </tr> 
   <tr>
    <td class="title"><label>联系人</label></td>
    <td class="content" id="contactPerson">${(location.manager)!}</td>
    <td class="title"><label>关注程度</label></td>
	<td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id)!}"/></span></td>
  </tr> 
   <tr>
    <td class="title"><label>联系电话</label></td>
    <td class="content" id="telephone">${(location.telephone)!}</td>
    <td class="title"><label>联系手机</label></td>
    <td class="content" id="mobileNumber">${(location.mobileNumber)!}</td>
  </tr> 
   <@s.if test='school.isEmphasis==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis'>
    <td class="title"><label>注销时间</label></td>
    <td class="content"><span><@s.date name="location.logOutTime" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>注销原因</label></td>
    <td class="content"><span>${(location.logOutReason)!}</span></td>
   </@s.if>
   <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="3" class="content" id="remark">${(location.remark)!}</td>
  </tr> 
  
</table>
<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#limgUrl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>

