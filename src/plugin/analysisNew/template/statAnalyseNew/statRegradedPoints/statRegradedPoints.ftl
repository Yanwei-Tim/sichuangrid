<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<div class="content">
    <div class="ui-corner-all" id="nav">
        <div  style="float:left;height:25px;line-height:25px;">
            <input type="hidden" id="internalId" value='<@s.property value="#parameters.internalId"/>'/>
            <div class="grid_4 form-right" style=" display:none">
                <label style="float:left;line-height:25px;padding-right:10px;">年度：</label>
            </div>
            <div class="grid_8 form-left">
	            <select name="statYear" id="statYear">
	                <@s.iterator begin="minYear" end="maxYear" var="newStatYear">
	                    <option value="${newStatYear }">${newStatYear }</option>
	               </@s.iterator>
	            </select>
            </div>
            <div class="grid_4 form-right" style=" display:none">
                <label style="float:left;line-height:25px;padding:0 10px;">月份：</label>
            </div>
            <div class="grid_8 form-left">
	            <select name="statMonth" id="statMonth" onchange="onMonthChange()">
	                <@s.iterator begin="1" end="12" var="newStatMonth">
		                <option value="${newStatMonth }">${newStatMonth }</option>
		           </@s.iterator>
	            </select>
            </div>
            <div class="grid_8 form-left">&nbsp;</div>
            <a id="search" href="javascript:void(0)"><span>查询</span></a>
            <@pop.JugePermissionTag ename="addAdministrativeRegradedPoint,addFunctionalRegradedPoint">
                <a id="add" href="javascript:void(0)">添加打分记录</span></a>
            </@pop.JugePermissionTag>
            <@pop.JugePermissionTag ename="viewAdministrativeRegradedPoints,viewFunctionalRegradedPoints">
                <a id="view" href="javascript:void(0)"><span>查看详情</span></a>
            </@pop.JugePermissionTag>
            <@pop.JugePermissionTag ename="auditAdministrativeRegradedPoint,auditFunctionalRegradedPoint">
                <a id="audit" href="javascript:void(0)"><span>审核</span></a>
            </@pop.JugePermissionTag>
            <a id="reload" href="javascript:void(0)"><span>刷新</span></a>
        </div>
    </div>
    <div style="width:100%">
        <table id="statRegradedPointsList"></table>
    </div>
    <div id="statRegradedPointsDialog"></div>
