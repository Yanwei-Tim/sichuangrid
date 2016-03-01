<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="maintainForm" method="post"action="${path }/baseinfo/newVillageBasicManage/maintainNewVillageBasic.action"  >

<input type="hidden" name="newVillageBasic.organization.id" value="${organization.id }"/>
<input type="hidden" name="newVillageBasic.isPlaning" value="${isPlaning }"/>
<div class="grid_1 lable-right" style="margin-left: 30px;">
	<em class="form-req" id="maritalStateStat" >*</em>
	<label class="form-lbl">年份：</label>
</div>
<div class="grid_3">
<select name="newVillageBasic.year" id="year" onchange="" class="form-txt {required:true,messages:{required:'年份必须选择'}}" style="width: 100px;">
</select>
</div>
<s:if test="isPlaning==0">
	 <div class='clearLine'></div>		
	<div class="grid_1 lable-right" style="margin-left: 30px;">
		<em class="form-req" id="maritalStateStat" >*</em>
		<label class="form-lbl">季度：</label>
	</div>
	<div class="grid_3">
	<select name="newVillageBasic.quarter" id="quarter" onchange="" class="form-txt {required:true,messages:{required:'季度必须选择'}}" style="width: 100px;">
	</select>
	</div>	 
</s:if>

 </form>
<script>

function getQuarter(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForQuarter.action?currenYear="+$("#year").val(),
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#quarter").append("<option  value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}

$(document).ready(function(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getQuarter();
		}
	});
	
	$("#year").change(function(){
		$("#quarter").empty();
		getQuarter();
	});
	
	
	$(document).ready(function(){
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data.id!=null && data.id!=""){
								$("#addNewVillageBasicDialog").createDialog({
									width: 1000,
									height: 500,
									title:${isPlaning}==0?'新增上报信息':'新增年度任务规划',
									url:'${path}/baseinfo/newVillageBasicManage/dispatchOpearte.action?organization.id='+getCurrentOrgId()+'&mode=add&newVillageBasic.id='+data.id+'&isPlaning=${isPlaning}',
									buttons: {
// 								   		"保存" : function(event){
// 								   			$("#maintainFormOfAdd").submit();
// 								   		},
								   		"关闭" : function(){
								        	$(this).dialog("close");
								   		}
									}
								});		
								$("#newVillageBasicDialog").dialog("close");
								$("#basicInfoMationList").trigger("reloadGrid");
						}else{
							 $.messageBox({message:data,level:'error'});
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
			},
			messages:{
			}
		});
	});
	
})

</script>