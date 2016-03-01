
$.fn.extend({
	treeSelect:function(options){
		var self=$(this);
		var selfId=self.attr("id");
		var defaultOption={
			store:new Ext.data.SimpleStore({fields:[],data:[[]]}), 
		    editable:false, //禁止手写及联想功能
		    showClearButton:false,
		    mode: 'local', 
		    triggerAction:'all',
		    name:'org',
		    fieldLabel : '组织机构',
		    maxHeight: 300, 
		    maxWidth:250,
		    listWidth:250,
		    tpl: "<div id='"+selfId+"-div'><div id='"+selfId+"-tree' style='overflow:auto'></div></div>", //html代码
		    selectedClass:'', 
		    onSelect:Ext.emptyFn,
		    emptyText:'请选择...',
		    inputName:"",
		    allOrg:false,
		    url:false,
		    nodeUrl:false,
		    rootId:false,
		    inputCodeName:"",
		    isRootSelected:true,
		    isFuncDep:false,
		    directlySupervisor:false,
		    changeFun:function(node,e){
				
			}
		};
		
		function style(){
			$("#"+selfId+"-div").parent().parent().remove();
			self.width(self.width()-25);
			$("#"+selfId+"-div").bgiframe();
		};
		$.extend(defaultOption, options);
		style();
		var comboWithTooltip = new Ext.form.ComboBox(defaultOption);
		comboWithTooltip.applyTo(selfId);
		var url;
		if(defaultOption.url){
			url = defaultOption.url;
		}else{
			url = "/sysadmin/orgManage/firstLoadExtTreeData.action";
		}
		var nodeUrl;
		if(defaultOption.nodeUrl){
			nodeUrl = defaultOption.nodeUrl;
		}else{
			nodeUrl = "/sysadmin/orgManage/ajaxOrgsForExtTree.action";
		}
		var tree = null;
		if(defaultOption.treeFunc === 'initTree'){
			tree = $("#"+selfId+"-tree").initTree({
				shouldJugeMultizones:true,
				allOrg:defaultOption.allOrg,url:url,nodeUrl:nodeUrl,
				directlySupervisor:defaultOption.directlySupervisor,
				rootId:defaultOption.rootId,
				loadCom:defaultOption.loadCom,
				isFuncDep:defaultOption.isFuncDep,
				isRootSelected:defaultOption.isRootSelected
			});
		}else{
			tree = $("#"+selfId+"-tree").initAdministrativeTree({
				shouldJugeMultizones:true,
				allOrg:defaultOption.allOrg,url:url,nodeUrl:nodeUrl,
				directlySupervisor:defaultOption.directlySupervisor,
				rootId:defaultOption.rootId,
				loadCom:defaultOption.loadCom,
				isFuncDep:defaultOption.isFuncDep,
				isRootSelected:defaultOption.isRootSelected
			});
		}
		$("#"+selfId+"-div").height(defaultOption.maxHeight).width(defaultOption.listWidth);
		
		
		var construct=function(){
			var inputId = "org_autocomplete"+selfId;
			if(defaultOption.showClearButton)
				$("#"+selfId+"-div").parent().parent().prepend("<div class='ui-widget currentOrgTxt' style='width:100%;background:#fff;'><input id="+inputId+" type='text' value='请输入层级' style='height:24px;line-height:24px;border:1px solid #ccc;padding-left:5px;margin:5px 10px;width:160px;' /><a href='javascript:clearTreeSelected_org_autocomplete(\""+selfId+"\")'>清空</a></div>");
			else
				$("#"+selfId+"-div").parent().parent().prepend("<div class='ui-widget currentOrgTxt' style='width:100%;background:#fff;'><input id="+inputId+" type='text' value='请输入层级' style='height:24px;line-height:24px;border:1px solid #ccc;padding-left:5px;margin:5px 10px;width:160px;' /></div>");
		}()
		$("#org_autocomplete"+selfId).autocomplete({
			source: function(request, response) {
				$.ajax({
					url: PATH+"/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
					data:{"organization.orgName":request.term},
					success: function(data) {
						response($.map(data, function(item) {
							return {
								label: item.orgName+","+stringFormatter(item.contactWay),
								value: item.orgName,
								id: item.id
							}
						}))
					},
					error : function(){
						$.messageBox({
							message:"搜索失败，请重新登入！",
							level:"error"
						});
					}
				})
			},
			select: function(event, ui) {
				var url = "/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id;
				if(options.rootId != undefined ){
					url = url +"&rootId="+options.rootId;
				}
				$("#user_autocomplete").removeAttr("userId");
				$("#user_autocomplete").val("");
				$.ajax({
					url:PATH+url,
					success:function(data){
						$.searchChild(tree,data);
					}
				});
			}
		});
		
		$("#org_autocomplete"+selfId).bind('focusin',function(){
            if( this.value === this.defaultValue ){
                this.value = '';
            }
		}).bind("focusout",function(){
            if( this.value === '' ){
                this.value = this.defaultValue
            }
		})

		function stringFormatter(str){
			if(str==undefined){
				return "";
			}
			return str;
		}
		
		tree.on("click",function(node,e) {
			if(node != null){
				comboWithTooltip.setRawValue(node.text);
		        comboWithTooltip.collapse();
		        if ($("input[name='"+defaultOption.inputName+"']").val()!=node.id){
			        $("input[name='"+defaultOption.inputName+"']").val(node.id);
		        }
		        if ($("input[name='"+defaultOption.inputCodeName+"']").val()!=node.attributes.orgInternalCode){
			        $("input[name='"+defaultOption.inputCodeName+"']").val(node.attributes.orgInternalCode);
		        }
			}
			defaultOption.changeFun(node,e);
		})
		$("#"+selfId+"-tree").height( defaultOption.maxHeight - 20 );
		return tree;
	}
});

function clearTreeSelected_org_autocomplete(selfId){
	$('#'+selfId).val('');
	$('#org_autocomplete'+selfId).val('');
}