<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
    <s:if test='"add".equals(mode)'>
        <form id="maintainForm" action="${path }/sysadmin/proclamationManage/addProclamation.action" method="post">
    </s:if>
    <s:if test='"edit".equals(mode)'>
        <form id="maintainForm" action="${path }/sysadmin/proclamationManage/updateProclamation.action" method="post">
    </s:if>
        <input type="hidden" id="mode" name="mode" value="${mode }"/>
        <input type="hidden" id="proclamationId" name="proclamation.id" value="${proclamation.id }"/>
        <div class="grid_3"><em class="form-req">*&nbsp;</em>公告内容：</div>
        <div class="grid_21" style="display:inline;float:left;height:280px;">
        	<s:if test='"view".equals(mode)'>
        		<div><s:property value="proclamation.content" escape="false"/></div>
	        </s:if>
	        <s:else>
	        	<textarea class="form-txt" name="proclamation.content" id="proclamation_content" rows="18" cols="78" maxlength="700" style="height:270px" >${proclamation.content}</textarea>
	        </s:else>
	    </div>
	    <div class="grid_3">&nbsp;</div>
	    <div class="grid_5">
	       <input type="checkbox"  name="proclamation.display" id="display" value="true" <s:if test='proclamation.display || "add".equals(mode)'>checked</s:if> <s:if test='"view".equals(mode)'>disabled</s:if>/>是否显示
	    </div>
	    
	    <div class="grid_6">
	       <input type="checkbox"  name="proclamation.pcusable" id="pcusable" value="true" <s:if test='proclamation.pcusable || "add".equals(mode)'>checked</s:if> <s:if test='"view".equals(mode)'>disabled</s:if>/>是否pc端接收
	    </div>
	    
	    <div class="grid_6">
	       <input type="checkbox"  name="proclamation.mobileusable" id="mobileusable" value="true" <s:if test='proclamation.mobileusable'>checked</s:if> <s:if test='"view".equals(mode)'>disabled</s:if>/>是否手机端接收
	    </div>
	    <div class="grid_4" id="nowNum">已经输入<strong>0</strong>个字符</div>
    <s:if test='mode != null && !"view".equals(mode)'>
         </form>
    </s:if>
</div>

<script type="text/javascript">
$(document).ready(function(){
    	<s:if test='!"view".equals(mode)'>
            //$("#content").richText({tools:'mini'});
            //$('#proclamation_content').richText();
            var nowNum = $("#nowNum").find("strong");
            $('#proclamation_content').focus().bind("keyup",function(){
                var a = (this.value || "").match(/[\u4e00-\u9fa5_a-zA-Z0-9]/g);
                nowNum.html(a ? a.length : 0 );
            });
            <s:if test='"edit".equals(mode)'>
            	nowNum.html($('#proclamation_content').val().length);
            </s:if>
        </s:if>
        $("#maintainForm").formValidate({
        	promptPosition:"bottomLeft",
        	submitHandler:function(form){
        	    $(form).ajaxSubmit({
            	    success:function(data){
            	   var displayResult = data.display;
	        	    	if(!data.id){
	                        $.messageBox({
	                            message:data,
	                            level:"error"
	                        });
	                        return;
	                    }
	                    <s:if test='"add".equals(mode) || "copy".equals(mode)'>
	                		$("#proclamationList").addRowData(data.id,data,"first");
	                        $.messageBox({message:"成功保存新增系统公告信息！"});
	                    </s:if>
	                    <s:if test='"edit".equals(mode)'>
	                        $("#proclamationList").setRowData(data.id,data);
	                        $.messageBox({message:"成功保存系统公告修改信息！"});
	                    </s:if>
	                    $("#proclamationList").trigger("reloadGrid");
	                    $("#proclamationList").setSelection(data.id);
	                   if(!displayResult){
	                	   $("#announcement").hide();
		               }
        	        },
        	        error:function(XMLHttpRequest,textStatus,errorThrown){
            	        $.messageBox({message:"提交错误！"});
        	        }
            	});
                $("#proclamationDialog").dialog("close");
        	},
        	rules:{
    			"proclamation.content": {
    				required: true,
    				minlength: 1,
    				maxlength: 500
    			}
    		},
    		messages: {
    			"proclamation.content": {
    				required: "请输入公告内容!",
    				minlength  : $.format("至少需要{0}个字符"),
    				maxlength  : $.format("不能大于{0}个字符")
    			}
    		}
        });
    });
//-->
</script>