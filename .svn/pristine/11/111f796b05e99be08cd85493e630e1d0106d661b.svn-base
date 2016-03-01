<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="otherPrint" style="height:380px;overflow:hidden;overflow-y:auto;">
	<style>
	.tablelist{margin-top: 5px;width:96%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
	.tablelist td{height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
	.tablelist .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
	.tablelist .title{width:14%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
	.propertyMulSelect{overflow:hidden;}
	.propertyMulSelect li{float:left;list-style: outside none none;}
	</style>
	
	<table id="issueAccordingPrint" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    	<jsp:include page="${path}/account/peopleAspiration/printPeopleAspirationDlg.jsp"/>
        <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        	
        	<tr class="tr1">
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=12>
        			<p>研</p><p>究</p><p>整</p><p>理</p><p>情</p><p>况</p>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="3">建设地点（区域）或建设起点——讫点</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;" colspan="12">${other.fromAddress} <c:if test="${other.fromAddress ne null &&other.toAddress ne null}">——</c:if>${other.toAddress}</td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" colspan="3">受益人口（人）</td>
	            <td width="4%" style="font-Size:10px; height:20px;width:16%;white-space: nowrap;" colspan="6">${other.beneficiaryNumber}</td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >工程名称</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:40%;white-space: nowrap;" colspan="10"  >${other.projectName}</td>
	           	<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" > 建设性质</td>
	            <td width="4%" id="_buildType" colspan="10" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:40%;font-weight:750;white-space: nowrap; "  >
	           	 	<p>新建<input type="checkbox" name="buildType" value="新建" disabled/>
             	           改建<input type="checkbox" name="buildType" value="改建" disabled/>
               		 扩建<input type="checkbox" name="buildType" value="扩建" disabled/>
               		 维修<input type="checkbox" name="buildType" value="维修" disabled/>
                  	其他 <input type="checkbox" name="buildType" value="其他" disabled/></p>
                  	<p><div id="_otherText" style="display:none">${other.otherBuildTypeContent}</div></p>
	         </td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >计划投资(万元)</td>
	            <td width="4%" colspan="6" style="text-align: left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${other.plannedInvestment}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">自筹资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${other.selfFund}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">缺口资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${other.gapFund}</td>
        
        	</tr>
        	<tr height="160">
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" rowspan="2">
	        		<p>项</p><p>目</p><p>类</p><p>别</p>
        		</td>
	            <td width="4%" colspan="1" style="text-align:left;font-Size:16px font-family:'黑体'; height:20px;width:40%;font-weight:750;white-space: nowrap;" >
	          		  建设工程<input type="checkbox" name="type" value="建设工程" disabled/></td>
	            <td width="4%" colspan="22" style="text-align:left;font-Size:10px; height:20px;width:92%;white-space: nowrap;">
	           	 <div class="otherText" style="display:none">${other.scopeContent}</div>
	           	</td>
        
        	</tr>
        	
        	<tr height="160">
        		<td height="40" colspan="1" width="4%" style="text-align: left;font-Size:16px font-family:'黑体'; font-weight:750; height:20px;width:4%;white-space: nowrap;" >
	        		其它<input type="checkbox" name="type" value="其他" disabled/>
        		</td>
        		
        		<td height="40" width="4%" colspan="22"  style="text-align: left;font-Size:10px height:20px;width:92%;">
	            	 <div class="otherText" style="display:none">${other.otherContent}</div>
	            </td>
        		
        	</tr>
        	
   
    </table>
    <s:set name="feedbacks" value="#request.peopleAspirations.feedbacks"></s:set>
    <s:set name="issueDealLogs" value="#request.peopleAspirations.issueDealLogs"></s:set> 
 	 <jsp:include page="${path}/account/peopleAspiration/printWorkLogDlg.jsp"/>
   
   
    
    
</table>
</div>


<script type="text/javascript">
var createType='${peopleAspirations.createTableType.id}';
var gender='${peopleAspirations.gender.id}';
var appealHumanType='${peopleAspirations.appealHumanType.id}';
var position ='${peopleAspirations.position.id}';
var buildType ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_OTHER_BUILD_TYPE" 
	defaultValue="${other.buildType.id}" />';
	
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@OTHER_PROJECT" 
	defaultValue="${other.projectCategory.id}" />';
$(document).ready(function(){
	$("input[name='createType']").each(function(){
        if($(this).val() ==createType) 
           $(this).attr('checked',"checked");
   });
	$("input[name='appealHumanType']").each(function(){
        if($(this).val() ==appealHumanType) 
           $(this).attr('checked',"checked");
   });
	$("input[name='gender']").each(function(){
        if($(this).val() ==gender) 
           $(this).attr('checked',"checked");
   }); 
	$("input[name='position']").each(function(){
        if($(this).val() ==position) 
           $(this).attr('checked',"checked");
   });  
	$("input[name='buildType']").each(function(i){
        if($(this).val() ==buildType){
           $(this).attr('checked',"checked");
           if(i==4)$('#_otherText').show();
        }
   	}); 

	$("input[name='type']").each(function(i){
        if($(this).val() ==type){
           $(this).attr('checked',"checked");
         $(this).parent().parent().find('.otherText').each(function(){
        	 $(this).show();
         })
         
        }
        	
   	});  

	   
	$("#_createType li").each(function(){
		$(this).attr("style","width:50%;white-space: nowrap;text-align:left;display: list-item;list-style: none;");
	})
	$("#_gender li").each(function(){
		$(this).attr("style","width:25%;white-space: nowrap;text-align:left;text-indent:0px; list-item;list-style: none;");
	}) 
	$("#_position li").each(function(){
		$(this).attr("style","width:11%;white-space: nowrap;text-align:left;");
	}) 
	$("#_appealHumanType li").each(function(){
		$(this).attr("style","width:100%;white-space: nowrap;text-align:left;text-indent:0px;");
	}) 
	$("#_buildType li").each(function(){
		$(this).attr("style","width:20%;white-space: nowrap;text-align:left;text-indent:0px;");
	})
	$("ul").each(function(){
		$(this).attr("style","margin-left: 0; padding-left: 0;");
	})


	   
})
</script>

