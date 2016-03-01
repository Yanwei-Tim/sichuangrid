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
		<div class="btnbanner btnbannerData">
			是否在职
			<select id="inJob" class="basic-input" name="serviceTeamMemberVo.onDuty">
				<option value="">全部</option>
				<option value="1" selected>是</option>
				<option value="0">否</option>
			</select>
			<div class="ui-widget autosearch">
			    <input type="text" value="请输入姓名" name="serviceTeamMemberVo.name" id="nameForSearch" 
			   	 maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名':this.value;"
	  			  onfocus="value=(this.value=='请输入姓名')?'':this.value;"/>
				<button id="refreshSearchKeyForTeam" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a id="searchInTeam" href="javascript:;"><span>搜索</span></a>
			<a id="outOfTeam" href="javascript:;"><span>离职</span></a>
			<a id="backToTeam" href="javascript:;"><span>重新担任</span></a>
			<div style="display: none;">
			<@pop.JugePermissionTag ename="downServiceTeam">
				<a id="export" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>导出Excel</span></a>
			</@pop.JugePermissionTag>
			</div>
		</div>
		<table id="maintainMembers"></table>
		<div id="maintainMembersPager"></div>
	</div>
	<div id="TeamMemberMaintanceDialog"></div>
</div>
<div class='clearLine'></div>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
	    <input type="text" value="请输入姓名" name="searchText" id="searchText" 
	    maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名':this.value;"
	    onfocus="value=(this.value=='请输入姓名')?'':this.value;"/>
		<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<a id="fastSearchButton" href="javascript:;"><span>搜索</span></a>
	<a id="refreshButton" href="javascript:;"><span>刷新</span></a>
</div>
<div class='clearLine'></div>
<div class='grid_24'>
	<b>可选成员</b>
</div>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="appendTeamMemberList"></table>
		<div id="appendTeamMemberListPager"></div>
	</div>
</div>
<input type="hidden" id="_teamId_" value="${(serviceTeamVo.id)!}"/>
<input type="hidden" id="teamOrgId" value="<@s.property value='@com.tianque.core.util.ThreadVariable@getOrganization().getId()'/>"/>
<input id="position_in_team" type="hidden" name="positionInTeam" value="" />
</div>

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
	$("#maintainMembers").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['memberId','baseId','internalId','orgId','姓名','性别','联系手机','备注','所属职务','是否在职','操作'],
	   	colModel:[
	   		{name:'memberId',index:'memberId',hidden:true,key:true},
	   		{name:'internalId',index:"internalId",sortable:false,hidden:true,frozen:true},
	   	    {name:"baseId",index:"baseId",hidden:true},
	        {name:"org.id",index:"orgId",hidden:true},
	        {name:'name',index:"name",width:100},
	        {name:'gender',index:'gender',width:50,formatter:genderFormat,align:"center"},
	        {name:'mobile',width:100,index:'mobile',sortable:false,width:150,align:"center"},
	        {name:'remark',index:'remark',sortable:false,width:200,hidden:true},
	        {name:"position",index:"position",width:100,align:"center",formatter:positionFormatter},
	        {name:'onDuty',index:'onDuty',sortable:false,align:"center",formatter:onDutyFormatter,width:80},
	        {name:'',index:'',sortable:false,width:100,align:"center",formatter:removeOperationFormatter}
		],
		height:120,
		//multiselect:true,
		showColModelButton:false,
		sortname:'memberId'
	});
	jQuery("#maintainMembers").jqGrid('setFrozenColumns');
	
	$("#maintainMembers").setGridParam({
		url:"${path}/plugin/serviceTeam/serviceTeamManage/findServiceTeamMembers.action",
		datatype: "json",
		page:1
	});
	getMemberList();
	
	//待选成员库
	$("#appendTeamMemberList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['baseId','orgId','所属区域(网格)','姓名','性别','联系手机','职务','操作'],
	   	colModel:[
	   	     {name:'baseId',index:'baseId',hidden:true,key:true}
	        ,{name:'orgId',index:'orgId',hidden:true}
	        ,{name:'org.orgName',index:'org.orgName',sortable:false,width:200}
	        ,{name:'name',index:'name',width:100}
	        ,{name:'gender',index:'gender',width:50,align:"center",formatter:genderFormat}
	        ,{name:'mobile',width:100,index:'mobile',sortable:false,width:150,align:"center"}
	        ,{name:'position',index:'position',sortable:false,width:100,align:"center",formatter:positionTypeFormatter}
	        ,{name:'operation',index:'operation',sortable:false,width:100,align:"center",formatter:addOperationFormatter}
		],
		height:120,
		//multiselect:true,
		showColModelButton:false,
		sortname:'baseId'
	});
	jQuery("#appendTeamMemberList").jqGrid('setFrozenColumns');
	
	$("#appendTeamMemberList").setGridParam({
		url:"${path}/plugin/serviceTeam/serviceTeamManage/findServiceTeamMemberNotInTeam.action",
		datatype: "json",
		page:1
	});
	getMemberNotInTeamList();

	//在当前团队内搜索
	$("#searchInTeam").click(getMemberList);
	
	//离职
	$("#outOfTeam").click(function(event){
		var rowData = getSelectedRowData();
		if(rowData.internalId>USER_ORG_LEVEL){
			$.messageBox({level:'warn',message:"选中的成员层级高于当前登录层级，无法对该成员进行删除操作！"});
			return;
		}
		if(checkDutyOutOrBack("out")){
			if(rowData.onDuty=='否'){
				$.messageBox({level:'warn',message:"该成员已经离职！"});
				return false;
			}
			doDutyOutOrBack("out",rowData.memberId);
		}
	});
	
	//重新担任
	$("#backToTeam").click(function(event){
		var rowData = getSelectedRowData();
		if(rowData.internalId>USER_ORG_LEVEL){
			$.messageBox({level:'warn',message:"选中的成员层级高于当前登录层级，无法对该成员进行删除操作！"});
			return;
		}
		if(checkDutyOutOrBack("back")){
			if(rowData.onDuty=='是'){
				$.messageBox({level:'warn',message:"该成员是在职成员，无需重新担任！"});
				return false;
			}
			doDutyOutOrBack("back",rowData.memberId);
		}
	});
	
	//是否在职过滤
	$("#inJob").change(getMemberList);
	
	//移除成员的监听事件
	$("#maintainMembers").delegate(".removeMemberToTeam","click",function(event){
		var rowId = $(this).data("rowid");
		var teamId = $(this).data("teamId");
		removeMemberToTeam(rowId);
		return false;
	});
	
	//添加成员的监听事件
	$("#appendTeamMemberList").delegate(".addMemberToTeam","click",function(event){
		var rowId = $(this).data("rowid");
		addMemberToTeam(rowId);
		return false;
	});
		
	//成员库搜索框清空	
	$("#refreshSearchKey").click(function(event){$("#searchText").attr("value","请输入姓名");});
	
	//成员库刷新
	$("#refreshButton").click(function(event){$("#searchText").attr("value","请输入姓名");getMemberNotInTeamList();});
	
	//团队内成员搜索框清空
	$("#refreshSearchKeyForTeam").click(function(event){$("#nameForSearch").attr("value","");});
	
	//成员库搜索
	$("#fastSearchButton").click(function(){
		if($("#searchText").val()=='请输入姓名'){
			return;
		}
		getMemberNotInTeamList();
	});
});

