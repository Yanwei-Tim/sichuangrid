<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">DangerousGoodsPractitionerTemp</@s.param>
</@s.include>

<script type="text/javascript">
<@pop.formatterProperty name="dangerousWorkingType" domainName="@com.tianque.domain.property.PropertyTypes@DANGEROUS_WORKING_TYPE" />

function periodOfValidityFormatter(el, options, rowData){
	return rowData.periodOfValidityStart + "至" + rowData.periodOfValidityEnd;
}
var gridOptionPrivate = {
	colModel:[				    
		 {name:'dangerousWorkingType',label:"危险从业类别",formatter:dangerousWorkingTypeFormatter,sortable:true,align:"center",width:80},		
		 {name:'workingCertificate',label:"从业资格证书",width:150,hidden:true,sortable:true},
		 {name:'workingCertificateNo',label:"从业资格证书号",width:150,hidden:true,sortable:false},
		 {name:'periodOfValidity',label:"有效期",formatter:periodOfValidityFormatter,align:"center",width:180,hidden:true,sortable:true}		
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="DangerousGoodsPractitionerTemp"/>
<#assign  moduleCName="危险品从业人员"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
