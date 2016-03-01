<%@ page language="java" import="java.util.Map,java.util.HashMap" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%-- --------------------重点人员----------------------- --%>

<%@page import="com.tianque.service.util.PopulationType"%>
<div class="grid_4" style="margin-left: 30px"   id="KEY_POPULATIONDiv" >
	<label class="form-lb1" style="font-weight: bold"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@KEY_POPULATION)"/>：</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px">
<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@KEY_POPULATION)" var="keyPopulation" >
	<s:set value="#keyPopulation.substring(0,1).toUpperCase()+#keyPopulation.substring(1)" var="keyPopulations"></s:set>
	<pop:JugePermissionTag ename="${keyPopulation}Management">
	<pop:JugePermissionTag ename="add${keyPopulations}">
		<div class="grid_6">
			<input type="checkbox" name="populationType" class="typeInfoContext" fateson="KEYPOPULATION"
			contextDiv="<s:property value="@com.tianque.service.util.PopulationType@KEY_POPULATION"/>Context"
			value="<s:property value="#keyPopulation"/>"
			id="<s:property value="#keyPopulation"/>"
			<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#keyPopulation)">checked</s:if>
			/>
			<span><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(#keyPopulation)"/></span>
		</div>
	</pop:JugePermissionTag>
	</pop:JugePermissionTag>
</s:iterator>
</div>
<div class='clearLine'>&nbsp;</div>
<div id='<s:property value="@com.tianque.service.util.PopulationType@KEY_POPULATION"/>Context'>
	<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@KEY_POPULATION)" var="keyPopulation">
		<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#keyPopulation)">
			<div id="<s:property value="#keyPopulation"/>Div" class="gridList">
				<jsp:include page="/baseinfo/commonPopulation/populationInfo/${keyPopulation}.jsp"/>
			</div>
		</s:if>
	</s:iterator>
</div>
<div class='clearLine'>&nbsp;</div>
<hr width="90%" id="hr1" ></hr>
<%-- --------------------关怀对象----------------------- --%>
<div class="grid_4 " style="margin-left: 30px"  id="OPTIMAL_OBJECTDiv" >
	<label class="form-lb1" style="font-weight: bold"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@KEY_OPTIMAL_OBJECT)"/>：</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px">
<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@OPTIMAL_OBJECT)" var="civicService">
	<s:set value="#civicService.substring(0,1).toUpperCase()+#civicService.substring(1)" var="civicServices"></s:set>
	<pop:JugePermissionTag ename="${civicService}Management">
	<pop:JugePermissionTag ename="add${civicServices}">
		<div class="grid_6">
			<input type="checkbox" name="populationType"
			class="typeInfoContext"   fateson="OPTIMALOBJECT"
			contextDiv="<s:property value="@com.tianque.service.util.PopulationType@OPTIMAL_OBJECT"/>Context"
			value="<s:property value="#civicService"/>"
			id="<s:property value="#civicService"/>"
			<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#civicService)">checked</s:if>
			/>
			<span><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(#civicService)"/></span>
		</div>
	</pop:JugePermissionTag>
	</pop:JugePermissionTag>
	
</s:iterator>
</div>
<div class='clearLine'>&nbsp;</div>
<div id='<s:property value="@com.tianque.service.util.PopulationType@OPTIMAL_OBJECT"/>Context'>
	<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@OPTIMAL_OBJECT)" var="civicService">
		<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#civicService)">
			<div id="<s:property value="#civicService"/>Div" class="gridList">
				<jsp:include page="/baseinfo/commonPopulation/populationInfo/${civicService}.jsp"/>
			</div>
		</s:if>
	</s:iterator>
</div>
<div class='clearLine'>&nbsp;</div>
<hr width="90%"  id="hr2"  ></hr>

<%-- -------------------失业人员------------------------ --%>
<div class="grid_4 " style="margin-left: 30px" id="UNEMPLOYED_PEOPLEDiv">
	<label class="form-lb1" style="font-weight: bold"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@UNEMPLOYED_PEOPLE)"/>：</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px">
