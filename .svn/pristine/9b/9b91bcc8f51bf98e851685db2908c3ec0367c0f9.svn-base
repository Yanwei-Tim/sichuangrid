<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainFirstDirectory" action="javascript:void(0);">
		<input type="hidden" value="${parentId}" name="dailyDirectory.parentDailyDirectory.id" />
		<input type="hidden" value="${dailyYearId}" name="dailyDirectory.dailyYear.id" />
		<input type="hidden" value="${dailyDirectory.id}" name="dailyDirectory.id" />
		
		<div class="grid_4 lable-right">
			<label>台账目录名称：</label>
		</div>
		<div class="grid_14">
			<input type="text" value="${dailyDirectory.fullName}" name="dailyDirectory.fullName" style="width:99.2%"
				<s:if test='"view".equals(mode)'>disabled</s:if> maxlength="30" class="form-txt" />
		</div>
		<!-- 
		<div class="grid_4" style="margin:0 0 0 20px">
			<label class="form-check-text">
				<input type="checkbox" id="nameId" value="1" name="dailyDirectory.require" 
					<s:if test="dailyDirectory.getRequire() == 1">checked</s:if>
					<s:if test='"view".equals(mode)'>disabled</s:if>  />
				是否必填
			</label>
		</div>
		 -->
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right" >
			<label>附件：</label>
		</div>
		<div id="custom-queue" class="grid_14 heightAuto" style="height: 60px;overflow-y: auto;overflow-x: hidden;border:1px solid #7F9DB9;margin-top:8px;">
			<s:if test="dailyDirectory!=null && dailyDirectory.getDailyDirectoryAttachFiles()!=null && dailyDirectory.getDailyDirectoryAttachFiles().size()>0">
				<s:iterator value="dailyDirectory.dailyDirectoryAttachFiles" var="obj">
					<a href="/daily/dailyDirectoryManage/downLoadAttachFile.action?keyId=<s:property value='#obj.fileId'/>" >
						<img  src="${resource_path}/resource/images/fujian.jpg"/>${obj.fileName}
					</a>
				</s:iterator>
			</s:if>
		</div>

		<div class='clearLine'>&nbsp;</div>
		<s:if test="show">
			<div class="grid_4 lable-right">
				<label>时限：</label>
			</div>
			<div class="grid_5" >
				<select id="timeLimit" name="dailyDirectory.timeLimit" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select">
					<s:iterator value="timeLimitMap" var="obj">
						<option value='<s:property value="#obj.key"/>' <s:if test="dailyDirectory.getTimeLimit() == #obj.key">selected</s:if> ><s:property value="value"/></option>
					</s:iterator>
				</select>
			</div>
			<div id="effectiveDaysDiv">
				<div class="grid_4 lable-right">
					<label>有效天数：</label>
				</div>
				<div class="grid_4" >
					<input value="${dailyDirectory.effectiveDays}" id="effectiveDays" name="dailyDirectory.effectiveDays" 
						<s:if test='"view".equals(mode)'>disabled</s:if> maxlength="3" class="form-txt" />
				</div>
				<div class="grid_1 lable-right">
					<label>天</label>
				</div>
			</div>
			
			<s:if test="flg">
				<div id="deadlineTypeDiv">
					<div class="grid_4 lable-right">
						<label>截止时间：</label>
					</div>
					<div class='clearLine'>&nbsp;</div>
					
					<div class="grid_10" >&nbsp;</div>
					<div class="grid_5" >
						<label for="radio1" class="form-check-text" >
							<input type="radio" id="radio1" name="dailyDirectory.deadlineType" 
								value='<s:property value="@com.tianque.working.domain.TimeLimitHelper@ORDINARY_LEDGER"/>'
								<s:if test="@com.tianque.working.domain.TimeLimitHelper@ORDINARY_LEDGER == dailyDirectory.getDeadlineType()">checked</s:if>
								<s:if test='"view".equals(mode)'>disabled</s:if> />
							普通台账
						</label>
					</div>
					<div class="grid_7" >
						<input type="text" id="deadlineDate" name="dailyDirectory.deadlineDate" 
							value="<s:date name="dailyDirectory.deadlineDate" format="yyyy-MM-dd"/>" 
							<s:if test='"view".equals(mode)'>disabled</s:if> class="form-txt" readonly />
					</div>
					<div class="grid_2" style="margin:0 0 0 8px">
						<label>日前</label>
					</div>
					
					<div class='clearLine'>&nbsp;</div>
					
					<div class="grid_10" >&nbsp;</div>
					<div class="grid_5" >
						<label for="radio2" class="form-check-text" >
							<input type="radio" id="radio2" name="dailyDirectory.deadlineType"  
								value='<s:property value="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_MONTHLY"/>'
								<s:if test="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_MONTHLY == dailyDirectory.getDeadlineType()">checked</s:if>
								<s:if test='"view".equals(mode)'>disabled</s:if> />
							按月上报台账
						</label>
					</div>
					<div class="grid_5" >
						<select id="deadlineStart2" name="dailyDirectory.deadlineStart" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select" >
							<option value=""></option>
							<s:iterator value="deadlineStartList" var="obj" begin="0" end="1"  >
								<s:iterator value="obj" var="obj1" >
									<option value='<s:property value="#obj1.key"/>' <s:if test="dailyDirectory.getDeadlineStart() == #obj1.key">selected</s:if> ><s:property value="value"/></option>
								</s:iterator>
							</s:iterator>
						</select>
					</div>
					<div class="grid_2" >
						<select id="deadlineEnd2" name="dailyDirectory.deadlineEnd" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select">
							<option value=""></option>
							<s:iterator value="deadlineEndList" var="val">
								<option value='<s:property value="#val"/>' <s:if test="#val == dailyDirectory.getDeadlineEnd()">selected</s:if> ><s:property value="#val"/></option>
							</s:iterator>
						</select>
					</div>
					<div class="grid_2" style="margin:0 0 0 8px">
						<label>日前</label>
					</div>
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_10" >&nbsp;</div>
					<div class="grid_5" >
						<label for="radio3" class="form-check-text" >
							<input type="radio" id="radio3" name="dailyDirectory.deadlineType"
								value='<s:property value="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_QUARTERLY"/>'
								<s:if test="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_QUARTERLY == dailyDirectory.getDeadlineType()">checked</s:if> 
								<s:if test='"view".equals(mode)'>disabled</s:if> />
							按季度上报台账
						</label>
					</div>
					<div class="grid_5" >
						<select id="deadlineStart3" name="dailyDirectory.deadlineStart" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select" >
							<option value=""></option>
							<s:iterator value="deadlineStartList" var="obj" id="sss" begin="2" end="3"  >
								<s:iterator value="sss" var="obj1" >
									<option value='<s:property value="key"/>'><s:property value="value"/></option>
								</s:iterator>
							</s:iterator>
						</select>
					</div>
					<div class="grid_2">
						<select id="deadlineEnd3" name="dailyDirectory.deadlineEnd" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select">
							<option value=""></option>
							<s:iterator value="deadlineEndList" var="val">
								<option value='<s:property value="#val"/>' <s:if test="dailyDirectory.getDeadlineEnd() == #val">selected</s:if> ><s:property value="#val"/></option>
							</s:iterator>
						</select>
					</div>
					<div class="grid_2" style="margin:0 0 0 8px">
						<label>日前</label>
					</div>
					<div class='clearLine'>&nbsp;</div>
					<div class="grid_10" >&nbsp;</div>
					<div class="grid_5" >
						<label for="radio4" class="form-check-text" >
							<input type="radio" id="radio4" name="dailyDirectory.deadlineType"  
								value='<s:property value="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_YEAR"/>'
								<s:if test="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_YEAR == dailyDirectory.getDeadlineType()">checked</s:if>
								<s:if test='"view".equals(mode)'>disabled</s:if> />
							按半年上报台账
						</label>
					</div>
					<div class="grid_5" >
						<select id="deadlineStart4" name="dailyDirectory.deadlineStart" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select" >
							<option value=""></option>
							<s:iterator value="deadlineStartList" var="obj" id="sss" begin="4" end="5"  >
								<s:iterator value="sss" var="obj1" >
									<option value='<s:property value="key"/>'><s:property value="value"/></option>
								</s:iterator>
							</s:iterator>
						</select>
					</div>
					<div class="grid_2">
						<select id="deadlineEnd4" name="dailyDirectory.deadlineEnd" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select">
							<option value=""></option>
							<s:iterator value="deadlineEndList" var="val">
								<option value='<s:property value="#val"/>' <s:if test="dailyDirectory.getDeadlineEnd() == #val">selected</s:if> ><s:property value="#val"/></option>
							</s:iterator>
						</select>
					</div>
					<div class="grid_2" style="margin:0 0 0 8px">
						<label>日前</label>
					</div>
				</div>
			</s:if>
		</s:if>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	
	determineValueChange();
	<s:if test="flg">
		$("#deadlineTypeDiv select").attr("disabled",true);
		$("#deadlineTypeDiv #deadlineDate").attr("disabled",true);
		$(":radio").each(function(i,obj){
			if(obj.checked){
				checkedObjId(obj.id);
			}
		});
	</s:if>
});

