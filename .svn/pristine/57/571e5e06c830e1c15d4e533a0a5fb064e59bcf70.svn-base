<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="center-left">
	<div>
		<div class="ui-widget">
				<input id="propertiesDomain_autocomplete" type="text" value=""/>
				<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
		</div>
	</div>
	<div >
		<ul id="propertiesDomain" class="propertyDomainList">
			<c:forEach items="${propertiesDomain }" var="propertiesDomain">
		    	<li><a href="javascript:void(0)" id="propertiesDomain_${propertiesDomain.id}" name="${propertiesDomain.id}">${propertiesDomain.domainName}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>

<div class="center-right">
	<div class="content">
		<input type="hidden" id="propertyDomainId" />
		<div class="ui-corner-all" id="nav">
			<c:if test="${userName=='admin' }">
				<pop:JugePermissionTag ename="addProperty">
					<a id="add" href="javascript:void(0)"><span>新增</span></a>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="updateProperty">
					<a id="update" href="javascript:void(0)"><span>修改</span></a>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="deleteProperty">
					<a id="delete" href="javascript:void(0)"><span>删除</span></a>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="moveProperty">
					<a id="toPrevious" href="javascript:void(0)"><span>上移</span></a>
					<a id="toNext" href="javascript:void(0)"><span>下移</span></a>
					<a id="toFirst" href="javascript:void(0)"><span>到顶</span></a>
					<a id="toEnd" href="javascript:void(0)"><span>到底</span></a>
				</pop:JugePermissionTag>
			</c:if>
			<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
				<a href="javascript:;" id="refreshMobileCache"><span>刷新手机代理缓存</span></a>
			</s:if>
		</div>
			
		<table id="propertyDataGrid"></table>
	</div>
	<div id="property-dailog"></div>
