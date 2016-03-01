TQ.unsettledPopulationList = function (dfop){
	function search(orgId){
		var condition = $("#searchByCondition").val();
		if(condition == '请输入姓名或身份证号码') return;
		var initParam = {
			"orgId": orgId,
			"unsettledPopulationCondition.logOut":0,
			"unsettledPopulationCondition.isDeath":0,
			"unsettledPopulationCondition.fastSearchKeyWords":condition
		}
	
		$("#unsettledPopulationList").setGridParam({
			url:PATH+'/baseinfo/unsettledPopulationSearch/fastSearchUnsettledPopulation.action',
			datatype: "json",
			page:1
		});
		$("#unsettledPopulationList").setPostData(initParam);
		$("#unsettledPopulationList").trigger("reloadGrid");
	}
	

	
	$(document).ready(function(){
		function clickDisabled(name){
			var id="#"+name;
			var isDisabled=$(id).attr("disabled");
			if(isDisabled=="disabled"){
				return true;
			}
		}
	
		$("#add").click(function(event){
			if(!isGrid()){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
				return;
			}
			$("#unsettledPopulationMaintanceDialog").createTabDialog({
				title:"新增未落户人口",
				width: dialogWidth,
				height: dialogHeight,
				postData:{
					mode:'add',
					imageType:"population",
					type:"unsettledPopulation"
				},
				tabs:dfop.addTabs,
				close : function(){
					$("#unsettledPopulationList").trigger("reloadGrid");
				}
			});
		  });
	
		$("#refreshSearchKey").click(function(event){
			$("#searchByCondition").attr("value","请输入姓名或身份证号码");
		});
	
		$("#reload").click(function(event){
			onOrgChanged(getCurrentOrgId(),isGrid());
			$("#searchByCondition").attr("value","请输入姓名或身份证号码");
		});
		$("#searchByConditionButton").click(function(){
			search(getCurrentOrgId());
	
		});
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
				var rowData=$("#unsettledPopulationList").getRowData(allValue[i]);
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
			var encryptIds=deleteOperatorByEncrypt("unsettledPopulation",allValue,"encryptId");
			$("#unsettledPopulationDialog").createDialog({
				width:450,
				height:210,
				title:'注销提示',
				modal:true,
				url:PATH+'/baseinfo/commonPopulation/populationEmphasiseConditionDlg.jsp?populationIds='+encryptIds+'&isEmphasis=1&lowerCaseModuleName=unsettledPopulation&type=actualPopulation&temp='+temp+"&orgId="+getCurrentOrgId(),
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
		   		var row=$("#unsettledPopulationList").getRowData(allValue[i]);
		   		if((row.logOut==1||row.logOut=='1')&&(row.death!=true&&row.death!='true')){
		   		$.ajax({
	   				async: false ,
	   				url:PATH+"/baseinfo/householdStaff/findHouseholdStaffByIdCardNoAndOrgIdEncrypt.action",
	   				data:{
	   					"population.idCardNo":row.encryptId,
	   					"population.organization.id":row["organization.id"]
	   				},
	   				success:function(population){
	   					if(population!=null&&population.logOut==0){
	   						temp.push(row.id);
	   						$.messageBox({message:"已经落户且在户籍人口中为关注状态不允许重新关注!",level:"warn"});
	   					}else{
	   						cancelLogOut.push(row.id);
	   					}
	   				}
	   			});
		   		}else{
		   			temp.push(allValue[i]);
			   		}
		   	}
		   	allValue=cancelLogOut;
		   	if(allValue==null||allValue.length==0){
				$.messageBox({level:'warn',message:"没有可取消注销的数据"});
				return;
			}
		   	var encryptIds=deleteOperatorByEncrypt("unsettledPopulation",allValue,"encryptId");
			$.confirm({
					title:"确认取消注销",
					message:"确定要取消注销吗?",
					okFunc: function(){
						$.ajax({
							url:PATH+"/baseinfo/unsettledPopulationManage/updateEmphasiseByEncryptId.action?population.logoutDetail.logout=0&populationIds="+encryptIds+"&orgId="+getCurrentOrgId(),
							success:function(datas){
								if(null==temp||temp.length==0){
									$.messageBox({message:"已经成功取消注销该"+title+"信息!"});
								}else{
									$.messageBox({level:'warn',message:"除选中的红色数据外,已经成功取消注销该"+title+"信息!"});
								}
									notExecute=temp;
								$("#unsettledPopulationList").trigger("reloadGrid");
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
		   		var row=$("#unsettledPopulationList").getRowData(allValue[i]);
		   		if(row.death==true||row.death=='true'){
		   			cancelDeath.push(allValue[i]);
		   		}else{
		   			temp.push(allValue[i]);
			   		}
		   	}
		   	allValue=cancelDeath;
		   	if(allValue==null||allValue.length==0){
				$.messageBox({level:'warn',message:"没有可取消死亡的数据"});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("unsettledPopulation",allValue,"encryptId");
			$.confirm({
					title:"确认取消死亡",
					message:"确定要取消死亡吗?",
					okFunc: function(){
						$.ajax({
							url:PATH+"/baseinfo/unsettledPopulationManage/updateDeathOfUnsettledPopulationByEncryptId.action?unsettledPopulation.death=false&unsettledPopulationIds="+encryptIds,
							success:function(datas){
									for(var i = 0;i<datas.length;i++){
										var responseData = datas[i];
										if(($("#isLock").val()=='2'&&$("#isDeath").val()=="0")){
						 					$("#unsettledPopulationList").delRowData(responseData.id);
					 					}else{
						 					$("#unsettledPopulationList").setRowData(responseData.id,responseData);
						 					$("#unsettledPopulationList").setSelection(responseData.id);
							 			}
									}
								if(null==temp||temp.length==0){
									$.messageBox({message:"已经成功取消死亡该"+title+"信息!"});
								}else{
									$.messageBox({level:'warn',message:"除选中的红色数据外,已经成功取消死亡该"+title+"信息!"});
								}
								notExecute=temp;
								$("#unsettledPopulationList").trigger("reloadGrid");
								disableButtons();
								checkExport();
							}
					});
				}
			});
		});
	
		$("#delete").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				return;
			}
			var encryptIds = deleteOperatorByEncrypt("unsettledPopulation",allValue,"encryptId");
			var str = "确定删除吗？一经删除无法恢复 ";
			$.confirm({
					title:"确认删除",
					message:str,
					okFunc: function() {
						$.ajax({
							url:PATH+"/baseinfo/unsettledPopulationManage/deleteUnsettledPopulation.action",
							type:"post",
							data:{
								"unsettledPopulationIds":encryptIds
							},
							success:function(data){
								$("#unsettledPopulationList").trigger("reloadGrid");
							    $.messageBox({message:"已经成功删除该"+title+"信息!"});
							    disableButtons();
							    checkExport();
						}
					});
				}
			});
		});
	
		$("#view").click(function(){
			if(clickDisabled("view")){
				return;
			}
			var selectedId =getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
			viewDialog(event,selectedId);
		});
	
		$("#search").click(function(event){
			$("#unsettledPopulationMaintanceDialog").createDialog({
				width:650,
				height:320,
				title:title+'查询-请输入查询条件',
				url:PATH+'/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=search',
				buttons: {
			   		"查询" : function(event){
			   			searchUnsettledPopulation();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	
		$("#import").click(function(event){
			$("#unsettledPopulationMaintanceDialog").createDialog({
				width: 400,
				height: 230,
				title:'数据导入',
				url:PATH+'/common/import.jsp?isNew=1&dataType=unsettledPopulationData&dialog=unsettledPopulationMaintanceDialog&startRow=6&templates='+dfop.templates+'&listName=unsettledPopulationList',
				buttons: {
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
			if ($("#unsettledPopulationList").getGridParam("records")>0){
				$("#unsettledPopulationMaintanceDialog").createDialog({
					width: 250,
					height: 200,
					title:'导出'+title+'信息',
					url:PATH+'/common/exportExcel.jsp',
					postData:{
						gridName:'unsettledPopulationList',
						downloadUrl:PATH+'/baseinfo/unsettledPopulationSearch/downloadUnsettledPopulation.action'
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
				$.messageBox({level:"warn",message:"列表中没有数据，不能导出！"});
			}
		});
	
		$("#isDeath").change(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
	
		$("#settle").click(function(event){
			if(!isGrid()){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行落户！"});
				return;
			}
			var selectedId =getSelectedIds();
			if(selectedId==undefined||selectedId.length>1||selectedId.length==0){
				$.messageBox({level:"warn",message:"请先选择一条记录，再进行落户！"});
				return;
			}
			var rowData =  $("#unsettledPopulationList").getRowData(selectedId);
			var idCardNo = rowData.idCardNo;
			if(rowData.death=="true"){
				$.messageBox({message:"该未落户人口已死亡不能进行落户!",level:"warn"});
				return;
			}
			if(rowData.logOut==1||rowData.logOut=='1'){
				$.messageBox({message:"该未落户人口已注销不能进行落户!",level:"warn"});
				return;
			}
			if(idCardNo==null||idCardNo==""){
				$.messageBox({message:"该未落户人口没有身份证号码不能进行落户!",level:"warn"});
				return;
			}
			if(!isHouseHoldStaff(idCardNo ,rowData["organization.id"])){
			$.confirm({
					title:"落户提示",
					message:"是否把“未落户人口：<u>"+rowData.name+"，"+idCardNo+"</u> 落户为户籍人口？",
					okFunc: function(){
						$("#unsettledPopulationMaintanceDialog").createTabDialog({
							title:"未落户人口-落户",
							width: dialogWidth,
							height: dialogHeight,
							postData:{
								type:"unsettledPopulation"
							},
							tabs:dfop.settleTabs(idCardNo),
							close : function(){
								$("#unsettledPopulationList").trigger("reloadGrid");
							}
						});
					}
			});
			}
		});
		$("#transfer").click(function(e){
			//获取需要转移的id
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行转移！"});
				 return;
			}
			for(var i=0;i<allValue.length;i++){
				var rowData_Popu=$("#unsettledPopulationList").getRowData(allValue[i]);
				if(rowData_Popu.logOut==1||rowData_Popu.death=="true"){
					$.messageBox({level:'warn',message:"所选记录中存在已注销（或死亡）记录，无法转移！"});
					 return;
				}
			}
			//get current orgid
		    var orgid=	getCurrentOrgId();
			if(orgid==""||orgid==null){
				$.messageBox({level:'warn',message:"没有获取到当前的组织机构id"});
				 return;
			}
			moveOperator(e,allValue,orgid);
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
			var rowData =  $("#unsettledPopulationList").getRowData(selectedId);
			var idCardNo = rowData.idCardNo;
			var	orgId=rowData["organization.id"];
			if(idCardNo==null || idCardNo=="" || typeof(idCardNo)=="undefined"){
				$.messageBox({level:"warn",message:"身份证号未录入不能修改"});
				return;
			}
			updateIdCardNo(selectedId,idCardNo,orgId);
		});
		
		function moveOperator(event,allValue,orgid){
			var encryptIds = deleteOperatorByEncrypt("unsettledPopulation",allValue,"encryptId");
			var allOrgId = getOrgIdsByIds("unsettledPopulation",allValue,"organization.id");
			$("#moveDataDialog").createDialog({
				width: 400,
				height: 230,
				title:"数据转移",
				url:PATH+"/transferManage/transferDispatchByEncryptId.action?orgId="+orgid+"&Ids="+encryptIds+"&type=unsettledPopulation&orgIds="+allOrgId,
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
		function isHouseHoldStaff(idCardNo ,orgId){
			var isHouseHoldStaff;
			$.ajax({
				async: false ,
				url:PATH+"/baseinfo/householdStaff/findHouseholdStaffByIdCardNoAndOrgId.action",
				data:{
					"population.idCardNo":idCardNo,
					"population.organization.id":orgId
				},
				success:function(population){
					if(population!=null&&population.logOut==0){
						isHouseHoldStaff=true;
						$.messageBox({message:"已经落户且在户籍人口中为关注状态不允许重新关注!",level:"warn"});
					}else{
						isHouseHoldStaff=false;
					}
				}
			});
			return isHouseHoldStaff;
		}
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
	
	
	
		$("#unsettledPopulationList").jqGridFunction({
			datatype: "local",
		   	colModel:[
		        {name:"id",index:"id",hidden:true,sortable:false,frozen:true},
		        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
		        {name:'organization.id',index:'organization.id',hidden:true,hidedlg:true,sortable:false,frozen:true},
		        {name:"operator",label:"操作",index:'id',width:90,formatter:operateFormatter,sortable:false,frozen:true,align:"center"},
		        {name:"name",index:'name',label:'姓名',sortable:false,width:80,formatter:nameFont,frozen:true},
		        {name:"gender",label:'性别',sortable:false,width:50,align:'center',formatter : genderFormatter },    
		        {name:'idCardNo',label:'身份证号码',sortable:false,width:160,align:'center',frozen:true},
		        {name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:150,sortable:false,hidden:true},
		        {name:'usedName',label:'曾用名',sortable:false,width:100,hidden:true},
		        {name:'mobileNumber',label:'联系手机',sortable:false,width:100,align:'center',hidden:true},
		        {name:'telephone',label:'固定电话',sortable:false,width:120,align:'center',hidden:true},
		        {name:"birthday",label:"出生日期", width:80,align:'center',sortable:false,hidden:true},
		        {name:'nation',label:'民族',formatter:nationFormatter,sortable:false,width:80,hidden:true},
		        {name:'politicalBackground',label:'政治面貌',formatter:politicalBackgroundFormatter,sortable:false,width:150,hidden:true},
		        {name:'schooling',label:'文化程度',formatter:schoolingFormatter,sortable:false,width:100,hidden:true},
		        {name:'career',label:'职业',formatter:careerFormatter,sortable:false,width:100,hidden:true},
		        {name:'workUnit',label:'工作单位或就读学校',sortable:false, width:200},
		        {name:'maritalState',label:'婚姻状况',formatter:maritalStateFormatter,sortable:false,align:'center',width:80,hidden:true},
		        {name:'stature',label:'身高(cm)',sortable:false,width:80,align:'center',hidden:true},
		        {name:'bloodType',label:'血型',formatter:bloodTypeFormatter,sortable:false,width:100,hidden:true},
		        {name:'faith',label:'宗教信仰',formatter:faithFormatter,sortable:false,width:80,hidden:true},
		        {name:'email',label:'电子邮箱',sortable:false,width:120,hidden:true},
		        {name:'unSettedTime',label:'未落户时间',sortable:false,align:'center',width:90,hidden:true},
		        {name:'unSettedReason',label:'未落户原因',formatter:unSettedReasonFormatter,align:'center',sortable:false,width:90},
		        {name:'certificateType',label:'持证种类',formatter:certificateTypeFormatter,align:'center',sortable:false,width:80,hidden:true},
		        {name:'certificateNo',label:'持证编号',sortable:false,width:100,hidden:true},
		        {name:"province",label:"户籍地", width:120,sortable:false,formatter:householdRegisterFormatter, hidden:true},
		        {name:'nativePlaceAddress',label:'户籍地详址',sortable:false,width:100,hidden:true},
		        {name:'currentAddress',label:'常住地址',sortable:false,width:200},
		        {name:'otherAddress',label:'临时居所',sortable:false,width:100,hidden:true},
		        {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		        {name:'updateDate', sortable:false, label:'数据更新时间',align:'center', width:160},
		        //{name:'mobileNumber',label:'联系手机',sortable:true,width:100,align:'center',hidden:true},
		       // {name:'telephone',label:'固定电话',sortable:true,width:120,align:'center',hidden:true},
		        {name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
		        {name:'logOut',sortable:false,hidden:true,hidedlg:true,width:80}
			],
		  	onSelectAll:function(aRowids,status){},
		    loadComplete: afterLoad,
		    multiselect:true,
		    ondblClickRow: function (rowid){
		    	if(dfop.hasViewUnsettledPopulation == 'true'){
		    		viewDialogs(rowid);
		    	}
		    },
		    onSelectRow:selectRow
		});
		if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
	});
	jQuery("#unsettledPopulationList").jqGrid('setFrozenColumns');
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
			var rowData=$("#unsettledPopulationList").getRowData(notExecute[i]);
			$("#"+notExecute[i]).css('background','red');
			$("#unsettledPopulationList").setSelection(notExecute[i])
		}
		notExecute.splice(0,notExecute.length);
	}
	
	function logOutFormatter(){
		var idCollection = new Array();
		idCollection=$("#unsettledPopulationList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#unsettledPopulationList").getRowData(idCollection[i]);
				if(ent.logOut==1||ent.logOut=='1'){
					$("#"+idCollection[i]+"").css('color','#778899');
				}
		}
	}
	
	function selectRow(){
		var selectedCounts = getActualjqGridMultiSelectCount("unsettledPopulationList");
		var count = $("#unsettledPopulationList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("unsettledPopulationList", true);
		}else{
			jqGridMultiSelectState("unsettledPopulationList", false);
		}
	}
	
	

	
		function viewDialogs(rowid){
			if(rowid==null){
	 			return;
			}
			var unsettledPopulation =  $("#unsettledPopulationList").getRowData(rowid);
			$("#unsettledPopulationMaintanceDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:'查看'+title+'信息',
				url:PATH+'/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action?mode=view&id='+unsettledPopulation.encryptId,
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
		
	function searchUnsettledPopulation(){
		var conditionName=$("#conditionName").val();
		var conditionUsedName=$("#conditionUsedName").val();
		var conditionGender=$("#conditionGender").val();
		var conditionIdCardNo=$("#conditionIdCardNo").val();
		var conditionCertificateType=$("#conditionCertificateType").val();
		var conditionCertificateNo=$("#conditionCertificateNo").val();
		var conditionNativePlaceAddress=$("#conditionNativePlaceAddress").val();
		var conditionCurrentAddress=$("#conditionCurrentAddress").val();
		var conditionWorkUnit=$("#conditionWorkUnit").val();
		var conditionTelephone=$("#conditionTelephone").val();
		var conditionMobileNumber=$("#conditionMobileNumber").val();
		var conditionBirthdayStart=$("#conditionBirthdayStart").val();
		var conditionBirthdayEnd=$("#conditionBirthdayEnd").val();
		var conditionUnsettedTimeStart=$("#conditionUnsettedTimeStart").val();
		var conditionUnsettedTimeEnd=$("#conditionUnsettedTimeEnd").val();
		var conditionUnSettedReason=$("#conditionUnSettedReason").val();
		var conditionProvince=$("#conditionProvince").val();
		var conditionCity=$("#conditionCity").val();
		var conditionDistrict=$("#conditionDistrict").val();
		var conditionNativePlaceAddress=$("#conditionNativePlaceAddress").val();
		var conditionPoliticalBackground=$("#conditionPoliticalBackground").val();
		var conditionMaritalState=$("#conditionMaritalState").val();
		var conditionNation=$("#conditionNation").val();
		var conditionFaith=$("#conditionFaith").val();
		var conditionSchooling=$("#conditionSchooling").val();
		var conditionBloodType=$("#conditionBloodType").val();
		var conditionEmail=$("#conditionEmail").val();
		var conditionRemark=$("#conditionRemark").val();
		var conditionStature=$("#conditionStature").val();
		var conditionOtherAddress=$("#conditionOtherAddress").val();
		var conditionCareer=$("#conditionCareer").val();
		var initParam = {
			"orgId": getCurrentOrgId()
		}
		if($("#logOut").val()=="-1"){
			if($("#isDeath").val()=="-1"){
				initParam = {
						"isSearch":"1",
						"orgId":getCurrentOrgId(),
		    			"unsettledPopulationCondition.name":conditionName,
		    			"unsettledPopulationCondition.usedName":conditionUsedName,
		    			"unsettledPopulationCondition.gender.id":conditionGender,
		    			"unsettledPopulationCondition.idCardNo":conditionIdCardNo,
		    			"unsettledPopulationCondition.certificateType.id":conditionCertificateType,
		    			"unsettledPopulationCondition.certificateNo":conditionCertificateNo,
		    			"unsettledPopulationCondition.nativePlaceAddress":conditionNativePlaceAddress,
		    			"unsettledPopulationCondition.politicalBackground.id":conditionPoliticalBackground,
		    			"unsettledPopulationCondition.currentAddress":conditionCurrentAddress,
		    			"unsettledPopulationCondition.otherAddress":conditionOtherAddress,
		    			"unsettledPopulationCondition.workUnit":conditionWorkUnit,
		    			"unsettledPopulationCondition.telephone":conditionTelephone,
		    			"unsettledPopulationCondition.mobileNumber":conditionMobileNumber,
		    			"unsettledPopulationCondition.birthdayStart":conditionBirthdayStart,
		    			"unsettledPopulationCondition.birthdayEnd":conditionBirthdayEnd,
		    			"unsettledPopulationCondition.unSettedReason.id":conditionUnSettedReason,
		    			"unsettledPopulationCondition.unsettedTimeStart":conditionUnsettedTimeStart,
		    			"unsettledPopulationCondition.unsettedTimeEnd":conditionUnsettedTimeEnd,
		    			"unsettledPopulationCondition.province":conditionProvince,
		    			"unsettledPopulationCondition.city":conditionCity,
		    			"unsettledPopulationCondition.district":conditionDistrict,
		    			"unsettledPopulationCondition.maritalState.id":conditionMaritalState,
		    			"unsettledPopulationCondition.nation.id":conditionNation,
		    			"unsettledPopulationCondition.faith.id":conditionFaith,
		    			"unsettledPopulationCondition.schooling.id":conditionSchooling,
		    			"unsettledPopulationCondition.bloodType.id":conditionBloodType,
		    			"unsettledPopulationCondition.email":conditionEmail,
		    			"unsettledPopulationCondition.remark":conditionRemark,
		    			"unsettledPopulationCondition.stature":conditionStature,
		    			"unsettledPopulationCondition.career.id":conditionCareer
						}
			}else{
				initParam = {
						"isSearch":"1",
						"orgId":getCurrentOrgId(),
	    			"unsettledPopulationCondition.name":conditionName,
	    			"unsettledPopulationCondition.usedName":conditionUsedName,
	    			"unsettledPopulationCondition.gender.id":conditionGender,
	    			"unsettledPopulationCondition.idCardNo":conditionIdCardNo,
	    			"unsettledPopulationCondition.certificateType.id":conditionCertificateType,
	    			"unsettledPopulationCondition.certificateNo":conditionCertificateNo,
	    			"unsettledPopulationCondition.nativePlaceAddress":conditionNativePlaceAddress,
	    			"unsettledPopulationCondition.politicalBackground.id":conditionPoliticalBackground,
	    			"unsettledPopulationCondition.currentAddress":conditionCurrentAddress,
	    			"unsettledPopulationCondition.otherAddress":conditionOtherAddress,
	    			"unsettledPopulationCondition.workUnit":conditionWorkUnit,
	    			"unsettledPopulationCondition.telephone":conditionTelephone,
	    			"unsettledPopulationCondition.mobileNumber":conditionMobileNumber,
	    			"unsettledPopulationCondition.birthdayStart":conditionBirthdayStart,
	    			"unsettledPopulationCondition.birthdayEnd":conditionBirthdayEnd,
	    			"unsettledPopulationCondition.unSettedReason.id":conditionUnSettedReason,
	    			"unsettledPopulationCondition.unsettedTimeStart":conditionUnsettedTimeStart,
	    			"unsettledPopulationCondition.unsettedTimeEnd":conditionUnsettedTimeEnd,
	    			"unsettledPopulationCondition.province":conditionProvince,
	    			"unsettledPopulationCondition.city":conditionCity,
	    			"unsettledPopulationCondition.district":conditionDistrict,
	    			"unsettledPopulationCondition.maritalState.id":conditionMaritalState,
	    			"unsettledPopulationCondition.nation.id":conditionNation,
	    			"unsettledPopulationCondition.faith.id":conditionFaith,
	    			"unsettledPopulationCondition.schooling.id":conditionSchooling,
	    			"unsettledPopulationCondition.bloodType.id":conditionBloodType,
	    			"unsettledPopulationCondition.email":conditionEmail,
	    			"unsettledPopulationCondition.remark":conditionRemark,
	    			"unsettledPopulationCondition.stature":conditionStature,
	    			"unsettledPopulationCondition.career.id":conditionCareer,
					"unsettledPopulationCondition.isDeath":$("#isDeath").val()
					}
				}
		}else{
			if($("#isDeath").val()=="-1"){
				initParam = {
						"isSearch":"1",
						"orgId":getCurrentOrgId(),
		    			"unsettledPopulationCondition.name":conditionName,
		    			"unsettledPopulationCondition.usedName":conditionUsedName,
		    			"unsettledPopulationCondition.gender.id":conditionGender,
		    			"unsettledPopulationCondition.idCardNo":conditionIdCardNo,
		    			"unsettledPopulationCondition.certificateType.id":conditionCertificateType,
		    			"unsettledPopulationCondition.certificateNo":conditionCertificateNo,
		    			"unsettledPopulationCondition.nativePlaceAddress":conditionNativePlaceAddress,
		    			"unsettledPopulationCondition.politicalBackground.id":conditionPoliticalBackground,
		    			"unsettledPopulationCondition.currentAddress":conditionCurrentAddress,
		    			"unsettledPopulationCondition.otherAddress":conditionOtherAddress,
		    			"unsettledPopulationCondition.workUnit":conditionWorkUnit,
		    			"unsettledPopulationCondition.telephone":conditionTelephone,
		    			"unsettledPopulationCondition.mobileNumber":conditionMobileNumber,
		    			"unsettledPopulationCondition.birthdayStart":conditionBirthdayStart,
		    			"unsettledPopulationCondition.birthdayEnd":conditionBirthdayEnd,
		    			"unsettledPopulationCondition.unSettedReason.id":conditionUnSettedReason,
		    			"unsettledPopulationCondition.unsettedTimeStart":conditionUnsettedTimeStart,
		    			"unsettledPopulationCondition.unsettedTimeEnd":conditionUnsettedTimeEnd,
		    			"unsettledPopulationCondition.province":conditionProvince,
		    			"unsettledPopulationCondition.city":conditionCity,
		    			"unsettledPopulationCondition.district":conditionDistrict,
		    			"unsettledPopulationCondition.maritalState.id":conditionMaritalState,
		    			"unsettledPopulationCondition.nation.id":conditionNation,
		    			"unsettledPopulationCondition.faith.id":conditionFaith,
		    			"unsettledPopulationCondition.schooling.id":conditionSchooling,
		    			"unsettledPopulationCondition.bloodType.id":conditionBloodType,
		    			"unsettledPopulationCondition.email":conditionEmail,
		    			"unsettledPopulationCondition.remark":conditionRemark,
		    			"unsettledPopulationCondition.stature":conditionStature,
		    			"unsettledPopulationCondition.career.id":conditionCareer,
						"unsettledPopulationCondition.logOut":$("#logOut").val()
				}
			}else{
				initParam = {
						"isSearch":"1",
						"orgId":getCurrentOrgId(),
		    			"unsettledPopulationCondition.name":conditionName,
		    			"unsettledPopulationCondition.usedName":conditionUsedName,
		    			"unsettledPopulationCondition.gender.id":conditionGender,
		    			"unsettledPopulationCondition.idCardNo":conditionIdCardNo,
		    			"unsettledPopulationCondition.certificateType.id":conditionCertificateType,
		    			"unsettledPopulationCondition.certificateNo":conditionCertificateNo,
		    			"unsettledPopulationCondition.nativePlaceAddress":conditionNativePlaceAddress,
		    			"unsettledPopulationCondition.politicalBackground.id":conditionPoliticalBackground,
		    			"unsettledPopulationCondition.currentAddress":conditionCurrentAddress,
		    			"unsettledPopulationCondition.otherAddress":conditionOtherAddress,
		    			"unsettledPopulationCondition.workUnit":conditionWorkUnit,
		    			"unsettledPopulationCondition.telephone":conditionTelephone,
		    			"unsettledPopulationCondition.mobileNumber":conditionMobileNumber,
		    			"unsettledPopulationCondition.birthdayStart":conditionBirthdayStart,
		    			"unsettledPopulationCondition.birthdayEnd":conditionBirthdayEnd,
		    			"unsettledPopulationCondition.unSettedReason.id":conditionUnSettedReason,
		    			"unsettledPopulationCondition.unsettedTimeStart":conditionUnsettedTimeStart,
		    			"unsettledPopulationCondition.unsettedTimeEnd":conditionUnsettedTimeEnd,
		    			"unsettledPopulationCondition.province":conditionProvince,
		    			"unsettledPopulationCondition.city":conditionCity,
		    			"unsettledPopulationCondition.district":conditionDistrict,
		    			"unsettledPopulationCondition.maritalState.id":conditionMaritalState,
		    			"unsettledPopulationCondition.nation.id":conditionNation,
		    			"unsettledPopulationCondition.faith.id":conditionFaith,
		    			"unsettledPopulationCondition.schooling.id":conditionSchooling,
		    			"unsettledPopulationCondition.bloodType.id":conditionBloodType,
		    			"unsettledPopulationCondition.email":conditionEmail,
		    			"unsettledPopulationCondition.remark":conditionRemark,
		    			"unsettledPopulationCondition.stature":conditionStature,
		    			"unsettledPopulationCondition.career.id":conditionCareer,
						"unsettledPopulationCondition.logOut":$("#logOut").val(),
						"unsettledPopulationCondition.isDeath":$("#isDeath").val()
				}
			}
		}
		$("#unsettledPopulationList").setGridParam({
			url:PATH+'/baseinfo/unsettledPopulationSearch/searchUnsettledPopulation.action',
			datatype: "json",
			page:1
		});
		$("#unsettledPopulationList").setPostData(initParam);
		$("#unsettledPopulationList").trigger("reloadGrid");
	}
	function getSelectedIds(){
		var selectedIds = $("#unsettledPopulationList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#unsettledPopulationList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
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
		$("#unsettledPopulationDialog").createDialog({
			width:320,
			height:150,
			title:'修改身份证号码',
			url:PATH+'/baseinfo/common/commonUpdateIdCardNoDlg.jsp?listTable=unsettledPopulationList&dialog=unsettledPopulationDialog&countrymenId='+rowId+'&orgId='+orgId+'&idCardNo='+idCardNo+'&actualPopulationType='+dfop.actualPopulationType,
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
}