<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<input type="hidden" name="module" id="module" value="${(module)! }" /> 
<input type="hidden" name="id" id="objectId" value="${(id)! }" /> 
<input name="serviceTeamMemberVo.objectName" type="hidden" id="objectName" value="${(name)!}"/>
<input name="serviceTeamMemberVo.object_Type" type="hidden" id="MemberObject_Type" value=""/>
<input name="dailogName" type="hidden" id="dailogName" value="${(dailogName)!}"/>
<div class="content">
	<div class="ui-corner-all contentNav" id="nav">
			<select id="onDutyId" class="basic-input" name="serviceMemberVo.onDuty">
				<option value="">全部</option>
				<option value="1" selected>现任</option>
				<option value="0">曾任</option>
			</select>
		<span class="lineBetween "></span>
		<@pop.JugePermissionTag ename="addServiceTeamMember">
		<a id="addServiceTeamMember" href="javascript:void(0)"><span>新增治安负责人</span></a>
		</@pop.JugePermissionTag>
		<a id="reloadTeamMemberList" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width:100%;" >
		<table id="serviceTeamMemberList"></table>
		<div id="serviceTeamMemberListPager"></div>
	</div>
	<div id="_serviceTeamMemberDialog"></div>
	<div id="_serviceTeamGuardersDialog"></div>
</div>
<div style="display: none;">
	<@pop.JugePermissionTag ename="serviceTeamMemberManagement">
		<span id="title"><@s.property value="#request.name" escape=false/></span>
	</@pop.JugePermissionTag>
</div>
<script type="text/javascript">

