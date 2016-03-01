<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchPeopleLogForm">
	<div class="container container_24">
		<div class="grid_4 lable-right">
			<label class="form-lbl">创建时间从：</label>
		</div>
		<div class="grid_7 " id="expiryDateDiv">
	    	<input type="text" name="searchPeopleLogVo.startDate" id="dateStart"
				maxlength="30" readonly class="form-txt " />
		</div>
		<div class="grid_4 lable-right">
			至：
		</div>
		<div class="grid_7" id="expiryDateDiv">
	    	<input type="text" name="searchPeopleLogVo.endDate" id="dateEnd" maxlength="50" readonly class="form-txt " />
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lb1">日志所属人：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="searchPeopleLogVo.belonger" id="belonger" maxlength="30"  class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">点评次数从：</label>
		</div>
		<div class="grid_3" id="sumCommentDiv">
	    	<input type="text" name="searchPeopleLogVo.sumCommentMin" id="sumCommentMin" 
	    		maxlength="10" style="width: 48px"  class="form-txt "/>
		</div>
		<div class="grid_2 lable-right">
			至：
		</div>
		<div class="grid_3" id="sumCommentDiv">
	    	<input type="text" name="searchPeopleLogVo.sumCommentMax" id="sumCommentMax" 
	    		maxlength="10" style="width: 48px"  class="form-txt "/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">标题：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="searchPeopleLogVo.title" id="title" 
				maxlength="50" style="width: 455px" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">地点：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="searchPeopleLogVo.address" id="address" 
				maxlength="50" style="width: 455px" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">点评人：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="searchPeopleLogVo.commentLog.commentUser" id="commentUser" 
			maxlength="50" style="width: 455px" class="form-txt"  />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">点评语：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="searchPeopleLogVo.commentLog.comments" id="comments" 
				maxlength="50" style="width: 455px" class="form-txt"  />
		</div>
	</div>
</form>
<script type="text/javascript">
$(function(){
	var patrn = /^[0-9]*$/;
	$("#dateStart").datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#dateEnd").datepicker("option", "minDate",date);
			}
		}
	});
	$("#dateEnd").datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#dateStart").datepicker("option", "maxDate",date);
			}
		}
	});
	$("#sumCommentMin").blur( function () {
		var sumCommentMin = $("#sumCommentMin").val(); 
		var sumCommentMax = $("#sumCommentMax").val();
		if (sumCommentMin == "") {
			sumCommentMin = 0;
			return;
		}
		if(!patrn.exec(sumCommentMin)){
			$.messageBox({message:"点评次数必须为数字!",level:"warn"});
			$("#sumCommentMin").val("");
			return;
		}
		if (sumCommentMax == "") {
			return;
		}
		
	});
	$("#sumCommentMax").blur( function () {
		var sumCommentMin = $("#sumCommentMin").val(); 
		var sumCommentMax = $("#sumCommentMax").val();
		if (sumCommentMax == "") {
			sumCommentMax = 0;
			return;
		}
		if(!patrn.exec(sumCommentMax)){
			$.messageBox({message:"点评次数必须为数字!",level:"warn"});
			$("#sumCommentMax").val("");
			return;
		}
		if (sumCommentMin == "") {
			return;
		}
		
	}); 
});

</script>
