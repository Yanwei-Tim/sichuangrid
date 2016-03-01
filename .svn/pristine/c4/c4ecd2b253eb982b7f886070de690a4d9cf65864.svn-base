<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form"  class="container container_24">
	<form id="cleanCacheByKeyForm">
	<div class="grid_7 lable-right">
   		<label class="form-lb1">缓存namespace：</label>
  	</div>
  	<div class="grid_10">
  		<input name="cacheNamespace" class="form-txt" type="text" id="cacheNamespace" >
   	</div>
   	<div class="grid_7">
	   	<div class="btnbanner btnbannerData">
	   		<a id="cleanCache" style="margin-left:10px;" href="javascript:void(0)"><span>清除缓存</span></a>
	   	</div>
	</div>
	</form>
</div>

<script type="text/javascript">
	$("#cleanCache").click(function(){
	if($("#cacheNamespace").val()=="" || $("#cacheNamespace").val()==null ){
		$.messageBox({  level:"warn",message:"缓存namespace不能为空"});
		return;
	}
		$.dialogLoading("open");
		$.ajax({
			url:PATH+'/sysadmin/jobMonitor/cleanCacheByNamespace.action',
			type:'post',
			data:{
				'cacheNamespace':$("#cacheNamespace").val()
			},
			success:function(data){
				 $.dialogLoading("close");
					if(data==true || data=='true'){
						$.messageBox({ message:"清除缓存成功!"});
						return;
					}else{
						$.messageBox({  level:"error",message:data});
					}
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown){
		    	$.dialogLoading("close");
		 	    alert("提交数据时发生错误");
	 	   	}
		});
	});
</script>