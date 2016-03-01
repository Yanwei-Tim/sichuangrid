<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
  
 
  <script type="text/javascript">

  $(function(){
	$("#categoryId").change(function(){
       var proId =  this.options[this.selectedIndex].id;
       if(proId!=null && proId.length>0){
    	   getIncidentTypeList({"proId":proId});
       }
	});
	$("#subdivision").change(function(){
		 var degree=  $(this.options[this.selectedIndex]).attr("name");
		 if(degree =='true'){
			 $("#degreeId").attr("disabled",false);
	     }else{
	    	 $('#degreeId option:first').attr('selected','selected');
	    	 $("#degreeId").attr("disabled",true);
		     }
	});

	$("#rangeCheck").click(function(){
		if($(this).attr("checked")=='checked'){
			  $("#range2").css("display","none");
	    }else{
			  $("#range2").css("display","inline");
		}
	});
  jQuery.validator.addMethod("isSelect", function(value, element){
		if(value == null  || value.length==0){
		   return false;
		}
		  return true;
	});
   jQuery.validator.addMethod("isSubdivisionSelect", function(value, element){
	 
	   var options=$("#subdivision option");
	       if(options!=null && options.length>0){
              if(value == null  || value.length==0){
                    return false;
               }
		    }
			return true;
	 });

   jQuery.validator.addMethod("isSelectDegree", function(value, element){
	
	   var  subdivision= $("#subdivision option:selected");
	    if(subdivision !=null && subdivision.attr("name")=='true'){
             if(value == null  || value.length==0){
                return false;
              }
		}else{
			 $("#degreeId").val("");
		 }
		 return true;
	 });
   jQuery.validator.addMethod("isSelectrange", function(value, element){
	   if($("#rangeCheck").attr("checked")=='checked'){
            return true;
		 }
	   else{
		   if(value == null  || value.length==0){
               return false;
            }
		 }
		 return true;
	 });
   $("#handleWarningForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
	    $(form).ajaxSubmit({
			 success : function(data) {
			 
	            if(!data.id){
		    	    $.messageBox({message : data,level : "error"});
			        return;
		    	 }
		    	   
	            $.messageBox({message:"转处理提交成功!"}); 
	        
	            var incidentId=$("#incidentId").val();
	            if($("#incident_"+incidentId).next().html()!=null){
	            	   $("#incident_"+incidentId).next().click();
	            	   $("#incident_"+incidentId).remove();
		         }else{
		        	 $("#handleWarningDailog").dialog("close");
			         $("#incident_"+incidentId).remove();
			         loadDataUrl();
			         emptyEarlyWarningCategoryId();
			        
			      }
	            $("#handleWarningDailog").dialog("close");
			   },
			  error : function(XMLHttpRequest, textStatus, errorThrown) {
				  alert("提交错误");
			  }
		  });  
		},
		rules:{
			"incidents.category.id":{
			   isSelect:true
			},
			"incidents.subdivision":{
			  isSubdivisionSelect:true
			 },
			 "incidents.degree.id":{
				 isSelectDegree:true
			 },
			 "incidents.range":{
				  isSelectrange:true,
				  digits:true
			 }
		},
		messages:{
			"incidents.category.id":{
			   isSelect:"请选择类型"
			},
			"incidents.subdivision":{
			  isSubdivisionSelect:"请选择类型"
			 },
			 "incidents.degree.id":{
				 isSelectDegree:"请选择类型"
			  },
			 "incidents.range":{
				  isSelectrange:"必须输入内容",
				  digits:"必须输入整数"
			 }
		}
	});
	
  });

  function getIncidentTypeList(param){
	 
	  $.ajax({
			url:'${path}/incident/incidentDealPlans/findIncidentTypeList.action',
			data:param,
			success:function(date){
				$("#subdivision").empty();
				if(date!=null && date.length>0){
					
					for(var i=0;i<date.length;i++ ){
						 $("#subdivision").append("<option id ='"+date[i].id +"'value ='"+date[i].id+"' name ='"+date[i].degreed+"'>"+date[i].name+"</option>");
						 $("#subdivision").attr("disabled",false);
							 if(i==0 && date[i].degreed){
							 $("#degreeId").attr("disabled",false);
						 }
					}
				}else{
					 $("#subdivision").attr("disabled",true);
					 $("#degreeId option:first").attr('selected','selected');
					 $("#degreeId").attr("disabled",true);
				}
			}
		});
	 }


  </script>

<form id="handleWarningForm" method="post" action="/incident/emergencyDisposal/handleWarning.action">
<%
	request.setAttribute("pageTypes",request.getParameter("pageType"));
%>
  <input type="hidden" id="incdId" name="incidents.id" value="<s:property value="#parameters.incidentId"/>"/>
  <input type="hidden" id="pageType" name="page.type" value="<s:property value="#parameters.pageType"/>"/>
  <input type="hidden"  name="incidents.status" value="<s:property value="@com.tianque.incident.vo.IncidentConstants@HANDLING"/>"/>
  <div class="container container_24">
  <div class="grid_24"><label class="form-req">你确定对该预警信息启动如下预案?</label></div>
  <div class="grid_4 lable-right"><em class="form-req">*</em><label>事件大类:</label></div>
  <div class="grid_8">
   <select id="categoryId" name="incidents.category.id" class="form-select" >
	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INCIDENT_SUBJECTION" />
   </select>
  </div>
   <div class="grid_4 lable-right"><em class="form-req">*</em><label>事件小类:</label></div>
   <div  class="grid_8">
    <select id="subdivision" name="incidents.subdivision" class="form-select"   disabled="disabled"></select>
   </div>
   <div class="grid_4 lable-right"><em class="form-req">*</em><label>事件分级:</label></div>
   <div  class="grid_8">
    <select id="degreeId" name="incidents.degree.id" class="form-select " disabled="disabled"  >
	  <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INCIDENT_DEGREE" />
    </select>
   </div>
    <div class="grid_4 lable-right">
  	<em class="form-req">*</em><label>&nbsp;审&nbsp;核&nbsp;人:</label>
  </div>
  <div class="grid_8">
  	<input type="text" id="incidents_auditer" name="incidents.auditer" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().name"/>"  readonly="readonly" style="width: 99%"/>
  </div>
  <div class="clearLine">&nbsp;</div>
    <div class="grid_4 lable-right"><em class="form-req">*</em><label>影响范围:</label></div>
     <div  class="grid_1 lable-right"  id="range1"> <input type="checkbox" id="rangeCheck" name="range" value="全区" checked="checked" /></div>
     <div class="grid_3 " >全区</div>
     <div id="range2" style="display:none">
       <div class="grid_8"><input class="form-select" type="text" name="incidents.range" value=""/></div>
       <div class="grid_1" id="rangeLabel"><label>米</label></div>
     </div>
 </div>
</from>