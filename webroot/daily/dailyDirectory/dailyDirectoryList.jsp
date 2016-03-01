<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="updateDailyDirectory-form" action="${resource_path}/daily/dailyYearManage/updateDailyYear.action">
	  <%--   <input type="hidden" id="level" value="${dailyYear.level}" name="dailyYear.level" />
	    <input type="hidden" id="effectiveDate" value="${dailyYear.effectiveDate}" name="dailyYear.effectiveDate" /> --%>
		<input type="hidden" id="dailyYearId" value="${dailyYear.id}" name="dailyYear.id" />
		<input type="hidden" id="dailyYear_yearDate" value="${dailyYear.yearDate}" name="dailyYear.yearDate" />
		<div class="grid_3 lable-right">
			<label>台账目录名称：</label>
		</div>
		<div class="grid_13">
			<input type="text" id="dailyYearName" value="${dailyYear.name}" name="dailyYear.name" 
				maxlength="30" class="form-txt" <s:if  test='"view".equals(mode)' >disabled</s:if> />
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="ui-widget autosearch">
			<input type="text" value="请输入目录名检索" name="searchText" id="searchText" style="width:150px;left:300px;" class="searchtxt"  maxlength="18" onblur="value=(this.value=='')?'请输入目录名检索':this.value;" onfocus="value=(this.value=='请输入目录名检索')?'':this.value;"/>
		    <button id="searchDirectory" class="ui-icon ui-icon-refresh searchbtnico" style="border:0;background-color:transparent; position:absolute;top:6px;left:155px;cursor:pointer;outline: none;"></button>
	    </div>
	    <s:if  test='!"view".equals(mode)' >
		    <a id="toPrevious" href="javascript:void(0)" ><SPAN><strong class="ui-ico-sy"></strong>上移</SPAN></a>
		    <a id="toNext" href="javascript:void(0)"><SPAN><strong class="ui-ico-xy"></strong>下移</SPAN></a>
		    <a id="toFirst" href="javascript:void(0)"><SPAN><strong class="ui-ico-top"></strong>到顶</SPAN></a>
		    <a id="toEnd" href="javascript:void(0)"><SPAN><strong class="ui-ico-foot"></strong>到底</SPAN></a>
		</s:if>
		
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;margin:5px 0 0 0;">
		<table id="dailyDirectoryList"></table>
		<div id="dailyDirectoryListPager"></div>
	</div>
</div>


<script type="text/javascript">
var strJson = {};
$(document).ready(function(){
	$("#dailyDirectoryList").jqGrid({
		 url: "${resource_path}/daily/dailyYearManage/prepareDailyDirectoryList.action?dailyYear.id=${dailyYear.id}",
	     treeGrid: true,
		 treeGridModel : 'adjacency',
		 hidegrid: false,
		 datatype: "json",
		 height:350,
		 width:750,
		 mtype: "POST",
		 editable: false,
		 colModel:[
			{name:"id",index:"id",hidden:true,width:50,key:true,sortable:false},
	   		{name:"fullName",label:"台账目录",width:350,sortable:false},
	   		<s:if  test='!"view".equals(mode)' >
				{name:"",label:"新增目录",width:90,sortable:false,formatter:addFormatter,align:"center"},
				{name:"shortName",label:'操作',width:90,sortable:false,formatter:operateFormatter,align:"center"},
			</s:if>
			{name:"dailyDirectoryAttachFiles",label:'附件',align:'center',width:90,sortable:false,formatter:attachFileFormatter},
			{name:"type.id",label:'typeId',align:'center',hidden:true,width:120,sortable:false},
			{name:"type.internalId",label:'internalId',align:'center',hidden:true,width:120,sortable:false},
			{name:"parentId",label:"parentId",sortable:false,hidden:true},
			{name:"level",label:"level",sortable:false,hidden:true},
			{name:"indexId",label:'indexId',align:'center',hidden:true,width:120,sortable:false},
			{name:"whetherAdd",label:'whetherAdd',align:'center',hidden:true,width:120,sortable:false},
			{name:"showClock",label:'showClock',align:'center',hidden:true,width:120,sortable:false}
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
			 var treeList=$("#dailyDirectoryList").find(".tree-wrap-ltr");
			 for(var i=0;i<treeList.length;i++){
				$(treeList[i]).prependTo($(treeList[i]).parent().parent().children("td")[1]);
			 }
			 attachFileShow();
			 disableButtons();
		   },
		   onSelectRow:selectRow
	   });
       
		$("#updateDailyDirectory-form").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				$("#updateDailyDirectory-form").ajaxSubmit({
			        success: function(data){
		                if(!data.id){$.messageBox({message:data,level: "error"});
		                	return;
		                }
		  				$("#dailyYearList").trigger("reloadGrid");
				    	$.messageBox({message:"成功修改台账目录信息!"});
				    	$("#nextStepDialog").dialog("close");
			 	   },
			 	   error: function(XMLHttpRequest, textStatus, errorThrown){
			 	     	$.messageBox({message:"提交错误",level: "error"	});
			 	   }
			 	});
			},
			rules:{
				"dailyYear.name":{
					required:true
				}
			},
			messages:{
				"dailyYear.name":{
					required:"请输入年度名字"
				}
	
			}
		});
});

