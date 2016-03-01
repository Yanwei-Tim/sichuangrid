<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
	#maintainForm .itemBox{border: 1px solid rgb(211, 207, 207);margin-top: 5px;}
	#maintainForm .addItem{color:blue;padding:0 0 0 5px;}
	#maintainForm .delItem a{padding:0 10px;color:#f60;}
</style>
<div id="dialog-form" title="街道社区党组织表" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">党组织名称：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="searchVo.name" maxlength="30" class="form-txt""/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">党组织类型：</label>
	 	</div>
		<div class="grid_7">
			<select name="searchVo.type" id="partyOrgType" class='form-txt '>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">成立时间 从：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchVo.minFoundDate" id="minFoundDate" readonly class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">到：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchVo.maxFoundDate" id="maxFoundDate" readonly class="form-txt"/>
		</div>
	 	<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">党支部书记：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchVo.secretary" maxlength="30" class="form-txt"/>
		</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">身份证号码：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchVo.idCardNo" maxlength="18" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系手机：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchVo.mobileNumber" maxlength="11"  class="form-txt" />
		</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchVo.telephone" maxlength="15"  class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">党组织地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="searchVo.address" maxlength="50" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script type="text/javascript">

$(document).ready(function(){
	$("#minFoundDate").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#maxFoundDate").datepicker("option","minDate",date);
			}
		}
    }); 

	$("#maxFoundDate").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+0d",
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#minFoundDate").datepicker("option", "maxDate",date);
			}
		}
	});
	
});

</script>


