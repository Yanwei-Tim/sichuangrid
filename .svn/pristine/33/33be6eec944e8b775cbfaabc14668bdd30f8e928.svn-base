<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="changeDialog-form" title="重置户口号" class="container container_24">
    <form id="changeFamilyMaster-form" action="${path}/baseinfo/familyInfo/changeFamilyMaster.action">
    <pop:token />
         <input type="hidden" id="groupFamilyId" name="groupFamilyId" value="${groupFamilyId }">
         
    	 <div class="grid_8 lable-right">
         	<label class="form-lbl">原家长姓名：</label>
    	 </div>
    	 <div class="grid_1"></div>
    	 <div class="grid_15 lable-lef" >
    	     <input type="text" class="form-txt" name="previousFamilyMaster" value="${previousFamilyMaster }" disabled="disabled">
    	 </div>
    	 <div class="grid_8 lable-right">
    	    <em class="form-req">*</em>
         	<label class="form-lbl">新家长姓名：</label>
    	 </div>
    	 <div class="grid_1"></div>
    	 <div class="grid_15 lable-lef" >
    	    <select name="newFamilyMaster" 
				id="newFamilyMaster" class="form-select" >
					<option value="" selected="selected" >请选择</option>
			 		<s:iterator value="groupFamilyHasPopulations" var="groupFamilyHasPopulation">
			 			<option value="${groupFamilyHasPopulation.populationId },${groupFamilyHasPopulation.populationType }">${groupFamilyHasPopulation.population.name }</option>
			 		</s:iterator>
				
			</select>
    	 </div>
    	 <div class="grid_8 lable-right">
    	    <em class="form-req">*</em>
         	<label class="form-lbl">原家长与新家长的关系：</label>
    	 </div>
    	 <div class="grid_1"></div>
    	 <div class="grid_15 lable-lef" >
    	     <select name="familyRelationId" 
				id="familyRelationId" class="form-select" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_MASTER"  exceptInternalIds="@com.tianque.domain.property.RelationShipWithHead@HEADER"/>
			</select>
    	 </div>
    	
    </form>
</div> 
<script>
     $(document).ready(function(){
    	 $("#changeFamilyMaster-form").formValidate({
 			promptPosition: "bottomLeft",
 			submitHandler: function(form){
 			   if($("#familyRelationId").val()==""||$("#familyRelationId").val()==null){
 					$.messageBox({message:"原家长与新家长的关系不能为空！",level:"warn"});
 					return ;
 				}
 				if($("#newFamilyMaster").val()==""||$("#newFamilyMaster").val()==null){
 					$.messageBox({message:"新家长姓名不能为空！",level:"warn"});
 					return ;
 				} 
 			     $("#changeFamilyMaster-form").ajaxSubmit({
 					success:function(data){
 						if(data==null||data==""){
 							$.messageBox({message:"更换家长成功！"});
 							 $("#changeFamilyMasterDlg").dialog("close");
 							$("#groupFamilyList").trigger("reloadGrid");
 						}else{
 							$.messageBox({message:data,level:"error"});
 						}
 					},
 					error:function(XMLHttpRequest, textStatus, errorThrown){
 			      			$.messageBox({message:"提交数据时发生错误！",level:"error"});
 		   		    }
 				});  
 			}
 		});
     });
</script>