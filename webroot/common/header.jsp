<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="photo">
	<s:if test='"population".equals(#parameters.imageType[0])'>
		<img id="headerImg" name="population.imgUrl" src="${path }/resource/images/head.png"/>
	</s:if>
	<s:else>
		<img id="headerImg" name="population.imgUrl" src="${path }/resource/images/locationHead.png"/>
	</s:else>
	<div class="shadow" style="display: none">
		<div class="bgc"></div>
		<div id="deleteHeaderImage" class="txt">删除当前图片</div>
	</div>
</div>

<form id="maintainUrlForm" method="post" action="${path}/imageUpload/uploadImg.action" enctype="multipart/form-data" name="maintainUrlForm">
	<div class="PictureUpload">
		<ul>
			<s:if test='"population".equals(#parameters.imageType[0])'>
				<li class="upPhoto2">
					<span class="fileFind" id="upPhoto2id"><input id="fileToUpload" type="file" value="" name="header" /></span>
				</li>
			</s:if>
			<s:else>
				<li class="upPhoto1">
					<span class="fileFind"  id="upPhoto1id"><input id="fileToUpload" type="file" value="" name="header" /></span>
				</li>
			</s:else>
			
		</ul>
	</div>
</form>
<script>
	$(function(){
		$("#maintainUrlForm").formValidate({
			 promptPosition: "bottomLeft",
			 submitHandler: function(form) {
					var fileEndName = $('#fileToUpload').val().substring($('#fileToUpload').val().lastIndexOf(".")+1,$('#fileToUpload').val().length).toUpperCase();
		            if(fileEndName != "GIF" && fileEndName != "JPG" && fileEndName != "PNG" ){
			             $("#maintainUrlForm")[0].reset();
			           	 $.messageBox({message:"上传失败，请上传正确格式的图像",level: "error"});
			           	 return;
			        }
		         $(form).ajaxSubmit({
		             success: function(data){
				         if(data == false || data == "false" ){
				        	 $.messageBox({message:"上传失败，请上传正确格式的图像",level: "error"});
				        	 clearFile();
			            	 return;
					     }
						$("#scanHeaderImage").createDialog({
							width: 600,
							height: 600,
							title:"图片截取",
							url:'${path}/baseinfo/commonPopulation/scanHeaderImage.jsp?imgUrl='+data,
							close:function(){
								clearFile();
						        $("#fileToUpload").val("");
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
				checkFileExtName:true
			   }
			},
			messages:{
				"header":{
				checkFileExtName:"图片只能为 jpg,gif,png格式的"
			   }
			}
		});

		jQuery.validator.addMethod("checkFileExtName", function(value, element){
			if($("#fileToUpload").val().substring($("#fileToUpload").val().lastIndexOf(".")+1,$("#fileToUpload").val().length).toUpperCase() != "JPG"
				&& $("#fileToUpload").val().substring($("#fileToUpload").val().lastIndexOf(".")+1,$("#fileToUpload").val().length).toUpperCase() != "GIF"
				&& $("#fileToUpload").val().substring($("#fileToUpload").val().lastIndexOf(".")+1,$("#fileToUpload").val().length).toUpperCase() != "PNG"){
				$.messageBox({message:"图片只能为 jpg,gif,png格式的",level: "error"});
				return false;
			 }
			return true;
		});
		
		
	function clearFile(){
		   $("#upPhoto2id").html("");
		   $("#upPhoto1id").html("");
		   $("#upPhoto2id").html('<input id="fileToUpload" type="file" value="" name="header" />');
		   $("#upPhoto1id").html('<input id="fileToUpload" type="file" value="" name="header" />');
		   $("#fileToUpload").change(function(){
				$("#maintainUrlForm").submit();
			});
	}
		
		
		
		
	$("#fileToUpload").change(function(){
		$("#maintainUrlForm").submit();
	});
		
		$("#deleteHeaderImage").click(function(){
			<s:if test='"population".equals(#parameters.imageType[0])'>
			$("#headerImg").attr("src","${path }/resource/images/head.png");
			</s:if>
			<s:else>
				$("#headerImg").attr("src","${path }/resource/images/locationHead.png");
			</s:else>
			$("#_imgUrl").val("");
			$(".shadow").hide();
			$(".PictureUpload").show();
		});
	})
	
</script>