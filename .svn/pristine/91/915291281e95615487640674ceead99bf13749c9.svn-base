<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="energyPrint" style="height:380px;overflow:hidden;overflow-y:auto;">
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
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;" colspan="12">${energy.fromAddress} <c:if test="${energy.fromAddress ne null &&energy.toAddress ne null}">——</c:if>${energy.toAddress}</td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" colspan="3">受益人口（人）</td>
	            <td width="4%" style="font-Size:10px; height:20px;width:16%;white-space: nowrap;" colspan="6">${energy.beneficiaryNumber}</td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >工程名称</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:40%;white-space: nowrap;" colspan="10">${energy.projectName}</td>
	           	<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" > 建设性质</td>
	            <td width="4%" id="_buildType" colspan="10" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:40%;font-weight:750;white-space: nowrap;">
	           	
	             <pop:PropertyDictMultiCheckbox name="buildType"
					column="4" readOnly="true"
					domainName="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_BUILD_TYPE"/>
	         </td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >计划投资(万元)</td>
	            <td width="4%" colspan="6" style="text-align: left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${energy.plannedInvestment}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">自筹资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${energy.selfFund}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">缺口资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${energy.gapFund}</td>
        
        	</tr>
        	
       		 <tr >
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" rowspan="9">
        		<p>项</p><p>目</p><p>类</p><p>别</p></td>
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px; height:20px;width:8%;white-space: nowrap;" rowspan="3">
	            <p>天然气<input type="checkbox" name="type" value="天然气" disabled/></p>

				<p>石油液化气<input type="checkbox" name="type" value="石油液化气" disabled/></p>
	            
	            </td>
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px; height:20px;width:8%;white-space: nowrap;" rowspan="3">
	          		<p> 管道建设<input type="checkbox" name="_subType" value="管道建设" disabled/></p>
					<p>	管道养护<input type="checkbox" name="_subType" value="管道养护" disabled/></p>
					<p>	户表工程<input type="checkbox" name="_subType" value="户表工程" disabled/></p></td>
	            <td width="4%" colspan="11"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:44%;font-weight:750;white-space: nowrap;">
			            管道类型：
			                      中压管道<input type="checkbox" name="pipeLineCategory" value="中压管道" disabled/>
			                      低压管道<input type="checkbox" name="pipeLineCategory" value="低压管道" disabled/>
		         	 高压管道<input type="checkbox" name="pipeLineCategory" value="高压管道" disabled/>
		           	户内管道<input type="checkbox" name="pipeLineCategory" value="户内管道" disabled/>
               </td>
	            <td width="4%" colspan="8" style="text-align:left;font-Size:16px font-family:'黑体'; height:20px;width:32%;font-weight:750;white-space: nowrap;">
					管道材质：PE管<input type="checkbox" name="pipeMaterialCategory" value="PE管" disabled/>
							 钢管<input type="checkbox" name="pipeMaterialCategory" value="钢管" disabled/>
							  其他<input type="checkbox" name="pipeMaterialCategory" value="其他" disabled/>           
	            </td>
	        
        
        	</tr>
        	
        	 <tr >
        	 
        
        	  	<td width="4%" colspan="4"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	          	 长度（公里）</td>
	            <td width="4%" colspan="5"  style="text-align: left;font-Size:10px ;height:20px;width:20%;font-weight:750;white-space: nowrap;">
	      			<div class="energyText" style="display:none">${energy.mileage}</div>
	      		</td>
          		<td width="4%" colspan="4"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	          	 填埋深度（米）</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">
	            	<div class="energyText" style="display:none">${energy.depth}</div>
	            </td>
	        
        
        	</tr>
        	
        	
        	<tr>
        	
        	    <td width="4%" colspan="9"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:36%;font-weight:750;white-space: nowrap;">
	        		   安全设施建设：
	           			防护栏<input type="checkbox" name="securityCategory" value="防护栏" disabled/> 
	           			安全标志标牌<input type="checkbox" name="securityCategory" value="安全标志标牌" disabled/>
	           			 防火设施<input type="checkbox" name="securityCategory" value="防火设施" disabled/>
	           		 	 使用说明书<input type="checkbox" name="securityCategory" value="使用说明书" disabled/></td>
	            <td width="4%" colspan="4"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	          	 数量（处）</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">
	            	<div class="energyText" style="display:none">${energy.securityNum}</div>
	            </td>
	        
        
        	</tr>
        	
        	
        	<tr>
        	
        	   <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
	           		<p>汽柴油<input type="checkbox" name="type" value="汽柴油" disabled/></p></td>
	           <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
	           		加油站建设<input type="checkbox" name="_subType" value="加油站建设" disabled/></td>
	            <td width="4%" colspan="20"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:80%;font-weight:750;white-space: nowrap;">
	          	 安全设施类型：
	          	 	防护栏<input type="checkbox" name="securityCategory" value="防护栏" disabled/> 
	          	 	 防撞墩<input type="checkbox" name="securityCategory" value="防撞墩" disabled/> 
	          	 	  减速带<input type="checkbox" name="securityCategory" value="减速带" disabled/> 
             		 防火设施<input type="checkbox" name="securityCategory" value="防火设施" disabled/>
             		  安全标志标牌<input type="checkbox" name="securityCategory" value="安全标志标牌" disabled/> </td>
	        
        
        	</tr>
        	
        	<tr>
        	
        	   <td width="4%" colspan="2" rowspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;">
	         	    <p> 电力<input type="checkbox" name="type" value="电力" disabled/></p></td>
	           <td width="4%" colspan="2"   rowspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;">
	           		<p>电网设施<input type="checkbox" name="_subType" value="电网设施" disabled/></p> 
					<p>电网维护<input type="checkbox" name="_subType" value="电网维护" disabled/></p> 
					<p>户表工程<input type="checkbox" name="_subType" value="户表工程" disabled/></p> </td>

	            <td width="4%" colspan="9"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:36%;font-weight:750;white-space: nowrap;">
	          	线路类型：变压器(台数)<input type="checkbox" name="lineCategory" value="变压器" disabled/>
				高压线路(KM)<input type="checkbox" name="lineCategory" value="高压线路" disabled/>
 				低压线路(KM)<input type="checkbox" name="lineCategory" value="低压线路" disabled/></td>
			<td width="4%" colspan="4"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	          	数量（KM、台）</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">
	            <div class="energyText" style="display:none">${energy.energyNumber}</div>
	            </td>

	        
        
        	</tr>
        	
        	
        	
        	<tr>

	            <td width="4%" colspan="9"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:36%;font-weight:750;white-space: nowrap;">
	          	安全设施建设：安全标志标牌<input type="checkbox" name="securityCategory" value="安全标志标牌" disabled/></td>
			<td width="4%" colspan="4"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	          	数量（块）</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">
	            	<div class="energyText" style="display:none">${energy.securityNum}</div>
	            </td>

	        
        
        	</tr>
        	
        	
        	<tr>
        	
        	    <td width="4%" colspan="2" rowspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:12%;font-weight:750;white-space: nowrap;">
	           		<p>沼气池<input type="checkbox" name="type" value="沼气池" disabled/></p></td>
	           <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;">
	         		 沼气池建设<input type="checkbox" name="_subType" value="沼气池建设" disabled/>  </td>

	          <td width="4%" colspan="4"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	          	数量（口）</td>
	            <td width="4%" colspan="5" style="text-align:left;font-Size:10px; height:20px;width:20%;white-space: nowrap;">
	            	<div class="energyText" style="display:none">${energy.energyNumber}</div>
	            </td>
			<td width="4%" colspan="4"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	          	容积(立方米)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">
	            	<div class="energyText" style="display:none">${energy.capacity}</div>
	            </td>
        
        	</tr>
        	
        	<tr>
        	
	           <td width="4%" colspan="4" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;white-space: nowrap;">
	          	      沼气池安全设施建设<input type="checkbox" name="_subType" value="沼气池安全设施建设" disabled/> </td>

	          <td width="4%" colspan="17"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:68%;font-weight:750;white-space: nowrap;">
	          	    警示牌<input type="checkbox" name="securityCategory" value="警示牌" disabled/>
	          	    安全使用手册<input type="checkbox" name="securityCategory" value="安全使用手册" disabled/>
	          	    其他<input type="checkbox" name="securityCategory" value="其他" disabled/></td>
	           
        	</tr>
        	
        	
        	<tr height="40">
        		<td height="40" colspan="2" width="4%" style="text-align: left;font-Size:16px font-family:'黑体'; font-weight:750; height:20px;width:8%;white-space: nowrap;" >
	        		<p>其他<input type="checkbox" name="type" value="其他" disabled/></p>
        		</td>
        		
        		<td height="40" width="4%" colspan="22"  style="text-align: left;font-Size:10px height:20px;width:8%;white-space: nowrap;">
	            	 <div class="energyText" style="display:none">${energy.otherContent}</div>
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
var buildType ='${energy.buildType.id}';
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PROJECT" 
	defaultValue="${energy.projectCategory.id}" />';
	