</div>
<script type="text/javascript">
    $(function(){
    	$("#statYear").val(${nowYear});
    	//onYearChange();
    	$("#statMonth").val(${nowMonth});
        $("#statRegradedPointsList").jqGridFunction({
            datatype: "local",
            sortname:'vOrg.id',
            sortorder:"asc",
            colModel:[
                {name:"org.id",key:true,hidden:true},
                {name:"org",hidden:true},
                {name:"orgName",label:"单位",index:"vOrg.id",width:120},
                {name:"yellowAmout",label:"黄牌扣分",align:"right",sortable:false,width:90},
                {name:"redAmout",label:"红牌扣分",align:"right",sortable:false,width:90},
                {name:"deductPointByPerson",label:"人工扣分",align:"right",sortable:false,width:90},
                {name:"addPointByPerson",label:"人工加分",align:"right",sortable:false,width:90},
                {name:"assessmentUser",label:"其他打分",align:"right",sortable:false,width:90},
                {name:"",label:"基准分",align:"right",sortable:false,width:90,formatter:referenceValue},
                {name:"amoutPoint",label:"总得分",align:"right",sortable:false,formatter:defaultValue,width:90},
                {name:"audited",label:"是否已审核",sortable:false,formatter:auditedFormater}
            ],
            showColModelButton:false,
            ondblClickRow:viewRegradedDetail,
            loadComplete:loadAddButton,
            onSelectRow:showButtons
        });
        if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
            onOrgChanged(getCurrentOrgId(),isGrid());
        }
        $("#search").click(function(){
            onOrgChanged(getCurrentOrgId(),isGrid());
        });
        $("#reload").click(function(){
            $("#statRegradedPointsList").trigger("reloadGrid");
        });

        $("#add").click(function(event){
            var selectId = $("#statRegradedPointsList").getGridParam("selrow");
            if(selectId == null){return;}
            var statRegradedPoint = $("#statRegradedPointsList").getRowData(selectId);
            if(statRegradedPoint.audited && statRegradedPoint.audited == "已审核"){return;}
            $("#statRegradedPointsDialog").createDialog({
                width:450,
                height:240,
                title:"给部门打分",
                url:"${path}/regradedPoints/regradedPointsManage/dispatch.action",
                postData:{
                    orgId:selectId
                },
                buttons:{
                    "保存" : function(){
                    	$("#maintainForm").submit();
                    },
                    "关闭" : function(){
                        $(this).dialog("close");
                    }
                }
            });  
        });
        $("#view").click(function(event){
        	viewRegradedDetail();
        });

        $("#audit").click(function(event){
            var selectId = $("#statRegradedPointsList").getGridParam("selrow");
            if(selectId == null){return;}
            var statRegradedPoint = $("#statRegradedPointsList").getRowData(selectId);
            if(statRegradedPoint.audited && statRegradedPoint.audited == "已审核" || ($("#statYear").val() >= ${nowYear} && $("#statMonth").val() >= ${nowMonth})){return;}
            $.confirm({
                title:"确认审核部门：" + statRegradedPoint.orgName + "的得分",
                message:"该部门得分被审核后，将不再允许对该部门进行打分!",
                width:400,
                okFunc:auditStatRegradedPoint
            });
        });
    });

    function viewRegradedDetail(){
    	var selectId = $("#statRegradedPointsList").getGridParam("selrow");
        if(selectId == null){return;}
        $("#statRegradedPointsDialog").createDialog({
            width:900,
            height:500,
            title:"查看打分详情",
            url:"${path}/statAnalyse/statRegradedPoints/viewSubDetailList.jsp?statDate=" + getStatDate() + "&orgId=" + selectId,
            buttons:{
                "关闭" : function(){
                    $(this).dialog("close"); 
               }
            },
            shouldEmptyHtml:false
        });
    }

    function onOrgChanged(orgId,isgrid){
        $("#statRegradedPointsList").setGridParam({
            url:"${path}/statAnalyse/statRegradedPointManage/statRegradedPoints.action",
            datatype:"json"
        });
        $("#statRegradedPointsList").setPostData({
            "year":function(){return $("#statYear").val();},
            "month":function(){return $("#statMonth").val();},
            "internalId":function(){return $("#internalId").val()},
            "targeOrgId":function(){return orgId;}
        });
        $("#statRegradedPointsList").trigger("reloadGrid");
    }

    function defaultValue(el,options,rowData){
        if(rowData.amoutPoint == null || rowData.amoutPoint < 0)
            return 0;
        else 
            return rowData.amoutPoint;
    }

    function getStatDate(){
    	var statMonth;
    	if($("#statMonth").val()<10){
        	statMonth="0"+$("#statMonth").val();
        }else{
            statMonth=$("#statMonth").val();
        }
        return $("#statYear").val() + "" + statMonth;
    }

    function loadAddButton(){ 
        $("#view").buttonDisable();
        $("#add").buttonDisable();
        $("#audit").buttonDisable();
    }

    function showButtons(){
        $("#view").buttonEnable();
        var selectId = $("#statRegradedPointsList").getGridParam("selrow");
        if(selectId == null){return;}
        var statRegradedPoints = $("#statRegradedPointsList").getRowData(selectId);
        
        if(statRegradedPoints.audited && statRegradedPoints.audited == "已审核"){
        	$("#add").buttonDisable();
            $("#audit").buttonDisable();
        }else{
        	$("#add").buttonEnable();
        	if($("#statYear").val() >= ${nowYear} && $("#statMonth").val() >= ${nowMonth})
        		$("#audit").buttonDisable();
        	else
        	    $("#audit").buttonEnable();
        }
    }

    function auditedFormater(el,options,rowData){
        if(rowData.audited)
            return "已审核";
        else
            return "未审核";
    }

    function auditStatRegradedPoint(){
    	var selectId = $("#statRegradedPointsList").getGridParam("selrow");
        if(selectId == null){return;}
        var statRegradedPoint = $("#statRegradedPointsList").getRowData(selectId);
        $.ajax({
            url:"${path}/statAnalyse/statRegradedPointManage/auditStatRegradedPoints.action",
            data:{
            	    "statRegradedPoint.parentOrg.id":getCurrentOrgId(),
            	    "statRegradedPoint.org.id":selectId,
            	    "statRegradedPoint.orgName":statRegradedPoint.orgName,
            	    "statRegradedPoint.yellowAmout":statRegradedPoint.yellowAmout,
            	    "statRegradedPoint.redAmout":statRegradedPoint.redAmout,
            	    "statRegradedPoint.deductPointByPerson":statRegradedPoint.deductPointByPerson,
            	    "statRegradedPoint.addPointByPerson":statRegradedPoint.addPointByPerson,
            	    "statRegradedPoint.assessmentUser":statRegradedPoint.assessmentUser,
            	    "statRegradedPoint.amoutPoint":statRegradedPoint.amoutPoint,
            	    "statRegradedPoint.audited":true,
            	    "statRegradedPoint.year":function(){return $("#statYear").val();},
            	    "statRegradedPoint.month":function(){return $("#statMonth").val();}
                },
            success:function(data){
                	$("#statRegradedPointsList").setRowData(selectId,data);
                    showButtons();
                }
        });
    }

    function onMonthChange(){
    	if($("#statYear").val() >= ${nowYear} && $("#statMonth").val() >= ${nowMonth})
            $("#audit").buttonDisable();
    }

    function referenceValue(){
        return 100;
    }
//-->
</script>