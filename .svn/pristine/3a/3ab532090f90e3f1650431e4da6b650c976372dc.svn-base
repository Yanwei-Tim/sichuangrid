<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">DangerousChemicalsUnitTemp</@s.param>
	<@s.param name="moduleNameTemp">DangerousChemicalsUnitTemp</@s.param>
</@s.include>



<script type="text/javascript">
<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
var gridOption = {
	colModel:[
		{name:"id", index:"id", hidden:true},
    	{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
		{name:"organization.orgName", index:"orgInternalCode", width:150,label:"所属网格",hidden:true},
    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
		//{name:"unitName-Font", index:"unitName",label:"单位名称",frozen:true,formatter:nameFont,width:200},
		{name:"name", index:"name",label:"单位名称",frozen:true,formatter:nameFontClaim,width:200},
		{name:"address",label:'单位地址', width:200},
		{name:"manager", label:"负责人",index:"superintendent",width:100},
		{name:"unitType",label:"单位类别", index:"unitType", width:150,sortable:false},
		{name:"commodityType",label:"货物类别", width:150,sortable:false},
		
		//{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		//{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
		{name:"telephone",label:"联系电话", width:120,sortable:false,hidden:true,align:'center'},
		{name:"copyScope",label:"副本许可范围", width:150,sortable:false,hidden:true},
		{name:"storageDevice",label:"存储设备", width:150,sortable:false,hidden:true},
		{name:"isEmphasis",sortable:false,label:"是否注销",hidden:true,hidedlg:true,width:100},
		//{name:"logOutReason",label:"注销原因", width:100,sortable:false,hidden:true},
		//{name:"logOutTime",label:"注销时间", width:100,sortable:false,hidden:true,align:'center'},
		{name:"remark",sortable:false,label:"备注",hidden:true,width:100}
	]
};
var dialogWidth=800;
var dialogHeight=640;

function getLocationName(rowData){
	return rowData.name;
}
</script>
<#assign moduleName="DangerousChemicalsUnitTemp"/>
<#assign bigType="location"/>
<#assign  importStartRow=6/>
<#assign moduleCName="危险化学品单位"/>
<#assign isNew=1/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>
