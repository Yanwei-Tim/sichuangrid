<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style type="text/css">
.propertyMulSelect { 
		height: 100px;
		overflow-y: auto !important;
		overflow-x: hidden;
        padding:3px 0 10px 12px;
	}
	.propertyMulSelect label{
        display:inline-block;
        *zoom:1;
        *display:inline;
        
		min-width:130px;
        height:28px;
        line-height:28px;
	}
	.propertyMulSelect label input {
		vertical-align: middle;
		margin-right: 3px;
	}
</style>
<div class="container container_24">
	<form id="maintainHisForm" method="post" action="">
		<s:if test='"add".equals(mode)'>
			<pop:token/>
		</s:if>
		<input id="mode" name="mode" type="hidden" value="${mode}" />
		<input id="id" name="visitHistory.id" type="hidden" value="${visitHistory.id}" />
		<input id="visitId" name="visitHistory.visitId" type="hidden" value="${visitHistory.visitId}" />
		<input id="visitType" type="hidden"  name="visitType" value="${visitType}"/>
		
		<div class="grid_4 lable-right">
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
  			<label class="form-lbl">上访时间：</label>
  		</div>
   		<div class="grid_16 form-left">
			<input type="text" id="visitDate" name="visitHistory.visitDate" readonly
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			value="<s:date name="visitHistory.visitDate" format="yyyy-MM-dd"/>" class="form-txt" />
   		</div>
   		<div class="clearLine">&nbsp;</div>
		<div class="radio" id="visitTypet" <s:if test='"view".equals(mode)'>hidden='true'</s:if> >
			<div id="visitTypeDiv">
				<div class="grid_4 lable-right">
					<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
					<label class="form-lbl">上访类型：</label>
				</div>
				<div class="grid_4 heightAuto multipeSelect" style="*position:static;">
					<input id="zcf" name="visitHistory.visitType" type="radio" class="category" value="1" <s:if test='"view".equals(mode)'>disabled='true'</s:if> <s:if test='1==visitHistory.visitType'>checked="checked"</s:if>/>
					<label class="form-check-text">正常访 </label>
					<ul class="zc-sf" id="zcfUl" style="*position:static;">
						<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
						<pop:PropertyDictMultiCheckbox name="visitTypes" column="3"
							domainName="@com.tianque.domain.property.PropertyTypes@NORMAL_SUPERIOR_VISIT" listVal="${visitHistory.visitTypes}" />
					</ul>
				</div>
				<div class="grid_4 heightAuto multipeSelect" style="*position:static;">
					<input id="fcf" name="visitHistory.visitType" type="radio" class="category" <s:if test='"view".equals(mode)'>disabled='true'</s:if>  <s:if test='0==visitHistory.visitType'>checked="checked"</s:if> value="0"  />
					<label class="form-check-text">异常访 </label>
					<ul class="zc-sf" id="fcfUl" style="*position:static;">
						<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
						<pop:PropertyDictMultiCheckbox name="visitTypes" column="3"
							domainName="@com.tianque.domain.property.PropertyTypes@EXCEPTION_SUPERIOR_VISIT" listVal="${visitHistory.visitTypes}" />
					</ul>
				</div>
			</div>
		</div>
   		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl" <s:if test='!"view".equals(mode)'>hidden='true'</s:if> >上访类型：</label>
		</div>
		<div class="grid_16">
			<input id="supval" type="text" name="supval_just_marked" class="form-txt dialogtip" value='${visitHistory.typeName}' maxlength="50" readonly="readonly" <s:if test='"view".equals(mode)'>disabled='true'</s:if> title="越级访： 指上访人越过所在单位或所反映问题的管辖单位而到上级机关的来访。（比如进省访、进京访）。&#13;重复访：信访人员以同一信访事项2次以上上访的行为。&#13;集体访：5人以上信访行为。&#13;异常访：信访人不到指定的场所和按规定的逐级信访程序到有权处理信访事项的机关或组织提出诉求，而是采取蓄意的、过激的、相关法律法规明确限制或禁止的方式，以集访、闹访、缠访、越级形态出现的影响党政机关办公秩序，损害社会治安秩序，恶化地区建设发展环境，妨害国家安全和公共安全等行为均属非异常访。"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
			<label class="form-lbl">上访状态：</label>
		</div>
		<div class="grid_6">
		    <select id="visitHistory.visitState.id" name="visitHistory.visitState.id"  class="form-txt" <s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SUPERIOR_VISIT_STATUS"  defaultValue="${visitHistory.visitState.id}"  />
		    </select>
		</div>
		<div class='clearLine'>&nbsp;</div>
   		<div class="clearLine">&nbsp;</div>

		<div class="grid_4 lable-right">
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
  			<label class="form-lbl">上访原因：</label>
  		</div>
		<div class="grid_18 form-left">
			<textarea rows="8" name="visitHistory.visitReason" class="form-txt {required:true,maxlength:200,messages:{required:'上访原因必有输入',maxlength:'上访原因最多输入400个字符'}}" <s:if test='"view".equals(mode)'>disabled='true'</s:if> >${visitHistory.visitReason}</textarea>
		</div>
   		<div class="clearLine">&nbsp;</div>

	</form>
