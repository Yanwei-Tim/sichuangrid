<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div>
	<div id="nav">
		<button id="chooseEvent" class="choose">标记为</button>
		<ul id="chooseMenu" class="chooseMenu">
			<li id="normalIssue" val="normalIssue">普通督办</li>
			<li id="yellowCardIssue" val="yellowCardIssue">黄牌督办</li>
			<li id="redCardIssue" val="redCardIssue">红牌督办</li>
			<li id="cancleSupervise" val="cancleSupervise">取消督办</li>
			<li id="urgent" val="urgent">加急</li>
			<li id="cancelUrgent" val="cancelUrgent">取消加急</li>
			<li id="command" val="command">领导批示</li>
		</ul>
			<a id="refresh" href="javascript:void(0)" style="display: none"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="jurisdictionsList"></table>
	</div>
</div>
<div id="issueDialogforbench"></div>
<script type="text/javascript">
var currentOrgId= <%= ThreadVariable.getUser().getOrganization().getId()%>
$(function(){
	$("#jurisdictionsList").workBenchGridFunction({
		datatype: "local",
		sortname:'cstep.lastdealdate', 
	   	colModel:[
		{name:'superviseLevel',index:'isteps.superviselevel',label:'<img src="${resource_path}/resource/system/images/grayPieces.gif" style="vertical-align:middle" />',formatter:rendSupervise,width:40},
		{name:'urgent',index:'iu.urgent',label:'<img src="${resource_path}/resource/system/images/bang.gif" style="vertical-align:middle" />',formatter:rendUrgent,width:40},	   	
		{name:'issueStepId',hidden:true,key:true},
		{name:'superviseLevel',hidden:true},
		{name:'subject',index:'iu.subject',label:'事件名称'},
		{name:'occurOrg.orgName',index:'iu.occurOrg',label:'所属区域'},
		{name:'dealState',index:'isteps.statecode',label:'状态',hidden:true,formatter:dealStateFormatter,align:'center'},
		{name:'dealTime',index:'cstep.lastdealdate',label:'录入时间'},
		{name:'urgent',index:'iu.urgent',label:'加急',hidden:true},
		{name:'issueLogId',hidden:true}
		],
		ondblClickRow: doubleClickTable,
		onSelectRow:selectRow
	});
	function doubleClickTable(){
	    var selectedId = $("#jurisdictionsList").getGridParam("selrow");
		$("#issueDialogforbench").createDialog({
			width:750,
			height:500,
			title:"查看日志信息",
			url:"${path}/issues/issueManage/viewSubDetail.action?mode=view&keyId="+selectedId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	$("#refresh").click(function(event){
		onOrgChanged();
	});

	if(currentOrgId!= null){
		onOrgChanged();
	}
	$("#chooseEvent").button({
	        icons: {
	        primary: "ui-icon-gear",
	        secondary: "ui-icon-triangle-1-s"
	    }
	}).click(function(e){
        var menu = $(this).next().show().position({
            my: "left top",
            at: "left bottom",
            of: this
        });
    })
    
    function superviseIssueForbench(keyId,typeCode){
		if (!isNullObject(keyId)){
			$("#issueDialogforbench").createDialog({
		        width:550,
		        height:350,
		        title:'督办',
				url:'/issues/issueManage/dispatchDealForBench.action?dealCode='+typeCode+'&keyId='+keyId,
		        buttons: {
		            "确定" : function(event){
		                $("#superviseIssueForm").submit();
		            },
		            "关闭" : function(){
		                $(this).dialog("close");
		            }
		        }
		    });
		}
	}
	function cancelSuperviseOrUrgent(keyId,typeCode){
		if (!isNullObject(keyId)){
			$.ajax({
				url:'/issues/issueManage/dealIssue.action',
		        data:{
		            "dealCode":typeCode,
		            "keyId":keyId
		            "struts.token":'<pop:token ajax="true" />'
		        },
		        success:function(data){
		            if(data.issueStepId>0){
		            	$("#jurisdictionsList").setRowData(data.issueStepId,data);
	    				$("#jurisdictionsList").setSelection(data.issueStepId);
	    				if( typeCode== <s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_SUPERVISE.code"/>){
		            		$.messageBox({message:"已成功取消该事件的督办!"});
			            }else{
			            	$.messageBox({message:"已成功取消该事件的加急!"});
				           }
		            }else{
		                $.messageBox({message:data});
		            }
		        }
		    });
		}
	}
    $("#chooseMenu li").click(function(){
        var thisValue=$(this).attr("val");
    	var selectedId = $("#jurisdictionsList").getGridParam("selrow");
		var ent =  $("#jurisdictionsList").getRowData(selectedId);
		if(selectedId==null){
			$.messageBox({level:'error',message:"您尚没有选中一条待办信息，请选择一条待办信息！"});
	 		return;
		}
		switch(thisValue){
			case 'normalIssue':
				if(ent.superviseLevel==1){
					$.messageBox({level:'error',message:"该待办信息已经普通督办了！"});
				}
				superviseIssueForbench(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@NORMAL_SUPERVISE.code"/>);
				onOrgChanged();
			    break;
			case 'yellowCardIssue':
				if(ent.superviseLevel==100){
					$.messageBox({level:'error',message:"该待办信息已经黄牌督办了！"});
			 		return;
				}
				superviseIssueForbench(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@YELLOW_SUPERVISE.code"/>);
			    break;
			case 'redCardIssue':
				if(ent.superviseLevel==200){
					$.messageBox({level:'error',message:"该待办信息已经红牌督办了！"});
			 		return;
				}
				superviseIssueForbench(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@RED_SUPERVISE.code"/>);
				break;
			case 'cancleSupervise':
				if(ent.superviseLevel== -1){
					$.messageBox({level:'error',message:"该待办信息已经取消督办了！"});
			 		return;
				}
				$.confirm({
					title:"系统提示",
					message:"是否确定要取消对该待办信息的督办!",
					width:400,
					okFunc:function(){
					cancelSuperviseOrUrgent(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_SUPERVISE.code"/>);
				}
				});
			    break;
			case 'urgent':
				if(ent.urgent==1){
					$.messageBox({level:'error',message:"该待办信息已经加急了！"});
			 		return;
				}
				$("#issueDialogforbench").createDialog({
					width:690,
					height:430,
					title:'加急',
					url:'${path}/issues/issueManage/dispatchDealForBench.action?dealCode=<s:property value="@com.tianque.issue.state.IssueOperate@URGENT.code"/>&keyId='+selectedId,
					buttons: {
						"确定" : function(event){
							$("#singleContentDealForm").submit();
						},
						"关闭" : function(){
							$(this).dialog("close");
						}
					}
				});
			    break;
			case 'command':
				$("#issueDialogforbench").createDialog({
					width: 600,
					height: 400,
					title: '领导批示',
					url:'${path}/issues/issueManage/dispatchDealForBench.action?dealCode=<s:property value="@com.tianque.issue.state.IssueOperate@INSTRUCT.code"/>&keyId='+selectedId,
					buttons: {
				   		"确定" : function(event){
			   				$("#singleContentDealForm").submit();
			   			},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			    break;
			case 'cancelUrgent':
				if(ent.urgent==0){
					$.messageBox({level:'error',message:"该待办信息已经取消加急了！"});
			 		return;
				}
				$.confirm({
					title:"系统提示",
					message:"是否确定要取消对该待办信息的加急!",
					width:400,
					okFunc:function(){
					cancelSuperviseOrUrgent(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_URGENT.code"/>);
				}
				});
			    break;
		}
		$("#chooseMenu").hide();
    })
});

function selectRow(){
	enableButtons();
	disableButtons();
}

function enableButtons(){
	$("#normalIssue").show();
	$("#yellowCardIssue").show();
	$("#redCardIssue").show();
	$("#cancleSupervise").show();
	$("#urgent").show();
	$("#cancelUrgent").show();
}
function disableButtons(){
	var selectedId = $("#jurisdictionsList").getGridParam("selrow");
	var ent =  $("#jurisdictionsList").getRowData(selectedId);
	if(ent.superviseLevel==1){
		$("#normalIssue").hide();
		}
	if(ent.superviseLevel==100){
		$("#yellowCardIssue").hide();
		$("#normalIssue").hide();
	}
	if(ent.superviseLevel==200){
		$("#redCardIssue").hide();
		$("#yellowCardIssue").hide();
		$("#normalIssue").hide();
	}
	if(ent.superviseLevel== -1){
		$("#cancleSupervise").hide();
	}
	if(ent.urgent==1){
		$("#urgent").hide();
	}
	if(ent.urgent==0){
		$("#cancelUrgent").hide();
	}
}

function onOrgChanged(){
	
	$("#jurisdictionsList").setGridParam({
		url:'${path}/issues/issueManage/findJurisdictionsNeedDo.action',
		datatype:'json'
	});
	$("#jurisdictionsList").setPostData({
		"keyId":currentOrgId,
		"page":1
	});
	$("#jurisdictionsList").trigger("reloadGrid");
}

function dealStateFormatter(el, options, rowData){
	if(rowData.dealState != null){
		if(110 == rowData.dealState){
			return "待受理";
		}else if(140 == rowData.dealState){
			return "待阅读";
		}else if(500 == rowData.dealState){
			return "已办";
		}else if(1000 == rowData.dealState){
			return "已完成";
		}else {
			return "办理中";
		}
	}
}
</script>
