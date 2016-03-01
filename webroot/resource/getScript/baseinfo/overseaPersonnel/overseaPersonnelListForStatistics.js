TQ.overseaPersonnelListForStatistics = function (dfop){
	var isgridBol = false;
	var title=$("#title").html();
	$(document).ready(function(){
		
		function operateFormatter(el, options, rowData){
			return "<a href='javascript:viewDialog("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>";
		}
	
		$("#overseaPersonnelList").jqGridFunction({
			datatype: "local",
			colModel:[
		        {name:"id",index:"id",hidden:true,frozen:true},
		        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
		        {name:"操作",index:'id',width:50,formatter:operateFormatter,sortable:false,frozen:true},
		        {name:"englishName",index:'englishName',label:'英文名',width:80,formatter:nameFont,frozen:true},
		        {name:"gender",label:'性别',width:50,align:'center',formatter : genderFormatter },
		        {name:'certificateType',label:'证件种类',width:100,formatter : certificateTypeFormatter},
		        {name:'certificateNo',label:'证件号码',width:160,formatter:certificateNoFont,frozen:true},
		        {name:'name',label:'姓名',width:100,hidden:true},
		        {name:'currentAddress',label:'常住地址',width:150},
		        {name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:330,hidden:true},
		        {name:"nationality",label:'国籍',width:80},
		        {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		        {name:'updateDate', sortable:false, label:'数据更新时间', width:140},
		        {name:'birthday', sortable:false, label:'出生日期', width:90,hidden:true},
		   		{name:'telephone',label:'固定电话',sortable:false, width:120,hidden:true},
		   		{name:'mobileNumber',label:'联系手机',index:"mobileNumber",sortable:false, width:100,hidden:true},
	      		{name:'workUnit',label:'工作单位或就读学校',sortable:false, width:160,hidden:true},
	      		{name:'arrivalTime',label:'抵达时间',sortable:false, width:160,hidden:true},
	      		{name:'leaveTime',label:'离开时间',sortable:false, width:160,hidden:true},
	      		{name:'profession',label:'职业',sortable:false, width:160,hidden:true,align:'center',formatter : professionFormatter},
		        {name:"logOut",sortable:false,hidden:true,hidedlg:true,width:100}
			],
			width:860,
		  	height:430,
		  	multiselect:true,
		    loadComplete: isEmphasisFormatter,
			ondblClickRow: function (rowId){
				if(dfop.hasViewPermission == 'true'){
					viewDialog(rowId);
				}
			},
		    onSelectRow:selectRow
		});
		if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
			if(dfop.isSearch == 1){
				searchOverseaPersonnel();
			}else{
				fastSearchfun(getOrgIdForStat());
			}
		}
	});
	function getOrgIdForStat(){
		var orgIdForStat = $("#orgIdForStat").val();
		if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
			return getCurrentOrgId();
		}else{
			return orgIdForStat;
		}
	}
	
	function searchOverseaPersonnel(){
		$("#overseaPersonnelList").setGridParam({
			url: PATH + "/baseinfo/overseaPersonnelSearch/searchOverseaPersonnel.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#overseaPersonnelList").setPostData($.extend({"searchOverseaPersonnelVo.logOut":$("#isLock").val(),"orgId":getCurrentOrgId()},$("#searchOverseaPersonnelForm").serializeObject()));
	    $("#overseaPersonnelList").trigger("reloadGrid");
	    $("#OVERSEAPERSONNELstatisticsDialog").dialog("close");
	}
	
	
	
	
	function viewDialog(id){
		var overseaPersonnel =  $("#overseaPersonnelList").getRowData(id);
		$("#viewOverseaPersonnelMaintanceDialog").createDialog({
			width: dialogWidth,
			height: 450,
			title:'查看'+title+'信息',
			url: PATH + '/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+overseaPersonnel.encryptId,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	
	
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
	function certificateNoFont(el,options,rowData){
		if(rowData.logOut==1){
			return "<font color='#778899'>"+rowData.certificateNo+"</font>";
		}
		return "<font color='#000'>"+rowData.certificateNo+"</font>";
	}
	function nameFont(el,options,rowData){
		if(rowData.death == true || rowData.death == "true"){
			return "<a href='javascript:viewDialog("+rowData.id+")'><font color='red'>"+rowData.englishName+"</font></a>";
		}
		if(rowData.logOut==1||rowData.logOut=='1'){
			return "<a href='javascript:viewDialog("+rowData.id+")'><font color='#778899'>"+rowData.englishName+"</font></a>";
		}
		return "<a href='javascript:viewDialog("+rowData.id+")'><font color='#6633FF'>"+rowData.englishName+"</font></a>";
	}
	
	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#overseaPersonnelList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#overseaPersonnelList").getRowData(idCollection[i]);
				if(ent.logOut==1 || ent.logOut=='1'){
				$("#overseaPersonnelList tr[id="+idCollection[i]+"]").css('color','#778899');
			}
		}
	}
	function viewOverseaPersonnel(){
		var selectedId = $("#overseaPersonnelList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		$("#overseaPersonnelMaintanceDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'查看'+title+'信息',
			url: PATH + '/baseinfo/overseaPersonnelManage/dispatchOperate.action?mode=view&overseaPersonnel.id='+selectedId,
			buttons: {
				"打印" : function(){
	        	print();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	
	function print(){
		var selectedId = $("#overseaPersonnelList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		$("#overseaPersonnelMaintanceDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight+20,
			title:'打印'+title+'信息',
			url: PATH + '/baseinfo/overseaPersonnelManage/dispatchOperate.action?mode=print&overseaPersonnel.id='+selectedId,
			buttons: {
				"确定" : function(){
				$("#overseaPersonnelPrint").printArea();
	        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	
	
	
	function selectRow(){
		var selectedCounts = getActualjqGridMultiSelectCount("overseaPersonnelList");
		var count = $("#overseaPersonnelList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("overseaPersonnelList", true);
		}else{
			jqGridMultiSelectState("overseaPersonnelList", false);
		}
		if(selectedCounts==1){
			$("#view").buttonEnable();
			$("#update").buttonEnable();
			$("#delete").buttonEnable();
			var selectedId = $("#overseaPersonnelList").getGridParam("selrow");
			var druggy =  $("#overseaPersonnelList").getRowData(selectedId);
			if(druggy.logOut!=1){
				$("#logOut").buttonEnable();
				$("#cancelLogOut").buttonDisable();
			}else{
				$("#cancelLogOut").buttonEnable();
				$("#logOut").buttonDisable();
			}
		}else{
			$("#view").buttonDisable();
			$("#update").buttonDisable();
			$("#cancelLogOut").buttonDisable();
			$("#logOut").buttonDisable();
		}
		if(isGrid()){
			$("#shift").buttonEnable();
		}else{
			$("#shift").buttonDisable();
		}
		if(selectedCounts==0){
			$("#delete").buttonDisable();
			$("#shift").buttonDisable();
		}
	}
	function getIdCardNo(){
		var idCardNo = "";
		$("#conditionIdCardNo").length > 0?idCardNo = $("#conditionIdCardNo").val():idCardNo = $("#searchByIdcard").val();
		return idCardNo;
	}
	
	function getSelectedIds(){
		var selectedIds = $("#overseaPersonnelList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	
	
	function fastSearchfun(orgId){
		var condition = $("#searchByCondition").val();
		if(condition == '请输入姓名或身份证号码') {
			var initParam = {
					"orgId": orgId,
					"searchOverseaPersonnelVo.logOut":0
			}
		}else{
			var initParam = {
					"orgId": orgId,
					"searchOverseaPersonnelVo.logOut":0,
					"searchOverseaPersonnelVo.fastSearchKeyWords":condition
			}
		}
		$("#overseaPersonnelList").setGridParam({
			url: PATH + '/baseinfo/overseaPersonnelSearch/fastSearchOverseaPersonnel.action',
			datatype: "json",
			page:1
		});
		$("#overseaPersonnelList").setPostData(initParam);
		$("#overseaPersonnelList").trigger("reloadGrid");
	}
}