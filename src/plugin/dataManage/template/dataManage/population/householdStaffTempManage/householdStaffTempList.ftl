<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">HouseholdStaffTemp</@s.param>
</@s.include>

<script type="text/javascript">
<@pop.formatterProperty name="relationShipWithHead" domainName="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" />
<@pop.formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
var gridOptionPrivate = {
	colModel:[
        {name:'accountNumber',index:'accountNumber',label:'户口号',width:90,hidden:true,sortable:true},
        {name:'relationShipWithHead',index:'relationShipWithHead',label:'与户主关系',width:150,formatter:relationShipWithHeadFormatter,hidden:false,sortable:true},
		{name:'residenceType',index:'residenceType',label:'户口类别',formatter:residenceTypeFormatter,width:100,hidden:true,sortable:true}
	]
};
var dialogWidth=800;
var dialogHeight=640;
</script>
<#assign  moduleName="HouseholdStaffTemp"/>
<#assign  moduleCName="户籍人口"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
