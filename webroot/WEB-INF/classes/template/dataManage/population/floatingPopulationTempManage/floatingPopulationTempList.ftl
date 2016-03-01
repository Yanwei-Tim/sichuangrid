<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">FloatingPopulationTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="inflowingReason" domainName="@com.tianque.domain.property.PropertyTypes@INFLOWING_REASON" />
<@pop.formatterProperty name="registrationType" domainName="@com.tianque.domain.property.PropertyTypes@REGISTRATIONTYPE" />
<@pop.formatterProperty name="healthState" domainName="@com.tianque.domain.property.PropertyTypes@HEALTH_STATE" />

function inflowingSourceFormatter(el, options, rowData){
	var str = "";
	if(rowData.inflowingProvince != null)
		str += rowData.inflowingProvince;
	if(rowData.inflowingCity != null)
		str += rowData.inflowingCity;
	if(rowData.inflowingDistrict != null)
		str += rowData.inflowingDistrict;
	return str;
}

var dialogWidth=800;
var dialogHeight=650;
var gridOptionPrivate = {
  	colModel:[
        {name:'inflowingReason', sortable:true,formatter:inflowingReasonFormatter, label:'流入原因', width:150},
        {name:'inflowingDate',index:'inflowingDate', sortable:true, hidden:true,label:'流入时间',align:'center',width:80},
        //{name:'inflowingProvince',index:'inflowingProvince', sortable:true,label:'流入来源',hidden:true,formatter:inflowingSourceFormatter,width:150},
        {name:'registrationType', sortable:true,formatter:registrationTypeFormatter, label:'登记证情况', width:90,hidden:true},
        {name:'registerDate', sortable:true, label:'登记日期', width:80,hidden:true},
        {name:'expectedDatedue', sortable:true, label:'预计到期日期', width:100,hidden:true}
  	]
}
</script>
<#assign  moduleName="FloatingPopulationTemp"/>
<#assign  moduleCName="流动人口"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
