<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">UnsettledPopulationTemp</@s.param>
</@s.include>


<script type="text/javascript">

<@pop.formatterProperty name="unSettedReason" domainName="@com.tianque.domain.property.PropertyTypes@UNSETTEDREASON"/>
<@pop.formatterProperty name="certificateType" domainName="@com.tianque.domain.property.PropertyTypes@CERTIFICATEHOLD_TYPE" />


	var gridOptionPrivate = {
			colModel:[
      		{name:'unSettedTime',label:'未落户时间',sortable:true,align:'center',width:90,hidden:true},
	        {name:'unSettedReason',label:'未落户原因',formatter:unSettedReasonFormatter,align:'center',sortable:true,width:90},
	        {name:'certificateType',label:'持证种类',formatter:certificateTypeFormatter,align:'center',sortable:true,width:80,hidden:true},
	        {name:'certificateNo',label:'持证编号',sortable:true,width:100,hidden:true}
	    	]
	};

var dialogWidth=800;
var dialogHeight=600;
var importStartRow = 6;

</script>
<#assign  moduleName="UnsettledPopulationTemp"/>
<#assign  moduleCName="未落户人口"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
