TQ.memberList = function (dfop){
	function loadMemberData(conditions) {
		var initParam = {
				"member.partyOrgType":partyOrgType,
				"member.orgId":$("#currentOrgId").val()
			}
		if(typeof(conditions) == 'undefined'||conditions==null){
		} else {
			$.extend(initParam,{
				"member.idCardNo":conditions
			})
		}
		
		$("#memberList").setGridParam({
			url:dfop.path+"/partyBuildng/memberManage/findMembersForPageByCondition.action",
			datatype: "json",
			page:1
		});
		$("#memberList").setPostData(initParam);
		$("#memberList").trigger("reloadGrid");
	}

	function search(){
		var conditions = $("#searchTextMember").val();
		if(conditions == '请输入姓名或身份证号码') return;
		loadMemberData(conditions);
	}

	function addressFormatter(el, options, rowData){
		if(rowData.province==null){
			return '';
		}
		if(rowData.city==null){
			return rowData.province;
		}
		if(rowData.district==null){
			return rowData.province+'-'+rowData.city;
		}
		return rowData.province+'-'+rowData.city+'-'+rowData.district;
	}

	function nameMemberFormatter(el, options, rowData){
		return "<a href='javascript:viewMember("+rowData.id+")'><U><font color='#6633FF'>"+rowData.name+"</font></U></a>";
	}

	$(function (){
		if(USER_ORG_ID!=getCurrentOrgId()) {
			$("#importMember").hide();
		} else {
			$("#importMember").show();
		}
		$("#memberList").jqGridFunction({
			datatype: "local",
			height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-$(".ui-tabs-nav").outerHeight()-100,
		   	colModel:[
		        {name:"id",index:"id",hidden:true,sortable:false},
		        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		        {name:"idCardNo",sortable:true,label:'身份证号',width:150},
		        {name:"name",sortable:true,label:'姓名',width:100,formatter:nameMemberFormatter},
		        {name:"gender",sortable:true,label:'性别',width:100,formatter:genderFormatter},
		        {name:"address",sortable:false,label:'户籍地',width:180,formatter:addressFormatter},
		        {name:"nativePlaceAddress",sortable:false,label:'现居地址',width:180},
		        {name:"partyOrg",sortable:true,label:'所在党支部',width:180}
			],
			multiselect: true,
		    ondblClickRow: viewMember
		});
		
		$("#fastSearchButtonMember").click(function(){
			search();
		});
		$("#searchTextMember").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKeyMember").click(function(){
			$("#searchTextMember").attr("value","请输入身份证号码");
		});
		
		$("#viewMember").click(function(){
			if($("#viewMember").attr("disabled")){
				return ;
			}
			var selectedIds = $("#memberList").jqGrid("getGridParam", "selarrrow");
			var selectedId = getMemberSelectedIdLast();
			if(selectedId==null || selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			viewMember(selectedId);
		});
		
		loadMemberData();
		
		$("#reloadMember").click(function(){
			$("#searchTextMember").attr("value","请输入身份证号码");
			loadMemberData();
		});
		
		$("#importMember").click(function(event){
			$("#memberDialog").createDialog({
				width: 400,
				height: 230,
				title:"数据导入",
				url:dfop.path+"/common/import.jsp?isNew=1&dataType=member&dialog=memberDialog&startRow=5&templates=member&listName=memberList&module="+getModule(),
				buttons:{
					"导入" : function(event){
						$("#mForm").submit();
					},
				   	"关闭" : function(){
				    	$(this).dialog("close");
				   	}
				},
				shouldEmptyHtml:false
			});
		});
	});

	function getModule() {
		if(partyOrgType==dfop.officePartyOrg){
			return dfop.officePartyOrg;
		}
		if(partyOrgType==dfop.townPartyOrg){
			return dfop.townPartyOrg;
		}
		if(partyOrgType==dfop.businessOrg){
			return dfop.businessOrg;
		}
		if(partyOrgType==dfop.collectiveOrg){
			return dfop.collectiveOrg;
		}
		if(partyOrgType==dfop.twoNewParty){
			return dfop.twoNewParty;
		}
		if(partyOrgType==dfop.twoNewPartyOrg){
			return dfop.twoNewPartyOrg;
		}
		if(partyOrgType==dfop.systemPartyOrg){
			return dfop.systemPartyOrg;
		}
		if(partyOrgType==dfop.regionPartyOrg){
			return dfop.regionPartyOrg;
		}
		if(partyOrgType==dfop.doubleReg){
			return dfop.doubleReg;
		}
	}

	function getMemberSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#memberList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}

	function getSelectedIds(){
		var selectedIds = $("#memberList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
}
