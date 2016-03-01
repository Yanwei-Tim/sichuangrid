<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
String currentUser=null;
if(ThreadVariable.getUser()!=null){
	 currentUser = ThreadVariable.getUser().getUserName();
}
%>

<%-- <script type="text/javascript" src="/resource/external/jPlayer/js/jquery.jplayer.min.js"></script> --%>
<style>
.jp-jplayer{width:0 !important;height:0 !important;}
.jp-audio{background:url(/resource/system/images/jplayer/bg.png) no-repeat;overflow:hidden;width:90px;height:29px;line-height:27px;padding-left:15px;}
.jp-audio a{float: left;top: 6px;position: relative;}
.jp-pause{display:none;}
.jp-time{float:right;color:#333;}
.jp-current-time{display:none;}

#issuePreservation:HOVER {
	background-color: #02C874;
}
#inboxRollOut:HOVER {
	background-color: #02C874;
}
#replyMessage:HOVER {
	background-color: #02C874;
}

 .jqgrowNoneImage{
	background-color: yellow !important;
	background-image: none !important;
}

.someClass { background-color: #DDDDDC !important; background-image: none !important; }
</style>

<!-- 选项卡 -->
<div  id="tabList" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
	<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">		
		<li  id="untreatedCustomerListLi" class="ui-state-default ui-corner-top ui-tabs-active ui-state-active" >
		    <a  class="ui-tabs-anchor" href="javascript:void(0);" id="untreatedCustomerList">未接入客户列表</a>
		</li>
		<li id="chatWindowListLi" class="ui-state-default ui-corner-top">
		    <a class="ui-tabs-anchor" href="javascript:void(0);" id="chatWindowList">聊天窗口</a>
		</li>
		<li id="acceptedInboxsLi" class="ui-state-default ui-corner-top">
		    <a class="ui-tabs-anchor" href="javascript:void(0);" id="acceptedInboxs">已受理</a>
		</li>
	</ul>
</div>

<div id="acceptedInboxsDiv" style="display: none;"></div>
<!-- 未接入客户列表 -->
<div id="untreatedCustomer" >
    
	<div class="ui-corner-all contentNav" id="nav">
	    
	    <pop:JugePermissionTag ename="accessCustomerInboxs">
			<a href="javascript:;" id="accessCustomer"><span>接入</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteInbox">
			<a id="deleteUntreatedInbox" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<a id="reloadUntreatedInbox" href="javascript:void(0)"><span>刷新</span></a>
	</div> 
	<div style="width: 100%;">
	   
		<table id="untreatedInboxList"> </table>
		<div id="untreatedInboxListPager"></div>
	 <div class='clearLine'></div>    
	</div>
</div>


<!-- 聊天窗口 -->
    <!-- 客户列表 -->
<div id="chatWindow" style="display: none;">
      <div id="jquery_jplayer_2" class="jp-jplayer"></div>
     <div id="accessCustomerList" class="grid_6" style="height:85%;overflow:auto;width:15%;line-height:30px;background: #f4f5f5;">    
    </div>
    <!-- 聊天对话框 -->
    <div id="chatDialog"  class="grid_6" style="height:85%;overflow:auto;width:40%;line-height:30px">
       <div id="chatCountDiv" class="grid_6" style="height:65%;overflow:auto;width:100%;line-height:30px;magin-left:30px">
          <ul id="chatDialogTable">
          </ul>
       </div>
       <div class='clearLine'>&nbsp;</div>
       <div class="grid_6" style="height:1%;width:100%;magin-left:200px;background-color: #0066FF"></div>
        <div class='clearLine'>&nbsp;</div>


       <div class='clearLine'>&nbsp;</div> 
  	    <div class="grid_6 lable-right" style="width:15%">
	  		<label class="form-lbl">回复内容：</label> 
	  	</div>
	    <div class="grid_7" style="width:70%;">
			<textarea  maxlength="64"  name="textMessage.content" id="textMessageContent" style="height:70px;" class='form-txt {maxlength:64,messages:{maxlength:$.format("回复内容最多可以输入{0}个字符")}}'></textarea>
	   </div>
	   <div id="myButton" style="width:10%;" class="grid_6 lable-right">
            <button id="replyMessage" type="button"  >回复</button>
        </div>
    </div>
    <!-- 转事件窗口 -->
    <div id="turnIssue" class="grid_6 container container_24" style="height:85%;overflow:auto;width:45%;background: #f4f5f5;">
      <div id="issueModular">
         
      </div>
<!--        <div >
        <button id="issuePreservation" type="button"  style="margin-top:100px;margin-left:50px">受理</button>
        <button id="inboxRollOut" type="button"  style="margin-top:100px;margin-left:20px">转出</button>
      </div> -->
    </div>
</div>     

<div id="inboxDialog"></div>
<div id="untreatedInboxDialog"></div>
<div id="replyMessageDlg"></div>
<input type="hidden" name="inbox.updateUser" id="currentUser" value="<%=currentUser%>" />


<input type="hidden" id="createUser" name="inbox.createUser" value=""><!-- 存的是粉丝名，区别于系统中的ccuu字段  用于存储对应的回复消息记录列表-->
<input type="hidden" id="toUserName" name="inbox.toUserName" value=""><!-- 用于获取微信号 -->
<input type="hidden" id="fromUserName" name="textMessage.fromUserName" value=""><!-- 发送给谁 -->


<script type="text/javascript">
//加载已受理页面只加载一次
var loadingAcceptedInboxs=true;
//加载事件受理页面 只加载一次
var loadingIssueWindow=true
var playMedia=true;
var setTimeoutReloadAccessInbox;
//切换窗口防止多次点击
var switchingWindow=1;//1表示未接入窗口,2表示聊天窗口,3表示已受理窗口

$(document).ready(function(){
	//initAcceptedInboxs();
	// 切换聊天窗口
	$("#chatWindowList").click(function(event) {
	
		if(switchingWindow==2){
			return;
		}
		switchingWindow=2;
		
		$("#untreatedCustomerListLi").removeClass("ui-tabs-active ui-state-active");
		$("#acceptedInboxsLi").removeClass("ui-tabs-active ui-state-active");
		
		$("#chatWindowListLi").addClass("ui-tabs-active ui-state-active");

		$("#accessCustomerList").empty();
		$("#untreatedCustomer").hide();
		$("#acceptedInboxsDiv").hide();
		$("#chatWindow").show();

         if(loadingIssueWindow){
        	 
        		//切换到聊天窗口时创建语音播放窗口,只加载一次,后面会隐藏掉
        		$("#jquery_jplayer_2").jPlayer({
        			ready: function () {
        			},
        			swfPath: "/resource/external/jPlayer/js",
        			solution: "flash, html",
        			supplied: "mp3,m4a,flv,arm",
        			wmode: "window",
        			keyEnabled: true
        		});
        		
        	createIssueWindow();
        	loadingIssueWindow=false;
        }else if(document.getElementById("issueMaintainForm")!=undefined){
        	//$("#issueMaintainForm").reset();
        	document.getElementById("issueMaintainForm").reset();
        } 
		findAccessInboxByUpdateUser();
	});
	//切换未接入消息列表
	$("#untreatedCustomerList").click(function() {
		if(switchingWindow==1){
			return;
		}
		switchingWindow=1;
		$(".peopleSelectDlg").remove();
			
		$("#chatWindowListLi").removeClass("ui-tabs-active ui-state-active");
		$("#acceptedInboxsLi").removeClass("ui-tabs-active ui-state-active");
		
		$("#untreatedCustomerListLi").addClass("ui-tabs-active ui-state-active");
	
		//切换窗口关闭声音
		if(!playMedia){
			playMedia=true;
			$("#jquery_jplayer_2").jPlayer("stop");
		}		
		$("#acceptedInboxsDiv").hide();
		$("#chatWindow").hide();
		$("#untreatedCustomer").show();
		
		untreatedInboxList({
			 "inbox.org.id" :getCurrentOrgId(),
			 "inbox.dealState" :0,//dealState=0表示消息处于未处理状态
			 "inbox.isKeyWordMsg" :2//isKeyWordMsg=2表示非关键字消息
		});
	});
	

	$("#untreatedInboxList").jqGridFunction({
			datatype: "local",
			sortname:"create_time",
			multiselect:true,
			sortorder:"asc",
		   	colModel:[  	          
		   	    {name:'fromUserName',index:'fromUserName',label:'粉丝唯一标识',hidden:true,sortable:true,width:100,align:'center'},
		   	    {name:'toUserName',index:'toUserName',label:'微信公众号',sortable:false,width:100,align:'center'},
		   	    {name:'isRead',index:'isRead',label:'阅读状态',hidden:true,sortable:false,width:100,align:'center'},
		   	    {name:'createUser',index:'createUser',label:'粉丝昵称',sortable:false,width:100,align:'center'},
		   	    {name:'untreatedInboxNumber',index:'untreatedInboxNumber',formatter:seachUntreatedInboxByOpenId,label:'消息数',sortable:true,width:100,align:'center'},
		   	    {name:'createTime',index:'create_time',label:'第一条消息接收时间',sortable:true,width:100,align:'center'} 	  
			],
			shrinkToFit:true
	});
	untreatedInboxList({
		 "inbox.org.id" :getCurrentOrgId(),
		 "inbox.dealState" :0,//dealState=0表示消息处于未处理状态
		 "inbox.isKeyWordMsg" :2//isKeyWordMsg=2表示非关键字消息
	});
	
	
	// 刷新
	$("#reloadUntreatedInbox").click(function() {
		untreatedInboxList({
			"inbox.org.id" :getCurrentOrgId(),
			"inbox.dealState" :0,//dealState=0表示消息处于未处理状态
			"inbox.isKeyWordMsg" :2//isKeyWordMsg=2表示非关键字消息
		});
	});
	
    //切换已受理页面
	$("#acceptedInboxs").click(function() {
		
		if(switchingWindow==3){
			return;
		}
		switchingWindow=3;
		$(".peopleSelectDlg").remove();
		
		$("#untreatedCustomerListLi").removeClass("ui-tabs-active ui-state-active");
		$("#chatWindowListLi").removeClass("ui-tabs-active ui-state-active");
		
		$("#acceptedInboxsLi").addClass("ui-tabs-active ui-state-active");
		
		
		//切换窗口关闭声音
		if(!playMedia){
			playMedia=true;
			$("#jquery_jplayer_2").jPlayer("stop");
		}	
		if(loadingAcceptedInboxs){
			$("#untreatedCustomer").hide();
			$("#chatWindow").hide();
			$("#acceptedInboxsDiv").show();
			var url = "${path}/hotModuel/inbox/inboxList.ftl";

			 $.get(url,{},function(data){
				
				 $("#acceptedInboxsDiv").html(data);
			 })
			 loadingAcceptedInboxs=false;
		}else{
			$("#acceptedInboxsDiv").show();
			$("#untreatedCustomer").hide();
			$("#chatWindow").hide();
		}
		
	});
	//转出
// 	$("#inboxRollOut").click(function(event){
// 		var openId = $("#fromUserName").val();
// 		if(openId==null||openId.length==0){
// 			$.messageBox({level : 'warn',message:"转出失败，当前已接入用户为空!"});
// 			return;
// 		}
		
// 		//dealState:0表示未做处理,dealState:3表示接入
// 	    var dealState=0;
// 		var oldDealState=3;
// 		var isKeyWordMsg=2;//isKeyWordMsg=2表示非关键字消息
// 		var dataList = 'openIds='+openId+
// 		'&inbox.dealState='+dealState+
// 		'&inbox.isKeyWordMsg='+isKeyWordMsg+
// 		'&oldDealState='+oldDealState;
		
// 	 	$.ajax({		
// 			type:'post',
// 			url:"${path}/weChat/inbox/updateDealState.action",
// 			async:false,
// 			data:dataList,
// 			dataType:'json',
// 			success : function(data) {
// 				if(data==true||data=="true"){
// 					$.messageBox({message:"转出成功!"});				
// 	        		$("#accessCustomerList").empty();
// 	        		$("#chatDialogTable").empty();
// 	        		findAccessInboxByUpdateUser();
// 	        		document.getElementById("issueMaintainForm").reset();
// 				}else {				
// 				    $.messageBox({message : "转出失败!"});
// 				}
// 			}
// 		});
// 	});

// 	$("#issuePreservation").click(function(event) {
// 		var openId = $("#fromUserName").val();
// 		if(openId==null||openId.length==0){
// 			$.messageBox({level : 'warn',message:"受理失败，当前已接入用户为空!"});
// 			return;
// 		}
// 		$("#touser").val(openId);
// 		$("#issueMaintainForm").submit();
// 	});

	$("#replyMessage").click(function(event){
		
		var fromUserName = $("#fromUserName").val();
		var toUserName = $("#toUserName").val();
		var createUser = $("#createUser").val();
		var content = $("#textMessageContent").val();
	    var isIssue=0;//与回复消息相关发送者发送的消息是否转为事件(0为否,1为是)
	    if(fromUserName.length==0){
	    	$.messageBox({level : 'warn',message:"回复失败，当前已接入用户为空!"});
			return;
	    }else if(toUserName.length==0){
	    	$.messageBox({level : 'warn',message:"回复失败，当前已接入用户为空!"});
			return;
	    }else if(createUser.length==0){
	    	$.messageBox({level : 'warn',message:"回复失败，当前已接入用户为空!"});
			return;
	    }else if(content.length==0){
	    	$.messageBox({level : 'warn',message:"回复失败，回复内容为空！"});
			return;
	    }

	    
	    var dataList="inbox.fromUserName="+fromUserName+
	    "&inbox.toUserName="+toUserName+
	    "&inbox.createUser="+createUser+
	    "&textMessage.content="+content+
	    "&textMessage.ToUserName="+fromUserName+//textMessage.ToUserName回复给谁(用户openId,微信回复接口(社管回复给用户)和发送接口(用户发消息到社管),这两个字段相反)
	    "&textMessage.isIssue="+isIssue;
	    
	    //验证粉丝发的最后一条消息到现在为止是否超过48小时(微信规定超过48小时不能回复)
		  $.ajax({
				url:"${path}/weChat/inbox/checkDate.action?inbox.fromUserName="+fromUserName+"&inbox.dealState=3&inbox.isKeyWordMsg=2",
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return false ;
					}else{
						//回复粉丝消息
					 	$.ajax({
							type:'post',
							url:"${path}/weChat/inbox/replyTextMessage.action",
							async:false,
							data:dataList,
							dataType:'json',
							success : function(data) {
								if(data==null||data=="null"){
									$.messageBox({message:"回复成功!"});				
									$("#textMessageContent").val('');
									clearTimeout(setTimeoutReloadAccessInbox);
									reloadAccessInboxByOpenId();
								}else {				
								    $.messageBox({message : "回复失败,"+data});
								}
							}
						});
					}
				}
		    });			
	});

	//根据用户OpenId接入
	$("#accessCustomer").click(function(event) {
			var allValue = getOpenIds();
				if(allValue.length ==0){
					$.messageBox({level : 'warn',message:"请选择一条或多条记录，再进行接入！"});
					return;
				}
	/* 		$.confirm({
				message:"是否真的接入选中用户？", 
				okFunc: function(){*/
					accessCustomerByOpenIds(event,allValue);
	/* 			}
			}); */
	});


	//删除
	$("#deleteUntreatedInbox").click(function(event) {
			var allValue = getOpenIds();
				if(allValue.length ==0){
					$.messageBox({level : 'warn',message:"请选择一条或多条记录，再进行删除！"});
					return;
				}
			$.confirm({
				message:"是否真的删除选中信息？",
				okFunc: function(){
					deleteInboxByOpenIdAndDealState(event,allValue);
				}
			});
	});
	
	setTimeout("reloadAccessInboxByOpenId()",30000);
});


