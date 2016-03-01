<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<script type="text/javascript" src="${resource_path }/resource/external/jquery.PrintArea.js"></script>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入群组名称" id="searchByCondition" maxlength="18" class="searchtxt" style="width:180px;" onblur="value=(this.value=='')?'请输入群组名称':this.value;" onfocus="value=(this.value=='请输入群组名称')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
		<a id="searchMyGroupByConditions" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addGroup">
		<a id="addGroup" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增群组</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="editHasContacter">
		<a id="editHasContacter" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护成员</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteAllGroup">
		<a id="deleteAllGroup" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<div>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="myGroupList"></table>
		<div id="myGroupListPager"></div>
	</div>
	<div id="maintainMyGroupDlg"></div>
	<div id="searchMyGroupDlg"></div>
	<div id="maintainMemberDlg"></div>
	<div id="addMemberDlg"></div>
</div>

<script type="text/javascript">
function updateMyGroup(evl,id){
	if(id==null||id==''|| id==undefined){
		$.messageBox({level:'warn',message:"请选择一条数据进行查看！"});
		return;
	}
	$("#maintainMyGroupDlg").createDialog({
		width:650,
		height:300,
		title:'编辑群组联系人',
		url:PATH+"/contact/myGroupManage/dispatch.action?mode=edit&myGroup.id="+id,
		buttons :{
			"保存" : function(){
				$("#maintainForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function viewMember(evl,selectId){
	if(selectId==null || selectId=='' || selectId==undefined){
		$.messageBox({level:'warn',message:"请选择一条数据进行查看"});
		return;	
	}
	$("#maintainMemberDlg").createDialog({
		width: 850,
		height: 630,
		title:"群组成员信息",
		url:PATH+"/contact/myGroupManage/dispatchMyGroupHasContacter.action?contacterId="+selectId+"&mode=view",
		buttons:{
			"关闭" : function(){
				$(this).dialog("close");
				$("#myGroupList").trigger("reloadGrid");
			}
		}
	});
	
}

function deleteMyGroup(evl,id){
	if(id==null||id==''|| id==undefined){
		$.messageBox({level:'warn',message:"请选择一条数据进行删除！"});
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除，群组信息无法恢复，继续删除?",
		okFunc: function(){
			$.ajax({
				url:PATH+'/contact/myGroupManage/deleteMyGroup.action?myGroup.id='+id,
				success:function(data){
					if(data == true){
						$.messageBox({ message:"成功删除群组信息!"});
						$("#myGroupList").trigger("reloadGrid");
						return;
					}
		            $.messageBox({
						message:data,
						level: "error"
					});
		        }
			});
	   }
 	});
}

function callback(){
    var dfop = {
    		
    }
    TQ.newMyGroupList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/interaction/contact/myGroup/newMyGroupList.js',
    callback: callback
})

</script>

