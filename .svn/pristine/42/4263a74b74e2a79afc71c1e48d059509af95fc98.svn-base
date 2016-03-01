<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<style>
	.peopleSelectDlg{border:1px solid #ccc;background:#fff;position:absolute;z-index:99999;}
	.peopleSearchTop{margin:5px 0;}
	.peopleSelectBox{width:539px;height:230px;padding:10px;position:relative;}
	.defaultSelect{}
	.peopleSelectBox .title{font:normal 14px/30px 'microsoft yahei' !important;}
	.peopleSelectBox .close{background:#444;color:#fff;position:absolute;top:14px;right:14px;padding: 5px 8px;}
</style>
<script type="text/javascript">
$(function(){
	$("#peopleSelectBoxClose").click(function(){
		$(".peopleSelectDlg").remove();
	})
	$("#peopleSelectTable").jqGridFunction({
		datatype: "local",
		multiselect:false,
		height:100,
		showColModelButton:false,
		colNames:['id','姓名', '身份证号码','性别','人员类型'],
	   	colModel:[
	   	       {name:"id",index:"id",hidden:true},
		       {name:"name",index:'name'},
		       {name:'idCardNo',index:'idCardNo'},
		       {name:'sex',index:'sex'},
		       {name:"personnelType"}
	   	],
		loadComplete:function(){
			
		},
		onSelectRow:function(rowId,selected){
			var selectValue=$("#involvedPersonnel").val().split(",");
			var bool=true;
			for(var i=0;i<selectValue.length;i++){
				if(selectValue[i]==rowId){bool=false;}
			}
			
			var rowData=$(this).getRowData(rowId);
			if(selected){
				if(bool){
					$("#involvedPersonnel").setPersonnelCompleteVal({
						label:rowData.name,
						value:rowData.id
					});
				}
			}else{
				if(bool){
					$("#involvedPersonnel").removePersonnelCompeleteValue(rowData.id);
				}
			}
		}
	});
	searchPeople();
	$("#searchPositiveinfo").click(function(){
		searchPeople();
	 });
});

function searchPeople(){
	var orgId=$("#org").val();
	var tag = $("#tagSerch").val();

	$("#peopleSelectTable").setGridParam({
		url:'${path}/issues/searchIssue/findPersonnelList.action',
		datatype: "json",
		page:1
	});

	$("#peopleSelectTable").setPostData({
	 	"orgId":orgId,
	 	"tag":tag
	});
	$("#peopleSelectTable").trigger("reloadGrid");
}
</script>
<div class="peopleSelectBox">
	<input type="hidden" name="org" value="<s:property value='#parameters.orgId'/>" id="org" />
	<a href="javascript:;" id="peopleSelectBoxClose" class="close">我选好了</a>
	<h3 class="title">请选择特殊人群：（可多选）</h3>
	<div class="peopleSearchTop">
		<div class="grid_3 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<input type="text" id="tagSerch" class="basic-input" />
		<input type="button" class="defaultButton" value="搜索" id="searchPositiveinfo"/>
	</div>
	<div>
		<table id="peopleSelectTable"></table>
		<div id="peopleSelectTablePager"></div>
	</div>
</div>
