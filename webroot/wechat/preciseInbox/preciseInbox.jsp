<%@ page language="java" contentType="text/html; charset=UTF-8"import="java.util.*,com.tianque.core.util.GridProperties"pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path2 = request.getContextPath();
	String proxyIp = GridProperties.PROXY_SERVER_DOMAIN_NAME;
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path2 + "/";
	String orgId = (String) request.getAttribute("orgId");
	String weChatUserId = (String) request.getAttribute("weChatUserId");
	String openId = (String) request.getAttribute("openId");
%>
<html>

<head>
<title>报送消息</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=proxyIp%>/resource/weChat/weui.min.css" media="all"rel="stylesheet" />
<script type="text/javascript"src="<%=proxyIp%>/resource/desktop/js/jquery.js"></script>
<script charset="utf-8"src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<style>
.page_title {
	text-align: center;
	font-size: 34px;
	color: #225FBA;
	font-weight: 400;
	margin: 0 15%;
}
</style>
</head>

<body>
	<div class="page cell" id="contentDiv" style="display: none;">
		<div class="page-container">
			<h1 class="page_title" style="color: black">报送类型</h1>
			<input id="proxyIp" type="hidden" value="<%=proxyIp%>" /> 
			<input id="weChatUserId" name="" type="hidden" value="<%=weChatUserId%>" />
			<input id="orgId" name="preciseInbox.org.id"  type="hidden" value="<%=orgId%>" />
            <input id="openId"  type="hidden" value="<%=openId%>" />
			<div class="weui_cells_title">跳转的列表项</div>
			<div class="weui_cells weui_cells_access" >
				<a id="floatingPopulation" class="weui_cell" style="color: blue"href="">
					<div class="weui_cell_bd weui_cell_primary"><p>请点击</p></div>
					<div class="weui_cell_ft">发现流动人口</div>
				</a> 			
				<a id="hiddenDanger" class="weui_cell" style="color: blue" href="">
					<div class="weui_cell_bd weui_cell_primary"><p>请点击</p></div>
					<div class="weui_cell_ft">发现治安隐患</div>
				</a> 
				<a id="comprehensive" class="weui_cell" style="color: blue"href="">
					<div class="weui_cell_bd weui_cell_primary"><p>请点击</p></div>
					<div class="weui_cell_ft">综合服务消息</div>
				</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var latitude; // 纬度，浮点数，范围为90 ~ -90
		var longitude; // 经度，浮点数，范围为180 ~ -180。
		var floatingPopulationUrl = $("#proxyIp").val()+"/weChat/preciseInbox/dispatch.action?mode=addFloatingPopulationInbox";
		var hiddenDangerUrl = $("#proxyIp").val()+"/weChat/preciseInbox/dispatch.action?mode=addHiddenDangerInbox";
		var comprehensiveUrl = $("#proxyIp").val()+"/weChat/preciseInbox/dispatch.action?mode=addComprehensiveInbox";
		var orgId=$("#orgId").val();
		var weChatUserId=$("#weChatUserId").val();
		var openId=$("#openId").val();
		
		$(function() {
			initPage();
		})

		function initPage() {

			var url1 = $("#proxyIp").val() + '/WeChatJsSDKConfig/findWeChatJsSDKConfig.action';
			var targetUrl = location.href.split('#')[0];
			var param = {};
			param.targetUrl = targetUrl;
			param.toUserName = $("#weChatUserId").val();
			$.post(url1, param, function(config) {
				if (!window.jsApiList) {
					window.jsApiList = config.jsApiList;
				}
				wx.config({
					debug : false,
					appId : config.appId,
					timestamp : config.timestamp,
					nonceStr : config.nonceStr,
					signature : config.signature,
					jsApiList : config.jsApiList
				});
				// console.log(config);

				wx.ready(function() {
					wx.getLocation({
						type : 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
						success : function(res) {
							//console.log(res);
							latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
							longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
							var speed = res.speed; // 速度，以米/每秒计
							var accuracy = res.accuracy; // 位置精度
							$("#contentDiv").show();
						}
					});
				});
				wx.error(function(res) {
					//console.log(res);
				});

			});

		};
		
		$("#floatingPopulation").click(function() {
			if(latitude!=null&&latitude!=""){
				floatingPopulationUrl += "&orgId="+orgId+"&weChatUserId="+weChatUserId+"&openId="+openId+"&latitude="+latitude+"&longitude="+longitude;	
				$('#floatingPopulation').attr('href',floatingPopulationUrl);
			}	
		});
		$("#hiddenDanger").click(function() {
			if(latitude!=null&&latitude!=""){
			    hiddenDangerUrl += "&orgId="+orgId+"&weChatUserId="+weChatUserId+"&openId="+openId+"&latitude="+latitude+"&longitude="+longitude;
			    $('#hiddenDanger').attr('href',hiddenDangerUrl);
			}
		});
		$("#comprehensive").click(function() {
			if(latitude!=null&&latitude!=""){
			    comprehensiveUrl += "&orgId="+orgId+"&weChatUserId="+weChatUserId+"&openId="+openId+"&latitude="+latitude+"&longitude="+longitude;
			    $('#comprehensive').attr('href',comprehensiveUrl);
			}
		});
	</script>

</body>

</html>