<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class='container_24'>
	<form id="report_form" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="edit" />
		<input type="hidden" name="turnForm.stepId"  value="${turnForm.stepId}" />
		<input type="hidden" id="_reportFormLedgerType" name="turnForm.ledgerType"  value="${turnForm.ledgerType}" />
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_6">
		<input type="text"  name="turnForm.serialNumber" id="serialNumber"  maxlength="30" value="${turnForm.serialNumber}" 
				readonly="readonly" class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<em class="form-req">*</em><label class="form-lbl">承办单位：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="turnForm.targetOrg.orgName" id="turnForm.targetOrg.orgName"  maxlength="30" value="${turnForm.targetOrg.orgName}" 
				readonly="readonly" class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<em class="form-req">*</em><label class="form-lbl">责任人：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="turnForm.manager" id="turnForm.manager"  maxlength="30" value="${turnForm.manager}" 
				 class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em><label class="form-lbl">主要事项：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="reason" name="turnForm.reason"  maxlength="50" class='form-txt' >${turnForm.reason}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">        
			<em class="form-req">*</em><label class="form-lbl">办理时限：</label>
	 	</div>
		<div class="grid_4">
			<input type="text" id="handleStartDate" name="turnForm.handleStartDate" class="form-txt"
			value="<s:date name="turnForm.handleStartDate" format="yyyy-MM-dd "/>" readonly />
		</div>
		<div class="grid_1 lable-right">        
			至
	 	</div>
		<div class="grid_4">
			<input type="text" id="handleEndDate" name="turnForm.handleEndDate" class="form-txt"
			value="<s:date name="turnForm.handleEndDate" format="yyyy-MM-dd "/>" readonly />
		</div>
		<div class='clearLine'>&nbsp;</div>
			
		<div class="grid_5 lable-right">
			<em class="form-req">*</em><label class="form-lbl">转办单位分管领导意见：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="subOpinion" name="turnForm.subOpinion"  maxlength="250" class='form-txt' >${turnForm.subOpinion}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em><label class="form-lbl">转办单位主要领导意见：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="opinion" name="turnForm.opinion"  maxlength="250" class='form-txt' >${turnForm.opinion}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<c:if test="${turnForm.id ne null}">
			<div class="grid_5 lable-right">        
				<label class="form-lbl">承办单位接件时间：</label>
		 	</div>
			<div class="grid_6">
				<input type="text"  name="turnForm.receiveDate" id="turnForm.receiveDate"  maxlength="30" value="${turnForm.receiveDate}" 
					readonly="readonly" class='form-txt' />
			</div>
		</c:if>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">承办单位联系人：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="turnForm.name" id="turnForm.name"  maxlength="30" value="${turnForm.name}" 
				 class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">办理结果：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="handleResult" name="turnForm.handleResult"  maxlength="50" class='form-txt' >${turnForm.handleResult}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
	</form>
</div>

