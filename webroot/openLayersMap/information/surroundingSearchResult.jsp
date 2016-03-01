<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%	request.setAttribute("queryStr",java.net.URLDecoder.decode(request.getParameter("queryStr"),"utf-8")); %>
<jsp:include page="/includes/baseInclude.jsp" />
<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/videoList.js"></script>

<s:set var="currentTime" value="@java.lang.System@currentTimeMillis()"/>
<div class="searchResult" style="overflow: hidden;">
	<div class="resultNum"><span class="plaint" title="清空"><a id="emptyKeyPersonAreaDistributionMapAndList" href='javascript:void(0)' >【清空】</a></span>共有<span id="surroundingSearchTotal">0</span>条搜索记录</div>
	<div class="resultCon" style="overflow:auto;">
		<div id="surroundingSearch"></div>
		<div id="loadingValue"><img src='../resource/images/loading.gif' alt='加载中...'  style='vertical-align:middle;margin-left:15px;height:25px;' />信息加载中，请等待...</div>
	</div>
	<input type="hidden" id="search" value="" />
	<input type="hidden" id="queryStr" value="${queryStr }" /></div>
<div id="surroundingSearchPager" class="pagination"></div>

<script type="text/javascript">
	$(function(){
		function getInfoHeight(){
			var timer;
			function getHeight(){
				$(".resultCon").height($(".ui-layout-center").height()-120);
			}
			getHeight();
			$(window).resize(function(){
				clearTimeout(timer);
				timer=setTimeout(function(){getHeight()},500);
			})
		}
		getInfoHeight();
		
		viewSurroundingSearchInformation();
		$("#emptyKeyPersonAreaDistributionMapAndList").click(function(){
			$("#emptyKeyPersonAreaDistributionMapAndList").hide();
			$("#map").TqMap("deletePopup");
			clearMarkersByMarkerId_objectName("search");
			$("#surroundingSearchTotal").html(0);
			$("#surroundingSearch").nextAll().remove();
			$("#surroundingSearchPager").empty();
			clearGpsMapAllMarker();
			IS_MAP_MOVE_SEARCH=true;//地图滑动搜索开启
		});
		$("#elements").val("");
		
		function viewSurroundingSearchInformation(){
			clearMarkersByMarkerId_objectName("search");
			var modeType;
			var functionType="<s:property value='#parameters.functionType'/>";
			var elements="<s:property value='#parameters.elements'/>";
			if($("#peripheryType").val()=="place"){
				modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_CIRCUM_SEARCH'/>";
			}else if($("#peripheryType").val()=="person"){
				modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PERSON_CIRCUM_SEARCH'/>";
			}else if($("#peripheryType").val()=="dustbin"){
				modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@CITY_COMPONENTS_CIRCUM_SEARCH'/>";
			}else if($("#peripheryType").val()=="publicSecurity"){
				modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PUBLICSECURITY_CIRCUM_SEARCH'/>";
			}
			showResult(modeType,functionType);
		}
		
		function showResult(modeType,functionType){
			var elements="<s:property value='#parameters.elements'/>";
			var range="<s:property value='#parameters.range'/>";
			var queryStr = $("#queryStr").val();
			var lon = "<s:property value='#parameters.lon'/>";
			var lat = "<s:property value='#parameters.lat'/>";
			var dfoption = {
					url:'${path}/gis/twoDimensionMapCircumSearchCommonManage/findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat.action',
					dataType:"json",
					async:true,
					data:{
						"circumInfoVo.elements":elements,
		 				"circumInfoVo.range":range,
		 				"circumInfoVo.lon":lon,
		 				"circumInfoVo.lat":lat,
		 				"circumInfoVo.functionType":functionType,
		 				"circumInfoVo.queryStr":queryStr,
		 				"circumInfoVo.gisType":gisType,
		 				"modeType":modeType,
		 				"page":1,
						"rows":10,
						"circumInfoVo.orgId":$('#currOrgId').val()
					}
				}
			$.ajax($.extend(true,dfoption,{
					data:{ "rows":1000000 },
					success:function(data){
						peripheryMarker(data);
					}
			}));
			$("#surroundingSearch").GisList($.extend(true,dfoption,{
				data:{ "rows":10 },
				ajaxLoad:function(data){
					$(".resAbc span :image").width(32);
				},
				rowFormatter:function(row,rowNumber){
					row = TQConvert.toLonlat(row);
					showDetailsList();
					var thisRow=$("<li id='"+row.id+"'/>");
					if(row!=null){
						var num="";
						var btnList="";
						var btnTr="";
						var distance=$("<div />").html("<strong>距离范围:</strong>"+row.distance).addClass("btnList");
						var typeName=$("<div />").html("<strong>类&nbsp;&nbsp;&nbsp;型:</strong>"+row.typeName).addClass("btnList");
						var imgUrl = "${path }"+"<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERIPHERY_IMGURL'/>";
						if(row.typeName=="监控电子眼"){
							imgUrl="/resource/openLayersMap/images/video/video_off1.png";		
						}
						var domainId;
						if("place"==$("#peripheryType").val()||"dustbin"==$("#peripheryType").val()){
							domainId=row.encryptId;
						}else{
							domainId=row.id;
						}
						var operate ="<a href='#' style='color: blue;' onclick='peripheryMoveTo(\""+domainId+"\","+row.lon+","+row.lat+",\""+row.viewUrl+"\",\""+row.typeName+"\",\""+row.name+"\",\""+row.type+"\",\""+row.idCardNo+"\",\""+row.dustbinCode+"\",\""+row.code+"\")'>定位</a>"; 
						var address =""; 
						
						var centerLon=row.lon;
						var centerLat=row.lat;
						if(row.type=="deviceInformation"){
							var placeName=$("<div />").html('<a href="javascript:void(0);" onclick="showPopupbyKeyPlaces(\''+domainId+'\',\''+row.viewUrl+'\');">'+row.name+'</a>').addClass("btnList");
							address = (row.address=="undefined"||row.address==null)?"暂无":row.address;
			         		address=$("<div />").html("<strong>部&nbsp;&nbsp;&nbsp;门:</strong>"+address);
							num = $("<div />").html("<span><input type='image' src='"+imgUrl+"' /></span>").addClass("resAbc");
							btnList = $("<div />").addClass("btnList").append(placeName).append(typeName).append(address).append(distance).append(operate);
						}else if("place"==$("#peripheryType").val()){
			         		var placeName=$("<div />").html('<a href="javascript:void(0);" onclick="showPopupbyKeyPlaces(\''+domainId+'\',\''+row.viewUrl+'\');">'+row.name+'</a>').addClass("btnList");
			         		address = (row.address=="undefined"||row.address==null)?"暂无":row.address;
			         		address=$("<div />").html("<strong>地&nbsp;&nbsp;&nbsp;址:</strong>"+address);
							num = $("<div />").html("<span><input type='image' src='"+imgUrl+"' /></span>").addClass("resAbc");
							btnList = $("<div />").addClass("btnList").append(placeName).append(typeName).append(address).append(distance).append(operate);
						}else if("dustbin"==$("#peripheryType").val()){
							var placeName1=$("<div />").html('<a href="javascript:void(0);" onclick="showCityComponents(\''+row.typeName+'\',\''+domainId+'\',\''+row.viewUrl+'\');">'+row.dustbinCode+'</a>').addClass("btnList");
			         		if(row.address=="undefined"||row.address==null){
			         			address=$("<div />").html("<strong>地&nbsp;&nbsp;&nbsp;址:</strong>暂无");
			         		}else{
			         			address=$("<div />").html("<strong>地&nbsp;&nbsp;&nbsp;址:</strong>"+row.address);
			         		}
							num = $("<div />").html("<span><input type='image' src='"+imgUrl+"' /></span>").addClass("resAbc");
							btnList = $("<div />").addClass("btnList").append(placeName1).append().append(typeName).append(distance).append(operate);
						}else if($("#peripheryType").val()=="publicSecurity"){
							var code = $("<div />").html('<a href="javascript:void(0);" onclick="showPopupbyPublicSecurity('+domainId+',\''+row.viewUrl+'\');">'+row.code+'</a>');
							if(row.address=="undefined"||row.address==null){
			         			address=$("<div />").html("<strong>地&nbsp;&nbsp;&nbsp;址:</strong>暂无");
			         		}else{
			         			address=$("<div />").html("<strong>地&nbsp;&nbsp;&nbsp;址:</strong>"+row.address);
			         		}
							num = $("<div />").html("<span><input type='image' src='"+imgUrl+"' /></span>").addClass("resAbc");
							btnList = $("<div />").addClass("btnList").append(code).append(address).append(typeName).append(distance).append(operate);
						}else{
							var idcard=row.idCardNo;
							var name = $("<div />").html('<a href="javascript:void(0);" onclick="showPopupByKeyPerson('+domainId+',\''+row.viewUrl+'\');">'+row.name+'</a>');
							var idCardNo=$("<div />").html("<strong>身份证号码:</strong>"+row.idCardNo);
							num = $("<div />").html("<span><input type='image' src='"+imgUrl+"' /></span>").addClass("resAbc");
							btnList = $("<div />").addClass("btnList").append(name).append(idCardNo).append(typeName).append(distance).append(operate);
						}
						thisRow.append(num).append(name).append(idCardNo).append(btnList);
					}
					return thisRow;
				}
			}));
		}
	})
	