$("#chatDialogTable").delegate(".jp-play", "click", function(){
	playMedia=false;
	var media=$(this).data("media");
	var type=$(this).data("type");
	$("#jquery_jplayer_2").jPlayer( "clearMedia" );
	var box=$(this).closest(".jp-audio");
	$(this).hide().next().show();
	$("#jquery_jplayer_2").jPlayer("setMedia", {
		"mp3": media
	}).jPlayer("play");
});
$("#chatDialogTable").delegate(".jp-pause", "click", function(){
	playMedia=true;
	$(this).hide().prev().show();
	$("#jquery_jplayer_2").jPlayer("stop");
});


function initAcceptedInboxs(){
	if(loadingAcceptedInboxs){
		var url = "${path}/hotModuel/inbox/inboxList.ftl";

		 $.get(url,{},function(data){
			
			 $("#acceptedInboxsDiv").html(data);
			 $("#acceptedInboxsDiv").hide();
		 })
		 loadingAcceptedInboxs=false;
	}
}

//接入主程序
function accessCustomerByOpenIds(event,allValue){
	
	//dealState:0表示未做处理,dealState:3表示接入
    var dealState=3;
    var oldDealState=0;
	var isKeyWordMsg=2;//排除关键字消息
	var dataList = 'openIds='+allValue+
	'&inbox.dealState='+dealState+
	'&inbox.isKeyWordMsg='+isKeyWordMsg+
	'&oldDealState='+oldDealState;

 	$.ajax({
		url: PATH+ '/weChat/inbox/updateDealState.action',
		data:dataList,
		success : function(res) {
			if (res==true||res=="true") {
				
				$.messageBox({message : "接入成功!"});
				untreatedInboxList({
					 "inbox.org.id" :getCurrentOrgId(),
					 "inbox.dealState" :0,//dealState=0表示消息处于未处理状态
					 "inbox.isKeyWordMsg" :2//isKeyWordMsg=2表示非关键字消息
				});
				return;
			}
			$.messageBox({message : "接入失败!"});
		}
	}); 
}
/* var all={};
all.openid1={};
all.openid2={};
all.openid1.duihua = {};
all.openid1.shijian = {};
all['opendid1']
delete all.openid1; */
//查询当前用户已接入用户
function findAccessInboxByUpdateUser(){
	
	var currentUser=$("#currentUser").val();
	var dealState=3;//dealState=3 表示消息处于接入状态
	var isKeyWordMsg=2;//isKeyWordMsg=2表示非关键字消息
	var orgId=getCurrentOrgId();
	if(orgId==null){
		alert("orgId为空")
		return;
	}else if(currentUser==null){
		return;
	}
	var dataList = "inbox.org.id="+orgId+
	"&inbox.isKeyWordMsg="+isKeyWordMsg+
	"&inbox.updateUser="+currentUser+
	"&inbox.dealState="+dealState;
	
	
	
	$.ajax({
		  type:'post',
		  url:"${path}/weChat/inbox/findUntreatedInboxs.action",
		  async:false,
		  data:dataList,
		  dataType:'json',
		  success : function(json) {
			  var typeData;
			if (json.rows==null||json.rows=="") {
				//当处理完接入用户后，清空input标签的值
				var fromUserName=$("#fromUserName").val();
				var toUserName=$("#toUserName").val();
				var createUser=$("#createUser").val();
				if(fromUserName!=null&&fromUserName.length!=0){
					
					$("#fromUserName").val("");
				}
				if(toUserName!=null&&toUserName.length!=0){
					$("#toUserName").val("");
				}
				if(createUser!=null&&createUser.length!=0){
					$("#createUser").val("");
				}
				
	          return;
			}else{
				typeData=json.rows;
			}
				
			
            $.each(typeData, function(i, n) {
                var tbBody = "";
                var trColor;
               
                if (i % 2 == 0) {
                    trColor = "even";
                }
                else {
                    trColor = "odd";
                }
                

               //temp="+'"'+n.fromUserName+'","'+n.toUserName+'","'+n.createUser+'"'+"
               
                tbBody += "<div style='color:#0066FF;font-size:50px' id='"+n.fromUserName+"' onclick='getAccessInboxByOpenId("+'"'+n.fromUserName+'","'+n.toUserName+'","'+n.createUser+'"'+");'><a style='color:#0066FF;font-size:20px'>" + n.createUser + "</a></div>";
                $("#accessCustomerList").append(tbBody);
                
                if(i==0){
             	   getAccessInboxByOpenId(n.fromUserName,n.toUserName,n.createUser);
                }
            });	
		}
	});

}

