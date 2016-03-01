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

<table width="100%" class="table" >
  <tr bgcolor="#999999" align="center">
    <td width="4%"  class="td"><b>大则序号</b></td>
    <td width="12%"  class="td"><b>大则</b></td>
    <td width="4%"  class="td"><b>小则序号</b></td>
    <td width="47%"  class="td"><b>小则</b></td>
    <td width="4%"  class="td"><b>基本分值</b></td>
    <td width="17%"  class="td"><b>参与单位</b></td>
    <td width="4%"  class="td"><b>考评分</b></td>
    <td width="8%"  class="td"><b>考核摘要</b></td>
  </tr>
 <s:iterator value="examineItemVos" id="examineItemVo" status="ind">
 	<tr>
 	<s:if test="#examineItemVo.rowspanCatalog">
 		<td rowspan="<s:property value="#examineItemVo.rowspanCatalogCount"/>"  class="td" align="center"><s:property value="#examineItemVo.catalogIndex" /></td>
 		<td rowspan="<s:property value="#examineItemVo.rowspanCatalogCount"/>"  class="td"><s:property value="#examineItemVo.catalogShortName" /></td>
 	</s:if>
 	<s:if test="#examineItemVo.rowspanItem or #examineItemVo.middleItem">
 		<td align="right" rowspan="<s:property value="#examineItemVo.rowspanItemCount"/>"  class="td"><s:property value="#examineItemVo.examineIndex" /></td>
 	</s:if>
 		<td  class="td"><s:property value="#examineItemVo.examineContent" /></td>
 		<s:if test="#examineItemVo.middleItem">
 			<td  class="td"><s:property value="#examineItemVo.planScore" /> </td>
 		</s:if>
 		<s:else>
 			<td  class="td"></td>
 		</s:else>
		<td  class="td"><s:property value="#examineItemVo.examineOrganizationNames" /></td>
		
		<s:if test="#examineItemVo.editabled">
			<td  class="td"><input type="hidden" name="checkedExamineScoreRecord['${ind.index}'].examineItem.id" value="${id}">
			<input type="text" name="checkedExamineScoreRecord['${ind.index}'].score" class="numInput" id="${id}" style="width:50px;" >
			</td>
			<td  class="td"><textarea rows="5" cols="30" name="checkedExamineScoreRecord['${ind.index}'].scoreContent" ></textarea></td>
		</s:if>
		<s:else>
			<td  class="td"></td>
			<td  class="td"></td>
		</s:else>
 	</tr>
 </s:iterator>
</table>

<script type="text/javascript">

var maxScoreDate=new Array();
<s:iterator value="examineItemVos" id="examineItemVo" status="ind">
	<s:if test="!#examineItemVo.middleItem">
		maxScoreDate[${id}]=${planScore};
	</s:if>
</s:iterator>

$(document).ready(function(){
	$(".numInput").bind("blur",function(){
		var inputObject=$(this);
		var name=inputObject.attr("name");
		var errorMsgs=new Array();
		
		if(!isNum(inputObject.val())){
			errorMsgs.push("请输入数字");
		}else if(!checkMax(inputObject.attr("id"))){
			errorMsgs.push("超过最大分值："+maxScoreDate[inputObject.attr("id")]);
		}
		if(errorMsgs.length>0){
			inputObject.poshytip('hide');
			inputObject.dialogtip({
				content:"<div class='inputName' inputName='"+name+"'><span class='error'></span>"+errorMsgs.pop()+"</div>"
			});
			inputObject.poshytip('show');
			inputObject.focus();
		}else{
			inputObject.poshytip('hide');
		}
	});
});

function isNum(value){
	if(value==null||value=='')
		return true;
	return !isNaN(value)  
}

function checkMax(emid){
	var score=$('#'+emid).val();
	if(score==null||score=='')
		return true;
	if(eval(score)>maxScoreDate[emid])
		return false;
	return true;
}


</script>