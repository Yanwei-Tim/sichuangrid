<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">AidNeedPopulationTemp</@s.param>
</@s.include>

<script type="text/javascript">
<@pop.formatterProperty name="aidReason" domainName="@com.tianque.domain.property.PropertyTypes@AIDRREASON" />
<@pop.formatterProperty name="difficultType" domainName="@com.tianque.domain.property.PropertyTypes@DIFFICULT_TYPE" />
function isLowHouseholdsFormatter(el,options,rowData){
	if(rowData.isLowHouseholds==true||rowData.isLowHouseholds=='true'){
		return "是";
	}else{
		return "否";
	}
}

var gridOptionPrivate = {
	colModel:[
		{name:'aidReason',label:'救助原因',formatter:aidReasonFormatter,sortable:true,width:100,hidden:false},
		{name:'isLowHouseholds',label:'是否低保户',formatter:isLowHouseholdsFormatter,align:'center',sortable:true,width:100,hidden:false},
		{name:'difficultCardNo',label:'困难证号',sortable:true,width:100,hidden:true},
		{name:'difficultType',label:'困难类型',formatter:difficultTypeFormatter,sortable:true,width:100,hidden:true},
		{name:'subsidiesAmount',label:'领取金额(元)',sortable:true,width:100,hidden:true},
		{name:'subsidiesGetTime',label:'领取时间',sortable:true,align:'center',width:100,hidden:true},
		{name:'subsidiesStartTime',label:'享受起始时间',sortable:true,align:'center',width:100,hidden:true},
		{name:'subsidiesPopulation',label:'享受人数(人)',sortable:true,width:100,hidden:true},
		{name:'subsidiesArea',label:'享受地区',sortable:true,width:100,hidden:true}
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="AidNeedPopulationTemp"/>
<#assign  moduleCName="需救助人员"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
