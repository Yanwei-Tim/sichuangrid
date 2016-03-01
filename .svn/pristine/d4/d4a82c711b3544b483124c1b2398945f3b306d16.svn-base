<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript" src="${resource_path }/resource/external/jquery.PrintArea.js"></script>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		<a id="generatedQrcodeByMobile" href="javascript:void(0)"><span>生成二维码</span></a>
	</div>
	<div style="width: 100%;">
		<table id="mobileUpdateList"></table>
		<div id="mobileUpdateListPager"></div>
	</div>
</div>
<div id="mobileUpdateDlg"></div>
<div id="qrcodeDialog"></div>
<script type="text/javascript">
<pop:formatterProperty name="category" domainName="@com.tianque.domain.property.PropertyTypes@TERMINAL_CATEGORY"  />
  $(document).ready(function(){
	  $("#mobileUpdateList").jqGridFunction({
		  datatype:'local',
		  colModel:[
		    {name:"id", index:"id",hidden:true  },
		    {name:"",index:"",label:"操作",width:100,align:'center',sort:false,formatter:operateFormatter},
// 		    {name:"category",index:"category",label:"终端类别",width:100,align:'center',formatter:categoryFormatter},
		    {name:"organization.orgName",index:"organization.orgName",label:"更新部门",width:100,align:'center'},
		    {name:"version",index:"version",label:"版本号",width:100,align:'center'},
		    {name:"level",index:"level",label:"更新等级",width:100,align:'center'},
		    {name:"build",index:"build",label:"编译号",width:100,align:'center'},
		    {name:"url",index:"url",label:"版本路径",width:300,align:'center'},
		    {name:"information",index:"information",label:"更新说明",width:100,align:'center'},
		    {name:"userName",index:"userName",label:"最后更新人员的帐号",width:150,align:'center'}
		    ],
			width:805,
			showColModelButton:false,
			ondblClickRow: showMobileUpdateDetails,
			multiselect:true
	  });
	  
	  showMobileUpdateInfos();
	  
	  $("#add").click(function(){
		  $("#mobileUpdateDlg").createDialog({
			  title:'新增移动终端版本信息',
			  url:'${path}/sysadmin/mobileUpdate/dispatch.action?mode=add',
			  width:600,
			  height:450,
			  buttons:{
				  "保存":function(){
					  beforeSubmit($("#orgIdValue").val());
				  },
				  "关闭":function(){
					  $(this).dialog("close")
				  }
			  }
		  });
	  });
	  
	  
	  $("#generatedQrcodeByMobile").click(function(){
			var selectedRowIds=getSelectedIds();
			if(selectedRowIds.length ==0 ){
				$.messageBox({level:"warn",message:"请选择一条或者多条数据再进行操作！"});
		 		return;
			}
			$("#qrcodeDialog").createDialog({
				width: 700,
				height: 600,
				title:"二维码列表",
				url:"${path}/twoDimensionCodeManage/generateQrcode.action?qrcodeType=mobileQrcodeType&ids="+selectedRowIds,
				buttons:{
					"打印":function(){
						printQrcode();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
				
			});
			
		});
	  
	 
	  
  });
  
  function beforeSubmit(orgId){
		$.ajax({
			url:"${path}/sysadmin/mobileUpdate/findOrganizationById.action?orgId="+orgId,
			success:function(data){
				if(data=='true' || data==true){
					$("#mobileUpdateForm").submit();
				}else{
					$.messageBox({
						level:"error",
						message:data
					});
				}
				}
			});
	  
  }
  
  
  function showMobileUpdateDetails(rowId,iRow,iCol,e){
	  $("#mobileUpdateDlg").createDialog({
		  title:'查看移动终端版本信息',
		  url:'${path}/sysadmin/mobileUpdate/dispatch.action?mode=view&id='+rowId,
		  width:600,
		  height:450,
		  buttons:{
			  "关闭":function(){
				  $(this).dialog("close");
			  }
		  }
	  });
  }
  function showMobileUpdateInfos(){
	  var initParam = {
			}
	  $("#mobileUpdateList").setGridParam({
			url:'${path}/sysadmin/mobileUpdate/findMobileUpdateBySearchVo.action',
			datatype: "json",
			page:1
		});
	  
	 $("#mobileUpdateList").setPostData(initParam);
	 $("#mobileUpdateList").trigger("reloadGrid");
  }
  function operateFormatter(cellvalue, options, rowObject){
	  return "<a href='javascript:void(0)' onclick='updateInfo("+options.rowId+")'>修改</a> | <a href='javascript:void(0)' onclick='deleteMobileInfo("+options.rowId+")'>删除</a>"
  }
  function updateInfo(rowId){
	  $("#mobileUpdateDlg").createDialog({
		  title:'修改移动终端版本信息',
		  url:'${path}/sysadmin/mobileUpdate/dispatch.action?mode=edit&id='+rowId,
		  width:600,
		  height:450,
		  buttons:{
			  "保存":function(){
				  beforeSubmit($("#orgIdValue").val());
			  },
			  "关闭":function(){
				  $(this).dialog("close")
			  }
		  }
	  });
  }
  function deleteMobileInfo(rowId){
	if(rowId==null || rowId==''){
		$.messageBox({
			message:'请选择一条记录在删除'
		});
		return;
	}
	$.confirm({
		title:"确认删除",
		message:'确定要删除吗?一经删除将无法恢复',
		okFunc: function() {
			$.ajax({
			url:"${path}/sysadmin/mobileUpdate/deleteMobileUpdate.action?id="+rowId,
			success:function(data){
				$.messageBox({message:"已经成功删除移动终端版本信息!"});
				showMobileUpdateInfos();
				}
			});
		}
	});
  }
  
	function getSelectedIds(){
		var selectedIds = $("#mobileUpdateList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	function printQrcode(){
		$.dialogLoading("open");
		$("#qrcodeDialog").printArea();
		$("#qrcodeDialog").dialog("close");
		$.dialogLoading("close");
	}
</script>