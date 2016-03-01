TQ.overseaPersonnelListFunction = function (dfop){

	$(document).ready(function(){

		$("#add").click(function(event){
			if (!isgridBol){
				$.messageBox({level:'warn',message:"请先选择网格级别组织机构进行新增！"});
				return;
			}
			$("#overseaPersonnelMaintanceDialog").createTabDialog({
				width: dialogWidth,
				height: dialogHeight,
				postData:{
					mode:'add',
					imageType:"population",
					type:"overseaStaff"
				},
				title:"新增境外人员",
				tabs:dfop.addTabs,
				close : function(){
					$("#overseaPersonnelList").trigger("reloadGrid");
				}
			});

		});

		$("#delete").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			var str="确定要删除吗?一经删除将无法恢复";
			var encryptIds = deleteOperatorByEncrypt("overseaPersonnel",allValue,"encryptId");
			$.confirm({
					title:"确认删除",
					message:str,
					okFunc: function() {
						$.ajax({
							url:PATH+"/baseinfo/overseaPersonnelManage/deleteOverseaPersonnel.action",
							type:"post",
							data:{
								"deleteIds":encryptIds
							},
							success:function(data){
								$("#overseaPersonnelList").trigger("reloadGrid");
							    $.messageBox({message:"已经成功删除该"+title+"信息!"});
							    disableButtons();
							    checkExport();
						}
					});
				}
			});
		});

		$("#fastSearch").click(function(){
			fastSearchfun(getCurrentOrgId());
		});

		$("#refreshSearchKey").click(function(event){
			$("#searchByCondition").attr("value","请输入英文名或证件号码");
		});

		$("#reload").click(function(event){
			$("#searchByCondition").attr("value","请输入英文名或证件号码");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});

		function clickDisabled(name){
			var id="#"+name;
			var isDisabled=$(id).attr("disabled");
			if(isDisabled=="disabled"){
				return true;
			}
		}

		$("#logOut").click(function(){
			if(clickDisabled("logOut")){
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
				var rowData=$("#overseaPersonnelList").getRowData(allValue[i]);
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
			var encryptIds=deleteOperatorByEncrypt("overseaPersonnel",allValue,"encryptId");
			$("#overseaPersonnelDialog").createDialog({
				width:450,
				height:210,
				title:'注销提示',
				modal:true,
				url:PATH+'/baseinfo/commonPopulation/populationEmphasiseConditionDlg.jsp?populationIds='+encryptIds+'&isEmphasis=1&lowerCaseModuleName=overseaPersonnel&type=actualPopulation&temp='+temp+"&orgId="+getCurrentOrgId(),
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
			for(var i=0;i<allValue.length;i++){
				var rowData=$("#overseaPersonnelList").getRowData(allValue[i]);
				if(rowData.logOut==1 || rowData.logOut=='1'){
					cancelLogOut.push(allValue[i]);
				}else{
					temp.push(allValue[i]);
				}
			}
			allValue=cancelLogOut;
			if(allValue==null||allValue.length==0){
				$.messageBox({level:'error',message:"没有可取消注销的数据"});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("overseaPersonnel",allValue,"encryptId");
			$.confirm({
			title:"确认取消注销",
			message:"确定要取消注销吗?",
			okFunc: function(){
			$.ajax({
				url:PATH+"/baseinfo/overseaPersonnelManage/updateEmphasiseByEncryptId.action?population.logoutDetail.logout=0&populationIds="+encryptIds+"&orgId="+getCurrentOrgId(),
				success:function(datas){
					for(var i = 0;i<datas.length;i++){
						var responseData = datas[i];
						if(($("#isLock").val()=='1')){
						 	$("#overseaPersonnelList").delRowData(responseData.id);
					 	}else{
						 	$("#overseaPersonnelList").setRowData(responseData.id,responseData);
						 	$("#"+responseData.id+"").css('color','#000000');
						 	$("#overseaPersonnelList").setSelection(responseData.id);
						}
					}
					if(null==temp||temp.length==0){
						$.messageBox({message:"已经成功取消注销该境外人员!"});
					}else{
						$.messageBox({level:'warn',message:"除选中的红色数据外,已经成功取消注销该境外人员!"});
					}
					notExecute=temp;
					$("#overseaPersonnelList").trigger("reloadGrid");
					disableButtons();
					checkExport();
				}
			});
		   }
		   });
		});

		$("#isLock").change(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});

		function deleteOverseaPersonnel(selectId){
			var selectedId = $("#overseaPersonnelList").getGridParam("selrow");
			if(selectedId==null){
		 		return;
			}
			$.ajax({
				url:PATH+"/baseinfo/overseaPersonnelManage/deleteOverseaPersonnel.action?",
				data:{
					"overseaPersonnel.id":selectedId
				},
				success:function(responseData){
					if (responseData){
						$("#overseaPersonnelList").delRowData(selectedId);
					    $.messageBox({message:"已经成功删除该"+title+"信息!"});
					    disableButtons();
					    checkExport();
					}else{
						$.messageBox({ message:"该人员信息已被别的模块所使用，不能删除!",level: "warn"	});
					}
				}
			});
		}

		$("#search").click(function(event){
			$("#overseaPersonnelMaintanceDialog").createDialog({
				width: 650,
				height: 320,
				title:title+'查询-请输入查询条件',
				url:PATH+'/baseinfo/overseaPersonnelManage/dispatch.action?mode=search',
				buttons: {
			   		"查询" : function(event){
			   			searchOverseaPersonnel();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});

		});


		$("#import").click(function(event){
			$("#overseaPersonnelMaintanceDialog").createDialog({
				width: 400,
				height: 230,
				title:'数据导入',
				url:PATH+'/common/import.jsp?isNew=1&dataType=overseaPersonnel&dialog=overseaPersonnelMaintanceDialog&startRow=6&templates='+dfop.templates+'&listName=overseaPersonnelList',
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
			if ($("#overseaPersonnelList").getGridParam("records")>0){
				$("#overseaPersonnelMaintanceDialog").createDialog({
					width: 250,
					height: 200,
					title:'导出'+title+'信息',
					url:PATH+'/common/exportExcel.jsp',
					postData:{
						gridName:'overseaPersonnelList',
						downloadUrl:PATH+'/baseinfo/overseaPersonnelSearch/downloadOverseaPersonnel.action'
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

		function searchOverseaPersonnel(){
			$("#overseaPersonnelList").setGridParam({
				url:PATH+"/baseinfo/overseaPersonnelSearch/searchOverseaPersonnel.action",
				datatype: "json",
				page:1,
				mtype:"post"
			});
			$("#overseaPersonnelList").setPostData($.extend({"searchOverseaPersonnelVo.logOut":$("#isLock").val(),"orgId":getCurrentOrgId()},$("#searchOverseaPersonnelForm").serializeObject()));
		    $("#overseaPersonnelList").trigger("reloadGrid");
		}

		function fastSearchfun(orgId){
			var condition = $("#searchByCondition").val();
			if(condition == '请输入英文名或证件号码') return;
			var initParam = {
					"orgId": orgId,
					"searchOverseaPersonnelVo.logOut":0,
					"searchOverseaPersonnelVo.fastSearchKeyWords":condition
				}
			$("#overseaPersonnelList").setGridParam({
				url:PATH+'/baseinfo/overseaPersonnelSearch/fastSearchOverseaPersonnel.action',
				datatype: "json",
				page:1
			});
			$("#overseaPersonnelList").setPostData(initParam);
			$("#overseaPersonnelList").trigger("reloadGrid");
		}


		function englishNameFont(el,options,rowData){
			if(null == rowData.englishName) {
				return "&nbsp;&nbsp;"
			}else if(rowData.death == true || rowData.death == "true"){
				return (dfop.hasViewOverseaPerson == 'true' ? "<a href='javascript:viewDialog(event,"+rowData.id+")'><font color='red'>"+rowData.englishName+"</font></a>": "<font color='red'>"+rowData.englishName+"</font></a>");
			}else if(rowData.logOut==1||rowData.logOut=='1'){
				return (dfop.hasViewOverseaPerson == 'true' ? "<a href='javascript:viewDialog(event,"+rowData.id+")'><font color='#778899'>"+rowData.englishName+"</font></a>": "<font color='#778899'>"+rowData.englishName+"</font></a>");
			}
			return (dfop.hasViewOverseaPerson == 'true' ? "<a href='javascript:viewDialog(event,"+rowData.id+")'><font color='#6633FF'>"+rowData.englishName+"</font></a>": "<font color='#6633FF'>"+rowData.englishName+"</font></a>");
		}

		$("#overseaPersonnelList").jqGridFunction({
			datatype: "local",
		   	colModel:[
		        {name:"id",index:"id",hidden:true,sortable:false,frozen:true},
		        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
		        {name:'organization.id',index:'organization.id',hidden:true,hidedlg:true,sortable:false,frozen:true},
		        {name:"操作",index:'id',width:90,formatter:operateFormatter,sortable:false,frozen:true,align:"center"},
		        {name:"englishName",index:'englishName',label:'英文名',width:80,formatter:englishNameFont,sortable:false,frozen:true},
		        {name:'name',label:'姓名',width:80,sortable:false,hidden:true},
		        {name:"gender",label:'性别',width:50,align:'center',sortable:false,formatter : genderFormatter },
		        {name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:150,sortable:false,hidden:true},
		        {name:'birthday', sortable:false, label:'出生日期',align:'center', width:90,hidden:true},
		        {name:'mobileNumber',label:'联系手机',index:"mobileNumber",align:'center',sortable:false, width:100,hidden:true},
		        {name:'telephone',label:'固定电话',sortable:false,align:'center', width:120,hidden:true},
		        {name:"nationality",label:'国籍',sortable:false,width:120},
		        {name:'certificateType',label:'证件种类',width:100,sortable:false,formatter : certificateTypeFormatter},
		        {name:'certificateNo',label:'证件号码',sortable:false,width:160,frozen:true},
		        {name:'currentAddress',label:'常住地址',sortable:false,width:200},
		        {name:'profession',label:'职业',sortable:false, width:80,hidden:true,align:'center',formatter : professionFormatter},
		        {name:'workUnit',label:'工作单位或就读学校',sortable:false, width:200,hidden:true},
		        {name:'arrivalTime',label:'抵达时间',sortable:false,align:'center', width:90,hidden:true},
	      		{name:'leaveTime',label:'离开时间',sortable:false,align:'center', width:90,hidden:true},
		        {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		        {name:'updateDate', sortable:false, label:'数据更新时间',align:'center', width:160},
		        {name:"logOut",sortable:false,hidden:true,hidedlg:true,width:100}
			],
		  	onSelectAll:function(aRowids,status){},
		    loadComplete: afterLoad,
		    multiselect:true,
		    ondblClickRow: function (rowid){
				if(dfop.hasViewOverseaPerson == 'true'){
					viewDialogs(rowid);
				}
			},
		    onSelectRow:selectRow
		});
		function selectRow(){
			var selectedCounts = getActualjqGridMultiSelectCount("overseaPersonnelList");
			var count = $("#overseaPersonnelList").jqGrid("getGridParam","records");
			if(selectedCounts == count){
				jqGridMultiSelectState("overseaPersonnelList", true);
			}else{
				jqGridMultiSelectState("overseaPersonnelList", false);
			}
		}

		if (getCurrentOrgId()!=null && getCurrentOrgId()!=""){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
		jQuery("#overseaPersonnelList").jqGrid('setFrozenColumns');

	   function getSelectedIds(){
			var selectedIds = $("#overseaPersonnelList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
		}
		function getSelectedIdLast(){
			var selectedId;
			var selectedIds = $("#overseaPersonnelList").jqGrid("getGridParam", "selarrrow");
			for(var i=0;i<selectedIds.length;i++){
				selectedId = selectedIds[i];
			}
			return selectedId;
		}
		//页面加载完成之后a,b,c
		function afterLoad(){
			disableButtons();
			checkExport();
			isEmphasisFormatter();
			changeColor();
		}

		function changeColor(){
			if(notExecute==null||notExecute.length==0){
				return;
			}
			for(var i=0;i<notExecute.length;i++){
				var rowData=$("#overseaPersonnelList").getRowData(notExecute[i]);
				$("#"+notExecute[i]).css('background','red');
				$("#overseaPersonnelList").setSelection(notExecute[i])
			}
			notExecute.splice(0,notExecute.length);
		}

		//c 给曾经关注的人特殊样式
		function isEmphasisFormatter(){
			var idCollection = new Array();
			idCollection=$("#overseaPersonnelList").getDataIDs();
				for(var i=0;i<idCollection.length;i++){
					var ent =  $("#overseaPersonnelList").getRowData(idCollection[i]);
					if(ent.logOut==1){
					$("#"+idCollection[i]+"").css('color','#778899');
				}
			}
		}
		
		
		
		$("#transfer").click(function(e){
			//获取需要转移的id
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行转移！"});
				 return;
			}
			for(var i=0;i<allValue.length;i++){
				var rowData_Popu=$("#overseaPersonnelList").getRowData(allValue[i]);
				if(rowData_Popu.logOut==1){
					$.messageBox({level:'warn',message:"所选记录中存在已注销记录，无法转移！"});
					 return;
				}
			}
		    var id=	allValue[0];
			//get current orgid
		    var orgid=	getCurrentOrgId();
			if(orgid==""||orgid==null){
				$.messageBox({level:'warn',message:"没有获取到当前的组织机构id"});
				 return;
			}
			moveOperator(e,allValue,orgid);
		});

		function moveOperator(event,allValue,orgid){
			var encryptIds=deleteOperatorByEncrypt("overseaPersonnel",allValue,"encryptId");
			var allOrgId = getOrgIdsByIds("overseaPersonnel",allValue,"organization.id");
			$("#moveDataDialog").createDialog({
				width: 400,
				height: 230,
				title:"数据转移",
				url:PATH+"/transferManage/transferDispatchByEncryptId.action?orgId="+orgid+"&ids="+encryptIds+"&type=overseaPersonnel&orgIds="+allOrgId,
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
		
		
		
		
		
	});

	function viewDialogs(rowid){
		if(null == rowid){
	 		return;
		}
		var overseaPersonnel =  $("#overseaPersonnelList").getRowData(rowid);
		$("#overseaPersonnelMaintanceDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'查看'+title+'信息',
			url:PATH+'/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?mode=view&isHiddenPersonnelTrack=1&id='+overseaPersonnel.encryptId,
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
}