//刷新聊天列表
function reloadAccessInboxByOpenId(){
	//$(this).css("background","#588600");
	var fromUserName=$("#fromUserName").val();
	var toUserName=$("#toUserName").val();
	var createUser=$("#createUser").val();
	if(fromUserName==null||fromUserName.length==0){
		return;
	}
	if(toUserName==null||toUserName.length==0){
		return;
	}
	if(createUser==null||createUser.length==0){
		return;
	}
	$("#chatDialogTable").empty();
	
	var dealState=3;//dealState=3表示消息处于接入状态
	var isKeyWordMsg=2;//isKeyWordMsg=2表示非关键字消息
	var dataList=
	"inbox.dealState="+dealState+
	"&inbox.isKeyWordMsg="+isKeyWordMsg+
	"&inbox.fromUserName="+fromUserName;
	
	$.ajax({         		  
		  type:'post',
		  url:"${path}/weChat/inbox/findInboxsAndReplyMessages.action",
		  async:false,
		  dataType:'json',
		  data:dataList,
		   success:function(json){    		
           	if(json!=null){
       		        		
                var typeData = json;
                $.each(typeData, function(i, n) {
                    var tbBody = "";
                    var trColor;
                   
                    if (i % 2 == 0) {
                        trColor = "even";
                    }
                    else {
                        trColor = "odd";
                    }
             
                    viewAccessFile(n,tbBody);
                    
                });
                var div = document.getElementById('chatCountDiv');
            	div.scrollTop = div.scrollHeight;
        	}
           	setTimeoutReloadAccessInbox = setTimeout("reloadAccessInboxByOpenId()",30000);
		  }
	  });
	
	
}

