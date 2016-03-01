<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:set var="currentTime" value="@java.lang.System@currentTimeMillis()"/>
<div class="searchResult" style="overflow: hidden;">
	<div class="resultNum"><span class="plaint" title="清空"><a id="emptyIssueMapAndList" href='javascript:void(0)' >【清空】</a></span>共有<span id="issueListTotal">0</span>条搜索记录</div>
	<div class="resultCon">
		<div id="issueList"></div>
		<div id="loadingValue"><img src='../resource/images/loading.gif' alt='加载中...'  style='vertical-align:middle;margin-left:15px;height:25px;' />信息加载中，请等待...</div>
	</div>
</div>
<div id="issueListPager" class="pagination"></div>

<script type="text/javascript">
var issueListPrevsPageInfo=null;

$(function(){
	viewIssueLayerInformation();
	$("#emptyIssueMapAndList").click(function(){
		IS_MAP_MOVE_SEARCH=true;//地图滑动搜索开启
		if(!"<s:property value='#parameters.flag'/>"){//判断是否辖区分布统计 true则为是的情况
			$("#updownlist").val(1);//将搜索下拉选项选中为人员信息
			$("#queryString").val("请输入(姓名/身份证号码/地址)");
			$("#issueMarkerId").parent().removeClass("currentPosClick");//去除服务办事图层选中样式
		}
		$("#map").TqMap("deletePopup");
		clearMarkersByMarkerId_objectName("issue");
		$("#issueListTotal").html(0);
		$("#emptyIssueMapAndList").hide();
		$("#issueList").nextAll().remove();
		$("#issueListPager").empty();
	});
	
	function viewIssueLayerInformation(){
		var screen = $("#map").TqMap("getScreenExtent");
		var minX = screen.minLon;
		var minY = screen.minLat;
		var maxX = screen.maxLon;
		var maxY = screen.maxLat;
		var orgId = $('#currOrgId').val();
		var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_SEARCH'/>";
		clearMapAllMarkerAndPopup();
		if("<s:property value='#parameters.flag'/>"){//判断是否右边列表点击事件 true则为是的情况
			var url="";
			var issueType="<s:property value='#parameters.type'/>";
			issueAreaDistribution(orgId,issueType,modeType);
			return;
		}
		
		if($("#showPersonLayer li.currentPosClick label").first().text()==$("#issueMarkerId").text()){
			if(isTownDownOrganization()){
				$("#map").TqMap("clearMarkers");
				viewTownAboveIssueLayerInformation(minX,minY,maxX,maxY,orgId);
			}else{
				viewTownUnderIssueLayerInformation(minX,minY,maxX,maxY,orgId);
			}
		}else{
			$("#loadingValue").html('');
		}
	}
	
	//事件辖区分布
	function issueAreaDistribution(orgId,issueType,modeType){
		$.ajax({
			url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action',
			async:false,
			data:{
				"organization.id":orgId,
				"modeType":modeType,
				"typeName":issueType,
				"page":1,
	            "rows":1000000,
	            "sidx":"iu.centerlon",
	            "sord":"asc"
			},
			success:function(data){
				drawBulle(data);
			}
		});
		$("#issueList").GisList({
			url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action',
			datatype: "json",
			data:{
				"organization.id":orgId,
				"modeType":modeType,
				"typeName":issueType,
				"page":1,
	            "rows":10,
	            "sidx":"iu.centerlon",
	            "sord":"asc"
			},
			ajaxLoad:issueListNextPage,//翻页方法
			rowFormatter:function(row,rowNumber){
				showDetailsList();
				var thisRow = $("<li id='"+row.issueId+"'/>");
				if(row!=null) {
					var num = "";
					var name = "";
					if(row.centerLon!=null&&row.centerLat!=null){
						num = $("<div />").html("<span>"+getBubble(rowNumber)+"</span>").addClass("resAbc");
						name = $("<div class='name' />").html("<a href=\"javascript:void(0);\" onclick=\"javascript:showIssuePopup("+row.centerLon+","+row.centerLat+","+row.issueLogId+")\">事件主题："+row.subject+"</a>");
					}else{
						num = $("<div />").addClass("resNum");
						name = $("<div class='name'/>").html("<a>"+"事件主题:"+row.subject+"</a>");
					}
					var serialNumber = $("<div />").html("服务单号 ："+row.serialNumber);
					thisRow.append(num).append(name).append(serialNumber);
				}
				return thisRow;
			}
		});
	}
	
	function viewTownAboveIssueLayerInformation(minX,minY,maxX,maxY,orgId){
		$.ajax({
			url:'${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndTypeName.action',
			async:false,
			data:{
				"organization.id":orgId,
				"screenCoordinateVo.minLon":minX,
				"screenCoordinateVo.maxLon":maxX,
				"screenCoordinateVo.minLat":minY,
				"screenCoordinateVo.maxLat":maxY,
				"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_STATISTIC'/>"
			},
			success:function(data){
				showDetailsList();
				var str="<tr class='keyPopulation'><td class='msgL'><span class='text'>所属辖区</td><td class='msgL'><span class='text'>绑定数</td>";
				for(var i=0;i<data.length;i++){
					str+="<tr class='keyPopulation' onclick='detailedListingClick("+data[i].organization.id+")'><td class='msgL'><span class='text'>"+data[i].organization.orgName+"</span></td><td class='dataPoint'><span class='num'>" +data[i].boundMapNum+"</span></td></tr>";
					if(data[i].lon!=null && data[i].lat!=null){
						var pop=$("#map").TqMap("addPopupText",{
							 lon:data[i].lon,
							 lat:data[i].lat,
							 popupW:50,
							 popupH:12,
							 popupText:""+data[i].boundMapNum+"",
							 backgroundColor:"#FF0000",
							 data:data[i].organization.id
						});
						$(pop.contentDiv).attr("title",data[i].organization.orgName+" 事件绑定数："+data[i].boundMapNum);
						$("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:popClick});
					}
				}
				$("#tabList").html("<div class='new_personal_tableCon'><table class='new_personal_table'>"+str+"</table></div>");
				$(".new_personal_table tr").hover(function(){
					$(this).addClass("tableCur").siblings().removeClass("tableCur");
				},function(){
					$(this).removeClass("tableCur");
				});
			}
		});
	}
	
	function drawBulle(data){
		if(data.records>0){
			var rows=data.rows;
			for(var i =0 ;i<rows.length;i++){
				if(rows[i].centerLon!=null&&rows[i].centerLat!=null){
					var imgUrl="../resource/openLayersMap/images/bubble.png";
					var mouseoverImgUrl="../resource/openLayersMap/images/bubble_GLUE.fw.png";
					$("#map").TqMap("addMarker",{
						iconUrl:PATH + imgUrl,
						markerW:15,
						markerH:20,
						lon:rows[i].centerLon,
						lat:rows[i].centerLat,
						markerId:rows[i].issueLogId+"_issue",
						data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"事件："+rows[i].subject}
					});
					$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickByIssue});
					registerMarkerMouseoverAndMouseoutEvent();
				}
			}
		}
	}
	
	function writeRightData(row,rowNumber){
		var thisRow = $("<li id='"+row.issueId+"'/>");
		if(row!=null) {
			var num = "";
			var name = "";
			if(row.centerLon!=null&&row.centerLat!=null){
				num = $("<div />").html("<span>"+getBubble(rowNumber)+"</span>").addClass("resAbc");
				name = $("<div class='name' />").html("<a href=\"javascript:void(0);\" onclick=\"javascript:showIssuePopup("+row.centerLon+","+row.centerLat+","+row.issueLogId+")\">事件主题："+row.subject+"</a>");
			}else{
				num = $("<div />").addClass("resNum");
				name = $("<div class='name'/>").html("<a>"+"事件主题:"+row.subject+"</a>");
			}
			var serialNumber = $("<div />").html("服务单号 ："+row.serialNumber);
			var btnList = "";
			if(row.centerLon!=null&&row.centerLat!=null){
				btnList = $("<div />").addClass("btnList").append(serialNumber).append("<a href='javascript:;' class='cancelBind' onclick='unboundIssue("+row.issueId+","+row.serialNumber+","+rowNumber+","+row.issueLogId+","+row.occurOrg.id+",\""+row.subject+"\")'>[解除绑定]</a>");
			}else{ 
				btnList = $("<div />").addClass("btnList").append(serialNumber).append("<a href='javascript:;' class='addressBind' onclick='boundIssue("+row.issueId+","+row.serialNumber+","+rowNumber+","+row.issueLogId+","+row.occurOrg.id+",\""+row.subject+"\")'>[绑定]</a>");
			}
			thisRow.append(num).append(name).append(serialNumber).append(btnList);
		}
		return thisRow;
	}
	
	//乡镇以下列表数据
	function viewTownUnderIssueLayerInformation(minX,minY,maxX,maxY,orgId){
		$.ajax({
			url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action',
			async:false,
			data:{
				"screenCoordinateVo.minLon":minX,
				"screenCoordinateVo.maxLon":maxX,
				"screenCoordinateVo.minLat":minY,
				"screenCoordinateVo.maxLat":maxY,
				"organization.id":orgId,
				"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_SEARCH'/>",
				"page":1,
				"rows":1000000,
				"sidx":"iu.centerlon",
	            "sord":"asc"
			},
			success:function(data){
				showDetailsList();
				drawBulle(data);
			}
		});
		$("#issueList").GisList({
			url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action',
			datatype: "json",
			data:{
				"screenCoordinateVo.minLon":minX,
				"screenCoordinateVo.maxLon":maxX,
				"screenCoordinateVo.minLat":minY,
				"screenCoordinateVo.maxLat":maxY,
				"organization.id":orgId,
				"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_SEARCH'/>",
				"page":1,
				"rows":10,
				"sidx":"iu.centerlon",
	            "sord":"asc"
			},
			ajaxLoad:issueListNextPage,//翻页方法
			rowFormatter:function(row,rowNumber){
				return writeRightData(row,rowNumber);
			}
		});
	}
	
	function issueListNextPage(data){
		if(data.records>0){
			var rows=data.rows;
			if(issueListPrevsPageInfo!=null && issueListPrevsPageInfo!="undefined" && issueListPrevsPageInfo!=""){
				for(var j = 0; j < issueListPrevsPageInfo.length; j++){
					$("#map").TqMap("deleteMarkerByMarkerId",{markerId:issueListPrevsPageInfo[j].issueLogId +"_issue"});
					var imgUrl="../resource/openLayersMap/images/bubble.png";
					var mouseoverImgUrl="../resource/openLayersMap/images/bubble_GLUE.fw.png";
					$("#map").TqMap("addMarker",{
						iconUrl:PATH + imgUrl,
						markerW:15,
						markerH:20,
						lon:issueListPrevsPageInfo[j].centerLon,
						lat:issueListPrevsPageInfo[j].centerLat,
						markerId:issueListPrevsPageInfo[j].issueLogId+"_issue",
						data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"事件："+issueListPrevsPageInfo[j].subject}
					});
					$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickByIssue});
					registerMarkerMouseoverAndMouseoutEvent();
				}
			}
			issueListPrevsPageInfo=rows;
			for(var i =0 ;i<rows.length;i++){
				$("#map").TqMap("deleteMarkerByMarkerId",{markerId:rows[i].issueLogId+"_issue"});
				if(rows[i].centerLon!=null&&rows[i].centerLat!=null){
					var imgUrl = getBubbleUrl(i);
					var mouseoverImgUrl=getMouseoverBubbleUrl(i);
					$("#map").TqMap("addMarker",{
						iconUrl:PATH + imgUrl,
						markerW:20,
						markerH:25,
						lon:rows[i].centerLon,
						lat:rows[i].centerLat,
						markerId:rows[i].issueLogId+"_issue",
						data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"事件："+rows[i].subject}
					});
					$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickByIssue});
					registerMarkerMouseoverAndMouseoutEvent();
				}
			}
		}
	}
	
});

