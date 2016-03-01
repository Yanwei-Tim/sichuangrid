<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="housePropertyDialog"></div>
<div id="permanentResidentDialog"></div>
<div id="keyPlaceDialog"></div>
<div id="peripheryDialog"></div>
<div id="issueDialog"></div>
<div id="publicSecurityDialog"></div>

<div>
	<input type="hidden" value="" name="elements" id="elements">
	<input type="hidden" value="" name="range" id="range">
	<input type="hidden" value="" name="idCardNo" id="idCardNo"><!-- 保存最后点击marker的idCardNo 用于周边搜索重新打上marker -->
	<input type="hidden" value="" name="id" id="id"><!-- 保存最后点击marker的id 用于周边搜索重新打上marker -->
	<input type="hidden" value="" name="type" id="type"><!-- 保存最后点击marker的type 用于周边搜索重新打上marker -->
	<input type="hidden" value="" name="placeType" id="placeType">
	<input type="hidden" value="" name="mode" id="mode">
	<input type="hidden" value="0" name="person.length" id="person-length">
	<input type="hidden" value="" name="featureId" id="featureId" />
	<input type="hidden" value="" name="centerLon" id="centerLon" /><!-- 保存最后点击marker的centerlon 用于周边搜索重新打上marker -->
	<input type="hidden" value="" name="centerLat" id="centerLat" /><!-- 保存最后点击marker的centerLat 用于周边搜索重新打上marker -->
	<input type="hidden" value="place" id="peripheryType"/><!-- 查询状态  是查询 place 还是 person -->
	<input type="hidden" value="" id="title"><!-- 保存最后点击marker的title 用于周边搜索重新打上marker -->
	<input type="hidden" value="" id="querySurroundStr">
</div>

<script type="text/javascript">

