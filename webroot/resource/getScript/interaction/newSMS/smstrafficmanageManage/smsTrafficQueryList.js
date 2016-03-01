TQ.smsTrafficQueryList = function (dfop){
	
	function operatorFormatter(el,options,rowData){
		return "<a href='javascript:setOperator("+rowData.id+")'><span>设置流量</span></a>";
	}

	function hasTelecomTrafficFormatter(el,options,rowData){
		return changeTraffic(rowData.hasTelecomTraffic)+"/"+changeTraffic(rowData.telecomTraffic);
	}

	function hasMobileTrafficFormatter(el,options,rowData){
		return changeTraffic(rowData.hasMobileTraffic)+"/"+changeTraffic(rowData.mobileTraffic);
	}

	function hasChinaUnicomTrafficFormatter(el,options,rowData){
		return changeTraffic(rowData.hasChinaUnicomTraffic)+"/"+changeTraffic(rowData.chinaUnicomTraffic);
	}

	function surplusTelecomTrafficFormatter(el,options,rowData){
		var number =   changeTraffic(rowData.telecomTraffic) - changeTraffic(rowData.hasTelecomTraffic);
		return number > 0 ? number : 0;
	}

	function surplusMobileTrafficFormatter(el,options,rowData){
		var number =  changeTraffic(rowData.mobileTraffic) - changeTraffic(rowData.hasMobileTraffic);
		return number > 0 ? number : 0;
	}

	function surplusChinaUnicomTrafficFormatter(el,options,rowData){
		var number = changeTraffic(rowData.chinaUnicomTraffic) - changeTraffic(rowData.hasChinaUnicomTraffic);
		return number > 0 ? number : 0;
	}

	function changeTraffic(traffic){
		if(traffic==null){
			return "0";
		}else{
			return traffic;
		}
	}

	function toggleButtonState(){
	  	var selectedIds=$("#trafficList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	}
	function afterLoad(){

	}
	
	$(document).ready(function(){
		var centerHeight=$("div.ui-layout-center").outerHeight();
		$(".orgTree").height(centerHeight-70);

		$("#org_autocomplete").autocomplete({
			source: function(request, response) {
				$.ajax({
					url: PATH+"/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
					data:{"organization.orgName":request.term},
					success: function(data) {
						response($.map(data, function(item) {
							return {
								label: item.orgName+","+stringFormatter(item.contactWay),
								value: item.orgName,
								id: item.id
							}
						}))
					},
					error : function(){
						$.messageBox({
							message:"搜索失败，请重新登入！",
							level:"error"
						});
					}
				})
			},
			select: function(event, ui) {
				$("#user_autocomplete").removeAttr("userId");
				$("#user_autocomplete").val("");
				$.ajax({
					url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
					success:function(data){
						$.searchChild(tree,data);
					}
				});
			}
		});
		var clearUserUserAutocomplete=true;
		
		var orgTypeCurrent = dfop.orgTypeCurrent;
		if(orgTypeCurrent == '1'){
			tree=$("#orgTree").initFunctionalTree();
		}else{
			tree=$("#orgTree").initTree();
		}
		
		function afterChangNode(node) {
		    var id = node.attributes.id;
		    loadDeptTraffic(id);
		}
		
		function loadDeptTraffic(orgId){
			var initParam = {
					"organizationId": orgId
				}
			$("#orgTrafficList").setGridParam({
				url:PATH+'/smstrafficmanageManage/findDeptTraffic.action',
				datatype: "json",
				page:1
			});
			$("#orgTrafficList").setPostData(initParam);
			$("#orgTrafficList").trigger("reloadGrid");
		}
		
		$.addClick(tree,afterChangNode);
		
		// 生成列表
		$("#orgTrafficList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
		    	{name:'orgName', index:'orgName',label:'部门名称', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'hasTelecomTraffic', index:'hasTelecomTraffic',label:'电信流量', formatter:hasTelecomTrafficFormatter, width:200, sortable:true, align:'center', hidden:false},
		    	{name:'hasMobileTraffic', index:'hasMobileTraffic',label:'移动流量', formatter:hasMobileTrafficFormatter, width:200, sortable:true, align:'center', hidden:false},
		    	{name:'hasChinaUnicomTraffic', index:'hasChinaUnicomTraffic',label:'联通流量', formatter:hasChinaUnicomTrafficFormatter, width:200, sortable:true, align:'center', hidden:false},
		    	{name:'telecomTraffic', index:'telecomTraffic',label:'剩余电信流量', formatter:surplusTelecomTrafficFormatter, width:140, sortable:true, align:'center'},
		    	{name:'mobileTraffic', index:'mobileTraffic',label:'剩余移动流量', formatter:surplusMobileTrafficFormatter,width:140, sortable:true, align:'center'},
		    	{name:'chinaUnicomTraffic', index:'chinaUnicomTraffic',label:'剩余联通流量', formatter:surplusChinaUnicomTrafficFormatter,width:140, sortable:true, align:'center'}
		   	]
//	 	   	pgbuttons:false,
//	 	   	pginput:false,
//	 	   	viewrecords:false
		});
		jQuery("#orgTrafficList").jqGrid('setFrozenColumns');
		
	});

}