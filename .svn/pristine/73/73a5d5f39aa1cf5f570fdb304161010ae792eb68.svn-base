TQ.districtBasiccaseList = function (dfop){
	
	var flag=false;
	function DrawImage(ImgD){
		var image=new Image();
		image.src=ImgD.src;
		if(image.width>0 && image.height>0){
			flag=true;
		}
	}
	function editImg(fieldCh, fieldEn){
		return {
			width: 470,
			height: 230,
			title:"编辑"+fieldCh,
			url:dfop.path+"/districtBasiccaseManage/getDistrictBasiccaseByIdAndOrgId.action?mode="+fieldEn+"&organization.id="+getCurrentOrgId(),
			buttons:{
				"保存" : function(event){
	   				$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		}
	}
	function editText(fieldCh, fieldEn){
		return {
			width: 520,
			height: 450,
			title:"编辑"+fieldCh,
			url:dfop.path+"/districtBasiccaseManage/getDistrictBasiccaseByIdAndOrgId.action?mode="+fieldEn+"&organization.id="+getCurrentOrgId(),
			buttons:{
				"保存" : function(event){
					$("#maintainForm").submit();
					$(".errorInput").poshytip('show');
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		}
	}
	$(function(){
		initButtonsEnable();
		function initButtonsEnable(){
			if($("#currentOrgId").val()!=USER_ORG_ID){
				$("#zoomin,#editPartyOrgsAndMembersCase,#editAdministrativeCases,#editStreetPartySituation,#editSystemConstruction,#editRegionalPartySituation,#editVolunteersSituation,#eidtDoubleRegistrationSituation").hide();
			}
		}
		var timer;
		$(".mapinfo").hover(function(){
			$(".mapEdit").show();
		},function(){
			$(".mapEdit").hide();
		})
		
		getPhotosList();
		$.loadingComp("close");//关闭遮障层
		$("#editPartyOrgsAndMembersCase").click(function(event){
			$("#districtBasicCaseDialog").createDialog(
				editText('全区党组织及党员情况', 'editPartyOrgsAndMembersCase')
			);
		});
		$("#editAdministrativeCases").click(function(event){
			$("#districtBasicCaseDialog").createDialog(
				editText('机关党建情况', 'editAdministrativeCases')
			);
		});
		$("#editStreetPartySituation").click(function(event){
			$("#districtBasicCaseDialog").createDialog(
				editText('街道党建情况', 'editStreetPartySituation')
			);
		});
		$("#editSystemConstruction").click(function(event){
			$("#districtBasicCaseDialog").createDialog(
				editText('系统党建情况', 'editSystemConstruction')
			);
		});
		$("#editRegionalPartySituation").click(function(event){
			$("#districtBasicCaseDialog").createDialog(
				editText('区域党建情况', 'editRegionalPartySituation')
			);
		});
		$("#editVolunteersSituation").click(function(event){
			$("#districtBasicCaseDialog").createDialog(
				editText('志愿者情况', 'editVolunteersSituation')
			);
		});
		$("#eidtDoubleRegistrationSituation").click(function(event){
			$("#districtBasicCaseDialog").createDialog(
				editText('双报到情况', 'eidtDoubleRegistrationSituation')
			);
		});
		
	function photosList(){
		$("#zoomin").click(function(event){
			$("#photosDialog").createDialog({
				width: 700,
				height: 600,
				title:"图片列表",
				url:dfop.path+"/districtBasiccaseManage/getDistrictBasiccaseByIdAndOrgId.action?mode=zoomin&organization.id="+getCurrentOrgId(),
				buttons:{
					"关闭" : function(){
						$(this).dialog("close");
						$(".leftAccordion .cur").click();
					}
				},
				close:function(){
					$(".leftAccordion .cur").click();
				}

				
			});
		});
	}	

	function getPhotosList(){
		$.ajax({
			async:false,
			url:dfop.path+"/districtBasiccaseManage/getDistrictBasiccaseByIdAndOrgId.action",
			data:{
				"organization.id":getCurrentOrgId(),
				"mode":"districtCase"
			},
			success:function(responseData){
				if(responseData.caseImageUploads!=null && responseData.caseImageUploads!=""){
					for(var i=0; i <responseData.caseImageUploads.length;i++){
						$("#image_"+i+"").attr("src",function(){return dfop.path+"/"+responseData.caseImageUploads[i].imgUrl});
					}
					photosList();				
				}else{
				 	$("#zoomin").click(function(event){
				 		$("#districtBasicCaseDialog").createDialog(
				 			editImg('图片', 'editPhotos')
				 		);
			 		});
				}
				$("#caseId").val(responseData.id);
				$("#maintainPartyOrgsAndMembers_p").append(responseData.partyOrgsAndMembersCase);
				$("#administrativeCases_p").append(responseData.administrativeCases);
				$("#streetPartySituation_p").append(responseData.streetPartySituation);
				$("#systemConstruction_p").append(responseData.systemConstruction);
				$("#regionalPartySituation_p").append(responseData.regionalPartySituation);
				$("#volunteersSituation_p").append(responseData.volunteersSituation);
				$("#doubleRegistrationSituation_p").append(responseData.doubleRegistrationSituation);
				//$("#villageProfileName").text(responseData.organization.orgName);
			}
		});
	}
		$("#zoomDelete").click(function(event){
			var imgSrc = $(".case_images").find("#image_0").attr("src");
			if( imgSrc == dfop.path+"/resource/images/nopic.jpg"){
				$('#districtBasicCaseDialog').html('<div style="text-align:center;height:60px;vertical-align: middle"><font size="3px">没有可供清除的图片！</font></div>');
				$('#districtBasicCaseDialog').dialog({
					title:"清除图片",
					resizable:false,
					modal:true,
					width:300,
					height:'auto',
					buttons:{ "关闭": function() { $(this).dialog("close"); } }
				 });
				return ;
			}
			$.confirm({
				title:"确认清除",
				message:"你确定要清除此图片吗？",
				okFunc: function(){
					$.ajax({
						async:false,
						url:dfop.path+"/caseImageUploadManage/deletePhotosAndOrgById.action?caseImageUpload.id="+$("#imgeId").val()+"&caseImageUpload.imgUrl="+imgSrc+"&organization.id="+getCurrentOrgId(),
						success:function(data){
							if(data.imgUrl == null){
								$("#image").empty().attr("src",function(){return dfop.path+"/"+"resource/images/nopic.jpg"});
							    $.messageBox({message:"已经成功清除该图片！"});
							    return true;
							}
							$.messageBox({message:"清除该图片失败！"});
							return false;
						}
					});
				}
			});
		});
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setInterval(function(){introductionHeight()},200);
		}).resize();
		$("#leaderGroup li").die('mouseover').live('mouseover',function(){
			$(this).find(".leaderGroup-buttons").show();
		});
		$("#leaderGroup li").die('mouseout').live('mouseout',function(){
			$(this).find(".leaderGroup-buttons").hide();
		});
		function introductionHeight(){
			$(".gridDiv").height($(".ui-layout-center").height()-$("#thisCrumbs").height()-10);
			positionPanel();
		}
	})
}