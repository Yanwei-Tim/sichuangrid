<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">MentalPatientTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="dangerLevel" domainName="@com.tianque.domain.property.PropertyTypes@MENTALPATIENT_DANGEROUS_LEVEL" />
<@pop.formatterProperty name="psychosisType" domainName="@com.tianque.domain.property.PropertyTypes@PSYCHOSIS_TYPES" />

function dangerLevelColor(el,options,rowData){
	var displayName=dangerLevelFormatter(el,options,rowData);
	if(displayName=='undefined'||displayName=='')
		return '';
	else if(displayName=='高')
		return '<span>高：<span style="color:#ff0000;">█████</span></span>';
	else if(displayName=='中')
		return '<span>中：<span style="color:#ffcc00;">███</span></span>';
	else if(displayName=='低')
		return '<span>低：<span style="color:#99cc00;">█</span></span>';
	else
		return '';
}
var gridOptionPrivate = {
	colModel:[
		{name:'psychosisName',label:"患病名称",hidden:true,sortable:true,width:90},
		{name:'psychosisType',label:"精神病类型",sortable:true,width:100,hidden:true, formatter:psychosisTypeFormatter},
		{name:'isTreat',label:"是否接受过治疗",hidden:true,sortable:true,width:130},
		{name:'cureDepartment',label:"康复机构",hidden:true,sortable:true,width:90}		
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="MentalPatientTemp"/>
<#assign  moduleCName="严重精神障碍患者"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>