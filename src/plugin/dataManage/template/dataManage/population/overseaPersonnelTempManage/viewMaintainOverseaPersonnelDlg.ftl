<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<body>
<table class="tablelist">
  <tr>
  	<input id="_imgUrl" name="overseaPersonnel.imgUrl" type="hidden" value="${(overseaPersonnel.imgUrl)!}"/>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="overseaPersonnelOrgName">${(overseaPersonnel.organization.orgName)!}</td>
    <td class="imagesTX" rowspan="7">
    <@s.if test='null!=overseaPersonnel.imgUrl && !"".equals(overseaPersonnel.imgUrl)'>
		<img id="img_Url" name="overseaPersonnel.imgUrl" src="${path }${(overseaPersonnel.imgUrl)!}" width="148px"  height="174px"/>
	</@s.if>
	<@s.else>
		<img id="img" name="overseaPersonnel.imgUrl" src="${path }/resource/images/head.png" width="148px"  height="174px"/>
	</@s.else></td>
  </tr>
  <tr>
    <td class="title"><label>英文名</label></td>
  	<td class="content"><span>${(overseaPersonnel.englishName)!}</span></td>
  	<td class="title"><label>中文名</label></td>
  	<td class="content"><span>${(overseaPersonnel.name)!}</span></td>
  </tr>
  <tr>
     	<td class="title"><label>性别</label></td>
  		<td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${(overseaPersonnel.gender.id)!}" /></span></td>
  		<td class="title"><label>证件种类</label></td>
  	<td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CERTIFICATE_TYPE" defaultValue="${(overseaPersonnel.certificateType.id)!}" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>证件号码</label></td>
  	<td class="content"><span>${(overseaPersonnel.certificateNo)!}</span></td>
  	<td class="title"><label>证件有效期</label></td>
  	<td class="content">从
	  	<@s.date name="overseaPersonnel.certificateStrartDay" format="yyyy-MM-dd" />
	  	到
	  	<@s.date name="overseaPersonnel.certificateEndDay" format="yyyy-MM-dd" />
	</td>
  </tr>
  <tr>
  	<td class="title"><label>联系手机</label></td>
  	<td class="content"><span>${(overseaPersonnel.mobileNumber)! }</span></td>
  	<td class="title"><label>固定手机</label></td>
  	<td class="content"><span>${(overseaPersonnel.telephone)!}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>出生日期</label></td>
  	<td class="content"><span><@s.date name="overseaPersonnel.birthday" format="yyyy-MM-dd" /></span></td>
  	<td class="title"><label>国籍</label></td>
  	<td class="content"><span>${(overseaPersonnel.nationality)!}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>职业</label></td>
  	<td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@PROFESSION_TYPE" defaultValue="${(overseaPersonnel.profession.id)!}" /></span></td>
  	<td class="title" ><label>工作单位或就读学校</label></td>
  	<td class="content" colspan="2"><span>${(overseaPersonnel.workUnit)! }</span></td>
  </tr>
  <tr>
  	<td class="title"><label>抵达日期</label></td>
  	<td class="content"><span><@s.date name="overseaPersonnel.arrivalTime" format="yyyy-MM-dd" /></span></td>
  	<td class="title" ><label>离开日期</label></td>
  	<td class="content" colspan="2"><span><@s.date name="overseaPersonnel.leaveTime" format="yyyy-MM-dd" /></span></td>
  </tr>
 
  <tr>
	    <td class="title"><label>流入原因</label></td>
	  	<td class="content" colspan="4"><span>${(overseaPersonnel.inflowReason)!}</span></td>
  </tr>
  <@s.if test="overseaPersonnel.logoutDetail.logout==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis">
  	 <tr>
        <td class="title"><label>注销时间</label></td>
	  	<td class="content" ><span><@s.date name="overseaPersonnel.logoutDetail.logoutDate" format="yyyy-MM-dd" /></span></td>
        <td class="title"><label>注销原因</label></td>
        <td class="content" colspan="2"><span>${(overseaPersonnel.logoutDetail.logoutReason)!}</span></td>
    </tr>
  </@s.if>
  <td class="title"><label>户籍详址</label></td>
  	<td class="content" colspan="4"><span>${(overseaPersonnel.nativePlaceAddress)!}</span></td>
  </tr>
  <tr>
  	<td class="title"><label>邮箱</label></td>
  	<td class="content" colspan="4"><span>${(overseaPersonnel.mail)!}</span></td>
  </tr>
  <tr>
  <tr>
  	<td class="title"><label>备注</label></td>
  	<td class="content" colspan="5"><span>${(overseaPersonnel.remark)!}</span></td>
  </tr>
</table>
</body>

<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#img_Url").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>
