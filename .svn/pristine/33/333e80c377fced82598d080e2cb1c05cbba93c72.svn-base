<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="/includes/baseInclude.jsp" />
	<div id="workingSituation" class="container container_24">
		<form action="${path}/baseInfo/workingSituationManage/updateWorkingSituationSignDetail.action" method="post" id="maintainFormForWorkingSituation">
		<input type="hidden" name="workingSituation.id" id="workingSituation-id" value="${workingSituation.id}" />
		<input type="hidden" name="workingSituation.organization.id" id="orgId" value="${(workingSituation.organization.id)}" />
				
		<div class='clearLine'>&nbsp;</div>
			
		<div class="grid_7 lable-right">
			<label class="form-lbl">录入时间：</label>
		</div>
		<div class="grid_14">
			<input type="text" name="workingSituation.occurrenceDate" id="occurrenceDate" class="form-txt"
		value="<s:date name="workingSituation.occurrenceDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly style="background-color: #EBEBE4"/>
		</div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">民警姓名:</label>
		</div>
		<div class="grid_14">
			<input type="text" id="policeName" name="workingSituation.policeName" class="form-txt" value="${workingSituation.policeName}" maxlength="20" readonly style="background-color: #EBEBE4"/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">工作内容：</label>
		</div>
		<div class="grid_14">
		  <select name="workingSituation.workcontent.id" class="form-txt" id="workcontent" disabled >
		  <pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@WORKING_CONTENT_TYPE" defaultValue="${workingSituation.workcontent.id}" />
			</select>
		</div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_14">
            <textarea  name="workingSituation.remark"  cols="" class="form-txt" readonly style="background-color: #EBEBE4">${workingSituation.remark}</textarea>
        </div>
			
		
		<div class="grid_7 lable-right">
			<label class="form-lbl">签收人:</label>
		</div>
		<div class="grid_14">
			<input type="text" id="signUserName" name="workingSituation.signUserName" class="form-txt" value="${workingSituation.signUserName}" maxlength="20" readonly style="background-color: #EBEBE4"/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">签收日期：</label>
		</div>
		<div class="grid_14">
			<input type="text" name="workingSituation.signDate" id="signDate" class="form-txt"
		value="<s:date name="workingSituation.signDate" format="yyyy-MM-dd HH:mm:ss"/>" readonly style="background-color: #EBEBE4"/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_7 lable-right">
			<label class="form-lbl">签收意见：</label>
		</div>
		 <div class="grid_14">
            <textarea rows="4" name="workingSituation.advice"  cols="" class="form-txt">${workingSituation.advice}</textarea>
        </div>
		</form>
	 </div>	
<script type="text/javascript">



$(document).ready(function(){

	
	$("#maintainFormForWorkingSituation").formValidate({
		submitHandler: function(form){
		
		$(form).ajaxSubmit({
			async:false,
			success : function(data) {
				if (!data) {
					$.messageBox({
						message : "签收失败!",
						level : "error"
					});
					return;
				}
	
				$.messageBox({message:"签收成功!"}); 
			
				$("#workingSituationList").trigger("reloadGrid");
				$("#workingSituationDialog").dialog("close");	
				 
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{
			"workingSituation.advice":{
				maxlength:200
			}
		},
		messages:{	
			"workingSituation.advice":{
			    maxlength:$.format("签收意见最多输入{0}个字")
					}
		}
	});
	
});

</script>