<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<style>
    .greenLimitWarn {
        width: 22px;
        height: 22px;
        display: block;
        margin: 0 auto;
        vertical-align: top;
        background: url(/resource/system/images/issue/icon_gLamp.png) no-repeat;
    }
    .yellowLimitWarn {
        width: 22px;
        height: 22px;
        display: block;
        margin: 0 auto;
        vertical-align: top;
        background: url(/resource/system/images/issue/icon_yLamp.png) no-repeat;
    }
    .redLimitWarn {
        width: 22px;
        height: 22px;
        display: block;
        margin: 0 auto;
        vertical-align: top;
        background: url(/resource/system/images/issue/icon_rLamp.png) no-repeat;
    }
</style>
<div class="content">
    <div class="ui-corner-all" id="nav">
        <div class="btnbanner btnbannerData">
        <#--	 <@s.include value="/common/orgSelectedComponent.jsp"/>-->
        <@s.include value="/common/orgSelectedTaskListComponent.jsp"/>
            <div class="ui-widget autosearch">
                <input class="basic-input searchtxt" type="text" value="请输入地点"
                       name="exceptionalSituationRecordVo.fastSearchCondition" id="searchText" maxlength="18"
                        onblur="value=(this.value=='')?'请输入地点':this.value;"
                       onfocus="value=(this.value=='请输入地点')?'':this.value;"/>
                <button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
            </div>
            <a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
        <@pop.JugePermissionTag ename="searchExceptionalSituationRecord">
            <a id="search" href="javascript:void(0)"><span><strong
                    class="ui-ico-cx"></strong>高级搜索</span></a>
            <span class="lineBetween"></span>
        </@pop.JugePermissionTag>
        </div>
    <@pop.JugePermissionTag ename="addExceptionalSituationRecord">
        <a id="add" href="javascript:void(0)"><span><strong
                class="ui-ico-xz"></strong>新增</span></a>
    </@pop.JugePermissionTag>
    <@pop.JugePermissionTag ename="deleteExceptionalSituationRecord">
        <a id="delete" href="javascript:void(0)"><span><strong
                class="ui-ico-sc"></strong>批量删除</span></a>
    </@pop.JugePermissionTag>
        <a id="reload" href="javascript:void(0)"><span><strong
                class="ui-ico-refresh"></strong>刷新</span></a>
    </div>
    <div style="width: 100%;" class="">
        <table id="exceptionalSituationRecordList"></table>
        <div id="exceptionalSituationRecordListPager"></div>
    </div>
    <div id="exceptionalSituationRecordDialog"></div>
    <div id="addTaskListReplyDlg"></div>
