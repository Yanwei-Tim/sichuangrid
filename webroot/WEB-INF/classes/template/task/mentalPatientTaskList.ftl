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
    <div class="ui-corner-all contentNav" id="nav">
        <div class="btnbanner btnbannerData">
            <div class="btnbanner">
            <#--<@s.include value="/common/orgSelectedComponent.jsp"/>-->
		    <@s.include value="/common/orgSelectedTaskListComponent.jsp"/>
            </div>
            <div class="ui-widget autosearch">
                <input class="basic-input" type="text" value="请输入地点或姓名" id="searchByCondition" maxlength="18" style="width: 150px;" onblur="value=(this.value=='')?'请输入地点或姓名':this.value;" onfocus="value=(this.value=='请输入地点或姓名')?'':this.value;">
                <button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
            </div>
        </div>
        <a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
    <@pop.JugePermissionTag ename='searchMentalPatientTask'>
        <a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
    </@pop.JugePermissionTag>
        <span class="lineBetween "></span>
    <@pop.JugePermissionTag ename='addMentalPatientTask'>
        <a id="addFamilyInfo"  href="javascript:void(0)"><span>新增</span></a>
    </@pop.JugePermissionTag>
    <@pop.JugePermissionTag ename='refreshMentalPatientTask'>
        <a id="refresh"  href="javascript:void(0)"><span>刷新</span></a>
    </@pop.JugePermissionTag>
    <@pop.JugePermissionTag ename='deleteMentalPatientTask'>
        <a id="delete" href="javascript:void(0)"><span><strong
                class="ui-ico-sc"></strong>批量删除</span></a>
    </@pop.JugePermissionTag>
    </div>
    <div style="clear: both;"></div>
    <div style="width: 100%">
        <table id="mentalPatientTaskList"></table>
        <div id="mentalPatientTaskListPager"></div>
    </div>
    <div id="mentalPatientTaskDialog"></div>
    <div id="importFamilyInfoDialog"></div>
    <div id="searchFamilyInfoDialog"></div>
    <div id="addTaskListReplyDlg"></div>
</div>
<script type="text/javascript">

var orgId=selectConfigTaskOrg();
var title="严重精神障碍患者信息";

