<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="loginManagePrint">
	<style type="text/css">
		#MainDialog{
			overflow-x:hidden;
		}
		
		#orgLoginManageGridbox{
			overflow: auto;
			height:245px;
			width:100%;
		}
	    .printTable_title{
			margin:0 5px;background: #E7EDF5;
			border-top: 1px solid #ccc;border-left:1px solid #ccc;
			border-right: 1px solid #ccc;font-size: 12px;height: 28px;line-height: 28px;
			padding-top: 2px;font-weight:bold;color: #333;text-align:center;
			font-size:16px;width:915px;border-bottom:1px solid #ccc;
		}
		.SigmaReportPrint table{
			width:915px;  
			border-collapse:collapse;
		}
		.SigmaReportPrint td{
			font-weight:normal;
			color:#333;
		}
		.SigmaReportPrint .head{
		    width:915px;
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

	<div id="orgLoginManageGridbox" class="SigmaReportPrint"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	onOrgChangedForPrint();
	$("#title_orgLoginManageGridbox").children().remove();
	
	var theBox	 = $('#orgLoginManageGridbox'),
	box_tit 	 = theBox.find('#title_orgLoginManageGridbox'),
	box_head 	 = theBox.find('#head_orgLoginManageGridbox'),
	box_body 	 = theBox.find('#body_orgLoginManageGridbox');

	box_tit.width(914);
	box_head.width(914);
	box_body.width(914);
});
function reloadReportDateForPrint(){
	$.ajax({
		async: false,
		url: '${path}/baseinfo/loginManageNew/findLoginManageForListPage.action',
		data:{
			"loginManage.year": '<@s.property value="#parameters.nowYear"/>',
			"loginManage.month":'<@s.property value="#parameters.nowMonth"/>',
			"loginManage.organization.id":'<@s.property value="#parameters.orgId"/>',
			"type":<@s.property value="#parameters.type"/>
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
				{name:"allLoginCount",caption:"总账号数",width:70,mode:"number",format:"#",align:"center"},
				{name:"threeMonthsLoginCount",caption:"1-3个月未登录",width:70,mode:"number",format:"#",align:"center"},
				{name:"outThreeMonthsLoginCount",caption:"3个月以上未登录",width:70,mode:"number",format:"#",align:"center"},	
				{name:"nerverLoginCount",caption:"从未登录",width:70,mode:"number",format:"#",align:"center"}
   			]}			
   		];
   	loginManageGrid = new SigmaReport("orgLoginManageGridbox",context,columns, "SigmaReportPrint","printTable_title");
	loginManageGrid.registerRender("blueRender",blueRender);
	loginManageGrid.registerRender("redRender",redRender);
	loginManageGrid.bindData(reportData.objectDataList);
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