<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="agriculturePrint" style="height:380px;overflow:hidden;overflow-y:auto;">
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
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=10>
        			<p>研</p><p>究</p><p>整</p><p>理</p><p>情</p><p>况</p>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="3">建设地点（区域）或建设起点——讫点</td>
	            <td width="4%" style="text-align: left;font-Size:16px; height:20px;width:36%;white-space: nowrap;" colspan="9">${agriculture.fromAddress} <c:if test="${agriculture.fromAddress ne null &&agriculture.toAddress ne null}">——</c:if>${agriculture.toAddress}</td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" colspan="1">受益人口（人）</td>
	            <td width="4%" style="font-Size:10px; height:20px;width:32%;white-space: nowrap;" colspan="8">${agriculture.beneficiaryNumber}</td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >工程名称</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:40%;white-space: nowrap;" colspan="10">${agriculture.projectName}</td>
	           	<td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" > 建设性质</td>
	            <td width="4%" id="_buildType" colspan="10" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:40%;font-weight:750;white-space: nowrap;">
	              	新建<input type="checkbox" name="buildType" value="新建" disabled/>
             	           改建<input type="checkbox" name="buildType" value="改建" disabled/>
               		 扩建<input type="checkbox" name="buildType" value="扩建" disabled/>
               		 维修<input type="checkbox" name="buildType" value="维修" disabled/>
	         </td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >计划投资(万元)</td>
	            <td width="4%" colspan="4" style="text-align: left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">${agriculture.plannedInvestment}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">自筹资金(万元)</td>
	            <td width="4%" colspan="4" style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">${agriculture.selfFund}</td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">缺口资金(万元)</td>
	            <td width="4%" colspan="7" style="text-align:left;font-Size:10px; height:20px;width:28%;white-space: nowrap;">${agriculture.gapFund}</td>
        
        	</tr>
        	
        	
        	
        	 <tr >
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" rowspan="9">
        		<p>项</p><p>目</p><p>类</p><p>别</p></td>
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	             	农业产业化<input type="checkbox" name="type" value="农业产业化" disabled/>
	            </td>
	            <td width="4%" colspan="10" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:40%;font-weight:750;">
	            
			               粮食生产<input type="checkbox" name="_subType" value="粮食生产" disabled/>
				     油料生产<input type="checkbox" name="_subType" value="油料生产" disabled/>
				     食用菌<input type="checkbox" name="_subType" value="食用菌" disabled/>
				     大棚西瓜<input type="checkbox" name="_subType" value="大棚西瓜" disabled/>
				     设施蔬菜<input type="checkbox" name="_subType" value="设施蔬菜" disabled/>
				     中药材<input type="checkbox" name="_subType" value="中药材" disabled/>
				     干果生产<input type="checkbox" name="_subType" value="干果生产" disabled/>
				     水果生产<input type="checkbox" name="_subType" value="水果生产" disabled/>
				     其他<input type="checkbox" name="_subType" value="其他" disabled/>   
				</td>
				
	           <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
			          规模(亩、袋、平方米)
               </td>
	            <td width="4%" colspan="8" style="text-align: left;font-Size:10px height:20px;width:32%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.scopeNumber}</div>
	            </td>
        	</tr>
        	<tr>
        	 <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	             	田间工程<input type="checkbox" name="type" value="田间工程" disabled/>
	            </td>
	            <td width="4%" colspan="10" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:40%;font-weight:750;">
	            
			               排灌沟渠<input type="checkbox" name="_subType" value="排灌沟渠" disabled/>
			              作业道(60厘米至200厘米)<input type="checkbox" name="_subType" value="作业道" disabled/>
				     塘堰防渗<input type="checkbox" name="_subType" value="塘堰防渗" disabled/>
				     机耕道(200厘米至300厘米)<input type="checkbox" name="_subType" value="机耕道" disabled/>
				     蓄水池<input type="checkbox" name="_subType" value="蓄水池" disabled/>
				     其他<input type="checkbox" name="_subType" value="其他" disabled/>   
				</td>
				
	           <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
			          工程量(亩、袋、平方米)
               </td>
	            <td width="4%" colspan="8" style="text-align: left;font-Size:10px height:20px;width:32%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.quantities}</div>
	            </td>
        	</tr>
        	
        	<tr>
        	 <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" rowspan="2">
	             	电力提灌站<input type="checkbox" name="type" value="电力提灌站" disabled/>
	            </td>
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	             	电力提灌站<input type="checkbox" name="subType_" value="电力提灌站" disabled/>
	            </td>
	             <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	             	   数量（处）
	            </td>
	             <td width="4%" colspan="5" style="text-align: left;font-Size:10px height:20px;width:20%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.num}</div>
	            </td>
	         
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
	            
			            装机容量（千瓦）
				</td>
	            <td width="4%" colspan="9" style="text-align: left;font-Size:10px height:20px;width:36%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.capacity}</div>
	            </td>
        	</tr>
        	
        	<tr>
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	             	机沉井<input type="checkbox" name="subType_" value="机沉井" disabled/>
	            </td>
	             <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	             	   数量（处）
	            </td>
	             <td width="4%" colspan="5" style="text-align: left;font-Size:10px height:20px;width:20%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.num}</div>
	            </td>
	         
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
			            出水量（方/小时）
				</td>
	            <td width="4%" colspan="9" style="text-align: left;font-Size:10px height:20px;width:36%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.waterYield}</div>
	            </td>
        	</tr>
        	
        	
        	
        	<tr>
        	 <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" rowspan="2">
	             	农业培训<input type="checkbox" name="type" value="农业培训" disabled/>
	            </td>
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	             	农业培训<input type="checkbox" name="subType_" value="农业培训" disabled/>
	            </td>
	             <td width="4%" colspan="4" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;" >
	             	农技<input type="checkbox" name="_subType" value="农技" disabled/>
	             	农经<input type="checkbox" name="_subType" value="农经" disabled/>
	             	阳光工程<input type="checkbox" name="_subType" value="阳光工程" disabled/>
	             	其他<input type="checkbox" name="_subType" value="其他" disabled/>
	            </td>
	            
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	             	培训次数
	            </td>
	             <td width="4%" colspan="3" style="text-align: left;font-Size:10px height:20px;width:12%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.trainNumber}</div>
	            </td>
	         
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;">
			            培训人数
				</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:10px height:20px;width:28%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.trainPeopleNumber}</div>
	            </td>
        	</tr>
        	
        	<tr>
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	             	农机培训<input type="checkbox" name="subType_" value="农机培训" disabled/>
	            </td>
	             <td width="4%" colspan="4" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;" >
	             	拖拉机驾驶员<input type="checkbox" name="machineCategory" value="拖拉机驾驶员" disabled/>
	             	其他操作<input type="checkbox" name="machineCategory" value="其他操作" disabled/>
	             	其他<input type="checkbox" name="machineCategory" value="其他" disabled/>
	            </td>
	            
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	             	  培训次数
	            </td>
	             <td width="4%" colspan="3" style="text-align: left;font-Size:10px height:20px;width:12%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.trainNumber}</div>
	            </td>
	         
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;">
			            培训人数
				</td>
	            <td width="4%" colspan="7" style="text-align: left;font-Size:10px height:20px;width:28%;white-space: nowrap;">
					 <div class="agricultureText" style="display:none">${agriculture.trainPeopleNumber}</div>
	            </td>
        	</tr>
        	
        
        	
        	<tr height="40">
        		<td height="40" colspan="2" width="4%" style="text-align: left;font-Size:16px font-family:'黑体'; font-weight:750; height:20px;width:8%;white-space: nowrap;" >
	        		其它<input type="checkbox" name="type" value="其它" disabled/>
        		</td>
        		
        		<td height="40" width="4%" colspan="22"  style="text-align: left;font-Size:10px height:20px;width:8%;white-space: nowrap;">
	            	 <div class="agricultureText" style="display:none">${agriculture.otherContent}</div>
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
var buildType ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT_BUILD_TYPE" 
	defaultValue="${agriculture.buildType.id}" />';
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT" 
	defaultValue="${agriculture.projectCategory.id}" />';
