<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="searchAidNeedPopulationForm">
<pop:token />
	<div id="search-condition-form" title="需救助人员查询" class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">关注状态：</label>
		</div>
		<div class="grid_7">
			<select id="isLock" name="searchAidNeedPopulationVo.isEmphasis" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">现在关注</option>
				<option value="1">曾经关注</option>
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">死亡状态：</label>
		</div>
	   	<div class="grid_7">
			<select id="isDeathState" name="searchAidNeedPopulationVo.isDeath" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">未死亡</option>
				<option value="1">已死亡</option>
			</select>
		</div>
		<div class='clearLine'></div>
	    <div class="grid_5 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" id="conditionIdCardNo" name="searchAidNeedPopulationVo.idCardNo" maxlength="18" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionName" name="searchAidNeedPopulationVo.name" class="form-txt" maxlength="20"/>
		</div>
		<div class='clearLine'></div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">出生日期：</label>
		</div>
		<div class="grid_1 lable-left">
			<label class="form-lbl">从</label>
		</div>
		<div class="grid_3" id="birthdayDiv">
	    	<input type="text" id="conditionBirthdayStart" name="searchAidNeedPopulationVo.birthdayStart" readonly class="form-txt" />
		</div>
		<div class="grid_1 lable-right">
			至
		</div>
		<div class="grid_3" id="birthdayDiv">
	    	<input type="text" id="conditionBirthdayEnd" name="searchAidNeedPopulationVo.birthdayEnd" readonly class="form-txt" />
		</div>
		
		<div class="grid_4 lable-right">
		<label class="form-check-text">是否低保户： </label>
		</div>
		<div class="grid_7">
			<s:select cssClass="form-txt" id="isLowHouseholds" name="searchAidNeedPopulationVo.isLowHouseholds.code" listKey="code" list="@com.tianque.search.vo.QuickFilterType@DEFAULT_BOOLEAN_STATE_FILTER_TYPES" ></s:select>
		 </div>
		
		<div  class="grid_5 lable-right">
	  		<label class="form-lbl">性别：</label>
	  	</div>
		<div class="grid_7">
			<select id="conditionGender" class="form-txt" name="searchAidNeedPopulationVo.gender.id">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
			</select>
	   	</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">救助原因：</label>
		</div>
		<div class="grid_7">
            <select  id="conditionAidReason" class="form-txt" name="searchAidNeedPopulationVo.aidReason.id" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@AIDRREASON"/>
			</select>
        </div>
  
		<div class='clearLine'>&nbsp;</div>
		<div id="showOrHidden1">
		<div class="grid_5 lable-right" >
			<label class="form-lbl">困难证号：</label>
		</div>
        <div class="grid_7">
			<input type="text" id="conditionDifficultCardNo"  class="form-txt" maxlength="30" name="searchAidNeedPopulationVo.difficultCardNo"/>
		</div>
			<div class="grid_5 lable-right">
			<label class="form-lbl">困难类型：</label>
		</div>
		<div class="grid_7">
          <select  id="conditiondifficultType" class="form-txt" name="searchAidNeedPopulationVo.difficultType.id">
   		      <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DIFFICULT_TYPE" />
		</select>
        </div>
        </div>
      	<div class="clearLine">&nbsp;</div>
	   	<div class="grid_5 lable-right">
	  		<label class="form-lbl">户籍地：</label>
	  	</div>
	  	<div class="grid_5">
			<select id="conditionProvince" class="form-txt" name="searchAidNeedPopulationVo.province"></select>
	 	</div>
	  	<div class="grid_1">
	  		<label class="form-lbl">省</label>
	  	</div>
	  	<div class="grid_5">
			<select id="conditionCity" class="form-txt" name="searchAidNeedPopulationVo.city"></select>
		</div>
	  	<div class="grid_1">
	  		<label class="form-lbl">市</label>
	  	</div>
	  	<div class="grid_5">
			<select id="conditionDistrict" class="form-txt" name="searchAidNeedPopulationVo.district"></select>
	 	</div>
	  	<div class="grid_1">
	  		<label class="form-lbl">县</label>
	  	</div>
	   	<div class='clearLine'></div>
	
	  	<div class="grid_5 lable-right">
	 		<label class="form-lbl">户籍地详址：</label>
	 	</div>
	  	<div class="grid_19" >
		    <input type="text" id="conditionNativePlaceAddress"  maxlength="50" class="form-txt" style="width: 99%" name="searchAidNeedPopulationVo.nativePlaceAddress"/>
	  	</div>
	  	  <div class="grid_5 lable-right">
			<label class="form-lbl">常住地址：</label>
		</div>
		<div class="grid_19">
		    <input type="text" id="conditionCurrentAddress"  maxlength="50" class="form-txt" style="width: 99%" name="searchAidNeedPopulationVo.currentAddress"/>
		</div>
		
		<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<jsp:param value="服务成员：" name="memberLabelName"/>
			<jsp:param value="searchAidNeedPopulationVo.hasServiceTeamMember" name="memberSelectName"/>
			<jsp:param value="服务记录：" name="recordLabelName"/>
			<jsp:param value="searchAidNeedPopulationVo.hasServiceTeamRecord" name="recordSelectName"/>
		</jsp:include>
		
	  	<div class='clearLine'></div>
		<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
	        <div class="grid_5 lable-right">
				<label class="form-lbl">民族： </label>
			</div>
			<div class="grid_7">
				<select  class="form-txt" id="conditionNation" name="searchAidNeedPopulationVo.nation.id">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION"  />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">政治面貌： </label>
			</div>
			<div class="grid_7">
				<select class="form-txt" id="conditionPoliticalBackground" name="searchAidNeedPopulationVo.politicalBackground.id">
			   	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND"  />
			</select>
			</div>
		 	<div class='clearLine'>&nbsp;</div>
		    <div  class="grid_5 lable-right">
				<label class="form-lbl">职业：</label>
			 </div>
			 <div class="grid_7">
				<select  class="form-txt" id="conditionCareer" name="searchAidNeedPopulationVo.career.id">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">文化程度： </label>
			</div>
			<div class="grid_7">
				<select  class="form-txt" id="conditionSchooling" name="searchAidNeedPopulationVo.schooling.id"
			    <c:if test='${mode=="view"}'>disabled='true'</c:if>>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"/>
				</select>
			</div>
			<div class='clearLine'></div>
				 	
 			<div class="grid_5 lable-right">
		    	<label class="form-lbl">婚姻状况： </label>
		    </div>
		    <div class="grid_7">
				<select class="form-txt" id="conditionMaritalState" name="searchAidNeedPopulationVo.maritalState.id">
				    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS"  />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lb1">工作单位或就读学校：</label>
			</div>
			<div class="grid_7">
		 		<input type="text" id="conditionWorkUnit" maxlength="50"  class="form-txt" name="searchAidNeedPopulationVo.workUnit"/>
			</div>
	   		<div class='clearLine'></div>			
	   	 	<div class="grid_5 lable-right">
	            <label class="form-lbl">宗教信仰：</label>
	        </div>
	        <div class="grid_7">
	        	<select  class="form-txt" id="conditionFaith" name="searchAidNeedPopulationVo.faith.id">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH"  />
				</select>
	        </div>
   			<div class="grid_5 lable-right">
			<label class="form-lbl">血型：</label>
		    </div>
			<div class="grid_7">
				<select  class="form-txt" id="conditionBloodType" name="searchAidNeedPopulationVo.bloodType.id">
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" />
				</select>
			</div>
		
			<div class="grid_5 lable-right">
			   <label class="form-lbl">身高(CM)：</label>
		    </div>
		    <div class="grid_1 lable-left">
			   <label class="form-lbl">从</label>
		    </div>
			<div class="grid_3" id="statureStartDiv">
		    	<input type="text" id="statureStart"  class="form-txt" name="searchAidNeedPopulationVo.statureStart"/>
			</div>
			<div class="grid_1 lable-right">
				至
			</div>
			<div class="grid_3" id="statureEndDiv">
		    	<input type="text" id="statureEnd"  class="form-txt" name="searchAidNeedPopulationVo.statureEnd"/>
			</div>
	        <div class="grid_4 lable-right">
	            <label class="form-lbl">电子邮箱：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text"  id="conditionEmail" class="dialogtip form-txt" maxlength="50" name="searchAidNeedPopulationVo.email"/>
	        </div>	
	                <div class='clearLine'></div>	
	                <div class="grid_5 lable-right">
	            <label class="form-lbl">固定电话：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text"  id="conditionTelephone" name="searchAidNeedPopulationVo.telephone" class="dialogtip form-txt"  title="请输入由数字和-组成的联系电话 例如：0577-88888888"
	            maxlength="20"/>
	        </div>
			<div class="grid_5 lable-right">
	            <label class="form-lbl">联系手机：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text"  name="searchAidNeedPopulationVo.mobileNumber" id="conditionMobileNumber" class="dialogtip form-txt" title="请输入11位以1开头的联系手机 例如：13988888888"
	             maxlength="11"/>
	        </div>
			<div class='clearLine'></div>
			<div id="showOrHidden2">
			<div class="grid_5 lable-right">
				<label class="form-lbl">领取金额：从</label>
			</div>
			
			<div class="grid_3" id="subsidiesAmountDiv">
		    	<input type="text" id="subsidiesAmountStart"  class="form-txt" name="searchAidNeedPopulationVo.subsidiesAmountStart"/>
			</div>
			<div class="grid_1 lable-right">
				至
			</div>
			<div class="grid_3" id="childNumberDiv">
		    	<input type="text" id="subsidiesAmountEnd"  class="form-txt" name="searchAidNeedPopulationVo.subsidiesAmountEnd"/>
			</div>
				<div class="grid_5 lable-right">
				<label class="form-lbl">领取时间：从</label>
			</div>
			<div class="grid_3" id="birthdayDiv">
		    	<input type="text" id="subsidiesGetTimeStart" readonly class="form-txt" name="searchAidNeedPopulationVo.subsidiesGetTimeStart" />
			</div>
			<div class="grid_1 lable-right">
				至
			</div>
			<div class="grid_3" id="birthdayDiv">
		    	<input type="text" id="subsidiesGetTimeEnd" readonly class="form-txt" name="searchAidNeedPopulationVo.subsidiesGetTimeEnd" />
			</div>
				<div class="grid_5 lable-right">
				<label class="form-lbl">享受起始时间：从</label>
			</div>
			<div class="grid_2" id="subsidiesStartTime">
		    	<input type="text" id="subsidiesStartTimeStart" readonly class="form-txt" name="searchAidNeedPopulationVo.subsidiesStartTimeStart"/>
			</div>
			<div class="grid_1 lable-right">
				至
			</div>
			<div class="grid_3" id="birthdayDiv">
		    	<input type="text" id="subsidiesStartTimeEnd" readonly class="form-txt" name="searchAidNeedPopulationVo.subsidiesStartTimeEnd"/>
			</div>
				<div class="grid_5 lable-right">
				<label class="form-lbl">享受人数：从</label>
			</div>
			
			<div class="grid_3" id="childNumberDiv">
		    	<input type="text" id="subsidiesPopulationStart"  class="form-txt" name="searchAidNeedPopulationVo.subsidiesPopulationStart"/>
			</div>
			<div class="grid_1 lable-right">
				至
			</div>
			<div class="grid_3" id="childNumberDiv">
		    	<input type="text" id="subsidiesPopulationEnd"  class="form-txt" name="searchAidNeedPopulationVo.subsidiesPopulationEnd"/>
			</div>
	   </div>		
	</div>
</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#conditionBirthdayStart,#conditionBirthdayEnd").datepickers(); 
	$("#subsidiesGetTimeStart,#subsidiesGetTimeEnd").datepickers(); 
	$("#subsidiesStartTimeStart,#subsidiesStartTimeEnd").datepickers();
	
	if(null==$("#isLowHouseholds").attr("checked")){
		$("#showOrHidden1").hide();
		$("#showOrHidden2").hide();
	};
	
	$("#isLowHouseholds").change(function(){
		if ($(this).attr("checked") == null){
			$("#showOrHidden1").hide();
			$("#showOrHidden2").hide();
			
			$("#difficultCardNo").val("");
			$("#difficultType").val("");
			$("#subsidiesAmount").val("");
			$("#subsidiesGetTime").val("");
			$("#subsidiesStartTime").val("");
			$("#subsidiesPopulation").val("");
			$("#subsidiesArea").val("");
		}else{
			$("#showOrHidden1").show();
			$("#showOrHidden2").show();
			
			}
	});

	$("#showMoreBtn").toggle(
		function(){
			$("#aidNeedPopulationDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#aidNeedPopulationDialog").dialog( "option" , {height:365} );
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