var dialogWidth=820;
var dialogHeight=610;

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
	      {name:'operation',label:'操作',formatter:teamMemberOperationFormatter,sortable:false, width:100,align:"center"},     
	      {name:"memberName",index:'memberName',label:'姓名',sortable:false,width:80},
	      {name:"gender",label:'性别',sortable:false,formatter:genderFormatter,width:80,align:"center"},
	      {name:"position",label:'职务(身份)',sortable:false,formatter:positionOrRelationFormatter,width:100},
		  {name:'teamName',label:'所在团队',sortable:false,width:150},
	      {name:'mobile',label:'联系手机',sortable:false, width:110,align:"center"},
	      {name:'onDuty',label:'是否在职',formatter:onDutyFormatter,sortable:false, width:80,hidden:true},
	      {name:'teamMember',label:'是否为团队成员',sortable:false, width:80,hidden:true}
	      
		],
		multiselect:true,
		showColModelButton:false,
		height:360,
		sortname:'id'
	});
	$("#serviceTeamMemberList").jqGrid('setFrozenColumns');
	loadTeamMembers();
	
	$("#onDutyId").change(function(){
		loadTeamMembers();
	});
	
	//新增(团队成员)按钮事件
	$("#addServiceTeamMember").click(function(event){
		$("#_serviceTeamMemberDialog").createDialog({
			title:"新增治安负责人",
			width: 700,
			height: 480,
			url:'${path}/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocation.action?dailogName=_serviceTeamMemberDialog&mode=view&serviceTeamMemberVo.org.id='+getCurrentOrgId(),
			buttons: {			
				"关闭" : function(){
		        	$(this).dialog("close");
		        	$("#${dailogName}").trigger("reloadGrid");
				}
			},
			close:function(){
	        	$("#${dailogName}").trigger("reloadGrid");
	   		}
		});
	});
	//为服务对象新增(监护人)按钮事件
	$("#addGuardersForObject").click(function(event){
		$("#_serviceTeamGuardersDialog").createDialog({
			title:"新增监护人员",
			width: 600,
			height: 280,
			url:'${path}/plugin/serviceTeam/router/routerManage/maintainServiceMemberForPopulation.action?mode=add&dailogName=_serviceTeamGuardersDialog&serviceTeamMemberVo.org.id='+getCurrentOrgId(),
			buttons: {			
				"保存并关闭" : function(event){
					$("#isSubmit").val("true");
					$("#serviceTeamGuarderForm").submit();
					$("#serviceTeamMemberList").trigger("reloadGrid");
				},
				"保存并继续" : function(event){
					$("#isSubmit").val("false");
		   			$("#serviceTeamGuarderForm").submit();
		   			$("#serviceTeamMemberList").trigger("reloadGrid");
				}
			},
			close:function(){
	        	$("#serviceTeamMemberList").trigger("reloadGrid");
	   		}
		});
	});
	//刷新按钮事件
	$("#reloadTeamMemberList").click(function(){ 
		$("#onDutyId").val($("#onDutyId").find(":eq(1)").val());
		loadTeamMembers();
	});
	
	//加载服务成员
	function loadTeamMembers() {
	var personData = $("#"+$('#module').val()+"List").getRowData($("#objectId").val());	
		var listParam = null;
			listParam = {
				'serviceMemberVo.objectType':$("#_locationType_").val(),
				'serviceMemberVo.objectId':personData.id,
				'serviceMemberVo.onDuty':$("#onDutyId").val(),
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
	//添加删除成员的监听事件
	$("#serviceTeamMemberList").delegate(".delMemberforObject","click",function(event){
		var rowId = $(this).data("rowid");
		delMemberforObject(rowId);
		return false;
	});
	//添加删除监护人的监听事件
	$("#serviceTeamMemberList").delegate(".delGuarder","click",function(event){
		var rowId = $(this).data("rowid");
		delGuarder(rowId);
		return false;
	});
	//添加修改监护人的监听事件
	$("#serviceTeamMemberList").delegate(".updataGuarder","click",function(event){
		var rowId = $(this).data("rowid");
		updataGuarder(rowId);
		return false;
	});
	//添加服务成员卸任的监听事件
	$("#serviceTeamMemberList").delegate(".leaveMemberforObject","click",function(event){
		var rowId = $(this).data("rowid");
		leaveMemberforObject(rowId);
		return false;
	});
	//添加服务成员卸任的监听事件
	$("#serviceTeamMemberList").delegate(".backMemberforObject","click",function(event){
		var rowId = $(this).data("rowid");
		backMemberforObject(rowId);
		return false;
	});
	
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
	
	//删除操作按钮
	function teamMemberOperationFormatter(el,options,rowData){
		if(rowData.teamMember==1){
			if(rowData.onDuty!=0){
				return "<a class='delMemberforObject' title='删除' href='javascript:;' data-rowid='"+rowData.id+"'><span>删除</span></a>&nbsp;|&nbsp;<a class='leaveMemberforObject' title='卸任' href='javascript:;' data-rowid='"+rowData.id+"'><span>卸任</span></a>";
			}else{
				return "<a class='delMemberforObject' title='删除' href='javascript:;' data-rowid='"+rowData.id+"'><span>删除</span></a>&nbsp;|&nbsp;<a class='backMemberforObject' title='重新担任' href='javascript:;' data-rowid='"+rowData.id+"'><span>重新担任</span></a>";
			}
		}
		if(rowData.teamMember==0){
			return "<a class='delGuarder' title='删除' href='javascript:;' data-rowid='"+rowData.id+"'><span>删除</span></a>&nbsp;|&nbsp;<a class='updataGuarder' title='修改' href='javascript:;' data-rowid='"+rowData.memberId+"'>修改</a>";
		}
	}
	
	//服务成员重新担任
	function backMemberforObject(id){
	var data = $("#serviceTeamMemberList").getRowData(id);
		$.ajax({
				async: false,
				url:"${path}/plugin/serviceTeam/router/routerManage/leaveOrBackOnDuty.action",
			   	data:{
			   		"serviceMemberWithObject.id":id,
			   		"serviceMemberWithObject.onDuty":1,
			   		"serviceMemberWithObject.memberId":data.memberId
		        },
				success:function(responseData){
					if(responseData>0){
						$.messageBox({message:"治安负责人重新担任成功！"});
						//$("#serviceTeamMemberList").addRowData(id);
		        		$("#serviceTeamMemberList").trigger("reloadGrid");
					}else if(responseData==-1){
						$.messageBox({level:'warn',message:"该成员已离职，无法重新担任！"});
					}
				}
			});
	}
	//服务成员卸任
	function leaveMemberforObject(id){
	var data = $("#serviceTeamMemberList").getRowData(id);
		$.ajax({
				async: false,
				url:"${path}/plugin/serviceTeam/router/routerManage/leaveOrBackOnDuty.action",
			   	data:{
			   		"serviceMemberWithObject.id":id,
			   		"serviceMemberWithObject.onDuty":0,
			   		"serviceMemberWithObject.memberId":data.memberId
		        },
				success:function(responseData){
					if(responseData>0){
						$.messageBox({message:"治安负责人卸任成功！"});
						$("#serviceTeamMemberList").delRowData(id);
		        		$("#serviceTeamMemberList").trigger("reloadGrid");
					}else{
						$.messageBox({level:'error',message:"无法定位要卸任的服务成员！"});
					}
				}
			});
	}
	
	//修改监护人按钮事件
	function updataGuarder(memberId){
		$("#_serviceTeamGuardersDialog").createDialog({ 
			title:"修改监护人员",
			width: 600,
			height: 260,
			url:'${path}/plugin/serviceTeam/router/routerManage/maintainServiceMemberForPopulation.action?mode=edit&dailogName=_serviceTeamGuardersDialog&serviceTeamGuarders.id='+memberId,
			buttons: {			
				"保存并关闭" : function(event){
					$("#isSubmit").val("true");
					$("#serviceTeamGuarderForm").submit();
				}
			},
			close:function(){
	        	//$("#serviceTeamMemberForm").trigger("reloadGrid");
	   		}
		});
	}
	
	//删除服务成员
	function delMemberforObject(id){
		$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
				var data = $("#serviceTeamMemberList").getRowData(id);
		$.ajax({
				async: false,
				url:"${path}/plugin/serviceTeam/router/routerManage/deleteServiceMemberWithObject.action",
			   	data:{
			   		"selectedIds":id
		        },
				success:function(responseData){
					if(responseData>0){
						$.messageBox({message:"成功删除治安负责人！"});
						$("#serviceTeamMemberList").delRowData(id);
		        		$("#serviceTeamMemberList").trigger("reloadGrid");
					}else{
						$.messageBox({level:'error',message:"无法定位要删除的治安负责人"});
					}
				}
			});
		}
	});
	}
	//删除监护人员
	function delGuarder(id){
		$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				async: false,
				url:"${path}/plugin/serviceTeam/router/routerManage/deleteServiceGuardersWithObject.action",
			   	data:{
			   		"selectedIds":id
		        },
				success:function(responseData){
					if(responseData>0){
						$.messageBox({message:"成功从团队中删除监护人员！"});
						$("#serviceTeamMemberList").delRowData(id);
		        		$("#serviceTeamMemberList").trigger("reloadGrid");
					}else{
						$.messageBox({level:'error',message:"无法定位要删除的监护人员"});
					}
				}
			});
		}
	});
	}	
	
});
</script>