//公安部件popup显示
function showPopupbyPublicSecurity(id,viewUrl){
		var url=viewUrl+id;
		$("#publicSecurityDialog").createDialog({
			width:950,
			height:600,
			zIndex:99999,
			title:"查看信息",
			url:url,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
}
	
//重点场所popup显示
function showPopupbyKeyPlaces(id,viewUrl){
	var url=viewUrl+id;
	//url=base64encode(utf16to8(url));
	$("#keyPlaceDialog").createDialog({
		width:950,
		height:600,
		zIndex:99999,
		title:"查看信息",
		//url:'${path}/openLayersMap/detailsCommon/detailsViewDlg.jsp?viewUrl='+url,
		url:url,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}
	
//重点人员popup显示
function showPopupByKeyPerson(id,viewUrl){
	var url=viewUrl+id;
	//url=base64encode(utf16to8(url));
	$("#housePropertyDialog").createDialog({
		width:950,
		height:620,
		zIndex:99999,
		title:"查看信息",
		//url:'${path}/openLayersMap/detailsCommon/detailsViewDlg.jsp?viewUrl='+url,
		url:url,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}

//城市部件popup显示
function showCityComponents(partName,id,viewUrl){
	var url=viewUrl+id;
	if(partName!="监控电子眼"){
		$("#housePropertyDialog").createDialog({
			width:920,
			height:550,
			title:"查看信息",
			zIndex:99999,
			url:url,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
	}else{
		url='/gis/twoDimensionMapDetailViewCommonManage/getDetailViewPopupInfoByIdAndType.action?tableName=dustbin'+'&modeType=cityComponentsMapDetailViewService'+'&functionType=refineSearch'+'&isVideo=true'+'&id='+id+"&orgId="+$('#currOrgId').val()
		$("#commonDialog").createDialog({
			width:800,
			height:560,
			zIndex:99999,
			title:'视频监控',
			url:"${path}"+url,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
	}
}

//定位
function peripheryMoveTo(id,lon,lat,viewUrl,typeName,name,type,idCardNo,dustbinCode,code){
	var zoom=$("#map").TqMap("get","map").getZoom();
	$("#map").TqMap("setCenter",{
		lon:lon,
		lat:lat,
		zoom:zoom
	});//移动定位
	
	$("#map").TqMap("deleteMarkerByMarkerId",{
		markerId:id+'_search'
	});//根据markerId清除marker
	
	var imgUrl="../resource/openLayersMap/images/defaultMarker-2.gif";
	var mouseoverImgUrl="../resource/openLayersMap/images/defaultMarker.gif";
	var markerW=28;
	var markerH=31;
	if(typeName=="监控电子眼"){
		imgUrl="../resource/openLayersMap/images/video/video_off.png";
		mouseoverImgUrl="../resource/openLayersMap/images/video/video_on.png";
		markerW=24;
		markerH=42;
	}
	var parameter = {
			iconUrl:PATH+imgUrl,
			markerW:markerW,
			markerH:markerH,
			lon:lon,
			lat:lat,
			markerId:id+'_search',
			data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:typeName+"："+name,viewUrl:viewUrl,type:type}
		}
	if($("#peripheryType").val()=="person"){
		$.extend(parameter,{
			data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:typeName+"："+name,viewUrl:viewUrl,type:type,idCardNo:idCardNo}
		});
	}else if($("#peripheryType").val()=="dustbin"){
		$.extend(parameter,{
			data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:typeName+": "+dustbinCode,viewUrl:viewUrl,type:type}
		});
	}else if($("#peripheryType").val()=="publicSecurity"){
		$.extend(parameter,{
			data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:typeName+": "+code,viewUrl:viewUrl,type:type}
		});
	}
	$("#map").TqMap("addMarker",parameter);
	//注册marker点击事件
	$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickbyType});
	registerMarkerMouseoverAndMouseoutEvent();
}

function showPopupbyVideo(name,address){
	$("#commonDialog").createDialog({
		width: 850,
		height: 560,
		title:"实时视频播放",
		zIndex:99999,
		url:"${path}/openLayersMap/gisHeader/maintainVideoDlg.jsp",
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
</script>