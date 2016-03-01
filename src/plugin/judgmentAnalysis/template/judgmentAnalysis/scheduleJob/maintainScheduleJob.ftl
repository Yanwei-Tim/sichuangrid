<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24">
<@s.if test='"edit".equals(mode)'>
	<form action="${path}/judgmentAnalysis/scheduleJob/updateScheduleJob.action" method="post" id="scheduleJobform">
</@s.if>  
<@s.if test='"add".equals(mode)'>
	<form action="${path}/judgmentAnalysis/scheduleJob/addScheduleJob.action" method="post" id="scheduleJobform">
</@s.if>
<@pop.token/>
	<input type="hidden" name="scheduleJob.id" value="${(scheduleJob.id)!}" id="scheduleJobId" /> 
	<input id="mode"	type="hidden" name="mode" value="${(mode)!}" />
	 <div  class="grid_4 lable-right" >
		<em class="form-req">*</em>
			<label class="form-lbl">名称： </label>
		</div>
		<div class="grid_7" >
		    	<input type="text" name="scheduleJob.name" id="name" maxlength="20" 
		    	<@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
		    	class="form-txt"  value='${(scheduleJob.name)!}'/>
  		</div>
  		<div class="grid_1">
	  </div>
	    <div  class="grid_4 lable-right" >
		<em class="form-req">*</em>
			<label class="form-lbl">运行方式： </label>
		</div>
		<div class="grid_7">
		 <@s.if test='!"view".equals(mode)'>
			<select name="scheduleJob.runType" id="runType" class="form-txt">
				<option value="0">quartz</option>
				<option value="1">tbSchedule</option>
			</select>
		</@s.if>
		<@s.else>
			<@s.if test='scheduleJob.runType == 0'>
				<input type="text"  readonly="readonly" value="quarz" class="form-txt" />
			</@s.if>
	    	<@s.elseif test='scheduleJob.runType == 1'>
	    		<input type="text"  readonly="readonly" value="tbSchedule" class="form-txt" />
	    	</@s.elseif>
		</@s.else>
  		</div>
  		<div class="grid_1">
	  </div>
	    <div  class="grid_4 lable-right" >
		<em class="form-req">*</em>
			<label class="form-lbl">游标： </label>
		</div>
		<div class="grid_7" >
		    	<input type="text" name="scheduleJob.currentCycle" id="currentCycle" maxlength="10" 
		    	 <@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(scheduleJob.currentCycle)!}"
  				class="form-txt" title="请输入游标"/>
  		</div>
  		<div class="grid_1">
	  </div>
	  <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">bean名称： </label>
		</div>
		<div class="grid_7">
			<input type="text" name="scheduleJob.beanName" id="beanName" maxlength="32" 
		    	 <@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(scheduleJob.beanName)!}"
  				class="form-txt" title="请输入bean名称"/>
	 	</div> 
	 	<div class='clearLine'>&nbsp;</div>
	 	<div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">是否启用： </label>
		</div>
		<div class="grid_7">
			<select name="scheduleJob.enable" id="enable" class="form-txt" >
				<option value="false">否</option>
				<option value="true">是</option>
			</select>
	 	</div> 
	    <div  class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">执行时间： </label>
		</div>
		<div class="grid_7" >
		    	<input type="text" name="scheduleJob.cronExpression" id="cronExpression" maxlength="32" 
		    	<@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(scheduleJob.cronExpression)!}"  title="请输入执行时间"
  				 class="form-txt {required:true,messages:{required:'请输入执行时间'}}"/>
  		</div>
  		<div class="grid_1">
	  </div>
	 	
	 		<div class='clearLine'>&nbsp;</div>
	    <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">业务模型： </label>
		</div>
		<div class="grid_11 heightAuto cf">
		 <@s.if test='!"view".equals(mode)'>
			<div class="ctt" style="float:left; width:158px; ">
				<input id="tellToSelector" name="scheduleJob.businessModel.id" value="${(scheduleJob.businessModel.id)!}" maxlength="50"  class="form-txt"/>
			</div>
			 <div class="tBtn" style="float:left;">
				<input type="button" class="defaultBtn" value="选择" id="selecteTellToMeau" />
			</div>
		</@s.if>
		<@s.else>
	    	<input type="text"  readonly="readonly" class="form-txt"  value="${(scheduleJob.businessModel.name)!}" style="width:154px;"/>
  		</@s.else>
  		</div>
  		  <div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">维度组合： </label>
		</div>
		<div class="grid_11 heightAuto cf">
		<@s.if test='!"view".equals(mode)'>
			<div class="ctt" style="float:left; width:158px; ">
				<input id="tellToSelectorDimension" name="scheduleJob.dimensionCombination.id" value="${(scheduleJob.dimensionCombination.id)!}" maxlength="50"  class="form-txt"/>
			</div>
			 <div class="tBtn" style="float:left;">
				<input type="button" class="defaultBtn" value="选择" id="selecteTellToMeauDimension" />
			</div>
		</@s.if>
		<@s.else>
	    	<input type="text"  readonly="readonly" class="form-txt"  value="${(scheduleJob.dimensionCombination.name)!}" style="width:154px;"/>
  		</@s.else>
	 	</div> 
	 	<div class='clearLine'>&nbsp;</div>
	 	<div  class="grid_4 lable-right" >
			<em class="form-req"></em>
			<label class="form-lbl">是否补零： </label>
		</div>
		<div class="grid_7">
			<select name="scheduleJob.prefixZero" id="prefixZero" class="form-txt" >
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
	 	</div> 
