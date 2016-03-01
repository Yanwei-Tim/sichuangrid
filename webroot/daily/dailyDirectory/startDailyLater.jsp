<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="startDailyLater" action="" method="post">
		<div class="grid_7 lable-right">
			<label class="form-lbl">提醒日期：</label>
		</div>
	    <div class="grid_15">
	        <input type="text" id="startDailyLaterDate" name="dailyYear.reminderDate" class="form-txt" />
	    </div>
	    <div class='clearLine'>&nbsp;</div><br>
	    <div class="grid_15" style="margin:0 0 0 20px">
			<label class="form-check-text">
				<input type="checkbox" id="automaticallyEnabled" value="1" name="dailyYear.whetherAutoStart" />
				是否自动启用
			</label>
		</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#startDailyLater").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			directoryAjaxSubmit();
		}
	});
	
	function directoryAjaxSubmit(){
		var dailyYearId = ${param.dailyYearId};
		$("#startDailyLater").ajaxSubmit({
		   url:'${path}/daily/dailyYearManage/whetherAutoStartDailyYear.action?dailyYear.id='+dailyYearId,
	       success: function(data){
				if(!data.id){
					$.messageBox({message:data,level: "error"});
					return;
				}
				$.messageBox({message:"台帐启用提醒设置成功!"});
				$("#dailyYearList").trigger("reloadGrid");
				$("#dailyDirectoryDialog").dialog("close");
				$("#dailyTrunkDirectoryDailog").dialog("close");
	 	   },
	 	   error: function(XMLHttpRequest, textStatus, errorThrown){
				$.messageBox({message:"提交错误",level: "error"	});
	 	   }
		 });
	}
	
	$('#startDailyLaterDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+365d',
    	minDate:new Date(),
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			}
		}
	});
});
</script>
