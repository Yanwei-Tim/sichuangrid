<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div>
    <form action="${path}/serviceList/pyramidSalesManageManage/addPyramidSalesManage.action?" method="post" id="maintainForm">
    <pop:token/>
    <input type="hidden" name="mode" id="mode" value="${mode}" />
	<input type="hidden" name="pyramidSalesManage.id"  value="${pyramidSalesManage.id}" />
	<input type="hidden" name="pyramidSalesManage.organization.id"  value="${pyramidSalesManage.organization.id}" />
	<input type="hidden" name="pyramidSalesManage.isSign"  value="${pyramidSalesManage.isSign}" />
	<div class="container container_24"> 
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">时间：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='inputTime' name='pyramidSalesManage.inputTime' value='<s:date name="pyramidSalesManage.inputTime" format="yyyy-MM-dd HH:mm:ss"/>' class="form-txt" readonly="readonly" />
		</div>
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">地点：</label>
		</div>
		<div class="grid_8 lable-left">
			<input name='pyramidSalesManage.address' value='${pyramidSalesManage.address}' title="${pyramidSalesManage.address}" maxlength="40" class="form-txt {required:true,messages:{required:'地点不能为空'}}" <s:if test='"view".equals(mode)'>readonly="readonly"</s:if> />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">类别：</label>
		</div>
		<div class="grid_8">
			
			<select  name="pyramidSalesManage.category.id" id="category"  class="form-select {required:true,messages:{required:'请选择类别'}} " <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> >
				<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@PYRAMID_CATEGORY" defaultValue="${pyramidSalesManage.category.id}" 
			   		reletionId="pyramidCategory" reletionName="@com.tianque.domain.property.PropertyTypes@PYRAMID_SALES_CATEGORY" id="category" />
			   		
<%-- 	        	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PYRAMID_CATEGORY" defaultValue="${pyramidSalesManage.category.id}" /> --%>
	        </select>		    
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">传销类别：</label>
		</div>
		<div class="grid_8">
			<select id="pyramidCategory" name="pyramidSalesManage.pyramidCategory.id" class="form-txt" <s:if test="'view'.equals(mode)">disabled</s:if>>
			</select>
<%-- 			<select  name="pyramidSalesManage.pyramidCategory.id" id="pyramidCategory"  class="form-select {required:true,messages:{required:'请选择类别'}} " <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> > --%>
<%-- 	        	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PYRAMID_SALES_CATEGORY" defaultValue="${pyramidSalesManage.pyramidCategory.id}" /> --%>
<%-- 	        </select>		     --%>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">情况描述：</label>
		</div>
		<div class="grid_8 ">
			<input name='pyramidSalesManage.details' value='${pyramidSalesManage.details}' title="${pyramidSalesManage.details}" maxlength="50" class="form-txt {required:true,messages:{required:'情况描述不能为空'}}" <s:if test='"view".equals(mode)'>readonly="readonly"</s:if> />
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">参与人员：</label>
		</div>
		<div class="grid_8 ">
			<input name='pyramidSalesManage.personnel' value='${pyramidSalesManage.personnel}' title="${pyramidSalesManage.personnel}" maxlength="50" class="form-txt" <s:if test='"view".equals(mode)'>readonly="readonly"</s:if> />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
            <textarea rows="4" name="pyramidSalesManage.remake"  maxlength="300" cols="" class="form-txt" <s:if test='"view".equals(mode)'>disabled="disabled"</s:if> >${pyramidSalesManage.remake}</textarea>
    	</div>
		<div class='clearLine'>&nbsp;</div>
    	<select id="attachFileNames" name="pyramidSalesManage.attachFileNames" multiple="multiple" style="width:200px;display:none"></select>
    	<input type="hidden" id="deleteAttachIds" name="pyramidSalesManage.deleteAttachIds" />
    	<input name="isSubmit" id="isSubmit" type="hidden" value="">
    </div>
    </form>
    <div class="container container_24 filePanel">
    		<br/>
			<div class="grid_4 lable-right">
				<label class="form-lbl">附件上传：</label>
			</div>
			<div id="attachFilesDiv" class="grid_19 heightAuto">
				<s:if test='!"view".equals(mode)'>
				<input id="custom_file_upload" name="uploadFile" type="file" />
				</s:if>
				<div id="custom-queue" style="clear:both;border:1px solid #ccc;overflow-x:hidden;height:100px;"></div>
			</div>
			<div class='clearLine'>&nbsp;</div>
	</div>
	<br/>
	<s:if test='"view".equals(mode)'>
	<div class="container container_24"> 
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">网格员姓名：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='createUser' value='${pyramidSalesManage.createUser}'  readonly="readonly" class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">电话：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='createUser' value='${pyramidSalesManage.telephone}'  readonly="readonly" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">签收人：</label>
		</div>
		<div class="grid_8 ">
			<input name='pyramidSalesManage.signPeople' value='${pyramidSalesManage.signPeople}' readonly="readonly" class="form-txt"/>
		</div>
		
		<div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">签收日期：</label>
		</div>
		<div class="grid_8 lable-left">
			<input id='inputTime' name="pyramidSalesManage.signDate" value='<s:date name="pyramidSalesManage.signDate" format="yyyy-MM-dd HH:mm:ss"/>' readonly="readonly" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">签收意见：</label>
		</div>
		<div class="grid_20 heightAuto">
            <textarea rows="4" id="signContent" name="pyramidSalesManage.signContent"  cols="" class="form-txt" disabled="disabled" >${pyramidSalesManage.signContent}</textarea>
    	</div>
		<div class='clearLine'>&nbsp;</div>
	</div>
	</s:if>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		submitHandler: function(form){
			 $("#maintainForm").ajaxSubmit({
					success : function(data) {
						if(!data.id){
	           	 			$.messageBox({ 
								level: "error",
								message:data
				 			});
	            			return;
						}
						if("add"=="${mode}"){
							$.messageBox({message:"新增成功！"});
						}
						$("#serviceListDialog").dialog('close');
						$("#pyramidSalesManageList").trigger("reloadGrid");
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
			 });
		},
	});
	
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});
	
});
	
<s:if test='!"add".equals(mode)'>	
	<s:if test="pyramidSalesManage.photos!=null && pyramidSalesManage.photos.size > 0">
		<s:iterator value="pyramidSalesManage.photos" var="file">
		    var itemHtml = '<div id="item'+'${file.id}'
				+'" class="uploadifyQueueItem completed">';
			<s:if test='!"view".equals(mode)'>
				itemHtml=itemHtml+'<div class="cancel"><a onclick="deleteAttach(${file.id})"><img src="${path}/resource/external/uploadify/cancel.png"></a></div>';
			</s:if>
			itemHtml=itemHtml+'<a href="'+'${path}/serviceList/pyramidSalesManageManage/downLoadAttachFile.action?attachFileId=${file.id}'
			+'" target="_blank"><span class="fileName">'+'${file.name}'+'</span></a></div>';
			$("#custom-queue").append($(itemHtml));
		</s:iterator>
	</s:if>	
</s:if>
function deleteAttach(attachId){
	$("#item"+attachId).remove();
	var str=$("#deleteAttachIds").val()+","+attachId;
	$("#deleteAttachIds").val(str);
}
</script>

