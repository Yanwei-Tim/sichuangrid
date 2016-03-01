<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
	 .edit-cell{background: none !important;} 
</style>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />

<div class="center-left">
	<div>
		<div class="ui-widget">
			<input id="org_autocomplete" type="text" value=""/>
			<button id="refreshOrgTree" class="ui-icon ui-icon-refresh" ></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>
<div class="center-right">
	<div class="content">
		<s:if test="#loginAction.user.userName=='admin'">
		<div class="ui-corner-all" id="nav">
			<div>
				<a id="update"  href="javascript:void(0)"><span>修改</span></a>
				<a id="toPrevious" href="javascript:void(0)"><span>上移</span></a>
				<a id="toNext" href="javascript:void(0)"><span>下移</span></a>
				<a id="toFirst" href="javascript:void(0)"><span>到顶</span></a>
				<a id="toEnd" href="javascript:void(0)"><span>到底</span></a>
			</div>
		</div>
		</s:if>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="permissionList"> </table>
			<div id="assessmentUserListPager"></div>
		</div>
	</div>
</div>
<input type="hidden" id="editSelectedId" />
<div id="permissionMaintanceDialog"></div>
<div id="noticeDialog"></div>

<script type="text/javascript">
var dialogWidth=580;
var dialogHeight=540;
var currentOrgId;
var tree;
$(function(){
	var centerHeight=$(".ui-layout-center").height();
	$(".orgTree").height(centerHeight-70);
	currentOrgId="";
	$("#org_autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "${path}/sysadmin/permissionManage/findPermissionsByPermissionName.action",
				data:{"name":request.term},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.cname+"-"+item.moduleName,
							value: item.cname,
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
			$("#user_autocomplete").removeAttr("userId");
			$("#user_autocomplete").val("");
			$.ajax({
				url:PATH+"/sysadmin/permissionManage/findPermissionsNodeId.action?id="+ui.item.id,
				success:function(data){
					if(data.indexOf("/")==0){
						data = data.substring(1)
					}
				 	var yes = "orgTree-root/" + data ;
					$.searchChild(tree,yes);
				}
			});
		}
	});
	function afterChangNode(node) {
	    var id = node.attributes.id;
	    currentOrgId=id;
	    clearUserUserAutocomplete=true;
        $("#org_autocomplete").val(node.attributes.text);
        if(clearUserUserAutocomplete){
            $("#user_autocomplete").removeAttr("userId");
            $("#user_autocomplete").val("");
        }
        findUserByOrgId(id);
	    showButtons();
	}
	//再这里调用orgTreeManage.js中的方法,初始化了树start
	tree=$("#orgTree").initPermissionTree();
	//再这里调用orgTreeManage.js中的方法,初始化了树end

	//绑定tree的a
	$.addClick(tree,afterChangNode);
	//以下为几个按钮
	$("#update").click(function(){
    	var selectedId = $("#permissionList").getGridParam('selrow');
    	if(selectedId == null){
        	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		//改名
       	var permission = $("#permissionList").getRowData(selectedId);
    	$("#permissionMaintanceDialog").createDialog({
            width:dialogWidth,
            height:200,
            title:'修改权限名称',
            url:'${path}/sysadmin/permissionManage/updatePermission.action?mode=edit&permission.id='+ permission.id ,
            buttons:{
                "保存":function(event){
                    $("#maintainForm").submit();
                },
                "关闭":function(){
                    $(this).dialog("close");
                }
            }
        });
    });
    $("#toPrevious").click(function(){
    	var selectedId = $("#permissionList").getGridParam("selrow");
        if(selectedId == null){
        	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
        var permission = $("#permissionList").getRowData(selectedId);
		try{
        	$.toPreviousNode(tree,selectedId);
		}catch(err){
		}
		var previd=$("#"+selectedId).prev().attr("id");
		if(previd==undefined||previd==""){
			$.messageBox({level:'warn',message:"第一条数据不能进行到顶或上移！"});
			return;
		}
        $.ajax({
            type:"post",
            url:'${path}/sysadmin/permissionManage/movePermission.action?mode=previous&permission.id='+ permission.id ,
            success:function(data){
				if(data){
	                $("#permissionList").toPrevious();
	                $("#permissionList").trigger("reloadGrid");
				}else{
					$.messageBox({
						message:"上移没有成功！",
						level:"error"
					});
				}
            }
        });
    });
    $("#toNext").click(function(){
    	var selectedId = $("#permissionList").getGridParam("selrow");
        if(selectedId == null){
        	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
        var permission = $("#permissionList").getRowData(selectedId);
		try{
        	$.toNextNode(tree,selectedId);
		}catch(err){
		}
		var nextid=$("#"+selectedId).next().attr("id");
		if(nextid==undefined||nextid==""){
			$.messageBox({level:'warn',message:"最后一条数据不能进行到底或下移！"});
			return;
		}
        $.ajax({
            type:"post",
            url:'${path}/sysadmin/permissionManage/movePermission.action?mode=next&permission.id='+ permission.id ,
            success:function(data){
				if(data){
	                $("#permissionList").toNext();
	                $("#permissionList").trigger("reloadGrid");
					}else{
					$.messageBox({
						message:"下移没有成功！",
						level:"error"
					});
				}
            }
        });

    });
    $("#toFirst"). click(function(){
    	var selectedId = $("#permissionList").getGridParam("selrow");
        if(selectedId == null){
        	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
        var permission = $("#permissionList").getRowData(selectedId);
		try{
        	$.toFirstNode(tree,selectedId);
		}catch(err){
		}
		var previd=$("#"+selectedId).prev().prev().attr("id");
		if(previd==undefined||previd==""){
			$.messageBox({level:'warn',message:"第一条数据不能进行到顶或上移！"});
			return;
		}
		$.ajax({
            type:"post",
            url:'${path}/sysadmin/permissionManage/movePermission.action?mode=first&permission.id='+permission.id,
            success:function(data){
				if(data){
	                $("#permissionList").toEnd();
	                $("#permissionList").trigger("reloadGrid");
				}else{
					$.messageBox({
						message:"移到最顶一位出现问题！",
						level:"error"
					});
				}
            }
        });
    });
    $("#toEnd").click(function(){
    	var selectedId = $("#permissionList").getGridParam("selrow");
        if(selectedId == null){
        	$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
        var permission = $("#permissionList").getRowData(selectedId);
		try{
        	$.toEndNode(tree,selectedId);
		}catch(err){
		}
		var nextid=$("#"+selectedId).next().next().attr("id");
		if(nextid==undefined||nextid==""){
			$.messageBox({level:'warn',message:"最后一条数据不能进行到底或下移！"});
			return;
		}
		$.ajax({
            type:"post",
            url:'${path}/sysadmin/permissionManage/movePermission.action?mode=end&permission.id='+ permission.id ,
            success:function(data){
				if(data){
	                $("#permissionList").toEnd();
	                $("#permissionList").trigger("reloadGrid");
				}else{
					$.messageBox({
						message:"移到最后一位出现问题！",
						level:"error"
					});
				}
            }
        });
    });

	$("#isLock").change(function(event){
		lockUserButtonText($(event.srcElement).val());
		$("#user_autocomplete").empty();
		findUserByOrgId();
		showButtons();
	});

	$("#refreshOrgTree").click(function(){
		$.selectRootNode(tree);
	});

	$('#refreshOrgTree li').hover(
			function() { $(this).addClass('ui-state-hover'); },
			function() { $(this).removeClass('ui-state-hover'); }
	);

	$("#permissionList").jqGridFunction({
		//设置grid单元格可编辑
		cellEdit: true,
		cellurl:"${path}/baseinfo/primaryOrgManage/setSeq.action",
		cellsubmit: "clientArray",
		url:'${path}/sysadmin/permissionManage/getChildPermissions.action',
		datatype: "local",
		sortname:"id",
		postData: {
			"parentId":function(){
    			return currentOrgId;
   			 }
        },
	   	colModel:[
	   	    {name:'id',index:'id',hidden:true,sortable:false},
	   	    {name:'cname',label:'权限名',align:'center',sortable:true},
	   	    {name:'ename',index:'ename',label:'内置码',sortable:true},
	   	    {name:'permissionType',index:'permissionType',label:'权限类型',formatter:isPermissionFormatter,align:'center',sortable:true},
	   	    {name:'moduleName',index:'moduleName',label:'模块名',align:'center',sortable:true},
	   	 	{name:'indexId',index:'indexId',label:'排列序号',width:200,sortable:true,align:"center",editable:true,editrules:{custom:true, custom_func:indexIdIsNumber}}
		],
		shrinkToFit:true,
		rowNum: -1,
		showColModelButton:false,
		beforeSubmitCell:function(rowid, cellname, value, iRow, iCol){
			$(".edit-cell.ui-state-highlight").attr("class","");
			 $.ajax({
				url:"${path}/sysadmin/permissionManage/setPermissionSeq.action",
				data:{
					"seq":value,
					"id":rowid
				},
				success:function(data){
					$("#permissionList").trigger("reloadGrid");
				}
			}); 
	         
		},
		afterEditCell:function(rowid, cellname, value, iRow, iCol){
			$("#editSelectedId").val(rowid);
			$(".edit-cell.ui-state-highlight").attr("class","");
		}
	});

});

//验证排列序号是否是数字并且验证是否超过最大的排序值
function indexIdIsNumber(value,colname) {
		var reg=/^[0-9]+$/;
	if(value==""&& value==null&& typeof(value)=="undefined")
		return [false,"值错误：不能为空"];
    if (value!=""&& value!=null&& typeof(value)!="undefined"&& !reg.test(value))
         return [false,"值错误：请输入自然数"];
    if(!isCanSeq($("#editSelectedId").val(),value))
    	 return [false,"值错误：不能大于最大的排列序号"];
    	   
        return [true,""]; 
  }
//验证是否超过最大的排序值
 function isCanSeq(rowid,value){
    	var isCanSeqResult=false;
    	 $.ajax({
				url:"${path}/sysadmin/permissionManage/isCanSeq.action",
				async:false,
				data:{
					"seq":value,
					"id":rowid
				},
				success:function(data){
					if(data=='true' || data==true){
						isCanSeqResult=data;
					}
						
				}
			}); 
    	 return isCanSeqResult; 
    }  
function isPermissionFormatter(el,options,rowData){
    if(rowData.permissionType)
        return "菜单";
    else
        return "功能";
}

function doAssessmentUser(){
	var selectId = $("#permissionList").getGridParam("selrow");
	if(selectId == null){return;}
	var assessmentUserVo = $("#permissionList").getRowData(selectId);
    var assessmentUserVo = $("#permissionList").getRowData(selectId);
    $.ajax({
    	url:"${path}/sysadmin/assessmentUserManage/assessmentUser.action",
        data:{
                "assessmentUser.userId":assessmentUserVo.id
            },
        success:function(data){
                $("#permissionList").setRowData(data.id,data);
                $.messageBox({message:"已成功将该用户纳入考核!"});
                showButtons();
            }
    });
}

function doNotAssessmentUser(){
	var selectId = $("#permissionList").getGridParam("selrow");
    if(selectId == null){return;}
    var assessmentUserVo = $("#permissionList").getRowData(selectId);
    $.ajax({
        url:"${path}/sysadmin/assessmentUserManage/doNotAssessmentUser.action",
        data:{
                "assessmentUser.userId":assessmentUserVo.id,
                "assessmentUser.userName":assessmentUserVo.name
            },
        success:function(data){
            	$("#permissionList").setRowData(data.id,data);
                $.messageBox({message:"已成功将该用户设置为不纳入考核!"});
                showButtons();
            }
    });
}

function showButtons(){
	//将在上部增加几个按钮
	var selectId = $("#permissionList").getGridParam("selrow");
	if(selectId == null){return;}
	var previd=$("#"+selectId).prev().attr("id");
	var nextid=$("#"+selectId).next().attr("id");
	if(previd==undefined||previd==""){
		$.messageBox({level:'warn',message:"第一条数据不能进行到顶或上移！"});
		return;
	}
	if(nextid==undefined||nextid==""){
		$.messageBox({level:'warn',message:"最后一条数据不能进行到底或下移！"});
		return;
	}
	var assessmentUser = $("#permissionList").getRowData(selectId);
	if(assessmentUser.lock == 'false'){
	    if(assessmentUser.assessmented && assessmentUser.assessmented != '是'){
	        $("#assessment").buttonEnable();
	        $("#doNotAssessment").buttonDisable();
	    }else{
	        $("#assessment").buttonDisable();
	        $("#doNotAssessment").buttonEnable();
	    }
	}
}

function findUserByOrgId(){
	$("#permissionList").setGridParam({datatype:'json'});
	$("#permissionList").trigger("reloadGrid");
}

function setUserautocompleteLockstatus(lock){
	if (lock == "true"){
		$("#user_autocomplete").option( "searchLockStatus", "locked");
	}else{
		$("#user_autocomplete").option( "searchLockStatus", "unlocked");
	}
}

function stringFormatter(str){
	if(str==undefined){
		return "";
	}
	return str;
}
function isActivedUsers(){
	if( $("#isLock").val() == "false"){ return true;}
    return false;
}


function isAdminFormatter(el,options,rowData){
    if(rowData.admin){
        return "是";
    }
    if(rowData["admin"]){
        return "是";
    }
    return "否";
}

function isAssessmentFormatter(el,options,rowData){
    if(rowData.assessmented)
        return "是";
    else
        return "否";
}

</script>
