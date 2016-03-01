<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="organizationOrgName">${location.organization.orgName}</td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="location.imgUrl" value="${(location.imgUrl)!}"/>
	    <@s.if test='null!=location.imgUrl && !"".equals(location.imgUrl)'>
			<img id="locrl" name="location.imgUrl" src="${path }${(location.imgUrl)!}" width="148px"  />
		</@s.if>
		<@s.else>
			<img id="img" name="location.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"  />
		</@s.else></td>
  </tr>
  <tr>
    <td class="title"><label>医院名称</label></td>
    <td colspan="3" class="content"><span>${(location.name)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>医院地址</label></td>
    <td colspan="3" class="content"><span>${(location.address)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>所属单位</label></td>
    <td colspan="3" class="content"><span>${(location.affiliatedUnit)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>医院性质</label></td>
    	<td class="content"><span><@pop.PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND"
					defaultValue="${(location.kind.id)!}"/></span>
		</td>
     <td class="title"><label>医院类型</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag
				name="@com.tianque.domain.property.PropertyTypes@HOSPITALS_TYPE"
					defaultValue="${(location.type.id)!}"/></span></td>
  </tr>
  
  <tr>
    <td class="title"><label>医院院长</label></td>
    	<td class="content"><span>${(location.director)!}</span>
		</td>
     <td class="title"><label>联系电话</label></td>
    <td class="content"><span>${(location.telephone)!}</span></td>
  </tr>
  
  <tr>
    <td class="title"><label>联系手机</label></td>
    	<td class="content"><span>${(location.mobileNumber)!}</span>
		</td>
     <td class="title"><label>联系传真</label></td>
    <td class="content" colspan="2"><span>${(location.fax)!}</span></td>
  </tr>
  
  <tr>
     <td class="title"><label>电子邮箱</label></td>
    <td class="content" colspan="4"><span>${(location.email)!}</span></td>
  </tr>
  
  <tr>		
   	 <td class="title"><label>备注</label></td>
   	 <td colspan="4" class="content"><span id=""><pre>${(location.remark)!}</pre></span></td>	 
  </tr> 
</table>

<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#locrl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}

	
});
</script>
