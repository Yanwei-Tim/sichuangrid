<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="search-condition-form" title="境外人员查询" class="container container_24">
	<form id="searchOverseaPersonnelForm">
	<pop:token />
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">注销状态：</label>
	</div>
	<div class="grid_8">
    	<select id="isLock" name="searchOverseaPersonnelVo.logOut" class="form-txt">
            <option value="-1" selected="selected">全部</option>
            <option value="0">未注销</option>
            <option value="1">已注销</option>
   		</select>
   	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">英文名：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="searchOverseaPersonnelVo.englishName" id="englishName" maxlength="20" class="form-txt" />
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">中文名：</label>  
	</div>
	<div class="grid_8">
		<input type="text" name="searchOverseaPersonnelVo.chineseName" id="chineseName" maxlength="20" class="form-txt" />
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">性别：</label>
	</div>
	<div class="grid_8">
		<select id="conditionGender" name="searchOverseaPersonnelVo.gender" class="form-txt">
   			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
 		</select>
 	</div>
 	
	<div class='clearLine'></div>
	
 	
<div class='clearLine'></div>	
 	<div class="grid_4 lable-right">
			<label class="form-lbl">出生日期：</label>
	</div>
	<div class="grid_8" id="birthdayDiv">
    	<input type="text" name="searchOverseaPersonnelVo.birthdayStart" id="conditionBirthdayStart" readonly class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		&nbsp;至：
	</div>
	<div class="grid_8" id="birthdayDiv">
    	<input type="text"  name="searchOverseaPersonnelVo.birthdayEnd" id="conditionBirthdayEnd" readonly class="form-txt" />
	</div>
<div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label>证件种类：</label>
	</div>
	<div class="grid_8">
		 <select id="conditionCertificateType" name="searchOverseaPersonnelVo.certificateTypeId" class="form-txt">
		    <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CERTIFICATE_TYPE" />
		 </select>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">证件号码：</label>
	</div>
	<div class="grid_8">         
		<input type="text" name="searchOverseaPersonnelVo.certificateNo" id="conditionCertificateNo" maxlength="18" class="form-txt" />
	</div>
<div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">证件有效期：</label>
	</div>
	<div class="grid_8" id="certificateStrartDayDiv">
    	<input type="text" name="searchOverseaPersonnelVo.certificateStrartDay" id="certificateStrartDay" readonly class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		&nbsp;至：
	</div>
	<div class="grid_8" id="certificateStrartEndDiv">
    	<input type="text"  name="searchOverseaPersonnelVo.certificateEndDay" id="certificateEndDay" readonly class="form-txt" />
	</div>
   	
   	<div class="grid_4 lable-right">
		<label class="form-lbl">国籍：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="searchOverseaPersonnelVo.nationality" id="conditionNationality" maxlength="15" class="form-txt" />
	</div>
	
	<div class='clearLine'></div>
 	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">流入原因：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="searchOverseaPersonnelVo.inflowReason" id="inflowReason" maxlength="15" class="form-txt" style="width: 99%"/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">住房住址：</label>
		</div>
		<div class="grid_20">
			<input type="text"  name="searchOverseaPersonnelVo.currentAddress" id="conditionCurrentlyAddress" maxlength="50" class="form-txt" style="width: 99%"/>
		</div>
		<div class='clearLine'></div>
		 <div class="grid_4 lable-right">
			<label class="form-lbl">职业：</label>
		</div>
		<div class="grid_6">
		<select id="conditionProfession" class="form-txt" name="searchOverseaPersonnelVo.professionId">
   			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PROFESSION_TYPE" />
 		</select>
 		</div>
 		<div class="grid_6 lable-right">
			<label class="form-lbl">工作单位或就读学校：</label>
		</div>
		<div class="grid_8">
	   		<input type="text" name="searchOverseaPersonnelVo.workUnit" id="conditionWorkUnit" maxlength="50" class="form-txt" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" name="searchOverseaPersonnelVo.mobileNumber" id="conditionMobileNumber" maxlength="11" class="form-txt" />
		</div>
			<div class="grid_4 lable-right">
			<label class="form-lbl">固定电话：</label>
		</div>
		<div class="grid_8">         
	    	<input type="text" name="searchOverseaPersonnelVo.telephone" id="conditionTelephone" maxlength="11" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div> 
		<div class="grid_4 lable-right">
			<label class="form-lbl">抵达时间：</label>
		</div>
		<div class="grid_8" id="arrivalTimeDiv">
	    	<input type="text" name="searchOverseaPersonnelVo.arrivalTimeStart" id="arrivalTimeStart" readonly class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			至：
		</div>
		<div class="grid_8" id="arrivalTimeDiv1">
	    	<input type="text" name="searchOverseaPersonnelVo.arrivalTimeEnd" id="arrivalTimeEnd" readonly class="form-txt" />
		</div>
		
		<div class='clearLine'>&nbsp;</div> 
		<div class="grid_4 lable-right">
			<label class="form-lbl">离开时间：</label>
		</div>
		<div class="grid_8" id="leaveTimeDiv">
	    	<input type="text" name="searchOverseaPersonnelVo.leaveTimeStart" id="leaveTimeStart" readonly class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			至：
		</div>
		<div class="grid_8" id="leaveTimeDiv1">
	    	<input type="text"  name="searchOverseaPersonnelVo.leaveTimeEnd" id="leaveTimeEnd" readonly class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div> 
	</div>
</form>
</div>

<script>
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#overseaPersonnelMaintanceDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#overseaPersonnelMaintanceDialog").dialog( "option" , {height:330} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	
	var idCard = $("#searchByC").val();
	if(idCard=="请输入护照号码"){
		$("#conditionCertificateNo").val("");
	}else{
		$("#conditionCertificateNo").val(idCard);
	}
	
	$("#conditionBirthdayStart,#conditionBirthdayEnd").datepickers();
	$("#arrivalTimeStart,#arrivalTimeEnd").datepickers();
	$("#leaveTimeStart,#leaveTimeEnd").datepickers();
	$("#certificateStrartDay,#certificateEndDay").datepickers();
	threeSelect({province:'conditionProvince',
		city:'conditionCity',
		district:'conditionDistrict'
		});
})
</script>