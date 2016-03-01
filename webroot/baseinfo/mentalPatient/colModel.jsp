<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="MentalPatient" name="moduleName"/>
</jsp:include>
<script type="text/javascript">

var dialogWidth=800;
var dialogHeight=600;

<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="dangerLevel" domainName="@com.tianque.domain.property.PropertyTypes@MENTALPATIENT_DANGEROUS_LEVEL" />
<pop:formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<pop:formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<pop:formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<pop:formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<pop:formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<pop:formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
<pop:formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<pop:formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
function isTreat(el, options, rowData){
	if(true==rowData.treat){
		return "是";
	}else{
		return "否";
	}
}
function dangerLevelColor(el,options,rowData){
	var displayName=dangerLevelFormatter(el,options,rowData);
	if(displayName=='undefined'||displayName=='')
		return '';
	else if(displayName=='高')
		return '<span>高：<span style="color:#ff0000;">█████</span></span>';
	else if(displayName=='中')
		return '<span>中：<span style="color:#ffcc00;">███</span></span>';
	else if(displayName=='低')
		return '<span>低：<span style="color:#99cc00;">█</span></span>';
	else
		return '';
}
function operateFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='viewMentalPatient'><a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><U><font color='#6633FF'>查看</font></U></a></pop:JugePermissionTag>";
}
var gridOption = {
	colModel:[
  	   	{name:"id", index:"id", hidden:true,frozen : true},
  	  	{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
  	   	{name:"organization.id", index:"organization.id", hidden:true,hidedlg:true},
  	   	{name:"operation",index:"id",label:"操作",formatter:operateFormatter,width:90,frozen : true,align:"center"},
  	    {name:'dangerLevel',label:"危险程度",index:"dangerLevel",width:100,sortable:true,formatter:dangerLevelColor},
  		{name:"name", label:"姓名",formatter:nameFont,index:"name",width:100,frozen : true},
  	    {name:"gender", label:"性别",index:"gender",width:40, formatter:genderFormatter},
  		{name:"idCardNo", label:"身份证号码", index:"idCardNo", width:160,frozen : true},
  		{name:"currentAddress",label:"常住地址", width:100,sortable:false},
  		{name:"usedName",label:"曾用名", width:100, sortable:false,hidden:true},
  		{name:"birthday",label:"出生日期", width:100,sortable:false,hidden:true},
  	    {name:"organization.orgName",label:"所属网格", index:"orgInternalCode", width:200,hidden:true},	
  		{name:'psychosisName',label:"患病名称",sortable:false,width:100,hidden:true},
  		{name:'dangerLevel',label:"危险程度",index:"dangerLevel",align:'center',width:80,formatter:dangerLevelFormatter},
  		
  		{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
  		{name:'updateDate', sortable:false, label:'数据更新时间', width:140},
  		{name:'treat',label:"是否接受过治疗",index:"treat",align:'center',width:120,formatter:isTreat,hidden:true},
  		{name:"cureDepartment",label:"康复机构",sortable:false, width:100,hidden:true}, 	
  		{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,sortable:false,width:100,hidden:true},
  		{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:false,hidden:true},
  		{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,width:100, sortable:false,hidden:true},
  		{name:"bloodType",label:"血型",formatter:bloodTypeFormatter,width:100,sortable:false,hidden:true},
  		{name:"nation",label:"民族",formatter:nationFormatter, width:100, sortable:false,hidden:true},
  		{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100, sortable:false,hidden:true},
  		{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:false,hidden:true},
  		{name:"email",label:"电子邮件", width:100, sortable:false,hidden:true},
  		{name:"stature",label:"身高(cm)",sortable:false, width:100,hidden:true},
  		{name:"otherAddress",label:"临时居所", width:100, sortable:false,hidden:true},
  		{name:"province",label:"户籍地", width:200,sortable:false,formatter:householdRegisterFormatter, hidden:true},
  		{name:"nativePlaceAddress",label:"户籍详址",width:200, hidden:true},
  		{name:"workUnit",label:"工作单位或就读学校",width:200, hidden:true},
  		{name:'telephone',label:"联系电话",sortable:false,width:100,hidden:true},
  		{name:'mobileNumber',label:"联系手机",sortable:false,width:100, hidden:true},
  		{name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
  		{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
  	]
}
</script>
