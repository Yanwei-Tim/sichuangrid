TQ.serviceTeamMemberList = function (){
	$(document).ready(function(){
	    
		//是否进入了网格
		if(isGrid()){
			$("#serviceTeamMemberVo_orgScope").hide();
		}
		//服务成员列表
		$("#serviceTeamMemberList").jqGridFunction({
			mtype:'post',
			datatype: "local",
			colNames:['id','encryptId','orgId','操作','姓名','性别','联系手机','职位','联系电话','出生年份','所属区域(网格)'],
			colModel:[
				{name:"baseId",index:"baseId",key:true,sortable:true,hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				{name:"organization.id",index:"organization.id",sortable:false,hidden:true},
				{name:"operation",index:"id.id",formatter:operateFormatter_ServiceTeam_Member,sortable:false,width:80,align:"center"},
				{name:'name',index:"name",width:100,formatter:nameFont_ServiceTeamMember,frozen:true,sortable:true},
				{name:'gender',index:'gender',width:80,formatter:genderFormatter,sortable:true,align:"center"},
				{name:'mobile',index:'mobile',sortable:true,width:110,align:"center"},
				{name:'job',index:'job',sortable:true,width:150},
				{name:'homePhone',index:'homePhone',sortable:true,width:110},
				{name:"birthday",index:"birthday",sortable:true,width:80,align:"center"},
				{name:"org.orgName",index:"org.orgName",sortable:false,width:350}
			],
			multiselect:true,
			showColModelButton:false,
			sortname:'baseId',
			ondblClickRow:viewServiceMember
		});
		$("#serviceTeamMemberList").jqGrid('setFrozenColumns');
		loadTeamMembers();
		
		$("#serviceTeamMemberVo_orgScope").change(function(){
			loadTeamMembers();
		});
		$("#refreshSearchKey").click(function(event){
			$("#serviceTeamMemberVo_condition").attr("value","请输入姓名");
		});
		$("#fastSearchButton").click(function(){
			loadTeamMembers();
		});
		
		//获取成员列表行数
		function countSelectedIds(){
			var selectedIds = $("#serviceTeamMemberList").jqGrid("getGridParam", "selarrrow");
			return null == selectedIds ? 0 :selectedIds.length;
		}
		//修改服务成员
		$("#update").click(function(){
			if(countSelectedIds() > 1) {
				$.messageBox({level:'warn',message:"请选择一条服务成员信息进行修改！"});
				return;
			}
			var selectedId = $("#serviceTeamMemberList").jqGrid("getGridParam", "selrow");
			if(null == selectedId){
				$.messageBox({level:'warn',message:"请选择需要修改的服务成员信息！"});
				return;
			}
			updateOperator(selectedId);
		});

		//删除按钮事件
		$("#delete").click(function(){
			if(countSelectedIds() != 1) {
				$.messageBox({level:'warn',message:"请选择一条服务成员信息进行删除！"});
				return;
			}
			var selectedId = $("#serviceTeamMemberList").jqGrid("getGridParam", "selrow");
			deleteOperator(selectedId);
		});
		//新增按钮事件
		$("#add").click(function(event){
			$("#_serviceTeamMemberDialog").createDialog({
				title:"新增服务成员",
				width: 680,
				height: 280,
				url:PATH+'/plugin/serviceTeam/serviceTeamMember/dispatch.action?mode=add&dailogName=_serviceTeamMemberDialog&serviceTeamMemberVo.org.id='+getCurrentOrgId(),
				buttons: {
					"保存并关闭" : function(event){
						$("#isSubmit").val("true");
						$("#serviceTeamMemberForm").submit();
					},
					"保存并继续" : function(event){
						$("#isSubmit").val("false");
			   			$("#serviceTeamMemberForm").submit();
					}
				}
			});
		});
		
		//高级搜索对话框
		$("#search").click(function(){
			$("#_serviceTeamMemberDialog").createDialog({
				title:"服务成员查询-请输入查询条件",
				width: 600,
				height: 240,
				url:PATH+'/plugin/serviceTeam/serviceTeamMember/dispatch.action?mode=search&serviceTeamMemberVo.org.id='+getCurrentOrgId(),
				buttons: {
					"查询" : function(event){
						loadData($("#serviceTeamMember_form").serializeArray());
						$(this).dialog("close");
					},
					"关闭" : function(){
			        	$(this).dialog("close");
					}
				}

			});
		});
		
		//刷新按钮事件
		$("#reload").click(function(){
			$("#serviceTeamMemberVo_orgScope").val($("#serviceTeamMemberVo_orgScope").find(":eq(0)").val());
			$("#serviceTeamMemberVo_condition").val('请输入姓名');
			loadTeamMembers();
		});
		//层级转移
		$("#changeOrg").click(function(){
			var selectedIds = getSelectedIds();
			if(selectedIds==null || selectedIds==""){
				$.messageBox({level:'warn',message:"请选择至少一条数据进行操作！"});
		 		return;
			}
			var allValue=deleteOperatorByEncrypt('serviceTeamMember',selectedIds,'encryptId');
			$("#_serviceTeamMemberDialog").createDialog({
				width: 300,
				height: 300,
				title:'服务成员层级转移',
				url:PATH+'/plugin/serviceTeam/serviceTeamMember/dispatchByEncrypt.action?mode=changeOrg&selectedIds='+allValue,
				buttons: {
					"转移" : function(){
						$("#isSubmit").val("true");
						$("#changeOrgForm").submit();
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
					}
				}
			});
			
		});
		//合并重复服务成员数据
		$("#combine").click(function(){
			var selectedId = getSelectedIds();
			if(selectedId==null || selectedId==""){
				$.messageBox({level:'warn',message:"请选择一条数据进行操作！"});
		 		return;
			}
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能选择一个成员维护对象！"});
		 		return;
			}
			var rowData=  $("#serviceTeamMemberList").getRowData(selectedId);
			$("#_serviceTeamMemberDialog").createDialog({
				width: 850,
				height: 600,
				title:'合并服务成员信息',
				url:PATH+'/plugin/serviceTeam/serviceTeamMember/dispatchByEncrypt.action?mode=combine&serviceTeamMemberVo.id='+rowData.encryptId,
				buttons: {
					"合并" : function(){
						if(getTableNum("sameMembersList")==0){
							$.messageBox({level:'warn',message:"没有需要合并的数据！"});
		 					return;
						}
						$("#isSubmit").val("true");
						$("#combineForm").submit();
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
					}
				}
			});
		});
		//加载服务成员
		function loadTeamMembers() {
			var condition = $("#serviceTeamMemberVo_condition").val();
			var listParam = null;
			if('请输入姓名' != condition) {
				listParam = {
					'serviceTeamMemberVo.orgScope':$("#serviceTeamMemberVo_orgScope").val(),
					'serviceTeamMemberVo.org.Id':getCurrentOrgId(),
					'serviceTeamMemberVo.name':condition
				};
			}else listParam = {
				'serviceTeamMemberVo.orgScope':$("#serviceTeamMemberVo_orgScope").val(),
				'serviceTeamMemberVo.org.Id':getCurrentOrgId()
			}
			loadData(listParam);
		}
		//加载服务成员数据
		function loadData(listParam) {
			$("#serviceTeamMemberList").setGridParam({
				url:PATH+'/plugin/serviceTeam/serviceTeamMember/findServiceTeamMemberBases.action',
				datatype: "json",
				page:1
			});
			$("#serviceTeamMemberList").setPostData(listParam);
			$("#serviceTeamMemberList").trigger("reloadGrid");
		}
		//绑定服务对象
		$("#maintainServiceObjectsForServiceTeamMember").click(function(event){
			var selectedId =  $("#serviceTeamMemberList").jqGrid("getGridParam", "selarrrow");
			if(selectedId==null || selectedId==""){
				$.messageBox({level:'warn',message:"请选择一个成员维护对象！"});
		 		return;
			}
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能选择一个成员维护对象！"});
		 		return;
			}
			var rowData=  $("#serviceTeamMemberList").getRowData(selectedId);
			$.ajax({
					url:PATH+'/plugin/serviceTeam/serviceTeamMember/getServiceTeamMemberDetailsByBaseId.action?serviceTeamMemberBase.id='+rowData.encryptId,
					success:function(data){
						if(data[0]==null){
						    $.messageBox({level:'warn',message:"未在团队中的成员不能维护服务对象!"});
							return;
						}else{
						var member = $("#serviceTeamMemberList").getRowData(selectedId);
						var memberName = $(member.name).text();
							//在团队中，进行对象维护操作
							$("#_serviceTeamMemberDialog").createDialog({
								title:"维护服务对象",
								width: 800,
								height: 400,
								url:PATH+'/plugin/serviceTeam/serviceTeamMember/dispatch.action?mode=maintainServiceObjects&dailogName=_serviceTeamMemberDialog&serviceTeamMemberVo.memberIds='+data+'&serviceTeamMemberVo.name='+encodeURIComponent(memberName),
								buttons: {
									"关闭" : function(event){
										$(this).dialog("close");
									}
								}
							});
						}
					}
				});
			
		
		});
		//获取选中列的ID
		function getSelectedIds(){
			var selectedIds = $("#serviceTeamMemberList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
		}

	});
	
	//获得列表数据数量
	function getTableNum(tableid){
	  var num=$("#"+tableid+" tbody tr").size();
	  return num-1;
	}	

}