function getKeyPlace(id,type,viewUrl){
	var url =viewUrl+id;
	$("#keyPlaceDialog").createDialog({
		width:950,
		height:600,
		title:"查看信息",
		url:'${path}'+url,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}

function getHouseProperty(id,viewUrl){
	if(viewUrl=="" || viewUrl==null || viewUrl==undefiend){
		return;
	}
	url=viewUrl+id;
	$("#housePropertyDialog").createDialog({
		width:950,
		height:620,
		title:"查看住房信息",
		url:'${path}'+url,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}

function getPermanentResident(id){
	
	url = '<s:property value="@com.tianque.openLayersMap.util.GisGlobalValueMap@KEY_MODULE_MAP.get(@com.tianque.openLayersMap.util.GisGlobalValueMap@KEY_POPULATION_DETAIL)"/>'+id;
	
	url =  url.replaceall("&amp;", "&");
	
	$("#permanentResidentDialog").createDialog({
		width:950,
		height:620,
		title:"查看人员信息",
		url:'${path}'+url,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}

function searchPeriphery(elements,range,lon,lat){
	IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
	var elementsCheck="";
	var rangeCheck="";
	var queryStr = $("#searchValue").val();
	$(".typelistManage:visible .multiselect").each(function(){
		if($(this).val()){
			elementsCheck =elementsCheck + $(this).val().join(",")+",";
		}
		if(elementsCheck.indexOf("SKYNET")==0 || elementsCheck.indexOf("BAYONET")==0
				|| elementsCheck.indexOf("SNAPSHOTSYSTEM")==0){
			$("#peripheryType").attr("value","publicSecurity");
		}
	})
	for(i=0;i<range.length;i++){
        if(range[i].checked==true){
        	rangeCheck = range[i].value;
        }
    }
	
	if(elementsCheck=="" || rangeCheck==""){
		$.messageBox({level:'error', message:"请选择要素和范围！"});
		return;
	}
	if(isTransformat){
		rangeCheck = (Math.floor((eval(rangeCheck)/deltLen))+1)+"";//25D距离与实际距离的换算
	}
	$("#map").TqMap("removeAllFeatures");
	$("#map").TqMap("drawCircle",{lon:lon,lat:lat,redius:rangeCheck});
	var url='';
	if($("#mode").val() == "<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERSON_MODE'/>"){
		$('#type').val("<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERIPHERY_PLACE_SERVICE'/>");
	}else if($("#mode").val() == "<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PLACE_MODE'/>"){
		$('#type').val("<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERIPHERY_PERSON_SERVICE'/>");
	}
	$("#querySurroundStr").val(queryStr)
	loadTabListInfo("${path}/openLayersMap/information/surroundingSearchResult.jsp?elements="+elementsCheck+"&range="+rangeCheck+"&functionType="+searchFunctionType+"&queryStr="+encodeURI(encodeURI(queryStr))+"&lon="+lon+"&lat="+lat);
	
}

function peripheryMarker(data){
	$("#map").TqMap("deletePopup");
	if(data!=null && data.rows!=null){
		var data=data.rows;
		var name;
		for(var i=0;i<data.length;i++){
			var row = TQConvert.toLonlat(data[i]);
			var imgUrl="../resource/openLayersMap/images/defaultMarker.gif";
			var mouseoverImgUrl="../resource/openLayersMap/images/defaultMarker-2.gif";
			var markerW=28;
			var markerH=31;
			var markerIdTemp;
			if(row.typeName=="监控电子眼"){
				imgUrl="../resource/openLayersMap/images/video/video_off.png";
				mouseoverImgUrl="../resource/openLayersMap/images/video/video_on.png";
				markerW=24;
				markerH=42;
			}
			//因为新的单位场所id有加密做特殊处理 markerIdTemp
			if($("#peripheryType").val()=="place" ||"dustbin"==$("#peripheryType").val()){
				markerIdTemp= row.encryptId+'_search';			
			}else{
				markerIdTemp=row.id+'_search';
			}
			var parameter = {
					iconUrl:PATH+imgUrl,
					markerW:markerW,
					markerH:markerH,
					lon:row.lon,
					lat:row.lat,
					markerId:markerIdTemp,
					data:{typeName:row.typeName,imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:row.typeName+"："+row.name,viewUrl:row.viewUrl,type:row.type}
				}
			if($("#peripheryType").val()=="person"){
				$.extend(parameter,{
					data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:row.typeName+"："+row.name,viewUrl:row.viewUrl,type:row.type,idCardNo:row.idCardNo}
				});
			}else if($("#peripheryType").val()=="dustbin"){
				$.extend(parameter,{
					data:{typeName:row.typeName,imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:row.typeName+"："+row.dustbinCode,viewUrl:row.viewUrl,type:row.type}
				});
			}else if($("#peripheryType").val()=="publicSecurity"){
				$.extend(parameter,{
					data:{typeName:row.typeName,imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:row.typeName+"："+row.code,viewUrl:row.viewUrl,type:row.type}
				});
			}
			$("#map").TqMap("addMarker",parameter);
			//注册marker点击事件
			$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickbyType});
			registerMarkerMouseoverAndMouseoutEvent();
		}
	}
	
}


//重点人员marker点击事件
function markerClickByPerson(e){
	var id=e.object.markerId.split("_search")[0];
	var viewUrl=e.object.data.viewUrl;
	var url=viewUrl+id;
	$("#housePropertyDialog").createDialog({
		width:950,
		height:620,
		zIndex:1007,
		title:"查看信息",
		url:url,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}

function markerClickbyType(e){
	var typeName=e.object.data.typeName;
	var id=e.object.markerId.split("_search")[0];
	var viewUrl=e.object.data.viewUrl;
	var url=viewUrl+id;
	if(typeName!="监控电子眼"){
		$("#keyPlaceDialog").createDialog({
			width:920,
			height:550,
			title:"查看信息",
			url:url,
			zIndex:9999,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
	}else{
		url='/gis/twoDimensionMapDetailViewCommonManage/getDetailViewPopupInfoByIdAndType.action?tableName=dustbin'+'&modeType=cityComponentsMapDetailViewService'+'&functionType=refineSearch'+'&isVideo=true'+'&id='+id+"&orgId="+$('#currOrgId').val()
		$("#keyPlaceDialog").createDialog({
			width:800,
			height:560,
			zIndex:9999,
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

String.prototype.replaceall=function(s1,s2){
	var demo=this;
	while(demo.indexOf(s1)!=-1)
		demo=demo.replace(s1,s2);
	return demo;
}

function tablist1(that){
	detailsTabChange(that,1);
	$("#peripheryType").attr("value","place");
	$(".searchStr").html("请输入(名称/地址)：");
	$("#search").html("场所搜索");
}
function tablist2(that){
	detailsTabChange(that,2);
	$("#peripheryType").attr("value","person");
	$(".searchStr").html("请输入(姓名/身份证号码)：");
	$("#search").html("人员搜索");
}

function tablist3(that){
	detailsTabChange(that,3);
	$("#peripheryType").attr("value","dustbin");
	$(".searchStr").html("请输入(部件名称/地址)：");
	$("#search").html("搜索");
}

function detailsTabChange(that,index){
	$(".typelistManage").hide();
	$(".typelistManage").each(function(i){
		if(index==i+1) $(this).show();
	})
	$(".typelistManage :input").attr("checked",false);
	$(that).parent().addClass("current").siblings().removeClass("current");
}
</script>