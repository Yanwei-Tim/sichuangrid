<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="/openLayersMap/includes/jsInclude.jsp"/>
<style type="text/css">
	#gisMap{
		height: 532px;
	}

/* 本例子css */
		.picScroll-left{ width:100%;  overflow:hidden; position:relative;  border:1px solid #ccc;   }
		.picScroll-left .hd .prev,.picScroll-left .hd .next{ display:block;overflow:hidden;cursor:pointer;top:0px;width:32px; height:100px;position:absolute;}
		.picScroll-left .hd .prev{left:0px;background:#fff url(/resource/system/images/button-left.png) no-repeat center center; }
		.picScroll-left .hd .next{right:0px;background:#fff url(/resource/system/images/button-right.png) no-repeat center center;  }

		
		.picScroll-left .bd{ padding:10px; margin-left:35px;}
		.picScroll-left .bd ul{ overflow:hidden; zoom:1; }
		.picScroll-left .bd ul li{ margin:0 3px; float:left; _display:inline; overflow:hidden; text-align:center;  }
		.picScroll-left .bd ul li .pic{ text-align:center;width:128px; height:80px; background:#f00;border-radius:5px;-webkit-border-radius:5px;-moz-border-radius:5px;}
		.picScroll-left .bd ul .green .pic{ background:#38c853;}
		.picScroll-left .bd ul .red .pic{ background:#e02f2f;}
		.picScroll-left .bd ul .yellow .pic{ background:#e6c109;}
		.picScroll-left .bd ul .gray .pic{ background:#999999;}
	
		.wrapBox{ border:1px solid #cccbcb;width:100%;}
		.wrapBox .wrapLeft{ float:left;}
		.wrapBox .wrapRight{ float:left;}
		.pop1 table{width:398px;border:1px solid #d6d6d6;}
		.pop1 table td{ height:34px; line-height:34px; text-indent:10px;}
		.pop1 .popRed{font-size:14px;color:#e02f2f;}
		.pop1 .popGray{color:#8e8e8e;text-indent:0px; text-align:right;}
		.popBtn{width:400px;}
		.centerBtn{width:270px;margin:0 auto;}
		.centerBtn a{border-radius:5px;-webkit-border-radius:5px;-moz-border-radius:5px;width:84px; height:32px; line-height:32px; text-align:center;color:#fff;font-size:14px;float:left;margin:10px 25px;}
		.ignoreBtn{background-color:#5F5FFF}
		.dealBtn{background-color:#e02f2f;}
		.mesName,.mesNumber{ border-bottom: 1px dashed rgba(255,255,255,0.4);}
		.imgSlide{border:1px solid #c7c5c5;}
		.imgSlide .bd ul li p{ height:26px; line-height:26px;}
		.imgSlide .hd .prev{border-right:1px solid #c7c5c5; }
		.imgSlide .hd .next{border-left:1px solid #c7c5c5;}
		
</style>
<div class="container container_24" style="z-index:0;">
	<div class="grid_19">
		<div id="gisMap"></div>
		<div class="imgSlideWrap hidden" >
            <div class="picScroll-left imgSlide">
                <div class="hd">
                    <a class="next"></a>
                    <a class="prev"></a>
                </div>
                <div class="bd" id="familyInfoMsgCon">
                </div>
            </div>
		</div>
	</div>
	<div class="grid_5 ">
		<table class="zjsList" id="tenHouseholdsJointMapList">
		</table>
		<div class="zjsPage" id="tenHouseholdsJointMapListPager">
		</div>
	</div>
</div>
<div id="gisIndexDialog"></div>
<div id="issueDialog"></div>

<script type="text/template" id="familyInfoMsgTPL">
	<ul class="picList">
		{{each json as obj l}}
			<li class="green" userId="{{obj.userId}}">
                <div class="pic"><p class="mesName">{{obj.user.name}}</p><p class="mesNumber">{{obj.user.helpLine}}</p><p class="mesStatus">{{obj.isReport+""}}</p></div>
            </li>
		{{/each}}
	</ul>
</script>
<script type="text/javascript">
var receiveMsgInfoId;
$(document).ready(function(){
	$("#gisMap").empty().TqMap({
			isShowM2D: true,						            //是否加载二维地图
			isShowM3D: false,						            //是否加载三维地图
			isShowWFS: false,						            //是否加载热区
			isViewMousePosition:false,
			isViewPanZoomBar:false,
			isViewScaleLine:false,
			gisType:"2D",
			mapMaxResolution:18,
			mapMinResolution:18
		});
	$("#tenHouseholdsJointMapList").jqGridFunction({
		url:'${path}/tenHouseholdsJoint/receiveMsgInfo/findReceiveMsgInfos.action',
		datatype: "json",
		postData:{
			"messageInfoVo.orgId":getCurrentOrgId()
		},
		sortname:"sendDate",
		mtype:'post',
		rowNum:10,
		colModel: [
			{name:"id", index:"id", sortable:false,hidden:true,frozen : true},
			{name:"message.id", index:"messageId", sortable:false,hidden:true,frozen : true},
			{name:"teamId", index:"teamId", sortable:false,hidden:true,frozen : true},
			{name:"userId", index:"userId", sortable:false,hidden:true,frozen : true},
			{name:"user.helpLine",index:"helpLine",label:"求助电话",formatter:helpLineFormatter_receiveBox,sortable:false,width:100,frozen:true},
			{name:"user.name", index:"name",label:"姓名",width:80, sortable:false,frozen : true},
			{name:"createDate",index:"createDate",label:"求助时间",width:60, sortable:false,align:"center"},
		],
	  	multiselect:false,
	  	showColModelButton:false
	});
	
	function helpLineFormatter_receiveBox(el,options,rowData){
		return "<a href='javascript:' onclick='loadFamilyInfoAndMsg("+rowData.id+")'><span>"+rowData.user.helpLine+"</span></a>";
	}
})
function loadFamilyInfoAndMsg(id){
	var rowData = $("#tenHouseholdsJointMapList").getRowData(id);
	$.ajax({
		url:PATH+'/tenHouseholdsJoint/sendMsgInfo/findTeamFamilySendMsgInfos.action',
		data:{
			"messageInfoVo.teamId":rowData.teamId,
			"messageInfoVo.messageId":rowData["message.id"]
		},
		success:function(datas){
			$("#gisMap").TqMap("clearMarkers");
			$("#gisMap").TqMap("deletePopup");
			for(var i=0;i<datas.length;i++){
				addMarker_ten(datas[i],rowData.userId);
				if(datas[i].userId == rowData.userId){
					showMsgPopup_ten(datas[i],rowData.id);
				}else{
					showFamilyInfoDlg(datas[i].userId);
				}
			}
			var relationHtml = template("familyInfoMsgTPL",{'json':datas});
			relationHtml = relationHtml.replace(/<p class="mesStatus">undefined<\/p>/gi,"<p class='mesStatus'>等待通知</p>")
											.replace(/<p class="mesStatus">false<\/p>/gi,"<p class='mesStatus'>正在通知</p>")
											.replace(/<p class="mesStatus">true<\/p>/gi,"<p class='mesStatus'>通知成功</p>")
			$("#familyInfoMsgCon").empty().html(relationHtml);
			$("#familyInfoMsgCon").find("li[userId='"+rowData.userId+"']").removeClass("green").addClass("red");
			$("#familyInfoMsgCon").find("li[userId='"+rowData.userId+"']").find("p.mesStatus").text("发出告警");
			$(".imgSlideWrap").show();
			loadSlideContainer();
		}
	})
}
$(".imgSlideWrap").delegate("li","click",function(){
	var userId = $(this).attr("userId");
	var marker = $("#gisMap").TqMap("getMarkerBy",{value:"user_"+userId});
	if(marker){
		$(marker.icon.imageDiv).trigger("click");
	}
})
function dealTenHouseholdsJointMsg(id){
	receiveMsgInfoId = id;
	$("#issueDialog").createDialog({
		width:840,
		height:570,
		title:'新增事件处理信息',
		url:PATH+'/issues/issueManage/dispatch.action?mode=addTenHJointIssue&keyId='+$("#currentOrgId").val()+"&id="+id,
		buttons: {
	   		"保存" : function(event){
	   			$("#issueMaintainForm").submit();
	   		},
			"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function reloadIssue(){
	$.ajax({
		url:PATH+'/tenHouseholdsJoint/receiveMsgInfo/dealReceiveMsgInfo.action?id='+receiveMsgInfoId,
		success:function(data){
			 $.messageBox({message:"已经成功处理该告警信息!"});
			$("#gisMap").TqMap("deletePopup");
		}
	})
}

function dealReceiveMsgInfo(id){
	$.confirm({
		title:"确认忽略该告警信息",
		message:"确认忽略该告警信息，数据一经忽略，将不能恢复！",
		okFunc: function() {
			reloadIssue()
		}
	});
}

function addMarker_ten(data,userId){
	var imgUrl = "/resource/openLayersMap/images/bubble.png";
	if(data.userId == userId){
		imgUrl = "/resource/openLayersMap/images/bubble_GLUE.fw.png";
		$("#gisMap").TqMap("setCenter",{lon:data.user.centerLon,lat:data.user.centerLat});
	}
	$("#gisMap").TqMap("addMarker",{
			iconUrl:PATH + imgUrl,
			markerW:28,
			markerH:33,
			lon:data.user.centerLon,
			lat:data.user.centerLat,
			markerId:"user_"+data.userId,
			data:data
		});
}
function showMsgPopup_ten(data,id){
	$("#gisMap").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:function(e){
			$("#gisMap").TqMap("addPopup",{
				url:PATH+"/tenHouseholdsJoint/receiveMsgInfo/dispatch.action?mode=gisSms&id="+id,
				lon:data.user.centerLon,
				lat:data.user.centerLat,
				lonlat:{lon:data.user.centerLon,lat:data.user.centerLat},
				popupW:400,
				popupH:200
			});	
		}});
}
function showFamilyInfoDlg(id){
	$("#gisMap").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:function(e){
			$("#gisIndexDialog").createDialog({
				width: 900,
				height: 400,
				title:'查看信息用户',
				modal : true,
				url:'${path}/tenHouseholdsJoint/familyCondition/dispatch.action?mode=view&id='+id+'&organizationId='+getCurrentOrgId(),
				buttons: {
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}});
}

function loadSlideContainer(){
	var winW=$(window).width();
			var  mapWidth=$(window).width()-470;
			$(".imgSlideWrap").width(mapWidth).css({position:'absolute',top: '425px','z-index': 999});
    var liWidth=$(".picList li").outerWidth(true);
	
	var liSum=parseInt(mapWidth/liWidth)
    var num=mapWidth/liWidth+0.2;
    $(".picList").width($(".picList li").outerWidth() * ($(".picList li").length + 1) + 200)
    $(".picScroll-left .bd,.imgSlideBox").width(mapWidth+108);
    $(".picScroll-left").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,scroll:1,effect:"left",autoPlay:false,vis:liSum,trigger:"click"});
}	
</script>


