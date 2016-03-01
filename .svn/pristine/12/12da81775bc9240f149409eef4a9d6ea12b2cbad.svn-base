<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div  class="container container_24">
	<input id="weChatUserIds" type="hidden" name="weChatUserId" value="${tencentUser.weChatUserId}"  />
	<div id="personSelectBox">
		<div class="personSelectMenus" id="personSelectMenus">
			<ul>
				<li><a href="javascript:;"><span class="icon-zn"></span>粉丝列表</a></li>
				<li><a href="javascript:;"><span class="icon-my"></span>我的群组</a></li>
			</ul>
		</div>
		<%-- 粉丝列表 --%>
		<div class="personSelectPanel">
				<div class="grid_24 departmentBox">
					<h3>&nbsp&nbsp&nbsp&nbsp请选择对象：</h3>
				</div>
				<div class='clearLine'>&nbsp;</div>
			<div id="fanList">
				<ul id="fan_ul" class="orgLevelBox" ></ul>
			</div>
		</div>
		<%-- 我的群组 --%>
		<div class="personSelectPanel" >
			<s:if test="weChatGroupList.size()>0">
				<div>
					<div id="myGroup" style="cursor: pointer;margin-top: 5px;">
						<span class="ui-icon ui-icon-triangle-1-s" style="float: left;"></span>我的群组(<s:property value="weChatGroupList.size()" />)
					</div>
					<ul class="orgLevelBox" id="myGroupList">
						<s:iterator value="weChatGroupList">
							<li >
								<label><input type="checkbox" id="group_${groupId}" value="${groupId}" name="${name}(群)">${name}(群)</label>
							</li>
						</s:iterator>
					</ul>
				</div>
			</s:if>
			<s:else>
				<font color="red">暂无群组</font>
			</s:else>
		</div>
		<div class="personSelectedBox">
			<div id="rightList" class="orgLevelContactersList" >
				<h3>已选择对象：</h3><ul></ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
/****************************************粉丝列表***************************************************/
//加载粉丝列表数据
function getFanListData(weChatUserId){
	$.ajax({
		async:false,
		url:"${path}/tencentUserManage/getFanList.action",
		data:{"tencentUser.weChatUserId":weChatUserId},
		success:function(data){
			showWorkContactsList(data);
		}
	});
};
//显示粉丝列表
function showWorkContactsList(data){
	$("#fanList>ul").empty();
	if(data==null||data==''){
		return ;
	}
	var userReciverIds = $("#userReceivers").val().split(",");
	if(data.length > 0 || userReciverIds.length > 0 ){
		$("#fanList>ul").append(
			'<li><label><input type="checkbox"  id="selectAllFan" />全选</label></li>'
		);
		for(var i=0;i<data.length;i++){
			$("#fanList>ul").append(
				'<li>' +
					'<label><input type="checkbox" name="'+data[i].name+'" value="'+data[i].openId+'" id="fan_'+data[i].openId+'" />'+ 
					data[i].name+
					'</label>'+
				'</li>'
			);
			var exist=$.inArray("fan_"+data[i].openId+'',userReciverIds);
			if(exist>=0){
				//判断该联系人是否已经被选择/如果是就将其选中
				
				$('#fan_'+data[i].openId).attr("checked",true);
				//判断该联系人是否已加到 已选择对象中去 
				if($("#rightList ul li[id='"+data[i].openId+"']").length==0){
					$("#rightList ul").prepend('<li id="fan_'+data[i].openId+'">'+data[i].name+'<a href="javascript:;" class="delete">删除</a></li>');
				}
			}
		}
		isSelectAllFanCheckButton();
	}
}


