<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<form id="searchAidspopulationsForm">
	<@pop.token />
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">关注状态：</label>
		</div>
		<div class="grid_7">
             <select id="isLock" name="searchAidspopulationsVo.isEmphasis" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">现在关注</option>
                 <option value="1">曾经关注</option>
             </select>
        </div>
        <div class="grid_5 lable-right">
			<label class="form-lbl">死亡状态：</label>
		</div>
		<div class="grid_7">
             <select id="isDeath" name="searchAidspopulationsVo.isDeath" class="form-txt">
                 <option value="-1" selected="selected">全部</option>
                 <option value="0">正常</option>
                 <option value="1">已死亡</option>
             </select>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">身份证号码：</label>
		</div>
		<div class="grid_7">
	    	<input type="text" name="searchAidspopulationsVo.idCardNo" id="conditionIdCardNo" class="form-txt" maxlength="18"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionName" name="searchAidspopulationsVo.name" class="form-txt" />
		</div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">感染途径：</label>
        </div>
        <div class="grid_7">
            <select id="conditionInfectway" name="searchAidspopulationsVo.infectway" class="form-txt">
                <@pop.OptionTag name="@com.tianque.constant.PropertyTypes@INFECT_WAY" />
            </select>
        </div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">联系手机：</label>
        </div>
        <div class="grid_7">
            <input type="text" id="conditionMobileNumber" name="searchAidspopulationsVo.mobileNumber" class="form-txt" maxlength="11" />
        </div>
        
        <@s.include value="${path}/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<@s.param name="memberLabelName">服务成员：</@s.param>
			<@s.param name="memberSelectName">searchAidspopulationsVo.hasServiceTeamMember</@s.param>
			<@s.param name="recordLabelName">服务记录：</@s.param>
			<@s.param name="recordSelectName">searchAidspopulationsVo.hasServiceTeamRecord</@s.param>
		</@s.include>
        
        <div class='clearLine'></div>
    	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
			<div class="grid_5 lable-right">
	            <label class="form-lbl">违法情况： </label>
	        </div>
	        <div class="grid_7">
	            <select id="conditionViolationsofthelaw" name="searchAidspopulationsVo.violationsofthelaw" class="form-txt">
	                <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@VIOLATIONSOFTHELAW" />
	            </select>
	        </div>
	        
			<div class="grid_5 lable-right">
				<label class="form-lbl">犯罪类型：</label>
			</div>
			<div class="grid_7">
	            <select id="conditionCrimetype" name="searchAidspopulationsVo.crimetype" class="form-txt">
	                <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@CRIMETYPE" />
	            </select>
	        </div>
	
			<div class="grid_5 lable-right">
	            <label class="form-lbl">收治机构层级：</label>
	        </div>
	        <div class="grid_7">
	            <select id="conditionReceivedlevel" name="searchAidspopulationsVo.receivedlevel" class="form-txt">
	                <@pop.OptionTag name="@com.tianque.constant.PropertyTypes@RECEIVED_LEVEL" />
	            </select>
	        </div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">收治机构：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="conditionReceivedorganization" name="searchAidspopulationsVo.receivedorganization" class="form-txt" />
			</div>
	       	<div class="grid_5 lable-right">
				<label class="form-lbl">性别：</label>
			</div>
			<div class="grid_7">
				<select id="conditionGender" name="searchAidspopulationsVo.gender" class="form-txt">
					<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
				</select>
			</div>
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">职业：</label>
			</div>
			<div class="grid_7">
			 	<select id="conditionCareer" name="searchAidspopulationsVo.career" class="form-txt">
	               <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER"  />
	            </select>
			</div>					
	
	 		<div class="grid_5 lable-right">
	            <label class="form-lbl">出生日期 ：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text" id="conditionBirthday" name="searchAidspopulationsVo.birthday" readonly="readonly" class="form-txt" />
	        </div>
	        <div class="grid_5 lable-right" >
	            <label class="form-lbl">至：</label>
	        </div>
	        <div class="grid_7">
	            <input type="text" id="endConditionBirthday" name="searchAidspopulationsVo.endBirthday" readonly="readonly" class="form-txt" />
	        </div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">户籍地： </label>
			</div>
			<div class="grid_5">
				<select id="conditionProvince" name="searchAidspopulationsVo.province" class="form-txt"></select>
			</div>
			<div class="grid_1 lable-right">
				<label class="form-lbl">省</label>
			</div>
			<div class="grid_5">
				<select id="conditionCity" name="searchAidspopulationsVo.city" class="form-txt"></select>
			</div>
			<div class="grid_1 lable-right">
				<label class="form-lbl">市</label>
			</div>
			<div class="grid_5">
				<select id="conditionDistrict" name="searchAidspopulationsVo.district" class="form-txt"></select>
			</div>
			<div class="grid_1 lable-right">
				<label class="form-lbl">县</label>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">户籍地详址：</label>
			</div>
			<div class="grid_19">
				<input type="text" id="conditionNativePlaceAddress" name="searchAidspopulationsVo.nativePlaceAddress" class="form-txt" />
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">常住地址：</label>
			</div>
			<div class="grid_19">
				<input type="text" id="conditionCurrentAddress" name="searchAidspopulationsVo.currentAddress" class="form-txt" />
			</div>
			 <div class="grid_5 lable-right">
				<label class="form-lbl">工作单位或就读学校：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="conditionWorkUnit" name="searchAidspopulationsVo.workUnit" class="form-txt" />
			</div>
			<div class="grid_5 lable-right">
			    <label class="form-lbl">文化程度：</label>
			</div>
			<div class="grid_7">
			    <select name="searchAidspopulationsVo.schooling.id" class="form-txt">
			        <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"/>
			    </select>
			</div>
			 <div class="clearLine">&nbsp;</div>
		</div>
	</div>		
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#showMoreBtn").toggle(
		function(){
			$("#aidspopulationsDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#aidspopulationsDialog").dialog( "option" , {height:330} );
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
});
</script>
