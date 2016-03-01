<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="sciencePrint" style="height:380px;overflow:hidden;overflow-y:auto;">
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
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:48%;white-space: nowrap;" colspan="12">${science.fromAddress} <c:if test="${science.fromAddress ne null &&science.toAddress ne null}">——</c:if>${science.toAddress}</td>
	            <td width="4%"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" colspan="3">受益人口（人）</td>
	            <td width="4%" style="font-Size:10px; height:20px;width:16%;white-space: nowrap;" colspan="6">${science.beneficiaryNumber}</td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >工程名称</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:40%;white-space: nowrap;" colspan="10">${science.projectName}</td>
	           	<td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" > 建设性质</td>
	            <td width="4%" id="_buildType" colspan="10" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:40%;font-weight:750;white-space: nowrap;">
	           	 	新建<input type="checkbox" name="buildType" value="新建" disabled/>
             	           改建<input type="checkbox" name="buildType" value="改建" disabled/>
               		 扩建<input type="checkbox" name="buildType" value="扩建" disabled/>
               		 维修<input type="checkbox" name="buildType" value="维修" disabled/>
                  	添置设备设施<input type="checkbox" name="buildType" value="添置设备设施" disabled/>
	         </td>
        	</tr>
        	
        	<tr >
        		
        		<td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" >计划投资(万元)</td>
	            <td width="4%" colspan="6" style="text-align: left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${science.plannedInvestment}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">自筹资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${science.selfFund}</td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">缺口资金(万元)</td>
	            <td width="4%" colspan="6" style="text-align:left;font-Size:10px; height:20px;width:24%;white-space: nowrap;">${science.gapFund}</td>
        
        	</tr>
        	<tr >
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" rowspan="6">
	        		<p>文</p><p>化</p><p>体</p><p>育</p>
	        		<p>广</p><p>电</p><p>旅</p><p>游</p>
	        		<p><input type="checkbox" name="_type" value="" disabled/></p>
        		</td>
	            <td width="4%" colspan="1" style="text-align:left;font-Size:16px font-family:'黑体'; height:20px;width:40%;font-weight:750;white-space: nowrap;" rowspan="3">
	          		  广播电视<input type="checkbox" name="type" value="广播电视" disabled/></td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
			            电视“户户通”<input type="checkbox" name="subType" value="电视“户户通”" disabled/>
			      
               </td>
	           <td width="4%" colspan="12" style="text-align:left;font-Size:16px font-family:'黑体'; height:20px;width:48%;font-weight:750;white-space: nowrap;">
				           直播卫星 <input type="checkbox" name="contentCategory" value="直播卫星" disabled/>
				           有线电视“户户通”<input type="checkbox" name="contentCategory" value="有线电视“户户通”" disabled/>
					地面数字电视“户户通”<input type="checkbox" name="contentCategory" value="地面数字电视“户户通”" disabled/>
			  		其他<input type="checkbox" name="contentCategory" value="其他" disabled/>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">户数</td>
	            <td width="4%" colspan="5" style="text-align:left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	           	 <div class="scienceText" style="display:none">${science.scienceScope}</div>
	           	</td>
        
        	</tr>
        	
        	<tr >
        	
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
			           广播“村村响”<input type="checkbox" name="subType" value="广播“村村响”" disabled/>
			      
               </td>
	           <td width="4%" colspan="12" style="text-align:left;font-Size:16px font-family:'黑体'; height:20px;width:48%;font-weight:750;white-space: nowrap;">
				        广播站室<input type="checkbox" name="contentCategory" value="广播站室" disabled/>
				          广播<input type="checkbox" name="contentCategory" value="广播" disabled/> 
				        其他<input type="checkbox" name="contentCategory" value="其他" disabled/>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">个数</td>
	            <td width="4%" colspan="5" style="text-align:left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	            	<div class="scienceText" style="display:none">${science.scienceScope}</div></td>
        
        	</tr>
        	
        	<tr >
        	
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
			          电影“月月放”<input type="checkbox" name="subType" value="电影“月月放”" disabled/>
			      
               </td>
	           <td width="4%" colspan="12" style="text-align:left;font-Size:16px font-family:'黑体'; height:20px;width:48%;font-weight:750;white-space: nowrap;">
				      播放地点：村(社区)<input type="checkbox" name="contentCategory" value="村(社区)" disabled/>
				        	其他<input type="checkbox" name="contentCategory" value="其他" disabled/>
	            </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">场次</td>
	            <td width="4%" colspan="5" style="text-align:left;font-Size:10px; height:20px;width:6%;white-space: nowrap;">
	            	<div class="scienceText" style="display:none">${science.scienceScope}</div>
	            </td>
        
        	</tr>
        	 <tr >
        		
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	          		  旅游<input type="checkbox" name="type" value="旅游" disabled/></td>
	            <td width="4%" colspan="14"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:56%;font-weight:750;white-space: nowrap;">
			            A级旅游景区（点）管理<input type="checkbox" name="subType" value="A级旅游景区（点）管理" disabled/>
			                               星级农家乐管理<input type="checkbox" name="subType" value="星级农家乐管理" disabled/>
			                                星级旅游饭店管理<input type="checkbox" name="subType" value="星级旅游饭店管理" disabled/>
			                              其他<input type="checkbox" name="subType" value="其他" disabled/>
			      
               </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">户数</td>
	            <td width="4%" colspan="3" style="text-align:left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	            	<div class="scienceText" style="display:none">${science.scienceScope}</div>
	            </td>
        
        	</tr>
        	<tr >
        		
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">
	          		 文化<input type="checkbox" name="type" value="文化" disabled/></td>
	            <td width="4%" colspan="14"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:56%;font-weight:750;white-space: nowrap;">
			              乡镇综合文化站  <input type="checkbox" name="subType" value="乡镇综合文化站" disabled/>
			               村（社区）农家书屋  <input type="checkbox" name="subType" value="村（社区）农家书屋" disabled/>
			               其他<input type="checkbox" name="subType" value="其他" disabled/>
			      
               </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">户数</td>
	            <td width="4%" colspan="3" style="text-align:left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	            	<div class="scienceText" style="display:none">${science.scienceScope}</div>
	            </td>
        
        	</tr>
        	<tr >
        		
	            <td width="4%" colspan="1" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" >
	          		 体育	<input type="checkbox" name="type" value="体育" disabled/></td>
	            <td width="4%" colspan="14"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:56%;font-weight:750;white-space: nowrap;">
			         	全民健身路径<input type="checkbox" name="subType" value="全民健身路径" disabled/>
			          	 乡镇社会体育指导站<input type="checkbox" name="subType" value="乡镇社会体育指导站" disabled/>
			                                其他<input type="checkbox" name="subType" value=" 其他" disabled/>
			      
               </td>
	            <td width="4%" colspan="1"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;">户数</td>
	            <td width="4%" colspan="3" style="text-align:left;font-Size:10px; height:20px;width:12%;white-space: nowrap;">
	            	<div class="scienceText" style="display:none">${science.scienceScope}</div>
	            </td>
        
        	</tr>
        	
        	
        	 <tr >
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" rowspan="2">
	        		<p>科技</p><p>科协
	        		<input type="checkbox" name="_type" value="" disabled/></p>
        		</td>
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
	          		 科技、科协项目
	          		 <input type="checkbox" name="type" value="科技、科协项目" disabled/></td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
			            项目级别
			      
               </td>
                <td width="4%" colspan="7"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;white-space: nowrap;">
			      国家级类<input type="checkbox" name="projectLevelCategory" value="国家级类" disabled/>
			       省级类<input type="checkbox" name="projectLevelCategory" value="省级类" disabled/> 
			       市级类<input type="checkbox" name="projectLevelCategory" value="市级类" disabled/>
			        县级类<input type="checkbox" name="projectLevelCategory" value="县级类" disabled/> 
               </td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">项目名称</td>
	            <td width="4%" colspan="10" style="text-align:left;font-Size:10px; height:20px;width:40%;white-space: nowrap;">
	            	 <div class="scienceText" style="display:none">${science.itemName}</div>
	            </td>
        
        	</tr>
        	 <tr >
        		
	            <td width="4%" colspan="2" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
	          		科技、科协宣传
	          		 <input type="checkbox" name="type" value="科技、科协宣传" disabled/></td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
			                     宣传次数
               </td>
                <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">
			      <div class="scienceText" style="display:none">${science.publicizeNum}</div>
               </td>
	            <td width="4%" colspan="2"  style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;">主题内容</td>
	            <td width="4%" colspan="13" style="text-align:left;font-Size:10px; height:20px;width:52%;">
	            	<div class="scienceText" style="display:none">${science.content}</div>
	            </td>
        
        	</tr>
        	
        	
        	<tr height="40">
        		<td height="40" colspan="1" width="4%" style="text-align: left;font-Size:16px font-family:'黑体'; font-weight:750; height:20px;width:4%;white-space: nowrap;" >
	        		<p>其它<input type="checkbox" name="type" value="其它" disabled/></p>
        		</td>
        		
        		<td height="40" width="4%" colspan="23"  style="text-align: left;font-Size:10px height:20px;width:92%;">
	            	 <div class="scienceText" style="display:none">${science.otherContent}</div>
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
var buildType ='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_BUILD_TYPE" 
	defaultValue="${science.buildType.id}" />';
	
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT" 
	defaultValue="${science.projectCategory.id}" />';
