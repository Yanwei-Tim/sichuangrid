<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<@s.include value="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入地点" id="searchByCondition" class="searchtxt" onblur="value=(this.value=='')?'请输入地点':this.value;" onfocus="value=(this.value=='请输入地点')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		
		<@pop.JugePermissionTag ename="searchWeChatHiddenDanger">
		<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
		</@pop.JugePermissionTag>
		<!--
		<@pop.JugePermissionTag ename="replyWeChatHiddenDanger">
		 <a href="javascript:;" class="nav-dropdownBtn"><span>回复</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
			 <div class="nav-sub-buttons">
				<a id="autoText" href="javascript:void(0)"><span>文本</span></a>
				<a id="autoImage" href="javascript:void(0)"><span>图片</span></a>
				<a id="autoVoice" href="javascript:void(0)"><span>语音</span></a>
				<a id="autoNews" href="javascript:void(0)"><span>图文</span></a>
			</div>--!>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="deleteWeChatHiddenDanger">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</@pop.JugePermissionTag>		
		<a id="refresh"  href="javascript:void(0)"><span>刷新</span></a>
		<@pop.JugePermissionTag ename="assignWeChatHiddenDanger">
			<a id="assign" href="javascript:void(0)"><span>指派</span></a>
		</@pop.JugePermissionTag>
		
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="weChatHiddenDangerList"></table>
		<div id="weChatHiddenDangerListPager"></div>
	</div>
	<div id="weChatHiddenDangerDialog"></div>
	<div id="weChatHiddenDangerSearchDialog"></div>
	<div id="replyMessageDlg"></div>
</div>

<script type="text/javascript">
$(document).ready(function(){
    findList();
    //刷新
	$("#refresh").click(function(){
         findList({
         "weChatHiddenDanger.orgId" :getCurrentOrgId()
         });
    });
    //删除
    $("#delete").click(function(event){
		var selectedId = $("#weChatHiddenDangerList").jqGrid("getGridParam", "selarrrow");
		if(selectedId.length==0){
		   $.messageBox({level:"warn",message:"请至少选择一条记录进行删除！"});
		   return;
		}
		$.confirm({
		   title:"确认删除该条记录",
		   message:"该删除后，将不可恢复，您确定要删除该吗？",
		   width:400,
		   okFunc: function(){
		     $.ajax({
		        url: "${path}/weChatHiddenDangerManage/deleteWeChatHiddenDangerByIds.action?ids="+selectedId,
		        success: function(data){
		           		if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});				
							return ;
						}
						 $("#weChatHiddenDangerList").trigger("reloadGrid");
						 $.messageBox({message:"用户删除成功！"}); 
				}	        
		     });
		   }
		});
	});
	
	  $("#weChatHiddenDangerList").jqGridFunction({
 		    url:'${path}/weChatHiddenDangerManage/findWeChatHiddenDangers.action',
			datatype:"json",
			multiselect:true,
			colModel:[
			{name:'id',index:'id',hidden:true,frozen:true,sortable:false,key:true},
      		{name:'address',index:'address',sortable:true,label:'地点',width:120},
	        {name:'exceptionType',index:'exceptionType',sortable:true,label:'异常类型',width:140},
	        {name:'exceptionSituation',index:'exceptionSituation',sortable:true,label:'异常情况',width:200},
	        {name:'remark',index:'remark',sortable:true,label:'备注',width:200},
	        {name:'assign',index:'assign',sortable:true,label:'是否指派',formatter:assignMesgFormatter,width:100},
	        {name:"createDate",index:'createDate',sortable:true,label:'接收时间',width:180}
			]
	  });
});

     $("#refreshSearchKey").click(function(){
	       $("#searchByCondition").attr("value","请输入地点");
               findList({
		          "weChatHiddenDanger.address":$("#searchByCondition").val()
	       });
      });

       //搜索
     $("#fastSearchButton").click(function(){
	  var createUser=$("#searchByCondition").val();
	   if(createUser!=null&&"请输入地点"!=createUser){
		  var queryObj = {
		    "weChatHiddenDanger.address":$("#searchByCondition").val()
		 };
		findList(queryObj);
	    }
     });

    //高级搜索
    $("#search").click(function(event){
	    $("#weChatHiddenDangerSearchDialog").createDialog({
			width:550,
			height:350,
			title:"消息管理查询-请输入查询条件",
			url:'${path}/weChatHiddenDangerManage/dispatch.action?mode=search',
			buttons:{
				"查询" : function(event) {
					searchWeChatHiddenDanger();
					$(this).dialog("close");
				},
				"关闭" : function() {
					$(this).dialog("close");
				}
			}
	    });
    });

    function assignMesgFormatter(el,options,rowData){
	  if(rowData.assign=='1'){
	     return "是";
	  }else{
	     return "否";
       }
    }
    function searchWeChatHiddenDanger(){
	  var queryObj = {
		 "weChatHiddenDanger.exceptionSituation" : $("#exceptionSituation").val(),
		 "weChatHiddenDanger.propertyDictId" : $("#propertyDictId").val(),
		 "weChatHiddenDanger.address" : $("#address").val(),
		 "weChatHiddenDanger.startCreateTime" : $("#startCreateTime").val(),
		 "weChatHiddenDanger.endCreateTime" : $("#endCreateTime").val()
		};
	    findList(queryObj);
    }
 
    //列表显示
	function findList(obj){
		$("#weChatHiddenDangerList").setGridParam({
			url:'${path}/weChatHiddenDangerManage/findWeChatHiddenDangers.action',
			datatype: "json",
			page:1,
			mytype:"post"
		});
		if(obj){$("#weChatHiddenDangerList").setPostData(obj)};
		$("#weChatHiddenDangerList").trigger("reloadGrid");
	}
	
	 //指派 EventsToAccept
    $("#assign").click(function(event,rowData) {
		var selectedIds = $("#weChatHiddenDangerList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length ==0){
			$.messageBox({level : 'warn',message:"请选择一条或多条记录，再进行指派！"});
			return;
		}
		var weChatHiddenDanger;
		var assign;
		var dealStateName;
		for(var i=0,len=selectedIds.length; i<len; i++){
			weChatHiddenDanger =  $("#weChatHiddenDangerList").getRowData(selectedIds[i]);
			if(i==0){
			assign=weChatHiddenDanger.assign;
			}
			if(assign=="是"){
				$.messageBox({level : 'warn',message:"选中有已指派数据，请再重新选择！"});
				return;
			}
		}
		
		$("#weChatHiddenDangerDialog").createDialog({
			width:320,
			height:300,
			title: '指派到任务清单',
			url:'${path}/weChatHiddenDangerManage/dispatch.action?weChatHiddenDanger.ids='+selectedIds+'&weChatHiddenDanger.orgId='+getCurrentOrgId()+'&mode=isassign',
			buttons: {
		   		"保存" : function(event){
					$("#maintainShiftForm").submit();
					 findList({
                        "weChatHiddenDanger.orgId" :getCurrentOrgId()
                     });
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});

     });
</script>