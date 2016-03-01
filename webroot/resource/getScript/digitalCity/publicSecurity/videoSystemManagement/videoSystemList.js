TQ.videoSystemList = function (dfop){
	// Formatter
	function videoOperatorFormatter(el,options,rowData){
		return (dfop.hasUpdateVideoSystem == 'true' ? "<a href='javascript:videoUpdateOperator("+rowData.id+")'><span>修改</span></a> "+
				   "&nbsp;|&nbsp;" : '' )+
				   (dfop.hasDeleteVideoSystem == 'true' ? "<a href='javascript:videoDeleteOperator("+rowData.id+")'><span>删除</span></a>" : '');
	}
	
	
	function videoShowFormatter(el,options,rowData){
		return "<a href='javascript:showVedios("+JSON.stringify(rowData.address) 
										+ "," + JSON.stringify(rowData.url)
										+ "," + JSON.stringify(rowData.account)
										+ "," + JSON.stringify(rowData.password)
										+ "," + JSON.stringify(rowData.videoNo)
										+")'><span>播放</span></a>";
	}
	
	
	$(document).ready(function(){
		loadList();
		if(useOrgDepartmentNo.indexOf("511622") == 0){
			gisType = "3D";
		}
		function toggleButtonState(){
		  	var selectedIds=$("#videoSystemList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){

		}
		function loadList(){
			// 生成列表
			$("#videoSystemList").jqGridFunction({
				mtype:'post',
				datatype: "local",
			   	colModel:[
					{name:"id",index:'id',hidden:true},
					{name:"encryptId",index:'id',frozen:true,hidden:true},
			    	{name:"operator", index:'id',label:'操作',formatter:videoOperatorFormatter,width:100,frozen:true,sortable:false,align:'center' },
			    	{name:"showVedio", index:'id',label:'视频',formatter:videoShowFormatter,width:100,frozen:true,sortable:false,align:'center' },
			    	{name:'videoNo', index:'code',label:'编号', width:150, sortable:true, align:'center', hidden:false,formatter:videoNameFormatter}, 	
			    	{name:'organization.orgName',index:'orgId',label:'所属区域', width:360,hidden:false,sortable:true},
			    	{name:'address', index:'address',label:'地址', width:400, sortable:true, align:'center', hidden:false}, 	
			    	{name:'url', index:'url',label:'网络地址', width:400, sortable:true, align:'center', hidden:false}, 	
			    	{name:'account', index:'account',label:'账号', width:400, sortable:true, align:'center', hidden:false}, 	
			    	{name:'password', index:'password',label:'密码', width:400, sortable:true, align:'center', hidden:false,formatter:videoPwdFormatter}, 	
			    	{name:'createUser', index:'createUser',label:'创建人', width:100, sortable:true, align:'center', hidden:true}, 	
			    	{name:'updateUser', index:'updateUser',label:'修改人', width:100, sortable:true, align:'center', hidden:true}, 	
			    	{name:'createDate', index:'createDate',label:'创建时间', width:100, sortable:true, align:'center', hidden:true}, 	
			    	{name:'updateDate', index:'updateDate',label:'修改时间', width:100, sortable:true, align:'center', hidden:true},
			    	{name:"isEmphasis",label:"是否关注",hidden:true,hidedlg:true,width:100},
			    	{name:'isBind',index:'isBind',label:'绑定地图',sortable:false,width:120,align:"center"},
					{name:'openLayersMapInfo.centerLon',sortable:false,index:'openLayersMapInfo.centerLon',label:'监控视频中心坐标X',width:120,hidden:true},
					{name:'openLayersMapInfo.centerLat',sortable:false,index:'openLayersMapInfo.centerLat',label:'监控视频中心坐标Y',width:120,hidden:true},
					{name:'openLayersMapInfo.centerLon2',sortable:false,index:'openLayersMapInfo.centerLon2',label:'监控视频中心2D坐标X',width:120,hidden:true},
					{name:'openLayersMapInfo.centerLat2',sortable:false,index:'openLayersMapInfo.centerLat2',label:'监控视频中心2D坐标Y',width:120,hidden:true}
			   	],
				gridComplete:function(){
			    	 var ids=jQuery("#videoSystemList").jqGrid('getDataIDs');
			    	 for(var i=0; i<ids.length; i++){
			    		 var id=ids[i];
			    		 var isBind = $("#videoSystemList").getCell(id,"isBind");
			    		 var lon=""
			    		 var lat="";
			    		 if(gisType=="2D"){
			    			 lon = $("#videoSystemList").getCell(id,"openLayersMapInfo.centerLon2");
			    			 lat = $("#videoSystemList").getCell(id,"openLayersMapInfo.centerLat2");
			    		 }else if(gisType=="3D"){
			    			 lon = $("#videoSystemList").getCell(id,"openLayersMapInfo.centerLon");
			    			 lat = $("#videoSystemList").getCell(id,"openLayersMapInfo.centerLat");
			    		 }
			    		 if(lon!="" && lon!=null && lat!="" && lat!=null){
			    			 modify = "<a href='javascript:viewBindMap("+lon+","+lat+")' style='color:#f60; text-decoration: underline;' >查看地图</a>";//查看
			    		}else{
			    			modify="未绑定";
			    		}

			    		 jQuery("#videoSystemList").jqGrid('setRowData', ids[i], { isBind: modify});

			    	 }

			     },
			  	multiselect:true,
			  	onSelectAll:function(data){
			  		toggleButtonState(data);
			  	},
		    	loadComplete: function(data){
		    		afterLoad(data);
		    	},
		    	ondblClickRow:function (rowid){
					if(dfop.hasViewVideoSystem=='true'){
						viewVideoSystem(rowid);
					}
				},
				onSelectRow: function(data){
					toggleButtonState(data);
				}
			});
		}
		
		jQuery("#videoSystemList").jqGrid('setFrozenColumns');
		
		$("#fastSearchButton").click(function(){
			search(getCurrentOrgId());
		});
		$("#searchText").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入监控视频编号");
		});
		$("#add").click(function(){
			if (!isGrid()){
				$.messageBox({level:'warn',message:"请先选择网格级别组织机构进行新增！"});
				return;
			}
			$("#videoSystemDialog").createDialog({
				model :"add",
				title:"新增监控视频信息",
				width: dialogWidth,
				height: dialogHeight,
				url:PATH+'/videoSystemManage/dispatch.action?mode=add&organization.id='+getCurrentOrgId()+"&gisType="+gisType,
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
		$("#update").click(function(){
			var selectedIds = $("#videoSystemList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){return;}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				 return;
			}
			videoUpdateOperator(selectedId);
		});
		$("#delete").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			videoDeleteOperator(allValue);
		});
		$("#view").click(function(){
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
			viewVideoSystem(selectedId);
		});
		$("#reload").click(function(){
			if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
				onOrgChanged(getCurrentOrgId(),isGrid());
			}
		});
		
	/*	$("#dataUpdate").click(function(){
			
			var urlPath = $("#urlPath").val();
			var userName = $("#userName").val();
			var passWord = $("#passWord").val();
			$("#videoUpdateialog").createDialog({
				width: 1000,
				height: 650,
				title:'视频播放',
				url:PATH+'/digitalCity/publicSecurity/videoSystemManagement/vedioShowForTest.jsp',
				postData:{
					urlPath:urlPath,
					userName:userName,
					passWord:passWord
					},
				buttons: {
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
			});
			
			if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
				onOrgChanged(getCurrentOrgId(),isGrid());
			}
		});*/

		$("#search").click(function(event){
			$("#videoSystemDialog").createDialog({
				width:650,
				height:320,
				title:'监控视频查询-请输入查询条件',
	 	 		url:PATH+'/digitalCity/publicSecurity/videoSystemManagement/searchVideoSystemDlg.jsp?orgId=' + getCurrentOrgId(),
				buttons: {
			   		"查询" : function(event){
						searchVideoSystem();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
		
		$("#cancelEmphasise").click(function(event){
			if($(this).attr("disabled")=="disabled"){
				return;
			}
			var selectedId =getSelectedIds();
			var cancelEmphasise=new Array();
			var temp=new Array();
			if(selectedId == null || selectedId.length<=0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行取消关注！"});
				return;
			}
			for(var i=0;i<selectedId.length;i++){
				var rowData = $("#videoSystemList").getRowData(selectedId[i]);
				if(rowData.isEmphasis==false || rowData.isEmphasis=="false"){
					cancelEmphasise.push(selectedId[i]);
				}else{
					temp.push(selectedId[i]);
				}
			}
			selectedId=cancelEmphasise;
			if(selectedId==null||selectedId.length==0){
				$.messageBox({level:'warn',message:"没有可取消关注的数据！"});
				return;
			}
			var selectedIds="";
			for(var i=0;i<selectedId.length;i++){
				selectedIds+=selectedId[i]+",";
			}
			if(selectedIds.length=2){
				selectedIds=selectedId;
			}
			var encryptIds=deleteOperatorByEncrypt("videoSystem",selectedIds,"encryptId");
			$("#videoSystemDialog").createDialog({
				width:450,
				height:210,
				title:'取消关注提示',
				modal:true,
				url:PATH+'/digitalCity/publicSecurity/emphasiseConditionDlg.jsp?publicSecurityType='+dfop.publicSecurityType+'&locationIds='+encryptIds+'&isEmphasis=true&dailogName=videoSystem&temp='+temp,
				buttons: {
				   "保存" : function(event){
					   $("#emphasisForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});
		
		$("#reEmphasise").click(function(){
			if($(this).attr("disabled")=="disabled"){
				return;
			}
			var selectedId = getSelectedIds();
			var reEmphasise=new Array();
			var temp=new Array();
			if(selectedId == null || selectedId.length<=0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再重新关注！"});
				return;
			}
			for(var i=0;i<selectedId.length;i++){
				var rowData = $("#videoSystemList").getRowData(selectedId[i]);
				if(rowData.isEmphasis==true||rowData.isEmphasis=="true"){
					reEmphasise.push(selectedId[i]);
				}else{
					temp.push(selectedId[i]);
				}
			}
			selectedId=reEmphasise;
			if(selectedId==null||selectedId.length==0){
				$.messageBox({level:'warn',message:"没有可重新关注的数据！"});
				return;
			}
			var selectedIds="";
			for(var i=0;i<selectedId.length;i++){
				selectedIds+=selectedId[i]+",";
			}
			if(selectedIds.length=2){
				selectedIds=selectedId;
			}
			var encryptIds=deleteOperatorByEncrypt("videoSystem",selectedIds,"encryptId");
			$.ajax({
				url:PATH+"/videoSystemManage/updateEmphasiseByEncryptId.action",
				data:{
					"ids": encryptIds,
					"videoSystem.isEmphasis":0
				},
				success:function(responseData){
					if(null==temp || temp.length==0){
						$.messageBox({message:""+dfop.moduleCName+"重新关注成功 ！ "});
					}else{
						$.messageBox({level:'warn',message:"除选中的红色数据外,其余"+dfop.moduleCName+"重新关注成功 ！ "});
					}
					notExecute=temp;
					//onOrgChanged(getCurrentOrgId(),isGrid());
					$("#videoSystemList").trigger("reloadGrid");
					
				}
			});
		});
		
		$("#transfer").click(function(e){
			if(isTownOrganization()){
				$.messageBox({level:'warn',message:"请先选择乡镇（街道）级别组织机构进行转移！"});
				 return;
			}
			var allValue = $("#videoSystemList").jqGrid("getGridParam", "selarrrow");
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行转移！"});
				 return;
			}
			for(var i=0;i<allValue.length;i++){
				var rowData=$("#videoSystemList").getRowData(allValue[i]);
				if(rowData.isBind!="未绑定"){
					$.messageBox({level:'warn',message:"所选记录已绑定地图，无法转移！"});
					 return;
				}
				if(rowData.isEmphasis==true || rowData.isEmphasis=="true"){
					$.messageBox({level:'warn',message:"所选记录是已注销记录，无法转移！"});
					 return;
				}
			}
			var orgid=	getCurrentOrgId();
			if(orgid==""||orgid==null){
				$.messageBox({level:'warn',message:"没有获取到当前的组织机构id"});
				 return;
			}
			$.confirm({
				title:"转移监控视频",
				message:"转移监控视频时,若目标网格存在相同数据,该数据不进行转移。",
				okFunc: function() {
					moveOperator(e,allValue,orgid);
				}
			});
		});
	});
	
	function moveOperator(event,allValue,orgid){
		var encryptIds=deleteOperatorByEncrypt("videoSystem",allValue,"encryptId");
		$("#moveDataDialog").createDialog({
			width: 400,
			height: 230,
			title:"数据转移",
			url:PATH+"/publicSecurity/transferManage/transferDispatchByEncrypt.action?orgId="+orgid+"&ids="+encryptIds+"&type=videoSystem",
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

	
	function parseObj(strData) {
		return (new Function("return " + strData))();
	}
	function searchVideoSystem(){
		var formdata = $("#searchVideoSystemForm").serialize();
		if (formdata != '') {
			formdata = formdata.replace(/\+/g," "); 
			formdata = formdata.replace(/=/g, '":"');
			formdata = formdata.replace(/&/g, '","');
			formdata += '"';
		}
		formdata = decodeURIComponent('{"' + formdata + '}');
		$("#videoSystemList").setGridParam({
			url:PATH+'/videoSystemManage/findVideosystemPagerBySearchVo.action',
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#videoSystemList").setPostData(parseObj(formdata));
		$("#videoSystemList").trigger("reloadGrid");
	}
	function search(orgId){
		var fastSearchVal = $("#searchText").val();
		if(fastSearchVal == '请输入监控视频编号' || fastSearchVal==''){
			onOrgChanged(getCurrentOrgId(),isGrid());
			return;
		}
		var	postData = {
			 "organization.id":orgId,
			 "searchVideoSystemVo.videoNo":fastSearchVal
		}
		$("#videoSystemList").setGridParam({
	 		url:PATH+'/videoSystemManage/findVideosystemPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#videoSystemList").setPostData(postData);
		$("#videoSystemList").trigger("reloadGrid");
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#videoSystemList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#videoSystemList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	function isTownOrganization(){
		return $("#currentOrgId").attr("orgLevelInternalId") > dfop.townOrganization;
	}
}

function videoNameFormatter(el,options,rowData){
	if(null == rowData.videoNo) {
		return "&nbsp;&nbsp;"
	}else if(rowData.isEmphasis==1){
		return '<a href="javascript:viewVideoSystem('+rowData.id+')"><font color="#778899">'+rowData.videoNo+'</font></a>';
	}
	return '<a  href="javascript:viewVideoSystem('+rowData.id+')"><font color="#6633FF">'+rowData.videoNo+'</font></a>';
}

function videoPwdFormatter(el,options,rowData){
	return "**********";
}
function videoUpdateOperator(selectedId){
	var ent =  $("#videoSystemList").getRowData(selectedId);
	var encryptId=ent.encryptId;
	if(ent.isEmphasis==true || ent.isEmphasis=="true"){
		 $.messageBox({level : 'warn',message:"该监控视频信息已经注销，不能修改!"});
		 return;
	}
	$("#videoSystemDialog").createDialog({
		model :"edit",
		title:"修改监控视频信息",
		width: dialogWidth,
		height: dialogHeight,
		url:PATH+'/videoSystemManage/dispatchByEncrypt.action?mode=edit&videoSystem.id='+encryptId+"&gisType="+gisType,
		buttons: {
			"保存" : function(){
				$("#maintainForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}		
	});
}
function videoDeleteOperator(allValue){
	var id=deleteOperatorByEncrypt("videoSystem",allValue,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
 				url:PATH+'/videoSystemManage/deleteVideosystemByIds.action',
 				type:"post",
 				data:{
 					"ids":id
 				},
				success:function(data){
				    $.messageBox({message:"已经成功删除该监控视频信息!"});
					$("#videoSystemList").trigger("reloadGrid");
				}
			});
		}
	});
}

function showVedios(address,url,account,password,guid){
	//数据验证
	if(url == null){
		$.messageBox({level:'warn',message:"该条监控信息，网络地址为空，请完善！"});
		return 0;
	}else if(url.indexOf(":") <=0){
		$.messageBox({level:'warn',message:"该条监控信息，网络地址格式有无！"});
		return 0;
	}
	if(account == null){
		$.messageBox({level:'warn',message:"该条监控信息，账号为空，请完善！"});
		return 0;
	}
	if(password == null){
		$.messageBox({level:'warn',message:"该条监控信息，密码为空，请完善！"});
		return 0;
	}
	if(guid == null){
		$.messageBox({level:'warn',message:"该条监控信息，编号为空，请完善！"});
		return 0;
	}
	var ip = url.split(":")[0];
	var portNum = url.split(":")[1];
	$("#videoSystemDialog").createDialog({
		width: 707,
		height: 580,
		title:'视频播放',
		url:PATH+'/digitalCity/publicSecurity/videoSystemManagement/videoShow.jsp',
		postData:{
			address:address,
			ip:ip,
			portNum:portNum,
			account:account,
			password:password,
			guid:guid
		},
		buttons: {
   			"关闭" : function(){
        		$(this).dialog("close");
   			}
		},
	});
}

function viewBindMap(lon,lat){
	$("#gisvideoSystemDialog").createDialog({
		zIndex:1020,
        width: 800,
        height: 600,
        title:'查看建筑物',
        url:PATH+"/openLayersMap/product_3.0/gisPublicSecurity.jsp?flag=3&dailogName=videoSystem&organizationId="+getCurrentOrgId()+"&viewLon="+lon+"&viewLat="+lat+"&gisType="+gisType,
        buttons: {
            "关闭" : function(){
          	  $("#videoSystemList").trigger("reloadGrid");
               $(this).dialog("close");
            }
        },
        shouldEmptyHtml:false
	});
}