<div id="formPrint" style="height:380px;overflow:hidden;overflow-y:auto;display:none">
	<style>
	.tablelist{margin-top: 5px;width:96%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
	.tablelist td{height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
	.tablelist .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
	.tablelist .title{width:14%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
	</style>
	<table  width="96%" border="0" cellpadding="0" cellspacing="0">
       		<tr>
	          	<td class="tdhl" width="100%" colspan="6" align="right">   编号：( ${turnForm.serialNumber} )   号</td>
       		</tr>
        	<tr>
	          	<td colspan="6" style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;">
	          		中江县三本台账工作<u style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;text-decoration : underline">
	          			<s:if test="turnForm.ledgerType==2">困   难</s:if>
	          			<s:elseif test="turnForm.ledgerType==3">稳   定</s:elseif>
	          			<s:else>民   生</s:else>
	          			</u>信息转办事项通知单
	          	    <br><br>
	          	</td>
        	</tr>
        	
        </table>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
       		<tr>
        		<td width="12%" class="tdhr"><b>承办单位：</b></td>
	          	<td width="48%">${turnForm.targetOrg.orgName}</td>
	          	<td width="16%" class="tdhr"><b>责任人：  </b></td>
	          	<td width="24%">${turnForm.manager}</td>
        	</tr>	
        
       
	       	<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       			主要事项
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	       			${turnForm.reason}
	            </td>
	       	</tr>
       	
       		<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		办理时限
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	       			<s:if test="turnForm.handleStartDate!=null"><s:date name="turnForm.handleStartDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>
	       			   —— 
	       			<s:if test="turnForm.handleEndDate!=null"><s:date name="turnForm.handleEndDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>	   
	            </td>
       		</tr>
       		<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		转办单位分管领导意 见
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	            	${turnForm.subOpinion}
	            </td>
       		</tr>
       		<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		转办单位主要领导意见
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	            	<p align="right" valign="bottom" style="margin-right:120px;margin-top:40px;">  签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	            	<p align="right" valign="bottom" style="margin-right:20px;"> &nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</p>
	            </td>
       		</tr>
       		<tr>
        		<td width="12%" class="tdhr"><b>承办单位接件时间</b></td>
	          	<td width="48%">
	          		<s:if test="turnForm.receiveDate!=null"><s:date name="turnForm.receiveDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>
	          	</td>
	          	<td width="16%" class="tdhr"><b>承办单位联系人：  </b></td>
	          	<td width="24%">${turnForm.name}</td>
        	</tr>	
       		
       		<tr class="tr1" height="60">
	       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		办理结果
	       		</td>
	       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	            	${turnForm.handleResult}
	            </td>
       		</tr>
       	
    </table>
</div>


<script type="text/javascript">

$(document).ready(function(){
	$('#handleStartDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d',
	    onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=""){
			var dateUnit=dateText.split("-");
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#handleEndDate").datepicker("option","minDate",date);
		}
	}
	});
	$('#handleEndDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
		minDate:'+0d',
	    maxDate:'+360d',
	    onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#handleStartDate").datepicker("option","maxDate",date);
			}
		}
	    });


	$("#report_form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				type: 'post',
		        url: '/threeRecords/turnForm/dispatch.action?'+$("#issueDealForm").serialize(), 
				success:function(data){
					if(data!=null){
						$("#formPrint").html(data);
	            	}
					if($("#_reportFormLedgerType").val()==2||$("#_reportFormLedgerType").val()==3){
	            		onLoadDelay();
		            }else{
						reloadIssue();
		            }
					$("#issueDialog").dialog("close");
			
					$("#printFormDialog").next().find(".ui-dialog-buttonset  button").eq(0).hide();
					//$.messageBox({message:"已经成功保存转办单!"});
					$("#formPrint").printArea();
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("提交数据时发生错误");
		  	}
		});
		},
		rules:{
			"turnForm.manager":{
				required:true,
				minlength:2,
				maxlength:50
			},
			"turnForm.reason":{
				required:true,
				minlength:2,
				maxlength:50
			},
			"turnForm.handleStartDate":{
				required:true
			},
			"turnForm.handleEndDate":{
				required:true
			},
			"turnForm.subOpinion":{
				required:true,
				minlength:2,
				maxlength:250
			},
			"turnForm.opinion":{
				required:true,
				minlength:2,
				maxlength:250
			}
		},
		messages:{
			"turnForm.manager":{
				required:"请输入责任人",
				minlength:$.format("责任人至少需要输入{0}个字符"),
				maxlength:$.format("责任人最多需要输入{0}个字符")
			},
			"turnForm.reason":{
			required:"请输入主要事项",
			minlength:$.format("主要事项至少需要输入{0}个字符"),
			maxlength:$.format("主要事项最多需要输入{0}个字符")
			},
			"turnForm.handleStartDate":{
				required:"请选择办理开始时间"
			},
			"turnForm.handleEndDate":{
				required:"请选择办理结束时间"
			},
			"turnForm.subOpinion":{
				required:"请输入转办单位分管领导意见",
				minlength:$.format("转办单位分管领导意见至少需要输入{0}个字符"),
				maxlength:$.format("转办单位分管领导意见最多需要输入{0}个字符")
			},
			"turnForm.opinion":{
				required:"请输入转办单位主要领导意见",
				minlength:$.format("转办单位主要领导意见至少需要输入{0}个字符"),
				maxlength:$.format("转办单位主要领导意见最多需要输入{0}个字符")
			}
	    }
	});
		
})
</script>