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
				<a id="fastSearchDimension" href="javascript:void(0)"><span>搜索</span></a>
			</div>
			<span class="lineBetween "></span>
				<a id="add" href="javascript:void(0)"><span>新增</span></a>
				<a id="lookDimension" href="javascript:void(0)"><span>查看</span></a>
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="dimensionList"> </table>
			<div id="dimensionListPager" style="margin-bottom:30px;"></div>
		</div>
	</div>
<div id="dimensionMaintanceDialog"></div>
<div id="viewDimensionMaintanceDialog"></div>
<script type="text/javascript">

$(document).ready(function(){

	$("#fastSearchDimension").click(function(event){
		var name = $("#name").val();
	 	if(name!=null&&"请输入名称"!=name){
			$("#dimensionList").setGridParam({
				url:'${path}/judgmentAnalysis/dimension/findDimensionForPage.action',
				datatype: "json",
				postData: {
					"dimension.name":$("#name").val()
				},
				page:1
			});
			$("#dimensionList").trigger("reloadGrid");
		}
	});
	

	$("#deleteDimensionById").click(function(){
		var selectedRowId1=getSelectedIds();
		if(null==selectedRowId1 || ""==selectedRowId1){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		delDimension(selectedRowId1);
	});

	$("#add").click(function(event){
		if($("#add").attr("disabled")){
			return ;
		}
		$("#dimensionMaintanceDialog").createDialog({
			width: 580,
			height: 350,
			title:'新增',
			url:'${path}/judgmentAnalysis/dimension/addDimensionView.action?mode=add',
			buttons: {
		   		"保存" : function(event){
		   			$("#dimensionform").submit();
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
		updateDimension(selectedId);
	});
	
	$("#lookDimension").click(function(event){
		var selectedId =getSelectedIds();
		if(selectedId==null || '' == selectedId){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewDimensionInfo(selectedId);
	});
	
	function getSelectedIds(){
		var selectedIds = $("#dimensionList").getGridParam("selrow");
		return selectedIds;
	}

	$("#dimensionList").jqGridFunction({
		url:'${path}/judgmentAnalysis/dimension/findDimensionForPage.action',
		datatype: "json",
		postData: {},
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"operation",index:"operation",label:"操作",sortable:false,formatter:operateFormatter,width:170,align:"center"},
	        {name:"name",index:'name',sortable:true,label:'名称',align:'center',width:260,formatter:nameFormatter},
      		{name:"keyName",index:"keyName",sortable:true,label:'关键词',align:'center',width:165},
	        {name:"type",index:"type",label:'类型',sortable:true,align:'center',width:100,formatter:typeFormatter},
	        {name:'propertyDomain',label:'字典项名称',width:130,align:'center',sortable:true}
		],
		multiselect:false,
		ondblClickRow: viewDimensionInfo
	});
	
});
function typeFormatter(el,options,rowData){
    if(rowData.type==1){
        return "字典项";
    }else  if(rowData.type==2){
        return "自定义";
    }else{
    	return '其他';
    }
}
function operateFormatter(el,options,rowData){
	return "<a href='javascript:updateDimension("+rowData.id+")'><span>修改</span></a>&nbsp;&nbsp;<a href='javascript:delDimension("+rowData.id+")'><span>删除</span></a>";
}
function nameFormatter(el,options,rowData){
	return "<a href='javascript:viewDimensionInfo("+rowData.id+")'><U><font color='#6633FF'>"+rowData.name+"</font></U></a>";
} 


function viewDimensionInfo(rowid){
	if(rowid==null){
 		return;
	}
	var dimension =  $("#dimensionList").getRowData(rowid);
	$("#viewDimensionMaintanceDialog").createDialog({
		width: 580,
		height: 350,
		title:'查看',
		modal : true,
		url:'${path}/judgmentAnalysis/dimension/viewDimension.action?mode=view&dimension.id='+dimension.id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function updateDimension(rowid){
	if(rowid==null){
		return;
	}
	var dimension =  $("#dimensionList").getRowData(rowid);
	$("#dimensionMaintanceDialog").createDialog({
		width: 580,
		height: 350,
		title:'修改',
		url:'${path}/judgmentAnalysis/dimension/updateDimensionView.action?mode=edit&dimension.id='+dimension.id,
		buttons: {
	   		"保存" : function(event){
		   		$("#dimensionform").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function delDimension(rowid){
	if(rowid==null){
 		return;
	}
	$.confirm({
		title:"确认删除",
		message:"删除后就不能还原，是否确认删除？",
		width: 300,
		okFunc: function(){
			$.ajax({
				url:"${path}/judgmentAnalysis/dimension/deleteDimensionById.action?id="+rowid,
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return ;
					}
					$("#dimensionList").trigger("reloadGrid");
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
	$("#dimensionList").setGridParam({
		url:'${path}/judgmentAnalysis/dimension/findDimensionForPage.action',
		datatype: "json",
		page:1
	});
	$("#dimensionList").setPostData(postData);
	$("#dimensionList").trigger("reloadGrid");
}
$("#refreshSearchKey").click(function(){
	$("#name").attr("value","请输入名称");
});

</script>
