<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<style type="text/css">
.update li{ font-size:14px; line-height:22px; padding:0px 5px 10px 5px;}
.update li.red{ color:#C30; font-weight:640}
.update li.title{ text-align:center;  background-color:#fafafa; padding-top:10px; font-weight:640; margin-bottom:5px; font-size:16px;}
</style>

<form id="mForm" action="${path}/sysadmin/userCheckUpgradeContentManage/saveUserCheckUpgradeContent.action" method="post">
	<input type="hidden" id="checkState" name="userCheckUpgradeContent.checkState"/>
	<input type="hidden" id="id" name="userCheckUpgradeContent.id" value="${userCheckUpgradeContent.id}"/>
	<input type="hidden" id="upgradeContentId" name="userCheckUpgradeContent.upgradeContent.id" value="${userCheckUpgradeContent.upgradeContent.id}" />
	<input type="hidden" id="userId" name="userCheckUpgradeContent.user.id" />
	<div class="MgsBox" style="overflow:scroll;overflow-x:hidden;">
	 <div class="boxCon" style="width: 622px; height: 430px;">
	   <ol class="update">
	   	<li class="title"><s:date name='userCheckUpgradeContent.upgradeContent.upgradeDate' format='yyyy-MM-dd'/></li>
	   	<li class="red">${userCheckUpgradeContent.upgradeContent.upgradeContent}</li>
	   </ol>
	 </div>
	</div>
</form>

<script type="text/javascript">
$(document).ready(function(){
	$("#userId").val(USER_ID);
	$("#mForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			$("#mForm").ajaxSubmit({
				success : function(data) {
					if(data != null && data.id != null){
						$("#userCheckUpgradeContentDialog").dialog("close");
			    		$.messageBox({message:"操作成功!"});
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
		      			alert("提交数据时发生错误");
	   		    }
			});
		}
	})
});

function excport(){
	var selectedId = $("#upgradeContentId").val();
	if(selectedId == null || selectedId.length == 0){
		$.messageBox({level:'warn',message:"请选择一条记录，再进行操作！"});
		 return;
	}
// 	if(selectedId.length > 1){
// 		$.messageBox({level:'warn',message:"只能选择一条数据，进行操作!"});
// 		 return;
// 	}
	$("#upgradeContentManageDialog").createDialog({
		width:700,
		height:420,
		title:'升级内容-导出Word',
		modal : true,
		resizable : true,
		url:'${path}/sysadmin/upgradeContentManage/dispatch.action?mode=printWord&id='+ selectedId,
		buttons: {
			"导出word" : function(event){
				exportWord(selectedId);
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
   });
}
function exportWord(selectedId){
	this.location="${path}/sysadmin/upgradeContentManage/dispatch.action?mode=printWord&id="+ selectedId;
}
</script>


