<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
		<div class="content"> 
		<div class="ui-corner-all contentNav" id="nav" >
			<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<input class="basic-input" type="text" value="请输入名称" id="name" class="searchtxt" onblur="value=(this.value=='')?'请输入名称':this.value;" onfocus="value=(this.value=='请输入名称')?'':this.value;"/>
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
				<a id="fastSearchBusinessDescription" href="javascript:void(0)"><span>搜索</span></a>
			</div>
			<span class="lineBetween "></span>
				<a id="add" href="javascript:void(0)"><span>新增</span></a>
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
				<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="businessDescriptionList"> </table>
			<div id="businessDescriptionListPager" style="margin-bottom:30px;"></div>
		</div>
	</div>
<div id="businessDescriptionMaintanceDialog"></div>
<div id="viewBusinessDescriptionMaintanceDialog"></div>
<script type="text/javascript">

$(document).ready(function(){

	$("#fastSearchBusinessDescription").click(function(event){
		var name = $("#name").val();
	 	if(name!=null&&"请输入名称"!=name){
			$("#businessDescriptionList").setGridParam({
				url:'${path}/judgmentAnalysis/businessDescriptionManage/findBusinessDescriptionForPage.action',
				datatype: "json",
				postData: {
					"businessDescription.name":$("#name").val()
				},
				page:1
			});
			$("#businessDescriptionList").trigger("reloadGrid");
		}
	});
	

	$("#deleteBusinessDescriptionById").click(function(){
		var selectedRowId=getSelectedIds();
		if(null==selectedRowId || ""==selectedRowId){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		delBusinessDescription(selectedRowId);
	});

	$("#add").click(function(event){
		if($("#add").attr("disabled")){
			return ;
		}
		$("#businessDescriptionMaintanceDialog").createDialog({
			width: 580,
			height: 350,
			title:'新增',
			url:'${path}/judgmentAnalysis/businessDescriptionManage/dispatch.action?mode=add',
			buttons: {
		   		"保存" : function(event){
		   			$("#businessDescriptionForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#update").click(function(event){
		var selectedId=getSelectedIds();
		if(selectedId==null || '' == selectedId){
	 		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		updateBusinessDescription(selectedId);
	});
	
	$("#view").click(function(event){
		var selectedId =getSelectedIds();
		if(selectedId==null || '' == selectedId){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewBusinessDescriptionInfo(selectedId);
	});
	
	function getSelectedIds(){
		var selectedIds = $("#businessDescriptionList").getGridParam("selrow");
		return selectedIds;
	}

	$("#businessDescriptionList").jqGridFunction({
		url:'${path}/judgmentAnalysis/businessDescriptionManage/findBusinessDescriptionForPage.action',
		datatype: "json",
		postData: {},
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"operation",index:"operation",label:"操作",sortable:false,formatter:operateFormatter,width:170,align:"center"},
	        {name:"name",index:'name',sortable:true,label:'名称',align:'center',width:260},
	        {name:"keyName",index:'keyName',sortable:true,label:'关键词',align:'center',width:260}
	        
		],
		multiselect:false,
		ondblClickRow: viewBusinessDescriptionInfo
	});
	
});

function operateFormatter(el,options,rowData){
	return "<a href='javascript:updateBusinessDescription("+rowData.id+")'><span>修改</span></a>&nbsp;&nbsp;<a href='javascript:delBusinessDescription("+rowData.id+")'><span>删除</span></a>";
}
function nameFormatter(el,options,rowData){
	return "<a href='javascript:viewBusinessDescriptionInfo("+rowData.id+")'><U><font color='#6633FF'>"+rowData.name+"</font></U></a>";
} 


function viewBusinessDescriptionInfo(rowid){
	if(rowid==null){
 		return;
	}
	var businessDescription =  $("#businessDescriptionList").getRowData(rowid);
	$("#viewBusinessDescriptionMaintanceDialog").createDialog({
		width: 580,
		height: 350,
		title:'查看',
		modal : true,
		url:'${path}/judgmentAnalysis/businessDescriptionManage/dispatch.action?mode=view&id='+businessDescription.id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function updateBusinessDescription(rowid){
	if(rowid==null){
		return;
	}
	var businessDescription =  $("#businessDescriptionList").getRowData(rowid);
	$("#businessDescriptionMaintanceDialog").createDialog({
		width: 580,
		height: 350,
		title:'修改',
		url:'${path}/judgmentAnalysis/businessDescriptionManage/dispatch.action?mode=edit&id='+businessDescription.id,
		buttons: {
	   		"保存" : function(event){
		   		$("#businessDescriptionForm").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function delBusinessDescription(rowid){
	if(rowid==null){
 		return;
	}
	$.confirm({
		title:"确认删除",
		message:"删除后就不能还原，是否确认删除？",
		width: 300,
		okFunc: function(){
			$.ajax({
				url:"${path}/judgmentAnalysis/businessDescriptionManage/deleteBusinessDescriptionByIds.action?ids="+rowid,
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return ;
					}
					$("#businessDescriptionList").trigger("reloadGrid");
					$.messageBox({message:"删除成功！"});
				}
			});
		}
	});
}

$("#refresh").click(function(){
	$("#name").val('请输入名称');
	refresh();
})
function refresh(){
	var	postData = {
	}
	$("#businessDescriptionList").setGridParam({
		url:'${path}/judgmentAnalysis/businessDescriptionManage/findBusinessDescriptionForPage.action',
		datatype: "json",
		page:1
	});
	$("#businessDescriptionList").setPostData(postData);
	$("#businessDescriptionList").trigger("reloadGrid");
}
$("#refreshSearchKey").click(function(){
	$("#name").attr("value","请输入名称");
});

</script>
