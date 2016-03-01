<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<style>
	 .table {   
   			border:solid black 2px; 
            padding:0;    
            margin:0 auto;   
            border-collapse: collapse;   
        }   
      .td {  
            border:solid black 1px;
            font-size:12px text-align:center;
    		padding: 3px 3px 3px 8px;   
            color: #000000;   
        }
        .td1{
        
        	} 
</style>

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

<table width="100%" class="table">
  <tr bgcolor="#999999" align="center">
    <td width="4%" class="td"><b>大则序号</b></td>
    <td width="12%" class="td"><b>大则</b></td>
    <td width="4%" class="td"><b>小则序号</b></td>
    <td width="47%" class="td"><b>小则</b></td>
    <td width="4%" class="td"><b>基本分值</b></td>
    <td width="17%" class="td"><b>参与单位</b></td>
    <td width="4%" class="td"><b>考评分</b></td>
    <td width="8%" class="td"><b>考核摘要</b></td>
  </tr>
 <s:iterator value="examineItemVos" id="examineItemVo" status="ind">
 	<tr>
 	<s:if test="#examineItemVo.rowspanCatalog">
 		<td rowspan="<s:property value="#examineItemVo.rowspanCatalogCount"/>" align="center" class="td"><s:property value="#examineItemVo.catalogIndex" /></td>
 		<td rowspan="<s:property value="#examineItemVo.rowspanCatalogCount"/>" class="td"><s:property value="#examineItemVo.catalogShortName" /></td>
 	</s:if>
 	<s:if test="#examineItemVo.rowspanItem or #examineItemVo.middleItem">
 		<td align="right" rowspan="<s:property value="#examineItemVo.rowspanItemCount"/>" class="td"><s:property value="#examineItemVo.examineIndex" /></td>
 	</s:if>
 		<td class="td"><s:property value="#examineItemVo.examineContent" /></td>
 		<s:if test="#examineItemVo.middleItem">
 			<td class="td"><s:property value="#examineItemVo.planScore" /> </td>
 		</s:if>
 		<s:else>
 			<td></td>
 		</s:else>
		<td class="td"><s:property value="#examineItemVo.examineOrganizationNames" /></td>
		
		<s:if test="#examineItemVo.editabled">
			<s:iterator value="examineScores.scoreRecords" >
				<s:if test="#examineItemVo.id==examineItem.id">
					<td class="td">
					<input type="text"  class="numInput" id="${id}" style="width:50px;" value="${score}" readonly >
					</td>
					<td class="td"><textarea rows="5" cols="30" readonly >${scoreContent}</textarea></td>
				</s:if>
			</s:iterator>
		</s:if>
		<s:else>
			<td class="td"></td>
			<td class="td"></td>
		</s:else>
 	</tr>
 </s:iterator>
</table>
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