<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="年度台帐目录维护" class="container container_24">
	<s:if test='!"view".equals(mode)'>
	    <form id="dailyYear-form" method="post" action="" >
	</s:if>
	
	<input id="mode" type="hidden" name="mode" value="${mode}" />
	<input id="dailyDirectoryId" type="hidden" name="dailyYear.id" value="${dailyDirectory.id}" />
	<pop:token />
    <div class="grid_3 lable-right">
		<label >台帐年度：</label>
	</div>
	<div class="grid_7">
		<select name="dailyYear.yearDate" class="form-txt" id="dailyYearDate">
			<s:iterator value="set">
				<option value='<s:property />'><s:property />
			</s:iterator>
		</select>
	</div>
	<s:if test='!"view".equals(mode)'>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div>
	</s:if>
	<div class="grid_1">
		<input type="hidden" class="form-txt" id="dailyYearName" maxlength="30" name="dailyYear.name" value="${dailyYear.name}">
	</div>
	<div class="grid_4 lable-right" >
		<!--  <input id="enableLoad" type="checkbox" onclick="enableLoadTreeGrid(this)" >-->
		<label >台帐模板：</label>
	</div>
	<div class="grid_7">
		<select id="select_dailyYearList" name="templateId" class="form-txt" onchange="reloadTreeGrid()" >
			<s:iterator value="dailyYears">
				<option value="${id}" >${name}</option>
			</s:iterator>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
  	<div style="line-height:normal;!important;">
     	<table id="dailyDirectoryTree_template" style="*border-bottom:1px solid #a6c9e2;line-height:20px;width:400px  height:205px;overflow-x:auto"></table>
  	</div>

   <s:if test='!"view".equals(mode)'>
	   </form>
	</s:if>
</div>
<script type="text/javascript">
$(document).ready(function(){
	<s:if test='!"view".equals(mode)'>
		$("#dailyYearDate option").eq(1).attr('selected', 'true');
		$("#dailyYearName").val($("#dailyYearDate").val() + '年台账目录');
		$("#dailyYearDate").change(function(){
			$("#dailyYearName").val($("#dailyYearDate").val() + '年台账目录');
		});
		$("#dailyYear-form").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			},
			rules:{
				"dailyYear.yearDate":{
					required:true,
					remote:{
						data:{
                			"dailyYear.yearDate": function(){ return $('#dailyYearDate').val()}
						},
						url:"${path}/daily/dailyYearManage/checkUnique.action",
						type:"get"
					}
				}
			},
			messages:{
				"dailyYear.yearDate":{
					required:"请输入年度",
					remote:"该年度已经存在!"
				}

			}
		});

		 $("#dailyDirectoryTree_template").jqGrid({
			 url: "${path}/daily/dailyYearManage/prepareDailyDirectoryList.action?mode=add&dailyYear.id="+$("#select_dailyYearList").val(),
		     treeGrid: true,
			 treeGridModel : 'adjacency',
			 hidegrid: false,
			 datatype: "json",
			 mtype: "GET",
			 editable: false,
			 height:350,
			 width:750,
			 colModel:[
						{name:"id",hidden:true,width:50,key:true,sortable:false},
				   		{name:"fullName",label:"台帐目录",width:590,sortable:false},
				   		{name:"enable",label:"拷贝",width:40,sortable:false ,formatter:chooseDailyDirectoryModule},
				   		{name:"type",label:"type",sortable:false,hidden:true},
				   		{name:"level",label:"level",sortable:false,hidden:true},
						{name:"whetherAdd",label:'whetherAdd',align:'center',hidden:true,width:120,sortable:false}
				   	 ],
		   	 rowNum:-1,
		   	 treeReader : {
			   	   	level_field: "level",
					parent_id_field: "parentId",
					leaf_field: "leaf",
					expanded_field: "expanded"
			   	 },
		   	  jsonReader: {
						repeatitems : false,
						id: "0"
			  },
			  loadComplete:function(){
				 var treeList=$("#dailyDirectoryTree_template").find(".tree-wrap-ltr");
				 for(var i=0;i<treeList.length;i++){
					$(treeList[i]).prependTo($(treeList[i]).parent().parent().children("td")[1]);
				 }
			   }
		   });
		 reloadTreeGrid();

	</s:if>

	<s:if test='"add".equals(mode)'>
		$("#dailyYear-form").attr("action","${path}/daily/dailyYearManage/addDailyYear.action");
	</s:if>
});

function doAjaxSubmit(){
	$("#dailyYear-form").ajaxSubmit({
        success: function(data){
        		if(data==null || !data.id){
					$.messageBox({message:data,level:"error"});
					return  ;
				}
                var warnName="";
            	var serviceManagement = data.serviceManagement;
            	var checkName = data.checkName;
            	var issueDealName = data.issueDealName;
            	
            	var managementSubmit = data.managementSubmit;
            	var checkNameSubmit = data.checkNameSubmit;
            	var issueDealNameSubmit = data.issueDealNameSubmit;           	
            	
            	if (null != managementSubmit && managementSubmit == "false") {
            		warnName = serviceManagement;
            	} 
            	if (null != checkNameSubmit && checkNameSubmit == "false" && "" != warnName) {
            		warnName = warnName+'、'+checkName;
            	} else if (null != checkNameSubmit && checkNameSubmit == "false") {
            		warnName = checkName;
            	}
            	if (null != issueDealNameSubmit && issueDealNameSubmit == "false" && ""!=warnName) {
            		warnName = warnName + '、' + issueDealName;
            	} else if (null != issueDealNameSubmit && issueDealNameSubmit == "false") {
            		warnName = issueDealName;
            	}
            	if (null != managementSubmit && managementSubmit == "false" || null != checkNameSubmit && checkNameSubmit == "false" || null != issueDealNameSubmit && issueDealNameSubmit == "false"){
            		 $.messageBox({
 						message:warnName+"下级目录必须勾选一项！",
 						level: "warn"
 					 });
                 	return;
            	}
                var dailyYearId = data.id;
   	   		 	<s:if test='"add".equals(mode)'>
   	   				whetherStartDaily(dailyYearId);
			    	addDailyYearMenu(data);
			    	hoverYear(data);
			    	$("#dailyYearList").trigger("reloadGrid");
			    	$("#propertiesDomainYear li a:first").click();
			     </s:if>
			     $("#dailyTrunkDirectoryDailog").dialog("close");
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     $.messageBox({message:"提交错误",level: "error"	});
 	   }
 	});
}

