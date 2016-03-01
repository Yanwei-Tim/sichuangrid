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
				<a id="fastSearchDimensionCombination" href="javascript:void(0)"><span>搜索</span></a>
			</div>
			<span class="lineBetween "></span>
				<a id="add" href="javascript:void(0)"><span>新增</span></a>
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="dimensionCombinationList"> </table>
			<div id="dimensionCombinationListPager" style="margin-bottom:30px;"></div>
		</div>
	</div>
<div id="dimensionCombinationMaintanceDialog"></div>
<div id="viewDimensionCombinationMaintanceDialog"></div>
<script type="text/javascript">

$(document).ready(function(){

	$("#fastSearchDimensionCombination").click(function(event){
		var name = $("#name").val();
	 	if(name!=null&&"请输入名称"!=name){
			$("#dimensionCombinationList").setGridParam({
				url:'${path}/judgmentAnalysis/dimensionCombinationManage/findDimensionCombinationForPage.action',
				datatype: "json",
				postData: {
					"dimensionCombination.name":$("#name").val()
				},
				page:1
			});
			$("#dimensionCombinationList").trigger("reloadGrid");
		}
	});
	

	$("#deleteDimensionCombinationById").click(function(){
		var selectedRowId=getSelectedIds();
		if(null==selectedRowId || ""==selectedRowId){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		delDimensionCombination(selectedRowId);
	});

	$("#add").click(function(event){
		if($("#add").attr("disabled")){
			return ;
		}
		$("#dimensionCombinationMaintanceDialog").createDialog({
			width: 580,
			height: 350,
			title:'新增',
			url:'${path}/judgmentAnalysis/dimensionCombinationManage/dispatch.action?mode=add',
			buttons: {
		   		"保存" : function(event){
		   			$("#dimensionCombinationForm").submit();
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
		updateDimensionCombination(selectedId);
	});
	
	$("#view").click(function(event){
		var selectedId =getSelectedIds();
		if(selectedId==null || '' == selectedId){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewDimensionCombinationInfo(selectedId);
	});
	
	function getSelectedIds(){
		var selectedIds = $("#dimensionCombinationList").getGridParam("selrow");
		return selectedIds;
	}

	$("#dimensionCombinationList").jqGridFunction({
		url:'${path}/judgmentAnalysis/dimensionCombinationManage/findDimensionCombinationForPage.action',
		datatype: "json",
		postData: {},
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"operation",index:"operation",label:"操作",sortable:false,formatter:operateFormatter,width:170,align:"center"},
	        {name:"name",index:'name',sortable:true,label:'名称',align:'center',width:260},
	        {name:"keyName",index:'keyName',sortable:true,label:'关键词',align:'center',width:260},
	        {name:"tableName",index:'tableName',sortable:true,label:'组合表名',align:'center',width:260},
	        {name:"combination",index:'combination',sortable:true,label:'组合名称',align:'center',width:260}
		],
		multiselect:false,
		ondblClickRow: viewDimensionCombinationInfo
	});
	
});

function operateFormatter(el,options,rowData){
	return "<a href='javascript:updateDimensionCombination("+rowData.id+")'><span>修改</span></a>&nbsp;&nbsp;<a href='javascript:delDimensionCombination("+rowData.id+")'><span>删除</span></a>";
}
function nameFormatter(el,options,rowData){
	return "<a href='javascript:viewDimensionCombinationInfo("+rowData.id+")'><U><font color='#6633FF'>"+rowData.name+"</font></U></a>";
} 


function viewDimensionCombinationInfo(rowid){
	if(rowid==null){
 		return;
	}
	var dimensionCombination =  $("#dimensionCombinationList").getRowData(rowid);
	$("#viewDimensionCombinationMaintanceDialog").createDialog({
		width: 580,
		height: 350,
		title:'查看',
		modal : true,
		url:'${path}/judgmentAnalysis/dimensionCombinationManage/dispatch.action?mode=view&id='+dimensionCombination.id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function updateDimensionCombination(rowid){
	if(rowid==null){
		return;
	}
	var dimensionCombination =  $("#dimensionCombinationList").getRowData(rowid);
	$("#dimensionCombinationMaintanceDialog").createDialog({
		width: 580,
		height: 350,
		title:'修改',
		url:'${path}/judgmentAnalysis/dimensionCombinationManage/dispatch.action?mode=edit&id='+dimensionCombination.id,
		buttons: {
	   		"保存" : function(event){
		   		$("#dimensionCombinationForm").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function delDimensionCombination(rowid){
	if(rowid==null){
 		return;
	}
	$.confirm({
		title:"确认删除",
		message:"删除后就不能还原，是否确认删除？",
		width: 300,
		okFunc: function(){
			$.ajax({
				url:"${path}/judgmentAnalysis/dimensionCombinationManage/deleteDimensionCombinationByIds.action?ids="+rowid,
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return ;
					}
					$("#dimensionCombinationList").trigger("reloadGrid");
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
	$("#dimensionCombinationList").setGridParam({
		url:'${path}/judgmentAnalysis/dimensionCombinationManage/findDimensionCombinationForPage.action',
		datatype: "json",
		page:1
	});
	$("#dimensionCombinationList").setPostData(postData);
	$("#dimensionCombinationList").trigger("reloadGrid");
}
$("#refreshSearchKey").click(function(){
	$("#name").attr("value","请输入名称");
});

</script>
