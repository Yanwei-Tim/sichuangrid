<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainForm" method="post" action="">
		<pop:token></pop:token>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>所属层级：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="keyWord.org.orgName" class="form-txt"
				value="${keyWord.org.orgName}" readonly /> <input type="hidden"
				name="keyWord.org.id" class="form-txt" value="${keyWord.org.id}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>微信号：</label>
		</div>
		<div class="grid_19">
			<select name="keyWord.weChatUserId" class='form-txt' id="weChatUserId"
				style="width: 510px" readonly >
				<option value="请选择微信号">请选择微信号</option>
				<s:iterator value="listTencentUser">
				<s:if test="#request.weChatUserId==#request.keyWord.weChatUserId">
					<option value="${weChatUserId }" selected>${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
						${name}]</option>
				</s:if>
				<s:else>
				   <option value="${weChatUserId }">${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
						${name}]</option>
				</s:else>
				</s:iterator>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>关键词：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="keyWord.keyName" id="keyName" value="${keyWord.keyName}"
				class='form-txt  {required:true,maxlength:3,isHasKeyWord:true,messages:{required:" 请输入关键字",maxlength:$.format("关键字最多可以输入{0}个字符"),isHasKeyWord:"该关键词已经存在"}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>关键词描述：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="keyWord.keyRemark" value="${keyWord.keyRemark}"
				class='form-txt {required:true,maxlength:10,messages:{required:" 请输入关键词描述",maxlength:$.format("关键词描述最多可以输入{0}个字符")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材类型：</label>
		</div>
		<div class="grid_19">
		<input  type="hidden" name="keyWord.sourceTypeDict.id" value="${keyWord.sourceTypeDict.id}"/>
		<input type="text" class='form-txt' readonly value='<pop:PropertyDictViewTag defaultValue="${keyWord.sourceTypeDict.id}" name="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE"></pop:PropertyDictViewTag>'/>
		
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材编号：</label>
		</div>
		<div class="grid_19">
			<select name="keyWord.sourceId" id="SourceId"  class="form-txt" style="width: 510px" readonly
				onchange="changeSourceId(value)">
				<option value="请选择素材编号">请选择素材编号</option>
				<s:iterator value="listWeChatSources">
					<s:if test="#request.id==#request.keyWord.sourceId">
						<option value="${id }" selected>${id}[${sourceDescription}]</option>
					</s:if>
					<s:else>
					   <option value="${id }">${id}[${sourceDescription}]</option>
					</s:else>
				</s:iterator>
			</select>
			<s:iterator value="listWeChatSources">
				<span class="sourceDescriptions" id="${id}">${sourceDescription}</span>
			</s:iterator>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材描述：</label>
		</div>
		<div class="grid_19">
			<input name="keyWord.sourceDescription" id="keyWordSourceDescription" value="${keyWord.sourceDescription}" readonly
				class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<input type="hidden" id="mode" name="mode" value="${mode}"/>
		<input type="hidden" id="keyWordId" name="keyWord.id" value="${keyWord.id}"/>
	</form>

</div>
<div id="selectPersonDialog"></div>
<script type="text/javascript">
	$(".sourceDescriptions").hide();
	function changeSourceId(value) {
		$(".sourceDescriptions").each(function() {
			var id = $(this).attr("id");
			if (value == "请选择素材编号" || value == null) {
				$("#keyWordSourceDescription").val("");
			}
			if (id == value) {
				$("#keyWordSourceDescription").val($(this).text());
			}
		})
	}
	
	$(document).ready(function() {
		//检验关键词重复
		jQuery.validator.addMethod("isHasKeyWord", function(value, element){
			var flag=false;
			if($("#weChatUserId").val()!="请选择微信号"||$("#weChatUserId").val()!=""){
				$.ajax({
					async:false,
					url:"${path}/keyWordManage/validateKeyWord.action?keyWord.weChatUserId="+$("#weChatUserId").val()+"&keyWord.keyName="+$("#keyName").val(),
					success:function(responseData){
					    if(responseData!=null){
					      if("edit" == $("#mode").val()){
								if($("#weChatUserId").val()==responseData["weChatUserId"]&&$("#keyName").val()==responseData["keyName"]&&$("#keyWordId").val()==responseData["id"]){
									flag=true;
								}
							}
						}
						if(responseData==null){
							flag=true;
						}
					}
				});
			}
			return flag;
		});
		
		<s:if test='"edit".equals(mode)'>
    	  $("#maintainForm").attr("action", "${path}/keyWordManage/updateKeyWord.action");
		</s:if>
		<s:else>
		$("#maintainForm").attr("action", "${path}/keyWordManage/addKeyWord.action");
		</s:else>
		//提交
		$("#maintainForm").formValidate({
			promptPosition : "bottomLeft",
			submitHandler : function(form) {
				if($("#weChatUserId").val()=="请选择微信号"||$("#weChatUserId").val()==""){
					$.messageBox({level: "warn",message : "请选择要绑定的微信号"});
					return false;
					}
				if($("#SourceId").val()=="请选择素材编号"||$("#SourceId").val()=="")
					{	
					$.messageBox({level: "warn",message : "请选择要绑定的素材编号"});
					return false;
					}
				$(form).ajaxSubmit({
					success : function(data) {
						if (data ==true||data=="true") {
							$("#keyWordMaintanceDialog").dialog("close");
							<s:if test='"edit".equals(mode)'>
							$.messageBox({message : "修改成功!"});
							</s:if>
							<s:else>
							$.messageBox({message : "添加成功!"});
							</s:else>
							$("#keyWordList").trigger("reloadGrid");
						} else {
							$.messageBox({
								message : data,
								level : "error"
							});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
				});
			}
		});

	});
</script>


