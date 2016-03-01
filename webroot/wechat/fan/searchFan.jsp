<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<div class="grid_4 lable-right">
		<label>微信号：</label>
	</div>
	<div class="grid_19">
		<select class='form-txt' id="search_weChatUserId"
			style="width: 475px" readonly>
			<option value="请选择微信号">请选择微信号</option>
			<s:iterator value="listTencentUser">
				<option value="${weChatUserId }">${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
					${name}]</option>
			</s:iterator>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label>所属分组：</label>
	</div>
	<div class="grid_19">
		<select class='form-txt' id="search_groupId"
			style="width: 475px;overflow-y:auto;" readonly >
			<option value="请选择所属分组">请选择所属分组</option>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label>粉丝昵称：</label>
	</div>
	<div class="grid_19">
		<input type="text"  id="search_name" class='form-txt' />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label>备注名：</label>
	</div>
	<div class="grid_19">
		<input type="text"  id="search_nickName" class='form-txt' />
	</div>
	<div class='clearLine'>&nbsp;</div>
</div>

<script type="text/javascript">
$(function(){
	$("#search_weChatUserId").change(function(){
		var option="<option value='请选择所属分组'>请选择所属分组</option>";
		var weChatUserId=$(this).val();
		if(weChatUserId=="请选择微信号"){
			$('#search_groupId').html(option);
		}else{
			$.ajax({
				url: '${path}/fanManage/getweChatGroupByWeChatUserId.action?fan.weChatUserId='+weChatUserId,
				success : function(data) {
					if(data!=null&&data!=""){
						var options="";
						 $.each(data,function(i,v){
							options+="<option value="+data[i].groupId+">"+data[i].name+"</option>"
						 });
						$('#search_groupId').html(options);
					}else{
						$.messageBox({message:"该微信号暂无分组",level: "error"});
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
		     		$.messageBox({message:"提交错误",level: "error"});
		      	}
			});
		}
	})
})

</script>

