<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>    
<%@ taglib uri="/struts-tags" prefix="s" %>
<s:iterator  value="leaderTeamList" var="leaderTeam" status="s" >
	<li data-id="<s:property value="#leaderTeam.id" />" data-index="<s:property value="#s.index+1" />" id="leaderInfo<s:property value="#leaderTeam.id" />">
			<div class="imgBox">
			   <s:if test="#leaderTeam.imageUrl==null && #leaderTeam.imageUrl!=''">
				<img src="${resource_path}/resource/system/images/defaultAvatar.jpg"></img>
			   </s:if>
			   <s:else>
	   			<img  src="<s:property value="#leaderTeam.imageUrl" />"></img>
	   		   </s:else>
			</div>
			<div class="smallText">
				<span class="name"><s:property value="#leaderTeam.name" /></span>
				<s:if test="#leaderTeam.gender !=null && #leaderTeam.gender ==0 ">
				（男）
			    </s:if>
			   <s:elseif test="#leaderTeam.gender !=null && #leaderTeam.gender ==1 ">
			             （女）
			   </s:elseif>
	   		   <s:elseif test="#leaderTeam.gender !=null && #leaderTeam.gender ==2 ">
	   		    (未知)
	   		   </s:elseif>
				<strong class="post"><s:property value="#leaderTeam.duty" /></strong>
				<p class="info">
					 <s:property value="#leaderTeam.introduction" />
				</p>
			</div>
			<div class="leaderGroup-buttons" name="leaderOperate" lid="<s:property value="#leaderTeam.id" />">
			<pop:JugePermissionTag ename="updateLeaderTeam">
				<a href="javascript:;" class="base-button" name="update"><span>编辑</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteLeaderTeam">
				<a href="javascript:;" class="base-button" name="delete"><span>删除</span></a>
			</pop:JugePermissionTag>	
			</div>
	</li>
</s:iterator>
<script type="text/javascript">
   function updateLeaderInfo(obj){
	   var leaderId = $(obj).parent().attr("lid");
	   $("#leaderTeamMaintanceDialog").createDialog({
			width:500,
			height:410,
			title:'修改领导班子成员',
			modal : true,
			url:"${path}/baseinfo/villageProfile/dispatchLeaderOperate.action?mode=edit&leaderTeams.id="+leaderId,
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
   function deleteLeaderInfo(obj){
	   var leaderId = $(obj).parent().attr("lid");
	   $.confirm({
			title:"确认删除",
			message:"领导班子信息删除后，将无法恢复,您确认要删除领导班子吗?",
			width: 400,
			okFunc: function(){
			   $.ajax({
					url:"${path}/baseinfo/villageProfile/deleteLeaderTeam.action",
					data:{
						"leaderTeams.id":leaderId
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
        $("div[name=leaderOperate] a[name=update]").die().live('click',function(){
        	updateLeaderInfo(this);

        });
        $("div[name=leaderOperate] a[name=delete]").die().live('click',function(){
        	deleteLeaderInfo(this);

        });

	});
</script>