//获取当前团队成员列表
function getMemberList(){
	$("#maintainMembers").setPostData({
		"serviceTeamMemberVo.onDuty":$("#inJob").val(),
		"serviceTeamMemberVo.teamId":"${(serviceTeamVo.id)!}",
		"serviceTeamMemberVo.org.id":$("#teamOrgId").val(),
		"serviceTeamMemberVo.name":$("#nameForSearch").val()=='请输入姓名'?'':$("#nameForSearch").val()
   	});
	$("#maintainMembers").trigger("reloadGrid");
}

//获取成员库列表
function getMemberNotInTeamList(){
	$("#appendTeamMemberList").setPostData({
		"serviceTeamMemberVo.teamId":"${(serviceTeamVo.id)!}",
		"serviceTeamMemberVo.org.id":$("#teamOrgId").val(),
		"serviceTeamMemberVo.name":$("#searchText").val()=='请输入姓名'?'':$("#searchText").val()
   	});
	$("#appendTeamMemberList").trigger("reloadGrid");
}

//执行离任或再任
function doDutyOutOrBack(mode,memberId){
	var info = "离职";
	var onDuty=  0;
	if(mode == "back"){
		info = "重新担任";
		onDuty = 1;
		doDutyOutOrBackReal(mode,memberId,onDuty,info,0);
	}else{
	//离职时判断是否该成员有关联的对象
		$.ajax({
			async: false,
			url:"${path}/plugin/serviceTeam/serviceTeamManage/findServiceTeamMemberHasObjects.action?",
			data:{
		   		"serviceTeamMemberVo.memberId":memberId,
		   		"serviceTeamMemberVo.onDuty":onDuty,
		   		"serviceTeamMemberVo.teamId":$("#_teamId_").val()
	        },
	   		success:function(responseData){
				if(responseData>0){
					$.confirm({
						title:"确认离职",
						message:"该成员存在服务对象，是否确定进行离职操作?",
						okFunc: function(){
							doDutyOutOrBackReal(mode,memberId,onDuty,info,responseData);
						}
					});
				}else{
					doDutyOutOrBackReal(mode,memberId,onDuty,info,0);
				}
			}
		});
	}
}

// 抽离离任或再任的真正操作
function doDutyOutOrBackReal(mode,memberId,onDuty,info,memberHasObject){
	$.ajax({
		async: false,
		url:"${path}/plugin/serviceTeam/serviceTeamManage/updateServiceTeamMemberOnDuty.action?",
        data:{
       			 "count":memberHasObject,
		   		"serviceTeamMemberVo.memberId":memberId,
		   		"serviceTeamMemberVo.onDuty":onDuty,
		   		"serviceTeamMemberVo.teamId":$("#_teamId_").val()
	        },
		success:function(responseData){
			if(responseData>0){
				$.messageBox({message:"成员"+info+"操作成功！"});
				$("#maintainMembers").trigger("reloadGrid");
			}else{
				$.messageBox({level:'error',message:"无法定位要"+info+"的成员"});
			}
		}
	});
}

