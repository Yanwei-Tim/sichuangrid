<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>

<form id="searchIdleYouthForm">
<pop:token />
<div id="search-condition-form" title="重点青少年查询" class="container container_24">
	<div class="grid_5 lable-right">
			<label class="form-lbl">关注状态：</label>
	</div>
	<div class="grid_7">
             <select id="isLock" name="searchIdleYouthVo.isEmphasis" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">现在关注</option>
                 <option value="1">曾经关注</option>
             </select>
	</div>
         	<div class="grid_5 lable-right">
			<label class="form-lbl">死亡状态：</label>
		</div>
	<div class="grid_7">
            <select id="isDeath" name="searchIdleYouthVo.isDeath" class="form-txt">
                <option value="-1" selected="selected">全部</option>
                <option value="0">正常</option>
                <option value="1">已死亡</option>
      		 </select>
	</div>
    <div class="grid_5 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_7">
    	<input type="text" id="conditionIdCardNo" maxlength="18" class="form-txt"  name="searchIdleYouthVo.idCardNo"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionName" class="form-txt" maxlength="20"  name="searchIdleYouthVo.name"/>
	</div>
	<div class='clearLine'></div>
	<div  class="grid_5 lable-right">
  		<label class="form-lbl">性别：</label>
  	</div>
	<div class="grid_7">
		<select id="conditionGender" class="form-txt" name="searchIdleYouthVo.gender.id">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
		</select>
   	</div>
   	<div class="grid_5 lable-right">
		<label class="form-lbl">曾用名/别名：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionUsedName" class="form-txt" maxlength="20" name="searchIdleYouthVo.usedName"/>
	</div>
	<div class='clearLine'></div>
	<!-- Jeffrey -->
	<div class="grid_5 lable-right">
        <label class="form-lbl">是否在校住宿：</label>
    </div>
    <div  class="grid_7">
    <s:select cssClass="form-txt" name="searchIdleYouthVo.hasIsStayInSchoolType.code" listKey="code" list="@com.tianque.search.vo.QuickFilterType@DEFAULT_BOOLEAN_STATE_FILTER_TYPES" ></s:select>    
    </div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">学校名称：</label>
	</div>
	<div class="grid_7">
    	<input type="text" id="schoolName" name="searchIdleYouthVo.schoolName" maxlength="30" class="form-txt" />
	</div>
	 <jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchIdleYouthVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchIdleYouthVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
   	<div class='clearLine'></div>
   	<div class="grid_5 lable-right">
   			<label class="form-lb1">人员类型：</label>
   	</div>
    <div class="grid_19"  id="conditionStaffTypeId">
	    <pop:PropertyDictMultiCheckbox name="staffTypeIds" column="2" domainName="@com.tianque.domain.property.PropertyTypes@IDLEYOUTH_STAFF_TYPE"  />
	</div>
	
   	<br/> 	<br/> 	<br/> 	<br/>	<br/>	<br/>	<br/>
   	
   	<div class='clearLine'></div>
   	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
   			<div class="grid_5 lable-right">
		    <label class="form-lbl">婚姻状况： </label>
		    </div>
		    <div class="grid_7">
				<select  class="form-txt" id="conditionMaritalState" name="searchIdleYouthVo.maritalState.id">
				    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS"  />
				</select>
			</div>
   			<div class="grid_5 lable-right">
			<label class="form-lbl">血型：</label>
		    </div>
			<div class="grid_7">
				<select  class="form-txt" id="conditionBloodType" name="searchIdleYouthVo.bloodType.id">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE"  />
				</select>
			</div>
	   	    <div class="grid_5 lable-right">
				<label class="form-lbl">身高从：</label>
			</div>
			<div class="grid_7" id="statureDiv">
		    	<input type="text" id="statureStart" maxlength="3" class="form-txt" name="searchIdleYouthVo.statureStart"/>
			</div>
			<div class="grid_5 lable-right">
				&nbsp;至：
			</div>
			<div class="grid_7" id="statureDiv">
		    	<input type="text" id="statureEnd" maxlength="3" class="form-txt" name="searchIdleYouthVo.statureEnd"/>
			</div>
	        <div class='clearLine'></div>	
	                <div class="grid_5 lable-right">
	            <label class="form-lbl">固定电话：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text"  id="conditionTelephone" class="dialogtip form-txt" name="searchIdleYouthVo.telephone" title="请输入由数字和-组成的联系电话 例如：0577-88888888"
	            maxlength="20"/>
	        </div>
			<div class="grid_5 lable-right">
	            <label class="form-lbl">联系手机：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text"  id="conditionMobileNumber" class="dialogtip form-txt" name="searchIdleYouthVo.mobileNumber" title="请输入11位以1开头的联系手机 例如：13988888888"
	             maxlength="11"/>
	        </div>
	        <div class="grid_5 lable-right">
	            <label class="form-lbl">电子邮箱：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text"  id="conditionEmail" class="dialogtip form-txt" name="searchIdleYouthVo.email"
	             maxlength="50"/>
	        </div>	
	        <div class="grid_5 lable-right">
				<label class="form-lbl">民族： </label>
			</div>
			<div class="grid_7">
				<select  class="form-txt" id="conditionNation"  name="searchIdleYouthVo.nation.id">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION"  />
				</select>
			</div>
			<div class='clearLine'></div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">政治面貌： </label>
			</div>
			<div class="grid_7">
				<select  class="form-txt" id="conditionPoliticalBackground" name="searchIdleYouthVo.politicalBackground.id">
			   	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND"  />
			</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">文化程度： </label>
			</div>
			<div class="grid_7">
				<select  class="form-txt" id="conditionSchooling" name="searchIdleYouthVo.schooling.id">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"  />
				</select>
			</div>
			<div class='clearLine'></div>
		 	<div class="grid_5 lable-right">
				<label class="form-lbl">出生日期  从：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" id="conditionBirthdayStart" readonly="readonly" class="form-txt"  name="searchIdleYouthVo.birthdayStart"/>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">至：</label>
			</div>
			<div class="grid_7">
		    	<input type="text" id="conditionBirthdayEnd" readonly="readonly" class="form-txt" name="searchIdleYouthVo.birthdayEnd" />
			</div>
			<div class="clearLine">&nbsp;</div>
 		 			<div  class="grid_5 lable-right">
						<label class="form-lbl">职业：</label>
					</div>
			<div class="grid_7">
   				<select id="conditionCareer" class="form-txt" name="searchIdleYouthVo.career.id">
   					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER"  />
				</select>
 					</div>
 					<div class="grid_5 lable-right">
 						<label class="form-lb1">工作单位或就读学校：</label>
 					</div>
 					<div class="grid_7">
 			 			<input type="text" id="conditionWorkUnit" maxlength="50"  class="form-txt" name="searchIdleYouthVo.workUnit"/>
 					</div>
 					<div class="clearLine">&nbsp;</div>
		   	<div class="grid_5 lable-right">
		  		<label class="form-lbl">户籍地：</label>
		  	</div>
		  	<div class="grid_5">
				<select id="conditionProvince" class="form-txt" name="searchIdleYouthVo.province"></select>
		 	</div>
		  	<div class="grid_1">
		  		<label class="form-lbl">省</label>
		  	</div>
		  	<div class="grid_5">
				<select id="conditionCity" class="form-txt" name="searchIdleYouthVo.city"></select>
			</div>
		  	<div class="grid_1">
		  		<label class="form-lbl">市</label>
		  	</div>
		  	<div class="grid_5">
				<select id="conditionDistrict" class="form-txt" name="searchIdleYouthVo.district"></select>
		 	</div>
		  	<div class="grid_1">
		  		<label class="form-lbl">县</label>
		  	</div>
		   	<div class='clearLine'></div>
		
		  	<div class="grid_5 lable-right">
		 		<label class="form-lbl">户籍地详址：</label>
		 	</div>
		  	<div class="grid_19" >
			    <input type="text" id="conditionNativePlaceAddress"  maxlength="50" class="form-txt" style="width: 99%" name="searchIdleYouthVo.nativePlaceAddress"/>
		  	</div>
		  	  <div class="grid_5 lable-right">
				<label class="form-lbl">常住地址：</label>
			</div>
			<div class="grid_19">
			    <input type="text" id="conditionCurrentAddress"  maxlength="50" class="form-txt" style="width: 99%" name="searchIdleYouthVo.currentAddress"/>
			</div>
		  	<div class='clearLine'></div>
		</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#conditionStaffTypeId li").width("49%");
	$("#showMoreBtn").toggle(
		function(){
			$("#idleYouthDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#idleYouthDialog").dialog( "option" , {height:330} );
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
});
</script>

