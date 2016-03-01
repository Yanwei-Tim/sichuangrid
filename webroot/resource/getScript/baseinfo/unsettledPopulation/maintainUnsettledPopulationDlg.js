TQ.maintainUnsettledPopulationDlg = function (dfop){
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}
	$(document).ready(function(){
		if($("#unsettledPopulation-idCardNo").val()!=null && $("#unsettledPopulation-idCardNo").val() != "" ){
			$("#unsettledPopulation-gender").attr("disabled",true);
			if(dfop.isUpdate == 'true'){
				$("#unsettledPopulation-idCardNo").attr("readonly","readonly");
			}
		}
		if(dfop.fromClaim == 'true'){
			populationHasHouseInfoChanged();
		}
		// 根据房屋编号自动获取实有房屋信息
		function searchHoseInfoByCode(){
			$.ajax({
				async: false ,
				url: PATH + "/baseinfo/actualHouseManage/getHouseInfoByHouseCode.action",
			   	data:{
					"searchHouseInfoVo.houseCode":$('#houseCode').val(),
					"searchHouseInfoVo.houseTypeId":$('#actualHouseTypeId').val()
		        },
				success:function(date){
					if(null != date) {
						procCurrentAddressType(date);
					} else {
						$("#address").val("");
						$("#community").val("");
						$("#block").val("");
						$("#unit").val("");
						$("#room").val("");
						$("#currentAddress").val("");
					}
				}
			});
		}
	
		$("#unsettledPopulation-idCardNo").blur(function(value){
			if($("#unsettledPopulation-idCardNo").val()!=null && $("#unsettledPopulation-idCardNo").val()!="" && typeof($("#unsettledPopulation-idCardNo").val())!="undefined"){
				$("#unsettledPopulation-gender").attr("disabled",true);
			}else{
				$("#unsettledPopulation-gender").attr("disabled",false);
			}
			fillGenderByIdCardNo($("#unsettledPopulation-idCardNo").val(),"unsettledPopulation-gender","",true);
		});
		function procCurrentAddressType(date) {
			$("#houseId").val(date.id);
			$("#addressTypeId").val(date.addressType.id);
			$("#currentAddressType").val(date.addressType.id);
			if($("#currentAddressType").find("option:selected").attr("internalId")== dfop.currentAddressType) {
				$("#building").show();
				$("#otherAddress").hide();
				$("#community").val(date.community);
				$("#block").val(date.block);
				$("#unit").val(date.unit);
				$("#room").val(date.room);
				$("#address").val(date.community+"小区"+date.block+"幢"+date.unit+"单元"+date.room+"室");
			} else {
				$("#building").hide();
				$("#otherAddress").show();
				$("#currentAddress").val(date.address);
				$("#community").val("");
				$("#block").val("");
				$("#unit").val("");
				$("#room").val("");
			}
		}
		$("#houseCode").change(searchHoseInfoByCode);
		initActualHouse();
	
		$("#houseCode").houseAutoComplete({
			searchLevel:"houseCode",
			select:function(event,ui){
				renderHouseInfoForHouseCode(ui.item);
			}});
	
		function renderHouseInfoForHouseCode(house){
			$("#houseId").val(house.houseId);
			$("#addressTypeId").val(house.addressType.id);
			$("#currentAddressType").val(house.addressType.id);
			$("#community").val(house.community);
			$("#block").val(house.block);
			$("#unit").val(house.unit);
			$("#room").val(house.room);
			procCurrentAddressType(house);
			$("#houseCode").val(house.houseCode);
		}
	
		function initActualHouse() {
			$.ajax({
				async: false ,
				url: PATH + "/sysadmin/propertyManage/findPropertyDictByDomainName.action",
			   	data:{
				"propertyDomain.domainName":"住房类别"
		        },
				success:function(responseData){
		    	   if(responseData!=null&&responseData.length>0){
		        	   for(var i=0;i<responseData.length;i++){
		            	   var data = responseData[i];
		            	   if(data.internalId== dfop.houseInfoType){
		        			   $("#actualHouseTypeId").val(data.id);
		        	   		}
		        	   }
					}
		        }
			});
		}
	
		if(""!=$("#_imgUrl").val()){
			$("#img").attr("src",$("#_imgUrl").val());
		}
		$('#unsettledPopulation-unSettedTime').datePicker({
			yearRange:'1900:2030',
			dateFormat:'yy-mm-dd',
	        maxDate:'+0d'
		});
	function changeOrgId(){
		$("#unsettledPopulationOrganizationId").val($("#organizationId").val());
	}
	var unsettledPopulationOrgTree="";
	function isGridForTreeSelect(){
		if(unsettledPopulationOrgTree != ""){
			return $.getSelectedNode(unsettledPopulationOrgTree).attributes.orgLevelInternalId == dfop.organizationLevel;
		}else{
			return true;
		}
	}
	var existed = true;
	var bol = true;
	
	
		$("#tabs").tabFunction({ cache: true });
		
		$.ajax({
			async: false,
			url:  PATH + "/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#orgName").val(responseData);
			}
		});
	
		threeSelect({
			province:'province',
			city:'city',
			district:'district',
			provinceValue:$('#nativeProvinceValue').val(),
			cityValue:$('#nativeCityValue').val(),
			districtValue:$('#nativeDistrictValue').val()
		});
		$(".dialogtip").inputtip();
		$("#currentAddressType").change(chooseCurrentAddressType);
		initCurrentAddress();
	
	
		jQuery.validator.addMethod("exsistedIdCard", function(value, element){
			var flag =true;
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url: PATH + "/baseinfo/unsettledPopulationManage/hasDuplicateUnsettledPopulation.action",
			   	data:{
				"unsettledPopulation.idCardNo":$("#unsettledPopulation-idCardNo").val(),
				"ownerOrg.id":$("#organizationId").val(),
				"mode":$('#mode').val(),
				"unsettledPopulation.id":$("#mode").val() == "add"?"-1":$('#unsettledPopulation-id').val()
		        },
				success:function(responseData){
					flag = !eval(responseData);
				}
			});
			return flag;
		});
	
		jQuery.validator.addMethod("isGridOrganization", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			if(isGridForTreeSelect()){
				return true;
			}else{
				return false;
			}
	
		});
	
		function renderHouseInfoFromCBUR(house){
			$("#community").val(house.community);
			$("#block").val(house.block);
			$("#unit").val(house.unit);
			$("#room").val(house.room);
			businessHouseToCurrentAddress();
			$("#houseId").val(house.houseId);
		}
	
		function renderHouseInfoFromADD(house){
			$("#community").val("");
			$("#block").val("");
			$("#unit").val("");
			$("#room").val("");
			$("#currentAddress").val(house.address);
			$("#houseId").val(house.houseId);
		}
	
		function searchHouse(searchByAddress,searchLevel){
			$.ajax({
				async: false ,
				url: PATH + "/baseinfo/houseAutoComplete/findSingleHousesIdByAddressLib.action",
			   	data:{
					 "orgId":getCurrentOrgId(),
					 "searchByAddress":searchByAddress,
					 "searchType":searchLevel,
					 "community":function(){return $("#community").val()},
					 "block":function(){return $("#block").val()},
					 "unit":function(){return $("#unit").val()},
					 "searchCondition":function(){return searchByAddress?$("#currentAddress").val(): $("#room").val();}
		        },
				success:function(responseData){
		    		if(responseData==null||responseData==undefined||responseData==""||responseData=="null"){
		    			$("#houseId").val("");
			    	}else{
			        	$("#houseId").val(responseData);
				    }
		        }
			});
		}
	
		jQuery.validator.addMethod("currentAddress", function(value, element){
			//var other = $("#other").val();
			//if(other!=null&& $("#currentAddressType").val()== other.split("-")[0]){
				if($("#currentAddressType").find("option:selected").attr("internalId")==dfop.currentAddressTypeOther){
					//searchHouse(true,"");
					var livingHouse=$("#currentAddress").val();
					return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
	//				if(value==null||value==undefined||value==""){
	//	    			return false;
	//	    		}else{
	//	    			return true;
	//	    		}
				}
			//}
		  return true;
		});
	
		jQuery.validator.addMethod("community", function(value, element){
			//var businessHouse = $("#businessHouse").val();
			//if(businessHouse!=null&& $("#currentAddressType").val()== businessHouse.split("-")[0]){
				if($("#currentAddressType").find("option:selected").attr("internalId")== dfop.currentAddressType) {
					var livingHouse=$("#community").val();
					return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
				}
			//}
		  return true;
		});
	
		resetBirthdayField($("#unsettledPopulation-birthday").val());
		$('#unsettledPopulation-idCardNo').blur(idCardChanged);
	
		$('#unsettledPopulation-FristDate').datePicker({
			yearRange: '1900:2060',
			dateFormat:'yy-mm-dd',
			maxDate:'+0y'
		});
	
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
			$("#_imgUrl").attr("src",$("#_imgUrl").val());
			businessHouseToCurrentAddress();
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(!data.id){
							$.messageBox({
								message:data,
								evel: "error"
							});
							return;
						}
						if($("#populationHasHouseInfo").val()=="false"){
						if("add"==$("#mode").val()){
							 if(($("#isLock").val()=='1'&&$("#logOut").val()!="1")||($("#isLock").val()=='2'&&$("#isDeath").val()!="1")){
						 		$("#unsettledPopulationList").addRowData(data.id,data,"first");
						 		$("#unsettledPopulationList").setSelection(data.id);
					 		}
							 $("#unsettledPopulationList").trigger("reloadGrid");
							 $.messageBox({message:"未落户人口新增成功"});
						 }
						 if("edit"==$("#mode").val()){
							 if(data.death == true || data.death == "true"){
						 		if(($("#isLock").val()=='1'&&$("#logOut").val()=="0")||($("#isLock").val()=='2'&&$("#isDeath").val()=="0")){
							 		$("#unsettledPopulationList").delRowData(data.id);
						 		}else{
							 		$("#unsettledPopulationList").setRowData(data.id,data);
							 		$("#"+data.id+"").css('color','#778899');
							 		$("#unsettledPopulationList").setSelection(data.id);
						 		}
					 		}else{
						        if($("#isLock").val()=='2'&&$("#isDeath").val()=="1"){
							 		$("#unsettledPopulationList").delRowData(data.id);
								}else{
							 		$("#unsettledPopulationList").setRowData(data.id,data);
							 		$("#unsettledPopulationList").setSelection(data.id);
						 		}
					 		}
							 $("#unsettledPopulationList").trigger("reloadGrid");
							 $.messageBox({message:"未落户人口修改成功"});
							 disableButtons();
							 checkExport();
						 }
						 }
						 $("#" + dfop.dailogName).proccessSuccess(data.encryptId,data);
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			ignore:":hidden",
			rules:{
	
			},
			messages:{
	
	
			}
		});
	
	
		function businessHouseToCurrentAddress(){
			var businessHouse = $("#businessHouse").val();
			var other = $("#other").val();
			var currentAddressType = $("#currentAddressType").val();
			if(currentAddressType==businessHouse.split("-")[0]){
				var community = $("#community").val();
				var block = $("#block").val();
				var unit = $("#unit").val();
				var room = $("#room").val();
				var currentAddress ="";
	
				if(community!=null&&community!=undefined&&community!=""){
					currentAddress += community+"小区";
				}
				if(block!=null&&block!=undefined&&block!=""){
					currentAddress += block+"幢";
				}
				if(unit!=null&&unit!=undefined&&unit!=""){
					currentAddress += unit+"单元";
				}
				if(room!=null&&room!=undefined&&room!=""){
					currentAddress += room+"室";
				}
				 $("#currentAddress").val(currentAddress);
			}else{
				$("#community").val("");
				$("#block").val("");
				$("#unit").val("");
				$("#room").val("");
			}
		}
		$(".dialogtip").inputtip();
		if(dfop.modeType == 'true'){
			$("#orgName").click(function(event){
				$("#noticeDialog").createDialog({
					width: 300,
					height: 350,
					title:'请选择一个部门',
					url: PATH + '/common/organizationSelector.jsp',
					buttons: {
						"确定" : function(){
							closeDialog();
						},
						"取消" : function(){
							$("#noticeDialog").dialog("close");
						}
					}
				});
			});
		}
	
		if(dfop.dialog == 'true'){
			unsettledPopulationOrgTree=$("#orgName").treeSelect({
				inputName:"ownerOrg.id",
				url:"/sysadmin/orgManage/loadTownForExtTree.action",
				onSelect:changeOrgId
			});
			//$.setTreeValue($("#organizationId").val(),tree);
			var bol = false;
			$.ajax({
				async:false,
				url: PATH + "/issue/issueManage/checkOccurOrgId.action",
				data:{
					"issueNew.occurOrg.id": dfop.orgId
				},
				success:function(responseData){
					bol = responseData;
				}
			});
			var url = "";
			if(bol){
				url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIdsWhenRootIsTown.action?organization.id="+$("#organizationId").val()
			}else{
				url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+$("#organizationId").val()
			}
			$.ajax({
				url:url,
				success:function(data){
					$.searchChild(unsettledPopulationOrgTree,data);
				}
			});
		}
	});
	
	function setZone(selectOrgId,selectOrgName){
		if(dfop.modeType == 'true'){
			$("#organizationId").val(selectOrgId);
			$("#orgName").val(selectOrgName);
		}
	}
	
	function resetBirthdayField(text){
		if (text!="" && $('#unsettledPopulation-idCardNo').val()!=""){
			$("#birthdayDiv").html("<input type='text' name='unsettledPopulation.birthday' id='unsettledPopulation-birthday' class='form-txt' value='"+text+"' readonly/>");
		}else{
			$('#unsettledPopulation-birthday').datePicker({
				yearRange: '1900:2030',
	    		dateFormat: 'yy-mm-dd',
	        	maxDate:'+0d'
	        });
		}
	}
	function idCardChanged(){
		var text=$('#unsettledPopulation-idCardNo').val();
		text=getBirthDayTextFromIdCard(text);
		resetBirthdayField(text);
		text = $('#unsettledPopulation-idCardNo').val();
		getCommonPopulation(text);
	}
	
	function getCommonPopulation(idCardNo){
		if( idCardNo.length != null && idCardNo.length == 18){
			ajaxForCommonPopulation(idCardNo);
		}else if( idCardNo.length != null && idCardNo.length == 15){
			ajaxForCommonPopulation(idCardNo);
		}
	}
	
	
	function ajaxForCommonPopulation(idCardNo){
		$.ajax({
			async:false,
			url:  PATH + "/commonPopulation/commonPopulationManage/getCommonPopulationByIdCardNo.action",
			data:{
				"commonPopulation.idCardNo":idCardNo
			},
			success:function(responseData){
				manageCommonPopulation(responseData);
			}
		});
	}
	
	function manageCommonPopulation(responseData){
		//省市县的选中
		threeSelect({
			province:'province',
			city:'city',
			district:'district',
			provinceValue:responseData.province,
			cityValue:responseData.city,
			districtValue:responseData.district
		});
	
	}
	function getBirthDayTextFromIdCard(idCard){
		if(idCard!=null&&idCard.length==18){
			idCard=idCard.substring(6,14);
			if(idCard.substring(4,6)<=0||idCard.substring(4,6)>12){
				return "";
			}else if(idCard.substring(6,8)<=0||idCard.substring(6,8)>31){
				return "";
			}else{
				return idCard.substring(0,4)+"-"+idCard.substring(4,6)+"-"+idCard.substring(6,8);
			}
		}else if(idCard!=null&&idCard.length==15){
			idCard=idCard.substring(6,12);
			if(idCard.substring(2,4)<=0||idCard.substring(2,4)>12){
				return "";
			}else if(idCard.substring(4,6)<=0||idCard.substring(4,6)>31){
				return "";
			}else{
				return "19"+idCard.substring(0,2)+"-"+idCard.substring(2,4)+"-"+idCard.substring(4,6);
			}
		}
		return "";
	}
	
	function getUnsettledPopulation(){
		$.ajax({
			async: false,
			url:  PATH + "/baseinfo/unsettledPopulationManage/getUnsettledPopulationByIdCardNo.action",
			data:{
				"unsettledPopulation.organization.id":$("#organizationId").val(),
				"unsettledPopulation.idCardNo":$("#unsettledPopulation-idCardNo").val()
			},
			success:function(responseData){
				if(responseData.id){
					$("#mode").val("edit");
					$("#maintainForm").attr("action", PATH + "/baseinfo/unsettledPopulationManage/updateUnsettledPopulation.action");
					$("#unsettledPopulation-id").val(responseData.id);
					$("#name").val(responseData.name);
					$("#usedName").val(responseData.usedName);
					$("#nativeProvinceValue").val(responseData.province);
					$("#nativeCityValue").val(responseData.city);
					$("#nativeDistrictValue").val(responseData.district);
					$("#unsettledPopulation-gender").val(responseData.gender.id);
					$("#populationGender").val(responseData.gender.id);
					$("#unsettledPopulation-bloodType").val(responseData.bloodType!=null?responseData.bloodType.id:"");
					$("#nativePlaceAddress").val(responseData.nativePlaceAddress!=null?responseData.nativePlaceAddress:"");
					$("#currentAddress").val(responseData.currentAddress);
					$("#politicalBackground").val(responseData.currentAddress);
					$("#workUnit").val(responseData.workUnit!=null?responseData.workUnit:"");
					$("#remark").val(responseData.remark!=null?responseData.remark:"");
					if(responseData.isDeath==true){
					 $("#unsettledPopulation-isDeath").attr("checked","checked");
					}else{
						 $("#unsettledPopulation-isDeath").removeAttr("checked");
					}
					threeSelect({
						province:'province',
						city:'city',
						district:'district',
						provinceValue:$('#nativeProvinceValue').val(),
						cityValue:$('#nativeCityValue').val(),
						districtValue:$('#nativeDistrictValue').val()
					});
				}
			}
		});
	}
	
	function emptyObject(){
					$("#name").val("");
					$("#usedName").val("");
					$("#unsettledPopulation-idCardNo").val("");
					$("#nativeProvinceValue").val("");
					$("#unsettledPopulation-gender").val("");
					$("#populationGender").val("");
					$("#unsettledPopulation-bloodType").val("");
					$("#nativeCityValue").val("");
					$("#nativeDistrictValue").val("");
					$("#nativePlaceAddress").val("");
					$("#unsettledPopulation-birthday").val("");
					$("#telephone").val("");
					$("#currentAddress").val("");
					$("#politicalBackground").val("");
					$("#isTreat").removeAttr("checked");
					$("#mobileNumber").val("");
					$("#workUnit").val("");
					$("#address").val("");
					$("#otherAddress").val("");
					$("#career").val("");
					$("#school").val("");
					$("#unsettledPopulation-unSettedReason").val("");
					$("#unsettledPopulation-unSettedTime").val("");
					$("#stature").val("");
					$("#unsettledPopulation-certificateType").val("");
					$("#unsettledPopulation-certificateNo").val("");
					$("#unsettledPopulation-faith").val("");
					$("#unsettledPopulation-maritalState").val("");
					$("#residenceType").val("");
					$("#unsettledPopulation-nationality").val("");
					$("#unsettledPopulation-schooling").val("");
					$("#email").val("");
					$("#unsettledPopulation-remark").val("");
					threeSelect({
						province:'province',
						city:'city',
						district:'district',
						provinceValue:$('#nativeProvinceValue').val(),
						cityValue:$('#nativeCityValue').val(),
						districtValue:$('#nativeDistrictValue').val()
					});
	
	}
	
	function initCurrentAddress(){
		$.ajax({
			async: false ,
			url: PATH + "/sysadmin/propertyManage/findPropertyDictByDomainName.action",
		   	data:{
			"propertyDomain.domainName":"现居住址类型"
	        },
			success:function(responseData){
	    	   if(responseData!=null&&responseData.length>0){
	        	   for(var i=0;i<responseData.length;i++){
	            	   var data = responseData[i];
	            	   if(data.internalId == dfop.currentAddressType){
	        			   $("#businessHouse").val(data.id+"-"+data.internalId);
	        	   		}else{
	        	   		   $("#other").val(data.id+"-"+data.internalId);
	        	   		}
	        	   }
				}
	        }
		});
		chooseCurrentAddressType();
	}
	
	function chooseCurrentAddressType(){
		var businessHouse = $("#businessHouse").val();
		var other = $("#other").val();
		var currentAddressType = $("#currentAddressType").val();
		if(currentAddressType==businessHouse.split("-")[0]){
			$("#building").show();
			$("#otherAddress").hide();
		}else if(currentAddressType==other.split("-")[0]){
			$("#building").hide();
			$("#otherAddress").show();
		}
	}
	jQuery.validator.addMethod("exculdeParticalChar", validatorSpecialWord);
	jQuery.validator.addMethod("validatorWorkUnit", validatorSpecialWord);
	
	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！-]");
		return this.optional(element)||!pattern.test(value) ; 
	}
	
	
	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
		return this.optional(element)||!pattern.test(value) ; 
	}
}