<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">SuperiorVisitTemp</@s.param>
</@s.include>
<script type="text/javascript">

<@pop.formatterProperty name="visitState" domainName="@com.tianque.domain.property.PropertyTypes@SUPERIOR_VISIT_STATUS" />

var gridOptionPrivate = {
	colModel:[
		{name:"visitState", label:"上访状态",sortable:false,width:100,align:"center", formatter:visitStateFormatter,hidden:false},
		{name:"visitReason",label:"上访原因", width:130,hidden:false}
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="SuperiorVisitTemp"/>
<#assign  moduleCName="重点上访人员"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
