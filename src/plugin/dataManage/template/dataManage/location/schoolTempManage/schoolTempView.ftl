<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content">${location.organization.orgName}</td>
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
    <td class="title"><label>学校名称</label></td>
    <td colspan="3" class="content">${(location.name)!}</td>
  </tr>
  <tr>
  	<td class="title"><label>学校地址</label></td>
    <td colspan="3" class="content">${(location.address)!}</td>
  </tr>
  <tr>
    <td class="title"><label>英文名称</label></td>
    <td class="content" id="schoolEnglishName">${(location.englishName)!}</td>
    <td class="title"><label>学校性质</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY"
				defaultValue="${(location.kind.id)!}"/></span></td>
  </tr>
  <tr>
    <td class="title"><label>学校类型</label></td>
  	<td class="content"><span><@pop.PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE"
				defaultValue="${(location.type.id)!}"/></span>
	</td>
	<td class="title"><label>关注程度</label></td>
	<td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(location.attentionExtent.id)!}"/></span></td>
  </tr>
  <tr>
    <td class="title"><label>学校网址</label></td>
    <td class="content">${(location.webSite)!}</td>
    <td class="title"><label>在校人数</label></td>
    <td class="content">${(location.atSchoolHeadcount)!}</td>
  </tr>
  <tr>
    <td class="title"><label>校长</label></td>
    <td class="content">${(location.manager)!}</td>
    <td class="title"><label>传真</label></td>
    <td class="content">${(location.fax)!}</td>
  </tr>
  <tr>
    <td class="title"><label>电子邮箱</label></td>
    <td class="content">${(location.email)!}</td>
    <td class="title"><label>法制副校长</label></td>
    <td class="content" colspan="2">${(location.vicePresident)!}</td>
  </tr>
  <tr>
    <td class="title"><label>联系电话</label></td>
    <td class="content">${(location.telephone)!}</td>
    <td class="title"><label>联系手机</label></td>
    <td class="content" colspan="2">${(location.mobileNumber)!}</td>
  </tr>
  <tr>
    <td class="title"><label>周边情况</label></td>
    <td colspan="4" class="content">${(location.remark)!}</td>
  </tr>
  
</table>

<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#locUrl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>
