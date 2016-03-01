<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	List<LedgerFeedBack> feedbacks = (List) request
			.getAttribute("feedbacks");
	if (feedbacks != null && feedbacks.size() > 0) {
		request.setAttribute("feedback", feedbacks.get(0));
	}
%>
<div id="waterPrint" style="height:380px;overflow:hidden;overflow-y:auto;">
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
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=9>
        			<p>研</p><p>究</p><p>整</p><p>理</p><p>情</p><p>况</p>
        		</td>
        		<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" >建设地点（区域）或建设起点——讫点</td>
	            <td width="16%" style="text-align: left;font-Size:10px; height:20px;width:54%;white-space: nowrap;" colspan="3">${waterResource.fromAddress}<c:if test="${waterResource.fromAddress ne null &&waterResource.toAddress ne null}">——</c:if>${waterResource.toAddress}</td>
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">受益人口（人）</td>
	            <td width="10%" style="font-Size:10px; height:20px;width:10%;white-space: nowrap;">${waterResource.beneficiaryNumber}</td>
        	</tr>
        	
        	
        	<tr >
        		
        		<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" >工程名称</td>
	            <td width="36%" style="text-align: left;font-Size:10px; height:20px;width:36%;white-space: nowrap;">${waterResource.projectName}</td>
	           	<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" > 建设性质</td>
	            <td width="16%" id="_buildType" colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	           	
	             <pop:PropertyDictMultiCheckbox name="buildType"
					column="4" readOnly="true"
					domainName="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_BUILD_TYPE"/>
	         </td>
        	</tr>
        	
        	
        	<tr >
        		
        		<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" >计划投资(万元)</td>
	            <td width="16%" style="text-align: left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">${waterResource.plannedInvestment}</td>
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">自筹资金(万元)</td>
	            <td width="16%" style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">${waterResource.selfFund}</td>
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">缺口资金(万元)</td>
	            <td width="16%" style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">${waterResource.gapFund}</td>
        
        	</tr>
        	
        	
			<tr >
        		<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" rowspan="2">
        			<input type="checkbox" name="type" value="饮水工程"  disabled/>饮水工程
        		</td>
	            <td width="16%"  colspan="5" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" cal='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_PROJECT_SUB_TYPE" 
					defaultValue="${waterResource.projectSubCategory.id}" />'>
					
					工程类别：
					<input type="checkbox" name="_subType" value="农村饮水" disabled/>农村饮水
					<input type="checkbox" name="_subType" value="场镇饮水" disabled/> 场镇饮水
					<input type="checkbox" name="_subType" value="城市供水" disabled/> 城市供水
					<input type="checkbox" name="_subType" value="其他" disabled/>其他
	            </td>
        
        	</tr>
        	<tr >
        		
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	集中供水（处）</td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	           	 <div class="waterText" style="display:none">${waterResource.centralized}</div>
	            </td>
	            <td width="16%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	分散供水（日供水量：方）</td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	             <div class="waterText" style="display:none">${waterResource.scatter}</div></td>
        
        	</tr>
        	
        	
        	<tr >
        		<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
	        		<input type="checkbox" name="type" value="山坪塘" disabled/>山坪塘
	        		<input type="checkbox" name="type" value="石河堰" disabled/>石河堰
	        		<input type="checkbox" name="type" value="蓄水池" disabled/>蓄水池
        		</td>
        		
        		<td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	蓄水量(立方)</td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	           	 <div class="waterText" style="display:none"> ${waterResource.impoundage}</div>
	            </td>
	            <td width="16%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	数量（口、节）</td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	             	<div class="waterText" style="display:none">${waterResource.num}</div>
	             </td>
        		
        	</tr>
        	
        	
        	<tr >
        		<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
	        		<input type="checkbox" name="type" value="渠道" disabled/>渠道
        		</td>
        		
        		<td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	里程（公里）</td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	             	<div class="waterText" style="display:none">${waterResource.mileage}</div>
	            </td>
	            <td width="16%" colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	
	          	  工程类别：
	          	  干渠<input type="checkbox" name="_subType" value="干渠" disabled/> 
	          	   支渠<input type="checkbox" name="_subType" value="支渠" disabled/> 
	          	   斗毛渠<input type="checkbox" name="_subType" value="斗毛渠" disabled/> 
	          	    其他<input type="checkbox" name="_subType" value="其他" disabled/> 	
	            	
	            </td>
        		
        	</tr>
        	
        	<tr >
        		<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
	        		<input type="checkbox" name="type" value="中小河流治理" disabled/>中小河流治理
        		</td>
        		
        		<td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	里程（公里）</td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	             	<div class="waterText" style="display:none">${waterResource.mileage}</div>
	            </td>
	            <td width="16%" colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	
	          	  工程类别：
	          	  
	          	县管河流<input type="checkbox" name="_subType" value="县管河流" disabled/> 
	          	 乡管河流<input type="checkbox" name="_subType" value="乡管河流" disabled/> 
	            </td>
        		
        	</tr>
        	
        	<tr height="40">
        		<td height="40" width="16%" style="text-align: left;font-Size:10px; height:20px;width:16%;white-space: nowrap;" >
	        		<input type="checkbox" name="type" value="其他" disabled/>其他
        		</td>
        		
        		<td height="40" width="16%" colspan="5"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	 <div class="waterText" style="display:none">${waterResource.otherContent}</div>
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
var buildType ='${waterResource.buildType.id}';
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_PROJECT" 
	defaultValue="${waterResource.projectCategory.id}" />';
var subtype='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_PROJECT_SUB_TYPE" 
	defaultValue="${waterResource.projectSubCategory.id}" />';
	
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
	$("input[name='buildType']").each(function(){
        if($(this).val() ==buildType) 
           $(this).attr('checked',"checked");
   	}); 

	$("input[name='type']").each(function(i){
        if($(this).val() ==type){
           $(this).attr('checked',"checked");
           $(this).parent().parent().find("input[name='_subType']").each(function(){
       	   if($(this).val() ==subtype)
           	   $(this).attr('checked',"checked");
           })
           if(i==0){
        		$(this).parent().parent().next().find(".waterText").each(function(){
        			$(this).show();
                });
            }else{
		       	$(this).parent().parent().find(".waterText").each(function(){
		            	//$(this).text("");
		       		$(this).show();
		           })
		       }
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

