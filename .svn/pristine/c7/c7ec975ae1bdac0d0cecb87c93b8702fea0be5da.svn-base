<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
		
<body>
<table class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${(location.organization.orgName)!}</td>
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
    <td class="title"><label>名称</label></td>
    <td class="content" colspan="3" ><span>${(location.name)!}</span></td>
  </tr>
 <tr>
    <td class="title"><label>住所</label></td>
    <td class="content" colspan="3" ><span>${(location.address)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>营业执照号码</label></td>
    <td class="content"  ><span>${(location.licenseNumber)!}</span></td>
    <td class="title"><label>有效期</label></td>
    <td class="content"><span>从&nbsp;<@s.date name="location.validityStartDate" format="yyyy-MM-dd" />&nbsp;至&nbsp;<@s.date name="location.validityEndDate" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>类别</label></td>
    <td class="content" ><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE" defaultValue="${(location.type.id)!}" /></span></td>
    <td class="title"><label>负责人（法人）</label></td>
    <td class="content" ><span>${(location.manager)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>固定电话</label></td>
    <td class="content" ><span>${(location.telephone)!}</span></td>
     <td class="title"><label>联系手机</label></td>
    <td class="content" ><span>${(location.mobileNumber)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>传真号码</label></td>
    <td class="content" ><span>${(location.foxNumber)!}</span></td>
     <td class="title"><label>面积</label></td>
    <td class="content" colspan="2"><span>${(location.area)!}</span>
    <font size="1">M</font><font size="1"><sup>2</sup></font> 
    </td>
  </tr>
    <tr>
    <td class="title"><label>从业人数</label></td>
    <td class="content" ><span>${(location.employeeNumber)!}</span></td>
     <td class="title"><label>党员人数</label></td>
    <td class="content" colspan="2"><span>${(location.partyNum)!}</span></td>
  </tr>
  <@s.if test="location.isEmphasis==@com.tianque.service.vo.IsEmphasis@IsNotEmphasis">
  	<tr>
    <td class="title"><label>注销时间</label></td>
    <td class="content" ><span><@s.date name="location.logoutDetail.logoutDate" format="yyyy-MM-dd" /></span></td>
    <td class="title"><label>注销原因</label></td>
    <td class="content" colspan="2"><span>${(location.logoutDetail.logoutReason)!}</span></td>
  </tr>
  </@s.if>
  <tr>
	 <td class="title"><label>简介</label></td>
	 <td class="content" colspan="4" ><span><pre>${(location.introduction)!}</pre></span></td>
 </tr>
  
 
  <tr>
    <td class="title"><label>荣誉</label></td>
    <td class="content" colspan="4" ><span><pre>${(location.honor)!}</pre></span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" colspan="4">${(location.remark)!}</td>
  </tr>
</table>
</body>

<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#locrl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>