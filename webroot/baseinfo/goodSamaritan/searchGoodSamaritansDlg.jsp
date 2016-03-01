<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchGoodSamaritanForm">
	<pop:token />
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">关注状态：</label>
		</div>
		<div class="grid_7">
             <select id="isLock" name="searchGoodSamaritanVo.isEmphasis" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">现在关注</option>
                 <option value="1">曾经关注</option>
             </select>
        </div>
        <div class="grid_5 lable-right">
			<label class="form-lbl">死亡状态：</label>
		</div>
		<div class="grid_7">
             <select id="isDeath" name="searchGoodSamaritanVo.isDeath" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">正常</option>
                 <option value="1">已死亡</option>
             </select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="searchGoodSamaritanVo.idCardNo" id="conditionIdCardNo" class="form-txt" maxlength="18"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionName" name="searchGoodSamaritanVo.name" class="form-txt" />
		</div>
        
        <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchGoodSamaritanVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchGoodSamaritanVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
	
	
        
        <div class='clearLine'></div>
    	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
			<div class="grid_5 lable-right">
	            <label class="form-lbl">伤残等级： </label>
	        </div>
	        <div class="grid_7">
	            <select id="conditionDrugReason" name="searchGoodSamaritanVo.disableGradeType.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DISABLE_GRADE" />
	            </select>
	        </div>
	        
			<div class="grid_5 lable-right">
				<label class="form-lbl">是否牺牲：</label>
			</div>
			<div class="grid_7">
	            <select id="detoxicateCondition" name="searchGoodSamaritanVo.sacrificeType.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@IS_SACRIFICE" />
	            </select>
	        </div>
			 
			<div class="grid_5 lable-right">
	            <label class="form-lbl">参保情况：</label>
	        </div>
	        <div class="grid_7">
	            <input id="insureSituationOfLast" value="${insureSituationOfLast.id}" type="hidden">
				<input name="searchGoodSamaritanVo.insureSituationType.id" id="insureSituationType_realy" value="${companyPlace.type.id}" type="hidden">
				
			    <select id="insureSituationTypeId" name="insureSituationTypeId"  class="form-txt" onchange="changeType()" >
			    	<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@INSURE_SITUATION"  defaultValue="${population.insureSituationType.id}" 
					   reletionId="insureSituationSmallTypeId" reletionName="@com.tianque.domain.property.PropertyTypes@INSURE_SITUATION_SUB" id="insureSituationTypeId"/> 
			    </select>
	        </div>
	        
	        <div class="grid_5 lable-right">
	            <label class="form-lbl"></label>
	        </div>
	        <div class="grid_7">
	            <select id="insureSituationSmallTypeId" name="searchGoodSamaritanVo.insureSituationSmallType.id"  class="form-txt">
			    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INSURE_SITUATION_SUB"  defaultValue="${population.insureSituationSmallType.id }"  />
			    </select>
	        </div>
			
	       	<div class="grid_5 lable-right">
				<label class="form-lbl">困难类型：</label>
			</div>
			<div class="grid_7">
				<select id="conditionGender" name="searchGoodSamaritanVo.difficultType.id" class="form-txt">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DIFFICULT_TYPE_GOOD_SAMARITAN" />
				</select>
			</div>
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">职业：</label>
			</div>
			<div class="grid_7">
			 	<select id="conditionCareer" name="searchGoodSamaritanVo.career.id" class="form-txt">
	               <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER"  />
	            </select>
			</div>	
	
	 		<div class="grid_5 lable-right">
	            <label class="form-lbl">出生日期 ：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text" id="conditionBirthday" name="searchGoodSamaritanVo.startBirthday" readonly="readonly" class="form-txt" />
	        </div>
	        <div class="grid_5 lable-right" >
	            <label class="form-lbl">至：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text" id="endConditionBirthday" name="searchGoodSamaritanVo.endBirthday" readonly="readonly" class="form-txt" />
	        </div>
	
			
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">户籍地： </label>
			</div>
			<div class="grid_6">
				<select id="conditionProvince" name="searchGoodSamaritanVo.province" class="form-txt"></select>
			</div>
			<div class="grid_1 lable-right">
				<label class="form-lbl">省</label>
			</div>
			<div class="grid_5">
				<select id="conditionCity" name="searchGoodSamaritanVo.city" class="form-txt"></select>
			</div>
			<div class="grid_1 lable-right">
				<label class="form-lbl">市</label>
			</div>
			<div class="grid_5">
				<select id="conditionDistrict" name="searchGoodSamaritanVo.district" class="form-txt"></select>
			</div>
			<div class="grid_1 lable-right">
				<label class="form-lbl">县</label>
			</div>
			 
			<div class="grid_5 lable-right">
				<label class="form-lbl">户籍地详址：</label>
			</div>
			<div class="grid_19">
				<input type="text" id="conditionNativePlaceAddress" name="searchGoodSamaritanVo.nativePlaceAddress" class="form-txt" />
			</div>
	       
	        <div class="clearLine">&nbsp;</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">常住地址：</label>
			</div>
			<div class="grid_19">
				<input type="text" id="conditionCurrentAddress" name="searchGoodSamaritanVo.currentAddress" class="form-txt" />
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
