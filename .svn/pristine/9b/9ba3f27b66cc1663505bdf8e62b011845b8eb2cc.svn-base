<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ActualHouseTemp</@s.param>
</@s.include>


<script type="text/javascript">
<@pop.formatterProperty name="houseStructure" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_STRUTS" />
<@pop.formatterProperty name="houseSource" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_SOURCE" />
<@pop.formatterProperty name="houseUses" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" />
<@pop.formatterProperty name="buildingUses" domainName="@com.tianque.domain.property.PropertyTypes@BUILDING_USES" />
<@pop.formatterProperty name="ownProperty" domainName="@com.tianque.domain.property.PropertyTypes@OWN_PROPERTY" />
<@pop.formatterProperty name="rentalBuildings" domainName="@com.tianque.domain.property.PropertyTypes@RENTAL_BUILDINGS" />
<@pop.formatterProperty name="housingVouchers" domainName="@com.tianque.domain.property.PropertyTypes@HOUSING_VOUCHERS" />
<@pop.formatterProperty name="landDocuments" domainName="@com.tianque.domain.property.PropertyTypes@LAND_DOCUMENTS" />
<@pop.formatterProperty name="propertyTypes" domainName="@com.tianque.domain.property.PropertyTypes@PROPERTY_TYPES" />
<@pop.formatterProperty name="certificateType" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGCERTIFICATE_TYPE" />
<@pop.formatterProperty name="usage" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_USAGE" />
<@pop.formatterProperty name="hiddenDangerLevel" domainName="@com.tianque.domain.property.PropertyTypes@HIDDEN_TROUBLE_LEVEL" />
var dialogWidth=760;
var dialogHeight=510;
var isgridBol = false;

function isRentalhouseFormatter(el,options,rowData){
	if(true==rowData.isRentalHouse){
		return '是';
	}else{
		return '否';
	}
}
function rentalPersonFormatter(el,options,rowData){
	return "<@pop.JugePermissionTag ename='viewRentalHouseTemp'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><span>"+rowData.rentalPerson+"</span></a></@pop.JugePermissionTag>";
}

function dataStateFormatter(el, options, rowData){
	if(rowData.DATASTATE==1){
		return "格式错误";
	}else if(rowData.DATASTATE==2){
		return "通过校验";
	}else{
		return "未校验";
	}
}
function hiddenDangerLevelColor(el,options,rowData){
	var displayName=hiddenDangerLevelFormatter(el,options,rowData);
	if(displayName=='undefined'||displayName=='')
		return '';
	else if(displayName=='严重')
		return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
	else if(displayName=='一般')
		return '<span>一般：<span style="color:#ffcc00;">███</span></span>';
	else if(displayName=='安全')
		return '<span>安全：<span style="color:#99cc00;">█</span></span>';
	else
		return '';
}
var gridOption={
		colModel:[
		        {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
		        {name:"organization.id",index:"organization.id",sortable:false,hidden:true,hidedlg:true,frozen:true},
		        {name:"operator", index:'id', label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
	            {name:'organization.orgName',index:'organization.orgName',label:'所属网格',sortable:false,width:230},
		        {name:'houseCode',index:'houseCode',label:'房屋编号',width:120,sortable:true,frozen:true},
		        {name:'address',index:'address',label:'房屋准确地址',sortable:true,width:200},
		        {name:'name',index:'name',label:'产权人姓名',width:80,sortable:true,frozen:true},
		        {name:'certificateNumbe',index:'certificateNumbe',label:'产权人证件号码',sortable:true,width:200},
		        {name:'houseSource', index:'houseSource',label:'房屋来源',sortable:true, width:80,hidden:true, formatter:houseSourceFormatter},
		        {name:"houseUses",index:'houseUses',label:'房屋用途',sortable:true, width:100,formatter:houseUsesFormatter},
		        {name:'houseArea',index:'houseArea',label:'住房面积(㎡)',hidden:true,sortable:true,width:100},
		        {name:'houseStructure',index:'houseStructure',label:'住房结构',hidden:true,sortable:true,formatter:houseStructureFormatter,width:110},
		        {name:'manager',index:'manager',label:'代表人/业主',hidden:true,width:120,sortable:true,frozen:false},

	            {name:'buildingName',index:'buildingName',label:'建筑物名称',width:80,sortable:true,hidden:true},
	            {name:'buildingUses',index:'buildingUses',label:'建筑物用途 ',formatter:buildingUsesFormatter,width:80,sortable:true,hidden:true},
	            {name:'propertyName',index:'propertyName',label:'物业管理单位名称 ',width:80,sortable:true,hidden:true},
	            {name:'houseOwnerIdCardNo',index:'houseOwnerIdCardNo',label:'业主身份证号码 ',width:80,sortable:true,hidden:true},
	            {name:'telephone',index:'telephone',label:'业主联系电话 ',width:80,sortable:true,hidden:true},
	            {name:'houseDoorModel',index:'houseDoorModel',label:'房屋户型',width:80,sortable:true,hidden:true},
	            {name:'builtYear',index:'builtYear',label:'建成年份 ',width:80,sortable:true,hidden:true},
	            {name:'houseArea',index:'houseArea',label:'住房面积 ',width:80,sortable:true,hidden:true},
	            {name:'ownProperty',index:'ownProperty',label:'自有产权',width:80,sortable:true,hidden:true,formatter:ownPropertyFormatter},
	            {name:'rentalBuildings',index:'rentalBuildings',label:'租赁公房 ',width:80,sortable:true,hidden:true,formatter:rentalBuildingsFormatter},
	            {name:'housingVouchers',index:'housingVouchers',label:'房屋凭证',width:80,sortable:true,hidden:true,formatter:housingVouchersFormatter},
	            {name:'houseRightNumber',index:'houseRightNumber',label:'房屋权证号 ',width:80,sortable:true,hidden:true},
	            {name:'houseRightNumberDate',index:'houseRightNumberDate',label:'房屋权证发证时间 ',width:80,sortable:true,hidden:true},
	            {name:'landDocuments',index:'landDocuments',label:'土地凭证',width:80,sortable:true,hidden:true,formatter:landDocumentsFormatter},
	            {name:'landRightsNumbe',index:'landRightsNumbe',label:'土地权证号 ',width:80,sortable:true,hidden:true},
	            {name:'landRightsNumbeDate',index:'landRightsNumbeDate',label:'土地权证发证时间 ',width:80,sortable:true,hidden:true},
	            {name:'propertyTypes',index:'propertyTypes',label:'产权人类型',width:80,sortable:true,hidden:true,formatter:propertyTypesFormatter},
	            {name:'certificateType',index:'certificateType',label:'产权人证件类型 ',width:80,sortable:true,hidden:true,formatter:certificateTypeFormatter},
	            {name:'propertyPersonTel',index:'propertyPersonTel',label:'产权人联系电话',width:80,sortable:true,hidden:true},
	            {name:'propertyPersonMobile',index:'propertyPersonMobile',label:'产权人联系手机 ',width:80,sortable:true,hidden:true},
	            {name:'remark',index:'remark',label:'备注 ',width:80,sortable:true,hidden:true}
	        	]
};
</script>
<#assign moduleName="ActualHouseTemp"/>
<#assign moduleCName="房屋信息"/>
<#assign bigType="house"/>
<#assign importStartRow="6"/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>
