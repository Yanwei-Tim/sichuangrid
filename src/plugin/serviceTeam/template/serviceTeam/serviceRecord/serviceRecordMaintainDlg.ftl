<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<style type="text/css">
</style>
<div class="container container_24">
	<form id="serviceRecordForm" method="post" action="/plugin/serviceTeam/serviceRecord/${mode!}ServiceRecord.action">
	<@pop.token />
		<input id="mode" type="hidden" name="mode" value="${mode!}" />
		<input id="serviceObjectId" type="hidden" name="serviceObjectId" value="${(serviceObjectId)!}" />
		<input id="userOrgId" type="hidden" name="serviceRecord.userOrgId" />
		<input id="serviceRecordOrgId" type="hidden" name="serviceRecord.organization.id" value="${(serviceRecordVo.organization.id )!}" />
		<input id="serviceRecordId" type="hidden" name="serviceRecord.id" value="${(serviceRecordVo.encryptId )!}"/>
		<input id="teamId" name="serviceRecord.teamId" type="hidden" value="${(serviceRecordVo.teamId)!}" />
		<input id="memberId" name="serviceRecord.memberId" type="hidden" value="${(serviceRecordVo.memberId)!}" />
		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">服务时间：</label>
		</div>
		<div class="grid_8 ">
			<input type="text" name="serviceRecord.occurDate" id="occurDate" maxlength="32"
				readonly="readonly" class='form-txt {required:true,messages:{required:"请输入服务时间"}}' value='<@s.date name="serviceRecordVo.occurDate" format="yyyy-MM-dd" />' />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">服务地点：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 94%" name="serviceRecord.occurPlace" id="name" maxlength="20" value='${(serviceRecordVo.occurPlace)!}'
				class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入服务地点",exculdeParticalChar:"不能输入非法字符",minlength:$.format("服务地点至少需要输入{0}个字符"),maxlength:$.format("服务地点最多需要输入{0}个字符")}}' style="width:96%"/>
		</div>
		<div class='clear'></div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">记录所属人：</label>
		</div>
		<div class="grid_20 heightAuto" style="margin-top:5px;">
			<input type="text" id="serviceMembers" name="serviceRecord.serviceMembers" style="height:70px;"  class='form-txt' />
		</div>
		<div class='clear'></div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">服务参与人：</label>
		</div>
		<div class="grid_20 ">
			<input type="text" name="serviceRecord.serviceJoiners" id="serviceJoiners"  maxlength="100" style="width: 98%" class="form-txt {maxlength:100,messages:{maxlength:"服务参与人最多需要输入100个字符"}}" value="${(serviceRecordVo.serviceJoiners)!}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<@s.if test="objectIds==null">
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lb1">服务对象：</label>
			</div>
			<div class="grid_18 heightAuto" style="margin-top:5px;">
				<input type="text" id="serviceObjects" name="serviceRecord.serviceObjects" style="height:70px;" class='form-txt'/>
			</div>
			<div class="grid_2 lable-right" >
				<input type="button" value="选  择" class="defaultBtn" id="selectServiceObjects" style="margin-top:8px;" />
			</div>
			<div class='clearLine'>&nbsp;</div>
   		</@s.if>
   		
   		<div id="recordTypeDiv">
			<div class="grid_4 lable-right">
	   			<em class="form-req">*</em>
	   			<label class="form-lb1">记录类型：</label>
			</div>
			<div class="grid_8">
				<input type="radio" class="form-btn" name="serviceRecord.recordType" value='0' />&nbsp;&nbsp;排查类&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" class="form-btn" name="serviceRecord.recordType" value='1' />&nbsp;&nbsp;整改类
			</div>
			<div class='grid_12'>&nbsp;</div>
			<input type="hidden" id="recordType" value="${(serviceRecordVo.recordType)!}" />
			<input type="hidden" id="showRecordType" value="${(showRecordType)!}" />
		</div>
		
		<@s.else>
			<input type="hidden" id="serviceObjectsHidden" name="serviceRecord.serviceObjects" value="${(objectIds)!}-${(objectNames)!}-${(populationType)!}" />
		</@s.else>
		<div class="grid_4 lable-right">
			<label>服务内容：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea name="serviceRecord.serviceContent" style="height:70px;" class='form-txt {maxlength:300,messages:{maxlength:"服务内容最多需要输入300个字符"}}'>${(serviceRecordVo.serviceContent)!}</textarea>
		</div>
		<input type="hidden"  id="objectType"/>
		<select id="attachFileNames" name="attachFileNames" multiple="multiple" style="width:200px;display:none"></select>
		<input type="hidden"  id="objectBigType" value="" />
	</form>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="filePanel">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>
		<div id="attachFilesDiv" class="grid_20 heightAuto">
			<@s.if test='!"view".equals(mode)'>
			<input id="custom_file_upload" name="uploadFile" type="file" />
			</@s.if>
			<div id="custom-queue" style="clear:both;border:1px solid #ccc;overflow-x:hidden;height:100px;"></div>
		</div>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div id="objectSearchDialog"></div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#userOrgId").val(USER_ORG_ID);
		recordTypeDivControl();
		<@s.if test="objectIds==null">
			$("#serviceObjects").personnelComplete({
				multiple: true,
				height:70,
				itemClose: function(data){
					if($("#serviceObjects").val()==""){
						$("#objectType").val("");
					}
				}
			});
			$("#input_serviceObjects").attr("readonly","readonly").css("background","none");
		</@s.if>
	
		$("#serviceMembers").personnelComplete({
			multiple: true,
			height:70,
			param:"serviceTeamMemberVo.name",
			url:"/plugin/serviceTeam/serviceRecord/findServiceMembers.action",
			postDataFunction: function(){
				return {"serviceTeamMemberVo.org.id":USER_ORG_ID,"serviceTeamMemberVo.memberId":$("#memberId").val()};
			},
			select: function(data){
					$("#memberId").val(data.split("-")[0]);
			},
			itemClose: function(data){
					if($("#serviceMembers").val()==""){
						$("#memberId").val("");
					}
			}
		});	
	
		//表单验证
		$("#serviceRecordForm").formValidate({
			submitHandler: function(form) {
				if($("#serviceMembers").val()==""){
      				 $.messageBox({
      					message:"请选择记录所属人！",
      					level:"error"
      				});
      				return;
      			}
      			if($("#serviceObjects").val()==""){
      				 $.messageBox({
      					message:"请选择服务对象 ！",
      					level:"error"
      				});
      				return 
      			}
      			var ifChecked=$('input:radio[name="serviceRecord.recordType"]:checked').val();
      			if(ifChecked==null && !($("#objectBigType").val()=="" && $("#recordType").val()=="" && $("#showRecordType").val()=="2")){
      				$.messageBox({
      					message:"请选择记录类型 ！",
      					level:"error"
      				});
      				return
      			}
				$(form).ajaxSubmit({
	         		success: function(data){
	         		if(!data.id){
           	 			$.messageBox({
							message:data,
							level: "error"
			 			});
            			return;
					}
	         			if("add" == $("#mode").val()){
							$.messageBox({message:"成功添加记录!"});
							if($("#isSubmit").val()=="true"){
		                	 	$("#serviceRecordDialog").dialog("close");
		                	}else{
		                		emptyObject();
			                }
							$("#serviceRecordList").trigger("reloadGrid");
	         			}
	         			if("edit" == $("#mode").val()){
	         				$("#serviceRecordDialog").dialog("close");
							$.messageBox({message:"成功修改记录!"});
							$("#serviceRecordList").trigger("reloadGrid");
	         			}
		  	   		},
		      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	         				$.messageBox({message:"提交错误",level: "error"});
		      	   	}
	      	  	});
			}
		});
		
		$('#occurDate').datePicker({
			yearRange: '2001:2030',
			dateFormat: 'yy-mm-dd',
			maxDate:'+0d'
		});
		
		//继续新增时清空内容
		function emptyObject() {
			//$("#serviceRecordForm").resetForm();
			$("#serviceRecordForm").resetFormData({not:"#showRecordType"});
			$("#serviceMembers").clearPersonnelComplete();
			$("#serviceObjects").clearPersonnelComplete();
			$("input[name='serviceRecord.recordType']").attr("checked",false);
			//$("#recordTypeDiv").hide();
			recordTypeDivControl();
			$("#attachFileNames").html("");
			$("#custom-queue").html("");
		}
		
		//新增时组织id获取
		<@s.if test='"add".equals(mode)'>
			$("#serviceRecordOrgId").val(getCurrentOrgId());
		</@s.if>
		
		//文件上传
		$('#custom_file_upload').uploadFile({
			queueID : 'custom-queue',
			selectInputId : "attachFileNames"
		});
		
		//修改时附件列表和对象获取
		<@s.if test='"edit".equals(mode)'>
			<@s.iterator id="member" value="serviceRecordVo.members">
				var value = "${member.memberId}-${member.memberName}"
				var label = "${member.memberName}";
				$("#serviceMembers").setPersonnelCompleteVal({value:value,label:label,desc:""}); 
			</@s.iterator>	
			
			<@s.if test="objectIds==null">
				$("#serviceObjects").val("");
				<@s.iterator id="object" value="serviceRecordVo.objects">
					var value = "${object.objectId}-${object.objectName}-${object.objectType}"
					var label = "${object.objectName}";
					$("#serviceObjects").setPersonnelCompleteVal({value:value,label:label,desc:""}); 
					$("#objectType").val("${object.objectType}");
				</@s.iterator>
			</@s.if>
			<@s.else>
				$("#serviceObjectsHidden").val("");
				<@s.iterator id="object" value="serviceRecordVo.objects">
					if(${(objectIds)!}==${object.objectId}){
						var value="${object.objectId}-${object.objectName}-${object.objectType}";
						autoFillObjectValueForUpdate(value);
						$("#objectType").val("${object.objectType}");
					}
				</@s.iterator>
				$("#serviceObjectsHidden").val(value);	
			</@s.else>
			
			<@s.if test="serviceRecordVo.serviceRecordAttachments!=null && serviceRecordVo.serviceRecordAttachments.size > 0">
				<@s.iterator value="serviceRecordVo.serviceRecordAttachments" var="att">
				    $("#custom-queue").addUploadFileValue({
						id:"${att.id}",
					    filename:"${att.fileName}",
					    link:"${path}/plugin/serviceTeam/serviceRecord/downloadServiceRecordAttachment.action?attachmentId=${att.id}",  	     	 
					  	onRemove:function(){removeAttach("${att.id}")}
				     });   
				    $("<option>").attr("id","${att.id}").val("${att.fileName}").attr("selected",true).appendTo($("#attachFileNames"));
				</@s.iterator>
			</@s.if>	
		</@s.if>
	
		function removeAttach(fileId){
		$("#attachFileNames").find("option[id="+fileId+"]").remove();
			$.ajax({
				async: false,
				url: "/plugin/serviceTeam/serviceRecord/deleteAttachment.action?attachmentId="+fileId,
				data:{},
				success:function(responseData){		
				}
			});
		}
		
		function autoFillObjectValueForUpdate(option){
			var value=$("#serviceObjectsHidden").val();
			if(value!=""){
				$("#serviceObjectsHidden").val(option.value+","+value);
			}else{
				$("#serviceObjectsHidden").val(option.value);
			}
		}
		//服务对象选择按钮事件绑定
		$('#selectServiceObjects').click(function(event){
			openSelectObjects();
		});
	});
	
	function recordTypeDivControl(){
		if($("#objectBigType").val()=="" && $("#recordType").val()=="" && $("#showRecordType").val()=="2"){
			$("#recordTypeDiv").hide();
		}else{
			$("#recordTypeDiv").show();
			if($("#recordType").val()=='0'){
				$("input[name='serviceRecord.recordType'][value='0']").attr("checked",true);
			}else if($("#recordType").val()=='1'){
				$("input[name='serviceRecord.recordType'][value='1']").attr("checked",true);
			}
		}
	}
	
	function openSelectObjects(){
		$("#objectSearchDialog").createDialog({
			width: 820,
			height: 450,
			title: '服务对象查询',
			url:"${path}/plugin/serviceTeam/serviceObject/dispatch.action?mode=selectObject&objectType="+$("#objectType").val()+"&serviceObjects="+$("#serviceObjects").val(),
			buttons: {
				"确认":function(event){
					addServiceObjectForMember();
				},
				"关闭":function(){
					$(this).dialog("close");
				}
			}
		});
	}
</script>