function getAccessInboxByOpenId(fromUserName,toUserName,createUser){
	

	if(fromUserName==null){
		fromUserName=$("#fromUserName").val();
		
	}	
	if(toUserName==null){
		toUserName=$("#toUserName").val();
	}
	if(createUser==null){
		createUser=$("#createUser").val();
	}
	
	$("#accessCustomerList").children("div").css("background-color","#f4f5f5");
    
     $("#"+fromUserName+"").css("background-color","#99ff99");  
	//alert($(id)); 

	$("#fromUserName").val(fromUserName);
	$("#toUserName").val(toUserName);
	$("#createUser").val(createUser);
	
	$("#chatDialogTable").empty();
	
	var dealState=3;//dealState=3表示处于已接入状态
	var isKeyWordMsg=2;//isKeyWordMsg=2表示非关键字消息
	var dataList=
	"inbox.dealState="+dealState+
	"&inbox.isKeyWordMsg="+isKeyWordMsg+
	"&inbox.fromUserName="+fromUserName;
	
	$.ajax({         		  
		  type:'post',
		  url:"${path}/weChat/inbox/findInboxsAndReplyMessages.action",
		  async:false,
		  dataType:'json',
		  data:dataList,
		   success:function(json){  
           	if(json!=null){
       		        		
                var typeData = json;
                $.each(typeData, function(i, n) {
                    var tbBody = "";
                    var trColor;
                   
                    if (i % 2 == 0) {
                        trColor = "even";
                    }
                    else {
                        trColor = "odd";
                    }
                    if(i==0){       
                    	setTimeout(function(){
                    		changeParameter(n);
                    	}, 500);
                    }
                    viewAccessFile(n,tbBody);
                    
                });
                var div = document.getElementById('chatCountDiv');
            	div.scrollTop = div.scrollHeight;
            	
               /*   if(loadingIssueWindow){
                	createIssueWindow();
                	loadingIssueWindow=false;
                }else if(document.getElementById("issueMaintainForm")!=undefined){
                	//$("#issueMaintainForm").reset();
                	document.getElementById("issueMaintainForm").reset();
                }  */
        	}
           	
		  }
	  });
	
}

