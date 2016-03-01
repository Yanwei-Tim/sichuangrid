TQ.memberForTownList = function (dfop){
	
	
	
	function loadMemberData(conditions) {
		var initParam = '';
		if(typeof(conditions) == 'undefined'||conditions==null || conditions=='请输入身份证号码'){
			initParam = {
					"member.partyOrgType":partyOrgType,
					"member.partyOrg":partyOrg
				}
		} else {
			initParam = {
					"member.partyOrgType":partyOrgType,
					"member.idCardNo":conditions,
					"member.partyOrg":partyOrg
				}
		}
		
		$("#memberList").setGridParam({
			url:dfop.path+"/partyBuildng/memberManage/findMembersForPageByCondition.action",
			datatype: "json",
			page:1
		});
		$("#memberList").setPostData(initParam);
		$("#memberList").trigger("reloadGrid");
	}

	function search(){
		var conditions = $("#searchTextMember").val();
		if(conditions == '请输入身份证号码' || conditions==''){
			return;
		} 
		loadMemberData(conditions);
	}

	function addressFormatter(el, options, rowData){
		if(rowData.province==null){
			return '';
		}
		if(rowData.city==null){
			return rowData.province;
		}
		if(rowData.district==null){
			return rowData.province+'-'+rowData.city;
		}
		return rowData.province+'-'+rowData.city+'-'+rowData.district;
	}

	$(function (){
		if(USER_ORG_ID!=getCurrentOrgId()) {
			$("#addMember").hide();
			$("#updateMember").hide();
			$("#deleteMember").hide();
			$("#importMember").hide();
		} else {
			$("#addMember").show();
			$("#updateMember").show();
			$("#deleteMember").show();
			$("#importMember").show();
		}
		if(partyOrgType==dfop.regionPartyOrg){
			autoHeight=220;
		}
		$("#memberList").jqGridFunction({
			datatype: "local",
			height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-$(".ui-tabs-nav").outerHeight()-autoHeight,
		   	colModel:[
		        {name:"id",index:"id",hidden:true,sortable:false},
		        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		        {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateMemberFormatter,width:80,align:"center"},
		        {name:"idCardNo",sortable:true,label:'身份证号',width:120},
		        {name:"name",sortable:true,label:'姓名',width:100,formatter:nameMemberFormatter},
		        {name:"gender",sortable:true,label:'性别',width:100,formatter:genderFormatter},
		        {name:"address",sortable:false,label:'户籍地',width:140,formatter:addressFormatter},
		        {name:"nativePlaceAddress",sortable:false,label:'现居地址',width:160},
		        {name:"partyOrg",sortable:true,label:'所在党支部',width:165}
			],
			multiselect: true,
		    ondblClickRow: viewOperatorMember
		});
		
		$("#fastSearchButtonMember").click(function(){
			search();
		});
		$("#searchTextMember").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKeyMember").click(function(){
			$("#searchTextMember").attr("value","请输入身份证号码");
		});
		
		
		$("#addMember").click(function(){
			if(partyOrgType==dfop.regionPartyOrg&&partyOrg==''){
				$.messageBox({level:'warn',message:"请先填写区域党建信息！"});
				return;
			}
				
			$("#memberDialog").createTabDialog({
				title:"新增成员信息",
				width: dialogWidthMember,
				height: dialogHeightMember,
				postData:{
					imageType:"population"
				},
				saveClose:false,
				tabs:[
						{title:'成员信息',url:dfop.path+'/partyBuildng/memberManage/dispatch.action?mode=add&member.partyOrgType='+partyOrgType+'&member.partyOrg='+encodeURI(encodeURI(partyOrg))}
					]
			});
		});
		
		$("#updateMember").click(function(){
			var selectedIds = $("#memberList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				return;
			}
			var selectedId = getMemberSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				 return;
			}
			updateOperatorMember(selectedId);
		});
		
		$("#deleteMember").click(function(){
			var allValue = getMemberSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			deleteOperatorMember(allValue);
		});
		
		$("#viewMember").click(function(){
			if($("#viewMember").attr("disabled")){
				return ;
			}
			var selectedIds = $("#memberList").jqGrid("getGridParam", "selarrrow");
			var selectedId = getMemberSelectedIdLast();
			if(selectedId==null || selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			viewOperatorMember(selectedId);
		});
		
		loadMemberData();
		
		$("#importMember").click(function(event){
			$("#memberDialog").createDialog({
				width: 400,
				height: 230,
				title:"数据导入",
				url:dfop.path+"/common/import.jsp?isNew=1&dataType=member&dialog=memberDialog&startRow=5&templates=member&listName=memberList&module="+getModule(),
				buttons:{
					"导入" : function(event){
						$("#mForm").submit();
					},
				   	"关闭" : function(){
				    	$(this).dialog("close");
				   	}
				},
				shouldEmptyHtml:false
			});
		});
		
		$("#reloadMember").click(function(){
			$("#searchText").attr("value","请输入身份证号码");
			loadMemberData();
		});
	});

	function getModule() {
		if(partyOrgType==dfop.officePartyOrg){
			return dfop.officePartyOrg;
		}
		if(partyOrgType==dfop.townPartyOrg){
			return dfop.townPartyOrg;
		}
		if(partyOrgType==dfop.businessOrg){
			return dfop.businessOrg;
		}
		if(partyOrgType==dfop.collectiveOrg){
			return dfop.collectiveOrg;
		}
		if(partyOrgType==dfop.twoNewParty){
			return dfop.twoNewParty;
		}
		if(partyOrgType==dfop.twoNewPartyOrg){
			return dfop.twoNewPartyOrg;
		}
		if(partyOrgType==dfop.systemPartyOrg){
			return dfop.systemPartyOrg;
		}
		if(partyOrgType==dfop.regionPartyOrg){
			return dfop.regionPartyOrg;
		}
		if(partyOrgType==dfop.doubleReg){
			return dfop.doubleReg;
		}
	}

	

	function getMemberSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#memberList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}

	function getMemberSelectedIds(){
		var selectedIds = $("#memberList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
}



function nameMemberFormatter(el, options, rowData){
	return "<a href='javascript:viewOperatorMember("+rowData.id+")'><U><font color='#6633FF'>"+rowData.name+"</font></U></a>";
}

function operateMemberFormatter(el,options,rowData){
	return "<a href='javascript:;' onclick='updateOperatorMember("+rowData.id+")'><span>修改</span></a> | <a href='javascript:;' onclick='deleteOperatorMember("+rowData.id+",event)'><span>删除</span></a>";
}

function updateOperatorMember(selectedId){
	var rowData=  $("#memberList").getRowData(selectedId);
	$("#memberDialog").createTabDialog({
		title:"修改成员信息",
		width: dialogWidthMember,
		height: dialogHeightMember,
		postData:{
			imageType:"population"
		},
		saveClose:false,
		tabs:[
				{title:'成员信息',url:'/partyBuildng/memberManage/dispatch.action?mode=edit&member.partyOrgType='+partyOrgType+'&member.id='+rowData.encryptId}
			]
	});
}
function deleteOperatorMember(allValue,event){
	var selectedIds=deleteOperatorByEncrypt('member',allValue,'encryptId');
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:'/partyBuildng/memberManage/deleteMemberByIds.action',
				type:'post',
				data:{
					"ids":selectedIds,
					"member.partyOrgType":partyOrgType
					},		
				success:function(data){
					if(data!=null) {
						$.messageBox({message:"已经成功删除该成员信息!"});
						$("#memberList").trigger("reloadGrid");
					} else {
						$.messageBox({message:data,level:"error"});
					}
				}
			});
		}
	});
}

function viewOperatorMember(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#memberList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#memberDialog").createTabDialog({
		title:"查看成员信息",
		width: dialogWidthMember,
		height: dialogHeightMember,
		postData:{
			imageType:"population"
		},
		tabs:[
				{title:'成员信息',url:'/partyBuildng/memberManage/dispatch.action?mode=view&member.partyOrgType='+partyOrgType+'&member.id='+id}
			]
	});
}