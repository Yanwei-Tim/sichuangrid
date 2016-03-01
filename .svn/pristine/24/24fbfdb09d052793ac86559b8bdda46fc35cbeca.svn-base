<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form id="searchRectificativePersonForm">
<pop:token />
	<div id="search-condition-form" title="矫正人员查询" class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">关注状态：</label>
		</div>
		<div class="grid_7">
	        <select id="isLock" name="searchRectificativePersonVo.isEmphasis" class="form-txt">
	            <option value="-1" selected="selected">全部</option>
	            <option value="0">现在关注</option>
	            <option value="1">曾经关注</option>
	        </select>
	     </div>
	   	<div class="grid_5 lable-right">
				<label class="form-lbl">死亡状态：</label>
		</div>
		<div class="grid_7">
	         <select id="isDeath" name="searchRectificativePersonVo.isDeath" class="form-txt">
	             <option value="-1" selected="selected">全部</option>
	             <option value="0">正常</option>
	             <option value="1">已死亡</option>
	         </select>
		</div>
	    <div class="grid_5 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" id="conditionIdCardNo" name="searchRectificativePersonVo.idCardNo" maxlength="18" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionName" name="searchRectificativePersonVo.name" class="form-txt" maxlength="20"/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">刑法执行类别：</label>
		</div>
		<div class="grid_7">
			<select id="conditionExecuteType" name="searchRectificativePersonVo.executeTypeId" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@EXECUTE_TYPE" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_7">
		    <input type="text" id="conditionMobileNumber" name="searchRectificativePersonVo.mobileNumber" maxlength="11" class="form-txt" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">矫正日期：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionRectifyStartDate" name="searchRectificativePersonVo.rectifyStartDate" class="form-txt" readonly="readonly"/>
		</div>
		<div class="grid_5 lable-right" >
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionRectifyEndDate" name="searchRectificativePersonVo.rectifyEndDate" class="form-txt" readonly="readonly" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_7">
			<select id="conditionGender" class="form-txt" name="searchRectificativePersonVo.genderId">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
			</select>
		</div>
		
		<div class="grid_5 lable-right">
		    <label class="form-lbl">文化程度：</label>
		</div>
		<div class="grid_7">
		    <select name="searchRectificativePersonVo.schooling.id" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"/>
		    </select>
		</div>
		
	 <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchRectificativePersonVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchRectificativePersonVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>

		<div class='clearLine'></div>
	    <div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
			<div id="showMoreCtt" style="display: none">
			 	<div class="grid_5 lable-right">
					<label class="form-lbl">出生日期 ：</label>
				</div>
				<div class="grid_7">
			    	<input type="text" id="conditionStartBirthday" readonly="readonly" name="searchRectificativePersonVo.startBirthday" class="form-txt" />
				</div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">至：</label>
				</div>
				<div class="grid_7">
			    	<input type="text" id="conditionEndBirthday" readonly="readonly" name="searchRectificativePersonVo.endBirthday" class="form-txt" />
				</div>
				<div class='clearLine'></div>
			   	<div class="grid_5 lable-right">
			  		<label class="form-lbl">户籍地：</label>
			  	</div>
			  	<div class="grid_5">
					<select id="conditionProvince" name="searchRectificativePersonVo.province" class="form-txt"></select>
			 	</div>
			  	<div class="grid_1 lable-right">
			  		<label class="form-lbl">省</label>
			  	</div>
			  	<div class="grid_5">
					<select id="conditionCity" name="searchRectificativePersonVo.city" class="form-txt"></select>
				</div>
			  	<div class="grid_1 lable-right">
			  		<label class="form-lbl">市</label>
			  	</div>
			  	<div class="grid_6">
					<select id="conditionDistrict" name="searchRectificativePersonVo.district" class="form-txt"></select>
			 	</div>
			  	<div class="grid_1 lable-right">
			  		<label class="form-lbl">县</label>
			  	</div>
			   	<div class='clearLine'></div>
			
			  	<div class="grid_5 lable-right">
			 		<label class="form-lbl">户籍详址：</label>
			 	</div>
			  	<div class="grid_19" >
				    <input type="text" id="conditionNativePlaceAddress" name="searchRectificativePersonVo.nativePlaceAddress"  maxlength="50" class="form-txt" style="width: 99%"/>
			  	</div>
			  	<div class='clearLine'></div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">常住地址：</label>
				</div>
				<div class="grid_19">
				    <input type="text" id="conditionCurrentlyAddress" name="searchRectificativePersonVo.currentAddress" maxlength="50" class="form-txt" style="width: 99%"/>
				</div>
			</div>
	   </div>		
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#rectificativePersonDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#rectificativePersonDialog").dialog( "option" , {height:330} );
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

	$('#conditionRectifyStartDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionRectifyEndDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionRectifyEndDate').datePicker({
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionRectifyStartDate").datepicker("option", "maxDate",date);
			}
		}
	});
});
</script>
