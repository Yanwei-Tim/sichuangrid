<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="gridDiv" style="overflow:hidden;overflow-y:auto;position:relative;">
	<pop:JugePermissionTag ename="updateLeaderTeam">
	<input type="hidden" value="true" id="updateLeaderTeamP"/>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteLeaderTeam">
	<input type="hidden" value="true" id="deleteLeaderTeamP"/>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="sortLeaderTeam">
	<input type="hidden" value="true" id="sortLeaderTeamP"/>
	</pop:JugePermissionTag>
	<div class="gridOrgSelect">
		<jsp:include page="/baseinfo/villageProfile/orgSelectVillageProfile.jsp"/>
	</div>
	
	<div class="mapinfo" style="">
		<div class="map">
			<pop:JugePermissionTag ename="editGrid">
				<div class="mapEdit">
					<a href="javascript:;" id="zoomin"><span class="exitButton"></span>编辑</a>
					<input type="hidden" value="" id="villageInfoId"/>
					<a href="javascript:;" id="zoomDelete"><span class="deleteButton"></span>清除</a>
				</div>
			</pop:JugePermissionTag>	
			<img src="${resource_path}/resource/images/nopic.jpg"  width="240" height="176" id="image" />
		</div>
	</div>
	
	<div class="gridPanel villagePanel">
		<h1 class="infonav-title">辖区概况<pop:JugePermissionTag ename="editGrid"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editNatureGeography"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
		<div class="accdContent ui-widget-border">
			<p class="natureGeography" id="natureGeography_p"></p>
		</div>
	</div>
	<div class="clear"></div>
	<!-- accordion -->
	<div id="bs-accordion" style="padding-bottom:30px;">
		<div>
			<h1 class="infonav-title">基本信息
				<pop:JugePermissionTag ename="editGrid">
				<div class="leaderGroup-buttons">
					<a href="javascript:;" class="base-button" id="editVillageSimple"><span>编辑</span></a>
				</div>
				</pop:JugePermissionTag>
			</h1>
			<div class="accdContent ui-widget-border">
				 <div id="villageProfileBaseInfo">
				    <input type="hidden" id="hide" value=""/>
				 	<ul class="container_24">
				 	    <li class="grid_3 bold">责任区</li><li class="grid_3"><label id="orgName_label" style="margin-left: -10px;"></label></li>
						<li id="gridNo"></li>
						<li class="grid_3 bold">面&nbsp;&nbsp;积</li><li class="grid_3"><label id="acreage_label" style="margin-left: -10px;"></label>㎡</li>
						<li class="grid_3 bold">户数</li><li class="grid_3"><label id="doors_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">居民数</li><li class="grid_3"><label id="villagers_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">党员数</li><li class="grid_3"><label id="villageRingsters_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">居民代表数</li><li class="grid_3"><label id="villageDelegate_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">集体经济年收入</li><li class="grid_3"><label id="hamletEconomyinclude_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">人均年收入</li><li class="grid_3"><label id="individualAverageInclude_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">集体资产</li><li class="grid_3"><label id="villageCollectivityAsset_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">上级领导</li><li class="grid_3"><label id="interzoneLeading_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">挂钩的机关部门</li><li class="grid_3"><label id="department_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">党(总)支部书记</li><li class="grid_3"><label id="villageBuildupSecretary_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">联系电话</li><li class="grid_3"><label id="buildupSecretaryPhone_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">主任</li><li class="grid_3"><label id="villageDirector_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">联系电话</li><li class="grid_3"><label id="villageDirectorPhone_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">信息员A岗</li><li class="grid_3"><label id="informationPersonA_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">联系电话</li><li class="grid_3"><label id="informationPersonAPhone_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">信息员B岗</li><li class="grid_3"><label id="informationPersonB_label" style="margin-left: -10px;"></label></li>
						<li class="grid_3 bold">联系电话</li><li class="grid_3"><label id="informationPersonBPhone_label" style="margin-left: -10px;"></label></li>
					</ul>
				 </div>
				 <div id="villageProfileBaseInfo2">
				 	<div  class="content">
						<div style="width: 100%;">
							<table id="servicePersonList"></table>
							<div id="servicePersonListPager" style="width: 100%;"></div>
						</div>
						<div id="servicePersonDialog">
						</div>
					</div>
				 </div>
			</div>
		</div>
		<div>
			<h1 class="infonav-title" id="leaderGroupHeader">辖区领导班子介绍<pop:JugePermissionTag ename="addLeaderTeam"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="add"><span>新增</span></a></div></pop:JugePermissionTag><span></span></h1>
			<div class="accdContent ui-widget-border">
				<ul class="leaderGroup" id="leaderGroup">
				
				</ul>
			</div>
		</div>
	</div>
