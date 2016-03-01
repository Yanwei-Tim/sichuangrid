<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<#setting number_format="0.##########">
<@s.include value="/includes/baseInclude.jsp"/>

<input id="accountNumber" type="hidden" name="accountNumber" value="${(population.accountNumber)!}" />
<input type="hidden" id="_idCardNo"   />
<input type="hidden" id="_householdName"  />
<input type="hidden" id="_mobileNumber"  />
<input type="hidden" id="_telephone"  />
<input type="hidden" id="_homePhone"  />
<input type="hidden" id="_houseMarch"  />
<input id="outGoneValue" type="hidden" name="_outGone" value="${(population.outGone)?string("true", "false")}" />
<input type="hidden" id="_populationOrgId" value=${(population.organization.id)! } />
<input type="hidden" id="houseFamilyId" value="${(population.censusRegisterFamily.id)!}" />

<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="housePopulationOrgName">${(population.organization.orgName)!}</td>
  </tr>
  <tr>
    <td class="title"><label>户口号</label></td>
    <td class="content"><span>${(population.accountNumber)!}</span></td>
	<td class="title"><label>与户主关系</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" defaultValue="${(population.relationShipWithHead.id)!}" /><@s.if test="population.relationShipWithHeadText!=null && !population.relationShipWithHeadText.equals('')">(${(population.relationShipWithHeadText)! })</@s.if></span></td>
  </tr>
  <tr>
    <td class="title"><label>人户状态</label></td>
    <td class="content"><span ><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RESIDENT_STATUS" defaultValue="${(population.residentStatus.id)!}" /></span></td>
    <td class="title"><label>是否外出</label></td>
    <td class="content"><span id="isout"></span></td>
  </tr>
  <tr>
    <td class="title"><label>外出时间</label></td>
    <td class="content"><span><@s.date name="population.reasonsDate" format="yyyy-MM-dd" /></span></td>
 	<td class="title"><label>外出原因</label></td>
    <td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@OUT_REASON" defaultValue="${(population.outReasons.id)!}" /></span></td>
  </tr>
  <tr>
  	<td class="title"><label>外出去向</label></td>
    <td class="content"><span id="nativePlace">${(population.outProvince)!}${(population.outCity)!}${(population.outDistrict)!}</span></td>
    <td class="title"><label>外出详址</label></td>
    <td class="content"><span>${(population.goOutDetailedAddress)! }</span></td>
  </tr>
  <tr>
    <td class="title"><label>户口类别</label></td>
    <td colspan="3" class="content" id="population.actualtype">
    	<span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" defaultValue="${(population.residenceType.id)!}" /></span>
    </td>
  </tr>
</table>
<script type="text/javascript">
<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<@pop.formatterProperty name="relationShipWithHead" domainName="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" />

$(document).ready(function(){
	outGoneFormatter();
});
function outGoneFormatter(){
	var str = "";
	if($("#outGoneValue").val()=="true"||$("#outGoneValue").val()==true)
		str += "是";
	if($("#outGoneValue").val() =="false"||$("#outGoneValue").val()==false)
		str += "否";
	$("#isout").html(str);
}

</script>
<style type="text/css">
.label {
	color: #999;
}
</style>