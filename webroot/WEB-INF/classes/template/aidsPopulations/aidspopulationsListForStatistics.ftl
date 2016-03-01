<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/baseinfo/commonPopulation/jsFunctionInclude.jsp" >
	<@s.param name="moduleName" >Aidspopulations</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<@pop.formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
<@pop.formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
<@pop.formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
<@pop.formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
<@pop.formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
<@pop.formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
<@pop.formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
<@pop.formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

<@pop.formatterProperty name="infectway" domainName="@com.tianque.constant.PropertyTypes@INFECT_WAY" />
<@pop.formatterProperty name="violationsofthelaw" domainName="@com.tianque.domain.property.PropertyTypes@VIOLATIONSOFTHELAW" />
<@pop.formatterProperty name="crimetype" domainName="@com.tianque.domain.property.PropertyTypes@CRIMETYPE" />
<@pop.formatterProperty name="receivedlevel" domainName="@com.tianque.constant.PropertyTypes@RECEIVED_LEVEL" />


function operateFormatter(el,options,rowData){
	return "<a href='javascript:;' onclick='viewDialog(event,"+rowData.id+");'><U><font color='#6633FF'>查看</font></U></a>";
}
function nameFormatter(el,options,rowData){
	if(null == rowData.name) {
		return "&nbsp;&nbsp;"
	}else if(rowData.death == true || rowData.death == "true" || rowData.death == 1){
		return '<a href="javascript:;" <@pop.JugePermissionTag ename="viewAidspopulations"> onclick="viewDialog(event,'+rowData.id+')" </@pop.JugePermissionTag> ><font color="red">'+rowData.name+'</font></a>';
	}else if(rowData.logOut==1||rowData.logOut=='1' || rowData.isEmphasis==1){
		return '<a href="javascript:;"  <@pop.JugePermissionTag ename="viewAidspopulations"> onclick="viewDialog(event,'+rowData.id+')" </@pop.JugePermissionTag> ><font color="#778899">'+rowData.name+'</font></a>';
	}
	return '<a href="javascript:;" <@pop.JugePermissionTag ename="viewAidspopulations"> onclick="viewDialog(event,'+rowData.id+')" </@pop.JugePermissionTag>   ><font color="#6633FF">'+rowData.name+'</font></a>';
}
function domicileFormatter(el,options,rowData){
	return rowData.province + rowData.city + rowData.district;
}
var gridOption = {
	colModel:[
		{name:"id", index:"id", sortable:false,hidden:true,frozen : true},
		{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
		{name:"organization.id", index:"organization.id",sortable:false,hidden:true,hidedlg:true},
		{name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter,width:90,frozen :true,align:"center"},
		{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
		{name:"name", index:"name",label:"姓名",formatter:nameFormatter,width:80, sortable:true,frozen : true},
		{name:"gender",index:"gender",label:"性别",width:50, sortable:true,formatter:genderFormatter,align:"center"},
		{name:"idCardNo",index:"idCardNo", width:160, label:"身份证号码",sortable:true, align:"center", frozen : true},
		{name:"currentAddress",index:"currentAddress",label:"常住地址", sortable:true,width:200},
		{name:"infectway",index:"infectway", label:"感染途径",formatter:infectwayFormatter,sortable:true,width:100},
		
		{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		{name:'updateDate', sortable:true, label:'数据更新时间',align:"center", width:160},
		
		{name:"organization.orgName", index:"orgInternalCode",sortable:false, width:200,label:"所属网格",hidden:true},
		{name:"violationsofthelaw",index:'violationsofthelaw',label:"违法情况",align:"center",formatter:violationsofthelawFormatter, sortable:true,width:100,hidden:true},
		{name:"crimetype",index:'crimetype', label:"犯罪类型",sortable:true,formatter:crimetypeFormatter, width:80,hidden:true},
		{name:"receivedorganization",index:'receivedorganization', label:"收治机构",sortable:true,align:"center", width:80,hidden:true},
		{name:"receivedlevel",index:'receivedlevel', label:"收治机构层级",formatter:receivedlevelFormatter,sortable:true,align:"center", width:80,hidden:true},
		{name:"helpCircumstances", label:"帮扶情况", width:200,sortable:true,hidden:true},
		
		{name:"usedName", index:'usedName', sortable:true,label:'曾用名', width:80 ,hidden:true},
		{name:"birthday",label:"出生日期",align:"center", sortable:true,width:100,hidden:true},
		{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,align:"center",width:90,sortable:true,hidden:true},
		{name:"nation",label:"民族",formatter:nationFormatter,width:90,sortable:true,hidden:true},
		//{name:"nativePlace",label:"籍贯",width:150,sortable:true,hidden:true},
		//{name:'province', index:'province',label:'户籍地址(省)', width:100, sortable:true, align:'center', hidden:true}, 	
    	//{name:'city', index:'city',label:'户籍地址(市)', width:100, sortable:true, align:'center', hidden:true}, 	
    	//{name:'district', index:'district',label:'户籍地址(县)', width:100, sortable:true, align:'center', hidden:true}, 	
    	{name:'province', index:'province',label:'户籍地址',formatter:domicileFormatter, width:150, sortable:true, align:'center', hidden:true}, 	
		{name:"nativePlaceAddress",sortable:true,label:"户籍详址",width:200, hidden:true},
		{name:'addressno', index:'addressno',label:'地址编号', width:100, sortable:true, align:'center', hidden:true}, 	
		{name:'currentAddress', index:'currentAddress',label:'常住地址', width:100, sortable:true, align:'center', hidden:true}, 
	    //{name:'currentaddresstype', index:'currentaddresstype',label:'常住地址类型', width:100, sortable:true, align:'center', hidden:true}, 
	    //{name:'community', index:'community',label:'常住地址商品房 小区', width:100, sortable:true, align:'center', hidden:true}, 
	    //{name:'block', index:'block',label:'常住地址商品房 幢', width:100, sortable:true, align:'center', hidden:true}, 	
		//{name:'unit', index:'unit',label:'常住地址商品房 单元', width:100, sortable:true, align:'center', hidden:true}, 
	    //{name:'room', index:'room',label:'常住地址商品房  室', width:100, sortable:true, align:'center', hidden:true}, 	
	    {name:'otheraddress', index:'otheraddress',label:'临时居所', width:100, sortable:true, align:'center', hidden:true},
		{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,width:150,sortable:true,hidden:true},
		{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100,sortable:true,hidden:true},
		{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:true,hidden:true},
		{name:"mobileNumber",label:"联系手机", width:100,align:"center",sortable:true,hidden:true},
		{name:"telephone",label:"联系电话", width:120,align:"center",sortable:true,hidden:true},
		{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:true,hidden:true},
		{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100},
		{name:'death',sortable:false,hidden:true,hidedlg:true,width:80}
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<div style="display: none;">
<@pop.JugePermissionTag ename="aidspopulationsManagement">
	<span id="title">艾滋病人员</span>
</@pop.JugePermissionTag></div>
<@s.include value="/baseinfo/commonPopulation/commonStatisticPopulationList.jsp" >
	<@s.param name="moduleName" >Aidspopulations</@s.param>
	<@s.param  name="moduleCName" >艾滋病人员</@s.param>
	<@s.param  name="searchType">${request.getParameter("searchType")}</@s.param>
	<@s.param  name="orgIdForStat">${request.getParameter("orgIdForStat")}</@s.param>
</@s.include>

