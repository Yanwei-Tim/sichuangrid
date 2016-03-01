<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<input type="hidden" name="serviceTeamMemberVo.id" id="objectId" value="${(id)! }" /> 
<input name="serviceTeamMemberVo.objectName" type="hidden" id="objectName" value="${(name)!}"/>
<input type="hidden" name="serviceTeamMemberVo.objectType" id="object_Type" value="${(populationType)! }" /> 

	<table id="serviceTeamMemberList"></table>

<div style="display: none;">
	<@pop.JugePermissionTag ename="serviceTeamMemberManagement">
		<span id="title"><@s.property value="#request.name" escape=false/></span>
	</@pop.JugePermissionTag>
</div>
<script type="text/javascript">

$(document).ready(function(){
<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<@pop.formatterProperty name="position" domainName="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPositionPropertyTypes@TEAMPOSITION" />

	//服务成员列表
	$("#serviceTeamMemberList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel:[
		  {name:"id",index:"id",width:50,hidden:true}, 
		  {name:"teamId",index:"teamId",hidden:true},
	      {name:"memberId",index:"memberId",width:50,hidden:true},
	      {name:"memberName",index:'memberName',label:'姓名',sortable:false,width:80},
	      {name:"gender",label:'性别',sortable:false,formatter:genderFormatter,width:80,align:"center"},
	      {name:"position",label:'职务(身份)',sortable:false,formatter:positionOrRelationFormatter,width:100},
		  {name:'teamName',label:'所在团队',sortable:false,width:150},
	      {name:'mobile',label:'联系手机',sortable:false, width:110,align:"center"},
	      {name:'onDuty',label:'是否在职',formatter:onDutyFormatter,sortable:false, width:80,hidden:true},
	      {name:'teamMember',label:'是否为团队成员',sortable:false, width:80,hidden:true}
	      
		],
		showColModelButton:false,
		sortname:'id',
		rowNum:Math.pow(2,31)-1,
	    shrinkToFit:true,
	    height:'auto'
	});
	$("#serviceTeamMemberList").jqGrid('setFrozenColumns');
	loadTeamMembers();
	
	//加载服务成员
	function loadTeamMembers() {
		var listParam = null;
			listParam = {
				'serviceMemberVo.objectType':$("#object_Type").val(),
				'serviceMemberVo.objectId':${(id)! },
				'serviceMemberVo.objectName':$("#objectName").val()
			};
	
		loadData(listParam);
	}
	//加载服务成员数据
	function loadData(listParam) {
		$("#serviceTeamMemberList").setGridParam({
			url:'${path}/plugin/serviceTeam/router/routerManage/findServiceMembersByServiceMemberVo.action',
			datatype: "json",
			page:1
		});
		$("#serviceTeamMemberList").setPostData(listParam);
		$("#serviceTeamMemberList").trigger("reloadGrid");
	}
	//是否在职
	function onDutyFormatter(el,options,rowData){
		if(rowData.onDuty==0){
			return "否";
		}else{
			return "是";
		}
	}
	
 function positionOrRelationFormatter(el,options,rowData){
		if(rowData.teamMember==1){
					if(!(rowData.position+""=="undefined")){
					    return positionData[rowData.position.id];	
					    }else{
					      return "";	
					    }
		}
		if(rowData.teamMember==0){
			return rowData.relation;
		}
	}
	
});
</script>