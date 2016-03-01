TQ.basicCaseListFunc = function (dfops){
	function editImg(fieldCh, fieldEn){
		return {
			width: 470,
			height: 230,
			title:"编辑"+fieldCh,
			url:dfops.path+"/basicCaseManage/getBasicCaseByOrgIdAndId.action?mode="+fieldEn+"&organization.id="+getCurrentOrgId()+"&baseInfoType="+dfops.baseInfoType,
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
			url:dfops.path+"/basicCaseManage/getBasicCaseByOrgIdAndId.action?mode="+fieldEn+"&organization.id="+getCurrentOrgId()+"&baseInfoType="+dfops.baseInfoType,
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
	function addPartyworkMembers(){
		$("#partyworkMembersDialog").createDialog({
			width:500,
			height:420,
			title:'新增党工委成员',
			modal : true,
			url:dfops.path+"/basicCaseManage/dispatchPartyworkMembersOperate.action?mode=add"+"&baseInfoType="+dfops.baseInfoType,
			buttons :{
				"保存" : function(){
				   $("#updateDetailsForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}

	$(function(){
		
		initButtonsEnable();
		function initButtonsEnable(){
			if($("#currentOrgId").val()!=USER_ORG_ID){
				$("#zoomin,#editBasicCase,#add").hide();
			}
		}
		
		var timer;
		var dfop={
			delay: 200,
			stop:function(event,ui){
				var ajaxData=[];
				$("#leaderGroup li").each(function(i){
					var thisId=$(this).data("id");
					var thisIndex=i;
				//	alert(thisId+"|"+thisIndex);
					ajaxData.push({id:thisId,index:thisIndex});
				});
				var param = {};   
				for(var i=0;i<ajaxData.length;i++){   
				 param["partyworkMembersList[" + i + "].id"] = ajaxData[i].id;
				 param["partyworkMembersList[" + i + "].sort"] = ajaxData[i].index;   
				}  
				membersSort(param);
			}
		};
		if($("#sortPartyWorkMembersP").val()){
			$("#leaderGroup").sortable(dfop).disableSelection();
		}
		$(".mapinfo").hover(function(){
			$(".mapEdit").show();
		},function(){
			$(".mapEdit").hide();
		});
		getPhotoList();
		$.ajax({
			url:dfops.path+"/basicCaseManage/membersList.action",
			data:{
				"partyworkMembers.organization.id":getCurrentOrgId(),
				"partyworkMembers.baseInfoType":dfops.baseInfoType
			},
			success:function(responseData){
				if(responseData){
					$("#leaderGroup").html(responseData);
					introductionHeight();
				}else{
					$.messageBox({message:"添加成功",level:"error"});				
				}
			}
		});
		$.loadingComp("close");//关闭遮障层
		$("#editBasicCase").click(function(event){
			var orgLevelInternalId=$("#currentOrgId").attr("orgLevelInternalId");
			var fieldCh='';
			if(orgLevelInternalId==10){
				fieldCh='社区党建情况';
			}else if(orgLevelInternalId==20){
				fieldCh='街道党建情况';
			}
			$("#basicCaseDialog").createDialog(
				editText(fieldCh, 'editBasicCase')
			);
		});
//	 	$("#zoomin").click(function(event){
//	 		$("#basicCaseDialog").createDialog(
//	 			editImg('图片', 'zoomin');
//	 		);
//	 	});
		function photosList(){
			$("#zoomin").click(function(event){
				$("#basicCasePhotosDialog").createDialog({
					width: 700,
					height: 600,
					title:"图片列表",
					url:dfops.path+"/basicCaseManage/getBasicCaseByOrgIdAndId.action?mode=zoomin&organization.id="+getCurrentOrgId()+"&baseInfoType="+dfops.baseInfoType,
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

		$("#add").click(function(event){
			addPartyworkMembers();
			return false;
		});
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setInterval(function(){introductionHeight()},200);
		});
		$("#leaderGroup li").die('mouseover').live('mouseover',function(){
			$(this).find(".leaderGroup-buttons").show();
		});
		$("#leaderGroup li").die('mouseout').live('mouseout',function(){
			$(this).find(".leaderGroup-buttons").hide();
		});
		
		function getPhotoList(){
			$.ajax({
				async:false,
				url:dfops.path+"/basicCaseManage/getBasicCaseByOrgIdAndId.action",
				data:{
					"organization.id":getCurrentOrgId(),
					"baseInfoType":dfops.baseInfoType,
					"mode":"upGrids"
				},
				success:function(responseData){
					if(responseData.caseImageUploads!=null && responseData.caseImageUploads!=""){
						for(var i=0; i <responseData.caseImageUploads.length;i++){
							$("#image_"+i+"").attr("src",function(){return dfops.path+""+"/"+responseData.caseImageUploads[i].imgUrl});
						}
						photosList();
					}else{
					 	$("#zoomin").click(function(event){
					 		$("#basicCaseDialog").createDialog(
					 			editImg('图片', 'editPhotos')
					 		);
				 		});
					}
					$("#basicCaseId").val(responseData.id);
					$("#maintainBaseCase_p").text('');
					$("#maintainBaseCase_p").append(responseData.baseCase);
				}
			});
		}
	});

	function introductionHeight(){
		$(".gridDiv").height($(".ui-layout-center").height()-$("#thisCrumbs").height()-10);
		positionPanel();
	}

	function membersSort(ajaxData){
		$.ajax({
			url:dfops.path+"/basicCaseManage/membersSort.action",
			data:ajaxData,
			success:function(responseData){
				if(responseData){
					$.messageBox({message:"排序成功"});	
				}else{
					$.messageBox({message:"排序失败",level: "error"});	
				}
			}
		});
	}
}