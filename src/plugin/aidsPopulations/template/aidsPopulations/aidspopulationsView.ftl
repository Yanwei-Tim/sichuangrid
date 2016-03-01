<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<#setting number_format="0.##########">
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${(population.imgUrl)!}"/>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="aidspopulationsPopulationOrgName">${(population.organization.orgName)!}</td>
    <td rowspan="7">
    <@s.if test='null!=population.imgUrl && !"".equals(population.imgUrl)'>
		<img id="aidsImg" name="population.imgUrl" src="" width="148px"/>
	</@s.if>
	<@s.else>
		<img id="img" name="population.imgUrl" src="${path }/resource/images/head.png" width="148px"/>
	</@s.else></td>
  </tr>
  <tr>
    <td class="title"><label>姓 名</label></td>
    <td class="content"><span>${(population.name)!}</span></td>
    <td class="title"><label>身份证号码</label></td>
    <td class="content"><span>${(population.idCardNo)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>曾用名/别名</label></td>
    <td class="content"><span>${(population.usedName)!}</span></td>
    <td class="title"><label>性别</label></td>
   <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${(population.gender.id)!}" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>民族</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${(population.nation.id)!}" /></span></td>
    <td class="title"><label>出生日期</label></td>
    <td class="content"><span><@s.date name="population.birthday" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>联系手机</label></td>
    <td class="content"><span>${(population.mobileNumber)!}</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${(population.telephone)!}</span></td>
  </tr>
   <tr>
   	  <td class="title"><label>关注程度</label></td>
	 <td  colspan="3" class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(population.attentionExtent.id)!}"/></span></td>
  </tr>
  <tr>
     <td class="title"><label>感染途径 </label></td>
	 <td class="content"><span id=""><@pop.PropertyDictViewTag name="@com.tianque.constant.PropertyTypes@INFECT_WAY" defaultValue="${(population.infectway.id)!}" /></span></td>
	 <td class="title"><label>违法情况 </label></td>
	 <td class="content"><span id=""><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@VIOLATIONSOFTHELAW" defaultValue="${(population.violationsofthelaw.id)!}" /></span></td>
  </tr>
  <tr>
   	 <td class="title"><label>犯罪类型</label></td>
	 <td class="content"><span id=""><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CRIMETYPE" defaultValue="${(population.crimetype.id)!}" /></span></td>
     <td class="title"><label>收治机构</label></td>
	 <td class="content" colspan="2"><span id="">${(population.receivedorganization)!}</span></td>
  </tr>
  <tr>
   	 <td class="title"><label>收治机构所属层级</label></td>
	 <td colspan="4" class="content"><span id=""><@pop.PropertyDictViewTag name="@com.tianque.constant.PropertyTypes@RECEIVED_LEVEL" defaultValue="${(population.receivedlevel.id)!}" /></span></td>
  </tr>
</table>


<script>
$(document).ready(function(){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#aidsImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
});
</script>