function checkedObjId(id){
	if(id=="radio1"){
		<s:if test='!"view".equals(mode)'>
			$("#deadlineTypeDiv #deadlineDate").attr("disabled",false);
		</s:if>
	}else if(id=="radio2"){
		<s:if test='!"view".equals(mode)'>
			$("#deadlineStart2").attr("disabled",false);
			$("#deadlineEnd2").attr("disabled",false);
		</s:if>
		$("#deadlineEnd3").val('');
		$("#deadlineEnd4").val('');
	}else if(id=="radio3"){
		<s:if test='!"view".equals(mode)'>
			$("#deadlineStart3").attr("disabled",false);
			$("#deadlineEnd3").attr("disabled",false);
		</s:if>
		$("#deadlineEnd2").val('');
		$("#deadlineEnd4").val('');
	}else if(id=="radio4"){
		<s:if test='!"view".equals(mode)'>
			$("#deadlineStart4").attr("disabled",false);
			$("#deadlineEnd4").attr("disabled",false);
		</s:if>
		$("#deadlineEnd2").val('');
		$("#deadlineEnd3").val('');
	}
}

function determineValueChange(){
	$("#effectiveDaysDiv").hide();
	$("#deadlineTypeDiv").hide();
	var value = $("#timeLimit").val();
	if(value == <s:property value="@com.tianque.working.domain.TimeLimitHelper@EFFECTIVE_DAYS"/>){
		$("#effectiveDaysDiv").show();
	}else if(value == <s:property value="@com.tianque.working.domain.TimeLimitHelper@DEADLINE_TYPE"/>){
		$("#effectiveDays").val('');
		$("#deadlineTypeDiv").show();
	}
}

</script>