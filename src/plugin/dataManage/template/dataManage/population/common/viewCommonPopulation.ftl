<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<input id="provinceValue"	type="hidden" name="" value="${(population.province)!}" />
<input id="cityValue"	type="hidden" name="" value="${(population.city)!}" />
<input id="districtValue"	type="hidden" name="" value="${(population.district)!}" />
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="commonPopulationOrgName">${(population.organization.orgName)!}</td>
    <td class="imagesTX" rowspan="7">
    <input id="_imgUrl" type="hidden" name="population.imgUrl" value="${(population.imgUrl)!}"/>
		<@s.if test='null!=population.imgUrl && !"".equals(population.imgUrl)'>
			<img id="popl" name="population.imgUrl" src="${path }${population.imgUrl}" width="148px"  />
		</@s.if>
		<@s.else>
			<img id="img" name="population.imgUrl" src="${path }/resource/images/head.png" width="148px"  />
		</@s.else>
	</td>
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
    <td class="content"><span>${(population.mobileNumber)! }</span></td>
    <td class="title"><label>固定电话</label></td>
    <td class="content"><span>${(population.telephone )!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>政治面貌</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${(population.politicalBackground.id)!}" /></span></td>
    <td class="title"><label>文化程度</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${(population.schooling.id)!}" /></span></td>
  </tr>
  
  <tr>
    <td class="title"><label>身高</label></td>
    <td class="content"><span>${(population.stature)! }(CM)</span></td>
    <td class="title"><label>血型</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" defaultValue="${(population.bloodType.id)!}" /></span></td>
  </tr>
  
  <tr>
    <td class="title"><label>电子邮件</label></td>
    <td class="content"><span>${(population.email)! }</span></td>
    <td class="title"><label>联系方式</label></td>
    <td class="content" colspan="2"><span>${(population.telephone)! }</span></td>
  </tr>
  
  <tr>
    <td class="title"><label>职业</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${(population.career.id)!}" /></span></td>
  	<td class="title"><label>工作单位或就读学校</label></td>
    <td class="content" colspan="2"><span>${(population.workUnit)! }</span></td>
  </tr>
   <@s.if test='"unsettledPopulationTemp"==tempClassName '>
   <tr>
    <td class="title"><label>持证种类</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@CERTIFICATEHOLD_TYPE" defaultValue="${(population.certificateType.id)!}" /></span></td>
    <td class="title"><label>持证编号</label></td>
    <td colspan="2" class="content"><span>${(population.certificateNo)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>未落户时间</label></td>
    <td class="content"><span><@s.date name="population.unSettedTime" format="yyyy-MM-dd"/></span></td>
    <td class="title"><label>未落户原因</label></td>
    <td colspan="2" class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@UNSETTEDREASON" defaultValue="${(population.unSettedReason.id)!}" /></span></td>
  </tr>
	</@s.if>
	
  <tr>
    <td class="title"><label>婚姻状况</label></td>
    <td  class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${(population.maritalState.id)!}" /></span></td>
  	 <td class="title"><label>户籍地址</label></td>
    <td colspan="2" class="content"><span id="nativePlace"></span></td>
  </tr>
  <tr>
    <td class="title"><label>宗教信仰</label></td>
    <td class="content" colspan="5"><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@FAITH" defaultValue="${(population.faith.id)!}" /><span></span></td>
  </tr>
  <tr>
    <td class="title"><label>户籍地详址</label></td>
    <td class="content" colspan="5"><span>${(population.nativePlaceAddress)!}</span></td>
  </tr>
  
  <tr>
    <td class="title"><label>临时居所</label></td>
    <td colspan="4" class="content"><span>${(population.otherAddress)!}</span></td>
  </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td colspan="4" class="content"><span><pre>${(population.remark)!}</pre></span></td>
  </tr>
</table>
<script>
$(document).ready(function(){
	nativePlaceFormatter();
	
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#popl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}
	
});

function nativePlaceFormatter(){
	var str = "";
	if($("#provinceValue").val() != null &&$("#provinceValue").val()!="")
		str += $("#provinceValue").val();
	if($("#cityValue").val() != null &&$("#cityValue").val()!="")
		str += $("#cityValue").val();
	if($("#districtValue").val() != null &&$("#districtValue").val()!="")
		str += $("#districtValue").val();
	$("#nativePlace").html(str);
}

</script>