</div>
<script type="text/javascript" >
var currentDomainProperty;
function formatInternelProperty(rowValue) {
	setTimeout(delay,900);
	if(!currentDomainProperty.systemSensitive){
		return "无系统内置属性";
	}
	if(jQuery.isEmptyObject(currentDomainProperty) || jQuery.isEmptyObject(currentDomainProperty.internaleProperties)){
		return "系统错误，不能正常显示";
	}
	var properties = currentDomainProperty.internaleProperties;
    for (var prop in properties){
        if(rowValue == (properties[prop]).identify){
            return (properties[prop]).displayName;
        }
    }
    return "系统错误，不能正常显示";
}
function delay(){
}
$(document).ready(function(){
	var centerHeight=$("div.ui-layout-center").outerHeight();
	$("#propertiesDomain").height(centerHeight-75);
	var propertyDomainId;
	function getPropertyDomainInternalName(srcObj){
		propertyDomainId = $(srcObj).attr("name");
		var domainName = $("#propertyTitle").html(domainName);
		$("#propertyDomainId").val(propertyDomainId);
		$.ajax({
			url:'${path}/sysadmin/propertyManage/findPropertyDomainById.action?propertyDomainId=' + propertyDomainId,
			cache:false,
			success:function(data){
                  if(!data.id){
                 	 $.messageBox({
							message:data,
							level: "error"
				     });
	                return;
                  }
                 currentDomainProperty = data;
        		if(propertyDomainId!="default"){
        			$("#add").buttonEnable();
        		}
	        }
		});
	}
	$("#propertiesDomain a").click(function(){
		var firstLoad = $("#propertiesDomain a.select").size();
		$("#propertiesDomain a").removeClass("select");
		$(this).addClass("select");
		getPropertyDomainInternalName($(this));
		if(firstLoad!=0){
			$("#propertyDataGrid").setGridParam({url:"${path}/sysadmin/propertyManage/ajaxPropertyDict.action?propertyDomainId="+propertyDomainId});
			$("#propertyDataGrid").trigger("reloadGrid");
		}
	});
	$("#propertiesDomain a:first").click();

	$("#add").click(function(){
		if($("#propertyDomainId").val() == ""){
			return ;
		}
		$("#property-dailog").createDialog({
			width:300,
			height:175,
			title:'新建系统属性',
			url:"${path }/sysadmin/propertyManage/prepareProperty.action?mode=add&propertyDomainId="+$("#propertyDomainId").val(),
			buttons :{
				"保存" : function(){
					$("#propertyDict-create-form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	
	$("#refreshOrgTree").click(function(){
		$("#propertiesDomain_autocomplete").val("");
	});
	
	$("#propertiesDomain_autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "${path}/sysadmin/propertyManage/findPropertyDomainBydomainName.action",
				data:{"domainName":request.term},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.domainName,
							value: item.domainName,
							id: item.id
						}
					}))
				},
				error : function(){
					$.messageBox({
						message:"搜索失败，请重新登入！",
						level:"error"
					});
				}
			})
		},
		select: function(event, ui) {
			var thisItem=$("#propertiesDomain_"+ui.item.id);
			var height=0;
			thisItem.click();
			thisItem.closest("li").prevAll().each(function(){
				height=height+$(this).outerHeight(true);
			})
			$("#propertiesDomain").stop().animate({ scrollTop: height }, 300);
		}
	});

	$("#update").click(function(event){
		var selectedId = $("#propertyDataGrid").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		var rowData=  $("#propertyDataGrid").getRowData(selectedId);
		$("#property-dailog").createDialog({
			width:300,
			height:175,
			title:'修改系统属性',
			modal : true,
			resizable : true,
			url:"${path }/sysadmin/propertyManage/prepareProperty.action?mode=edit&propertyDict.id="+ rowData.encryptId +"&propertyDomainId="+$("#propertyDomainId").val(),
			buttons: {
			   "保存" : function(event){
			      $("#propertyDict-create-form").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
	   });
	});

	$('#refreshMobileCache').click(function(){
		$.ajax({
			type:"post",
			url:"${path}/sysadmin/refreshMobileCacheManage/refreshMobileCache.action",
			success:function(data){
				$.messageBox({message:"刷新手机代理缓存成功！"});
			}
		});
	});
	
	$('#toEnd').click(function(){
		var selectedId = $("#propertyDataGrid").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		var dictdata =  $("#propertyDataGrid").getRowData(selectedId);
		$.ajax({
			type:"post",
			url:"${path}/sysadmin/propertyManage/movePropertyDict.action",
			data:{
				"propertyDict.id":dictdata.encryptId,
				 position:"last",
				"propertyDict.propertyDomain.id":$("#propertyDomainId").val(),
				"referPropertyDictId":$("#"+selectedId+"[role='row']").next().attr("id")
			},
			success:function(data){
				$("#propertyDataGrid").toEnd();
				toggleButtons();
			}
		});
	});

	$('#toFirst').click(function(){
		var selectedId = $("#propertyDataGrid").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		var dictdata =  $("#propertyDataGrid").getRowData(selectedId);
		$.ajax({
			type:"post",
			url:"${path}/sysadmin/propertyManage/movePropertyDict.action",
			data:{
				"propertyDict.id":dictdata.encryptId,
				 position:"first",
				"propertyDict.propertyDomain.id":$("#propertyDomainId").val(),
				"referPropertyDictId":$("#"+selectedId+"[role='row']").prev().attr("id")
			},
			success:function(data){
				$("#propertyDataGrid").toFirst();
				toggleButtons();
			}
		});
	});

	$('#toPrevious').click(function(){
		var selectedId = $("#propertyDataGrid").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		var dictdata =  $("#propertyDataGrid").getRowData(selectedId);
		$.ajax({
			type:"post",
			url:"${path}/sysadmin/propertyManage/movePropertyDict.action",
			data:{
				"propertyDict.id":dictdata.encryptId,
				 position:"before",
				"propertyDict.propertyDomain.id":$("#propertyDomainId").val(),
				"referPropertyDictId":$("#"+selectedId+"[role='row']").prev().attr("id")
			},
			success:function(data){
				$("#propertyDataGrid").toPrevious();
				toggleButtons();
			}
		});
	});

	$('#toNext').click(function(){
		var selectedId = $("#propertyDataGrid").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		var dictdata =  $("#propertyDataGrid").getRowData(selectedId);
		$.ajax({
			type:"post",
			url:"${path}/sysadmin/propertyManage/movePropertyDict.action",
			data:{
				"propertyDict.id":dictdata.encryptId,
				 position:"after",
				"propertyDict.propertyDomain.id":$("#propertyDomainId").val(),
				"referPropertyDictId":$("#"+selectedId+"[role='row']").next().attr("id")
			},
			success:function(data){
				$("#propertyDataGrid").toNext();
				toggleButtons();
			}
		});
	});
	$("#propertyDataGrid").jqGridFunction({
		url:"${path}/sysadmin/propertyManage/ajaxPropertyDict.action?propertyDomainId="+propertyDomainId,
	   	datatype: "json",
	   	colModel:[
			{name:'id', index:'id', hidden:true,sortable:false },
			{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			{name:'displayName', label:'显示名称', sortable:true },
			{name:'internalId',label:'内置属性', sortable:true,formatter: formatInternelProperty},
			{name:'createUser', label:'创建人', sortable:true },
			{name:'createDate', label:'创建时间', sortable:true }
	   	],
	   	rowNum:-1,
	    pager:false,
	    ondblClickRow: doubleClickTable,
	    shrinkToFit:true,
	   	showColModelButton:false,
	    onSelectRow:function(){}
	});

	$("#delete").click(function(event){
		var selectedId = $("#propertyDataGrid").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		$.confirm({
			title:"确认删除",
			message:"该系统属性信息删除后,将无法恢复,您确认删除该系统属性信息吗?",
			width:400,
			okFunc:function(){
				deletePropertyDict(propertyDomainId);
			}
		});
	});

});

//当在datagrid中选择网格后，enable行操作按钮
function toggleButtons(){
	var rowId = $("#propertyDataGrid").jqGrid('getGridParam','selrow');
	if(rowId==undefined){

	}else{
		if($("#"+rowId+"[role='row']").prev().attr("id")==undefined){
			$.messageBox({level:'warn',message:"第一条数据不能进行到顶或上移！"});
			return;
		}
		if($("#"+rowId+"[role='row']").next().attr("id")==undefined){
			$.messageBox({level:'warn',message:"最后一条数据不能进行到底或下移！"});
			return;
		}
	}

}

//双击行操作,查询这行记录的明细
function doubleClickTable(rowid){
		var dictdata =  $("#propertyDataGrid").getRowData(rowid);
		$("#property-dailog").createDialog({
			width:600,
			height:200,
			title:'查看系统属性明细',
			modal : true,
			resizable : true,
			url:"${path}/sysadmin/propertyManage/propertyDetail.action?mode=view&propertyDict.id=" + dictdata.encryptId + "&propertyDomainId="+$("#propertyDomainId").val(),
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
}

function deletePropertyDict(propertyDomainId){
	var	selectedId = $("#propertyDataGrid").getGridParam("selrow");
	if(selectedId==null){
		$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
		return;
	}
	var rowData=  $("#propertyDataGrid").getRowData(selectedId);
	$.ajax({
		url:"${path}/sysadmin/propertyManage/deletePropertyDictById.action?propertyDomainId="+propertyDomainId+"&propertyDict.id="+ rowData.encryptId,
		success:function(responseData){
			if (responseData>0){
				$("#propertyDataGrid").delRowData(selectedId);
			    $.messageBox({message:"系统属性删除成功!"});
			}else{
				$.messageBox({level:'error',message:"找不到要删除的系统属性信息，不能删除!"});
			}
		}
	});
}
</script>