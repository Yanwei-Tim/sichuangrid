<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="resetDialog-form" title="重置户口号" class="container container_24">
    <form id="resetFamilyAccount-form" action="${path}/baseinfo/familyInfo/resetFamilyAccountByEncrypt.action">
    <pop:token />
         <input type="hidden" id="groupFamilyId" name="groupFamilyId" value="${groupFamily.encryptId}">
         <input type="hidden" id="previousFamilyAccount" name="previousFamilyAccount" value="${groupFamily.familyAccount }">
         
    	 <div class="grid_8 lable-right">
         	<label class="form-lbl">原家庭编号：</label>
    	 </div>
    	 <div class="grid_1"></div>
    	 <div class="grid_15 lable-lef " style="white-space: nowrap;" >
    	     <label class="form-lbl">${groupFamily.familyAccount }</label>
    	 </div>
    	 <div class="grid_8 lable-right">
         	<label class="form-lbl">家长证件号：</label>
    	 </div>
    	 <div class="grid_1"></div>
    	 <div class="grid_15 lable-lef" >
    	     <label class="form-lbl">${groupFamily.masterCardNo } </label>
    	 </div>
    	 <div class="grid_8 lable-right">
         	<label class="form-lbl">家长姓名：</label>
    	 </div>
    	 <div class="grid_1"></div>
    	 <div class="grid_15 lable-lef" >
    	     <label class="form-lbl">${groupFamily.familyMaster } </label>
    	 </div>
    	 <div class="grid_8 lable-right">
    	    <em class="form-req">*</em>
         	<label class="form-lbl">新家庭编号：</label>
    	 </div>
    	 <div class="grid_16">
    	     <input id="newFamilyAccount" class="form-txt" name="newFamilyAccount" value="" maxlength="20" >
    	 </div>
    </form>
</div> 
<script>
     $(document).ready(function(){
    	 $("#resetFamilyAccount-form").formValidate({
 			promptPosition: "bottomLeft",
 			submitHandler: function(form){
 				if($("#newFamilyAccount").val()==""||$("#newFamilyAccount").val()==null){
 					$.messageBox({message:"新户口号不能为空！",level:"warn"});
 					return ;
 				}
 			    $("#resetFamilyAccount-form").ajaxSubmit({
 					success:function(data){
 						if(data==null||data==""){
 							$.messageBox({message:"重置家庭户口号成功！"});
 							 $("#resetFamilyAccountDlg").dialog("close");
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