TQ.actualHouseList = function (dfop){
	function getPostDataForList(){
		var condition = $("#searchByCondition").val();
		var initParam = {
			"orgId": getCurrentOrgId(),
			"searchHouseInfoVo.houseUses.id" : $("#houseUsesId").val()
		}
		if($("#houseIsRentalHouse").val()!=""){
			initParam = $.extend(initParam,{"searchHouseInfoVo.isRentalHouse" : $("#houseIsRentalHouse").val()});
		}
		if(condition != '请输入房屋编号或房屋准确地址'){
			initParam = $.extend(initParam,{"searchHouseInfoVo.houseCodeAndAddress":condition,"searchHouseInfoVo.isFastSearch":true});
		}
		return initParam;
	}
	function removeLoad(){
		$(".click_load .click_btn").hide();
	}
	
	
	$(document).ready(function(){
		housePeopleProportion();
		getBuildingUses();
		$("#deleteMongodb").click(function(event){
			$.ajax({
        		async: true ,
        		url:PATH+'/baseinfo/actualHouseManage/deleteMongodb.action'
        	});
			$.messageBox({level:'warn',message:"已经触发删除！"});
		});
		$("#moveToMongodb").click(function(event){
			$.ajax({
        		async: true ,
        		url:PATH+'/baseinfo/actualHouseManage/moveToMongodb.action'
        	});
			$.messageBox({level:'warn',message:"已经触发导入！"});
		});
		$("#contentDiv").click( function () { 
			removeLoad();
		}); 
		loadList();
		$("#searchByConditionButton").click(function(event){
			fastSearchFun();
		});
		$("#refreshSearchKey").click(function(){
			$("#searchByCondition").attr("value","请输入房屋编号或房屋准确地址");
		});
		$("#searchActualHouse").click(function(event){
			searchFun();
		});
		$("#addActualHouse").click(function(event){
			addFun();
		});
		$("#combineHouse").click(function(event){
			combineHouseFun();
		});
		$("#deleteActualHouse").click(function(){
			deleteFun();
		});
		$("#changeHouseCode").click(function(){
			changeHouseCodeFun();
		});
		$("#importActualHouse").click(function(event){
			importFun();
		});
		$("#exportActualHouse").click(function(event){
	        exportFun();
	    });
		$("#reload").click(function(event){
			reloadFun();
		});
		$(".click_load .click_btn").click(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
			$(this).hide(100);
		});
		$("#mantanceLivinginHousePopulation").click(function(event){
			maintainFun();
		});
		$("#updateActualHouse").click(function(event){
			updateFun();
		});
		$("#houseUsesId,#houseIsRentalHouse").change(function(){
			$("#actualHouseList").setGridParam({
				url:PATH+'/baseinfo/actualHouseManage/searchHouseInfo.action',
				datatype: "json",
				page:1
			});
			$("#actualHouseList").setPostData(getPostDataForList());
			$("#actualHouseList").trigger("reloadGrid");
		});
		$("#transfer").click(function(event){
			var selectedIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length == 0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行转移！"});
				return;
			}
			for(var i=0;i<selectedIds.length;i++){
				var rowData_Popu=$("#actualHouseList").getRowData(selectedIds[i]);
				if(rowData_Popu.isEmphasis==1){
					$.messageBox({level:'warn',message:"所选记录中存在已取消关注（或已注销、死亡）记录，无法转移！"});
					 return;
				}
			}
			$.confirm({
				title:"转移"+dfop.moduleCName,
				message:"转移"+dfop.moduleCName+"时,相关联的人员房屋关系将解除，确认转移？",
				okFunc: function() {
					moveHouse(event,selectedIds);
				}
			});
		});
		$.pulldownFun({
	        on:"#percentBtn",
	        dialog:"#persetDown",
	        onHover:"onHover"
	    });
		function moveHouse(event,selectedIds){
			var encryptIds=deleteOperatorByEncrypt("actualHouse",selectedIds,"encryptId");
			var allOrgId = getOrgIdsByIds("actualHouse",selectedIds,"organization.id");
			$("#moveDataDialog").createDialog({
				width: 500,
				height: 300,
				title:"转移"+dfop.moduleCName+"信息",
				url:PATH+'/baseinfo/actualHouseManage/shiftDispatchByEncryptId.action?orgId='+getCurrentOrgId()+"&houseIds="+encryptIds+"&type=actualHouse&orgIds="+allOrgId,
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
		
		function loadList(){
			$("#actualHouseList").jqGridFunction({
				datatype: "local",
			   	colModel:[
			        {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
			        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			        {name:"organization.id",index:"organization.id",sortable:false,hidden:true,hidedlg:true,frozen:true},
			        {name:"isHasPeople", index:'id', label:'空闲状态',formatter:isHasPeopleFormatter,sortable:false,width:80,align:'center'},
			        {name:"operator", index:'id', label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
			        {name:'address',index:'address',label:'房屋准确地址',formatter:addressFormatter,sortable:false,width:200},
			        {name:'name',index:'name',label:'产权人姓名',width:80,sortable:false},
			        {name:'certificateNumbe',index:'certificateNumbe',label:'产权人证件号码',sortable:false,width:200},
			        {name:'memberNum',index:'memberNum',label:'居住人数',sortable:false,formatter:memberNumFormatter,width:80,align:'center'},
			        {name:'houseSource', index:'houseSource',label:'房屋来源',sortable:false, width:80,hidden:true, formatter:houseSourceFormatter},
			        {name:"houseUses",index:'houseUses',label:'房屋用途',sortable:false, width:100,formatter:houseUsesFormatter},
			        {name:'isRentalHouse',index:'isRentalHouse',label:'是否出租房',sortable:false,width:100,align:"center",formatter:isRentalhouseFormatter,unformat:isRentalHouseUnFormatter},
			        {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
			        {name:'updateDate', label:'数据更新时间', width:150,sortable:false,align:"center"},
			        {name:'houseArea',index:'houseArea',label:'住房面积(㎡)',hidden:true,sortable:false,width:100},
			        {name:'houseStructure',index:'houseStructure',label:'住房结构',hidden:true,sortable:false,formatter:houseStructureFormatter,width:110},
			        {name:'houseOwner',index:'houseOwner',label:'代表人/业主',hidden:true,width:120,sortable:false,frozen:true},
		            {name:'organization.orgName',index:'organization.orgName',label:'所属网格',hidden:true,sortable:false,width:230}

		            /*
		            {name:'buildingName',index:'buildingName',label:'建筑物名称',width:80,sortable:false,hidden:true},
		            {name:'buildingUses',index:'buildingUses',label:'建筑物用途 ',formatter:buildingUsesFormatter,width:80,sortable:false,hidden:true},
		            {name:'propertyName',index:'propertyName',label:'物业管理单位名称 ',width:80,sortable:false,hidden:true},
		            {name:'houseOwner',index:'houseOwner',label:'代表人/业主 ',width:80,sortable:false,hidden:true},
		            {name:'houseOwnerIdCardNo',index:'houseOwnerIdCardNo',label:'业主身份证号码 ',width:80,sortable:false,hidden:true},
		            {name:'telephone',index:'telephone',label:'业主联系电话 ',width:80,sortable:false,hidden:true},
		            {name:'mobileNumber',index:'mobileNumber',label:'业主联系手机',width:80,sortable:false,hidden:true},
		            {name:'houseDoorModel',index:'houseDoorModel',label:'房屋户型',width:80,sortable:false,hidden:true},
		            {name:'builtYear',index:'builtYear',label:'建成年份 ',width:80,sortable:false,hidden:true},
		            {name:'houseArea',index:'houseArea',label:'住房面积 ',width:80,sortable:false,hidden:true},
		            {name:'ownProperty',index:'ownProperty',label:'自有产权',width:80,sortable:false,hidden:true,formatter:ownPropertyFormatter},
		            {name:'rentalBuildings',index:'rentalBuildings',label:'租赁公房 ',width:80,sortable:false,hidden:true,formatter:rentalBuildingsFormatter},
		            {name:'housingVouchers',index:'housingVouchers',label:'房屋凭证',width:80,sortable:false,hidden:true,formatter:housingVouchersFormatter},
		            {name:'houseRightNumber',index:'houseRightNumber',label:'房屋权证号 ',width:80,sortable:false,hidden:true},
		            {name:'houseRightNumberDate',index:'houseRightNumberDate',label:'房屋权证发证时间 ',width:80,sortable:false,hidden:true},
		            {name:'landDocuments',index:'landDocuments',label:'土地凭证',width:80,sortable:false,hidden:true,formatter:landDocumentsFormatter},
		            {name:'landRightsNumbe',index:'landRightsNumbe',label:'土地权证号 ',width:80,sortable:false,hidden:true},
		            {name:'landRightsNumbeDate',index:'landRightsNumbeDate',label:'土地权证发证时间 ',width:80,sortable:false,hidden:true},
		            {name:'propertyTypes',index:'propertyTypes',label:'产权人类型',width:80,sortable:false,hidden:true,formatter:propertyTypesFormatter},
		            {name:'certificateType',index:'certificateType',label:'产权人证件类型 ',width:80,sortable:false,hidden:true,formatter:certificateTypeFormatter},
		            {name:'propertyPersonTel',index:'propertyPersonTel',label:'产权人联系电话',width:80,sortable:false,hidden:true},
		            {name:'propertyPersonMobile',index:'propertyPersonMobile',label:'产权人联系手机 ',width:80,sortable:false,hidden:true},
		            {name:'remark',index:'remark',label:'备注 ',width:80,sortable:false,hidden:true},
		            {name:'createUser',index:'createUser',label:'创建用户',width:80,sortable:false,hidden:true},
		            {name:'createDate',index:'createDate',label:'创建日期 ',width:80,sortable:false,hidden:true}
		             */
		            
		            
		            
				],
				ondblClickRow:function (rowid){
					if(dfop.hasViewActualHouse=='true'){
						viewActualHouse(rowid);
					}
				},
				loadComplete: afterLoad,
				onSelectAll:function(aRowids,status){ },
				multiselect:true,
				onSelectRow:function(){setHouseInfoOperateButton();}
			});
			if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
				//onOrgChanged(getCurrentOrgId(),isGrid());
			}
		}
		function fastSearchFun(){
			var condition = $("#searchByCondition").val();
			if(condition == '请输入房屋编号或房屋准确地址') return;
			$("#actualHouseList").setGridParam({
				url:PATH+'/baseinfo/actualHouseManage/searchHouseInfo.action',
				datatype: "json",
				page:1
			});
			$("#actualHouseList").setPostData(getPostDataForList());
			$("#actualHouseList").trigger("reloadGrid");
		}
		function searchFun(){
			$("#actualHouseDialog").createDialog({
				width: 650,
				height: 420,
				datatype: "json",
				title:'房屋信息查询-请输入查询条件',
				url:PATH+'/baseinfo/actualHouseManage/dispatchOperate.action?mode=search&orgId='+getCurrentOrgId(),
				buttons: {
					"查询" : function(event){
					searchActualHouseInfo();
		   				$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
		function searchActualHouseInfo() {
			$("#actualHouseList").setGridParam({
				url:PATH+'/baseinfo/actualHouseManage/searchHouseInfo.action',
				datatype: "json",
				page:1
			});
			var postData=$.extend({"searchHouseInfoVo.organization.id":getCurrentOrgId(),orgId:getCurrentOrgId()},$("#searchActualHouseForm").serializeObject());
			if($("#houseUsesId").val()!=""){
				postData = $.extend(postData,{"searchHouseInfoVo.houseUses.id" : $("#houseUsesId").val()});
			}
			if($("#houseIsRentalHouse").val()!="") {
				postData = $.extend(postData,{"searchHouseInfoVo.isRentalHouse" : $("#houseIsRentalHouse").val()});
			}

			$("#actualHouseList").setPostData(postData);
			$("#actualHouseList").trigger("reloadGrid");
		}
		function addFun(){
			if (!isGrid()){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
				return;
			}
			if (currentOrgId==null||currentOrgId==""){
				$.notice({level:'warn',
						message:'请先选择一个部门'});
			}else{
				$("#actualHouseMaintanceDialog").createTabDialog({
						model :"add",
						title:"新增房屋信息",
						width: 800,
						height: 600,
						postData:{
							operateSource:'actualHouse'
						},
						tabs:[
							{title:'房屋信息',url:PATH+'/baseinfo/actualHouseManage/prepareDispatch.action?dailogName=actualHouseMaintanceDialog&mode=add&orgId='+currentOrgId}
						],
						close : function(){
						$("#actualHouseList").trigger("reloadGrid");
						}
						
				});
			}
		}
		
		function combineHouseFun(){
			if (!isGrid){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行合并！"});
				return;
			}
			var rowIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");//被合并的房屋ID
			if(rowIds.length==null || rowIds == ""){
				$.messageBox({level:'warn',
					message:'请先选择一条房屋信息'});
				return;
			}else if(rowIds.length>1){
				$.messageBox({level:'warn',
					message:'只能对一条信息进行操作!'});
				return;
			}
			if (currentOrgId==null||currentOrgId==""){
				$.notice({level:'warn',
						message:'请先选择一个部门'});
				return;
			}else{
				var actualHouse = $("#actualHouseList").getRowData(rowIds);
				$("#mergeActualHouseDialog").createDialog({
						title:"房屋合并",
						width: 800,
						height: 600,
						url:PATH+'/baseinfo/actualHouseManage/dispatchByEncryptId.action?mode=merge&orgId='+currentOrgId+'&houseInfo.id='+actualHouse.encryptId,
						buttons: {
					   		"合并" : function(){
					   			var mainIds = $("#houseId").val();
					   			megerHouseInfo(mainIds,rowIds);
//	 				        	$(this).dialog("close");
					   		},
					   		"关闭" : function(){
					        	$(this).dialog("close");
					   		}
						}
						
				});
			}
		}
		
		function megerHouseInfo(mainIds,rowIds){
			var rowIds = $("#sameHouseList").jqGrid("getGridParam", "selarrrow");
			if(rowIds.length==null || rowIds == ""){
				$.messageBox({level:'warn',
					message:'请在房屋重复信息列表下选择一条需要合并的房屋信息'});
				return;
			}else if(rowIds.length>1){
				$.messageBox({level:'warn',
					message:'一次只能选择一条需要合并的房屋信息!'});
				return;
			}
			var actualHouseMeger = $("#sameHouseList").getRowData(rowIds);
			var actualHouseMain = $("#actualHouseList").getRowData(mainIds);
			$("#megerHouseInfoDialog").createDialog({
				title:"房屋信息对比",
				width: 700,
				height: 600,
				url:PATH+'/baseinfo/actualHouseManage/getMegerHouseInfo.action?houseId='+actualHouseMain.encryptId+'&megerId='+actualHouseMeger.id,
				buttons: {
			   		"保存" : function(){
			   			$.confirm({
			   				title:"确认合并",
			   				message:"确定要合并吗?被合并的房屋关联信息将被解除或合并！",
			   				okFunc: function(){
			   					$.ajax({
			   						url:PATH+"/baseinfo/actualHouseManage/combineHouseInfo.action",
			   						data:$('#megerForm').serialize(),
			   						success:function(data){
			   						    $.messageBox({message:"已经成功合并该房屋信息!"});
			   						    $("#megerHouseInfoDialog").dialog("close");
			   						    $("#mergeActualHouseDialog").dialog("close");
			   							$("#actualHouseList").trigger("reloadGrid");
			   					    }
			   				    });
			   			   }
			   		 	});
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
				
		});
			
		}
		
		function deleteFun(){
			var rowIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
			if(rowIds.length ==0){
				 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			deleteActualHouse(event,rowIds);
		}
		function changeHouseCodeFun(){
			var rowIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
			if(rowIds.length == 0){
				$.messageBox({level:'warn',message:'没有选中任何记录！'});
				return ;
			}
			if(rowIds.length > 1){
				$.messageBox({level:'warn',message:'只能选中一条记录！'});
				return ;
			}
			var rowId = $("#actualHouseList").jqGrid("getGridParam", "selrow");
			var houseInfo =  $("#actualHouseList").getRowData(rowId);
			$("#actualHouseDialog").createDialog({
				width:300,
				height:200,
				title:'重置房屋编号',
				url:PATH+"/baseinfo/actualHouseManage/dispatchOperate.action?mode=change&dialogName=actualHouseDialog&orgId="+getCurrentOrgId()+"&houseInfo.houseCode="+houseInfo.houseCode,
				buttons : {
					"保存" : function(){
						$("#changeHouseCodeForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}
		function importFun(){
			$("#noticeDialog").createDialog({
				width: 400,
				height: 230,
				title:'数据导入',
				url:PATH+'/common/import.jsp?isNew=1&dataType=actualHouseData&dialog=noticeDialog&startRow=6&templates=ACTUALHOUSE&listName=actualHouseList',
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
		}
		function exportFun(){
			if ($("#actualHouseList").getGridParam("records")>0){
	            $("#actualHouseMaintanceDialog").createDialog({
	                width: 250,
	                height: 200,
	                title:'导出房屋信息',
	                url:PATH+'/common/exportExcel.jsp',
	                postData:{
	                    gridName:'actualHouseList',
	                    orgId:getCurrentOrgId(),
	                    downloadUrl:PATH+'/baseinfo/actualHouseManage/downloadHouseInfo.action'
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
	        }
		}
		function reloadFun(){
			$("#conditionMark").val("fast_hosueCode");
			$("#searchByCondition").attr("value","请输入房屋编号或房屋准确地址");
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
		function maintainFun(){
			var selectedIdLast =  getSelectedIdLast();
			if(selectedIdLast==null){
				$.messageBox({level:"warn",message:"请选择一条记录，再进行操作！"});
				return;
			}
			var selectedIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds.length>1){
				$.messageBox({level:"warn",message:"只能选择一条记录进行操作！"});
				return;
			}
			var houseInfo =  $("#actualHouseList").getRowData(selectedIdLast);
			$("#actualHouseMaintanceDialog").createDialog({
				width: 700,
				height: 600,
				title:'住房管理',
				url:PATH+'/baseinfo/actualHousePopulation/prepairMantanceLivingPopulationByEncrypt.action?houseInfo.id='+houseInfo.encryptId,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				},
				close:function(){
					$("#actualHouseList").trigger("reloadGrid");
				}
			});
		}
		function updateFun(){
			var selectedId =  getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
			updateActualHouse(event,selectedId);
		}
		function isRentalhouseFormatter(el,options,rowData){
			if(true==rowData.isRentalHouse){
				return '是';
			}else{
				return '否';
			}
		}
		function isRentalHouseUnFormatter(el){
			if(el=="是"){
				return	1;
			}else{
				return 0;
			}
		}		
		function isHasPeopleFormatter(el,options,rowData){
			if(undefined != rowData.buildingUses && buildingUsesPropertyId == rowData.buildingUses.id){
				if(rowData.memberNum <= 0){
					return "<img src='"+PATH+"/resource/system/images/house_leisure_state_red.png'></img>";
				}else{
					return "<img src='"+PATH+"/resource/system/images/house_leisure_state_green.png'></img>";
				}
				
			}else{
				return "";
			}
			
		}
		
		function operatorFormatter(el,options,rowData){
			return (dfop.hasUpdateActualHouse == 'true' ? "<a href='javascript:updateActualHouse(event,"+rowData.id+")'><span>修改</span></a>"+
					   "&nbsp;|&nbsp;" : '' )+
					   (dfop.hasDeleteActualHouse == 'true' ? "<a href='javascript:deleteActualHouse(event,"+rowData.id+")'><span>删除</span></a>" : '');
		}
		function memberNumFormatter(el,options,rowData){
			return "<a href='javascript:viewActualHousePopulation("+rowData.id+")'>"+rowData.memberNum+"</a>";
		}
		function houseCodeFont(el,options,rowData){
			return (dfop.hasViewActualHouse == 'true' ? "<a href='javascript:viewActualHouse("+rowData.id+")'>"+rowData.houseCode+"</a>": rowData.houseCode);
		}
		function addressFormatter(el,options,rowData){
			if(rowData.address!=null && rowData.address!="" && typeof(rowData.address)!="undefined" && rowData.address!=" ")
				return  (dfop.hasViewActualHouse == 'true' ? "<a href='javascript:viewActualHouse("+rowData.id+")'>"+rowData.address+"</a>": rowData.address);
			else
				return "";
		}
		
		
		$("#generatedQrcodeByHouse").click(function(){
			var selectedRowIds=getSelectedIds();
			if(selectedRowIds.length ==0 ){
				$.messageBox({level:"warn",message:"请选择一条或者多条数据再进行操作！"});
		 		return;
			}
			var encryptIds=deleteOperatorByEncrypt("actualHouse",selectedRowIds,"encryptId");
			$("#qrcodeDialog").createDialog({
				width: 700,
				height: 600,
				title:"二维码列表",
				url:PATH+"/twoDimensionCodeManage/generateQrcodeByEncryptId.action?qrcodeType=houseQrcodeType&ids="+encryptIds,
				buttons:{
					"打印":function(){
						printQrcode();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
				
			});
			
		});

	});

	function getSelectedIds(){
		var selectedRowIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
		return selectedRowIds;
	}

	function afterLoad(){
		setHouseInfoOperateButton();
	}
	function setHouseInfoOperateButton(){
		var selectedCounts = getActualjqGridMultiSelectCount("actualHouseList");
		var count = $("#actualHouseList").jqGrid("getGridParam","records");
		if(selectedCounts == count && count > 0){
			jqGridMultiSelectState("actualHouseList", true);
		}else{
			jqGridMultiSelectState("actualHouseList", false);
		}
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#actualHouseList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}

	function printQrcode(){
		$.dialogLoading("open");
		$("#qrcodeDialog").printArea();
		$("#qrcodeDialog").dialog("close");
		$.dialogLoading("close");
	}
}




function viewActualHouse(rowid){
	if(rowid==null){
 		return;
	}
	var houseInfo =  $("#actualHouseList").getRowData(rowid);
	$("#actualHouseMaintanceDialog").createDialog({
		width: 800,
		height: 580,
		title:'查看房屋信息',
		modal : true,
		url:PATH+'/baseinfo/houseInfo/actualHouse/viewActualHouseDlg.jsp?mode=view&houseId='+houseInfo.encryptId,
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

function deleteActualHouse(event,rowIds){
	if(rowIds==null){
		$.messageBox({level:'warn',message:'没有选中任何记录！'});
		return ;
	}
	var encryptIds=deleteOperatorByEncrypt("actualHouse",rowIds,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除，房屋内的住户将变为无房，无法恢复",
		okFunc: function(){
			$.ajax({
				url:PATH+"/baseinfo/actualHouseManage/deleteHouseInfo.action",
				type:"post",
				data:{
					"houseIds":encryptIds
				},
				success:function(data){
					$("#actualHouseList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除该房屋信息!"});
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

function updateActualHouse(event,selectedId){
	if(selectedId==null){
		$.messageBox({level:'warn',message:'没有选中任何记录！'});
		return ;
	}
	var houseInfo =  $("#actualHouseList").getRowData(selectedId);
	var tabsValue;
	if(houseInfo.isRentalHouse){
		tabsValue=[
					{title:'房屋信息',url:PATH+'/baseinfo/actualHouseManage/prepareDispatchByEncrypt.action?dailogName=actualHouseMaintanceDialog&mode=edit'},
					{title:'出租房',url:PATH+'/baseinfo/rentalHouseManage/prepareDispatchByEncrypt.action?dailogName=actualHouseMaintanceDialog&mode=edit'}
				];
	}else{
		tabsValue=[
			{title:'房屋信息',url:PATH+'/baseinfo/actualHouseManage/prepareDispatchByEncrypt.action?dailogName=actualHouseMaintanceDialog&mode=edit'}
		]
	}
	$("#actualHouseMaintanceDialog").createTabDialog({
		postData:{
			id : houseInfo.encryptId,
			mode:'edit',
			type:'ACTUALHOUSE',
			operateSource:'actualHouse'
		},
		title:'修改房屋信息',
		width: 800,
		height: 600,
		tabs:tabsValue,
		close : function(){
			$("#actualHouseList").trigger("reloadGrid");
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