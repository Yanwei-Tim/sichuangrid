TQ.partyworkMembersList = function (dfop){
	function updateMembersInfo(obj){
		   var leaderId = $(obj).parent().attr("lid");
		   $("#partyworkMembersDialog").createDialog({
				width:500,
				height:410,
				title:'修改党工委成员',
				modal : true,
				url:dfop.path+"/basicCaseManage/dispatchPartyworkMembersOperate.action?mode=edit&partyworkMembers.id="+leaderId+"&baseInfoType="+dfop.baseInfoType,
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
	   function deleteMembersInfo(obj){
		   var leaderId = $(obj).parent().attr("lid");
		   $.confirm({
				title:"确认删除",
				message:"领导班子信息删除后，将无法恢复,您确认要删除领导班子吗?",
				width: 400,
				okFunc: function(){
				   $.ajax({
						url:dfop.path+"/basicCaseManage/deletePartyWorkMembers.action",
						data:{
							"partyworkMembers.id":leaderId
						},
						success:function(responseData){
							if(responseData){
								$("#leaderInfo"+leaderId).remove();
								$.messageBox({message:"删除成功"});	
								if($("#leaderGroup:hidden")[0]){
									$("#leaderGroupHeader").click();
								}
								positionPanel();		
							}else{
								$.messageBox({message:"删除失败",level:"error"});				
							}
						}
					});
				}
			});
	   }
	   $(document).ready(function(){
		   	initButtonsEnable();
			function initButtonsEnable(){
				if($("#currentOrgId").val()!=USER_ORG_ID){
					$("div[name=leaderOperate] a[class=base-button]").hide();
				}
			}
		   
	        $("div[name=leaderOperate] a[name=update]").die().live('click',function(){
	        	updateMembersInfo(this);

	        });
	        $("div[name=leaderOperate] a[name=delete]").die().live('click',function(){
	        	deleteMembersInfo(this);

	        });

		});
}