//点击站内联系人或我的群组的checkbox
function clickFanOrGroupCheckbox(checkbox) {
	if (checkbox.attr("checked")) {
		isSelectAllFanCheckButton();
		var value="";
		if(checkbox.attr("id").indexOf("group_")!=-1){
			$("#rightList ul").prepend('<li id="group_'+checkbox.val()+'">'+checkbox.attr("name")+'<a href="javascript:;" class="delete">删除</a></li>');
			value="group_"+checkbox.val();
		}
		else {
			$("#rightList ul").prepend('<li id="fan_'+checkbox.val()+'">'+checkbox.attr("name")+'<a href="javascript:;" class="delete">删除</a></li>');
			value="fan_"+checkbox.val();
		}
		$("#userReceivers").data(value,checkbox.attr("name"));
		$("#userReceivers").setPersonnelCompleteVal({
			value : value,
			label : checkbox.attr("name"),
			desc : ""
		});
		var a= checkbox.attr("name").substring(checkbox.attr("name").length-3,checkbox.attr("name").length);
		if(a=="(群)")
			deletePersonOrGroup("fan");
		else 
			deletePersonOrGroup("group");
	}else{
		var value="";
		if(checkbox.attr("id").indexOf("group_")!=-1){
		  $("#rightList ul li:[id='group_"+checkbox.val()+"']").remove();
		  value="group_"+checkbox.val();
		}
		else{
		  $("#rightList ul li:[id='fan_"+checkbox.val()+"']").remove();
		  value="fan_"+checkbox.val();
		
		}
		$("#selectAllFan").attr("checked",false);
		$("#userReceivers").removeData(+value);
		$("#userReceivers").removePersonnelCompeleteValue(value);
	}
}
//选中全部站内联系人
function selectAllFans(selectAllCheckbox){
	var isChecked=selectAllCheckbox.attr("checked");
	$("#fan_ul li input[id!='"+selectAllCheckbox.attr("id")+"']").each(function(){
		if(isChecked){
			if($(this).attr("checked")==undefined){
				 $(this).attr("checked",true);
				 clickFanOrGroupCheckbox($(this));
			}
		}else{
			if($(this).attr("checked")){
				 $(this).attr("checked",false);
				 clickFanOrGroupCheckbox($(this));
			}
		}
	});
}
//删除组或是粉丝（不能同时选择两种类型发送）
function deletePersonOrGroup(type)
{
		   $("#rightList ul li").each(function(){
				if($(this).attr("id").indexOf(type)==0)
				{
					var leftId=$(this).attr("id")
					if(type=="fan")
						{
					     $("#fan_ul li input").each(function()
							{
					    	
						          if(leftId==$(this).attr("id"))
						        	  {
						        		$("#userReceivers").removeData($(this).attr("id"));
						        		$("#userReceivers").removePersonnelCompeleteValue($(this).attr("id"));
						        	    $(this).attr("checked",false);
						        	    return false;
						        	  }
							});
						}
					else {
						$("#myGroupList li input").each(function()
								{
							          if(leftId==$(this).attr("id"))
							        	  {
							         		$("#userReceivers").removeData($(this).attr("id"));
							        		$("#userReceivers").removePersonnelCompeleteValue($(this).attr("id"));
							        	    $(this).attr("checked",false);
							        	    return false;
							        	  }
								});
					}
					$(this).remove();
				}
		   });
}
//判断站内联系人的全选按钮是否应该被选中
function isSelectAllFanCheckButton(){
	var isChecked=true;
	$("#fan_ul li:gt(0) input").each(function(){
		if($(this).attr("checked")==undefined){
			isChecked=false;
			return;
		}
	});
	$("#selectAllFan").attr("checked",isChecked);
}



/**************************************************************************************************************/
$(function(){
	//加载选项卡
	$("#personSelectMenus li").click(function(){
		var thisIndex=$(this).index();
		$(".personSelectPanel").eq(thisIndex).show().siblings(".personSelectPanel").hide();
		$(this).addClass("cur").siblings().removeClass("cur");
	}).eq(0).click();
	//加载粉丝列表
	getFanListData($("#weChatUserIds").val());
	//遍历我的群组 当该群组已经被选择 就选择该群组
	var userReciverIds = $("#userReceivers").val().split(",");
	$("#myGroupList li label input").each(function(){
		var exist=$.inArray("group_"+$(this).val(),userReciverIds);
		if(exist>=0){
			$(this).attr("checked",true);
			if($("#rightList ul li[id='"+$(this).val()+"']").length==0){
				$("#rightList ul").prepend('<li id="group_'+$(this).val()+'">'+$(this).attr("name")+'<a href="javascript:;" class="delete">删除</a></li>');
			}
		}
	});

	//全选站内联系人
	$("#fan_ul").delegate("#selectAllFan","click",function(){
		selectAllFans($(this));
	});
	//选择单个站内联系人
	$("#fan_ul").delegate("li input[id!='selectAllFan']","click",function(){
		clickFanOrGroupCheckbox($(this));
	});
	//选择群组
	$("#myGroupList li input").click(function(){
		clickFanOrGroupCheckbox($(this));
	});
	//从已选择对象中删除已选择的收件人
	$("#rightList").delegate("a","click",function(){
		var id =$(this).parent().attr("id");
		var checkbox= $("#"+id);
			//当该站内联系人不在当前所选择部门下显示时,直接从收件人栏里删除
			if(checkbox.length==0){
				$("#userReceivers").removePersonnelCompeleteValue(id);
			}else{
				checkbox.attr("checked",false);
				clickFanOrGroupCheckbox(checkbox);
			}
		$(this).parent().remove();
	});
	
	//显示或隐藏群组
	$("#myGroup").click(function(event){
		var uiSpan=$(this).find("span:first");
		if(uiSpan.hasClass("ui-icon-triangle-1-s")){
			uiSpan.removeClass("ui-icon-triangle-1-s").addClass("ui-icon-triangle-1-e");
			$(this).parent().find("ul").hide();
		}else{
			uiSpan.removeClass("ui-icon-triangle-1-e").addClass("ui-icon-triangle-1-s");
			$(this).parent().find("ul").show();
		}
	});
});

</script>
