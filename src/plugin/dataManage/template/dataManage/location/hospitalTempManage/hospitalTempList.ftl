<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">HospitalTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="level" domainName="@com.tianque.domain.property.PropertyTypes@HOSPITAL_LEVEL" />
<@pop.formatterProperty name="kind" domainName="@com.tianque.domain.property.PropertyTypes@HOSPITALS_KIND" />
<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@HOSPITALS_TYPE" />
<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

var gridOption = {
		colModel:[
	    	{name:"id",index:"id",hidden:true},
   	    	{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
    		{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
	        {name:"name",index:'name',label:'医院名称',sortable:true,formatter:nameFontClaim},
	       //{name:'anotherName',index:'anotherName',label:'医院别名'},
	        {name:'director',index:'director',sortable:true,label:'医院院长'},
	        {name:'address',index:'address',sortable:true,label:'医院地址'},
	        //{name:'level',index:'level',label:'医院等级',formatter:levelFormatter},
	       //{name:'medicalInsurance',index:'medicalInsurance',label:'是否医保'},
	        {name:'affiliatedUnit',index:'affiliatedUnit',sortable:true,label:'所属单位'},
	        {name:'organization.orgName',index:'orgInternalCode',label:'所属网格', hidden:true},
	        //{name:'contactName',index:'contactName',label:'联系人',sortable:false},
	        {name:'telephone',index:'telephone',label:'联系电话',sortable:true},
	        {name:'mobileNumber',index:'mobileNumber',label:'联系手机',sortable:true},
	        {name:'email',index:'email',label:'电子邮箱',sortable:true,hidden:true},
	        {name:'fax',index:'fax',label:'传真',sortable:true,hidden:true},
	        {name:'kind',index:'kind',label:'医院性质',formatter:kindFormatter,hidden:true},
	        {name:'type',index:'type',label:'医院类型',formatter:typeFormatter,hidden:true}
  			
	  	]
};
var dialogWidth=800;
var dialogHeight=600;

function getLocationName(rowData){
	return rowData.chineseName;
}
</script>

<#assign moduleName="HospitalTemp"/>
<#assign moduleCName="医院"/>
<#assign bigType="location"/>
<#assign  importStartRow=5/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>

