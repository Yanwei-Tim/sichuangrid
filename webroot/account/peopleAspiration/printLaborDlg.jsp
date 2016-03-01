<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="laborPrint" style="height:380px;overflow:hidden;overflow-y:auto;">
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
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=4>
        			<p>就</p><p>业</p><input type="checkbox" name="type" value="就业" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			求职意愿登记<input type="checkbox" name="_subType" value="求职意愿登记" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;white-space: nowrap;" colspan="7">
        			  企业<input type="checkbox" name="subContentCategory" value="企业" disabled/>
        			  建筑<input type="checkbox" name="subContentCategory" value="建筑" disabled/>
        			  运输<input type="checkbox" name="subContentCategory" value="运输" disabled/>
        			  服务<input type="checkbox" name="subContentCategory" value="服务" disabled/>
        			  其他<input type="checkbox" name="subContentCategory" value="其他" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;white-space: nowrap;" colspan="1">
        			<p>本人</p><p>学历</p>
        		</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:8%;white-space: nowrap;" colspan="2">
			           <div class="laborText" style="display:none"><pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_DEGREE" 
					   defaultValue="${labor.degree.id}" /></div>
				</td>
				<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			本人技能特长
        		</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:44%;" colspan="11">
	          		<div class="laborText" style="display:none">${labor.skill}</div>
				</td>
        	</tr>
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" colspan="2" >
        			就业登记<input type="checkbox" name="_subType" value="就业登记" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;" colspan="7">
        			个体经营<input type="checkbox" name="subContentCategory" value="个体经营" disabled/>
        			 单位就业<input type="checkbox" name="subContentCategory" value="单位就业" disabled/>
        			  灵活就业<input type="checkbox" name="subContentCategory" value="灵活就业" disabled/>
        			  其他<input type="checkbox" name="subContentCategory" value="其他" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" colspan="1">
        			<p>本人</p><p>学历</p>
        		</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:8%;white-space: nowrap;" colspan="2">
			        <div class="laborText" style="display:none"><pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_DEGREE" 
					   defaultValue="${labor.degree.id}" /></div>
				</td>
				<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			本人技能特长
        		</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:44%;" colspan="11">
	          		<div class="laborText" style="display:none">${labor.skill}</div>
				</td>
        	</tr>
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			失业登记<input type="checkbox" name="_subType" value="失业登记" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;" colspan="7">
        			新成长失业人员<input type="checkbox" name="subContentCategory" value="新成长失业人员" disabled/>
        			 就业转失业人员<input type="checkbox" name="subContentCategory" value="就业转失业人员" disabled/>
        			 其他<input type="checkbox" name="subContentCategory" value="其他" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" colspan="1">
        			<p>本人</p><p>学历</p>
        		</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:8%;white-space: nowrap;" colspan="2">
			           <div class="laborText" style="display:none"><pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_DEGREE" 
					   defaultValue="${labor.degree.id}" /></div>
				</td>
				<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;white-space: nowrap;" colspan="2">
        			本人技能特长
        		</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:44%;" colspan="11">
	          		<div class="laborText" style="display:none">${labor.skill}</div>
				</td>
        	</tr>
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			就业技能培训<input type="checkbox" name="_subType" value="就业技能培训" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:28%;font-weight:750;" colspan="7">
        			 职业技能培训<input type="checkbox" name="subContentCategory" value="职业技能培训" disabled/>
        			 岗前培训<input type="checkbox" name="subContentCategory" value="岗前培训" disabled/>
        			 劳务品牌培训<input type="checkbox" name="subContentCategory" value="劳务品牌培训" disabled/>
        			 其他<input type="checkbox" name="subContentCategory" value="其他" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" colspan="1">
        			<p>本人</p><p>学历</p>
        		</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:8%;white-space: nowrap;" colspan="2">
			           <div class="laborText" style="display:none"><pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_DEGREE" 
					   defaultValue="${labor.degree.id}" /></div>
				</td>
				<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			本人技能特长
        		</td>
	            <td width="4%" style="text-align: left;font-Size:10px; height:20px;width:44%;" colspan="11">
	          		<div class="laborText" style="display:none">${labor.skill}</div>
				</td>
        	</tr>
        	
        
        
        	<tr class="tr1">
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=6>
        			<p>社</p><p>会</p><p>保</p><p>障</p><input type="checkbox" name="type" value="社会保障" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="1" rowspan="4">
        			养老保险<input type="checkbox" name="_subType" value="养老保险" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:20%;font-weight:750;" colspan="5"  rowspan="4">
        			新型农村社会养老保险 <input type="checkbox" name="subContentCategory" value="新型农村社会养老保险" disabled/>
        			城镇居民社会养老保险<input type="checkbox" name="subContentCategory" value="城镇居民社会养老保险" disabled/>
					城镇职工养老保险<input type="checkbox" name="subContentCategory" value="城镇职工养老保险" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:60%;font-weight:750;" colspan="15">
        			年龄段：60周岁以上<input type="checkbox" name="ageLevel" value="60周岁以上" disabled/>  
        					16—59周岁<input type="checkbox" name="ageLevel" value="16—59周岁" disabled/>  
        		</td>
	           
        	</tr>
        	<tr class="tr1">
        	
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2" rowspan="2">
        			是否残疾 
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:76%;font-weight:750;" colspan="18">
        			否<input type="checkbox" name="crippleLevel" value="否" disabled/> 
        		</td>
	           
        	</tr>
       		<tr class="tr1">
        	
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			是<input type="checkbox" name="crippleLevel" value="1—2级3—4级" disabled/>  
        		</td>
	           
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:64%;font-weight:750;" colspan="16">
        			残疾等级：1—2级<input type="checkbox" name="crippleLevel" value="1—2级" disabled/>  
        					 3—4级<input type="checkbox" name="crippleLevel" value="3—4级" disabled/>  
        		</td>
        	</tr>
        	<tr class="tr1">
        	
        		<td id="yangLao" width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:80%;font-weight:750;" colspan="20">
        			身份性质：行政事业<input type="checkbox" name="dignity" value="行政事业" disabled/> 
        					    企业<input type="checkbox" name="dignity" value="企业" disabled/> 
        					     个体<input type="checkbox" name="dignity" value="个体" disabled/> 
        					     失地农民<input type="checkbox" name="dignity" value="失地农民" disabled/> 
        					     其他<input type="checkbox" name="dignity" value="其他" disabled/>  
        		</td>
	           
        	</tr>
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" rowspan="2" colspan="1">
        			医疗保险<input type="checkbox" name="_subType" value="医疗保险" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:24%;font-weight:750;" colspan="6" >
        			城镇职工医疗保险  <input class="medical" type="checkbox" name="subContentCategory" value="城镇职工医疗保险 " disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:60%;font-weight:750;" colspan="15">
        			身份性质：行政事业<input type="checkbox" name="dignity" value="行政事业" disabled/> 
        					    企业<input type="checkbox" name="dignity" value="企业" disabled/> 
        					    个体<input type="checkbox" name="dignity" value="个体" disabled/> 
        		</td>
	           
        	</tr>
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:24%;font-weight:750;" colspan="6" >
        			城镇居民医疗保险 <input class="medical" type="checkbox" name="subContentCategory" value="城镇居民医疗保险 " disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:80%;font-weight:750;" colspan="20">
        			身份性质： 
        			 学生  <input type="checkbox" name="dignity" value="学生" disabled/>
        			 失地农民<input type="checkbox" name="dignity" value="失地农民" disabled/>
        			 重度残疾<input type="checkbox" name="dignity" value="重度残疾" disabled/>
        		           新生婴儿<input type="checkbox" name="dignity" value="新生婴儿" disabled/>
        		           其他<input type="checkbox" name="dignity" value="其他" disabled/>	
        		</td>
	           
        	</tr>
        </table>
          <div style="page-break-after: always;"></div> 
         <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        	<tr class="tr1">
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=10>
        			<p>研</p><p>究</p><p>整</p><p>理</p><p>情</p><p>况</p>
        		</td>
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=3>
        		<p>社</p><p>会</p><p>保</p><p>障</p><input type="checkbox" name="type" value="社会保障" disabled/>
        		</td>	
        	
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" colspan="4">
        			工伤保险<input type="checkbox" name="_subType" value="工伤保险" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:76%;font-weight:750;" colspan="19">
        			身份性质：行政事业<input type="checkbox" name="dignity" value="行政事业" disabled/>
        					    企业<input type="checkbox" name="dignity" value="企业" disabled/>
        					     其他<input type="checkbox" name="dignity" value="其他" disabled/>
        		</td>
	           
        	</tr>
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" colspan="4">
        			生育保险<input type="checkbox" name="_subType" value="生育保险" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:76%;font-weight:750;" colspan="19">
        			身份性质：行政事业<input type="checkbox" name="dignity" value="行政事业" disabled/>
        					   企业<input type="checkbox" name="dignity" value="企业" disabled/>
        					    其他<input type="checkbox" name="dignity" value="其他" disabled/>
        		</td>
	           
        	</tr>
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:16%;font-weight:750;" colspan="4">
        			失业保险<input type="checkbox" name="_subType" value="失业保险" disabled/>
        		</td>
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:76%;font-weight:750;" colspan="19">
        			身份性质：行政事业<input type="checkbox" name="dignity" value="行政事业" disabled/>
        					   企业<input type="checkbox" name="dignity" value="企业" disabled/>
        					    其他<input type="checkbox" name="dignity" value="其他" disabled/>
        		</td>
	           
        	</tr>
        
        	<tr class="tr1">
        		<td style="text-align: center;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" width="4%" rowspan=2>
        			<p>农</p><p>民</p><p>工</p><p>工</p><p>资</p><input type="checkbox" name="type" value="农民工工资" disabled/>
        		</td>	
        	
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			工资拖欠工程项目名称
        		</td>
        		<td  width="4%" colspan="4"  style="text-align: left;font-Size:10px height:20px;width:16%;">
	            	 <div class="salaryText" style="display:none">${labor.projectName}</div>
	            </td>
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			工资拖欠施工单位
        		</td>
        		<td  width="4%" colspan="4"  style="text-align: left;font-Size:10px height:20px;width:16%;">
	            	 <div class="salaryText" style="display:none">${labor.company}</div>
	            </td>
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			工资拖欠施工单位地址
        		</td>
        		<td width="4%" colspan="6"  style="text-align: left;font-Size:10px height:20px;width:24%;">
	            	 <div class="salaryText" style="display:none">${labor.yawnAddr}</div>
	            </td>
	           
        	</tr>
        	
        	<tr class="tr1">
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			工资拖欠施工单位联系人姓名
        		</td>
        		<td  width="4%" colspan="4"  style="text-align: left;font-Size:10px height:20px;width:16%;">
	            	 <div class="salaryText" style="display:none">${labor.yawnContactor}</div>
	            </td>
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:4%;font-weight:750;" colspan="1">
        			联系电话
        		</td>
        		<td  width="4%" colspan="4"  style="text-align: left;font-Size:10px height:20px;width:16%;">
	            	 <div class="salaryText" style="display:none">${labor.yawnMobile}</div>
	            </td>
        		
        		<td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="2">
        			涉及人数
        		</td>
        		<td width="4%" colspan="3"  style="text-align: left;font-Size:10px height:20px;width:12%;">
	            	 <div class="salaryText" style="display:none">${labor.relationNumber}</div>
	            </td>
	            <td width="4%" style="text-align: left;font-Size:16px font-family:'黑体';height:20px;width:8%;font-weight:750;" colspan="1">
        			涉及金额（万元）
        		</td>
        		<td width="4%" colspan="4"  style="text-align: left;font-Size:10px height:20px;width:16%;">
	            	 <div class="salaryText" style="display:none">${labor.money}</div>
	            </td>
	           
        	</tr>
        	
        	<tr height="40">
        		<td height="40" colspan="1" width="4%" style="text-align: left;font-Size:16px font-family:'黑体'; font-weight:750; height:20px;width:4%;white-space: nowrap;" >
	        		<p>其</p><p>他</p><p><input type="checkbox" name="type" value="其他" disabled/></p>
        		</td>
        		
        		<td height="40" width="4%" colspan="23"  style="text-align: left;font-Size:10px height:20px;width:92%;white-space: nowrap;">
	            	 <div id="otherLaborContent" class="laborText" style="display:none">${labor.otherContent}</div>
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
var type='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT" 
	defaultValue="${labor.projectCategory.id}" />';
	
