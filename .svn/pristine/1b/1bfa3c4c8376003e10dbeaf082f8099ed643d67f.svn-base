<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24">
<@s.if test='"edit".equals(mode)'>
	<form action="${path}/judgmentAnalysis/dimension/updateDimension.action" method="post" id="dimensionform">
</@s.if>  
<@s.if test='"add".equals(mode)'>
	<form action="${path}/judgmentAnalysis/dimension/addDimension.action" method="post" id="dimensionform">
</@s.if>
<@pop.token/>
	<input type="hidden" name="dimension.id" value="${(dimension.id)!}" id="dimensionId" /> 
	<input id="mode"	type="hidden" name="mode" value="${(mode)!}" />
	 <div  class="grid_4 lable-right" >
		<em class="form-req">*</em>
			<label class="form-lbl">名称： </label>
		</div>
		<div class="grid_7" >
		    	<input type="text" name="dimension.name" id="name" maxlength="10" 
		    	<@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
		    	class="form-txt"  value='${(dimension.name)!}'/>
  		</div>
  		<div class="grid_1">
	  </div>
	   <div  class="grid_4 lable-right" >
		<em class="form-req">*</em>
			<label class="form-lbl">关键词： </label>
		</div>
		<div class="grid_7" >
		    	<input type="text" name="dimension.keyName" id="keyName" maxlength="6" 
		    	<@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(dimension.keyName)!}"
  				class="form-txt" title="请输入关键词"/>
  		</div>
  		<div class="grid_1">
	  </div>
	   <div class='clearLine'>&nbsp;</div>
	    <div  class="grid_4 lable-right" >
		<em class="form-req">*</em>
			<label class="form-lbl">类型： </label>
		</div>
		<div class="grid_7">
		 <@s.if test='!"view".equals(mode)'>
			<select name="dimension.type" id="type" class="form-txt" >
				<option value="">--请选择--</option>
				<option value="1">字典项</option>
				<option value="2">自定义</option>
			</select>
		</@s.if>
		<@s.else>
			<@s.if test='dimension.type == 1'>
				<input type="text"  readonly="readonly"value="字典项" class="form-txt" />
			</@s.if>
	    	<@s.elseif test='dimension.type ==2'>
	    		<input type="text"  readonly="readonly"value="自定义" class="form-txt" />
	    	</@s.elseif>
		</@s.else>
  		</div>
  		<div class="grid_1">
	  </div>
	   
	    <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">编码： </label>
		</div>
		<div class="grid_7" >
		    	<input type="text" name="dimension.code" id="code" maxlength="128" 
		    	 <@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(dimension.code)!}"
  				class="form-txt" title="请输入编码"/>
  		</div>
  		<div class="grid_1">
	  </div>
	    <div class='clearLine'>&nbsp;</div>
	  <div  class="grid_4 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">字典项名称： </label>
		</div>
		<div class="grid_7">
			<input type="text" name="dimension.propertyDomain" id="propertyDomain" maxlength="20" 
		    	 <@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(dimension.propertyDomain)!}"
  				class="form-txt" title="请输入字典项名称"/>
	 	</div> 
	 	
	 	
	    <div  class="grid_5 lable-right" >
		<em class="form-req">*</em>
			<label class="form-lbl">字段类型： </label>
		</div>
		<div class="grid_7">
		 <@s.if test='!"view".equals(mode)'>
			<select name="dimension.databaseType" id="databaseType" class="form-txt" >
				<option value="varchar2">varchar2</option>
				<option value="number">number</option>
				<option value="clob">clob</option>
				<option value="date">date</option>
			</select>
		</@s.if>
		<@s.else>
	    	<input type="text"  readonly="readonly"value="自定义" class="form-txt"  value="${(dimension.databaseType)!}"/>
  		</@s.else>
  		</div>
  		
  		<div  class="grid_4 lable-right" >
		<em class="form-req">*</em>
			<label class="form-lbl">字段长度： </label>
		</div>
		<div class="grid_7">
			<input type="text" name="dimension.databaseLen" id="databaseLen" maxlength="2" 
		    	 <@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(dimension.databaseLen)!}"
  				class="form-txt" title="请输入字段长度"/>
	 	</div> 
	 	<div  class="grid_5 lable-right" >
		<em class="form-req"></em>
			<label class="form-lbl">字段默认值： </label>
		</div>
		<div class="grid_7">
			<input type="text" name="dimension.databaseDefault" id="databaseDefault" maxlength="10" 
		    	 <@s.if test='"view".equals(mode)'> readonly="readonly"</@s.if>
  				 value="${(dimension.databaseDefault)!}"
  				class="form-txt" title="请输入字段默认值"/>
	 	</div> 
</form>
<script>
$(document).ready(function(){
	$("#type").val('${(dimension.type)!}');
	$("#databaseType").val('${(dimension.databaseType)!}');
	$("#dimensionform").formValidate({
		submitHandler: function(form) {
			$("#dimensionform").ajaxSubmit({
             	success: function(data){
                     if(!data.id){
                    	 $.messageBox({
							message:data,
							level: "error"							
						 });
                     	return;
                     }
                     <@s.if test='"add".equals(mode) || "copy".equals(mode) '>
					    $("#dimensionList").addRowData(data.id,data,"first");
					    $.messageBox({message:"维度添加成功!"});
				     	$("#dimensionMaintanceDialog").dialog("close");
				    </@s.if>
				    <@s.if test='"edit".equals(mode)'>
				        $("#dimensionList").setRowData(data.id,data);
					    $.messageBox({message:"维度修改成功!"});
				     	$("#dimensionMaintanceDialog").dialog("close");
				     </@s.if>
				     $("#dimensionList").trigger("reloadGrid");
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
		rules:{
			"dimension.name":{
				required:true,
				minlength:1,
				maxlength:10
			},
			'dimension.keyName':{
				required:true,
				minlength:1,
				maxlength:6
			},
			'dimension.code':{
				minlength:1,
				maxlength:128
			},
			'dimension.type':{
				required:true
			},
			'dimension.propertyDomain':{
				maxlength:20
			},
			'dimension.databaseLen':{
				required:true,
				maxlength:2
			},
			'dimension.databaseDefault':{
				maxlength:10
			}
		},
		messages: {
			"dimension.name":{
				required:"请输入名称",
				minlength:"名称最少输入1个字符",
				maxlength:"名称最多输入10个字符"
			},
			"dimension.keyName":{
				required:"请输入关键词",
				minlength:"关键词最少输入1个字符",
				maxlength:"关键词最多输入6个字符"
			},
			"dimension.code":{
				minlength:"编码最少输入1个字符",
				maxlength:"编码最多输入128个字符"
			},
			'dimension.type':{
				required:"请输入类型"
			},
			'dimension.propertyDomain':{
				maxlength:"字典项名称最多输入20个字符"
			},
			'dimension.databaseLen':{
				required:"请输入字段长度",
				maxlength:"字段长度最多输入2个字符"
			},
			'dimension.databaseDefault':{
				maxlength:"字段默认值最多输入10个字符"
			}
		}
	});
	
	jQuery.validator.addMethod("validateCode", function(value, element){
		var flag=true;
		$.ajax({
			async:false,
			url:"${(path)!}/judgmentAnalysis/dimension/validateDimension.action",
			data:{
				"dimension.code": function(){ return $("#code").val()},
				"mode" : function(){ return $('#mode').val()},
				"dimension.id" : function(){ return $('#dimensionId').val()}
			},
			success:function(data){
				if(data!=true && data!="true" ){
					flag = false;
				}
			}
		});
		return flag;
	});  
});

</script>
