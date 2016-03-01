<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="search-condition-form" title="未落户人口查询" class="container container_24">
    <div class="grid_4 lable-right">
		<label class="form-lbl">注销状态：</label>
	</div>
	<div class="grid_8">
        <select id="logOut" class="form-txt">
            <option value="-1" selected="selected">全部</option>
            <option value="0">未注销</option>
            <option value="1">已注销</option>
        </select>
    </div>
   	<div class="grid_4 lable-right">
		<label class="form-lbl">死亡状态：</label>
	</div>
	<div class="grid_8">
        <select id="isDeath" name="unsettledPopulationCondition.isDeath" class="form-txt">
            <option value="-1" selected="selected">全部</option>
            <option value="0">正常</option>
            <option value="1">已死亡</option>
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
  		<label class="form-lbl">性别：</label>
  	</div>
	<div class="grid_8">
		<select id="conditionGender" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
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
   			<label class="form-lb1">未落户时间：</label>
   	</div>
   	<div class="grid_8">
		<input type="text" id="conditionUnsettedTimeStart" class="form-txt" readonly="readonly"/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">至：</label>
	</div>
	<div class="grid_8">
		<input type="text" id="conditionUnsettedTimeEnd" class="form-txt" readonly="readonly" />
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
   			<label class="form-lb1">未落户原因：</label>
   	</div>
   	<div class="grid_8">
   	<select name="unsettledPopulation.unSettedReason.id" class="form-select" id="conditionUnSettedReason">
		   <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@UNSETTEDREASON" defaultValue="${unsettledPopulation.unSettedReason.id}" />
	</select>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">持证种类：</label>
	</div>
	<div class="grid_8">
		<select name="unsettledPopulation.certificateType.id" class="form-select" id="conditionCertificateType">
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CERTIFICATEHOLD_TYPE" defaultValue="${unsettledPopulation.certificateType.id}" />
			</select>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">持证编号：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="unsettledPopulation.certificateNo" id="conditionCertificateNo" class="form-txt"
			value="<fmt:formatDate value="${unsettledPopulation.certificateNo}" type="date" pattern="yyyy-MM-dd" />"/>
	</div>
				
	
	<div class='clearLine'></div>
    <div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
   			<div class="grid_4 lable-right">
		    	<label class="form-lbl">婚姻状况： </label>
		    </div>
		    <div class="grid_8">
				<select name="unsettledPopulation.maritalState.id" class="form-select" id="conditionMaritalState">
				    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="${unsettledPopulation.maritalState.id}" />
				</select>
			</div>
	   	    <div class="grid_4 lable-right">
	            <label class="form-lbl">身高：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text" name="unsettledPopulation.stature" id="conditionStature" class="dialogtip form-txt" value="${unsettledPopulation.stature}"
	             maxlength="3"/>
	        </div>
	        <div class="grid_1">cm</div>
	        <div class='clearLine'></div>
	   			<div class="grid_4 lable-right">
				<label class="form-lbl">血型：</label>
			    </div>
			<div class="grid_8">
				<select name="unsettledPopulation.bloodType.id" class="form-select" id="conditionBloodType">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" defaultValue="${unsettledPopulation.bloodType.id}" />
				</select>
			</div>
	   			<div class="grid_4 lable-right">
	            <label class="form-lbl">宗教信仰：</label>
	        </div>
	        <div class="grid_8">
	        	<select name="unsettledPopulation.faith.id" class="form-select" id="conditionFaith">
			   <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH" defaultValue="${unsettledPopulation.faith.id}" />
				</select>
	        </div>	
	                <div class='clearLine'></div>	
	                <div class="grid_4 lable-right">
	            <label class="form-lbl">固定电话：</label>
	        </div>
	        <div class="grid_8">
	            <input type="text" name="unsettledPopulation.telephone" id="conditionTelephone" class="dialogtip form-txt" value="${unsettledPopulation.telephone}" title="请输入由数字和-组成的联系电话 例如：0577-88888888"
	            maxlength="20"/>
	        </div>
			<div class="grid_4 lable-right">
	            <label class="form-lbl">联系手机：</label>
	        </div>
	        <div class="grid_8">
	            <input type="text" name="unsettledPopulation.mobileNumber" id="conditionMobileNumber" class="dialogtip form-txt" value="${unsettledPopulation.mobileNumber}" title="请输入11位以1开头的联系手机 例如：13988888888"
	             maxlength="11"/>
	        </div>
	        <div class="grid_4 lable-right">
	            <label class="form-lbl">电子邮箱：</label>
	        </div>
	        <div class="grid_8">
	            <input type="text" name="unsettledPopulation.email" id="conditionEmail" class="dialogtip form-txt" value="${unsettledPopulation.email}" 
	             maxlength="50"/>
	        </div>	
	         <div class="grid_4 lable-right">
				<label class="form-lbl">民族： </label>
			</div>
			<div class="grid_8">
				<select name="unsettledPopulation.nation.id" class="form-select" id="conditionNation">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${unsettledPopulation.nation.id}" />
				</select>
			</div>
			<div class='clearLine'></div>
			 <div class="grid_4 lable-right">
				<label class="form-lbl">政治面貌： </label>
			</div>
			<div class="grid_8">
				<select name="unsettledPopulation.politicalBackground.id" class="form-select" id="conditionPoliticalBackground">
			   	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${unsettledPopulation.politicalBackground.id}" />
			</select>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">文化程度： </label>
			</div>
			<div class="grid_8">
				<select name="unsettledPopulation.schooling.id" class="form-select" id="conditionSchooling"
			    <c:if test='${mode=="view"}'>disabled='true'</c:if>>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${unsettledPopulation.schooling.id}" />
				</select>
			</div>
			<div class='clearLine'></div>
		 	<div class="grid_4 lable-right">
				<label class="form-lbl">出生日期  从：</label>
			</div>
			<div class="grid_8">
		    	<input type="text" id="conditionBirthdayStart" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">至：</label>
			</div>
			<div class="grid_8">
		    	<input type="text" id="conditionBirthdayEnd" readonly="readonly" class="form-txt" />
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
		 		<label class="form-lbl">户籍地详址：</label>
		 	</div>
		  	<div class="grid_20" >
			    <input type="text" id="conditionNativePlaceAddress"  maxlength="50" class="form-txt" style="width: 99%"/>
		  	</div>
		  	  <div class="grid_4 lable-right">
				<label class="form-lbl">常住地址：</label>
			</div>
			<div class="grid_20">
			    <input type="text" id="conditionCurrentAddress"  maxlength="50" class="form-txt" style="width: 99%"/>
			</div>
		  	<div class='clearLine'></div>
		</div>
   </div>		
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#unsettledPopulationMaintanceDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#unsettledPopulationMaintanceDialog").dialog( "option" , {height:330} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	
	var idCard = $("#searchByIdC").val();
	if(idCard=="请输入姓名"){
		$("#conditionName").val("");
	}else{
		$("#conditionName").val(idCard);
	}
	threeSelect({
		province:'conditionProvince',
		city:'conditionCity',
		district:'conditionDistrict'
	});



	$('#conditionBirthdayStart').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionBirthdayEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionBirthdayEnd').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionBirthdayStart").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionUnsettedTimeStart').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionUnsettedTimeEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionUnsettedTimeEnd').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionUnsettedTimeStart").datepicker("option", "maxDate",date);
			}
		}
	});
});

</script>