</div>
<script type="text/javascript">

$('#visitDate').datePicker({
	yearRange : '1900:2030',
	dateFormat : 'yy-mm-dd',
	maxDate : '+0d'
});

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

	function showVisit(values,lable){
		var VisitValue="";
		var VisitIds="";
		var superValue="";
		if($("input[name='visitHistory.visitType']:checked").val()=="1"){
	    	$("#fcfUl input").attr("checked",false);
	    	$("#zcfUl input:checked").each(function(index,domEle){
	    		superValue="正常访：";
	    		VisitValue+=","+$(domEle).parent().text();
	    		VisitIds+=","+$(domEle).val();
	      	});
		}else {
	    	$("#zcfUl input").attr("checked",false);
	   		$("#fcfUl input:checked").each(function(index,domEle){
	   			superValue="异常访：";
	   			VisitValue+=","+$(domEle).parent().text();
	   			VisitIds+=","+$(domEle).val();
	      	});
		}
		if(VisitValue==""){
			$("#fcf").removeAttr("checked");
			$("#zcf").removeAttr("checked");
			superValue="";
		}
		$("#supval").val(superValue+VisitValue.substr(1));
		$("#visitType").val(VisitIds.substr(1));
	}
	<s:if test='!"view".equals(mode)'>
	$("#maintainHisForm").formValidate({
		promptPosition: "bottomLeft",
		showErrors:showErrorsForTab,
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success:function(data){
					<s:if test='"add".equals(mode)'>
	    				$.messageBox({message:"上访历史信息新增成功！"});
	    				$("#supervisitHistoryList").trigger("reloadGrid");
						$("#supervisorMaintanceDialog").dialog("close");
		     		</s:if>
		     		<s:if test='"edit".equals(mode)'>
						$.messageBox({message:"上访历史信息修改成功！"});
						$("#supervisorMaintanceDialog").dialog("close");
						$("#supervisitHistoryList").trigger("reloadGrid");
				    </s:if>
					$("#supervisitHistoryList").setSelection(data.id);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
	     	      	alert("提交数据时发生错误");
	  	   		}
			});
		},
		rules:{
			"visitHistory.visitState.id":{
				required:true
			},
			"visitHistory.visitDate":{
				required:true
			},
			"visitHistory.visitReason":{
				minlength:2,
				maxlength:400
			}
		},
		messages:{
			"visitHistory.visitDate":{
				required:"请选择上访时间"
			},
			"visitHistory.visitState.id":{
				required:"请选择上访状态"
			},
			"visitHistory.visitReason":{
				maxlength:$.format("上访原因最多只能输入{0}个字符")
			}	
		}
	});
	<s:if test='"add".equals(mode)'>
		$("#maintainHisForm").attr("action","${path}/baseinfo/superiorVisitHistoryManage/addSuperiorVisitHistory.action");
	</s:if>
	<s:else>
		$("#maintainHisForm").attr("action","${path}/baseinfo/superiorVisitHistoryManage/updateSuperiorVisitHistory.action");
	</s:else>
</s:if>
	
});


</script>