<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="search-condition-form" title="流动人口查询" class="container container_24">
    <div class="grid_4 lable-right">
		<label class="form-lbl">注销状态：</label>
	</div>
   	<div class="grid_8">
		<select id="conditionLogOutState" class="form-txt">
			<option value="-1" selected="selected">全部</option>
			<option value="0">未注销</option>
			<option value="1">已注销</option>
		</select>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">死亡状态：</label>
	</div>
   	<div class="grid_8">
		<select id="conditionIsDeathState" class="form-txt">
			<option value="-1" selected="selected">全部</option>
			<option value="false">未死亡</option>
			<option value="true">已死亡</option>
		</select>
	</div>
    <div class="grid_4 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_8">
    	<input type="text" id="conditionIdCardNo" maxlength="18" class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">姓名：</label>
	</div>
	<div class="grid_8">
		<input type="text" id="conditionName" class="form-txt" maxlength="20"/>
	</div>
	<div class='clearLine'></div>
	<div  class="grid_4 lable-right">
  		<label class="form-lbl">流入原因：</label>
  	</div>
	<div class="grid_8">
		<select  id="conditionInflowingReason" class="form-txt" >
		   <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INFLOWING_REASON" />
		</select>
   	</div>
   	<div class="grid_4 lable-right">
		<label class="form-lbl">曾用名/别名：</label>
	</div>
	<div class="grid_8">
		<input type="text" id="conditionUsedName" class="form-txt" maxlength="20"/>
	</div>
   	<div class='clearLine'></div>
   	<div class="grid_4 lable-right">
   			<label class="form-lb1">流入时间：</label>
   	</div>
   	<div class="grid_8">
		<input type="text" id="conditionInflowingStartDate" class="form-txt" readonly="readonly"/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">至：</label>
	</div>
	<div class="grid_8">
		<input type="text" id="conditionInflowingEndDate" class="form-txt" readonly="readonly" />
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
   			<label class="form-lb1">预计到期日期：</label>
   	</div>
   	<div class="grid_8">
		<input type="text" id="conditionExpectedStartDatedue" class="form-txt" readonly="readonly"/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">至：</label>
	</div>
	<div class="grid_8">
		<input type="text" id="conditionExpectedEndDatedue" class="form-txt" readonly="readonly" />
	</div>
	<div class='clearLine'></div>
	
	
	<div class='clearLine'></div>
	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
	<div id="showMoreCtt" style="display: none">
	    <div class="grid_4 lable-right">
						<label class="form-lb1">流入来源：</label>
					</div>
					<div class="grid_5">
			<select  id="conditionInflowingProvince" class="form-txt" >
			</select>
				</div>
					<div class="grid_1">
		  				<label class="form-lbl">省</label>
					</div>
					<div class="grid_5">
			<select  id="conditionInflowingCity" class="form-txt" >
			</select>
			</div>
					<div class="grid_1">
		  				<label class="form-lbl">市</label>
					</div>
					<div class="grid_5">
			<select  id="conditionInflowingDistrict" class="form-txt" >
			</select>
				</div>
					<div class="grid_1">
		  				<label class="form-lbl">县</label>
					</div>
					<div class="clearLine">&nbsp;</div>
					<div  class="grid_4 lable-right">
					<label class="form-lbl">登记证情况：</label>
				</div>
		<div class="grid_8">
  				<select id="conditionRegistrationType" class="form-txt" >
  					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@REGISTRATIONTYPE"/>
			</select>
					</div>
					<div class="grid_4 lable-right">
			<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_8">
			<select id="conditionGender" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
			</select>
		</div>
		<div class="clearLine">&nbsp;</div>
					<div  class="grid_4 lable-right">
					<label class="form-lbl">登记日期：</label>
				</div>
		<div class="grid_8">
	    	<input type="text" id="conditionRegisterStartDate" readonly="readonly" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" id="conditionRegisterEndDate" readonly="readonly" class="form-txt" />
		</div>
		<div class='clearLine'></div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">出生日期：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" id="conditionStartBirthday" readonly="readonly" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" id="conditionEndBirthday" readonly="readonly" class="form-txt" />
		</div>
		<div class="clearLine">&nbsp;</div>
		 			<div  class="grid_4 lable-right">
					<label class="form-lbl">职业：</label>
				</div>
		<div class="grid_6">
  				<select id="conditionCareer" class="form-txt" >
  					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER"  />
			</select>
					</div>
					<div class="grid_6 lable-right">
						<label class="form-lb1">工作单位或就读学校：</label>
					</div>
					<div class="grid_8">
			 			<input type="text" id="conditionWorkUnit" maxlength="50"  class="form-txt" />
					</div>
					<div class="clearLine">&nbsp;</div>
	   	<div class="grid_4 lable-right">
	  		<label class="form-lbl">户籍地：</label>
	  	</div>
	  	<div class="grid_5">
			<select id="conditionProvince" class="form-select"></select>
	 	</div>
	  	<div class="grid_1">
	  		<label class="form-lbl">省</label>
	  	</div>
	  	<div class="grid_5">
			<select id="conditionCity" class="form-select"></select>
		</div>
	  	<div class="grid_1">
	  		<label class="form-lbl">市</label>
	  	</div>
	  	<div class="grid_5">
			<select id="conditionDistrict" class="form-select"></select>
	 	</div>
	  	<div class="grid_1">
	  		<label class="form-lbl">县</label>
	  	</div>
	   	<div class='clearLine'></div>
	
	  	<div class="grid_4 lable-right">
	 		<label class="form-lbl">户籍详址：</label>
	 	</div>
	  	<div class="grid_20" >
		    <input type="text" id="conditionNativePlaceAddress"  maxlength="50" class="form-txt" style="width: 99%"/>
	  	</div>
	  	<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">常住地址：</label>
		</div>
		<div class="grid_20">
		    <input type="text" id="conditionCurrentAddress"  maxlength="50" class="form-txt" style="width: 99%"/>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#floatingPopulationDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#floatingPopulationDialog").dialog( "option" , {height:330} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	
	var idCard = $("#searchByIdcard").val();
	if(idCard=="请输入身份证号码"){
		$("#conditionIdCardNo").val("");
	}else{
		$("#conditionIdCardNo").val(idCard);
	}
	threeSelect({
		province:'conditionProvince',
		city:'conditionCity',
		district:'conditionDistrict'
	});

	threeSelect({
		province:'conditionInflowingProvince',
		city:'conditionInflowingCity',
		district:'conditionInflowingDistrict'
	});

	$('#conditionStartBirthday').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionEndBirthday").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionEndBirthday').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionStartBirthday").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionInflowingStartDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionInflowingEndDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionInflowingEndDate').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionInflowingStartDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionExpectedStartDatedue').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionExpectedEndDatedue").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionExpectedEndDatedue').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionExpectedStartDatedue").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionRegisterStartDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionRegisterEndDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionRegisterEndDate').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionRegisterStartDate").datepicker("option", "maxDate",date);
			}
		}
	});
});
</script>