<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@UNEMPLOYED_PEOPLE)" var="familyPlanning">
	<s:set value="#familyPlanning.substring(0,1).toUpperCase()+#familyPlanning.substring(1)" var="familyPlannings"></s:set>
	<pop:JugePermissionTag ename="${familyPlanning}Management">
	<pop:JugePermissionTag ename="add${familyPlannings}">
		<div class="grid_6">
			<input type="checkbox" name="populationType"
			class="typeInfoContext"  fateson="UNEMPLOYEDPEOPLE"
			contextDiv="<s:property value="@com.tianque.service.util.PopulationType@UNEMPLOYED_PEOPLE"/>Context"
			value="<s:property value="#familyPlanning"/>"
			id="<s:property value="#familyPlanning"/>"
			<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#familyPlanning)">checked</s:if>
			/>
			<span><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(#familyPlanning)"/></span>
		</div>
		</pop:JugePermissionTag>
	</pop:JugePermissionTag>
</s:iterator>
</div>
<div class='clearLine'>&nbsp;</div>
<div id='<s:property value="@com.tianque.service.util.PopulationType@UNEMPLOYED_PEOPLE"/>Context'>
	<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@UNEMPLOYED_PEOPLE)" var="familyPlanning">
		<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#familyPlanning)">
			<div id="<s:property value="#familyPlanning"/>Div" class="gridList">
				<jsp:include page="/baseinfo/commonPopulation/populationInfo/${familyPlanning}.jsp"/>
			</div>
		</s:if>
	</s:iterator>
</div>


	<div class='clearLine'>&nbsp;</div>
	<hr width="90%" id="hr3"></hr>
	<%-- -------------------育龄妇女------------------------ --%>
	<div class="grid_4 " style="margin-left: 30px" id="NURTURES_WOMENDiv">
		<label class="form-lb1" style="font-weight: bold"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@NURTURES_WOMEN)"/>：</label>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div style="margin-left: 30px">
	<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@NURTURES_WOMEN)" var="familyPlanning">
		<s:set value="#familyPlanning.substring(0,1).toUpperCase()+#familyPlanning.substring(1)" var="familyPlannings"></s:set>
		<pop:JugePermissionTag ename="nurturesWomen">
		<pop:JugePermissionTag ename="add${familyPlannings}">
			<div class="grid_6">
				<input type="checkbox" name="populationType"
				class="typeInfoContext"  fateson="NURTURESWOMEN"
				contextDiv="<s:property value="@com.tianque.service.util.PopulationType@NURTURES_WOMEN"/>Context"
				value="<s:property value="#familyPlanning"/>"
				id="<s:property value="#familyPlanning"/>"
				<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#familyPlanning)">checked</s:if>
				/>
				<span><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(#familyPlanning)"/></span>
			</div>
		</pop:JugePermissionTag>
		</pop:JugePermissionTag>
	</s:iterator>
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div id='<s:property value="@com.tianque.service.util.PopulationType@NURTURES_WOMEN"/>Context'>
		<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@NURTURES_WOMEN)" var="familyPlanning">
			<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#familyPlanning)">
				<div id="<s:property value="#familyPlanning"/>Div" class="gridList">
					<jsp:include page="/baseinfo/commonPopulation/populationInfo/${familyPlanning}.jsp"/>
				</div>
			</s:if>
		</s:iterator>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<hr width="90%" id="hr4"></hr>
	<%-- -------------------FXJ------------------------ --%>
	<div class="grid_4 " style="margin-left: 30px" id="FXJ_POPULATIONiv">
		<label class="form-lb1" style="font-weight: bold"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@FXJ_POPULATION)"/>：</label>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div style="margin-left: 30px">
	<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@FXJ_POPULATION)" var="familyPlanning">
		<s:set value="#familyPlanning.substring(0,1).toUpperCase()+#familyPlanning.substring(1)" var="familyPlannings"></s:set>
		<pop:JugePermissionTag ename="FXJPeopleInfoManagement">
		<pop:JugePermissionTag ename="add${familyPlannings}">
			<div class="grid_6">
				<input type="checkbox" name="populationType"
				class="typeInfoContext"  fateson="FXJPOPULATION"
				contextDiv="<s:property value="@com.tianque.service.util.PopulationType@FXJ_POPULATION"/>Context"
				value="<s:property value="#familyPlanning"/>"
				id="<s:property value="#familyPlanning"/>"
				<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#familyPlanning)">checked</s:if>
				/>
				<span><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(#familyPlanning)"/></span>
			</div>
		</pop:JugePermissionTag>
		</pop:JugePermissionTag>
	</s:iterator>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div id='<s:property value="@com.tianque.service.util.PopulationType@FXJ_POPULATION"/>Context'>
		<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@FXJ_POPULATION)" var="familyPlanning">
			<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#familyPlanning)">
				<div id="<s:property value="#familyPlanning"/>Div" class="gridList">
					<jsp:include page="/baseinfo/commonPopulation/populationInfo/${familyPlanning}.jsp"/>
				</div>
			</s:if>
		</s:iterator>
	</div>

