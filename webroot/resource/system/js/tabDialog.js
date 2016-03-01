;(function ($) {
	$.fn.createTabDialog=function(o){
		var self=$(this);
		var tabDiv, uba_startDateTime = new Date().getTime();
		function formSubmit(){
			var form = $("#tabDialogComponent",self).find("form");
			if(form.length==0){
				return true;
			}
			var isSuccessValid = form.valid();
			if(isSuccessValid){
				$("#tabDialogComponent",self).find("form").submit();
			}
			return isSuccessValid;
		}
		function activate(currentIndex){
			if(dfop.mode=="view"){
				self.dialog("option","buttons",{"关闭":close});
			}
			else if(currentIndex == 0){
				if(tabDiv.find(".ui-tabs-nav li").length>1 && (o.saveButton==null || o.saveButton=='undefined')){
					self.dialog("option","buttons",{"下一步":next,"保存":save} );
				}else if(tabDiv.find(".ui-tabs-nav li").length>1 && o.saveButton=="exist"){
					self.dialog("option","buttons",{"下一步":next} );
				}else{
					self.dialog("option","buttons",{"保存":save,"关闭":close} );
				}
			}else if(currentIndex==(tabDiv.find(".ui-tabs-nav li").length-1)){
				self.dialog("option","buttons",{"上一步":previous,"保存":save});
			}else{
				self.dialog("option","buttons",{"上一步":previous,"下一步":next,"保存":save} );
			}
		}
		function createTabs(tabs){
			tabDiv=$("#tabDialogComponent",self);
			var tabUL=$("#tabDialogComponent",self).find("ul");
			tabDiv.tabs({
				activate:function(event,ui){
					var currentIndex = tabDiv.tabs("option","active");
					activate(currentIndex);
					tabDiv.data("startLoad","true");
				},
				tabTemplate:'<li><a href="#{href}" label="#{label}">#{label}</a></li>',
				ajaxOptions:{
					data:{
						id: function(){return $("#tabDialogId").val()},
						organizationId: function(){return $.getCurrentOrgId()},
						type: function(){return $("#tabDialogType").val()},
						operateSource:function(){return $("#operateSource").val()}
					}
				},
				select:function(event,ui){
					$("#tabDialogComponent .ui-tabs-panel").empty();
					//当不是第一个页签时，隐藏上传按钮和删除图片样式
					if(ui.index>0){
						$(".PictureUpload").hide();
						$(".shadow").hide();
					}else{
						$(".PictureUpload").show();
					}
					$(".tip-error").remove();
				},
				add:function(event,ui){
					var currentIndex = tabDiv.tabs("option","active");
					activate(currentIndex);
				},
				remove:function(event,ui){
					var currentIndex = tabDiv.tabs("option","active");
					activate(currentIndex);
				},
				load:function(event,ui){
					$.messageBox("close");
					tabDiv.data("startLoad","false");
				}
			});
			for(var i=0;i<tabs.length;i++){
				var tab = tabs[i];
				tabDiv.tabs('add', tab.url, tab.title);
			}
			if(!$("#tabDialogId",self).val()||$("#tabDialogId",self).val()==""){
				tabDiv.tabs('disable');
			}
			$("#tabDialogComponent .ui-tabs-nav").delegate("a","click",function(event){
				event.preventDefault();
			})
		}
		
		function next(){
			if(!formSubmit() || tabDiv.data("startLoad")=="true"){
				return;
			}
			var nowTime = new Date().getTime();
			sendAjax("下一步" , (nowTime - uba_startDateTime));
			uba_startDateTime = nowTime;
			var currentIndex = tabDiv.tabs("option","active");
			if(currentIndex < (tabDiv.find("li").length-1)){
				tabDiv.tabs('enable');
				tabDiv.tabs("option","active",(currentIndex+1));
			}
			if(currentIndex < (tabDiv.find("li").length-2)){
				self.dialog("option", "buttons", {"上一步":previous,"下一步":next,"保存":save} );
			} else if (currentIndex == (tabDiv.find(".ui-tabs-nav li").length-2)) {
				self.dialog("option","buttons",{"上一步":previous,"保存":save});
			}
		}
		function save(){
			if(tabDiv.data("startLoad")=="true"){
				return;
			}
			if($("#previousOrNextOrSave") != 'undefined'){
				$("#previousOrNextOrSave").val('save');
			}
			if(!formSubmit()){
				return;
			}
			var nowTime = new Date().getTime();
			sendAjax("保存" , (nowTime - uba_startDateTime));
			isClickSave = true;
			uba_startDateTime = nowTime;
			if(dfop.saveClose){
				$.messageBox({level:'success',message:'保存成功！'});
				self.dialog("close");
			}
		}
		
		function close(){
			self.dialog("close");
		}
		function previous(){
			if(tabDiv.data("startLoad")=="true"){
				return;
			}
			if(!formSubmit()){
				return;
			}
			var nowTime = new Date().getTime();
			sendAjax("上一步" , (nowTime - uba_startDateTime));
			uba_startDateTime = nowTime;
			var currentIndex = tabDiv.tabs("option","active");
			if(currentIndex!=0){
				tabDiv.tabs('enable');
				tabDiv.tabs("option","active",(currentIndex-1));
			}
			if(currentIndex>1){
				self.dialog("option","buttons",{"上一步":previous,"下一步":next,"保存":save} );
			}else if(currentIndex == 1 && (o.saveButton==null || o.saveButton=='undefined')){
				self.dialog("option","buttons",{"下一步":next,"保存":save} );
			}else if(currentIndex == 1 && o.saveButton=="exist"){
				self.dialog("option","buttons",{"下一步":next} );
			}
		}
		function sendAjax(p1, time){
			if(o != null && typeof(UBA_dealNull) == 'function' && typeof(UBA_WEB_ACCESS_OPERATING_PATH) == 'string' && typeof(UBA_ARRAY_HAS_URL) == 'function' && UBA_ARRAY_HAS_URL(window.location.hash+o.title)){
				var nowA = tabDiv.find("li:eq("+tabDiv.tabs("option","active")+") a"), _ubaModuleName = UBA_dealNull($("#thisCrumbs:visible").text()).replace(new RegExp("(undefined|当前位置|当前层级| |:|：|\t|\n|\r|(\r\n)|(\u0085)|(\u2028)|(\u2029))", "g"), "");
				$.ajax({type: 'POST', url: UBA_WEB_ACCESS_OPERATING_PATH+'uba_operatingTime.png?orgName='+ encodeURI(UBA_dealNull($("#CURRENT_SYSTEM_NAME").attr("orgName"))) 
										+ '&userName=' + encodeURI(UBA_dealNull($("#CURRENT_SYSTEM_NAME").attr('user')))
										+ '&doName=' + encodeURI(UBA_dealNull(p1))
										+ '&sysName=' + encodeURI(UBA_dealNull($("#CURRENT_SYSTEM_NAME").val()))
										+ '&modelName=' + encodeURI(_ubaModuleName == '>>' ||　_ubaModuleName == '>>>' || $("#contentDiv").text().length == 0 ? ($("div.workbenchMain").length == 1 ? '首页' : '') : _ubaModuleName)
										+ '&nowStep=' + encodeURI(UBA_dealNull(nowA.text()))
										+ '&title=' + encodeURI(UBA_dealNull(o.title))
										+ '&trajectoryInfo=' + encodeURI('[' + UBA_dealNull(self.attr('uba_trajectoryInfo')).substr(1).replace(new RegExp('&', 'g'),'%26').replace(new RegExp('\\?', 'g'),'%3F').replace(new RegExp('=', 'g'),'%3D')+ "]")
										+ "&time=" + time
										+ "&tabId=" + self.attr('uba_tabId') + '_' + self.attr('uba_tabId_index')
										+ "&url=" + UBA_dealNull(nowA.attr('href')).replace(new RegExp('&', 'g'),'%26').replace(new RegExp('\\?', 'g'),'%3F').replace(new RegExp('=', 'g'),'%3D'), dataType: 'jsonp'});
				self.attr('uba_tabId_index', parseInt(self.attr('uba_tabId_index'))+1);
				self.attr('uba_trajectoryInfo', '');
			}
		}
		var dfop = {
			tabs:[],
			data:{},
			width:840,
			height:600,
			mode:"",
			model: "",
			title:"",
			saveClose:true,
			url:PATH+'/common/tabDialog/tabDialog.jsp',
			loadComplete:function(){
				createTabs(dfop.tabs);
			}
		}
		
		$.extend(dfop,o);
		
		self.data("data",dfop.data);
		
		self.createDialog(dfop);
		if(dfop.mode=="view"){
			self.dialog("option","buttons",{"关闭":close});
		}else{
			if(dfop.tabs.length>1){
				self.dialog("option","buttons",{"下一步":next,"保存":save} );
			}else{
				self.dialog("option","buttons",{"保存":save,"关闭":close} );
			
			}
		}
	}
	$.fn.proccessSuccess=function(id,data){
		$("#tabDialogId",$(this)).val(id);
	}
	$.fn.proccessTypeSuccess=function(type,data){
		$("#tabDialogType",$(this)).val(type);
	}
	$.fn.getTabIndexByTitle=function(title){
		return $("#tabDialogComponent .ui-tabs-nav li a",$(this)).index($("a[label='"+title+"']"));
	}
	$.fn.addTabToDialog=function(option){
		var index = $(this).getTabIndexByTitle(option.title);
		if(index>=0){
			$(this).removeTabFromDialog(index);
		}
		var tabDiv=$("#tabDialogComponent",$(this));
		tabDiv.tabs('add', option.url, option.title, option.index );
		if(tabDiv.tabs('option', 'disabled')){
			tabDiv.tabs('disable');
		}
	}
	$.fn.tabDialog=function(option){
		$("#tabDialogComponent",$(this)).tabs(option);
	}
	$.fn.removeTabFromDialog=function(index){
		if(index>=0){
			$("#tabDialogComponent",$(this)).tabs( 'remove', index );
		}
	}
	$.fn.removeTabFromDialogByTitle=function(title){
		$(this).removeTabFromDialog($(this).getTabIndexByTitle(title));
	}
})(jQuery);