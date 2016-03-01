<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="environmentPrint" style="height:380px;overflow:hidden;overflow-y:auto;">
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
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:60%;white-space: nowrap;" colspan="15">${environment.fromAddress} <c:if test="${environment.fromAddress ne null &&environment.toAddress ne null}">——</c:if>${environment.toAddress}</td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" colspan="1">受益人口（人）</td>
	            <td width="4%" style="font-Size:10px; height:20px;width:16%;white-space: nowrap;" colspan="4">${environment.beneficiaryNumber}</td>
        	</tr>
        	
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" colspan="1">工程名称</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:60%;white-space: nowrap;" colspan="15">${environment.projectName}</td>
	            <td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:32%;font-weight:750;" colspan="8">
	            	建设性质：新建<input type="checkbox" name="buildType" value="新建" disabled/>
	            			 改建<input type="checkbox" name="buildType" value="改建" disabled/>
	            			  扩建<input type="checkbox" name="buildType" value="扩建" disabled/>
	            			   维修<input type="checkbox" name="buildType" value="维修" disabled/>
	            			   整治<input type="checkbox" name="buildType" value="整治" disabled/>
	            			    添置设备<input type="checkbox" name="buildType" value="添置设备" disabled/></td>
        	</tr>
        	
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" colspan="1">建设规模及建设内容</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:60%;white-space: nowrap;" colspan="15">${environment.content}</td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" colspan="1">
	            	治理数量				
	            </td>
	            <td width="4%" style="font-Size:10px; height:20px;width:16%;white-space: nowrap;" colspan="4">${environment.beneficiaryNumber}</td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >计划投资(万元)</td>
	            <td width="4%" colspan="6" style="text-align: left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${environment.plannedInvestment}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">自筹资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${environment.selfFund}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">缺口资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${environment.gapFund}</td>
        
        	</tr>
        	<tr >
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >
        			工业企业<input type="checkbox" name="type" value="工业企业" disabled/>
        		</td>
	            <td width="4%" colspan="22" style="text-align: left;font-Size:10px; height:20px;width:88%;">
	          	 治理类型：废水(吨/天)<input type="checkbox" name="_subType" value="废水" disabled/> 
	           	   废气(立方米）<input type="checkbox" name="_subType" value="废气" disabled/>
          		   噪声（分贝）<input type="checkbox" name="_subType" value="噪声" disabled/>
          		   固体废弃物（吨）<input type="checkbox" name="_subType" value="固体废弃物" disabled/>
          		    其他<input type="checkbox" name="_subType" value="其他" disabled/>
	           
	           </td>
        
        	</tr>
        	<tr >
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >
        			农村环保<input type="checkbox" name="type" value="农村环保" disabled/>
        		</td>
	            <td width="4%" colspan="22" style="text-align: left;font-Size:10px; height:20px;width:88%;">
			           治理类型：畜禽养殖污染(吨/天<input type="checkbox" name="_subType" value="畜禽养殖污染" disabled/> 
			           	   土壤污染(平方米)<input type="checkbox" name="_subType" value="土壤污染" disabled/> 
			           	     白色污染(平方米)<input type="checkbox" name="_subType" value="白色污染" disabled/> 
         				     河流污染(立方米）<input type="checkbox" name="_subType" value="河流污染" disabled/> 
          				   水库污染(立方米）<input type="checkbox" name="_subType" value="水库污染" disabled/> 
          				     其他<input type="checkbox" name="_subType" value="其他" disabled/> 
	           
	           </td>
        
        	</tr>
        	<tr >
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >
        		生活污染源<input type="checkbox" name="type" value="生活污染源" disabled/>
        		</td>
	            <td width="4%" colspan="22" style="text-align: left;font-Size:10px; height:20px;width:88%;">
				     治理类型：
				     生活废水(含地沟油)(吨/天)<input type="checkbox" name="_subType" value="生活废水" disabled/> 
				      垃圾处理(吨/天)<input type="checkbox" name="_subType" value="垃圾处理" disabled/> 
				       噪声(分贝)<input type="checkbox" name="_subType" value="噪声" disabled/> 
				       餐饮油烟<input type="checkbox" name="_subType" value="餐饮油烟" disabled/> 
				       其他<input type="checkbox" name="_subType" value="其他" disabled/> 
	           </td>
        
        	</tr>
        	<tr >
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >
        			电磁辐射<input type="checkbox" name="type" value="电磁辐射" disabled/>
        		</td>
	            <td width="4%" colspan="22" style="text-align: left;font-Size:10px; height:20px;width:88%;">
	       	  治理类型： 电力设施<input type="checkbox" name="_subType" value="电力设施" disabled/> 
	       	  		       通信网络<input type="checkbox" name="_subType" value="通信网络" disabled/> 
	       	  		        三类射线辐射<input type="checkbox" name="_subType" value="三类射线辐射" disabled/> 
	       	  		        其他<input type="checkbox" name="_subType" value="其他" disabled/> 
	           
	           </td>
        
        	</tr>
        	
        	<tr height="40">
        		<td height="40" colspan="2" width="4%" style="text-align: left;font-Size:16px font-family:'黑体'; font-weight:750; height:20px;width:8%;white-space: nowrap;" >
	        		<p>其他<input type="checkbox" name="type" value="其他" disabled/></p>
        		</td>
        		
        		<td height="40" width="4%" colspan="22"  style="text-align: left;font-Size:10px height:20px;width:8%;white-space: nowrap;">
	            	 <div id="environmentOtherText" style="display:none">${environment.otherContent}</div>
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
var buildType ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_BUILD_TYPE" 
	defaultValue="${environment.buildType.id}" />';
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_PROJECT" 
	defaultValue="${environment.projectCategory.id}" />';
	
var subtype='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_PROJECT_SUB" 
	defaultValue="${environment.projectSubCategory.id}" />';

	
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
           if(i==4){
               $('#environmentOtherText').show();
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

