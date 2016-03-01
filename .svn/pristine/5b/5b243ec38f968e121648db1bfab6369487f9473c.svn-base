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
<div id="trafficPrint" style="height:380px;overflow:hidden;overflow-y:auto;">
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
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="8%" rowspan=14>
        			<p>研</p><p>究</p><p>整</p><p>理</p><p>情</p><p>况</p>
        		</td>
        		<td width="8%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" >建设地点（区域）或建设起点——讫点</td>
	            <td width="16%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;" colspan="3">${traffic.fromAddress}<c:if test="${traffic.fromAddress ne null &&traffic.toAddress ne null}">——</c:if>${traffic.toAddress}</td>
	            <td width="8%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >受益人口（人）</td>
	            <td width="10%" style="font-Size:10px; height:20px;width:10%;white-space: nowrap;">${traffic.beneficiaryNumber}</td>
        	</tr>
        	
        	<tr >
        		
        		<td width="8%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" >工程名称</td>
	            <td width="16%" colspan="2" style="text-align: left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">${traffic.projectName}</td>
	           	<td width="16%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" > 建设性质</td>
	            <td width="16%" id="_buildType" colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            <pop:PropertyDictMultiCheckbox name="buildType"
					column="4" readOnly="true"
					domainName="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_BUILD_TYPE"/>
	         </td>
        	</tr>
        	
        	
        	<tr >
        		
        		<td width="8%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" >计划投资(万元)</td>
	            <td width="16%" style="text-align: left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">${traffic.plannedInvestment}</td>
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">自筹资金(万元)</td>
	            <td width="16%" style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">${traffic.selfFund}</td>
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">缺口资金(万元)</td>
	            <td width="16%" style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">${traffic.gapFund}</td>
        
        	</tr>
        	
        	
			<tr >
        		<td width="8%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:8%; " rowspan="2">
        			<input type="checkbox" name="type" value="道路建设"  disabled/>道路建设
        			<input type="checkbox" name="type" value="道路维护"  disabled/>道路维护
        			
        		</td>
        		
	            <td width="16%"  colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
					道路类别:
	            	国道 <input type="checkbox" name="roadCategory" value="国道"  disabled/>  
	            	省道 <input type="checkbox" name="roadCategory" value="省道"  disabled/>
	            	 县道 <input type="checkbox" name="roadCategory" value="县道"  disabled/>
         			乡道 <input type="checkbox" name="roadCategory" value="乡道"  disabled/>
         			村道 <input type="checkbox" name="roadCategory" value="村道"  disabled/>
         			 社道 <input type="checkbox" name="roadCategory" value="社道"  disabled/>
         			 城区道路 <input type="checkbox" name="roadCategory" value="城区道路"  disabled/>
         			
					
	            </td>
	             <td width="16%"  colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
					
					
					路面类型：  
					 水泥路面 <input type="checkbox" name="roadSurfaceCategory" value="水泥路面"  disabled/>
				          沥青路面 <input type="checkbox" name="roadSurfaceCategory" value="沥青路面"  disabled/>
				         泥碎路面 <input type="checkbox" name="roadSurfaceCategory" value="泥碎路面"  disabled/>
					
					
	            </td>
        
        	</tr>
        	
        	<tr >
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	里程（公里）</td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	            	<div class="trafficText" style="display:none">${traffic.mileage}</div>
	            </td>
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	宽（米）</td>
	            <td width="16%" colspan="2" style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	           		<div class="trafficText" style="display:none">${traffic.wide}</div></td>
        
        	</tr>
        	
        	
        	<tr >
        		<td width="8%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;width:16%;white-space: nowrap;">
        			<input type="checkbox" name="type" value="桥梁建设"  disabled/>桥梁建设
        			<input type="checkbox" name="type" value="桥梁维护"  disabled/>桥梁维护
        			
        		</td>
        		
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	桥长（米）</td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	            	<div class="trafficText" style="display:none">${traffic.rodeLength}</div>
	            </td>
	            <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	桥宽（米）</td>
	            <td width="16%" colspan="2" style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	           		<div class="trafficText" style="display:none"> ${traffic.rodeWide}</div>
	            </td>
       
        
        	</tr>
        	
        	
        	
        	
        	<tr >
        		<td width="8%" rowspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;width:16%;white-space: nowrap;" >
        			<input type="checkbox" name="type" value="安保工程"  disabled/>安保工程
        		</td>
        		
	           <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	里程（公里）</td>
	            <td width="16%" style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	           		<div class="trafficText" style="display:none"> ${traffic.mileage}</div>
	           </td>
				
	          
	          
	             <td width="16%"  colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
					          
				          安保设施类型：
				          防护栏 <input type="checkbox" name="securityCategory" value="防护栏"  disabled/>
				          防撞墩 <input type="checkbox" name="securityCategory" value="防撞墩"  disabled/>
				          减速带 <input type="checkbox" name="securityCategory" value="减速带"  disabled/>
				          标志标牌 <input type="checkbox" name="securityCategory" value="标志标牌"  disabled/>
				          其它 <input type="checkbox" name="securityCategory" value="其它"  disabled/>
					
	            </td>
        
        	</tr>
        	
        	<tr >
        		
	           <td width="16%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	道路类别:
	            	国道 <input type="checkbox" name="roadCategory" value="国道"  disabled/>  
	            	省道 <input type="checkbox" name="roadCategory" value="省道"  disabled/>
	            	 县道 <input type="checkbox" name="roadCategory" value="县道"  disabled/>
         			乡道 <input type="checkbox" name="roadCategory" value="乡道"  disabled/>
         			村道 <input type="checkbox" name="roadCategory" value="村道"  disabled/>
         			 社道 <input type="checkbox" name="roadCategory" value="社道"  disabled/>
	           </td>
	           <td width="16%"  colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
					          
				          路面类型类型：
				 
				          水泥路面 <input type="checkbox" name="roadSurfaceCategory" value="水泥路面"  disabled/>
				          沥青路面 <input type="checkbox" name="roadSurfaceCategory" value="沥青路面"  disabled/>
				         泥碎路面 <input type="checkbox" name="roadSurfaceCategory" value="泥碎路面"  disabled/>
					
	            </td>
        
        	</tr>
        	
        	
        	<tr >
        		<td width="8%" rowspan="5" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;width:16%;white-space: nowrap;" >
        			<input type="checkbox" name="type" value="客运"  disabled/>客运
        		</td>
        		
	           <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	班线客运 <input type="checkbox" name="projectSubCategory" value="班线客运"  disabled/> </td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	      		      农村客运 <input type="checkbox" name="passengerCategory" value="农村客运"  disabled/>
	      		       县际客运 <input type="checkbox" name="passengerCategory" value="县际客运"  disabled/>
				      市际客运 <input type="checkbox" name="passengerCategory" value="市际客运"  disabled/>
				      省际客运 <input type="checkbox" name="passengerCategory" value="省际客运"  disabled/>
	            
	            </td>
				
	          
	          
	             <td width="16%"  colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
					          
				          线路数量 <input type="checkbox" name="contentCategory" value="线路数量"  disabled/>
				          收费情况 <input type="checkbox" name="contentCategory" value="收费情况"  disabled/>
				          服务质量 <input type="checkbox" name="contentCategory" value="服务质量"  disabled/>
				          其它 <input type="checkbox" name="contentCategory" value="其它"  disabled/>
					
	            </td>
        
        	</tr>
        	
        	<tr >
        		
	           <td width="16%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;" rowspan="2">
	            	城市公共交通 <input type="checkbox" name="projectSubCategory" value="城市公共交通"  disabled/> </td>
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	      		      公交汽车 <input type="checkbox" name="publicTransportCategory" value="公交汽车"  disabled/>
	            
	            </td>
				
	          
	          
	             <td width="16%"  colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
					 
				          覆盖区域 <input type="checkbox" name="contentCategory" value="覆盖区域"  disabled/>
				          站台数量 <input type="checkbox" name="contentCategory" value="站台数量"  disabled/>
				          收费情况 <input type="checkbox" name="contentCategory" value="收费情况"  disabled/>
				          服务质量 <input type="checkbox" name="contentCategory" value="服务质量"  disabled/>
				          其他 <input type="checkbox" name="contentCategory" value="其他"  disabled/>
					
	            </td>
        
        	</tr>
        	
        	
        	<tr >
        		
	            <td width="16%"  style="text-align:left;font-Size:10px; height:20px;width:16%;white-space: nowrap;">
	      		    出租汽车<input type="checkbox" name="publicTransportCategory" value="出租汽车"  disabled/>
	            
	            </td>
	          
	             <td width="16%"  colspan="3" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;width:16%;white-space: nowrap;" >
					
				           车辆数量 <input type="checkbox" name="contentCategory" value="车辆数量"  disabled/>
				           收费情况 <input type="checkbox" name="contentCategory" value="收费情况"  disabled/>
				           服务质量 <input type="checkbox" name="contentCategory" value="服务质量"  disabled/>
				           其他 <input type="checkbox" name="contentCategory" value="其他"  disabled/>
					
	            </td>
        
        	</tr>
        	
        	
        	
        	<tr >
        		
	           <td width="12%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;" rowspan="2">
	            	客运站管理<input type="checkbox" name="projectSubCategory" value="客运站管理"  disabled/></br>
					客运站建设<input type="checkbox" name="projectSubCategory" value="客运站建设"  disabled/></td>
	            <td width="12%"  style="text-align:left;font-Size:10px; height:20px;width:12%;" rowspan="2">
	      		     <p>招呼站<input type="checkbox" name="passengerManageCategory" value="招呼站"  disabled/></p>
				     <p>客运站<input type="checkbox" name="passengerManageCategory" value="客运站"  disabled/></p>
	            </td>
				<td width="12%" style="text-align:left;font-Size:10px; height:20px;width:12%;" rowspan="2">
	      		  客运站等级
	      		    <p> 一级<input type="checkbox" name="passengerLevelCategory" value="一级"  disabled/>
	      		                      二级<input type="checkbox" name="passengerLevelCategory" value="二级"  disabled/>
	      		    </p>
					<p> 三级<input type="checkbox" name="passengerLevelCategory" value="三级"  disabled/>
					          四级<input type="checkbox" name="passengerLevelCategory" value="四级"  disabled/>
                                                                简易<input type="checkbox" name="passengerLevelCategory" value="简易"  disabled/>
                    </p>
	            
	            </td>
	            
	            <td colspan="2" width="12%"   style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;width:12%;" >
					  安全管理<input type="checkbox" name="contentCategory" value="安全管理"  disabled/>
					  收费情况<input type="checkbox" name="contentCategory" value="收费情况"  disabled/>
					  服务质量<input type="checkbox" name="contentCategory" value="服务质量"  disabled/>
 					 其他<input type="checkbox" name="contentCategory" value="其他"  disabled/>
	            </td>
        
        	</tr>
        	<tr>
				<td colspan="2" width="12%"   style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;width:12%;white-space: nowrap;" >
					 备注：${traffic.remark }
					
	            </td>
        	
        	</tr>
        	
        	<tr height="40">
        		<td height="40" width="16%" style="text-align: left;font-Size:10px; height:20px;width:16%;white-space: nowrap;" >
	        		<input type="checkbox" name="type" value="其他" disabled/>其他
        		</td>
        		
        		<td height="40" width="16%"colspan="5"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	            	<div  class="trafficText"  style="display:none">${traffic.otherContent}</div>
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
var buildType ='${traffic.buildType.id}';
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PROJECT" 
	defaultValue="${traffic.projectCategory.id}" />';
