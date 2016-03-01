<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">safetyProductionTemp</@s.param>
</@s.include>
<script type="text/javascript">
	<@pop.formatterProperty name="industry" domainName="@com.tianque.domain.property.PropertyTypes@BUSINESSTYPE" />
	<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@ENTERPRISE_TYPE" />
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
			{name:"id", index:"id",sortable:false, hidden:true },
	    	{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:80,frozen:true,sortable:false,align:'center'},
			{name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:150,sortable:false, hidden:true},
	    	{name:"attentionExtent",index:"attentionExtent",label:"关注程度",sortable:true,width:100,formatter:attentionExtentColor,frozen:true},
			{name:"name-Font", index:"name", label: '企业名称',frozen:true,sortable:true,formatter:nameFontClaim,width:200},
			{name:"name", index:"name", label: '企业名称',sortable:true,frozen:true,hidden:true,width:200},
			{name:"type", index:"type", label:'企业类型', sortable:true,formatter:typeFormatter,width:90,align:'center'},
			{name:"businessLicense",sortable:true, label: '工商执照号码',width:100},
			{name:"manager", index:"manager", sortable:true,label:'法人代表',width:100},
			{name:"industry", index:"industry", sortable:true,label:'所属行业', formatter:industryFormatter,width:100},
			{name:"riskEnterprise",sortable:false, label:'是否危化企业',align:'center',formatter:isRiskEnterprise,width:100},
			
			{name:"hiddenCases", sortable:true, label:'隐患情况',hidden:true,width:200},
			{name:"employeeAmount",sortable:true,label:"员工数",hidden:true,width:100},
			{name:"partyMemberAmount",sortable:true,label:"党员数",hidden:true,width:100},
			{name:"area",label:"面积",sortable:false,hidden:true,width:100},
			{name:"fax",sortable:true,label:"传真",hidden:true,width:150},
			{name:"telephone",sortable:true,label:"法人联系电话",hidden:true,width:120},
			{name:"mobileNumber",sortable:true,label:"法人手机号码",hidden:true,width:100},
			{name:"address",sortable:true,label:"企业地址",hidden:true,width:200}
			//{name:"isEmphasis",sortable:false,label:"是否关注",hidden:true,hidedlg:true,width:100}
		]
	};	
	var dialogWidth=800;
	var dialogHeight=600;

	function getLocationName(rowData){
		return rowData.name;
	}
</script>
<#assign moduleName="safetyProductionTemp"/>
<#assign moduleCName="安全生产重点"/>
<#assign bigType="location"/>
<#assign  importStartRow=6/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>
