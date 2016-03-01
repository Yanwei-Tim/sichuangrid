<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">DruggyTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="drugReason" domainName="@com.tianque.domain.property.PropertyTypes@DRUG_REASON" />
<@pop.formatterProperty name="drugSource" domainName="@com.tianque.domain.property.PropertyTypes@DRUG_SOURCE" />
<@pop.formatterProperty name="detoxicateCase" domainName="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CASE" />
<@pop.formatterProperty name="detoxicateCondition" domainName="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CONDITION" />
function isAdanon(el, options, rowData){
	if(true==rowData.adanon){
		return "是";
	}else{
		return "否";
	}
}

var gridOptionPrivate = {
	colModel:[
		{name:"drugReason", label:"吸毒原因", formatter:drugReasonFormatter,hidden:true,sortable:true},
		{name:"drugFristDate",label:"首吸时间", width:100,hidden:false,align:"center",sortable:true},
		//{name:"drugSource", label:"毒品来源",sortable:true,hidden:false, width:100, formatter:drugSourceFormatter},
		{name:"detoxicateCase", label:"戒毒情况",sortable:true, hidden:true,width:100, formatter:detoxicateCaseFormatter},
		{name:"detoxicateCondition", label:"吸毒状态",sortable:true,hidden:false, width:100,formatter:detoxicateConditionFormatter,align:"center"},
		{name:"adanon", label:"是否服美沙酮戒毒",width:140,sortable:false,hidden:true,formatter:isAdanon,align:"center"},
		{name:"controlActuality", label:"管控现状", width:200,sortable:true,hidden:true},
		{name:"drugType",label:"滥用毒品种类", width:200,sortable:true,hidden:true}
	]
};
var dialogWidth=800;
var dialogHeight=640;
</script>
<#assign  moduleName="DruggyTemp"/>
<#assign  moduleCName="吸毒人员"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>


