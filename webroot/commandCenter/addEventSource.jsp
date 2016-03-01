<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="新增" class="container container_24">

	<form id="maintainForm" method="post" action="/commandCenterManage/addEventSource.action">
		<div>
			<div class="grid_4 lable-right">
	  			<em class="form-req">*&nbsp;</em><label class="form-lbl">发生地点：</label>
	  		</div>
    		<div class="grid_20 form-left">
    			<input type="text" name="eventSource.orgName" id="orgName" maxlength="50"
    			 class="form-txt {required:true,exculdeParticalChar:true,messages:{required:'请输入发生地点',exculdeParticalChar:'不能输入非法字符'}}" />
    		</div>

			<div class="grid_4 lable-right">
				<em class="form-req">*&nbsp;</em><label class="form-lbl">标题：</label>
			</div>
			<div class="grid_20 form-left">
		    	<input type="text" id="title" name="eventSource.title" maxlength="20"
		    	class="form-txt {required:true,maxlength:100,messages:{required:'请输入标题' , maxlength:$.format('标题最多输入{0}个字符')}}" />
			</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*&nbsp;</em><label class="form-lbl">来源类型: </label>
			</div>
			<div class="grid_7 form-left">
				<select  id="eventSourceKind" name="eventSource.sourceType.id" class="form-txt {required:true,messages:{required:'必须输入'}}">
                	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND"  showInternalId="true" notNull="true" exceptInternalIds="@com.tianque.domain.property.IssueSourceType@CALLCENTER_INPUT,@com.tianque.domain.property.IssueSourceType@MANUAL_INPUT,@com.tianque.domain.property.IssueSourceType@CITYMANAGE_INPUT,@com.tianque.domain.property.IssueSourceType@APPROVAL_INPUT"/>
            	</select>
			</div>
			<div class="grid_4 lable-right">
	  			<em class="form-req">*&nbsp;</em><label class="form-lbl">来源时间:</label>
	  		</div>
			<div class="grid_8 form-left">
			<input  name="eventSource.sourceDate" id="sourceDate" class="form-txt {required:true,messages:{required:'必须输入'}}" 
		value="<s:date name="eventSource.sourceDate" format="yyyy-MM-dd"/>" readonly style="background-color: white"/>
    		
    		</div>

 			<div class="grid_4 lable-right">
				<em class="form-req">*&nbsp;</em><label class="form-lbl">来源人:</label>
			</div>
			<div class="grid_7 form-left">
				<input type="text" id="sourcePeople" name="eventSource.sourcePeople" maxlength="20"
				 class="form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:'请输入来源人',exculdeParticalChar:'不能输入非法字符',minlength:$.format('来源人至少需要输入{0}个字符'),maxlength:$.format('来源人最多需要输入{0}个字符')}}" />
			
			</div>

			<div class="grid_4 lable-right">
				<label class="form-lbl">联系电话:</label>
			</div>
			<div class="grid_8 form-left">
				<input type="text" id="telephone" name="eventSource.telephone" maxlength="15"
				  class="form-txt {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" title="请输入由数字和-组成的联系电话 例如：0577-88888888" />
			
			</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*&nbsp;</em><label class="form-lbl">消息内容：</label>
			</div>
			<div class="grid_20 form-left">
    			<textarea id="content" name="eventSource.content" rows="20" cols="20" 
    		 	 class="form-txt" style="height: 4em;"></textarea>
			
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
$('#sourceDate').datePicker({
	yearRange:'1900:2030',
	dateFormat:'yy-mm-dd',
    maxDate:'+0d'
});
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(data!=true && data!=false){
               	 		$.messageBox({
							message:data,
							level: "error"
				 		});
                		return;
               		}
						$.messageBox({message:"已经成功保存!"});
						$("#informationList").trigger("reloadGrid");
						$("#informationDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
      	      		alert("提交数据时发生错误");
   	   		    }
			});
		},
		rules:{	
			"eventSource.orgName":{
				required:true,
				maxlength:50
		   },
		   "eventSource.title":{
			    required:true,
				maxlength:20
		   },
		   "eventSource.sourcePeople":{
			    required:true,
				maxlength:50
		   },
		   "eventSource.telephone":{
			    required:false,
				maxlength:50
		   },
		   "eventSource.content":{
			   required:true,
				maxlength:200
		   }
		},
		messages:{
			"eventSource.orgName":{
				required:"请输入发生地点"
				},
			"eventSource.title":{
				required:"请输入标题"
			},
			"eventSource.sourcePeople":{
				required:"请输入来源人"
			},
			"eventSource.content":{
				required:"请输入内容"
			}
			
		}
	});

})
</script>