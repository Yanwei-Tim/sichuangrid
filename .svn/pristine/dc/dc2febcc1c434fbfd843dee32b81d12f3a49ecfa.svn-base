TQ.builddatasList = function (dfop){
	function operateFormatter(el,options,rowData){
		return (dfop.hasUpdateBuilddatas == 'true' ? "<a href='javascript:updateOperator(event,"+rowData.id+")'><span>修改</span></a>"+
				   "&nbsp;|&nbsp;" : '' )+
				   (dfop.hasDeleteBuilddatas == 'true' ? "<a href='javascript:deleteOperator(event,"+rowData.id+")'><span>删除</span></a>" : '');
	}
	function nameFont(el,options,rowData){
		return (dfop.hasViewBuilddatas == 'true' ? "<a href='javascript:viewBuilddatas("+rowData.id+")'>"+rowData.buildingname+"</a>": rowData.buildingname);
	}

	$("#refreshSearchKey").click(function(){
		$("#searchByCondition").attr("value","请输入楼宇名称或地址");
	});



	$(document).ready(function(){
		if(useOrg.indexOf("511622") == 0){
			gisType = "3D";
		}
		$("#builddatasList").jqGridFunction({
			datatype: "local",
		   	colModel:[
		   		{name:"id",index:"id",sortable:false,hidden:true},
		   		{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				{name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter,width:80,align:"center"},
				{name:'buildingid',index:'BUILDINGID',label:'楼宇ID',sortable:true,width:65,hidden:true},
				{name:'buildingname',index:'BUILDINGNAME',label:'楼宇名称',sortable:true,width:100,formatter:nameFont},
				{name:'buildingaddress',index:'BUILDINGADDRESS',sortable:true,label:'楼宇地址',width:140},
				{name:'owner',index:'OWNER',label:'业主',sortable:true,width:120},
				{name:'responsibleperson',index:'RESPONSIBLEPERSON',sortable:true,label:'负责人',width:120},
				{name:'phone',index:'PHONE',label:'联系电话',width:120,sortable:true,align:"center"},
				{name:'updateDate',index:'updateDate',label:'更新时间',sortable:true,width:120,align:"center"},
				{name:'buildingstructures',index:'BUILDINGSTRUCTURES',sortable:true,label:'建筑结构',formatter:buildingstructuresFormatter,width:120},
				{name:'type',index:'type',label:'楼宇类型',sortable:true,formatter:typeFormatter,width:120},
				{name:'isBind',index:'isBind',label:'绑定地图',sortable:false,width:120,align:"center"},
				{name:'openLayersMapInfo.centerLon',sortable:false,index:'openLayersMapInfo.centerLon',label:'楼宇中心坐标X',width:120,hidden:true},
				{name:'openLayersMapInfo.centerLat',sortable:false,index:'openLayersMapInfo.centerLat',label:'楼宇中心坐标Y',width:120,hidden:true},
				{name:'openLayersMapInfo.centerLon2',sortable:false,index:'openLayersMapInfo.centerLon2',label:'楼宇中心2D坐标X',width:120,hidden:true},
				{name:'openLayersMapInfo.centerLat2',sortable:false,index:'openLayersMapInfo.centerLat2',label:'楼宇中心2D坐标Y',width:120,hidden:true},
				{name:'isLayout',index:'isLayout',sortable:true,label:'是否已生成布局',formatter:layoutFormatter,width:120,hidden:true,align:"center"}
			],
			gridComplete:function(){
		    	 var ids=jQuery("#builddatasList").jqGrid('getDataIDs');
		    	 for(var i=0; i<ids.length; i++){
		    		 var id=ids[i];
		    		 var isBind = $("#builddatasList").getCell(id,"isBind");
		    		 var lon=""
		    		 var lat="";
		    		 if(gisType=="2D"){
		    			 lon = $("#builddatasList").getCell(id,"openLayersMapInfo.centerLon2");
		    			 lat = $("#builddatasList").getCell(id,"openLayersMapInfo.centerLat2");
		    		 }else if(gisType=="3D"){
		    			 lon = $("#builddatasList").getCell(id,"openLayersMapInfo.centerLon");
		    			 lat = $("#builddatasList").getCell(id,"openLayersMapInfo.centerLat");
		    		 }
		    		 if(lon!="" && lon!=null && lat!="" && lat!=null){
		    			 type= $("#type").val();
		    			// modify = "<a href='javascript:unbindMap("+id+")' style='color:#f60; text-decoration: underline;' >解除绑定</a>";
		    			 modify = "<a href='javascript:viewBindMap("+lon+","+lat+")' style='color:#f60; text-decoration: underline;' >查看地图</a>";//查看
		    		}else{
	    			 	// modify = "<a href='javascript:bindMap("+id+")' style='color:#f60; text-decoration: underline;' >绑定</a>";
		    			modify="未绑定";
		    		}

		    		 jQuery("#builddatasList").jqGrid('setRowData', ids[i], { isBind: modify});

		    	 }

		     },
			multiselect:true,
		  	onSelectAll:function(aRowids,status){},
			ondblClickRow: viewBuilddatas,
			onSelectRow:selectRow
		});

		$("#isBind").change(function(){
			onOrgChanged(getCurrentOrgId());
		});
		
		$("#searchByConditionButton").click(function(){
			var condition = $("#searchByCondition").val();
			if(condition == '请输入楼宇名称或地址') return;
			$("#builddatasList").setGridParam({
				url:PATH+'/builddatasManage/searchBuilddatasByNameAndAddress.action',
				datatype: "json",
				page:1
			});
			$("#builddatasList").setPostData(getPostDataForList());
			$("#builddatasList").trigger("reloadGrid");
		});

		function getPostDataForList(){
			var condition = $("#searchByCondition").val();
			var initParam = {
				"organization.id": getCurrentOrgId()
			}
			if(condition != '请输入楼宇名称或地址'){
				initParam = $.extend(initParam,{"builddatasSearchVo.nameAndAddress":condition});
			}
			return initParam;
		}
		
		$("#add").click(function(e){
			if(!isGrid()){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
				return;
			}
			$("#builddatasMaintanceDialog").createDialog({
				width: 600,
				height: 320,
				title: '新增'+title,
				url:PATH+'/builddatasManage/dispatch.action?mode=add&flag=true&organization.id=' + getCurrentOrgId()+"&gisType="+gisType,
				buttons: {
					"保存" : function(){
						$("#maintainForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});

		});

		$("#update").click(function(e){
			var selectedId = $("#builddatasList").getGridParam("selrow");
			if(selectedId==null){
				 return;
			}
			updateOperator(event,selectedId);
		});

		$("#delete").click(function(e){
			var selectedId = $("#builddatasList").jqGrid("getGridParam", "selarrrow");
			if(selectedId.length==0){
				 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			deleteOperator(event,selectedId);
		});
		$("#search").click(function(event){
			$("#builddatasMaintanceDialog").createDialog({
				width: 600,
				height: 240,
				title: '楼宇信息查询-请输入查询条件',
				url:PATH+'/baseinfo/buildDatas/builddatasSearchDlg.jsp?orgId=' + getCurrentOrgId(),
				buttons: {
					"查询" : function(){
						searchBuilddatas();
						$(this).dialog("close");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		});
		$("#view").click(function(event){
			var selectedId = $("#builddatasList").getGridParam("selrow");
			if(selectedId==null){
		 		return;
			}
			viewBuilddatas(selectedId);
		});

		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			onOrgChanged(getCurrentOrgId());
		}else{
			setGisBaseinfoSelectNode();
		}

		$("#refresh").click(function(){
			if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
				$("#isBind").val("全部");
				onOrgChanged(getCurrentOrgId());
			}
		});

		$("#layout").click(function(){
			var selectedId = $("#builddatasList").getGridParam("selarrrow");
			
			if (selectedId == null||selectedId.length>1||selectedId.length==0) {
				 $.messageBox({level:"warn",message:"请选择一条记录进行住房管理！"});
				return;
			}
			var buildData =  $("#builddatasList").getRowData(selectedId);
			var buildingname = $("#builddatasList").getCell(selectedId,"buildingname");
			var isLayout = $("#builddatasList").getCell(selectedId,"isLayout");
			var layoutUrl="";
			if(isLayout == '否'){
				$("#generatedLayoutDialog").createDialog({
					width: 300,
					height: 200,
					title: "生成布局",
					url:PATH+'/baseinfo/buildDatas/generatedLayout.jsp?orgId=' + getCurrentOrgId()+'&buildingId='+buildData.encryptId+'&buildingname='+buildingname,
					buttons: {
						"生成" : function(){
							$("#layoutForm").submit();
						},
						"关闭" : function(){
							$(this).dialog("close");
						}
					}
				});
			}else{
				layoutUrl =PATH+'/builddatasManage/generatedLayout.action?mode=layout&builddatas.id='+selectedId+'&flag=false';
				generatedLayout(layoutUrl,buildingname,selectedId);
			}

		});

		
		$("#house").click(function(event) {
				if(!isGrid()){
					 $.messageBox({level:"warn",message:"请选择网格层级再操作！"});
					 return;
				}
				var selectedId = $("#builddatasList").getGridParam("selarrrow");
				
				if (selectedId == null||selectedId.length>1||selectedId.length==0) {
					 $.messageBox({level:"warn",message:"请选择一条记录进行住房管理！"});
					return;
				}
				var building = $("#builddatasList").getRowData(selectedId);
				var houseurl = '&buildingid='+building.encryptId;
				$("#HouseMaintanceDialog").createDialog({
					width : 700,
					height : 410,
					title : '住房管理',
					url :PATH+'/baseinfo/buildDatas/houseManageList.jsp?mode=edit&width=800&height=400&orgId='+ getCurrentOrgId()+ houseurl,
					buttons : {
						"关闭" : function() {
							$(this).dialog("close");
						}
					}
				});

		});

	});

	function bindMap(id) {
		if(!isGrid()){
			$.messageBox({
				message:"请选择网格级别组织机构进行绑定",
				lever: "warn"
		 	});
	        return;
		}
		bindBuild(PATH+'/builddatasManage/dispatch.action?mode=bind&idArr='+id+'&organization.id=' + getCurrentOrgId());

	}

	function unbindMap(id){

		if(!isGrid()){
			$.messageBox({
				message:"请选择网格级别组织机构进行解除绑定",
				lever: "warn"
		 	});
	        return;
		}
		
		$.confirm({
			title:"解除绑定",
			message:"您确定要解除绑定吗？",
			width: 400,
			okFunc: function(){cancelBind(id);}
		});

	}

	function cancelBind(id){
		 $.ajax({
		     async: false,
		     datatype: "json",
		     url:PATH+"/builddatasManage/unboundBuilddatas.action",
		     data:{
		    	 "builddatas.id":id
			 },
	         success: function(data){
				if(data==true){
					$.messageBox({message:"解除绑定成功!"});
					onOrgChanged(getCurrentOrgId());
				}
	 	    },
	 	    error: function(XMLHttpRequest, textStatus, errorThrown){
	 	    	$.messageBox({message:"提交错误!"});
	 	    }
	 	});
	}

	function saveFeature(lon,lat,featureId){


		$.ajax({
		     async: false,
		     datatype: "json",
		     method:"post",
		     url:PATH+"/gis/commonOpenLayersManage/boundBuildding.action",
		     data:{
		    	 "builddatas.id":bindid,
			     "openLayersMapInfo.featureId":featureId,
			     "openLayersMapInfo.centerLon":lon,
			     "openLayersMapInfo.centerLat":lat
			 },
	       success: function(data){
				$.messageBox({message:"绑定成功!"});
				onOrgChanged(getCurrentOrgId());
				$("#gisBuilddatasDialog").dialog("close");
		   },
		   error: function(XMLHttpRequest, textStatus, errorThrown){
		    	$.messageBox({message:"提交错误!"});
		   }
		});

	}

	function checkFeature(lon,lat,featureId){
		$.ajax({
			url:PATH+"/builddatasManage/getBuilddatasByBuildId.action?",
			data:{
				"builddatas.buildingid":featureId
			},
			success:function(data){

				if(data != null){
					$.messageBox({
						message:"该热区已绑定楼宇！",
						lever: "warn"
					 });
		            return;
				}

				saveFeature(lon,lat,featureId);

			}
		});
	}

	function layoutFormatter(el, options, rowData){
		if(rowData.isLayout != null){
			if(0 == rowData.isLayout){
				return "否";
			}else if(1 == rowData.isLayout){
				return "是";
			}
		}
	}

	function selectRow() {
		var count = $("#builddatasList").jqGrid("getGridParam", "records");
		var selectedCounts = getActualjqGridMultiSelectCount("builddatasList");
		if (selectedCounts == count) {
			jqGridMultiSelectState("builddatasList", true);
		} else {
			jqGridMultiSelectState("builddatasList", false);
		}

	}

	function parseObj(strData) {
		return (new Function("return " + strData))();
	}

	function searchBuilddatas() {
		var formdata = $("#maintainForm").serialize();
		if (formdata != '') {
			formdata = formdata.replace(/=/g, '":"');
			formdata = formdata.replace(/&/g, '","');
			formdata += '"';
		}
		formdata = decodeURIComponent('{"' + formdata + '}');

		$("#builddatasList").setGridParam({
			url :PATH+'/builddatasManage/searchBuilddatas.action',
			datatype : "json",
			page : 1
		});
		$("#builddatasList").setPostData(parseObj(formdata));
		$("#builddatasList").trigger("reloadGrid");
	}

	function saveLayout(){
		var table = document.getElementById("layoutTable");

		var tds = table.children;
		var buildLayoutStr = "";

		var layoutColumn= document.getElementById("layoutColumn").value;
		var layoutRow= document.getElementById("layoutRow").value;
		//var orgId= document.getElementById("orgId").value;
		var orgId = getCurrentOrgId();
		var buildingid= document.getElementById("buildingid").value;
		var centerx= document.getElementById("centerx").value;
		var centery= document.getElementById("centery").value;
		var selectedId = $("#builddatasList").getGridParam("selrow");


		for(var i = 0;i <layoutRow;i++){

			for(var j = 0;j <layoutColumn;j++){
				if(!document.getElementById("layoutTable").rows[i].cells[j]){
					break;
				}
				var colSpan = document.getElementById("layoutTable").rows[i].cells[j].colSpan;
				var rowSpan = document.getElementById("layoutTable").rows[i].cells[j].rowSpan;
				var houseId = document.getElementById("layoutTable").rows[i].cells[j].childNodes[3].value;

				var room = document.getElementById("layoutTable").rows[i].cells[j].childNodes[1].children[1].value;

				if(room==''){
					room = ' ';
				}

				buildLayoutStr += i+","+j+","+colSpan+","+rowSpan+","+houseId+","+room+":";


			}
		}


		$.ajax({
			url:PATH+"/builddatasManage/addBuildLayout.action?",
			data:{
				"layoutColumn":layoutColumn,
				"layoutRow":layoutRow,
				"organization.id":orgId,
				"builddatas.buildingid":buildingid,
				"builddatas.centerx":centerx,
				"builddatas.centery":centery,
				"buildLayoutStr":buildLayoutStr,
				"builddatas.id":selectedId
			},
			success:function(data){
				if(!data){
		        	$.messageBox({
						message:"生成楼宇布局失败",
						lever: "error"
					 });
		            return;
				}

				$("#buildLayoutDialog").dialog("close");
				$.messageBox({message:"生成楼宇布局成功"});
				onOrgChanged(getCurrentOrgId());
			}
		});
	}

	function bindBuild(bindUrl){

		bindDialog=$("#gisBuilddatasDialog").createDialog({
	              width: 800,
	              height: 600,
	              title:'绑定楼宇',
	              url:bindUrl,
	              buttons: {
	                  "关闭" : function(){
	                	  $("#builddatasList").trigger("reloadGrid");
	                     $(this).dialog("close");
	                  }
	              },
	              shouldEmptyHtml:false
	         });
	}
	
	function closeBindDialog(){
		$.messageBox({message:"绑定楼宇成功!"});
		$("#builddatasList").trigger("reloadGrid");
		$(bindDialog).dialog("close");
	}


	function saveBuild(spot){

		$.ajax({
			url:PATH+"/builddatasManage/getBuilddatasByBuildId.action?",
			data:{
				"builddatas.buildingid":spot.ID
			},
			success:function(data){

				if(data != null){
					$.messageBox({
						message:"该热区已绑定楼宇！",
						lever: "warn"
					 });
		            return;
				}

				$("#buildingid").val(spot.ID);
				$("#centerx").val(spot.CenterX);
				$("#centery").val(spot.CenterY);
				$("#builddatas-centerx").val(spot.CenterX);
				$("#builddatas-centery").val(spot.CenterY);
				$("#gisBuilddatasDialog").dialog("close");

			}
		});



		//url = '&builddatas.buildingid='+spot.ID;
	    //url += '&builddatas.centerx='+spot.CenterX;
	    //url += '&builddatas.centery='+spot.CenterY;


	}

	function saveBuild2D(lon,lat,featureId){
		$.ajax({
			url:PATH+"/builddatasManage/getBuilddatasByBuildId.action?",
			data:{
				"builddatas.buildingid":featureId
			},
			success:function(data){

				if(data != null){
					$.messageBox({
						message:"该热区已绑定楼宇！",
						lever: "warn"
					 });
		            return;
				}

				$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:markerMousemove});
				$("#buildingid").val(featureId);
				$("#centerLon").val(lon);
				$("#centerLat").val(lat);
				$("#builddatas-centerLon").val(lon);
				$("#builddatas-centerLat").val(lat);
				$("#gisBuilddatasDialog").dialog("close");

			}
		});

	}

	$("#importBuilddatas").click(function(event){
		importFun();
	});
	$("#exportBuilddatas").click(function(event){
	    exportFun();
	});

	function importFun(){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:'数据导入',
			url:PATH+'/common/import.jsp?isNew=1&dataType='+dfop.importDataType+'&dialog=noticeDialog&startRow=6&templates='+dfop.importTemplates+'&listName=builddatasList',
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
		if ($("#builddatasList").getGridParam("records")>0){
	        $("#builddatasMaintanceDialog").createDialog({
	            width: 250,
	            height: 200,
	            title:'导出楼宇信息',
	            url:PATH+'/common/exportExcel.jsp',
	            postData:{
	                gridName:'actualHouseList',
	                orgId:getCurrentOrgId(),
	                downloadUrl:PATH+'/baseinfo/actualHouseManage/downloadHouseInfo.action'
	            },
	            buttons: {
	                "导出" : function(event){
	                    $("#exceldownload").submit();
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

	/*
	function updateBuilding(id,orgId){

		$.ajax({
			url:"${path}/builddatasManage/updateBuilddatas.action?builddatas.id="+id+"&builddatas.organization.id="+orgId+url,
			success:function(data){

				if(!data.id){
		           	 $.messageBox({
						message:data,
						lever: "error"
					 });
		            return;
				}


				url="";

			}
		});
	}
	*/
}

function viewBindMap(lon,lat){
	$("#gisBuilddatasDialog").createDialog({
		zIndex:1020,
        width: 800,
        height: 600,
        title:'查看建筑物',
        url:PATH+"/openLayersMap/product_3.0/gis2Dbuilddatas.jsp?flag=3&organizationId="+getCurrentOrgId()+"&viewLon="+lon+"&viewLat="+lat+"&gisType="+gisType,
        buttons: {
            "关闭" : function(){
          	  $("#builddatasList").trigger("reloadGrid");
               $(this).dialog("close");
            }
        },
        shouldEmptyHtml:false
	});
}


function viewBuilddatas(rowId) {
	if (rowId == null) {
		return;
	}
	var builddata=$("#builddatasList").getRowData(rowId);
	$("#builddatasMaintanceDialog").createDialog({
		width : 600,
		height : 320,
		title : '查看' + title ,
		url : PATH+'/builddatasManage/dispatchByEncrypt.action?mode=view&builddatas.id='+builddata.encryptId,
		buttons : {
			"打印" : function(){
				printByUrl(PATH+'/builddatasManage/dispatchByEncrypt.action?mode=view&builddatas.id='+builddata.encryptId);
		  	},
			"关闭" : function() {
				$(this).dialog("close");
			}
		}
	});
}


function deleteBuilddatas(allValue) {
	$.ajax({
		url : PATH+"/builddatasManage/deleteMultiBuilddatas.action",
		type: "post",
		data:{
			"idList":allValue
		},
		success : function(data) {
			if (data != null && data[0] > 0) {
				$("#builddatasList").trigger("reloadGrid");
				$.messageBox({
					message : "删除楼宇成功！"
				});
			} else {
				$.messageBox({
					message : data,
					lever : "error"
				});
				return;
			}
		}
	});
}