</div>
<script type="text/javascript">
    var taskListTimeStandard;
    var serverTime = new Date().getTime();
    function getTaskListTimeStandardByItemName(){
        $.get(PATH + "/taskListTimeStandardManage/getTaskListTimeStandardByItemName.action", {
            'orgId': selectConfigTaskOrg(),
            'itemNameDictInternalId':<@pop.static value="@com.tianque.plugin.taskList.constant.TaskListItemNameInternalId@EXCEPTIONAL_SITUATION_RECORD_MANAGE"/>
        }, function (data) {
            taskListTimeStandard = data[0];
            serverTime = taskListTimeStandard?taskListTimeStandard.sysCurrTime:new Date().getTime();
            // 有配置的才显示亮牌
            if(taskListTimeStandard){
                $("#exceptionalSituationRecordList").jqGrid("showCol", ["signDate","replayDate"]);
            }
        });
    }
    $(document).ready(function () {
        getTaskListTimeStandardByItemName();
    <@pop.formatterProperty name="exceptionSituation" domainName="@com.tianque.domain.property.PropertyTypes@EXCEPTION_SITUATION_TYPE" />
        $("#exceptionalSituationRecordList").jqGridFunction({
            datatype: "local",
            multiselect: true,
            colModel: [
                {name: "id", index: "id", sortable: false, hidden: true},
            /**
             {name:"operation",index:"id",label:"操作",sortable:false,width:80,align:"center",formatter:operaterFormatter},
             **/
            <@pop.JugePermissionTag ename="signExceptionalSituationRecord">
                {name:'signDate',label:'签收督办',sortable:true,formatter:signSuperviseFormatter,align:'center',hidden:true,hidedlg:true,width:130},
            </@pop.JugePermissionTag>
            <@pop.JugePermissionTag ename="replyExceptionalSituationRecord">
                {name:'replayDate',label:'回复督办',sortable:true,formatter:replaySuperviseFormatter,align:'center',hidden:true,hidedlg:true,width:130},
            </@pop.JugePermissionTag>
                {name: 'recordDate', index: "recordDate", label: '时间', align: "center", sortable: true, width: 150},
                {name: 'address', index: 'address', label: '地点', sortable: false, width: 200},
                {name: 'exceptionSituation',label: '异常类型',align: "center",sortable: false, width: 150, formatter: exceptionSituationFormatter},
                {name: 'status', label: '是否签收',sortable: false,align: "center",width: 150,formatter: statusFormatter},
                {name: 'hasReplay',label: '是否回复',sortable: false,align: "center",width: 150,formatter: addTaskListReplyFormatter},
                {name: 'mark', index: 'mark', label: '备注', sortable: false, width: 500},
                {name: 'signDate', label: '签收时间', align: "center", sortable: true,hidden:true,hidedlg:true, width: 150},
                {name:'createDate',label:'创建时间',sortable:true,align:'center',hidden:true,hidedlg:true,width:120}
            ],
        ondblClickRow: viewExceptionalSituationRecord
    });
        function signSuperviseFormatter(el,options,rowData){
            if(rowData.status==1){
                return "-";
            }else{
                if(!taskListTimeStandard){
                    return '<span title="未配置">err</span>';
                }
                var createDateStr =rowData.createDate;
                var useTime =(serverTime - Date.parse(createDateStr.replace(/-/g, "/")))/(1000*60*60);
                if(useTime>taskListTimeStandard.signRedLimit){
                    return '<strong class="redLimitWarn" title="红牌超时"/>';
                }else if(useTime>taskListTimeStandard.signYellowLimit){
                    return '<strong class="yellowLimitWarn" title="黄牌超时"/>';
                }else{
                    var title = "剩"+Math.ceil(taskListTimeStandard.signYellowLimit-useTime)+'小时时限';
                    return '<strong class="greenLimitWarn" title="'+title+'"/>';
                }
            }
        }
        function replaySuperviseFormatter(el,options,rowData){
            if(rowData.status!=1){
                return '<span title="未签收">...</span>';
            }
            if(rowData.hasReplay==1){
                return "-";
            }else{
                if(!taskListTimeStandard){
                    return '<span title="未配置">err</span>';
                }
                var signDateStr =rowData.signDate;
                var useTime =(serverTime - Date.parse(signDateStr.replace(/-/g, "/")))/(1000*60*60);
                if(useTime>taskListTimeStandard.replayRedLimit){
                    return '<strong class="redLimitWarn" title="红牌超时"/>';
                }else if(useTime>taskListTimeStandard.replayYellowLimit){
                    return '<strong class="yellowLimitWarn" title="黄牌超时"/>';
                }else{
                    var title = "剩"+Math.ceil(taskListTimeStandard.replayYellowLimit-useTime)+'小时时限';
                    return '<strong class="greenLimitWarn" title="'+title+'"/>';
                }
            }
        }
        $("#exceptionalSituationRecordList").jqGrid('setFrozenColumns');
        //新增按钮事件
        $("#add").click(function (event) {
            if (!isConfigTaskGrid()) {
                $.messageBox({level: "warn", message: "请先选择网格级别组织机构进行新增！"});
                return;
            }
            $("#exceptionalSituationRecordDialog").createDialog({
                title: "新增异常情况报告记录",
                width: 600,
                height: 300,
                url: "${path}/plugin/taskListManage/exceptionalSituationRecord/dispatch.action?mode=add",
                buttons: {
                    "保存": function (event) {
                        $("#exceptionalSituationRecordForm").submit();
                    },
                    "关闭": function (event) {
                        $(this).dialog("close");
                    }
                }
            });
        });
        //刷新按钮事件绑定
        $("#reload").click(function (event) {
            $("#searchText").val("请输入地点");
            getExceptionalSituationRecordList();
        });

        $("#refreshSearchKey").click(function () {
            $("#searchText").val("请输入地点");
        });

        $("#fastSearchButton").click(function (event) {
            var fastSearchCondition = $("#searchText").val();
            if (fastSearchCondition == "请输入地点") {

            } else {
                var postData = {
                    "exceptionalSituationRecordVo.organization.id": selectConfigTaskOrg(),
                    "exceptionalSituationRecordVo.fastSearchCondition": fastSearchCondition
                };
                if (isConfigTaskSelect()) {
                    $.extend(postData, {
                        "exceptionalSituationRecordVo.mode": "gridConfigTask",
                        "exceptionalSituationRecordVo.funOrgId": $("#funOrgId").val()
                    })
                }
                $("#exceptionalSituationRecordList").setPostData(postData);
                $("#exceptionalSituationRecordList").trigger("reloadGrid");
            }
        });

        getExceptionalSituationRecordList();

        //高级搜索对话框
        $("#search").click(function (event) {
            $("#exceptionalSituationRecordDialog").createDialog({
                title: "异常情况报告记录查询-请输入查询条件",
                width: 700,
                height: 300,
                url: "${path}/plugin/taskListManage/exceptionalSituationRecord/dispatch.action?mode=search",
                buttons: {
                    "查询": function (event) {
                        searchExceptionalSituationRecords();
                        $(this).dialog("close");
                    },
                    "关闭": function () {
                        $(this).dialog("close");
                    }
                }

            });
        });

        $("#delete").click(function () {
            var ids = $("#exceptionalSituationRecordList").jqGrid("getGridParam", "selarrrow");
            if (ids.length < 1) {
                $.messageBox({level: 'warn', message: "没有选中数据，无法对异常情况报告记录进行删除操作！"});
            } else {
                deleteExceptionalSituationRecordOperator(ids);
            }
        });
    });

        //列表显示（包括快速过滤）
        function getExceptionalSituationRecordList() {
            $("#exceptionalSituationRecordList").setGridParam({
                url: "${path}/plugin/taskListManage/exceptionalSituationRecord/findExceptionalSituationRecords.action",
                datatype: "json",
                page: 1,
                mytype: "post"
            });
            var postData = {
                "exceptionalSituationRecordVo.organization.id": selectConfigTaskOrg()
            };
            if (isConfigTaskSelect()) {
                $.extend(postData, {
                    "exceptionalSituationRecordVo.mode": "gridConfigTask",
                    "exceptionalSituationRecordVo.funOrgId": $("#funOrgId").val()
                })
            }
            $("#exceptionalSituationRecordList").setPostData(postData);
            $("#exceptionalSituationRecordList").trigger("reloadGrid");
        }

        //删除服务记录
        function deleteExceptionalSituationRecordOperator(selectedIds) {
            var flag1 = false;
            var flag2 = false;
            for (var i = 0; i < selectedIds.length; i++) {
                var exceptionalSituationRecord = $("#exceptionalSituationRecordList").getRowData(selectedIds[i]);
                if (exceptionalSituationRecord.internalId > USER_ORG_LEVEL) {
                    flag1 = true;
                }
                if (exceptionalSituationRecord.status == '是') {
                    flag2 = true;
                }
            }
            if (flag1) {
                $.messageBox({level: 'warn', message: "选中的异常情况报告记录层级高于当前登录层级，无法对该异常情况报告记录进行删除操作！"});
                return;
            }
            if (flag2) {
                $.messageBox({level: 'warn', message: "选中的异常情况报告记录已签收，无法对该异常情况报告记录进行删除操作！"});
                return;
            }
            $.confirm({
                title: "确认删除",
                message: "确定要删除吗?",
                okFunc: function () {
                    $.ajax({
                        url: "${path}/plugin/taskListManage/exceptionalSituationRecord/deleteExceptionalSituationRecords.action?ids=" + selectedIds,
                        success: function (data) {
                            if (data > 0) {
                                $.messageBox({message: "成功删异常情况报告记录!"});
                                $("#exceptionalSituationRecordList").trigger("reloadGrid");
                            } else {
                                $.messageBox({
                                    message: "删除异常情况报告记录出错!",
                                    level: "warn"
                                });
                            }
                        }
                    });
                }
            });
        }

        //高级搜索
        function searchExceptionalSituationRecords() {
            $("#exceptionalSituationRecordList").setGridParam({
                url: "${path}/plugin/taskListManage/exceptionalSituationRecord/findExceptionalSituationRecords.action",
                datatype: "json",
                page: 1,
                mtype: "post"
            });
            var data = $("#searchExceptionalSituationRecordForm").serializeArray();
            var dataJson = {};
            for (i = 0; i < data.length; i++) {
                if (dataJson[data[i].name]) {
                    dataJson[data[i].name] = dataJson[data[i].name] + "," + data[i].value;
                } else {
                    dataJson[data[i].name] = data[i].value;
                }
            }

            var postData = $.extend(dataJson, {
                "exceptionalSituationRecordVo.organization.id": selectConfigTaskOrg()
            })
            if (isConfigTaskSelect()) {
                $.extend(postData, {
                    "exceptionalSituationRecordVo.mode": "gridConfigTask",
                    "exceptionalSituationRecordVo.funOrgId": $("#funOrgId").val()
                })
            }
            $("#exceptionalSituationRecordList").setPostData(postData);
            $("#exceptionalSituationRecordList").trigger("reloadGrid");
        }

        function viewExceptionalSituationRecord(selectedId) {
            $("#exceptionalSituationRecordDialog").createDialog({
                width: 600,
                height: 400,
                title: '查看异常情况报告记录信息',
                url: "${path}/plugin/taskListManage/exceptionalSituationRecord/viewExceptionalSituationRecord.action?mode=view&id=" + selectedId,
                buttons: {
                    "关闭": function () {
                        $(this).dialog("close");
                    }
                }
            });
        }

        function homeOrNotFormatter(el, options, rowData) {
            if (el == 0) {
                return "否";
            }
            if (el == 1) {
                return "是";
            }
        }

        function statusFormatter(el, options, rowData) {
            var flag = "<@pop.JugePermissionTag ename='signExceptionalSituationRecord'>true</@pop.JugePermissionTag>";
            if (rowData.status == 0 && flag == 'true') {
                return "<@pop.JugePermissionTag ename='signExceptionalSituationRecord'><a href='javascript:' onclick='signRecord("+ rowData.id+")'><span style='color:#ff0000;'>签收</span></a></@pop.JugePermissionTag>";
            } else if (rowData.status == 0 && flag != 'true') {
                return "否";
            }
            if (rowData.status == 1) {
                return "是";
            }
        }

        function addTaskListReplyFormatter(el, options, rowData) {
            var flag = '<@pop.JugePermissionTag ename="replyExceptionalSituationRecord">true</@pop.JugePermissionTag>';
            if (rowData.status == 1 && flag == 'true') {
                if (rowData.hasReplay == 1) {
                    return "<a href='javascript:void(0);' onclick='addTaskListReply(" + rowData.id + ")'><span style='color:#999999;'>已回复</span></a>";
                }
                return "<a href='javascript:void(0);' onclick='addTaskListReply(" + rowData.id + ")'><span style='color:#ff0000;'>回复</span></a>";
            } else if (rowData.hasReplay == 0 && flag != 'true') {
                return "否";
            }
            if (rowData.hasReplay == 1) {
                return "是";
            }
            return "";
        }

        function operaterFormatter(el, options, rowData) {
            if (rowData.status == 0) {
                return "<@pop.JugePermissionTag ename='deleteExceptionalSituationRecord'><a href='javascript:' onclick='deleteExceptionalSituationRecordOperator("+ rowData.id+")'><span>删除</span></a></@pop.JugePermissionTag>";
            } else {
                return "无";
            }
        }

        function refreshList(searchText) {
            getTaskListTimeStandardByItemName();
            $("#exceptionalSituationRecordList").setGridParam({
                url: "${path}/plugin/taskListManage/exceptionalSituationRecord/findExceptionalSituationRecords.action",
                datatype: "json",
                page: 1
            });

            var postData = {
                "exceptionalSituationRecordVo.organization.id": selectConfigTaskOrg()
            };
            if (isConfigTaskSelect()) {
                $.extend(postData, {
                    "exceptionalSituationRecordVo.mode": "gridConfigTask",
                    "exceptionalSituationRecordVo.funOrgId": $("#funOrgId").val()
                })
            }

            $("#exceptionalSituationRecordList").setPostData(postData);
            $("#exceptionalSituationRecordList").trigger("reloadGrid");
        }

        function signRecord(id) {
            $("#exceptionalSituationRecordDialog").createDialog({
                width: 600,
                height: 400,
                title: '异常情况报告记录签收',
                url: "${path}/plugin/taskListManage/exceptionalSituationRecord/dispatch.action?mode=sign&id=" + id,
                buttons: {
                    "签收": function () {
                        $("#exceptionalSituationRecordForm").submit();
                    },
                    "关闭": function () {
                        $(this).dialog("close");
                    }
                }
            });
        }

        function addTaskListReply(id) {
            $("#addTaskListReplyDlg").createDialog({
                width: 600,
                height: 400,
                title: '回复',
                url: "${path}/plugin/taskListManage/common/addTaskListReplyDlg.action?taskListReply.moduleKey=reply_exceptionalSituationRecord&taskListReply.taskId=" + id,
                buttons: {
                    "回复": function () {
                        $("#maintainForm").submit();
                    },
                    "关闭": function () {
                        $(this).dialog("close");
                    }
                }
            });
        }
</script>
