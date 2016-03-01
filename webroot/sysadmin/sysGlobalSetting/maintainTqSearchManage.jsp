<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="统一搜索设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
	<s:if test='!"view".equals(mode)'>
	    <form id="maintainForm" method="post" action="" >
	</s:if>
			
    <div class="grid_6 lable-right">
		<label >是否启用统一搜索：</label>
	</div>
	<div class="grid_2">
			<input id="isTqSearchOpen" type="radio" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IS_TQSEARCH_OPEN"/>' value="true" <s:if test='"true".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IS_TQSEARCH_OPEN))'>checked</s:if>/>是
		</div>
		<div class="grid_2">
			<input type="radio" name='map.<s:property value="@com.tianque.core.globalSetting.util.GlobalSetting@IS_TQSEARCH_OPEN"/>' value="false" <s:if test='"false".equals(map.get(@com.tianque.core.globalSetting.util.GlobalSetting@IS_TQSEARCH_OPEN))'>checked</s:if>/>否
		</div>
	<s:if test="userName=='longzhendong'">
		<div class="grid_8">
			<button type="submit" style="height:30px;">保存</button>
			<button type="reset" style="height:30px;">重置</button>
		</div>
	</s:if>
   <s:if test='!"view".equals(mode)'>
	   </form>
	</s:if>
</div>
<script type="text/javascript">
var existed = true;
$(document).ready(function(){
	<s:if test='!"view".equals(mode)'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			},
			rules:{
			},
			messages:{
			}
		});
	</s:if>
	$("#maintainForm").attr("action","${path}/sysadmin/globalSettingManage/updateGlobalSetting.action");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
			 $.messageBox({message:"设置成功!"});
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     	$.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}

function doNothing(){}

</script>