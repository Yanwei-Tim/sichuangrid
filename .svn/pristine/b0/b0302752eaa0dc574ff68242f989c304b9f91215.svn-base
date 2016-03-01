TQ.houseFamilyList = function (dfop){
	function operateFormatter(el, options, rowData){
		return (dfop.hasViewHouseFamilyInfo == 'true' ? "<a href='javascript:viewFun("+rowData.censusRegisterFamily.id+")'><U><font color='#6633FF'>查看</font></U></a>"+
				   "&nbsp;|&nbsp;" : '' )+
				   (dfop.hasDeleteHouseFamilyInfo == 'true' ? "<a href='javascript:deleteHouseFamilyFun(event,"+rowData.censusRegisterFamily.id+")'><U><font color='#6633FF'>删除</font></U></a>" : '');
	}
	function nameFontHouseFamily(el,options,rowData){
		if(null == rowData.name) {
			return "&nbsp;&nbsp;"
		}else if(rowData.death == true || rowData.death == "true" || rowData.death == 1){
			return (dfop.hasViewModuleName == 'true' ? "<a href='javascript:viewHeadDialog(event,"+rowData.censusRegisterFamily.id+")'><font color='red'>"+rowData.name+"</font></a>": "<font color='red'>"+rowData.name+"</font>");
		}else if(rowData.logOut==1||rowData.logOut=='1' || rowData.isEmphasis==1){
			return (dfop.hasViewModuleName == 'true' ? "<a href='javascript:viewHeadDialog(event,"+rowData.censusRegisterFamily.id+")'><font color='#778899'>"+rowData.name+"</font></a>": "<font color='#778899'>"+rowData.name+"</font>");
		}
		return (dfop.hasViewModuleName == 'true' ? "<a href='javascript:viewHeadDialog(event,"+rowData.censusRegisterFamily.id+")'><font color='#6633FF'>"+rowData.name+"</font></a>": "<font color='#6633FF'>"+rowData.name+"</font>");
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
		loadList();
		$("#refreshSearchKey").click(function(event){
			$("#searchText").attr("value","请输入户口号");
		});
		$("#fastSearch").click(function(){
			fastSearchFun();
		});
		$("#search").click(function(){
			searchFun();
		});
		$("#delete").click(function(){
			deleteFun();
		});
		$("#changeAccNo").click(function(){
			changeAccNoFun();
		});
		$("#changeHouseHold").click(function(){
			changeHouseHoldFun();
		});
		$("#maintain").click(function(){
			maintainFun();
		});
		$("#reload").click(function(){
		 	onOrgChanged(getCurrentOrgId());
		 	$("#searchText").attr("value","请输入户口号");
		});
		function loadList(){
			$("#houseFamilyList").jqGridFunction({
				datatype: "local",
				sortname:"cen.id",
				colNames:['id','encryptFamilyId','encryptHouseholdStaffId','操作','户口号','户主姓名','户主性别','户主身份证号码','户主住址','数据更新时间','所属区域(网格)','所属区域(网格)ID','户主工作单位或就读学校',
						'户主联系手机','户主固定电话','effectiveMemberNums','家庭成员数'],
				colModel:[
						{name:"censusRegisterFamily.id",index:"id",sortable:false,hidden:true,hidedlg:true,key:true},
						{name:"censusRegisterFamily.encryptId",sortable:false,hidden:true,frozen:true,hidedlg:true},
						{name:"encryptId",sortable:false,hidden:true,frozen:true},
						{name:'operation',width:80,formatter:operateFormatter,frozen:true,sortable:false,align:"center"},
						{name:'censusRegisterFamily.accountNumber',width:90,frozen:true,sortable:false,hidden:false},
						{name:"name",width:100,sortable:true,frozen:true,formatter:nameFontHouseFamily},
						{name:'gender',sortable:true,formatter:genderFormatter,width:75,align:"center"},
						{name:'idCardNo', width:150,sortable:true,align:"center",frozen:true},
						{name:'censusRegisterFamily.currentAddress',frozen:true,sortable:false},
						{name:'updateDate', sortable:false, width:140,align:"center"},
						{name:'organization.orgName',index:'organization.orgName',sortable:false,hidden:true},
						{name:'organization.id',index:'organization.id',sortable:false,hidden:true},
						{name:'workUnit',index:'hou.workUnit',sortable:true,hidden:true},
						{name:'mobileNumber',index:'hou.mobileNumber',width:120,sortable:true,align:"center",hidden:true},
						{name:'telephone',index:'hou.telephone',width:100,sortable:true,align:"center",hidden:true},
						{name:'effectiveMemberNums',index:'effectiveMemberNums',width:100,sortable:false,align:"center",hidden:true,hidedlg:true},
						{name:'memberNums',index:'memberNums',width:100,sortable:false,align:"center",hidden:false}
				],
				multiselect:true,
				loadComplete: changeColor,
				ondblClickRow:function (rowid){
					if(dfop.hasViewHouseFamilyInfo=='true'){
						viewFun(rowid);
					}
				}
			});
			$("#houseFamilyList").jqGrid('setFrozenColumns');
			if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
				//onOrgChanged(getCurrentOrgId());
			}
		}
		//快速搜索
		function fastSearchFun(){
			orgId = getCurrentOrgId();
			var conditions = $("#searchText").val();
			if(conditions == '请输入户口号') return;
			var	initParam = {
					 "orgId":orgId,
					 "searchFamilyHouseVo.fastSearchKeyWords":conditions
				}
			$("#houseFamilyList").setGridParam({
				url:PATH+"/baseinfo/searchHouseFamily/searchSomeHouseFamilyInfo.action",
				datatype: "json",
				page:1
			});
		    $("#houseFamilyList").setPostData(initParam);
			$("#houseFamilyList").trigger("reloadGrid");
		}
		function searchFun(){
			$("#houseFamilyDialog").createDialog({
				width: 650,
				height: 320,
				title:'户籍家庭查询-请输入查询条件',
				url:PATH+'/baseinfo/searchHouseFamily/narrowlySearchHouseFamilyInfo.action',
				buttons: {
					"查询" : function(event){
					 var initParam = getInitParam();
				     $("#houseFamilyList").setGridParam({
					     url:PATH+'/baseinfo/searchHouseFamily/searchSomeHouseFamilyInfo.action',
					     datatype : 'json',
					     page:1
				      });
				     $("#houseFamilyList").setPostData(initParam);
				     $("#houseFamilyList").trigger("reloadGrid");
					 $(this).dialog("close");
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}
		function deleteFun(){
			var rowIds = $("#houseFamilyList").jqGrid("getGridParam", "selarrrow");
			if(rowIds.length == 0){
				$.messageBox({level:'warn',message:'没有选中任何记录！'});
				return ;
			}
			$.confirm({
				title:"确认删除",
				message:'户籍家庭删除后将无法恢复,确认删除吗?',
				width: 400,
				okFunc: function(){
					deleteHouseFamily(rowIds);
				}
			});
		}
		function changeAccNoFun(){
			var rowIds=$("#houseFamilyList").jqGrid("getGridParam", "selarrrow");
			if(!isGrid()){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行操作！"});
				return;
			}
			if(rowIds.length == 0){
				$.messageBox({level:'warn',message:'没有选中任何记录！'});
				return ;
			}
			if(rowIds.length > 1){
				$.messageBox({level:'warn',message:'只能选中一条记录！'});
				return ;
			}
			var rowData = $("#houseFamilyList").getRowData(rowIds[0]);
			var hid = rowData['censusRegisterFamily.encryptId'];
			$("#houseFamilyDialog").createDialog({
				width:400,
				height:300,
				title:'重置户口号',
				url:PATH+"/baseinfo/houseFamily/dispatchOperateByEncrpt.action?mode=edit&dialogName=houseFamilyDialog&houseFamily.id="+hid+"&orgId="+getCurrentOrgId(),
				buttons : {
					"保存" : function(){
						$("#changeAccNoForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}
		function changeHouseHoldFun(){
			var rowIds=$("#houseFamilyList").jqGrid("getGridParam", "selarrrow");
			if(rowIds.length == 0){
				$.messageBox({level:'warn',message:'没有选中任何记录！'});
				return ;
			}
			if(rowIds.length > 1){
				$.messageBox({level:'warn',message:'只能选中一条记录！'});
				return ;
			}
			var rowData = $("#houseFamilyList").getRowData(rowIds[0]);
			var hid = rowData['censusRegisterFamily.encryptId'];
			//rowDataOrgId 用于户籍分表
			var rowDataOrgId = rowData['organization.id'];
			$("#houseFamilyDialog").createDialog({
				width:500,
				height:300,
				title:'更换户主',
				url:PATH+"/baseinfo/houseFamily/dispatchOperateByEncrpt.action?mode=change&dialogName=houseFamilyDialog&houseFamily.id="+hid+"&orgId="+getCurrentOrgId()+"&houseFamily.organization.id="+rowDataOrgId,
				buttons : {
					"保存" : function(){
						$("#changeHouseHoldForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}
		function maintainFun(){
			var rowIds=$("#houseFamilyList").jqGrid("getGridParam", "selarrrow");
			if(rowIds.length == 0){
				$.messageBox({level:'warn',message:'没有选中任何记录！'});
				return ;
			}
			if(rowIds.length > 1){
				$.messageBox({level:'warn',message:'只能选中一条记录！'});
				return ;
			}
			var rowData = $("#houseFamilyList").getRowData(rowIds[0]);
			var hid = rowData['censusRegisterFamily.encryptId'];
			$("#houseFamilyDialog").createDialog({
				width:800,
				height:600,
				title:'维护户籍家庭成员',
				url:PATH+"/baseinfo/houseFamily/dispatchOperateByEncrpt.action?mode=maintain&dialogName=houseFamilyDialog&houseFamily.id="+hid+"&orgId="+getCurrentOrgId(),
				buttons : {
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}
		function deleteHouseFamily(rowIds){
			var rowData;
			for(var i=0;i<rowIds.length;i++){
				rowData=$("#houseFamilyList").getRowData(rowIds[i]);
				if(rowData.effectiveMemberNums <= 0  || (rowData.memberNums==1 && rowData.idCardNo!=null && rowData.idCardNo!="" && typeof (rowData.idCardNo)!="undefined")){
					run.push(rowIds[i]);
					//$.messageBox({ message:"有成员的户籍家庭不能删除！",level: "warn"});
				}else{
					notRun.push(rowIds[i]);
				}
			}
			var encryptIds = deleteOperatorByEncrypt("houseFamily",run,"censusRegisterFamily.encryptId");
			$.ajax({
				url:PATH+'/baseinfo/houseFamily/deleteByIds.action',
				type:"post",
				data:{
					"ids":encryptIds
				},
				success:function(data){
					if(data){
						if(notRun.length>0){
							$.messageBox({message : "除红色选项的家庭下含有成员不能删除，其它家庭删除成功！"});
						}else{
							$.messageBox({message : "家庭删除成功！"});
						}
						//删除以后给run置空
						run=[];
						$("#houseFamilyList").trigger("reloadGrid");
					}else{
						$.messageBox({ message:"删除户籍家庭错误！",level: "error"});
					}
		        }
			});
		}
		function getInitParam(){
			var initParam ={
				"orgId":getCurrentOrgId()
		  	};
		  	if(!isEmpty($("#searchFamilyHouseVo\\.familyHouseAccountNumber").val())){
			  	$.extend(initParam,{"searchFamilyHouseVo.familyHouseAccountNumber":$("#searchFamilyHouseVo\\.familyHouseAccountNumber").val()});
		  	}
			if(!isEmpty($("#searchFamilyHouseVo\\.familyHouseIdCardNo").val())){
				$.extend(initParam,{"searchFamilyHouseVo.familyHouseIdCardNo":$("#searchFamilyHouseVo\\.familyHouseIdCardNo").val()});
			}
			if(!isEmpty($("#searchFamilyHouseVo\\.headOfFamilyHouseName").val())){
				$.extend(initParam,{ "searchFamilyHouseVo.headOfFamilyHouseName":$("#searchFamilyHouseVo\\.headOfFamilyHouseName").val()});
			}
			if(!isEmpty($("#searchFamilyHouseVo\\.familyHouseAddress").val())){
				$.extend(initParam,{"searchFamilyHouseVo.familyHouseAddress":$("#searchFamilyHouseVo\\.familyHouseAddress").val()});
			}
			if(!isEmpty($("#searchFamilyHouseVo\\.familyHouseMemberName").val())){
				$.extend(initParam,{  "searchFamilyHouseVo.familyHouseMemberName":$("#searchFamilyHouseVo\\.familyHouseMemberName").val()});
			}
			if(!isEmpty($("#searchFamilyHouseVo\\.familyHouseMemberIdCardNo").val())){
				$.extend(initParam,{"searchFamilyHouseVo.familyHouseMemberIdCardNo":$("#searchFamilyHouseVo\\.familyHouseMemberIdCardNo").val()});
			}
			if(!isEmpty($("#searchFamilyHouseVo\\.memberNum").val())){
				$.extend(initParam,{"searchFamilyHouseVo.memberNum":$("#searchFamilyHouseVo\\.memberNum").val()});
			}
		    return initParam;
		}
		function isEmpty(str){
			return str =="" || str ==null;
		}
	});


	function changeColor(){
		if(notRun==null||notRun.length==0){
			return;
		}
		for(var i=0;i<notRun.length;i++){
			var rowData=$("#houseFamilyList").getRowData(notRun[i]);
			$("#"+notRun[i]).css('background','red');
			$("#houseFamilyList").setSelection(notRun[i])
		}
		notRun.splice(0,notRun.length);
	}
}
function deleteHouseFamilyFun(event,rowId){
	var rowData=$("#houseFamilyList").getRowData(rowId);
	if(!(rowData.effectiveMemberNums <= 0 || 
			(rowData.memberNums==1 && rowData.idCardNo!=null && rowData.idCardNo!="" 
					&& typeof (rowData.idCardNo)!="undefined"))){
		$.messageBox({ message:"有成员的户籍家庭不能删除！",level: "warn"});
		return;
	}
	var str="确定要删除吗?一经删除将无法恢复";
	var encryptIds = deleteOperatorByEncrypt("houseFamily",rowId,"censusRegisterFamily.encryptId");
	$.confirm({
		title:"确认删除",
		message:str,
		okFunc: function() {
			$.ajax({
				url:PATH+'/baseinfo/houseFamily/deleteByIds.action',
				type:"post",
				data:{
					"ids":encryptIds
				},
								
				success:function(data){
					if(data){
						$.messageBox({ message:"成功删除户籍家庭!"});
						onOrgChanged(getCurrentOrgId());
						return;
					}else{
						$.messageBox({ message:"删除户籍家庭错误！",level: "error"});
					}
		        }
			});
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

function viewHeadDialog(event,rowid){
	if(null == rowid){
 		return;
	}
	var rowData = $("#houseFamilyList").getRowData(rowid);
	var hpid = rowData.encryptId;
	$("#houseHoldStaffViewDialog").createDialog({
		width: 800,
		height: 600,
		title:'查看户籍人口详细信息',
		modal : true,
		url:PATH+'/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&population.id='+hpid,
		buttons: {
			"打印" : function(){
				printChoose(rowid);
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