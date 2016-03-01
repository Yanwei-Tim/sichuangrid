TQ.unsettledPopulationStatistics = function (dfop){
	var isgridBol = false;
	var title=$("#title").html();
	function search(orgId){
		var condition = $("#searchByCondition").val();
		if(condition == '请输入姓名或身份证号码') {
			var initParam = {
				"orgId": orgId,
				"unsettledPopulationCondition.logOut":0,
				"unsettledPopulationCondition.isDeath":0
			}
		}else{
			var initParam = {
				"orgId": orgId,
				"unsettledPopulationCondition.logOut":0,
				"unsettledPopulationCondition.isDeath":0,
				"unsettledPopulationCondition.fastSearchKeyWords":condition
			}
		}
	
		$("#unsettledPopulationList").setGridParam({
			url: PATH + '/baseinfo/unsettledPopulationSearch/fastSearchUnsettledPopulation.action',
			datatype: "json",
			page:1
		});
		$("#unsettledPopulationList").setPostData(initParam);
		$("#unsettledPopulationList").trigger("reloadGrid");
	}	
	
	$(document).ready(function(){
	
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
	
		function nameFont(el,options,rowData){
			if(rowData.death == true || rowData.death == "true"){
				return "<a href='javascript:viewUnsettledPopulation("+rowData.id+")'><font color='red'>"+rowData.name+"</font></a>";
			}
			if(rowData.logOut==1||rowData.logOut=='1'){
				return "<a href='javascript:viewUnsettledPopulation("+rowData.id+")'><font color='#778899'>"+rowData.name+"</font></a>";
			}
			return "<a href='javascript:viewUnsettledPopulation("+rowData.id+")'><font color='#6633FF'>"+rowData.name+"</font></a>";
		}
		
		function idCardNoFont(el,options,rowData){
			var idCardNo = rowData.idCardNo;
		    if(idCardNo==undefined||idCardNo=='undefined'){
		    	idCardNo = "";
		    }
			if(rowData.logOut==1||rowData.logOut=='1'){
				return "<font color='#778899'>"+idCardNo+"</font>";
			}else{
				return "<font color='#000'>"+idCardNo+"</font>";
			}
		}
		
		function operateFormatter(el, options, rowData){
			return dfop.hasViewPermission == 'true' ? "<a href='javascript:viewUnsettledPopulation("+rowData.id+")'><U><font color='#6633FF'>查看</font></U></a>" : '';
		}
	
		$("#unsettledPopulationList").jqGridFunction({
			datatype: "local",
			colModel:[
		        {name:"id",index:"id",hidden:true,frozen:true},
		        {name:'organization.id',index:'organization.id',hidden:true,hidedlg:true,frozen:true},
		        {name:"operator",label:"操作",index:'id',width:50,formatter:operateFormatter,sortable:false,frozen:true},
		        {name:"name",index:'name',label:'姓名',sortable:true,width:60,formatter:nameFont,frozen:true},
		        {name:"gender",label:'性别',sortable:true,width:50,align:'center',formatter : genderFormatter },
		        {name:'idCardNo',label:'身份证号码',sortable:true,width:140,formatter:idCardNoFont,frozen:true},
		        {name:'currentAddress',label:'常住地址',sortable:false,width:120},
	      		{name:'workUnit',label:'工作单位或就读学校',sortable:false, width:100},
		        {name:'usedName',label:'曾用名',sortable:false,width:100,hidden:true},
	      		{name:'organization.orgName',index:'orgInternalCode',label:'所属网格',width:140,sortable:true,hidden:true},
	      		{name:"province",label:"户籍地", width:200,sortable:false,formatter:householdRegisterFormatter, hidden:true},
		        {name:'unSettedTime',label:'未落户时间',sortable:false,width:120,hidden:true},
		        {name:'unSettedReason',label:'未落户原因',formatter:unSettedReasonFormatter,sortable:false,width:120},
		        {name:'sourcesState',index:'sourcesState',label:"数据来源",sortable:false,hidden:true,formatter:sourceStateFormatter,width:80,align:'center'},
		        {name:'updateDate', sortable:false, label:'数据更新时间', width:140},
		        {name:'certificateType',label:'持证种类',formatter:certificateTypeFormatter,sortable:true,width:80,hidden:true},
		        {name:'certificateNo',label:'持证编号',sortable:false,width:100,hidden:true},
		    	{name:"birthday",label:"出生日期", width:200,sortable:false,hidden:true},
		        {name:'bloodType',label:'血型',formatter:bloodTypeFormatter,sortable:false,width:100,hidden:true},
		        {name:'politicalBackground',label:'政治面貌',formatter:politicalBackgroundFormatter,sortable:false,width:100,hidden:true},
		        {name:'nativePlaceAddress',label:'户籍地详址',sortable:false,width:100,hidden:true},
		        {name:'otherAddress',label:'临时居所',sortable:false,width:100,hidden:true},
		        {name:'mobileNumber',label:'联系手机',sortable:false,width:100,hidden:true},
		        {name:'telephone',label:'固定电话',sortable:false,width:100,hidden:true},
		        {name:'career',label:'职业',formatter:careerFormatter,sortable:false,width:100,hidden:true},
		        {name:'maritalStatus',label:'婚姻状况',formatter:maritalStateFormatter,sortable:false,width:100,hidden:true},
		        {name:'stature',label:'身高(cm)',sortable:false,width:100,hidden:true},
		        {name:'nation',label:'民族',formatter:nationFormatter,sortable:false,width:100,hidden:true},
		        {name:'faith',label:'宗教信仰',formatter:faithFormatter,sortable:false,width:100,hidden:true},
		        {name:'schooling',label:'文化程度',formatter:schoolingFormatter,sortable:false,width:100,hidden:true},
		        {name:'email',label:'电子邮箱',sortable:false,width:100,hidden:true},
		        {name:'death',sortable:false,hidden:true,hidedlg:true,width:80},
		        {name:'logOut',sortable:false,hidden:true,hidedlg:true,width:80}
			],
		  	onSelectAll:function(aRowids,status){
		  	  if(status){
		   		var selectedIds = $("#unsettledPopulationList").jqGrid("getGridParam", "selarrrow");
		   		var cancelLogOut = 0;
		   		var isLogOut = 0;
		   		for(var i=0; i<selectedIds.length;i++){
		   			var row=$("#unsettledPopulationList").getRowData(selectedIds[i]);
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
					   	$("#delete").buttonEnable();
					   	if(isGrid()){
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
		    loadComplete: logOutFormatter,
		    multiselect:true,
		    ondblClickRow: function (rowId){
		    	if(dfop.hasViewPermission == 'true'){
		    		viewUnsettledPopulation(rowId);
		    	}		    	
		    },
		    onSelectRow:selectRow
		});
	
		function logOutFormatter(){
			var idCollection = new Array();
			idCollection=$("#unsettledPopulationList").getDataIDs();
				for(var i=0;i<idCollection.length;i++){
					var ent =  $("#unsettledPopulationList").getRowData(idCollection[i]);
					if(ent.logOut==1||ent.logOut=='1'){
						$("#unsettledPopulationList tr[id="+idCollection[i]+"]").css('color','#778899');
					}
			}
		}
		
		if (getOrgIdForStat() !=null && getOrgIdForStat()!=""){
			if(dfop.isSearch == 1){
				searchUnsettledPopulation();
			}else{
				search(getOrgIdForStat());
			}
		}
		
	});
	jQuery("#unsettledPopulationList").jqGrid('setFrozenColumns');
	
	$("#unsettledPopulationList").closest(".ui-jqgrid-bdiv").css({ "height" : $("#unsettledPopulationListDiv").parent().height()-70});
	function getOrgIdForStat(){
		var orgIdForStat = $("#orgIdForStat").val();
		if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
			return getCurrentOrgId();
		}else{
			return orgIdForStat;
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
		if(selectedCounts==1){
			$("#view").buttonEnable();
			$("#update").buttonEnable();
			$("#delete").buttonEnable();
			var selectedId = getSelectedIdLast();
			var unsettledPopulation =  $("#unsettledPopulationList").getRowData(selectedId);
			if(unsettledPopulation.logOut==1 ||unsettledPopulation.logOut=="1"){
				$("#cancelLogOut").buttonEnable();
				$("#isLogOut").buttonDisable();
			}else{
				$("#isLogOut").buttonEnable();
				$("#cancelLogOut").buttonDisable();
			}
		}else{
			$("#view").buttonDisable();
			$("#update").buttonDisable();
			var selectedIds = $("#unsettledPopulationList").jqGrid("getGridParam", "selarrrow");
	   		var cancelLogOut = 0;
	   		var isLogOut = 0;
	   		for(var i = 0;i<selectedIds.length;i++){
	   			var row=$("#unsettledPopulationList").getRowData(selectedIds[i]);
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
		if(selectedCounts==0){
			$("#delete").buttonDisable();
			$("#cancelLogOut").buttonDisable();
			$("#isLogOut").buttonDisable();
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
			url: PATH + '/baseinfo/unsettledPopulationSearch/searchUnsettledPopulation.action',
			datatype: "json",
			page:1
		});
		$("#unsettledPopulationList").setPostData(initParam);
		$("#unsettledPopulationList").trigger("reloadGrid");
		 $("#UNSETTEDPOPULATIONstatisticsDialog").dialog("close");
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
	$("#refreshSearchKey").click(function(){$("#searchByCondition").attr("value","");});
}