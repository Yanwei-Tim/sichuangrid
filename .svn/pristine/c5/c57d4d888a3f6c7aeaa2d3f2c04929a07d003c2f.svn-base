<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">SchoolTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="kind" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOL_PROPERTY" />
<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOL_TYPE" />
<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />

var gridOption = {
		colModel:[
	    	{name:'id', index:'id', hidden:true },
	    	{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
  			{name:"organization.orgName", index:"orgInternalCode",label:"所属网格",width:150, hidden:true},
	    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
  			{name:'name-Font',index:'name',label:'学校名称',frozen:true,formatter:nameFont,width:200},
  			{name:'name',index:'name',label:'学校名称',frozen:true,hidden:true,width:200},
  			{name:'address',index:'address',label:'学校地址',sortable:false,width:200},
  			{name:'manager',index:'manager',label:'校长',width:100},
  			{name:'englishName',index:"englishName",label:'英文名称',width:200,hidden:true},
  			{name:'kind',index:'kind',label:'学校性质',sortable:false, formatter:kindFormatter,width:100,align:'center'},
  			{name:'type',index:'type',label:'学校类型',sortable:false, formatter:typeFormatter,width:100},
 			{name:'vicePresident', index:'contactName',label:'法制副校长',sortable:false,width:100},
 			
  			{name:'telephone',index:'telephone', label:'联系电话',align:'center',hidden:true,sortable:false,width:130},
  			{name:'mobileNumber',index:'mobileNumber', label:'联系手机',align:'center',sortable:false,hidden:true,width:100},
  			{name:"webSite",index:"webSite",label:"学校网址",hidden:true,width:150},
  			{name:"fax",index:"fax",label:"传真",hidden:true,width:150},
  			{name:"email",index:"email",label:"电子邮箱",hidden:true,width:150}
  			//{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
  			
	  	]
};
var dialogWidth=800;
var dialogHeight=600;

function getLocationName(rowData){
	return rowData.chineseName;
}
</script>

<#assign moduleName="SchoolTemp"/>
<#assign moduleCName="学校"/>
<#assign bigType="location"/>
<#assign  importStartRow=5/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>