</form>
<script>
$(document).ready(function(){
	$("#tellToSelector").personnelComplete({});
	$("#tellToSelectorDimension").personnelComplete({});
	$("#prefixZero").val('${(scheduleJob.prefixZero)!}');
	$("#runType").val('${(scheduleJob.runType)!}');
	var val = '${(scheduleJob.enable?string('true','false'))!}';
	if(val == 'false'){
		$("#enable").val("false");
	}else{
		$("#enable").val("true");
	}
	$("#scheduleJobform").formValidate({
		submitHandler: function(form) {
			if($("#runType").val() == "0" && $("#cronExpression").val()==""){
				 $.messageBox({
					message:"请输入执行时间",
					level: "warn"							
				 });
				 return;
			}
			$("#scheduleJobform").ajaxSubmit({
             	success: function(data){
                     if(!data.id){
                    	 $.messageBox({
							message:data,
							level: "error"							
						 });
                     	return;
                     }
                     <@s.if test='"add".equals(mode) || "copy".equals(mode) '>
					    $("#scheduleJobList").addRowData(data.id,data,"first");
					    $.messageBox({message:"维度添加成功!"});
				     	$("#scheduleJobMaintanceDialog").dialog("close");
				    </@s.if>
				    <@s.if test='"edit".equals(mode)'>
				        $("#scheduleJobList").setRowData(data.id,data);
					    $.messageBox({message:"维度修改成功!"});
				     	$("#scheduleJobMaintanceDialog").dialog("close");
				     </@s.if>
				     $("#scheduleJobList").trigger("reloadGrid");
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
		rules:{
			"scheduleJob.name":{
				required:true,
				minlength:1,
				maxlength:20
			},
			'scheduleJob.currentCycle':{
				required:true,
				minlength:1,
				maxlength:10
			},
			'scheduleJob.runType':{
				required:true
			}
		},
		messages: {
			"scheduleJob.name":{
				required:"请输入名称",
				minlength:"名称最少输入1个字符",
				maxlength:"名称最多输入20个字符"
			},
			'scheduleJob.currentCycle':{
				required:"请输入游标",
				minlength:"游标最少输入1个字符",
				maxlength:"游标最多输入10个字符"
			},
			'scheduleJob.runType':{
				required:"请输入运行方式"
			}
		}
	});
	
});
$("#holder_tellToSelector").attr("style","readOnly:true;height: 20px;");
if(document.getElementById('input_tellToSelector') != null && document.getElementById('input_tellToSelector') != ''){
	document.getElementById('input_tellToSelector').disabled=true;
}
$("#holder_tellToSelectorDimension").attr("style","readOnly:true;height: 20px;");
if(document.getElementById('input_tellToSelectorDimension') != null && document.getElementById('input_tellToSelectorDimension') != ''){
	document.getElementById('input_tellToSelectorDimension').disabled=true;
}

if($("#tellToSelector").val()!='' && $("#tellToSelector").val() != undefined){
	$.ajax({
			url:"${path}/judgmentAnalysis/scheduleJob/findBusinessModelNames.action?modelIds="+$("#tellToSelector").val(),
			type:'GET',
			dataType:'json',
			success : function(data){
				$("#tellToSelector").clearPersonnelComplete();
				if(data != ""){
					for(i=0;i<data.length;i++){
					 	 $("#tellToSelector").setPersonnelCompleteVal({value:data[i].id,label:data[i].name,desc:""});
					}
				}
			},
			error : function(){
				$.messageBox({
					message : "加载失败，请刷新页面！",
					level : "error"
				});
			}
		});
}
$("#selecteTellToMeau").click(function(event){
	var modelIds = $("#tellToSelector").val();
	createBusinessModelSearchDialog("${(path)!}/hotModuel/judgmentAnalysis/scheduleJob/searchBusinessModelDlg.ftl?modelIds="+modelIds,"tellToSelector");
});
function createBusinessModelSearchDialog(searchUrl,inputId,isMultiselect){
	$("#orgSelectDialog").createDialog({
		width:600,
		height:550,
		title:'选择业务模型',
		url: searchUrl,
		postData:{},
		buttons: {
			"确定" : function(event){
					fillSearchBusinessModelInputer(inputId,isMultiselect);
					$(this).dialog("close");
			},
			"关闭" : function(){
					$(this).dialog("close");
			}
		}
	});
}
//维度组合
if($("#tellToSelectorDimension").val()!='' && $("#tellToSelectorDimension").val() != undefined){
	$.ajax({
			url:"${path}/judgmentAnalysis/scheduleJob/findCombinationNames.action?combinationIds="+$("#tellToSelectorDimension").val(),
			type:'GET',
			dataType:'json',
			success : function(data){
				$("#tellToSelectorDimension").clearPersonnelComplete();
				if(data != ""){
					for(i=0;i<data.length;i++){
					 	 $("#tellToSelectorDimension").setPersonnelCompleteVal({value:data[i].id,label:data[i].name,desc:""});
					}
				}
			},
			error : function(){
				$.messageBox({
					message : "加载失败，请刷新页面！",
					level : "error"
				});
			}
		});
}
$("#selecteTellToMeauDimension").click(function(event){
	var combinationIds = $("#tellToSelectorDimension").val();
	createCombinationSearchDialog("${(path)!}/hotModuel/judgmentAnalysis/scheduleJob/searchDimensionCombinationDlg.ftl?combinationIds="+combinationIds,"tellToSelectorDimension");
});
function createCombinationSearchDialog(searchUrl,inputId,isMultiselect){
	$("#orgSelectDialog").createDialog({
		width:700,
		height:550,
		title:'选择维度组合',
		url: searchUrl,
		postData:{},
		buttons: {
			"确定" : function(event){
					fillSearchCombinationInputer(inputId,isMultiselect);
					$(this).dialog("close");
			},
			"关闭" : function(){
					$(this).dialog("close");
			}
		}
	});
}

</script>