//加载扭转事件窗口
function createIssueWindow(){

	var mode="eventsToAccept";
	var keyId=getCurrentOrgId();
	var url = "${path}/issues/issueManage/dispatch.action";
/* var param = {};
param.asdfasd=24; */

	 $.get(url,{"mode":mode,"keyId":keyId},function(data){
		 //alert(data)
		 $("#issueModular").html(data);
	 })
}

function viewAccessFile(n,tbBody){
   	if(n.inboxAttachments.length>0 && n.inboxAttachments[0].extFileName=='jpg'){
		   tbBody += "<li style='color:3366ff'><span>"+n.createUser +"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>"+n.createTime +"</span></li>"+"<li><a href='javascript:;' onclick='viewFile("+n.inboxAttachments[0].inboxAttachmentId+")' ><img src='"+n.inboxAttachments[0].fileActualUrl+"' style='width:32px;height:32px;'/></a></li>";
           $("#chatDialogTable").append(tbBody);
	 }else if(n.inboxAttachments.length>0){
		   //tbBody += '<li style="color:3366ff"><span>'+n.createUser +'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>'+n.createTime +'</span></li>'+'<li id="jp_container_1" class="jp-audio"  ><a class="jp-play" data-type="'+n.inboxAttachments[0].extFileName+'" data-media="'+n.inboxAttachments[0].fileActualUrl+'"><img src="/resource/system/images/jplayer/play.png" /></a><a href="javascript:;" class="jp-pause" tabindex="1"><img src="/resource/system/images/jplayer/stop.gif" /></a><div class="jp-time"><div class="jp-current-time"></div><div class="jp-duration"></div></div></li>';
		  if(n.content!=null&&n.content!=""){
			  tbBody += '<li style="color:3366ff"><span>'+n.createUser +'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>'+n.createTime +'</span></li>'+'<li id="jp_container_1" class="jp-audio"  ><a class="jp-play" data-type="'+n.inboxAttachments[0].extFileName+'" data-media="'+n.inboxAttachments[0].fileActualUrl+'"><img src="/resource/system/images/jplayer/play.png" /></a><a href="javascript:;" class="jp-pause" tabindex="1"><img src="/resource/system/images/jplayer/stop.gif" /></a></li>';
			  tbBody += '<li >('+n.content +')</li>'
		  }else{
		   tbBody += '<li style="color:3366ff"><span>'+n.createUser +'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>'+n.createTime +'</span></li>'+'<li id="jp_container_1" class="jp-audio"  ><a class="jp-play" data-type="'+n.inboxAttachments[0].extFileName+'" data-media="'+n.inboxAttachments[0].fileActualUrl+'"><img src="/resource/system/images/jplayer/play.png" /></a><a href="javascript:;" class="jp-pause" tabindex="1"><img src="/resource/system/images/jplayer/stop.gif" /></a></li>';
		  }
	       $("#chatDialogTable").append(tbBody);
	 }else if(n.msgType!=null && n.msgType=='location'){
		   tbBody +="<li style='color:3366ff'><span>"+n.createUser +"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>"+n.createTime +"</span></li>"+"<li><a href=javascript:; onclick=viewMap("+n.lat+","+n.lng+",'"+n.content+"') ><img src='/resource/openLayersMap/images/bubble.png' /></a></li>";
		   $("#chatDialogTable").append(tbBody);
	 }else if(n.inboxAttachments.length==0){
           tbBody += "<li style='color:3366ff'><span>"+n.createUser +"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span >"+n.createTime +"</span></li>"+"<li>" + n.content + "</li>";                             
           $("#chatDialogTable").append(tbBody); 
	 }else{
			return '';
	 }
}

