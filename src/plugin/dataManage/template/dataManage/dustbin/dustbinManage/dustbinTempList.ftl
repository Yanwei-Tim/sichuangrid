<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">DustbinTemp</@s.param>
</@s.include>
<script type="text/javascript">
	<@pop.formatterProperty name="partType" domainName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_TYPE" />
	<@pop.formatterProperty name="partName" domainName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME" />

	function hasVideo(hasVideo){
		if(true==hasVideo || "true" == hasVideo){
			return "是";
		}else{
			return "否";
		}
	}
	
	function nameFontClaim2(el,options,rowData){
		return "<pop:JugePermissionTag ename='importViewDustbinTemp,claimViewDustbinTemp'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+")'></pop:JugePermissionTag>"+rowData.dustbinCode+"<pop:JugePermissionTag ename='importViewDustbinTemp,claimViewDustbinTemp'></a></pop:JugePermissionTag>";
	}
	
	var gridOption = {
		colModel:[
			{name:"id", index:"id", hidden:true,frozen:true},
			{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
			{name:"dustbinCode", index:"dustbinCode",label:"部件标识码",width:100,formatter:nameFontClaim2,frozen:true},
			{name:"organization.orgName", index:"orgInternalCode", hidden:true,width:230,label:"所属网格"},
			{name:"partType", index:"partType",label:"部件类型",formatter:partTypeFormatter,width:100},
			{name:"partName", index:"partName",label:"部件名称",formatter:partNameFormatter,width:100},
			{name:"deptName", index:"deptName",label:"主管部门名称",width:100},
			{name:"ownershipUnitName",label:"权属单位名称",width:100,sortable:false},
			{name:"maintenanceUnitName",label:"养护单位名称",width:100,sortable:false},
			{name:"hasVideo", label:"是否有视频流",index:"hasVideo", formatter:hasVideo,width:140},
			{name:"address", label:"地址",index:"address",width:140},
			{name:"remark",label:'备注', width:180,hidden:true}
		]
	};
	var dialogWidth=800;
	var dialogHeight=600;
	
	function getLocationName(rowData){
		return rowData.name;
	}
</script>
<#assign moduleName="DustbinTemp"/>
<#assign moduleCName="部件信息"/>
<#assign bigType="dustbin"/>
<#assign  importStartRow=6/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>