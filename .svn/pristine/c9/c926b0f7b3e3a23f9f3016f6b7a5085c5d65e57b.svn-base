TQ.commonPopulationDlg = function (dfop){
	if($("#mode").val()=="add"){
		$("#populationDeathDiv").css("display","none");
		$("#isDead").attr("checked",false);
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
	jQuery.validator.addMethod("checkIsNeedCity", function(value, element){
		var flag = false;
		if($('#province').val()==""){return false;}
		if($('#city').val()!=""){return true;}
		$.ajax({
			type:'post',
			dataType:'json',
			async: false,
			url:'/baseinfo/permanentAddressManage/getPermanentAddressByParentName.action',
			data:{"parentName":$('#province').val()},
			success:function(data){
				var count = data.split("\,");
				if(count.length<=1){
					flag = true;
				}
			}
		});
		return flag;
	});
	
	jQuery.validator.addMethod("checkIsNeedDistrict", function(value, element){
		var flag = false;
		if($('#province').val()==""){return false;}
		if($('#district').val()!=""){return true;}
		$.ajax({
			type:'post',
			dataType:'json',
			async: false,
			url:'/baseinfo/permanentAddressManage/getPermanentAddressByParentNameAndPId.action',
			data:{"parentName":$('#city').val()},
			success:function(data){
				var count = data.split("\,");
				if(count.length<=1){
					flag = true;
				}
			}
		});
		return flag;
	});
	
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
	
	function getCommonPopulation(idCardNo){
		if( idCardNo.length != null && idCardNo.length == 18){
			ajaxForCommonPopulation(idCardNo);
		}else if( idCardNo.length != null && idCardNo.length == 15){
			ajaxForCommonPopulation(idCardNo);
		}
	}
	
	function idCardChanged(){
		var text=$('#idCardNo').val();
		if(isUnsettledPopulation(text)){
			$('#idCardNo').val("");
			return;
		}
		
		text=getBirthDayTextFromIdCard(text);
		resetBirthdayField(text);
		text=$('#idCardNo').val();
		getCommonPopulation(text);
	}
	
	function isUnsettledPopulation(idCardNo){
		var isTure;
		$.ajax({
			async: false,
			url:  PATH + "/baseinfo/unsettledPopulationManage/getUnsettledPopulationByIdCardNo.action",
			data:{
				"unsettledPopulation.organization.id" : $("#currentOrgId").val(),
				"unsettledPopulation.idCardNo":idCardNo
			},
			success:function(responseData){
				if(responseData!=null){
					isTure = true;
					$.messageBox({
						message:"未落户人口不能新增为重点人员！",
						level: "error"
				 	});
				}else{
					isTure = false;
				}
			}
		});
		return isTure;
	}
	
	function proccessAddressBySyncData(data){
		if(data.baseInfoId == null){
			return;
		}
		if(data.isHaveHouse == true){
			$("#haveNotHouse").hide();
			$("#haveHouse").show();
			$("input[name='population.houseId']", $("#maintainForm")).val(data.houseId);
			$("input[name='population.noHouseReason']", $("#maintainForm")).val("");
			$("select[name='population.isHaveHouse']", $("#maintainForm")).val("true");
			$("#currentAddress").val(data.currentAddress);
		}else if(data.isHaveHouse == false){
			$("#haveNotHouse").show();
			$("#haveHouse").hide();
			$("input[name='population.houseId']", $("#maintainForm")).val("");
			$("input[name='population.noHouseReason']", $("#maintainForm")).val(data.noHouseReason);
			$("select[name='population.isHaveHouse']", $("#maintainForm")).val("false");
			populationHasHouseInfoChanged();
		}else{
			//fateson add 房屋地址为未知这时候需要修改地址的名字 (BUG单 #21299系统中所有业务人员时若新增了房屋为未知的人员后修改时回填的房屋为无住房)
			//start
		    $("#populationHasHouseInfo").attr("name","aaaaaaaaaaaaaaaa");
			//end
			$("#haveHouse").hide();
			$("#haveNotHouse").hide();
			$("input[name='population.houseId']", $("#maintainform")).val("");
			$("input[name='population.nohousereason']", $("#maintainform")).val("");
			$("#populationHasHouseInfo").val("null");
			populationHasHouseInfoChanged();
		}
	}
	
	function clearBaseInfoMessage(){
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
		$("#nation2").val("");
		$("#politicalBackground2").val("");
		$("#maritalState2").val("");
		$("#faith").val("");
		$("#career").val("");
		$("#schooling2").val("");
		$("#bloodType").val("");
		$("#_imgUrl").val("");
		$("#populationOtherAddress").val("");
		$("#currentAddress").val("");
		$("#noHouseReason").val("");
		$("#remark").val("");
		$("#headerImg").attr("src", PATH + "/resource/images/head.png");
		$(".shadow").hide();
	}
	
	function searchAndSynData(idCardNo, orgId) {
		$.ajax({
			url: PATH + "/commonPopulation/commonPopulationManage/getActualPopulationByOrgIdAndIdCardNoIncludeLogout.action",
			async:false,
			data:{
				"orgId":orgId,
				"idCardNo":idCardNo
	        },
	        success:function(data){
	        	if(data == null) {
	        		searchAndSynDataCallbackFunction();
	        		idCardChanged();
	        		$("#populationDeathDiv").css("display","none");
					$("#isDead").attr("checked",false);
	        		clearBaseInfoMessage();
	        		searchAndSynDataCallbackFunctionForClearSearch();
	        		return;
	        	}
	        	searchAndSynDataCallbackFunctionForAfterSearch(data);
	   			
	        	if(data.baseInfoId > 0) {
	        		$("#actualPersonTypeDiv").hide();
	        	} else {
	        		$("#actualPersonTypeDiv").show();
	        	}
	        	if(data.logOut == '1'){
	        		if(data.actualPopulationType=='householdStaff'){
		        		$.messageBox({
							message:"该身份证在此网格的户籍人口中已被注销",
							level: "error"
					 	});
	        		}else if(data.actualPopulationType=='floatingPopulation'){
	        			$.messageBox({
							message:"该身份证在此网格的流动人口中已被注销",
							level: "error"
					 	});
	        		}
	        		$('#idCardNo').val("");
	        		return;
	        	}
	        	if(dfop.isNurturesWomenDialog == 'true'){
	        		if(data.gender!=null&&data.gender.displayName!='女'){
	        			$.messageBox({
							message:"该身份证为不为女性，无法新增为育龄妇女！",
							level: "error"
					 	});
	        			$('#idCardNo').val("");
	        			return;
	        		}
	        	}
	        	if(data.death == true ||data.death == 'true'){
	        		$.confirm({
	        			title:"提示",
	        			message:"该身份证在其他网格已被标记为死亡，若取消死亡，请去掉死亡勾选。",
	        			okFunc:function(){
	   	    			   $("#populationDeathDiv").css("display","block");
	   	    			   $("#isDead").attr("checked",true);
	        			},
	        			cancelFunc: function(){
	        				$('#idCardNo').val("");
	        				$("#populationDeathDiv").css("display","none");
	        				$("#isDead").attr("checked",false);
	        				clearBaseInfoMessage();
	                		return;
	        			}
	        		});
	        	}
	        	 
	        	proccessAddressBySyncData(data);
				$.each(data,function(i,n){
					if(n.id){
						$("select[name='population."+i+".id']:visible").val(n.id);
					}else{
						$("input[name='population."+i+"']:visible").val(n);
					}
				})
	            //fateson add 户籍地两个字段不能获取，且户籍地市、县两字段下拉选项缺失
				//alert(data.province+"----"+data.city+"----"+data.district);
				//$("select[name='population.province']:visible").val(data.province);
				//$("select[name='population.city']:visible").val(data.city);
				//$("select[name='population.district']:visible").val(data.district);
				threeSelect({
					province:'province',
					city:'city',
					district:'district',
					provinceValue:data.province,
					cityValue:data.city,
					districtValue:data.district
				});
				 
				
				$("#remark").val(data.remark);
				$("input[name='population.actualPopulationType']", $("#maintainForm")).val(data.actualPopulationType);
				$("input[name='population.addressInfoId']", $("#maintainForm")).val(data.addressInfoId);
	   			//图片同步
	   			if(data.imgUrl!=null && data.imgUrl!=""){
	   				$("#headerImg").attr("src",data.imgUrl+"?r="+Math.random());
	   				$("#_imgUrl").val(data.imgUrl);
	   				$(".shadow").show();
	   			}
			}
		})
	}
	function householdType(){
		//$("#beforeInfo").load( PATH + "/baseinfo/commonPopulation/floatingPersonTypeDiv.jsp");
		$("#floatTypeInfo").show();
		$("#police_h").show();
		$("#police").show();
	}
	function floatType(){
		//$("#beforeInfo").load( PATH + "/baseinfo/commonPopulation/floatingPersonTypeDiv.jsp");
		$("#floatTypeInfo").show();
		$("#police_h").hide();
		$("#police").hide();
	}
	$(document).ready(function(){
		if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
			$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
			$(".shadow").show();
		}
		
		if(dfop.isToPositiveInfoDialog == 'true'){
			$('#idCardNo').attr("readonly","readonly");
			//fateson add  start
			$('#idCardNo').attr("class","form-txt");
			//end
		}
	
		threeSelect({
			province:'province',
			city:'city',
			district:'district',
			provinceValue:$('#provinceValue').val(),
			cityValue:$('#cityValue').val(),
			districtValue:$('#districtValue').val()
		});
	
		jQuery.validator.addMethod("currentAddress", function(value, element){
			if($("#currentAddressType").find("option:selected").attr("internalId")== dfop.currentAddressTypeOther){
				var livingHouse=$("#currentAddress").val();
				return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
			}
		  	return true;
		});
	
		jQuery.validator.addMethod("community", function(value, element){
			if($("#currentAddressType").find("option:selected").attr("internalId")==dfop.currentAddressTypeBusinessHouse) {
				var livingHouse=$("#community").val();
				return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
			}
			return true;
		});
	
		jQuery.validator.addMethod("exsistedIdCard", function(value, element){
			var flag =true;
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url:$('#ajaxUrl').val(),
			   	data:{
					"population.idCardNo":$('#idCardNo').val(),
					"organizationId":$('#populationOrganizationId').val(),
					"mode":$('#mode').val(),
					"population.id":$('#populationId').val()
		        },
				success:function(responseData){
					flag = !eval(responseData);
				}
			});
			return flag;
		});
		
		
		//新增社区矫正人员和刑释解教人员身份证号缺少互斥判断
		if(dfop.isPositiveInfoDialog == 'true'){
			jQuery.validator.addMethod("exsistedRectificativePersonIdCard", function(value, element){
				var flag =true;
				if(value==null||value==undefined||value==""){return true}
				$.ajax({
					async: false ,
					url: PATH + "/baseinfo/rectificativePersonManage/hasDuplicateRectificativeForPositive.action",
				   	data:{
						"population.idCardNo":$('#idCardNo').val(),
						"organizationId":$('#populationOrganizationId').val(),
						"mode":$('#mode').val(),
						"population.id":$('#populationId').val()
			        },
					success:function(responseData){
						flag = !eval(responseData);
					}
				});
				return flag;
			});
		}
		
		
		//新增社区矫正人员和刑释解教人员身份证号缺少互斥判断
		if(dfop.isRectificativePersonDialog == 'true'){
			jQuery.validator.addMethod("exsistedPositiveInfoIdCard", function(value, element){
				var flag =true;
				if(value==null||value==undefined||value==""){return true}
				$.ajax({
					async: false ,
					url: PATH + "/baseinfo/positiveInfoManage/hasDuplicatePositiveInfoForRectificative.action",
				   	data:{
						"population.idCardNo":$('#idCardNo').val(),
						"organizationId":$('#populationOrganizationId').val(),
						"mode":$('#mode').val(),
						"population.id":$('#populationId').val()
			        },
					success:function(responseData){
						flag = !eval(responseData);
					}
				});
				return flag;
			});
		}
		
		jQuery.validator.addMethod("isElderly", function(value, element){
			if(value==null||value==undefined||value=="" ){return true};
			var d = new Date();
			if(value!=null&&value.length==18){
				value=value.substring(6,14);
				var year = value.substring(0,4);
				var month = value.substring(4,6);
				var day = value.substring(6,8);
			}else if(value!=null&&value.length==15){
				value=value.substring(6,12);
				var temp = value.substring(0,2);
				var year = parseInt(temp)+1900;
				var month = value.substring(2,4);
				var day = value.substring(4,6);
			}
			var elementDate = year;
			var dat1 = d.getFullYear()-60;//当前年份
			var nowMon = d.getMonth();
			var nowDay = d.getDate();
			if (dat1<year) {
				return false;
			}
			 if(dat1 == year){
				if((nowMon+1) < month){
					return false;
				}
				if((nowMon+1) == month){
					if(nowDay<day){
						return false;
					}
					if(nowDay==day){
						return false;
					}
				}
			}
			return true;
		});
		
		jQuery.validator.addMethod("isNurturesWomen", function(value, element){
			if(value==null||value==undefined||value=="" ){return true};
			var d = new Date();
			var nowYear = d.getFullYear();//得到系统时间的年份、月份、日期
			var nowMonth = d.getMonth();
			var nowDay = d.getDate();
			
			if(value!=null&&value.length==18){
				value=value.substring(6,14);
				var year = value.substring(0,4);
				var month = value.substring(4,6);
				var day = value.substring(6,8);
			}else if(value!=null&&value.length==15){
				value=value.substring(6,12);
				var temp = value.substring(0,2);
				var year = parseInt(temp)+1900;
				var month = value.substring(2,4);
				var day = value.substring(4,6);
			}
			var startData = nowYear-15;
			var endData = nowYear-49;
			if (startData < year || endData > year) {
				return false;
			}
			if(endData == year){
				if((nowMonth+1) > month){
					return false;
				}
				if((nowMonth+1) == month){
					if(nowDay > day){
						return false;
					}
					if(nowDay == day){
						return false;
					}
				}
			}
			if(startData == year){
				if((nowMonth+1) < month){
					return false;
				}
				if((nowMonth+1) == month){
					if(nowDay < day){
						return false;
					}
					if(nowDay==day){
						return false;
					}
				}
			}
			return true;
		});
		
		jQuery.validator.addMethod("isYouth", function(value, element){
			if(value==null||value==undefined||value=="" ){return true};
			var d = new Date();
			if(value!=null&&value.length==18){
				value=value.substring(6,14);
				var year = value.substring(0,4);
				var month = value.substring(4,6);
				var day = value.substring(6,8);
			}else if(value!=null&&value.length==15){
				value=value.substring(6,12);
				var temp = value.substring(0,2);
				var year = parseInt(temp)+1900;
				var month = value.substring(2,4);
				var day = value.substring(4,6);
			}
			var dat1 = d.getFullYear()-35;//当前年份
			var dat2 = d.getFullYear();
			var nowMon = d.getMonth();
			var nowDay = d.getDate();
			if (dat1>year || dat2<year){
				 return false;
			}
			if(dat1==year){
				if((nowMon+1) > month){
					return false;
				}
				if(eval(nowMon+1) == month){
					if(nowDay>day){
						return false;
					}
					if(nowDay==day){
						return false;
					}
				}
			}
			if(dat2 == year){
				if(eval(nowMon+1) < month){
					return false;
				}
				if(eval(nowMon+1) == month){
					if(nowDay<day){
						return false;
					}
					if(nowDay==day){
						return false;
					}
				}
			}
			return true;
		});
		
		jQuery.validator.addMethod("fillGenderByIdCardNoForNurturesWomen", function(value, element){
			var flag=false;
			var idCardNo = $('#idCardNo').val();
			if(idCardNo==null||idCardNo=="" || typeof(idCardNo)=="undefined"){
				flag=false;
			}
			if(idCardNo.length!=15 && idCardNo.length!=18){
				flag=false;
			}
			 
			if (15 == idCardNo.length) { //15位身份证号码
				if (parseInt(idCardNo.charAt(14) / 2) * 2 != idCardNo.charAt(14)){
					 flag=false;
				} else{
					flag=true;
				}
			 }
			if (18 == idCardNo.length) { //18位身份证号码
				if (parseInt(idCardNo.charAt(16) / 2) * 2 != idCardNo.charAt(16)){
					flag=false;
				}else{
					flag=true;
				}
			}
			return flag;
		});
	
		jQuery.validator.addMethod("actualPersonTypeNotNull", function(value, element){
			var flag=false;
			$("input[name='actualPersonType']").each(function(i){
				 if ($(this).attr("checked")=="checked") {
					 flag=true;
				 }
			});
			return flag;
		});
		
		$("#maintainForm").formValidate({
			submitHandler: function(form) {
				$("select",$("#maintainForm")).removeAttr("disabled");
	         	$(form).ajaxSubmit({
	         		async : false,
	         		success : function(data){
						if(!data.id){
		               	 	$.messageBox({
								message:data,
								level: "error"
						 	});
	                		return;
	                	}
	                	$("#" + dfop.dailogName).proccessSuccess(data.encryptId,data);
		  	   		},
		      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		      	      	alert("提交错误");
		      	   	}
	      	  	});
			},
			ignore:':hidden',
			rules:{
			},
			messages:{
			}
		});
		
		editModeFunction();
	
		$("#idCardNo").blur(function(){
			if(_isNeedIdCardNoBlur && $(this).attr("createMsg")=="false") {
				searchAndSynData($(this).val(), getCurrentOrgId());
			}
			fillGenderByIdCardNo($("#idCardNo").val(),"gender","populationGender",true);
		});
	
		if(dfop.isAdd == 'true'){
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
	
		if(dfop.isNurturesWomenDialog == 'true'){
			 $("#gender option").each(function() {
				if($(this).text() == '女') {
			    	$(this).attr("selected", "selected");
			    	$("#populationGender").val($(this).val())
			    }else{
					$(this).remove();
			    }
			});
		   
			//备注修改为600
			$("#remark").attr("class","form-txt {maxlength:600,messages:{maxlength:'备注最多需要输入600个字符'}}");
		}
		
		
		//如果修改的时候 房屋为未知  那么也修改名字
		if(dfop.isEdit == 'true'){
			var  populationHasHouseInfoval=  $("#populationHasHouseInfo").val();
			if(populationHasHouseInfoval=='null'){
				 $("#populationHasHouseInfo").attr("name","aaaaaaaaaaaaaaaa");
			} 
		}
		
		
		
		 //隐藏星号 省 区 县 非必填写
		 //3.5 中 需要必填写
		/* $("#houseprovincecitydistrictid").hide();
		$("#province").attr("class","form-txt ");
		$("#city").attr("class","form-txt ");
		$("#district").attr("class","form-txt "); */
		
		jQuery.validator.addMethod("exculdeParticalChar", validatorSpecialWord);
		
		function validatorSpecialWord(value,element){
			if(value==null||value==undefined||value==""){return true}
			var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！-]");
			return this.optional(element)||!pattern.test(value) ; 
		}
	});
}