var _serialNumber,_index,_id,_issueLogId,_orgId,_subject;
function boundIssue(id,serialNumber,index,issueLogId,orgId,subject){
	$("#map").TqMap("deactivateSelectFeature",{sfname: "select"});
	_id=id;
	_orgId=orgId;
	_serialNumber = serialNumber;
	_index = index;
	_issueLogId = issueLogId;
	_subject=subject;
	$("#map").TqMap("addMarker",{
		iconUrl:PATH +"/resource/openLayersMap/images/bubble.png",
		markerW:26,
		markerH:30,
		markerId:"0_issue"
	});
	$("#map").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
	$("#map").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAddIssue});
	$("#map").TqMap("registerEvent",{eventName:"click",func:boundOpenLayersMapClick});
}

function boundOpenLayersMapClick(){
	var id = _id;
	var serialNumber=_serialNumber;
	var issueLogId = _issueLogId;
	var index = _index;
	var lon = mapMousemoveLon;
	var lat = mapMousemoveLat;
	var orgId=_orgId;
	var layerData=getGis2DLayerDataByOrgId(_orgId);
	if(layerData!=null && layerData!=""){
		if(!checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
			$.messageBox({
				message:"该位置已超出了当前部门所划分地图区域的范围,请在指定范围绑定事件！",
				level:'error'
			});
			return;
		}
	}
	var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_MANAGE'/>";
	$("#map").TqMap("deleteMouseTip");//删除鼠标跟随的提示信息
	$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAddIssue});
	$("#map").TqMap("deleteMarkerByMarkerId",{markerId:"0_issue"});
	$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:markerMousemove});
	$("#map").TqMap("unregisterEvent",{eventName:"click",func:boundOpenLayersMapClick});
	$.ajax({
		async: false,
		url:"${path}/gis/twoDimensionMapManageCommonManage/boundTwoDimensionMap.action?ids="+id+"&lon="+lon+"&lat="+lat+"&modeType="+modeType,
		datatype: "json",
		success:function(data){
			if(data){
				$.messageBox({
					message:"事件绑定成功"
				});
				var imgUrl = getBubbleUrl(index); 
				var mouseoverImgUrl=getMouseoverBubbleUrl(index);
				$("#map").TqMap("addMarker",{
					iconUrl:PATH+imgUrl,
					markerW:20,
					markerH:25,
					lon:lon,
					lat:lat,
					markerId:issueLogId+"_issue",
					data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"事件："+_subject}
				});
				$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickByIssue});
				registerMarkerMouseoverAndMouseoutEvent();
				var btnHtml = '<a href="javascript:;" class="cancelBind" onclick="javascript:unboundIssue('+id+','+serialNumber+','+index+','+issueLogId+','+_orgId+',\''+_subject+'\');">[解除绑定]</a>';
				$(".resultlist:visible #"+id+" .btnList").html(btnHtml);
				$(".resultlist:visible #"+id+" .resNum").removeClass("resNum").addClass("resAbc").html("<span><input type='image' src='"+imgUrl+"' /></span>");
				$(".resultlist:visible #"+id+" .name a").attr("onclick","javascript:showIssuePopup("+data.centerLon+","+data.centerLat+","+issueLogId+")");
			}else{
				$.messageBox({
					error:"系统错误，请与管理员联系!"
				});
			}
		}
	});
}

