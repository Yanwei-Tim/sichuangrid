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
		<input type="button"  id='superAdd'  value='选择' style="width: 50px;height: 28px;margin-left: 8px;"/>
	</div>
	
	<div class='clearLine'></div>
	<div style="width: 100%;margin-top: 6px">
			<table id="supervisorList"> </table>
			<div id="supervisorListPager"></div>
	</div>
	
	
	
<div class='clearLine'></div>
<div class='grid_24'>
	<b>欲添加成员</b>
</div>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="appendTeamMembersList"></table>
		<div id="appendTeamMembersListPager"></div>
	</div>
</div>
</div>



<script>
var token = "<@s.property value='@com.tianque.core.util.CreateTokenUtil@getToken()'/>";
$(document).ready(function(){

//----getSelectedIdWhenUpdate 为commonPopulationList.jsp中设置的全局变量  -------------
//获取业务人员的数据
var personData = $("#"+$('#lowerCaseModuleName').val()+"List").getRowData($("#objectId").val());
function setPersonData(personData){
	var personType = $("#_populationType_").val();
	$("#MemberObjectType").val(personType);
}
setPersonData(personData);



	
var tree=$("#issueOccurOrgSelector").treeSelect({
			inputName:"serviceTeamMemberVo.org.id",
			inputCodeName:"serviceTeamMemberVo.org.orgInternalCode",
			rootId:USER_ORG_ID
		});

//已选成员库
$("#appendTeamMembersList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	      {name:"memberBaseId",index:"memberBaseId",hidden:true},
	       {name:"teamId",index:"teamId",hidden:true},
	      {name:"memberName",index:'memberName',label:'姓名',sortable:false,width:80},
	      {name:"gender",label:'性别',align:'center',sortable:false,width:50},
	      {name:"position",label:'职务',sortable:false,width:90},
		  {name:'teamName',label:'所在团队',sortable:false,width:120},
	      {name:'org.orgName',label:'所属(区域)网格',sortable:false, width:180},
	      {name:"operation",label:'操作',sortable:false,formatter:deleteOperationFormatter,align:"center",width:90}
		],
		height:125,
		pgbuttons: false, // 分页按钮是否显示 
		pginput: false, // 是否允许输入分页页数 
		rownumbers: false, // 是否显示行数 
		sortName:'memberBaseId',
		rowList:false,
	    showColModelButton:false
	    
	});

	$("#appendTeamMembersList").jqGrid('setFrozenColumns');
		
		
<@pop.formatterProperty name="position" domainName="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPositionPropertyTypes@TEAMPOSITION" />
	//团队成员信息列表
	$("#supervisorList").jqGridFunction({
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
	    showColModelButton:false,
	    multiselect:true
	});

	$("#supervisorList").jqGrid('setFrozenColumns');
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
		var ids = $("#appendTeamMembersList").jqGrid('getDataIDs');
		var memberIds ="";
		for(var i=0;i<ids.length;i++){
			memberIds+=ids[i]+",";
		}
		$("#supervisorList").setGridParam({
			url:"${path}/plugin/serviceTeam/router/routerManage/findServiceMemberFromTeams.action?mode=allSearch&serviceTeamMemberVo.memberIds="+memberIds,
			datatype: "json",
			page:1,
			mtype:"post"
		});
		
		$("#supervisorList").setPostData(data);
		$("#supervisorList").trigger("reloadGrid");
	}
	//刷新清空表单内容
	$("#superReload").click(function(){
		 $("#serviceTeamMemberVo_orgScope").val("仅显示本级");
		 $("#teamTypeId").val("请选择");
		 $("#teamNameVo").val("");
		 $("#memberNameVo").val("");
		 $("#supervisorList").clearGridData();
		 loadData();
	});
	//操作按钮
function deleteOperationFormatter(el,options,rowData){
	return "<a class='deleteMemberForObject' href='javascript:;' data-rowid='"+rowData.memberBaseId+"'>删除</a>";
}

function addOperationFormatter(el,options,rowData){
	return "<a class='addMemberObject' href='javascript:;' data-rowid='"+rowData.memberBaseId+"'>添加</a>";
}

$("#superAdd").click(function(){
	addMemberForObject();
	loadData();
})


$("#supervisorList").delegate(".addMemberObject","click",function(event){
		var rowId = $(this).data("rowid");
		addMemberObject(rowId);
		loadData();
		return false;
	});

