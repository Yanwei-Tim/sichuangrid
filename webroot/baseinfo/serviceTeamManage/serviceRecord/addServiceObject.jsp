<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">

<form id="maintainServiceObjectForm" method="post">
<pop:token />
<div class="grid_4 lable-right"><label class="form-lb1">所属区域：</label></div>
<div class="grid_18">
				<input type="text" id="orgSelector"  value="中国" />
				<input type="hidden" name="searchServiceObjectVo.orgId"/>
</div>
<div class="grid_4 lable-right"><label class="form-lbl">对象类型：
</label></div>
<div class="grid_7"><select name="searchServiceObjectVo.populationBigType"
	id="populationBigType" class="form-select {isSelect:true,messages:{isSelect:'请选择类型'}}"
	<s:if test='"view".equals(mode)'>disabled="disabled"</s:if>>
	<!-- <option value='-1'>请选择</option> -->
	<option value=<s:property value="@com.tianque.core.util.BaseInfoTables@IMPORTANTPERSONNEL_KEY"/>>特殊人群</option>
	<option value=<s:property value="@com.tianque.core.util.BaseInfoTables@BIRTH_KEY"/>>计生</option>
	<option value=<s:property value="@com.tianque.core.util.BaseInfoTables@CIVIL_KEY"/>>民政</option>
</select></div>
<div class="grid_4 lable-right"><label class="form-lbl">对象子类型：
</label></div>
<div class="grid_7"><select name="searchServiceObjectVo.populationType"
	id="populationType" class="form-select {isSelect:true,messages:{isSelect:'请选择类型'}}"
	<s:if test='"view".equals(mode)'>disabled="disabled"</s:if>>
	<!-- <option value='-1'>请选择</option> -->
</select></div>
<div class='clearLine'></div>
<div class="grid_4 lable-right"><label class="form-lb1">姓名：</label></div>
<div class="grid_18"><input type="text"
	id="name" maxlength="10"
	name="searchServiceObjectVo.name" style="width: 97%"
	value="${searchServiceObjectVo.name}" title="请输入姓名"
	class='form-txt {maxlength:10,messages:{maxlength:$.format("组织名称最多输入{0}个字符")}}' />
</div>
<div class="grid_4 lable-right"><label class="form-lb1">身份证号码：</label></div>
<div class="grid_18"><input type="text"
	id="idCardNumber" maxlength="18"
	name="searchServiceObjectVo.idCardNumber" style="width: 97%"
	value="${searchServiceObjectVo.idCardNumber}" title="请输入身份证号码"
	class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:18,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("身份证号码至少需要输入{0}个字符"),maxlength:$.format("身份证号码最多需要输入{0}个字符")}}' />
</div>
<div class='clearLine'></div>
<div style="margin-left: 110px"><input type="button"  id='searchObject'  value='查询' class="defaultBtn" /><input type="button"  id='reloadData'  value='刷新' class="defaultBtn" />
</div>
<div class='clearLine'></div>
<input type="hidden" value="0" name="searchServiceObjectVo.logOut"/>
</form>

<div class='clearLine'></div>
<div style="width: 100%;margin-top: 6px">
		<table id="PopulationForMemberAddObjectList"> </table>
		<div id="PopulationForMemberAddObjectListPager"></div>
</div>
</div>
<script>
$(document).ready(function(){
	var tree=$("#orgSelector").treeSelect({
		inputName:"searchServiceObjectVo.orgId"
	});
	loadPopulationType();
	$("#populationBigType").change(function(){
		loadPopulationType();
	 });
	function loadPopulationType(){
		var key = $("#populationBigType").val();
		//if($(this).find("option:selected").text()!="请选择"){
			$("#populationType").empty();
			$.ajax({
				async: false,
				url: "${path}/baseinfo/serviceTeam/searchServiceObject/findPopulationTypeBypopulationBigType.action",
				data:{
					"key":key
				},
				success:function(list){
					//$("#populationType").append("<option value='-1'>请选择</option>");
					for(var j = 0 ;j<=list.length;j++){
						var map = list[j];
					for(var i in map){ 
						$("#populationType").append("<option value='"+i+"'>"+map[i]+"</option>");
					}
					}
				}
			});
		/*}else{
			$("#populationType").empty();
			$("#populationType").append("<option value='-1'>请选择</option>");
		}*/
	}
	jQuery.validator.addMethod("isSelect", function(value, element){
	    if(value == null  || value ==-1){
            return false;
		  }
		return true;
	});
	
	<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
	$("#PopulationForMemberAddObjectList").jqGridFunction({
		datatype: "local",
   		colModel:[
			{name:"populationId",index:"populationId",frozen:true,hidden:true},    
     		{name:"sid",index:"sid",hidden:true,sortable:false,frozen:true},
      		{name:"name",index:'name',label:'姓名',width:100,sortable:false},
      		{name:"gender",label:'性别',formatter:genderFormatter,sortable:false,width:60,align:'center'},
      		{name:'idCardNo',index:'idCardNo',label:'身份证号码',sortable:false,width:180},
      		{name:'attentionPopulationTypeCname',label:'详细类型',index:"attentionPopulationTypeCname",sortable:false, width:180},
 	  		{name:'populationType',label:'详细类型',index:"populationType",sortable:false,hidden:true,frozen:true}
		],
		height:128,
    	multiselect:true,
    	rowNum:5,
    	showColModelButton:false,
    	rowList:[5,10,15]
	});
	
	$("#searchObject").click(function(){
	   	var data=$("#maintainServiceObjectForm").serializeArray();
	   	var dataJson={};
		for(i=0;i<data.length;i++){
			if (dataJson[data[i].name]) {
				dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				dataJson[data[i].name] = data[i].value;
			}
		}
		$("#PopulationForMemberAddObjectList").setGridParam({
		    url:"${path}/baseinfo/serviceTeam/searchServiceObject/findPopulationListForMemberAddObject.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#PopulationForMemberAddObjectList").setPostData(dataJson);
	    $("#PopulationForMemberAddObjectList").trigger("reloadGrid");
	});
	$("#reloadData").click(function(){
		 $("#name").val("");
		 $("#idCardNumber").val("");
		 $("#PopulationForMemberAddObjectList").clearGridData();
	});
	
});

function addServiceObjectForMember(){
	var selectedIds = $("#PopulationForMemberAddObjectList").jqGrid("getGridParam", "selarrrow");
	if(selectedIds=="" || selectedIds==null){
		$.messageBox({message:"没有选择人员用于新增！",level: "error"});
		return;
	}
	if("<s:property value='#parameters.objectType[0]'/>"!=""&&
			$("#populationType").val()!="<s:property value='#parameters.objectType[0]'/>"){
		$.messageBox({message:"当前选中人员的类型和该服务记录中已选中的人员类型不符，请重新选择！",level: "error"});
		return;
		}
	var data=new Array();
	for(var i=0;i<selectedIds.length;i++){
		var temp={"id":selectedIds[i],
				  "name":$("#PopulationForMemberAddObjectList").getRowData(selectedIds[i]).name,
				  "type":$("#populationType").val()
				 };
		data[i] = temp;
	}
	return data;
}

</script>