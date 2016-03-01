<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<img id="fileToUploadImg" name="population.imgUrl" src="${path }/<s:property value='#parameters.imgUrl'/>"/>
<div style="line-height:20px;color:#790000;">请拖动进度条调整图片大小！</div>
<form id="scanHeaderImageForm" action="/imageUpload/scanAndCutImage.action" method="post">
<pop:token />
	<input name="srcPath" type="hidden" value="<s:property value='#parameters.imgUrl'/>"/>
	<input id="imgWidth" name="imgWidth" type="hidden" />
	<input id="imgHeight" name="imgHeight" type="hidden" />
	<input id="cropX" name="cropX" type="hidden" />
	<input id="cropY" name="cropY" type="hidden" />
	<input id="cropWidth" name="cropWidth" type="hidden" />
	<input id="cropHeight" name="cropHeight" type="hidden" />
	<input id="cropConsistent" name="cropConsistent" type="hidden" value="true"></input>
</form>

<script>
	$(function(){
		$("#scanHeaderImageForm").formValidate({
			 promptPosition: "bottomLeft",
			 submitHandler: function(form) {
				if($("#cropConsistent").val()=='' || $("#cropConsistent").val()=="false"){
			 		$.messageBox({message:'请将虚线框拖进图片，以便我们进行保存',level:'error'});
			 		return false;
				}
		         $(form).ajaxSubmit({
		             success: function(data){
		             	$("#headerImg").attr("src",$("#fileToUploadImg").attr("src")+"?r="+Math.random());
		             	$("#_imgUrl").val($("#fileToUploadImg").attr("src"));
		             	$(".shadow").show();
		             	$("[id=scanHeaderImage]").dialog("close");
		         	}
		         })
			}
		});
		function registerInput(jracObject,input,event_name){
			jracObject.observator.register(event_name, input);
			input.bind(event_name, function(event, jracObject, value) {
				$(this).val(value);
			})
		}
		//var cropX=(590-200)/2;
		//var cropY=(480-200)/2;
		var cropX=0;
		var cropY=0;
		$("#fileToUploadImg").jrac({
			'crop_width': 200,
			'crop_height': 248,
			'crop_x': cropX,
			'crop_y': cropY,
			'crop_aspect_ratio':20/25,
			'crop_resize': true,
			'viewport_resize': false,
			'viewport_onload': function(){
				var jracObject=this;
				registerInput(jracObject,$("#cropX"),'jrac_crop_x');
				registerInput(jracObject,$("#cropY"),'jrac_crop_y');
				registerInput(jracObject,$("#imgWidth"),'jrac_image_width');
				registerInput(jracObject,$("#imgHeight"),'jrac_image_height');
				registerInput(jracObject,$("#cropWidth"),'jrac_crop_width');
				registerInput(jracObject,$("#cropHeight"),'jrac_crop_height');
			}
		}).bind('jrac_events', function(event, $viewport) {
        	$("#cropConsistent").val($viewport.observator.crop_consistent());
        });
	})
</script>