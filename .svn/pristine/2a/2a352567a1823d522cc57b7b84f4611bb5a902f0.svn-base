<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="expirationDate" action="javascript:void(0);" method="post">
		<input type="hidden" id="parentId" value="${dailyDirectory.parentDailyDirectory.id}" name="dailyDirectory.parentDailyDirectory.id" />
		<input type="hidden" value="${dailyDirectory.dailyYear.id}" name="dailyDirectory.dailyYear.id" />
		<input type="hidden" id="dailyDirectoryId" value="${dailyDirectory.id}" name="dailyDirectory.id" />
		<input type="hidden" value="${dailyDirectory.fullName}" name="dailyDirectory.fullName" />
		<div class='clearLine'>&nbsp;</div>
		<s:if test="show">
			<div class="grid_4 lable-right">
				<label>台帐时限：</label>
			</div>
			<div class="grid_7" >
				<select id="timeLimit" name="dailyDirectory.timeLimit" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select">
					<s:iterator value="timeLimitMap" var="obj">
						<option value='<s:property value="#obj.key"/>' <s:if test="dailyDirectory.getTimeLimit() == #obj.key">selected</s:if> ><s:property value="value"/></option>
					</s:iterator>
				</select>
			</div>
			
				<div id="effectiveDaysDiv">
					<div class="grid_5 lable-right">
						<label>有效天数：</label>
					</div>
					<div class="grid_6" >
						<input value="${dailyDirectory.effectiveDays}" id="effectiveDays" name="dailyDirectory.effectiveDays" 
							<s:if test='"view".equals(mode)'>disabled</s:if> maxlength="3" class="form-txt" />
					</div>
					<div class="grid_1 lable-right">
						<label>天</label>
					</div>
				</div>
			
			<s:if test="flg">
			<div class='clearLine'>&nbsp;</div>
				<div id="deadlineTypeDiv">
					<div class="grid_4 lable-right">
						<label>截止时间：</label>
					</div>
					
					<div class="grid_7" >
						<label for="radio1" class="form-check-text" >
							<input type="radio" id="radio1" name="dailyDirectory.deadlineType" 
								value='<s:property value="@com.tianque.working.domain.TimeLimitHelper@ORDINARY_LEDGER"/>'
								<s:if test="@com.tianque.working.domain.TimeLimitHelper@ORDINARY_LEDGER == dailyDirectory.getDeadlineType()">checked</s:if>
								<s:if test='"view".equals(mode)'>disabled</s:if> />
							普通台账
						</label>
					</div>
					<div class="grid_9" >
						<input type="text" id="deadlineDate" name="dailyDirectory.deadlineDate" 
							value="<s:date name="dailyDirectory.deadlineDate" format="yyyy-MM-dd"/>" 
							<s:if test='"view".equals(mode)'>disabled</s:if> class="form-txt" readonly />
					</div>
					<div class="grid_3" style="margin:0 0 0 8px">
						<label>日前</label>
					</div>
					
					<div class='clearLine'>&nbsp;</div>
					
					<div class="grid_4" >&nbsp;</div>
					<div class="grid_7" >
						<label for="radio2" class="form-check-text" >
							<input type="radio" id="radio2" name="dailyDirectory.deadlineType"  
								value='<s:property value="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_MONTHLY"/>'
								<s:if test="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_MONTHLY == dailyDirectory.getDeadlineType()">checked</s:if>
								<s:if test='"view".equals(mode)'>disabled</s:if> />
							按月上报台账
						</label>
					</div>
					<div class="grid_6" >
						<select id="deadlineStart2" name="dailyDirectory.deadlineStart" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select" >
							<option value=""></option>
							<s:iterator value="deadlineStartList" var="obj" begin="0" end="1"  >
								<s:iterator value="obj" var="obj1" >
									<option value='<s:property value="#obj1.key"/>' <s:if test="dailyDirectory.getDeadlineStart() == #obj1.key">selected</s:if> ><s:property value="value"/></option>
								</s:iterator>
							</s:iterator>
						</select>
					</div>
					<div class="grid_3" >
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
					<div class="grid_4" >&nbsp;</div>
					<div class="grid_7" >
						<label for="radio3" class="form-check-text" >
							<input type="radio" id="radio3" name="dailyDirectory.deadlineType"
								value='<s:property value="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_QUARTERLY"/>'
								<s:if test="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_QUARTERLY == dailyDirectory.getDeadlineType()">checked</s:if> 
								<s:if test='"view".equals(mode)'>disabled</s:if> />
							按季度上报台账
						</label>
					</div>
					<div class="grid_6" >
						<select id="deadlineStart3" name="dailyDirectory.deadlineStart" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select" >
							<option value=""></option>
							<s:iterator value="deadlineStartList" var="obj" id="sss" begin="2" end="3"  >
								<s:iterator value="sss" var="obj1" >
									<option value='<s:property value="key"/>' <s:if test="dailyDirectory.getDeadlineStart() == key">selected</s:if>> <s:property value="value"/></option>
								</s:iterator>
							</s:iterator>
						</select>
					</div>
					<div class="grid_3">
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
					<div class="grid_4" >&nbsp;</div>
					<div class="grid_7" >
						<label for="radio4" class="form-check-text" >
							<input type="radio" id="radio4" name="dailyDirectory.deadlineType"  
								value='<s:property value="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_YEAR"/>'
								<s:if test="@com.tianque.working.domain.TimeLimitHelper@REPORTED_LEDGER_YEAR == dailyDirectory.getDeadlineType()">checked</s:if>
								<s:if test='"view".equals(mode)'>disabled</s:if> />
							按半年上报台账
						</label>
					</div>
					<div class="grid_6" >
						<select id="deadlineStart4" name="dailyDirectory.deadlineStart" <s:if test='"view".equals(mode)'>disabled</s:if> class="form-select" >
							<option value=""></option>
							<s:iterator value="deadlineStartList" var="obj" id="sss" begin="4" end="5"  >
								<s:iterator value="sss" var="obj1" >
									<option value='<s:property value="key"/>' <s:if test="dailyDirectory.getDeadlineStart() == key">selected</s:if>> <s:property value="value"/></option>
								</s:iterator>
							</s:iterator>
						</select>
					</div>
					<div class="grid_3">
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
	<s:if test='"add".equals(mode)'>
		$("#effectiveDaysDiv").hide();
		<s:if test="flg">
			$("#deadlineTypeDiv").hide();
		</s:if>
	</s:if>
	<s:else>
		determineValueChange();
	</s:else>

	jQuery.validator.addMethod("effectiveDaysIsEmpty", function(value, element){
		var timeLimitValue = $('#timeLimit').val();
		if(timeLimitValue == <s:property value="@com.tianque.working.domain.TimeLimitHelper@EFFECTIVE_DAYS"/>){
			if(value==null||value==undefined||value==""){
				return false;
			}
		}
		return true;
	});

	jQuery.validator.addMethod("deadlineIsEmpty", function(value, element){
		var timeLimitValue = $('#timeLimit').val();
		if(timeLimitValue == <s:property value="@com.tianque.working.domain.TimeLimitHelper@DEADLINE_TYPE"/>){
			if(value==null||value==undefined||value==""){
				return false;
			}
		}
		return true;
	});
	
	$("#timeLimit").change(function(){
		$("#effectiveDays").val('');
		determineValueChange();
		determineRadio();
	});
	
	$('#deadlineDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		minDate:new Date()
	});
	
	$("#expirationDate").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			 directoryAjaxSubmit();
		},
		rules:{
			"dailyDirectory.effectiveDays":{
				positiveInteger:true,
				effectiveDaysIsEmpty:true
			},
			"dailyDirectory.deadlineStart":{
				deadlineIsEmpty:true
			},
			"dailyDirectory.deadlineEnd":{
				deadlineIsEmpty:true
			},
			"dailyDirectory.deadlineDate":{
				deadlineIsEmpty:true
			}
		},
		messages:{
			"dailyDirectory.effectiveDays":{
				positiveInteger:"请输入正整数",
				effectiveDaysIsEmpty:"不能为空"
			},
			"dailyDirectory.deadlineStart":{
				deadlineIsEmpty:"不能为空"
			},
			"dailyDirectory.deadlineEnd":{
				deadlineIsEmpty:"不能为空"
			},
			"dailyDirectory.deadlineDate":{
				deadlineIsEmpty:"不能为空"
			}
		}
	});

	<s:if test="flg">
		$("#deadlineTypeDiv select").removeAttr("name").attr("disabled",true);
		$("#deadlineTypeDiv #deadlineDate").attr("disabled",true);
		$(":radio").each(function(i,obj){
			if(obj.checked){
				checkedObjId(obj.id);
			}
			$(obj).change(function(){
				$("#deadlineTypeDiv #deadlineDate").attr("disabled",true).val('');
				$("#deadlineTypeDiv select").removeAttr("name").attr("disabled",true);
				$("#deadlineTypeDiv select").each(function(i,obj1){
					obj1.selectedIndex = 0;
					obj1.disabled = true;
				});
				checkedObjId(obj.id);
			});
		});
	</s:if>
	
	<s:if test='"add".equals(mode)'>
		$("#expirationDate").attr("action","${path}/daily/dailyDirectoryManage/addDailyDirectory.action");
	</s:if>
	<s:if test='"expDate".equals(mode)'>
		$("#expirationDate").attr("action","${path}/daily/dailyDirectoryManage/updateDailyDirectory.action");
	</s:if>

});

