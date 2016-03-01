<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="dialog-form"  class="container container_24">
	<form id="layoutForm" method="post" action="${path}/builddatasManage/generatedLayout.action">
	<pop:token />
		<div class="grid_16">
			<label class="form-lbl">请填写要生成的楼层数和房间数</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4  lable-right">
			<label class="form-lbl">层:</label>
		</div>
		<div class="grid_7">
			<input type="text" name="buildLayout.totalRow" id="totalRow" value="" class="form-txt"  maxlength="2" />
		</div>
		<div class="grid_4  lable-right">
			<label class="form-lbl">室:</label>
		</div>
		<div class="grid_7">
			<input type="text" name="buildLayout.totalCol" id="totalCol" value=""  class="form-txt" maxlength="2" />
		</div>
	</form>
</div>

<script>
jQuery.validator.addMethod("layoutRowValidate", function(value, element) {
	return parseInt(value) <= 50;
});

jQuery.validator.addMethod("layoutColumnValidate", function(value, element) {
	return parseInt(value) <= 40;
});

	$(function(){
		$("#layoutForm").formValidate({
			promptPosition: "bottomLeft", 
			submitHandler: function(form){
				var totalRow = $(form).children("div").children("input#totalRow").val();
				var totalCol = $(form).children("div").children("input#totalCol").val();
				var buildingname = '<s:property value="#parameters.buildingname"/>';
				var selectedId = '<s:property value="#parameters.buildingId"/>';
				var orgId = '<s:property value="#parameters.orgId"/>';
				if(totalRow == '' || isNaN(totalRow) || totalCol == '' || isNaN(totalCol)  ){
					$.messageBox({message:"请正确填写参数！",level: "error"	});
					return;
				}
				layoutUrl = '${path}/builddatasManage/generatedLayout.action?mode=generated&builddatas.id='+selectedId+'&buildLayout.org.id='+orgId+'&buildLayout.totalRow='+totalRow+'&buildLayout.totalCol='+totalCol;
				window.parent.generatedLayout(layoutUrl,buildingname,selectedId);
				$("#generatedLayoutDialog").dialog("close");
			},
			rules:{
				"buildLayout.totalRow":{
					required:true,
					positiveInteger:true,
					maxlength:2,
					layoutRowValidate:true
				},
				"buildLayout.totalCol":{
					required:true,
					positiveInteger:true,
					maxlength:2,
					layoutColumnValidate:true
				}
			},
			messages:{
				"buildLayout.totalRow":{
					required:"请输入层数",
					positiveInteger:"只能输入正整数",
					maxlength:$.format("层数最多只能输入50个"),
					layoutRowValidate:"层数最多只能输入50个"
				},
				"buildLayout.totalCol":{
					required:"请输入室数",
					positiveInteger:"只能输入正整数",
					maxlength:$.format("室数最多只能输入40个"),
					layoutColumnValidate:"室数最多只能输入40个"
				}
			}
		});
	});
</script>