<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container_24">
<div class='clearLine'></div>
<div class='grid_24'>
	<b>现有成员</b>
</div>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="maintainMembers"></table>
		<div id="maintainMembersPager"></div>
	</div>
	<div id="TeamMemberMaintanceDialog"></div>
</div>
<div class='clearLine'></div>
<input type="hidden" id="_teamId_" value="${(serviceTeamVo.teamType)!}"/>
<input type="hidden" id="teamOrgId" value="${(serviceTeamVo.org.id)!}"/>
</div>

<script type="text/javascript">
var dialogWidth=650;
var dialogHeight=360;

$(document).ready(function(){
	<@pop.formatterProperty name="serviceTeamMemberBase.gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
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
	$("#maintainMembers").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['id','baseId','orgId','姓名','性别','联系手机','固定电话','备注','所属职务'],
	   	colModel:[
	   	    {name:"id",index:"id",hidden:true},
	   	    {name:"baseID",index:"baseID",hidden:true},
	        {name:"serviceTeamMemberBase.orgId",index:"orgId",hidden:true},
	        {name:'serviceTeamMemberBase.name',index:"name",width:100,align:"center"},
	        {name:'serviceTeamMemberBase.gender.id',index:'gender',width:50,formatter:genderFormat,align:"center"},
	        {name:'serviceTeamMemberBase.mobile',width:100,index:'mobile',sortable:false,width:150,align:"center"},
	        {name:'serviceTeamMemberBase.homePhone',index:'homePhone',sortable:false,width:150,hidden:true},
	        {name:'serviceTeamMemberBase.remark',index:'remark',sortable:false,width:200,hidden:true},
	        {name:"position",index:"position",width:100,align:"center"}
		],
		height:120,
		showColModelButton:false
	});
	jQuery("#maintainMembers").jqGrid('setFrozenColumns');
	
	$("#maintainMembers").setGridParam({
		url:"${path}/plugin/serviceTeam/serviceTeamManage/findServiceTeamMembers.action",
		datatype: "json",
		page:1
	});
	$("#maintainMembers").setPostData({
		"serviceTeamMemberVo.onDuty":"true",
		"serviceTeamMemberVo.teamTypeId":"${(serviceTeamVo.teamType)!}",
		"serviceTeamMemberVo.orgId":$("#teamOrgId").val(),
		"serviceTeamMemberVo.name":""
   	});
	$("#maintainMembers").trigger("reloadGrid");
	
});


//职务字典选项
function positionTypeFormatter(el,options,rowData){
	return "<select id='positionType' name='position.id' class='basic-input'><@pop.OptionTag name='@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPositionPropertyTypes@TEAMPOSITION' /></select>";
}

</script>