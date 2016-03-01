<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
#load {position:relative;}
#load .ui-button{width:80px;height:25px;}
#load .ui-dialog-buttonset{position:absolute;bottom:20px;right:15px;}
</style>
<div id="search-condition-form" title="统计分析服务办事类别数据重新生成" 
         class="container container_24">
         <div>
			<form id="maintainFormForReData" method="post" action="${path}/issueTypeStanal/issueTypeStanalManage/reProduceDate.action">
			   		<div>
			   		    <input type="hidden" name="orgId" id="orgId" value="${orgId }"/>
						<div class="grid_8 lable-right">
							<label class="form-lbl">生成时间：</label>
						</div>
						<div class="grid_16 form-left">
					    	<select name="year" id="replaceYear">
					    		<option value="0" selected="selected"></option>
					    		<s:iterator begin="minYear" end="maxYear" var="newMinYear">
					    			<option value="${newMinYear }">${newMinYear }</option>
					    		</s:iterator>
					    	</select>年
					    	<select name="month" id="replaceMonth" >
					    		<option value="0" selected="selected"></option>	
					    		<s:iterator begin="1" end="12" var="newMinMonth">
					    			<option value="${newMinMonth }">${newMinMonth }</option>
					    		</s:iterator>
					    	</select>月
						</div>
				</div>
			  </form>
		   <div class='clearLine'>&nbsp;</div>
		   <div class="grid_24" style="margin-top:10px;text-align:center;">
		       <label class="form-lbl heightAuto" id="templateVersion">
		           <font color="red">数据生成可能需要比较长的时间</font>
		       </label>
		  </div>
		</div>
</div>
