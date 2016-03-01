<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<head>
</head>
<body>
<div id="dialog-form" title="" class="container container_24">
	<form id="mainForm" action="${(path)!}/baseInfo/taskTimeManage/update.action" method="post">
		<input type="hidden" name="taskTime.id" value="${(taskTime.id)!}"/>
		<div class="grid_9 lable-right">
			<label class="form-lb1">人口类型：</label>
		</div>
		<div class="grid_9">
			<input type="text" name="taskTime.type"  disabled="disabled"  style="width:95%" class="form-txt" value="${(taskTime.type)!}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div  class="grid_9 lable-right">
  			<label class="form-lbl">走访周期：</label>
  		</div>
		<div class="grid_9">
		   <select name="taskTime.time.id" id="time" class="form-txt " >
		   			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@VISIT_TIME" defaultValue="${(taskTime.time.id)!}" />
			</select>
   		</div>
	</form>	
</div>
</body>

<script>

$("#mainForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form){
	var testIndex = time.selectedIndex; // 选中索引
 		if(testIndex == null||testIndex ==""||testIndex == 0){
 		$.messageBox({level:'error',message:'请选择走访周期'});
 		   return ;
 	    }
		$("#mainForm").ajaxSubmit({
			async : false,
			success:function(data){
				$("#refectoryDialog").dialog("close");
			}
		});
	}
});
</script>