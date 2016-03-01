<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">NurturesWomenTemp</@s.param>
</@s.include>
<script type="text/javascript">
	<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<@pop.formatterProperty name="career" domainName="@com.tianque.domain.property.PropertyTypes@CAREER" />
	<@pop.formatterProperty name="politicalBackground" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
	<@pop.formatterProperty name="maritalState" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
	<@pop.formatterProperty name="faith" domainName="@com.tianque.domain.property.PropertyTypes@FAITH" />
	<@pop.formatterProperty name="residenceType" domainName="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" />
	<@pop.formatterProperty name="schooling" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
	<@pop.formatterProperty name="healthState" domainName="@com.tianque.domain.property.PropertyTypes@HEALTH_STATE" />
	<@pop.formatterProperty name="bloodType" domainName="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
	<@pop.formatterProperty name="nation" domainName="@com.tianque.domain.property.PropertyTypes@NATION" />
	<@pop.formatterProperty name="currentAddressType" domainName="@com.tianque.domain.property.PropertyTypes@CURRENT_ADDRESS_TYPE"/>
	<@pop.formatterProperty name="licenseSituation" domainName="@com.tianque.domain.property.PropertyTypes@LICENSE_SITUATION"/>
	<@pop.formatterProperty name="onechildOfCouple" domainName="@com.tianque.domain.property.PropertyTypes@ONE_CHILD_SITUATION"/>
	<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
	

function isAdanon(el, options, rowData){
	if(true==rowData.adanon){
		return "是";
	}else{
		return "否";
	}
}


var gridOptionPrivate = {
	colModel:[
		 {name:'firstMarriageTime',width:100,label:"初婚时间",sortable:true,hidden:false},
		 {name:'marriageRegistrationTime',width:100,label:"离婚时间",sortable:true,hidden:true},
		 {name:'marriageOrDivorceTime',width:100,label:"最近再婚时间",sortable:true,hidden:true},
		 {name:'onechildOfCouple',width:100,label:"夫妻双方独生子女情况",formatter:onechildOfCoupleFormatter,sortable:true,hidden:true},
		 {name:'boyNumber',width:100,label:"男孩数",sortable:true,hidden:false},
		 {name:'girlNumber',width:100,label:"女孩数",sortable:true,hidden:false},
		 {name:'singleChildCardno',width:100,label:"独生子女证号",sortable:true,hidden:true},
		 {name:'singleChildCDIssueTime',width:100,label:"独生子女证办证时间",sortable:true,hidden:true},
		 {name:'licenseSituation',width:100,label:"领证情况",sortable:true,formatter:licenseSituationFormatter,hidden:true},
		 {name:'fertilityServicesNo',width:100,label:"生育服务证号",sortable:true,hidden:true},
		 {name:'licenseTime',width:100,label:"领证时间",sortable:true,hidden:true},
		 {name:'certifiedUnit',width:100,label:"发证单位",sortable:true,hidden:true},
		 {name:'contraceptiveMethod',width:100,label:"避孕方法",sortable:true,hidden:true},
		 {name:'contraceptiveTime',width:100,label:"避孕起始时间",sortable:true,hidden:true},
		 
		 {name:"remark",sortable:false,label:"备注",hidden:true,hidedlg:true,width:100},
		 {name:'isDeath',sortable:false,label:"是否死亡",hidden:true,hidedlg:true},
		 {name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100},
		 //男方
		  {name:"manName",index:'manname',label:"丈夫姓名",width:70,sortable:true,hidden:true},
		  {name:'manIdcardNo',index:'manidcardno',label:"丈夫身份证号码",width:160,sortable:true,hidden:true},
		  {name:'manMaritalState',width:100,label:"丈夫婚烟状况",formatter:maritalStateFormatter,sortable:true,hidden:true},
		  {name:'manFirstMarriageTime',width:100,label:"丈夫初婚时间",sortable:true,hidden:true},
		  {name:'manMobileNumber',width:150,label:"丈夫联系手机",align:"center",hidden:true,sortable:true},
		  {name:'manTelephone',width:100,label:"丈夫联系电话",align:"center",hidden:true,sortable:true},
		  {name:'manBirthday',index:'manbirthday',label:"丈夫出生日期",width:70,align:"center",hidden:true,sortable:true,width:120},
		  {name:'manPoliticalbackground',label:"丈夫政治面貌",formatter:politicalBackgroundFormatter,width:150,hidden:true,sortable:true},
		  {name:'manNation',width:100,label:"丈夫民族",formatter:nationFormatter,hidden:true,sortable:true},
		  {name:'manSchooling',formatter:schoolingFormatter,label:"丈夫文化程度",width:100,hidden:true,sortable:true},
		  {name:'manCareer',width:100,label:"丈夫职业",formatter:careerFormatter,sortable:true,hidden:true},
		  {name:'manWorkUnit',sortable:true,label:"丈夫工作单位或就读学校",hidden:true,width:180},
		  {name:'manProvince',sortable:true,label:"丈夫户籍地",hidden:true,width:150},
		  {name:'manNativeplaceAddress',width:150,label:"丈夫户籍地详址",hidden:true,sortable:true},
		  {name:'manCurrentAddress',label:"丈夫常住地址",sortable:true,hidden:true,width:200}
		  
		
	]
};
var dialogWidth=800;
var dialogHeight=640;
var importStartRow = 6;
</script>
<#assign  moduleName="NurturesWomenTemp"/>
<#assign  moduleCName="育龄妇女"/>
<#include "${path}/dataManage/population/common/countrymen/dataPublicList.ftl"/>
