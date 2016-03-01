<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">securityEnterpriseTemp</@s.param>
</@s.include>
<script type="text/javascript">
	<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@SECURITY_TYPE" />
	<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
	function isRiskEnterprise(el, options, rowData){
		if(true == rowData.riskEnterprise){
			return "是";
		}else{
			return "否";
		}
	}
	
	
	
	var gridOption ={
		colModel:[
			{name:"id", index:"id", hidden:true },
	    	{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center' },
			{name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:150, hidden:true},
	    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
			{name:"name-Font", index:"name", label: '治安重点名称',frozen:true,formatter:nameFontClaim,width:200},
			{name:"name", index:"name", label: '治安重点名称',hidden:true,frozen:true,width:150},
			{name:"address",sortable:false,label:"治安重点地址",width:200},
			{name:"manager", index:"manager", label:'负责人',width:100},
			{name:"type", index:"type", label:'治安重点类型', formatter:typeFormatter,width:100},
			{name:"riskEnterprise", sortable:false, label:'是否存在隐患', formatter:isRiskEnterprise,width:100,align:'center'},
			{name:"hiddenCases", sortable:false, label:'隐患情况',width:200},
			{name:"hiddenRectification", sortable:false, label:'隐患整改情况',width:200,hidden:true},
			
			{name:"telephone",sortable:false,label:"负责人联系电话",hidden:true,width:120,align:'center'},
			{name:"mobileNumber",sortable:false,label:"负责人手机号码",hidden:true,width:100,align:'center'}
			//{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
		]
	};	
	var dialogWidth=800;
	var dialogHeight=600;
	
	function getLocationName(rowData){
		return rowData.name;
	}
</script>
<#assign moduleName="securityEnterpriseTemp"/>
<#assign moduleCName="治安重点"/>
<#assign bigType="location"/>
<#assign  importStartRow=6/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>
