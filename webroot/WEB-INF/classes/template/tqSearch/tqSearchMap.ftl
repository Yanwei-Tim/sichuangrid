<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="gisDialogLay">
	<div class="gisMap" id="searchMap"></div>
	<div class="gisRight">
		<div class="gisSearch">
			<form id="tqSearchMapForm">
				<input type="hidden" name="type" id="mapSearchType">
				<input type="hidden" name="tqSearchVo.searchs.lon" value="<@s.property value='#parameters.lon'/>">
				<input type="hidden" name="tqSearchVo.searchs.lat" value="<@s.property value='#parameters.lat'/>">
				<input type="hidden" name="tqSearchVo.orgId" value="<@s.property value='#parameters.orgId'/>">
				<span>区域范围:</span>
				 <select name="tqSearchVo.searchs.range" class="gisSelect" id="mapSearchRange">
				    <option value="100">100米</option>
				    <option value="200">200米</option>
				    <option value="500">500米</option>
				    <option value="1000">1公里</option>
				    <option value="2000">2公里</option>
				 </select>
			 </form>
		</div>
		<div class="gisRightTab">
			<ul id="gisTabType">
			    <@s.iterator value="@com.tianque.plugin.tqSearch.constants.TqSearchType@mapSearchType" id="st">
						<li><a href="javascript:;" key="${key}">${value}</a></li>
				</@s.iterator>
			</ul>
		</div>
		<div class="gisRightMainLay" id="tqSearchMapList">
				<!--内容块 -->
		</div>
		<div class="zjsPage" id="tqSearchMapListPager">
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	var parameters={
		id:"<@s.property value='#parameters.id'/>",
		type:"<@s.property value='#parameters.type'/>",
		lon:"<@s.property value='#parameters.lon'/>",
		lat:"<@s.property value='#parameters.lat'/>",
		featureId:"<@s.property value='#parameters.featureId'/>"
	}
	initMap();
	function initMap(){
		$("#searchMap").TqMap({
				isShowM2D: true,						            //是否加载二维地图
				isShowM3D: true,						            //是否加载三维地图
				isShowMS: true,						            //是否加载卫星地图
				isShowWFS: false,						            //是否加载热区
				gisType:"2D",
				zoom:6
		});
		$("#searchMap").TqMap("addGisTypeControl",{
			gisType:"2D",
			showType:["2D","3D"],
			changingFunc:function(){
				var imap = $("#searchMap").TqMap("get","map");
				if(TQMap.isTransformat==true){
					var lonlat =  imap.getCenter();
					if(gisType=="3D" && targitGisType!="3D"){
						lonlat = TQTransformat.to2DPoint(lonlat);
					}else if(gisType!="3D" && targitGisType=="3D"){
						lonlat = TQTransformat.to25DPoint(lonlat);
					}
					$("#searchMap").TqMap("setCenter",{lon:lonlat.lon,lat:lonlat.lat,zoom:imap.zoom});
				}
			}
		})
		if(parameters.lon && parameters.lon>0){
			$("#searchMap").TqMap("setCenter",{lon:parameters.lon,lat:parameters.lat});
			$("#searchMap").TqMap("addMarker",{
				iconUrl: "/resource/openLayersMap/images/bubble.png",
				markerW:24,
				markerH:27,
				lon:parameters.lon,
				lat:parameters.lat,
				data:{id:parameters.id},
				onClick:function(marker){
					var urlInfo = TQSearch.getDetailUrl(parameters.type);
					TQSearch.showDetailInfo($.extend({
						id:parameters.id
					},urlInfo));
				}
			})
		}else{
			$.messageBox({level:"info",message:"数据经纬度为空"});
		}
	}
	
	$("#tqSearchMapList").delegate("#mapRowTitle","click",function(){
		var $li = $(this).parents("li");
		var urlInfo = TQSearch.getDetailUrl($li.attr("type"));
		urlInfo.url = urlInfo.url
				.replace("{{key}}",$li.attr("key"));
		TQSearch.showDetailInfo($.extend({
			id:$li.attr("id")
		},urlInfo));
	})
	
	$("#tqSearchMapList").delegate("#mapRowGoTo","click",function(){
		var $li = $(this).parents("li");
		var lon = $li.attr("lon");
		var lat = $li.attr("lat");
		var markerId = $li.attr("dbId");
		var imap = $("#searchMap").TqMap("get","map");
		$("#searchMap").TqMap("setCenter",{lon:lon,lat:lat,zoom:imap.zoom});
		var markerLayer = $("#searchMap").TqMap("get","markers");
		markerLayer.markers.forEach(function(marker){
			marker.icon.imageDiv.style.zIndex = markerLayer.getZIndex();
			if(marker.markerId == markerId){
				marker.icon.imageDiv.style.zIndex += 1;
			}
		})
	})
	
	$(".gisRightTab li").click(function(){
		if(parameters.lon && parameters.lon>0){
			$(".gisRightTab li").removeClass("on")
			$(this).addClass("on");
			var key = $(this).find("a").attr("key");
			$("#mapSearchType").val(key);
			submitMapForm();
		}
	}).eq(0).click();
	
	$("#mapSearchRange").change(function(){
		submitMapForm();
	})
	
	function submitMapForm(){
		$("#searchMap").TqMap("removeAllFeatures");
		if(!parameters.lon){
			$.messageBox({level:"info",message:"数据经纬度为空"});
			return;
		}
		var range = $("#mapSearchRange").val();
		$("#searchMap").TqMap("drawCircle",{
			lon:parameters.lon,
			lat:parameters.lat,
			redius:Math.round(Math.sqrt(2*Math.pow(range,2)))
		});
		var searchType = $("#gisTabType li.on a").attr("key");
		TQSearch.submitMapForm({
				templateId:searchType+"MapTPL",
				success:function(data){
					if(!data || !data.result){
						return;
					}
					var list = data.result;
					$("#searchMap").TqMap("clearMarkersLikeMarkerId","rowData");
					for(var i=0;i<list.length;i++){
						var obj = list[i];
						$("#searchMap").TqMap("addMarker",{
							iconUrl:getIconUrl("/resource/openLayersMap/images/blueBubble",i),
							markerW:24,
							markerH:27,
							lon:obj.lon,
							lat:obj.lat,
							markerId:obj.id,
							isOnMouseOverAndOut:true,
							data:{id:obj.keyId,type:obj.dataType},
							onClick:function(marker){
								$("li[dbId='"+marker.markerId+"'] a#mapRowTitle").click()
							}
						})
					}
				}
			});
	}
})
</script>

