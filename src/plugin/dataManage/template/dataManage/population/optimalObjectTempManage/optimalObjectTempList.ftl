<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">OptimalObjectTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="optimalCardType" domainName="@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE" />
<@pop.formatterProperty name="abilityLiving" domainName="@com.tianque.domain.property.PropertyTypes@VIABILITY" />
<@pop.formatterProperty name="supportSituation" domainName="@com.tianque.domain.property.PropertyTypes@SUPPORT_STATUS" />

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
		{name:"optimalCardType", index:'optimalCardType', label:'优抚类型',sortable:true, width:100, formatter:optimalCardTypeFormatter, frozen : true,hidden:false},
		{name:"abilityLiving", index:'abilityLiving', label:'生活能力',sortable:true, width:80, formatter:abilityLivingFormatter, frozen : true,hidden:false},
		{name:"optimalCardNo", index:'optimalCardNo', label:'优待证号', width:100, frozen : true,sortable:true,hidden:true,hidden:true},
		{name:"supportSituation", index:'supportSituation', label:'供养情况', width:100, formatter:supportSituationFormatter, sortable:true,frozen : true,hidden:true}
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="OptimalObjectTemp"/>
<#assign  moduleCName="优抚对象"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
