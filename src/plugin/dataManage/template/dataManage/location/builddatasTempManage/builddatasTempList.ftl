<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">BuilddatasTemp</@s.param>
</@s.include>
<script type="text/javascript">
	<@pop.formatterProperty name="buildingstructures" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
	<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@BUILDDATAS_TYPE" />
	
	function nameFont(el,options,rowData){
		return "<pop:JugePermissionTag ename='importViewBuilddatasTemp,claimViewBuilddatasTemp'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+")'></pop:JugePermissionTag>"+rowData.buildingname+"<pop:JugePermissionTag ename='importViewBuilddatasTemp,claimViewBuilddatasTemp'></a></pop:JugePermissionTag>";
	}
	
	var gridOption = {
		colModel:[
			{name:"id", index:"id", hidden:true,frozen:true},
			{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
			{name:'buildingid',index:'BUILDINGID',label:'楼宇ID',sortable:true,width:65,hidden:true},
			{name:'buildingname',index:'BUILDINGNAME',label:'楼宇名称',sortable:true,width:100,formatter:nameFont},
			{name:'buildingaddress',index:'BUILDINGADDRESS',sortable:true,label:'楼宇地址',width:140},
			{name:'owner',index:'OWNER',label:'业主',sortable:true,width:120},
			{name:'responsibleperson',index:'RESPONSIBLEPERSON',sortable:true,label:'负责人',width:120},
			{name:'phone',index:'PHONE',label:'联系电话',width:120,sortable:true,align:"center"},
			{name:'buildingstructures',index:'BUILDINGSTRUCTURES',sortable:true,label:'建筑结构',formatter:buildingstructuresFormatter,width:120},
			{name:'type',index:'TYPE',label:'楼宇类型',sortable:true,formatter:typeFormatter,width:120}
		]
	};
	var dialogWidth=800;
	var dialogHeight=350;
	
	function getLocationName(rowData){
		return rowData.name;
	}
</script>
<#assign moduleName="BuilddatasTemp"/>
<#assign moduleCName="楼宇信息"/>
<#assign bigType="builddatas"/>
<#assign  importStartRow=6/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>