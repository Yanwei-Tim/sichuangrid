<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div style="height: 405px;">
	<span style="font-weight: bold; margin-left:20px; ">见义勇为人员</span>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">伤残等级：</label>
	</div>
	<div class="grid_6">
		<select name="population.disableGradeType.id" id="goodSamaritan-disableGradeType" class="form-txt {required:true,messages:{required:'请输入伤残等级'}}">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DISABLE_GRADE" defaultValue="${population.disableGradeType.id}" />
		</select>
	</div>
	<div class="grid_4 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">是否牺牲：</label>
	</div>
	<div class="grid_7">
	    <select id="population.sacrificeType.id" name="population.sacrificeType.id"  class="form-txt {required:true,messages:{required:'请输入是否牺牲'}}">
	    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@IS_SACRIFICE"  defaultValue="${population.sacrificeType.id }"  />
	    </select>
	</div>
	
	<div class="grid_6 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">参保情况：</label>
	</div>
	<div class="grid_6">
		<input id="insureSituationOfLast" value="${insureSituationOfLast.id}" type="hidden">
		<input name="population.insureSituationType.id" id="insureSituationType_realy" value="${population.insureSituationType.id}" type="hidden">
		
	    <select id="insureSituationTypeId" name="insureSituationTypeId"  class="form-txt {required:true,messages:{required:'请输入参保情况大类'}}" onchange="changeType()" >
	    	<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@INSURE_SITUATION"  defaultValue="${population.insureSituationType.id}" 
			   reletionId="insureSituationSmallTypeId" reletionName="@com.tianque.domain.property.PropertyTypes@INSURE_SITUATION_SUB" id="insureSituationTypeId"/> 
	    </select>
	</div>
	<div class="grid_6" id="insureSituationSmallId" style="display: none;">
	    <select id="insureSituationSmallTypeId" name="population.insureSituationSmallType.id"  class="form-txt {required:true,messages:{required:'请输入参保情况小类'}}">
	    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INSURE_SITUATION_SUB"  defaultValue="${population.insureSituationSmallType.id }"  />
	    </select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_6 lable-right">
		<em class="form-req">*</em>
		<label class="form-lb1">行为发生地：</label>
	</div>
	<div class="grid_18">
		<input type="text" name="population.actOccurred" id="goodSamaritan-actOccurred" maxlength="100" value="${population.actOccurred}"
		class='form-txt {required:true,messages:{required:"请输入姓名"}}'/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_6 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">获奖年份：</label>
	</div>
	<div class="grid_6">
	<input id="awardYear" value="${population.awardYear}" type="hidden">
		<select  id="cyear" onchange="" name="population.awardYear" style="width:140px;">
		</select>
	</div>
	<div class="grid_4 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">获奖类型：</label>
	</div>
	<div class="grid_7">
	    <select id="population.awardType.id" name="population.awardType.id" class="form-txt {required:true,messages:{required:'请输入获奖级别'}}">
	    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@AWARD"  defaultValue="${population.awardType.id }"  />
	    </select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_6 lable-right">
	    <em class="form-req">*</em>
		<label class="form-lbl">困难类型：</label>
	</div>
	<div class="grid_6">
		<select name="population.difficultType.id" class="form-txt {required:true,messages:{required:'请输入困难类型'}}" id="druggy-difficultType">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DIFFICULT_TYPE_GOOD_SAMARITAN" defaultValue="${population.difficultType.id}" />
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right">
		<label class="form-lbl">确认单位：</label>
	</div>
	<div class="grid_18">
	    <input name="population.confirmUnit" value="${population.confirmUnit}"  id="goodSamaritan-confirmUnit" class="form-txt" maxlength="100"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_6 lable-right">
		<label class="form-lb1">主要见义勇为事迹：</label>
	</div>
	
	<div class="grid_18 heightAuto">
		<textarea rows="4" id="heroicDeeds" name="population.heroicDeeds" maxlength="150" class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:150,messages:{exculdeParticalChar:'不能输入非法字符',minlength:$.format('至少需要输入{0}个字符'),maxlength:$.format('最多输入{0}个字符')}}">${population.heroicDeeds}</textarea>
    </div>
	<div class='clearLine'>&nbsp;</div>
	<div class="filePanel cf">
		<div class="grid_6 lable-right">
			<label class="form-lbl">附件：
				<select id="personAttachFiles" name="population.personAttachFiles" multiple="multiple" style="width:180px;display:none"></select>			
			</label>
		</div>
		<div class="grid_18 heightAuto">
			<div id="custom-queue_proKey" style="height:65px;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
			<input id="custom_file_upload_proKey"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" readonly="readonly" disabled="disabled"/>
		</div>
	</div>
</div>
<script>
$(function(){
	$("#insureSituationTypeId option").each(function(){
		if($("#insureSituationTypeId").val() == ""){
			if($(this).text()=="其他"){
				$("#insureSituationOfLast").val($(this).val());
			}
		}
	});
	
	$.ajax({
		async: false,
		url: "${path}/stat/currentTime/getYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
	
	$("#cyear").find("option[value="+$("#awardYear").val()+"]").attr("selected",true);
	
	$("#insureSituationType_realy").val($("#insureSituationTypeId").val());
	
	$('#custom_file_upload_proKey').uploadFile({
		queueID:"custom-queue_proKey",
		selectInputId:"personAttachFiles",
		targetType:"goodSamaritan"
	});
	
	var insureSituationTypeId = $("#insureSituationTypeId").val();
	var insureSituationOfLast = $("#insureSituationOfLast").val();
	//当参保大类为”请选择“和”其他“的时候，二级分类隐藏，当大类为”养老保险“，”医疗保险“时，显示二级分类
	if(insureSituationTypeId==insureSituationOfLast || insureSituationTypeId==""){
		$("#insureSituationSmallId").hide();
	}else{
		 $("#insureSituationSmallId").show(); 
	} 
	
	
	fillFile();
})

//类型
 function changeType(){
		$("#insureSituationType_realy").val($("#insureSituationTypeId").val());
		var insureSituationTypeId = $("#insureSituationTypeId").val();
		var insureSituationOfLast = $("#insureSituationOfLast").val();
		//当参保大类为”请选择“和”其他“的时候，二级分类隐藏，当大类为”养老保险“，”医疗保险“时，显示二级分类
		if(insureSituationTypeId==insureSituationOfLast || insureSituationTypeId==""){
			$("#insureSituationSmallId").hide();
		}else{
			 $("#insureSituationSmallId").show(); 
		} 
	} 

function fillFile(){
		<c:if test="${personAttachFileList!=null && fn:length(personAttachFileList)>0}">
			<c:forEach items="${personAttachFileList}" var="placeAnnexFile">
		        $("#custom-queue_proKey").addUploadFileValue({
			        id:"${placeAnnexFile.id}",
			        filename:"${placeAnnexFile.fileName}",
			    	link:"${path}/baseinfo/goodSamaritanManage/downloadPersonAttachFile.action?populationAttachFile.id=${placeAnnexFile.id}",
			        onRemove:function(id){removeAttachById('personAttachFiles',id)}
				});
		        $("<option>").attr("id","${placeAnnexFile.id}").val("${placeAnnexFile.id},${placeAnnexFile.fileName}").attr("selected",true).appendTo($("#personAttachFiles"));
		     </c:forEach>
	     </c:if>
}

function removeAttachById(fname,id){
	$("#"+fname).find("option[id="+id+"]").remove();
}
</script>