//解除绑定
function unboundIssue(id,serialNumber,index,issueLogId,orgId,subject) {
	var modeType="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@KEY_ISSUE'/>";
	$.confirm({
		title:"确认解除绑定",
		message:"该事件已经绑定，确认要解除绑定吗？",
		width: 400,
		okFunc: function() {
			$.post(
				"${path}/gis/twoDimensionMapManageCommonManage/unBoundTwoDimensionMap.action",
				{
					"id":id,
					"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_MANAGE'/>",
					"gisBoundVo.orgId":$("#currOrgId").val()
				},
				function(result) {
					if(result) {
						$.messageBox({message:"事件解除绑定成功！"});
						var btnHtml = '<a href="javascript:;" class="addressBind" onclick="javascript:boundIssue('+id+','+serialNumber+','+index+','+issueLogId+','+orgId+',\''+subject+'\');">[绑定]</a>';
						$(".resultlist:visible #"+id+" .btnList").html(btnHtml);	//按钮设置
						$(".resultlist:visible #"+id+" .name a").removeAttr("onclick");
						$(".resultlist:visible #"+id+" .resAbc").empty().removeClass("resAbc").addClass("resNum");		//marker样式改变
						$("#map").TqMap("deleteMarkerByMarkerId",{markerId:issueLogId+"_issue"});
					}else {
						$.messageBox({
							message:"事件解除绑定失败！",
							level:'error'
						});
					}
				},
				'json'
			);
		}
	});
}