var subtype='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT_SUB" 
	defaultValue="${agriculture.projectSubCategory.id}" />';
var machineCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_MACHINERY_TRAIN" 
	defaultValue="${agriculture.machineCategory.id}" />';
var farmCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_FARMING_TRAIN" 
	defaultValue="${agriculture.farmCategory.id}" />';	
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
           if(i==0||i==1){
        	   $(this).parent().parent().find(".agricultureText").each(function(){
     			  $(this).show();
                })
               $(this).parent().parent().find("input[name='_subType']").each(function(){
     			 if($(this).val() ==subtype){
     		         $(this).attr('checked',"checked");
     			 }
                })
           }else if(i==2||i==3){
        	  $("input[name='subType_']").each(function(){
       			 if($(this).val() ==subtype){
       		         $(this).attr('checked',"checked");
	       		     $(this).parent().parent().find(".agricultureText").each(function(){
	       				$(this).show();
	       	         })
       			 }
               })
        	   
           }else{
	           $(this).parent().parent().find(".agricultureText").each(function(){
				  $(this).show();
	           })
           }
           $("input[name='machineCategory']").each(function(){
    		  if($(this).val() ==machineCategory){
    		     $(this).attr('checked',"checked");
    		  }
           })
 		   $("input[name='farmCategory']").each(function(){
    		   if($(this).val() ==farmCategory){
    		      $(this).attr('checked',"checked");
    		   }
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