var subtype='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PROJECT_SUB_TYPE" 
	defaultValue="${energy.projectSubCategory.id}" />';
var pipeLineCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PIPELINE_CATEGORY" 
	defaultValue="${energy.pipeLineCategory.id}" />';
var pipeMaterialCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PIPE_MATERIAL_CATEGORY" 
	defaultValue="${energy.pipeMaterialCategory.id}" />';
var securityCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_SECURITY_CATEGORY" 
	defaultValue="${energy.securityCategory.id}" />';
var  lineCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_LINE_CATEGORY" 
	defaultValue="${energy.lineCategory.id}" />';
	
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
           $(this).parent().parent().parent().find("input[name='_subType']").each(function(){
	       	   if( subtype.indexOf($(this).val())>-1&&subtype!="")
	           	   $(this).attr('checked',"checked");
	           })
           if(i==4){
        	   $(this).parent().parent().parent().next().find("input[name='_subType']").each(function(){
    	       	   if($(this).val() ==subtype)
    	           	   $(this).attr('checked',"checked");
    	           })
           }
           if(i==0||i==1){
        		$(this).parent().parent().parent().next().find(".energyText").each(function(){
        			$(this).show();
                });
        		$(this).parent().parent().parent().next().find(".energyText").each(function(){
        			$(this).show();
                });
        		$(this).parent().parent().parent().find("input[name='pipeLineCategory']").each(function(){
        			 if($(this).val() ==pipeLineCategory)
      	           	   $(this).attr('checked',"checked");
      	           })
				$(this).parent().parent().parent().find("input[name='pipeMaterialCategory']").each(function(){
        			 if($(this).val() ==pipeMaterialCategory)
      	           	   $(this).attr('checked',"checked");
      	           })
      	        $(this).parent().parent().parent().next().next().find("input[name='securityCategory']").each(function(){
        			 if($(this).val() ==securityCategory)
      	           	   $(this).attr('checked',"checked");
      	           }) 
      	        $(this).parent().parent().parent().next().next().find(".energyText").each(function(){
      	        	$(this).show();
      	           }) 

            }else if(i==2){
            	$(this).parent().parent().parent().find("input[name='securityCategory']").each(function(){
       			 if($(this).val() ==securityCategory)
     	           	   $(this).attr('checked',"checked");
     	           }) 
            }else if(i==3){
            	$(this).parent().parent().parent().next().find("input[name='securityCategory']").each(function(){
          			 if($(this).val() ==securityCategory)
        	           	   $(this).attr('checked',"checked");
        	           }) 
     	           $(this).parent().parent().parent().next().find(".energyText").each(function(){
     	        	  	$(this).show();
           	        }) 
          	       $(this).parent().parent().parent().find(".energyText").each(function(){
          	    	    $(this).show();
         	       }) 
				   $(this).parent().parent().parent().find("input[name='lineCategory']").each(function(){
          			 if($(this).val() ==lineCategory)
        	           	   $(this).attr('checked',"checked");
        	       }) 

         	      
               }else{
		       	$(this).parent().parent().parent().find(".energyText").each(function(){
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