<div class='clearLine'>&nbsp;</div>
<hr width="90%" id="hr5"></hr>

<%-- -------------------见义勇为------------------------ --%>
<pop:JugePermissionTag ename="goodSamaritanManagement">
<div class="grid_4 " style="margin-left: 30px" id="GOOD_SAMARITANDiv">
	<label class="form-lb1" style="font-weight: bold"><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(@com.tianque.service.util.PopulationType@GOOD_SAMARITAN)"/>：</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px">
<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@GOOD_SAMARITAN)" var="familyPlanning">
	<s:set value="#familyPlanning.substring(0,1).toUpperCase()+#familyPlanning.substring(1)" var="familyPlannings"></s:set>
	<pop:JugePermissionTag ename="${familyPlanning}Management">
	<pop:JugePermissionTag ename="add${familyPlannings}">
		<div class="grid_6">
			<input type="checkbox" name="populationType"
			class="typeInfoContext"  fateson="GOODSAMARITAN"
			contextDiv="<s:property value="@com.tianque.service.util.PopulationType@GOOD_SAMARITAN"/>Context"
			value="<s:property value="#familyPlanning"/>"
			id="<s:property value="#familyPlanning"/>"
			<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#familyPlanning)">checked</s:if>
			/>
			<span><s:property value="@com.tianque.service.util.PopulationType@getCnameByPopulationType(#familyPlanning)"/></span>
		</div>
		</pop:JugePermissionTag>
	</pop:JugePermissionTag>
</s:iterator>
</div>
<div class='clearLine'>&nbsp;</div>
<div id='<s:property value="@com.tianque.service.util.PopulationType@GOOD_SAMARITAN"/>Context' >
	<s:iterator value="@com.tianque.service.util.PopulationType@get(@com.tianque.service.util.PopulationType@GOOD_SAMARITAN)" var="familyPlanning">
		<s:if test="@com.tianque.util.Arrays@hasObjectInArray(populationType,#familyPlanning)">
			<div id="<s:property value="#familyPlanning"/>Div" class="gridList" style="height:500px">
				<jsp:include page="/baseinfo/commonPopulation/populationInfo/${familyPlanning}.jsp"/>
			</div>
		</s:if>
	</s:iterator>
</div>


	<div class='clearLine'>&nbsp;</div>
	<hr width="90%" id="hr3"></hr>
