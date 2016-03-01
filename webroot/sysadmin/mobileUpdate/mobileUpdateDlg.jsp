<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
  <form id="mobileUpdateForm" action="${path}/sysadmin/mobileUpdate/addOrUpdateMobileUpdate.action" method="post" enctype="multipart/form-data">
  <pop:token />
    <input type="hidden" id="mode" name="mode" value="${mode}"/>
    <input type="hidden" id="id" name="mobileUpdate.id" value="${mobileUpdate.id}"/>
    <input type="hidden" id="chinaOrgId" name="chinaOrgId"/>
    <div class="grid_4 lable-right">
	    <em class="form-req">*</em>
	    <label class="form-lbl"> 版本号：</label>
	</div>
	<div class="grid_8">
		<input type="text" maxlength=20 id="version" name="mobileUpdate.version" value="${mobileUpdate.version}" class="form-txt {required:true,messages:{required:'版本号不能为空'}}" />
	</div>
    <div class="grid_4 lable-right">
        <em class="form-req">*</em>
	    <label class="form-lbl"> 更新等级：</label>
	</div>
	<div class="grid_8">
		<input type="text" maxlength=20 id="level" name="mobileUpdate.level" value="${mobileUpdate.level}" class="form-txt {required:true,messages:{required:'更新等级不能为空'}}" />
	</div>
    <div class="grid_4 lable-right">
         <em class="form-req">*</em>
	    <label class="form-lbl"> 编译号：</label>
	</div>
	<div class="grid_8">
		<input type="text" maxlength=20 id="build" name="mobileUpdate.build" value="${mobileUpdate.build}" class="form-txt {required:true,messages:{required:'编译号不能为空'}}" />
	</div>
	
	 <div class="grid_4 lable-right">
	     <em class="form-req">*</em>
	    <label class="form-lbl">选择部门：</label>
	</div>
	<s:if test="mode == 'view'">
		<div class="grid_8">
			<input name="selectOrgName"  id="mobileOrg"  type="text" class="form-txt" value="${mobileUpdate.organization.orgName }">
		</div>
	</s:if>
	<s:elseif test="mode != 'view'">
		<div class="grid_8">
		   <input name="selectOrgName"  id="mobileOrgSelector"  type="text" class="form-txt">
			<input name="mobileUpdate.organization.id" id="orgIdValue" value="${mobileUpdate.organization.id }" type="hidden"/>
			<input name="oldOrgDepartmentNo" id="oldOrgDepartmentNo" value="${mobileUpdate.organization.departmentNo }" type="hidden"/>
		</div>
	</s:elseif>
	
	<s:if test="mode=='edit'">
      <div class="grid_4 lable-right">
	    <label class="form-lbl">最后更新人：</label>
	  </div>
	  <div class="grid_8">
		<input type="text" id="userName" name="mobileUpdate.userName" value="${mobileUpdate.userName}" disabled class="form-txt" />
	  </div>
	</s:if>
	<s:if test="mode=='edit'">
	  <div class="grid_4 lable-right">
	    <label class="form-lbl"> 当前文件路径：</label>
	  </div>
	  <div class="grid_8">
		<input type="text" value="${mobileUpdate.url}"  class="form-txt" disabled/>
	  </div>
	</s:if>
	<s:if test="mode != 'view'">
	  <div class="grid_4 lable-right">
	    <em class="form-req">*</em>
	    <label class="form-lbl"> 更新文件：</label>
	  </div>
	  <div class="grid_8">
	    <s:if test="mode=='edit'">
     	  <input id="fileToUpload" type="file" value="" name="upload" class="form-txt {validateFileSuffix:true,messages:{validateFileSuffix:'只能上传apk格式的文件'}}" />
	    </s:if>
	    <s:else>
     	  <input id="fileToUpload" type="file" value="" name="upload" class="form-txt {required:true,validateFileSuffix:true,messages:{required:'请选择文件',validateFileSuffix:'只能上传apk格式的文件'}}" />
	    </s:else>
	  </div>
	  <div class="grid_8">
	    <span style="color:red">(只能上传apk格式的文件)</span>
	  </div>
	</s:if>
	<div class="clear"></div>
	 <div class="grid_4 lable-right">
	    <label class="form-lbl">更新说明：</label>
	</div>
	<div class="grid_20">
		<textarea rows="10" cols="180" id="information" name="mobileUpdate.information" class="form-txt {required:true,messages:{required:'更新说明不能为空'}}">${mobileUpdate.information}</textarea>
	</div>
  </form>
  <script type="text/javascript">
  	var mobileTree;
    var mode = $("#mode").val();
    var chinaOrgId;
    if(mode=='view'){
    	$("#mobileUpdateForm input,textarea,select").attr('disabled','true');
    }
    $(document).ready(function(){
    	getChinaOrg();
    	chinaOrgId=$("#chinaOrgId").val();
    	
    	jQuery.validator.addMethod("validateFileSuffix", function(value, element){
    		if(value==''){
    			return true;
    		}
    		return value.substr(value.lastIndexOf(".")+1) == 'apk';
    	});
    		
    	$("#mobileUpdateForm").formValidate({
    		submitHandler:function(form){
    			$(form).ajaxSubmit({
    				success: function(data){
    					if(data == 'true'){
    						 showMobileUpdateInfos();
    						 $.messageBox({
     							message:"操作成功",
     						});
    					}else{
    						$.messageBox({
    							message:data,
    							level: "error"
    						});
    					}
    				  $("#mobileUpdateDlg").dialog('close');
    				}
    			});
    		}
    	});


    	if(mode!='view'){
    		var rootOrgId;
    		if(chinaOrgId==''){
    			rootOrgId=USER_ORG_ID;
    		}else{
    			rootOrgId=chinaOrgId;
    		}
	    	var orgSelector;
		 	orgSelector=$("#mobileOrgSelector").treeSelect({
		 		rootId:rootOrgId,
				inputName:"targetOrgId",
				loadCom:function(){
					if(mode=='edit'){
						$.setTreeValue(getDefaultOccurOrg(),orgSelector); 
					}
				}
			});
			$.addClick(orgSelector,getOrgValue);
			mobileTree=orgSelector;
    	}
		function getOrgValue(){
			var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
			if (selectOrgId!=null){
				$("#orgIdValue").val(selectOrgId);
			}
		}
		function getDefaultOccurOrg(){
			<s:if test="null!=mobileUpdate.organization && null!=mobileUpdate.organization.id">
				return "${mobileUpdate.organization.id}";
			</s:if>
			<s:else>
				return -1;
			</s:else>
		}
		
    });
    
    function getChinaOrg(){
		$.ajax({
			url:'${path}/sysadmin/orgManage/getOrganizationByIdAndDictName.action',
			type:"post",
			async:false,
			success:function(data){
				if(typeof(data.id)=='undefined' || typeof(data.id)==undefined){
					$.messageBox({
						message:"查询组织机构出错"
		 			});
				}else{
					$("#chinaOrgId").attr("value",data.id);
				}
			}
		});
    	
    }
  </script>
</div>