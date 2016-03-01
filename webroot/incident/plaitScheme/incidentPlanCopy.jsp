<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>	
<%@ include file="/includes/baseInclude.jsp"%>
  <script type="text/javascript">
  $(function(){

   jQuery.validator.addMethod("isSelect", function(value, element){
	    if(value == null  || value.length==0){
            return false;
		  }
		return true;
	});
	
 
	$("#subjectionId").change(function(){
       var proId =  this.options[this.selectedIndex].id;
       if(proId!=null && proId.length>0){
    	   getIncidentTypeList({"proId":proId});
       }
	});

	$("#incidentTList").change(function(){
		 var degree=  $(this.options[this.selectedIndex]).attr("name");
		 if(degree =='true'){
			 $("#incidentClass").attr("disabled",false);
	     }else{
	    	 $("#incidentClass").attr("disabled",true);
		 }
	});
	
  });

  function getIncidentTypeList(param){
	  $.ajax({
			url:'${path}/incident/incidentDealPlans/findIncidentTypeList.action',
			data:param,
			success:function(date){
				$("#incidentTList").empty();
				if(date!=null && date.length>0){
					
					for(var i=0;i<date.length;i++ ){
						 $("#incidentTList").append("<option id ='"+date[i].id +"'value ='"+date[i].id+"' name ='"+date[i].degreed+"'>"+date[i].name+"</option>");
						 if(i==0 && date[i].degreed){
							 $("#incidentTList").attr("disabled",false);
							 $("#incidentClass").attr("disabled",false);
						 }
					}
				}else{
					 $("#incidentTList").attr("disabled",true);
					 $("#incidentClass").attr("disabled",true);
				}
			}
		});
	 }
  </script>
  <div class="container container_24">
  
  <div class="grid_8 lable-right"><em class="form-req">*</em><label>事件大类:</label></div>
  <div class="grid_16">
   <select id="subjectionId" name="incidentDealPlan.incidentType.subjection.id" class="form-select {isSelect:true,messages:{isSelect:'请选择类型'}}" >
	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INCIDENT_SUBJECTION" />
   </select>
  </div>
   <div class="grid_8 lable-right"><em class="form-req">*</em><label>事件小类:</label></div>
   <div  class="grid_16">
    <select id="incidentTList" class="form-select" disabled="disabled"></select>
   </div>
   <div class="grid_8 lable-right"><em class="form-req">*</em><label>事件分级:</label></div>
   <div  class="grid_16">
    <select id="incidentClass" name="incidentDealPlan.degree.id" class="form-select"  disabled="disabled">
	  <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INCIDENT_DEGREE" />
    </select>
   </div>
</div>