TQ.partyworkMembersDlg = function (dfop){

	function clearFile(){
		   $("#uploadfileBtnId").html("");
		   $("#uploadfileBtnId").html('<span>上传照片</span><input id="uploadImg" type="file" name="header" />');
		   $("#uploadImg").change(function(){
				$("#maintainUrlForm").submit();
			});
		   
	}


	function addMemberInfoDiv(data){
		var imageUrl = "/resource/system/images/admin.png";
		if(data.imageUrl){
			imageUrl=data.imageUrl;
		}
		var size = $("#leaderGroup li").size();
		if(size==0){
			size = 1;
		}else{
			size +=1;
		}
		var leaderInfoDiv =
	              '<li data-id="'+data.id+'" data-index="'+size+'" id="leaderInfo'+data.id+'">'+
						'<div class="imgBox">'+
							'<img src="'+imageUrl+'"></img>'+
						'</div>'+
						'<div class="smallText">'+
							'<span class="name">'+data.name+'('+data.genderName+')</span>'+
							'<strong class="post">'+data.duty+'</strong></br>'+
							'<p class="info">'+data.introduction+'</p>'+
						'</div>'+
						'<div class="leaderGroup-buttons" name="leaderOperate" lid="'+data.id+'">';
		if($("#updatePartyWorkMembersP").val()){
			leaderInfoDiv+='<a href="javascript:;" class="base-button" name="update"><span>编辑</span></a>';
		}
		if($("#deletePartyWorkMembersP").val()){
			leaderInfoDiv+='<a href="javascript:;" class="base-button" name="delete"><span>删除</span></a>';
		}
		leaderInfoDiv+='</div></li>';
		return $(leaderInfoDiv);
	}

	$(function(){
		var temp=$("#imgUrl").attr("src").split("/");
		if($("#imgUrl").attr("src")=='' || $("#imgUrl").attr("src")==null|| temp[temp.length-1]=='avatar.jpg'){
			$("#deleteHeaderImageBox").hide();
		}else{
			$("#deleteHeaderImageBox").show();
		}
		$("#updateDetailsForm").formValidate({
			promptPosition :"bottomLeft",
			submitHandler: function(form) {
				$("#orgId").val(getCurrentOrgId());
				if($("#mode").val()=='edit'){
					updateDetailsForm.action=dfop.path+"/basicCaseManage/updatePartyWorkMembers.action?"+dfop.token;
				}
				$(form).ajaxSubmit({
					success:function(data){
						if(data.id){
							if($("#mode").val()=='edit'){
								var div = addMemberInfoDiv(data);
								$("#leaderInfo"+data.id).replaceWith(div);
								$.messageBox({message:"编辑成功"});	
								positionPanel();	
							}else{
								var div = addMemberInfoDiv(data);
								var size = $("#leaderGroup li").size();
								if(size == 0){
									$("#leaderGroup").html(div);
								}else{
									$("#leaderGroup li").last().after(div);
								}
								$.messageBox({message:"添加成功"});
								positionPanel();
							}
							$("#partyworkMembersDialog").dialog("close");
						}else{
							$.messageBox({message:data,level: "error"	});
						}
					}
				});
				return false;
				},
				rules: {
				"partyworkMembers.name": {
					required: true,
					exculdeParticalChar:true
				},
				"partyworkMembers.duty" :{
					required: true,
					exculdeParticalChar:true
				},
				"partyworkMembers.gender.id" :{
					required: true
				},
				"partyworkMembers.introduction" :{
					required: true,
					maxlength:1000
				},
				"partyworkMembers.sort" :{
					nonNegativeInteger:true
				}
			},
			messages: {
				"partyworkMembers.name": {
					required: "姓名不能为空！",
					exculdeParticalChar:"不能输入非法字符"
				},
				"partyworkMembers.duty" :{
					required: "职务不能为空！",
					exculdeParticalChar:"不能输入非法字符"
				},
				"partyworkMembers.gender.id" :{
					required: "性别不能为空！"
				},
				"partyworkMembers.introduction":{
					required: "简介不能为空！",
					maxlength:$.format("简介最多输入{0}个字符")
				},
				"partyworkMembers.sort":{
					nonNegativeInteger:"排序值必须为0或者正整数！"  
				}
			}
		});

		$("#maintainUrlForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				$(form).ajaxSubmit({
					success: function(data){
						if(data == false || data == "false" ){
				        	 $.messageBox({message:"上传失败，请上传正确格式的图像",level: "error"});
				        	 clearFile();
			            	 return;
					     }
						$("#scanHeaderImage").createDialog({
							width: 610,
							height: 600,
							title:"头像截图",
							url:dfop.path+'/scanUserHeaderImg.jsp?imgUrl='+data,
							close:function(){
								$("#uploadImg").val("");
								clearFile();
							},
							buttons: {
								"保存" : function(event){
									$("#scanHeaderImageForm").submit();
								},
								"关闭" : function(){
									clearFile();
									$(this).dialog("close");
								}
							}
						});
		         	}
		         })
			},
	        rules:{
				"header":{
				checkFullFile:true,
				checkFileExtName:true
			   }
			},
			messages:{
				"header":{
				checkFullFile:"图片为空不能上传",
				checkFileExtName:"图片只能为 jpg,gif格式的"
			   }
			}
		});
		$("#uploadImg").change(function(){
			$("#maintainUrlForm").submit();
		});
		$("#deleteHeaderImage").click(function(){
			$("#imgUrl").attr("src","/resource/system/images/avatar.jpg");
			$("#headerUrl").val("");
			$("#deleteHeaderImageBox").hide();
		});
		
		jQuery.validator.addMethod("checkFullFile", function(value, element){
			if($("#uploadImg").val() == null || $("#uploadImg").val()==""){
				$.messageBox({message:"图片为空不能上传",level: "error"});
				return false;
			 }
			return true;
		});
		jQuery.validator.addMethod("checkFileExtName", function(value, element){
			if($("#uploadImg").val().substring($("#uploadImg").val().lastIndexOf(".")+1,$("#uploadImg").val().length) != "jpg"
				&& $("#uploadImg").val().substring($("#uploadImg").val().lastIndexOf(".")+1,$("#uploadImg").val().length) != "JPG"
				&& $("#uploadImg").val().substring($("#uploadImg").val().lastIndexOf(".")+1,$("#uploadImg").val().length)!="gif"
				&& $("#uploadImg").val().substring($("#uploadImg").val().lastIndexOf(".")+1,$("#uploadImg").val().length) != "GIF"){
				$.messageBox({message:"图片只能为 jpg,gif格式的",level: "error"});
				return false;
			 }
			return true;
		});
	});
}