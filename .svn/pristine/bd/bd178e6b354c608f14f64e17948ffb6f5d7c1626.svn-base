<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
<pop:formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="agoProfession" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<pop:formatterProperty name="positiveInfoType" domainName="@com.tianque.domain.property.PropertyTypes@POSITIVEINFO" />
function rendIsRepeat(el, options, rowData){
	if(1==rowData.isRepeat){
		return "是";
	}else{
		return "否";
	}
}
function operateFormatter(el,options,rowData){
	return "<a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><U><font color='#6633FF'>查看</font></U></a>";
}
function attentionExtentColor(el,options,rowData){
	var displayName=attentionExtentFormatter(el,options,rowData);
	if(displayName=='undefined'||displayName=='')
		return '';
	else if(displayName=='严重')
		return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
	else if(displayName=='中等')
		return '<span>中等：<span style="color:#ffcc00;">███</span></span>';
	else if(displayName=='一般')
		return '<span>一般：<span style="color:#99cc00;">█</span></span>';
	else
		return '';
}
var dialogWidth=800;
var dialogHeight=650;
var gridOption = {
	colModel:[
  		{name:"id",index:"id",hidden:true,frozen:true},
  		{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
  		{name:"organization.id", index:"organization.id",hidden:true,hidedlg:true},
  		{name:"操作",index:'id',label:'操作',width:50,formatter:operateFormatter,sortable:false,frozen:true},
  		{name:'attentionExtent',index:"attentionExtent",label:'关注程度',width:100,formatter :attentionExtentColor,sortable:true,frozen:true},
  		{name:"name",index:'name',label:'姓名', formatter:nameFont,width:100,frozen:true},
  		{name:'gender',sortable:false,label:'性别',formatter : genderFormatter,width:40},
  		{name:'idCardNo',index:'idCardNo',label:'身份证号码',width:150,sortable:false,frozen:true},
  		{name:'currentAddress',label:'常住地址',index:'currentAddress',sortable:false,width:200},
  		{name:'organization.orgName',label:'所属网格',index:'orgInternalCode',width:250,hidden:true,sortable:false},
  		{name:"province",label:'户籍地',hidden:true, formatter:householdRegisterFormatter,width:200},
  		{name:'imprisonmentDate',label:'原判刑期',hidden:true,index:'imprisonmentDate',width:80},
  		{name:'isRepeat',index:'isRepeat',label:'是否累犯',hidden:true,formatter : rendIsRepeat,width:80},
  		{name:'releaseOrBackDate',index:'releaseOrBackDate',label:'解教（刑释）日期',width:150},
  		{name:"positiveInfoType",label:'人员类型',index:'positiveInfoType',hidden:true,formatter:positiveInfoTypeFormatter},
  		
  		{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
  		{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
  		{name:'resettlementDate',index:'resettlementDate',label:'安置日期',hidden:true},
  		{name:'schooling',label:'文化程度',sortable:false,formatter : schoolingFormatter,hidden:true},
  		{name:'caseReason',label:'原罪名（错）',index:'caseReason',sortable:false,hidden:true},
  		{name:'laborEduAddress',label:'劳教(服刑)场所',index:'laborEduAddress',sortable:false,hidden:true},
  		{name:'noResettlementReason',label:'未安置原因',index:'noResettlementReason',sortable:false,hidden:true},
  		{name:'agoProfession',label:'原职业',sortable:false,formatter : agoProfessionFormatter,hidden:true},
  		{name:'nativePlaceAddress',label:'户籍地详址',sortable:false,index:'nativePlaceAddress',hidden:true},
  		{name:'nativePoliceStation',label:'户籍派出所',sortable:false,index:'nativePoliceStation',hidden:true},
  		{name:"telephone",label:'联系电话',index:'telephone',hidden:true},
  		{name:"mobileNumber",label:'联系手机',index:'mobileNumber',hidden:true},
  		{name:"birthday",index:'birthday',label:'出生日期', hidden:true},
  		{name:"isExpired",index:'isExpired',label:'是否过期',hidden:true,hidedlg:true},
  		{name:"isEmphasis",sortable:false,label:'是否关注',hidden:true,hidedlg:true,width:100},
  		{name:'death',sortable:false,hidden:true,hidedlg:true,width:80}
   	]
}
</script>