function addFormatter(el, options, rowData){
	var level = rowData.level;
	if(/^\d+$/.test(level)) { 
		level = rowData.level;
	} else {
		level = getNum(rowData.level);
	}
	if(level == 1){
		return "<pop:JugePermissionTag ename='addDailyDirectory'><input type='button' onclick='addFirstDirectory()' value='新增主目录'></pop:JugePermissionTag >"
	}else if((level > 1 && rowData.type == null) || (rowData.type != null && level == 3 && rowData.whetherAdd==null) || (rowData.type != null && level == 2 && rowData.whetherAdd==1) || (rowData.type != null && level == 3 && rowData.whetherAdd==1)){
		return "<pop:JugePermissionTag ename='addDailyDirectory'><input type='button' onclick='addChildDirectory("+rowData.id+")' value='新增子目录'></pop:JugePermissionTag >"
	}else{
		return "";
	}
}

$("#searchDirectory").click(function(){
	 $("#searchText").val("");
});

$("#searchText").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "${resource_path}/daily/dailyDirectoryManage/searchDirectorys.action",
				data:{"dailyDirectory.fullName":function(){return request.term;},"dailyDirectory.dailyYear.id":"${dailyYear.id}"},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.fullName+",",
							value: item.fullName,
							id: item.id
						}
					}))
				},
				error : function(){
					$.messageBox({message:"搜索失败，请重新登入！",level:"error"});
				}
			});
		},
	select: function(event, ui) {
			var _rowData= $("#dailyDirectoryList");
			var testParentId = _rowData.getRowData(ui.item.id).parentId;
			var _str = "td[aria-describedby='dailyDirectoryList_fullName']";
			while(testParentId > 0){
				if($("tr[id="+testParentId+"]").find(_str).find(".ui-icon-triangle-1-s").length == 0){
					$("tr[id="+testParentId+"]").find(_str).find(".treeclick").click();
				}
				testParentId = _rowData.getRowData(testParentId).parentId;
			}
			$("#dailyDirectoryList").setSelection(ui.item.id);
	}
});

