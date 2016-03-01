<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style type="text/css">
.imageC pre img{width:200px;height:200px;}

</style>
<form id="commentForm" action="${path}/peopleLog/peopleLogManage/saveComment.action">
<input type="hidden" id="logId" name="logId" value="${log.id}">
<table  class="tablelist">
  <tr>
    <td class="title"><label>所属网格</label></td>
    <td colspan="3" class="content" id="orgName">${log.organization.orgName}</td>
  </tr>
  <tr>
    <td class="title"><label>日志所属人</label></td>
    <td class="content"><span>${log.belonger}</span></td>
    <td class="title"><label>时间</label></td>
    <td class="content"><span><s:date name="log.publishDate" format="yyyy-MM-dd" /></span></td>
  </tr>
  <tr>
    <td class="title"><label>地点</label></td>
    <td class="content"><span>${log.address}</span></td>
    <td class="title"><label>标题</label></td>
    <td class="content"><span>${log.title}</span></td>
  </tr>
  <tr>
    <td class="title" ><label>内容</label></td>
    <td class="content" colspan="3" style="padding-top: 8px; padding-bottom: 5px;line-height:45px;">
    	<span class="imageC"><pre>${log.contents}</pre></span>
    </td>
  </tr>
  <tr>
    <td class="title"><label>总结</label></td>
    <td class="content" colspan="3"><span>${log.summary}</span></td>
  </tr>
  <tr>
    <td class="title"><label>附件</label></td>
    <td class="content" colspan="3">
    <select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
    <div id="custom-queue" style="width:96%;height:65px;overflow:auto;overflow-x: hidden;"></div>
    </td>
  </tr>
  <tr>
     <td class="title"><label>点评内容</label></td>
	 <td class="content" colspan="3">
	 	<textarea rows="5" id="commentContent" name="comment.comments" class="form-txt {required:true,maxlength:200 ,messages:{required:'请输入评论内容',maxlength:'备注最多只能输入200字符'}}"></textarea>
	</td>
  </tr>
</table>
</form>
<script>
$(document).ready(function(){
	<s:if test="log.peopleLogFiles!=null && log.peopleLogFiles.size>0">
	<s:iterator value="log.peopleLogFiles">
    	$("#custom-queue").addUploadFileValue({
	    	id:"<s:property value='id'/>",
	        filename:"<s:property value='fileName'/>",
	        link:"${path}/peopleLog/peopleLogManage/downloadPeopleLogAttachFile.action?peopleLogAttachFile.id=<s:property value='id'/>",
	        showCloseButton:false
		});
        $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").attr("selected",true).appendTo($("#attachFileNames"));
     </s:iterator>
</s:if>
	
	
	$("#commentForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					ii=data;
					if(!data.id){
           	 			$.messageBox({
							evel: "error"
			 			});
            			return;
					}
					 $.messageBox({message:"评论成功！"});
    				 $("#subLogList").setRowData(data.id,data);
    				 $("#subDialog").dialog("close");
    				 if(typeof(statistics) != 'undefined'){
    					 statistics();
    				 }
    				 if(typeof(subLogMeMaxList) != 'undefined'){
    					 subLogMestatistics();
    					 $("#subLogMeMaxList").trigger("reloadGrid");
    				 }
    				 if(typeof(workBench_PeopleLogList) != 'undefined'){
    					 $("#workBench_PeopleLogList").trigger("reloadGrid");
    				 }
 		   			 if($("#peopleLogTabs").attr("id") && $("#peopleLogTabs").getTabId($("#peopleLogTabs").tabs("option","selected"))=="subPeopleLogAll"){
 		   				subLogstatistics();
 		   				$("#subLogAllList").trigger("reloadGrid");
 		   			 }
 		   			 if($("#peopleLogTabs").attr("id") && $("#peopleLogTabs").getTabId($("#peopleLogTabs").tabs("option","selected"))=="subPeopleLog"){
 		   				subLogstatistics();
 		   				$("#subLogMeList").trigger("reloadGrid");
 		   			 }
    				 $("#peopleLogList").trigger("reloadGrid");

				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("保存失败");
	   		    }
			});
		}
	});

});

</script>