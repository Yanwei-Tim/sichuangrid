<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">rectificativePersonTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="executeType" domainName="@com.tianque.domain.property.PropertyTypes@EXECUTE_TYPE" />
function isAdanon(el, options, rowData){
	if(true==rowData.adanon){
		return "是";
	}else{
		return "否";
	}
}

var gridOptionPrivate = {
	colModel:[
		{name:"executeType",label:"刑法执行类别",index:"executeType",width:130, formatter:executeTypeFormatter,align:"center",sortable:true},
		{name:"rectifyStartDate",label:"社区矫正开始时间",align:"center",width:145,sortable:true},
		{name:"rectifyEndDate",label:"社区矫正结束时间",align:"center",width:145,sortable:true},
		{name:"penaltyTerm",label:"原判刑期",sortable:true,width:100, hidden:true},
		{name:"recentSituation",label:"近况描述 ",sortable:true,width:200, hidden:true}
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="rectificativePersonTemp"/>
<#assign  moduleCName="社区矫正人员"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
