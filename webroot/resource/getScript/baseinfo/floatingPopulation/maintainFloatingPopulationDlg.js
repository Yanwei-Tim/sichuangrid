TQ.maintainFloatingPopulationDlg = function (dfop){
	if($("#mode").val()=="add"){
		$("#populationDeathDiv").css("display","none");
		$("#isDead").attr("checked",false);
	}
	
	
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
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
			url: PATH + "/commonPopulation/commonPopulationManage/getCommonPopulationByIdCardNo.action",
			data:{
				"commonPopulation.idCardNo":idCardNo
			},
			success:function(responseData){
				manageCommonPopulation(responseData);
			}
		});
	}
	
	function manageCommonPopulation(responseData){
		threeSelect({
			province:'province',
			city:'city',
			district:'district',
			provinceValue:responseData.province,
			cityValue:responseData.city,
			districtValue:responseData.district
		});
	}
	
	function changeOrgId(){
		$("#organizationId").val($("#orgId").val());
	}
	//用来判断是否改动
	var editIdCardNo = $("#idCardNo").val();
	$(document).ready(function(){
		var exsistedIdCard = false;
		var isUnsettledPopulation=false;
		if(dfop.isHaveHouse == 'true'){
			populationHasHouseInfoChanged();
		}
		isSettled ();
		$("#idCardNo").blur(function(){
			if($(this).attr("createMsg")=="false") {
				if(dfop.isUpdate == 'true'){
					idCardChanged();
				}
			}
			if(dfop.isHouseholdStaffPopulationDialog == 'true'){
				isSettled ();
			}
			if(dfop.isUnsettledPopulationMaintanceDialog == 'true'){
				isSettled ();
			}
			if(!isUnsettledPopulation&&!exsistedIdCard&&editIdCardNo!=$("#idCardNo").val()){
				editIdCardNo = $("#idCardNo").val();
				getBaseInfoByIdCardNo();
			}
			fillGenderByIdCardNo($("#idCardNo").val(),"gender","populationGender",true);
			
		});
		
		function selectedIdOKFunction(){
			$("#name").val(responseData.name);
			$("#gender").val(responseData.gender.id);
			$("#populationGender").val(responseData.gender.id);
			$("#birthday").val(getBirthDayTextFromIdCard($("#idCardNo").val()));
			$("#usedName").val(responseData.usedName);
			$("#mobileNumber").val(responseData.mobileNumber);
			$("#telephone").val(responseData.telephone);
			$("#email").val(responseData.email);
			$("#workUnit").val(responseData.workUnit);
			$("#stature").val(responseData.stature);
			$("#currentAddress").val(responseData.currentAddress);
			$("#nativePoliceStation").val(responseData.nativePoliceStation);
			$("#nativePlaceAddress").val(responseData.nativePlaceAddress);
			setTimeout(function(){
				threeSelect({
					province:'province',
					city:'city',
					district:'district',
					provinceValue:responseData.province,
					cityValue:responseData.city,
					districtValue:responseData.district
				});
         	},10);
         
			if(responseData.isHaveHouse==true){
				$("#populationHasHouseInfo").val('true');
				$("#houseId").val(responseData.houseId);
				$("#houseCode").val(responseData.houseCode);
				$("#currentAddressType").val(responseData.currentAddressType.id);
				$("#community").val(responseData.community);
				$("#block").val(responseData.block);
				$("#unit").val(responseData.unit);
				$("#room").val(responseData.room);
			}else{
				$("#populationHasHouseInfo").val('false');
				$("#noHouseReason").val(responseData.noHouseReason);
				$("#populationOtherAddress").val(responseData.otherAddress);
			}
			populationHasHouseInfoChanged();
			$("#nativeLocation").val(responseData.nativeLocation);
			$("#politicalBackground").val(isEmpty(responseData.politicalBackground));
			$("#maritalState").val(isEmpty(responseData.maritalState));
			$("#residenceType").val(isEmpty(responseData.residenceType));
			$("#nation").val(isEmpty(responseData.nation));
			$("#faith").val(isEmpty(responseData.faith));
			$("#schooling").val(isEmpty(responseData.schooling));
			$("#bloodType").val(isEmpty(responseData.bloodType));
			$("#remark").val(responseData.remark);
			$("#settleTime").val(getCourrentTime());
		}
		
		function isSettled (){
			if(null==$("#idCardNo").val()){
				return;
			}else{
				if($("#idCardNo").val().length!=15&&$("#idCardNo").val().length!=18){
					return;
				}
			}
			$.ajax({
				async: false ,
				url: PATH + "/baseinfo/unsettledPopulationManage/getUnsettledPopulationByIdCardNo.action",
			   	data:{
					"unsettledPopulation.idCardNo":$("#idCardNo").val(),
					"unsettledPopulation.organization.id":getCurrentOrgId()
		        },
				success:function(responseData){
		    	   if(null!=responseData){
		    		   isUnsettledPopulation=true;
	    		   		if(dfop.isHouseholdStaffPopulationDialog == 'true'){
			    		   $.confirm({
			   					title:"提示",
			   					message:"此身份证号码已存在于“未落户人口”模块，是否需要对此人进行落户操作？",
			   					okFunc: function(){
			   			   			selectedIdOKFunction();
			   					},
			   					cancelFunc: function(){
			   						$("#idCardNo").val("");
			   						$("#birthday").val("");
			   						$("#province").val("");
			   						$("#city").val("");
			   						$("#district").val("");
			   					}
			   				});
			    		}else{
			    			selectedIdOKFunction();
			    		}
					}
		        }
			});
		}
		function isEmpty(o){
			if(o==null){
				return null;
			}else{
				return o.id;
			}
		}
		
		function getCourrentTime(){
			var time = new Date();
			var myDate = time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate();
			return myDate;
		}
		
		if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
			$("#img").attr("src", PATH + "/"+$("#_imgUrl").val());
		};
	
	
		threeSelect({province:'province',
			city:'city',
			district:'district',
			provinceValue:$('#provinceValue').val(),
			cityValue:$('#cityValue').val(),
			districtValue:$('#districtValue').val()
		});
		$(".dialogtip").inputtip();
		
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
	
	
		jQuery.validator.addMethod("validatorPopulationOtherAddress", validatorSpecialWord);
		jQuery.validator.addMethod("validatorNativePlaceAddress", validatorSpecialWord);
		jQuery.validator.addMethod("validatorWorkUnit", validatorSpecialWord);
		jQuery.validator.addMethod("validatorName", validatorSpecialWord);
		
		function validatorSpecialWord(value,element){
			if(value==null||value==undefined||value==""){return true}
			var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
			return this.optional(element)||!pattern.test(value) ; 
		}
	
		var exsistedIdCardMessage;
		jQuery.validator.addMethod("exsistedIdCard", function(value, element){
			var flag =true;
			if($('#mode').val() != "add"){
				return flag;
			}
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url: PATH + "/commonPopulation/commonPopulationManage/getActualPopulationbyOrgIdAndIdCardNoExistedMessage.action",
			   	data:{
					"idCardNo":$('#idCardNo').val(),
					"orgId":$('#populationOrganizationId').val(),
					"actualPopulationType": dfop.actualPopulationType,
					"populationId":$('#populationId').val()
		        },
				success:function(responseData){
					exsistedIdCardMessage = responseData;
				}
			});
			 if(!(exsistedIdCardMessage==null||exsistedIdCardMessage=="")){
				 flag = false;
			  }
			if(!flag){
				exsistedIdCard = true;
			}else{
				exsistedIdCard = false;
			}
			return flag;
		});
	
		function clearBaseInfoMessage(){
			$("#baseInfoId").val("");
			$("#name").val("");
			$("#gender").val("");
			$("#populationGender").val("");
			$("#usedName").val("");
			$("#mobileNumber").val("");
			$("#telephone").val("");
			$("#email").val("");
			$("#workUnit").val("");
			$("#stature").val("");
			$("#nativePoliceStation").val("");
			$("#nativePlaceAddress").val("");
			$("#nation").val("");
			$("#politicalBackground").val("");
			$("#maritalState").val("");
			$("#faith").val("");
			$("#career").val("");
			$("#schooling").val("");
			$("#bloodType").val("");
			$("#_imgUrl").val("");
			$("#headerImg").attr("src", PATH + "/resource/images/head.png");
			$(".shadow").hide();
		}
		function getBaseInfoByIdCardNo(){
			$.ajax({
				async: false,
				url: PATH + "/baseinfo/baseinfoPopulation/getBaseInfoByIdCardNo.action",
			   	data:{
				"idCardNo":$("#idCardNo").val()
		        },
				success:function(responseData){
		    	   if(null!=responseData){
		    		   if(responseData.death == true ||responseData.death == 'true'){
		    			   $.confirm({
			           			title:"提示",
			           			message:"该身份证在其他网格已被标记为死亡，若取消死亡，请去掉死亡勾选。",
			           			okFunc:function(){
			      	    			   $("#populationDeathDiv").css("display","block");
			      	    			   $("#isDead").attr("checked",true);
			           			},
			           			cancelFunc: function(){
			           				editIdCardNo = "";
			           				$('#idCardNo').val("");
			           				$("#populationDeathDiv").css("display","none");
			           				$("#isDead").attr("checked",false);
			           				clearBaseInfoMessage();
			                   		return;
			           			}
			           		});
		    		   }
		    		    $("#baseInfoId").val(responseData.id);
		    		    $("#name").val(responseData.name);
						$("#gender").val(responseData.gender.id);
						$("#populationGender").val(responseData.gender.id);
						$("#birthday").val(getBirthDayTextFromIdCard($("#idCardNo").val()));
						$("#usedName").val(responseData.usedName);
						$("#mobileNumber").val(responseData.mobileNumber);
						$("#telephone").val(responseData.telephone);
						$("#email").val(responseData.email);
						$("#workUnit").val(responseData.workUnit);
						$("#stature").val(responseData.stature);
						$("#nativePoliceStation").val(responseData.nativePoliceStation);
						$("#nativePlaceAddress").val(responseData.nativePlaceAddress);
						setTimeout(function(){
								threeSelect({
									province:'province',
									city:'city',
									district:'district',
									provinceValue:responseData.province,
									cityValue:responseData.city,
									districtValue:responseData.district
								});
				         	},10);
						$("#nation").val(isEmpty(responseData.nation));
						$("#politicalBackground").val(isEmpty(responseData.politicalBackground));
						$("#maritalState").val(isEmpty(responseData.maritalState));
						$("#faith").val(isEmpty(responseData.faith));
						$("#career").val(isEmpty(responseData.career));
						$("#schooling").val(isEmpty(responseData.schooling));
						$("#bloodType").val(isEmpty(responseData.bloodType));
						if(null!=responseData.imgUrl && responseData.imgUrl!=""){
							$("#_imgUrl").val(responseData.imgUrl);
							$("#headerImg").attr("src",responseData.imgUrl+"?r="+Math.random());
							$(".shadow").show();
						}
		    	   }else{
		    		   clearBaseInfoMessage();
		    	   }
		        }
			});
		}
		
		resetBirthdayField($("#birthday").val());
		
		$("#maintainForm").formValidate({
				submitHandler: function(form) {
					$("#_imgUrl").val($("#imgUrl").val());
		         	$(form).ajaxSubmit({
		         		async:false,
	             		success: function(data){
		                     if(!data.id){
		                    	 $.messageBox({
									message:data,
									level: "error"
								 });
		                     	return;
		                     }
		                     $("#"+ dfop.dailogName).proccessSuccess(data.encryptId,data);
			      	   },
			      	   error: function(XMLHttpRequest, textStatus, errorThrown){
			      	      alert("提交错误");
			      	   }
		      	  	});
				},
				ignore:":hidden",
				rules:{
					"population.idCardNo":{
						exsistedIdCard:true
					}
				},
				messages:{
					"population.idCardNo":{
						exsistedIdCard:function(){
							return exsistedIdCardMessage;
						}
					}
				}
			});
			if(dfop.isUpdate == 'true'){
			    $("#populationOrganizationId").val($("#currentOrgId").val());
				$.ajax({
					async: false,
					url:  PATH + "/sysadmin/orgManage/getOrgRelativePath.action",
					data:{
						"organization.id" : $("#currentOrgId").val()
					},
					success:function(responseData){
						$("#commonPopulationOrgName").val(responseData);
					}
				});
			}
	});
	
	function idCardChanged(){
		var text=$('#idCardNo').val();
		if(editIdCardNo!=$("#idCardNo").val()){
			$("#populationDeathDiv").css("display","none");		
			$("#isDead").attr("checked",false);
			text=getBirthDayTextFromIdCard(text);
			resetBirthdayField(text);
			text=$('#idCardNo').val();
			getCommonPopulation(text);
		}
	}
	
	function resetBirthdayField(text){
		if (text!="" && $('#idCardNo').val()!=""){
			$("#birthdayDiv").html("<input type='text' name='population.birthday' id='birthday' class='form-txt' value='"+text+"' readonly/>");
		}else{
			$('#birthday').datePicker({
				yearRange: '1900:2030',
	    		dateFormat: 'yy-mm-dd',
	            maxDate:'+0d'});
		}
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
}