<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp" %>

<div class="content">
    <div class="ui-corner-all" id="nav">
        <pop:JugePermissionTag ename="addTaskListTimeStandard">
            <a id="addTaskListTimeStandard" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
        </pop:JugePermissionTag>
        <pop:JugePermissionTag ename="updateTaskListTimeStandard">
            <a id="updateTaskListTimeStandard" href="javascript:void(0)"><span><strong
                    class="ui-ico-xz"></strong>修改</span></a>
        </pop:JugePermissionTag>
        <pop:JugePermissionTag ename="deleteTaskListTimeStandard">
            <a id="deleteTaskListTimeStandard" href="javascript:void(0)">
                <span><strong class="ui-ico-xz"></strong>删除</span>
            </a>
        </pop:JugePermissionTag>

        <a id="refresh" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>刷新</span></a>
    </div>
    <div style="width: 100%">
        <table id="taskListTimeStandardList"></table>
        <div id="taskListTimeStandardListPager"></div>
    </div>

    <div id="taskListTimeStandardDialog"></div>
</div>

<script type="text/javascript">
    <pop:formatterProperty name="itemNameDict" domainName="@com.tianque.domain.property.PropertyTypes@TASKLIST_ITEM_NAME" />
    $(document).ready(function () {
        var postData = {};
        $("#taskListTimeStandardList").jqGridFunction({
            url: '${path}/taskListTimeStandardManage/findTaskListTimeStandard.action',
            postData: postData,
            datatype: "json",
            colModel: [
                {name: 'id', index: 'id', sortable: false, hidden: true, frozen: true, hidedlg: true, key: true},
                {name: 'org.id', index: 'orgId', sortable: false, hidden: true, frozen: true, hidedlg: true},
                {name: 'org.orgName', index: 'orgId', label: '职能部门', sortable: true, align: 'center', width: 130},
                {
                    name: 'itemNameDict.id',
                    index: 'itemNameDict',
                    label: '项目名称',
                    formatter: itemNameDictFormatter,
                    sortable: true,
                    align: 'center',
                    width: 150
                },
                {name: 'signYellowLimit', label: '签收黄牌时限', sortable: true, align: 'center', width: 120},
                {name: 'signRedLimit', label: '签收红牌时限', sortable: true, align: 'center', width: 120},
                {name: 'replayYellowLimit', label: '回复黄牌时限', sortable: true, align: 'center', width: 120},
                {name: 'replayRedLimit', label: '回复红牌时限', sortable: true, align: 'center', width: 120},
                {name: 'remark', label: '备注', sortable: true, align: 'center', width: 120}
//	   		{name:'propaganda',label:'宣传',sortable:true,align:'center',formatter:propagandaFormatter,width:120}
            ],
            multiselect: true,
            viewrecords: true,
            onSelectRow: function () {
            },
            ondblClickRow: function (rowid) {
                //viewTaskListTimeStandard([rowid]);
            }
        });

        $("#delTaskListTimeStandard").click(function () {
            delTaskListTimeStandardList();
        });

        $("#addTaskListTimeStandard").click(function () {
            $("#taskListTimeStandardDialog").createDialog({
                width: 600,
                height: 500,
                title: '添加信息',
                url: '${path}/taskListTimeStandardManage/dispatch.action?mode=add',
                buttons: {
                    "保存": function () {
                        $("#maintainForm").submit();
                    },
                    "关闭": function () {
                        $(this).dialog("close");
                    }
                }
            });
        });
        $("#deleteTaskListTimeStandard").click(function(){
            delTaskListTimeStandardList()
        });
        function delTaskListTimeStandardList(selectedId) {
            if (selectedId == null || selectedId == undefined || selectedId == '') {
                selectedId = $("#taskListTimeStandardList").jqGrid("getGridParam", "selarrrow");
            }
            if (selectedId == null || selectedId == undefined || selectedId == '') {
                $.messageBox({level: "warn", message: "请至少一条数据再进行操作！"});
                return;
            }

            $.confirm({
                title: "确认删除",
                message: "确定要删除吗?一经删除，数据无法恢复",
                okFunc: function () {
                    $.ajax({
                        url: "${path}/taskListTimeStandardManage/deleteTaskListTimeStandard.action",
                        type: "post",
                        data: {
                            "ids": selectedId + ''
                        },
                        success: function (data) {
                            if (data == true) {
                                $("#taskListTimeStandardList").trigger("reloadGrid");
                                $.messageBox({message: "已经成功删除该信息!"});
                            } else {
                                $.messageBox({message: data, level: 'error'});
                            }
                        }
                    });
                }
            });
        }

        $("#updateTaskListTimeStandard").click(function () {
            var selectedIds = $("#taskListTimeStandardList").jqGrid("getGridParam", "selarrrow");
            if (selectedIds.length > 1) {
                $.messageBox({level: "warn", message: "只能选择一条数据进行操作！"});
                return;
            }
            updateTaskListTimeStandard(selectedIds[0]);
        });
        function updateTaskListTimeStandard(selectedId) {
            if (selectedId == null || selectedId == undefined || selectedId == '') {
                $.messageBox({level: "warn", message: "请选择一条数据再进行操作！"});
                return;
            }
            $("#taskListTimeStandardDialog").createDialog({
                width: 600,
                height: 500,
                title: '修改信息',
                url: '${path}/taskListTimeStandardManage/dispatch.action?mode=edit&taskListTimeStandard.id=' + selectedId,
                buttons: {
                    "保存": function () {
                        $("#maintainForm").submit();
                    },
                    "关闭": function () {
                        $(this).dialog("close");
                    }
                }
            });
        }

        $("#refresh").click(function () {
            $("#taskListTimeStandardList").trigger("reloadGrid");
        });
    });
</script>
