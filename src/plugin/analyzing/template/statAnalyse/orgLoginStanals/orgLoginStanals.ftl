<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="/common/orgSelectedComponent.jsp"/>

<style type="text/css">
#load {position:relative;}
#load .ui-button{width:80px;height:25px;}
#load .ui-dialog-buttonset{position:absolute;bottom:20px;right:15px;}
</style>
<div id="nav" class="ui-corner-all">
        <input type="hidden" id="internalId" value='<@s.property value="#parameters.internalId"/>'/>
         <div class="grid_4 form-right">
             <label style="float:left;line-height:25px;padding-right:10px;">年度：</label>
         </div>
         <div class="grid_8 form-left">
          <select name="statYear" id="statYear">
          </select>
         </div>
         <div class="grid_4 form-right">
             <label style="float:left;line-height:25px;padding:0 10px;">月份：</label>
         </div>
         <div class="grid_8 form-left">
          <select name="statMonth" id="statMonth" onchange="onMonthChange()">
          </select>
         </div>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<@s.if test="#parameters.internalId[0]==@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION">
			<@pop.JugePermissionTag ename="exportAdminLoginStanals">
				<a id="export" href="javascript:void(0)"><span>导出</span></a>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="searchAdminLoginStanals">
			    <a id="search" href="javascript:void(0)"><span>查询</span></a>
			</@pop.JugePermissionTag>
		</@s.if>
		<@s.else>
			<@pop.JugePermissionTag ename="exportFuncationLoginStanals">
				<a id="export" href="javascript:void(0)"><span>导出</span></a>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="searchFuncationLoginStanals">
			    <a id="search" href="javascript:void(0)"><span>查询</span></a>
			</@pop.JugePermissionTag>		
		</@s.else>
</div>
<div class="container_24">
	<div style="clear: both;"></div>
	<div id="gridbox" class="SigmaReport" style="overflow-x:hidden;overflow-y:auto !important;position:relative;height: 440px;width:100%"></div>
