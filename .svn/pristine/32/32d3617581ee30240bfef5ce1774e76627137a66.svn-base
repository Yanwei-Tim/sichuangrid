<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
    <div class="ui-corner-all container_24" id="nav">
		<div class="grid_9">
		<pop:JugePermissionTag ename="searchSecurityTrouble">
	        <a id="search" href="javascript:void(0)"><span>查询</span></a>
	    </pop:JugePermissionTag>
        	<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">治安,安全隐患类型：</label>
		</div>
        <div class="grid_8">
		    <pop:IssueTypeSelectTag afterChange="searchSecuritytrouble" name="issueTypeId" domainName="@com.tianque.issue.constants.IssueTypes@SECURITYTROUBLE" id="issueTypeId"></pop:IssueTypeSelectTag>
		</div>
		<div class="grid_1">
		</div>
    </div>
    <div style="clear: both;"></div>
    <div style="width: 100%;">
        <table id="securitytroubleList"> </table>
        <div id="securitytroubleListPager"></div>
    </div>
    <div id="securitytroubleDialog"></div>
    <div id="noticeDialog"></div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="sourceKind" domainName="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND" />
var dialogWidth=700;
var dialogHeight=550;
var issueStatus = '<s:property value="#parameters.issueStatus"/>';
    $(function(){
        $("#securitytroubleList").jqSubGrid({
            datatype:'local',
            subGridPostData:function(data){return {id:data.id}},
            subGridUrl:"${path}/issue/issueManage/newViewSubDetail.action?managementMode=view",
            colModel:[
                {name:"id",index:"id",hidden:true},
    			{name:'supervisionState',index:'supervisionState',label:' ',formatter:rendSupervise,width:40},
    			{name:'urgent',index:'urgent',label:' ',formatter:rendUrgent,width:40},
                //{name:"serialNumber",index:"serialNumber",label:"服务单号",width:"180"},
                {name:'subject',index:'subject',label:'事件名称'},
                {name:'status',index:'status',label:'状态',formatter:statusFormatter},
                {name:'occurDate',index:'occurDate',label:'发生时间'},
                {name:'currentOrgDisplayName',index:'currentOrgDisplayName',label:'当前处理部门'},
                {name:'occurOrg.orgName',index:'occurOrg',label:'所属网格',hidden:true},
                {name:'sourceKind.displayName',index:'sourceKind',label:'来源方式',formatter:sourceKindFormatter},
                {name:'relatePeopleCount',index:'relatePeopleCount',label:'涉及人数'}
            ]
        });

        if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
            onOrgChanged(getCurrentOrgId());
        }

        $("#search").click(function(event){
            $("#securitytroubleDialog").createDialog({
                width: 580,
                height: 260,
                title: "治安,安全隐患查询-请输入查询条件",
                url: "${path}/securitytrouble/securitytroubleManage/dispatch.action?mode=search&sourceOrganization.id="+getCurrentOrgId(),
                buttons: {
                    "查询" : function(event){
                	searchSecuritytrouble();
                        $(this).dialog("close");
                    },
                    "关闭" : function(){
                        $(this).dialog("close");
                    }
                }
            });
        });
        $("#reload").click(function(){
        	var condictionIssueTypeDomainName = '${issueTypeDomainName }';

            $("#securitytroubleList").setGridParam({
                url:'${path}/securitytrouble/searchSecuritytroubleManage/searchSecuritytrouble.action',
                datatype: "json",
                page:1
            });
            $("#securitytroubleList").setPostData({
                "orgId":getCurrentOrgId(),
                "searchSecuritytroubleVo.issueTypeDomainName":condictionIssueTypeDomainName
            });
            $("#securitytroubleList").trigger("reloadGrid");
        });
    });

    function onOrgChanged(orgId){
    	searchSecuritytrouble();
    }

    function statusFormatter(el, options, rowData){
        if(rowData.status != null){
            if(1 == rowData.status){
                return "待受理";
            }else if(2 == rowData.status){
                return "处理中";
            }else if(3 == rowData.status){
                return "已完成";
            }
        }
    }

    function currentUserFormatter(el, options, rowData){
        if(rowData.currentUserName != null){
            return rowData.currentUserName;
        }
    }

    function searchSecuritytrouble(){
    	var fromPersonCondition=$("#conditionFromPerson").val();
        var inputFromCondition=$("#conditionInputFrom").val();
        var inputEndCondition=$("#conditionInputEnd").val();
        var nameCondition=$("#conditionName").val();
        var conditionStatus=$("#conditionStatus").val();
        var conditionIssueNo = $("#conditionIssueNo").val();
        var condictionIssueTypeId = $("#issueTypeId").val();
        var condictionIssueTypeDomainName = '${issueTypeDomainName }';

        $("#securitytroubleList").setGridParam({
            url:'${path}/securitytrouble/searchSecuritytroubleManage/searchSecuritytrouble.action',
            datatype: "json",
            page:1
        });
        $("#securitytroubleList").setPostData({
            "orgId":getCurrentOrgId(),
            "searchSecuritytroubleVo.name":nameCondition,
            "searchSecuritytroubleVo.inputFrom":inputFromCondition,
            "searchSecuritytroubleVo.inputEnd":inputEndCondition,
            "searchSecuritytroubleVo.fromPerson":fromPersonCondition,
            "searchSecuritytroubleVo.status":conditionStatus,
            "searchSecuritytroubleVo.issueNo":conditionIssueNo,
            "searchSecuritytroubleVo.issueTypeId":condictionIssueTypeId,
            "searchSecuritytroubleVo.issueTypeDomainName":condictionIssueTypeDomainName
        });
        $("#securitytroubleList").trigger("reloadGrid");
    }
</script>
