<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="sel_container">
	<a href="javascript:;" id="globalOrgBtnMod" class="curcity-expand" title="选择组织机构">
		<span id="globalOrgBtn"><strong class="btnMod"></strong></span>
	</a>
</div>

<script>
	$(function(){
		$("#globalOrgBtnMod").click(function(){
			$("#globalOrgBox").createDialog({
				url:'${path}/sysadmin/orgManage/orgSelectComponent.action?id='+$("#currOrgId").val(),
				width:550,
				height:280,
				draggable:false,
				title:'辖区选择',
				buttons: {
					"确定" : function(event){
						afterChangNode($("#orgSelectInput").val());
						$(this).dialog("close");
					},
					"取消" : function(){
						$(this).dialog("close");
					}
				}
			});
		});
	});
		
	function afterChangNode(id){
		//$("#map").TqMap("deletePopup");
		var layerData=getGis2DLayerDataByOrgId(id);
		/*if(isTownDownOrganization()&&IS_MAP_MOVE_SEARCH==true){
			clearMapAllMarkerAndPopup();
		}else{
			$("#map").TqMap("removeAllFeatures");
		}
		if(IS_MAP_MOVE_SEARCH==true){//隐藏辖区分布功能外显示的图列
			$(".gis_zoom_button").addClass("hidden");//清除图例按钮
			$(".gis_zoom_content").hide();//清除图例内容
		}
		if(isTownDownOrganization()&&$("#detailClick").val()!="true"){//如果不是辖区详情点击且是乡镇级别以上则删除建筑物marker
			clearMarkersByMarkerId_objectName("hourse");
		}*/
		if(layerData!=null && layerData!=""){
			$("#map").TqMap("setCenter",{lon:layerData.lon,lat:layerData.lat,zoom:layerData.zoom});
		}else{
			$.messageBox({message:"当前部门还未划分区域!",level:"error"});
		}
		prevsCenterLonLat =$("#map").TqMap("get","map").getCenter();
	}		

</script>
