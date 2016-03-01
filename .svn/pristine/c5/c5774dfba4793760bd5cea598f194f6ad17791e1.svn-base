<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
	String keyType = request.getParameter("keyType");
	if(!"".equals(keyType)){
		request.setAttribute("keyType", keyType);
	}
%>
<form id="searchYouthsForm">
	<pop:token />
	<input type="hidden" name="searchYouthsVo.keyType" id="searchKeyType" value="${keyType}">
	<div id="dialog-form" title="青少年" class="container container_24">
	<div class="grid_5 lable-right">
		<label class="form-lbl">死亡状态：</label>
	</div>
	<div class="grid_7">
	 	 <select id="isDeathUser" name="searchYouthsVo.isDeathUser" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">正常</option>
                 <option value="1">已死亡</option>
             </select>
      </div>
    <div class="grid_5 lable-right" >
		<label class="form-lbl" style="display: none;">关注状态：</label>
	</div>
	<div class="grid_7" >
            <select id="isLock" name="searchYouthsVo.isEmphasis" class="form-txt" style="display: none;">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">现在关注</option>
                 <option value="1">曾经关注</option>
             </select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchYouthsVo.name" id="youths.name" maxlength="20"   class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchYouthsVo.idCardNo" id="youths.idCardNo" maxlength="18"   class="form-txt" />
	</div>
	 <div class="grid_5 lable-right">
			<label class="form-lbl">性别：</label>
		</div>
		<div class="grid_7">
			<select name="searchYouthsVo.gender.id" id="searchYouthsVo.gender.id" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"  />
			</select>
		</div>
	
	<div class="grid_5 lable-right">
	    <label class="form-lbl">曾用名/别名：</label>
	</div>
	<div class="grid_7">
	    <input id="youths.attentionReason" type="text" name="searchYouthsVo.usedName" class="form-txt"  maxlength="50"/>
	</div>
	<div class="grid_5 lable-right">
	    <label class="form-lbl">年龄：</label>
	</div>
	<div class="grid_2">
	    <input id="youths.beginAge" type="text" name="searchYouthsVo.beginAge"  class="form-txt"  maxlength="50"/>
	</div>
	<div class="grid_2">
		&nbsp;&nbsp;周岁到
	</div>
	<div class="grid_2">
	    <input id="youths.endAge" type="text" name="searchYouthsVo.endAge"  class="form-txt"  maxlength="50"/>
	</div>
	<div class="grid_2">
	     &nbsp;&nbsp;周岁
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
		<div class="grid_5 lable-right">
	    <label class="form-lbl">固定电话：</label>
	  	 </div>
		<div class="grid_7">
		    <input id="youths.telephone" type="text" name="searchYouthsVo.telephone" class="form-txt"  maxlength="11"/>
		</div>
		
		<div class="grid_5 lable-right">
	    <label class="form-lbl">联系手机：</label>
	   </div>
		<div class="grid_7">
		    <input id="youths.mobileNumber" type="text" name="searchYouthsVo.mobileNumber" class="form-txt"  maxlength="11"/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
		    <label class="form-lbl">民族：</label>
		</div>
		<div class="grid_7">
		    <select name="searchYouthsVo.nation.id" id="youths.nation.id" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="" />
		    </select>
		</div>
		<div class="grid_5 lable-right">
		    <label class="form-lbl">职业：</label>
		</div>
		<div class="grid_7">
		    <select name="searchYouthsVo.career.id" id="youths.career.id" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="" />
		    </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
		    <label class="form-lbl">婚姻状况：</label>
		</div>
		<div class="grid_7">
		    <select name="searchYouthsVo.maritalState.id" id="youths.maritalState.id" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS" defaultValue="" />
		    </select>
		</div>
		<div class="grid_5 lable-right">
		    <label class="form-lbl">户口类别：</label>
		</div>
		<div class="grid_7">
		    <select name="searchYouthsVo.residenceType.id" id="youths.residenceType.id" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" defaultValue="" />
		    </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
		    <label class="form-lbl">宗教信仰：</label>
		</div>
		<div class="grid_7">
		    <select name="searchYouthsVo.faith.id" id="youths.faith.id" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FAITH" defaultValue="" />
		    </select>
		</div>
		<div class="grid_5 lable-right">
		    <label class="form-lbl">血型：</label>
		</div>
		<div class="grid_7">
		    <select name="searchYouthsVo.bloodType.id" id="youths.bloodType.id" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BLOOD_TYPE" defaultValue="" />
		    </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
		    <label class="form-lbl">文化程度：</label>
		</div>
		<div class="grid_7">
		    <select name="searchYouthsVo.schooling.id" id="youths.schooling.id" class="form-txt">
		        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="" />
		    </select>
		</div>
		<div class="grid_5 lable-right"><label class="form-lbl">电子邮箱：</label> </div>
		<div class="grid_7">
		    <input id="youths.email" type="text" name="searchYouthsVo.email" class="form-txt"  maxlength="11"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
		<label class="form-lbl">户籍地：</label></div>
		<div class="grid_5">
			<select name="searchYouthsVo.province" id="province" class="form-txt"></select>
		</div>
		<div class="grid_1">
			省
		</div>
		
		<div class="grid_5">
			<select name="searchYouthsVo.city" id="city" class="form-txt" ></select>
		</div>
		<div class="grid_1">
			市
		</div>
		
		<div class="grid_5">
			<select name="searchYouthsVo.district" id="district" class="form-txt" ></select>
		</div>
		<div class="grid_1">
			县
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right"><label class="form-lbl">常住地址：</label></div>
		<div class="grid_19">
			<input id="youths.currentlyAddress" style="width:99%" type="text" name="searchYouthsVo.currentlyAddress" class="form-txt" maxlength="50" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">户籍详址：</label>
		</div>
		<div class="grid_19">
			<input id="youths.nativePlaceAddress" style="width:99%" type="text" name="searchYouthsVo.nativePlaceAddress" class="form-txt"  maxlength="50" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">其他地址：</label>
		</div>
		<div class="grid_19">
			<input id="youths.otherAddress" style="width:99%" type="text" name="searchYouthsVo.otherAddress" class="form-txt"  maxlength="50" />
		</div>
	    </div>
