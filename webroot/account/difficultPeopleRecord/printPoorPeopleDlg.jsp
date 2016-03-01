<%@page import="com.tianque.plugin.account.property.PropertyTypes"%>
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
<input type="hidden" id="poorPeopleMemberSize" value="${fn:length(ledgerPoorPeople.ledgerPoorPeopleMembers)}" />

<div id="poorPeoplePrint" style="overflow:hidden;overflow-y:auto;">
	<style>
	.tablelist{margin-top: 5px;width:96%; border-left:1px solid #CCC; border-top:1px solid #CCC; border-collapse:collapse;background:#fff;}
	.tablelist td{height:26px;line-height:26px;text-indent:5px;border-right:1px solid #ccc;border-bottom:1px solid #ccc; border-collapse:collapse; word-break:break-all; word-warp:break-word;}
	.tablelist .btitle{background:#ECF1F8; font:bold 12px/26px ""; padding:0 0 0 5px;}
	.tablelist .title{width:14%;font:normal 12px/26px ""; color:#000; padding:0 0 0 5px;}
	.propertyMulSelect{overflow:hidden;}
	.propertyMulSelect li{float:left;list-style: outside none none;}
	</style>
	
	<table id="issueAccordingPrint" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    	<jsp:include page="${path}/account/difficultPeopleRecord/printPoorPeopleHeadDlg.jsp"/>
    	
    	 <table width="96%" border="0" cellpadding="10" cellspacing="0" style="margin:0 0 0 0;text-align:center;border-collapse:collapse;width:96%;" class="tablelist">
        	<tr class="tr1">
        		<td id="_appealHumanType" width="7%" style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" rowspan=6>
        		   <br/><br/> 户主<br/>
        		   <input type="checkbox" name="isPartyMember" value="1" <s:if test="ledgerPoorPeople.owner">checked</s:if> disabled/>
        			<br/>困难者<br/>
        		   <input type="checkbox" name="isPartyMember" value="0"  disabled/>
        		</td>
        		
        		<td width="7%" style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >姓名</td>
	            <td width="9%" style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;">${ledgerPoorPeople.name}</td>
	            <td width="7%" style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">性别</td>
	            <td id="_gender" width="13%" style="text-align: left;font-Size:10px;height:20px;white-space: nowrap;" >
	                                           男<input type="checkbox" name="gender" value="男">女<input type="checkbox" name="gender" value="女">不明<input type="checkbox" name="gender" value="不明"> 
	            </td>
	            <td width="8%" style="text-align:left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >出生年月</td>
	            <td width="9%" style="text-align:left;font-Size:10px; height:20px;white-space: nowrap;"><s:date name="ledgerPoorPeople.birthDay" format="yyyy-MM-dd" /></td>
	            <td width="8%" style="text-align:left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >困难程度</td>
	            <td width="9%" style="text-align:left;font-Size:10px; height:20px;white-space: nowrap;">${ledgerPoorPeople.difficultyDegree}</td>
	            <td width="8%" style="text-align:left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >关注程度</td>
	            <td width="9%" style="text-align:left;font-Size:10px; height:20px;white-space: nowrap;">${ledgerPoorPeople.attentionDegree}</td>
        	</tr>
        	<tr class="tr1">
        		<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >常住地址</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" colspan="4">${ledgerPoorPeople.permanentAddress}</td>
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >户籍地址</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" colspan="4">${ledgerPoorPeople.censusRegisterAddress}</td>
        	</tr>
        	<tr class="tr1">
        	    <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >户籍性质</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" colspan="2">${ledgerPoorPeople.censusRegisterNature}</td>
        		<td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" colspan=3>是否党员：是
        		<input type="checkbox" name="isPartyMember" value="1" <s:if test="ledgerPoorPeople.isPartyMember == true">checked</s:if> disabled/>
        		否
        		<input type="checkbox" name="isPartyMember" value="0" <s:if test="ledgerPoorPeople.isPartyMember == false">checked</s:if> disabled/>
        		</td>
        		<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >民族</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" >${ledgerPoorPeople.national.displayName}</td>
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >文化程度</td>
	            <td style="text-align: left;font-Size:10px; height:20px;">${ledgerPoorPeople.levelEducation.displayName}</td>
        	</tr>
        	<tr class="tr1">
        	    <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >身份证号码</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" colspan="5">${ledgerPoorPeople.idCardNo}</td>
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >家庭人口</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" >${ledgerPoorPeople.memberNo}</td>
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >婚姻状况</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" >${ledgerPoorPeople.maritalStatus.displayName}</td>
        	</tr>
        	<tr class="tr1">
        		<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >职业或身份</td>
	            <td id="_position" style="text-align: left;font-Size:10px; height:20px;" colspan="5">
	                                      教师<input type="checkbox" name="position" value="教师"> 医生<input type="checkbox" name="position" value="医生"> 公务人员<input type="checkbox" name="position" value="公务人员">
	                                      企业人员 <input type="checkbox" name="position" value="企业人员 "> 学生<input type="checkbox" name="position" value="学生"> 城镇居民<input type="checkbox" name="position" value="城镇居民">                    
	    		      在家农民 <input type="checkbox" name="position" value="在家农民">外出农民 <input type="checkbox" name="position" value="外出农民">其他<input type="checkbox" name="position" value="其他"></td>
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >健康状况</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" >${ledgerPoorPeople.healthCondition}</td>
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >联系电话</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" >${ledgerPoorPeople.mobileNumber}</td>
        	</tr>
        	
        	<tr class="tr1">
        		<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >人均年收入</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" >${ledgerPoorPeople.annualPerCapitaIncome}</td>
        		<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >纳入</td>
	            <td id="_securityType" style="text-align: left;font-Size:10px; height:20px;" colspan="3">
	                                 城镇低保<input type="checkbox" name="security" value="城镇低保">农村低保<input type="checkbox" name="security" value="农村低保">
	                                 农村五保<input type="checkbox" name="security" value="农村五保">
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" colspan = 2>是否外出及外出原因</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" colspan=2>${ledgerPoorPeople.goOutReason}</td>
        	</tr>
        <div id="copyAddFamilyMembersParent">
        	<tr class="tr1">
        		<td id="ledgerPoorPeopleMembers" style="text-align: center;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;" rowspan=2>
        		<p>家</p><p>庭</p><p>成</p><p>员</p>
        		</td>
        		<c:forEach items="${ledgerPoorPeople.ledgerPoorPeopleMembers}" var="ledgerPoorPeopleMembers" varStatus="remember">
        		<c:if test="remember.index > 0"><tr class="tr1" ></c:if>
        		<td id = "membersName" style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" rowspan=2>姓名</td>
	            <td id = "membersNameValue" style="text-align: left;font-Size:10px; height:20px;" rowspan=2>${ledgerPoorPeopleMembers.name }</td>
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';font-weight:750;white-space: nowrap;" >纳入</td>
	            <td style="text-align: left;font-Size:10px; height:20px;" colspan="3">
	            	城镇低保<input type="checkbox" name="members_security" value="城镇低保" <c:if test="${ledgerPoorPeopleMembers.securityType.displayName == '城镇低保'}">checked</c:if> >
	            	农村低保<input type="checkbox" name="members_security" value="农村低保" <c:if test="${ledgerPoorPeopleMembers.securityType.displayName == '农村低保'}">checked</c:if>>
	                                           农村五保<input type="checkbox" name="members_security" value="农村五保" <c:if test="${ledgerPoorPeopleMembers.securityType.displayName == '农村五保'}">checked</c:if>>
	            </td>
	            <td style="text-align:left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >出生年月</td>
	            <td style="text-align:left;font-Size:10px; height:20px;white-space: nowrap;"><fmt:formatDate value="${ledgerPoorPeopleMembers.birthday }" pattern='yyyy-MM-dd'/></td>
        		<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">与户主关系</td>
	            <td style="text-align: left;font-Size:10px; height:20px;">${ledgerPoorPeopleMembers.headHouseholdRelation }</td>
	            </tr>
	            
        	<tr class="tr1" >
        		<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">性别</td>
	            <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" colspan=2>
	                                          男<input type="checkbox" name="members_gender" value="男" <c:if test="${ledgerPoorPeopleMembers.gender.displayName == '男'}">checked</c:if>>  
	                                          女<input type="checkbox" name="members_gender" value="女" <c:if test="${ledgerPoorPeopleMembers.gender.displayName == '女'}">checked</c:if>> 
	                                          不明<input type="checkbox" name="members_gender" value="不明" <c:if test="${ledgerPoorPeopleMembers.gender.displayName == '不明'}">checked</c:if> > 
	            </td>
	            <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" colspan=3>是否失业：是
        			<input type="checkbox" name="members_isPartyMember" value="1" <c:if test="${ledgerPoorPeopleMembers.unemployment == true}">checked</c:if> disabled/>
        			否
        			<input type="checkbox" name="members_isPartyMember" value="0" <c:if test="${ledgerPoorPeopleMembers.unemployment == false}">checked</c:if> disabled/>
        		</td>
        		<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >健康状况</td>
	            <td  style="text-align: left;font-Size:10px; height:20px;">${ledgerPoorPeopleMembers.healthCondition }</td>
        	</tr>
        	</c:forEach>
        </div>
        
            <tr class="tr1" >
            	<td id="ledgerPoorPeopleMembers" style="text-align: center;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;" rowspan=8>
        		<p>困</p><p>难</p><p>类</p><p>型</p>
        		</td>
        		 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;">生活
        		  <input type="checkbox" name="difficult" value="生活" disabled/>
        		 </td>
        		 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" colspan=5>
        		          因病<input type="checkbox" name="difficultCause" value="因病" disabled/> 因残<input type="checkbox" name="difficultCause" value="因残" disabled/> 因灾<input type="checkbox" name="difficultCause" value="因灾" disabled/><br>
        		          缺乏劳动能力<input type="checkbox" name="difficultCause" value="缺乏劳动能力" disabled/>其他<input type="checkbox" name="difficultCause" value="生活-其他" disabled/>
        		 </td>
        		 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;">是否孤儿：是
        			<input type="checkbox" name="isPartyMember" value="1" <s:if test="ledgerPoorPeople.orphan == true">checked</s:if> disabled/>
        			否
        			<input type="checkbox" name="isPartyMember" value="0" <s:if test="ledgerPoorPeople.orphan == false">checked</s:if> disabled/>
        		 </td>
        		 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;">是否孤老：是
        			<input type="checkbox" name="isPartyMember" value="1" <s:if test="ledgerPoorPeople.lonelinessOld == true">checked</s:if> disabled/>
        			否
        			<input type="checkbox" name="isPartyMember" value="0" <s:if test="ledgerPoorPeople.lonelinessOld == false">checked</s:if> disabled/>
        		 </td>  
        		 <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">技能特长</td>
	             <td style="text-align: left;font-Size:10px; height:20px;">${ledgerPoorPeople.skillsSpeciality}</td>        		       		 
            </tr>
            <tr class="tr1" >
            	 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;">医疗
        		  <input type="checkbox" name="difficult" value="医疗" disabled/>
        		 </td>
        		 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" colspan=9>
        		          重大疾病<input type="checkbox" name="difficultCause" value="重大疾病" disabled/> 
        		          其他<input type="checkbox" name="difficultCause" value="医疗-其他" disabled/> 
        		 </td>
            </tr>
            <tr class="tr1" >
                 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" rowspan = 2>住房
        		  <input type="checkbox" name="difficult" value="住房" disabled/>
        		 </td>
        		 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" rowspan = 2 colspan=5>
        		          水灾<input type="checkbox" name="difficultCause" value="水灾" disabled/> 地灾<input type="checkbox" name="difficultCause" value="地灾" disabled/> 火灾<input type="checkbox" name="difficultCause" value="火灾" disabled/><br>
        		         贫困<input type="checkbox" name="difficultCause" value="贫困" disabled/>危房改造<input type="checkbox" name="difficultCause" value="危房改造" disabled/>其他<input type="checkbox" name="difficultCause" value="住房-其他" disabled/>
        		 </td>
        		  <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" colspan=2>有无住房：有
        			<input type="checkbox" name="isPartyMember" value="1" <s:if test="ledgerPoorPeople.housing == true">checked</s:if> disabled/>
        			无
        			<input type="checkbox" name="isPartyMember" value="0" <s:if test="ledgerPoorPeople.housing == false">checked</s:if> disabled/>
        		 </td>  
        		 <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">住房结构</td>
	             <td  style="text-align: left;font-Size:10px; height:20px;">${ledgerPoorPeople.housingStructure}</td>            		 
            </tr>
            <tr class="tr1" >
            	<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">住房面积</td>
	            <td style="text-align: left;font-Size:10px; height:20px;">${ledgerPoorPeople.housingArea}</td>          
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">建房年月</td>
	            <td style="text-align: left;font-Size:10px; height:20px;"><s:date name="ledgerPoorPeople.buildHouseDate" format="yyyy-MM-dd" /></td>          
            </tr>
            <tr class="tr1" >
            	 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;">就学
        		  <input type="checkbox" name="difficult" value="就学" disabled/>
        		 </td>
        		 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" colspan=7>
        		          学前教育<input type="checkbox" name="difficultCause" value="学前教育" disabled/>小学<input type="checkbox" name="difficultCause" value="小学" disabled/>初中 <input type="checkbox" name="difficultCause" value="初中" disabled/> 
        		           高中职中<input type="checkbox" name="difficultCause" value="高中职高" disabled/>大学<input type="checkbox" name="difficultCause" value="大学" disabled/>特殊教育<input type="checkbox" name="difficultCause" value="特殊教育" disabled/>其他<input type="checkbox" name="difficultCause" value="就学-其他" disabled/> 
        		 </td>
        		 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" colspan=2>是否孤儿：是
        			<input type="checkbox" name="isPartyMember" value="1" <s:if test="ledgerPoorPeople.orphan == true">checked</s:if> disabled/>
        			否
        			<input type="checkbox" name="isPartyMember" value="0" <s:if test="ledgerPoorPeople.orphan == false">checked</s:if> disabled/>
        		 </td>
            </tr>
            <tr class="tr1" >
                 <td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;" rowspan = 2>就业
        		  <input type="checkbox" name="difficult" value="就业" disabled/>
        		 </td>
        		 <td style="text-align: left;font-Size:10px; height:20px;" rowspan = 2 colspan=5>
        		          城镇登记失业人员<input type="checkbox" name="difficultCause" value="城镇登记失业人员" disabled/>  “4050”人员<input type="checkbox" name="difficultCause" value="4050人员" disabled/>  残疾人员<input type="checkbox" name="difficultCause" value="残疾人员" disabled/>
        		           低收入家庭人员<input type="checkbox" name="difficultCause" value="低收入家庭人员" disabled/>  按城镇人口安置的被征地农民<input type="checkbox" name="difficultCause" value="按城镇人口安置的被征地农民" disabled/>
        		            连续失业一年以上的人员<input type="checkbox" name="difficultCause" value="连续失业一年以上的人员" disabled/>  土地（林地）被依法征用或流转的农村劳动者<input type="checkbox" name="difficultCause" value="土地（林地）被依法征用或流转的农村劳动者" disabled/>  
        		            其他<input type="checkbox" name="difficultCause" value="就业-其他" disabled/> 
        		 </td>
        		 <td style="text-align:left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" >失业时间</td>
	             <td style="text-align:left;font-Size:10px; height:20px;white-space: nowrap;"><s:date name="ledgerPoorPeople.unemploymentDate" format="yyyy-MM-dd" /></td>
        		 <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">失业原因</td>
	             <td  style="text-align: left;font-Size:10px; height:20px;">${ledgerPoorPeople.unemploymentReason}</td>            		 
            </tr>
            <tr class="tr1" >
            	<td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">登记证号</td>
	            <td style="text-align: left;font-Size:10px; height:20px;">${ledgerPoorPeople.registrationCardNumber}</td>    
	            <td style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;">技能特长</td>
	            <td style="text-align: left;font-Size:10px; height:20px;">${ledgerPoorPeople.skillsSpeciality}</td>    
            </tr>
            <tr class="tr1" >
            	<td style="text-align: left;font-Size:10px; height:20px;white-space: nowrap;">
                 	其他<input type="checkbox" name="difficult" value="其他困难" disabled/>
                </td>
	            <td  style="text-align: left;font-Size:10px; height:20px;" colspan=9>${ledgerPoorPeople.otherInfo}</td>    
	         </tr>
	          <tr class="tr1" >
            	<td  style="text-align: left;font-Size:16px; font-family:'黑体';height:20px;font-weight:750;white-space: nowrap;" colspan=2>具体需求</td>
	            <td  style="text-align: left;font-Size:10px; height:20px;" colspan=9>
	                                         口粮<input type="checkbox" name="required" value="口粮" disabled/>&nbsp;&nbsp; 五保<input type="checkbox" name="required" value="五保" disabled/>&nbsp;&nbsp; 低保 <input type="checkbox" name="required" value="低保" disabled/>&nbsp;&nbsp;
	                                         救助资金<input type="checkbox" name="required" value="救助资金" disabled/>&nbsp;&nbsp;&nbsp; 救助物资<input type="checkbox" name="required" value="救助物资" disabled/>&nbsp;&nbsp;&nbsp; 住房<input type="checkbox" name="required" value="住房" disabled/>&nbsp;&nbsp;&nbsp;
    			         职业培训<input type="checkbox" name="required" value="职业培训" disabled/>&nbsp;&nbsp;&nbsp;职业指导<input type="checkbox" name="required" value="职业指导" disabled/>&nbsp;&nbsp;&nbsp;求职登记<input type="checkbox" name="required" value="求职登记" disabled/>&nbsp;&nbsp;&nbsp;
    			         职业介绍<input type="checkbox" name="required" value="职业介绍" disabled/>&nbsp;&nbsp;&nbsp;其他<input type="checkbox" name="required" value="其他" disabled/>
	            </td>    
	         </tr>
        	
    </table>
       
      <jsp:include page="${path}/account/peopleAspiration/printWorkLogDlg.jsp"/>
    
</table>
</div>
<script type="text/javascript">
var createType='${ledgerPoorPeople.createTableType.displayName}';
var gender='${ledgerPoorPeople.gender.displayName}';
var position ='${ledgerPoorPeople.position.displayName}';
var securityType = '${ledgerPoorPeople.securityType.displayName}';
var requiredType = '${ledgerPoorPeople.requiredTypeName}';
var difficultTypeName = '${ledgerPoorPeople.poorTypeName}';
var difficultCauseName = '${ledgerPoorPeople.poorSourceName}';

$(document).ready(function(){
	initTitleShowValue("中江县困难群众信息登记卡");
	
   $("input[name='createType']").each(function(){
        if($(this).val() ==createType) 
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
   $("input[name='security']").each(function(){
        if($(this).val() ==securityType) 
           $(this).attr('checked',"checked");
   }); 
   $("input[name='required']").each(function(){
	   if(requiredType!=null){
		   var types = requiredType.split(',');
			for(var i=0 ;i<types.length;i++){
			  if(types[i]== $(this).val()){
				  $(this).attr("checked","checked");
			  }
			}
		}
  }); 
   $("input[name='difficult']").each(function(){
	   if(difficultTypeName!=null){
		   var types = difficultTypeName.split(',');
			for(var i=0 ;i<types.length;i++){
			  if(types[i]== $(this).val()){
				  $(this).attr("checked","checked");
			  }
			}
		}
  }); 
   $("input[name='difficultCause']").each(function(){
	   if(difficultCauseName!=null){
		   var types = difficultCauseName.split(',');
			for(var i=0 ;i<types.length;i++){
			  if(types[i]== $(this).val()){
				  $(this).attr("checked","checked");
			  }
			}
		}
  }); 
   var poorPeopleMemberSize =  $("#poorPeopleMemberSize").val();
   if(poorPeopleMemberSize > 1){
	 var rowspan = parseInt($("#ledgerPoorPeopleMembers").attr("rowspan")) * poorPeopleMemberSize ;
	 $("#ledgerPoorPeopleMembers").attr("rowspan", rowspan);
   }
   $("#intoOtherParameter").empty();
   $("#intoOtherParameter").append("民生工作台账 </br>稳定工作台账 </br>");
})
</script>