function whetherStartDaily(dailyYearId){
	$("#dailyDirectoryDialog").createDialog({
		width:330,
		height:190,
		title:"提示",
		url:"${path}/daily/dailyDirectory/whetherStartDaily.jsp?dailyYearId="+dailyYearId
	});
}

function hoverYear(data){
	$('#propertiesDomainYear a:[name='+data.id+']').parent().hoverEvent(function(){
		$.ajax({
			url:'${path}/workingRecord/dailyDirectory/isYesrsCount.action?dailyYearId='+data.id,
			success:function(count){
				if(count>0){
					$.notice({
						width: 400,
						level:'warn',
						message:'该年度台账目录已经被引用，需清空该年度台账所有记录方可删除!'
					});
				}else{
					$.confirm({
						title:"确认删除",
						message:"该年度台账目录删除后将无法恢复，是否继续删除?",
						width: 400,
						okFunc: function(){deleteDailyDirectoryYear(data.id);}
					});
				}
			}
		});
	});
}

function deleteDailyDirectoryYear(itemId){
	$.ajax({
		url:'${path}/daily/dailyYearManage/deleteDailyYearById.action?dailyYear.id='+itemId,
		success:function(count){
		if(count){
			$('a:[name='+itemId+']').parent().remove();
			if($("#propertiesDomainYear li a:first").length>0){
				   $("#propertiesDomainYear li a:first").click();
			}else{
					dispatchDailyTrunkDirectory(-1);
			}
		 }
		}
	});
}

function addDailyYearMenu(data){
	$("#propertiesDomainYear").prepend('<li><a href="javascript:void(0)" class="daily" name="'+data.id+'" >'+data.name+'年度台账</a><span></span></li>');
	$("#propertiesDomainYear li a").unbind("click");
	$("#propertiesDomainYear li a").bind("click", function(){
		$("#propertiesDomainYear li").removeClass("select");
		dispatchDailyTrunkDirectory($(this).attr("name"));
		$(this).parent().addClass("select");
	});
}

function chooseDailyDirectoryModule(el,options,rowData){
	var submitName=" name=\"dialyDirectoryIds\" value=\""+rowData.id+"\" checked ";
	if(rowData.id==0) {
		var submitName=" name=\"dialyDirectoryIds\" value=\""+rowData.id+"\" checked disabled";
	} 

	return "<input type=\"checkbox\" " + submitName + " onclick='clickRecord(this)'/>";
}

function clickRecord(doom){
	var rowid = ($(doom).parents("tr:first").attr("id"));
	if($(doom).attr("checked")){
	    var rc = $("#dailyDirectoryTree_template").getInd(rowid,true);
		checkParentNodes(rc);
	}else{
		var record = $("#dailyDirectoryTree_template").getInd(rowid,true);
	    uncheckChildNodes(record);
	}
};

function uncheckChildNodes(record){
   	if(record.level==3){
		$("input[type='checkbox']","#"+record.id).attr("checked",false);
		return ;
   	}
   	var nextDom = $("#"+record.id).next("tr");
   	uncheckParentNode(nextDom,record.level);
};
function checkParentNodes(rc){
	if(rc.level==1){
       return;
    }
    for(var i=rc.level-1;i>0;i--){
        var prevDom = $("#dailyDirectoryTree_template").find("#"+rc.id).prev("tr");
        checkParentNode(prevDom,i);
    }
};

function checkParentNode(prevDom,i){
	if($(".tree-minus",prevDom).hasClass("tree-minus") && $("#dailyDirectoryTree_template").getInd(prevDom.attr("id"),true).level==i){
		$("input[value=\""+ prevDom.attr("id") + "\"]").attr("checked",true);
    }else{
    	checkParentNode(prevDom.prev("tr"),i);
    }
}

function uncheckParentNode(nextDom,level){
	if($("#dailyDirectoryTree_template").getInd(nextDom.attr("id"),true).level>level){
		$("input[value=\""+ nextDom.attr("id") + "\"]").attr("checked",false);
    	uncheckParentNode(nextDom.next("tr"),level);
    }else if ($("#permissionTree").getInd(nextDom.attr("id"),true).level<=level){
        return;
    }
}

function reloadTreeGrid(){
	$("#dailyDirectoryTree_template").setGridParam({
		url: "${path}/daily/dailyYearManage/prepareDailyDirectoryList.action",
		postData: {
		 	"dailyYear.id":$("#select_dailyYearList").val(),
		 	"mode":"add"
		}
	});
	$("#dailyDirectoryTree_template").trigger("reloadGrid");

}

function enableLoadTreeGrid(dom){
	if($(dom).attr("checked")==true  || $(dom).attr("checked")=='checked'){
		$("#select_dailyYearList").attr("disabled",false);
		reloadTreeGrid();
	}else{
		$("#select_dailyYearList").attr("disabled",true);
		$("#dailyDirectoryTree_template input[type='checkbox']").attr("disabled",true);
	}
}


</script>