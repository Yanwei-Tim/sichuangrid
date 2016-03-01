<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<form name="maintainForm" id="maintainForm" method="post" action="">
<input type="hidden" id="orgId" name="examineScores.org.id" value=${examineScores.org.id}>
<input type="hidden" name="examineScores.id" value=${examineScores.id}>

<table width="100%">
	<tr>
		<td align="right">以:</td>
		<td><input type="hidden" name="examineScores.year" id="examineScoresYear">
			<select id="year" <s:if test='"view".equals(mode)'>disabled='true'</s:if>
			name="examineScores.plan.id"
			 onchange="reloadExamineScoreRecord(this)">
				<option></option>
				<s:iterator value="examinePlans" >
					<option value="${id}" <s:if test="year.equals(examineScores.year)">selected</s:if>>${year}年${title}</option>
				</s:iterator>
			</select>
			的考核细则为准
		</td>
	</tr>
</table>
<div id="tableExamine">

</div>
</form>

<script type="text/javascript">

var yearsData=new Array();
<s:iterator value="examinePlans" >
yearsData[${id}]='${year}';
</s:iterator>

$(document).ready(function(){
	<s:if test='"add".equals(mode)'>
	$("#maintainForm").attr("action", "${path}/examine/examineScoreRecordManage/addExamineScoreRecord.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
	$("#maintainForm").attr("action","${path}/examine/examineScoreRecordManage/updateExamineScoreRecord.action");
	</s:if>
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			var validatePass=true;
			$(".numInput").each(function(){
				if(!isNum($(this).val())||!checkMax($(this).attr("id")))
					validatePass=false;
			});
			if(!validatePass)
				return ;
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
						$.messageBox({
							message: data,
							level: "error"
						});
						return;
					}
					<s:if test='"add".equals(mode)' >
						$("#examineScoreRecordList").addRowData(data.id, data, "first");
						$.messageBox({message:"已成功保存年度考核新信息"});
					</s:if>
					<s:if test='"edit".equals(mode)'>
					 	$("#examineScoreRecordList").setRowData(data.id,data);
						$.messageBox({message:"已成功保存年度考核修改信息"});
					</s:if>
					 $("#examineScoreRecordMaintanceDialog").dialog("close");
				     $("#examineScoreRecordList").setSelection(data.id);
				}
			});
		},
		rules:{
			"examineScores.plan.id":{
				required:true,
				remote:{
					data:{
            			"examineScores.org.id": function(){ return $('#orgId').val()},
            			"examineScores.year":function(){return yearsData[$("#year").val()];}              
					},
					url:"${path}/examine/examineScoreRecordManage/checkUnique.action",
					type:"get"
				}
			}
		},
		messages:{
			"examineScores.plan.id":{
				required:"年度考核细则必须选择",
				remote:"该年度的考核评分已经存在!"
			}
			
		}
	});
});

function reloadExamineScoreRecord(dom){
	var examinePlanId=$(dom).val();
	$("#examineScoresYear").val(yearsData[examinePlanId]);
	$("#tableExamine")
	.load("${path}/examine/examineScoreRecordManage/findSelectedExamineVoForPage.action?examinePlan.id="+examinePlanId);
}
</script>