var subType='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT_SUB" 
	defaultValue="${science.projectSubCategory.id}" />';	
var contentCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_BROADCAST" 
	defaultValue="${science.contentCategory.id}" />';	
var projectLevelCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT_LEVEL" 
	defaultValue="${science.projectLevelCategory.id}" />';
	
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
          if(i==0||i==2||i==3||i==1){
        	  $("input[name='_type']").eq(0).attr('checked',"checked");
          }else if(i==4||i==5){
        	  $("input[name='_type']").eq(1).attr('checked',"checked");
          }
         if(i!=0&&i!=6){
	         $(this).parent().parent().find(".scienceText").each(function(){
	        	 $(this).show();
	         })
         }else if(i==6){
	         $(this).parent().parent().parent().find(".scienceText").each(function(){
	        	 $(this).show();
	         })
         }
         $(this).parent().parent().find("input[name='projectLevelCategory']").each(function(){
			 if(projectLevelCategory.indexOf($(this).val())>-1){
				 $(this).attr('checked',"checked");
			 }
         })
		$("input[name='subType']").each(function(){
			 if(subType.indexOf($(this).val())>-1){
		         $(this).attr('checked',"checked");
		         if(i==0)
		         $(this).parent().parent().find(".scienceText").each(function(){
		        	 $(this).show();
		         })
				$(this).parent().parent().find("input[name='contentCategory']").each(function(){
					 if(contentCategory.indexOf($(this).val())>-1){
						 $(this).attr('checked',"checked");
					 }
		         })
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

