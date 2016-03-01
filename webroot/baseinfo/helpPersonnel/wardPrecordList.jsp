<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<input type="hidden" id="domainId" name="" value="<s:property value="#parameters.domainId"/>"/>
<input type="hidden" id="keyType" name="" value="<s:property value="#parameters.keyType"/>"/>

<div class="content">
	<div class="ui-cornRr-all" id="nav">
			<a id="addHelpPrecord" href="javascript:void(0)" >新增</span></a>
			<a id="updateHelpPrecord" href="javascript:void(0)"><span>修改</span></a>
			<a id="viewHelpPrecord" href="javascript:void(0)"><span>查看</span></a>
		   <a id="deleteHelpPrecord" href="javascript:void(0)"><span>删除</span></a>
	</div>
</div>

<div style="width: 100%;">
	<table id="helpPrecordList"></table>
	<div id="helpPrecordListPager"></div>
</div>

<script type="text/javascript">
	var keyType=$("#keyType").val();
	var domainId=$("#domainId").val();

	function loadList(){
		$("#helpPrecordList").setGridParam({
			url:'${path}/baseinfo/helpPrecord/findHelpPrecord.action',
			datatype: "json",
			page:1
		});
		$("#helpPrecordList").setPostData({
			"personnelType":keyType,
			"personnelId":domainId
		});
		$("#helpPrecordList").trigger("reloadGrid");
	}
	$(document).ready(function(){
		if("view" == $("#mode").val()){
			$("#addHelpPrecord").buttonDisable();
			$("#updateHelpPrecord").buttonDisable();
			$("#deleteHelpPrecord").buttonDisable();
		}
		$("#helpPrecordList").jqGridFunction({
			datatype: "local",
			colModel:[
				{name:"id", index:"id", hidden:true },
				{name:"helpTime", sortable:false,index:"floorpersonsTime", label: '监管时间',width:100},
				{name:"address", sortable:false, label:'监管地点', width:100},
				{name:"helpPersonnelName", sortable:false, label: '监管人员',width:200},
				{name:"events", sortable:false, label: '监管情况'}
			],
			loadComplete: afterPrecordLoad,
			width: <s:property value="#parameters.width"/>,
			height: <s:property value="#parameters.height"/>,
			onSelectRow:seleHelpPrecordRow,
			ondblClickRow: viewWardHelp,
			showColModelButton:false
		});
		loadList();
		$("#addHelpPrecord").click(function(){
			var domainId = $("#domainId").val();
			if(domainId==null || domainId==""){
				domainId=0;
			}
			$("#helpPrecordDialog").createDialog({
				width: 400,
				height: 400,
				title:'新增监管情况',
				url:'${path}/baseinfo/helpPrecord/dispatch.action?personnelId='+domainId+'&personnelType=<s:property value="#parameters.keyType"/>&mode=add&precordType=ward',
				buttons: {
					"保存" : function(event){
				     $("#helpPrecordForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});

		$("#updateHelpPrecord").click(function(){
			var selectedId = $("#helpPrecordList").getGridParam("selrow");
			if(selectedId==null){
			  return;
			}
			$("#helpPrecordDialog").createDialog({
				width: 400,
				height: 400,
				title:'修改监管情况',
				url:'${path}/baseinfo/helpPrecord/dispatch.action?helpPrecord.id='+selectedId+'&mode=edit&precordType=ward',
				buttons: {
					"保存" : function(event){
				      $("#helpPrecordForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});

		$("#viewHelpPrecord").click(function(){
			viewWardHelp();
		});

		$("#deleteHelpPrecord").click(function(){
			var selectedId = $("#helpPrecordList").getGridParam("selrow");
			if(selectedId == null){
				return;
			}
			$.confirm({
				title:"确认删除",
				message:"该监管情况信息删除后，将无法恢复,您确认删除该监管情况吗?",
				width: 300,
				okFunc:deleteHelpPrecord
			});
		});


	});

	function viewWardHelp(){
		var selectedId = $("#helpPrecordList").getGridParam("selrow");
		if(selectedId==null){
		  return;
		}
		$("#helpPrecordDialog").createDialog({
			width: 400,
			height: 400,
			title:'查看监管情况',
			url:'${path}/baseinfo/helpPrecord/dispatch.action?helpPrecord.id='+selectedId+'&mode=view&precordType=ward',
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function afterPrecordLoad(){
		disableHelpPrecordButton();
	}

	function seleHelpPrecordRow(){
		if("view" == $("#mode").val()){
			$("#addHelpPrecord").buttonDisable();
			$("#updateHelpPrecord").buttonDisable();
			$("#deleteHelpPrecord").buttonDisable();
		}else{
			$("#updateHelpPrecord").buttonEnable();
			$("#deleteHelpPrecord").buttonEnable();
		}
		$("#viewHelpPrecord").buttonEnable();
	}

	function disableHelpPrecordButton(){
		$("#updateHelpPrecord").buttonDisable();
		$("#viewHelpPrecord").buttonDisable();
		$("#deleteHelpPrecord").buttonDisable();
	}


	function deleteHelpPrecord(){
		var selectedId = $("#helpPrecordList").getGridParam("selrow");
		if(selectedId==null){
			 return;
		}
		$.ajax({
			url:"${path}/baseinfo/helpPrecord/deleteHelpPrecordById.action",
			data:{
				"helpPrecord.id":selectedId
			},
			success:function(responseData){
				if(responseData){
					if(responseData == true){
						$("#helpPrecordList").delRowData(selectedId);
						$.messageBox({message:"监管情况删除成功！"});
						disableHelpPrecordButton();
					}else{
						$.messageBox({message:"监管情况删除失败！"});
					}
				}
			}
		});
	}
</script>