var replayOrgType = "";//com.tianque.plugin.taskList.constant.ReplayOrgType
var replayOrgTypeConst ={'POLICE':'<@pop.static value="@com.tianque.plugin.taskList.constant.ReplayOrgType@POLICE"/>','JUSTICE':'<@pop.static value="@com.tianque.plugin.taskList.constant.ReplayOrgType@JUSTICE"/>'};
var orgTypeNameConst ={'POLICE':'<@pop.static value="@com.tianque.plugin.taskList.constant.ReplayOrgType@ORG_NAME_POLICE"/>','JUSTICE':'<@pop.static value="@com.tianque.plugin.taskList.constant.ReplayOrgType@ORG_NAME_JUSTICE"/>'};
var taskListTimeStandard;
var taskListTimeStandardCache = {};
var serverTime = new Date().getTime();
function getTaskListTimeStandardByItemName(){
    $.ajax({
        url:PATH + "/taskListTimeStandardManage/getTaskListTimeStandardByItemName.action",
        //async:false,
        data:{
            'orgId':selectConfigTaskOrg(),
            'itemNameDictInternalId':<@pop.static value="@com.tianque.plugin.taskList.constant.TaskListItemNameInternalId@MENTAL_PATIENT_TASKVISIT_MANAGEMENT"/>
        },
        success:function (data) {
            if (Object.prototype.toString.call(data) === '[object Array]'&& data.length>0) {
                serverTime = data[0]?data[0].sysCurrTime:new Date().getTime();
                var showCol = [];
                for (var i = 0; i < data.length; i++) {
                    // 有配置的才显示亮牌
                    //if (data[i].org.id == '<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getId()"/>') {
                    if(data[i].org.functionalOrgType.displayName==orgTypeNameConst.POLICE){
                            showCol.push('signDatePolice');
                            showCol.push('replayDatePolice');
                            taskListTimeStandardCache.police = data[i];
                        }else if(data[i].org.functionalOrgType.displayName==orgTypeNameConst.JUSTICE){
                            showCol.push('signDateJustice');
                            showCol.push('replayDateJustice');
                            taskListTimeStandardCache.justice = data[i];
                        }
                    //}
                }
                $("#mentalPatientTaskList").jqGrid("showCol", showCol);
            }
        }
    })
}
$(document).ready(function () {
    var userOrgName = '<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getFunctionalOrgType().getDisplayName()"/>';
    if(userOrgName=='<@pop.static value="@com.tianque.plugin.taskList.constant.ReplayOrgType@ORG_NAME_POLICE"/>'){
        replayOrgType = replayOrgTypeConst.POLICE;
    }else  if(userOrgName=='<@pop.static value="@com.tianque.plugin.taskList.constant.ReplayOrgType@ORG_NAME_JUSTICE"/>'){
        replayOrgType = replayOrgTypeConst.JUSTICE;
    }
    getTaskListTimeStandardByItemName();
    var postData={
        "orgId":orgId
    };
    if(isConfigTaskSelect()){
        $.extend(postData,{"mentalPatientTask.mode":"gridConfigTask","mentalPatientTask.funOrgId": $("#funOrgId").val()})
    }
    $("#mentalPatientTaskList").jqGridFunction({
        url:'${path}/baseInfo/mentalPatientTaskManage/getMentalPatientTaskList.action?onlyHasException=true',
        datatype: "json",
        postData:postData,
        colModel:[
            {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
            <@pop.JugePermissionTag ename="mentalPatientPoliceTask">
                {name:'signDatePolice',label:'签收督办(派出所)',sortable:true,formatter:signSupervisePoliceFormatter,align:'center',hidden:true,hidedlg:true,width:130},
            </@pop.JugePermissionTag>
            <@pop.JugePermissionTag ename="mentalPatientJusticeTask">
                {name:'signDateJustice',label:'签收督办(卫生所)',sortable:true,formatter:signSuperviseJusticeFormatter,align:'center',hidden:true,hidedlg:true,width:130},
            </@pop.JugePermissionTag>
<@pop.JugePermissionTag ename="mentalPatientPoliceTask">
            <@pop.JugePermissionTag ename="replyMentalPatientTask">
                {name:'replayDatePolice',label:'回复督办(派出所)',sortable:false,formatter:replaySupervisePoliceFormatter,align:'center',hidden:true,hidedlg:true,width:130},
            </@pop.JugePermissionTag>
</@pop.JugePermissionTag>
<@pop.JugePermissionTag ename="mentalPatientJusticeTask">
            <@pop.JugePermissionTag ename="replyMentalPatientTask">
                {name:'replayDateJustice',label:'回复督办(卫生所)',sortable:false,formatter:replaySuperviseJusticeFormatter,align:'center',hidden:true,hidedlg:true,width:130},
            </@pop.JugePermissionTag>
</@pop.JugePermissionTag>
            {name:"organization.orgName",index:"fullOrgName",label:'所属网格',sortable:false,hidden:false,frozen:false},
            {name:"name",index:"name",label:'姓名', width:100,sortable:false,hidden:false,frozen:false},
            {name:"idCard",index:"idCard",label:'身份证号码', width:130,sortable:false,hidden:false,frozen:false},
            {name:"phone",index:"phone",label:'电话号码', width:110,sortable:false,hidden:false,frozen:true},
            {name:"time",index:"time",label:'时间',sortable:false,hidden:false,frozen:false},
            {name:"place",index:"place",label:'地点',sortable:false,hidden:false,frozen:false},
            {name:"helpPeople",index:"helpPeople",label:'帮扶人员',sortable:false,hidden:false,frozen:false},
            {name:"statusPolice",index:"statusPolice",label:'派出所签收状态',formatter:statusPoliceFormatter,sortable:false,hidden:false,frozen:false,align:"center"},
            {name:"statusJustice",index:"statusJustice",label:'卫生所签收状态',formatter:statusJusticeFormatter,sortable:false,hidden:false,frozen:false,align:"center"},
            {name:'hasReplay',label:'是否回复',sortable:false,align:"center", width:150,formatter:addTaskListReplyFormatter},
            {name:'hasException',label:'有无异常',sortable:false,align:"center", width:150,hidden:true},
            {name:"guardianName",index:"guardianName",label:'监护人姓名',sortable:false,hidden:false,frozen:false},
            {name:"guardianTel",index:"guardianTel",label:'监护人电话',sortable:false,hidden:false,frozen:false},
            {name:"isDriinked",index:"isDriinked",label:'是否服药',formatter:isDrinkedFormatter,sortable:false,hidden:false,frozen:false},
            {name:"exception",index:"exception",label:'异常详情',sortable:false,hidden:false,frozen:false},
            {name:"isout",index:"isout",label:'是否外出',sortable:false,hidden:false,frozen:false,formatter:isOutFormatter},
            {name:"reporterTel",index:"reporterTel",label:'网格员联系电话',sortable:false,hidden:false,frozen:false},
            {name:"remark",index:"remark",label:'备注',sortable:false,hidden:false,frozen:false},
            {name:'signDatePolice',label:'派出所签收时间',sortable:true,align:'center',hidden:true,hidedlg:true,width:120},
            {name:'signDateJustice',label:'卫生所签收时间',sortable:true,align:'center',hidden:true,hidedlg:true,width:120},
            {name:'createDate',label:'创建时间',sortable:true,align:'center',hidden:true,hidedlg:true,width:120}
        ],
        multiselect:true,
        onSelectAll:function(aRowids,status){},
        showColModelButton:true,
        <@pop.JugePermissionTag ename="viewMentalPatientTask">
            ondblClickRow:viewMentalPatientTask,
        </@pop.JugePermissionTag>
        onSelectRow:function(){}
    });
    function signSupervisePoliceFormatter(el,options,rowData){
//        if(replayOrgType==replayOrgTypeConst.POLICE){
            taskListTimeStandard = taskListTimeStandardCache.police;
            return signSuperviseFormatter(el,options,rowData);
//        }else{
//            return '<span title="当前账号不是派出所">err</span>';
//        }
    }
    function signSuperviseJusticeFormatter(el,options,rowData){
//        if(replayOrgType==replayOrgTypeConst.JUSTICE){
            taskListTimeStandard = taskListTimeStandardCache.justice;
            return signSuperviseFormatter(el,options,rowData);
//        }else{
//            return '<span title="当前账号不是卫生所">err</span>';
//        }
    }
    function replaySupervisePoliceFormatter(el,options,rowData){
            taskListTimeStandard = taskListTimeStandardCache.police;
            return replaySuperviseFormatter(el,options,rowData);
    }
    function replaySuperviseJusticeFormatter(el,options,rowData){
            taskListTimeStandard = taskListTimeStandardCache.justice;
            return replaySuperviseFormatter(el,options,rowData);
    }
    function signSuperviseFormatter(el,options,rowData){
        if(!taskListTimeStandard){
            return '<span title="未配置">err</span>';
        }
//        if(!replayOrgType) return '<span title="组织类别错误（不是公安或卫生所）">err</span>';
        if(taskListTimeStandard.org.functionalOrgType.displayName==orgTypeNameConst.POLICE&&rowData.statusPolice==1){
            return "-";
        }else if(taskListTimeStandard.org.functionalOrgType.displayName==orgTypeNameConst.JUSTICE&&rowData.statusJustice==1){
            return "-";
        }else{
            var createDateStr =rowData.createDate;
            if(!createDateStr){
                return 'date err';
            }
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
    function replaySuperviseFormatter(el,options,rowData) {
        if (rowData.hasReplay == 1 || rowData.hasException != 1) {
            return "-";
        }
        if (!taskListTimeStandard) {
            return '<span title="未配置">err</span>';
        }
        var signDateStr = null;
        if(taskListTimeStandard.org.functionalOrgType.displayName==orgTypeNameConst.POLICE){
            signDateStr = rowData.signDatePolice;
            if (rowData.statusPolice != 1) {
                return '<span title="未签收">...</span>';
            }
        }else if(taskListTimeStandard.org.functionalOrgType.displayName==orgTypeNameConst.JUSTICE){
            signDateStr = rowData.signDateJustice;
            if (rowData.statusJustice != 1) {
                return '<span title="未签收">...</span>';
            }
        }
        var useTime = (serverTime - Date.parse(signDateStr.replace(/-/g, "/"))) / (1000 * 60 * 60);
        if (useTime > taskListTimeStandard.replayRedLimit) {
            return '<strong class="redLimitWarn" title="红牌超时"/>';
        } else if (useTime > taskListTimeStandard.replayYellowLimit) {
            return '<strong class="yellowLimitWarn" title="黄牌超时"/>';
        } else {
            var title = "剩" + Math.ceil(taskListTimeStandard.replayYellowLimit - useTime) + '小时时限';
            return '<strong class="greenLimitWarn" title="' + title + '"/>';
        }

    }
    $("#refresh").click(function(event){
        $("#searchByCondition").attr("value","请输入地点或姓名");
        onOrgChanged();
    });

    $("#delete").click(function(event){
        var selectedId = $("#mentalPatientTaskList").jqGrid("getGridParam", "selarrrow");
        if(selectedId.length==0){
            $.messageBox({level:"warn",message:"请至少选择一条记录进行删除！"});
            return;
        }
        $.confirm({
            title:"确认删除该"+title,
            message:"该"+title+"删除后，将不可恢复，您确定要删除该"+title+"吗？",
            width:400,
            okFunc: function(){
                $.ajax({
                    url: "${path}/baseInfo/mentalPatientTaskManage/deleteMentalPatientTask.action?ids="+selectedId,
                    success: function(data){
                        onOrgChanged(selectConfigTaskOrg(),isConfigTaskGrid());
                    }
                });
            }
        });
        var evt = event || window.event;
        if (window.event) {
            evt.cancelBubble=true;
        } else {
            evt.stopPropagation();
        }
	});
	
	$("#search").click(function(){
		$("#searchFamilyInfoDialog").createDialog({
				width: 500,
				height: 350,
				title: '高级查询',
				url:'${path}/baseInfo/mentalPatientTaskManage/dispatch.action?mode=search',
				buttons: {
					"查询" : function(){
						searchMentalPatient();
						$(this).dialog("close");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	});
	
	
	$("#fastSearchButton").click(function(e){
	var orgId=selectConfigTaskOrg();
	fastSearch(orgId);
	});
	
	$("#refreshSearchKey").click(function(){
		$("#searchByCondition").attr("value","请输入地点或姓名");
	});
	
	$("#addFamilyInfo").click(function(){
		if(!isConfigTaskGrid()){
				$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
				return;
		}
		$("#mentalPatientTaskDialog").createDialog({
				width: 600,
				height: 550,
				title: '新增严重精神障碍患者记录',
				url:'${path}/baseInfo/mentalPatientTaskManage/dispatch.action?addFlag=false&mode=add&orgId='+selectConfigTaskOrg(),
				buttons: {
					"保存" : function(){
						$("#maintainForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	});
	
})

//高级搜索
function searchMentalPatient()
{
    var initParam = {
        "mentalPatientTask.organization.id": selectConfigTaskOrg(),
        "mentalPatientTask.name": $("#conditionName").val(),
        "mentalPatientTask.place": $("#conditionPlace").val(),
        "mentalPatientTask.guardianTel":$("#conditionGuardianTel").val(),
        "mentalPatientTask.guardianName":$("#conditionGuardianName").val(),
        "mentalPatientTask.isout":$("input:radio[name='mentalPatientTask.isout']:checked").val(),
        "mentalPatientTask.isDriinked":$("input:radio[name='mentalPatientTask.isDriinked']:checked").val(),
        "mentalPatientTask.conditionStartDate":$("#conditionStartDate").val(),
        "mentalPatientTask.conditionEndDate":$("#conditionEndDate").val(),
        "mentalPatientTask.statusJustice":$("#statusJustice").val(),
        "mentalPatientTask.statusPolice":$("#statusPolice").val(),
        "mentalPatientTask.hasReplay":$("#hasReplay").val(),
        "mentalPatientTask.hasException":$("#hasException").val(),
        "mentalPatientTask.helpPeople":$("#helpPeople").val(),
        "mentalPatientTask.idCard":$("#idCard").val(),
        "mentalPatientTask.phone":$("#phone").val()
    }
    if(isConfigTaskSelect()){
        $.extend(initParam,{"mentalPatientTask.mode":"gridConfigTask","mentalPatientTask.funOrgId": $("#funOrgId").val()})
    }
    $("#mentalPatientTaskList").setGridParam({
        url:"${path}/baseInfo/mentalPatientTaskManage/searchMentalPatient.action?onlyHasException=true",
        datatype: "json",
        page:1
    });
    $("#mentalPatientTaskList").setPostData(initParam);
    $("#mentalPatientTaskList").trigger("reloadGrid");
}

function isDrinkedFormatter(el, options, rowData){
    if(true==rowData.isDriinked){
        return "已服药";
    }else{
        return "未服药";
    }
}

function statusPoliceFormatter(el, options, rowData){
    var flag = "<@pop.JugePermissionTag ename='mentalPatientPoliceTask'>true</@pop.JugePermissionTag>";
    if(rowData.statusPolice == 0 && flag){
        return "<@pop.JugePermissionTag ename='mentalPatientPoliceTask'><a href='javascript:' onclick='updateOperator("+rowData.id+",\"police\")'><span style='color:#ff0000;'>签收</span></a></@pop.JugePermissionTag>";
    }else if(rowData.statusPolice == 0 && flag != 'true'){
        return "否";
    }
    if(rowData.statusPolice == 1){
        return "是";
    }
}

function statusJusticeFormatter(el, options, rowData){
    var flag = "<@pop.JugePermissionTag ename='mentalPatientJusticeTask'>true</@pop.JugePermissionTag>";
    if(rowData.statusJustice == 0 && flag){
        return "<@pop.JugePermissionTag ename='mentalPatientJusticeTask'><a href='javascript:' onclick='updateOperator("+rowData.id+",\"justice\")'><span style='color:#ff0000;'>签收</span></a></@pop.JugePermissionTag>";
    }else if(rowData.statusJustice == 0 && flag != 'true'){
        return "否";
    }
    if(rowData.statusJustice == 1){
        return "是";
    }
}



function isOutFormatter(el, options, rowData){
    if(true==rowData.isout){
        return "是";
    }else{
        return "否";
    }
}

function updateOperator(selectedId,i){
    if(selectedId==null){
        return;
    }
    var builddata=$("#mentalPatientTaskList").getRowData(selectedId);
    $("#mentalPatientTaskDialog").createDialog({
        width: 600,
        height:470,
        title: '签收',
        url:'${path}/baseInfo/mentalPatientTaskManage/dispatch.action?mode=sign&orgId='+ selectConfigTaskOrg()+'&mentalPatientTaskId='+selectedId+'&signType='+i,
        buttons: {
            "签收" : function(){
                $("#maintainForm").submit();
            },
            "关闭" : function(){
                $(this).dialog("close");
            }
        }
    });
    var evt = event || window.event;
    if(typeof evt!="object"){return false;}
    if (window.event) {
        evt.cancelBubble=true;
    } else {
        evt.stopPropagation();
    }
}

function viewMentalPatientTask(rowid){
    if(rowid==null){
        return;
    }
    $("#mentalPatientTaskDialog").createDialog({
        width: 600,
        height: 420,
        title:'查看重症精神病信息',
        modal : true,
        url:'${path}/baseInfo/mentalPatientTaskManage/viewMentalPatientTask.action?id='+rowid,
        buttons: {
            "关闭" : function(){
                $(this).dialog("close");
            }
        }
    });
}

function refreshList(searchText){
    getTaskListTimeStandardByItemName();
    var orgId=selectConfigTaskOrg();
    $("#mentalPatientTaskList").setGridParam({
        url:'${path}/baseInfo/mentalPatientTaskManage/getMentalPatientTaskList.action?onlyHasException=true',
        datatype: "json",
        page:1
    });
    var postData={
        "orgId":orgId
    };
    if(isConfigTaskSelect()){
        $.extend(postData,{"mentalPatientTask.mode":"gridConfigTask","mentalPatientTask.funOrgId": $("#funOrgId").val()})
    }
    $("#mentalPatientTaskList").setPostData(postData);
    $("#mentalPatientTaskList").trigger("reloadGrid");
}

function fastSearch(orgId){
    var condition = $("#searchByCondition").val();

    if(condition == '请输入地点或姓名') return;
    var initParam = {
        "mentalPatientTask.organization.id":orgId,
        "mentalPatientTask.fastSearchKeyWords":condition,
        "mentalPatientTask.isGrid":isConfigTaskGrid()

    }
    if(isConfigTaskSelect()){
        $.extend(initParam,{"mentalPatientTask.mode":"gridConfigTask","mentalPatientTask.funOrgId": $("#funOrgId").val()})
    }
    $("#mentalPatientTaskList").setGridParam({
        url:'${path}/baseInfo/mentalPatientTaskManage/searchMentalPatient.action',
        datatype: "json",
        page:1
    });
    $("#mentalPatientTaskList").setPostData(initParam);
    $("#mentalPatientTaskList").trigger("reloadGrid");
}

function onOrgChanged(){
    getTaskListTimeStandardByItemName();
    var orgId = selectConfigTaskOrg();
    $("#mentalPatientTaskList").setGridParam({
        url:'${path}/baseInfo/mentalPatientTaskManage/getMentalPatientTaskList.action?onlyHasException=true',
        datatype: "json",
        page:1
    });
    var postData={
        "orgId":orgId
    };
    if(isConfigTaskSelect()){
        $.extend(postData,{"mentalPatientTask.mode":"gridConfigTask","mentalPatientTask.funOrgId": $("#funOrgId").val()})
    }
    $("#mentalPatientTaskList").setPostData(postData);
    $("#mentalPatientTaskList").trigger("reloadGrid");
}

function addTaskListReplyFormatter(el, options, rowData){
    var flag = '<@pop.JugePermissionTag ename="replyMentalPatientTask">true</@pop.JugePermissionTag>';
    // replayOrgType 没有说明不是公安或卫生
    if(rowData.hasException!=1){
        return '<span title="无异常">-</span>';
    }
    if(rowData.hasException==1 &&(rowData.statusPolice == 1||rowData.statusJustice==1) && flag == 'true' && replayOrgType){
        if(rowData.hasReplay==1){
            return "<a href='javascript:void(0);' onclick='addTaskListReply("+rowData.id+")'><span style='color:#999999;'>已回复</span></a>";
        }
        return "<a href='javascript:void(0);' onclick='addTaskListReply("+rowData.id+")'><span style='color:#ff0000;'>回复</span></a>";
    }
    if(rowData.hasReplay == 0){
        return "否";
    }else if(rowData.hasReplay == 1){
        return "是";
    }
    return "";
}
function addTaskListReply(id){
    $("#addTaskListReplyDlg").createDialog({
        width: 600,
        height: 400,
        title: '回复',
        url:"${path}/plugin/taskListManage/common/addTaskListReplyDlg.action?taskListReply.moduleKey=reply_mentalPatientTask&taskListReply.taskId="+id+"&taskListReply.replayOrgType="+replayOrgType,
        buttons: {
            "回复" : function(){
                $("#maintainForm").submit();
            },
            "关闭" : function(){
                $(this).dialog("close");
            }
        }
    });
}
</script>