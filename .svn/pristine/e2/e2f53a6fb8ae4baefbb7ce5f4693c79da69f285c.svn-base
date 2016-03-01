<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属区域</label></td>
    <td colspan="3" class="content" id="orgName">${(location.organization.orgName)!}</td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="location.imgUrl" value="${(location.imgUrl)!}"/>
    <@s.if test='null!=location.imgUrl && !"".equals(location.imgUrl)'>
		<img id="locatigUrl" name="location.imgUrl" src="${path }${(location.imgUrl)!}" width="148px"  />
	</@s.if>
	<@s.else>
		<img id="img" name="location.imgUrl" src="${path }/resource/images/locationHead.png" width="148px"  />
	</@s.else></td>
  </tr>
  <tr>
    <td class="title"><label>组织名称</label></td>
    <td colspan="3" class="content"><span>${(location.name)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>住所</label></td>
    <td colspan="3" class="content"><span>${(location.address)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>组织类别</label></td>
    <td class="content" colspan="3"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SOCIETY_GROUP" defaultValue="${(location.type.id)!}" /></span></td>

     <!--<td class="title"><label>组织子类别</label></td> -->
     <!--<td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" defaultValue="${(location.subType.id)!}" /></span></td>-->
  </tr>
  <tr>
    <td class="title"><label>法定代表人</label></td>
    <td class="content"><span>${(location.manager)!}</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${(location.telephone)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${(location.mobileNumber)!}</span></td>
    <td class="title"><label>业务主管单位</label></td>
    <td class="content"><span>${(location.chargeUnit)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>登记证号码</label></td>
    <td class="content"><span>${(location.registrationNumber)!}</span></td>
    <td class="title"><label>有效期</label></td>
    <td class="content"><span>从&nbsp;<@s.date name="location.validityStartDate" format="yyyy-MM-dd"/>&nbsp;至&nbsp;<@s.date name="location.validityEndDate" format="yyyy-MM-dd"/></span></td>
  </tr>
  <tr>
    <td class="title"><label>成员数</label></td>
    <td class="content"><span>${(location.personNum)!}</span></td>
    <td class="title"><label>党员人数</label></td>
    <td class="content" colspan="2"><span>${(location.partyNum)!}</span></td>
  </tr>
  <@s.if test="location.isEmphasis==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis">
  	<tr>
    <td class="title"><label>注销时间</label></td>
    <td class="content" ><span><@s.date name="location.logOutTime" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>注销原因</label></td>
    <td class="content" colspan="2"><span>${(location.logOutReason)!}</span></td>
  </tr>
  </@s.if>
  <tr>
    <td class="title"><label>简 介</label></td>
    <td colspan="4" class="content"><span>${(location.introduction)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>获得荣誉</label></td>
    <td colspan="4" class="content"><span>${(location.honor)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="4" class="content"><span>${(location.remark)!}</span></td>
  </tr>
</table>

<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#locatigUrl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>