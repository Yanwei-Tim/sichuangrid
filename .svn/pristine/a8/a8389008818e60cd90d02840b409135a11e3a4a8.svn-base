<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

	<span style="font-weight: bold; margin-left:20px; ">重点上访人员</span>
	<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_6">
	<select name="population.attentionExtent.id" id="attentionExtent" class="form-txt"
		<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>
<div class="grid_6 lable-right">
	<label class="form-lbl">上访状态：</label>
</div>
<div class="grid_6">
    <select id="population.visitState.id" name="population.visitState.id"  class="form-txt">
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SUPERIOR_VISIT_STATUS"  defaultValue="${population.visitState.id }"  />
    </select>
</div>
<div class='clearLine'>&nbsp;</div>


<div class="grid_4 lable-right">
	<em class="form-req">*</em>
   	<label class="form-lb1">上访原因：</label>
</div>
<div class="grid_18 heightAuto">
   	<textarea rows="8" name="population.visitReason" class="form-txt {required:true,maxlength:200,messages:{required:'上访原因必有输入',maxlength:'上访原因最多输入200个字符'}}" >${population.visitReason }</textarea>
</div>
<div class='clearLine'>&nbsp;</div>
<script>
	$(document).ready(function(){
		$("#zcf").typeSelect({
			close:function(values,labels){
				showVisit();
			},
			width:300,
			position:"bottom-right"
		});
		$("#fcf").typeSelect({
			close:function(values,labels){
				showVisit();
			},
			width:300,
			position:"bottom-right"
		});
	});

	function showVisit(values,lable){
		var VisitValue="";
		var superValue="";
		if($("input[name='population.visitType']:checked").val()=="1"){
	    	$("#fcfUl input").attr("checked",false);
	    	$("#zcfUl input:checked").each(function(index,domEle){
	    		superValue="正常访：";
	    		VisitValue+=","+$(domEle).parent().text();
	      	});
		}else {
	    	$("#zcfUl input").attr("checked",false);
	   		$("#fcfUl input:checked").each(function(index,domEle){
	   			superValue="异常访：";
	   			VisitValue+=","+$(domEle).parent().text();
	      	});
		}
		if(VisitValue==""){
			$("#fcf").removeAttr("checked");
			$("#zcf").removeAttr("checked");
			superValue="";
		}
		$("#supval").val(superValue+VisitValue.substr(1));
	}
</script>