<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="矫正人员维护" class="container container_24">
<div id="tabs" style="height:430px;">
	<div id="rectifive" class="container container_24">
	 <form id="maintainForm" name="maintainForm" method="post"	action="${path}/baseinfo/rectificativePersonManage/maintainBusinessInfo.action">
	 <pop:token />
	<input id="mode" type="hidden" name="mode" value="${mode}" />
    <input id="cacheIdId" type="hidden" name="cacheId.id" value="${cacheId.id}" />
    <input id="updateType" type="hidden" name="updateType" value="${updateType}" />
    <input id="population" type="hidden" name="population.id" value="${population.id}" />
    <input id="population.organization.id" type="hidden" name="population.organization.id" value="${population.organization.id}" />
    <input id="populationImgUrl" type="hidden" name="population.imgUrl" value="${population.imgUrl}" />
    <input id="isEmphasis"	type="hidden" name="population.isEmphasis" value="${population.isEmphasis}" />
    <input type="hidden" name="contextId"  value="${contextId}" />
	<jsp:include page="/baseinfo/commonPopulation/populationInfo/rectificativePerson.jsp"/>
	 </form>

	</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$(".dialogtip").inputtip();

	<c:if test='${mode!="view"}'>
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
		$("#populationImgUrl").val($("#imgUrl").val());
         $(form).ajaxSubmit({
        	 async:false,
             success: function(data){
             	if(!data.id){
                	$.messageBox({
						message:data,
						level: "error"
					});
                    return;
                }
                data["organization.orgName"] = data.organization.orgName;
                if("add"==$("#mode").val()){
                	if($("#isLock").val()!='1'){
        				$("#rectificativePersonList").addRowData(data.id,data,"first");
        				$("#rectificativePersonList").setSelection(data.id);
        			}
                	doAction("${dailogName}",data.id);
        		    $("#rectificativePersonList").trigger("reloadGrid");
        		    $.messageBox({message:"新增矫正人员成功！"});
        		}
				if("edit"==$("#mode").val()){
        			$("#rectificativePersonList").setRowData(data.id,data);
        			if(data.death){
	                	if($("#isLock").val()=='0') {
	                		$("#rectificativePersonList").delRowData(data.id);
						}else {
							$("td[aria-describedby='rectificativePersonList_name']").css('color','red');
							$("td[aria-describedby='rectificativePersonList_idCardNo']").css('color','#778899');
							$("#"+data.id+"").css('color','#778899');
						}
					}
        			$("#rectificativePersonList").trigger("reloadGrid");
        			$.messageBox({message:"修改矫正人员成功！"});
        			doAction("${dailogName}",data.id);
        		}
        		// $("#rectificativePersonList").setSelection(data.id);
      	   },
      	   error: function(XMLHttpRequest, textStatus, errorThrown){
      	      alert("提交数据时发生错误");
      	   }
		});
	},
	rules: {
		"population.bussinessRemark":{
		    maxlength: 200
		}
	},
	messages: {
		"population.bussinessRemark":{
		    maxlength  : $.format("最多只能输入{0}个字符")
		},
		"population.nativePoliceStation":{
			maxlength  : $.format("最多需要{0}个字符")
		}
	}
	});
	</c:if>
});

</script>