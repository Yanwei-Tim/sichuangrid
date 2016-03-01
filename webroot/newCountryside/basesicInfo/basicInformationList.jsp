<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-corner-all" id="nav">
	<div class="btnbanner btnbannerData">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
	</div>
</div>
<div class="gridDiv" style="overflow:hidden;overflow-y:auto;position:relative;">
	<div class="mapinfo" style="">
		<div class="map">
				<div class="mapEdit">
					<a href="javascript:;" id="zoomin"><span class="exitButton"></span>编辑</a>
					<input type="hidden" value="" id="basicInfoMationId"/>
					<a href="javascript:;" id="zoomDelete"><span class="deleteButton"></span>清除</a>
				</div>
			<img src="${resource_path}/resource/images/nopic.jpg"  width="240" height="176" id="image" />
		</div>
	</div>
	
	<div class="gridPanel villagePanel">
		<h1 class="infonav-title"><span id="orgIntroduction"></span><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editNatureGeography"><span>编辑</span></a></div></h1>
		<div class="accdContent ui-widget-border">
			<p class="natureGeography" id="natureGeography_p"></p>
		</div>
	</div>
	<div class="clear"></div>
	<!-- accordion -->
	<div id="bs-accordion" style="padding-bottom:30px;">
<!-- 		<div> -->
<%-- 			<h1 class="infonav-title"><span id="basicinfomation"></span> --%>
<%-- 				<pop:JugePermissionTag ename="editGrid"> --%>
<!-- 				<div class="leaderGroup-buttons"> -->
<%-- 					<a href="javascript:;" class="base-button" id="editVillageSimpleInfo"><span>编辑</span></a> --%>
<!-- 				</div> -->
<%-- 				</pop:JugePermissionTag> --%>
<!-- 			</h1> -->
<!-- 			<div class="accdContent ui-widget-border"> -->
<!-- 				 <div id="villageProfileBaseInfo"> -->
<!-- 				    <input type="hidden" id="hide" value=""/> -->
<!-- 				 	<ul class="container_24"> -->
<!-- 				 		<li class="grid_3 bold">名称</li><li class="grid_3"><label id="thisOrgName" style="margin-left: -10px;"></label></li> -->
<!-- 				 		<li class="grid_3 bold">地理位置</li><li class="grid_3"><label id="geographicalPosition" style="margin-left: -10px;"></label></li> -->
<!-- 				 		<li class="grid_3 bold">气候</li><li class="grid_3"><label id="climate" style="margin-left: -10px;"></label></li> -->
<!-- 				 		<li class="grid_3 bold">总面积</li><li class="grid_3"><label id="totalArea" style="margin-left: -10px;"></label></li> -->
<!-- 				 		<li class="grid_3 bold">耕地面积</li><li class="grid_3"><label id="cultivatedLandArea" style="margin-left: -10px;"></label></li> -->
<!-- 				 		<li class="grid_3 bold">林地面积</li><li class="grid_3"><label id="woodlandArea" style="margin-left: -10px;"></label></li> -->
<!-- 				 		<li class="grid_3 bold">荒地面积</li><li class="grid_3"><label id="wastelandArea" style="margin-left: -10px;"></label></li> -->
<!-- 				 		<li class="grid_3 bold">海拔(米)</li><li class="grid_3"><label id="altitude" style="margin-left: -10px;"></label></li> -->
<!-- 				 		<li class="grid_3 bold">土壤(酸/碱)</li><li class="grid_3"><label id="soilProperties" style="margin-left: -10px;"></label></li> -->
<!-- 					</ul> -->
<!-- 				 </div> -->
<!-- 				 <div id="villageProfileBaseInfo2"> -->
<!-- 				 	<div  class="content"> -->
<!-- 						<div style="width: 100%;"> -->
<!-- 							<table id="servicePersonList"></table> -->
<!-- 							<div id="servicePersonListPager" style="width: 100%;"></div> -->
<!-- 						</div> -->
<!-- 						<div id="servicePersonDialog"> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				 </div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div>
			<h1 class="infonav-title"><span id="leaderGroupHeader"></span><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="add"><span>新增</span></a></div></h1>
			<div class="accdContent ui-widget-border" style="min-height: 195px;">
				<ul class="leaderGroup" id="leaderGroup">
				
				</ul>
			</div>
		</div>
	</div>
