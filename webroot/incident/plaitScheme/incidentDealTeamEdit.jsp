<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
 
  $(function(){
	  initTeamName();
      var  mode=$("#mode").val();//防止异步dailog 关闭后  $("#planStepmode").val() 不存在 所以提前放到新的内存空间中去
	  $("#dealTeamForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
		    $(form).ajaxSubmit({
				 success : function(data) {
 		    	    if(!data.id){
	   		    	    $.messageBox({message : data,level : "error"});
					    return;
	   		    	 }
	   		    	
	   		    	  if("edit"==mode){
	   		    		 $("#incidentDealTeamList").setRowData(data.id,data);
	   		    		 $.messageBox({message:"处理小组修改成功!"});  
		   		       }else if("add"==mode){
		   		    	 $("#incidentDealTeamList").addRowData(data.id,data,"first");
		   			     $.messageBox({message:"处理小组添加成功!"}); 
			   		   } 
	   		    	  $("#editincidentDealTeam").dialog("close");
	   		    	 
				   },
				  error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("提交错误");
				  }
			  });  
			},
			rules:{
				"incidentDealTeam2.teamLeader":{
				  required:true,
				  maxlength:50
				},
				"incidentDealTeam2.teamMember":{
					required:true,
				    maxlength:200
				},
				"incidentDealTeam2.responsibility":{
					required:true,
				    maxlength:500
				}
			    
			},
			messages:{
				"incidentDealTeam2.teamLeader":{
				  required:"请输入牵头部门",
				  maxlength:$.format("牵头部门最多输入50个字符")
			    },
			    "incidentDealTeam2.teamMember":{
			    	  required:"请输入参与部门",
					  maxlength:$.format("参与部门最多输入200个字符")
				},
				"incidentDealTeam2.responsibility":{
					  required:"请输入职责",
					  maxlength:$.format("职责最多输入500个字符")
				}
			}
		});
		
   });

  function initTeamName(){
	  
	  var teamName= "${incidentDealTeam2.teamName}";
	  var optionArray= $("#teamName > option");
			for(var i=0;i<optionArray.length;i++){
				if(optionArray[i].value ==teamName){
					optionArray[i].selected="selected";
			    }
			 }
	}

</script>
<form id="dealTeamForm" method="post" action="${path}/incident/incidentDealPlans/incidentDealTeamEidt.action">
  <input type="hidden" id="dealTeamId" name="incidentDealTeam2.id" value="${incidentDealTeam2.id}"/>
  
  <s:if test="#parameters.mode[0]!=null && #parameters.mode[0].equals('add')">
   <input type="hidden" id="addhDealPlanId" name="incidentDealTeam2.incidentDealPlan.id" value="<s:property value="#parameters.addhDealPlanId"/>"/>
    <input type="hidden" id="mode" name="mode" value="<s:property value="#parameters.mode"/>"/>
  </s:if>
  <s:else>
   <input type="hidden" id="hDealPlanId" name="incidentDealTeam2.incidentDealPlan.id" value="${incidentDealTeam2.incidentDealPlan.id}"/>
   <input type="hidden" id="mode" name="mode" value="${mode}"/>
  </s:else>
  <input type="hidden" id="teamType" name="incidentDealTeam2.teamType" value="0"/>
 
  <div class="container container_24">
    <div class="grid_5 lable-right">
      <em class="form-req">*</em>
      <label>小组类型:</label>
    </div>
    <div class="grid_7">
      <select id="teamName" name="incidentDealTeam2.teamName" class="form-select" >
          <option value="治安保卫组" >治安保卫组</option>
          <option value="抢险求援组" >抢险求援组</option>
          <option value="医疗救护组" >医疗救护组</option>
          <option value="宣传报道组" >宣传报道组</option>
          <option value="后勤保障组" >后勤保障组</option>
          <option value="事故调查组" >事故调查组</option>
          <option value="幕后处理组" >幕后处理组</option>
      </select>
    </div>
   <div class='clearLine'>&nbsp;</div>
    <div class="grid_5 lable-right">
      <em class="form-req">*</em>
      <label>牵头部门:</label>
    </div>
    <div class="grid_19">
      <input type="text" class="form-txt" id="teamLeader" name="incidentDealTeam2.teamLeader" value="${incidentDealTeam2.teamLeader}" />
    </div>
     <div class="grid_5 lable-right">
      <em class="form-req">*</em>
      <label>参与部门:</label>
    </div>
    <div class="grid_19" style="height:90px;">
    	<textarea  id="teamMember" name="incidentDealTeam2.teamMember" class="form-txt" style="height:80px;">${incidentDealTeam2.teamMember}</textarea>
    </div>
     <div class="grid_5 lable-right">
      <em class="form-req">*</em>
      <label>职责:</label>
    </div>
    <div class="grid_19">
    	<textarea  id="responsibility" name="incidentDealTeam2.responsibility" class="form-txt" style="height:100px;">${incidentDealTeam2.responsibility}</textarea>
    </div>
  </div>
</form>