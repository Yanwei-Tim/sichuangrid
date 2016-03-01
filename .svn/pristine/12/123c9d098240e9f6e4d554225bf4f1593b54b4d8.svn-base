TQ.activityRecordsMaintainDlg = function (dfop){
	$(document).ready(function(){
		
		function isAttachFileValue(){
			if($("#attachFileNames").val()){
				$("#isAttachment").val(true);
			}else{
				$("#isAttachment").val(false);
			}
		}
		$("#addActivityRecordForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				isAttachFileValue();
				$(form).ajaxSubmit({
					success:function(data){
						if(!data.id){
	           	 			$.messageBox({
								evel: "error",
								message:data
				 			});
	            			return;
						}
		                if("add"==dfop.mode){
		                	 $.messageBox({message:"活动记录新增成功！"});
		                	 $("#activityRecordList").trigger("reloadGrid");
		                }
		                if("edit"==dfop.mode){
		                	$("#activityRecordList").trigger("reloadGrid");
		                	 $.messageBox({message:"活动记录修改成功！"});
		                }
		                $("#activityRecordDialog").dialog("close");
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
				"activityRecords.organizationName":{
					required:true
				},
				"activityRecords.activityDate":{
					required:true
				},
				"activityRecords.activityPlace":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:280
				},
				"activityRecords.activityTheme":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:148
				},
				"activityRecords.organizer":{
					minlength:2,
					maxlength:248
				},
				"activityRecords.participant":{
					minlength:2,
					maxlength:248
				},
				"activityRecords.details":{
					required:true
				}
			},
			messages:{
				"activityRecords.organizationName":{
					required:"请选择"
				},
				"activityRecords.activityDate":{
					required:"请选择活动时间"
				},
				"activityRecords.activityPlace":{
					required:"请输入活动地点",
					exculdeParticalChar:"不能输入非法字符",
					minlength:$.format("活动地点至少需要输入{0}个字符"),
					maxlength:$.format("活动地点最多需要输入{0}个字符")
				},
				"activityRecords.activityTheme":{
					required:"请输活动主题",
					exculdeParticalChar:"不能输入非法字符",
					minlength:$.format("活动主题至少需要输入{0}个字符"),
					maxlength:$.format("活动主题最多需要输入{0}个字符")
				},
				"activityRecords.organizers":{
					minlength:$.format("组织者至少需要输入{0}个字符"),
					maxlength:$.format("组织者最多需要输入{0}个字符")
				},
				"activityRecords.participants":{
					minlength:$.format("参与者至少需要输入{0}个字符"),
					maxlength:$.format("参与者最多需要输入{0}个字符")
				},
				"activityRecords.details":{
					required:"请输入活动内容"
				}
			}
		});
		$('#activityDate').datePicker({
			yearRange:'1900:2030',
			dateFormat:'yy-mm-dd',
	    	maxDate:'+0d',
	       	onSelect:function(dateText, inst) {
				if(dateText!=null&&dateText!=''){
					var dateUnit=dateText.split('-');
					var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				}
			}
		});

		$('#custom_file_upload').uploadFile({
			queueID : 'custom-queue',
			selectInputId : "attachFileNames"
		});
		fillFile();

	});
	



	function removeAttach(fileName){
		$("#attachFileNames").find("option[value="+fileName+"]").remove();
	}

	function removeAttachById(id){
		$("#attachFileNames").find("option[id="+id+"]").remove();
	}

	function deleteActivityRecordsAttachFiles(id){
		$.ajax({
			url:"${path}/partyBuilding/activityRecordManage/deleteActivityRecordsAttachFiles.action?activityRecordsAttachFiles.id="+id,
			type:'GET',
			dataType:'json',
			success : function(_data){
				if(_data==true){
					removeAttachById(id);
				}
			},
			error : function(){
				$.messageBox({
					message : "加载失败，请刷新页面！",
					level : "error"
				});
			}
		});
	}

}