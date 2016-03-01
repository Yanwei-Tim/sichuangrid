TQ.rentalHouseList = function (dfop){
	function isSignGuarantee(el, options, rowData){
		if(1==rowData.isSignGuarantee){
			return "是";
		}else{
			return "否";
		}
	}
	function isEmphasis(el, options, rowData){
		if(1==rowData.isEmphasis){
			return "是";
		}else{
			return "否";
		}
	}
	var hiddenDangerLevelColor = function(el,options,rowData){
		var displayName=hiddenDangerLevelFormatter(el,options,rowData);
		if(displayName=='undefined'||displayName=='')
			return '';
		else if(displayName=='严重')
			return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
		else if(displayName=='一般')
			return '<span>一般：<span style="color:#ffcc00;">███</span></span>';
		else if(displayName=='安全')
			return '<span>安全：<span style="color:#99cc00;">█</span></span>';
		else
			return '';
	}
	function removeLoad(){
		$(".click_load .click_btn").hide();
	}
	function onOrgChanged(orgId,isGrid){
		currentOrgId=orgId;
		$("#rentalHouseList").setGridParam({
			url:PATH+'/baseinfo/rentalHouseManage/searchHouseInfo.action',
			datatype: "json",
			page:1
		});
		$("#rentalHouseList").setPostData({
			"orgId":function(){return currentOrgId;},
			"searchHouseInfoVo.isEmphasis" : 0
		});
		$("#rentalHouseList").trigger("reloadGrid");
	}
	function getPostDataForList(){
		var condition = null;
		if("请输入房屋准确地址、编号或房主姓名" != $("#searchByCondition").val()) {
			condition = $("#searchByCondition").val();
		}
		var initParam = {
			"orgId": getCurrentOrgId(),
			"searchHouseInfoVo.usage.id" : $("#usageId").val(),
			"searchHouseInfoVo.isEmphasis":0
		}
		initParam = $.extend(initParam,{"searchHouseInfoVo.houseCodeAndAddress":condition});
		return initParam;
	}
	
	$(document).ready(function(){
		housePeopleProportion();
		getBuildingUses();
		$("#usageId").change(function(){
			$("#rentalHouseList").setGridParam({
				url:PATH+'/baseinfo/rentalHouseManage/searchHouseInfo.action',
				datatype: "json",
				page:1
			});
			$("#rentalHouseList").setPostData(getPostDataForList());
			$("#rentalHouseList").trigger("reloadGrid");
		});
		$("#isEmphas").change(function(){
			$("#rentalHouseList").setGridParam({
				url:PATH+'/baseinfo/rentalHouseManage/searchHouseInfo.action',
				datatype: "json",
				page:1
			});
			$("#rentalHouseList").setPostData(getPostDataForList());
			$("#rentalHouseList").trigger("reloadGrid");
		});
		function isHasPeopleFormatter(el,options,rowData){
			if(undefined != rowData.buildingUses && buildingUsesPropertyId == rowData.buildingUses.id){
			if(rowData.memberNum <= 0){
				return "<img src='"+PATH+"/resource/system/images/house_leisure_state_red.png'></img>";
			}else{
				return "<img src='"+PATH+"/resource/system/images/house_leisure_state_green.png'></img>";
			}
			
			}else{
				
			return '';
		   }
		}
		function operatorFormatter(el,options,rowData){
			return (dfop.hasUpdateRentalHouse == 'true' ? "<a href='javascript:updateOperator(event,"+rowData.id+")'><span>修改</span></a>"+
					   "&nbsp;|&nbsp;" : '' )+
					   (dfop.hasDeleteRentalHouse == 'true' ? "<a href='javascript:deleteOperator(event,"+rowData.id+")'><span>删除</span></a>" : '');
		}
		function houseCodeFont(el,options,rowData){
			if(rowData.isEmphasis==true||rowData.isEmphasis=='true'){
				return (dfop.hasViewRentalHouse == 'true' ? "<a href='javascript:viewRentalHouse("+rowData.id+")'><font color='#778899'>"+rowData.houseCode+"</font></a>": "<font color='#778899'>"+rowData.houseCode+"</font>");
			}
			return (dfop.hasViewRentalHouse == 'true' ? "<a href='javascript:viewRentalHouse("+rowData.id+")'>"+rowData.houseCode+"</a>": rowData.houseCode);
		}
		function addressFormatter(el,options,rowData){
			if(rowData.isEmphasis== 1 || rowData.isEmphasis== '1'){
				return (dfop.hasViewRentalHouse == 'true' ? "<a href='javascript:viewRentalHouse("+rowData.id+")'><font color='#778899'>"+rowData.address+"</font></a>": "<font color='#778899'>"+rowData.address+"</font>");
			}else if(rowData.address!=null && rowData.address!="" && typeof(rowData.address)!="undefined"  && rowData.address!=" "){
				return (dfop.hasViewRentalHouse == 'true' ? "<a href='javascript:viewRentalHouse("+rowData.id+")'>"+rowData.address+"</a>": rowData.address);
			}else {
				return "";
			}
		}
		$("#rentalHouseList").jqGridFunction({
			//subGridPostData:function(data){return {"houseInfo.houseCode":data.houseCode,orgId:data["organization.id"]}},
			//subGridUrl:"${path}/baseinfo/actualHousePopulation/findLivingInHouseTotalInfos.action",
			datatype: "local",
			//colNames:['id','organization.id', '操作','隐患等级','房屋编号','房屋编号','房屋准确地址','出租人（单位）','租赁备案证号','出租用途','数据更新时间','是否有治安负责人','所属网格','是否注销'],
			colModel:[
		        {name:"id",index:"id",sortable:false,hidden:false,frozen:true},
		        {name:"isEmphasis",index:"isEmphasis",sortable:false,hidden:true,frozen:true,hidedlg:true},
		        {name:"houseId",index:"houseId",sortable:false,hidden:true,frozen:true,hidedlg:true},
		        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		        {name:"houseInfoEncryptId",index:"houseInfoEncryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		        {name:"organization.id",index:"organization.id",sortable:false,hidden:true,frozen:true,hidedlg:true},
		        {name:"isHasPeople", index:'id', label:'空闲状态',formatter:isHasPeopleFormatter,sortable:false,width:80,align:'center'},
		        {name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
		        {name:'hiddenDangerLevel',index:"hiddenDangerLevel",label:'隐患等级',width:90,sortable:false,formatter :hiddenDangerLevelColor},
		        //spt{name:'houseCode-fromatter',index:'houseCode',label:'房屋编号',sortable:false,formatter:houseCodeFont,width:120,frozen:true},
		        //{name:'houseCode',index:'houseCode',label:'房屋编号',width:50,hidden:true,sortable:false,frozen:true},
		        //{name:'houseId',index:'houseId',label:'houseId',width:50,hidden:true,sortable:false,frozen:true},
		        {name:'address',index:'address',label:'房屋准确地址',formatter:addressFormatter,sortable:false,width:200},
		        {name:'rentalPerson',index:'rentalPerson',label:'出租人（单位）',width:100,sortable:false},
		        {name:'houseFileNum',index:'houseFileNum',label:'租赁备案证号',width:100,frozen:true,sortable:false,hidden:true},
		        {name:'memberNum',index:'memberNum',label:'居住人数',sortable:false,hidden:false,formatter:memberNumFormatter,width:80,align:'center'},
		        {name:'usage', index:'usage', label:'出租用途',width:100, sortable:false,formatter:usageFormatter},
		        
		        {name:'hasServiceTeamMember',label:'有无治安负责人',index:'hasServiceTeamMember',width:100,sortable:false,align:'center',formatter:hasServiceTeamMemberFormatter},
				{name:'lastRecordTime',label:'最新巡场时间',index:'hasServiceTeamRecord',sortable:false,align:'center',width:100},
				{name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		        {name:'updateDate', label:'数据更新时间',sortable:false,width:200,align:'center'},
		        {name:'isSignGuarantee',index:'isSignGuarantee', label:'是否签治安责任保证书',formatter:isSignGuarantee,hidden:true,sortable:false,width:130,align:"center"},
	            {name:'organization.orgName',index:'organization.orgName',label:'所属网格',sortable:false,width:200,hidden:true},
	            {name:"isEmphasisName",index:"isEmphasis",label:'是否注销',hidden:true,formatter:isEmphasis},
	            
	            {name:"houseOwnerIdCardNo",index:"houseOwnerIdCardNo",label:'身份证号码',hidden:true},
	            {name:"roomNumber",index:"roomNumber",label:'租用间数',hidden:false},
	            {name:"limitPersons",index:"limitPersons",label:'限住人数',hidden:false},
	            {name:"registDate",index:"registDate",label:'登记日期',hidden:true,formatter:'date',formatoptions:{newformat: 'Y-m-d'},align:'center'},
	            {name:"rentalMobileNumber",index:"rentalMobileNumber",label:'联系手机',hidden:true},
	            {name:"rentalTelephone",index:"rentalTelephone",label:'联系电话',width:100,hidden:true},
	            {name:"hiddenRectification",index:"hiddenRectification",label:'隐患情况',hidden:false}
			],
			ondblClickRow:function (rowid){
				if(dfop.hasViewRentalHouse=='true'){
					ondblClickRow:viewRentalHouse(rowid);
				}
			},
			loadComplete: afterLoad,
			onSelectAll:function(aRowids,status){},
			multiselect:true,
			onSelectRow:function(){setHouseInfoOperateButton();}
		});
		$("#rentalHouseList").jqGrid('setFrozenColumns');
		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			//onOrgChanged(getCurrentOrgId(),isGrid());
		}
		$("#isLock").change(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});

		$("#mantanceRentalHousePopulation").click(function(event){
			var selectedIds = $("#rentalHouseList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length>1){
				$.messageBox({level:"warn",message:"只能选择一条记录进行操作！"});
				return;
			}
			if(selectedIds==null || selectedIds.length<1){
				$.messageBox({level:"warn",message:"请选择一条记录再进行操作！"});
				return;
			}
			var selectedId = selectedIds[0];
			var houseInfo =  $("#rentalHouseList").getRowData(selectedId);
			if(houseInfo.isEmphasis == 1){
				$.messageBox({level:'warn',message:"该条数据已注销!"});
				return;
			}
			$("#rentalHouseMaintanceDialog").createDialog({
				width: 840,
				height: 600,
				title:'维护使用人信息',
				url:PATH+'/baseinfo/actualHousePopulation/prepairMantanceLivingPopulation.action?houseInfo.id='+houseInfo.houseInfoEncryptId,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				},
				close:function(){
					$("#rentalHouseList").trigger("reloadGrid");
				}
			});
		});

		$("#addRentalHouse").click(function(event){
			if (!isGrid()){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
				return;
			}
			if (currentOrgId==null||currentOrgId==""){
				$.notice({level:'warn',
						message:'请先选择一个部门'});
			}else{
				$("#rentalHouseMaintanceDialog").createTabDialog(
					{
						model :"add",
						title:"新增出租房",
						width: 800,
						height: 600,
						postData:{
							operateSource:'rentalHouse'
						},
						tabs:[
							{title:'房屋信息',url:PATH+'/baseinfo/actualHouseManage/prepareDispatch.action?dailogName=rentalHouseMaintanceDialog&mode=add&orgId='+currentOrgId},
							{title:'出租房',url:PATH+'/baseinfo/rentalHouseManage/prepareDispatch.action?dailogName=rentalHouseMaintanceDialog&mode=add&orgId='+currentOrgId}
						],
						close : function(){
						$("#rentalHouseList").trigger("reloadGrid");
						}
					}
				);
			}
		});

		$("#updateRentalHouse").click(function(event){
			var selectedId =  getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
			updateOperator(event,selectedId);
		});

		$("#importRentalHouse").click(function(event){
			$("#noticeDialog").createDialog({
				width: 400,
				height: 230,
				title:'数据导入',
				url:PATH+'/common/import.jsp?isNew=1&dataType=rentalHouseData&dialog=noticeDialog&startRow=6&templates=RENTALHOUSE&listName=rentalHouseList',
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

		$("#searchRentalHouse").click(function(event){
			$("#rentalHouseDialog").createDialog({
				width: 650,
				height: 420,
				datatype: "json",
				title:'出租房查询-请输入查询条件',
				url:PATH+'/baseinfo/rentalHouseManage/dispatchOperate.action?mode=search&orgId='+getCurrentOrgId(),
				buttons: {
					"查询" : function(event){
					searchRentalHouseInfo();
		   				$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		function searchRentalHouseInfo() {
			$("#rentalHouseList").setGridParam({
				url:PATH+'/baseinfo/rentalHouseManage/searchHouseInfo.action',
				datatype: "json",
				mtype:"post",
				page:1
			});
			var postData=$.extend({"searchHouseInfoVo.organization.id":getCurrentOrgId(),orgId:getCurrentOrgId()},$("#searchRentalHouseForm").serializeObject());
			if($("#usageId").val()!=""){
				postData = $.extend(postData,{"searchHouseInfoVo.usage.id" : $("#usageId").val()});
			}
			if($("#isEmphas").val()!=null) {
				postData = $.extend(postData,{"searchHouseInfoVo.isEmphasis" : $("#isEmphas").val()});
			}
			$("#rentalHouseList").setPostData(postData);
			$("#rentalHouseList").trigger("reloadGrid");
		}

		$("#deleteRentalHouse").click(function(event){
			var allValue = setCompVal();
			if(allValue.length ==0){
				 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			deleteOperator(event,allValue);
		});

		$("#viewRentalHouse").click(function(event){
			var selectedId =  getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
			viewRentalHouse(selectedId);
		});

		$("#isEmphasisRentalHouse").click(function(){
			var allValue = setCompVal();
			var isEmphasis=new Array();
			var temp=new Array();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行注销！"});
				return;
			}
			for(var i=0;i<allValue.length;i++){
				var row=$("#rentalHouseList").getRowData(allValue[i]);
				if(row.isEmphasis==false||row.isEmphasis=="false"){
					isEmphasis.push(allValue[i]);
					}else{
						temp.push(allValue[i]);
						}
				}
			allValue=isEmphasis;
			if(allValue==null||allValue.length==0){
				$.messageBox({level:'warn',message:"没有可注销的数据"});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("rentalHouse",allValue,"encryptId");
			$.confirm({
				title:"确认注销",
				message:"确定要注销吗?",
				okFunc: function(){
					$.ajax({
						url:PATH+"/baseinfo/rentalHouseManage/updateEmphasiseByEncryptId.action?houseInfo.isEmphasis=1&houseIds="+encryptIds,
						success:function(datas){
							for(var i = 0;i<datas.length;i++){
								var responseData = datas[i];
								if(($("#isEmphas").val()=='false')){
					 				$("#rentalHouseList").delRowData(responseData.id);
						 		}else{
					 				$("#rentalHouseList").setRowData(responseData.id,responseData);
					 				$("#"+responseData.id+"").css('color','#778899');
					 				$("#rentalHouseList").setSelection(responseData.id);
				 				}
							}
							if(null==temp || temp.length==0){
								$.messageBox({message:"已经成功注销该出租房信息!"});
							}else{
							    $.messageBox({level:'warn',message:"除选中的红色数据外,已经成功注销该出租房信息!"});
							}
							notExecute=temp;
							$("#rentalHouseList").trigger("reloadGrid");
					    }
				    });
			   }
		 });
	});
		$("#cancelIsEmphasis").click(function(){
			var allValue = setCompVal();
			var cancelIsEmphasis=new Array();
			var temp=new Array();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再取消注销！"});
				return;
			}
			for(var i=0;i<allValue.length;i++){
				var row=$("#rentalHouseList").getRowData(allValue[i]);
				if(row.isEmphasis==true||row.isEmphasis=="true"){
					cancelIsEmphasis.push(allValue[i]);
					}else{
						temp.push(allValue[i]);
						}
				}
			allValue=cancelIsEmphasis;
			if(allValue==null||allValue.length==0){
				$.messageBox({level:'warn',message:"没有可取消注销的数据"});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("rentalHouse",allValue,"encryptId");
			$.confirm({
				title:"确认取消注销",
				message:"确定要取消注销吗?",
				okFunc: function(){
					$.ajax({
						url:PATH+"/baseinfo/rentalHouseManage/updateEmphasiseByEncryptId.action?houseInfo.isEmphasis=0&houseIds="+encryptIds,
						success:function(datas){
							for(var i = 0;i<datas.length;i++){
								var responseData = datas[i];
								if(($("#isEmphas").val()=='false')){
					 				$("#rentalHouseList").delRowData(responseData.id);
						 		}else{
					 				$("#rentalHouseList").setRowData(responseData.id,responseData);
					 				$("#"+responseData.id+"").css('color','#778899');
					 				$("#rentalHouseList").setSelection(responseData.id);
				 				}
							}
							if(null==temp || temp.length==0){
							    $.messageBox({message:"已经成功取消注销该出租房信息!"});
							}else{
							    $.messageBox({level:'warn',message:"除选中的红色数据外,已经成功取消注销该出租房信息!"});
							}
							notExecute=temp;
							$("#rentalHouseList").trigger("reloadGrid");
					    }
				    });
			   }
		 });
		});
		$("#contentDiv").click( function () { 
			removeLoad();
		}); 
		
		$("#reload").click(function(event){
			$("#conditionMark").val("fast_hosueCode");
			$("#searchByCondition").attr("value","请输入房屋准确地址、编号或房主姓名");
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		
		$(".click_load .click_btn").click(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
			$(this).hide(100);
		});
		$("#searchHouseByConditionButton").click(search);
		function search(){
			var condition = $("#searchByCondition").val();
			if(condition == '请输入房屋准确地址、编号或房主姓名') return;
			$("#rentalHouseList").setGridParam({
				url:PATH+'/baseinfo/rentalHouseManage/searchHouseInfo.action',
				datatype: "json",
				page:1
			});
			$("#rentalHouseList").setPostData(getPostDataForList());
			$("#rentalHouseList").trigger("reloadGrid");
		}

		$("#exportRentalHouse").click(function(event){
	        if ($("#rentalHouseList").getGridParam("records")>0){
	            $("#rentalHouseMaintanceDialog").createDialog({
	                width: 250,
	                height: 200,
	                title:'导出出租房信息',
	                url:PATH+'/common/exportExcel.jsp',
	                postData:{
	                    gridName:'rentalHouseList',
	                    orgId:getCurrentOrgId(),
	                    downloadUrl:PATH+'/baseinfo/rentalHouseManage/downloadRentalHouse.action'
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
		
		$("#superviseServiceTeamMember").click(function(event){
			var selectedId = $("#rentalHouseList").jqGrid("getGridParam", "selarrrow");
			if(selectedId.length==0){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行操作！"});
				return;
			}else if(selectedId.length>1){
				$.messageBox({level:'warn',message:"请选择单条记录进行操作！"});
				return;
			}
			var rowData = $("#rentalHouseList").getRowData(selectedId);
			if(rowData.isEmphasis == 1){
				$.messageBox({level:'warn',message:"该条数据已注销!"});
				return;
			}
			var houseAddress = $(rowData.address).text();
			$("#rentalHouseMaintanceDialog").createDialog({
				width:900,
				height:560,
				title:'出租房治安负责人管理',
				url:'/plugin/serviceTeam/router/routerManage/maintainServiceMemberForHouse.action?dailogName=rentalHouseMaintanceDialog&populationType=RENTALHOUSE&name='+houseAddress+'&id='+selectedId,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				},
				close: function(event, ui) {$("#rentalHouseList").trigger("reloadGrid");}
			});
		});
		
		$("#superviseRecord").click(function(event){
			var selectedId = $("#rentalHouseList").jqGrid("getGridParam", "selarrrow");
			if(selectedId.length==0){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行操作！"});
				return;
			}else if(selectedId.length>1){
				$.messageBox({level:'warn',message:"请选择单条记录进行操作！"});
				return;
			}
			var rowData = $("#rentalHouseList").getRowData(selectedId);
			if(rowData.isEmphasis == 1){
				$.messageBox({level:'warn',message:"该条数据已注销!"});
				return;
			}
			var houseAddress = $(rowData.address).text();
			$("#rentalHouseMaintanceDialog").createDialog({
				width:860,
				height:600,
				title:'出租房巡场情况管理',
				url:'/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?dailogName=rentalHouseMaintanceDialog&populationType=RENTALHOUSE&name='+encodeURIComponent(houseAddress)+'&id='+selectedId+'&showRecordType=1',
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				},
				close: function(event, ui) {$("#rentalHouseList").trigger("reloadGrid");}
			});
		});
		function memberNumFormatter(el,options,rowData){
			return "<a href='javascript:viewActualHousePopulation("+rowData.id+")'>"+rowData.memberNum+"</a>";
		}
		$.pulldownFun({
	        on:"#percentBtn",
	        dialog:"#persetDown",
	        onHover:"onHover"
	    });
		
		$("#transfer").click(function(event){
			var selectedIds = $("#rentalHouseList").jqGrid("getGridParam", "selarrrow");
//	 		if($("#shift").attr("disabled")){
//	 			return ;
//	 		}
//	 		var selectedId = getSelectedIdLast();
			/* if (!isGrid()){
				$.messageBox({level:'warn',message:"请先选择网格级别组织机构进行转移！"});
				return;
			} */
			if(selectedIds==null || selectedIds.length==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行转移！"});
				return;
			}
			for(var i=0;i<selectedIds.length;i++){
			var rowData_Popu=$("#rentalHouseList").getRowData(selectedIds[i]);
			if(rowData_Popu.isEmphasis==1){
				$.messageBox({level:'warn',message:"所选记录中存在已取消关注（或已注销、死亡）记录，无法转移！"});
				 return;
			}
			}
			$.confirm({
				title:"转移"+moduleCName,
				message:"转移"+moduleCName+"时,相关联的人员房屋关系将解除，确认转移？",
				okFunc: function() {
					moveHouse(event,selectedIds);
				}
			});
			
		});
		
	});

	function moveHouse(event,selectedIds){
		$("#moveDataDialog").createDialog({
			width: 500,
			height: 300,
			title:"转移"+moduleCName+"信息",
			url:PATH+'/baseinfo/rentalHouseManage/shiftDispatch.action?orgId='+getCurrentOrgId()+"&houseIds="+selectedIds+"&type=rentalHouse",
			buttons: {
				"保存" : function(event){
			      $("#maintainShiftForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function afterLoad(){
		setHouseInfoOperateButton();
		isEmphasisFormatter();
		changeColor();
	}
	function changeColor(){
		if(notExecute==null||notExecute.length==0){
			return;
		}
		for(var i=0;i<notExecute.length;i++){
			var rowData=$("#rentalHouseList").getRowData(notExecute[i]);
				//"<a href='javascript:viewFloatingPopulationInfo("+rowData.id+")'><font color='red'>"+rowData.name+"</font></a>";
				//"<a href='javascript:viewFloatingPopulationInfo("+rowData.id+")'><font color='red'>"+rowData.idCardNo+"</font></a>";
			$("#"+notExecute[i]).css('background','red');
			//$("#"+notExecute[i]+"a").css('color','red');
			$("#rentalHouseList").setSelection(notExecute[i])
		}
		notExecute.splice(0,notExecute.length);
	}
	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#rentalHouseList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#rentalHouseList").getRowData(idCollection[i]);
			if(ent.isEmphasis=="true"||ent.isEmphasis==true){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}
	function setHouseInfoOperateButton(){
		var selectedCounts = getActualjqGridMultiSelectCount("rentalHouseList");
		var count = $("#rentalHouseList").jqGrid("getGridParam","records");
		if(selectedCounts == count && count > 0){
			jqGridMultiSelectState("rentalHouseList", true);
		}else{
			jqGridMultiSelectState("rentalHouseList", false);
		}
	}
	/*
	function printRentalHouse(rowid){
		$("#rentalHouseMaintanceDialog").createDialog({
			width: 790,
			height: dialogHeight,
			title: '打印出租房信息',
			modal: true,
			url: '${path}/baseinfo/rentalHouseManage/dispatch.action?mode=print&houseInfo.id='+rowid,
			buttons: {
				"确定" : function(event){
					$("#houseInfoPrint").printArea();
					$(this).dialog("close");
				 },
		  		 "关闭" : function(){
		       		 $(this).dialog("close");
		  		 }
			}
		});
	}*/
	function setCompVal(){
		var selectedIds = $("#rentalHouseList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#rentalHouseList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	$("#refreshSearchKey").click(function(){
		$("#searchByCondition").attr("value","请输入房屋准确地址、编号或房主姓名");
	});

}

function viewRentalHouse(rowid){
	if(rowid==null){
 		return;
	}
	var houseInfo = $("#rentalHouseList").getRowData(rowid);
	$("#rentalHouseMaintanceDialog").createDialog({
		width: 800,
		height: 590,
		title:'查看出租房信息',
		modal:true,
		url:PATH+'/baseinfo/houseInfo/rentalhouse/viewRentalHouseDlg.jsp?mode=view&houseId='+houseInfo.encryptId+'&houseInfoId='+houseInfo.houseId,
		buttons: {
		   "打印" : function(){
				printChoose(rowid);
	  	   },
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

