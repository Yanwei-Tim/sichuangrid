<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24">
	<form id="serviceTeamMemberForm" method="post">
	<@pop.token />
 		<input name="serviceTeamMemberVo.org.id" type="hidden" id="service_object_orgId" value=""/>
 		<input name="serviceTeamMemberVo.org.orgInternalCode" type="hidden" id="service_object_orgInternalCode"/>
 		<input name="serviceTeamMemberVo.objectType" type="hidden" id="MemberObjectType" value=""/>
 		<input name="serviceTeamMemberVo.objectId" type="hidden" id="MemberObjectId" value=""/>
	<div class="grid_4 lable-right">
		<label class="form-lbl">所属区域：</label>
	</div>	
	<div class="grid_20">
		<input type="text" id="issueOccurOrgSelector" name="serviceTeamMemberVo.orgScope" value="" class="form-txt" />
    </div>
    <div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">团队类别：</label>
	</div>

	<div class="grid_20">
		<select id="teamTypeId" name="serviceTeamMemberVo.teamType.id" class="basic-input" >
					<@pop.OptionTag name="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPropertyTypes@TEAM" />
				</select>
    </div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">团队名称：</label>
	</div>
	<div class="grid_20">
		<input  type="text" id="teamNameVo" maxlength="50" name="serviceTeamMemberVo.teamName" value="${(serviceTeamMemberVo.teamName)!}" title="请输入团队名称"
			class='form-txt {required:true,maxlength:150,minlength:2,exculdeParticalChar:true,messages:{required:"请输入团队名称",maxlength:$.format("团队名称不能多于{0}个字符"),minlength:$.format("团队名称不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' />
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">姓名：</label>
	</div>
	<div class="grid_20">
		<input type="text" id="memberNameVo" maxlength="20" name="serviceTeamMemberVo.memberName" style="width: 97%" value="${(serviceTeamMemberVo.memberName)!}" title="请输入服务人员姓名"
		class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}' />
	</div>
	</form>

	<div class='clearLine'></div>
	<div style="margin-left: 525px">
		<input type="button"  id='superSearch'  value='查询' style="width: 50px;height: 28px;"/>
		<input type="button"  id='superReload'  value='刷新' style="width: 50px;height: 28px;margin-left: 8px;"/>
	</div>
	
	<div class='clearLine'></div>
	<div style="width: 100%;margin-top: 6px">
			<table id="supervisorMemberList"> </table>
			<div id="supervisorMemberListPager"></div>
	</div>
</div>

<script>
$(document).ready(function(){

//----getSelectedIdWhenUpdate 为commonPopulationList.jsp中设置的全局变量  -------------
//获取业务人员的数据
var personData = $("#"+$('#module').val()+"List").getRowData($("#objectId").val());

function setPersonData(personData){
	var personType = $("#_locationType_").val();
	$("#MemberObjectId").val(personData.id);
	$("#MemberObjectType").val(personType);
}
setPersonData(personData);

	
var tree=$("#issueOccurOrgSelector").treeSelect({
			inputName:"serviceTeamMemberVo.org.id",
			inputCodeName:"serviceTeamMemberVo.org.orgInternalCode",
			rootId:USER_ORG_ID
		});
<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<@pop.formatterProperty name="position" domainName="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPositionPropertyTypes@TEAMPOSITION" />
	//团队成员信息列表
	$("#supervisorMemberList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	      {name:"memberBaseId",index:"memberBaseId",hidden:true},
	       {name:"teamId",index:"teamId",hidden:true},
	      {name:"memberName",index:'memberName',label:'姓名',sortable:false,width:80},
	      {name:"gender",label:'性别',align:'center',sortable:false,formatter:genderFormatter,width:50},
	      {name:"position",label:'职务',sortable:false,formatter:positionFormatter,width:90},
		  {name:'teamName',label:'所在团队',sortable:false,width:120},
	      {name:'org.orgName',label:'所属(区域)网格',sortable:false, width:180},
	      {name:"operation",label:'操作',sortable:false,formatter:addOperationFormatter,align:"center",width:90}
		],
		height:125,
		sortName:'memberBaseId',
	    rowNum:5,
	    rowList:[5,10,15,20,25,30],
	    showColModelButton:false
	});

	$("#supervisorMemberList").jqGrid('setFrozenColumns');
	//loadData();
	
	$("#serviceTeamMemberVo_orgScope").change(function(){
		loadData();
	});
	//查询按钮触发
	$("#superSearch").click(function(){
		loadData();
	});
	
	//加载数据
	function loadData() {
		var data = $("#serviceTeamMemberForm").serializeObject();
		$("#supervisorMemberList").setGridParam({
			url:"${path}/plugin/serviceTeam/router/routerManage/findServiceMemberFromTeams.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		
		$("#supervisorMemberList").setPostData(data);
		$("#supervisorMemberList").trigger("reloadGrid");
	}
	//刷新清空表单内容
	$("#superReload").click(function(){
		 $("#serviceTeamMemberVo_orgScope").val("仅显示本级");
		 $("#teamTypeId").val("请选择");
		 $("#teamNameVo").val("");
		 $("#memberNameVo").val("");
		 $("#supervisorMemberList").clearGridData();
		 loadData();
	});
	//操作按钮
function addOperationFormatter(el,options,rowData){
	return "<a class='addMemberForObject' href='javascript:;' data-rowid='"+rowData.memberBaseId+"'>添加</a>";
}

//添加成员的监听事件
$("#supervisorMemberList").delegate(".addMemberForObject","click",function(event){
		var rowId = $(this).data("rowid");
		addMemberForObject(rowId);
		return false;
	});
	
//添加按钮触发
	$(".addMemberForObject").click(function(){
		addMemberForObject();
	});
	
	//为服务对象添加一个团队服务成员
function addMemberForObject(memberBaseId){
var data = $("#supervisorMemberList").getRowData(memberBaseId);
	$.ajax({
		async: false,
		url:"${path}/plugin/serviceTeam/router/routerManage/addObjectAndMemberRelation.action",
	   	data:{
	   		"serviceMemberWithObject.memberId":memberBaseId,
			"serviceMemberWithObject.objectType":$("#_locationType_").val(),
			"serviceMemberWithObject.objectName":$("#objectName").val(),
			"serviceMemberWithObject.objectId":personData.id,
			"serviceMemberWithObject.teamMember":1,
			"serviceMemberWithObject.onDuty":1,
			"serviceMemberWithObject.teamId":data.teamId,
			"serviceMemberWithObject.objectLogout":1
        },
		success:function(responseData){
			$.messageBox({message:"为服务对象添加服务成员成功！"});
			$("#serviceTeamMemberList").trigger("reloadGrid");
			$("#supervisorMemberList").delRowData(memberBaseId);
			var reccount = $("#supervisorMemberList").jqGrid('getGridParam', 'reccount');
        	if(reccount==0){
        		$("#supervisorMemberList").trigger("reloadGrid");
        	}
			//$("#serviceTeamMemberList").addRowData(data.memberBaseId,data,"first");
		}
	});

}

});


</script>