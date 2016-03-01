<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24">
<@s.if test='"edit".equals(mode)'>
	<form action="${path}/judgmentAnalysis/dimensionCombinationManage/updateDimensionCombination.action" method="post" id="dimensionCombinationForm">
</@s.if>  
<@s.if test='"add".equals(mode)'>
	<form action="${path}/judgmentAnalysis/dimensionCombinationManage/addDimensionCombination.action" method="post" id="dimensionCombinationForm">
</@s.if>
  <@pop.token />
        <input id="mode" type="hidden" name="mode" value="${mode!}" />
        <input id="id" type="hidden" name="dimensionCombination.id" value="${(dimensionCombination.id )!}"/>		 
 
	<div class="grid_4 lable-right">
	 <em class="form-req">*</em>
		<label class="form-lbl">名称：</label>
	</div>
	<div class="grid_8">
		 <input  type="text" id="dimensionCombinationName" maxlength="50" name="dimensionCombination.name" value="${(dimensionCombination.name)!}" title="请输入维度组合"
			class='form-txt {required:true,maxlength:10,minlength:2,exculdeParticalChar:true,messages:{required:"请输入维度组合名称",maxlength:$.format("业务模型名称不能多于{0}个字符"),minlength:$.format("维度组合不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' 
			<@s.if test='"view".equals(mode)'> readonly </@s.if>
		 />
  	</div>
  	
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">关键字：</label>
	</div>
	<div class="grid_8">
		<input  type="text" id="dimensionCombinationKeyName" maxlength="20" name="dimensionCombination.keyName" value="${(dimensionCombination.keyName)!}" title="请输入关键字"
			class='form-txt {required:true,maxlength:10,messages:{required:"请输入关键字",maxlength:$.format("关键字不能多于{0}个字符")}}' 
			<@s.if test='"view".equals(mode)'> readonly </@s.if>
		 />
	</div>
	
	
	<div class="clear"></div>
	
	<div class="grid_4 lable-right">
	 <em class="form-req">*</em>
		<label class="form-lbl">业务模型：</label>
	</div>
	<div class="grid_8">
		<input  type="text" id="businessModel"  readonly value="${(dimensionCombination.businessModel.name)!}" 
			class='form-txt {required:true,messages:{required:"请选择业务模型"}}'
		 />
		 <input type="hidden" name="dimensionCombination.businessModel.id" id="businessModelVal" value="${(dimensionCombination.businessModel.id)!}">
  	</div>
  	
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">模式：</label>
	</div>
	<div class="grid_8">
		<select id="dimensionCombinationMode" name="dimensionCombination.mode" <@s.if test='"view".equals(mode)'> readonly </@s.if> class='form-txt {required:true,messages:{required:"请选择模式"}}' >
			<option value="">请选择</option>
			<@s.iterator value="@com.tianque.plugin.judgmentAnalysis.constants.DimensionCombinationMode@values()" var="mode">
				<option value="<@s.property value="value" escape=false/>"><@s.property value="name" escape=false/></option>
			</@s.iterator>
		</select>
	</div>
	
	
	<div class="clear"></div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">维度组合：</label>
	</div>
	<div class="grid_8">
		<input  type="text" id="combination" readonly  name="dimensionCombination.combination" value="${(dimensionCombination.combination)!}" 
			class='form-txt'
		 />
  	</div>
  	
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">周期：</label>
	</div>
	<div class="grid_8">
		<select id="dimensionCombinationPeriod" name="dimensionCombination.period" <@s.if test='"view".equals(mode)'> readonly </@s.if> class='form-txt {required:true,messages:{required:"请选择周期"}}' >
			<option value="">请选择</option>
			<@s.iterator value="@com.tianque.plugin.judgmentAnalysis.constants.DimensionCombinationPeriod@values()" var="period">
				<option value="<@s.property value="value" escape=false/>"><@s.property value="name" escape=false/></option>
			</@s.iterator>
		</select>
	</div>
	
	<div class="clear"></div>
	
	<div class="grid_4 lable-right" >
		<label class="form-lb1">索引：</label>
	</div>
	<div class="grid_16">
		<input type="button" class="defaultBtn" value="新增" id="addIndex" />
	</div>
	
	<div class="clear"></div>
	 <div class="grid_4 lable-right" >
	</div>
	<div class="grid_16" id="indexList">
		<@s.iterator value="dimensionCombination.idxList" id="idx" status="st">
			<div id="idxDiv<@s.property value='#st.index'/>">
				<div class="grid_12 lable-right" ><input id="idx<@s.property value='#st.index'/>" type="text" onclick="selectIdx(<@s.property value='#st.index'/>)"  readonly  name="dimensionCombination.idx" value="<@s.property value="idx" escape=false/>" class="form-txt" /></div>
				<div class="grid_4 lable-right" ><input type="button" class="defaultBtn" value="删除" id="delIndex" onclick="delIdx(<@s.property value='#st.index'/>)"  /></div>
			</div>
		</@s.iterator>
	</div>
	
	<div class="clear"></div>
	
