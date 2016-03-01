<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<form id="printOptionsForm" method="post">
<table class="tablelist">
	
</table>
</form>

<script>
$(document).ready(function(){
	var options = $("#printOptionsForm table");
	var optionsStr='';
	var disabledStr;
	$("#floatingPopulation li").each(function(index) {
	     //alert(index + ': ' + $(this).text());
	     //alert(index%2);
	     //alert($(this).find("a").attr("href")) ;
	     if(index==0){
			disabledStr ='checked="checked" disabled="disabled"';
		 }else{
			disabledStr ='';
		 }
	     if(index%2==0) optionsStr+='<tr>';
	     optionsStr+='<td><label>'+$.trim($(this).text())+'：</label><input type="checkbox" '+disabledStr+' name="printOptions" value ="'+$(this).find("a").attr("href")+'"></td>';
	     if(index>0 && index%2!=0) optionsStr+='</tr>';
	});
	options.append(optionsStr);
});
</script>