<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<input type="hidden" id="earlyZero" value="<s:property value="@com.tianque.domain.property.IncidentDegreeSituation@ZERO_DEGREE"/>">
<input type="hidden" id="earlyFirst" value="<s:property value="@com.tianque.domain.property.IncidentDegreeSituation@FIRET_DEGREE"/>">
<input type="hidden" id="earlySencend" value="<s:property value="@com.tianque.domain.property.IncidentDegreeSituation@SECOND_DEGREE"/>">
<input type="hidden" id="earlyThird" value="<s:property value="@com.tianque.domain.property.IncidentDegreeSituation@THRID_DEGREE"/>">
<input type="hidden" id="earlyForth" value="<s:property value="@com.tianque.domain.property.IncidentDegreeSituation@FOURTH_DEGREE"/>">

<form id="handleWarningForm" method="post" action="/incident/emergencyDisposal/maintainHandleWarning.action">
	 <input type="hidden" name="mode" id="mode" value="${mode}" />
 	 <input type="hidden" id="incidents_id" name="incidents.id" value="${incidents.id}" />
 	 <input id="houseInfoBuildingId" type="hidden" name="incidents.gisInfo.buildingId" value="${incidents.gisInfo.buildingId}" />
     <input id="houseInfoCenterX" type="hidden" name="incidents.gisInfo.centerX" value="${incidents.gisInfo.centerX}" />
    <input id="houseInfoCenterY" type="hidden" name="incidents.gisInfo.centerY" value="${incidents.gisInfo.centerY}" />
 	<input type="hidden" id="incidents_sourceType" name="incidents.sourceType" value="手工录入"/>
 	 <input type="hidden" id="incidents_status" name="incidents.status" value="<s:property value="@com.tianque.incident.vo.IncidentConstants@EARLY_WARNING"/>"/>
  <div class="container container_24">
  	 <div class="grid_4 lable-right"><em class="form-req">*&nbsp;</em><label>预警标题:</label></div>
  	 <div class="grid_20"><input type="text" id="incidents_title" name="incidents.title" value="${incidents.title}"  maxlength="50" class="form-txt"></input></div>
  
    <div class="grid_4 lable-right"><em class="form-req">*</em><label> 事发地点:</label></div>
  	 <div class="grid_20"><input type="text" id="incidents_occurAddress" name="incidents.occurAddress" value="${incidents.occurAddress}" maxlength="50" class="form-txt"></input></div>
 
  	  <div class="grid_4 lable-right"><em class="form-req">*</em><label> 提交人:</label></div>
  	 <div class="grid_8"><input type="text" id="incidents_applicant" name="incidents.applicant" value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().name"/>" maxlength="20" class="form-txt" readonly="readonly"></div>
  	 
  
  	  <div class="grid_4 lable-right"><em class="form-req">*&nbsp;</em><label>事件大类:</label></div>
	  <div class="grid_8">
		   <select id="categoryId" name="incidents.category.id" class="form-select" >
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INCIDENT_SUBJECTION" defaultValue="${incidents.category.id}"/>
		   </select>
	  </div>
	  
   	 <div class="grid_4 lable-right"><em class="form-req">*&nbsp;</em><label>事件小类:</label></div>
	   <div  class="grid_8">
	    <select id="subdivision" name="incidents.subdivision" class="form-select"   disabled="disabled">
	    </select>
	   </div>
	   
     <div class="grid_4 lable-right"><em class="form-req"></em><label>事件分级:</label></div>
	   <div  class="grid_8">
	    <select id="degreeId" name="incidents.degree.id" class="form-select " disabled="disabled"  >
		  <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INCIDENT_DEGREE"  defaultValue="${incidents.degree.id}"/>
	    </select>
	   </div>
	   
     <div class="grid_4 lable-right"><em class="form-req">*&nbsp;</em><label>影响范围:</label></div>
     <div  class="grid_1 lable-right"  id="range1"> <input type="checkbox" id="rangeCheck" name="range" value="全区" checked="checked" /></div>
     <div class="grid_2 " >全区</div>
     
     <div id="range2" style="display:none">
	       <div class="grid_8"><input class="form-txt" type="text" id="incidents_range" name="incidents.range" value="" /></div>
	       <div class="grid_1" id="rangeLabel"><label>米</label></div>
     </div>
     <div class='clearLine'>&nbsp;</div>
       <div class="grid_4 lable-right"><em class="form-req">*</em><label> 事件详细情况:</label></div>
	  	 <div class="grid_20">
	  	 	<textarea id="incidents_content" name="incidents.content" rows="4" class="form-txt">${incidents.content}</textarea>
	  	 </div>
     
     
 </div>	
 </form>
 <script type="text/javascript">
 var rangeValue = '${incidents.range}';
 var incidentIdValue=$("#categoryId").find("option:selected").val();
 $(function (){
	 $('#subdivision').attr('value','${incidents.subdivision}');
	 $('#degreeId').attr('value','${incidents.degree.id}'); 
	 if($("#mode").val()=='edit'){
		 if('${incidents.degree.id}'==null || '${incidents.degree.id}' ==''){
			 setTimeout(function() { 
				 $("#degreeId").attr("disabled",true);
				}, 1);
			}
	 }
	 fillTheEditDate('${incidents.incidentType.id}','${incidents.incidentType.degreed}','${incidents.incidentType.name}');
	$("#categoryId").change (function(){
		var queryId= $("#categoryId").find("option:selected").val();
		if(queryId !=null && queryId>0){
			querysubdivision(queryId);
			}
		});
	$("#subdivision").change(function(){
		var degreed=$("#subdivision").find("option:selected").attr("id");
		if(degreed=='true'){
			$("#degreeId").removeAttr("disabled");
		}else{
			$('#degreeId option:first').attr('selected','selected'); 
			$("#degreeId").attr("disabled",true);
			}
		});
	
	$("#rangeCheck").change(function(){
		if($("#rangeCheck:checked").length){
			$("#range2").css('display','none');
			$("#incidents_range").val("")
		}else{
			$("#range2").css("display","block"); 
			}
		});
	 
	 });

 jQuery.validator.addMethod("isCategoryIdSelect", function(value, element){
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
	 if($("#subdivision")[0] && $("#subdivision option:selected").attr("id")=='true'){
           if(value == null  || value.length==0){
              return false;
            }
		}
		 return true;
	 });
 jQuery.validator.addMethod("isSelectrange", function(value, element){
	   if($("#rangeCheck").attr("checked")=='checked'){
          return true;
	   }else{
		   if(value == null  || value.length==0){
	             return false;
	        }
		   return true;
		 }
	 });

 function querysubdivision(queryId){
	 $.ajax({
			async:false,
			url:"${path}/incident/emergencyDisposal/findPlansSubdivisionBySubjection.action?incidentType.id="+queryId,
			success:function (resultDate){
				fillTheSubbitionDate(resultDate);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				  alert("提交数据时发生错误");
				  }
		 });
	 }
 function fillTheEditDate(id,degreed,name,range){
	if($("#mode").val()=='edit'){
		if(rangeValue != "全区"){
				$("#rangeCheck").attr("checked",false);
				$("#range2").css("display","block"); 
				$("#incidents_range").val('${incidents.range}');
		}
		if(incidentIdValue !="" ||incidentIdValue !=null || incidentIdValue != 'undefined'){
			querysubdivision(incidentIdValue);
			$("#subdivision").attr("disabled",false);
			$("#degreeId").attr("disabled",false);
			}
	}
 }
 function fillTheSubbitionDate(resultDate){
	 $("#subdivision").html("");
		if(resultDate!=null && resultDate!=""){
			for(i=0;i<resultDate.length;i++){
				$("#subdivision").append("<option id ='"+resultDate[i].degreed +"'value='"+resultDate[i].id+"'>"+resultDate[i].name+"</option>");
				$('#subdivision option:first').attr('selected','selected'); 
				$("#subdivision").attr("disabled",false);
				if(i!=0 && resultDate[0].degreed){
					$("#degreeId").attr("disabled",false);
				}else{
					$("#degreeId").attr("disabled",true);
					}
				}
			$("#subdivision").trigger("change");
		}else{
			$("#subdivision").html("");
			$("#subdivision").attr("disabled",true);
			$('#degreeId option:first').attr('selected','selected'); 
			$("#degreeId").attr("disabled",true);
		}
	 }

 function fillDate(id,title,degree){
	 var incident= 'incident_';
	 var incidentDegreeId='incidentDegreeId_';
	 var incidentTitle='incidentTitle_';
	 if(degree==null || degree==""){
		 var str= "<dl id='"+incident +id+"' class='Structure clearfix'><dt id='incidentDegreeId_'"+id+"' class='warnGrade5'>"+'无分级'+"<span class='gradeStar gradeStar5'></span></dt><dd id='incidentTitle_'"+id+"'> <a href='javascript:;'>"+title+"</a> </dd> </dl>";
	 }
	 else if($("#earlyFirst").val()==degree.internalId){
		var str= "<dl id='"+incident +id+"'  class='Structure clearfix'><dt id='"+incidentDegreeId+id+"' class='warnGrade1'>"+degree.displayName+"<span class='gradeStar gradeStar1'></span></dt><dd id='"+incidentTitle+id+"'> <a href='javascript:;'>"+title+"</a> </dd> </dl>";
	 }
	 else if($("#earlySencend").val()==degree.internalId){
		 var str= "<dl id='"+incident +id+"' class='Structure clearfix'><dt id='incidentDegreeId_'"+id+"' class='warnGrade2'>"+degree.displayName+"<span class='gradeStar gradeStar2'></span></dt><dd id='incidentTitle_'"+id+"'> <a href='javascript:;'>"+title+"</a> </dd> </dl>";
	} 
	 else if($("#earlyThird").val()==degree.internalId){
		 var str= "<dl id='"+incident +id+"' class='Structure clearfix'><dt id='incidentDegreeId_'"+id+"' class='warnGrade3'>"+degree.displayName+"<span class='gradeStar gradeStar3'></span></dt><dd id='incidentTitle_'"+id+"'> <a href='javascript:;'>"+title+"</a> </dd> </dl>";
	}
	 else if($("#earlyForth").val()==degree.internalId){
		 var str= "<dl id='"+incident +id+"' class='Structure clearfix'><dt id='incidentDegreeId_'"+id+"' class='warnGrade4'>"+degree.displayName+"<span class='gradeStar gradeStar4'></span></dt><dd id='incidentTitle_'"+id+"'> <a href='javascript:;'>"+title+"</a> </dd> </dl>";
	}else{
		var str="";
		} 
	return str;
	 }
 function fillIncidentDegreeIdDate(id,title,degree){
	 if(degree==null || degree==""){
		 var str="<dt id='incidentDegreeId_'"+id+"' class='warnGrade5'>"+'无分级'+"<span class='gradeStar gradeStar5'></span></dt><dd id='incidentTitle_'"+id+"'> <a href='javascript:;'>"+title+"</a> </dd>";
		return str;
	 } else {
		 if($("#earlyFirst").val()==degree.internalId){
			var str= "<dt id='incidentDegreeId_'"+id+"' class='warnGrade1'>"+degree.displayName+"<span class='gradeStar gradeStar1'></span></dt><dd id='incidentTitle_'"+id+"'> <a href='javascript:;'>"+title+"</a></dd>";
		 }
		 else if($("#earlySencend").val()==degree.internalId){
		    var str= "<dt id='incidentDegreeId_'"+id+"' class='warnGrade2'>"+degree.displayName+"<span class='gradeStar gradeStar2'></span></dt><dd id='incidentTitle_'"+id+"'> <a href='javascript:;'>"+title+"</a></dd>";
		 } 
		 else if($("#earlyThird").val()==degree.internalId){
		    var str= "<dt id='incidentDegreeId_'"+id+"' class='warnGrade3'>"+degree.displayName+"<span class='gradeStar gradeStar3'></span></dt><dd id='incidentTitle_'"+id+"'> <a href='javascript:;'>"+title+"</a></dd>";
		 }
		 else if($("#earlyForth").val()==degree.internalId){
			var str= '<dt id="incidentDegreeId_"'+id+' class="warnGrade4">'+degree.displayName+'<span class="gradeStar gradeStar4"></span></dt><dd id="incidentTitle_"'+id+'> <a href="javascript:;">'+title+'</a> </dd>';
		 }else{
			var str="";
			} 
	 }
	return str;
	 }
 $("#handleWarningForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		        if(!$("#subdivision").val()){
		        	 $.messageBox({message:"请先增加一个事件小类",level: "warn"});
		        	 return ;
		        }
	        	formSubmit(form);
		},
		rules:{
			"incidents.title":{
				required:true,
				exculdePartical:true,
				minlength:2
			},
			"incidents.occurAddress":{
				required:true,
				exculdeParticalChar:true,
				minlength:2
			},
			"incidents.applicant":{
				required:true,
				exculdeParticalChar:true,
				maxlength:20
			},
			"incidents.content":{
				required:true,
				exculdeParticalChar:true,
				maxlength:500
			},
			"incidents.category.id":{
				required:true,
				isCategoryIdSelect:true
			},
			"incidents.subdivision":{
				required:true,
				isSubdivisionSelect:true
			},
			"incidents.degree.id":{
				required:true,
				isSelectDegree:true
			},
			"incidents.range":{
				isSelectrange:true,
				 digits:true
				}
		},
		messages:{
			"incidents.title":{
				required:"请输入预警标题",
				exculdePartical:"预警标题只能输入字母，数字和中文字符",
				minlength:$.format("预警标题至少需要输入{0}个字符")
			},
			"incidents.occurAddress":{
				required:"请输入预警发生地址",
				exculdeParticalChar:"预警发生地址只能输入字母，数字和中文符号",
				minlength:$.format("预警发生地址至少需要输入{0}个字符")
			},
			"incidents.applicant":{
				required:"请输入提交人",
				exculdeParticalChar:"提交人只能输入字母，数字和中文符号",
				maxlength:$.format("提交人最多只能输入{0}个字符")
			},
			"incidents.content":{
				required:"请输入详细内容",
				exculdeParticalChar:"请输入详细内容只能输入字母，数字和中文符号",
				minlength:$.format("请输入详细内容最多只能输入{0}个字符")
			},
			"incidents.category.id":{
				isCategoryIdSelect:"请选择事件大类"
			},
			"incidents.subdivision":{
				isSubdivisionSelect:"请选择事件小类"
			},
			"incidents.degree.id":{
				isSelectDegree:"请选择事件等级"
			},
			"incidents.range":{
				isSelectrange:"请输入预警范围",
				digits:"只能输入整数"
				}
		}
	});

 function formSubmit(form){
	    $(form).ajaxSubmit({
	        success: function(data){
	    		if(!data.id){
		                $.messageBox({ message:data, level: "error" });
	                	return;
	                }
	            	if("add"==$("#mode").val()){
						$.messageBox({message:"新建预警成功!"});
						$("#incdList_warning").append(fillDate(data.id,data.title,data.degree));
						 $("#incident_"+incidentId).click();
						$("#handleWarningDailog").dialog("close");
	            	}
	            	if("edit"==$("#mode").val()){
						$.messageBox({message:"修改预警成功!"});
						
						  var incidentId=$("#incidentId").val();
						  $("#incident_"+incidentId).html(fillIncidentDegreeIdDate(data.id,data.title,data.degree));
						  $("#incident_"+incidentId).click();
						  $("#handleWarningDailog").dialog("close");
	            	}
	 	   },
	 	   error: function(XMLHttpRequest, textStatus, errorThrown){
	 	      alert("提交数据时发生错误");
	 	   }
		});
	}

 </script>