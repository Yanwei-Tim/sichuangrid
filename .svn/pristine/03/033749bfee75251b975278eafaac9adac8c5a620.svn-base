<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>

<script type="text/javascript">
<pop:formatterProperty name="companyType" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" />
<pop:formatterProperty name="supervisoryLevel" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL" />
<pop:formatterProperty name="economicNature" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" />
<pop:formatterProperty name="facilitiesCategory" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FACILITIESCATEGORY" />
<pop:formatterProperty name="securityClassification" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SECURITYCLASSIFICATION" />
<pop:formatterProperty name="fireFightingLevel" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" />
var gridOption={
	colModel:[
        {name:"id",index:"id",hidden:true,frozen:true},
	    {name:"companyName",id:"companyName",label:'单位名称',formatter:companyNameFormatter,width:150,frozen:true},
	    {name:"companyType",label:'单位类别',align:'center',width:80,formatter:companyTypeFormatter},
	    {name:"corporateRepresentative",label:'法人代表',align:'center',width:150},
	    {name:"companyAddress",label:'单位地址',align:'center',width:150},
	    {name:"supervisoryLevel",label:'管理级别',align:'center',width:80,formatter:supervisoryLevelFormatter},
	    {name:"supervisoryDepartment",label:'管理部门',align:'center',width:150},
	    {name:"securityChief",label:'治安负责人',align:'center',width:100},
	    {name:"isKey",label:'是否重点单位',formatter:isKeyFormatter,align:'center',width:100},
	    {name:"fireFightingLevel",label:'消防等级',align:'center',width:100,formatter:fireFightingLevelFormatter},
	    {name:"organization.orgName",label:'所属网格',align:'center',width:100,hidden:true},
	    {name:"idCardNo",label:'身份证号码',align:'center',width:100,hidden:true},
	    {name:"telephone",label:'单位电话',align:'center',width:100,hidden:true},
	    {name:"facsimile",label:'单位传真',align:'center',width:100,hidden:true},
	    {name:"businessLicenseNo",label:'营业执照号码',align:'center',width:100,hidden:true},
	    {name:"orgCode",label:'组织机构代码',align:'center',width:100,hidden:true},
	    {name:"registeredCapital",label:'注册资本(元)',align:'center',width:100,hidden:true},
	    {name:"economicNature",label:'经济性质',align:'center',width:100,hidden:true,formatter:economicNatureFormatter},
	    {name:"expiryDate",label:'有效期至',align:'center',width:100,hidden:true},
	    {name:"registrationDate",label:'注册日期',align:'center',width:100,hidden:true},
	    {name:"businessScope",label:'经营范围',align:'center',width:100,hidden:true},
	    {name:"registrationAddress",label:'注册地址',align:'center',width:100,hidden:true},
	    {name:"employeesNum",label:'从业人数(人)',align:'center',width:100,hidden:true},
	    {name:"competentDepartment",label:'主管部门',align:'center',width:100,hidden:true},
	    {name:"remark",sortable:false,label:"备注",hidden:true,width:100},
	    {name:"isEmphasis",sortable:false,label:"是否注销",hidden:true,hidedlg:true,width:100},
	    {name:"logOutTime",sortable:false,label:"注销时间",align:'center',hidden:true,width:100},
	    {name:"logOutReason",sortable:false,label:"注销原因",align:'center',hidden:true,width:100}
	]
};
var dialogWidth=800;
var dialogHeight=600;	

function isKeyFormatter(el, options, rowData){
	var str ="否";
	if(el){
		str="是"
	}
	return str;
}
function companyNameFormatter(el,options,rowData){
	if(rowData.isEmphasis==false||rowData.isEmphasis=='false'){
		return "<font color='#778899'>"+rowData.companyName+"</font>";
	}
	return "<font color='#000'>"+rowData.companyName+"</font>";
}
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
</script>
<jsp:include page="/baseinfo/commonPopulation/commonLocationList.jsp">
	<jsp:param value="LaborEmploymentUnit" name="moduleName"/>
	<jsp:param value="劳动用工单位" name="moduleCName"/>
</jsp:include>