var roadCategory ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_ROAD" 
	defaultValue="${traffic.roadCategory.id}" />';
var roadSurfaceCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_ROADSURFACE" 
	defaultValue="${traffic.roadSurfaceCategory.id}" />';
var	securityCategory ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_SECURITY" 
	defaultValue="${traffic.securityCategory.id}" />';
var projectSubCategory ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PASSTYPE" 
	defaultValue="${traffic.projectSubCategory.id}" />';
var passengerCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PASSENGER" 
	defaultValue="${traffic.passengerCategory.id}" />';

var contentCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_SECURITY_SERVICE" 
	defaultValue="${traffic.contentCategory.id}" />';
var publicTransportCategory ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PUBLIC_TRANSPORT" 
	defaultValue="${traffic.publicTransportCategory.id}" />';
var passengerLevelCategory ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_LEVEL" 
	defaultValue="${traffic.passengerLevelCategory.id}" />';
var passengerManageCategory ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PASSENGER_MANAGE" 
	defaultValue="${traffic.passengerManageCategory.id}" />'	;
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
           $(this).parent().parent().find("input[name='roadCategory']").each(function(){
       	   if($(this).val() ==roadCategory)
           	   $(this).attr('checked',"checked");
           })
		   $(this).parent().parent().find("input[name='roadSurfaceCategory']").each(function(){
       	   if($(this).val().indexOf(roadSurfaceCategory)>-1&&roadSurfaceCategory!="")
           	   $(this).attr('checked',"checked");
           })
           $(this).parent().parent().find("input[name='securityCategory']").each(function(){
       	   if($(this).val().indexOf(securityCategory)>-1&&securityCategory!="")
           	   $(this).attr('checked',"checked");
           })
           $(this).parent().parent().next().find("input[name='roadCategory']").each(function(){
       	   if($(this).val() ==roadCategory)
           	   $(this).attr('checked',"checked");
           })
           
           $("input[name='passengerLevelCategory']").each(function(){
       	   if($(this).val() ==passengerLevelCategory)
           	   $(this).attr('checked',"checked");
           })
            $("input[name='passengerManageCategory']").each(function(){
       	   if($(this).val() ==passengerManageCategory)
           	   $(this).attr('checked',"checked");
           })
           
           
		   $(this).parent().parent().next().find("input[name='roadSurfaceCategory']").each(function(){
       	   if($(this).val().indexOf(roadSurfaceCategory)>-1&&roadSurfaceCategory!="")
           	   $(this).attr('checked',"checked");
           })
           if(i==5){
	           $("input[name='projectSubCategory']").each(function(selected){
		       	   if($(this).val().indexOf(projectSubCategory)>-1&&projectSubCategory!=""){
		           	   $(this).attr('checked',"checked");
		           	 if(selected==1){
		           		  $("input[name='publicTransportCategory']").each(function(){
		           		  if($(this).val()==publicTransportCategory){
		  	           	     $(this).attr('checked',"checked");
		           			 $(this).parent().parent().find("input[name='contentCategory']").each(function(){
		     	          	 if($(this).val().indexOf(contentCategory)>-1&&contentCategory!="")
		     	              	 $(this).attr('checked',"checked");
		     	              })
		           		 }
		               	})
		           	   }else{
			        	   $(this).parent().parent().find("input[name='contentCategory']").each(function(){
				          	   if($(this).val().indexOf(contentCategory)>-1&&contentCategory!="")
				              	   $(this).attr('checked',"checked");
				              }) 
				       }
		           	   
		       	   }
	           })
           }
           $("input[name='passengerCategory']").each(function(){
       	   if($(this).val()==passengerCategory)
           	   $(this).attr('checked',"checked");
           })
           
           if(i==0||i==1){
			   $(this).parent().parent().next().find(".trafficText").each(function(){
	         		$(this).show();
	           });
           }
           else{
        	   $(this).parent().parent().find(".trafficText").each(function(){
             		$(this).show();
               });
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

