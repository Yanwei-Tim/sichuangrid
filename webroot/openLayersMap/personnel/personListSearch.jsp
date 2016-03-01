<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:set var="currentTime" value="@java.lang.System@currentTimeMillis()"/>
<div class="searchResult" style="overflow: hidden;">
	<div class="resultNum"><span class="plaint" title="清空"><a id="emptyPersonSearchMapAndList" href='javascript:void(0)' >【清空】</a></span>共有<span id="personListTotal">0</span>条搜索记录</div>
	<div class="resultCon">
		<div id="personList"></div>
	</div>
	<input type="hidden" id="buildingId" value="" />
</div>
<div id="personListPager" class="pagination"></div>
<input type="hidden" value="<s:property value='#parameters.type' />" id="keyPersonType" />

<script type="text/javascript">
var personListPrevsPageInfo=null;

$(function(){
	viewPersonLayerInformation();

	$("#emptyPersonSearchMapAndList").click(function(){
		$("#queryString").val("请输入(姓名/身份证号码/地址)");
		$("#map").TqMap("deletePopup");
		clearMarkersByMarkerId_objectName("person");
		$("#personListTotal").html(0);
		$("#emptyPersonSearchMapAndList").hide();
		$("#personList").nextAll().remove();
		$("#personListPager").empty();
	});
	
	function viewPersonLayerInformation(){
		var screen = $("#map").TqMap("getScreenExtent");
		var minX = screen.minLon;
		var minY = screen.minLat;
		var maxX = screen.maxLon;
		var maxY = screen.maxLat;
		var orgId=$('#currOrgId').val();
		clearMapAllMarkerAndPopup();
		if(isTownDownOrganization()){
			$("#map").TqMap("clearMarkers");
			viewTownAbovePersonLayerInformation(minX,minY,maxX,maxY,orgId,$("#queryString").val());
		}else{
			viewTownUnderPersonLayerInformation(minX,minY,maxX,maxY,orgId,$("#queryString").val());
		}
	}
	
	function viewTownAbovePersonLayerInformation(minX,minY,maxX,maxY,orgId,queryStr){
		$.ajax({
			url:'${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndSearchValue.action',
			async:false,
			data:{
				"organization.id":orgId,
				"screenCoordinateVo.minLon":minX,
				"screenCoordinateVo.maxLon":maxX,
				"screenCoordinateVo.minLat":minY,
				"screenCoordinateVo.maxLat":maxY,
				"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PERSON_STATISTIC'/>",
	            "searchValue":queryStr
			},
			success:function(data){
				var str="<tr class='keyPopulation'><td class='msgL'><span class='text'>所属辖区</td><td class='msgL'><span class='text'>绑定数</td>";
				for(var i=0;i<data.length;i++){
					str+="<tr class='keyPopulation' onclick='detailedListingClick("+data[i].organization.id+")'><td class='msgL'><span class='text'>"+data[i].organization.orgName+"</span></td><td class='dataPoint'><span class='num'>"+data[i].boundMapNum+"</span></td></tr>";
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
						 $(pop.contentDiv).attr("title",data[i].organization.orgName+" 常住人口数量："+data[i].boundMapNum);
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
	
	function viewTownUnderPersonLayerInformation(minX,minY,maxX,maxY,orgId,queryStr){
		$.ajax({
			url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue.action',
			async:false,
			data:{
				"screenCoordinateVo.minLon":minX,
				"screenCoordinateVo.maxLon":maxX,
				"screenCoordinateVo.minLat":minY,
				"screenCoordinateVo.maxLat":maxY,
				"searchValue":queryStr,
				"organization.id":orgId,
				"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PERSON_SEARCH'/>",
				"page":1,
				"rows":1000000,
				"sidx":"centerlon",
	            "sord":"asc"
			},
			success:function(data){
				if(data.records>0){
					var rows=data.rows;
					for(var i =0 ;i<rows.length;i++){
						if(rows[i].lat!=null&&rows[i].lon!=null){
							var imgUrl="../resource/openLayersMap/images/bubble.png";
							var mouseoverImgUrl="../resource/openLayersMap/images/bubble_GLUE.fw.png";
							$("#map").TqMap("addMarker",{
								iconUrl:PATH + imgUrl,
								markerW:15,
								markerH:20,
								lon:rows[i].lon,
								lat:rows[i].lat,
								markerId:rows[i].id+"_person",
								data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"姓名："+rows[i].name,idCardNo:rows[i].idCardNo}
							});
							$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickByPerson});
							registerMarkerMouseoverAndMouseoutEvent();
						}
					}
				}
			}
		});
		$("#personList").GisList({
			url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue.action',
			datatype: "json",
			data:{
            	"screenCoordinateVo.minLon":minX,
				"screenCoordinateVo.maxLon":maxX,
				"screenCoordinateVo.minLat":minY,
				"screenCoordinateVo.maxLat":maxY,
				"searchValue":queryStr,
				"organization.id":orgId,
				"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PERSON_SEARCH'/>",
				"page":1,
				"rows":10,
				"sidx":"centerlon",
	            "sord":"asc"
			},
			ajaxLoad:personListNextPage,//翻页方法
			rowFormatter:function(row,rowNumber){
				showDetailsList();
				var thisRow=$("<li id='"+row.id+"'/>");
				if(row!=null){
					var num="";
					var name="";
					var btnList="";
					var type="'person'";
					var gender= genderFormate(row.gender.id);
					var idCardNo=$("<div />").html("<strong>身份证号码:</strong>"+row.idCardNo);
					var address=$("<span />").html("<strong>住&nbsp;&nbsp;&nbsp;址:</strong>"+row.address);
					if(null != row.lon&&null != row.lat) {
						num = $("<div />").html("<span>"+getBubble(rowNumber++)+"</span>").addClass("resAbc");
						name = $("<div class='name'/>").html('<a href="javascript:void(0);" onclick="showPersonPopup('+row.lon+','+row.lat+',\''+row.idCardNo+'\','+type+','+row.id+','+null+');">'+row.name+'('+gender+')</a>');
						btnList = $("<div />").addClass("btnList").append(address);
					}else{
						num = $("<div />").html("<span></span>").addClass("resNum");
						name = $("<div class='name'/>").html("<a>"+row.name+"("+gender+")</a>");
					}
					thisRow.append(num).append(name).append(idCardNo).append(btnList).append(btnList);
				}
				return thisRow;
			}
		});
		
	}
	
	
	function personListNextPage(data){
		if(data.records>0){
			var rows=data.rows;
			if(personListPrevsPageInfo!=null && personListPrevsPageInfo!="undefined" && personListPrevsPageInfo!=""){
				for(var j = 0; j < personListPrevsPageInfo.length; j++){
					$("#map").TqMap("deleteMarkerByMarkerId",{markerId:personListPrevsPageInfo[j].id+"_person"});
					var imgUrl="../resource/openLayersMap/images/bubble.png";
					var mouseoverImgUrl="../resource/openLayersMap/images/bubble_GLUE.fw.png";
					$("#map").TqMap("addMarker",{
						iconUrl:PATH + imgUrl,
						markerW:15,
						markerH:20,
						lon:personListPrevsPageInfo[j].lon,
						lat:personListPrevsPageInfo[j].lat,
						markerId:personListPrevsPageInfo[j].id+"_person",
						data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"姓名："+personListPrevsPageInfo[j].name,idCardNo:personListPrevsPageInfo[j].idCardNo}
					});
					$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickByPerson});
					registerMarkerMouseoverAndMouseoutEvent();
				}
		 	}
			personListPrevsPageInfo=rows;
			for(var i =0 ;i<rows.length;i++){
				if(rows[i].lon!=null&&rows[i].lat!=null){
					var imgUrl = getBubbleUrl(i);
					var mouseoverImgUrl=getMouseoverBubbleUrl(i);
					$("#map").TqMap("deleteMarkerByMarkerId",{markerId:rows[i].id+"_person"});
					$("#map").TqMap("addMarker",{
						iconUrl:PATH + imgUrl,
						markerW:20,
						markerH:25,
						lon:rows[i].lon,
						lat:rows[i].lat,
						markerId:rows[i].id+"_person",
						data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"姓名："+rows[i].name,idCardNo:rows[i].idCardNo}
					});
					$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickByPerson});
					registerMarkerMouseoverAndMouseoutEvent();
				}
			}
		}
	}
	
});

