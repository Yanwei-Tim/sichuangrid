<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript" src="/resource/external/jPlayer/js/jquery.jplayer.min.js"></script>
<style>

</style>

<input id="currentFromUserName"  type="hidden" value="${inbox.fromUserName}" />
<%-- <input id="toUserName"  type="hidden" value="${inbox.toUserName}" /> --%>
<div class="content" style="line-height:30px;magin-left:30px">
<!--       <table id="untreatedInboxTable" style="border-spacing:50px;">
      </table> -->
       <div id="jquery_jplayer_3" class="jp-jplayer"></div>
       <ul id="untreatedInboxTable">
       </ul>
</div>

<script type="text/javascript">
var currentFromUserName=$("#currentFromUserName").val();
//var toUserName=$("#toUserName").val();
$(document).ready(function(){
	
	$("#jquery_jplayer_3").jPlayer({
		ready: function () {
		},
		swfPath: "/resource/external/jPlayer/js",
		solution: "flash, html",
		supplied: "mp3,m4a,flv,arm",
		wmode: "window",
		keyEnabled: true
	});
	viewUntreatedInboxByOpenIdList();
});

$("#untreatedInboxTable").delegate(".jp-play", "click", function(){
	
	var media=$(this).data("media");
	console.log(media);
	var type=$(this).data("type");
	$("#jquery_jplayer_3").jPlayer( "clearMedia" );
	var box=$(this).closest(".jp-audio");
	$(this).hide().next().show();	
	$("#jquery_jplayer_3").jPlayer("setMedia", {
		"mp3": media
	}).jPlayer("play");
});
$("#untreatedInboxTable").delegate(".jp-pause", "click", function(){
	$(this).hide().prev().show();
	$("#jquery_jplayer_3").jPlayer("stop");
});

//查看单个用户未处理消息
function viewUntreatedInboxByOpenIdList(){
	
	
	
	var dataList="inbox.dealState=0&inbox.isKeyWordMsg=2&inbox.fromUserName="+currentFromUserName;	
	$.ajax({         		  
		  type:'post',
		  url:"${path}/weChat/inbox/findInboxsByOpenIdAndDealState.action",
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
                  // alert(n.createUser);
                    viewUntreatedFile(n,tbBody);       
                });
        	}
		  }
	  });
	
}

function viewUntreatedFile(n,tbBody){
	
   	if(n.inboxAttachments.length>0 && n.inboxAttachments[0].extFileName=='jpg'){
		   tbBody += "<li style='color:3366ff'><span>"+n.createUser +"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>"+n.createTime +"</span></li>"+"<li><a href='javascript:;' onclick='viewFile("+n.inboxAttachments[0].inboxAttachmentId+")' ><img src='"+n.inboxAttachments[0].fileActualUrl+"' style='width:32px;height:32px;'/></a></li>";
           $("#untreatedInboxTable").append(tbBody);
	 }else if(n.inboxAttachments.length>0){
		  // tbBody += '<li style="color:3366ff"><span>'+n.createUser +'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>'+n.createTime +'</span></li>'+'<li id="jp_container_1" class="jp-audio"><a href="javascript:;" class="jp-play" data-type="'+n.inboxAttachments[0].extFileName+'" data-media="'+n.inboxAttachments[0].fileActualUrl+'"><img src="/resource/system/images/jplayer/play.png" /></a><a href="javascript:;" class="jp-pause" tabindex="1"><img src="/resource/system/images/jplayer/stop.gif" /></a></li>';

		  if(n.content!=null&&n.content!=""){
			  tbBody += '<li style="color:3366ff"><span>'+n.createUser +'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>'+n.createTime +'</span></li>'+'<li id="jp_container_1" class="jp-audio"  ><a class="jp-play" data-type="'+n.inboxAttachments[0].extFileName+'" data-media="'+n.inboxAttachments[0].fileActualUrl+'"><img src="/resource/system/images/jplayer/play.png" /></a><a href="javascript:;" class="jp-pause" tabindex="1"><img src="/resource/system/images/jplayer/stop.gif" /></a></li>';
			  tbBody += '<li >('+n.content +')</li>'
		  }else{
		   tbBody += '<li style="color:3366ff"><span>'+n.createUser +'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>'+n.createTime +'</span></li>'+'<li id="jp_container_1" class="jp-audio"  ><a class="jp-play" data-type="'+n.inboxAttachments[0].extFileName+'" data-media="'+n.inboxAttachments[0].fileActualUrl+'"><img src="/resource/system/images/jplayer/play.png" /></a><a href="javascript:;" class="jp-pause" tabindex="1"><img src="/resource/system/images/jplayer/stop.gif" /></a></li>';
		  }
		   $("#untreatedInboxTable").append(tbBody);
	 }else if(n.msgType!=null && n.msgType=='location'){
		   tbBody +="<li style='color:3366ff'><span>"+n.createUser +"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>"+n.createTime +"</span></li>"+"<li><a href=javascript:; onclick=viewMap("+n.lat+","+n.lng+",'"+n.content+"') ><img src='/resource/openLayersMap/images/bubble.png' /></a></li>";
		   $("#untreatedInboxTable").append(tbBody);
	 }else if(n.inboxAttachments.length==0){
           tbBody += "<li style='color:3366ff'><span>"+n.createUser +"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span >"+n.createTime +"</span></li>"+"<li>" + n.content + "</li>";                             
           $("#untreatedInboxTable").append(tbBody); 
	 }else{
			return '';
	 }
}

</script>