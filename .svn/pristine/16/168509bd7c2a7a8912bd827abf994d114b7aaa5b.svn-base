<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="medicalPrint" style="height:380px;overflow:hidden;overflow-y:auto;">
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
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="3">建设地点（区域）或建设起点——讫点</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;" colspan="12">${medical.fromAddress} <c:if test="${medical.fromAddress ne null &&medical.toAddress ne null}">——</c:if>${medical.toAddress}</td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" colspan="3">受益人口（人）</td>
	            <td width="4%" style="font-Size:10px; height:20px;width:16%;white-space: nowrap;" colspan="6">${medical.beneficiaryNumber}</td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" > 建设性质</td>
	            <td width="4%" id="_buildType" colspan="10" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:40%;font-weight:750;white-space: nowrap;">
					 新建<input type="checkbox" name="buildType" value="新建" disabled/>
             	           改建<input type="checkbox" name="buildType" value="改建" disabled/>
               		 扩建<input type="checkbox" name="buildType" value="扩建" disabled/>
               		 维修<input type="checkbox" name="buildType" value="维修" disabled/>
                  	添置设备<input type="checkbox" name="buildType" value="添置设备" disabled/>
                <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >建设工程量(平方米)</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:12%;white-space: nowrap;" colspan="3">${medical.buildArea}</td>
	           	<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >设备(套、台)</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:20%;white-space: nowrap;" colspan="5">${medical.equipment}</td>
	           
	         </td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >计划投资(万元)</td>
	            <td width="4%" colspan="6" style="text-align: left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${medical.plannedInvestment}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">自筹资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${medical.selfFund}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">缺口资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${medical.gapFund}</td>
        
        	</tr>
        	
       		 <tr >
        		
        		<td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" rowspan="6">
        		<p>项</p><p>目</p><p>类</p><p>别</p></td>
	            <td width="4%" colspan="5" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;white-space: nowrap;" >
	           		 食品卫生<input type="checkbox" name="type" value="食品卫生" disabled/>
	            </td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:76%;font-weight:750;white-space: nowrap;" colspan="18">
	            
           		              种养殖<input type="checkbox" name="_subType" value="养殖" disabled/>
             	              生产<input type="checkbox" name="_subType" value="生产" disabled/>
               		  流通<input type="checkbox" name="_subType" value="流通" disabled/>
                	  消费<input type="checkbox" name="_subType" value="消费" disabled/> 
                  	 其他<input type="checkbox" name="_subType" value="其他" disabled/>
        	</tr>
        	<tr >
	            <td width="4%" colspan="5" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;white-space: nowrap;" >
	           		 公共卫生服务<input type="checkbox" name="type" value="公共卫生服务" disabled/>
	            </td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:76%;font-weight:750;white-space: nowrap;" colspan="18">
           		            疾病预防<input type="checkbox" name="_subType" value="疾病预防" disabled/>
             	             妇幼保健<input type="checkbox" name="_subType" value="妇幼保健" disabled/>
               		 卫生监督<input type="checkbox" name="_subType" value="卫生监督" disabled/>
                	 卫生应急<input type="checkbox" name="_subType" value="卫生应急" disabled/> 
                  	 精神卫生<input type="checkbox" name="_subType" value="精神卫生" disabled/>
                  	其他<input type="checkbox" name="_subType" value="其他" disabled/>
                </td>
        	</tr>
        	
        	<tr >
	            <td width="4%" colspan="5" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;white-space: nowrap;" >
	           		 医疗服务<input type="checkbox" name="type" value="医疗服务" disabled/>
	            </td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:76%;font-weight:750;white-space: nowrap;" colspan="18">
           		           服务质量<input type="checkbox" name="_subType" value="服务质量" disabled/>
             	            服务态度<input type="checkbox" name="_subType" value="服务态度" disabled/>
               		 医德医风<input type="checkbox" name="_subType" value="医德医风" disabled/>
                  	其他<input type="checkbox" name="_subType" value="其他" disabled/>
                </td>
        	</tr>
        	<tr >
	            <td width="4%" colspan="5" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;white-space: nowrap;" >
	           		 新农合<input type="checkbox" name="type" value="新农合" disabled/>
	            </td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:76%;font-weight:750;white-space: nowrap;" colspan="18">
           		           筹资<input type="checkbox" name="_subType" value="筹资" disabled/>
             	           报销<input type="checkbox" name="_subType" value="报销" disabled/>
               		 监管<input type="checkbox" name="_subType" value="监管" disabled/>
               		 公示<input type="checkbox" name="_subType" value="公示" disabled/>
                  	其他<input type="checkbox" name="_subType" value="其他" disabled/>
                </td>
        	</tr>
        	<tr >
	            <td width="4%" colspan="5" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;white-space: nowrap;" >
	           		 服务能力建设<input type="checkbox" name="type" value="服务能力建设" disabled/>
	            </td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:76%;font-weight:750;white-space: nowrap;" colspan="18">
           		           队伍建设<input type="checkbox" name="_subType" value="队伍建设" disabled/>
             	           医疗设备更新<input type="checkbox" name="_subType" value="医疗设备更新" disabled/>
               		 基础设施<input type="checkbox" name="_subType" value="基础设施" disabled/>
                  	其他<input type="checkbox" name="_subType" value="其他" disabled/>
                </td>
        	</tr>
        	
        	<tr height="40">
        		<td height="40" colspan="2" width="4%" style="text-align: left;font-Size:16px font-family:'黑体'; font-weight:750; height:20px;width:8%;white-space: nowrap;" >
	        		<p>其他<input type="checkbox" name="type" value="其他" disabled/></p>
        		</td>
        		
        		<td height="40" width="4%" colspan="22"  style="text-align: left;font-Size:10px height:20px;width:8%;white-space: nowrap;">
	            	 <div class="medicalText" style="display:none">${medical.otherContent}</div>
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
var buildType ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_BUILD_TYPE" 
	defaultValue="${medical.buildType.id}" />';
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_PROJECT" 
	defaultValue="${medical.projectCategory.id}" />';
	
var subtype='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_PROJECT_SUB" 
	defaultValue="${medical.projectSubCategory.id}" />';

	
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
	       	   if( subtype.indexOf($(this).val())>-1&&subtype!="")
	           	   $(this).attr('checked',"checked");
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

