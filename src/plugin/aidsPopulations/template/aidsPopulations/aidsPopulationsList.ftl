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


function isEmphasis(el, options, rowData){
	if(true==rowData.isEmphasis){
		return "是";
	}else{
		return "否";
	}
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
		{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:false,width:100,formatter:attentionExtentColor,frozen:true},
		{name:"name", index:"name",label:"姓名",formatter:nameFont,width:80, sortable:false,frozen : true},
		{name:"gender",index:"gender",label:"性别",width:50, sortable:false,formatter:genderFormatter,align:"center"},
		{name:"idCardNo",index:"idCardNo", width:160, label:"身份证号码",sortable:false, align:"center", frozen : true},
		{name:"organization.orgName", index:"orgInternalCode",sortable:false, width:200,label:"所属网格",hidden:true},
		{name:"usedName", index:'usedName', sortable:false,label:'曾用名', width:80 ,hidden:true},
		{name:"currentAddress",index:"currentAddress",label:"常住地址", sortable:false,width:200},
		{name:"infectway",index:"infectway", label:"感染途径",formatter:infectwayFormatter,sortable:false,width:100},
		{name:'hasServiceTeamMember',label:'有无服务成员',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
		{name:'lastRecordTime',label:'最新服务时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
		{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		{name:'updateDate', sortable:false, label:'数据更新时间',align:"center", width:160},
		{name:"violationsofthelaw",index:'violationsofthelaw',label:"违法情况",align:"center",formatter:violationsofthelawFormatter, sortable:false,width:100,hidden:true},
		{name:"crimetype",index:'crimetype', label:"犯罪类型",sortable:false,formatter:crimetypeFormatter, width:80,hidden:true},
		{name:"receivedorganization",index:'receivedorganization', label:"收治机构",sortable:false,align:"center", width:80,hidden:true},
		{name:"receivedlevel",index:'receivedlevel', label:"收治机构层级",formatter:receivedlevelFormatter,sortable:false,align:"center", width:80,hidden:true},
		{name:"helpCircumstances", label:"帮扶情况", width:200,sortable:false,hidden:true},
		{name:"birthday",label:"出生日期",align:"center", sortable:false,width:100,hidden:true},
		{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,align:"center",width:90,sortable:false,hidden:true},
		{name:"nation",label:"民族",formatter:nationFormatter,width:90,sortable:false,hidden:true},
		{name:'province', index:'province',label:'户籍地址',formatter:domicileFormatter, width:150, sortable:false, align:'center', hidden:true}, 	
		{name:"nativePlaceAddress",sortable:false,label:"户籍详址",width:200, hidden:true},
		{name:'addressno', index:'addressno',label:'地址编号', width:100, sortable:false, align:'center', hidden:true}, 
	    {name:'otherAddress', index:'otherAddress',label:'临时居所', width:100, sortable:false, align:'center', hidden:true},
		{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,width:150,sortable:false,hidden:true},
		{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100,sortable:false,hidden:true},
		{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:false,hidden:true},
		{name:"mobileNumber",label:"联系手机", width:100,align:"center",sortable:false,hidden:true},
		{name:"telephone",label:"联系电话", width:120,align:"center",sortable:false,hidden:true},
		{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:false,hidden:true},
		{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100},
		{name:'death',sortable:false,hidden:true,hidedlg:true,width:80}
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<@s.include value="/baseinfo/commonPopulation/commonPopulationList.jsp" >
	<@s.param name="moduleName" >Aidspopulations</@s.param>
	<@s.param  name="moduleCName" >艾滋病人员</@s.param>
	<@s.param  name="supervisorPerson">服务人员</@s.param>
</@s.include>
<div style="display:none;">
	<input type="hidden" id="_populationType_" name="population.populationType" value='<@s.property value="@com.tianque.core.util.BaseInfoTables@AIDSPOPULATIONS_KEY"/>'/>
</div>