//成员的监听事件
$("#appendTeamMembersList").delegate(".deleteMemberForObject","click",function(event){
		var rowId = $(this).data("rowid");
		deleteMemberForObject(rowId);
		loadData();
		return false;
	});
	
//添加按钮触发
	$(".addMemberForObject").click(function(){
		addMemberForObject();
		loadData();
	});
	//为服务对象添加一个团队服务成员
function addMemberForObject(){
	var selectedId = $("#supervisorList").jqGrid("getGridParam", "selarrrow");
	var oldIds = $("#supervisorList").jqGrid('getDataIDs');
	var ids = $("#appendTeamMembersList").jqGrid('getDataIDs');
	var dataRow;
	var all = selectedId.length==oldIds.length;
	for(var i =0 ;i<selectedId.length;i++){
		dataRow = $("#supervisorList").getRowData(selectedId[i]);
		if($.inArray(selectedId[i], ids)==-1){
		  $("#appendTeamMembersList").addRowData(dataRow.memberBaseId,dataRow,"first");
		}
	};
	$("#appendTeamMembersList").trigger("reloadGrid");;
	
}

function addMemberObject(selectedId){
	var ids = $("#appendTeamMembersList").jqGrid('getDataIDs');
	var dataRow;
	for(var i=0;i<ids.length;i++){
		if(selectedId==ids[i])
		return;
	}
	dataRow = $("#supervisorList").getRowData(selectedId);
	$("#appendTeamMembersList").addRowData(selectedId,dataRow,"first");
	$("#appendTeamMembersList").trigger("reloadGrid");;
	
}

	$(".deleteMemberForObject").click(function(){
		deleteMemberForObject();
		loadData();
	});
	function deleteMemberForObject(memberBaseId){
		$("#appendTeamMembersList").jqGrid("delRowData",memberBaseId);
		var row = $("#supervisorList").getRowData(memberBaseId);
		$("#supervisorList .cbox").each(function(){
		   var id = $(this).attr("id").split("_")[2];
		   if(id==memberBaseId) {
		   	$(this).attr("checked", false); 
		   	$("#cb_supervisorList").attr("checked", false);
		   }
		})
	}
	
	
	
});

function saveMembers(dialogName){
	var personDataIds = $("#"+$('#lowerCaseModuleName').val()+"List").jqGrid("getGridParam", "selarrrow");
	var appendMembers = $("#appendTeamMembersList").jqGrid('getDataIDs');
	var dataRow ;
	var personRow ;
	if(appendMembers.length>10){
		$.messageBox({message:"一次最多保存10个服务成员，请分多次保存！",level:"warn"});
		return ;
	}
	
	if(appendMembers.length==0){
		$.messageBox({message:"请添加服务成员！",level:"warn"});
		return ;
	}
	var serviceMembers = new Array();
	var serviceMember;
	for(var id=0;id<personDataIds.length;id++){
		personRow= $("#"+$('#lowerCaseModuleName').val()+"List").getRowData(personDataIds[id]);
		for(var i =0 ;i<appendMembers.length;i++){
			serviceMember = new  Object();
			dataRow = $("#appendTeamMembersList").getRowData(appendMembers[i]);
			serviceMember.memberId=dataRow.memberBaseId;
			serviceMember.teamId=dataRow.teamId;
			serviceMember.objectType=$("#_populationType_").val();
			serviceMember.objectId=personRow.id;
			serviceMember.teamMember=1,
			serviceMember.onDuty=1;
			serviceMember.objectLogout=1;
			serviceMember.objectName=$(personRow.name).text();
			serviceMembers.push(serviceMember);
		}
	}
	$.ajax({
		type: "POST",
		async: false,
		url:"${path}/plugin/serviceTeam/router/routerManage/addObjectAndMembersRelation.action",
	   	data:{
	   		"serviceMembersWithObjectVo.serviceMembers": $.toJSON(serviceMembers),
			"struts.token":token
        },
		success:function(responseData){
			if (responseData==true){
				$.messageBox({message:"为服务对象添加服务成员成功！"});
				$("#"+dialogName).dialog("close");
				$("#"+$('#lowerCaseModuleName').val()+"List").trigger("reloadGrid");
			}else {
				$.messageBox({message:responseData,level:"error"});
			}
		}
	});

}


</script>