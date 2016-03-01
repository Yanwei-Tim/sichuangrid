<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">UnemployedPeopleTemp</@s.param>
</@s.include>
<script type="text/javascript">

<@pop.formatterProperty name="technologyLevel" domainName="@com.tianque.domain.property.PropertyTypes@TECHNOLOGYLEVEL" />
<@pop.formatterProperty name="unemploymentReason" domainName="@com.tianque.domain.property.PropertyTypes@UNEMPLOYMENTREASON" />
<@pop.formatterProperty name="employmentIntention" domainName="@com.tianque.domain.property.PropertyTypes@EMPLOYMENTINTENTION" />
<@pop.formatterProperty name="trainingIntention" domainName="@com.tianque.domain.property.PropertyTypes@TRAININGINTENTION" />
<@pop.formatterProperty name="unemployedPeopleType" domainName="@com.tianque.domain.property.PropertyTypes@UNEMPLOYEDPEOPLETYPE" />

var gridOptionPrivate = {
	colModel:[
		{name:"unemploymentReason", label:"失业原因",sortable:true,formatter:unemploymentReasonFormatter, width:300,hidden:false},
		{name:"unemploymentDate",label:"失业时间",sortable:true,align:"center", width:100,hidden:false},
		{name:"unemployedPeopleType", label:"人员类型",sortable:true,formatter:unemployedPeopleTypeFormatter, width:100,hidden:true},
		{name:"registerNumber", label:"失业/就业证号",sortable:true,hidden:true},
		{name:"upEnterWorkDate", label:"参加工作时间",sortable:false,align:"center", width:100,hidden:true},
		{name:"originalWorkUnit", label:"原工作单位名称",width:200,sortable:true,hidden:true},
		{name:"originalWorkUnitType", label:"原单位类型", width:90,sortable:true,hidden:true},
		{name:"title", label:"职称",sortable:true, width:100,hidden:true},
		{name:"technologyLevel", label:"技术等级",sortable:true,formatter:technologyLevelFormatter, width:100,hidden:true}
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="UnemployedPeopleTemp"/>
<#assign  moduleCName="失业人员"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