//删除主程序
function deleteInboxByOpenIdAndDealState(event,allValue){
	$.ajax({
		url: PATH
		+ '/weChat/inbox/deleteInboxByOpenIdAndDealState.action?openIds='
		+ allValue+"&inbox.dealState=0",
		success : function(res) {
			if (res==true||res=="true") {
				var idArr = allValue.toString().split(",");
				for(var i = 0; i < idArr.length; i++) {
					$("#untreatedInboxList")
					.delRowData(idArr[i]);
				}
				$.messageBox({message : "删除成功!"});
				$("#untreatedInboxList").trigger("reloadGrid"); 
				return;
			}
			$.messageBox({message : "删除失败!"});
		}
	});
}


function getOpenIds(){
		var openIds = $("#untreatedInboxList").jqGrid("getGridParam", "selarrrow");
		return openIds;
}


//未接入客户列表显示
function untreatedInboxList(obj){
	 $("#untreatedInboxList").setGridParam({
		url:'${path}/weChat/inbox/findUntreatedInboxs.action',
		datatype: "json",
		page:1,
		mytype:"post",
		loadComplete: afterLoad
	});
	$("#untreatedInboxList").setPostData(obj);
	$("#untreatedInboxList").trigger("reloadGrid"); 
}

//查看一个用户发的所有未处理消息(包括附件)
function seachUntreatedInboxByOpenId(el,options,rowData){
	
	var untreatedInboxNumber=rowData.untreatedInboxNumber;
	if(untreatedInboxNumber!=0){
		return "<div style='clear:both' align='center'><a href='javascript:;' onclick='viewUntreatedInboxByOpenId("+'"'+rowData.fromUserName+'","'+rowData.toUserName+'"'+")' style='color:green;' >"+untreatedInboxNumber+"</a><div>";
	} 
	
	return "";  
}

