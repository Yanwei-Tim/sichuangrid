TQ.groupFamilyList = function (dfop){
	function viewPopulationInfoList(orgId,idCardNo) {
		$.ajax({
			url:PATH+"/commonPopulation/commonPopulationManage/getActualPopulationByOrgIdAndIdCardNo.action",
			data:{
				"orgId":orgId,
				"idCardNo":idCardNo
	        },
	        success:function(data){
	        	viewFamilyPopulationInfoList(data.id,data.actualPopulationType);
			}
		})

	}
	function viewFamilyPopulationInfoList(pid,type) {
		if(type == dfop.population_type_household_staff){
			actionUrl = PATH+'/baseinfo/householdStaff/dispath.action?mode=view&population.id='+pid;
		}else if(type == dfop.population_type_floating_population){
			actionUrl = PATH+'/baseinfo/floatingPopulationManage/dispath.action?mode=view&population.id='+pid;
		}else if(type == dfop.population_type_unsettled_population){
			actionUrl = PATH+'/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=view&unsettledPopulation.id='+pid;
		}else if(type == dfop.population_type_oversea_staff) {
			actionUrl = PATH+'/baseinfo/overseaPersonnelManage/dispatch.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+pid;
		}else return;
		$("#groupFamilyPopulationDialog").createDialog({
			width: 800,
			height: 600,
			title:'查看家庭成员信息',
			modal : true,
			url:actionUrl,
			buttons: {
				"打印" : function(){
					printChoose();
		  	   	},
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
	}
	function operateFormatter(el, options, rowData){
		return (dfop.hasViewGroupFamilyInfo == 'true' ? "<a href='javascript:viewGroupFamily("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>"+
				   "&nbsp;|&nbsp;" : '' )+
			   (dfop.hasDeleteGroupFamilyInfo == 'true' ? "<a href='javascript:deleteGroupFamily("+rowData.id+")'><U><font color='#6633FF'>删除</font></U></a>" : '');
	}



	function patriarchFormatter(el, options, rowData){
		if(rowData.familyMaster == null){
			return "&nbsp;&nbsp;";
		}
		if(rowData.populationtype == dfop.baseinfotables_overseapersonnel_key){
			return '<a href="javascript:;"onclick="viewDialog(event,'+rowData.id+')" >'+rowData.familyMaster+'</a>';
		}else if(rowData.populationtype == dfop.baseinfotables_unsettedpopulation_key){
			return '<a href="javascript:;"onclick="viewUnsettledPopulationDialog(event,'+rowData.id+')" >'+rowData.familyMaster+'</a>';
		}else if(rowData.populationtype == dfop.baseinfotables_floatingpopulation_key){
			return '<a href="javascript:;"onclick="viewFloatingPopulationDialog(event,'+rowData.id+')" >'+rowData.familyMaster+'</a>';
		}else if(rowData.populationtype == dfop.baseinfotables_householdstaff_key){
			return '<a href="javascript:;"onclick="viewHouseholdStaffDialog(event,'+rowData.id+')" >'+rowData.familyMaster+'</a>';
		}
		return '<a href="javascript:viewPopulationInfoList('+rowData.organization.id+',\''+rowData.masterCardNo+'\')">'+rowData.familyMaster+'</a>';
	}
	$(document).ready(function(){
		/**
		 * 当默认加载时执行，当且仅当用户是高于省级，并且是第一次去加载组织机构树时
		 * */
		if(USER_ORG_LEVEL > dfop.cityOrgLevel && $("#currentOrgId").attr("orglevelinternalid")>dfop.cityOrgLevel){
			//此处是去请求查询成都市的组织机构信息
			$.ajax({
				url:PATH+"/sysadmin/orgManage/getOrgForFirstCity.action",
				data:{
					"orgId":USER_ORG_ID
		        },
		        success:function(data){
		        	/**重新赋值给组织机构树，和隐藏域currentOrgId【已此为id的那个公用的隐藏域】*/
		        	if(data.id){
		        		getNewOrgInfo(data);
		        	}else{
		        		$.messageBox({level:"error",message:data});
		        	}
				}
			})
		}
		function getNewOrgInfo(data){
			var selectInput=data;
			var orgLevelInternalId=data.orgLevel.internalId;
			var text=selectInput.orgName;
			var leafNum=selectInput.subCount;
			var value=selectInput.id;
			 $("#currentOrgId").attr({
				"orgLevelInternalId":orgLevelInternalId,
				text:text,
				leafNum:leafNum,
				value:value
			});
			$("#globalOrgBtnMod.globalOrgBtnMod").html(text);
		}
		jQuery("#groupFamilyList").jqGridFunction({
			datatype: "local",
		 	colNames:['id','encryptId','orgId','type','populationid','encryptPopulationId','操作','家庭编号','家长姓名','家长证件号','家长性别','家庭住址','家长联系手机','家庭成员数','数据更新时间','所属区域(网格)','家长固定电话'],
		   	colModel:[
		   	       {name:"id",index:"id",sortable:false,hidden:true,hidedlg:true,key:true},
		   	   	   {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
		   	       {name:"organization.id",index:"organization.id",sortable:false,hidden:true,hidedlg:true},
		   	       {name:"populationtype",index:"populationtype",sortable:false,hidden:true,hidedlg:true},
		   	       {name:"populationid",index:"populationid",sortable:false,hidden:true,hidedlg:true},
		   	   	   {name:"encryptPopulationId",index:"populationid",sortable:false,hidden:true,frozen:true},
		   	       {name:'operation',width:90,formatter:operateFormatter,align:"center",frozen:true,sortable:false},
			       {name:'familyAccount',index:'familyAccount',width:100,frozen:true,sortable:true},
			       {name:'familyMaster',index:'familyMaster',formatter:patriarchFormatter,width:100,frozen:true,sortable:true},
			       {name:"masterCardNo",index:'masterCardNo',width:160,align:"center",sortable:true},
			       {name:'gender',sortable:true,formatter:genderFormatter,align:"center",width:75},
			       {name:'currentAddress',width:200,sortable:true},
			       {name:'masterMobileNumber',index:'masterMobileNumber',align:"center",width:120,sortable:true,hidden:true},
			       {name:'membersCount',index:'membersCount',align:"center",width:120,sortable:true},
			       {name:'updateDate', sortable:true, width:140,align:"center"},
			       {name:'organization.orgName',width:150,sortable:false,hidden:true},
			       {name:'masterTelephone',index:'masterTelephone',width:120,align:"center",hidden:true,sortable:true}
		   	],
		   	multiselect:true,
		  	loadComplete: afterLoad,
		  	ondblClickRow: function (rowid){
		  		if(dfop.hasViewGroupFamilyInfo == 'true'){
		  			viewGroupFamily(rowid);
		  		}
		  	}
		});

		jQuery("#groupFamilyList").jqGrid('setFrozenColumns');

		if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
			//onOrgChanged(getCurrentOrgId());
	    }
		
		function afterLoad(){
			changeColor();
		}
		function removeLoad(){
			$(".click_load .click_btn").hide();
		}
		$(".click_load .click_btn").click(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
			$(this).hide(100);
		});
		$("#contentDiv").click( function () { 
			removeLoad();
		});
		
		function changeColor(){
			if(notDeleteIds==null||notDeleteIds.length==0){
				return;
			}
			for(var i=0;i<notDeleteIds.length;i++){
				var rowData=$("#groupFamilyList").getRowData(notDeleteIds[i]);
				$("#"+notDeleteIds[i]).css('background','red');
				$("#groupFamilyList").setSelection(notDeleteIds[i])
			}
			notDeleteIds.splice(0,notDeleteIds.length);
		}


	   	$("#view").click(function(event){
			
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
			viewGroupFamily(selectedId);
		});

		$("#fastSearchButton").click(function(){
		 	search(getCurrentOrgId());
		});
		
		$("#refreshSearchKey").click(function(event){
			$("#searchText").attr("value","请输入家长证件号或家长姓名或家庭编号");
		});

		$("#reload").click(function(){
		 	onOrgChanged(getCurrentOrgId());
		 	$("#searchText").attr("value","请输入家长证件号或家长姓名或家庭编号");
		});
		
		$("#delete").click(function(){
			var ids=$("#groupFamilyList").jqGrid("getGridParam", "selarrrow");
			var canDeleteIds=new Array();
			if(ids.length==0){
				$.messageBox({level:"warn",
					message:'没有选中任何家庭，可供删除！'});
				return ;
			}else{
				for(var i = 0;i<ids.length;i++){
					var row=$("#groupFamilyList").getRowData(ids[i]);
				   if(row.membersCount == 0||row.membersCount == "0" ){
					   canDeleteIds.push(row.id);
				   	}else{
				   		notDeleteIds.push(row.id);
				   	}
				}
				if(canDeleteIds.length==0 ){
					$.messageBox({level:"warn",
						message:'没有符合删除家庭信息的选项'});
					return ;
				}
				deleteGroupFamily(canDeleteIds);
			}
		});
		
		$("#reset").click(function(){
			var ids=$("#groupFamilyList").jqGrid("getGridParam", "selarrrow");
			if(ids.length==0){
				$.messageBox({level:"warn",
					message:'没有选中任何家庭，不能重置！'});
				return ;
			}
			if(ids.length>1){
				$.messageBox({level:"warn",
					message:'只能选中一条家庭信息进行重置！'});
				return ;
			}else{
				var groupFamily=$("#groupFamilyList").getRowData(ids[0]);
				$("#resetFamilyAccountDlg").createDialog({
					width:400,
					height:260,
					title:'重置家庭编号',
					url:PATH+'/baseinfo/familyInfo/dispatchByEncryptId.action?mode=reset&groupFamilyId='+groupFamily.encryptId,
					buttons:{
						"确定":function(){
							$("#resetFamilyAccount-form").submit();
						},
						"关闭":function(){
							$(this).dialog("close");
						}
					}
				});
			}
		}); 
		
		$("#change").click(function(){
			var ids=$("#groupFamilyList").jqGrid("getGridParam", "selarrrow");
			if(ids.length==0){
				$.messageBox({level:"warn",
					message:'没有选中任何家庭，不能更换家长！'});
				return ;
			}
			if(ids.length>1){
				$.messageBox({level:"warn",
					message:'只能选中一条家庭信息进行更换家长！'});
				return ;
			}else{
				var groupFamily=$("#groupFamilyList").getRowData(ids[0]);
				$("#changeFamilyMasterDlg").createDialog({
					width:400,
					height:260,
					title:'更换家长',
					url:PATH+'/baseinfo/familyInfo/dispatchByEncryptId.action?mode=change&groupFamilyId='+groupFamily.encryptId,
					buttons:{
						"确定":function(){
							$("#changeFamilyMaster-form").submit();
						},
						"关闭":function(){
							$(this).dialog("close");
						}
					}
				});
			}
		}); 
		

		$("#manage").click(function(){
			var ids=$("#groupFamilyList").jqGrid("getGridParam", "selarrrow");
			if(ids.length==0){
				$.messageBox({level:"warn",
					message:'没有选中任何家庭，不能维护家庭成员！'});
				return ;
			}
			if(ids.length>1){
				$.messageBox({level:"warn",
					message:'只能选中一条家庭信息进行维护家庭成员！'});
				return ;
			}else{
				var groupFamily=$("#groupFamilyList").getRowData(ids[0]);
				$("#manageFamilyMembersDlg").createDialog({
					width:800,
					height:500,
					title:'维护家庭成员',
					url:PATH+'/baseinfo/familyInfo/dispatchByEncryptId.action?mode=manage&groupFamilyId='+groupFamily.encryptId,
					buttons:{
						"确定":function(){
							$("#groupFamilyList").trigger("reloadGrid");
							$(this).dialog("close");
						},
						"关闭":function(){
							$(this).dialog("close");
						}
					}
				});
			}
		});
		
	});



	function search(orgId){
		var conditions = $("#searchText").val();
		if(conditions == '请输入家长证件号或家长姓名或家庭编号') return;
		var initParam ={
				 "orgId":orgId,
				 "searchGroupFamilyVo.fastSearchKeyWords":conditions
		     }
		$("#groupFamilyList").setGridParam({
			url:PATH+"/baseinfo/searchGroupFamily/searchGroupFamilyInfo.action",
			datatype: "json",
			page:1
		});
	    $("#groupFamilyList").setPostData(initParam);
		$("#groupFamilyList").trigger("reloadGrid");
	}



	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#groupFamilyList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}

	function onOrgChanged(orgId){

		var initParam = {
			"orgId": orgId
		}

		$("#groupFamilyList").setGridParam({
			url:PATH+'/baseinfo/familyInfo/pageGroupFamiliesByOrgId.action',
			datatype: "json",
			page:1
		});
		$("#groupFamilyList").setPostData(initParam);
		$("#groupFamilyList").trigger("reloadGrid");
	}


	function getInitParam(){
		var initParam ={
				"orgId":getCurrentOrgId()
		};
		if(!isEmpty($("#groupFamily_familyAccount").val())){
			$.extend(initParam,{"searchGroupFamilyVo.familyAccount":$("#groupFamily_familyAccount").val()});
		}
		if(!isEmpty($("#groupFamily_familyAddress").val())){
			$.extend(initParam,{"searchGroupFamilyVo.familyAddress":$("#groupFamily_familyAddress").val()});
		}
		if(!isEmpty($("#groupFamily_familyMaster").val())){
			$.extend(initParam,{ "searchGroupFamilyVo.familyMaster":$("#groupFamily_familyMaster").val()});
		}
		if(!isEmpty($("#groupFamily_masterCardNo").val())){
			$.extend(initParam,{"searchGroupFamilyVo.masterCardNo":$("#groupFamily_masterCardNo").val()});
		}
		if(!isEmpty($("#groupFamily_memberName").val())){
			$.extend(initParam,{"searchGroupFamilyVo.memberName":$("#groupFamily_memberName").val()});
		}
		if(!isEmpty($("#groupFamily_memberCardNo").val())){
			$.extend(initParam,{"searchGroupFamilyVo.memberCardNo":$("#groupFamily_memberCardNo").val()});
		}
		if(!isEmpty($("#groupFamily_membersCount").val())){
			$.extend(initParam,{"searchGroupFamilyVo.membersCount":$("#groupFamily_membersCount").val()});
		}
	   	return initParam;
	}

	function  isEmpty(str){
		return str =="" || str ==null;
	}

	$("#search").click(function(){
		$("#groupFamilyDialog").createDialog({
			width: 650,
			height: 320,
			title:'家庭信息查询-请输入查询条件',
			url:PATH+'/baseinfo/familyInfo/groupFamily/searchGroupFamily.jsp',
			buttons: {
				"查询" : function(event){
				 var initParam = getInitParam();
			     $("#groupFamilyList").setGridParam({
				     url:PATH+'/baseinfo/searchGroupFamily/vagueSearchGroupFamilyInfo.action',
				     datatype : 'json',
				     page:1
			      });
			     $("#groupFamilyList").setPostData(initParam);
			     $("#groupFamilyList").trigger("reloadGrid");
				 $(this).dialog("close");
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});
}
function viewGroupFamily(selectedId){
	if(selectedId==null){
 		return;
	}
	var groupFamily =  $("#groupFamilyList").getRowData(selectedId);
	$("#groupFamilyDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看'+title,
		modal : true,
		url:PATH+'/baseinfo/familyInfo/getGroupFamilyByFamilyEncryptId.action?groupFamily.id='+groupFamily.encryptId,
		buttons: {
			"打印" : function(){
				printByUrl(PATH+'/baseinfo/familyInfo/getGroupFamilyByFamilyId.action?groupFamily.id='+selectedId+'&mode=print');
		  	},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	}); 
}
function deleteGroupFamily(ids){
	var encryptIds = deleteOperatorByEncrypt("groupFamily",ids,"encryptId");
	$.ajax({
		url:PATH+'/baseinfo/familyInfo/judgeGroupFamilyMemberByEncryptId.action',
		type:"post",
		data:{
			"ids":encryptIds
		},
		dataType:'json',
		success : function(data){
			if(data>0){
				$.messageBox({
					message : "该家庭还有家庭成员，不能删除！",
					level : "warn"
				});
			}else{
				$.confirm({
					title:"确认删除",
					message:"是否确定删除?",
					width: 400,
					okFunc: function(){deleteGroupFamilyById(ids);}
				});
			}
		},
		error : function(){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
}
function deleteGroupFamilyById(ids){
	var encryptIds = deleteOperatorByEncrypt("groupFamily",ids,"encryptId");
	$.ajax({
		url:PATH+'/baseinfo/familyInfo/deleteGroupFamilyByIds.action',
		type:'post',
		dataType:'json',
		data:{
			"ids":encryptIds
		},
		success : function(data){
			if(data){
				if(notDeleteIds.length>0){
					$.messageBox({
						message : "除红色选项的家庭下含有成员不能删除，其它家庭删除成功！"
					});
				}else{
					$.messageBox({
						message : "家庭删除成功！"
					});
				}
				
				$("#groupFamilyList").trigger("reloadGrid");
			}
		},
		error : function(){
			$.messageBox({
				message : "加载失败，请刷新页面！",
				level : "error"
			});
		}
	});
}

function viewUnsettledPopulationDialog(event,rowid){
	if(rowid==null){
			return;
	}
	var groupFamily =  $("#groupFamilyList").getRowData(rowid);
	$("#groupFamilyPopulationDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'查看'+title+'信息',
		url:PATH+'/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action?mode=view&id='+groupFamily.encryptPopulationId,
		buttons: {
			"打印" : function(){
				printChoose(unsettledPopulation.id);
  	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}

function viewFloatingPopulationDialog(event,rowid){
	if(null == rowid){
 		return;
	}
	var groupFamily =  $("#groupFamilyList").getRowData(rowid);
	$("#groupFamilyPopulationDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看'+title+'信息',
		modal : true,
		url:PATH+'/baseinfo/floatingPopulationManage/dispathByEncrypt.action?mode=view&id='+groupFamily.encryptPopulationId,
		buttons: {
		   "打印" : function(){
				printChoose(floatingPopulation.id);
	  	   	},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}

function viewHouseholdStaffDialog(event,rowid){
	if(rowid==null){
 		return;
	}
	var groupFamily =  $("#groupFamilyList").getRowData(rowid);
	$("#groupFamilyPopulationDialog").createDialog({
		width: 800,
		height: 600,
		title:'查看'+title+'信息',
		modal : true,
		url:PATH+'/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&id='+groupFamily.encryptPopulationId,
		buttons: {
			"打印" : function(){
				printChoose(houseStaff.id);
	  	   	},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}