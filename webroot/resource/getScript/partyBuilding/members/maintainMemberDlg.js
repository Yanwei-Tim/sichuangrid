TQ.maintainMemberDlg = function (dfop){
	//初始发生化网格 
	initOccurOrgSelector();
	var idcardno = dfop.idcardno;
	var flag = true;
	var member;
	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
		return this.optional(element)||!pattern.test(value); 
	}

	$(function(){
		$("#idCardNo").blur(function(){
	    	idCardChanged();
	    	if(!flag) {
				isSettled(member);
				member=null;
			}
	    	getCommonPopulation($('#idCardNo').val());
	    	fillGenderByIdCardNo($("#idCardNo").val(),"gender","memberGender",true);
		});
		
		$('#joinPartyDate').datePicker({
			yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
			maxDate:'+0d'});
		
		$('#joinPartyBranchDate').datePicker({
			yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
			maxDate:'+0d'});
		
		$('#endDate').datePicker({
			yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
	        minDate:'+0d'});
		
		resetBirthdayField($("#birthday").val());
		
		if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
			$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
			$(".shadow").show();
		};
		
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
				url: dfop.path+"/commonPopulation/commonPopulationManage/getCommonPopulationByIdCardNo.action",
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
				province:'province1',
				city:'city1',
				district:'district1',
				provinceValue:responseData.province,
				cityValue:responseData.city,
				districtValue:responseData.district
			});
		}
		
		threeSelect({province:'province1',
			city:'city1',
			district:'district1',
			provinceValue:$('#provinceValue').val(),
			cityValue:$('#cityValue').val(),
			districtValue:$('#districtValue').val()
		});
		
		jQuery.validator.addMethod("validatorNativePlaceAddress", validatorSpecialWord);
		jQuery.validator.addMethod("exsistedIdCard", exsistedIdCard);
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
		        $(form).ajaxSubmit({
					success: function(data){
			        	if(!data.id){
							$.messageBox({
								message:data,
								level: "error"
							});
							return;
						}
				    	$.messageBox({level:'success',message:'保存成功！'});
						$("#memberDialog").dialog("close");
				    	$("#memberList").trigger("reloadGrid");
		      	   	},
		      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		      	    	alert("提交数据时发生错误");
		      	   	}
		      	});
			},
			rules:{
			},
			messages:{
			},
			ignore:':hidden'
		});
		if(dfop.add=='true'){
			$("#partyOrg").attr("readonly","readonly");
		}
		if(dfop.edit=='true'){
			$("#idCardNo").attr("readonly","readonly");
		 	$("#partyOrg").attr("readonly","readonly");
		}
		if(dfop.view=='true'){
			$(".form-txt").attr("disabled","disabled");
		 	$("#maintainForm input[type=checkbox]").attr("disabled","disabled");
		 	$("#fileToUpload").attr("disabled","disabled");
		 	$("#deleteHeaderImage").css("display","none");
		 	$(".shadow").hide();
		 	if($(".ui-button-text").text()=="关闭保存关闭"){
		 		$(".ui-button-text").eq(1).parent().remove();
		 	}
		}
	});
	function idCardChanged(){
		var text=$('#idCardNo').val();
		text=getBirthDayTextFromIdCard(text);
		resetBirthdayField(text);
	}

	//根据身份证得到出生日期
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
			$("#birthdayDiv").empty();
			$("#birthdayDiv").html("<input type='text' name='member.birthday' id='birthday' class='form-txt' value='"+text+"' readonly/>");
		}else{
			$('#birthday').datePicker({
				yearRange: '1900:2030',
	    		dateFormat: 'yy-mm-dd',
	            maxDate:'+0d'});
		}
	}

	function isEmpty(o){
		if(o==null){
			return null;
		}else{
			return o.id;
		}
	}

	function isSettled(data){
		if(data!=null && data.id!=null) {
			$("#memberId").val(data.id);
			$("#associateId").val(data.associateId);
			$("#idCardNo").val(data.idCardNo);
			$("#birthday").val(getBirthDayTextFromIdCard($("#idCardNo").val()));
			$("#name").val(data.name);
			$("#gender").val(isEmpty(data.gender));
			$("#memberGender").val(isEmpty(data.gender));
			$("#nation").val(isEmpty(data.nation));
			$("#schooling").val(isEmpty(data.schooling));
			$("#degree").val(data.degree);
			$("#specialPosition").val(data.specialPosition);
			$("#telephone").val(data.telephone);
			$("#mobileNumber").val(data.mobileNumber);
			
			$("#nativePlaceAddress").val(data.nativePlaceAddress);
			$("#belongOrg").val(isEmpty(data.belongOrg));
			$("#partyPosition").val(isEmpty(data.partyPosition));
			$("#joinPartyDate").val(formatDate(data.joinPartyDate));
			$("#career").val(data.career);
			$("#endDate").val(formatDate(data.endDate));
			$("#rewardAndPunishment").val(data.rewardAndPunishment);
			$("#accedingSituation").val(data.accedingSituation);
			$("#joinPartyBranchDate").val(formatDate(data.joinPartyBranchDate));
			$("#democracy").val(isEmpty(data.democracy));
			if(data.isHandicap){
				$("#isHandicap").attr("checked","checked");
			}
			if(data.isOld){
				$("#isOld").attr("checked","checked");
			}
			if(data.isOversea){
				$("#isOversea").attr("checked","checked");
			}
			if(data.reportOrganization != null){
				$("#selectReportOrganizationName").val(data.reportOrganization.name)
				$("#selectReportOrganization").val(data.reportOrganization.id);
				$("#reportOrganization").val(data.reportOrganization.orgName);
			}
			if(data.imgUrl!=null) { 
				$("#_imgUrl").val(data.imgUrl);
				$("#headerImg").attr("src",data.imgUrl+"?r="+Math.random());
				$(".shadow").show();
			}
		}else{
			//如果没有存在，就置空
			$("#memberId").val("");
			$("#associateId").val("");
			//$("#idCardNo").val(data.idCardNo);
			$("#birthday").val(getBirthDayTextFromIdCard($("#idCardNo").val()));
			$("#name").val("");
			$("#gender").val("");
			$("#memberGender").val("");
			$("#nation").val("");
			$("#schooling").val("");
			$("#degree").val("");
			$("#specialPosition").val("");
			$("#telephone").val("");
			$("#mobileNumber").val("");
			
			$("#nativePlaceAddress").val("");
			$("#belongOrg").val("");
			$("#partyPosition").val("");
			$("#joinPartyDate").val("");
			$("#career").val("");
			$("#endDate").val("");
			$("#rewardAndPunishment").val("");
			$("#accedingSituation").val("");
			$("#joinPartyBranchDate").val("");
			$("#democracy").val("");
				$("#isHandicap").attr("checked",false);
				$("#isOld").attr("checked",false);
				$("#isOversea").attr("checked",false);
				$("#selectReportOrganizationName").val("")
				$("#selectReportOrganization").val("");
				$("#reportOrganization").val("");
				$("#_imgUrl").val("");
				$("#headerImg").attr("src",dfop.path+"/resource/images/head.png");
				$(".shadow").hide();
		}
	}

	 function formatDate(date) {
		return date.substr(0,date.indexOf('T'));
	}

	function exsistedIdCard(value,element){
		if(value==null||value==undefined||value==""
				||("edit"==$("#mode").val()&&idcardno==$("#idCardNo").val())){
			return flag;
		}
		$.ajax({
			async: false,
			url:dfop.path+'/partyBuildng/memberManage/exsistedIdCard.action',
		   	data:{
		   		"member.partyOrgType":dfop.partyOrgType,
				"member.idCardNo":$("#idCardNo").val()
	        },
			success:function(responseData){
				flag = responseData.isExsisted;
				if(!flag) {
					member = responseData.member;
				}
			}
		});
		return !flag;
	}

	function initOccurOrgSelector(){
		var v = $("#selectReportOrganizationName").val();
		if(dfop.partyOrgType==dfop.officePartyOrg){
			if("view"==$("#mode").val()){
				$("#reportOrganization").val(v);
				$("#selectReportOrganization").val(v);
			}else{
				var orgTree=$("#reportOrganization").treeSelect({
					allOrg:false,
					isRootSelected:false,
					emptyText:v,
					inputCodeName:"none",
					inputName:"member.reportOrganization.id",
					isRootSelected:false
				});
				orgTree.on("click",function(node,e) {
					if(node.attributes.orgLevelInternalId !=dfop.orgLevel){
						$("#selectReportOrganization").val("");
						$("#reportOrganization").val("");
						$.messageBox({level:'warn',message:"请选择社区层级!"});
					}		
				});
			}
		}else{
			$("#reportOrganization").val(v);
		}
	}

	function getDefaultOccurOrg(){
		if(dfop.test=='true'){
			return dfop.orgId
		}else{
			return -1;
		}
	}
}