</div>
<div id="villageProfileDialog"></div>
<div id="leaderTeamMaintanceDialog"></div>
<script type="text/javascript">
var positionPanel=function(){
	var portletNum=parseInt((document.documentElement.clientWidth-250)/350);
	var sortTableWidth=(document.documentElement.clientWidth-250)/portletNum;
	var sortTable=$("#leaderGroup li");
	sortTable.each(function(i){
		$(this).width(sortTableWidth-16);
	});
	$("#leaderGroup").height("auto").height($("#leaderGroup").height());
}
getCurrentOrgName();
var flag=false;
function DrawImage(ImgD){
	var image=new Image();
	image.src=ImgD.src;
	if(image.width>0 && image.height>0){
		flag=true;
	}
}

/* function updateLeaderTeam(id){
	if(id==null || id==""){
		$.messageBox({message:"编辑失败，未获得正确参数",level:"error"});
		return;
	}
	$("#leaderTeamMaintanceDialog").createDialog({
		width:500,
		height:405,
		title:'编辑领导班子成员',
		modal : true,
		url:"${path}/baseinfo/basicInfomationManage/dispatchLeaderOperate.action?mode=edit&newVillageLeaderTeam.id="+id,
		buttons :{
			"保存" : function(){
			   $("#updateDetailsForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function deletLeaderTeam(id){
	if(id==null || id==""){
		$.messageBox({message:"删除，未获得正确参数",level:"error"});
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除，该成员数据将不能恢复，继续删除？",
		okFunc: function(){
			$.ajax({
				url:'${path}/baseinfo/basicInfomationManage/deleteLeaderTeamById.action',
				type:"post",
				data:{
					"id":id
				},
				success:function(data){
					var leaderId="leaderInfo"+id;
					$("#"+leaderId).remove();
				    $.messageBox({message:"已经成功删除该成员信息!"});
			    }
		    });
	   }
 	});
} */
function getCurrentOrgName(){
	$.ajax({
		async:false,
		url:"${path}/sysadmin/orgManage/getSimpleOrgById.action",
		data:{
			"id":getCurrentOrgId()
		},
		success:function(responseData){
			$("#orgIntroduction").text(responseData.orgName+"基本情况简介");
			$("#leaderGroupHeader").text(responseData.orgName+"领导班子介绍");
			$("#basicinfomation").text(responseData.orgName+"基础信息");
			$("#thisOrgName").text(responseData.orgName);
		}
	});
	
}
function editImg(fieldCh, fieldEn){
	return {
		width: 470,
		height: 230,
		title:"编辑"+fieldCh,
		url:"${path}/baseinfo/basicInfomationManage/disptchOperate.action?mode="+fieldEn+"&organization.id="+getCurrentOrgId()+"&basicInfoMation.id="+$("#basicInfoMationId").val(),
		buttons:{
			"保存" : function(event){
   				$("#maintainForm").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	}
}

function editText(fieldCh, fieldEn){
	return {
		width: 600,
		height: 400,
		title:"编辑"+fieldCh,
		url:"${path}/baseinfo/basicInfomationManage/disptchOperate.action?mode="+fieldEn+"&organization.id="+getCurrentOrgId(),
		buttons:{
			"保存" : function(event){
   				$("#maintainForm").submit();
	   		},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	}
}
function addLeaderTeam(){
	$("#leaderTeamMaintanceDialog").createDialog({
		width:500,
		height:405,
		title:'新增领导班子成员',
		modal : true,
		url:"${path}/baseinfo/basicInfomationManage/dispatchLeaderOperate.action?mode=add",
		buttons :{
			"保存" : function(){
			   $("#updateDetailsForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
$(function(){
	var timer;
	var dfop={
		delay: 200,
		stop:function(event,ui){
			var ajaxData=[];
			$("#leaderGroup li").each(function(i){
				var thisId=$(this).data("id");
				var thisIndex=i;
				ajaxData.push({id:thisId,index:thisIndex});
			});
			var param = {};   
			for(var i=0;i<ajaxData.length;i++){
			 param["leaderTeamList[" + i + "].id"] = ajaxData[i].id;
			 param["leaderTeamList[" + i + "].sort"] = ajaxData[i].index;   
			}  
			leaderSort(param);
		}
	};
	introductionHeight();
	$(".mapinfo").hover(function(){
		$(".mapEdit").show();
	},function(){
		$(".mapEdit").hide();
	})
	if($("#sortLeaderTeamP").val()){
		 $("#leaderGroup").sortable(dfop).disableSelection();
	}
	$("h1.ui-state-hover").first().css("border-top-width","1px");
	var option={tab:"ul.infonav>li",box:".infobox div",hover:"ui-state-highlight"}
	$.tab(option);
	
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		$.ajax({
			async:false,
			url:"${path}/baseinfo/basicInfomationManage/getBasicInfomationByOrgId.action",
			data:{
				"organization.id":getCurrentOrgId()
			},
			success:function(responseData){
				if(responseData!=null && responseData!=""){
					if(responseData.imgUrl==null || responseData.imgUrl==""){
						$("#image").attr("src",function(){return "${path}"+"/"+"resource/images/nopic.jpg"});
					}else{
						$("#image").attr("src",function(){return "${path}"+"/"+responseData.imgUrl});
					}
					$("#basicInfoMationId").attr("value",responseData.id);
					$("#natureGeography_p").append(responseData.basicIntroduction);
					$("#geographicalPosition").append(responseData.geographicalPosition);
					$("#climate").append(responseData.climate);
					$("#totalArea").append(responseData.totalArea);
					$("#cultivatedLandArea").append(responseData.cultivatedLandArea);
					$("#woodlandArea").append(responseData.woodlandArea);
					$("#wastelandArea").append(responseData.wastelandArea);
					$("#altitude").append(responseData.altitude);
					$("#soilProperties").append(responseData.soilProperties);
				}
			}
		});
	}
	$.ajax({
		url:"${path}/baseinfo/basicInfomationManage/getTeamListByOrgId.action",
		data:{
			"organization.id":getCurrentOrgId()
		},
		success:function(responseData){
			if(responseData){
				$("#leaderGroup").html(responseData);
				introductionHeight();
			}else{
				$.messageBox({message:"添加失败",level:"error"});				
			}
		}
	});
	$.loadingComp("close");//关闭遮障层
	
	$("#editVillageSimpleInfo").click(function(event){
		$("#villageProfileDialog").createDialog({
			width: 600,
			height: 420,
			title:"编辑基本信息",
			url:"${path}/baseinfo/basicInfomationManage/disptchOperate.action?mode=basicInfomationEdit&organization.id="+getCurrentOrgId(),
			buttons:{
				"保存" : function(event){
	   				$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#zoomin").click(function(event){
		$("#villageProfileDialog").createDialog(
			editImg('图片', 'photoEdit')
		);
	});
	$("#zoomDelete").click(function(event){
		var imgSrc = $("#image").attr("src");
		if( imgSrc == "${resource_path}/resource/images/nopic.jpg"){
			$('#villageProfileDialog').html('<div style="text-align:center;height:60px;vertical-align: middle"><font size="3px">没有可供清除的图片！</font></div>');
			$('#villageProfileDialog').dialog({ 
				title:"清除图片",
				resizable:false,
				modal:true,
				width:300,
				height:160,
				buttons:{ "关闭": function() { $(this).dialog("close"); } }
			 });
			return ;
		}
		$.confirm({
			title:"确认清除",
			message:"你确定要清除此图片吗？",
			okFunc: function(){
				$.ajax({
					async:false,
					url:"${path}/baseinfo/basicInfomationManage/deleteVillagePhoto.action?basicInfoMation.id="+$("#basicInfoMationId").val()+"&basicInfoMation.imgUrl="+imgSrc+"&organization.id="+getCurrentOrgId(),
					success:function(data){
						if(data.imgUrl == null){
						$("#image").empty().attr("src",function(){return "${path}"+"/"+"resource/images/nopic.jpg"});
					    $.messageBox({message:"已经成功清除该图片！"});
					    return true;
						}
						$.messageBox({message:"清除该图片失败！"});
						return false;
					}
				});
			}
		});
	});
	$("#editNatureGeography").click(function(event){
		$("#villageProfileDialog").createDialog(
			editText('辖区概况', 'basicInfoMationEditNature')
		);
		return false;
	});
	$("#add").click(function(event){
	    addLeaderTeam();
	    return false;
	});
	$(window).resize(function(){
		clearTimeout(timer);
		timer=setInterval(function(){introductionHeight()},200);
	})
	$("#leaderGroup li").die('mouseover').live('mouseover',function(){
		$(this).find(".leaderGroup-buttons").show();
	});
	$("#leaderGroup li").die('mouseout').live('mouseout',function(){
		$(this).find(".leaderGroup-buttons").hide();
	});
	function introductionHeight(){
		$(".gridDiv").height($(".ui-layout-center").height()-$("#thisCrumbs").height()-10);
		positionPanel();
	}
	function leaderSort(ajaxData){
		$.ajax({
			url:"${path}/baseinfo/basicInfomationManage/leaderSort.action",
			data:ajaxData,
			success:function(responseData){
				if(responseData){
					$.messageBox({message:"排序成功"});	
				}else{
					$.messageBox({message:"排序失败",level: "error"});	
				}
			}
		});
	}
});
</script>