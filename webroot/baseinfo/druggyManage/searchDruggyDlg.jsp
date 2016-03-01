<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchDruggyForm">
	<pop:token />
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">关注状态：</label>
		</div>
		<div class="grid_7">
             <select id="isLock" name="searchDruggyVo.isEmphasis" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">现在关注</option>
                 <option value="1">曾经关注</option>
             </select>
        </div>
        <div class="grid_5 lable-right">
			<label class="form-lbl">死亡状态：</label>
		</div>
		<div class="grid_7">
             <select id="isDeath" name="searchDruggyVo.isDeath" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">正常</option>
                 <option value="1">已死亡</option>
             </select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="searchDruggyVo.idCardNo" id="conditionIdCardNo" class="form-txt" maxlength="18"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionName" name="searchDruggyVo.name" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">戒毒情况：</label>
        </div>
        <div class="grid_7">
            <select id="conditionDetoxicateCase" name="searchDruggyVo.detoxicateCase.id" class="form-txt">
                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CASE" />
            </select>
        </div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">联系手机：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionMobileNumber" name="searchDruggyVo.mobileNumber" class="form-txt" maxlength="11" />
        </div>
        
        <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchDruggyVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchDruggyVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
	
	<div class="grid_5 lable-right">
	    <label class="form-lbl">文化程度：</label>
	</div>
	<div class="grid_7">
	    <select name="searchDruggyVo.schooling.id" class="form-txt">
	        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"/>
	    </select>
	</div>
        
        <div class='clearLine'></div>
    	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
			<div class="grid_5 lable-right">
	            <label class="form-lbl">吸毒原因： </label>
	        </div>
	        <div class="grid_7">
	            <select id="conditionDrugReason" name="searchDruggyVo.drugReason.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DRUG_REASON" />
	            </select>
	        </div>
	        
			<div class="grid_5 lable-right">
				<label class="form-lbl">吸毒状态：</label>
			</div>
			<div class="grid_7">
	            <select id="detoxicateCondition" name="searchDruggyVo.detoxicateCondition.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CONDITION" />
	            </select>
	        </div>
			<%-- 
			<div class="grid_5 lable-right">
	            <label class="form-lbl">毒品来源：</label>
	        </div>
	        <div class="grid_7">
	            <select id="conditionDrugSource" name="searchDruggyVo.drugSource.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DRUG_SOURCE" />
	            </select>
	        </div>
			 --%>
	       	<div class="grid_5 lable-right">
				<label class="form-lbl">性别：</label>
			</div>
			<div class="grid_7">
				<select id="conditionGender" name="searchDruggyVo.gender.id" class="form-txt">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
				</select>
			</div>
	
	        <div class="grid_5 lable-right">
				<label class="form-lbl">滥用毒品种类：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="conditionDrugType" name="searchDruggyVo.drugType" class="form-txt" maxlength="50" />
			</div>
	
	
			<div class="grid_5 lable-right">
				<label class="form-lbl">管控现状：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="conditionControlActuality" name="searchDruggyVo.controlActuality" class="form-txt" />
			</div>
	
			<div class="grid_5 lable-right">
				<label class="form-lbl">职业：</label>
			</div>
			<div class="grid_7">
			 	<select id="conditionCareer" name="searchDruggyVo.career.id" class="form-txt">
	               <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER"  />
	            </select>
			</div>					
			<div class="grid_5 lable-right">
				<label class="form-lbl">工作单位或就读学校：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="conditionWorkUnit" name="searchDruggyVo.workUnit" class="form-txt" />
			</div>
	
			<div class='clearLine'></div>
	 		<div class="grid_5 lable-right">
	            <label class="form-lbl">出生日期 ：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text" id="conditionBirthday" name="searchDruggyVo.birthday" readonly="readonly" class="form-txt" />
	        </div>
	        <div class="grid_5 lable-right" >
	            <label class="form-lbl">至：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text" id="endConditionBirthday" name="searchDruggyVo.endBirthday" readonly="readonly" class="form-txt" />
	        </div>
	
			<div class="grid_5 lable-right">
				<label class="form-lbl">查获日期 ：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="conditionFerretOutDate" name="searchDruggyVo.ferretOutDate" readonly="readonly" class="form-txt" />
			</div>
			 <div class="grid_5 lable-right">
				<label class="form-lbl">至：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="endConditionFerretOutDate" name="searchDruggyVo.endFerretOutDate" readonly="readonly" class="form-txt" />
			</div>
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">首吸时间 ：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="conditionDrugfristDate" name="searchDruggyVo.drugfristDate" readonly="readonly" class="form-txt" />
			</div>
			 <div class="grid_5 lable-right">
				<label class="form-lbl">至：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="endConditionDrugfristDate" name="searchDruggyVo.endDrugfristDate" readonly="readonly" class="form-txt" />
			</div>
	
			<div class="grid_5 lable-right">
				<label class="form-lbl">户籍地： </label>
			</div>
			<div class="grid_6">
				<select id="conditionProvince" name="searchDruggyVo.province" class="form-txt"></select>
			</div>
			<div class="grid_1 lable-right">
				<label class="form-lbl">省</label>
			</div>
			<div class="grid_5">
				<select id="conditionCity" name="searchDruggyVo.city" class="form-txt"></select>
			</div>
			<div class="grid_1 lable-right">
				<label class="form-lbl">市</label>
			</div>
			<div class="grid_5">
				<select id="conditionDistrict" name="searchDruggyVo.district" class="form-txt"></select>
			</div>
			<div class="grid_1 lable-right">
				<label class="form-lbl">县</label>
			</div>
			 
			<div class="grid_5 lable-right">
				<label class="form-lbl">户籍地详址：</label>
			</div>
			<div class="grid_19">
				<input type="text" id="conditionNativePlaceAddress" name="searchDruggyVo.nativePlaceAddress" class="form-txt" />
			</div>
	       
	        <div class="clearLine">&nbsp;</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">常住地址：</label>
			</div>
			<div class="grid_19">
				<input type="text" id="conditionCurrentAddress" name="searchDruggyVo.currentAddress" class="form-txt" />
			</div>
		</div>
	</div>		
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#druggyDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#druggyDialog").dialog( "option" , {height:330} );
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

	$('#conditionBirthday').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionBirthday").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionBirthday').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionBirthday").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionFerretOutDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#endConditionFerretOutDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionFerretOutDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionFerretOutDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionDrugfristDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#endConditionDrugfristDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionDrugfristDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionDrugfristDate").datepicker("option", "maxDate",date);
			}
		}
	});
});
</script>
