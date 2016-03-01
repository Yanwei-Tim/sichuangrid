<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="Gps查看" class="container container_24">
 	<div class="grid_4 lable-right">
		<label class="form-lbl">用户名：</label>
 	</div>
	<div class="grid_7">
		<input type="text" class='form-txt' value="${positioningTrajectory.user.userName}" disabled/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">用户姓名：</label>
 	</div>
	<div class="grid_7">
		<input type="text" class='form-txt' value="${positioningTrajectory.user.name}" disabled/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">部门：</label>
 	</div>
	<div class="grid_7">
		<input type="text" class='form-txt' value="${positioningTrajectory.user.organization.orgName}" disabled/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">工作单位：</label>
 	</div>
	<div class="grid_7">
		<input type="text" class='form-txt' value="${positioningTrajectory.user.workCompany}" disabled/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">手机：</label>
 	</div>
	<div class="grid_7">
		<input type="text" class='form-txt' value="${positioningTrajectory.user.mobile}" disabled/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">工作电话：</label>
 	</div>
	<div class="grid_7">
		<input type="text" class='form-txt' value="${positioningTrajectory.user.workPhone}" disabled/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
<!-- 	<div class="grid_4 lable-right"> -->
<!-- 		<label class="form-lbl">设备类型：</label> -->
<!-- 	</div> -->
<!-- 	<div class="grid_7"> -->
<%-- 		<input type="text" class='form-txt' value="${positioningTrajectory.deviceInformation.deviceType}" disabled/> --%>
<!-- 	</div> -->
<!-- 	<div class="grid_4 lable-right"> -->
<!-- 		<label class="form-lbl">设备编号：</label> -->
<!-- 	</div> -->
<!-- 	<div class="grid_7"> -->
<%-- 		<input type="text" class='form-txt' value="${positioningTrajectory.deviceInformation.deviceNumber}" disabled/> --%>
<!-- 	</div> -->
<!-- 	<div class='clearLine'>&nbsp;</div> -->
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">经度：</label>
 	</div>
	<div class="grid_7">
		<input type="text" class='form-txt' value="${positioningTrajectory.longitude}" disabled/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">纬度：</label>
 	</div>
	<div class="grid_7">
		<input type="text" class='form-txt' value="${positioningTrajectory.latitude}" disabled/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">定位时间：</label>
 	</div>
	<div class="grid_7">
		<input type="text" class='form-txt' value="<s:date name="positioningTrajectory.locateDate" format="yyyy-MM-dd HH:mm:ss"/>" disabled/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right"> 轨迹回放 </div>
	<div class="grid_18"> <hr style="margin-top: 16px;"/> </div>
	
	<form id="maintainForm" method="post" action="/baseinfo/positioningTrajectoryManage/positioningTrajectoryList.action">
		<input type="hidden" name="searchPositioningTrajectoryVo.deviceNumber" value="${positioningTrajectory.deviceNumber }"/>
		<input type="hidden" name="searchPositioningTrajectoryVo.userName" value="${positioningTrajectory.user.userName }"/>
		<input type="hidden" name="sidx" value="locateDate"/>
		<input type="hidden" name="sord" value="desc"/>
		<input type="hidden" name="page" value="1"/>
		<input type="hidden" name="rows" value="3000"/>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">起始时间：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" id="startDate" name="searchPositioningTrajectoryVo.locateDateStart" class='form-txt {required:true,messages:{required:"请输入起始时间"}}' value="" readonly/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">结束时间：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" id="endDate" name="searchPositioningTrajectoryVo.locateDateEnd"  class='form-txt {required:true,messages:{required:"请输入结束时间"}}' value='' readonly/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">速度：</label>
	 	</div>
		<div class="grid_7" >
			<select id="speed" class='form-txt '>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3" selected>3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
		</div>
		<div class="grid_1"></div>
		<div class="grid_2">
			<!--<input type="button" id="submit" class='form-txt' value="查看轨迹"/>-->
			<img alt="路径回放" src="${path }/resource/openLayersMap/images/playback.png" id="submit">
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script type="text/javascript">
var gpsRows = $("#map").data("gpsRows");
var gpsPoints = $("#map").data("gpsPoints");
var gpsIntervalProcess = null;
var gpsSpeed = 200;
var gpsStep = 0.00003;
//var gps2DLayer = getGis2DLayerDataByOrgId("${positioningTrajectory.user.organization.id}")
$(document).ready(function(){
	$("#startDate").dateTimePicker({
		yearRange:"1900:2030",
		timeFormat: 'HH:mm:ss',
		showMinute:true,
		maxDate:'+0d',
	   	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endDate").dateTimePicker("option","minDate",date);
			}
		}
	}); 

	$("#endDate").dateTimePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
		timeFormat: 'HH:mm:ss',
		showMinute:true,
		maxDate:'+0d',
		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#startDate").dateTimePicker("option", "maxDate",date);
			}
		}
	});
	$("#submit").click(function(){
		$("#maintainForm").submit();
	})
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
					if(data!=null && data.rows!=null){
			        	$("#map").TqMap("deleteAllPopupText");
			        	gpsSpeed = (eval($("#speed").val())+1)*50;
			        	clearGpsMapAllMarker();
			        	gpsRows = data.rows;
			        	gpsRows = convertLonLatsByGisType(gpsRows);
						timeLocus();
					}
					$("#"+$("#dialog-form").parent().attr("id")).dialog("close");
			    },
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});

	function timeLocus(){
		if(gpsRows==null || gpsRows.length<1) return;
		IS_MAP_MOVE_SEARCH=false;//轨迹回放过程中，屏蔽滑动搜素
		var zoom = $("#map").TqMap("get","map").zoom;
		var rows = gpsRows.copy();
		$("#map").TqMap("setCenter",{lon:rows[0].longitude,lat:rows[0].latitude,zoom:zoom});
		var now = rows.pop();
		var expectNext = rows[rows.length-1];
		gpsPoints="";
		var index = 1;
		gpsIntervalProcess = setInterval(function(){
			var next = getNextPoint(now,expectNext);
			gpsPoints += next.longitude +" "+next.latitude;
			showRouteFeature(gpsPoints);
			addLocusPersonMarker(next.longitude,next.latitude);
			if(index ==1){
				addLocusPointMarker(42,35,now,"startPoint.png")
				//bindReloadLocusFuncForMarker();
			}
			if(next.longitude == expectNext.longitude && next.latitude == expectNext.latitude){
				//if(gps2DLayer == null || (OpenLayers.Geometry.fromWKT(gps2DLayer.points).getBounds().containsLonLat(new OpenLayers.LonLat(expectNext.longitude,expectNext.latitude)))){
					addLocusPointMarker(10,10,next,"point.png")
				//}else{
				//	addLocusPointMarker(10,10,next,"point_err.png")
				//}
				now = rows.pop();
				expectNext = rows[rows.length-1];
			}
			if(rows.length==0){
				addLocusPointMarker(42,35,now,"endPoint.png")
				var offset = new OpenLayers.Pixel(15, -(23/2))
				addLocusPointMarker(71,23,now,"playback.png",offset,"playback")
				bindReloadLocusFuncForMarker();
				
				clearInterval(gpsIntervalProcess);
				IS_MAP_MOVE_SEARCH=true;//轨迹回放结束，重启滑动搜素
				$("#map").data("gpsPoints",gpsPoints)
				$("#map").data("gpsRows",gpsRows);
			}else{
				gpsPoints += ",";
				now = next;
				index++;
			}
		}, gpsSpeed);
	}
	function bindReloadLocusFuncForMarker(){//绑定轨迹回放事件
		$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:function(e){
			if(gpsRows==null || gpsRows.length<1) return;
			$("#map").TqMap("deleteMarkerByMarkerId",{ markerId:"playback" });
			IS_MAP_MOVE_SEARCH=false;
			clearInterval(gpsIntervalProcess);
			var rows = gpsRows.copy();
			showRouteFeature($("#map").data("gpsPoints"),"#33CCFF");
			var points = "";
			var now = rows.pop();
			var expectNext = rows[rows.length-1];
			gpsIntervalProcess = setInterval(function(){
				var next = getNextPoint(now,expectNext);
				points += next.longitude +" "+next.latitude;
				showRouteFeature(points);
				addLocusPersonMarker(next.longitude,next.latitude);
				if(rows.length==0){
					var offset = new OpenLayers.Pixel(10, -(23/2))
					addLocusPointMarker(71,23,now,"playback.png",offset,"playback")
					bindReloadLocusFuncForMarker();
					
					clearInterval(gpsIntervalProcess);
					IS_MAP_MOVE_SEARCH=true;
				}else{
					points += ",";
					now = next;
					if(next.longitude == expectNext.longitude && next.latitude == expectNext.latitude){
						now = rows.pop();
						expectNext = rows[rows.length-1];
					}
				}
			},gpsSpeed);
		}});
	}
	function getNextPoint(now,next){
		if(now==null || next==null) return now;
		var deltLength = 0.00005;
		if(gpsStep!=null) deltLength = gpsStep;
		var deltLon = eval(next.longitude) - eval(now.longitude);
		var deltLat = eval(next.latitude) - eval(now.latitude);
		var length = Math.sqrt(deltLon*deltLon + deltLat*deltLat);
		if(length>deltLength){
			var lon = eval(now.longitude) + (deltLon/length)*deltLength;
			var lat = eval(now.latitude) + (deltLat/length)*deltLength;
			return {'longitude':lon,'latitude':lat};
		}
		return next;
	}
	function showRouteFeature(points,color){//轨迹的路线
		var strokeC = (color==null)?"#FF0000":color;
		$("#map").TqMap("featureShow",{ points:"LINESTRING("+ points + ")",featureId:"locus_line",fill:false, strokeWidth:3,strokeColor:strokeC});
	}
	function addLocusPointMarker(w,h,row,image,offset,markerId){
		if(offset==null) offset = new OpenLayers.Pixel(-(w/2), -(h/2));
		markerId = (markerId==null)?"locus_point":markerId;
		$("#map").TqMap("addMarker",{
			iconUrl:"${resource_path}/resource/openLayersMap/images/"+image,
			markerW:w,markerH:h, offset:offset, lon:row.longitude, lat:row.latitude, markerId:markerId, data:{title:"时间："+row.locateDate+"  信息："+row.trackInfo}
	 	});
		registerMarkerMouseoverAndOutEvent();
	}
	function addLocusPersonMarker(lon,lat){//轨迹中人的marker
		if(!$("#map").TqMap("get","map").getExtent().contains(lon,lat,false)){
			$("#map").TqMap("setCenter",{lon:lon,lat:lat,zoom:$("#map").TqMap("get","map").zoom});
		}
		$("#map").TqMap("deleteMarkerByMarkerId",{markerId:"locus_person"});
		$("#map").TqMap("addMarker",{
			iconUrl:"${resource_path}/resource/openLayersMap/images/locusPerson.png",
			markerW:32, markerH:50, lon:lon, lat:lat, markerId:"locus_person", data:{title:"轨迹回放"}
	 	});
		bindReloadLocusFuncForMarker();
		registerMarkerMouseoverAndOutEvent();
	}

	function registerMarkerMouseoverAndOutEvent(){
		$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"mouseover",func:function(e){
			$(e.object.events.element).attr("title",e.object.data.title);
		}});

		$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"mouseout",func:function(e){
		}});
	}

	function convertLonLatsByGisType(objs){
		//当前地图不是25D图层时，获取二维的坐标
		if(  typeof gisType !="undefined" && gisType!="3D" && objs!=null){
			for(var i=0;i<objs.length;i++){
				objs[i].longitude = objs[i].longitude2;
				objs[i].latitude = objs[i].latitude2;
			}
		}
		return objs;
	}
})


</script>