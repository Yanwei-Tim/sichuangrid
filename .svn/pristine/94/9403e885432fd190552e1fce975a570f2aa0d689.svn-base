<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
var currentOrgId;

$(function(){
	
    <c:if test="${user.admin && issueType.issueTypeDomain.personalized}">
    <c:if test='${mode=="add"}'>
	$("#orgName").treeSelect({
		inputName:"orgId",
		onSelect: getCurrentOrgIdInIssueTypePage
	});
	</c:if>
	</c:if>
    <c:if test='${mode!="view"}'>
	    <c:if test='${mode=="add"}'>
		    $("#maintainForm").attr("action","${path}/sysadmin/issueTypeManage/addIssueType.action");
		</c:if>
		<c:if test='${mode=="edit"}'>
	        $("#maintainForm").attr("action","${path}/sysadmin/issueTypeManage/updateIssueType.action");
	    </c:if>
        $("#maintainForm").formValidate({
        	promptPosition:"bottomLeft",
            submitHandler:function(form){
                $(form).ajaxSubmit({
                    success:function(data){
                        if(!data.id){
                            $.messageBox({
                                message:data,
                                level:"error"
                            });
                            return;
                        }
                        <c:if test='${mode=="add" ||mode=="copy"}'>
                            $("#issueTypeList").addRowData(data.id,data,"last");
                            $.messageBox({message:"成功保存新事件处理类别信息！"});
                        </c:if>
                        <c:if test='${mode=="edit"}'>
                            $("#issueTypeList").setRowData(data.id,data);
                            $.messageBox({message:"成功保存事件处理类别修改信息！"});
                        </c:if>
                        $("#issueTypeDialog").dialog("close");
                        $("#issueTypeList").setSelection(data.id);
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        $.messageBox({message:"提交错误！"});
                    }
                });
            },
            rules:{
                "issueType.issueTypeName":{
                    required:true,
                    minlength:2,
                    maxlength:50,
                    exculdeParticalChar:true,
                    remote:checkIssueTypeName
                },
                <c:if test="${gridInternalProperty!=null}">
	                "issueType.internalId":{
	                    required:true
	                },
                </c:if>
                "issueType.content":{
                    maxlength:200
                }
            },
            messages:{
                "issueType.issueTypeName":{
                    required:"事件处理类别名称必须输入",
                    minlength:$.format("事件处理类别名称至少需要输入{0}个字符"),
                    maxlength:$.format("事件处理类别名称最多需要输入{0}个字符"),
                    exculdeParticalChar:"事件处理类别名称只能输入字母，数字和中文字符",
                    remote:"事件处理类别已存在"
                },
                <c:if test="${gridInternalProperty!=null}">
	                "issueType.internalId":{
	                    required:"内置属性必选"
	                },
	            </c:if>
                "issueType.content":{
                    maxlength:$.format("描述最多需要输入{0}个字符")
                }
            }
        });
    </c:if>
    $("#gridInternalProperty").val(${issueType.internalId});
});

function getCurrentOrgIdInIssueTypePage() {
	currentOrgId = $("#orgId").val();
	$.ajax({
		async: false,
		url: "${path}/sysadmin/issueTypeManage/validateIssueTypeName.action",
		data:{
            "issueType.issueTypeName": function(){ return $('#issueTypeName').val()},
            "mode" : function(){ return $('#mode').val()},
            "issueType.id" : function(){ return $('#typeid').val()},
            "issueType.issueTypeDomain.id":function(){return $("#domainId").val()},
            "orgId":currentOrgId
		}
	});
}

function checkIssueTypeName() {
	$.ajax({
		async: false,
		data:{
        "issueType.issueTypeName": function(){ return $('#issueTypeName').val()},
        "mode" : function(){ return $('#mode').val()},
        "issueType.id" : function(){ return $('#typeid').val()},
        "issueType.issueTypeDomain.id":function(){return $("#domainId").val()}
        <c:if test="@com.tianque.core.util.ThreadVariable@getUser().admin && issueType.issueTypeDomain.personalized">
        ,"orgId":$("#orgId").val()
        </c:if>
    },
    url:"${path}/sysadmin/issueTypeManage/validateIssueTypeName.action",
    type:"get"
	});
}

function setCurrentOrgId() {
	if(currentOrgId == null && currentOrgId=="") {
		currentOrgId = $("#orgId").val();
	}
	return $("#orgId").val();
}
</script>
<div class="container container_24">
    <c:if test='${mode!="view"}'>
        <form id="maintainForm" method="post"  action="" >
    </c:if>
        <input type="hidden" name="mode" value="${mode}" id="mode" />
        <input type="hidden" name="issueType.id" value="${issueType.id}" id="typeid" /> 
        <input type="hidden" name="issueType.issueTypeDomain.id" value="${issueType.issueTypeDomain.id}" id="domainId" />
        <input type="hidden" name="issueType.indexId" value="${issueType.indexId}" id="indexId" /> 
        <div class="grid_5 lable-right">
        <em class="form-req">*</em>
           <label class="form-lbl">类别名称：</label>
        </div>
        <div style="display:inline;float:left;height:60px;line-height:60px;width:75%;">
            <textarea style="height:40px;" name="issueType.issueTypeName" class="form-select" 
            <c:if test='${mode=="view"}'>disabled='true'</c:if> maxlength="50">${issueType.issueTypeName}</textarea>
        </div>
        <c:if test='${mode!="view"}'>
        <div class="grid_1">            
        </div>
        </c:if>
         
        <div class='clearLine'>&nbsp;</div>
        <c:if test="${user.admin && issueType.issueTypeDomain.personalized }">
	        <div class="grid_5 lable-right">
	           <label class="form-lbl">所属县区：</label>
	        </div>
	        <c:if test='${mode=="add"}'>
		        <div style="display:inline;float:left;height:30px;line-height:30px;width:75%;">
		        	<input type="text" id="orgName" width="200"/>
					<input type="hidden" name="orgId" id="orgId"/>
				</div>
			</c:if>
			 <c:if test='${mode!="add"}'>
		        <div style="display:inline;float:left;height:30px;line-height:30px;width:75%;">
		            <input type="text" id="org-name" class="form-txt" value="${issueType.org.orgName}" maxlength="20" disabled />
					<input type="hidden" name="orgId" id="orgId" value="${issueType.org.id}" />
				</div>
	    	</c:if> 
       </c:if>
        <div class='clearLine'>&nbsp;</div>
        <c:if test="${gridInternalProperty!=null }">
	    <div class="grid_5 lable-right">
	     <em class="form-req">*</em>
	        <label class="form-lbl"> 内置属性：</label>
	    </div>
	    <div style="display:inline;float:left;height:30px;line-height:30px;width:75%;">
	        <select name="issueType.internalId" <c:if test='${mode=="view"}'>disabled</c:if> class="form-txt" id="gridInternalProperty">
	           <c:forEach items="${gridInternalProperty }" var="gp">
	                 <option value="${gp.identify}" >${gp.displayName}</option>
	           </c:forEach>
	        </select>
	    </div>
	    <c:if test='${mode!="view" }'>
        <div class="grid_1">           
        </div>
        </c:if>
	    </c:if>
	    <div class='clearLine'>&nbsp;</div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">描述：</label>
        </div>
        <div style="display:inline;float:left;height:125px;line-height:125px;width:75%;">
            <textarea style="height:110px;" name="issueType.content" class="form-select" 
            <c:if test='${mode=="view"}'>disabled='true'</c:if> maxlength="200">${issueType.content}</textarea>
        </div>
    <c:if test='${mode!="view"}'>
        </form>
    </c:if>
</div>