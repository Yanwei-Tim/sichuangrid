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
	<input type="hidden" name="examinePlan.id" value="${examinePlan.id }"/>

<table width="100%">
	<tr>
		<td align="right" width="10%" class="td1">年度:</td>
		<td width="10%">
			<select name="examinePlan.year" id="year" <s:if test='"view".equals(mode)'>disabled='true'</s:if>>
				<s:iterator value="availableYears" id="year">
					<option value="<s:property />" <s:if test="examinePlan.year == #year">selected="selected" </s:if> ><s:property /> </option>
				</s:iterator>
			</select>
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</td>
		<td align="right" width="10%" class="td1">标题:</td>
		<td width="40%" class="td1">
			<input lang="40" size="40" name="examinePlan.title" value="${examinePlan.title }" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</td>
		<td align="right" width="10%" class="td1">制定部门:</td>
		<td width="20%" class="td1">
			<input width="100%" readonly="readonly" name="examinePlan.draftOrganization" value="${examinePlan.draftOrganization }" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</td>
	</tr>
</table>
<table width="100%" border="1" class="table">
  <tr bgcolor="#999999" align="center">
    <td width="4%" class="td"><b>大则序号</b></td>
    <td width="10%" class="td"><b>大则</b></td>
    <td width="4%" class="td"><b>小则序号</b></td>
    <td width="4%" class="td"><b>是否选择</b></td>
    <td width="48%" class="td"><b>小则</b></td>
    <td width="4%" class="td"><b>基本分值</b></td>
    <td width="17%" class="td"><b>参与单位</b></td>
    <td width="8%" class="td"><b>备注</b></td>
  </tr>
 <s:iterator value="examineItemVos" id="examineItemVo">
 	<tr>
 	<s:if test="#examineItemVo.rowspanCatalog">
 		<td rowspan="<s:property value="#examineItemVo.rowspanCatalogCount"/>" align="center" class="td"><s:property value="#examineItemVo.catalogIndex" /></td>
 		<td rowspan="<s:property value="#examineItemVo.rowspanCatalogCount"/>" class="td"><s:property value="#examineItemVo.catalogShortName" /></td>
 	</s:if>
 	<s:if test="#examineItemVo.rowspanItem or #examineItemVo.middleItem">
 		<td align="right" rowspan="<s:property value="#examineItemVo.rowspanItemCount"/>" class="td"><s:property value="#examineItemVo.examineIndex" /></td>
 	</s:if>	
 	<s:if test="#examineItemVo.middleItem">
 		<td class="td"></td>
 	</s:if>
 	<s:else>
 		<td align="center" class="td">
 			<input type="checkbox" name="examinePlan.examineItems.id" value="<s:property value="#examineItemVo.id" />" 
 				<s:if test="#examineItemVo.selected">checked="checked"</s:if>	<s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
 		</td>
 	</s:else>
 		<td><s:property value="#examineItemVo.examineContent" /></td>
 		<td class="td">
 		<s:if test="#examineItemVo.middleItem">
 			<s:property value="#examineItemVo.planScore" />
 		</s:if>
 		</td>
		<td><s:property value="#examineItemVo.examineOrganizationNames" /></td>
		<td><s:property value="#examineItemVo.remark" /></td>
 	</tr>
 </s:iterator>
</table>
</form>


<script type="text/javascript">
$(document).ready(function(){
	<s:if test='"add".equals(mode)'>
	$("#maintainForm").attr("action", "${path}/examine/examinePlanManage/addExaminePlan.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
	$("#maintainForm").attr("action","${path}/examine/examinePlanManage/updateExaminePlan.action");
	</s:if>
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
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
						$("#examineList").addRowData(data.id, data, "first");
						$.messageBox({message:"已成功保存评分规则新信息"});
					</s:if>
					<s:if test='"edit".equals(mode)'>
					 	$("#examineList").setRowData(data.id,data);
						$.messageBox({message:"已成功保存评分规则修改信息"});
					</s:if>
					 $("#examineMaintanceDialog").dialog("close");
				     $("#examineList").setSelection(data.id);
				}
			})
		},
		rules:{
			"examinePlan.year": {
				required:true
			},
			"examinePlan.title":{
				required:true,
				maxlength:40
			},
			"examinePlan.examineItems.id":{
				required:true
			}
		},
		messages: {
			"examinePlan.year":{
				required:"请输入年度"
			},
			"examinePlan.title":{
				required:"请输入标题",
				maxlength:$.format("标题最多需要输入{0}个字符")
			},
			"examinePlan.examineItems.id":{
				required:"请选择细则"
			}
		}
	});
});
</script>