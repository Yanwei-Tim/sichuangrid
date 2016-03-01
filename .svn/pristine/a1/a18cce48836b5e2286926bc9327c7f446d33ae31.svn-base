<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchPartyMemberInfoForm" method="post">
<div class="container container_24">
	<div class="grid_5 lable-right">
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="hidden" name="searchPartyMemberInfoVo.organization.id" id="orgInternalCode" class="form-txt" value="${orgId}"/>
	    	<input type="text" name="orgInternalCode" id="orgInternalCode" class="form-txt" value="${population.organization.orgName}" readonly/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="searchPartyMemberInfoVo.idCardNo" id="conditionIdCardNo" class="form-txt" maxlength="18"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionName" name="searchPartyMemberInfoVo.name" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">出生日期 从：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionBirthday" name="searchPartyMemberInfoVo.birthday" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_5" align="center">
            <label class="form-lbl">至：&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
            <input type="text" id="endConditionBirthday" name="searchPartyMemberInfoVo.endBirthday" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">进入党支部时间 从：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionJoinPartyBranchDate" name="searchPartyMemberInfoVo.joinPartyBranchDate" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_5" align="center">
            <label class="form-lbl">至：&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
            <input type="text" id="endConditionJoinPartyBranchDate" name="searchPartyMemberInfoVo.endJoinPartyBranchDate" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_5 lable-right">
			<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_7">
			<select id="conditionGender" name="searchPartyMemberInfoVo.gender.id" class="form-select">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
			</select>
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">党员类型： </label>
        </div>
        <div class="grid_7">
            <select id="conditionPartyMemberType" name="searchPartyMemberInfoVo.partyMemberType.id" class="form-select">
                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYMEMBER_TYPE" />
            </select>
        </div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">入党时间从：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionJoinPartyDate" name="searchPartyMemberInfoVo.joinPartyDate" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_5" align="center">
            <label class="form-lbl">至：&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
            <input type="text" id="endConditionJoinPartyDate" name="searchPartyMemberInfoVo.endJoinPartyDate" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">培训情况：</label>
        </div>
        <div class="grid_7">
            <select id="conditionTrainingState" name="searchPartyMemberInfoVo.trainingState.id" class="form-select">
                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TRAINING_STATE" />
            </select>
        </div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">是否持流动党员证：</label>
        </div>
        <div class="grid_7">
			<select id="conditionIsFlowPartyCard" name="searchPartyMemberInfoVo.isFlowPartyCard" class="form-select">
                 <option></option>
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">流入时间从：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionFlowPartyBranchDate" name="searchPartyMemberInfoVo.flowPartyBranchDate" readonly="readonly" class="form-txt" />
        </div>
        <div class="grid_5" align="center">
            <label class="form-lbl">至：&nbsp;&nbsp;</label>
        </div>
        <div class="grid_7">
            <input type="text" id="endConditionFlowPartyBranchDate" name="searchPartyMemberInfoVo.endFlowPartyBranchDate" readonly="readonly" class="form-txt" />
        </div>
		<div class="clearLine">&nbsp;</div>
		<div class="grid_5 lable-right">
   			<label class="form-lb1">户籍地：</label>
   		</div>
   		<div class="grid_6">
			<select name="searchPartyMemberInfoVo.province" id="conditionProvince" class="form-txt" >
			</select>
  		</div>
   		<div class="grid_1">
   		  <label class="form-lbl">省</label>
   		</div>
   		<div class="grid_5">
			<select name="searchPartyMemberInfoVo.city" id="conditionCity" class="form-txt" >
			</select>
 		</div>
   		<div class="grid_1">
   		  <label class="form-lbl">市</label>
   		</div>
   		<div class="grid_5">
			<select name="searchPartyMemberInfoVo.district" id="conditionDistrict" class="form-txt" >
			</select>
  		</div>
   		<div class="grid_1">
   		  <label class="form-lbl">县</label>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_5 lable-right">
            <label class="form-lbl">流入地党支部：</label>
        </div>
        <div class="grid_19">
            <input type="text" id="conditionFlowPartyBranch" name="searchPartyMemberInfoVo.flowPartyBranch" class="form-txt" />
        </div>
        <div class="clearLine">&nbsp;</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">户籍地详址：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="conditionNativePlaceAddress" name="searchPartyMemberInfoVo.nativePlaceAddress" class="form-txt" />
		</div>

        <div class="clearLine">&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">常住地址：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="conditionCurrentAddress" name="searchPartyMemberInfoVo.currentAddress" class="form-txt" />
		</div>
        <div class='clearLine'></div>
    	<hr>

    	<div id="bs-accordion" style="padding-bottom:10px;">
	  		 <div>
		        <h1 class="" style="text-align:right;">更多查询条件</h1>
		        <div class="accdContent">

					<div class="grid_5 lable-right">
						<label class="form-lbl">民族：</label>
					</div>
					<div class="grid_7">
			            <select id="conditionNation" name="searchPartyMemberInfoVo.nation.id" class="form-select">
			                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" />
			            </select>
			        </div>
			        <div class="grid_5 lable-right">
						<label class="form-lbl">政治面貌：</label>
					</div>
					<div class="grid_7">
			            <select id="conditionPoliticalbackground" name="searchPartyMemberInfoVo.politicalbackground.id" class="form-select">
			                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
			            </select>
			        </div>
			        <div class="grid_5 lable-right">
						<label class="form-lbl">婚姻状况：</label>
					</div>
					<div class="grid_7">
			            <select id="conditionMaritalstate" name="searchPartyMemberInfoVo.maritalstate.id" class="form-select">
			                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" />
			            </select>
			        </div>
			        <div class="grid_5 lable-right">
						<label class="form-lbl">文化程度：</label>
					</div>
					<div class="grid_7">
			            <select id="conditionSchooling" name="searchPartyMemberInfoVo.schooling.id" class="form-select">
			                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" />
			            </select>
			        </div>
			        <div class="grid_5 lable-right">
						<label class="form-lbl">职业：</label>
					</div>
					<div class="grid_7">
					 	<select id="conditionCareer" name="searchPartyMemberInfoVo.career.id" class="form-select">
			               <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" />
			            </select>
					</div>
					<div class="grid_5 lable-right">
						<label class="form-lbl">工作单位或就读学校：</label>
					</div>
					<div class="grid_7">
						<input type="text" id="conditionWorkUnit" name="searchPartyMemberInfoVo.workUnit" class="form-txt" />
					</div>
					<div class="grid_5 lable-right">
			            <label class="form-lbl">身高 从：</label>
			        </div>
			        <div class="grid_7">
			            <input type="text" id="conditionStature" name="searchPartyMemberInfoVo.stature" class="form-txt" />
			        </div>
			        <div class="grid_5" align="center">
			            <label class="form-lbl">至：&nbsp;&nbsp;</label>
			        </div>
			        <div class="grid_7">
			            <input type="text" id="conditionEndStature" name="searchPartyMemberInfoVo.endStature" class="form-txt" />
			        </div>
			        <div class="grid_5 lable-right">
						<label class="form-lbl">血型：</label>
					</div>
					<div class="grid_7">
			            <select id="conditionBloodtype" name="searchPartyMemberInfoVo.bloodtype.id" class="form-select">
			                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
			            </select>
			        </div>
			        <div class="grid_5 lable-right">
						<label class="form-lbl">宗教信仰：</label>
					</div>
					<div class="grid_7">
			            <select id="conditionFaith" name="searchPartyMemberInfoVo.faith.id" class="form-select">
			                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH" />
			            </select>
			        </div>
			        <div class="grid_5 lable-right">
						<label class="form-lbl">电子邮箱：</label>
					</div>
					<div class="grid_7">
						<input type="text" id="conditionEmail" name="searchPartyMemberInfoVo.email" class="form-txt" />
					</div>
					<div class="grid_5 lable-right">
			            <label class="form-lbl">联系手机：</label>
			        </div>
			        <div class="grid_7">
			            <input type="text" id="conditionMobileNumber" name="searchPartyMemberInfoVo.mobileNumber" class="form-txt" maxlength="11" />
			        </div>
			        <div class="grid_5 lable-right">
			            <label class="form-lbl">固定电话：</label>
			        </div>
			        <div class="grid_7">
			            <input type="text" id="conditionTelephone" name="searchPartyMemberInfoVo.telephone" class="form-txt" maxlength="11" />
			        </div>
        	  </div>
	        </div>
	   </div>
</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	var _accordion=$("#bs-accordion div>h1");
	_accordion.toggle(
		function(){
		$("#partyMemberInfoDialog").dialog( "option" , {height:410});
	    buttom:($(window).height() - 350) * 0.5;
		$(this).next("div").show();
		$(this).text("隐藏更多条件");
		},
		function(){
		$("#partyMemberInfoDialog").dialog( "option" , {height:280} );
		$(this).next("div").hide();
		$(this).text("显示更多条件");
	});

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
				$("#conditionBirthday").datepicker("option", "minDate",date);
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
				$("#endConditionBirthday").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionJoinPartyBranchDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionJoinPartyBranchDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionJoinPartyBranchDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionJoinPartyBranchDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionFlowPartyBranchDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionFlowPartyBranchDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionFlowPartyBranchDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionFlowPartyBranchDate").datepicker("option", "maxDate",date);
			}
		}
	});

	$('#conditionJoinPartyDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionJoinPartyDate").datepicker("option", "minDate",date);
			}
		}
	});

	$('#endConditionJoinPartyDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endConditionJoinPartyDate").datepicker("option", "maxDate",date);
			}
		}
	});


});
</script>
