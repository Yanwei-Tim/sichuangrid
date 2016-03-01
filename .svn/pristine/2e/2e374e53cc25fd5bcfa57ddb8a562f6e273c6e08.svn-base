<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="townPrint" style="height:380px;overflow:hidden;overflow-y:auto;">
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
        		<td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >工程名称</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:60%;white-space: nowrap;" colspan="15">${town.projectName}</td>
	          <td width="4%" id="_buildType" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
	         	建设里程(公里)
	         </td>
	         <td  width="4%" colspan="4"  style="text-align: left;font-Size:10px height:20px;width16%;white-space: nowrap;">
	            	 <div class="townText" style="display:none">${town.mileage}</div>
	            </td>
        	</tr>
        	<tr class="tr1">
	           	<td width="4%" colspan="16"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:64%;font-weight:750;" > 建设性质：
	              	新建<input type="checkbox" name="buildType" value="新建" disabled/>
             	           改建<input type="checkbox" name="buildType" value="改建" disabled/>
               		 扩建<input type="checkbox" name="buildType" value="扩建" disabled/>
               		 维修<input type="checkbox" name="buildType" value="维修" disabled/>
               		 添置设施、设备<input type="checkbox" name="buildType" value="添置设施、设备" disabled/>
	         </td>
	          <td width="4%" id="_buildType" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
	         	建设面积(平方米)
	         </td>
	         <td  width="4%" colspan="4"  style="text-align: left;font-Size:10px height:20px;width:24%;white-space: nowrap;">
	            	 <div class="townText" style="display:none">${town.area}</div>
	            </td>
        	</tr>
        	
        	<tr >
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >计划投资(万元)</td>
	            <td width="4%" colspan="6" style="text-align: left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${town.plannedInvestment}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">自筹资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${town.selfFund}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">缺口资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${town.gapFund}</td>
        
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >县级项目库编号</td>
	            <td width="4%" colspan="6" style="text-align: left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${town.projectNumber}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">数量(处、套、台)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${town.scopeNumber}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">受益人口（人）</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${town.beneficiaryNumber}</td>
        
        	</tr>
        	
        	
        	 <tr >
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" rowspan="8">
        		<p>项</p><p>目</p><p>类</p><p>别</p></td>
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		 城市管理<input type="checkbox" name="type" value="城市管理" disabled/>
	            </td>
	            <td width="4%" colspan="21" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
					占道经营<input type="checkbox" name="_subType" value="占道经营" disabled/>
					 旧城改造<input type="checkbox" name="_subType" value="旧城改造" disabled/>
					  环境治理<input type="checkbox" name="_subType" value="环境治理" disabled/>
					 其他<input type="checkbox" name="_subType" value="其他" disabled/>
				</td>
	        
        
        	</tr>
        	 <tr >
        		
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		 城市街道<input type="checkbox" name="type" value="城市街道" disabled/>
	            </td>
	            <td width="4%" colspan="8" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:32%;font-weight:750;white-space: nowrap;">
					 建设<input type="checkbox" name="_subType" value="建设" disabled/>
					 维护<input type="checkbox" name="_subType" value="维护" disabled/>
					 安保<input type="checkbox" name="_subType" value="安保" disabled/>
				</td>
				
				<td width="4%" colspan="13" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:52%;font-weight:750;white-space: nowrap;">
					
					 安保设施类型：
					 防护栏<input type="checkbox" name="securityType" value="防护栏" disabled/>
					 防撞柱<input type="checkbox" name="securityType" value="防撞柱" disabled/>
					 减速带<input type="checkbox" name="securityType" value="减速带" disabled/>
					 标志标牌<input type="checkbox" name="securityType" value="标志标牌" disabled/>
					 其它<input type="checkbox" name="securityType" value="其它" disabled/>
					 
				</td>
        
        	</tr>
        	
        	
        	<tr >
        		
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		 市政公共设施<input type="checkbox" name="type" value="市政公共设施" disabled/>
	            </td>
	            <td width="4%" colspan="21" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
					公共停车场<input type="checkbox" name="_subType" value="公共停车场" disabled/>
					 道路桥梁<input type="checkbox" name="_subType" value="道路桥梁" disabled/>
					 道路照明<input type="checkbox" name="_subType" value="道路照明" disabled/>
					 排水设施<input type="checkbox" name="_subType" value="排水设施" disabled/>
					 公园<input type="checkbox" name="_subType" value="公园" disabled/>
					广场<input type="checkbox" name="_subType" value="广场" disabled/>
					 城区河道<input type="checkbox" name="_subType" value="城区河道" disabled/>
					 公共绿地<input type="checkbox" name="_subType" value="公共绿地" disabled/>
				</td>
	        
        
        	</tr>
        	
        	<tr >
        		
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		 市政环卫<input type="checkbox" name="type" value="市政环卫" disabled/>
	            </td>
	            <td width="4%" colspan="21" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
					公厕<input type="checkbox" name="_subType" value="公厕" disabled/>
					环卫设施<input type="checkbox" name="_subType" value="环卫设施" disabled/>
					城区生活垃圾处理<input type="checkbox" name="_subType" value="城区生活垃圾处理" disabled/>
				</td>
	        
        
        	</tr>
        	
        	<tr >
        		
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		 村镇规划建设管理<input type="checkbox" name="type" value="村镇规划建设管理" disabled/>
	            </td>
	            <td width="4%" colspan="21" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
					农村规划建设管理<input type="checkbox" name="_subType" value="农村规划建设管理" disabled/>
					场镇规划建设管理<input type="checkbox" name="_subType" value="场镇规划建设管理" disabled/>
					新农村建设<input type="checkbox" name="_subType" value="新农村建设" disabled/>
					基础设施配套<input type="checkbox" name="_subType" value="基础设施配套" disabled/>
					其他<input type="checkbox" name="_subType" value="其他" disabled/>
				</td>
	        
        
        	</tr>
        	<tr >
        		
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		 住房保障<input type="checkbox" name="type" value="住房保障" disabled/>
	            </td>
	            <td width="4%" colspan="21" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
					廉租房<input type="checkbox" name="_subType" value="廉租房" disabled/>
					公租房<input type="checkbox" name="_subType" value="公租房" disabled/>
					安置房<input type="checkbox" name="_subType" value="安置房" disabled/>
					危房改造<input type="checkbox" name="_subType" value="危房改造" disabled/>
					经济适用房<input type="checkbox" name="_subType" value="经济适用房" disabled/>
					其他<input type="checkbox" name="_subType" value="其他" disabled/>
				</td>
	        
        
        	</tr>
        	<tr >
        		
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	           		 建筑质量安全<input type="checkbox" name="type" value="建筑质量安全" disabled/>
	            </td>
	            <td width="4%" colspan="21" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
					房屋<input type="checkbox" name="_subType" value="房屋" disabled/>
					桥梁<input type="checkbox" name="_subType" value="桥梁" disabled/>
					工地<input type="checkbox" name="_subType" value="工地" disabled/>
					路段<input type="checkbox" name="_subType" value="路段" disabled/>
					公园绿地<input type="checkbox" name="_subType" value="公园绿地" disabled/>
					其他<input type="checkbox" name="_subType" value="其他" disabled/>
				</td>
	        
        
        	</tr>
        	
        	
        	<tr height="40">
        		<td height="40" colspan="2" width="4%" style="text-align: left;font-Size:16px font-family:'黑体'; font-weight:750; height:20px;width:8%;white-space: nowrap;" >
	        		其它<input type="checkbox" name="type" value="其他" disabled/>
        		</td>
        		
        		<td height="40" width="4%" colspan="22"  style="text-align: left;font-Size:10px height:20px;width:8%;white-space: nowrap;">
	            	 <div id="otherContent" style="display:none">${town.otherContent}</div>
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
var buildType ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT_BUILD_TYPE" 
	defaultValue="${town.buildType.id}" />';
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT" 
	defaultValue="${town.projectCategory.id}" />';
var subtype='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT_SUB" 
	defaultValue="${town.projectSubCategory.id}" />';
var securityType= '<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_SECURITY_TYPE" 
	defaultValue="${town.securityType.id}" />';
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
       	   $(this).parent().parent().find(".townText").each(function(){
    		   $(this).show();
           })
           $(this).parent().parent().find("input[name='_subType']").each(function(){
    		  if($(this).val() ==subtype){
    		     $(this).attr('checked',"checked");
    		  }
           })
           if(i==1){
        	   $(this).parent().parent().find("input[name='securityType']").each(function(){
         		  if($(this).val() ==securityType){
         		     $(this).attr('checked',"checked");
         		  }
                })
           }else if(i==7){
        	   $("#otherContent").show();
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

