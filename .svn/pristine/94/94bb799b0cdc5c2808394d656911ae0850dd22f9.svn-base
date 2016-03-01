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
	
	<div class="mapinfo">
		<div class="map">
			
			<pop:JugePermissionTag ename="editGrid">
				<div class="mapEdit">
					<a href="javascript:;" id="zoomin"><span class="exitButton"></span>编辑</a>
					<input type="hidden" value="" id="villageInfoId"/>
					<a href="javascript:;" id="zoomDelete"><span class="deleteButton"></span>清除</a>
				</div>
			</pop:JugePermissionTag>
			<img src="${resource_path}/resource/images/nopic.jpg"  width="240" height="176" id="image" onload='javascript:DrawImage(this);' />
		</div>
	</div>
	<div class="gridPanel villagePanel">
		<h1 class="infonav-title">辖区概况<pop:JugePermissionTag ename="editGrid"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editIntroduction"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
		<div class="accdContent ui-widget-border">
			 <p class="natureGeography" id="maintainIntroduction_p"></p>
		</div>
	</div>
	<div class="clear"></div>
	<div id="bs-accordion">
		<div>
			<h1 class="infonav-title" id="leaderGroupHeader">辖区领导班子介绍<pop:JugePermissionTag ename="addLeaderTeam"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="add"><span>新增</span></a></div></pop:JugePermissionTag></h1>
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
var positionPanel=function(){
	var portletNum=parseInt((document.documentElement.clientWidth-260)/350);
	var sortTableWidth=(document.documentElement.clientWidth-260)/portletNum;
	var sortTable=$("#leaderGroup li");
	sortTable.each(function(i){
		$(this).width(sortTableWidth-16);
	});
	$("#leaderGroup").height("auto").height($("#leaderGroup").height());
}
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
		url:"${path}/baseinfo/villageProfile/getVillageProfileAndOrgById.action?mode="+fieldEn+"&organization.id="+getCurrentOrgId()+"&dialogName=villageProfileDialog",
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
		width: 520,
		height: 550,
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
			//	alert(thisId+"|"+thisIndex);
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
	if($("#sortLeaderTeamP").val()){
		$("#leaderGroup").sortable(dfop).disableSelection();
	}
	$(".mapinfo").hover(function(){
		$(".mapEdit").show();
	},function(){
		$(".mapEdit").hide();
	})
	$.ajax({
		async:false,
		url:"${path}/baseinfo/villageProfile/getIntroductionAndOrgById.action",
		data:{
			"organization.id":getCurrentOrgId(),
			"mode":"upGrids"
		},
		success:function(responseData){
			if(responseData.imgUrl==null || responseData.imgUrl==""){
				$("#image").attr("src",function(){return "${path}"+"/"+"resource/images/nopic.jpg"});
			}else{
				$("#image").attr("src",function(){return "${path}"+"/"+responseData.imgUrl});
			}
			$("#villageInfoId").val(responseData.id);
			$("#maintainIntroduction_p").append(responseData.introduction);
			$("#natureGeography_p").append(responseData.natureGeography);
			$("#economyGeography_p").append(responseData.economyGeography);
			$("#communityGeography_p").append(responseData.communityGeography);
			$("#itemEngineering_p").append(responseData.itemEngineering);
			$("#villageProfileName").text(responseData.organization.orgName);
		}
	});
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
	$("#editIntroduction").click(function(event){
		$("#villageProfileDialog").createDialog(
			editText('辖区信息', 'editIntroduction')
		);
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
				height:'auto',
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