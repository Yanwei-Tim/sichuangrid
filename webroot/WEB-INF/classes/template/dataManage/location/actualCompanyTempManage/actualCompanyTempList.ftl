<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ActualCompanyTemp</@s.param>
</@s.include>

<script type="text/javascript">
<@pop.formatterProperty name="companyType" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" />
<@pop.formatterProperty name="supervisoryLevel" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL" />
<@pop.formatterProperty name="economicNature" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" />
<@pop.formatterProperty name="facilitiesCategory" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FACILITIESCATEGORY" />
<@pop.formatterProperty name="securityClassification" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SECURITYCLASSIFICATION" />
<@pop.formatterProperty name="fireFightingLevel" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" />

function isKeyFormatter(el, options, rowData){
	var str ="否";
	if(el){
		str="是"
	}
	return str;
}

var gridOption={
	colModel:[
        {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
    	{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:65,frozen:true,sortable:false,align:'center' },
	    {name:"organization.orgName",sortable:false,label:'所属网格',align:'center',width:150,hidden:true},
	    {name:"name",id:"name",label:'单位名称',sortable:true,formatter:nameFontClaim,width:200,frozen:true},
	    {name:"address",label:'单位地址',sortable:true,width:200},
	    {name:"manager",sortable:true,label:'法人代表',width:100},
	    {name:"companyType",label:'单位类别',sortable:true,width:80,formatter:companyTypeFormatter},
	    {name:"businessLicenseNo",label:'营业执照号码',sortable:true,width:100},
	    {name:"orgCode",label:'组织机构代码',sortable:true,width:100},
	    {name:"supervisoryLevel",label:'管理级别',align:'center',sortable:true,width:80,formatter:supervisoryLevelFormatter,hidden:true},
	    {name:"supervisoryDepartment",label:'管理部门',sortable:true,width:150,hidden:true},
	    {name:"securityChief",label:'治安负责人',sortable:true,width:100,hidden:true},
	    {name:"isKey",label:'是否重点单位',sortable:true,formatter:isKeyFormatter,align:'center',width:100,hidden:true},
	    {name:"fireFightingLevel",sortable:true,label:'消防等级',align:'center',width:100,formatter:fireFightingLevelFormatter,hidden:true},
	    {name:"idCardNo",label:'身份证号码',sortable:true,align:'center',width:150,hidden:true},
	    {name:"telephone",label:'单位电话',sortable:true,align:'center',width:130,hidden:true},
	    {name:"fax",label:'单位传真',sortable:true,align:'center',width:130,hidden:true},
	    {name:"registeredCapital",label:'注册资本(元)',sortable:true,width:100,hidden:true},
	    {name:"economicNature",label:'经济性质',width:100,sortable:true,hidden:true,formatter:economicNatureFormatter},
	    {name:"expiryDate",label:'有效期至',align:'center',sortable:true,width:100,hidden:true},
	    {name:"registrationDate",label:'注册日期',align:'center',sortable:true,width:100,hidden:true},
	    {name:"businessScope",label:'经营范围',width:100,sortable:true,hidden:true},
	    {name:"registrationAddress",label:'注册地址',sortable:true,width:180,hidden:true},
	    {name:"employeesNum",label:'从业人数(人)',sortable:true,width:100,hidden:true},
	    {name:"competentDepartment",label:'主管部门',sortable:true,width:100,hidden:true},
	    {name:"remark",sortable:true,label:"备注",hidden:true,width:100}
	]
};
var dialogWidth=900;
var dialogHeight=600;	


jQuery.validator.addMethod("fund", function(value, element) {
    var decimal = /^-?\d+(\.\d{1,4})?$/;
    if (value==""){
	     return true;
	 }
    if(value>=0){
    	return this.optional(element) || (decimal.test(value));
    }
    return false;
});

function getLocationName(rowData){
	return $(rowData.companyName).text();
}
</script>
<#assign moduleName="ActualCompanyTemp"/>
<#assign moduleCName="实有单位"/>
<#assign isNew=1/>
<#assign bigType="location"/>
<#assign  importStartRow=6/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>