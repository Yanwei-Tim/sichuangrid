<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<div class='container_24'>
	<form id="report_form" method="post" action="/threeRecords/assignForm/dispatch.action">
		<input type="hidden" name="mode" id="mode" value="edit" />
		<input type="hidden" name="assignForm.id" id="id" value="${assignForm.id}" />
		<input type="hidden" name="assignForm.stepId"  value="${assignForm.stepId}" />
		<input type="hidden" name="id"  value="${assignForm.stepId}" />
		<input type="hidden" id="_reportFormLedgerType" name="assignForm.ledgerType"  value="${assignForm.ledgerType}" />
		<input type="hidden" name="receivers" id="receivers" value="" />
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_6">
		<input type="text"  name="assignForm.serialNumber" id="serialNumber"  maxlength="30" value="${assignForm.serialNumber}" 
				readonly="readonly" class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">台账类型：</label>
	 	</div>
		<div class="grid_17">
			<ul class="propertyMulSelect">
	            <li><input type='checkbox'  name='type' disabled='disabled' <s:if test="assignForm.ledgerType!=3&&assignForm.ledgerType!=2">checked='checked'</s:if>/>民生工作台账   </li>
	            <li><input type='checkbox' name='type' disabled='disabled' <s:if test="assignForm.ledgerType==2">checked='checked'</s:if>/>困难群众工作台账  </li>
	            <li><input type='checkbox' name='type' disabled='disabled' <s:if test="assignForm.ledgerType==3">checked='checked'</s:if>/>稳定工作台账  </li>
            </ul>	
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">会议类型：</label>
	 	</div>
		<div class="grid_6">
			<select name="assignForm.type.id" id="type" class="form-txt {required:true,messages:{required:'请选择会议类型'}}" >
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@ASSGIN_MEETING_TYPE" defaultValue="${assignForm.type.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">会议期数：</label>
		</div>
		<div class="grid_6">
			<input type="text"  name="assignForm.periods" id="periods"  maxlength="30" value="${assignForm.periods}"  class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">主要事项：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="reason" name="assignForm.reason"  maxlength="50" class='form-txt' >${assignForm.reason}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">联席会议 议决内容摘要：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="handleContent" name="assignForm.handleContent"  maxlength="50" class='form-txt' >${assignForm.handleContent}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		<div class="grid_5 lable-right">        
			<label class="form-lbl">办理时限：</label>
	 	</div>
		<div class="grid_4">
			<input type="text" id="handleStartDate" name="assignForm.handleStartDate" class="form-txt {required:true,messages:{required:'请输入办理时限'}}"
			value="<s:date name="assignForm.handleStartDate" format="yyyy-MM-dd "/>" readonly />
		</div>
		<div class="grid_1 lable-right">        
			至
	 	</div>
		<div class="grid_4">
			<input type="text" id="handleEndDate" name="assignForm.handleEndDate" class="form-txt {required:true,messages:{required:'请输入办理时限'}}"
			value="<s:date name="assignForm.handleEndDate" format="yyyy-MM-dd "/>" readonly />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<c:forEach var="receiver" items="${assignForm.receivers}" varStatus="status">
	        <div class="grid_5 lable-right">
				<label class="form-lbl"> <c:if test="${receiver.isManage }">主办单位</c:if>
	       		<c:if test="${!receiver.isManage }">协办单位${status.index}</c:if></label>
			</div>
	       	<div class="grid_6">
			<input type="text"  name="orgName"  maxlength="30" value="${receiver.targetOrg.orgName}" 
				 class='form-txt' readonly />
			</div>	   
	       	<div class="grid_5 lable-right">
				<label class="form-lbl">责任人</label>
			</div>
			<div class="grid_6">
			<input type="text"  name="assignFormReceiver.manager${receiver.targetOrg.id}" data='${receiver.targetOrg.id}' class=" manager form-txt {required:true,messages:{required:'请输入负责人'}}"  maxlength="30" value="${receiver.manager}" class='form-txt' />
			</div>
          </c:forEach>
		
	</form>
</div>



<div id="formPrint" style="display:none">
	<div style="display:none">
	<style>
	.tablelist{margin-top: 5px;width:96%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
	.tablelist td{height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
	.tablelist .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
	.tablelist .title{width:14%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
	</style>
	<table width="96%" border="0" cellpadding="0" cellspacing="0">
       		<tr>
	          	<td class="tdhl" width="100%" colspan="6" align="right">   编号：( ${assignForm.serialNumber} )   号</td>
       		</tr>
        	<tr>
	          	<td colspan="6" style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;">
	          		中江县三本台账工作联席会议<u style="text-align: center;font-Size:20px;font-family:'黑体';height:20px;width:15%;	font-weight:700;text-decoration : underline">
	          			<s:if test="assignForm.ledgerType==2">困   难</s:if>
	          			<s:elseif test="assignForm.ledgerType==3">稳   定</s:elseif>
	          			<s:else>民   生</s:else>
	          		</u>信息交办单
	          	    <br><br>
	          	</td>
        	</tr>
        </table>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        	
         <tr class="tr1" height="30">
       		<td height="30" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
       		台账类型
       		</td>
       		<td colspan="3" style="text-align:left;font-Size:10px; height:20px;" width="36%">
            	<ul class="propertyMulSelect">
	            	<li style='display: list-item;list-style: none;'><input type='checkbox'  name='type' disabled='disabled' <s:if test="assignForm.ledgerType!=3&&assignForm.ledgerType!=2">checked='checked'</s:if>/>民生工作台账   </li>
	            	<li style='display: list-item;list-style: none;'><input type='checkbox' name='type' disabled='disabled' <s:if test="assignForm.ledgerType==2">checked='checked'</s:if>/>困难群众工作台账  </li>
	            	<li style='display: list-item;list-style: none;'><input type='checkbox' name='type' disabled='disabled' <s:if test="assignForm.ledgerType==3">checked='checked'</s:if>/>稳定工作台账  </li>
            	</ul>
            </td>
             <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >会议期数</td>
            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
             ${assignForm.periods}
            </td>
       	</tr>
       	<tr class="tr1" height="60">
       		<td height="60" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
       		主要事项
       		</td>
       		<td colspan="5" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
       			${assignForm.reason}
            </td>
	       	</tr>
	       	
	       		<tr class="tr1" height="40">
	       		<td height="40" style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
		       		<span>联席会议</span>
		       		<span>议决内容</span>
		       		<span>摘    要</span>
	       		</td>
	       		<td colspan="5" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	       			${assignForm.handleContent}
	            </td>
	       	</tr>
        
        	 <c:forEach var="receiver" items="${assignForm.receivers}" varStatus="status">
        		<tr class="tr1" >
	       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
	       		    <c:if test="${receiver.isManage }">主办单位</c:if>
	       		    <c:if test="${!receiver.isManage }">协办单位${status.index}</c:if>
	       		</td>
	            <td colspan="3" width="36%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;">
	           		${receiver.targetOrg.orgName}
	            </td>
	            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >责任人</td>
	            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
	             	${receiver.manager}
	            </td>
             </tr>
             </c:forEach>
             <c:forEach begin="${fn:length(assignForm.receivers)}" end="3" varStatus="status">
        		<tr class="tr1" >
		       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
		       		    协办单位${status.index}
		       		</td>
		            <td colspan="3"  width="36%" style="text-align: left;font-Size:10px; height:20px;width:36%;white-space: nowrap;">
		            </td>
		            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >责任人</td>
		            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
		            </td>
	            </tr>
        	</c:forEach>
        
	        <tr class="tr1">
	       		<td  style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
	       		办理时限
	       		</td>
	       		<td colspan="5" style="text-align:left;font-Size:10px; height:20px;word-break:break-all;word-wrap:break-word">
	       			<s:if test="assignForm.handleStartDate!=null"><s:date name="assignForm.handleStartDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>
	       			   —— 
	       			<s:if test="assignForm.handleEndDate!=null"><s:date name="assignForm.handleEndDate" format="yyyy年MM月dd日" /></s:if>
	          		<s:else>&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;日</s:else>	   
	            </td>
	       	</tr>
        
       		 <c:forEach var="receiver" items="${assignForm.receivers}" varStatus="status">
        		<tr class="tr1" >
	       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
	       		    <c:if test="${receiver.isManage }">责任单位接件时间</c:if>
	       		    <c:if test="${!receiver.isManage }">协办单位${status.index}接件时间</c:if>
	       		</td>
	            <td width="12%" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	            	<fmt:formatDate value="${receiver.receiveDate}" pattern="yyyy年MM月dd日"/>
	            </td>
	            <td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
	       		    <c:if test="${receiver.isManage }">责任单位联系人</c:if>
	       		    <c:if test="${!receiver.isManage }">协办单位联系人</c:if>
	       		</td>
	            <td width="12%" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	           		${receiver.name}
	            </td>
	            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >联系电话</td>
	            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
	             	${receiver.mobile}
	            </td>
             </tr>
             </c:forEach>
             <c:forEach begin="${fn:length(assignForm.receivers)}" end="3" varStatus="status">
	            <tr class="tr1" >
		       		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" width="20%" >
		       		    协办单位${status.index}接件时间
		       		</td>
		            <td width="12%" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
		            </td>
		            <td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" width="12%" >
		       		   	协办单位联系人
		       		</td>
		            <td width="12%" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
		            </td>
		            <td width="12%" style="text-align:left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;" >联系电话</td>
		            <td width="32%" style="text-align:left;font-Size:10px; height:20px;width:32%;white-space: nowrap;" >
		            </td>
             	</tr>
        	</c:forEach>
    </table>
    </div>
</div>


<script type="text/javascript">

$(document).ready(function(){
	$('#handleStartDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+30d',
	    minDate:'+0d',
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
	    maxDate:'+360d',
	    minDate:'+0d',
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
			var arr = new Array();
			var obj;
			var targetOrg;
			$(".manager").each(function(){
				obj=new Object();
				targetOrg={};
		        if($(this).val() !="") {
			        targetOrg.id=$(this).attr("data");
			        obj.targetOrg=targetOrg;
			        obj.manager=$(this).val();
		        	arr.push(obj);
			    }
		    });
		    $('#receivers').val(JSON.stringify(arr));

			$(form).ajaxSubmit({

				type: 'post',
		        url: '/threeRecords/assignForm/dispatch.action?'+$("#issueDealForm").serialize(), 	
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
					//$.messageBox({message:"已经成功保存交办单!"});
					$("#formPrint").printArea();
				},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("提交数据时发生错误");
		  	}
		});
		},
		rules:{
			"assignForm.periods":{
				digits:true,
				range:[1,9999999999]
			}
		},
		messages:{
			"assignForm.periods":{
			digits:'会议期数只能输入1到1,9999999999之间的整数',
			range:$.format('会议期数只能输入{0}到{1}之间的整数')
			}
	    }
	});


    
})




</script>