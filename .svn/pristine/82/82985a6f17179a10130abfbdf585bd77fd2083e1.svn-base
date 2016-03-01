<%@    page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container container_24">
    <form action="${path}/taskListTimeStandardManage/addTaskListTimeStandard.action" method="post" id="maintainForm">
        <pop:token/>
        <input type="hidden" name="taskListTimeStandard.id" value="${taskListTimeStandard.id}"/>
        <div class="grid_6 lable-right">
            <em class="form-req">*</em>
            <label class="form-lbl">职能部门：</label>
        </div>
        <div class="grid_18">
            <select name="taskListTimeStandard.org.id" class='form-txt  {required:true,messages:{required:"职能部门必须选择"}}' id="organizationId" <c:if test='${mode!="add"}'>disabled="disabled"</c:if>>
                <c:forEach items="${orgList}" var="fo">
                    <option value="${fo.id }" <c:if test="${fo.id==taskListTimeStandard.org.id}">selected='selected'</c:if>>${fo.orgName }</option>
                </c:forEach>
            </select>
        </div>
        <div class="grid_6 lable-right">
            <em class="form-req">*</em>
            <label class="form-lbl">项目名称：</label>
        </div>
        <div class="grid_18">
            <select name="taskListTimeStandard.itemNameDict.id" class='form-txt' id="itemName" <c:if test='${mode!="add"}'>disabled="disabled"</c:if>>
                <pop:OptionTag notNull="true" name="@com.tianque.domain.property.PropertyTypes@TASKLIST_ITEM_NAME"
                               defaultValue="${taskListTimeStandard.itemNameDict.id}"/>
            </select>
        </div>
        <div class='clearLine'></div>
        <%--<div class="grid_2"></div>--%>
        <%--<div class="grid_22">--%>
        <%--<em class="form-req">*</em>--%>
        <%--<label class="form-lbl" style="color: red;">办理时限（至少填写一项）</label>--%>
        <%--</div>--%>
        <table>
            <tr>
                <td colspan="2">
                    <div style="padding: 15px 0px 5px 50px;color: #C9C9C9;">——<span
                            style="font-weight: bolder;color:#353535;font-size:14px;padding:0px 10px 0px 10px">签收</span>———————————————————————————————————
                    </div>
                </td>
            </tr>
            <tr>
                <td style="padding: 0px 0px 0px 60px;">
                    <div class='clearLine'></div>
                    <div class="grid_6 lable-right">
                        <label class="form-lbl">黄牌时限：</label>
                    </div>
                    <div class="grid_4">
                        <input type="text" name="taskListTimeStandard.signYellowLimit" id="signYellowLimit"
                               class='form-txt {required:true,positiveInteger:true,messages:{required:"黄牌时限必填",positiveInteger:"只能输入数字"}}'
                               value="${taskListTimeStandard.signYellowLimit }" maxlength="9"/>
                    </div>
                    <div class="grid_7">
                        &nbsp;小时
                    </div>
                    <div class="grid_7 lable-right" style="color:red;">
                    </div>
                </td>
                <td>
                    <div class='clearLine'></div>
                    <div class="grid_6 lable-right">
                        <label class="form-lbl">红牌时限：</label>
                    </div>
                    <div class="grid_4">
                        <input type="text" name="taskListTimeStandard.signRedLimit" id="signRedLimit"
                               class='form-txt {required:true,positiveInteger:true,messages:{required:"红牌时限必填",positiveInteger:"只能输入数字"}}'
                               value="${taskListTimeStandard.signRedLimit }" maxlength="9"/>
                    </div>
                    <div class="grid_7">
                        &nbsp;小时
                    </div>
                    <div class="grid_7 lable-right" style="color:red;">
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="color: red;padding: 15px 0px 0px 60px;">
                    注:签收的时限标准从事件上报时间计算，红牌时限需大于黄牌时限
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <div style="padding: 15px 0px 5px 50px;color: #C9C9C9;">——<span
                            style="font-weight: bolder;color:#353535;font-size:14px;padding:0px 10px 0px 10px">回复</span>———————————————————————————————————
                    </div>
                </td>
            </tr>
            <tr>
                <td style="padding: 0px 0px 0px 60px;">
                    <div class='clearLine'></div>
                    <div class="grid_6 lable-right">
                        <label class="form-lbl">黄牌时限：</label>
                    </div>
                    <div class="grid_4">
                        <input type="text" name="taskListTimeStandard.replayYellowLimit" id="replayYellowLimit"
                               class='form-txt {required:true,positiveInteger:true,messages:{required:"黄牌时限必填",positiveInteger:"只能输入数字"}}'
                               value="${taskListTimeStandard.replayYellowLimit }" maxlength="9"/>
                    </div>
                    <div class="grid_7">
                        &nbsp;小时
                    </div>
                    <div class="grid_7 lable-right" style="color:red;">
                    </div>
                </td>
                <td>
                    <div class='clearLine'></div>
                    <div class="grid_6 lable-right">
                        <label class="form-lbl">红牌时限：</label>
                    </div>
                    <div class="grid_4">
                        <input type="text" name="taskListTimeStandard.replayRedLimit" id="replayRedLimit"
                               class='form-txt {required:true,positiveInteger:true,messages:{required:"红牌时限必填",positiveInteger:"只能输入数字"}}'
                               value="${taskListTimeStandard.replayRedLimit }" maxlength="9"/>
                    </div>
                    <div class="grid_7">
                        &nbsp;小时
                    </div>
                    <div class="grid_7 lable-right" style="color:red;">
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="color: red;padding: 15px 0px 0px 60px;">
                    注:回复的时限标准从签收时间计算，红牌时限需大于黄牌时限
                </td>
            </tr>

        </table>

        <div class="grid_6 lable-right">
            <label class="form-lbl">备注：</label>
        </div>
        <div class="grid_18">
            <textarea rows="2" cols="" id="remark" name="taskListTimeStandard.remark" class="form-txt"
                      maxlength="200">${taskListTimeStandard.remark }</textarea>
        </div>
        <input type="hidden" id="itemNameIds" name="taskListTimeStandard.itemNameIds">
    </form>
