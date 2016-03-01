<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
table tr td {
	border: 1px;
	border-color: black;
	margin: 0;
	padding: 0;
}
</style>
<input type="hidden" id="orgIdP" value="<s:property value='#parameters.orgId'/>"/>
<input type="hidden" id="reoprtDateTypeIdP" value="<s:property value='#parameters.reoprtDateTypeId'/>"/>
<input type="hidden" id="yearP" value="<s:property value='#parameters.year'/>"/>
<input type="hidden" id="monthP" value="<s:property value='#parameters.month'/>"/>
<input type="hidden" id="internalIdP" value="<s:property value='#parameters.internalId'/>"/>
<div class="content" id="statRegradedPointsPrint">
    <div style="width:100%">
        <table id="statRegradedPointsPrintList"></table>
    </div>
    <div id="statRegradedPointsPrintDialog"></div>
</div>
<script type="text/javascript">
    $(function(){
        $("#statRegradedPointsPrintList").jqGridFunction({
            datatype: "local",
            sortname:'vOrg.id',
            sortorder:"asc",
            colModel:[
                {name:"org.id",key:true,hidden:true},
                {name:"org",hidden:true},
                {name:"orgName",label:"单位",index:"vOrg.id",width:135,align:"center"},
                {name:"yellowAmout",label:"黄牌扣分",align:"right",sortable:false,width:70,align:"center"},
                {name:"redAmout",label:"红牌扣分",align:"right",sortable:false,width:70,align:"center"},
                {name:"deductPointByPerson",label:"人工扣分",align:"right",sortable:false,width:70,align:"center"},
                {name:"addPointByPerson",label:"人工加分",align:"right",sortable:false,width:70,align:"center"},
                {name:"assessmentUser",label:"其他打分",align:"right",sortable:false,width:70,align:"center"},
                {name:"",label:"基准分",align:"right",sortable:false,width:70,align:"center",formatter:referenceValue},
                {name:"amoutPoint",label:"总得分",align:"right",sortable:false,width:70,align:"center",formatter:defaultValue}
            ],
            rowNum:Math.pow(2,31)-1,
            showColModelButton:false
        });
        
        if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
            onOrgChanged(getCurrentOrgId(),isGrid());
        }
    });

    function onOrgChanged(orgId,isgrid){
        $("#statRegradedPointsPrintList").setGridParam({
            url:"${path}/statAnalyse/statRegradedPointManage/statRegradedPoints.action",
            datatype:"json"
        });
        $("#statRegradedPointsPrintList").setPostData({
            "year":function(){return $("#yearP").val();},
            "month":function(){return $("#monthP").val();},
            "reoprtDateType.id":function(){return $("#reoprtDateTypeIdP").val();},
            "internalId":function(){return $("#internalIdP").val();},
            "targeOrgId":function(){return orgId;}
        });
        $("#statRegradedPointsPrintList").trigger("reloadGrid");
    }

    function defaultValue(el,options,rowData){
        if(rowData.amoutPoint == null || rowData.amoutPoint < 0)
            return 0;
        else 
            return rowData.amoutPoint;
    }

    function auditedFormater(el,options,rowData){
        if(rowData.audited)
            return "已审核";
        else
            return "未审核";
    }

    function referenceValue(){
        return 100;
    }
</script>