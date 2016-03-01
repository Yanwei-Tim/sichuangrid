<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="ActualCompany" name="moduleName"/>
</jsp:include>

<script type="text/javascript">
<pop:formatterProperty name="companyType" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_COMPANYTYPE" />
<pop:formatterProperty name="supervisoryLevel" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SUPERVISORYLEVEL" />
<pop:formatterProperty name="economicNature" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" />
<pop:formatterProperty name="facilitiesCategory" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FACILITIESCATEGORY" />
<pop:formatterProperty name="securityClassification" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_SECURITYCLASSIFICATION" />
<pop:formatterProperty name="fireFightingLevel" domainName="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" />
function operatorFormatter(el,options,rowData){
	
	return "<pop:JugePermissionTag ename='updateActualCompany'><a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteActualCompany'><a href='javascript:;' onclick='deleteOperator(event,"+(rowData.id)+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function companyNameFormatter(el,options,rowData){
	if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
		return " <a href='javascript:;' <pop:JugePermissionTag ename='viewActualCompany'> onclick='viewActualCompany("+rowData.id+")' </pop:JugePermissionTag> ><font color='#778899'>"+rowData.companyName+"</font></a>";
	}
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewActualCompany'> onclick='viewActualCompany("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.companyName+"</a>";
}
var gridOption={
	colModel:[
		{name:"id",index:"id",sortable:false,hidden:false,frozen:true},
	    {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
	    {name:"companyName",id:"companyName",label:'单位名称',sortable:true,formatter:companyNameFormatter,width:200,frozen:true},
	    {name:"companyAddress",label:'单位地址',sortable:true,width:200},
	    {name:"corporateRepresentative",sortable:true,label:'法人代表',width:100},
	    {name:"companyType",label:'单位类别',sortable:true,width:80,formatter:companyTypeFormatter},
	    {name:"businessLicenseNo",label:'营业执照号码',sortable:true,width:100},
	    {name:"orgCode",label:'组织机构代码',sortable:true,width:100},
	    {name:'hasServiceTeamMember', sortable:true, label:'有无治安负责人', width:140,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime', sortable:true, label:'最新巡场时间',align:'center', width:140},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
	    {name:'updateDate', sortable:true, align:'center', label:'数据更新时间', width:140},
	    {name:"supervisoryLevel",label:'管理级别',align:'center',sortable:true,width:80,formatter:supervisoryLevelFormatter,hidden:true},
	    {name:"supervisoryDepartment",label:'管理部门',sortable:true,width:150,hidden:true},
	    {name:"securityChief",label:'治安负责人',sortable:true,width:100,hidden:true},
	    {name:"isKey",label:'是否重点单位',sortable:true,formatter:isKeyFormatter,align:'center',width:100,hidden:true},
	    {name:"fireFightingLevel",sortable:true,label:'消防等级',align:'center',width:100,formatter:fireFightingLevelFormatter,hidden:true},
	    {name:"organization.orgName",sortable:false,label:'所属网格',align:'center',width:150,hidden:true},
	    {name:"idCardNo",label:'身份证号码',sortable:true,align:'center',width:150,hidden:true},
	    {name:"telephone",label:'单位电话',sortable:true,align:'center',width:130,hidden:true},
	    {name:"facsimile",label:'单位传真',sortable:true,align:'center',width:130,hidden:true},
	    {name:"registeredCapital",label:'注册资本(元)',sortable:true,width:100,hidden:true},
	    {name:"economicNature",label:'经济性质',width:100,sortable:true,hidden:true,formatter:economicNatureFormatter},
	    {name:"expiryDate",label:'有效期至',align:'center',sortable:true,width:100,hidden:true},
	    {name:"registrationDate",label:'注册日期',align:'center',sortable:true,width:100,hidden:true},
	    {name:"businessScope",label:'经营范围',width:100,sortable:true,hidden:true},
	    {name:"registrationAddress",label:'注册地址',sortable:true,width:180,hidden:true},
	    {name:"employeesNum",label:'从业人数(人)',sortable:true,width:100,hidden:true},
	    {name:"competentDepartment",label:'主管部门',sortable:true,width:100,hidden:true},
	    {name:"remark",sortable:true,label:"备注",hidden:true,width:100},
	    {name:"isEmphasis",sortable:false,label:"是否注销",align:'center',hidden:true,hidedlg:true,width:100},
	    {name:"logOutTime",sortable:true,label:"注销时间",align:'center',hidden:true,width:100},
	    {name:"logOutReason",sortable:true,label:"注销原因",hidden:true,width:100}
	]
};
var dialogWidth=900;
var dialogHeight=600;	

function isKeyFormatter(el, options, rowData){
	var str ="否";
	if(el){
		str="是"
	}
	return str;
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

function getLocationName(rowData){
	return $(rowData.companyName).text();
}
</script>
<jsp:include page="/baseinfo/commonPopulation/commonLocationList.jsp">
	<jsp:param value="ActualCompany" name="moduleName"/>
	<jsp:param value="实有单位" name="moduleCName"/>
	<jsp:param value="1" name="isNew"/>
</jsp:include>
<div style="display:none;">
	<input type="hidden" id="_locationType_" name="population.populationType" value='<s:property value="@com.tianque.core.util.BaseInfoTables@ACTUALCOMPANY_KEY"/>'/>
	<input type="hidden" id="_supervisorName_" value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getMemberAlias(@com.tianque.core.util.BaseInfoTables@ACTUALCOMPANY_KEY)" escape="false"/>'/>
	<input type="hidden" id="_superviceRecordName_" value='<s:property value="@com.tianque.service.util.ServiceTeamMemberOrRecord@getRecordAlias(@com.tianque.core.util.BaseInfoTables@ACTUALCOMPANY_KEY)" escape="false"/>'/>
</div>