function checkedObjId(id){
	
	if(id=="radio1"){
		$("#deadlineTypeDiv #deadlineDate").attr("disabled",false);
	}else if(id=="radio2"){
		$("#deadlineStart2").attr("disabled",false).attr("name","dailyDirectory.deadlineStart");
		$("#deadlineEnd2").attr("disabled",false).attr("name","dailyDirectory.deadlineEnd");
		$("#deadlineEnd3").val('');
		$("#deadlineEnd4").val('');
	}else if(id=="radio3"){
		$("#deadlineStart3").attr("disabled",false).attr("name","dailyDirectory.deadlineStart");
		$("#deadlineEnd3").attr("disabled",false).attr("name","dailyDirectory.deadlineEnd");
		$("#deadlineEnd2").val('');
		$("#deadlineEnd4").val('');
	}else if(id=="radio4"){
		$("#deadlineStart4").attr("disabled",false).attr("name","dailyDirectory.deadlineStart");
		$("#deadlineEnd4").attr("disabled",false).attr("name","dailyDirectory.deadlineEnd");
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

function determineRadio(){
	$("#deadlineTypeDiv #deadlineDate").attr("disabled",true).val('');
	$(":radio").attr('checked',false);
	$("#deadlineTypeDiv select").each(function(i,obj){
		obj.selectedIndex = 0;
		obj.disabled = true;
	});
}

function directoryAjaxSubmit(){
	$("#expirationDate").ajaxSubmit({
        success: function(data){
			if(!data.id){
				$.messageBox({message:data,level: "error"});
				return;
			}
			var listId = $("#dailyDirectoryList");
			<s:if test='"add".equals(mode)'>
				if(data.parentId == 0){
					setPlace(data);
				}else{
					listId.addChildNode(data.id,data.parentId,data);
				}
				var level = listId.getRowData(data.id).level;
				if(isNaN(level)){
					level = $($(level)).text();
				}
				
				listId.setSelection(data.id);
				$("#dailyDirectoryList").trigger("reloadGrid");
				$.messageBox({message:"成功保存新台账目录信息!"});
			</s:if>
			<s:if test='"expDate".equals(mode)'>
				
				$("#dailyDirectoryList").trigger("reloadGrid");
				$.messageBox({message:"成功保存台账目录修改信息!"});
			</s:if>
			$("#dailyDirectoryDialog").dialog("close");
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
			$.messageBox({message:"提交错误",level: "error"	});
 	   }
 	});
}

function setPlace(data){
	var datas = $("#dailyDirectoryList").getGridParam("data");
	var indexId = 0;
	var placeId = 0;
	$.each(datas,function(a,b){
		var _level = b.level;
		if(isNaN(_level)){
			_level = $($(level)).text();
		}
		if(parseInt(_level) == 0 && b.indexId > indexId){
			indexId = b.indexId;
			placeId = b.id;
		}
		return;
	});
	$("#dailyDirectoryList").addChildNode(data.id,null,data);
	$("#"+placeId).before($("#"+data.id));
	$("#dailyDirectoryList").setCell(data.id,"level",0);
	$("#dailyDirectoryList").setCell(placeId,"indexId",indexId+1);
}

function hideRowData(ids,parentId){
	for(var i=0;i<ids.length;i++){
		var rowDataId = $("#dailyDirectoryList").getRowData(ids[i]).parentId;
		if(rowDataId == parentId){
			$("#"+ids[i]).hide();
			hideRowData(ids,ids[i]);
		}
	}
	return ;
}

function showRowData(ids,parentId){
	var _str = "td[aria-describedby='dailyDirectoryList_fullName']";
	for(var i=0;i<ids.length;i++){
		var rowDataId = $("#dailyDirectoryList").getRowData(ids[i]).parentId;
		if(rowDataId == parentId){
			if($("#"+parentId).find(_str).find(".ui-icon-triangle-1-e").length == 0){
				$("#"+ids[i]).show();
				showRowData(ids,ids[i]);
			}
		}
	}
	return ;
}
</script>