var subtype='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT_SUB" 
	defaultValue="${labor.projectSubCategory.id}" />';

var subContentCategory='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT_CONTENT" 
	defaultValue="${labor.projectSubContentCategory.id}" />';
var ageLevel='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_AGE" 
	defaultValue="${labor.ageLevel.id}" />';	
var crippleLevel='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_CRIPPLE" 
	defaultValue="${labor.crippleLevel.id}" />';
var dignity='<pop:PropertyDictViewTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_DIGNITY" 
	defaultValue="${labor.dignity.id}" />';		
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
           if(i==3){
        	   $('.salaryText').each(function(){
					$(this).show();
               })
           }else if(i==4) $('#otherLaborContent').show();
        }
        	
   	});  
  	$("input[name='_subType']").each(function(i){
    	if( subtype.indexOf($(this).val())>-1&&subtype!=""){
            $(this).attr('checked',"checked");
            $(this).parent().parent().find(".laborText").each(function(){
  			  $(this).show();
  		   })
		   $("input[name='subContentCategory']").each(function(){
			 	if( subContentCategory.indexOf($(this).val())>-1&&subContentCategory!=""){
				 	$(this).attr('checked',"checked");
			 	}
  		   })
  		   if(i==4){
	  		   $("input[name='ageLevel']").each(function(){
				 	if( ageLevel.indexOf($(this).val())>-1&&ageLevel!=""){
					 	$(this).attr('checked',"checked");
				 	}
	  		   })
	  		  $("input[name='crippleLevel']").each(function(){
				 	if( $(this).val().indexOf(crippleLevel)>-1&&crippleLevel!=""){
					 	$(this).attr('checked',"checked");
				 	}
	  		   })
	  		   
	  		   $("#yangLao input[name='dignity']").each(function(){
	  			 if( dignity.indexOf($(this).val())>-1&&dignity!=""){
					 	$(this).attr('checked',"checked");
				 	}
	  		   })
  		   }else if(i==5){
  				$(".medical").each(function(){
  					if( $(this).val().indexOf(subContentCategory)>-1&&subContentCategory!=""){
  					 	$(this).attr('checked',"checked");
  					  	$(this).parent().parent().find("input[name='dignity']").each(function(){
  			  			 if( dignity.indexOf($(this).val())>-1&&dignity!=""){
  							 	$(this).attr('checked',"checked");
  						 	}
  			  		   })
  				 	}
  	  		   })
  	  		}else if(i>=6){
				$("input[name='type']").eq(1).removeAttr('checked');
  	  	  	}
  	  	  	if(i<6)$("input[name='type']").eq(2).removeAttr('checked');
            $(this).parent().parent().find("input[name='dignity']").each(function(){
	  			 if( dignity.indexOf($(this).val())>-1&&dignity!=""){
					 	$(this).attr('checked',"checked");
				 	}
	  		   })
    	}
    })
	   
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

