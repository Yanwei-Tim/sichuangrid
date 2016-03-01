<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">AidspopulationsTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="infectway" domainName="@com.tianque.constant.PropertyTypes@INFECT_WAY" />
<@pop.formatterProperty name="violationsofthelaw" domainName="@com.tianque.domain.property.PropertyTypes@VIOLATIONSOFTHELAW" />
<@pop.formatterProperty name="crimetype" domainName="@com.tianque.domain.property.PropertyTypes@CRIMETYPE" />
<@pop.formatterProperty name="receivedlevel" domainName="@com.tianque.constant.PropertyTypes@RECEIVED_LEVEL" />

var gridOptionPrivate = {
	colModel:[
		{name:"infectway",index:"infectway", label:"感染途径",formatter:infectwayFormatter,sortable:true,width:100},
		{name:"violationsofthelaw",index:'violationsofthelaw',label:"违法情况",align:"center",formatter:violationsofthelawFormatter, sortable:true,width:100,hidden:true},
		{name:"crimetype",index:'crimetype', label:"犯罪类型",sortable:true,formatter:crimetypeFormatter, width:80,hidden:true},
		{name:"receivedorganization",index:'receivedorganization', label:"收治机构",sortable:true,align:"center", width:80,hidden:true},
		{name:"receivedlevel",index:'receivedlevel', label:"收治机构层级",formatter:receivedlevelFormatter,sortable:true,align:"center", width:80,hidden:true}
	]
};
var dialogWidth=800;
var dialogHeight=640;
</script>
<#assign  moduleName="AidspopulationsTemp"/>
<#assign  moduleCName="艾滋病人员"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>


