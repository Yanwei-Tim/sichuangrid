<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">NewSocietyOrganizationsTemp</@s.param>
</@s.include>

<script type="text/javascript">
<@pop.formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@SOCIETY_GROUP" />
<@pop.formatterProperty name="subType" domainName="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" />
var dialogWidth=800;
var dialogHeight=640;
function hasServiceTeamMemberFormatter(el,options,rowData){
	if(rowData.hasServiceTeamMember==0){
		return "<@s.property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HAVNTSTRING'/>";
	}else{
		return "<@s.property value='@com.tianque.core.util.ConstantsProduct$HasOrHavntOrAll@HASSTRING'/>";
	}
}
var gridOption = {
		colModel:[
			{name:"id",index:'id',sortable:false,hidden:true},
	    	{name:"operator", index:'id',label:'操作',formatter:operateFormatterClaim,width:65,frozen:true,sortable:false,align:'center' },
			{name:"organization.orgName", index:'orgInternalCode',label:"所属区域",align:'center',sortable:false,width:180, hidden:true},
	 		{name:"name",index:'name',label:"组织名称",sortable:true,formatter:nameFontClaim,width:200},
	 		{name:"address",index:'address',sortable:true,label:"住所",width:200},
	 		{name:"manager",index:'manager',label:"法定代表人",sortable:true,width:120},
			{name:"type",index:'type',label:"组织类别",formatter:typeFormatter,sortable:true,width:120},
			//{name:'subType',index:'subType',label:'组织子类别',width:120,sortable:true,formatter:subTypeFormatter},
			{name:"validityStartDate",index:'validityStartDate',label:"有效期开始日期",align:'center',sortable:true,width:120},
			{name:"validityEndDate",index:'validityEndDate',label:"有效期结束日期",align:'center',sortable:true,width:120},
			{name:"telephone",index:'telephone',label:"固定电话",align:'center',sortable:true,width:120,hidden:true},
			{name:"mobileNumber",index:'mobileNumber',label:"联系手机",align:'center',sortable:true,width:120,hidden:true},
			{name:"isEmphasis",sortable:false,hidden:true,hidedlg:true,width:100},
			{name:"chargeUnit",index:'chargeUnit',label:"业务主管单位",sortable:true,width:120,hidden:true},
			{name:"registrationNumber",index:'registrationNumber',label:"登记证号码",sortable:true,width:120,hidden:true},
			{name:"personNum",index:'personNum',label:"成员数",sortable:true,width:90,hidden:true},
			{name:"partyNum",index:'partyNum',label:"党员人数",sortable:true,width:90,hidden:true},
			{name:"introduction",index:'introduction',label:"简 介",sortable:true,width:120,hidden:true},
			{name:"honor",index:'honor',label:"获得荣誉",sortable:true,width:120,hidden:true},
			{name:"remark",sortable:true,label:"备注",hidden:true,width:100}
		]
	};
</script>
<#assign moduleName="NewSocietyOrganizationsTemp"/>
<#assign bigType="location"/>
<#assign  importStartRow=6/>
<#assign moduleCName="社会组织"/>
<#assign isNew=1/>
<#include "${path}/dataManage/common/publicFrame.ftl"/>