</div>
<div id="villageProfileDialog"></div>
<div id="leaderTeamMaintanceDialog"></div>
<script type="text/javascript">
var flag=false;
function DrawImage(ImgD){
	var image=new Image();
	image.src=ImgD.src;
	if(image.width>0 && image.height>0){
		flag=true;
	}
}
function editImg(fieldCh, fieldEn){
	return {
		width: 470,
		height: 230,
		title:"编辑"+fieldCh,
		url:"${path}/baseinfo/villageProfile/getVillageProfileAndOrgById.action?mode="+fieldEn+"&organization.id="+getCurrentOrgId(),
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
		url:"${path}/baseinfo/villageProfile/getVillageProfileAndOrgById.action?mode="+fieldEn+"&organization.id="+getCurrentOrgId(),
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
function onOrgChanged(orgId){
	$("#gridIntroductionManagement").removeClass("click");
	$("#gridIntroductionManagement").removeClass("accordion-click");
	if(isCountryDownOrganization()){
		if(isGrid()){
			asyncMiddleLevelOpen($("#gridIntroductionManagement"),'${path}/baseinfo/villageProfile/gridProfileComplete.jsp');
		}else{
			asyncMiddleLevelOpen($("#gridIntroductionManagement"),'${path}/baseinfo/villageProfile/villageProfileComplete.jsp');
		}
	}else{
		asyncMiddleLevelOpen($("#gridIntroductionManagement"),'${path}/baseinfo/villageProfile/introduction.jsp');
	}
}
var positionPanel=function(){
	var portletNum=parseInt((document.documentElement.clientWidth-250)/350);
	var sortTableWidth=(document.documentElement.clientWidth-250)/portletNum;
	var sortTable=$("#leaderGroup li");
	sortTable.each(function(i){
		$(this).width(sortTableWidth-16);
	});
	$("#leaderGroup").height("auto").height($("#leaderGroup").height());
}
function addLeaderTeam(){
	$("#leaderTeamMaintanceDialog").createDialog({
		width:500,
		height:405,
		title:'新增领导班子成员',
		modal : true,
		url:"${path}/baseinfo/villageProfile/dispatchLeaderOperate.action?mode=add",
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
			url:"${path}/baseinfo/villageProfile/getVillageProfileAndOrgById.action",
			data:{
				"organization.id":getCurrentOrgId(),
				"mode":"complete"
			},
			success:function(responseData){
				if(responseData.imgUrl==null || responseData.imgUrl==""){
					$("#image").attr("src",function(){return "${path}"+"/"+"resource/images/nopic.jpg"});
				}else{
					$("#image").attr("src",function(){return "${path}"+"/"+responseData.imgUrl});
				}
				$("#villageInfoId").val(responseData.id);
				$("#orgName_label").append(responseData.organization.orgName);
				$("#acreage_label").append(responseData.acreage);
				$("#hide").val(responseData.gridNum);
				$("#doors_label").append(responseData.doors );
				$("#villagers_label").append(responseData.villagers);
				$("#villageRingsters_label").append(responseData.villageRingsters );
				$("#villageDelegate_label").append(responseData.villageDelegate );
				$("#hamletEconomyinclude_label").append(responseData.hamletEconomyinclude);
				$("#individualAverageInclude_label").append(responseData.individualAverageInclude );
				$("#villageCollectivityAsset_label").append(responseData.villageCollectivityAsset );
				$("#interzoneLeading_label").append(responseData.interzoneLeading );
				$("#department_label").append(responseData.department );
				$("#villageBuildupSecretary_label").append(responseData.villageBuildupSecretary);
				$("#buildupSecretaryPhone_label").append(responseData.buildupSecretaryPhone );
				$("#villageDirector_label").append(responseData.villageDirector );
				$("#villageDirectorPhone_label").append(responseData.villageDirectorPhone);
				$("#informationPersonA_label").append(responseData.informationPersonA );
				$("#informationPersonAPhone_label").append(responseData.informationPersonAPhone);
				$("#informationPersonB_label").append(responseData.informationPersonB);
				$("#informationPersonBPhone_label").append(responseData.informationPersonBPhone);
				$("#natureGeography_p").append(responseData.natureGeography);
				$("#economyGeography_p").append(responseData.economyGeography);
				$("#communityGeography_p").append(responseData.communityGeography);
				$("#itemEngineering_p").append(responseData.itemEngineering);
			}
		});
	}
	$.ajax({
		url:"${path}/baseinfo/villageProfile/leaderTeamList.action",
		data:{
			"leaderTeams.orgId":getCurrentOrgId()
		},
		success:function(responseData){
			if(responseData){
				$("#leaderGroup").html(responseData);
				introductionHeight();
			}else{
				$.messageBox({message:"添加成功",level:"error"});				
			}
		}
	});
	$.loadingComp("close");//关闭遮障层
	if(!isGrid()){
		if($("#hide").val()=="null"){
			$("#gridNo").html("<li class='grid_3 bold'>网格数</li><li class='grid_3'><label id='gridNum_label' style='margin-left: -10px;'></label></li>");
		}else{
			$("#gridNo").html("<li class='grid_3 bold'>网格数</li><li class='grid_3'><label id='gridNum_label' style='margin-left: -10px;'>"+$("#hide").val()+"</label></li>")
		}
	}
	$("#editVillageSimple").click(function(event){
		$("#villageProfileDialog").createDialog({
			width: 700,
			height: 420,
			title:"编辑基本信息",
			url:"${path}/baseinfo/villageProfile/getVillageProfileAndOrgById.action?mode=editBaseVillageProfile&organization.id="+getCurrentOrgId(),
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
			editImg('图片', 'zoomin')
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
					url:"${path}/baseinfo/villageProfile/deletePhotosAndOrgById.action?villageProfile.id="+$("#villageInfoId").val()+"&villageProfile.imgUrl="+imgSrc+"&organization.id="+getCurrentOrgId(),
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
			editText('辖区概况', 'editNatureGeography')
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
			url:"${path}/baseinfo/villageProfile/leaderSort.action",
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
})
</script>