</div>
<div id="MainDialog"></div>
<script type="text/javascript">
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#statYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#statMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
}
$(document).ready(function(){

	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#statYear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
			getmonth();
		}
	});
	$("#internalId").val(${internalId});
	
	
	if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
		setTimeout(function(){
			onOrgChanged();
		},100)
	}

	$("#reload").click(function(event){
	    $("#statYear").val(${(nowYear)!?c});
	    $("#statMonth").val(${nowMonth})
		$("#internalId").val(${internalId});
		onOrgChanged();
	});

	$("#search").click(function(event){
		if(!$("#statYear").attr("disabled") && $("#statMonth").attr("disabled")){
			onOrgChanged();
			return ;
		}
		if(!$("#statYear").attr("disabled") && $("#statMonth").attr("disabled")){
	    	$.notice({
				level:'warn',
				message:'请选择完整的查询时间'
			});
			return ;
		}
		onOrgChanged();
	});	
	$("#produceDate").click(function(event){
		$("#MainDialog").createDialog({
			width: 400,
			height: 200,
			title: "部门登录信息数据重新生成",
			url: "${path}/statAnalyse/orgLoginStanalsManager/dispatch.action?mode=produceDate"+"&orgId="+getCurrentOrgId(),
			buttons: {
		   		"生成" : function(event){
				//$("#maintainFormForReData").submit();
				produceStaticDate();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#statYear").change(function(){
		$("#statMonth").empty();
		getmonth();
	});
	$("#export").click(function(){
		var url = '${path}/statAnalyse/orgLoginStanalsManager/downloadOrgLoginStanals.action?orgId='+getCurrentOrgId()+'&nowYear='+$("#statYear").val()+'&nowMonth='+$("#statMonth").val()+'&internalId='+ $("#internalId").val();
		downloadFile(url);
	});
	function downloadFile(url){  
	    var elemIF = document.createElement("iframe");  
	    elemIF.src = url;  
	    elemIF.style.display = "none";  
	    document.body.appendChild(elemIF);  
	}
});

function reloadReportDate(){
	$.ajax({
		async: false,
		url: '${path}/statAnalyse/orgLoginStanalsManager/findOrgLoginStanalsByOrgIdForListPage.action',
		data:{
			"nowYear":$("#statYear").val(),
			"nowMonth":$("#statMonth").val(),
			"orgId":getCurrentOrgId(),
			"internalId":function(){return $("#internalId").val()}
		},
		success:function(responseData){
			rebuildeGrid(responseData);
		}
	});
}
var blueRender = new function(){
	setTimeout('this.paint',100);
	this.paint = function(grid,row,col){
		var data = row.getCellValue(col);
		if(data===null || data==="" || data===undefined) date= "0";
		if(row.getCellValueByColName('workday_month')==row.getCellValueByColName('loggedday_month'))
			return "<span style='color:red;font-weight:bold;'>"+data+"</span>";
		else
			return "<span style='color:red;font-weight:bold;'>"+data+"</span>";
	}
}

var redRender = new function(){
	setTimeout('this.paint',100);
	this.paint = function(grid,row,col){
		var data = row.getCellValue(col);
		if(data===null || data==="" || data===undefined){
			date= "0";
		}
		if(data<3){
			return "<span style='color:red;font-weight:bold;'>"+data+"</span>";
		}else{
			return "<span style='color:red;font-weight:bold;'>"+data+"</span>";
		}
	}
}

function rebuildeGrid(reportData){
   	var context = {};
   	var columns = [		
   			{name:"bigTitle",caption:reportData.bigTitle,children:[
				{name:"orgName",caption:"组织结构名称",width:100,mode:"string"}, 
				{name:"name",caption:"用户名",width:100,mode:"string"},                                                  
				{name:"helpinfo",caption:reportData.columnCaption[0],
					children:[{name:"workday_month",caption:"工作天数",width:85,mode:"number",format:"#"},
				     	     {name:"loggedday_month",caption:"登录天数",width:85,mode:"blueRender",format:"#"}]
				},
				{name:"helpinfo",caption:reportData.columnCaption[1],
					children:[{name:"workday_week1",caption:"工作天数",width:70,mode:"number",format:"#"},
					          {name:"loggedday_week1",caption:"登录天数",width:70,mode:"redRender",format:"#"}]
				},
				{name:"helpinfo",caption:reportData.columnCaption[2],
					children:[{name:"workday_week2",caption:"工作天数",width:70,mode:"number",format:"#"},
					          {name:"loggedday_week2",caption:"登录天数",width:70,mode:"redRender",format:"#"}]
				},	
				{name:"helpinfo",caption:reportData.columnCaption[3],
					children:[{name:"workday_week3",caption:"工作天数",width:70,mode:"number",format:"#"},
					          {name:"loggedday_week3",caption:"登录天数",width:70,mode:"redRender",format:"#"}]
				},	
				{name:"helpinfo",caption:reportData.columnCaption[4],
					children:[{name:"workday_week4",caption:"工作天数",width:70,mode:"number",format:"#"},
					          {name:"loggedday_week4",caption:"登录天数",width:70,mode:"redRender",format:"#"}]
				},	
				{name:"helpinfo",caption:reportData.columnCaption[5],
					children:[{name:"workday_week5",caption:"工作天数",width:70,mode:"number",format:"#"},
					          {name:"loggedday_week5",caption:"登录天数",width:70,mode:"redRender",format:"#"}]
				}
   			]}			
   		];
   	for(n=5;n>=0;n--){
   		if(reportData.columnCaption[n]==null)
   			columns[0]['children'].pop();
   	}

	grid = new SigmaReport("gridbox",context,columns);
	grid.registerRender("blueRender",blueRender);
	grid.registerRender("redRender",redRender);
	grid.bindData(reportData.objectDataList);
}

function onOrgChanged(){
        $.ajax({
            url:"${path}/statAnalyse/statRegradedPointManage/nextOrgLevelNameByOrgId.action",
            data:{
        			"targeOrgId":function(){return getCurrentOrgId();}
                },
            success:function(data){
                   // $("#nextOrgLoginStanalsName").html(data+"部门登录统计");
                    if (needShowFunctionOrgMenu(data)){
                        $("#orgLoginStanalsFunMenu").show();
                    }else{
                        $("#orgLoginStanalsFunMenu").hide();
                        $("#nextOrgLoginStanalsName").click();
                    }
                }
        });
	reloadReportDate();
	toPrintPage();
	
}
function toPrintPage(){
	$(".print").bind("click",function(){
		$("#MainDialog").createDialog({
			width:950,
			height:350,
			title:"打印登陆统计",
			url:'${path}/statAnalyse/orgLoginStanals/orgLoginStanalsPrint.jsp?orgId='+getCurrentOrgId()+'&nowYear='+$("#statYear").val()+'&nowMonth='+$("#statMonth").val()+'&internalId='+$("#internalId").val(),
			buttons: {
			   "打印" : function(){
				print();
		  	   },
			   "关闭" : function(){
			        $("#MainDialog").dialog("close");
			   }
			}
		});
	});
}

function needShowFunctionOrgMenu(orgLevel){
	return orgLevel!="村(社区)" && orgLevel!="片格";
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
function onMonthChange(){
	if($("#statYear").val() >= ${(nowYear)!?c} && $("#statMonth").val() >= ${nowMonth})
        $("#audit").buttonDisable();
}


var ticketId='';
var times = 1;
function produceStaticDate(){
	
	if($("#replaceYear").val() == 0 || $("#replaceMonth").val() == 0){
		$.messageBox({message:"请输入重新生成的年份和月份"});
		return ;
	}

	var orgId = getCurrentOrgId();
	$.ajax({
		async:false,
		url:"${path}/statAnalyse/orgLoginStanalsManager/reProduceDate.action",
		data:{
			"nowYear":$("#replaceYear").val(),
			"nowMonth":$("#replaceMonth").val(),
			"orgId":orgId,
			"internalId":function(){return $("#internalId").val()}
		},
		success:function(data){
			 var dataJson=eval("("+data+")");
			 $("body").mask("<div id='load' class='ui-widget-border' ><div style='margin-top:80px;'>&nbsp;</div><p id='progressbar-msg' style='margin-left:60px;text-align:left;'>正在生成数据，数据生成需要较长时间，请耐心等待.</p></div>");
			 ticketId = dataJson.ticketId;
             intarcalId = setInterval(getTicketByIds,500);
		}
	});
}
function getTicketByIds(){
    $.ajax({
        url: '${path}/ticket/getTicketById.action',
        type: 'post',
        dataType:'json',
        data:{
            "ticketId":ticketId
        },
        success: function(data){
        	var dataJson=eval("("+data.description+")");
            $("#progressbar-msg").text(dataJson.msg);
            if(dataJson&&dataJson.errorMsg){
                clearInterval(intarcalId);
                $("body").unmask();
                var errorHtml = "<div id='load' class='ui-widget-border'>"+
                                    "<div class='ui-widget' style='margin-top:30px;' id='errorUploadMsgs'>"+
                                    "</div>"+
                                    "<div class='clear'></div>"+
                                    "<div class='ui-dialog-buttonset'><button id='reloadExcel' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>重新生成</button><button id='cancel' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>关闭</button></div>"+
                                "</div>";
                $("body").mask(errorHtml);
                var str="<div class='ui-state-error ui-corner-all' style='width:400px;color:#CC0A0C;margin-top:5px;text-align:left;margin-left:5px'><em class='ui-icon ui-icon-info' style='float: left; margin-right: .3em;'></em>"+dataJson.errorMsg+"</div>";
                $("#errorUploadMsgs").html(str);
                $('#cancel').bind('click', function () {$("#MinDialog").dialog("close");$('body').unmask(); });
                $('#reloadExcel').bind('click',function(){
                	$("#MinDialog").dialog("close");
                    $('body').unmask();
                    $("#produceDate").click();
                });
            }
            if(dataJson.successMsg){
                clearInterval(intarcalId);
                $("body").unmask();
                var succHtml = "<div id='load' class='ui-widget-border'>"+
                                "<div class='ui-widget' style='margin-top:30px;'>"+
                                    "<div class='ui-corner-all ui-widget-border ui-state-highlight' style='width:400px;margin-top:5px;text-align:left;margin-left:5px;'><em class='ui-icon ui-icon-circle-check' style='float: left; margin-right: .3em;'></em>数据生成成功</div>"+
                                "</div>"+
                                "<div class='clear'></div>"+
                                "<div class='ui-dialog-buttonset'><button id='closeImportData' type='button' class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only'>关闭</button></div>"+
                                "</div>";
                $("body").mask(succHtml);
                $('#closeImportData').bind('click', function () {$(this).dialog("close");onOrgChanged(getCurrentOrgId(),isGrid());$('body').unmask();    });
            }
        }
    }); 
}


function print(){
	$("#orgLoginStanalsPrint").printArea();
	$("#MainDialog").dialog("close");
}
</script>