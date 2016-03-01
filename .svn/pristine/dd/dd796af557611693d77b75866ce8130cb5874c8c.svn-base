TQ.household_list =function (dfop){
	function householdRegisterFormatter(el, options, rowData){
		var str = "";
		if(rowData.province != null)
			str += rowData.province;
		if(rowData.city != null)
			str += rowData.city
		if(rowData.district != null)
			str += rowData.district;
		return str;
	}
	function outProvinceFormatter(el, options, rowData){
		var str = "";
		if(rowData.outProvince != null)
			str += rowData.outProvince;
		if(rowData.outCity != null)
			str += rowData.outCity
		if(rowData.outDistrict != null)
			str += rowData.outDistrict;
		return str;
	}
	function rendIsOutGone(outGone){
		if(true==outGone || "true" == outGone){
			return "是";
		}else{
			return "否";
		}
	}
	function rendIsRepeat(el, options, rowData){
		if(1==rowData.isRepeat){
				return "是";
			}else{
				return "否";
			}
		}

	function reasonsDateFormatter(el,options,rowData){
		if(rowData.reasonsDate != null){
			var date = new Date(rowData.reasonsDate);
			var year = date.getFullYear();
			var month = date.getMonth()+1;
			var day = date.getDate();
			return year+"-"+month+"-"+day;
		}else{
			return "";
		}
	}

	function removeLoad(){
		$(".click_load .click_btn").hide();
	}
	
	$(document).ready(function(){
			jQuery("#householdStaffList").jqGridFunction({
				datatype: "local",
			 	colNames:['id','encryptId','organizationId','操作','姓名','性别','身份证号码','所属区域(网格)','曾用名','联系手机','固定电话','出生日期','民族','政治面貌','文化程度','职业','工作单位或就读学校','婚姻状况',
			 	         '是否死亡','身高(cm)','血型','宗教信仰','电子邮箱','户籍地','户籍地详址','常住地址','临时居所','户口号','与户主关系','户口类别','是否外出','外出原因','外出时间','外出去向','外出详址','是否注销','注销原因','数据更新时间','数据来源'],
			   	colModel:[
					   {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
			   	       {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
			   	       {name:'organization.id',index:'organization.id',hidden:true,hidedlg:true,sortable:false,frozen:true},
			   	       {name:"operation",formatter:operateFormatter,frozen:true,width:90,sortable:false,align:"center"},
				       {name:"name",index:'name',sortable:false,width:100,frozen:true,formatter:nameFont},
				       {name:'gender',sortable:false,formatter:genderFormatter,align:'center',width:50},
				       {name:'idCardNo',index:'idCardNo',width:160,align:'center',sortable:false,frozen:true},
				       {name:'organization.orgName',index:'organization.orgName',width:150,hidden:true,sortable:false},
				       {name:'usedName',index:'usedName',width:70,hidden:true,sortable:false},
				       {name:'mobileNumber',index:'mobileNumber',width:100,hidden:true, align:'center',sortable:false},
				       {name:'telephone',index:'telephone',width:120,hidden:true, align:'center',sortable:false},
				       {name:'birthday',index:'birthday',width:80,hidden:true,align:'center',sortable:false},
				       {name:'nation',index:'nation',width:70,formatter:nationFormatter,hidden:true,sortable:false},
				       {name:'politicalBackground',index:'politicalBackground',formatter:politicalBackgroundFormatter,width:150,hidden:true,sortable:false},
				       {name:'schooling',index:'schooling',formatter:schoolingFormatter,width:75,hidden:true,sortable:false},
				       {name:'career',index:'career',width:80,sortable:false,align:'center',hidden:true,formatter:careerFormatter},
				       {name:'workUnit',index:'workUnit',width:220,sortable:false},
				       {name:'maritalState',index:'maritalState',formatter:maritalStateFormatter,width:75,align:'center',hidden:true,sortable:false},
				       {name:'death',sortable:false,hidedlg:true,hidden:true,width:100},
				       {name:'stature',index:'stature',width:80,hidden:true,align:'center',sortable:false},
				       {name:'bloodType',index:'bloodType',formatter:bloodTypeFormatter,width:70,hidden:true,sortable:false},
				       {name:'faith',index:'faith',formatter:faithFormatter,width:75,hidden:true,sortable:false},
				       {name:'email',index:'email',width:100,hidden:true,sortable:false},
				       {name:'province',index:'province',formatter:householdRegisterFormatter,width:150,hidden:true,sortable:false},
				       {name:'nativePlaceAddress',index:'nativePlaceAddress',width:150,hidden:true,sortable:false},
				       {name:'currentAddress',index:'currentAddress',width:220,sortable:false},
				       {name:'otherAddress',index:'otherAddress',width:150,hidden:true,sortable:false},
				       {name:'accountNumber',index:'accountNumber',width:90,hidden:true,sortable:false},
				       {name:'relationShipWithHead',index:'relationShipWithHead',width:150,formatter:relationShipWithHeadFormatter,sortable:false},
				       {name:'residenceType',index:'residenceType',formatter:residenceTypeFormatter,width:100,hidden:true,sortable:false},
				       {name:'outGone',sortable:false,hidden:true,width:75,align:'center',formatter:rendIsOutGone},
				       {name:'outReasons',index:'outReasons',width:80,hidden:true,sortable:false,formatter:outReasonsFormatter},
				       {name:'reasonsDate',index:'reasonsDate',width:80,align:'center',hidden:true,sortable:false},
				       {name:'outProvince',index:'outProvince',width:120,hidden:true,sortable:false,formatter:outProvinceFormatter},
				       {name:'goOutDetailedAddress',index:'goOutDetailedAddress',width:100,hidden:true,sortable:false},
				       {name:'logOut',sortable:false,hidden:true,hidedlg:true,width:80},
				       {name:'logoutDetail.logoutReason',sortable:false,hidden:true,hidedlg:true,width:80},
				       {name:'updateDate', sortable:false, align:'center', width:160},
				       {name:'sourcesState',sortable:false,hidden:true,formatter:sourceStateFormatter,width:80}
				       //,formatter:sourcesStateFormatter
			   	],
			   	multiselect:true,
			   	onSelectAll:function(aRowids,status){},
			   	altclass:true,
			   	loadComplete: afterLoad,
			   	ondblClickRow:function (rowid){
					if(dfop.hasViewHouseholdStaff=='true'){
						viewDialogForDbclick(rowid);
					}
				},
			    onSelectRow:selectRow
				});
			jQuery("#householdStaffList").jqGrid('setFrozenColumns');
			if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
				//onOrgChanged(getCurrentOrgId(),isGrid());
			}
	
			$("#contentDiv").click( function () { 
				removeLoad();
			});  
			
		function clickDisabled(name){
			var id="#"+name;
			var isDisabled=$(id).attr("disabled");
			if(isDisabled=="disabled"){
				return true;
			}
		}
		$("#search").click(function(){
			$("#householdStaffPopulationDialog").createDialog({
				width: 750,
				height: 420,
				title:'户籍人口查询-请输入查询条件',
				url:PATH+'/baseinfo/householdStaff/prepareSearchHouseholdStaff.action?orgId='+org,
				buttons: {
					"查询" : function(event){
					refreshPositiveInfoGrid();
					   $(this).dialog("close");
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});
	
		$("#isEmphasis").click(function(){
			var selectedId = getSelectedIdLast();
			if(selectedId == null){
				return;
			}
			$.ajax({
				url:PATH+"/baseinfo/positiveInfo/updatePositiveInfosById.action",
				data:{
					"positiveInfos.id":selectedId,
					"positiveInfos.isEmphasis":1
				},
				success:function(responseData){
					$("#householdStaffList").setRowData(responseData.id,responseData);
					$("#"+responseData.id+"").css('color','#778899');
					$.messageBox({message:"取消关注成功！"});
				}
			});
		});
		
		
		/*$("#moveToShardTable").click(function(){
			$.ajax({
				url:PATH+"/baseinfo/householdStaff/moveToShardTable.action",
				success:function(responseData){
					$.messageBox({message:"触发成功！"});
				}
			});
		});*/
	
	
		$("#add").click(function(event){
			if(!isGrid()){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
				return;
			}
			$("#householdStaffPopulationDialog").createTabDialog({
				width: 780,
				height: dialogHeight,	
				postData:{
					model :"add",
					imageType:"population",
					type:"householdStaff"
				},
				title:"新增户籍人口",
				tabs:dfop.tabs,
				close : function(){
					$("#householdStaffList").trigger("reloadGrid");
				}
			});
		});
		$("#testTime").click(function(){
			var initParam = {
				"orgId": getCurrentOrgId(),
				"householdStaffVo.logout":0,
				"householdStaffVo.isDeath":0 , 
				"rows":20,
				"page":1,
				"sidx":"id",
				"sord":"desc"
			}	
			$.ajax({
				async: false ,
				url:PATH+'/baseinfo/householdStaff/findHouseholdStaffByOrgIdDefaultListTest.action',
			   	data:initParam,
				success:function(data){
					alert("control层耗时："+data.controlTime+"，service层耗时："+data.serviceTime+"dao层耗时，"+data.daoTime);
				}
			});
		});
		
		/*
		$("#transfer").click(function(event){
			if (!isgridBol){
				return;
			}
			$("#householdStaffPopulationDialog").createDialog({
				width: 300,
				height: 300,
				title:"转移"+title+"信息",
				url:PATH+'/transferManage/transferDispatch.action?orgId='+org+'&positiveInfoId='+$("#householdStaffList").jqGrid("getGridParam", "selarrrow"),
				buttons: {
					"保存" : function(event){
						$("#maintainShiftForm").submit();
					},
					"关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});
		*/
		$("#transfer").click(function(e){
			var allValue = $("#householdStaffList").jqGrid("getGridParam", "selarrrow");
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行转移！"});
				 return;
			}
			for(var i=0;i<allValue.length;i++){
				var rowData_Popu=$("#householdStaffList").getRowData(allValue[i]);
				if(rowData_Popu.logOut==1||rowData_Popu.death=="true"){
					$.messageBox({level:'warn',message:"所选记录中存在已注销（或死亡）记录，无法转移！"});
					 return;
				}
			}
			var orgid=	getCurrentOrgId();
			if(orgid==""||orgid==null){
				$.messageBox({level:'warn',message:"没有获取到当前的组织机构id"});
				 return;
			}
			$.confirm({
				title:"转移"+dfop.moduleCName,
				message:"转移"+dfop.moduleCName+"时,目标网格存在相同数据,只会转移对应的业务人员。",
				okFunc: function() {
					moveOperator(e,allValue,orgid);
				}
			});
		});
		
		function moveOperator(event,allValue,orgid){
			var encryptIds=deleteOperatorByEncrypt("householdStaff",allValue,"encryptId");
			var allOrgId = getOrgIdsByIds("householdStaff",allValue,"organization.id");
			$("#moveDataDialog").createDialog({
				width: 400,
				height: 230,
				title:"数据转移",
				url:PATH+"/transferManage/transferDispatchByEncryptId.action?orgId="+orgid+"&Ids="+encryptIds+"&type=householdStaff&orgIds="+allOrgId,
				buttons: {
					"保存" : function(event){
						$("#maintainShiftForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
			var evt = event || window.event;
			if (window.event) { 
			evt.cancelBubble=true; 
			} else { 
			evt.stopPropagation(); 
			} 
		}
		$("#isLogOut").click(function(){
			if(clickDisabled("isLogOut")){
				return;
			}
			var allValue = getSelectedIds();
			var logOut=new Array();
			var temp=new Array();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条数据进行注销!"});
				 return;
			}
			for(var i=0;i<allValue.length;i++){
				var rowData=$("#householdStaffList").getRowData(allValue[i]);
				if(rowData.logOut==0 || rowData.logOut=='0'){
					logOut.push(allValue[i]);
				}else{
					temp.push(allValue[i]);
				}
			}
			allValue=logOut;
			if(allValue==null||allValue.length==0){
				$.messageBox({level:'warn',message:"没有可注销的数据"});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("householdStaff",allValue,"encryptId");
			$("#householdStaffDialog").createDialog({
				width:450,
				height:210,
				title:'注销提示',
				modal:true,
				url:PATH+'/baseinfo/commonPopulation/populationEmphasiseConditionDlg.jsp?populationIds='+encryptIds+'&isEmphasis=1&lowerCaseModuleName=householdStaff&actionUrl=householdStaff/updateEmphasiseById.action&type=actualPopulation&temp='+temp+"&orgId="+getCurrentOrgId(),
				buttons: {
				   "保存" : function(event){
					   $("#populationEmphasisForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});
	
		$("#cancelLogOut").click(function(){
			if(clickDisabled("cancelLogOut")){
				return;
			}
			var allValue = getSelectedIds();
			var cancelLogOut=new Array();
			var temp=new Array();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条数据进行取消注销!"});
				 return;
			}
			for(var i = 0;i<allValue.length;i++){
		   		var row=$("#householdStaffList").getRowData(allValue[i]);
		   		if((row.logOut==1||row.logOut=='1')&&(row.death!=true&&row.death!='true')){
		   			$.ajax({
		   				async: false ,
		   				url:PATH+"/baseinfo/householdStaff/checkCardNoIsRepeatByEncryptId.action",
		   			   	data:{
		   					"population.id":row.encryptId,
			   				"population.idCardNo":row.idCardNo,
			   				"organizationId":row["organization.id"]
		   		        },
		   				success:function(data){
		   					if(data){
		   						temp.push(row.id);
		   					}else{
		   						cancelLogOut.push(row.id);
		   					}
		   				}
		   			});
		   			if(temp.length!=0){
		   				if(row["logoutDetail.logoutReason"]=="已转为流动人口"){
				   			$.messageBox({level : 'warn',message:"已转为流动人口不可取消注销！"});
							return;
				   		}else{
				   			$.messageBox({level:'warn',message:"该人员在同一网格下的流动人口或者未落户人口中已存在，不能取消注销！"});
			   				return;
				   		}
				   	}
		   		}else{
		   			temp.push(allValue[i]);
			   	}
		   	}
		   	allValue=cancelLogOut;
		   	if(allValue==null||allValue.length==0){
				$.messageBox({level:'warn',message:"没有可取消注销的数据"});
				return;
			}
		   	var encryptIds=deleteOperatorByEncrypt("householdStaff",allValue,"encryptId");	
			$.confirm({
					title:"确认取消注销",
					message:"确定要取消注销吗?",
					okFunc: function(){
						$.ajax({
							url:PATH+"/baseinfo/householdStaff/updateEmphasiseByEncryptId.action?population.logoutDetail.logout=0&populationIds="+encryptIds,
							success:function(datas){
								if(null==temp||temp.length==0){
									$.messageBox({message:"已经成功取消注销该"+title+"信息!"});
								}else{
									$.messageBox({level:'warn',message:"除选中的红色数据外,已经成功取消注销该"+title+"信息!"});
								}
								notExecute=temp;
								$("#householdStaffList").trigger("reloadGrid");
								disableButtons();
								checkExport();
							}
					});
				}
			});
		});
	
		$("#cancelDeath").click(function(){
			if(clickDisabled("cancelDeath")){
				return;
			}
			var allValue = getSelectedIds();
			var cancelDeath=new Array();
			var temp=new Array();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条数据进行取消死亡!"});
				 return;
			}
			for(var i = 0;i<allValue.length;i++){
		   		var row=$("#householdStaffList").getRowData(allValue[i]);
		   		if(row.death==true||row.death=='true'){
		   			$.ajax({
		   				async: false ,
		   				url:PATH+"/baseinfo/householdStaff/checkCardNoIsRepeatByEncryptId.action",
		   			   	data:{
		   					"population.id":row.encryptId,
			   				"population.idCardNo":row.idCardNo,
			   				"organizationId":row["organization.id"]
		   		        },
		   				success:function(data){
		   					if(data){
		   						temp.push(row.id);
		   					}else{
		   						cancelDeath.push(row.id);
		   					}
		   				}
		   			});
		   			if(temp.length!=0){
		   				$.messageBox({level:'warn',message:"该人员在同一网格下的流动人口或者未落户人口中已存在，不能取消死亡！"});
		   				return;
		   			}
		   		}else{
		   			temp.push(allValue[i]);
			   	}
		   	}
		   	allValue=cancelDeath;
		   	if(allValue==null||allValue.length==0){
				$.messageBox({level:'warn',message:"没有可取消死亡的数据"});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("householdStaff",allValue,"encryptId");	
			$.confirm({
					title:"确认取消死亡",
					message:"确定要取消死亡吗?",
					okFunc: function(){
						$.ajax({
							url:PATH+"/baseinfo/householdStaff/updateDeathOfHouseholdStaffByEncryptId.action?population.death=false&householdStaffIds="+encryptIds,
							success:function(datas){
								if(null==temp||temp.length==0){
									$.messageBox({message:"已经成功取消死亡该"+title+"信息!"});
								}else{
									$.messageBox({level:'warn',message:"除选中的红色数据外,已经成功取消死亡该"+title+"信息!"});
								}
								notExecute=temp;
								$("#householdStaffList").trigger("reloadGrid");
								disableButtons();
								checkExport();
							}
					});
				}
			});
		});
	
		$("#delete").click(function(){
			var boo = false;
			var allValue = getSelectedIds();
			if(allValue.length ==0||allValue==""){
				 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除"});
				 return;
			}
			var str="确定要删除吗?一经删除将无法恢复";
			var strs = new Array();
			strs = (allValue+"").split(",");
			var encryptIds = deleteOperatorByEncrypt("householdStaff",strs,"encryptId");
				$.confirm({
						title:"确认删除",
						message:str,
						okFunc: function() {
								$.ajax({
									async:false,
									url:PATH+"/baseinfo/householdStaff/deleteHouseholdStaff.action",
									type: 'POST',
									data:{
										"householdStaffVo.idStr":encryptIds
									},
									success:function(data){
										if(data == true){
											//for(var i = 0; i < strs.length; i++){
											//	$("#householdStaffList").delRowData(strs[i]);
											//}
											$("#reload").click();
										    disableButtons();
										    checkExport();
										    $.messageBox({message:"已经成功删除该"+title+"信息!"});
										}else{
											 $.messageBox({
													message:data,
													level: "error"
												 });
										}
									}
							});
							$("#householdStaffList").trigger("reloadGrid");
				}
			});
		});
	
		$("#import").click(function(event){
			$("#householdStaffPopulationDialog").createDialog({
				width: 400,
				height: 230,
				title:'数据导入',
				url:PATH+'/common/import.jsp?isNew=1&dataType=householdStaffData&dialog=householdStaffPopulationDialog&startRow=6&templates='+dfop.templates+'&listName=householdStaffList',
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
	
		$("#export").click(function(event){
			if ($("#householdStaffList").getGridParam("records")>0){
				$("#householdStaffPopulationDialog").createDialog({
					width: 250,
					height: 200,
					title:'导出'+title+'信息',
					url:PATH+'/common/exportExcel.jsp',
					postData:{
						gridName:'householdStaffList',
						downloadUrl:PATH+'/baseinfo/householdStaff/downloadHouseholdStaff.action'
						},
					buttons: {
			   			"导出" : function(event){
			   				exceldownloadSubmitForm();
			        		$(this).dialog("close");
		   				},
			   			"关闭" : function(){
			        		$(this).dialog("close");
			   			}
					},
					shouldEmptyHtml:false
				});
			}else{
				$.messageBox({level:'warn',message:"列表中没有数据，不能导出！"});
			}
		});
	
	$("#view").click(function(event){
		if(clickDisabled("view")){
			return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewDialog(selectedId);
	});
	$("#updateIdCardNo").click(function(event){
		if(!isGrid()){
			$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行修改！"});
			return;
		}
		var selectedId =getSelectedIds();
		if(selectedId==undefined||selectedId.length==0){
			$.messageBox({level:"warn",message:"请先选择一条记录，再进行操作！"});
			return;
		}
		if(selectedId.length>1){
			$.messageBox({level:"warn",message:"只能选择一条记录进行操作！"});
			return;
		}
		var rowData =  $("#householdStaffList").getRowData(selectedId);
		var idCardNo = rowData.idCardNo;
		var	orgId=rowData["organization.id"];
		updateIdCardNo(selectedId,idCardNo,orgId);
	});
	
	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
		$("#searchText").attr("value","请输入姓名或身份证号码");
	});
	$(".click_load .click_btn").click(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
		$(this).hide(100);
	});
	$("#refreshSearchKey").click(function(event){
		$("#searchText").attr("value","请输入姓名或身份证号码");
	});

	$("#toFloatingPopulation").click(function(event){
		var selectedId =getSelectedIds();
		if(selectedId==undefined||selectedId.length==0){
			$.messageBox({level:"warn",message:"请先选择一条记录，再进行操作！"});
			return;
		}
		if(selectedId.length>10){
			$.messageBox({level:"warn",message:"最多只能选择10条信息进行操作！"});
			return;
		}
		if(selectedId.length>0){
			$("#toFloatingPopulationDialog").createDialog({
				width:740,
				height:500,
				title:'转为流动人口',
				url:PATH+'/baseinfo/householdPopulation/toFloatingPopulationDialog.jsp',
				buttons: {
			   		"确认转移" : function(event){
			   			toFloatingPopulations();
			   		},
	   				"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
			return;
		}
//		var rowData =  $("#householdStaffList").getRowData(selectedId);
//		var idCardNo = rowData.idCardNo;
//		if(rowData.death=="true"){
//			$.messageBox({message:"该人员已死亡不能进行操作!",level:"warn"});
//			return;
//		}
//		if(!isFloatingPopulation(idCardNo ,rowData["organization.id"])){
//			$.confirm({
//				title:"转为流动人口提示",
//				message:"是否把“户籍人口：<u>"+rowData.name+"，"+idCardNo+"</u> 转为流动人口？",
//				okFunc: function(){
//					$.dialogLoading("open");
//					$.ajax({
//						url:PATH+'/baseinfo/householdStaff/toFloatingPopulationByEncryptId.action?&population.id='+rowData.encryptId,
//						success:function(data){
//							$.dialogLoading("close");
//							if(data != null && data.id){
//								$.messageBox({ message:"转为流动人口成功!"});
//								$("#householdStaffList").trigger("reloadGrid");
//							}else{
//								$.messageBox({level:"error", message:"转流动人口失败,原因:"+data});
//								return;
//  						}
//					    },
//					    error: function(XMLHttpRequest, textStatus, errorThrown){
//					    	$.dialogLoading("close");
//					 	    alert("提交数据时发生错误");
//				 	   	}
//					});
//				}
//			});
//		}
	});
	function isFloatingPopulation(idCardNo ,orgId){
		var isFloatingPopulation;
		$.ajax({
			async: false ,
			url:PATH+"/baseinfo/floatingPopulationManage/findFloatingPopulationByIdCardNoAndOrgId.action",
			data:{
				"population.idCardNo":idCardNo,
				"organizationId":orgId
			},
			success:function(population){
				if(population!=null&&population.isDeath=='true'){
					isFloatingPopulation=true;
					$.messageBox({message:"该人员已死亡，不能转流动人口！",level:"warn"});
				}else{ 
					isFloatingPopulation=false;
				}
			}
		});
		return isFloatingPopulation;
	}
	
	});

	function deleteRole(){
		var selectedId = getSelectedIds();
		if(selectedId==null){
			 return;
		}
		$.ajax({
			url:PATH+'/baseinfo/positiveInfo/deletePositiveInfo.action?positiveInfos.id='+ selectedId,
			success:function(data){
				if(data == true){
					$("#householdStaffList").delRowData(positiveInfo.id);
					$.messageBox({ message:"成功删除"+title+"信息!"});
					checkExport();
					return;
				}else{
					$.messageBox({ message:"该人员信息已被别的模块所使用，不能删除!",level: "warn"	});
				}
	        }
		});
	}
	function viewDialogForDbclick(rowid){
		if(rowid==null){
	 		return;
		}
		var houseStaff =  $("#householdStaffList").getRowData(rowid);
		$("#householdStaffPopulationDialog").createDialog({
			width: 800,
			height: 600,
			title:'查看'+title+'信息',
			modal : true,
			url:PATH+'/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&id='+houseStaff.encryptId,
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
	
	/*
	function print(rowid){
		$("#householdStaffPopulationDialog").createDialog({
			width: 800,
			height: 605,
			title:'打印'+title+'信息',
			modal : true,
			url:PATH+'/baseinfo/positiveInfo/getPositiveInfosById.action?mode=print&positiveInfos.id='+rowid,
			buttons: {
				 "确定" : function(){
				$("#positiveInfoPrint").printArea();
	        	$(this).dialog("close");
		   		},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	*/
	
	function refreshPositiveInfoGrid() {
		var name=$("#householdStaffVo\\.name").val();
		var idCardNo=$("#householdStaffVo\\.idCardNo").val();
		var gender=$("#householdStaffVo\\.gender").val();
		var accountNumber=$("#householdStaffVo\\.accountNumber").val();
		var residentStatus=$("#householdStaffVo\\.residentStatus").val();
		var residentStatus=$("#_residentStatus").val();
		var workUnit=$("#householdStaffVo\\.workUnit").val();
		var outGone=$("#householdStaffVo\\.outGone").val();
		var telephone=$("#householdStaffVo\\.telephone").val();
		var mobileNumber=$("#householdStaffVo\\.mobileNumber").val();
		var schooling=$("#householdStaffVo\\.schooling").val();
		var birthday=$("#householdStaffVo\\.birthday").val();
		var endBirthday=$("#householdStaffVo\\.endBirthday").val();
		var usedName=$("#householdStaffVo\\.usedName").val();
		var career=$("#householdStaffVo\\.career").val();
		var politicalBackground=$("#householdStaffVo\\.politicalBackground").val();
		var maritalState=$("#householdStaffVo\\.maritalState").val();
		var residenceType=$("#householdStaffVo\\.residenceType").val();
		var nation=$("#householdStaffVo\\.nation").val();
		var faith=$("#householdStaffVo\\.faith").val();
		var bloodType=$("#householdStaffVo\\.bloodType").val();
		var email=$("#householdStaffVo\\.email").val();
		var province=$("#householdStaffVo\\.province").val();
		var city=$("#householdStaffVo\\.city").val();
		var district=$("#householdStaffVo\\.district").val();
		var currentAddress=$("#householdStaffVo\\.currentAddress").val();
		var nativePlaceAddress=$("#householdStaffVo\\.nativePlaceAddress").val();
		var otherAddress=$("#householdStaffVo\\.otherAddress").val();
		var outGoneBoolean=$("#householdStaffVo\\.outGoneBoolean").val();
		var outReasonsId=$("#householdStaffVo\\.outReasonsId").val();
		var outProvince=$("#householdStaffVo\\.outProvince").val();
		var outCity=$("#householdStaffVo\\.outCity").val();
		var outDistrict=$("#householdStaffVo\\.outDistrict").val();
		var logoutDateStart=$("#householdStaffVo\\.logoutDateStart").val();
		var logoutDateEnd=$("#householdStaffVo\\.logoutDateEnd").val();
		var logoutReason=$("#householdStaffVo\\.logoutReason").val();
		var initParam = {
			"orgId": getCurrentOrgId()
		}
			if($("#logout").val()=="-1"){
				if($("#_isDeath").val()=="-1"){
					initParam = {
							"orgId": getCurrentOrgId(),
							"householdStaffVo.name":name,
							"householdStaffVo.idCardNo":idCardNo,
							"householdStaffVo.gender":gender,
							"householdStaffVo.accountNumber":accountNumber,
							"householdStaffVo.workUnit":workUnit,
							"householdStaffVo.residentStatus":residentStatus,
							"householdStaffVo.outGone":outGone,
							"householdStaffVo.telephone":telephone,
							"householdStaffVo.mobileNumber":mobileNumber,
							"householdStaffVo.schooling":schooling,
							"householdStaffVo.birthday":birthday,
							"householdStaffVo.endBirthday":endBirthday,
							"householdStaffVo.usedName":usedName,
							"householdStaffVo.career":career,
							"householdStaffVo.politicalBackground":politicalBackground,
							"householdStaffVo.maritalState":maritalState,
							"householdStaffVo.residenceType":residenceType,
							"householdStaffVo.nation":nation,
							"householdStaffVo.faith":faith,
							"householdStaffVo.bloodType":bloodType,
							"householdStaffVo.email":email,
							"householdStaffVo.province":province,
							"householdStaffVo.city":city,
							"householdStaffVo.district":district,
							"householdStaffVo.currentAddress":currentAddress,
							"householdStaffVo.nativePlaceAddress":nativePlaceAddress,
							"householdStaffVo.otherAddress":otherAddress,
							"householdStaffVo.outGoneBoolean":outGoneBoolean,
							"householdStaffVo.outReasonsId":outReasonsId,
							"householdStaffVo.outProvince":outProvince,
							"householdStaffVo.outCity":outCity,
							"householdStaffVo.outDistrict":outDistrict,
							"householdStaffVo.logoutDateStart":logoutDateStart,
							"householdStaffVo.logoutDateEnd":logoutDateEnd,
							"householdStaffVo.logoutReason":logoutReason
							}
				}else{
					initParam = {
							"orgId": getCurrentOrgId(),
							"householdStaffVo.name":name,
							"householdStaffVo.idCardNo":idCardNo,
							"householdStaffVo.gender":gender,
							"householdStaffVo.accountNumber":accountNumber,
							"householdStaffVo.workUnit":workUnit,
							"householdStaffVo.residentStatus":residentStatus,
							"householdStaffVo.outGone":outGone,
							"householdStaffVo.telephone":telephone,
							"householdStaffVo.mobileNumber":mobileNumber,
							"householdStaffVo.schooling":schooling,
							"householdStaffVo.birthday":birthday,
							"householdStaffVo.endBirthday":endBirthday,
							"householdStaffVo.usedName":usedName,
							"householdStaffVo.career":career,
							"householdStaffVo.politicalBackground":politicalBackground,
							"householdStaffVo.maritalState":maritalState,
							"householdStaffVo.residenceType":residenceType,
							"householdStaffVo.nation":nation,
							"householdStaffVo.faith":faith,
							"householdStaffVo.bloodType":bloodType,
							"householdStaffVo.email":email,
							"householdStaffVo.province":province,
							"householdStaffVo.city":city,
							"householdStaffVo.district":district,
							"householdStaffVo.currentAddress":currentAddress,
							"householdStaffVo.nativePlaceAddress":nativePlaceAddress,
							"householdStaffVo.otherAddress":otherAddress,
							"householdStaffVo.isDeath":$("#_isDeath").val(),
							"householdStaffVo.outGoneBoolean":outGoneBoolean,
							"householdStaffVo.outReasonsId":outReasonsId,
							"householdStaffVo.outProvince":outProvince,
							"householdStaffVo.outCity":outCity,
							"householdStaffVo.outDistrict":outDistrict,
							"householdStaffVo.logoutDateStart":logoutDateStart,
							"householdStaffVo.logoutDateEnd":logoutDateEnd,
							"householdStaffVo.logoutReason":logoutReason
							}
				}
			}else{
				if($("#_isDeath").val()=="-1"){
					initParam = {
							"orgId": getCurrentOrgId(),
							"householdStaffVo.name":name,
							"householdStaffVo.idCardNo":idCardNo,
							"householdStaffVo.gender":gender,
							"householdStaffVo.accountNumber":accountNumber,
							"householdStaffVo.workUnit":workUnit,
							"householdStaffVo.residentStatus":residentStatus,
							"householdStaffVo.outGone":outGone,
							"householdStaffVo.telephone":telephone,
							"householdStaffVo.mobileNumber":mobileNumber,
							"householdStaffVo.schooling":schooling,
							"householdStaffVo.birthday":birthday,
							"householdStaffVo.endBirthday":endBirthday,
							"householdStaffVo.usedName":usedName,
							"householdStaffVo.career":career,
							"householdStaffVo.politicalBackground":politicalBackground,
							"householdStaffVo.maritalState":maritalState,
							"householdStaffVo.residenceType":residenceType,
							"householdStaffVo.nation":nation,
							"householdStaffVo.faith":faith,
							"householdStaffVo.bloodType":bloodType,
							"householdStaffVo.email":email,
							"householdStaffVo.province":province,
							"householdStaffVo.city":city,
							"householdStaffVo.district":district,
							"householdStaffVo.currentAddress":currentAddress,
							"householdStaffVo.nativePlaceAddress":nativePlaceAddress,
							"householdStaffVo.otherAddress":otherAddress,
							"householdStaffVo.logout":$("#logout").val(),
							"householdStaffVo.outGoneBoolean":outGoneBoolean,
							"householdStaffVo.outReasonsId":outReasonsId,
							"householdStaffVo.outProvince":outProvince,
							"householdStaffVo.outCity":outCity,
							"householdStaffVo.outDistrict":outDistrict,
							"householdStaffVo.logoutDateStart":logoutDateStart,
							"householdStaffVo.logoutDateEnd":logoutDateEnd,
							"householdStaffVo.logoutReason":logoutReason
							}
				}else{
					initParam = {
							"orgId": getCurrentOrgId(),
							"householdStaffVo.name":name,
							"householdStaffVo.idCardNo":idCardNo,
							"householdStaffVo.gender":gender,
							"householdStaffVo.accountNumber":accountNumber,
							"householdStaffVo.workUnit":workUnit,
							"householdStaffVo.residentStatus":residentStatus,
							"householdStaffVo.outGone":outGone,
							"householdStaffVo.telephone":telephone,
							"householdStaffVo.mobileNumber":mobileNumber,
							"householdStaffVo.schooling":schooling,
							"householdStaffVo.birthday":birthday,
							"householdStaffVo.endBirthday":endBirthday,
							"householdStaffVo.usedName":usedName,
							"householdStaffVo.career":career,
							"householdStaffVo.politicalBackground":politicalBackground,
							"householdStaffVo.maritalState":maritalState,
							"householdStaffVo.residenceType":residenceType,
							"householdStaffVo.nation":nation,
							"householdStaffVo.faith":faith,
							"householdStaffVo.bloodType":bloodType,
							"householdStaffVo.email":email,
							"householdStaffVo.province":province,
							"householdStaffVo.city":city,
							"householdStaffVo.district":district,
							"householdStaffVo.currentAddress":currentAddress,
							"householdStaffVo.nativePlaceAddress":nativePlaceAddress,
							"householdStaffVo.otherAddress":otherAddress,
							"householdStaffVo.logout":$("#logout").val(),
							"householdStaffVo.isDeath":$("#_isDeath").val(),
							"householdStaffVo.outGoneBoolean":outGoneBoolean,
							"householdStaffVo.outReasonsId":outReasonsId,
							"householdStaffVo.outProvince":outProvince,
							"householdStaffVo.outCity":outCity,
							"householdStaffVo.outDistrict":outDistrict,
							"householdStaffVo.logoutDateStart":logoutDateStart,
							"householdStaffVo.logoutDateEnd":logoutDateEnd,
							"householdStaffVo.logoutReason":logoutReason
							}
				}
		}
		$("#householdStaffList").setGridParam({
			url:PATH+'/baseinfo/householdStaff/findHouseholdStaffByOrgId.action',
			datatype : 'json',
			page:1
		});
		$("#householdStaffList").setPostData(initParam);
		$("#householdStaffList").trigger("reloadGrid");
	}
	
	function afterLoad(){
		logOutFormatter();
		disableButtons();
		checkExport();
		changeColor();
	}
	
	function changeColor(){
		if(notExecute==null||notExecute.length==0){
			return;
		}
		for(var i=0;i<notExecute.length;i++){
			var rowData=$("#householdStaffList").getRowData(notExecute[i]);
			$("#"+notExecute[i]).css('background','red');
			$("#householdStaffList").setSelection(notExecute[i])
		}
		notExecute.splice(0,notExecute.length);
	}
	
	function logOutFormatter(){
		var idCollection = new Array();
		idCollection=$("#householdStaffList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#householdStaffList").getRowData(idCollection[i]);
				if(ent.logOut==1||ent.logOut=='1'){
					$("#"+idCollection[i]+"").css('color','#778899');
				}else if(ent.death==true || ent.death=='true'){
					$("#"+idCollection[i]+"").css('color','#ccc');
				}
		}
	}
	

	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#householdStaffList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function selectRow(){
		var selectedCounts = getActualjqGridMultiSelectCount("householdStaffList");
		var count = $("#householdStaffList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("householdStaffList", true);
		}else{
			jqGridMultiSelectState("householdStaffList", false);
		}
	}
	
	function getSelectedIds(){
		var selectedIds = getActualjqGridMultiSelectIds("householdStaffList");
		return selectedIds;
	}
	
	$("#fastSearchButton").click(function(){
		search(getCurrentOrgId());
	});
	function search(orgId){
		var conditions = $("#searchText").val();
		if(conditions == '请输入姓名或身份证号码') return;
		var initParam = {
			"orgId": orgId,
			"householdStaffVo.logOut":0,
			"householdStaffVo.isDeath":0,
			"householdStaffVo.fastSearchKeyWords":conditions
		}
	
		 $("#householdStaffList").setGridParam({
			url:PATH+"/baseinfo/householdStaff/fastSearch.action",
			datatype: "json",
			page:1
			 });
		 $("#householdStaffList").setPostData(initParam);
		 $("#householdStaffList").trigger("reloadGrid");
	}
	function getIdCardNo(idCardNo,logOut){
		var actualIdCardNo;
		if(logOut==1||logOut=='1'){
			actualIdCardNo = idCardNo.substring(22).split("</font>")[0];
		}else{
			actualIdCardNo = idCardNo.substring(19).split("</font>")[0];
		}
		return actualIdCardNo;
	}
	
	function updateIdCardNo(rowId,idCardNo,orgId){
		$("#householdStaffPopulationDialog").createDialog({
			width:320,
			height:150,
			title:'修改身份证号码',
			url:PATH+'/baseinfo/common/commonUpdateIdCardNoDlg.jsp?listTable=householdStaffList&dialog=householdStaffPopulationDialog&countrymenId='+rowId+'&idCardNo='+idCardNo+'&orgId='+orgId+'&actualPopulationType='+dfop.actualPopulationType,
			buttons: {
		   		"保存" : function(event){
		   			$("#updateIdCardNoForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
};