function addFirstDirectory(){
		$("#dailyDirectoryDialog").removeAttr("style").removeClass().empty();
		$("#dailyDirectoryDialog").createDialog({
			width:470,
			height:260,
			title:"新增一级目录",
			url:"${resource_path}/daily/dailyDirectoryManage/dispatchOperate.action?mode=add&dailyYearId="+$("#dailyYearId").val(),
			buttons :{
				"保存" : function(){
					$("#maintainFirstDirectory").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
};
function homeDirectory(){
	var selfDoc = $("#dailyDirectoryList");
	var rowid = selfDoc.jqGrid('getGridParam','selrow');
	$("#dailyTrunkDirectoryDailog").createDialog({
		width:550,
		height:340,
		title:'新增台帐主目录',
		url:"${resource_path}/daily/dailyDirectoryManage/dispatchDailyTrunk.action?mode=add&dailyDirectory.dailyYear.id="+$("#dailyYearId").val(),
		buttons :{
			"保存" : function(){
				$("#dailyTrunkDirectory-form").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function addChildDirectory(selectedId){
		var rowDatas=$("#dailyDirectoryList").getRowData(selectedId);
		$("#dailyDirectoryDialog").removeAttr("style").removeClass().empty();
		$("#dailyDirectoryDialog").createDialog({
			width:480,
			height:280,
			title:"新增子目录",
			url:"${resource_path}/daily/dailyDirectoryManage/dispatchOperate.action?mode=add&dailyYearId="+$("#dailyYearId").val()+"&parentId="+selectedId+"&typeId="+rowDatas["type.id"],
			buttons :{
				"保存" : function(){
					$("#maintainFirstDirectory").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
};

function attachFileFormatter(el, options, rowData){
	if(rowData.id == 0 || rowData.dailyDirectoryAttachFiles == ''
		||rowData.dailyDirectoryAttachFiles==null
		||rowData.dailyDirectoryAttachFiles==undefined
		||rowData.dailyDirectoryAttachFiles.length <= 0){
		return '无';
	}
	var arr = new Array();
	for(var i=0; i<rowData.dailyDirectoryAttachFiles.length; i++){
		arr[i] = rowData.dailyDirectoryAttachFiles[i].fileId+'_'+rowData.dailyDirectoryAttachFiles[i].fileName;
	}
	strJson[""+rowData.id+""] = arr;
	return "<div style='clear:both' align='center'><a href='javascript:;' id='dailyLog_"+rowData.id+"' style='color:#666666' class='popUpMore' dailyLogId='"+rowData.id+"' >附件>></a></div>";
}

<s:if  test='!"view".equals(mode)' >
	function operateFormatter(el, options, rowData) {
		if (rowData.id==0) {
			return '';
		}
		if (rowData.directoryTypeId != null && rowData.directoryTypeId != ''){
			if(getLevel(rowData.level) > 4 || (getLevel(rowData.level) == 4 && rowData.whetherAdd == 2)) {
				return '<div class="operateFormatter"><img  id="'+rowData.id+'_update" title="修改" onclick="updateDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/edit.gif"><img  id="'+rowData.id+'_view" title="查看" onclick="viewDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/dailyView.gif"><img id="'+rowData.id+'_delete" title="删除" onclick="deleteDailyDirectory(this.id)" src="${resource_path}/resource/js/ui/images/evaluateManagement/delete.gif"><img id="'+rowData.id+'_clock" title="有效时间" onclick="setExpirationDate(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/clock.gif"></div>';
			} else if (getLevel(rowData.level) == 2 || (getLevel(rowData.level)==3 && rowData.whetherAdd == 1) || (getLevel(rowData.level)==3 && rowData.whetherAdd == 3)) {
				return '<div class="operateFormatter"><img  id="'+rowData.id+'_update" title="修改" onclick="updateDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/edit.gif"><img  id="'+rowData.id+'_view" title="查看" onclick="viewDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/dailyView.gif"></div>';
			} else if (getLevel(rowData.level) == 3 && rowData.whetherAdd == 2) {
				return '<div class="operateFormatter"><img  id="'+rowData.id+'_update" title="修改" onclick="updateDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/edit.gif"><img  id="'+rowData.id+'_view" title="查看" onclick="viewDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/dailyView.gif"><img id="'+rowData.id+'_delete" title="删除" onclick="deleteDailyDirectory(this.id)" src="${resource_path}/resource/js/ui/images/evaluateManagement/delete.gif"></div>';
			} else if ((getLevel(rowData.level) == 3 && rowData.whetherAdd == 4) || (getLevel(rowData.level) == 4 && rowData.whetherAdd == 0)) {
				return '<div class="operateFormatter"><img  id="'+rowData.id+'_update" title="修改" onclick="updateDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/edit.gif"><img  id="'+rowData.id+'_view" title="查看" onclick="viewDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/dailyView.gif"><img id="'+rowData.id+'_clock" title="有效时间" onclick="setExpirationDate(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/clock.gif"></div>';
			}
			return '<div class="operateFormatter"><img  id="'+rowData.id+'_update" title="修改" onclick="updateDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/edit.gif"><img  id="'+rowData.id+'_view" title="查看" onclick="viewDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/dailyView.gif"></div>';
		}
		if (getLevel(rowData.level) == 2){
			return '<div class="operateFormatter"><img  id="'+rowData.id+'_update" title="修改" onclick="updateDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/edit.gif"><img  id="'+rowData.id+'_view" title="查看" onclick="viewDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/dailyView.gif"><img id="'+rowData.id+'_delete" title="删除" onclick="deleteDailyDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/delete.gif"></div>';
		} else {
			if (rowData.showClock == true) {
				return '<div class="operateFormatter"><img  id="'+rowData.id+'_update" title="修改" onclick="updateDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/edit.gif"><img  id="'+rowData.id+'_view" title="查看" onclick="viewDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/dailyView.gif"><img id="'+rowData.id+'_delete" title="删除" onclick="deleteDailyDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/delete.gif"><img id="'+rowData.id+'_clock" title="有效时间" onclick="setExpirationDate(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/clock.gif"></div>';
			} else {
				return '<div class="operateFormatter"><img  id="'+rowData.id+'_update" title="修改" onclick="updateDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/edit.gif"><img  id="'+rowData.id+'_view" title="查看" onclick="viewDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/dailyView.gif"><img id="'+rowData.id+'_delete" title="删除" onclick="deleteDailyDirectory(this.id)" src="${resource_path}/resource/system/images/evaluateManagement/delete.gif"></div>';
			}
		}
	}
	
	function deleteDailyDirectory(data){
		var id = data.split('_');
		$.ajax({
			url:'${resource_path}/daily/dailyDirectoryManage/validateDelete.action?dailyDirectory.id='+id[0],
			success:function(msg){
				if(msg == true){
					$.confirm({
						title:"确认删除",
						message:"台账目录信息删除后，将无法恢复,您确认删除该台账目录信息吗?",
						width: 400,
						okFunc: function(){
							deleteDirectoryById(id[0]);
						}
					});
				}else{
					$.notice({level:'warn',message:msg});
				}
			}
		});
	}
	function deleteDirectoryById(id){
		$.ajax({
			url:'${resource_path}/daily/dailyDirectoryManage/deleteDailyDirectory.action?dailyDirectory.id='+id,
			success:function(msg){
				if(msg == true){
					var listId = $("#dailyDirectoryList"),_parentId = listId.getRowData(id).parentId;
					listId.delTreeNode(id);
					var _data = listId.getGridParam("data"),_bol = true;
					for(var i=0;i<_data.length;i++){
						if(_data[i].parentId == _parentId){
							_bol = false;
							break ;
						}
					}
					if(_bol){
						listId.setCell(_parentId,"leaf","true");
						$("#"+_parentId).find(".ui-icon").removeClass().addClass("ui-icon ui-icon-radio-off tree-leaf treeclick");
					}
					$.messageBox({message:"该台账目录成功删除!"});
					$("#dailyDirectoryList").trigger("reloadGrid");	
				}else{
					$.messageBox({message:msg,level:"error"});
				}
				return ;
			}
		});
	}
	function updateDirectory(data){
		var id = data.split('_');
		$("#dailyDirectoryDialog").createDialog({
			width:650,
			height:420,
			title:"修改台账目录信息",
			url:"${resource_path}/daily/dailyDirectoryManage/dispatchOperate.action?mode=edit&directoryId="+id[0],
			buttons :{
				"保存" : function(){
					$("#maintainFirstDirectory").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	function viewDirectory(data){
		var id = data.split('_');
		$("#dailyDirectoryDialog").createDialog({
			width:650,
			height:420,
			title:"查看台账目录信息",
			url:"${resource_path}/daily/dailyDirectoryManage/dispatchOperate.action?mode=view&directoryId="+id[0],
			buttons :{
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	//设置有效时间
	function setExpirationDate(data){
		var id = data.split('_');
		$("#dailyDirectoryDialog").createDialog({
			width:460,
			height:265,
			title:"设置台账有效时间",
			url:"${resource_path}/daily/dailyDirectoryManage/dispatchOperate.action?mode=expDate&directoryId="+id[0],
			buttons :{
				"保存" : function(){
					$("#expirationDate").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
</s:if>

function attachFileShow(){
	$.each($(".popUpMore"), function(i, n){
		var key = $(n).attr("dailyLogId");
		var fileDatas = strJson[key];
		if(fileDatas==null || fileDatas=='' 
			|| fileDatas == undefined || fileDatas.length<=0){
			return ;
		}
		var popMoreData = [];
		for(var j = 0; j < fileDatas.length; j++){
			var _index = fileDatas[j].indexOf('_');
			var _id = fileDatas[j].substring(0,_index);
			var _name = fileDatas[j].substring(_index+1);
			popMoreData[j]={id:_id, url:'/daily/dailyDirectoryManage/downLoadAttachFile.action?keyId='+_id, text:_name,clickFun:function(){}};
		}
		$(n).popUpMore({data: popMoreData});
	});

	strJson = {};
}

function getNum(text){
	if(null!=text){
		var value = text.replace(/[^0-9]/ig,""); 
		return value;
	}
}

function disableButtons(){
	$("#toPrevious").buttonDisable();
	$("#toEnd").buttonDisable();
	$("#toNext").buttonDisable();
	$("#toFirst").buttonDisable();
}

function selectRow(){
 	var selectedId = $("#dailyDirectoryList").getGridParam("selrow");
	var daily =  $("#dailyDirectoryList").getRowData(selectedId);
	
	var level = getLevel(daily.level);
	if(level >= 3){
		levelButtonDirectory();
	}
	if(level == 2){
		trunkButtonDirectory();
	}
	if(level == 1){
		$("#toNext").buttonDisable();
		$("#toEnd").buttonDisable();
		$("#toPrevious").buttonDisable();
		$("#toFirst").buttonDisable();
	}	
}

//上移
$("#toPrevious").click(function(){
	if($("#toPrevious").attr("disabled")=="true" || $("#toPrevious").attr("disabled")=="disabled"){
		return;
	}
	var selfDoc =$("#dailyDirectoryList");
	var rowid=selfDoc.jqGrid('getGridParam','selrow');
	var dailyDirectory  = selfDoc.getRowData(rowid);
	var dlevel = getLevel(dailyDirectory.level);
	
	if(dlevel >= 3) {
		topAjax(dailyDirectory);
		$("#"+rowid).click();
		levelButtonDirectory();
	} else {
		topAjaxLevel(dailyDirectory);
		$("#"+rowid).click();
		trunkButtonDirectory();
	}
	//loadList();
	//$("#dailyDirectoryList").trigger("reloadGrid");			
});

//子目录移动
function topAjax(dailyDirectory){
	var dailyId = getLevel(dailyDirectory.id);
	$.ajax({
			type:"post",
			async:false,
			url:"${resource_path}/daily/dailyDirectoryManage/moveDailyDirectory.action",
			data:{
 				moveMode:"previous",
      			"dailyDirectory.id":dailyId,
      			"dailyDirectory.parentDailyDirectory.id":dailyDirectory.parentId,
      			"dailyDirectory.indexId":dailyDirectory.indexId
     		},
			success:function(data){
				//topTreeGrid();
				$("#dailyDirectoryList").trigger("reloadGrid");		
			}
	});
}

//主目录移动
function topAjaxLevel(dailyDirectory){
	var dailyId = getLevel(dailyDirectory.id);
	$.ajax({
			type:"post",
			async:false,
			url:"${resource_path}/daily/dailyDirectoryManage/moveMainDailyDirectory.action?moveMode=previous",
			data:{
			"dailyDirectory.id":dailyId,
            "dailyDirectory.dailyYear.id":'<s:property value="#parameters.dailyYearId"/>',
            "dailyDirectory.indexId":dailyDirectory.indexId
			},
			success:function(data){
			//topTreeGrid();	
			$("#dailyDirectoryList").trigger("reloadGrid");		
		}
	});
}

function topTreeGrid(){
	var selfDoc = $("#dailyDirectoryList");
	var rowid = selfDoc.jqGrid('getGridParam','selrow');
	var selectDaily = selfDoc.getRowData(rowid);
	var shiftRow = selfDoc.getRowData( $("tr[id="+rowid+"]").prev().attr("id"));
	var selectLevel = getLevel(selectDaily.level);
	var selectDailyId = getLevel(selectDaily.id);
	var shiftId = getLevel(shiftRow.id);
	if (selectLevel >= 3) {
		 $("#"+selectDailyId).after($("#"+shiftId));
	} else if (selectLevel == 2) {
		var nextRow = selfDoc.getRowData($("tr[id="+rowid+"]").next().attr("id"));
		var shiftRow = selfDoc.getRowData( $("tr[id="+rowid+"]").prev().attr("id"));
		var shiftLevel = getLevel(shiftRow.level);
		var nextLevel = getLevel(nextRow.level);
		var nextRowId = getLevel(nextRow.id);
		var shiftRowId = getLevel(shiftRow.id);
		if (shiftLevel >= 3) {
			var node = selfDoc.jqGrid('getNodeParent',shiftRow);
            var childern = selfDoc.jqGrid("getNodeChildren",node);
            $(childern).each(function(i) {
	            var id = $(this).attr("id");
	         	$("#"+selectDailyId).after($("#"+id));
	        });
	        $("#"+selectDailyId).after($("#"+getLevel(node.id)));
	    } else if(shiftLevel == 2) {
	       	$("#"+selectDailyId).after($("#"+shiftRowId));
	    }
	    if ($("#"+nextRowId).length > 0 && (nextLevel >= 3)) {
        	var thisObject = selfDoc.jqGrid('getNodeParent',nextRow);
       		var thisObjectchildern = selfDoc.jqGrid("getNodeChildren",thisObject);
			$(thisObjectchildern).each(function(i) {
	           var id = $(this).attr("id");
	           $("#"+selectDailyId).after($("#"+id));
	        });
	    } 
    }    
}

//下移
$("#toNext").click(function(){
	if($("#toNext").attr("disabled")=="true" || $("#toNext").attr("disabled")=="disabled"){
		return;
	}
	var selfDoc = $("#dailyDirectoryList");
	var rowid = selfDoc.jqGrid('getGridParam','selrow');
	var dailyDirectory = selfDoc.getRowData(rowid);
	var dailyLevel = getLevel(dailyDirectory.level);
	if(dailyLevel >= 3) {
		nexrAjax(dailyDirectory);
		levelButtonDirectory();
	} else {
		nextAjaxLevel(dailyDirectory);
		trunkButtonDirectory();
	}
});

function nexrAjax(dailyDirectory){
	$.ajax({
			type:"post",
			async:false,
			url:"${resource_path}/daily/dailyDirectoryManage/moveDailyDirectory.action",
			data:{
 				moveMode:"next",
      			"dailyDirectory.id":getLevel(dailyDirectory.id),
      			"dailyDirectory.parentDailyDirectory.id":getLevel(dailyDirectory.parentId),
      			"dailyDirectory.indexId":getLevel(dailyDirectory.indexId)
     		},
			success:function(data){
				//moveTreeGridNext();
				$("#dailyDirectoryList").trigger("reloadGrid");	
			}
	});
}

function nextAjaxLevel(dailyDirectory){
	$.ajax({
			type:"post",
			async:false,
			url:"${resource_path}/daily/dailyDirectoryManage/moveMainDailyDirectory.action?moveMode=next",
			data:{
				"dailyDirectory.id":getLevel(dailyDirectory.id),
	            "dailyDirectory.dailyYear.id":'<s:property value="#parameters.dailyYearId"/>',
	            "dailyDirectory.indexId":getLevel(dailyDirectory.indexId)
			},
			success:function(data){
			//moveTreeGridNext();
			$("#dailyDirectoryList").trigger("reloadGrid");	
		}
	});
}

function moveTreeGridNext() {
	var selfDoc = $("#dailyDirectoryList");
	var rowid = selfDoc.jqGrid('getGridParam','selrow');
	var selectDaily = selfDoc.getRowData(rowid);
	var selectLevel = getLevel(selectDaily.level);
	
	if (selectLevel >= 3) {
		var shiftRow = selfDoc.getRowData( $("tr[id="+rowid+"]").next().attr("id"));
		$("#"+getLevel(shiftRow.id)).after($("#"+getLevel(selectDaily.id)));
	} else if (selectLevel == 2) {
		
		var nextLevleDaily = selfDoc.getRowData($("tr[id="+rowid+"]").next().attr("id"));
		var selectRowDaily = selfDoc.getRowData($("tr[id="+rowid+"]").next().attr("id"));
		var nextLevel = getLevel(nextLevleDaily.level);
		
		while (true) {
			if(nextLevel == 2) {
				break;
	        } else {
	        	nextLevleDaily = selfDoc.getRowData($("#"+getLevel(nextLevleDaily.id)).next().attr("id"));
	        }
  		}
		
  		var node = selfDoc.getRowData($("#"+getLevel(nextLevleDaily.id)).next().attr("id"));
  		var selectNodeNEXT = selfDoc.getRowData($("#"+getLevel(nextLevleDaily.id)).next().attr("id"));
  		$("#"+getLevel(nextLevleDaily.id)).after($("#"+getLevel(selectDaily.id)));
  		
  		if ($("#"+getLevel(selectRowDaily.id)).length > 0 && (getLevel(selectRowDaily.level) >= 3)){
	        	 var thisObject = selfDoc.jqGrid('getNodeParent',selectRowDaily);
	       		 var thisObjectchildern = selfDoc.jqGrid("getNodeChildren",thisObject);
				 $(thisObjectchildern).each(function(i) {
		            var id = $(this).attr("id");
		         	$("#"+getLevel(selectDaily.id)).after($("#"+id));
		        });  
		 }
		 if ($("#"+getLevel(selectNodeNEXT.id)).length > 0 && (getLevel(selectNodeNEXT.level) >= 3)) {
  		 	var thisObject = selfDoc.jqGrid('getNodeParent',selectNodeNEXT);
  			var nextLevleDailych  = selfDoc.jqGrid("getNodeChildren",thisObject);
  			$(nextLevleDailych).each(function(i) {
	            var id = $(this).attr("id");
	         	$("#"+getLevel(nextLevleDaily.id)).after($("#"+id));
	        });
  		 }
  		
  }
}


/* kk */


//到顶
$("#toFirst").click(function(){
	if($("#toFirst").attr("disabled")=="true" || $("#toFirst").attr("disabled")=="disabled"){
		return;
	}
	var selfDoc = $("#dailyDirectoryList");
	var rowid = selfDoc.jqGrid('getGridParam','selrow');
	var dailyDirectory = selfDoc.getRowData(rowid);
	if(getLevel(dailyDirectory.level) >= 3) {
		topFirstAjax(dailyDirectory);
		levelButtonDirectory();
	} else {
		topFirstAjaxLevel(dailyDirectory);
		trunkButtonDirectory();
	}
});

function topFirstAjax(dailyDirectory){
	$.ajax({
			type:"post",
			async:false,
			url:"${resource_path}/daily/dailyDirectoryManage/moveDailyDirectory.action",
			data:{
				moveMode:"first",
           		"dailyDirectory.id":getLevel(dailyDirectory.id),
           		"dailyDirectory.parentDailyDirectory.id":dailyDirectory.parentId,
           		"dailyDirectory.indexId":dailyDirectory.indexId
   	          },
			success:function(data){
				//topFirstDaily();
				$("#dailyDirectoryList").trigger("reloadGrid");	
			}
	});
}

function topFirstAjaxLevel(dailyDirectory){
	$.ajax({
			type:"post",
			async:false,
			url:"${resource_path}/daily/dailyDirectoryManage/moveMainDailyDirectory.action?moveMode=first",
        	 data:{
	            "dailyDirectory.id":getLevel(dailyDirectory.id),
	            "dailyDirectory.dailyYear.id":'<s:property value="#parameters.dailyYearId"/>',
	            "dailyDirectory.indexId":dailyDirectory.indexId
        },
		success:function(data){
			//topFirstDaily();
			$("#dailyDirectoryList").trigger("reloadGrid");	
		}
	});
}

function topFirstDaily(){
	var selfDoc =$("#dailyDirectoryList");
		var rowid=selfDoc.jqGrid('getGridParam','selrow');
		var selectDaily = selfDoc.getRowData(rowid);
		var pantDaily = selfDoc.jqGrid('getNodeParent',selectDaily);
		var nextLevleDaily = selfDoc.getRowData($("tr[id="+rowid+"]").next().attr("id"));
		
		if($("#"+ getLevel(nextLevleDaily.id)).length > 0 && (getLevel(nextLevleDaily.level) >= 3)) {
  		 	var thisObject = selfDoc.jqGrid('getNodeParent', nextLevleDaily);
  			var nextLevleDailych  = selfDoc.jqGrid("getNodeChildren", thisObject);
  			 $(nextLevleDailych).each(function(i){
	            var id = $(this).attr("id");
	         	$("#"+getLevel(pantDaily.id)).after($("#"+ id));
	        });
	  	}
		$("#"+ getLevel(pantDaily.id)).after($("#"+getLevel(selectDaily.id)));
}

//到底
$("#toEnd").click(function(){
	if($("#toEnd").attr("disabled")=="true" || $("#toEnd").attr("disabled")=="disabled"){
		return;
	}
	var selfDoc = $("#dailyDirectoryList");
	var rowid = selfDoc.jqGrid('getGridParam','selrow');
	var dailyDirectory  = selfDoc.getRowData(rowid);
	if (getLevel(dailyDirectory.level) >= 3) {
		toEndAjax(dailyDirectory);
		levelButtonDirectory();
	} else {
		toTrunkEnd(dailyDirectory);
		trunkButtonDirectory();
	}
});

function toEndAjax(dailyDirectory){
	$.ajax({
			type:"post",
			async:false,
			url:"${resource_path}/daily/dailyDirectoryManage/moveDailyDirectory.action",
			data:{
					moveMode:"end",
   	           		"dailyDirectory.id":getLevel(dailyDirectory.id),
   	           		"dailyDirectory.parentDailyDirectory.id":dailyDirectory.parentId,
   	           		"dailyDirectory.indexId":dailyDirectory.indexId
   	          },
		success:function(data){
			//toEnd();
			$("#dailyDirectoryList").trigger("reloadGrid");	
		}
	});
}

function toTrunkEnd(dailyDirectory){
	$.ajax({
             url:"${resource_path}/daily/dailyDirectoryManage/moveMainDailyDirectory.action?moveMode=end",
             async:false,
             data:{
            	"dailyDirectory.id":getLevel(dailyDirectory.id),
                "dailyDirectory.dailyYear.id":'<s:property value="#parameters.dailyYearId"/>',
                "dailyDirectory.indexId":dailyDirectory.indexId
            },
            success:function(data){
            	//toEnd();
            	$("#dailyDirectoryList").trigger("reloadGrid");	
            }
    });
}

function toEnd() {
	var selfDoc = $("#dailyDirectoryList");
	var rowid = selfDoc.jqGrid('getGridParam','selrow');
	var selectDaily = selfDoc.getRowData(rowid);
	if (getLevel(selectDaily.level) == 2) {
		var nextLevleDaily = selfDoc.getRowData($("tr[id="+ rowid +"]").next().attr("id"));
		selfDoc.append($("#"+ getLevel(selectDaily.id)));
		
		if($("#"+ getLevel(nextLevleDaily.id)).length > 0 && (getLevel(nextLevleDaily.level) >= 3)) {
  		 	var thisObject = selfDoc.jqGrid('getNodeParent', nextLevleDaily);
  			var nextLevleDailych = selfDoc.jqGrid("getNodeChildren", thisObject);
  			aaaa = nextLevleDailych;
  			$(nextLevleDailych).each(function(i) {
	            var id = $(this).attr("id");
	         	selfDoc.append($("#"+ id));
	        });
  		}
	 } else if (getLevel(selectDaily.level) >= 3) {
		var nextDaily = selectDaily;
	 	var i =0;
	 	while(true) {
	 		var nextRowLevel = selfDoc.getRowData($("tr[id="+ getLevel(nextDaily.id) +"]").next().attr("id")).level;
	 		if($("tr[id="+ getLevel(nextDaily.id) +"]").next().length > 0 && (getLevel(nextRowLevel)) >= 3) {
	 			nextDaily = selfDoc.getRowData($("tr[id="+ getLevel(nextDaily.id) +"]").next().attr("id"));
	 		}else{
	 			break;
	 		}
	    }
	 	$("#"+ getLevel(nextDaily.id)).after($("#"+ getLevel(selectDaily.id)));
    } 
}


/* kk */




function levelButtonDirectory(){
 	var selectedId = $("#dailyDirectoryList").getGridParam("selrow");
	var daily =  $("#dailyDirectoryList").getRowData(selectedId);
	var selLevel = daily.level;
	
	$("#toNext").buttonDisable();
	$("#toEnd").buttonDisable();
	$("#toPrevious").buttonDisable();
	$("#toFirst").buttonDisable();
	
	var dailyId = getLevel(daily.id);
	var shiftRow =$("#dailyDirectoryList").getRowData( $("tr[id="+dailyId+"]").prev().attr("id"));
	var nextRow=$("#dailyDirectoryList").getRowData($("tr[id="+dailyId+"]").next().attr("id"));
	
	var nextRowId;
	for(var i = 0;i < $("#dailyDirectoryList").find(".tree-wrap-ltr").length; i++){
		if(i == 0) {
			nextRow = getNextTrunck(dailyId);
		} else {
			nextRowId = getLevel(nextRow.id);
			nextRow = getNextTrunck(nextRowId);
		}
		if(daily.level>=nextRow.level) {
			break;
		} 
	}
	
	var sLevel = getLevel(shiftRow.level);
	var nLevel = getLevel(nextRow.level);
	if(sLevel >= selLevel) {
		$("#toPrevious").buttonEnable();
		$("#toFirst").buttonEnable();
	}
	if(selLevel <= nLevel) {
		  $("#toNext").buttonEnable();
		  $("#toEnd").buttonEnable();
	} 
} 

function trunkButtonDirectory(){
	var selectedId = $("#dailyDirectoryList").getGridParam("selrow");
	var daily =  $("#dailyDirectoryList").getRowData(selectedId);
	$("#toNext").buttonDisable();
	$("#toEnd").buttonDisable();
	$("#toPrevious").buttonDisable();
	$("#toFirst").buttonDisable();
	
	var dailyId = getLevel(daily.id);
	var shiftRow =$("#dailyDirectoryList").getRowData( $("tr[id="+dailyId+"]").prev().attr("id"));
	var sLevel = getLevel(shiftRow.level);
	if(sLevel>=2){
		$("#toPrevious").buttonEnable();
		$("#toFirst").buttonEnable();
	}
	var lastTrunk = $("#dailyDirectoryList").jqGrid('getNodeParent',$("#dailyDirectoryList").getRowData($("tr").last().attr("id")));
	var lLevel = getLevel(lastTrunk.level);
	if(lLevel == 2){
		lastTrunk = lastTrunk;
	} else if(lLevel > 2){
		for(var i = 2; i < lLevel;i++){
			lastTrunk = getLastTrunck(lastTrunk.id);
		}
	}
	if($("#dailyDirectoryList").getRowData($("tr").last().attr("id")).level>=2 || lLevel==2){
		if(lastTrunk.id!=selectedId &&$("tr").last().attr("id")!=selectedId){
			 $("#toNext").buttonEnable();
			 $("#toEnd").buttonEnable();
		}
	}
}

function getLastTrunck(id){
	var T = $("#dailyDirectoryList").jqGrid('getNodeParent',$("#dailyDirectoryList").getRowData(id));
	if(null != T){
		return T;
	} 
}

function getNextTrunck(id){
	var T = $("#dailyDirectoryList").getRowData($("tr[id="+id+"]").next().attr("id"));
	if(null != T){
		return T;
	} 
}

function getLevel(level){

	var L = level;
	if(/^\d+$/.test(L)) { 
		L = level;
	} else {
		L = getNum(level);
	}
	return L;

}

function loadList(){
	$("#dailyDirectoryList").setGridParam({
		url: "${resource_path}/daily/dailyYearManage/prepareDailyDirectoryList.action?dailyYear.id=${dailyYear.id}",
		datatype: "json",
		page:1
	});
	$("#dailyDirectoryList").trigger("reloadGrid");
}
</script>