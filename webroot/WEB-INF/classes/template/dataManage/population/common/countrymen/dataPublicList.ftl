<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

<script>
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
var gridOption = {
		colModel:[
			{name:"id", index:"id", hidden:true,frozen : true,sortable:false},
			{name:"operation",label:"操作",sortable:false,formatter:operateFormatterClaim,width:50,frozen:true,align:"center"},
			{name:"organization.orgName", index:"orgInternalCode", width:220,label:"所属网格",hidden:false,sortable:false},
			<#if moduleName =="FloatingPopulationTemp" || moduleName =="UnsettledPopulationTemp" || moduleName =="HouseholdStaffTemp">
			<#elseif moduleName=="MentalPatientTemp">
			{name:'dangerLevel',label:"危险程度",index:"dangerLevel",width:90,formatter:dangerLevelColor,sortable:true},
			<#else>
			{name:"attentionExtent",index:"attentionExtent",label:"关注程度",width:100,formatter:attentionExtentColor},
			</#if>
			{name:"name", index:"name",label:"姓名",formatter:nameFontClaim,width:70,sortable:true},
			{name:"idCardNo", index:"idCardNo", width:160, label:"身份证号码",sortable:true,align:"center"},
			{name:"usedName", index:'usedName', label:'曾用名', width:80 ,hidden:true,sortable:true},
			{name:"gender", label:"性别",index:"gender",width:60, formatter:genderFormatter,align:"center",sortable:true},
			{name:"otherAddress",label:"临时居所",width:200,sortable:true},		
			{name:"currentAddress",label:"常住地址",sortable:true, width:200,hidedlg:true,hidden:true},
			{name:"workUnit",label:"工作单位或就读学校", width:200,sortable:true,hidden:true},
			{name:"province",label:"户籍地", width:200,sortable:true,formatter:householdRegisterFormatter, hidden:true},
			{name:"nativePlaceAddress",label:"户籍详址",width:200, hidden:true,sortable:true},
			//{name:"stature",label:"身高(cm)",width:100,sortable:true,hidden:true},
			{name:"nation",label:"民族",formatter:nationFormatter,width:90,sortable:true,hidden:true},
			{name:"politicalBackground",label:"政治面貌",formatter:politicalBackgroundFormatter,width:150,sortable:true,hidden:true},
			{name:"schooling",label:"文化程度",formatter:schoolingFormatter,width:100,sortable:true,hidden:true},
			{name:"career",label:"职业",formatter:careerFormatter,width:100,sortable:true,hidden:true},
			{name:"maritalState",label:"婚姻状况",formatter:maritalStateFormatter,width:100,sortable:true,hidden:true},
			//{name:"bloodType",label:"血型",formatter:bloodTypeFormatter,width:100,sortable:true,hidden:true},
			//{name:"faith",label:"宗教信仰",formatter:faithFormatter,width:100,sortable:true,hidden:true},
			{name:"telephone",label:"联系电话", width:140,sortable:true,hidden:true,align:"center"},
			{name:"mobileNumber",label:"联系手机", width:100,sortable:true,hidden:true,align:"center"},
			//{name:"email",label:"电子邮件", width:140,sortable:true,hidden:true,sortable:true},
			{name:"birthday",label:"出生日期", width:100,hidden:true,align:"center",sortable:true},
			<#if moduleName =="DruggyTemp">
				{name:"ferretOutDate",label:"查获日期", width:100,hidden:true,align:"center",sortable:true},
			</#if>
			{name:'death',sortable:true,hidden:true,hidedlg:true,width:80}
		]
	};
for(var i=0;i<gridOptionPrivate.colModel.length;i++){
	gridOption.colModel.splice(9+i,0,gridOptionPrivate.colModel[i]);
}
</script>
<#assign bigType="population"/>
<#assign  importStartRow=6/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>