</form>

</div>
<div id="combinationDialog"></div>
<div id="businessModelDialog"></div>
<div id="idxDialog"></div>
<script type="text/javascript">
$(document).ready(function(){
	$("#dimensionCombinationMode").val('${(dimensionCombination.mode)!}');
	$("#dimensionCombinationPeriod").val('${(dimensionCombination.period)!}');
	var idx = $("#indexList > div").length;
	$("#addIndex").click(function(event){
		var indexDiv = '<div id="idxDiv'+idx+'"><div class="grid_12 lable-right" ><input id="idx'+idx+'" type="text" onclick="selectIdx('+idx+')"  readonly  name="dimensionCombination.idx" value=""  class="form-txt" /></div>'
		indexDiv += '<div class="grid_4 lable-right" ><input type="button" class="defaultBtn" value="删除" id="delIndex" onclick="delIdx('+idx+')"  /></div>';
		indexDiv += '</div>';
		
		idx = idx + 1;
		
		$("#indexList").append(indexDiv)
	});

	$("#combination").click(function(event){
		$("#combinationDialog").createDialog({
			width: 800,
			height: 600,
			title:'维度',
			url:'${path}/judgmentAnalysis/dimension/dimensionListForCombination.ftl',
			buttons: {
		   		"确认" : function(event){
		   			var ids = $("#dimensionList").getGridParam("selarrrow");
		   			if(ids == ''){
		   				$("#combination").val('');
		   				$(this).dialog("close");
		   				return;
		   			}
		   			
		   			var str ="";
		   			for (var i=0;i<ids.length;i++){
		   				str += $("#dimensionList").getCell(ids[i],"keyName");
		   				if(i <ids.length-1){
		   					str += "_"
		   				}
		   			}
		   			$("#combination").val(str);
		   			$(this).dialog("close");
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#businessModel").click(function(event){
		$("#businessModelDialog").createDialog({
			width: 800,
			height: 600,
			title:'维度',
			url:'${path}/judgmentAnalysis/businessModel/businessModelListForCombination.ftl',
			buttons: {
		   		"确认" : function(event){
		   			var id = $("#businessModelList").getGridParam("selrow");
		   			var dimensionCombination =  $("#businessModelList").getRowData(id);
		   			if(id == ''){
		   				$("#businessModelVal").val('');
		   				$("#businessModel").val('');
		   				$(this).dialog("close");
		   				return;
		   			}
		   			
		   			$("#businessModelVal").val(id);
		   			$("#businessModel").val(dimensionCombination.name);
		   			$(this).dialog("close");
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	//表单验证
	$("#dimensionCombinationForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
         		success: function(data){
         			if(!data.id){
           	 			$.messageBox({
							message:data,
							level: "error"
			 			});
            			return;
					}
         			if("add" == $("#mode").val()){
						$.messageBox({message:"成功添加!"});
	                	$("#dimensionCombinationMaintanceDialog").dialog("close");
						$("#dimensionCombinationList").trigger("reloadGrid");
         			}
         			if("edit" == $("#mode").val()){
         				$("#dimensionCombinationMaintanceDialog").dialog("close");
						$.messageBox({message:"成功修改!"});
						$("#dimensionCombinationList").trigger("reloadGrid");
         			}
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
         				$.messageBox({message:"提交错误",level: "error"});
	      	   	}
      	  	});
		}
	});

 
	
});

function selectIdx(idx){
	$("#idxDialog").createDialog({
			width: 800,
			height: 600,
			title:'索引',
			url:'${path}/judgmentAnalysis/dimension/dimensionListForCombination.ftl',
			buttons: {
		   		"确认" : function(event){
		   		
		   			var ids = $("#dimensionList").getGridParam("selarrrow");
		   			if(ids == ''){
		   				$("#idx"+idx).val('');
		   				$(this).dialog("close");
		   				return;
		   			}
		   			
		   			var str ="";
		   			for (var i=0;i<ids.length;i++){
		   				str += $("#dimensionList").getCell(ids[i],"keyName");
		   				if(i <ids.length-1){
		   					str += "_"
		   				}
		   			}
		   		
		   			$("#idx"+idx).val(str);
		   			$(this).dialog("close");
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
}

function delIdx(idx){
	$("#idxDiv"+idx).remove();
}


</script>