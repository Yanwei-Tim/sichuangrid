<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="memberList"></table>
		<div id="memberListPager"></div>
	</div>
	<input type="hidden" id="memberOnDuty" value="${(serviceTeamMember.onDuty)!}"/>
</div>
<input type="hidden" id="teamOrgId" value="${(serviceTeamVo.org.id)!}"/>
<script type="text/javascript">
var dialogWidth=650;
var dialogHeight=360;

$(document).ready(function(){

	<@pop.formatterProperty name="serviceTeamMemberBase.gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<@pop.formatterProperty name="position" domainName="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPositionPropertyTypes@TEAMPOSITION" />
	function genderFormat(cellvalue,options,rowData){
		if(cellvalue && cellvalue.id){
			if(genderDate[cellvalue.id]){
				return genderDate[cellvalue.id];
			}else{
				return "&nbsp;";
			}
		}else{
			return cellvalue;
		}						
	}
	//当前团队所属成员列表
	$("#memberList").jqGridFunction({
		datatype: "local",
	    colNames:['memberId','baseId','orgId','姓名','性别','联系手机','备注','所属职务'],
	   	colModel:[
	   		{name:'memberId',index:'memberId',hidden:true,key:true},
	   	    {name:"baseId",index:"baseId",hidden:true},
	        {name:"org.id",index:"orgId",hidden:true},
	        {name:'name',index:"name",width:119},
	        {name:'gender',index:'gender',width:60,formatter:genderFormat,align:"center"},
	        {name:'mobile',width:120,index:'mobile',sortable:false,width:150,align:"center"},
	        {name:'remark',index:'remark',sortable:false,width:200,hidden:true},
	        {name:"position",index:"position",width:120,align:"center",formatter:positionFormatter}
	     //  ,'是否在职' ,{name:'onDuty',index:'onDuty',sortable:false,align:"center",formatter:onDutyFormatter,width:80}
		],
		height:180,
		showColModelButton:false,
		sortname:'memberId'
	});
	
	$("#memberList").setGridParam({
		url:"${path}/plugin/serviceTeam/serviceTeamManage/findServiceTeamMembers.action",
		datatype: "json",
		page:1
	});
	
	$("#memberList").setPostData({
		"serviceTeamMemberVo.onDuty":${(serviceTeamMember.onDuty)!},
		"serviceTeamMemberVo.teamId":"${(serviceTeamVo.id)!}",
		"serviceTeamMemberVo.org.id":$("#teamOrgId").val()
   	});
	$("#memberList").trigger("reloadGrid");
	jQuery("#memberList").jqGrid('setFrozenColumns');
	
//getMemberList();
});

//获取当前团队成员列表
//function getMemberList(){}

//是否在职
function onDutyFormatter(el,options,rowData){
	if(rowData.onDuty==0){
		return "否";
	}else{
		return "是";
	}
}
</script>