<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="search-condition-form" title="统计分析基础信息重新生成" class="container container_24">
<form id="maintainForm" method="post" action="">
   		<div>
			<div class="grid_8 lable-right">
				<label class="form-lbl">开始时间：</label>
			</div>
			<div class="grid_16 form-left">
		    	<select name="replaceYear" id="replaceYear">
		    		<option value="0" selected="selected"></option>
		    		<s:iterator begin="minYear" end="maxYear" var="newMinYear">
		    			<option value="${newMinYear }">${newMinYear }</option>
		    		</s:iterator>
		    	</select>年
		    	<select name="replaceMonth" id="replaceMonth" >
		    		<option value="0" selected="selected"></option>	
		    		<s:iterator begin="1" end="12" var="newMinMonth">
		    			<option value="${newMinMonth }">${newMinMonth }</option>
		    		</s:iterator>
		    	</select>月
			</div>
	</div>
  </form>
</div>