//显示popup
function showPersonPopup(centerLon,centerLat,idCardNo,mode,id,type){
	$("#mode").val("<s:property value='@ com.tianque.openLayersMap.util.GisGlobalValueMap@PERSON_MODE'/>");
	var lonlat={lon:centerLon,lat:centerLat};
	$("#id").val(id);
	$('#centerLon').val(centerLon);
	$('#centerLat').val(centerLat);
	$("#type").val(type);
	$("#idCardNo").val(idCardNo);
	$("#map").TqMap("addPopup",{
		url:PATH+"/gis/commonGisManage/getPopulationByIdCardNo.action?mode="+mode
			+"&id="+id+"&type="+type+"&showPeriphery="+true
			+"&idCardNo="+idCardNo,
		lon:centerLon,
		lat:centerLat,
		lonlat:lonlat,
		popupW:360,
		popupH:280
	});
}

//重点人员marker点击事件
function markerClickByPerson(e){
	var mode = 'person';
	var centerLon=e.object.lonlat.lon;
	var centerLat=e.object.lonlat.lat;
	var lonlat={lon:centerLon,lat:centerLat};
	var id=e.object.markerId.split("_")[0];
	var idCardNo=e.object.data.idCardNo;
	$("#mode").val("<s:property value='@ com.tianque.openLayersMap.util.GisGlobalValueMap@PERSON_MODE'/>");
	$("#id").val(id);
	$('#centerLon').val(centerLon);
	$('#centerLat').val(centerLat);
	$("#idCardNo").val(idCardNo);
	$("#type").val(type);
	$("#map").TqMap("unregisterEvent",{eventName:"click",func:markerClickByPerson});
	$("#map").TqMap("addPopup",{
		url:PATH+"/gis/commonGisManage/getPopulationByIdCardNo.action?mode="+mode
			+"&id="+id+"&type="+null+"&showPeriphery="+true
			+"&idCardNo="+idCardNo,
		lon:centerLon,
		lat:centerLat,
		lonlat:lonlat,
		popupW:360,
		popupH:280
	});
}
	
	function personListByName(pageSize,page){
		var result = null;
		$.ajax({
			async: false,
			datatype: "json",
			url:'${path}/gis/commonOpenLayersManage/searchPersonByNameAndOrgIdForOpenLayers.action',
			data:{
				"name":$("#queryString").val(),
				"orgId":$('#currOrgId').val(),
				"page":page<=0?1:page,
				"rows":pageSize,
				"sidx":"id",
                "sord":"desc"
			},
			success:function(data){
				result = data;
			}
		});
		return result;
	}
	
//性别判断	
function genderFormate(genderId){
	var genderName = null;
	$.ajax({
		async: false,
		url: "${path }/sysadmin/propertyManage/getPropertyDictById.action",
		data:{
			"propertyDomain.domainName":"性别",
			"propertyDict.id":genderId
		},
		success:function(responseData){
			genderName=responseData.displayName;
		}
	});
	return genderName;

}
</script>