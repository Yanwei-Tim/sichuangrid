TQ.addVolunteerJobs = function (dfop){
	var orgId=dfop.orgId;
	var flag = false;

	$(document).ready(function() {
		$("#memberHasVolunteerJobsList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		    colNames:['id','orgId','志愿者岗位','专长要求','拟认领数'],
		   	colModel:[
		   	    {name:"id",index:"id",hidden:true},
		        {name:"organization.id",index:"organization.id",hidden:true},
		        {name:'name',index:"name",width:100,align:"center"},
		        {name:'content',index:"content",width:100,align:"center"},
		        {name:'claimPlans',index:'claimPlans',width:150,align:"center"}
			],
			height:102,
			multiselect:true,
			onSelectAll:function(data){
				setStr();
		  	},
			onSelectRow:function(data){
				setStr();
			},
			showColModelButton:false
		});
		jQuery("#memberHasVolunteerJobsList").jqGrid('setFrozenColumns');
		$("#memberHasVolunteerJobsList").setGridParam({
			url:dfop.path+'/volunteerjobsManage/findVolunteerJobsPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#memberHasVolunteerJobsList").setPostData({
			"organization.id": orgId,
			"searchvolunteerJobsVo.name":''
	   	});
		$("#memberHasVolunteerJobsList").trigger("reloadGrid");
		
		
		$("#searchServiceTargetTeam").live("change",function(){
			var teamId = $(this).val();
			if(teamId<0){
				$("#teamId").val("-1");
				$("#sp_name").html("");
				$("#sp_memberNums").html("");
				$("#sp_establishDate").html("");
				$("#sp_remark").html("");
				return false;
			}
		});
		
		$("#_fastSearch").click(function(){
			var condition = $("#_deptName").val();
			var listParam = null;
			if('快速搜索项目名称' != condition) {
				listParam = {
					"organization.id":orgId,
					"searchvolunteerJobsVo.name":condition
				};
			}else listParam = {
					"organization.id":orgId,
					"searchvolunteerJobsVo.name":""
				};
			loadTargetTeams(listParam);
		});
		
		function loadTargetTeams(listParam){
			$("#memberHasVolunteerJobsList").setGridParam({
				url:dfop.path+'/volunteerjobsManage/findVolunteerJobsPagerBySearchVo.action',
				datatype: "json",
				page:1
			});
			$("#memberHasVolunteerJobsList").setPostData(listParam);
			$("#memberHasVolunteerJobsList").trigger("reloadGrid");
		}
		
	});


	function setStr(){
		var str="";
		var id="";
		var selectId = "";
		selectId=$("#memberHasVolunteerJobsList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectId.length;i++){
				var data=$("#memberHasVolunteerJobsList").getRowData(selectId[i]);
				str+=data.name+",";
				id+=data.id+",";
		}
		$("#memberVolunteerJobsName").val(str.substr(0,(str.length-1)));
		$("#memberVolunteerJobsIds").val(id.substr(0,(id.length-1)));
	}
}