//离任再任操作验证
function checkDutyOutOrBack(mode){
	var info = "未离职";
	var onDuty=0;
	if(mode == "back"){
		info = "已离职";
		onDuty = 1;
	}
	var selectedId = $("#maintainMembers").jqGrid("getGridParam", "selrow");
	if(selectedId==null || selectedId==""){
		$.messageBox({level:'warn',message:"请选择一条"+info+"成员进行操作！"});
 		return false;
	}
	var rowData = getSelectedRowData();
	if(rowData.onDuty==onDuty){
		$.messageBox({level:'warn',message:"请选择一条"+info+"成员进行操作！"});
 		return false;
	}else{
		return true;
	}
}

//获得被选中的行
function getSelectedRowData(){
	var selectedId = $("#maintainMembers").jqGrid("getGridParam", "selrow");
	return $("#maintainMembers").getRowData(selectedId);
}
//操作按钮
function addOperationFormatter(el,options,rowData){
	return "<a class='addMemberToTeam' href='javascript:;' data-rowid='"+rowData.baseId+"'>添加</a>";
}

//移除操作按钮
function removeOperationFormatter(el,options,rowData){
	if(rowData.onDuty==0){
		return "";
	}else{
		return "<a class='removeMemberToTeam' href='javascript:;' data-rowid='"+rowData.memberId+"'>移除</a>";
	}
}

//添加成员到团队
function addMemberToTeam(baseId){
	var position = $("#positionType"+baseId).val();
	if(position==''){
		$.messageBox({level:'error',message:"请为该成员选择职务！"});
		return;
	}
	$.ajax({
		async: false,
		url:"${path}/plugin/serviceTeam/serviceTeamManage/addServiceTeamMember.action",
	   	data:{
	   		"serviceTeamMember.baseId":baseId,
			"serviceTeamMember.teamId":"${(serviceTeamVo.id)!}",
			"serviceTeamMember.position.id":position,
			"serviceTeamMember.onDuty":1
        },
		success:function(responseData){
			$.messageBox({message:"成功添加成员到团队！"});
			$("#maintainMembers").trigger("reloadGrid");
        	$("#appendTeamMemberList").delRowData(baseId);
        	var reccount = $("#appendTeamMemberList").jqGrid('getGridParam', 'reccount');
        	if(reccount==0){
        		$("#appendTeamMemberList").trigger("reloadGrid");
        	}
		}
	});
}

//移除成员
function removeMemberToTeam(memberId){
	var rowData=$("#maintainMembers").getRowData(memberId);
	if(rowData.internalId>USER_ORG_LEVEL){
		$.messageBox({level:'warn',message:"选中的成员层级高于当前登录层级，无法对该成员进行删除操作！"});
		return;
	}
	$.ajax({
			async: false,
			url:"${path}/plugin/serviceTeam/serviceTeamManage/removeServiceTeamMember.action",
		   	data:{
		   		"serviceTeamMemberVo.memberId":memberId,
		   		"serviceTeamMemberVo.teamId":"${(serviceTeamVo.id)!}"
	        },
			success:function(responseData){
				if(responseData>0){
					$.messageBox({message:"成功从团队中移除成员！"});
					$("#maintainMembers").delRowData(memberId);
	        		$("#appendTeamMemberList").trigger("reloadGrid");
				}else if(responseData==-1){
					$.messageBox({level:'warn',message:"该成员存在服务对象，无法移除！！"});
				}else if(responseData==-2){
					$.messageBox({level:'warn',message:"该成员存在服务记录，无法移除！！"});
				}else{
					$.messageBox({level:'error',message:"无法定位要移除的成员"});
				}
			}
		});
}

//职务字典选项
function positionTypeFormatter(el,options,rowData){
	return "<select id='positionType"+rowData.baseId+"' name='position.id' class='basic-input'><@pop.OptionTag name='@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPositionPropertyTypes@TEAMPOSITION' showInternalId=true /></select>";
}

//是否在职
function onDutyFormatter(el,options,rowData){
	if(rowData.onDuty==0){
		return "否";
	}else{
		return "是";
	}
}
	//导出Excel
	$("#export").click(function(event){
		if($("#maintainMembers").getGridParam("records")>0){
			var postData = $("#maintainMembers").getPostData();
			$("#TeamMemberMaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出团队现有成员信息",
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'maintainMembers',
					downloadUrl:'${path}/plugin/serviceTeam/serviceTeamMember/downloadServiceTeamMember.action'
				},
				buttons: {
		   			"导出" : function(event){
						$("#exceldownload").submit();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({level : 'warn',message:"列表中没有数据，不能导出！"});
			return;
		}
	});

</script>