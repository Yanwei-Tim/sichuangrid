<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div  class="container container_24">
<input type="hidden" value="${isCopySelect}" id="isCopySelect">
<c:choose>
	<c:when test="${fn:length(myGroups)>0 }">
	<input type="hidden" value="${contacterId}" id="groupParentId">
	<table id="myGroups" style="width:400px;" border="1px" align="center">
		<tr style="height:30px">
			<td align="center" style="width:100px;" >
				<input type="checkbox"  id="selectAll"/>
			</td>
			<td align="center" width="200px;">
				<span style="font-weight:bold">用户姓名</span>
			</td>
			<td align="center" width="200px;">
				<span style="font-weight:bold">用户名</span>
			</td>
		</tr>
		<s:iterator value="myGroups">
			<tr style="height:30px">
				<td><input type="checkbox" name="memberId" memberName="${name}" memberUserName="${userName}" id="member_${id}" value="${id}" onclick="selectAll()"/></td>
				<td align="center">${name}</td>
				<td align="center">${userName}</td>
			</tr>
		</s:iterator>
	</table>
	</c:when>
	<c:otherwise>
		<font color="red">该群组暂无联系人</font>
	</c:otherwise>
	</c:choose>
</div>

<script type="text/javascript">
function selectAll(){
	var isSelectAll = true;
	$("input:checkbox[name=memberId]").each(function(){
		if(this.checked==false){
			isSelectAll = false;
			return;
		}
	});
	if(isSelectAll){
		 $("[id='selectAll']").attr("checked",true);
	}else{
		 $("[id='selectAll']").attr("checked",false);
	}
}

$("#selectAll").click(function(){
	var c = $("[id='selectAll']").attr("checked");
	if(!c){
		$("input:checkbox[name=memberId]").each(function(){
			this.checked=false;
		});
	}else{
		$("input:checkbox[name=memberId]").each(function(){
			this.checked=true;
		}); 
	}
});
function getCheckboxValue(){
	isSelectAllWorkContacts();
	var checkID;
	 var spCodesTemp = "";
     $("input:checkbox[name=memberId]:checked").each(function(i){
      if(0==i){
       spCodesTemp = $(this).val();
      }else{
       spCodesTemp += (","+$(this).val());
      }
     });
     if(spCodesTemp!='' && spCodesTemp.length!=0){
    	 var checkIDs = spCodesTemp.split(",");
    	 var dataId;
    	 if($("#isCopySelect").val()=='true'||$("#isCopySelect").val()==true){
    		 dataId = $("#userReceiversCopy").attr("value");
    	 }else{
    		 dataId = $("#userReceivers").attr("value"); 
    	 }
    	 checkID = validateRepeat(checkIDs,dataId);
    	 for(var i=0;i<checkID.length;i++){
    		 memberName = $("#member_"+checkID[i]).attr("memberName");
    		 memberUserName = $("#member_"+checkID[i]).attr("memberUserName");
    		 contacterId = $("#groupParentId").attr("value");
        	$("#orgLevelContactersList ul").prepend('<li id="'+checkID[i]+'" parentId="'+contacterId+'" class="group" key="myGroup">'+memberName+'    '+memberUserName+'<a href="javascript:;" class="delete">删除</a></li>');
        	if($("#isCopySelect").val()=='true'||$("#isCopySelect").val()==true){
        		 $("#userReceiversCopy").data(checkID[i],memberName);
          		 $("#userReceiversCopy").setPersonnelCompleteVal({
          			value :checkID[i],
          			label :memberName,
          			desc : ""
          		});
        	 }else{
        		 $("#userReceivers").data(checkID[i],memberName);
          		$("#userReceivers").setPersonnelCompleteVal({
          			value :checkID[i],
          			label :memberName,
          			desc : ""
          		});
        	 }
         } 
     }
     return checkID;
}

function validateRepeat(checkIds,dataIds){
	var repeatCheckedId = new Array();
	var ids = dataIds.split(",");
	for(var i=0;i<checkIds.length;i++){
		var flag=true;
		for(var j=0;j<ids.length;j++){
			  if(parseInt(checkIds[i]) == ids[j]) { 
		            flag = false;   
			  }
		}
		if(flag){
			repeatCheckedId.push(checkIds[i]);
		}
	}
	return repeatCheckedId;
}

function isSelectAllWorkContacts(){
	var isChecked=true;
	$("#myGroups td:gt(0) input").each(function(){
		if($(this).attr("checked")==undefined){
			isChecked=false;
			return;
		}
	});
	$("#selectAll").attr("checked",isChecked);
}
</script>