<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style type="text/css">
	div.terms {
		width:538px;
		height:50px;
		border:1px solid #7F9DB9;
		background:#ffffff;
		padding:6px;
		overflow:auto;
	}
</style>

<div id="search-condition-form" title="待办诉求查询" class="container container_24">
<form id="searchMmaintainForm" method="post" action="">

       	<input type="hidden" name="conditionStatus" id="conditionStatus" value="${status}" />
        <input id="conditionOccurOrgId" name="occurOrg.id" type="hidden" />
		<input id="conditionOrganizationId" name="organization.id" type="hidden" value="${organization.id}" />
   		<div>
   			<div class="grid_3 lable-right">
				<label class="form-lbl">发生时间:</label>
			</div>
			<div class="grid_4 form-left">
			    <input type="text" id="conditionOccurFrom" name="conditionOccurFrom" value="" readonly="readonly" class="form-txt" />
			</div>
			
			<div class="grid_1 lable-right">
				<label class="form-lbl">至:</label>
			</div>
			<div class="grid_4 form-left">
				<input type="text" id="conditionOccurEnd" name="conditionOccurEnd" value="" readonly="readonly" class="form-txt" />
			</div>
			<!-- 来源方式 -->
			
			<div class='clearLine'></div>
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">来源人：</label>
			</div>
			<div class="grid_10 form-left">
		    	<input type="text" id="conditionSourcePerson" name="conditionSourcePerson" maxlength="20" value="" class="form-txt" />
			</div>
			
			<div class="grid_4 lable-right">
				<label class="form-lbl">联系电话：</label>
			</div>
			<div class="grid_5 form-left">
				<input type="text" id="conditionSourceMobile" name="conditionSourceMobile" maxlength="35" value="" class="form-txt" />
			</div>
			
			<div class='clearLine'></div>
			<!--  
			<div class="grid_3 lable-right">
	  			<label class="form-lbl">服务单号：</label>
	  		</div>
    		<div class="grid_10 form-left">
				<input type="text" id="conditionSerialNumber" name="conditionSerialNumber" maxlength="20" value="" class="form-txt" />
    		</div>
    		-->
    		<div class='clearLine'></div>
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件名称：</label>
			</div>
			<div class="grid_21 form-left">
		    	<input type="text" id="conditionSubject" name="conditionSubject" maxlength="50" value="" class="form-txt" />
			</div>
		
    		<div class='clearLine'></div>
    		
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件类型：</label>
			</div>
			<div class="grid_3 multipeSelect">
				<input id="dissension" name="" type="checkbox" class="category" />
				<label class="form-check-text" for="dissension">矛盾纠纷 </label>
				<ul class="zc-sf" >
					<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
				    <s:iterator value="contradiction" var="type">
						<li><input name="selContradictionId" type="checkbox"
						 value='<s:property value="id" />' />
						 <label><s:property value="issueTypeName" /></label>
						</li>
					</s:iterator>
					<div class="clear"></div>
				</ul>
			</div>
	
			<div class="grid_1"></div>
			<div class="grid_4 multipeSelect">
				<input id="employment" name="" type="checkbox" class="category" />
				<label class="form-check-text" for="employment">治安、安全隐患 </label>
				<ul class="zc-sf">
				<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
					<s:iterator value="specialisation" var="type">
						<li><input name="selSpecialisationId" type="checkbox"
						 value='<s:property value="id" />' />
						 <label><s:property value="issueTypeName" /></label>
						</li>
					</s:iterator>
					<div class="clear"></div>
				</ul>
			</div>
			
			<div class="grid_1"></div>
			<div class="grid_3 multipeSelect">
				<input id="peopleLive" name="" type="checkbox" class="category" />
				<label class="form-check-text" for="peopleLive">民生服务 </label>
				<ul class="zc-sf">
				<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
					<s:iterator value="peopleliveService" var="type">
						<li><input name="selPeopleliveServiceId" type="checkbox"
						 value='<s:property value="id" />' />
						 <label><s:property value="issueTypeName" /></label>
						</li>
					</s:iterator>
				</ul>
			</div>
			<div class="grid_1"></div>
			<s:if test="!otherType.isEmpty()">
				<div class="grid_3 multipeSelect">
					<input id="otherTypeSelector" name="" type="checkbox" class="category" />
					<label class="form-check-text" for="otherTypeSelector">其他 </label>
					<ul class="zc-sf">
					<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
						<s:iterator value="otherType" var="type">
							<li><input name="selOtherTypeId" type="checkbox"
							 value='<s:property value="id" />' />
							 <label><s:property value="issueTypeName" /></label>
							</li>
						</s:iterator>
					</ul>
				</div>
			</s:if>
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_3 lable-right"></div>
			<div class="terms" style="overflow-y:auto;height:60px" id="issueTypeDescription">
			</div>
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_3 lable-right">
				<label class="form-lbl">事件简述：</label>
			</div>
			<div class="grid_21 form-left heightAuto" style="display:inline;float:left;width:85.5%;">
    			<textarea rows="3" cols="" id="issueContent" name="issueContent"
    				class="form-txt" style="height:86px;"></textarea>
			</div>
	</div>
  </form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	renderIssueTypes();
	$("#dissension").typeSelect({width:600,columns:3,close:function(ids,labels){renderIssueTypes();}});
	$("#employment").typeSelect({width:400,columns:3,close:function(ids,labels){renderIssueTypes();}});
	$("#peopleLive").typeSelect({width:300,columns:3,close:function(ids,labels){renderIssueTypes();}});
	$("#otherTypeSelector").typeSelect({width:300,position:"bottom-left",close:function(ids,labels){renderIssueTypes();}});
	
	$("ul.zc-sf li label").click(function(){
		$(this).parent().find("input").click();
	})
	
	$("#conditionOccurFrom").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurEnd").datepicker("option","minDate",date);
			}
		}
    }); 

	$("#conditionOccurEnd").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+0d",
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurFrom").datepicker("option", "maxDate",date);
			}
		}
	});
});

function setZone(selectOrgId,selectOrgName){
	$("#conditionOccurOrgId").val(selectOrgId);
}

function renderIssueTypes(){
	var text="矛盾纠纷："+$.trim($("#dissension").getTypeSelectLabels())+"<br>治安、安全隐患："+$.trim($("#employment").getTypeSelectLabels())+"<br>民生服务："+$.trim($("#peopleLive").getTypeSelectLabels());
	<s:if test="!otherType.isEmpty()">
	text = text + "<br>其他："+$.trim($("#otherTypeSelector").getTypeSelectLabels());
	</s:if>

	$("#issueTypeDescription").html(text);
}
</script>