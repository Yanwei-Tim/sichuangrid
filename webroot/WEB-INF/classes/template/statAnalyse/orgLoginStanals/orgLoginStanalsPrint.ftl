<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<style type="text/css">

	#MainDialog{
		overflow-x:hidden;
	}
	
	#orgLoginStanalsGridbox{
		overflow: auto;
		height:245px;
		width:100%;
	}
    .printTable_title{
		margin:0 5px;background: #E7EDF5;
		border-top: 1px solid #ccc;border-left:1px solid #ccc;
		border-right: 1px solid #ccc;font-size: 12px;height: 28px;line-height: 28px;
		padding-top: 2px;font-weight:bold;color: #333;text-align:center;
		font-size:16px;width:970px;border-bottom:1px solid #ccc;
	}
	.SigmaReportPrint table{
		width:595px;  
		border-collapse:collapse;
	}
	.SigmaReportPrint td{
		font-weight:normal;
		color:#333;
	}
	.SigmaReportPrint .head{
	    width:595px;
		margin:0 5px;
		border-left:1px solid #ccc;
	}
	
	.SigmaReportPrint .body{
		margin:0 5px;
		border-left:1px solid #ccc;
	}
	
	.SigmaReportPrint .body tr.selected{
		background-color:#CCE4F9;
	}
	.SigmaReportPrint .body tr.disabled{
		background:#F0EDED;
		color:#CECECE;
	}
	
	.SigmaReportPrint .body tr.hover{
		background-color:rgb(255,255,151);
	}
	
	.SigmaReportPrint .scroll{
	}
	
	.SigmaReportPrint .body td{
		border-right:1px solid #ccc;
		border-bottom:1px solid #ccc;
		font-size:12px;
		height:20px;
		padding:0px;
		text-align:center;
		color:#333;
	}
	
	.SigmaReportPrint .body input{
		font-size:12px;
		border:0px solid red;
	}
	.SigmaReportPrint  input{
		font-size:12px;
		border:0px solid red;
	}
	
	.SigmaReportPrint .body div.focused{
		background-color:rgb(255,250,255);
	}
	
	.SigmaReportPrint .body div{
		white-space:nowrap;
		padding:3px;
		display:block;
		text-align:center;
	}
	.SigmaReportPrint .body div.checked{
		width:16px;
		height:16px;
		border:1px solid red;
		background-image:url(right.gif);
		background-repeat:no-repeat;
	}
	
	.SigmaReportPrint .head td{
		background:#e7edf5;
		border-right:1px solid #ccc;
		border-bottom:1px solid #ccc;
		font-size:12px;
		height:28px;
		line-height:28px;(
		overflow:hidden;
		padding-top:2px;
	}
	
	
	.SigmaReportPrint .head div.title{
		padding-top:2px;
		float:left;
		height:18px;
		overflow:hidden;
		white-space:nowrap;
	}
</style>

<div id="orgLoginStanalsGridbox" class="SigmaReportPrint"></div>

<script type="text/javascript">
$(document).ready(function(){
	var nowMonth = $("#mowMonth").val();
	
	//$("#title_enterPriseGridbox").html(data.orgName+str+"报表");
	onOrgChangedForPrint();
	$("#title_orgLoginStanalsGridbox").children().remove();
	
	var theBox	 = $('#orgLoginStanalsGridbox'),
	box_tit 	 = theBox.find('#title_orgLoginStanalsGridbox'),
	box_head 	 = theBox.find('#head_orgLoginStanalsGridbox'),
	box_body 	 = theBox.find('#body_orgLoginStanalsGridbox');

	box_tit.width(970);
	box_head.width(970);
	box_body.width(970);

	if(nowMonth!=1 && nowMonth!=5 && nowMonth!=7 && nowMonth!=10){
		box_tit.width(830);
		box_head.width(830);
		box_body.width(830);
	}
});
function reloadReportDateForPrint(){
	$.ajax({
		async: false,
		url: '${path}/statAnalyse/orgLoginStanalsManager/findOrgLoginStanalsByOrgIdForListPage.action',
		data:{
			"nowYear": '<@s.property value="#parameters.nowYear"/>',
			"nowMonth":'<@s.property value="#parameters.nowMonth"/>',
			"orgId":'<@s.property value="#parameters.orgId"/>',
			"internalId":'<@s.property value="#parameters.internalId"/>'
		},
		success:function(responseData){
			rebuildeGridForPrint(responseData);
		}
	});
}
var blueRender = new function(){
	this.paint = function(grid,row,col){
		var data = row.getCellValue(col);
		if(data===null || data==="" || data===undefined) date= "0";
		if(row.getCellValueByColName('workday_month')==row.getCellValueByColName('loggedday_month'))
			return "<span style='color:green;font-weight:bold;'>"+data+"</span>";
		else
			return ""+data+"";
	}
}

var redRender = new function(){
	this.paint = function(grid,row,col){
		var data = row.getCellValue(col);
		if(data===null || data==="" || data===undefined) date= "0";
		if(data<3)
			return "<span style='color:red;font-weight:bold;'>"+data+"</span>";
		else
			return ""+data+"";
	}
}

function rebuildeGridForPrint(reportData){
   	var context = {};
   	var columns = [		
   			{name:"bigTitle",caption:reportData.bigTitle,children:[
				{name:"orgName",caption:"组织结构名称",width:100,mode:"string"},                                                   
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
   	orgLoginStanalsGrid = new SigmaReport("orgLoginStanalsGridbox",context,columns, "SigmaReportPrint","printTable_title");
	//grid = new SigmaReport("orgLoginStanalsGridbox",context,columns);
	orgLoginStanalsGrid.registerRender("blueRender",blueRender);
	orgLoginStanalsGrid.registerRender("redRender",redRender);
	orgLoginStanalsGrid.bindData(reportData.objectDataList);
}

function onOrgChangedForPrint(){
        $.ajax({
            url:"${path}/statAnalyse/statRegradedPointManage/nextOrgLevelNameByOrgId.action",
            data:{
        			"targeOrgId":function(){return $("#orgId").val();}
                },
            success:function(data){
                    //$("#nextOrgLoginStanalsName").html(data+"登录统计");
                }
        });
        reloadReportDateForPrint();
}

</script>