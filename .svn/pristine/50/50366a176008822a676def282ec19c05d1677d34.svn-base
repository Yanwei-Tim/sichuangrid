<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialogeeform" title="系统插件设置" class="container container_24" style="overflow:auto;padding-top:25px;height:380px">
<a href="javascript:;" id="addJmsMessageItem">+增加</a>
    <ul id="jmsMessageItems">
	    <s:iterator value = "jmsMessages" var = "jm">
	  	  <li style="margin:5px;border:solid 1px;border-color:#D2CCCC;height:95px;">
	    	<form id="maintainForm" method="post" action="${path}/sysadmin/jmsMessageManager/maintainJmsMessage.action" >
	    		<input name="jmsMessage.id" value="${jm.id}" type="hidden"/>
	    		<div class="grid_3 lable-right">
					<label style="font-weight:bold">消息类型：</label>
				</div>
				<div class="grid_5">
					 <select name="jmsMessage.messageType"  style="float:left;margin-top:3px;">
				       <s:iterator value="@com.tianque.jms.constants.JmsMessageType@jmsMessageTypes" >
							<s:if test='#jm.messageType == key'>
				       			<option value="<s:property value='key'/>"  selected="selected" ><s:property value="value"/></option>
				       		</s:if>
				       		<s:else>
				       			<option value="<s:property value='key'/>"><s:property value="value"/></option>
				       		</s:else>
						</s:iterator>
				    </select>
				</div>
				<div style="clear:both"></div>
				<div class="grid_3 lable-right">
					<label style="font-weight:bold">JMS的URL：</label>
				</div>
				<div class="grid_5">
					<input type="text" name='jmsMessage.jmsUrl'class="form-txt" maxlength="50" value="${jm.jmsUrl}" />
				</div>
				<div class="grid_3 lable-right">
					<label style="font-weight:bold">消息队列：</label>
				</div>
				<div class="grid_5">
					<input type="text" name='jmsMessage.jmsQueue' class="form-txt" maxlength="50" value="${jm.jmsQueue}" />
				</div>
				<div class="grid_3 lable-right">
					<label style="font-weight:bold">启用MQ消息中间件：</label>
				</div>
				<div class="grid_2">
					<input type="radio" name="jmsMessage.isSenderMessage" value="true" <s:if test='true==#jm.isSenderMessage'>checked</s:if>/>是
				</div>
				<div class="grid_2">
					<input type="radio" name="jmsMessage.isSenderMessage" value="false" <s:if test='false==#jm.isSenderMessage'>checked</s:if>/>否
				</div>
		    	<div style="clear:both"></div>
		    	<div class="grid_22">
				</div>
		    	<div class="grid_1 lable-right">
					<button type="button" onclick="doAjaxSubmit(this)" style="height:30px;">保存</button>
				</div>
				<div class="grid_1 lable-right">
					<button type="button" class="delJmsMessageItem" style="height:30px;">删除</button>
				</div>
				<div style="clear:both"></div>
		    </form>
		   </li>
	    </s:iterator>
    </ul>
    <div id="appendJmsMessageForm" style="display: none;">
	    	<form id="maintainForm" method="post" action="${path}/sysadmin/jmsMessageManager/maintainJmsMessage.action" >
	    		<input name="jmsMessage.id"  type="hidden"/>
		    	<div class="grid_3 lable-right">
					<label style="font-weight:bold">消息类型：</label>
				</div>
				<div class="grid_5">
					<select name="jmsMessage.messageType"  style="float:left;margin-top:3px;">
				       <s:iterator value="@com.tianque.jms.constants.JmsMessageType@jmsMessageTypes">
				       		<option value="<s:property value='key'/>"><s:property value="value"/></option>
					   </s:iterator>
				    </select>
				</div>
				<div style="clear:both"></div>
				<div class="grid_3 lable-right">
					<label style="font-weight:bold">JMS的URL：</label>
				</div>
				<div class="grid_5">
					<input type="text" name='jmsMessage.jmsUrl'class="form-txt" maxlength="50" />
				</div>
				<div class="grid_3 lable-right">
					<label style="font-weight:bold">消息队列：</label>
				</div>
				<div class="grid_5">
					<input type="text" name='jmsMessage.jmsQueue' class="form-txt" maxlength="50" />
				</div>
				
				<div class="grid_3 lable-right">
					<label style="font-weight:bold">启用MQ消息中间件：</label>
				</div>
				<div class="grid_2">
					<input type="radio" name="jmsMessage.isSenderMessage" value="true"  checked="checked"/>是
				</div>
				<div class="grid_2">
					<input type="radio" name="jmsMessage.isSenderMessage" value="false" />否
				</div>
				<div class="grid_22">
				</div>
		    	<div class="grid_1 lable-right">
					<button type="button" onclick="doAjaxSubmit(this)" style="height:30px;">保存</button>
				</div>
				<div class="grid_1 lable-right">
					<button type="button" class="delJmsMessageItem" style="height:30px;">删除</button>
				</div>
				<div style="clear:both"></div>
		    </form>
    </div>
</div>

<script type="text/javascript" >
$("#addJmsMessageItem").click(function(){
	temp = '<li style="margin:5px;border:solid 1px;border-color:#D2CCCC;height:95px;">'+$("#appendJmsMessageForm").html()+'</li>';
	$("#jmsMessageItems").after(temp);
})
$("#dialogeeform").delegate(".delJmsMessageItem","click",function(){
	var jmsMessageId = $(this).closest("form").find("input[name='jmsMessage.id']").val();
	if(jmsMessageId){
		alert(0)
		$.ajax({
			url:"${path}/sysadmin/jmsMessageManager/deleteJmsMessage.action?jmsMessageId="+jmsMessageId,
			success:function(data){
				if(data == true){
					$.messageBox({message:"删除成功!"});
					return;
				}
			}
		});
	}
	$(this).closest("li").remove();
})

function doAjaxSubmit(bt){
	
	$(bt).closest("form").ajaxSubmit({
	   success: function(data){
		   if(data==null || !data.id){
	         	$.messageBox({message:data,level: "error"});
	      		return;
	      	}
		 	$.messageBox({message:"成功保存插件设置信息!"});
	   },
	   error: function(XMLHttpRequest, textStatus, errorThrown){
	     	$.messageBox({message:"提交错误",level: "error"	});				
	   }
	});
}
</script>