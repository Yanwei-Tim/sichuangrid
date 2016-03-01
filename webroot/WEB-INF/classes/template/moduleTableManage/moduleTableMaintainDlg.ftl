<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="container container_24">
	<form id="moduleTableForm" method="post" action="/orgchange/moduleTableManage/maintainModuleTable.action">
	<@pop.token />
		<input type="hidden" value='${(moduleTable.permission.ename)?if_exists}' name="moduleTable.permission.ename" />
		<input type="hidden" value="${mode?if_exists}" name="mode" id="mode"/>
		<input type="hidden" value="${(moduleTable.id)?if_exists}" name="moduleTable.id" id="moduleTableId"/>
		<div class="grid_3 lable-right">
			<label class="form-lb1">权限模块：</label>
		</div>
		<div class="grid_5 ">
			<input type="text" id="cname"
				readonly="readonly" class='form-txt ' value='${(moduleTable.permission.cname)?if_exists}' />
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">执行方式：</label>
		</div>
		<div class="grid_5 " >
		    <select id="implementationtype" class="form-txt" name="moduleTable.executeType" onchange="showBeanName(this);" value="${(moduleTable.executeType)?if_exists}">
		        <option value="0" <@s.if test="moduleTable.executeType ==0">selected</@s.if> >默认</option>
		        <option value="1" <@s.if test="moduleTable.executeType ==1">selected</@s.if> >自定义</option>
		    </select>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">脚本层级：</label>
		</div>
		<div class="grid_5" >
			  <select id="" name="moduleTable.executeLevel" class="form-txt">
		        <option value="1" <@s.if test="moduleTable.executeLevel ==1">selected</@s.if>>1</option>
		        <option value="2" <@s.if test="moduleTable.executeLevel ==2">selected</@s.if>>2</option>
		        <option value="3" <@s.if test="moduleTable.executeLevel ==3">selected</@s.if>>3</option>
		        <option value="4" <@s.if test="moduleTable.executeLevel ==4">selected</@s.if>>4</option>
		        <option value="5" <@s.if test="moduleTable.executeLevel ==5">selected</@s.if>>5</option>
		    </select>
		</div>
 		<div class='clearLine'>&nbsp;</div>
		<div class='clear'></div>
		<div id="showdiv">
		<div class="grid_3 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">业务表名：</label>
		</div>
		<div class="grid_4">
			<input type="text" name="moduleTable.tableName" id="tableName" readonly="readonly" value='${(moduleTable.tableName)?if_exists}'
			  class="form-txt  hasDatepicker {required:true,messages:{required:'表名必须填写'}}" />
		</div>
		<div class="grid_1">
			<input id="changePassword" value="true" name="moduleTable.split" class='form-txt' title="是否分表" type="checkbox" <@s.if test="moduleTable.split">checked="checked"</@s.if> />
		</div>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">网格字段：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="moduleTable.orgIdColumn" id="attributeName" readonly="readonly" value='${(moduleTable.orgIdColumn)?if_exists}'  
			class='form-txt   hasDatepicker' />
		</div>
		
		<div class="grid_3 lable-right">
			<label class="form-lb1">内部码字段：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="moduleTable.orgCodeColumn" id="orgCodeColumn" readonly="readonly" value='${(moduleTable.orgCodeColumn)?if_exists}'  
			class='form-txt   hasDatepicker'  />
		</div>
		<div class='clear'></div>
		
		<div id="bean" style="display:none;">
			<div class="grid_3 lable-right">
				<label>Bean名称：</label>
			</div>	
			<div class="grid_27 heightAuto">
				<input type="text" name="moduleTable.beanName" id="beanName" class='form-txt {maxlength:30,messages:{maxlength:"自定义bean名称最多需要输入30个字符"}}' value='${(moduleTable.beanName)?if_exists}'/>
			</div>
			<div class='clear'></div>
		</div>
		
		<div id="sql">
			<div class="grid_3 lable-right">
				<label>计数脚本：</label>
			</div>	
			<div class="grid_27 heightAuto">
				<textarea name="moduleTable.countScript" id="searchSql" style="height:40px;" class='form-txt {maxlength:1000,messages:{maxlength:"统计语句最多需要输入1000个字符"}}'>${(moduleTable.countScript)?if_exists}</textarea>
			</div>
			<div class='clear'></div>
		</div>
		<!--
		<div class="grid_4 lable-right">
			<label>部门内置类型码：</label>
		</div>	
		<div class="grid_20 heightAuto">
			<textarea name="moduleTable.orgCodeColumn" id="orgCodeColumn" readonly="readonly" style="height:70px;width: 495px" class='form-txt hasDatepicker {maxlength:30,messages:{maxlength:"部门内置类型码最多需要输入30个字符"}}'>${(moduleTable.orgCodeColumn)?if_exists}</textarea>
		</div>
		<div class="grid_4 lable-right">
			<label>删除语句：</label>
		</div>	
		<div class="grid_20 heightAuto">
			<textarea name="moduleTable.deleteScript" id="deleteSql" style="height:70px;width: 495px" class='form-txt {maxlength:600,messages:{maxlength:"删除语句最多需要输入600个字符"}}'>${(moduleTable.deleteScript)?if_exists}</textarea>
		</div>
		-->
		
		<div class="grid_3 lable-right">
			<label>删除脚本：</label>
		</div>	
		<div class="grid_27 heightAuto">
			<textarea name="moduleTable.deleteScript" id="deleteSql" style="height:40px;" class='form-txt {maxlength:600,messages:{maxlength:"删除语句最多需要输入600个字符"}}'>${(moduleTable.deleteScript)?if_exists}</textarea>
		</div>
		
		<div id="sqlForSelect">
			<div class="grid_3 lable-right">
				<label>查询语句：</label>
			</div>	
			<div class="grid_27 heightAuto">
				<textarea name="moduleTable.selectScript"  style="height:40px;" class='form-txt {maxlength:600,messages:{maxlength:"查询相信记录语句最多需要输入600个字符"}}'>${(moduleTable.selectScript)?if_exists}</textarea>
			</div>
			<div class='clear'></div>
		</div>
		
		<div id="sqlForUpdate">
			<div class="grid_3 lable-right">
				<label>更新脚本：</label>
			</div>	
			<div class="grid_27 heightAuto">
				<textarea name="moduleTable.updateScript"  style="height:110px;" class='form-txt {maxlength:600,messages:{maxlength:"更新语句最多需要输入600个字符"}}'>${(moduleTable.updateScript)?if_exists}</textarea>
			</div>
			<div class='clear'></div>
		</div>
	</form>
	<div id="nameSelectDialog"></div>