//消息阅读状态
function inboxIsRead(el,options,rowData){
	
	var isRead=rowData.isRead;
	if(isRead==0){
		return "未读消息";
	}else{
		return "已读消息";
	} 
	
	return "";  
}

function afterLoad(){
	//获取列表数据
	var rowDatas = new Array();
	rowDatas =  $("#untreatedInboxList").getDataIDs();
	//alert(rowDatas);
	 for(var i=0;i<rowDatas.length;i++){
		var ent =  $("#untreatedInboxList").getRowData(rowDatas[i]);
		
		if(ent.isRead=="0"){
			$("#"+ent.fromUserName).addClass("jqgrowNoneImage");
		}else{
			$("#"+ent.fromUserName).removeClass("jqgrowNoneImage");
		}
    } 
}


 //查看单个用户未处理消息
function viewUntreatedInboxByOpenId(fromUserName,toUserName){
	 $("#untreatedInboxDialog").createDialog({
		width: 600,
		height: 600,
		title: '未处理消息',
		url:"${path}/weChat/inbox/dispatch.action?mode=viewUntreatedInboxByOpenId&inbox.isKeyWordMsg=2&inbox.org.id="+getCurrentOrgId()+"&inbox.fromUserName="+fromUserName+"&inbox.toUserName="+toUserName,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
				$("#"+fromUserName).removeClass("jqgrowNoneImage");
			}
		}
	});
} 

function viewFile(inboxAttachmentId){
	$("#inboxDialog").createDialog({
		title:"查看附件",
		width: 550,
		height: 550,
		url:'${path}/weChat/inbox/dispatch.action?mode=playMedia&attachmentId='+inboxAttachmentId,
		buttons: {
			"关闭" : function(event){
				$(this).dialog("close");
			}
		}
	});
}

//地图显示
function viewMap(lat,lng,content){
   $("#replyMessageDlg").createDialog({
		width: 800,
		height: 500,
		title:'显示地理位置信息',
		url:"${path}/wechat/mapView.jsp?lat="+lat+"&lng="+lng+"&title="+content,
		buttons: {
		   "关闭" : function(){
		         $(this).dialog("close");
		   }
		}
	});
}
function changeParameter(obj){
	var time=obj.createTime;
	var date = time.substring(0,10);
	var hour = time.substring(11,13);
	var minute = time.substring(14,16);
    $('#occurDate').val(date);
 	$('#minute').attr("disabled",false);
	$('#hours').val(hour);
	$('#minute').val(minute);
}

</script>