</pop:JugePermissionTag>	
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px;" id="noticeYouth"></div>
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px" id="noticeNurturesWomen"></div>
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px" id="noticeElderlyPeople"></div>
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px" id="noticeNurturesWomen"></div>
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px" id="noticeFXJPopulation"></div>
<div class='clearLine'>&nbsp;</div>
<div style="margin-left: 30px" id="noticePositiveOrrectificative"></div>
<script type="text/javascript">
	
	
	$(function(){
		var idCardNo = $("#_idCardNo_").val();
		if($("#gender").find("option:selected").text()=="男"){
			$("#populationTypeForm").find("#nurturesWomen").removeAttr("checked");
			$("#noticeNurturesWomen").css("color","red");
			$("#noticeNurturesWomen").text("提示：男性不能关联为育龄妇女！");
			$("#populationTypeForm").find("#nurturesWomen").attr("disabled","disabled");
		}
		$("#positiveInfo").click(function() {
			isPositiveInfo();
        });
		$("#rectificativePerson").click(function() {  
			isRectificativePerson();
        });
		isElderly(idCardNo);
		isYouth(idCardNo);
		isNurturesWomen(idCardNo);
		$(".typeInfoContext").click(
			function(){
				var context=$("#"+$(this).attr("contextDiv")).addClass("keyTypeContent");
				var value=$(this).val();
				var self=$('#'+value+'Div');
				if($(this).attr("checked")){
					if(self.attr("id")){
						self.show();
						$("input,select",self).removeClass("typeInfoHidden");
					}else{
						$.ajax({
							url:"/baseinfo/populationSpecializedInfo/getAllPopulationSpecializedInfoByOrgIdAndIdCardNo.action?orgId=${orgId}&idCardNo=${idCardNo}&populationSpecializedType="+value,
							success:function(data){
								var valueObj=$('<div id="'+value+'Div" class="gridList" '+ ("handicapped" == value ? 'style="overflow: visible;"' : '') +'></div>');
								context.append(valueObj);
								valueObj.append(data);
							}
						});
					}
				}else{
					self.hide();
					$("input,select",self).addClass("typeInfoHidden");
				}
			}
		)
		
		//如果某种类型没有 新增的权限，那么 那个人口类型标题不要显示出来 
		//判断重点人员有没有  
		if($("input[fateson='KEYPOPULATION']").length==0){
			$("#KEY_POPULATIONDiv").hide();
			$("#hr1").attr("hidden","yes");
		}
		if( $("input[fateson='OPTIMALOBJECT']").length==0){
			$("#OPTIMAL_OBJECTDiv").hide();
			$("#hr2").attr("hidden","yes");
		}
		
		if($("input[fateson='UNEMPLOYEDPEOPLE']").length==0){
			$("#UNEMPLOYED_PEOPLEDiv").hide();
			$("#hr3").attr("hidden","yes");
		}
		 
		if($("input[fateson='NURTURESWOMEN']").length==0){
			$("#NURTURES_WOMENDiv").hide();
			$("#hr4").attr("hidden","yes");
		}

		if($("input[fateson='FXJPOPULATION']").length==0){
			$("#FXJ_POPULATIONiv").hide();
			$("#hr5").attr("hidden","yes");
		}
					
		
	});
	function elderlyInfo (){
		$("#populationTypeForm").find("#elderlyPeople").removeAttr("checked");
		$("#noticeElderlyPeople").css("color","red");
		$("#noticeElderlyPeople").text("提示：年龄不在老年人60岁以上的的范围内，不能关联为老年人！");
		$("#populationTypeForm").find("#elderlyPeople").attr("disabled","disabled");
	}
	function youthInfo(){
		$("#populationTypeForm").find("#idleYouth").removeAttr("checked");
		$("#noticeYouth").css("color","red");
		$("#noticeYouth").text("提示：年龄不在青少年年龄0~35岁的范围内，不能关联为青少年！");
		$("#populationTypeForm").find("#idleYouth").attr("disabled","disabled");
	}
	function nurturesWomen (){
		$("#populationTypeForm").find("#nurturesWomen").removeAttr("checked");
		$("#noticeNurturesWomen").css("color","red");
		$("#noticeNurturesWomen").text("提示：年龄不在15-49之间的，不能关联为育龄妇女！");
		$("#populationTypeForm").find("#nurturesWomen").attr("disabled","disabled");
	}
	
	function isPositiveInfo(){
		if($("#positiveInfo:checked").is(":checked")){
			$("#populationTypeForm").find("#rectificativePerson").removeAttr("checked");
			$("#noticePositiveOrrectificative").css("color","red");
			$("#noticePositiveOrrectificative").text("提示：已关联刑释人员不允许再关联社区矫正人员");
			$("#populationTypeForm").find("#rectificativePerson").attr("disabled","disabled");
		}else{
			$("#populationTypeForm").find("#rectificativePerson").attr("checked");
			$("#noticePositiveOrrectificative").css("color","red");
			$("#noticePositiveOrrectificative").text("");
			$("#populationTypeForm").find("#rectificativePerson").removeAttr("disabled","disabled");
		}
	}
	
	function isRectificativePerson(){
		if($("#rectificativePerson:checked").is(":checked")){
			$("#populationTypeForm").find("#positiveInfo").removeAttr("checked");
			$("#noticePositiveOrrectificative").css("color","red");
			$("#noticePositiveOrrectificative").text("提示：已关联社区矫正人员不允许再关联刑释人员");
			$("#populationTypeForm").find("#positiveInfo").attr("disabled","disabled");
		}else{
			$("#populationTypeForm").find("#positiveInfo").attr("checked");
			$("#noticePositiveOrrectificative").css("color","red");
			$("#noticePositiveOrrectificative").text("");
			$("#populationTypeForm").find("#positiveInfo").removeAttr("disabled","disabled");
		}
	}
	
	function isYouth(value){
		var d = new Date();
		if(value!=null&&value.length==18){
			value=value.substring(6,14);
			var year = value.substring(0,4);
			var month = value.substring(4,6);
			var day = value.substring(6,8);
		}else if(value!=null&&value.length==15){
			value=value.substring(6,12);
			var temp = value.substring(0,2);
			var year = parseInt(temp)+1900;
			var month = value.substring(2,4);
			var day = value.substring(4,6);
		}
		var dat1 = d.getFullYear()-35;//当前年份
		var dat2 = d.getFullYear();
		var nowMon = d.getMonth();
		var nowDay = d.getDate();
		if (dat1>year || dat2<year){
			youthInfo();
			return;
		}
		if(dat1==year){
			if((nowMon+1) > month){
				youthInfo();
				return;
			}
			if((nowMon+1) == month){
				if(nowDay>day){
					youthInfo();
					return;
				}
				if(nowDay==day){
					youthInfo();
					return;
				}
			}
		}
		if(dat2 == year){
			if(eval(nowMon+1) < month){
				youthInfo();
				return;
			}
			if((nowMon+1) == month){
				if(nowDay<day){
					youthInfo();
					return;
				}
				if(nowDay==day){
					youthInfo();
					return;
				}
			}
		}
	}
	function isElderly(value){
		var d = new Date();
		if(value!=null&&value.length==18){
			value=value.substring(6,14);
			var year = value.substring(0,4);
			var month = value.substring(4,6);
			var day = value.substring(6,8);
		}else if(value!=null&&value.length==15){
			value=value.substring(6,12);
			var temp = value.substring(0,2);
			var year = parseInt(temp)+1900;
			var month = value.substring(2,4);
			var day = value.substring(4,6);
		}
		var elementDate = year;
		var dat1 = d.getFullYear()-60;
		var nowMon = d.getMonth();
		var nowDay = d.getDate();
		if (dat1<year) {
			elderlyInfo();
			return;
		}
		 if(dat1 == year){
			if((nowMon+1) < month){
				elderlyInfo ();
				return;
			}
			if((nowMon+1) == month){
				if(nowDay<day){
					elderlyInfo ();
					return;
				}
				if(nowDay==day){
					elderlyInfo ();
					return;
				}
			}
		}
		 $("#populationTypeForm").find("#elderlyPeople").attr('checked',true);
	}
	
	function isNurturesWomen(value){
		var d = new Date();
		var nowYear = d.getFullYear();//得到系统时间的年份、月份、日期
		var nowMonth = d.getMonth();
		var nowDay = d.getDate();
		
		if(value!=null&&value.length==18){
			value=value.substring(6,14);
			var year = value.substring(0,4);
			var month = value.substring(4,6);
			var day = value.substring(6,8);
		}else if(value!=null&&value.length==15){
			value=value.substring(6,12);
			var temp = value.substring(0,2);
			var year = parseInt(temp)+1900;
			var month = value.substring(2,4);
			var day = value.substring(4,6);
		}
		var startData = nowYear-15;
		var endData = nowYear-49;
		if (startData < year || endData > year) {
			nurturesWomen();
			return;
		}
		if(endData == year){
			if((nowMonth+1) > month){
				nurturesWomen();
				return;
			}
			if((nowMonth+1) == month){
				if(nowDay > day){
					nurturesWomen();
					return;
				}
				if(nowDay == day){
					nurturesWomen();
					return;
				}
			}
		}
		if(startData == year){
			if((nowMonth+1) < month){
				nurturesWomen();
				return;
			}
			if((nowMonth+1) == month){
				if(nowDay < day){
					nurturesWomen();
					return;
				}
				if(nowDay==day){
					nurturesWomen();
					return;
				}
			}
		}
		if($("#gender").find("option:selected").text()=="女"){
			$("#populationTypeForm").find("#nurturesWomen").attr('checked',true);
		}
	}
</script>