//显示事件的popup
function showIssuePopup(centerLon,centerLat,issueLogId){
	$("#mode").val("<s:property value='@ com.tianque.openLayersMap.util.GisGlobalValueMap@PERSON_MODE'/>");
	$("#centerLon").val(centerLon);
	$("#centerLat").val(centerLat);
	var lonlat = {lon:centerLon,lat:centerLat};
	$("#map").TqMap("addPopup",{
		url:PATH+"/gis/commonOpenLayersManage/getIssueNewByIssueLogId.action?mode=person"
				+"&issueLogId="+issueLogId+"&type=place&showPeriphery="+true+"&idCardNo="+null,
		lon:centerLon,
		lat:centerLat,
		lonlat:lonlat,
		popupW:360,
		popupH:280
	});
}

//marker点击事件
function markerClickByIssue(e){
	$("#mode").val("<s:property value='@ com.tianque.openLayersMap.util.GisGlobalValueMap@PERSON_MODE'/>");
	var centerLon=mapMousemoveLon;
	var centerLat=mapMousemoveLat;
	var lonlat={lon:centerLon,lat:centerLat};
	$('#centerLon').val(centerLon);
	$('#centerLat').val(centerLat);
	var issueLogId = e.object.markerId.split("_")[0];
	$("#map").TqMap("unregisterEvent",{eventName:"click",func:markerClickByIssue});
	$("#map").TqMap("addPopup",{
		url:PATH+"/gis/commonOpenLayersManage/getIssueNewByIssueLogId.action?mode=person"
				+"&issueLogId="+issueLogId+"&type=place&showPeriphery="+true+"&idCardNo="+null,
		lon:centerLon,
		lat:centerLat,
		lonlat:lonlat,
		popupW:360,
		popupH:330
		//autoSize:true
	});
}
</script>