</div>
<script type="text/javascript">

$(document).ready(function(){

	if($("#implementationtype").val()=="1"){
		$('#bean').show();
	}
	

   // if($('#implementationtype').val()=="1"){
   //    $("#implementationtype").attr("readonly", "true");
   // }
	//sqlDivDisplay();
	//$("#isMainTable").click(function(){
	//	sqlDivDisplay();
	//});
	
	//表格名选择
	$("#tableName").click(function(){
		$("#nameSelectDialog").createDialog({
			title:'业务表选择',
			width:600,
			height : 630,
			url:'${path}/orgchange/moduleTableManage/dispatch.action?mode=selectTableInfo',
			buttons: {
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		
		});
	});
	//网格字段名称
	$("#attributeName").click(function(){
		selectColumn("attributeName");
	})
	
	//部门内置类型码
	$("#orgCodeColumn").click(function(){
		selectColumn("orgCodeColumn");
	});
	
	
	//表单验证
	$("#moduleTableForm").formValidate({
		submitHandler: function(form) {
			if($("#isMainTable").attr("checked")=="checked"){
				$("#isMainTable").attr("value","1");
			}else{
				$("#isMainTable").attr("value","0");
			}
			$(form).ajaxSubmit({
				async:false,
	         	success: function(data){
	         		if(!data.id){
	         			$.messageBox({message:data,level: "error"});
	         		}
	         		if("add" == $("#mode").val()){
						$.messageBox({message:"成功添加模块表信息!"});
						$("#moduleTableDialog").dialog("close");	               
						getModuleTableList({"moduleTable.permission.id":$("#permissionId").val()});
	         		}
	         		if("update" == $("#mode").val()){
	         			$.messageBox({message:"成功修改模块表信息!"});
						$("#moduleTableDialog").dialog("close");	               
						getModuleTableList({"moduleTable.permission.id":$("#permissionId").val()});
	         		}
		  	   	},
		      	error: function(XMLHttpRequest, textStatus, errorThrown){
	         		$.messageBox({message:"提交错误",level: "error"});
		      	}
	      	});
		}
	});
});

function selectColumn(column){
	if($("#tableName").val()=="" || $("#tableName") ==null){
			$.messageBox({message:"请先选择表格名称",level: "warn"});
			return;
		}
		$("#nameSelectDialog").createDialog({
			title:'字段名称',
			width:600,
			height : 630,
			url:'${path}/orgchange/moduleTableManage/dispatch.action?mode=selectColumnInfo&column='+column,
			buttons: {
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		
		});
}

function showBeanName(obj){
	if(obj.value=="0"){
      $('#bean').hide();
    }else if(obj.value=="1"){
      $('#bean').show();
    }
}

//function sqlDivDisplay(){
//	if($("#isMainTable").attr("checked")=="checked"){
//		$("#sql").show();
//	}else{
//		$("#sql").hide();
//		$("#searchSql").val("");
//	}
//}

</script>