</div>	
</form>
<script type="text/javascript">
$(document).ready(function(){
	var min_temp="";
	var max_temp="";
	var keyType=$("#searchKeyType").val();
	if("youngsters"==keyType){
		min_temp = "0";
		max_temp = "35";
	}else if("youngpioneer"==keyType){
		min_temp = "6";
		max_temp = "14";
	}else if("leaguemember"==keyType){
		min_temp = "14";
		max_temp = "28";
	}else{
		min_temp = "0";
		max_temp = "35";
	}
	$("#searchYouthsForm").formValidate({
		promptPosition: "bottomLeft",
		rules:{
			"searchYouthsVo.beginAge":{
				number:true,
				min:min_temp,
				max:max_temp
			},
			"searchYouthsVo.endAge":{
				number:true,
				min:min_temp,
				max:max_temp
			}
		},
		messages:{
			"searchYouthsVo.beginAge":{
				number:"年龄只能输入正数",
				min:"年龄最小输入"+min_temp,
				max:"年龄最大输入"+max_temp
			},
			"searchYouthsVo.endAge":{
				number:"年龄只能输入正数",
				min:"年龄最小输入"+min_temp,
				max:"年龄最大输入"+max_temp
			}
		}
	});
	
	$("#showMoreBtn").toggle(
		function(){
			$("#positiveInfoDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#positiveInfoDialog").dialog( "option" , {height:330} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	threeSelect({
		province:"province",
		city:"city",
		district:"district",
		provinceValue:$('#nativeProvinceValue').val(),
		cityValue:$('#nativeCityValue').val(),
		districtValue:$('#nativeDistrictValue').val()
	});
});

</script>


