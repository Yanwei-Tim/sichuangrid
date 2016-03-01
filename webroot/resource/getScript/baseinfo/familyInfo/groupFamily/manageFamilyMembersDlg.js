var manageFamilyMembersDlg = function (dfop){
	$("#groupFamilyMembersList").delegate(".deleteGroupFamilyMember","click",function(){
		var populationId = $(this).attr("populatId");
		var populationType = $(this).attr("populatType");
		$.confirm({
			title:"确认删除家庭成员",
			message:"确认要删除家庭成员吗?",
			okFunc: function(){
				$.ajax({
					async:false,
					url: PATH + '/baseinfo/familyInfo/deleteGroupFamilyMember.action?populationId='+populationId+'&populationType='+populationType+'&groupFamilyId='+$("#groupFamilyId").val(),
					type:'GET',
					dataType:'json',
					success : function(data){
						if(data==null||data==""){
								$.messageBox({message:"删除家庭成员成功！"});
								$("#existGroupFamilyMembersList").trigger("reloadGrid");
						}else{
								$.messageBox({message:data,level:"error"});
						}
					},
					error : function(){
						$.messageBox({
							message : "加载失败，请刷新页面！",
							level : "error"
						});
					}
				});
			}
		});
		$("#groupFamilyList").trigger("reloadGrid");
	});
	 $("#groupFamilyMembersList").delegate(".viewPopulationInfo","click",function(){
		var memberId = $(this).attr("memberId");
		var actionUrl;
		var pid = memberId.substr(0, memberId.indexOf("_"));
		var type = memberId.substr(memberId.indexOf("_")+1);
		if(type == dfop.populationType_HOUSEHOLD_STAFF){
			actionUrl =  PATH + '/baseinfo/householdStaff/dispath.action?mode=view&population.id='+pid;
		}else if(type == dfop.populationType_FLOATING_POPULATION){
			actionUrl =  PATH + '/baseinfo/floatingPopulationManage/dispath.action?mode=view&population.id='+pid;
		}else if(type == dfop.populationType_UNSETTLED_POPULATION){
			actionUrl =  PATH + '/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=view&unsettledPopulation.id='+pid;
		}else if(type == dfop.populationType_OVERSEA_STAFF) {
			actionUrl =  PATH + '/baseinfo/overseaPersonnelManage/dispatch.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+pid;
		}else return;
		$("#groupFamilyPopulationDialog").createDialog({
			width: 800,
			height: 600,
			title:'查看家庭成员信息',
			modal : true,
			url:actionUrl,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}); 
	
	
	$(document).ready(function(){
		
		function nameFont(el,options,rowData){
			return '<a href="javascript:void(0)" class="viewPopulationInfo" memberId="'+rowData.id+'"><span>'+rowData.population.name+'</span></a>';
		}
		function operateFormatter(el, options, rowData){
			return '<a href="javascript:void(0)" class="deleteGroupFamilyMember" populatId="'+rowData.populationIdEncryptId+'" populatType="'+rowData.populationType+'" ><U><font color="#6633FF">删除</font></U></a>';
		}
		 jQuery("#existGroupFamilyMembersList").jqGridFunction({
				datatype: "local",
			 	colNames:['id','populationIdEncryptId','populationId','populationType','姓名','性别','证件号','与家长关系','户籍地址/国籍','常住地址','操作'],
			 	height:200,
			   	colModel:[
				   {name:'id',index:'id',frozen:true,hidden:true,key:true},
				   {name:'populationIdEncryptId',index:'populationIdEncryptId',frozen:true,hidden:true,key:true},
				   {name:'populationId',index:'populationId',frozen:true,hidden:true,key:true},
				   {name:'populationType',index:'populationType',frozen:true,hidden:true,key:true},
		   	       {name:'population.name',width:80,formatter:nameFont,sortable:false},
		   	       {name:'population.gender',formatter:genderFormatter,width:40,sortable:false},
			       {name:'population.idCardNo',width:120,sortable:false},
			       {name:"familyRelation.displayName",width:80,sortable:false},
			       {name:'population.nativeLocation',width:150,sortable:false},
			       {name:'population.currentAddress',width:200,sortable:false},
			       {name:'operation',width:70,formatter:operateFormatter,align:"center",frozen:true,sortable:false}
			   	],
			   	showColModelButton:false
			   	
		});
	 jQuery("#existGroupFamilyMembersList").jqGrid('setFrozenColumns');
	 var initParam = {
				"groupFamilyId": $("#groupFamilyId").val()
			};
			$("#existGroupFamilyMembersList").setGridParam({
				url: PATH + '/baseinfo/familyInfo/findFamilyMembersByFamilyIdNoMasterAndIncludeRelation.action',
				datatype: "json",
				page:1
			});
			$("#existGroupFamilyMembersList").setPostData(initParam);
			$("#existGroupFamilyMembersList").trigger("reloadGrid");
    
    	function addFamilyMember(data,flag){
    		 $.ajax({
					url: PATH + '/baseinfo/familyInfo/addFamilyMember.action?familyRelationId='+$("#familyRelationId").val()+'&groupFamilyId='+$("#groupFamilyId").val()+'&savePeople.populationId='+data.populationId+'&savePeople.populationType='+data.populationType+'&whetherExistOtherFamily='+flag,
					type:'get',
					dataType:'json',
					success : function(data){
						if(data==null||data==""){
							$.messageBox({message:"新增家庭成员成功！"});
							$("#existGroupFamilyMembersList").trigger("reloadGrid");
							$("#idCardNo").val("");
							$("#familyRelationId option[value='']").attr("selected", true);
						}else{
							$.messageBox({message:data,level:"error"});
						}
					},
					error : function(){
						$.messageBox({
							message : "加载失败，请刷新页面！",
							level : "error"
						});
					}
				});
    	}
    	$("#addMember").click(function(){
    		var idCardNo=$("#idCardNo").val();
    		if( idCardNo==null ||idCardNo==""){
    			$.messageBox({message:"证件号码无效！",level:"warn"});
    			return ;
    		}
			 if($("#familyRelationId").val()==""||$("#familyRelationId").val()==null){
					$.messageBox({message:"与家长的关系不能为空！",level:"warn"});
					return ;
				}
			 $.ajax({
					url: PATH + '/baseinfo/familyInfo/judgeExistAtOrgCodeOfFamily.action?idCardNo='+$("#idCardNo").val()+'&familyRelationId='+$("#familyRelationId").val()+'&groupFamilyId='+$("#groupFamilyId").val(),
					type:'GET',
					dataType:'json',
					async :true,
					success : function(data){
						if(data != null){
							if(data.populationId != null){
								if(data.name == undefined  || data.name == null ){
									addFamilyMember(data,false);
								}else{
									if(data.name == $("input[name='groupFamily.familyMaster']").val() ){
										$.messageBox({message:"此人已在该家庭存在！",level:"warn"});
										$("#idCardNo").val("");
										$("#familyRelationId option[value='']").attr("selected", true);
									}else{
										$.confirm({
											title:"确认添加",
											message:data.name+"已存在其他家庭，确认添加后会将此人从原家庭中移除，是否确认添加?",
											width: 400,
											okFunc: function(){addFamilyMember(data,true);}
										});
									}
								}
							}else{
								$.messageBox({message:data,level:"error"});
							}
						}else{
							$.messageBox({message:"添加失败！！！",level:"error"});
						}
					},
					error : function(){
						$.messageBox({
							message : "加载失败，请刷新页面！",
							level : "error"
						});
					}
				});
			 $("#groupFamilyList").trigger("reloadGrid");
    	});
    	
     });
}