TQ.maintainOverseaPersonnelDlg = function (dfop){
	$("#certificateStrartDay").datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd'
	    });
	$('#certificateEndDay').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd'
	    });
	$('#birthday').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'
	    });
	$("#overseaPersonnelArrivaTime").datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
		maxDate:'+0d'
	    });
	$("#overseaPersonnelLeaveTime").datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd'
	    });
	
	function selectedTrue(inputType, textName){
		for(var i = 0; i < inputType.length; i++){
			if(inputType[i].text==textName){
				inputType[i].selected=true;
			}
		}
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
	var idleYouthOrgTree="";
	function isGridForTreeSelect(){
		if(idleYouthOrgTree != ""){
			return $.getSelectedNode(idleYouthOrgTree).attributes.orgLevelInternalId == dfop.organizationLevel;
		}else{
			return true;
		}
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
	            	   if(data.internalId==dfop.currentAddressType){
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
	
	function changeOrgId(){
		$("#organizationId").val($("#orgId").val());
	}
	
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
	jQuery.validator.addMethod("validateMail", function(value, element){
		if(value == ""){
			return true;
		}
		var regex = /^([a-za-z0-9]+[_|_|.]?)*[a-za-z0-9]+@([a-za-z0-9]+[_|_|.]?)*[a-za-z0-9]+.[a-za-z]{2,3}$/;
		if(!regex.test(value))
		{
			return false;
		}
		return true;
	});
	
	$(document).ready(function(){
		populationHasHouseInfoChanged();
		if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
			$("#img").attr("src", PATH + "/"+$("#_imgUrl").val());
		};
	
		$("#houseCode").change(searchHoseInfoByCode);
	
		// 根据房屋编号自动获取实有房屋信息
		function searchHoseInfoByCode(){
			$.ajax({
				async: false ,
				url: PATH + "/baseinfo/actualHouseManage/getHouseInfoByHouseCode.action",
			   	data:{
					"searchHouseInfoVo.houseCode":$('#houseCode').val(),
					"searchHouseInfoVo.houseTypeId":$('#houseTypeId').val()
		        },
				success:function(data){
					if(data){
						procCurrentAddressType(data);
						$.each(data, function(key, value) {
							if(null != value) {
								$("input[name='overseaPersonnel."+key+"']").val(value);
							} else {
								$("input[name='overseaPersonnel."+key+"']").val("");
							}
						});
						$("#houseInfoOrgName").val(data.organization.orgName);
					} else {
						$("input[name^='houseInfo.']:visible",$("#floatingPopulationform")).val("");
					}
					if(null == data) {
						$("#currentAddress").val("");
						$("#community").val("");
						$("#block").val("");
						$("#unit").val("");
						$("#room").val("");
					}
				}
			});
		}
	
		function procCurrentAddressType(data) {
			$("#houseId").val(data.id);
			$("#addressTypeId").val(data.addressType.id);
			$("#currentAddressType").val(data.addressType.id);
			if($("#currentAddressType").find("option:selected").attr("internalId")==dfop.currentAddressType) {
				$("#building").show();
				$("#otherAddress").hide();
				$("#community").val(data.community);
				$("#block").val(data.block);
				$("#unit").val(data.unit);
				$("#room").val(data.room);
				$("#currentAddress").val(data.community+"小区"+data.block+"幢"+data.unit+"单元"+data.room+"室");
			} else {
				$("#building").hide();
				$("#otherAddress").show();
				$("#currentAddress").val(data.address);
				$("#community").val("");
				$("#block").val("");
				$("#unit").val("");
				$("#room").val("");
			}
		}
	
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
	
		$("#community").houseAutoComplete({
			searchLevel:"community",
			select:function(event,ui){
				renderHouseInfoFromCBUR(ui.item);
			}});
	
		$("#block").houseAutoComplete({
			searchLevel:"block",
			select:function(event,ui){
				renderHouseInfoFromCBUR(ui.item);
			},
			postData: {
				community :function(){return $("#community").val();}
				}});
	
		$("#unit").houseAutoComplete({
			searchLevel:"unit",
			select:function(event,ui){
				renderHouseInfoFromCBUR(ui.item);
			},
			postData: {
				community :function(){return $("#community").val();},
				block :function(){return $("#block").val();}
				}});
	
		$("#room").houseAutoComplete({
			searchLevel:"room",
			select:function(event,ui){
				renderHouseInfoFromCBUR(ui.item);
			},
			postData: {
				community :function(){return $("#community").val();},
				block :function(){return $("#block").val();},
				unit :function(){return $("#unit").val();}
				}});
	
		$("#currentAddress").houseAutoComplete({
			searchByAddress:true,
			select:function(event,ui){
				renderHouseInfoFromADD(ui.item);
			}});
	
		threeSelect({province:'province',
			city:'city',
			district:'district',
			provinceValue:$('#provinceValue').val(),
			cityValue:$('#cityValue').val(),
			districtValue:$('#districtValue').val()
		});
		$(".dialogtip").inputtip();
		$("#currentAddressType").change(chooseCurrentAddressType);
		initCurrentAddress();
	
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
	
		jQuery.validator.addMethod("isGridOrganization", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			if(isGridForTreeSelect()){
				return true;
			}else{
				return false;
			}
	
		});
	
		jQuery.validator.addMethod("currentAddress", function(value, element){
			var other = $("#other").val();
			if(other!=null&& $("#currentAddressType").val()== other.split("-")[0]){
				if(other.split("-")[1]==dfop.currentAddressTypeOther){
					if(value==null||value==undefined||value==""){
		    			return false;
		    		}else{
		    			return true;
		    		}
				}
			}
		  return true;
		});
	
		jQuery.validator.addMethod("community", function(value, element){
			//var businessHouse = $("#businessHouse").val();
			//if(businessHouse!=null&& $("#currentAddressType").val()== businessHouse.split("-")[0]){
				if($("#currentAddressType").find("option:selected").attr("internalId")==dfop.currentAddressType) {
					var livingHouse=$("#community").val();
					return (livingHouse==null||livingHouse==undefined||livingHouse=="")?false:true;
				}
			//}
		  return true;
		});
	
		jQuery.validator.addMethod("exsistedIdCard", function(value, element){
			var flag =true;
			if(value==null||value==undefined||value==""){return true}
			var certificateType   = $("#overseaPersonnelCertificateType").val();
			if(certificateType==null||certificateType==undefined||certificateType==""){return true}
			$.ajax({
				async: false ,
				url: PATH + "/baseinfo/overseaPersonnelManage/hasDuplicateOverseaPersonnel.action",
			   	data:{
					"overseaPersonnel.certificateNo":$('#overseaPersonnelCertificateNo').val(),
					"overseaPersonnel.certificateType.id":$('#overseaPersonnelCertificateType').val(),
					"orgId":$('#populationOrganizationId').val(),
					"mode":$('#mode').val(),
					"overseaPersonnel.id":$('#populationId').val()
		        },
				success:function(responseData){
					flag = !eval(responseData);
				}
			});
			return flag;
		});
	
		jQuery.validator.addMethod("certificateEndDayFormatter", function(value, element){
			if($("#certificateEndDay").val()<$("#certificateStrartDay").val()){
				return false;
			}
			return true;
		});
	
		
		jQuery.validator.addMethod("guoji", function(value, element){
			var patrn=/^[A-Za-z\u4e00-\u9fa5]*$/;
			if (!patrn.exec(value)) return false;
			return true  
		});
		
		jQuery.validator.addMethod("leavelDayFormatter",function(value,element){
			if($("#overseaPersonnelLeaveTime").val()<$("#overseaPersonnelArrivaTime").val()){
				return false;
			}
			return true;
		});
		
		
		
		
		resetBirthdayField($("#birthday").val());
		$('#idCardNo').blur(idCardChanged);
			$("#maintainForm").formValidate({
				submitHandler: function(form) {
					$("#_imgUrl").val($("#imgUrl").val());
					businessHouseToCurrentAddress();
		         	$(form).ajaxSubmit({
		         	 async : false,
		             success: function(data){
		                     if((!data.id)||(data.id+""=="undefined")){
		                    	  $.messageBox({message:data,level: "error"});
		                    	  return false;
		                    }
		                   
							if($("#populationHasHouseInfo").val()=="false"){
		                     if("add" == $("#mode").val()){
		                    	 if($("#logOut").val()!="1"){
	
		        					 $("#overseaPersonnelList").addRowData(data.id,data,"first");
		        					 $("#overseaPersonnelList").setSelection(data.id);
		        				 }
		        				 $("#overseaPersonnelList").trigger("reloadGrid");
		                    	 $.messageBox({message:"境外人员信息新增成功！"});
		                     }
		                     if("edit" == $("#mode").val()){
		                    	 if(data.death == true || data.death == "true"){
		        					 if($("#logOut").val()=="0"){
		        						 $("#overseaPersonnelList").delRowData(data.id+"");
		        					 }else{
		        						 $("#overseaPersonnelList").setRowData(data.id,data);
		        						 $("#"+data.id+"").css('color','#778899');
		        					 }
		        				 }
		        				 $("#overseaPersonnelList").trigger("reloadGrid");
		                    	 $("#overseaPersonnelList").setRowData(data.id,data);
		        				 $.messageBox({message:"境外人员信息修改成功！"});
		                     }
							}
							  $("#" + dfop.dailogName).proccessSuccess(data.encryptId,data);
		      	   },
		      	   error: function(XMLHttpRequest, textStatus, errorThrown){
		      	      alert("提交错误");
		      	   }
		      	  });
			},
				ignore:":hidden",
				rules:{
				},
				messages:{
				}
			});
	
	if(dfop.isAdd == 'true'){
	    $("#populationOrganizationId").val($("#currentOrgId").val());
		$.ajax({
			async:false,
			url: PATH + "/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id" : $("#currentOrgId").val()
			},
			success:function(responseData){
				$("#overseaPersonnelOrgName").val(responseData);
			}
		});
	}
	
		if(dfop.isDialog == 'true'){
			idleYouthOrgTree = $("#commonPopulationOrgName").treeSelect({
				inputName:"overseaPersonnel.organization.id",
				url:"/sysadmin/orgManage/loadTownForExtTree.action",
				onSelect:changeOrgId
			});
			var bol = false;
			$.ajax({
				async:false,
				url: PATH + "/issue/issueManage/checkOccurOrgId.action",
				data:{
					"issueNew.occurOrg.id": dfop.userOrgId
				},
				success:function(responseData){
					bol = responseData;
				}
			});
			var url = "";
			if(bol){
				url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIdsWhenRootIsTown.action?organization.id="+$("#orgId").val()
			}else{
				url = PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+$("#orgId").val()
			}
			$.ajax({
				async:false,
				url:url,
				success:function(data){
					$.searchChild(idleYouthOrgTree,data);
				}
			});
		}
	
		
		// fateson add  使用js 去掉必填控件 
		//$("#certificateTypeid").hide();
		//$("#overseaPersonnelCertificateType").attr("class","form-txt");
		
		
	});
	
	
	function setZone(selectOrgId,selectOrgName){
		if(dfop.modeTypeAddPath == 'true'){
			$("#orgId").val(selectOrgId);
			$("#organizationId").val(selectOrgId);
			$("#commonPopulationOrgName").val(selectOrgName);
		}
	}
	
	function idCardChanged(){
		var text=$('#idCardNo').val();
		text=getBirthDayTextFromIdCard(text);
		resetBirthdayField(text);
		text=$('#idCardNo').val();
		getCommonPopulation(text);
	}
	
	function resetBirthdayField(text){
		if (text!="" && $('#idCardNo').val()!=""){
			$("#birthdayDiv").html("<input type='text' name='overseaPersonnel.birthday' id='birthday' readonly class='form-txt' value='"+text+"'/>");
		}
		$('#birthday').datePicker({
			yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
	        maxDate:'+0d'
	        });
		
	}
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
	
	function isHelpPersonnel(){
		var flag=false;
		$.ajax({
			async:false,
			url:  PATH + "/baseinfo/helpPersonnel/getHelpPersonnelByIdAndPlace.action",
			data:{
				"personnelId":$("#idleYouthId").val(),
				"personnelType":$("#keyType").val()
			},
			success:function(responseData){
				if(responseData.length>0){
					flag=true;
				}
			}
		});
		return flag;
	}
	
	function chooseCurrentAddressType(){
		var businessHouse = $("#businessHouse").val();
		var other = $("#other").val();
		var currentAddressType = $("#currentAddressType").val();
		if(currentAddressType==businessHouse.split("-")[0]){
			$("#building").css("display","block");
			$("#otherAddress").css("display","none");
		}else if(currentAddressType==other.split("-")[0]){
			$("#building").css("display","none");
			$("#otherAddress").css("display","block");
		}else{
			$("#building").css("display","none");
			$("#otherAddress").css("display","none");
		}
	}
	if(document.getElementById("jl_add3") != null && document.getElementById("jl_add3") != "null"){
		if($("#mode").val()== "add"){
			document.getElementById("jl_add3").style.display = "none";
		}else{
			if($("#population_outGone").val() == true || $("#population_outGone").val() == "true"){
				document.getElementById("jl_add3").style.display = "block";
			}else{
				document.getElementById("jl_add3").style.display = "none";
			}
		}
	}
	jQuery.validator.addMethod("exculdeParticalChar", validatorSpecialWord);
	
	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！]");
		return this.optional(element)||!pattern.test(value) ; 
	}
}