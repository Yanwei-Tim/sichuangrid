TQ.floatingPopulationStatistics = function (dfop){
	function search(orgId){
		var condition = $("#searchByCondition").val();
		var initParam = {
			"organizationId": orgId
		}
		if(condition == '请输入姓名或身份证号码'){
			 initParam = {
				"organizationId": orgId,
				"searchFloatingPopulationVo.logOut":0,
				"searchFloatingPopulationVo.isDeath":false
			}
		}else{
			 initParam = {
				"organizationId": orgId,
				"searchFloatingPopulationVo.logOut":0,
				"searchFloatingPopulationVo.isDeath":false,
				"searchFloatingPopulationVo.fastSearchKeyWords":condition
			}
		}
		$("#floatingPopulationList").setGridParam({
			url: PATH + '/baseinfo/floatingPopulationManage/fastSearchFloatingPopulation.action',
			datatype: "json",
			page:1
		});
		$("#floatingPopulationList").closest(".ui-jqgrid-bdiv").css({ "height" : $("#floatingPopulationListDiv").parent().height()-70});
		$("#floatingPopulationList").setPostData(initParam);
		$("#floatingPopulationList").trigger("reloadGrid");
	}
	
	function nativePlaceFormatter(el, options, rowData){
		var str = "";
		if(rowData.province != null)
			str += rowData.province;
		if(rowData.city != null)
			str += rowData.city;
		if(rowData.district != null)
			str += rowData.district;
		return str;
	}
	
	function inflowingSourceFormatter(el, options, rowData){
		var str = "";
		if(rowData.inflowingProvince != null)
			str += rowData.inflowingProvince;
		if(rowData.inflowingCity != null)
			str += rowData.inflowingCity;
		if(rowData.inflowingDistrict != null)
			str += rowData.inflowingDistrict;
		return str;
	}
	
	function idCardNoFont(el,options,rowData){
		if(rowData.logOut==1||rowData.logOut=='1'){
			return "<font color='#778899'>"+rowData.idCardNo+"</font>";
		}else{
			return "<font color='#000'>"+rowData.idCardNo+"</font>";
		}
	}
	
	var dialogWidth=800;
	var dialogHeight=600;
	$(document).ready(function(){
		$("#floatingPopulationList").jqGridFunction({
			datatype: "local",
			colModel:[
		        {name:"id", index:"id", hidden:true,frozen:true},
		        {name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
		        {name:"operator", index:'id', label:'操作',formatter:operateFormatter,width:50,frozen:true,sortable:false,align:'center' },
		        {name:"name", index:'name', label:'姓名',formatter:nameFont,width:80,frozen:true },
		        {name:'gender',formatter:genderFormatter, label:'性别', align:'center', width:40},
		        {name:'idCardNo-formmater',index:'idCardNo', label:'身份证号码', formatter:idCardNoFont, width:130,frozen:true},
		        {name:'idCardNo',index:'idCardNo',label:'身份证号码', hidden:true,hidedlg:true},
		        {name:'currentAddress', sortable:false, label:'常住地址', width:150 },
		        {name:'workUnit', sortable:false, label:'工作单位或就读学校', width:200},
		        {name:'inflowingReason', sortable:false,formatter:inflowingReasonFormatter, label:'流入原因', width:80},
	
		        {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		        {name:'updateDate', sortable:false, label:'数据更新时间', width:140},
		        {name:"usedName", index:'usedName', label:'曾用名/别名', width:80 ,hidden:true},
		        {name:'organization.orgName',sortable:false,index:'organization.orgName',label:'所属网格',width:200,hidden:true},
		        {name:'mobileNumber', sortable:false, label:'联系手机', width:80,hidden:true},
		        {name:'telephone', sortable:false, label:'固定电话', width:80,hidden:true},
		        {name:"birthday", index:'birthday', label:'出生日期', width:80 ,hidden:true},
		        {name:"nation",sortable:false,label:"民族",formatter:nationFormatter,width:80, hidden:true },
		        {name:"politicalBackground",sortable:false,label:"政治面貌",formatter:politicalBackgroundFormatter,width:80,hidden:true},
		        {name:"schooling",sortable:false,label:"文化程度",formatter:schoolingFormatter,width:80, hidden:true },
		        {name:'career', sortable:false,formatter:careerFormatter,label:'职业', width:80,hidden:true},
		        {name:"maritalState",sortable:false,label:"婚姻状况",formatter:maritalStateFormatter,width:80, hidden:true },
		        {name:"stature",sortable:false,label:"身高(cm)",width:80, hidden:true },
		        {name:"bloodType",sortable:false,label:"血型",formatter:bloodTypeFormatter,width:80, hidden:true },
		        {name:"faith",sortable:false,label:"宗教信仰",formatter:faithFormatter,width:80, hidden:true },
		        {name:"email",sortable:false,label:"电子邮箱",width:80, hidden:true },
		        {name:'province',index:'province',sortable:false,label:'户籍地',formatter:nativePlaceFormatter,width:150,hidden:true},
		        {name:'nativePlaceAddress', sortable:false, label:'户籍地详址', width:150,hidden:true},
		        {name:'otherAddress', sortable:false, label:'临时居所', width:150,hidden:true},
		        {name:'inflowingDate',index:'inflowingDate', sortable:false, label:'流入时间', width:80,hidden:true},
		        {name:'inflowingProvince',index:'inflowingProvince',label:'流入来源',formatter:inflowingSourceFormatter,width:150,hidden:true},
		        {name:'registrationType', sortable:false,formatter:registrationTypeFormatter, label:'登记证情况', width:80,hidden:true},
		        {name:'registerDate', sortable:false, label:'登记日期', width:80,hidden:true},
		        {name:'expectedDatedue', sortable:false, label:'预计到期日期', width:80,hidden:true},
		        {name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
		        {name:'logOut',sortable:false,hidden:true,hidedlg:true,width:80}
			],
			width:870,
		   	height:430,
			multiselect:true,
		  	onSelectAll:function(aRowids,status){ if(status) {
		   		var selectedIds = $("#floatingPopulationList").jqGrid("getGridParam", "selarrrow");
		   		var cancelLogOut = 0;
		   		var isLogOut = 0;
		   		for(var i = 0;i<selectedIds.length;i++){
		   			var row=$("#floatingPopulationList").getRowData(selectedIds[i]);
		   			if(selectedIds.length ==1){
				   		$("#view").buttonEnable();
						$("#update").buttonEnable();
					}
		   			if(selectedIds.length >2){
		   				$("#view").buttonDisable();
						$("#update").buttonDisable();
						$("#cancelLogOut").buttonDisable();
						$("#isLogOut").buttonDisable();
		   			}
		   			if(selectedIds.length != 0){
					   	$("#delete").buttonEnable();if(isGrid()){
							$("#shift").buttonEnable();
					   	}
				   	}
				   	if(row.logOut == 1 ||row.logOut == "1" ){
				   		cancelLogOut++;
				   	}else{
				   		isLogOut++;
				   	}
		   		}
		   		if(cancelLogOut>0&&isLogOut==0){
		   			$("#cancelLogOut").buttonEnable();
					$("#isLogOut").buttonDisable();
		   		}else if(cancelLogOut==0&&isLogOut>0){
		   			$("#cancelLogOut").buttonDisable();
					$("#isLogOut").buttonEnable();
		   		}else{
		   			$("#cancelLogOut").buttonDisable();
					$("#isLogOut").buttonDisable();
		   		}
	          }else{
	         	$("#delete").buttonDisable();
	         	$("#shift").buttonDisable();
		   		$("#view").buttonDisable();
				$("#update").buttonDisable();
				$("#cancelLogOut").buttonDisable();
				$("#isLogOut").buttonDisable();
		   	}},
			ondblClickRow: function (rowid){
				if(dfop.hasViewFloatingPopulationPermission == 'true'){
					viewFloatingPopulationInfo(rowid);
				}
			},
			loadComplete: afterLoad,
			onSelectRow: selectRow
		});
		
		
		if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
			if('<s:property value="#parameters.isSearch"/>' == 1){
				searchFloatingPopulation();
			}else{
				search(getOrgIdForStat());
			}
		}
		
		function getOrgIdForStat(){
			var orgIdForStat = $("#orgIdForStat").val();
			if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
				return getCurrentOrgId();
			}else{
				return orgIdForStat;
			}
		}
		
		jQuery("#floatingPopulationList").jqGrid('setFrozenColumns');
	
	
		
	
	});
	
	function afterLoad(){
		logOutFormatter();
		disableButtons();
		checkExport();
	}
	
	function logOutFormatter(){
		var idCollection = new Array();
		idCollection=$("#floatingPopulationList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#floatingPopulationList").getRowData(idCollection[i]);
				if(ent.logOut==1||ent.logOut=='1'){
					$("#floatingPopulationList tr[id="+idCollection[i]+"]").css('color','#778899');
				}
		}
	}
	
	function checkExport(){
		if($("#floatingPopulationList").getGridParam("records")>0 || $("#floatingPopulationList").getGridParam("selrow")!=null){
			$("#export").buttonEnable();
		}else{
			$("#export").buttonDisable();
	
		}
	}
	function disableButtons(){
		$("#update").buttonDisable();
		$("#delete").buttonDisable();
		$("#view").buttonDisable();
		$("#cancelLogOut").buttonDisable();
		$("#isLogOut").buttonDisable();
		$("#shift").buttonDisable();
	}
	function selectRow(){
		var count = $("#floatingPopulationList").jqGrid("getGridParam","records");
		var selectedCounts = getActualjqGridMultiSelectCount("floatingPopulationList");
		if(selectedCounts == count){
			jqGridMultiSelectState("floatingPopulationList", true);
		}else{
			jqGridMultiSelectState("floatingPopulationList", false);
		}
		if(selectedCounts==1){
			$("#view").buttonEnable();
			$("#update").buttonEnable();
			$("#delete").buttonEnable();
			var selectedId = getSelectedIdLast();
			var floatingPopulation =  $("#floatingPopulationList").getRowData(selectedId);
			if(floatingPopulation.logOut==1 ||floatingPopulation.logOut=="1"){
				$("#cancelLogOut").buttonEnable();
				$("#isLogOut").buttonDisable();
			}else{
				$("#isLogOut").buttonEnable();
				$("#cancelLogOut").buttonDisable();
			}
		}else{
			$("#view").buttonDisable();
			$("#update").buttonDisable();
			var selectedIds = $("#floatingPopulationList").jqGrid("getGridParam", "selarrrow");
	   		var cancelLogOut = 0;
	   		var isLogOut = 0;
	   		for(var i = 0;i<selectedIds.length;i++){
	   			var row=$("#floatingPopulationList").getRowData(selectedIds[i]);
			   	if(row.logOut == 1 ||row.logOut == "1" ){
			   		cancelLogOut++;
			   	}else{
			   		isLogOut++;
			   	}
	   		}
	   		if(cancelLogOut>0&&isLogOut==0){
	   			$("#cancelLogOut").buttonEnable();
				$("#isLogOut").buttonDisable();
	   		}else if(cancelLogOut==0&&isLogOut>0){
	   			$("#cancelLogOut").buttonDisable();
				$("#isLogOut").buttonEnable();
	   		}else{
	   			$("#cancelLogOut").buttonDisable();
				$("#isLogOut").buttonDisable();
	   		}
		}
		if(isGrid()){
			$("#shift").buttonEnable();
		}else{
			$("#shift").buttonDisable();
		}
		if(selectedCounts==0){
			$("#shift").buttonDisable();
			$("#delete").buttonDisable();
			$("#cancelLogOut").buttonDisable();
			$("#isLogOut").buttonDisable();
		}
	}
	
	function print(rowid){
		if(rowid==null){
	 		return;
		}
		var floatingPopulation =  $("#floatingPopulationList").getRowData(rowid);
		$("#floatingPopulationDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'打印'+title+'信息',
			modal : true,
			url: PATH + '/baseinfo/floatingPopulationManage/dispatch.action?mode=print&floatingPopulation.id='+floatingPopulation.id,
			buttons: {
				  "确定" : function(){
					$("#printSpaceDiv").printArea();
		        	$(this).dialog("close");
		   		},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	
	function deleteFloatingPopulation(allValue){
		$.ajax({
			url: PATH + "/baseinfo/floatingPopulationManage/deleteFloatingPopulation.action?floatingPopulationIds="+allValue,
			success:function(data){
				for(var ids=0;ids<data.length;ids++){
					$("#floatingPopulationList").delRowData(data[ids]);
				}
				$("#floatingPopulationList").trigger("reloadGrid");
			    $.messageBox({message:"已经成功删除该"+title+"信息!"});
			    disableButtons();
				checkExport();
		    }
	    });
	}
	
	function searchFloatingPopulation() {
		var conditionName = $("#conditionName").val();
		var conditionIdCardNo = $("#conditionIdCardNo").val();
		var conditionInflowingReasonId = $("#conditionInflowingReason").val();
		var conditionUsedName = $("#conditionUsedName").val();
		var conditionInflowingStartDate =  $("#conditionInflowingStartDate").val();
		var conditionInflowingEndDate =  $("#conditionInflowingEndDate").val();
		var conditionExpectedStartDatedue =  $("#conditionExpectedStartDatedue").val();
		var conditionExpectedEndDatedue =  $("#conditionExpectedEndDatedue").val();
		var conditionInflowingProvince =  $("#conditionInflowingProvince").val();
		var conditionInflowingCity =  $("#conditionInflowingCity").val();
		var conditionInflowingDistrict =  $("#conditionInflowingDistrict").val();
		var conditionRegistrationTypeId = $("#conditionRegistrationType").val();
		var conditionGenderId = $("#conditionGender").val();
		var conditionRegisterStartDate = $("#conditionRegisterStartDate").val();
		var conditionRegisterEndDate = $("#conditionRegisterEndDate").val();
		var conditionStartBirthday = $("#conditionStartBirthday").val();
		var conditionEndBirthday = $("#conditionEndBirthday").val();
		var conditionCareerId = $("#conditionCareer").val();
		var conditionWorkUnit = $("#conditionWorkUnit").val();
		var conditionProvince = $("#conditionProvince").val();
		var conditionCity = $("#conditionCity").val();
		var conditionDistrict = $("#conditionDistrict").val();
		var conditionNativePlaceAddress = $("#conditionNativePlaceAddress").val();
		var conditionCurrentAddress = $("#conditionCurrentAddress").val();
		var conditionLogOutState = $("#conditionLogOutState").val();
		var conditionIsDeathState = $("#conditionIsDeathState").val();
		var initParam = {
				"organizationId": getCurrentOrgId(),
				"searchFloatingPopulationVo.name":conditionName,
				"searchFloatingPopulationVo.idCardNo":conditionIdCardNo,
				"searchFloatingPopulationVo.inflowingReason":conditionInflowingReasonId,
				"searchFloatingPopulationVo.usedName":conditionUsedName,
				"searchFloatingPopulationVo.inflowingStartDate":conditionInflowingStartDate,
				"searchFloatingPopulationVo.inflowingEndDate":conditionInflowingEndDate,
				"searchFloatingPopulationVo.expectedStartDatedue":conditionExpectedStartDatedue,
				"searchFloatingPopulationVo.expectedEndDatedue":conditionExpectedEndDatedue,
				"searchFloatingPopulationVo.inflowingProvince":conditionInflowingProvince,
				"searchFloatingPopulationVo.inflowingCity":conditionInflowingCity,
				"searchFloatingPopulationVo.inflowingDistrict":conditionInflowingDistrict,
				"searchFloatingPopulationVo.registrationType":conditionRegistrationTypeId,
				"searchFloatingPopulationVo.gender":conditionGenderId,
				"searchFloatingPopulationVo.registerStartDate":conditionRegisterStartDate,
				"searchFloatingPopulationVo.registerEndDate":conditionRegisterEndDate,
				"searchFloatingPopulationVo.startBirthday":conditionStartBirthday,
				"searchFloatingPopulationVo.endBirthday":conditionEndBirthday,
				"searchFloatingPopulationVo.career":conditionCareerId,
				"searchFloatingPopulationVo.workUnit":conditionWorkUnit,
				"searchFloatingPopulationVo.province":conditionProvince,
				"searchFloatingPopulationVo.city":conditionCity,
				"searchFloatingPopulationVo.district":conditionDistrict,
				"searchFloatingPopulationVo.nativePlaceAddress":conditionNativePlaceAddress,
				"searchFloatingPopulationVo.currentAddress":conditionCurrentAddress
			}
		if(conditionLogOutState!=-1){
			$.extend(initParam,{"searchFloatingPopulationVo.logOut":conditionLogOutState});
		}
		if(conditionIsDeathState!=-1){
			$.extend(initParam,{"searchFloatingPopulationVo.isDeath":conditionIsDeathState});
		}
		$("#floatingPopulationList").setGridParam({
			url: PATH + '/baseinfo/floatingPopulationManage/searchFloatingPopulation.action',
			datatype: "json",
			page:1
		});
		$("#floatingPopulationList").setPostData(initParam);
		$("#floatingPopulationList").trigger("reloadGrid");
		$("#TRAMPRESIDENTstatisticsDialog").dialog("close");
	}
	
	function checkFieldIsUndefined(field){
		if (field.val() != undefined) {
			return field.val();
		} else {
			return "";
		}
	}
	
	function getSelectedIds(){
		var selectedIds='';
		var selectedIds = $("#floatingPopulationList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#floatingPopulationList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
}