<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">HandicappedTemp</@s.param>
</@s.include>

<script type="text/javascript">



<@pop.formatterProperty name="skillProfile" domainName="@com.tianque.domain.property.PropertyTypes@SKILLS_AND_SPECIALITIES" />
<@pop.formatterProperty name="disability" domainName="@com.tianque.domain.property.PropertyTypes@DISABILITY_STATUS" />
<@pop.formatterProperty name="disabilityType" domainName="@com.tianque.domain.property.PropertyTypes@DISABILITY_TYPE" />
<@pop.formatterProperty name="workProfile" domainName="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" />


var gridOptionPrivate = {
		colModel : [
		    {name : "guardianName",index : 'guardianName',label : '监护人',sortable:true,width : 80,hidden : true}, 
		    {name : 'disabilityCardNo',index : 'disabilityCardNo',label : '残疾证号',hidden : true,sortable:true,width : 80}, 
		    {name : 'disabilityType',label : '残疾类型',formatter : disabilityTypeFormatter,sortable : true,hidden : false,width : 80}, 
		    {name : 'disabilityReason',index : 'disabilityReason',label : '残疾原因',sortable:true,hidden : false,width : 120}, 
		    {name : "disabilityDate",index : 'disabilityDate',label : '残疾时间',sortable:true,width : 80,align : 'center',hidden : true}, 
		    {name : 'disability',label : '残疾等级',formatter : disabilityFormatter,sortable : true,hidden : true,width : 80}, 
		    {name : 'skillProfile',index : 'skillProfile',label : '技能特长',formatter : skillProfileFormatter,sortable:true,hidden : true,width : 80}, 
		    {name : 'workProfile',index : 'workProfile',label : '劳动能力',formatter : workProfileFormatter,sortable:true,hidden : true,width : 80} 
		]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="HandicappedTemp"/>
<#assign  moduleCName="残疾人"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
