<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ page  import="com.tianque.baseInfo.actualHouse.domain.HouseInfo" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css"> 
.AutoNewline 
{ 
  Word-break: break-all;/*必须*/ 
  width :50px;/*这里是设置多宽就进行换行 */ 
} 
</style> 
<div class="container_24">
	<div class='grid_24'>
	<form id="megerForm" action="" method="post">
	<input type="hidden" name="houseInfo.id" value="${houseInfo.id}"/>
	<input type="hidden" name="megerId" value="${megerId}"/>
	<input type="hidden" name="houseInfo.organization.id" value="${houseInfo.organization.id}"/>
	 	<table id="house" style="width: 580px;">
	 		<tr><td class="AutoNewline" style="width: 150px;font-weight:bold;font-size:large;">字段信息</td>
	 			<td class="AutoNewline" style="width: 200px;font-weight:bold;font-size:large;">主房屋信息</td>
	 			<td class="AutoNewline" style="width: 50px;font-weight:bold;font-size:large;"></td>
	 			<td class="AutoNewline" style="width: 200px;font-weight:bold;font-size:large;">合并房屋对比信息</td>
	 			<td class="AutoNewline" style="width: 50px;font-weight:bold;font-size:large;"></td>
	 		</tr>
	 		<s:iterator id="obj" value="houseInfoMap.packageHouseInfo" status="status">
	 		<tr><td  class="AutoNewline" style="width: 100px;font-weight:bold;">${obj.cName}:</td>
	 			<td class="AutoNewline" style="width: 200px;" id="main${status.count}">
	 			<s:if test="%{#obj.mainValue!=null && #obj.mainValue!=''}">
	 			${obj.mainValue}
	 			</s:if>
	 			<s:if test="%{#obj.mainValue==null || #obj.mainValue==''}">
	 			&lt;空&gt;
	 			</s:if>
	 			<input type="hidden" name="houseInfo.${obj.enName}" value="${obj.mainValue}"/>
	 			</td>
	 			
	 			<td class="AutoNewline" style="width: 50px;">
	 			<s:if test="%{#obj.mainPropertyDictId!=null && #obj.mainPropertyDictId!=''}"> 
	 			<input type="hidden" name="houseInfo.${obj.mainPropertyName}.id" value="${obj.mainPropertyDictId}" />
	 			</s:if>
	 			
	 			</td>
	 			<td class="AutoNewline" style="width: 50px;"></td>
	 		</tr>
	 		</s:iterator>
	 	</table>
	 	<table id="house2" style="width: 580px;">
	 	<s:iterator id="obj" value="houseInfoMap.packageHouseInfoDifrent" status="status">
	 	<tr>
	 	<td class="AutoNewline" style="width: 150px;font-weight:bold;">${obj.cName}:</td>
	 	<td class="AutoNewline" style="width: 200px;" id="main${status.count}">
	 			<s:if test="%{#obj.mainValue!=null && #obj.mainValue!=''}">
	 			${obj.mainValue}
	 			</s:if>
	 			<s:if test="%{#obj.mainValue==null || #obj.mainValue==''}">
	 			&lt;空&gt;
	 			</s:if>
	 	</td>
	 	<td class="AutoNewline" style="width: 50px;">
			<s:if test="%{#obj.mainPropertyDictId!=null && #obj.mainPropertyDictId!=''}"> 
	 			<input type="radio" name="houseInfo.${obj.mainPropertyName}.id" value="${obj.mainPropertyDictId}" checked/>
	 		</s:if>
	 		<s:if test="%{(#obj.mainPropertyDictId==null || #obj.mainPropertyDictId=='' )}">
	 			<s:if test="%{ (#obj.megerPropertyDictId!=null && #obj.megerPropertyDictId!='')}">
	 			<input type="radio" name="houseInfo.${obj.mainPropertyName}.id" value="${obj.mainPropertyDictId}" checked/>
	 			</s:if>
	 			<s:if test="%{(#obj.megerPropertyDictId==null || #obj.megerPropertyDictId=='' ) }">
	 			<input type="radio" name="houseInfo.${obj.enName}" value="${obj.mainValue}" checked/>
	 	    	</s:if>
	 	    </s:if>	
	 	    
		</td>
	 	<td class="AutoNewline" style="width: 200px;" id="meger${status.count}" value="${obj.megerValue}">${obj.megerValue}</td>
	 	<td class="AutoNewline" style="width: 50px;">
	 		<s:if test="%{#obj.megerPropertyDictId!=null && #obj.megerPropertyDictId!=''}">
	 		<input type="radio" name="houseInfo.${obj.mainPropertyName}.id" value="${obj.megerPropertyDictId}" />
	 		</s:if>
	 		<s:if test="%{#obj.megerPropertyDictId==null || #obj.megerPropertyDictId==''}">
	 		<input type="radio"  name="houseInfo.${obj.enName}" value="${obj.megerValue}"/>
	 		</s:if>
		</td>
	 	</tr>
	 	<s:if test="%{#obj.enName=='rentalHouse'}">
	 		<s:if test="%{#obj.isRentalVlaue!=null && #obj.isRentalVlaue!=''}">
	 			<tr>
	 			<td class="AutoNewline" style="width: 150px;font-weight:bold;"></td>
	 			<td class="AutoNewline" style="width: 200px;">${obj.isRentalVlaue}</td>
	 			<td class="AutoNewline" style="width: 50px;">
	 				<input type="radio"  name="useRentalHouse" value="0" checked/>
	 			</td>
	 			<td class="AutoNewline" style="width: 200px;">${obj.isRentalVlaue}</td>
	 			<td class="AutoNewline" style="width: 50px;">
	 				<input type="radio"  name="useRentalHouse" value="1"/>
	 			</td>
	 			</tr>
	 		</s:if>
	 	</s:if>
	 	</s:iterator>
	 	</table>
	 	
	 	</form>
	</div>
	
	</div>
