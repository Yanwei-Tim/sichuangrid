<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container container_24">
	<form id="claimRegionalBuildInfoForm" method="post" action="${path}/partyBuilding/regionalBuildInfoManage/claimRegionalBuildInfo.action">	
		<pop:token/>
		<input type="hidden" name="claimRegionalBuildInfo.regionalBuildInfoId" id="regionalBuildInfoId" class="form-txt" value="${claimRegionalBuildInfo.regionalBuildInfoId}"/>
				<input type="hidden" name="claimRegionalBuildInfo.id" id="claimRegionalBuildInfoId"   class="form-txt" value="${claimRegionalBuildInfo.id}"/>
		<input type="hidden" name="claimMode" id="claimMode"  class="form-txt" value="${claimMode}"/>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>认领单位：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="claimRegionalBuildInfo.claimDepartment" id="claimDepartment"  maxlength="60" class="form-txt {required:true,messages:{required:'输入认领单位'}}" value="${claimRegionalBuildInfo.claimDepartment}"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>认领时间：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="claimDate" name="claimRegionalBuildInfo.claimDate" class="form-txt {required:true,messages:{required:'输入认领时间'}}" readonly="readonly" value="<fmt:formatDate value="${claimRegionalBuildInfo.claimDate}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" />
		</div>
	</form>
</div>
<script type="text/javascript">
$('#claimDate').datePicker({
	yearRange:'1900:2030',
	dateFormat:'yy-mm-dd',
	maxDate:'+0d',
   	onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			
		}
	}
});
	$(document).ready(function(){
		
		$("#claimRegionalBuildInfoForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						if(!data.id){
	           	 			$.messageBox({
								evel: "error",
								message:data
				 			});
	            			return;
						}
						
		                if("add"=="${claimMode}"){
		                	 $.messageBox({message:"区域联建情况认领成功！"});
		                	 $("#regionalBuildInfoList").trigger("reloadGrid");
		                	 $("#regionalBuildInfoDialog").dialog("close");
		                }
		                if("edit"=="${claimMode}"){
		                	 $("#claimRegionalBuildInfoList").trigger("reloadGrid");
		                	 $.messageBox({message:"区域联建情况认领记录修改成功！"});
		                	 $("#claimRegionalBuildInfoDialog").dialog("close");
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
</script>