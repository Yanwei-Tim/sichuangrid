<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">NewEconomicOrganizationsTemp</@s.param>
</@s.include>
<script type="text/javascript">
<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE" />
var dialogWidth=800;
var dialogHeight=600;

function licenseNumberFont(el,options,rowData){
		if(rowData.isEmphasis==1||rowData.isEmphasis=='1'){
			return "<font color='#778899'>"+rowData.licenseNumber+"</font>";
		}else{
			return "<font color='#000'>"+rowData.licenseNumber+"</font>";
		}
	}

	
	function hasServiceTeamMemberFormatter(el,options,rowData){
		if(rowData.hasServiceTeamMember==0){
			return "<@s.property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING'/>";
		}else{
			return "<@s.property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HASSTRING'/>";
		}
	}
var gridOption = {
		colModel:[
	        {name:"id", index:"id",sortable:false, hidden:true,frozen:true},
	    	{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:65,frozen:true,sortable:false,align:'center' },
	        {name:'organization.orgName',sortable:false,index:'organization.orgName',label:'所属网格',width:150,hidden:true},
	        {name:"name", sortable:true, label:'名称',formatter:nameFontClaim,width:200,frozen:true },
	        {name:"address", sortable:true, label:'住所', width:200 },
	        {name:'manager',  sortable:true, label:'负责人', width:100},
	        {name:'type',formatter:typeFormatter, sortable:true, label:'类别', width:150},
	        {name:'licenseNumber',sortable:true, label:'营业执照号码', formatter:licenseNumberFont, width:130,frozen:true},
	        {name:'validityStartDate',sortable:true, label:'有效期开始日期',align:'center', width:110},
	        {name:'validityEndDate', sortable:true, label:'有效期结束日期',align:'center', width:110},
	        {name:'area',  sortable:true, label:'面积(<font size="2">m</font><font size="1"><sup>2</sup></font>)',width:100,hidden:true},
	        {name:'employeeNumber', sortable:true, label:'从业人数', width:90,hidden:true},
	        {name:'partyNum', sortable:true, label:'党员人数', width:90,hidden:true},
	        {name:'foxNumber', sortable:true, label:'传真', width:120,hidden:true},
	        {name:'telephone', sortable:true, label:'固定电话',align:'center', width:120,hidden:true},
	        {name:'mobileNumber', sortable:true, label:'联系手机',align:'center', width:100,hidden:true},
	        {name:'isEmphasis',sortable:false,hidden:true,hidedlg:true,width:80}
	    	]
};

</script>	
<#assign moduleName="NewEconomicOrganizationsTemp"/>
<#assign bigType="location"/>
<#assign  importStartRow=6/>
<#assign moduleCName="新经济组织"/>
<#assign isNew=1/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>
	
