<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
.defaultTable{width;100%;}
.defaultTable{margin-top: 5px;width:100%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
.defaultTable th{
background:#efefef;
vertical-align:middle;text-align:center;
height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
.defaultTable td{padding:5px;height:26px;line-height:26px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
.defaultTable .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
.defaultTable .title{width:13%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
.defaultTable .text{width:25%;}
.defaultTable .number{width:50px;}
.defaultTable .textarea{width:70px;height:70px;}
.defaultTable .add{color:#339900;font:bold 14px/1.5em '' !important;}
.defaultTable .subtract{color:#FF0000;font:bold 14px/1.5em '' !important;}
.defaultTable .rowInfo{color:#999;border:0;}
.defaultTable .rowInfo td{border:0;}
.defaultTable .rowInfo .rowTitle{font:bold 16px/1.5em '' !important;}
.defaultTable .rowInfo .rowSmalltext{color:#333;}
.rowtitle{color:#444;font:bold 14px/35px "" !important;}
.rowtitle span{color:#999;font-size:14px !important;}
</style>
<div id="dialog-form" title="事件处理部门打分" class="container container_24">
	<form id="gradeIssueForm" method="post" action="${path}/fourTeamsIssueManage/saveIssueGrade.action">
		<input type="hidden" name="issue.id" value="${keyId}">
	    <h3 class="rowtitle"><span>事件名称：</span>${issue.subject }</h3>
        <table class="defaultTable">
            <tbody>
            	<tr>
                    <th colspan="1" rowspan="2" width="50%">事件处理过程分解</th>
                    <th colspan="1" rowspan="2" width="10%">自动打分</th>
                    <th colspan="3" rowspan="1" width="40%">手动打分</th>
                </tr>
                <tr>
                    <th colspan="1" rowspan="1">打分类型</th>
                    <th colspan="1" rowspan="1">打分分值</th>
                    <th colspan="1" rowspan="1">打分原因</th>
                </tr> <s:set name="issueRegradedPointsList" value="issue.regradedPointsList"></s:set>
                <s:subset source="issueDealLogs" start="0"><s:iterator status="index">
                <tr>
                    <td>
                    	<table class="rowInfo">
                    		<tr>
                    			<td style="width:30%;vertical-align: middle;"><span class="rowTitle">[<s:property value="dealOrg.orgName"/>]</span></td>
                    			<td style="width:50%;">
                    				<span class="rowDate"><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></span>
                    				<div class="rowSmalltext">${content }</div>
                    			</td>
                    			<td style="width:20%;"><span class="rowName"><s:property value="dealUserName"/>&nbsp;&nbsp;<s:property value="dealDescription"/></span></td>
                    		</tr>
                    	</table>
                    </td>
                    	<s:set name="score" value="0"></s:set>
		                <s:set name="issueGradetype" value="1"></s:set>
		                <s:set name="info" value=""></s:set>
		                <s:set name="autoScore" value="0"></s:set>
		                <s:set name="autoType" value="1"></s:set>
		                <s:set name="issueLogId" value="id"></s:set>
		                <s:iterator value="issueRegradedPointsList" status="rpList">
		                	<s:if test="logId.equals(#issueLogId)">
		                		<s:if test="@com.tianque.state.RegradedType@REGRADEDBYPERSON.equals(regradedType)">
				                <s:set name="issueGradetype" value="pointType"></s:set>
				                <s:set name="score" value="points"></s:set>
				                <s:set name="info" value="content"></s:set>
				                </s:if><s:else>
				                <s:set name="autoType" value="pointType"></s:set>
		                		<s:set name="autoScore" value="points"></s:set></s:else>
		                	</s:if>
		                </s:iterator><s:if test="!@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId().equals(dealOrg.id)"></s:if>
                    <s:if test="#autoType == 1"><td><span class="add">+<s:property value="#autoScore"/></span>分<s:property value="#aa"/></td></s:if>
                  	<s:if test="#autoType == -1"><td><span class="subtract"><img src="${resource_path}/resource/system/images/subtract_ico.png"></img>-<s:property value="#autoScore"/></span>分</td></s:if>
                    <td><s:if test="!@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId().equals(dealOrg.id)">
                    	<div><input type="hidden" name="issueAccord.orgId" value="<s:property value="dealOrg.id"/>">
                    		<input type="hidden" name="issueAccord.logId" value="<s:property value="id"/>">
                    		<select name="issueAccord.type">
                    			<option value="1" <s:if test="#issueGradetype == 1">selected</s:if>>加分</option>
                    			<option value="-1" <s:if test="#issueGradetype == -1">selected</s:if>>减分</option>
                    		</select>
                    	</div></s:if>
                    </td>
                    <td><s:if test="!@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId().equals(dealOrg.id)">
                    	<div><input name="issueAccord.score" maxlength="7" type="text" readonly="readonly" class="number" value="<s:property value="#score"/>" style="background: none;" /></div>
                    </s:if></td>
                    <td><s:if test="!@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId().equals(dealOrg.id)">
                    <div><textarea class="textarea" name="issueAccord.info" onblur="if(this.value != ''){this.style.borderColor = '#ABADB3';}"><s:property value="#info"/></textarea></div></s:if></td>
                </tr>
                </s:iterator></s:subset>
            </tbody>
        </table>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#gradeIssueForm .number").spinner({
		min: 0,
		max:100,
		change: function(e,ui){
			//var _val = $(this).val().replace(new RegExp(' ', 'g'), '');
			//_val = _val == null || _val == '' ? 0 : _val;
			//$(this).val(_val);
			//$("#gradeIssueForm .number").each(function (){$(this)[0].blur();});
		}
	});
	$("#gradeIssueForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			var _scoreNum = 0, _needText = false;
			$("input[name='issueAccord.score']").each(function (ind){
				if($(this).val() != 0 ){
					_scoreNum ++;
					var _nowText = $("textarea[name='issueAccord.info']:eq("+ind+")");
					if(_nowText.val() == ''){
						_nowText.css({'border': '1px solid red'});
						_needText = true;
					}
				}
			});
			if(_scoreNum == 0){
				$.messageBox({level:'warn',message:"没有任何加减分情况，无需打分！"});
				return;
			}
			if(_needText){
				$.messageBox({level:'warn',message:"请填写打分原因！"});
				return;
			}
			$(form).ajaxSubmit({
				success:function(data){
					if(data != 'true' && data != true){
	           	 		$.messageBox({ message: data, level:"error"});
	           	 		$("#gradeDialog").dialog("close");
	            		return;
	            	}
					$.messageBox({message:"打分成功!"});
					$("#gradeDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		}
	});
});
</script>