</div>
<script>
    $(function () {
        var mode = "${mode}";
        if (mode == 'add') {
            $("#itemName").uiMultiselect({
                selectedList: 5,
                click: function (event, ui) {
                    prepareValues(ui.checked, ui.value);
                },
                checkAllText: '',
                header: false,
                uncheckAllText: ''
            });
            $("#itemName").multiselect("uncheckAll");
        }else if(mode=='edit'){
            $("#maintainForm").attr("action",PATH+"/taskListTimeStandardManage/updateTaskListTimeStandard.action");
        }

        var checkedValues = new Array();
        function prepareValues(ifCheck, cheValue) {
            if (ifCheck == 'true' || ifCheck == true) {
                if (cheValue != "") {
                    checkedValues.push(cheValue);
                }
            }
            if (ifCheck == 'false' || ifCheck == false) {
                for (var i in checkedValues) {
                    if (checkedValues[i] == cheValue) {
                        checkedValues.splice(i, 1);
                    }
                }
            }
            $("#itemNameIds").val(checkedValues);
        }

        $("#maintainForm").formValidate({
            promptPosition: "bottomLeft",
            submitHandler: function (form) {
                if(parseInt($("#signRedLimit").val())<=parseInt($("#signYellowLimit").val())){
                    $.messageBox({ message:"签收红牌时限必须大于黄牌",level: "error"});
                    return false;
                }
                if(parseInt($("#replayRedLimit").val())<=parseInt($("#replayYellowLimit").val())){
                    $.messageBox({ message:"回复红牌时限必须大于黄牌",level: "error"});
                    return false;
                }
                if(!$("#itemNameIds").val()&&mode == 'add'){
                    $.messageBox({ message:"项目名称必须选择",level: "error"});
                    return false;
                }
                $("#maintainForm").ajaxSubmit({
                    success: function (data) {
                        if (data != true) {
                            $.messageBox({message: data,level: "error"});
                            return;
                        }
                        if ("add" == mode) {
                            $.messageBox({message: "新增时限标准成功!"});
                        } else if ("edit" == mode) {
                            $.messageBox({message: "修改时限标准成功!"});
                        }
                        $("#taskListTimeStandardDialog").dialog("close");
                        $("#taskListTimeStandardList").trigger("reloadGrid");
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("提交数据时发生错误");
                    }
                });
            }
        });
    });
    //$.cacheScript({
    //    url:'/resource/getScript/issue/issueAccessConfig/maintainFunctionOrgTimeLimitStandardDlg.js',
    //    callback: callback
    //})

</script>
