<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
<form id="searchAllDocsForm" method="post" action="">
		<div><br/>
		 	<input type="hidden" name="searchDocumentVo.createYear"  value="${searchDocumentVo.createYear}"/>
			<div class="grid_4 lable-right">
				<label class="form-lbl">文件标题：</label>
			</div>
			<div class="grid_19 form-left">
		    	<input type="text" id="title" name="searchDocumentVo.title" maxlength="200" value="${searchDocumentVo.title}" class="form-txt" style="width: 470px"/>
			</div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">文件号：</label>
			</div>
			<div class="grid_8 form-left">
		    	<input type="text" id="documentNo" name="searchDocumentVo.documentNo" maxlength="50" value="${searchDocumentVo.documentNo}" class="form-txt" />
			</div>
    		<div class="grid_4 lable-right">
				<label class="form-lbl">机密程度：</label>
			</div>
			<div class="grid_8">
				<select class="form-txt" name="searchDocumentVo.secretDegree.id" id="secretDegree" style="width: 175px;height: 25px">
   						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SECRETDEGREE" defaultValue="${searchDocumentVo.secretDegree.id}"/>
 				</select>
 			</div>
			<div class='clearLine'></div>		
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">主题词：</label>
	  		</div>
    		<div class="grid_8 form-left">
				<input type="text" id="theme" name="searchDocumentVo.theme" maxlength="30" value="${searchDocumentVo.theme}" class="form-txt" />
    		</div>
    		
    		<div class="grid_4 lable-right">
				<label class="form-lbl">紧急程度：</label>
			</div>
			<div class="grid_8">
				<select class="form-txt" name="searchDocumentVo.urgentDegree.id" id="urgentDegree" style="width: 175px;height: 25px">
   						<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@URGENTDEGREE" defaultValue="${searchDocumentVo.urgentDegree.id}"/>
 				</select>
 			</div>
 			<div class='clearLine'>&nbsp;</div>	
 			<div class="grid_4 lable-right">
				<label class="form-lbl">是否同步资料库：</label>
			</div>
			<div class="grid_8">
			<select class="form-txt" style="width: 200px;height: 25px" id="synchroDocs" name="searchDocumentVo.synchroDocs">
				<option value=""></option>
				<option value="true">是</option>
				<option value="false">否</option>
			</select>
 			</div>
 			<div class="grid_4 lable-right">
				<label class="form-lbl">是否有附件：</label>
			</div>
			<div class="grid_8">
			  <select class="form-txt" style="width: 175px;height: 25px" id="attachFiles" name="searchDocumentVo.attachFiles">
			 	<option value=""></option>
				<option value="true">是</option>
				<option value="false">否</option>
			  </select>
 			</div>
			
			<div class='clearLine'>&nbsp;</div>		
			<div class="grid_4 lable-right">
				<label class="form-lbl">内容：</label>
			</div>
			<div class="heightAuto" style="display:inline;float:left;width:487px;">
    			<textarea rows="3" cols="" id="contents" name="searchDocumentVo.contents"
    				class="form-txt" style="height:90px;">${searchDocumentVo.contents}</textarea>
			</div>
			<div class='clearLine'></div>	
		</div>
</form>
</div>
