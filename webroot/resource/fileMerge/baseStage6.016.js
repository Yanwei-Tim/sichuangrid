;(function(t,e){if(typeof exports=="object")module.exports=e();else if(typeof define=="function"&&define.amd)define(e);else t.Spinner=e()})(this,function(){"use strict";var t=["webkit","Moz","ms","O"],e={},i;function o(t,e){var i=document.createElement(t||"div"),o;for(o in e)i[o]=e[o];return i}function n(t){for(var e=1,i=arguments.length;e<i;e++)t.appendChild(arguments[e]);return t}var r=function(){var t=o("style",{type:"text/css"});n(document.getElementsByTagName("head")[0],t);return t.sheet||t.styleSheet}();function s(t,o,n,s){var a=["opacity",o,~~(t*100),n,s].join("-"),f=.01+n/s*100,l=Math.max(1-(1-t)/o*(100-f),t),d=i.substring(0,i.indexOf("Animation")).toLowerCase(),u=d&&"-"+d+"-"||"";if(!e[a]){r.insertRule("@"+u+"keyframes "+a+"{"+"0%{opacity:"+l+"}"+f+"%{opacity:"+t+"}"+(f+.01)+"%{opacity:1}"+(f+o)%100+"%{opacity:"+t+"}"+"100%{opacity:"+l+"}"+"}",r.cssRules.length);e[a]=1}return a}function a(e,i){var o=e.style,n,r;if(o[i]!==undefined)return i;i=i.charAt(0).toUpperCase()+i.slice(1);for(r=0;r<t.length;r++){n=t[r]+i;if(o[n]!==undefined)return n}}function f(t,e){for(var i in e)t.style[a(t,i)||i]=e[i];return t}function l(t){for(var e=1;e<arguments.length;e++){var i=arguments[e];for(var o in i)if(t[o]===undefined)t[o]=i[o]}return t}function d(t){var e={x:t.offsetLeft,y:t.offsetTop};while(t=t.offsetParent)e.x+=t.offsetLeft,e.y+=t.offsetTop;return e}var u={lines:12,length:7,width:5,radius:10,rotate:0,corners:1,color:"#000",direction:1,speed:1,trail:100,opacity:1/4,fps:20,zIndex:2e9,className:"spinner",top:"auto",left:"auto",position:"relative"};function p(t){if(typeof this=="undefined")return new p(t);this.opts=l(t||{},p.defaults,u)}p.defaults={};l(p.prototype,{spin:function(t){this.stop();var e=this,n=e.opts,r=e.el=f(o(0,{className:n.className}),{position:n.position,width:0,zIndex:n.zIndex}),s=n.radius+n.length+n.width,a,l;if(t){t.insertBefore(r,t.firstChild||null);l=d(t);a=d(r);f(r,{left:(n.left=="auto"?l.x-a.x+(t.offsetWidth>>1):parseInt(n.left,10)+s)+"px",top:(n.top=="auto"?l.y-a.y+(t.offsetHeight>>1):parseInt(n.top,10)+s)+"px"})}r.setAttribute("role","progressbar");e.lines(r,e.opts);if(!i){var u=0,p=(n.lines-1)*(1-n.direction)/2,c,h=n.fps,m=h/n.speed,y=(1-n.opacity)/(m*n.trail/100),g=m/n.lines;(function v(){u++;for(var t=0;t<n.lines;t++){c=Math.max(1-(u+(n.lines-t)*g)%m*y,n.opacity);e.opacity(r,t*n.direction+p,c,n)}e.timeout=e.el&&setTimeout(v,~~(1e3/h))})()}return e},stop:function(){var t=this.el;if(t){clearTimeout(this.timeout);if(t.parentNode)t.parentNode.removeChild(t);this.el=undefined}return this},lines:function(t,e){var r=0,a=(e.lines-1)*(1-e.direction)/2,l;function d(t,i){return f(o(),{position:"absolute",width:e.length+e.width+"px",height:e.width+"px",background:t,boxShadow:i,transformOrigin:"left",transform:"rotate("+~~(360/e.lines*r+e.rotate)+"deg) translate("+e.radius+"px"+",0)",borderRadius:(e.corners*e.width>>1)+"px"})}for(;r<e.lines;r++){l=f(o(),{position:"absolute",top:1+~(e.width/2)+"px",transform:e.hwaccel?"translate3d(0,0,0)":"",opacity:e.opacity,animation:i&&s(e.opacity,e.trail,a+r*e.direction,e.lines)+" "+1/e.speed+"s linear infinite"});if(e.shadow)n(l,f(d("#000","0 0 4px "+"#000"),{top:2+"px"}));n(t,n(l,d(e.color,"0 0 1px rgba(0,0,0,.1)")))}return t},opacity:function(t,e,i){if(e<t.childNodes.length)t.childNodes[e].style.opacity=i}});function c(){function t(t,e){return o("<"+t+' xmlns="urn:schemas-microsoft.com:vml" class="spin-vml">',e)}r.addRule(".spin-vml","behavior:url(#default#VML)");p.prototype.lines=function(e,i){var o=i.length+i.width,r=2*o;function s(){return f(t("group",{coordsize:r+" "+r,coordorigin:-o+" "+-o}),{width:r,height:r})}var a=-(i.width+i.length)*2+"px",l=f(s(),{position:"absolute",top:a,left:a}),d;function u(e,r,a){n(l,n(f(s(),{rotation:360/i.lines*e+"deg",left:~~r}),n(f(t("roundrect",{arcsize:i.corners}),{width:o,height:i.width,left:i.radius,top:-i.width>>1,filter:a}),t("fill",{color:i.color,opacity:i.opacity}),t("stroke",{opacity:0}))))}if(i.shadow)for(d=1;d<=i.lines;d++)u(d,-2,"progid:DXImageTransform.Microsoft.Blur(pixelradius=2,makeshadow=1,shadowopacity=.3)");for(d=1;d<=i.lines;d++)u(d);return n(e,l)};p.prototype.opacity=function(t,e,i,o){var n=t.firstChild;o=o.shadow&&o.lines||0;if(n&&e+o<n.childNodes.length){n=n.childNodes[e+o];n=n&&n.firstChild;n=n&&n.firstChild;if(n)n.opacity=i}}}var h=f(o("group"),{behavior:"url(#default#VML)"});if(!a(h,"transform")&&h.adj)c();else i=a(h,"animation");return p});
window.moveTo(0,0);
if(document.all){
	top.window.resizeTo(screen.availWidth,screen.availHeight); 
}else if(document.layers||document.getElementById){
	if(top.window.outerHeight<screen.availHeight||top.window.outerWidth<screen.availWidth){
		top.window.outerHeight = screen.availHeight;
		top.window.outerWidth = screen.availWidth;
	}
}
var startDate=new Date();
var isPopLoginDialog=false;

// 简化 console.log 写法
window.ll = function f() {
    ll.history = ll.history || [];
    ll.history.push(arguments);
    if (this.console) {
        var a = arguments, c;
        try {
            a.callee = f.caller;
        } catch (b) {}
        c = [].slice.call(a);
        if (typeof console.log === "object") {
            log.apply.call(console.log, console, c);
        } else {
            console.log.apply(console, c);
        }
    }
};

// 屏蔽 console.log错误
(function(e) {
    function g(){}
    for (var h = "assert,count,debug,dir,dirxml,error,exception,group,groupCollapsed,groupEnd,info,log,markTimeline,profile,profileEnd,time,timeEnd,trace,warn".split(","), i; !!(i = h.pop()); ) {
        e[i] = e[i] || g;
    }
})(function() {
    try {
        console.log();
        return window.console;
    } catch (b) {
        return window.console = {};
    }
    try {
        ll();
        return ll;
    } catch (b) {
        return ll = {};
    }
}());


// 模块加载器
(function() {
    var moduleMap = {};
    var noop = function (){};
    window.TQ = {
        define: function(name, dependencies, factory) {
            if (!moduleMap[name]) {
                var module = {
                    name: name,
                    dependencies: dependencies,
                    factory: factory
                };
                moduleMap[name] = module;
            }
            return moduleMap[name];
        },
        use:function(name,callback){
            return moduleMap[name].factory.apply(callback);
        }
    };
})();


$.extend({
	httpData: function( xhr, type, s ) {
		var ct = xhr.getResponseHeader("content-type") || "",
			xml = type === "xml" || !type && ct.indexOf("xml") >= 0,
			data = xml ? xhr.responseXML : xhr.responseText;
	
		if ( xml && data.documentElement.nodeName === "parsererror" ) {
			jQuery.error( "parsererror" );
		}

		// Allow a pre-filtering function to sanitize the response
		// s is checked to keep backwards compatibility
		if ( s && s.dataFilter ) {
			data = s.dataFilter( data, type );
		}

		// The filter can actually parse the response
		if ( typeof data === "string" ) {
			// Get the JavaScript object, if JSON is used.
			if ( type === "json" || !type && ct.indexOf("json") >= 0 ) {
				data = jQuery.parseJSON( data );

			// If the type is "script", eval it in global context
			} else if ( type === "script" || !type && ct.indexOf("javascript") >= 0 ) {
				jQuery.globalEval( data );
			}
		}

		return data;
	},
	handleError: function( s, xhr, status, e ) {
		// If a local callback was specified, fire it
		if ( s.error ) {
			s.error.call( s.context, xhr, status, e );
		}

		// Fire the global callback
		if ( s.global ) {
			jQuery.triggerGlobal( s, "ajaxError", [xhr, s, e] );
		}
	},
	layout:function(option){
		function layoutFun(){			
			var documentHeight=document.body.clientHeight-$(".ui-layout-north").outerHeight()-$(".ui-layout-south:visible").outerHeight();
			var rightWidth=document.documentElement.clientWidth-$(".ui-layout-west:visible").width()-10;
			$(".ui-layout-center,.slideResizer").height(documentHeight-5);
			$(".ui-layout-center").width(rightWidth);
			$(".slideResizer .slideToggler").css("margin-top",documentHeight/2);
			//$(".ui-layout-north .sysMenuList").width(document.documentElement.clientWidth);//ie6 bug
			if(window._currentGrid!=undefined && !window._currentGrid.closest(".leaderCon")[0] && !window._currentGrid.closest(".workbenchTabList")[0] && window._currentGrid.closest(".ui-layout-center")[0]){
				window._currentGrid.setGridWidth(rightWidth-$(".superviseCenterRight:visible").width()-$("#contentDiv .center-left").width()-$("#contentDiv .center-content-right").width()-20).setGridHeight($(".ui-layout-center").height()-$("#nav:visible").outerHeight()-$("#thisCrumbs:visible").outerHeight()-$(".content-top").height()-$("#content-top").height()-$(".groupNav").height()-$("#commonPopulation:visible").height()-$(".ui-tabs-nav").outerHeight()-$("#viewPrimaryMemer").prev().outerHeight()-$("#contractCommonPopulation").outerHeight(true)-$("#statistics").outerHeight(true)-65);
			}
		}
		layoutFun();
		$(window).resize(function(){
			clearTimeout(window._layoutTimer);
			window._layoutTimer=setTimeout(function(){
				layoutFun();
			},300);
		});
		$(".slideResizer .slideToggler").toggle(function(){//缩进条按钮事件
			$(".ui-layout-west").hide();
			$(".slideResizer .slideToggler").addClass("slideTogglerCur").attr("title","展开");
			layoutFun();
			$(window).trigger("resize");
		},function(){
			$(".ui-layout-west").show();
			$(".slideResizer .slideToggler").removeClass("slideTogglerCur").attr("title","缩进");
			
			layoutFun();
			$(window).trigger("resize");
		});
	},
	versionIntroduction:function(options){
		var dfop={
			data:[
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version1.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version2.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version3.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version4.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version5.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version6.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version7.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version8.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version9.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version10.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version11.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version13.png" />'},
				{url:'javascript:;',content:'<img src="/resource/system/images/version/3.1.0/version14.png" />'}
			],
			close:function(){
				
			}
		};
		$.extend(dfop, options);
		var $stepFocusBox;
		function init(){//初始化
			if($("#stepFocusBox")[0]){
				$stepFocusBox=$("#stepFocusBox");
			}else{
				$stepFocusBox=$('<div class="focusBox" id="stepFocusBox"><div class="innerBox"><a href="javascript:;" title="关闭" class="close" id="stepFocusBoxClose"></a><a class="prev" href="javascript:;"></a><a class="next" href="javascript:;"></a><ul class="pic" id="stepBox"></ul><ul class="hd" id="stepBoxPager"></ul></div></div><div class="version-overlay"></div>');
				$("body").prepend($stepFocusBox);
			}
		}
		function bindEvent(){//绑定事件
			$("#stepFocusBox").hover(function(){
				$(this).find(".prev,.next").show();
			},function(){
				$(this).find(".prev,.next").hide();
			});
			$('#stepBox').imageready(function () {
				if(navigator.userAgent.indexOf('MSIE 6')>-1){
		    		$("#stepBox img").each(function(){
		    			var thisW=$(this).width();
		    			var thisH=$(this).height();
		    			setPNG(this,thisW,thisH);
		    		})
				}
		    });
		    $("#stepClose,#stepFocusBoxClose").click(close);
			$("#stepFocusBox").bgiframe();
			$("#stepFocusBox").slide({mainCell:".pic",effect:"left",pnLoop:false, autoPlay:false, delayTime:300, trigger:"click"});
		}
		function build(){//构建内容
			function buildStep(stepIndex){
				var $thisStep=$("<li />");
				$self.append($thisStep);
				$("#"+selfId+"Pager").append('<li title="'+stepIndex+'" />');
				return $thisStep;
			}
			function buildStepCtt($step,thisData,index){
				var $link=$("<a />").attr("href",thisData.url).html(thisData.content),
					$thisStepCtt=$("<div />").addClass("step").append($link);
				if((index+1)%3==2){
					$thisStepCtt.addClass("right");
				}
				$step.append($thisStepCtt);
			}
			var stepIndex=1,$step=buildStep(1);
			for(var i=0;i<dfop.data.length;i++){
				buildStepCtt($step,dfop.data[i],i);
				if(i==dfop.data.length-1){
					$closeBtn=$('<a href="javascript:;" class="stepBtn" id="stepClose">立即使用</a>');
					$step.append($closeBtn);
				}
				if((i+1) % 3==0){
					$step=buildStep(++stepIndex);
				}
			}
		}
		function close(){
			dfop.close();
			$("#stepFocusBox").fadeOut("slow",function(){
				$(this).remove();
			});
			$(".version-overlay").remove();
		}
		init();
		var selfId='stepBox';
		var $self=$("#"+selfId);
		build();
		bindEvent();
	},
	messageBox : function(options) {
		var dfop={
			message: false,
			level: "success",
			speed: 500,
			life:3000
		};
		$.extend(dfop, options);
		if(options=='close'){
			$("#jGrowl").removeAttr("style").empty();
			return false;
		}
		if(!$("#jGrowl")[0]){
			$("body").append("<div id='jGrowl'></div>")
		}else{
			$("#jGrowl").removeAttr("style").empty();
		}
		if(typeof(dfop.message) == 'string'){
			var _tmpString = (dfop.message.indexOf("<") == 0 ? $(dfop.message).text() : dfop.message).replace(new RegExp("(\t|\n|\r|(\r\n)|(\u0085)|(\u2028)|(\u2029))", "g"), "");
			dfop.message = _tmpString.indexOf("{") == 0 ? eval("("+_tmpString+")") : _tmpString;
		}
		dfop.message = dfop.message.message ? dfop.message.message : dfop.message;
		dfop.level = dfop.message.expLevel ? dfop.message.expLevel : dfop.level;
		dfop.message='<div class="'+dfop.level+'"><span></span>'+dfop.message+'</div>';
		$("#jGrowl").addClass("jGrowl").append(dfop.message).animate({top:0},dfop.speed);
		function hideMessageBox(){
			clearTimeout(window._messageBox);
			window._messageBox=setTimeout(function(){
				$("#jGrowl").remove();
			},dfop.life);
		}
		hideMessageBox();
		$("#jGrowl").hover(function(){
			clearTimeout(window._messageBox);
		},function(){
			hideMessageBox();
		})
	},
	tab:function(option){
		  var defaultOption={
		   tab:"",
		   box:"",
		   hover:""
		  }
		  $.extend(defaultOption,option);
		  var _tab=$(defaultOption.tab);
		  var _box=$(defaultOption.box);
		  var _hover=defaultOption.hover;
		  var _index;
		  _tab.click(function(){
		   _index=_tab.index(this);// 获取当前点击的索引值
		   $(this).addClass(_hover).siblings().removeClass(_hover);
		   _box.eq(_index).show().siblings().hide();
		  }).eq(0).click();
	},
	dialogLoading:function(option){
		var _init=function(){
			$("body").prepend('<div class="dialog_loading"><div id="dialogLoading" style="margin-top:250px;"></div></div>')
			var opts = {
			  lines: 12,
			  length: 13,
			  width: 5,
			  radius: 5,
			  corners: 1, 
			  rotate: 0, 
			  direction: 1,
			  color: '#333',
			  speed: 1,
			  trail: 100,
			  shadow: false,
			  hwaccel: false,
			  className: 'spinner',
			  zIndex: 2e9,
			  top: 'auto',
			  left: 'auto' 
			};
			var target = document.getElementById('dialogLoading');
			var spinner = new Spinner(opts).spin(target);
		};
		if(typeof(option)=='string'){
				if(option=="open"){
					_init();
					$(".dialog_loading").show();
				}
				if(option=="close"){
					$(".dialog_loading").remove();
				}
		}
	},
	loadingComp:function(option){
		var _init=function(){
			$("body").prepend('<div class="dialog_loading"><div class="loadingcon"></div></div>')
		};
		if(typeof(option)=='string'){
				if(option=="open"){
					_init();
					$(".dialog_loading").show();
				}
				if(option=="close"){
					$(".dialog_loading").remove();
				}
		}
	},
	navDropdownBtn:function(){
		$(".nav-dropdownBtn").live("mouseover",function(event){
			var that=this;
			clearTimeout(window._navBtnOutTimer);
			window._navBtnInTimer=setTimeout(function(){
				var top=$(that).position().top+$(that).height();
				var left=$(that).position().left-15;
				$(".nav-sub-buttons").hide();
				$(that).next(".nav-sub-buttons").css({
					top:top,
					left:left
				}).fadeIn(200);
			},200)
		}).live("mouseout",function(){
			clearTimeout(window._navBtnInTimer);
			window._navBtnOutTimer=setTimeout(function(){
				$(".nav-sub-buttons").hide()
			},500)
		})
		$(".nav-sub-buttons").live("mouseover",function(){
			clearTimeout(window._navBtnOutTimer);
		})
		$(".nav-sub-buttons").live("mouseout",function(){
			window._navBtnOutTimer=setTimeout(function(){
				$(".nav-sub-buttons").hide()
			},500)
		})
	},
	pulldownFun:function(option){
		var dfop={
			onHover:"on"
		}
	    var op=this.op=$.extend(dfop,option||{});
	    var on  = $(op.on),
	        dlg = $(op.dialog),
	        onHov=op.onHover,
	        mouseover_tid = [],
	        i   = 0;
	    
	    on.hover(
	        function(){
	            if(op.offset){
	                var offset=on.offset();
	                dlg.css({'top':offset.top+(+op.offset),'left':offset.left-(+op.offset-13)})
	            }
	            dlg.slideDown(50)
	            clearTimeout(mouseover_tid[i]);
	            $(op.on).addClass(onHov);
	        }, function(){
	            mouseover_tid[i] = setTimeout(function(){
	                dlg.slideUp(50)
	            },20)
	            $(op.on).removeClass(onHov);
	        }
	    )
	    dlg.hover(
	        function(){
	            clearTimeout(mouseover_tid[i]);
	            $(op.on).addClass(onHov);
	        }, function(){
	            mouseover_tid[i] = setTimeout(function(){
	                dlg.slideUp(150)
	            },200)
	            $(op.on).removeClass(onHov);
	        }
	    )
	}
});

function enableSaveButton(option) {
	var dlgFirstButton = $('.ui-dialog-buttonpane:visible').find('button:first');
	if (dlgFirstButton == null) {
		return;
	}
	if (option) {
		dlgFirstButton.show();
	} else {
		dlgFirstButton.hide();
	}
}
$.fn.extend({
	uiMultiselect:function(o){
		var self=$(this);
		var dfop = {
			click: function(event, ui){},
			beforeopen: function(){$(".ui-multiselect-menu:visible .ui-multiselect-close").click();},
			open: function(){},
			beforeclose: function(){},
			close: function(){},
			checkAll: function(){},
			uncheckAll: function(){},
			optgrouptoggle: function(event, ui){
				/*
				var values = $.map(ui.inputs, function(checkbox){
				return checkbox.value;
				}).join(", ");
				$callback.html("Checkboxes " + (ui.checked ? "checked" : "unchecked") + ": " + values);
				*/
			},
			selectedList:3,
			checkAllText: '全选',
			uncheckAllText: '清空',
			noneSelectedText: '请选择',
			selectedText: '# 已选择'
		}
		$.extend(dfop,o);
		self.multiselect(dfop);
	},
	gridRowRightClick:function(o){
		var self=$(this);
		var dfop = { 
			width: 150, 
			items: [
				{text: "新增记录", icon: "/resource/system/js/contextmenu/css/images/icons/add.png", alias: "add", action: function(){
					$("#add").click();
				}},
				{text: "修改记录", icon: "/resource/system/js/contextmenu/css/images/icons/edit.png", alias: "edit", action: function(){
					var selectId=self.data("selectid");
					if(selectId && typeof updateOperator=='function'){
						updateOperator(selectId);
					}
				}},
				{text: "删除记录", icon: "/resource/system/js/contextmenu/css/images/icons/del.png", alias: "del", action: function(){
					var selectId=self.data("selectid");
					if(selectId && typeof deleteOperator=='function'){
						deleteOperator(selectId);
					}
				}},
				{text: "高级搜索", icon: "/resource/system/js/contextmenu/css/images/icons/search.png", alias: "search", action: function(){
					$("#search").click();
				}},
				{text: "刷新", icon: "/resource/system/js/contextmenu/css/images/icons/refresh.png", alias: "reload", action: function(){
					$("#reload").click();
				}}
			], 
			onShow: applyrule,
			onContextMenu: BeforeContextMenu
		};
		$.extend(dfop,o);
		
		function applyrule(menu) {               
			if (this.id == "target2") {
				menu.applyrule({ 
					name: "target2",
					disable: true,
					items: ["1-2", "2-3", "2-4", "1-6"]
				});
			}else {
				menu.applyrule({ 
					name: "all",
					disable: true,
					items: []
				});
			}
		}
		
		function BeforeContextMenu() {
			return this.id != "target3";
		}
		self.contextmenu(dfop);
	},
	resetFormData:function(o){
		var self=$(this);
		var dfop={
			not:''
		}
		$.extend(dfop,o);
		self.find(":input").not(':button, :submit, :reset, :hidden').not(dfop.not).val('').removeAttr('checked').removeAttr('selected');  
	},
	serializeObject:function(option) {
		var defaultOption = {
			excludeWhenNull:false
		};
		$.extend(defaultOption,option);
		function excludeWhenNull(name,value){
			if(defaultOption.excludeWhenNull){
				for(var i=0;i<defaultOption.excludeWhenNull.length;i++){
					if(defaultOption.excludeWhenNull[i]==name&&(value==""||value==null||value==undefined)){
						return false;
					}
				}
			}
			return true;
		}
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
			if(excludeWhenNull(this.name,this.value)){
		        if (o[this.name]) {
		            if (!o[this.name].push) {
		                o[this.name] = [ o[this.name] ];
		            }
	            	o[this.name].push(this.value || '');
		        } else {
					o[this.name] = this.value || '';
		        }
			}
	    });
	    return o;
	},
	
	getTabId:function(indexId){
		return $($($(this).children()[0]).children()[indexId]).find("a").attr("id");
	},
	
	tabFunction:function(options,formName){
		if(null!=formName){
			$.extend(options,{
				select: function(event, ui) {
					if($("#"+formName).hasClass('ui-tabs-hide')){
						enableSaveButton(true);
					}else{
						enableSaveButton(false);
					}
				}
			});
		}
		var defaultOption={
			select: function(event, ui) {$(".tip-yellowsimple,.tip-error").remove();$("input[createMsg='true']",$(this)).attr("createMsg","false")}
		};
		$.extend(defaultOption,options);
		$(this).tabs(defaultOption);
	},

	popup:function(popDom,closeButton){
			$(this).click(function(){
				$(popDom).hide();
				$(this).parent().children(popDom).show();
				$(this).parent().css("z-index","1");
				$(popDom).bgiframe();
			});
			$(closeButton).click(function(){
				$(this).parent().parent().hide();
			});
		},
	messageTip : function(options,methods){
		var self=$(this);
		var defaultOption={
			url:"#",
			show:true,
			width:120,
			cache:true,
			arrowPositionLeft:70,
			notAcceptNote:{title:"未受理短信",link:"javascript:void(0)",num:0,limits:false},
			notAcceptOnlineAppeals:{title:"未受理平台消息",link:"javascript:void(0)",num:0,limits:false},
			backlog:{title:"待办事项",link:"javascript:void(0)",num:0,limits:false},
			myAuditDelay:{title:"待审核事项",link:"javascript:void(0)",num:0,limits:false},
			data:{smsReceivedBoxNum:0,personnelMessageNum:0,myNeedDoNum:0,myAuditDelayNum:0},
			sessionTimeout:7200000,
			showTip:true
		};
        $.extend(defaultOption,options);
        //短信暂时不需要
        //defaultOption.notAcceptNote.num=defaultOption.data.smsReceivedBoxNum;
        if(defaultOption.data){
	        defaultOption.notAcceptOnlineAppeals.num=defaultOption.data.personnelMessageNum;
	        defaultOption.backlog.num=defaultOption.data.myNeedDoNum;
	        defaultOption.myAuditDelay.num=defaultOption.data.myAuditDelayNum;
        }
        var data=defaultOption.data;
 	   
		if(data!=null){
			data.messageNum = data.myNeedDoNum+data.personnelMessageNum+data.smsReceivedBoxNum+data.myAuditDelayNum;
			if(data.messageNum>0){
				clearInterval(window.messageInterval);
				window.messageInterval=setInterval(function(){$("#message #msg").toggle("fade")},600)
			}else{
				clearInterval(window.messageInterval);
			}
			$(".header-right-toolMenu #msg").html(data.messageNum);
		}
		if(!defaultOption.showTip){
			return false
		}
        var cookieName="messageTip";
		if($.cookie(cookieName)==null || methods=='show'){
			$.cookie(cookieName,true, { path: '/', expires: 10 });
		}
		if($.cookie(cookieName)=='false'){
			return false;
		}
        var live=function(cookieName){
			$(".message-tip .tip-close").click(function(){
				$(".message-tip").remove();
				$.cookie(cookieName,false, { path: '/', expires: 10 });
			});
			$("#noAcceptNote").bind("click",function(){
				showPageByTopMenu('interactionManagement-smsInboxManagement');
			});
			$("#unreadPlatformNews").bind("click",function(){
				showPageByTopMenu('interactionManagement-pmInboxManagement');
			});
			$("#backlogMessage").bind("click",function(){
				showPageByTopMenu('serviceWork-myIssueListManagement');
			});
			$("#myAuditDelayMessage").bind("click",function(){
				showPageByTopMenu('serviceWork-myAuditDelayListManagement');
			});
	    }
	   
		var notAcceptNoteHtml='';
        var notAcceptOnlineAppealsHtml='';
        var backlogHtml='';
        var myAuditDelayHtml='';
        if(defaultOption.notAcceptNote.limits){
        	notAcceptNoteHtml='<li>'+defaultOption.notAcceptNote.title +' <a href="'+defaultOption.notAcceptNote.link+'" id="noAcceptNote"><span>'+defaultOption.notAcceptNote.num+'</span></a> 条  </li>';
        }
        if(defaultOption.notAcceptOnlineAppeals.limits){
        	notAcceptOnlineAppealsHtml='<li>'+defaultOption.notAcceptOnlineAppeals.title +' <a href="'+defaultOption.notAcceptOnlineAppeals.link+'" id="unreadPlatformNews"><span>'+defaultOption.notAcceptOnlineAppeals.num+'</span></a> 条</li>';
        }
       if(defaultOption.backlog.limits){
    	    backlogHtml='<li>'+defaultOption.backlog.title +' <a href="'+defaultOption.backlog.link+'" id="backlogMessage"><span>'+defaultOption.backlog.num+'</span></a> 条</li>';
       }
       if(defaultOption.myAuditDelay.limits){
    	   myAuditDelayHtml='<li>'+defaultOption.myAuditDelay.title +' <a href="'+defaultOption.myAuditDelay.link+'" id="myAuditDelayMessage"><span>'+defaultOption.myAuditDelay.num+'</span></a> 条</li>';
       }

       var html='<div class="message-tip">'
	    	   +'<div class="tip-top"></div>'
    	   	   +'<div class="tip-msgCon">'
	    	   +'<h3 class="tip-tit"><strong class="tip-callBells"></strong>消息提醒<a href="javascript:;" class="tip-close"></a></h3>'
	    	   +'<ul class="tip-content">'
	    	   + notAcceptNoteHtml
			   + notAcceptOnlineAppealsHtml
			   + backlogHtml
			   + myAuditDelayHtml
	    	   +'</ul>'
	    	   +'</div>'
	    	   +'<div class="tip-bottom"></div>'
    	   +'</div>';
       
		if($(".message-tip")[0]){
			$(".message-tip").remove();
		}
        $(html).hide().appendTo("body").fadeIn(300);
       	live(cookieName);
	},
	workBenchMessageTip : function(options,methods){
		var self=$(this);
		var defaultOption={
			url:"#",
			show:true,
			width:120,
			cache:true,
			arrowPositionLeft:70,
			notAcceptOnlineAppeals:{title:"未受理平台消息",link:"javascript:void(0)",num:0,limits:false},
			backlog:{title:"待办事项",link:"javascript:void(0)",num:0,limits:false},
			data:{personnelMessageNum:0,myNeedDoNum:0},
			sessionTimeout:7200000,
			showTip:true
		};
        $.extend(defaultOption,options);
        //短信暂时不需要
        //defaultOption.notAcceptNote.num=defaultOption.data.smsReceivedBoxNum;
        if(defaultOption.data){
	        defaultOption.notAcceptOnlineAppeals.num=defaultOption.data.personnelMessageNum;
	        defaultOption.backlog.num=defaultOption.data.myNeedDoNum;
        }
        var data=defaultOption.data;
 	   
		if(data!=null){
			data.messageNum = data.myNeedDoNum+data.personnelMessageNum;
			if(data.messageNum>0){
				clearInterval(window.messageInterval);
				window.messageInterval=setInterval(function(){$("#message #msg").toggle("fade")},600)
			}else{
				clearInterval(window.messageInterval);
			}
			$(".header-right-toolMenu #msg").html(data.messageNum);
		}
		if(!defaultOption.showTip){
			return false
		}
        var cookieName="messageTip";
		if($.cookie(cookieName)==null || methods=='show'){
			$.cookie(cookieName,true, { path: '/', expires: 10 });
		}
		if($.cookie(cookieName)=='false'){
			return false;
		}
        var live=function(cookieName){
			$(".message-tip .tip-close").click(function(){
				$(".message-tip").remove();
				$.cookie(cookieName,false, { path: '/', expires: 10 });
			});
			$("#unreadPlatformNews").bind("click",function(){
				showPageByTopMenu('interactionManagement-pmInboxManagement');
			});
			$("#backlogMessage").bind("click",function(){
				showPageByTopMenu('serviceWork-myIssueListManagement');
			});
	    }
	   
        var notAcceptOnlineAppealsHtml='';
        var backlogHtml='';
        if(defaultOption.notAcceptOnlineAppeals.limits){
        	notAcceptOnlineAppealsHtml='<li>'+defaultOption.notAcceptOnlineAppeals.title +' <a href="'+defaultOption.notAcceptOnlineAppeals.link+'" id="unreadPlatformNews"><span>'+defaultOption.notAcceptOnlineAppeals.num+'</span></a> 条</li>';
        }
       if(defaultOption.backlog.limits){
    	    backlogHtml='<li>'+defaultOption.backlog.title +' <a href="'+defaultOption.backlog.link+'" id="backlogMessage"><span>'+defaultOption.backlog.num+'</span></a> 条</li>';
       }

       var html='<div class="message-tip">'
	    	   +'<div class="tip-top"></div>'
    	   	   +'<div class="tip-msgCon">'
	    	   +'<h3 class="tip-tit"><strong class="tip-callBells"></strong>消息提醒<a href="javascript:;" class="tip-close"></a></h3>'
	    	   +'<ul class="tip-content">'
			   + notAcceptOnlineAppealsHtml
			   + backlogHtml
	    	   +'</ul>'
	    	   +'</div>'
	    	   +'<div class="tip-bottom"></div>'
    	   +'</div>';
       
		if($(".message-tip")[0]){
			$(".message-tip").remove();
		}
        $(html).hide().appendTo("body").fadeIn(300);
       	live(cookieName);
	},
	dialogtip:function(option){
		var defaultOption={
			className: 'tip-error',
			showOn: 'none',
			alignTo: 'target',
			hideTimeout:0,
			showTimeout:0,
			alignX: 'center',
			alignY: 'bottom',
			offsetX: 0,
			offsetY: 5
		}
		$.extend(defaultOption,option);
		$(this).poshytip(defaultOption);
	},
	showTip:function(option){
		var self=$(this);
		var defaultOption={
			className: 'tip-yellowsimple',
			hideTimeout:0,
			showTimeout:0,
			offsetX: 5,
			offsetY: 5,
			showOn: 'focus',
			alignTo: 'target',
			alignX: 'right',
			alignY: 'center',
			positionRight:false
		};
		var outTimer,inTimer;
		$.extend(defaultOption,option);
		self.poshytip(defaultOption);
		self.hover(function(){
			clearTimeout(outTimer);
			self.poshytip('show');
		},function(){
			outTimer=setTimeout(function(){
				self.poshytip('hide')
			},3000);
			$(".tip-yellowsimple").hover(function(){
				clearTimeout(outTimer);
				self.poshytip('show');
			},function(){
				outTimer=setTimeout(function(){
					self.poshytip('hide')
				},1000);
			});
		});
		self.removeAttr("title");
	},
	inputtip:function(option){
		var inputtipName="inputtip";
		var inputtipValue=$.cookie("inputtip");
		if(inputtipValue==null){
			$.cookie(inputtipName,true, { path: '/', expires: 10 });
		}
		if(inputtipValue=='false'){
			return;
		}else{
			var self=$(this);
			var defaultOption={
				className: 'tip-yellowsimple',
				hideTimeout:0,
				showTimeout:0,
				offsetX: 5,
				offsetY: 5,
				showOn: 'focus',
				alignTo: 'target',
				alignX: 'right',
				alignY: 'center',
				liveEvents:true
			}
			$.extend(defaultOption,option);
			var tipMsg = self.attr("tipMsg");
			if(tipMsg && tipMsg!=""){
				defaultOption.content=tipMsg;
			}
			self.poshytip(defaultOption);
			self.bind("change",function(){
				$(this).poshytip('hide');
			})
			$(".tip-yellowsimple").bgiframe();
			// $(this).removeAttr("title");
		}
	},
	
	setButtonEnabled:function(enabled){
		if (enabled){
			$(this).buttonEnable();
		}else{
			$(this).buttonDisable();
		}
	},

	isButtonEnabled:function(){
		return !($(this).attr("disabled")=="true" || $(this).attr("disabled")=="disabled");
	},
	
	buttonDisable:function(){
		$(this).addClass("huise").addClass("disable").attr("disabled",true).removeAttr("href");
		$(this).hover(
				function () {
				$(this).removeClass("hover");
				},
				function () {
				$(this).removeClass("hover");
				}
		);
	},
	buttonEnable:function(){
		$(this).removeClass("huise").removeClass("disable").attr("disabled",false).attr("href","javascript:void(0)");
		$(this).hover(
			function () {
			$(this).addClass("hover");
			},
			function () {
			$(this).removeClass("hover");
			}
		);
	},
	datePicker : function(o) {
		var self = $(this);
		var dfop={
			showWeek: false,
			changeMonth: true,
			changeYear: true,
			yearSuffix: '',
			dateFormat:'yy-mm-dd',
			showButtonPanel: true,
			showClearButton:true
		};
		$.extend(dfop,o);
		if(!$("#ui-datepicker-div").attr("id")){
			$.datepicker.initialized = false;
		}
		$(this).datepicker(dfop);
	},
	dateTimePicker : function(o) {
		var self = $(this);
		var dfop={
			showWeek: false,
			changeMonth: true,
			changeYear: true,
			yearSuffix: '',
			dateFormat:'yy-mm-dd',
			showButtonPanel: true,
			showClearButton:true,
			timeFormat: 'HH:mm:ss',
			stepHour: 1,
			stepMinute: 1,
			stepSecond: 15,
			showSecond:false,
			showMinute:true,
			currentText: '今天',
			clearText: '清除',
			closeText: '确定',
			amNames: ['上午', 'A'],
			pmNames: ['下午', 'P'],
			timeSuffix: '',
			timeOnlyTitle: '选择时间',
			timeText: '时间',
			hourText: '时：',
			minuteText: '分：',
			secondText: '秒：',
			controlType:{
				create: function(tp_inst, obj, unit, val, min, max, step){
					$('<input class="ui-timepicker-input" value="'+val+'" style="width:50%">')
						.appendTo(obj)
						.spinner({
							min: min,
							max: max,
							step: step,
							change: function(e,ui){ // key events
									// don't call if api was used and not key press
									if(e.originalEvent !== undefined)
										tp_inst._onTimeChange();
									tp_inst._onSelectHandler();
								},
							spin: function(e,ui){ // spin events
									tp_inst.control.value(tp_inst, obj, unit, ui.value);
									tp_inst._onTimeChange();
									tp_inst._onSelectHandler();
								}
						});
					return obj;
				},
				options: function(tp_inst, obj, unit, opts, val){
					if(typeof(opts) == 'string' && val !== undefined)
							return obj.find('.ui-timepicker-input').spinner(opts, val);
					return obj.find('.ui-timepicker-input').spinner(opts);
				},
				value: function(tp_inst, obj, unit, val){
					if(val !== undefined){
						tp_inst._onTimeChange();
						return obj.find('.ui-timepicker-input').spinner('value', val);
					}
					return obj.find('.ui-timepicker-input').spinner('value');
				}
			}
		};
		$.extend(dfop,o);
		$(this).datetimepicker(dfop);
		if(self.val()!=''){
			var thisValue=self.val();
			var arr=thisValue.split(" ");
			var date=arr[0].split("-");
			var time=arr[1].split(":");
			self.datetimepicker('setDate',new Date(parseInt(date[0],10),parseInt(date[1],10)-1,parseInt(date[2],10),time[0],time[1],time[2]));
		}
		
	},
	accordionFunction:function(selector,content,option){
		// 左边菜单
		var self=$(this);
		var defaultOption={
				collapsible: true,
				header: "> " + content + " > " + selector,
				autoHeight: false
		};
		$.extend(defaultOption,option);
		self.accordion(defaultOption);
	},
	hoverDisplay:function(biaoqian){
		// 显示隐藏
	   $(this).children(biaoqian).hide();
	   $(this).hover(function(){
			$(this).children(biaoqian).stop(true,true).slideDown(400);
		},function(){
			$(this).children(biaoqian).stop(true,true).slideUp("fast");
		});
	},
	hoverChange:function(hoverClass){
		// 指向更改class
	   $(this).hover(
		  function () {
			$(this).css("cursor","hand");
			$(this).addClass(hoverClass);
		  },
		  function () {
			$(this).removeClass(hoverClass);
		  }
		);
	},
	popUpMore:function(options){
		var self=$(this);
		var selfId=$(this).attr("id");
		var conId=selfId+new Date().getTime();
		var popUpCon='';
		var thisWindow = {
			l: $(window).scrollLeft(),
			t: $(window).scrollTop(),
			w: $(window).width(),
			h: $(window).height()
		};
		var defaultOption = {
			data:[],
			className: 'tip-yellowsimple',
			hideTimeout:0,
			showTimeout:0,
			offsetX: 5,
			offsetY: 0,
			showOn: 'none',
			alignTo: 'target',
			alignX: 'right',
			alignY: 'center',
			openNew:true,
			content:function(){}
		}
		$.extend(defaultOption,options);
		var target='_blank';
		if(defaultOption.openNew!=true){
			target='_self'
		}
		defaultOption.content=function(){
			for(var i=0;i<defaultOption.data.length;i++){
				if(defaultOption.data[i].dailyDirectoryid){
					popUpCon=popUpCon+'<li><a href="'+defaultOption.data[i].url+'" id="'+selfId+'_item'+defaultOption.data[i].id+'" thisId = "'+defaultOption.data[i].id+'" dailyDirectoryId="'+defaultOption.data[i].dailyDirectoryid+'" target="'+target+'">'+defaultOption.data[i].text+'</a></li>';
				}else{
					popUpCon=popUpCon+'<li><a href="'+defaultOption.data[i].url+'" id="'+selfId+'_item'+defaultOption.data[i].id+'" thisId = "'+defaultOption.data[i].id+'" target="'+target+'">'+defaultOption.data[i].text+'</a></li>';
				}
			}
			return '<div class="popupcon" id="'+conId+'">'+'<a class="tip_close"></a>'+popUpCon+'</div>';
		};
		bindItemEvent=function(){
			for(var i=0;i<defaultOption.data.length;i++){
				var thisItem=$(".popupcon").find("li:eq("+i+") a");
				thisItem.bind("click",defaultOption.data[i].clickFun);
			}
		};

		var init=function(){
			var tipMsg = self.attr("tipMsg");
			if(tipMsg && tipMsg!=""){
				inputOption.content=tipMsg;
			}
			self.poshytip(defaultOption);
			$(".tip-yellowsimple").bgiframe();
		};
		var style=function(){
			var thisTop=$("#"+conId).closest(".tip-yellowsimple:first").css("top");
			thisTop=Number(thisTop.substring(0,thisTop.length-2));
		}
		self.bind("click",function(){// alert(33333333)
			$(".tip-yellowsimple:first").remove();
			popUpCon='';
			if(self.offset().left+300>thisWindow.w){
				defaultOption.alignX="left";
			}
			else{
				defaultOption.alignX="right";
			};
			init();
			self.poshytip("show");
			style();
			bindItemEvent();
			$("#"+conId+" .tip_close:first").bind("click",function(){
				self.poshytip("hide");
				$("#"+conId).closest(".tip-yellowsimple:first").remove();
			})
		});
	},
	passwordCheck:function(options){
		digitalspaghetti.password.el.passwordMinChar=null;
		digitalspaghetti.password.el.passwordStrengthBar=null;
		var self=$(this);
		var selfId=$(this).attr("id");
		var selfScore=0;
		var defaultOption={
			displayMinChar: false,
			minChar: 6,
			minCharText: '密码个数最少为 %d 个字符',
			colors: ["#ff0000", "#ff0099", "#99cc00", "#00ccff", "#00ccff"],
			scores: [20, 20, 50, 50],
			verdicts:	['弱', '弱', '中等', '强', '强'],
			raisePower: 1.4,
			debug: false,
			scoreVal:0,
			keyUp:function(selfScore){}
		}
		$.extend(defaultOption,options);
		self.pstrength(defaultOption);
		var word,score;
		self.keyup(function(){
			var word=self.attr("value");
			// defaultOption.scoreVal=self.pstrength.validationRules(word,score);
			selfScore=defaultOption.scoreVal;
			defaultOption.keyUp(selfScore);
		});
	},
	hoverEvent:function(thisEvent){
		var self=$(this);
		var selfId=self.children("a:first").attr("name");
		self.hover(
			function(){
				self.addClass("hover");
				self.children("span:first").addClass("delItem");
				self.children("span:first").bind("click",thisEvent);
			},
			function(){
				self.removeClass("hover");
				self.children("span:first").removeClass("delItem");
				self.children("span:first").unbind("click");
			}
		)
	},
	datepickers:function(o){
		var self=$(this);
		var dfop={
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showWeek:false,
			yearSuffix: '',
			numberOfMonths: 1,
			datas:'.dates',
			yearRange: '1900:2030',
			maxDate:'+0d',
			onSelect: function(selectedDate) {
				
			},
			onClose:function(selectedDate){
				var option = $(this).data("flag") == "from" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
					instance.settings.dateFormat ||
					$.datepicker._defaults.dateFormat,
					selectedDate, instance.settings );
				if(dates.not(this).attr("value")=='' && $(this).attr("value") != ''){
					dates.not(this).focus();
				}
			}
		}
		$.extend(dfop,o);
		self.eq(0).data("flag","from");
		var dates=self.datepicker(dfop);
		self.eq(0).datepicker("option",dfop.from);
		self.eq(1).datepicker("option",dfop.to);
	},
	dateWeek:function(o){
		var self=$(this);
		var dfop={
			timer:1000
		}
		$.extend(dfop,o)
		var init=function(){
			var todayDate = new Date();
			var date = todayDate.getDate();
			var month = todayDate.getMonth() + 1;
			var year = todayDate.getFullYear();
			var weeks = [ "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" ];
			var hh = todayDate.getHours();
			var mm = todayDate.getMinutes();
			var ss = todayDate.getTime() % 60000;
			ss = (ss - (ss % 1000)) / 1000;
			var clock = hh + ':';
			if (mm < 10){
				clock += '0';
			}
			clock += mm + ':';
			if (ss < 10){
				clock += '0';
			}
			clock += ss;
			var string;
			string = year + "-" + month + "-" + date + "," + clock;
			self.html(string);
			return string;
		}
		setInterval(init,dfop.timer);
	},
	scrollWay:function(options){
		
		var $this=$(this);
		var maxLength= 0,
            upCount  = 0;

		dfop={
			floor:'.displayFloor',
			up:'',
			down:'',
			left:'',
			right:'',
			upLine:'1',
			leftLine:'11',
			speed:'3000',
			num:'1',
			pObject:'table',
			cObject:'tr',
			crObject:'td'
		};
		$.extend(dfop,options);
		
		/*button*/
		var _btnUp = $("#"+dfop.up),_btnDown = $("#"+dfop.down), _btnLeft = $("#"+dfop.left), _btnRight = $("#"+dfop.right);
		
		/*elements*/
		var scrollObj =$this.find(dfop.pObject);
		var floorCon=$(dfop.floor);
		
		var displayFloor=$("<ul/>").addClass("floorList").appendTo(floorCon);
		
		var marginUpSpace = parseInt(scrollObj
				.find(dfop.cObject+":first")
				.css("marginTop"),10)+parseInt(scrollObj
						.find(dfop.cObject+":first")
						.css("marginBottom"),10);
		
		var marginLeftSpace	= parseInt(scrollObj
				.find(dfop.cObject+":first "+dfop.crObject+":first")
				.css("marginLeft"),10)+parseInt(scrollObj
						.find(dfop.cObject+":first "+dfop.crObject+":first")
						.css("marginRight"),10);
		
		var paddingLeftSpace = parseInt(scrollObj
				.find(dfop.cObject+":first "+dfop.crObject+":first")
				.css("paddingLeft"),10)+parseInt(scrollObj
						.find(dfop.cObject+":first "+dfop.crObject+":first")
						.css("paddingRight"),10);
		
		var paddingUpSpace	= parseInt(scrollObj
				.find(dfop.cObject+":first "+dfop.crObject+":first")
				.css("paddingTop"),10)+parseInt(scrollObj
						.find(dfop.cObject+":first "+dfop.crObject+":first")
						.css("paddingBottom"),10);
		
		var borderTopSpace = parseInt(scrollObj
				.find(dfop.cObject+":first "+dfop.crObject+":first")
				.css("border-bottom-width"),10);


		var trHeight = parseInt(scrollObj
				.find(dfop.cObject+":first")
				.height(),10);

		// 合并前默认宽度
		var tdWidth	= parseInt(scrollObj
				.find(dfop.cObject+":first")
				.find(dfop.crObject)
				.outerWidth(),10);
		
		var allTdColspan = scrollObj.find(dfop.cObject+":first").find(dfop.crObject).attr("colspan");
		
		//合并后获取最小宽度
		if(allTdColspan > 1){
			//判断全部被合并的情况下
			tdWidth	=parseInt(scrollObj.find(dfop.cObject+":first").find(dfop.crObject).outerWidth(),10)/allTdColspan;
			
		}else{
			//判断非全部合并获取最小宽度
			scrollObj.find(dfop.cObject+":first").find(dfop.crObject).each(function(index){
				var _allTd = scrollObj.find(dfop.cObject+":first").find(dfop.crObject);
				
				if(_allTd.eq(index).outerWidth() > _allTd.eq(index-1).outerWidth()){
					tdWidth = _allTd.eq(index-1).outerWidth();
				}
			})
		}
		
		var lineTrH=marginUpSpace+trHeight+borderTopSpace;
		var lineTdW=tdWidth;
		var m=trSingleHeightLine = dfop.upLine ? parseInt(dfop.upLine, 10):parseInt($this.height()/lineTrH,10);
		var n=tdSingleWidthLine = dfop.leftLine ? parseInt(dfop.leftLine, 10):parseInt($this.width()/lineTdW,10);

		var upHeight = trSingleHeightLine * lineTrH;
		var leftWidth = tdSingleWidthLine * lineTdW;
		var spd = dfop.speed ? parseInt(dfop.speed,10):600;

        $this.data("len",scrollObj.find(dfop.cObject).length/parseInt(dfop.num,10));

		/*max length*/
		scrollObj.find(dfop.cObject).each(function(index){
			var thisLength=$(this).children(dfop.crObject).size();
			if(maxLength<thisLength){
				maxLength=thisLength;
			}
		});
		var leftCount=maxLength/dfop.num;
		/*create Floor*/
		function screateFloor(){
			var length=scrollObj.find(dfop.cObject).length;
			scrollObj.find(dfop.cObject).each(function(index){
				var index=length-index;
				var createLi=$("<li/>")
						.html("<div>第</div><div>"+index+"</div><div>层</div>")
						.appendTo(displayFloor)
						.height(lineTrH-1);
						
				createLi.children("div").css("lineHeight",lineTrH/3+'px');
				
				$(this).find(dfop.crObject).each(function(Tindex){
					var Tindex=Tindex+1;
					if(Tindex<10){
						if($(this).parent().find("td.split")[0]){
							
							if($(this).find("input:first").attr("value")==""){
								$(this).find("input:first").attr("value",(index)+'0'+parseInt(Tindex,10)+"[暂无数据!]");
							}
						}else{
							
							if($(this).find("input:first").attr("value")==""){
								$(this).find("input:first").attr("value",index+'0'+parseInt(Tindex,10)+"[暂无数据!]");
							}
						}
						
					}else{
						if($(this).parent().find("td.split")[0]){
							
							if($(this).find("input:first").attr("value")==""){
								$(this).find("input:first").attr("value",(index+1)+''+parseInt(Tindex,10)+"[暂无数据!]");
							}
						}else{
							
							if($(this).find("input:first").attr("value")==""){
								$(this).find("input:first").attr("value",index+''+parseInt(Tindex,10)+"[暂无数据!]");
							}
						}
					}
				})
			})
		}
		screateFloor();
		
		/*scroll Floor*/
		function scrollUp() {

			if (!scrollObj.is(":animated")) {
                upCount = $this.data("len");
				if (m < upCount) {

					m += trSingleHeightLine;
					scrollObj.animate({ marginTop: "-=" + upHeight + "px" }, spd);
					displayFloor.animate({ 
						marginTop: "-=" + upHeight + "px" 
						}, spd);
					
					if(m==upCount){
						_btnUp.removeClass("upEnable")
							.addClass("upDisable upHover");
					}else{
						_btnUp.addClass("upEnable")
							.removeClass("upDisable");
						
						_btnDown.addClass("downEnable")
							.removeClass("downDisable");
					}
				}
			} 
		}
		function scrollDown() { 
			if (!scrollObj.is(":animated")) {
				if (m > trSingleHeightLine) {
					m -= trSingleHeightLine; 
					scrollObj.animate({
							marginTop: "+=" + upHeight + "px"
						}, spd);
					displayFloor.animate({
							marginTop: "+=" + upHeight + "px"
						}, spd);
					
					if(m==trSingleHeightLine){
						_btnDown.removeClass("downEnable")
						.addClass("downDisable downHover");

					}else{
						_btnDown.addClass("downEnable")
						.removeClass("downDisable");
						
			
						_btnUp.addClass("upEnable")
						.removeClass("upDisable");
						
					}
					
				}
			} 
		}
		function scrollLeft() { 
			
			if (!scrollObj.is(":animated")) {
				if (n < leftCount) {
					n += tdSingleWidthLine;
					scrollObj.animate({
							marginLeft:"-="+leftWidth+"px"
						}, spd);
					
					if(n==leftCount){
						_btnLeft.removeClass("leftEnable")
							.addClass("leftDisable leftHover");
					}else{
						_btnLeft.addClass("leftDisable")
							.removeClass("leftEnable");
						_btnRight.addClass("rightEnable")
							.removeClass("rightDisable");
					}
					
				}
			}
		}
		function scrollRight() {
			if (!scrollObj.is(":animated")) { 
				if (n > tdSingleWidthLine) {
					n -= tdSingleWidthLine; 
					scrollObj.animate({
							marginLeft: "+=" + leftWidth + "px" 
						}, spd);
					if(n==tdSingleWidthLine){
						_btnRight.removeClass("rightEnable")
							.addClass("rightDisable rightHover");
					}else{
						_btnRight.addClass("rightEnable")
							.removeClass("rightDisable");
						_btnLeft.addClass("leftEnable")
							.removeClass("leftDisable");
					}	
				}
			} 
		}
		function scorllLeftFunc(){
			if(n<leftCount){
				scrollLeft();
				scrollRight();
			}else{
				return false;
			}
		}
		
		function scorllRightFunc(){
			if(n>dfop.leftLine){
				scrollRight();
			}else{
				return false;
			}
		}
		
		/*triggle event*/	
		function triggleEvent(){
			_btnUp.unbind("click").bind("click", scrollUp); 
			_btnDown.unbind("click").bind("click", scrollDown); 
			_btnLeft.unbind("click").bind("click", scorllLeftFunc); 
			_btnRight.unbind("click").bind("click", scorllRightFunc); 
		}
		triggleEvent();
	}
});
$.extend({
	sigmaReportLayout:function(){
		// 列表信息 和 柱图 饼图 外层容器计算高度
		$(".SigmaReport .highcharts-container").height(
			$(".ui-layout-center").outerHeight() - $("#chartsTabs .ui-tabs-nav").outerHeight() - $("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-45
		).width(
			$(".ui-layout-center").outerWidth()-$("#chartsTabs ul:eq(0)").width() -40
		);
	},
	gridboxHeight:function(){
		var sigmaReportHeight=$(".ui-layout-center").outerHeight() - $("#chartsTabs .ui-tabs-nav").outerHeight() - $("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-42
		$(".SigmaReport").height(sigmaReportHeight);
	},
	// 点击隐藏input框中的文字
	showTxtValue:function(){
		var f = $("input.show_search_btn");
		var t = $("input.show_search_btn").attr("value");
		f.focusin(function(){
			if(f.attr("value")==t){
				f.attr({"value":""})
			}
		}).focusout(function(){
		if(f.attr("value")==""){
			f.attr("value",t)
			}
		})
	}
});
$.fn.imageready = function (callback, userSettings) {
	function loaded() {
		unloadedImages--, !unloadedImages && callback()
	}
	function bindLoad() {
		this.one("load", loaded);
		if ($.browser.msie) {
			var src = this.attr("src"),
				param = src.match(/\?/) ? "&" : "?";
			param += options.cachePrefix + "=" + (new Date).getTime(), this.attr("src", src + param)
		}
	}
	var options = $.extend({}, $.fn.imageready.defaults, userSettings),
		$images = this.find("img").add(this.filter("img")),
		unloadedImages = $images.length;
	return $images.each(function () {
		var $this = $(this);
		if (!$this.attr("src")) {
			loaded();
			return
		}
		this.complete || this.readyState === 4 ? loaded() : bindLoad.call($this)
	})
};
$.fn.imageready.defaults = {
	cachePrefix: "random"
};

$.fn.peopleSelect=function(orgId){
	var self=$(this);
	var selfId=self.attr("id");
	var dfop={
		url:'/common/commonPeopleSelect.jsp?orgId='+orgId,
		postData:{}
	};
	var init=function(){
		var coordinate={
			left:self.offset().left,
			top:self.offset().top+self.outerHeight()
		}
		var peopleBox;
		if($("#"+selfId+"Dlg")[0]){
			peopleBox=$("#"+selfId+"Dlg").css(coordinate);
			peopleBox.show();
		}else{
			peopleBox=$('<div class="peopleSelectDlg" id="'+selfId+'Dlg" />').css(coordinate).appendTo("body");
			$.ajax({
				url:dfop.url,
				data:dfop.postData,
				success:function(html){
					peopleBox.html(html);
				}
			});
		}
	};
	init();
};

$.cacheScript = function(obj) {
	var cache = (obj.cache === undefined) ? 'true' : obj.cache;
	$.ajax({type: 'GET', url: RESOURCE_PATH+obj.url+version, success:obj.callback, dataType: 'script', ifModified: true, cache: cache});
};
//jquery ui datepicker plug

$.ajaxSetup({
	cache:false
});
jQuery.extend( {
	selectMenu:function(selectedId){
		$("#topMenu a").removeClass("select");
		$("#"+selectedId).addClass("select");
		//$(".sysMenuList").hide();
	}
});

$(function(){
//	$("#shouldLogin").ajaxComplete(function(event, xhr, ajaxOptions){
//		 if(xhr&&xhr.readyState == 4){
//			 if(xhr.status==200){
//				if(xhr.responseText!=undefined && xhr.responseText.indexOf("document.location.href='/login.jsp")>=0){
//					document.location.href='/login.jsp?errorMessage=您的帐号已失效或者已经在别的地方登录!';
//				}
//				startDate=new Date();
//			 }
//		}
//	});
	$("#currentOrgId").live("change",function(){
		if(typeof(onOrgChanged) != 'undefined'){
			onOrgChanged.call(null,this.value,$("#currentOrgId option:selected").attr("isGrid")=="true");
		}
	});
	$("#currentIncidentTypeId").live("change",function(){
		if(typeof(onIncidentTypeChanged) != 'undefined'){
			onIncidentTypeChanged.call(null,node.attributes.id);
		}
	});
	$("a:disabled").unbind().addClass("huise");
});

///////////////////////////////////////////////////
var placeName = '';
var tabName = '';

function deleteOperatorByEncrypt(gridName,allValue,fieldName){
	 var id="";
    if(allValue.length>1){
		  for (i = 0; i < allValue.length; i++) {
			  var rowdata=$("#"+gridName+"List").jqGrid('getRowData',allValue[i]); 
			  if(i==allValue.length-1)
				  id+=rowdata[fieldName];
			  else
				  id+=rowdata[fieldName]+",";
		  }
    }else{
   	 var rowdata=$("#"+gridName+"List").jqGrid('getRowData',allValue); 
        id=rowdata[fieldName];
    }
    return id;
}

function getOrgIdsByIds(gridName,allValue,fieldName){
	var orgIds="";
	 if(allValue.length>1){
		  for (i = 0; i < allValue.length; i++) {
			  var rowdata=$("#"+gridName+"List").jqGrid('getRowData',allValue[i]); 
			  if(i==allValue.length-1)
				  orgIds+=rowdata[fieldName];
			  else
				  orgIds+=rowdata[fieldName]+",";
		  }
   }else{
  	 var rowdata=$("#"+gridName+"List").jqGrid('getRowData',allValue); 
  	 orgIds=rowdata[fieldName];
   }
	 return orgIds;
}

function getCurrentOrgId(){
	var currentOrgId=$("#currentOrgId").val();
	if(currentOrgId){
		return currentOrgId;
	}else{
		return "";
	}
}
function getCurrentIncidentId(){
	var incidentTypeId=	$("#currentIncidentTypeId").val();
	if(incidentTypeId){
		return incidentTypeId;
	}else{
		return "";
	}
}

function getCurrentSelectedOrgId(){
	var currentOrgId=$("#currentIncidentTypeId").val();
	if(currentOrgId){
		return currentOrgId;
	}else{
		return "";
	}
}

function getCurrentTreeOrgId(){
	var currentOrgId=$("input[id='currentOrgId']").val();
	if(currentOrgId){
		return currentOrgId;
	}else{
		return "";
	}
}

function isNullObject(obj){
	if (obj==null || typeof(obj)=="undefined"){
		return true;
	}
	return false;
}

function popLoginDialog(){
	$.createDialog({
		id:"login-dlg",
		url:PATH+'/loginDLG.jsp?date='+new Date(),
		title:"登录超时，请重新登录",
		width:450,
		height:300,
		closeOnEscape:false,
		closeText:false,
		resizable: false
	});
	var cookie_skin = $.cookie("cssSkin");
	switch (cookie_skin) {
		case "default":
			$(".login").parent().css("background","#EAF4FD");
			break;
		case "blue":
			$(".login").parent().css("background","#EEF2FB");
			break;
		case "green":
			$(".login").parent().css("background","#F8FBEC");
			break;
	}
	$(".ui-dialog-titlebar-close").click(function(){document.location.href="/"});
}

function proccessLoginResult(result,callback){
	if(result && result.indexOf("notHasLogin")>=0){

	}else{
		callback();
	}
}

function getDate() {
	var todayDate = new Date();
	var date = todayDate.getDate();
	var month = todayDate.getMonth() + 1;
	var year = todayDate.getFullYear();
	return year + "-" + (month > 10 ? month : "0"+month)  + "-" + (date > 10 ? date : "0"+date);
}

function compareTwoDates(endDate, startDate) {
	return Date.parse(endDate) <= Date.parse(endDate);
}

function compareDateWithNow(currentDate) {
	return Date.parse(currentDate) >= Date.parse(getDate());
}
function setOrgSelect(){
	if($("#currentOrgId").attr("text")){
		$("#contentDiv").find("#globalOrgBtnMod").find("span").text($("#currentOrgId").attr("text"));
		$("#globalOrgBtnMod.globalOrgBtnMod").html($("#currentOrgId").attr("text"));
	}
}

function setCrumbs(srcObj){
    var rootNode = $("#thisCrumbs"),
        firstElm = rootNode.find(".crumbs_first"),
        secondElm = rootNode.find(".crumbs_second"),
        threeElm = rootNode.find(".crumbs_three"),
        curElm = rootNode.find(".crumbs_cur"),
        srcObj = $(srcObj),
        firstTxt = $(".accordionMenuTit .tit").text();
    if(srcObj.parents(".issueMenu").length>0){
    	firstTxt = $("#topMenu .sysMenuList .select").text();
    }
    firstElm.text(firstTxt);
    curElm.text(srcObj.text());
    if( srcObj.parent().is("div") || srcObj.parents(".issueMenu").length>0){
        firstElm.addClass("secondNone");
        secondElm.addClass("secondNone");
        secondElm.empty();
        threeElm.empty();
    }else if(srcObj.parent().is("dd")){
    	 firstElm.removeClass("secondNone");
    	 secondElm.removeClass("secondNone");
    	 secondTxt = srcObj.parents(".uiContBase").prev().text();
     	 threeTxt = srcObj.parents(".hasChild").find("#childName").text();
     	 secondElm.text(secondTxt);
         threeElm.text(threeTxt);
    }else{
    	 firstElm.removeClass("secondNone");
    	 secondElm.addClass("secondNone");
         secondTxt = srcObj.parents(".uiContBase").prev().text();
         secondElm.text(secondTxt);
         threeElm.empty();
    }
}
function asyncOpen(srcObj, url) {
	if(url==undefined || url==''){
		$.messageBox({message:'系统地址出错，请联系管理员',level:'error'});
		return;
	}
	document.title = $(srcObj).text();
	$("#baseLine").nextAll(":not(.ui-autocomplete):not('.ui-datepicker')").remove();
	$("#baseLine").nextAll(":not(.ui-autocomplete):not('.ui-datepicker'):hidden").remove();
	$("#contentDiv").html("");
	$("#loading").show();
	if(window.ajaxRequest){
    	window.ajaxRequest.abort();
	}
	window.ajaxRequest=$.ajax({
		url : url,
		cache: false,
		async: false,
		success : function(result) {
			proccessLoginResult(result,function(){
				$("#loading").hide();
				$("#contentDiv").html(result);
				setOrgSelect();
                setCrumbs(srcObj);
                window.ajaxRequest=null;
			});
		},
		error:function(err){
			$(".dialog_loading").hide();
			window.ajaxRequest=null;
			$.messageBox({message:'系统出错，请刷新页面重试',level:'error'});
		}
	});
}

var Convert = {
    StringToJSON: function(str) {
		return eval('(' + str + ')');
    },
    ToJSONString: function(obj) {
        switch(typeof(obj))
        {
            case 'object':
                var ret = [];
                if (obj instanceof Array)
                {
                    for (var i = 0, len = obj.length; i < len; i++)
                    {
                        ret.push(Convert.ToJSONString(obj[i]));
                    }
                    return '[' + ret.join(',') + ']';
                }
                else if (obj instanceof RegExp)
                {
                    return obj.toString();
                }
                else
                {
                    for (var a in obj)
                    {
                        ret.push(a + ':' + Convert.ToJSONString(obj[a]));
                    }
                    return '{' + ret.join(',') + '}';
                }
            case 'function':
                return 'function() {}';
            case 'number':
                return obj.toString();
            case 'string':
                return "\"" + obj.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function(a) {return ("\n"==a)?"\\n":("\r"==a)?"\\r":("\t"==a)?"\\t":"";}) + "\"";
            case 'boolean':
                return obj.toString();
            default:
                return obj.toString();

        }
    }
};

$.extend({
	dataDistributionPop:function(o){
		var that;
		var dfop={
			width:400,
			height:'auto',
			x:500,
			y:300,
			zIndex:999,
			content:'<div></div>'
		}
		$.extend(dfop,o);
		var pop={
			init:function(){
				this.build();
				this.style(this.tip);
			},
			build:function(){
				var tipContent=$('<div class="tips-text"></div>');
				var tip=$('<div class="tips dataDistributionPop"><div class="tips-angle diamond"></div></div>')
				tipContent.html(dfop.content);
				tip.prepend(tipContent).appendTo("body");
				this.tip=tip;
			},
			style:function(tip){
				tip.css({left:dfop.x,top:dfop.y,width:dfop.width,height:dfop.height})
			},
			destroy:function(){
				$(".dataDistributionPop").remove();
			}
		}
		pop.init();
		that=pop;
		return that;
	}
});


var incidentMenuFunction={	
	predictionScheme:function(selectedorgId,menuType){
		$.selectMenu("predictionScheme-menu");
		function afterLoad(){
			$(".submenu").hide();
			$(".path").show();
			$(".path").load(PATH+"/incident/extTreeForIncident/title.jsp",function(){
				$.ajax({
					url : PATH+'/incident/plaitScheme/index.jsp',
					cache: false,
					success : function(result) {
						proccessLoginResult(result,function(){hideLoading();$("#contentDiv").html(result);});
					}
				});
			});
		}
		plaitSchemeTreeShow(true,afterLoad);
	},
	emergencyDisposal:function(selectedorgId,menuType){
		$.selectMenu("emergencyDisposal-menu");
		menuBoxShow();
		$(".path").hide();
		$(".ui-layout-west").load("/incident/emergencyDisposal/emergencyDisposalSideBar.jsp");
		$(".submenu").hide();
		$(".path").show();
		$(".path").load(PATH+"/incident/emergencyDisposal/left/earlyWarningTitle.jsp");
	},
	incidentManagement:function(selectedorgId,menuType){
		$.selectMenu("incidentManagement-menu");
		function afterLoad(){
			$(".submenu").hide();
			$(".path").show();
			$(".path").load(PATH+"/incident/incidentManage/title.jsp",function(){
				$.ajax({
					url : PATH+'/incident/incidentManage/index.jsp',
					cache: false,
					success : function(result) {
						proccessLoginResult(result,function(){hideLoading();$("#contentDiv").html(result);});
					}
				});
			});
		}
		baseTreeShow(true,afterLoad);
	}
};

function proccessIncidentTypeTreeOption (o,self){
	var defaultOption={
		rootId:false,	//树的根节点ID
		showDegreeRule :true, //是否展开第三层节点数据
		excludeRoot:false,	//树中是否包含根节点
		url:'/incident/systemManage/firstLoadExtTreeData.action',
		isLocalData:false,
		isRootSelected:true, //是否选中根节点
		loadCom:function(){}
	};
	$.extend(defaultOption,o);
	
	var url=PATH+defaultOption.url;
	
	if(!defaultOption.showDegreeRule){
		if(url.indexOf("?")==-1){
			url=url+'?showDegreeRule='+defaultOption.showDegreeRule;
		}else{
			url=url+'&showDegreeRule='+defaultOption.showDegreeRule;
		}
	}
	if(defaultOption.excludeRoot){
		if(url.indexOf("?")==-1){
			url=url+'?excludeRoot='+defaultOption.excludeRoot;
		}else{
			url=url+'&excludeRoot='+defaultOption.excludeRoot;
		}
	}
	
	
	
	
	var treePanelId=self.attr("id");
	var Tree=Ext.tree;
	
	var treePanelOption={
        animate:false, 
        enableDD:false,
        containerScroll: true,
        dropConfig: {appendOnly:true},
        rootVisible : false
    };
	if(!defaultOption.isLocalData){
		treePanelOption.loader = new Tree.TreeLoader({dataUrl:url});
	}
	var treePanel = new Tree.TreePanel(treePanelId, treePanelOption);
	
	treePanel.on('beforeload',function(node){    
    	if(node.id!=(treePanelId+"-root")){
    		var param;
    		if(node.attributes.degreed){
    			 param = '?incidentTypeId='+node.attributes.id + '&degreed='+node.attributes.degreed+ '&showDegreeRule='+defaultOption.showDegreeRule;
    		}else{
    			 param = '?subjectionTypeId='+node.attributes.propertyDictId + '&showDegreeRule='+defaultOption.showDegreeRule;
    		}
    		treePanel.loader.dataUrl = PATH+'/incident/systemManage/ajaxIncidentsForExtTree.action'+param;
    	}
    });  
    var root = new Tree.AsyncTreeNode({
    	text : 'orgManage',
        draggable : false,
        id : (treePanelId+"-root")
    });
    treePanel.setRootNode(root);
    treePanel.render();
    if(!defaultOption.isLocalData){
		root.expand(false,false,function(n){
			if(null!=root.firstChild&&undefined!=root.firstChild){
				root.firstChild.expand(false,false,function(){
					if(defaultOption.isRootSelected){
						treePanel.getSelectionModel().select(root.firstChild);
						treePanel.fireEvent("click",root.firstChild);
					}
				})
			}
		});
    }
    root.expand(false,false,function(){
    	treePanel.exFunc();
    	defaultOption.loadCom();
    });
    treePanel.exFunc = function(){}
	return treePanel;
}
//初始化树
jQuery.fn.extend({
	plaitSchemeTree : function(o){
		return proccessIncidentTypeTreeOption(o,$(this));
	},
	/*系统管理*/
	incidentTypeTree : function(o){
		o=$.extend({},o);
		o.showDegreeRule=false;
		return proccessIncidentTypeTreeOption(o,$(this));
	},
	/*案例库*/
	caseLibarayTree : function(o){
		o=$.extend({},o);
		o.excludeRoot=true;
		return proccessIncidentTypeTreeOption(o,$(this));
	}
});


jQuery.extend({
	rename : function(tree,nodeId,newName){
		tree.getNodeById(nodeId).setText(newName);
	},
	removeNode : function(tree,nodeId){
		var node=tree.getNodeById(nodeId);
		
		node.parentNode.removeChild(node);
	},
	addNode:function(newNode,selNode){
		// var newNode = new Ext.tree.TreeNode({text:txt}); //新结点  
        // var selNode = tree.getSelectionModel().getSelectedNode();//选择的结点  
          //在同级结点之前添加结点  
        //  selNode.appendChild(newNode,selNode); 
		 //在同级结点之后添加新结点  
         // selNode.parentNode.insertBefore(newNode,selNode);  
		//添加子节点
        selNode.appendChild(newNode);  
	}
});

$.TianqueComponent=function(option){
	var defaultOption={
		list:{
			multiselect:true
		}
	}
	$.extend(defaultOption,option);
	new ComponentFactory(defaultOption);
}

var ComponentFactory=function(option){
	for (var key in option){
		this[key](option[key]);
	}
}
ComponentFactory.prototype.afterLoad=function(option){
	
}
ComponentFactory.prototype.list=function(option){
	$("#"+option.id).jqGridFunction(option);
}
ComponentFactory.prototype.buttons=function(option){
	for(var i=0;i<option.length;i++){
		if(option[i].click){
			$("#"+option[i].id).bind("click",{option:option[i]},function(event){
				if(!event.data.option.before||event.data.option.before()){
					$("#"+event.data.option.click.dialogId).createDialog(event.data.option.click);
				}
			});
		}
		if(option[i].change){
			$("#"+option[i].id).bind("change",{option:option[i]},function(event){
				if(!event.data.option.before||event.data.option.before()){
					event.data.option.change();
				}
			});
		}
	}
}
/* SuperSlide1.2 --  Copyright ©2012 大话主席 
 */
;(function($){
	$.fn.slide=function(options){
		$.fn.slide.deflunt={
		effect : "fade", //效果 || fade：渐显； || top：上滚动；|| left：左滚动；|| topLoop：上循环滚动；|| leftLoop：左循环滚动；|| topMarquee：上无缝循环滚动；|| leftMarquee：左无缝循环滚动；
		autoPlay:false, //自动运行
		delayTime : 500, //效果持续时间
		interTime : 2500,//自动运行间隔。当effect为无缝滚动的时候，相当于运行速度。
		defaultIndex : 0,//默认的当前位置索引。0是第一个
		titCell:".hd li",//导航元素
		mainCell:".bd",//内容元素的父层对象
		trigger: "mouseover",//触发方式 || mouseover：鼠标移过触发；|| click：鼠标点击触发；
		scroll:1,//每次滚动个数。
		vis:1,//visible，可视范围个数，当内容个数少于可视个数的时候，不执行效果。
		titOnClassName:"on",//当前位置自动增加的class名称
		autoPage:false,//系统自动分页，当为true时，titCell则为导航元素父层对象，同时系统会在titCell里面自动插入分页li元素(1.2版本新增)
		prevCell:".prev",//前一个按钮元素。
		nextCell:".next"//后一个按钮元素。
		};

		return this.each(function() {
			var opts = $.extend({},$.fn.slide.deflunt,options);
			var index=opts.defaultIndex;
			var prevBtn = $(opts.prevCell, $(this));
			var nextBtn = $(opts.nextCell, $(this));
			var navObj = $(opts.titCell, $(this));//导航子元素结合
			var navObjSize = navObj.size();
			var conBox = $(opts.mainCell , $(this));//内容元素父层对象
			var conBoxSize=conBox.children().size();
			var slideH=0;
			var slideW=0;
			var selfW=0;
			var selfH=0;
			var autoPlay = opts.autoPlay;
			var inter=null;//setInterval名称 
			var oldIndex = index;

			if(conBoxSize<opts.vis) return; //当内容个数少于可视个数，不执行效果。

			//处理分页
			if( navObjSize==0 )navObjSize=conBoxSize;
			if( opts.autoPage ){
				var tempS = conBoxSize-opts.vis;
				navObjSize=1+parseInt(tempS%opts.scroll!=0?(tempS/opts.scroll+1):(tempS/opts.scroll)); 
				navObj.html(""); 
				for( var i=0; i<navObjSize; i++ ){ navObj.append("<li>"+(i+1)+"</li>") }
				var navObj = $("li", navObj);//重置导航子元素对象
			}

			conBox.children().each(function(){ //取最大值
				if( $(this).width()>selfW ){ selfW=$(this).width(); slideW=$(this).outerWidth(true);  }
				if( $(this).height()>selfH ){ selfH=$(this).height(); slideH=$(this).outerHeight(true);  }
			});

			switch(opts.effect)
			{
				case "top": conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:'+opts.vis*slideH+'px"></div>').css( { "position":"relative","padding":"0","margin":"0"}).children().css( {"height":selfH} ); break;
				case "left": conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:'+opts.vis*slideW+'px"></div>').css( { "width":conBoxSize*slideW,"position":"relative","overflow":"hidden","padding":"0","margin":"0"}).children().css( {"float":"left","width":selfW} ); break;
				case "leftLoop":
				case "leftMarquee":
					conBox.children().clone().appendTo(conBox).clone().prependTo(conBox); 
					conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:'+opts.vis*slideW+'px"></div>').css( { "width":conBoxSize*slideW*3,"position":"relative","overflow":"hidden","padding":"0","margin":"0","left":-conBoxSize*slideW}).children().css( {"float":"left","width":selfW}  ); break;
				case "topLoop":
				case "topMarquee":
					conBox.children().clone().appendTo(conBox).clone().prependTo(conBox); 
					conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:'+opts.vis*slideH+'px"></div>').css( { "height":conBoxSize*slideH*3,"position":"relative","padding":"0","margin":"0","top":-conBoxSize*slideH}).children().css( {"height":selfH} ); break;
			}

			//效果函数
			var doPlay=function(){
				switch(opts.effect)
				{
					case "fade": case "top": case "left": if ( index >= navObjSize) { index = 0; } else if( index < 0) { index = navObjSize-1; } break;
					case "leftMarquee":case "topMarquee": if ( index>= 2) { index=1; } else if( index<0) { index = 0; } break;
					case "leftLoop": case "topLoop":
						var tempNum = index - oldIndex; 
						if( navObjSize>2 && tempNum==-(navObjSize-1) ) tempNum=1;
						if( navObjSize>2 && tempNum==(navObjSize-1) ) tempNum=-1;
						var scrollNum = Math.abs( tempNum*opts.scroll );
						if ( index >= navObjSize) { index = 0; } else if( index < 0) { index = navObjSize-1; }
					break;
				}
				switch (opts.effect)
				{
					case "fade":conBox.children().stop(true,true).eq(index).fadeIn(opts.delayTime).siblings().hide();break;
					case "top":conBox.stop(true,true).animate({"top":-index*opts.scroll*slideH},opts.delayTime);break;
					case "left":conBox.stop(true,true).animate({"left":-index*opts.scroll*slideW},opts.delayTime);break;
					case "leftLoop":
						if(tempNum<0 ){
								conBox.stop(true,true).animate({"left":-(conBoxSize-scrollNum )*slideW},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().last().prependTo(conBox); }
								conBox.css("left",-conBoxSize*slideW);
							});
						}
						else{
							conBox.stop(true,true).animate({"left":-( conBoxSize + scrollNum)*slideW},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().first().appendTo(conBox); }
								conBox.css("left",-conBoxSize*slideW);
							});
						}break;// leftLoop end

					case "topLoop":
						if(tempNum<0 ){
								conBox.stop(true,true).animate({"top":-(conBoxSize-scrollNum )*slideH},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().last().prependTo(conBox); }
								conBox.css("top",-conBoxSize*slideH);
							});
						}
						else{
							conBox.stop(true,true).animate({"top":-( conBoxSize + scrollNum)*slideH},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().first().appendTo(conBox); }
								conBox.css("top",-conBoxSize*slideH);
							});
						}break;//topLoop end

					case "leftMarquee":
						var tempLeft = conBox.css("left").replace("px",""); 

						if(index==0 ){
								conBox.animate({"left":++tempLeft},0,function(){
									if( conBox.css("left").replace("px","")>= 0){ for(var i=0;i<conBoxSize;i++){ conBox.children().last().prependTo(conBox); }conBox.css("left",-conBoxSize*slideW);}
								});
						}
						else{
								conBox.animate({"left":--tempLeft},0,function(){
									if(  conBox.css("left").replace("px","")<= -conBoxSize*slideW*2){ for(var i=0;i<conBoxSize;i++){ conBox.children().first().appendTo(conBox); }conBox.css("left",-conBoxSize*slideW);}
								});
						}break;// leftMarquee end

						case "topMarquee":
						var tempTop = conBox.css("top").replace("px",""); 
							if(index==0 ){
									conBox.animate({"top":++tempTop},0,function(){
										if( conBox.css("top").replace("px","") >= 0){ for(var i=0;i<conBoxSize;i++){ conBox.children().last().prependTo(conBox); }conBox.css("top",-conBoxSize*slideH);}
									});
							}
							else{
									conBox.animate({"top":--tempTop},0,function(){
										if( conBox.css("top").replace("px","")<= -conBoxSize*slideH*2){ for(var i=0;i<conBoxSize;i++){ conBox.children().first().appendTo(conBox); }conBox.css("top",-conBoxSize*slideH);}
									});
							}break;// topMarquee end


				}//switch end
					navObj.removeClass(opts.titOnClassName).eq(index).addClass(opts.titOnClassName);
					oldIndex=index;
			};
			//初始化执行
			doPlay();

			//自动播放
			if (autoPlay) {
					if( opts.effect=="leftMarquee" || opts.effect=="topMarquee"  ){
						index++; inter = setInterval(doPlay, opts.interTime);
						conBox.hover(function(){if(autoPlay){clearInterval(inter); }},function(){if(autoPlay){clearInterval(inter);inter = setInterval(doPlay, opts.interTime);}});
					}else{
						 inter=setInterval(function(){index++; doPlay() }, opts.interTime); 
						$(this).hover(function(){if(autoPlay){clearInterval(inter); }},function(){if(autoPlay){clearInterval(inter); inter=setInterval(function(){index++; doPlay() }, opts.interTime); }});
					}
			}

			//鼠标事件
			var mst;
			if(opts.trigger=="mouseover"){
				navObj.hover(function(){ clearTimeout(mst); index=navObj.index(this); mst = window.setTimeout(doPlay,200); }, function(){ if(!mst)clearTimeout(mst); });
			}else{ navObj.click(function(){index=navObj.index(this);  doPlay(); })  }
			nextBtn.click(function(){ index++; doPlay(); });
			prevBtn.click(function(){  index--; doPlay(); });

    	});//each End

	};//slide End

})(jQuery);


$.fn.workBenchGridFunction=function(option){
	var self=$(this);
	var defaultOption={
		datatype: "json",
		rowList:[10,15,20,30,50,80,100],
		gridview:true,
		viewrecords:true,
		jsonReader:{
			repeatitems:false,
			id:"0"
		},
		autowidth:true,
		scrollrows:true,
	   	sortname: 'id',
	    sortorder: "desc",
		pager: false,
		loadComplete:function(){
		},
		shrinkToFit:true,
		rowNum:7,
		height:170,
		showColModelButton:false
	}
	$.extend(defaultOption,option);
	self.jqGrid(defaultOption);
};

$.lunarInsert=function(o){
	var dfop={
		defaultDate:''
	}
	$.extend(dfop,o);
	var dateArr=dfop.defaultDate.split("-");
	var thisDate={
		year:dateArr[0],
		month:dateArr[1],
		day:dateArr[2]
	}
	var lunar=$.lunarCalendar(thisDate);
	var lunarDate=lunar.sx+'年'+lunar.lunarMonth+lunar.lunarDay;
	var week='星期'+lunar.week;
	var solarTerms=lunar.solarTerms==''?'':lunar.solarTerms;
	$(".lunarCalendar #today").html(parseInt(thisDate.day,10)+'<span>'+solarTerms+'</span>');
	$(".lunarCalendar #today_info").empty()
			.append('<div>'+lunar.sYear+'年'+lunar.sMonth+'月'+lunar.sDay+'日'+"&nbsp;&nbsp;"+week+'</div>')
			.append('<div>'+lunarDate+"<span>"+lunar.solarFestival+'</span></div>')
			//.append(lunar.dom);
};
$.fn.extend({
	workbenchModule:function(o,panelData){
		var self=$(this);
		var dfop={
			/*data:[
				{id:"informationTrain",title:"信息直通车",url:"/workBench/module/informationTrain.jsp"},
				{id:"basicInformation",title:"基础信息",url:"/sysadmin/menuManage/getLowLevelBaseInfoMenuListByPageList.action?ename=basicInformation"},
				{id:"statAnalyse",title:"统计分析",url:"/workBench/module/tabMenuList.jsp"},
				{id:"interactionManagement",title:"短信交流",url:"/workBench/module/tabMenuList.jsp"},
				{id:"issue",title:"事件处理",url:"/workBench/module/tabMenuList.jsp"},
				{id:"workingRecordMenu",title:"工作台帐",url:"/workBench/module/tabMenuList.jsp"}
			],
			*/
			url:'',
			custom:'customPanel',
			scroll:false,
			connectWith: ".column",
			cancel:'.portlet-content',
			delay: 200,
			forceHelperSize:true,
			tolerance: 'pointer',
			containment: '.workbench',//始钟只能在该容器中拖动
			dropOnEmpty: false,
			revert: true,
			start:function(event,ui){
				$(".ui-sortable-placeholder").width($(ui.item).width()).height($(ui.item).height());//辅助框宽高
			},
			stop:function(event,ui){
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildConfiguration.action",
					data: { keyNames: $("#workbench").workbenchModule("getValue"),mode:'edit' },
					success:function(dom){

					}
				});
			}
		}
		$.extend(dfop,o);
		var positionPanel=function(){
			
		}
		var createPortlet=function(data){//创建面板
			var title=data.title;
			var content=data.content;
			var url=data.url;
			if($("#"+data.id)[0]){return false;}
			var $column=$("<div />").addClass("column").attr("id",data.id).data("data",data);
			var $portlet=$("<div />").addClass("portlet ui-widget ui-helper-clearfix");
			var $portletHeader=$("<div />").addClass("portlet-header ui-widget-header").text(data.title);
			var $portletHeaderIcon=$("<div class='panelBtn'><a href='javascript:;' class='panel-refresh' title='刷新'></a><a href='javascript:;' class='panel-set' title='设置'></a><a href='javascript:;' class='panel-max' title='最大化'></a><a href='javascript:;' class='panel-close' title='关闭'></a></div>");
			var $portletContent=$("<div />").addClass("portlet-content");
			if(data.maxurl!=undefined){
				$column.attr("maxurl",data.maxurl);
			}
			self.append($column.append($portlet));
			$portlet.append($portletHeader.append($portletHeaderIcon)).append($portletContent);
			$.ajax({
				type: "POST",
				url: "/patel/patelManage/buildConfiguration.action",
				data: { keyName:data.id,mode:'add' },
				success:function(flag){
					if(flag==true){
						$.messageBox({message:"添加标签成功"});
					}
					$.ajax({
						url:'/patel/patelManage/getTabPatelConfiger.action',
						data:{keyName:data.id},
						success:function(dom){
							$portletContent.html(dom);
							positionPanel();
						}
					})
				}
			});
		};
		var removePortlet=function(eName){//移除面板
			$.ajax({
				type: "POST",
				url: "/patel/patelManage/buildConfiguration.action",
				data: { keyName: eName,mode:'delete' },
				success:function(data){
					if(data==true){
						var thisIndex=$("#"+eName).index();
						if(thisIndex!=-1){
							$("#"+eName).remove();
							$("#"+dfop.custom+" input:checkbox[value='"+eName+"']").attr("checked", false);
						}
						$.messageBox({message:"删除标签成功"});
					}
				}
			})
		};
		/*
		var build=function(){//构建面板
			for(var i in dfop.data){
				if(typeof dfop.data[i] == 'object'){
					createPortlet(dfop.data[i]);
				}
			}
		}
		*/
		switch(o){
			case 'add'://添加面板
				if(typeof panelData=='object'){
					var checkbox=$("#"+dfop.custom+" input:checkbox[value='"+panelData.id+"']");
					if(checkbox.attr("maxurl")){
						panelData.maxurl=checkbox.attr("maxurl");
					}
					createPortlet(panelData);
					checkbox.attr("checked", true);
				}
				return false;
				break;
			case 'remove'://删除面板
				if(typeof panelData=='string'){
					removePortlet(panelData);
				}
				return false;
				break;
			case 'getValue'://获取组件Value
				var panelData= "";
				$('.column').each(function(i){
					panelData=panelData+","+$(this).data("data").id;
				});
				return panelData.substr(1);
				break;
			case 'getLength'://获取组件数量
				return $('.column').size();
				break;
		}
		var check=function(name){
			if(name=='' || name==undefined || name==null){
				return false;
			}
			return true;
		}
		var bindEvent=function(){
			$("#"+dfop.custom+" input[value=statAnalyse]").attr("disabled","disabled");
			$("#"+dfop.custom).delegate("input:checkbox","click",function(){//选择面板
				var id=$(this).attr("value");
				var title=$(this).parent().text();
				var url=$(this).attr("loadUrl");
				var parentTitle=$(this).closest("dl").parent().children("label").text();
				if(parentTitle!=''){
					parentTitle=parentTitle+'-';
				}
				if(!(check(id) && check(title))){
					alert("id,title不能为空");
					return false;
				}
				if($(this).attr("checked")){
					self.workbenchModule("add",{id:id,title:parentTitle+title});
				}else{
					self.workbenchModule("remove",id);
				}
			})
			self.delegate(".panel-refresh","click",function() {//刷新事件
				var thisPanel=$(this).closest(".column");
				var thisPanelDate=thisPanel.data("data");
				var thisTabs=$(this).closest(".column").find(".ui-tabs:visible");
				var thisIndex=thisTabs.find(".ui-tabs-selected").index();
				thisTabs.tabs("load",thisIndex);
				//thisPanel.find(".portlet-content").load(thisPanelDate.url)
			});
			self.delegate(".panel-set","click",function() {//设置事件
				var configPanel=$(this).closest(".portlet-header").next().find(".panelconfig");
				if(!configPanel.find(".configPanelClose")[0]){
					var arraw=$('<div class="tips-angle diamond"></div>');
					
					/*var configPanelClose=$('<a href="javascript:;" class="configPanelClose">X</a>');
					configPanelClose.click(function(){
						configPanel.hide();
					});*/
					configPanel.prepend(arraw);
				}
				if(configPanel.is(":visible")){
					configPanel.hide();
				}else{
					configPanel.show();
				}
			});
			self.delegate(".panel-max","click",function() {//最大化事件
				var thisPanel=$(this).closest(".column")
				var thisPanelData=thisPanel.data("data");
				var selectTab=thisPanel.find(".ui-tabs").find(".ui-tabs-nav").find("li.ui-tabs-selected").find("a");
				var maxUrl;
				var dialogTitle=selectTab.text();
				var thisDialogId=thisPanelData.id+selectTab.attr("id")+"Dialog";
				if($("#"+thisDialogId)[0]){
					$("#"+thisDialogId).dialog("open");
					return false;
				}
				if(selectTab.attr("maxurl")=='' || selectTab.attr("maxurl")==undefined){
					if(thisPanel.attr("maxurl")!='' || thisPanel.attr("maxurl")!=undefined){
						maxUrl=thisPanel.attr("maxurl");
					}
				}else{
					thisPanelData.title=thisPanelData.title+"-";
					maxUrl=selectTab.attr("maxurl");
				}
				function maxStatAnalyseStyle(){
					var panelSize=$(".maxStatAnalyse").size()/2;
					var panelHeight=$("#maxstat${type}").parent().height()/panelSize-20;
					$(".maxStatAnalyse").height(panelHeight)
				}
				$("body").append('<div id="'+thisDialogId+'" />');
				$("#"+thisDialogId).createDialog({//构建dialog
					url:maxUrl,
					shouldEmptyHtml:false,
					width:document.documentElement.clientWidth-10,
					height:document.documentElement.clientHeight-10,
					minWidth:300,
					minHeight:280,
					zIndex:999,
					modal:false,
					resizable:true,
				    title:thisPanelData.title+dialogTitle,
				    close:function(){
				    	$("#"+thisDialogId).remove();
				    },
				    resizeStop:function(){
				    	
				    }
				});
			});

			self.delegate(".panel-close","click",function() {//删除事件
				var thisPanelData=$(this).closest(".column").data("data");
				self.workbenchModule("remove",thisPanelData.id);
			});
		}()
		var init=function(){//获取组件value
			$('.column').each(function(i){
				var $portletHeaderIcon=$("<div class='panelBtn'><a href='javascript:;' class='panel-refresh' title='刷新'></a><a href='javascript:;' class='panel-set' title='设置'></a><a href='javascript:;' class='panel-max' title='最大化'></a><a href='javascript:;' class='panel-close' title='关闭'></a></div>");
				$(this).find(".portlet-header").append($portletHeaderIcon);
			});
			$("#"+dfop.custom).find("input:checkbox").each(function(i){
				var thisData={};
				thisData.id=$(this).attr("value");
				thisData.title=$(this).parent().text();
				thisData.url=$(this).attr("loadUrl");
				$("#"+thisData.id).data("data",thisData);
			});
			positionPanel();
		}();
		$(window).resize(function(){
			clearTimeout(window._timer);
			window._timer=setTimeout(positionPanel,500);
		})
		self.sortable(dfop).data("data",dfop.data);
		return self;
	},
	workBenchTabs:function(o,data){
		var self=$(this);
		var selfId=self.attr("id");
		var keyName=selfId.substr(0,selfId.length-4);
		var dfop={
			tabTemplate: "<li><a href='#{href}'>#{label}</a></li>",
			ajaxOptions: {
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html("读取页面出错了。");
				}
			},
			cache:false
		}
		$.extend(dfop,o);
		switch(o){
			case 'add':
				if(typeof data=='object' && data!=undefined){
					var dom=self.find("li").filter(function(index) {//获取该dom元素节点
						  return $(this).text() == data.title;
					});
					if(!dom[0]){
						self.tabs("add",data.url,data.title);
					}
				}
				return false;
				break;
			case 'remove':
				if(typeof data=='string'){
					var dom=self.find(".ui-tabs-nav").find("li").filter(function(index) {//获取该dom元素节点
						var thisData=$.trim(data);
						var thisText=$.trim($(this).text());
						return thisText == thisData;
					});
					if(dom.index()!=-1){
						self.tabs("remove",dom.index());
					}
				}
				return false;
				break;
			case 'getValue'://获取组件Value
				var panelData="";
				self.find(".ui-tabs-nav").find("li").each(function(i){
					var thisText=$(this).text();
					var dom=$("#"+selfId+"Config").find("li").filter(function(index) {//获取该dom元素节点
						  return $(this).text() == thisText;
					});
					panelData=panelData+","+dom.eq(0).find("input:checkbox").attr("value");
				});
				return panelData.substr(1);
				break;
			case 'getLength'://获取组件数量
				var panelDataNum=0;
				self.find(".ui-tabs-nav").find("li").each(function(i){
					panelDataNum++;
				});
				return panelDataNum;
				break;
		}
		self.tabs(dfop);
		$("#"+selfId+"Config li input").click(function(){//配置层checkbox选中事件
			var title=$(this).parent().text();
			var loadUrl=$(this).attr("loadUrl");
			var maxUrl=$(this).attr("maxurl");
			var tabKeyName=$(this).val();
			var selectCheckbox=$("#"+selfId+"Config li input:checked");
			if(selectCheckbox.size()>4){
				$.messageBox({level:"error",message:"标签最多只能添加4个"});
				return false;
			}
			if($(this).attr("checked")){
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:keyName,tabKeyName:tabKeyName,mode:'add' },
					success:function(data){
						if(data==true){
							$("#"+selfId).workBenchTabs("add",{title:title,url:loadUrl});
							var dom=self.find(".ui-sortable").find("a").filter(function(index) {//获取该dom元素节点
								var thisData=$.trim(title);
								var thisText=$.trim($(this).text());
								return thisText == thisData;
							});
							if(dom.index()!=-1){
								dom.attr("maxurl",maxUrl);
							}
							$.messageBox({message:"添加标签成功"});
						}
					}
				});

			}else{
				$("#"+selfId).workBenchTabs("remove",title);
				if($("#"+selfId).workBenchTabs("getLength")==0){
					$("#workbench").workbenchModule("remove",keyName);
				}
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:keyName,tabKeyName:tabKeyName,mode:'delete'},
					success:function(data){
						if(data==true){
							$.messageBox({message:"移除标签成功"});
						}
					}
				});
			}
		})
	},
	searchSelect:function(options){
		var $self=$(this);
		var $options=$self.children("option");
		var $downlist=$("<div class='updownList'></div>");
		var $createDl=$("<dl/>");
		var $createDt=$("<dt/>");
		var $createDd=$("<div class='updwonlist-Content'></div>");
		var $createUl=$("<ul/>");
		var $c=$options.first().text();

		$self.after($downlist);
		$downlist.append($createDl);
		$createDl.append($createDt);
		$createDl.append($createDd);
		$createDd.append($createUl);
		$createDt.text($c);

		var defaultOptions={
				updownlist:'.updownList',
				updownlistC:'.updwonlist-Content',
				current:'cur',
				change:function(thisText,thisValue){

				}
		}
		$.extend(defaultOptions,options);
		var init=function(){
			$options.each(function(index){
				var $createLl=$("<li/>");
				var thisValue=$(this).attr("value");
				var $con=$(this).text();
				$createUl.append($createLl);
				$createLl.text($con).data("value",thisValue);
				$createLl.click(function(){
					var thisText=$(this).text();
					var thisValue=$(this).data("value");
					$(this).addClass(defaultOptions.current).siblings().removeClass(defaultOptions.current);
					$createDt.text($(this).text());
					$self.val(thisValue);
					$createDd.hide();
					defaultOptions.change(thisText,thisValue);
				})
			 })
		}();

		var clickUnit=function(){
			$createDt.bind("click",function(e){
				if($createDd.is(":visible")){
					 $createDd.hide();
				}else{
					 $createDd.show();
				}
			})

			$(document).on("click",function(e){
				var $target=$(e.target);
				if(!($target.is(defaultOptions.updownlistC) || $target.closest(defaultOptions.updownlistC)[0] || $target.closest(defaultOptions.updownlist)[0])){
					$createDd.hide();
				}
			})
		}()
	},
	workMemoPicker:function(o){
		var self=$(this);
		var selfId=self.attr("id");
		Array.prototype.uniq = function() {
	        var temp = {}, len = this.length;

	        for(var i=0; i < len; i++)  {
	            if(typeof temp[this[i]] == "undefined") {
	                temp[this[i]] = 1;
	            }
	        }
	        this.length = 0;
	        len = 0;
	        for(var i in temp) {
	        	i=i.replace(/0\d/g, function(num){//只在日期时作用
					return num.substring(1);
				});

	            this[len++] = i;
	        }
	        return this;
	    }
		var loadDate=function(thisYear,thisMonth){
			$.ajax({
				url:'/workMemo/workMemoManage/getDaysByUserIdAndDate.action',
				type:'POST',
				data:{year:thisYear,month:thisMonth},
				success:function(date){
					//var date=[12,27];//此处需要发送ajax请求，到后台load有数据的date
					if(date.listDays!=null && date.listDays!=undefined){
						/*for(var i=0;i<date.length;i++){
							var temp=date[i];
							if(temp.charAt(0)=='0'){
								date[i]=temp.substring(1);
							}
						}*/
						date.listDays.uniq();
					}
					if(date.userSignDays!=null && date.userSignDays!=undefined){
						date.userSignDays.uniq();
					}

					setTimeout(function(){
						setDatePicker(date.listDays);
						setSign(date.userSignDays);
					},200);
				}
			});
		}
		var upDate=function(){
			var selfDate=[];
			self.find("a.hasData").each(function(){
				var thisDate=$(this).text();
				selfDate.push(thisDate);
			})
			self.data("data",selfDate);
			var selfSign=[];
			self.find("td.hasSign").each(function(){
				var thatDate=$(this).find('a').text();
				selfSign.push(thatDate);
			})
			self.data("sign",selfSign);
		}
		var dfop={
			showButtonPanel: false,
			yearRange: '1900:2030',
			onChangeMonthYear:function(year, month, inst){
				loadDate(year,month);
			},
			onSelect:function(dateText, inst){
				$(".workMemoTip").remove();
				var dom=$(this).find("a").filter(function(index) {//获取该dom元素节点
					  return $(this).text() == inst.selectedDay;
				});
				var thisYearDate=$("#workMemo").data("data");//获取当前有数据的日期
				var thisSign = $("#workMemo").data("sign");
				console.log(thisSign)
				setTimeout(function(){//处理与jquery ui的冲突问题
					setDatePicker(thisYearDate);
					setSign(thisSign)
				},10);
				var dateArr=dfop.defaultDate.split("-");
				var thisDate={
					year:dateArr[0],
					month:dateArr[1],
					day:dateArr[2]
				}
				var todayTime=new Date(thisDate.year,parseInt(thisDate.month, 10)-1,thisDate.day).getTime();
				var selectTime=new Date(inst.currentYear,inst.currentMonth,inst.currentDay).getTime();
				if(dom.data("hasData")){//当前无数据，则返回
					$(".addworkmemos").hide();
					initDatePickerDom(dom,dateText,inst);
				}else{
					if(todayTime<=selectTime){
						showAddworkmemos(inst);
					}else{
						$(".addworkmemos").hide();
					}
				};
				var instDate=inst.currentYear+'-'+(inst.currentMonth+1)+'-'+inst.currentDay;
				$(".addworkmemos #addWorkDate").text(inst.currentYear+'年'+(inst.currentMonth+1)+'月'+inst.currentDay+'日');
				$(".addworkmemos #workDate").val(instDate);
				$(".addworkmemos #text").val("");
				$.lunarInsert({defaultDate:instDate});
			}
		}
		$.extend(dfop,o);
		var initDatePickerDom=function(dom,dateText,inst){
			var workMemoTip=$("<div />").addClass("workMemoTip");
			var workMemoTipCtt=$("<ul />");
			var appBtn=$('<a href="javascript:;">继续新增备忘</a>');
			workMemoTip.appendTo("body").append('<div class="tips-angle diamond"></div>');

			$.ajax({
				url:'/workMemo/workMemoManage/getMemoContentsByUserIdAndAddMemoDate.action',
				type:'POST',
				data:{date:inst.currentYear+"-"+((inst.currentMonth+1)<10 ? ("0"+(inst.currentMonth+1)) : (inst.currentMonth+1))+"-"+(inst.currentDay<10 ? ("0"+inst.currentDay) : inst.currentDay)},
				success:function(data){
					for(var i=0;i<data.length;i++){
						workMemoTipCtt.append('<li >'+data[i].memoContents+'<a href="javascript:;" id="'+data[i].id+'">删除</a></li>');
					}
				}
			});


			//tip定位以及插入数据
			workMemoTip.append('<h1 class="title">'+dateText+'</h1>').append(workMemoTipCtt).css({
				top:(dom.offset().top-27-40)+"px",
				left:(dom.offset().left+28)+"px"
			});
			var dateArr=dfop.defaultDate.split("-");
			var todayTime=new Date(dateArr[0],parseInt(dateArr[1],10)-1,dateArr[2]).getTime();
			var selectTime=new Date(inst.currentYear,inst.currentMonth,inst.currentDay).getTime();
			if(selectTime>=todayTime){
				workMemoTip.append(appBtn);
			}

			appBtn.click(function(){
				showAddworkmemos(inst);
			})
			workMemoTipCtt.delegate("a","click",function() {//删除事件
				var that=$(this);
				var id=that.attr("id");
				$.confirm({
					title:"确认删除",
					message:"确定要删除吗?一经删除将无法恢复",
					okFunc: function() {
						$.ajax({
							url:"/workMemo/workMemoManage/deleteWorkMemoById.action?workMemoId="+id,
							type:'POST',
							success:function(data){
								$.messageBox({message:"已经成功删除该备忘录!"});
								var today=new Date(dfop.defaultDate).getFullYear()+'-'+(new Date(dfop.defaultDate).getMonth()+1)+'-'+new Date(dfop.defaultDate).getDate();
								var currentDay=$(".addworkmemos #workDate").val();
								if(today==currentDay){
									$('.today ul li#'+data).remove();
								}
								that.parents("li").remove();
								if($(".workMemoTip ul li").size()<=0){
									var dom=$("#workMemo").find("a").filter(function(index) {
										return $(this).text() ==currentDay.split("-")[2];
									});
									dom.removeClass("hasData").data("hasData",false);
									upDate();
									$(".workMemoTip").hide();
								}
							}
						});
					}
				});
			})
		}
		var showAddworkmemos=function(inst){
			$(".workMemoTip").remove();
			$(".addworkmemos").show();
		}
		var setDatePicker=function(data){
			for(var i in data){
				if(typeof data[i] != 'function' && data[i]!=undefined){
					var thisDate=data[i];
					var dom=$("#"+selfId).find("a").filter(function(index) {
						  return $(this).text() == thisDate;
					});
					dom.addClass("hasData").data("hasData",true);
				}
			}
		}
		var setSign=function(data){
			for(var i in data){
				if(typeof data[i] != 'function' && data[i]!=undefined){
					var thatDate=data[i];
					var dom=$("#"+selfId).find("a").filter(function(index) {
						  return $(this).text() == thatDate;
					});
					dom.parent().addClass("hasSign").data("hasSign",true);
				}
			}
			upDate();
		}
		switch(o){
			case 'update':
				upDate();
				return false;
				break;
		}
		$("#workMemo").datePicker(dfop);
		var dateArr=dfop.defaultDate.split("-");
		loadDate(dateArr[0],dateArr[1]);
	},
	informationTrain:function(){
		var self=$(this);
		var selfId=self.attr("id");
		$("#"+selfId+"Config").delegate("input:checkbox","click",function(){
			var id=$(this).attr("value");
			var text=$(this).parent().text();
			var url=$(this).attr("loadUrl");
			var imageUrl=$(this).attr("imageUrl");
			var thisIcon=$('<a />').attr("href",url).attr("class","icon").attr("id",id).append('<img src="'+imageUrl+'" />').append(text);
			if($(this).attr("checked")){
				$(".informationTrain #"+id).remove();
				$(".informationTrain").append(thisIcon);
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:'informationTrain',tabKeyName:id,mode:'add' },
					success:function(data){
						if(data==true){
					//		$.messageBox({message:"添加标签成功"});
						}
					}
				});
			}else{
				$(".informationTrain #"+id).remove();
				if(getInformationTrainValue()==""){
					$("#workbench").workbenchModule("remove",'informationTrain');
				}
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:'informationTrain',tabKeyName:id,mode:'delete' },
					success:function(data){
						if(data==true){
					//		$.messageBox({message:"添加标签成功"});
						}
					}
				});
			}
		})

		self.sortable({
			revert: true,
			placeholder: "ui-state-highlight",
			start:function(event,ui){
				$("#informationTrainTabs .ui-state-highlight").width($(ui.item).width()).height($(ui.item).height());//辅助框宽高
			},
			stop:function(){
				$.ajax({
					type: "POST",
					url: "/patel/patelManage/buildTabConfiguration.action",
					data: { keyName:'informationTrain',keyNames:getInformationTrainValue(),mode:'edit' },
					success:function(data){
						if(data==true){
							$.messageBox({message:"移动菜单成功"});
						}
					}
				});
			}
		});
		function getInformationTrainValue(){
			var thisValue ="";
			   self.find("a").each(function(){
			   thisValue=thisValue+","+$(this).attr("id");
			})
			return thisValue.substr(1);
		}
	}
});
function isComment(el, options, rowData){
	if(rowData.commentNums > 0){
		return "<img src='"+RESOURCE_PATH+"/resource/system/images/normalcard.gif'>";
	}else{
		return "";
	}
}
function myLogstatistics(){
	$.ajax({
		async: false,
		url: PATH+"/peopleLog/commentLogManage/getStatisticsByOrgId.action",
		success:function(data){
			$("#countNum").html(data.countNum);
			$("#commentNum").html(data.commentNum);
		}
	});
}
function subLogstatistics(){
	$.ajax({
		async: false,
		url: PATH+"/peopleLog/commentLogManage/getStatisticsByOrgCode.action",
		success:function(data){
			$("#countSubMeNum").html(data.countSubMeNum);
			$("#commentSubMeNum").html(data.commentSubMeNum);
			$("#countSubAllNum").html(data.countSubAllNum);
			$("#commentSubAllNum").html(data.commentSubAllNum)
		}
	});
}
function doubleClickTable(){
    var selectedId = $("#peopleLogList").getGridParam("selrow");
	var rowData = $("#peopleLogList").getRowData(selectedId);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	$("#logViewDialog").createDialog({
		width:600,
		height:450,
		title:"查看日志信息",
		url:PATH+"/peopleLog/commentLogManage/dispatchOperate.action?mode=view&peopleLog.id="+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function getmonth(year,callback){
    $.ajax({
        async: false,
        url: PATH+"/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+year,
        success:function(responseData){
        callback(responseData);
        }
    });
}
function columnChart(name,moduleName,url,isMax){
    if(isMax){
        $("#"+name).columnChart({
            url: url,
            moduleName:moduleName,
            textx:-150,
            quantity:'个数'
        },{legend:{itemWidth:180,itemStyle:{height:20},symbolWidth:50}});
    }else{
     $("#"+name).columnChart({
        url: url,
        moduleName:moduleName,
        textx:-150,
        quantity:'个数'
    },{title:false,workbenchColum:true,yAxis:{title:{text:''}},legend:{ enabled:false}});
    }
}
function trendChart(name,moduleName,url,isMax){
    if(isMax){
     $("#"+name).lineChart({
        url: url,
        moduleName:moduleName
    });}else{
        $("#"+name).lineChart({
            url: url,
            moduleName:moduleName,
            width:800
        },{title:false,yAxis:{title:{text:''}},legend:{ enabled:false}});
    }

}

function listChart(name,moduleName,url,columns,isMax){
    $.ajax({
        url:url,
        success:function(data){
            if(data == null){
                $.messageBox({
                    message:"查询的月份没有数据生成",
                    level: "error"
                });
                return;
            }
            var context = {};

            var grid = new SigmaReport(name,context,columns,'SigmaReport','',moduleName,false);
            grid.bindData(data);

        }
    })
}

function pieChart(name,moduleName,url,isMax){
    if(isMax){
     $("#"+name).pieChart({
        url:url,
        moduleName:moduleName
    });}else{
         $("#"+name).pieChart({
                url:url,
                moduleName:moduleName,
                width:$("#"+name).parent().width(),
                height:200
            },{title:false,plotOptions:{pie:{ dataLabels: {enabled: false}}}});
    }
}
function getYear(callback){
    $.ajax({
        async: false,
        url: PATH+"/stat/currentTime/getCurrentTimeForYear.action",
        success:function(responseData){
        callback(responseData);
        }
    });
}

$.lunarCalendar=function(date) {
    var Today = new Date();
	var tY = Today.getFullYear();
	var tM = Today.getMonth();
	var tD = Today.getDate();
		
		
	var lunarInfo = new Array(
	        0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af, 0x9ad0, 0x55d2,
	        0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd255, 0xb54f, 0xd6a0, 0xada2, 0x95b0, 0x4977,
	        0x497f, 0xa4b0, 0xb4b5, 0x6a50, 0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970,
	        0x6566, 0xd4a0, 0xea50, 0x6a95, 0x5adf, 0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f,
	        0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2, 0xa950, 0xb557,
	        0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573, 0x52bf, 0xa9a8, 0xe950, 0x6aa0,
	        0xaea6, 0xab50, 0x4b60, 0xaae4, 0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0,
	        0x96d0, 0x4dd5, 0x4ad0, 0xa4d0, 0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6,
	        0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40, 0xaf46, 0xab60, 0x9570,
	        0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58, 0x5ac0, 0xab60, 0x96d5, 0x92e0,
	        0xc960, 0xd954, 0xd4a0, 0xda50, 0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5,
	        0xa950, 0xb4a0, 0xbaa4, 0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930,
	        0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6, 0xa4e0, 0xd260, 0xea65, 0xd530,
	        0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0, 0xd0b6, 0xd25f, 0xd520, 0xdd45,
	        0xb5a0, 0x56d0, 0x55b2, 0x49b0, 0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0,
	        0x4b63, 0x937f, 0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef,
	        0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0, 0xda50, 0x5d55, 0x56a0, 0xa6d0, 0x55d4,
	        0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50, 0x55a0, 0xaba4, 0xa5b0, 0x52b0,
	        0xb273, 0x6930, 0x7337, 0x6aa0, 0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260,
	        0xe968, 0xd520, 0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252,
	        0xd520);
	
	var solarMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	var Gan = new Array("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸");
	var Zhi = new Array("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥");
	var Animals = new Array("鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪");
	var solarTerm = new Array("小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至")
	var sTermInfo = new Array(0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758)
	var nStr1 = new Array('日', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十')
	var nStr2 = new Array('初', '十', '廿', '卅', ' ')
	
	var jcName0 = new Array('建', '除', '满', '平', '定', '执', '破', '危', '成', '收', '开', '闭');
	var jcName1 = new Array('闭', '建', '除', '满', '平', '定', '执', '破', '危', '成', '收', '开');
	var jcName2 = new Array('开', '闭', '建', '除', '满', '平', '定', '执', '破', '危', '成', '收');
	var jcName3 = new Array('收', '开', '闭', '建', '除', '满', '平', '定', '执', '破', '危', '成');
	var jcName4 = new Array('成', '收', '开', '闭', '建', '除', '满', '平', '定', '执', '破', '危');
	var jcName5 = new Array('危', '成', '收', '开', '闭', '建', '除', '满', '平', '定', '执', '破');
	var jcName6 = new Array('破', '危', '成', '收', '开', '闭', '建', '除', '满', '平', '定', '执');
	var jcName7 = new Array('执', '破', '危', '成', '收', '开', '闭', '建', '除', '满', '平', '定');
	var jcName8 = new Array('定', '执', '破', '危', '成', '收', '开', '闭', '建', '除', '满', '平');
	var jcName9 = new Array('平', '定', '执', '破', '危', '成', '收', '开', '闭', '建', '除', '满');
	var jcName10 = new Array('满', '平', '定', '执', '破', '危', '成', '收', '开', '闭', '建', '除');
	var jcName11 = new Array('除', '满', '平', '定', '执', '破', '危', '成', '收', '开', '闭', '建');
	
	function jcr(d) {
	    var jcrjx;
	    if (d == '建') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>出行.上任.会友.上书.见工</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>动土.开仓.嫁娶.纳采</div>';
	    if (d == '除') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>除服.疗病.出行.拆卸.入宅</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>求官.上任.开张.搬家.探病</div>';
	    if (d == '满') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>祈福.祭祀.结亲.开市.交易</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>服药.求医.栽种.动土.迁移</div>';
	    if (d == '平') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>祭祀.修填.涂泥.余事勿取</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>移徙.入宅.嫁娶.开市.安葬</div>';
	    if (d == '定') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>易.立券.会友.签约.纳畜</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>种植.置业.卖田.掘井.造船</div>';
	    if (d == '执') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>祈福.祭祀.求子.结婚.立约</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>开市.交易.搬家.远行</div>';
	    if (d == '破') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>求医.赴考.祭祀.余事勿取</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>动土.出行.移徙.开市.修造</div>';
	    if (d == '危') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>经营.交易.求官.纳畜.动土</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>登高.行船.安床.入宅.博彩</div>';
	    if (d == '成') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>祈福.入学.开市.求医.成服</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>词讼.安门.移徙</div>';
	    if (d == '收') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>祭祀.求财.签约.嫁娶.订盟</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>开市.安床.安葬.入宅.破土</div>';
	    if (d == '开') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>疗病.结婚.交易.入仓.求职</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>安葬.动土.针灸</div>';
	    if (d == '闭') jcrjx = '<div class="jcrjx_yi"><img src="/resource/newWorkBench/images/yi.gif"/>祭祀.交易.收财.安葬</div><div class="jcrjx_ji"><img src="/resource/newWorkBench/images/ji.gif"/>宴会.安床.出行.嫁娶.移徙</div>';
	    return(jcrjx);
	}
	
	//国历节日  *表示放假日
	var sFtv = new Array(
	        "0101*元旦",
	        "0106  中国13亿人口日",
	        "0110  中国110宣传日",
	        "0202  世界湿地日",
	        "0204  世界抗癌症日",
	        "0210  世界气象日",
	        "0214  情人节",
	        "0221  国际母语日",
	        "0207  国际声援南非日",
	        "0303  全国爱耳日",
	        "0308  妇女节",
	        "0312  植树节 孙中山逝世纪念日",
	        "0315  消费者权益保护日",
	        "0321  世界森林日",
	        "0322  世界水日",
	        "0323  世界气象日",
	        "0324  世界防治结核病日",
	        "0401  愚人节",
	        "0407  世界卫生日",
	        "0422  世界地球日",
	        "0501*国际劳动节",
	        "0504  中国青年节",
	        "0505  全国碘缺乏病日",
	        "0508  世界红十字日",
	        "0512  国际护士节",
	        "0515  国际家庭日",
	        "0517  世界电信日",
	        "0518  国际博物馆日",
	        "0519  中国汶川地震哀悼日 全国助残日",
	        "0520  全国学生营养日",
	        "0522  国际生物多样性日",
	        "0523  国际牛奶日",
	        "0531  世界无烟日",
	        "0601  国际儿童节",
	        "0605  世界环境日",
	        "0606  全国爱眼日",
	        "0617  防治荒漠化和干旱日",
	        "0623  国际奥林匹克日",
	        "0625  全国土地日",
	        "0626  国际反毒品日",
	        "0701  建党节 香港回归纪念日",
	        "0707  抗日战争纪念日",
	        "0711  世界人口日",
	        "0801  八一建军节",
	        "0815  日本正式宣布无条件投降日",
	        "0908  国际扫盲日",
	        "0909  毛泽东逝世纪念日",
	        "0910  教师节",
	        "0916  国际臭氧层保护日",
	        "0917  国际和平日",
	        "0918  九·一八事变纪念日",
	        "0920  国际爱牙日",
	        "0927  世界旅游日",
	        "0928  孔子诞辰",
	        "1001*国庆节 国际音乐节 国际老人节",
	        "1002  国际减轻自然灾害日",
	        "1004  世界动物日",
	        "1007  国际住房日",
	        "1008  世界视觉日 全国高血压日",
	        "1009  世界邮政日",
	        "1010  辛亥革命纪念日 世界精神卫生日",
	        "1015  国际盲人节",
	        "1016  世界粮食节",
	        "1017  世界消除贫困日",
	        "1022  世界传统医药日",
	        "1024  联合国日",
	        "1025  人类天花绝迹日",
	        "1026  足球诞生日",
	        "1031  万圣节",
	        "1107  十月社会主义革命纪念日",
	        "1108  中国记者日",
	        "1109  消防宣传日",
	        "1110  世界青年节",
	        "1112  孙中山诞辰",
	        "1114  世界糖尿病日",
	        "1117  国际大学生节",
	        "1201  世界艾滋病日",
	        "1203  世界残疾人日",
	        "1209  世界足球日",
	        "1210  世界人权日",
	        "1212  西安事变纪念日",
	        "1213  南京大屠杀",
	        "1220  澳门回归纪念日",
	        "1221  国际篮球日",
	        "1224  平安夜",
	        "1225  圣诞节 世界强化免疫日",
	        "1226  毛泽东诞辰")
	//农历节日  *表示放假日
	var lFtv = new Array(
	        "0101*春节",
	        "0102*大年初二",
	        "0103*大年初三",
	        "0105  路神生日",
	        "0115  元宵节",
	        "0202  龙抬头",
	        "0219  观世音圣诞",
	        "0404  寒食节",
	        "0408  佛诞节 ",
	        "0505*端午节",
	        "0606  天贶节 姑姑节",
	        "0624  彝族火把节",
	        "0707  七夕情人节",
	        "0714  鬼节(南方)",
	        "0715  盂兰节",
	        "0730  地藏节",
	        "0815*中秋节",
	        "0909  重阳节",
	        "1001  祭祖节",
	        "1117  阿弥陀佛圣诞",
	        "1208  腊八节 释迦如来成道日",
	        "1223  过小年",
	        "0100*除夕");
	//某月的第几个星期几; 5,6,7,8 表示到数第 1,2,3,4 个星期几
	var wFtv = new Array(
	        "0110  黑人节",
	        "0150  世界麻风日",
	        "0121  日本成人节",
	        "0520  母亲节",
	        "0530  全国助残日",
	        "0630  父亲节",
	        "0716  合作节",
	        "0730  被奴役国家周",
	        "0932  国际和平日",
	        "0940  国际聋人节 世界儿童日",
	        "1011  国际住房日",
	        "1144  感恩节")
	
	/*****************************************************************************
	 日期计算
	 *****************************************************************************/
	
	//====================================== 返回农历 y年的总天数
	function lYearDays(y) {
	    var i, sum = 348;
	    for (i = 0x8000; i > 0x8; i >>= 1) sum += (lunarInfo[y - 1900] & i) ? 1 : 0;
	    return(sum + leapDays(y));
	}
	
	//====================================== 返回农历 y年闰月的天数
	function leapDays(y) {
	    if (leapMonth(y)) return( (lunarInfo[y - 1899] & 0xf) == 0xf ? 30 : 29);
	    else return(0);
	}
	
	//====================================== 返回农历 y年闰哪个月 1-12 , 没闰返回 0
	function leapMonth(y) {
	    var lm = lunarInfo[y - 1900] & 0xf;
	    return(lm == 0xf ? 0 : lm);
	}
	
	//====================================== 返回农历 y年m月的总天数
	function monthDays(y, m) {
	    return( (lunarInfo[y - 1900] & (0x10000 >> m)) ? 30 : 29 );
	}
	
	//====================================== 算出农历, 传入日期控件, 返回农历日期控件
	//                                       该控件属性有 .year .month .day .isLeap
	function Lunar(objDate) {
	
	    var i, leap = 0, temp = 0;
	    var offset = (Date.UTC(objDate.getFullYear(), objDate.getMonth(), objDate.getDate()) - Date.UTC(1900, 0, 31)) / 86400000;
	
	    for (i = 1900; i < 2100 && offset > 0; i++) {
	        temp = lYearDays(i);
	        offset -= temp;
	    }
	
	    if (offset < 0) {
	        offset += temp;
	        i--;
	    }
	
	    this.year = i;
	
	    leap = leapMonth(i); //闰哪个月
	    this.isLeap = false;
	
	    for (i = 1; i < 13 && offset > 0; i++) {
	        //闰月
	        if (leap > 0 && i == (leap + 1) && this.isLeap == false) {
	            --i;
	            this.isLeap = true;
	            temp = leapDays(this.year);
	        }
	        else {
	            temp = monthDays(this.year, i);
	        }
	
	        //解除闰月
	        if (this.isLeap == true && i == (leap + 1)) this.isLeap = false;
	
	        offset -= temp;
	    }
	
	    if (offset == 0 && leap > 0 && i == leap + 1)
	        if (this.isLeap) {
	            this.isLeap = false;
	        }
	        else {
	            this.isLeap = true;
	            --i;
	        }
	
	    if (offset < 0) {
	        offset += temp;
	        --i;
	    }
	
	    this.month = i;
	    this.day = offset + 1;
	}
	
	//==============================返回公历 y年某m+1月的天数
	function solarDays(y, m) {
	    if (m == 1)
	        return(((y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0)) ? 29 : 28);
	    else
	        return(solarMonth[m]);
	}
	//============================== 传入 offset 返回干支, 0=甲子
	function cyclical(num) {
	    return(Gan[num % 10] + Zhi[num % 12]);
	}
	
	//============================== 阴历属性
	function calElement(sYear, sMonth, sDay, week, lYear, lMonth, lDay, isLeap, cYear, cMonth, cDay) {
	
	    this.isToday = false;
	    //瓣句
	    this.sYear = sYear;   //公元年4位数字
	    this.sMonth = sMonth;  //公元月数字
	    this.sDay = sDay;    //公元日数字
	    this.week = week;    //星期, 1个中文
	    //农历
	    this.lYear = lYear;   //公元年4位数字
	    this.lMonth = lMonth;  //农历月数字
	    this.lDay = lDay;    //农历日数字
	    this.isLeap = isLeap;  //是否为农历闰月?
	    //八字
	    this.cYear = cYear;   //年柱, 2个中文
	    this.cMonth = cMonth;  //月柱, 2个中文
	    this.cDay = cDay;    //日柱, 2个中文
	
	    this.color = '';
	
	    this.lunarFestival = ''; //农历节日
	    this.solarFestival = ''; //公历节日
	    this.solarTerms = ''; //节气
	    this.sx=Animals[(lYear - 4) % 12];//生肖
	    this.lunarMonth=FormatLunarMonth(lMonth);
	    this.lunarDay=FormatLunarDay(lDay);
	}
	
	//===== 某年的第n个节气为几日(从0小寒起算)
	function sTerm(y, n) {
	    var offDate = new Date(( 31556925974.7 * (y - 1900) + sTermInfo[n] * 60000  ) + Date.UTC(1900, 0, 6, 2, 5));
	    return(offDate.getUTCDate());
	}
	
	//============================== 返回阴历 (y年,m+1月)
	function cyclical6(num, num2) {
	    if (num == 0) return(jcName0[num2]);
	    if (num == 1) return(jcName1[num2]);
	    if (num == 2) return(jcName2[num2]);
	    if (num == 3) return(jcName3[num2]);
	    if (num == 4) return(jcName4[num2]);
	    if (num == 5) return(jcName5[num2]);
	    if (num == 6) return(jcName6[num2]);
	    if (num == 7) return(jcName7[num2]);
	    if (num == 8) return(jcName8[num2]);
	    if (num == 9) return(jcName9[num2]);
	    if (num == 10) return(jcName10[num2]);
	    if (num == 11) return(jcName11[num2]);
	}
	function CalConv2(yy, mm, dd, y, d, m, dt, nm, nd) {
	    var dy = d + '' + dd
	    if ((yy == 0 && dd == 6) || (yy == 6 && dd == 0) || (yy == 1 && dd == 7) || (yy == 7 && dd == 1) || (yy == 2 && dd == 8) || (yy == 8 && dd == 2) || (yy == 3 && dd == 9) || (yy == 9 && dd == 3) || (yy == 4 && dd == 10) || (yy == 10 && dd == 4) || (yy == 5 && dd == 11) || (yy == 11 && dd == 5)) {
	        return '<FONT color=#0000A0>日值岁破 大事不宜</font>';
	    }
	    else if ((mm == 0 && dd == 6) || (mm == 6 && dd == 0) || (mm == 1 && dd == 7) || (mm == 7 && dd == 1) || (mm == 2 && dd == 8) || (mm == 8 && dd == 2) || (mm == 3 && dd == 9) || (mm == 9 && dd == 3) || (mm == 4 && dd == 10) || (mm == 10 && dd == 4) || (mm == 5 && dd == 11) || (mm == 11 && dd == 5)) {
	        return '<FONT color=#0000A0>日值月破 大事不宜</font>';
	    }
	    else if ((y == 0 && dy == '911') || (y == 1 && dy == '55') || (y == 2 && dy == '111') || (y == 3 && dy == '75') || (y == 4 && dy == '311') || (y == 5 && dy == '95') || (y == 6 && dy == '511') || (y == 7 && dy == '15') || (y == 8 && dy == '711') || (y == 9 && dy == '35')) {
	        return '<FONT color=#0000A0>日值上朔 大事不宜</font>';
	    }
	    else if ((m == 1 && dt == 13) || (m == 2 && dt == 11) || (m == 3 && dt == 9) || (m == 4 && dt == 7) || (m == 5 && dt == 5) || (m == 6 && dt == 3) || (m == 7 && dt == 1) || (m == 7 && dt == 29) || (m == 8 && dt == 27) || (m == 9 && dt == 25) || (m == 10 && dt == 23) || (m == 11 && dt == 21) || (m == 12 && dt == 19)) {
	        return '<FONT color=#0000A0>日值杨公十三忌 大事不宜</font>';
	    }
	    else {
	        return 0;
	    }
	}
	function easter(y) {
	
	    var term2 = sTerm(y, 5); //取得春分日期
	    var dayTerm2 = new Date(Date.UTC(y, 2, term2, 0, 0, 0, 0)); //取得春分的公历日期控件(春分一定出现在3月)
	    var lDayTerm2 = new Lunar(dayTerm2); //取得取得春分农历
	
	    if (lDayTerm2.day < 15) //取得下个月圆的相差天数
	        var lMlen = 15 - lDayTerm2.day;
	    else
	        var lMlen = (lDayTerm2.isLeap ? leapDays(y) : monthDays(y, lDayTerm2.month)) - lDayTerm2.day + 15;
	
	    //一天等于 1000*60*60*24 = 86400000 毫秒
	    var l15 = new Date(dayTerm2.getTime() + 86400000 * lMlen); //求出第一次月圆为公历几日
	    var dayEaster = new Date(l15.getTime() + 86400000 * ( 7 - l15.getUTCDay() )); //求出下个周日
	
	    this.m = dayEaster.getUTCMonth();
	    this.d = dayEaster.getUTCDate();
	
	}
	//======================  中文日期
	function cDay(d) {
	    var s;
	
	    switch (d) {
	        case  10:
	            s = '初十';  break;
	        case  20:
	            s = '二十';  break;
	            break;
	        case  30:
	            s = '三十';  break;
	            break;
	        default  :
	            s = nStr2[Math.floor(d / 10)];
	            s += nStr1[d % 10];
	    }
	    return(s);
	}
	// 将农历iLunarMonth月格式化成农历表示的字符串
function FormatLunarMonth(iLunarMonth) {
    var szText = new String("正二三四五六七八九十");
    var strMonth;
    if (iLunarMonth <= 10) {
        strMonth = szText.substr(iLunarMonth - 1, 1);
    }
    else if (iLunarMonth == 11) strMonth = "十一";
    else strMonth = "十二";
    return strMonth + "月";
}
// 将农历iLunarDay日格式化成农历表示的字符串
function FormatLunarDay(iLunarDay) {
    var szText1 = new String("初十廿三");
    var szText2 = new String("一二三四五六七八九十");
    var strDay;
    if ((iLunarDay != 20) && (iLunarDay != 30)) {
        strDay = szText1.substr((iLunarDay - 1) / 10, 1) + szText2.substr((iLunarDay - 1) % 10, 1);
    }
    else if (iLunarDay != 20) {
        strDay = szText1.substr(iLunarDay / 10, 1) + "十";
    }
    else {
        strDay = "二十";
    }
    return strDay;
}
    function calendar(y, m) {
	    var sDObj, lDObj, lY, lM, lD = 1, lL, lX = 0, tmp1, tmp2, lM2,lY2,lD2,tmp3,dayglus,bsg,xs,xs1,fs,fs1,cs,cs1
	    var cY, cM, cD; //年柱,月柱,日柱
	    var lDPOS = new Array(3);
	    var n = 0;
	    var firstLM = 0;
	
	    sDObj = new Date(y, m, 1, 0, 0, 0, 0);    //当月一日日期
	
	    this.length = solarDays(y, m);    //公历当月天数
	    this.firstWeek = sDObj.getDay();    //公历当月1日星期几
	
	    ////////年柱 1900年立春后为庚子年(60进制36)
	    if (m < 2) cY = cyclical(y - 1900 + 36 - 1);
	    else cY = cyclical(y - 1900 + 36);
	    var term2 = sTerm(y, 2); //立春日期
	
	    ////////月柱 1900年1月小寒以前为 丙子月(60进制12)
	    var firstNode = sTerm(y, m * 2) //返回当月「节」为几日开始
	    cM = cyclical((y - 1900) * 12 + m + 12);
	
	    lM2 = (y - 1900) * 12 + m + 12;
	    //当月一日与 1900/1/1 相差天数
	    //1900/1/1与 1970/1/1 相差25567日, 1900/1/1 日柱为甲戌日(60进制10)
	    var dayCyclical = Date.UTC(y, m, 1, 0, 0, 0, 0) / 86400000 + 25567 + 10;
	
	    for (var i = 0; i < this.length; i++) {
	        if (lD > lX) {
	            sDObj = new Date(y, m, i + 1);    //当月一日日期
	            lDObj = new Lunar(sDObj);     //农历
	            lY = lDObj.year;           //农历年
	            lM = lDObj.month;          //农历月
	            lD = lDObj.day;            //农历日
	            lL = lDObj.isLeap;         //农历是否闰月
	            lX = lL ? leapDays(lY) : monthDays(lY, lM); //农历当月最后一天
	
	            if (n == 0) firstLM = lM;
	            lDPOS[n++] = i - lD + 1;
	        }
	
	        //依节气调整二月分的年柱, 以立春为界
	        if (m == 1 && (i + 1) == term2) {
	            cY = Animals[(y - 4) % 12];
	            lY2 = (y - 1900 + 36);
	        }
	        //依节气月柱, 以「节」为界
	        if ((i + 1) == firstNode) {
	            cM = cyclical((y - 1900) * 12 + m + 13);
	            lM2 = (y - 1900) * 12 + m + 13;
	        }
	        //日柱
	        cD = cyclical(dayCyclical + i);
	        lD2 = (dayCyclical + i);
	
	        this[i] = new calElement(y, m + 1, i + 1, nStr1[(i + this.firstWeek) % 7],
	                lY, lM, lD++, lL,
	                cY, cM, cD);
	
	
	        this[i].sgz5 = CalConv2(lY2 % 12, lM2 % 12, (lD2) % 12, lY2 % 10, (lD2) % 10, lM, lD - 1, m + 1, cs1);
	        this[i].sgz3 = cyclical6(lM2 % 12, (lD2) % 12);
			
	        if (this[i].sgz5 != 0) {
	            this[i].dom = this[i].sgz5;
	        } else {
	            this[i].dom = jcr(this[i].sgz3);
	
	        }
	    }
	
	    //节气
	    tmp1 = sTerm(y, m * 2) - 1;
	    tmp2 = sTerm(y, m * 2 + 1) - 1;
		this[tmp1].solarTerms = solarTerm[m * 2];
		this[tmp2].solarTerms = solarTerm[m * 2 + 1];
	    if (m == 3) this[tmp1].color = 'red'; //清明颜色
	
	    //国历节日
	    for (var i=0;i<sFtv.length;i++)
	        if (sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
	            if (Number(RegExp.$1) == (m + 1)) {
	                this[Number(RegExp.$2) - 1].solarFestival += RegExp.$4 + '  '
	                if (RegExp.$3 == '*')  this[Number(RegExp.$2) - 1].color = 'red'
	            }
	
	
	    //农历节日
	    for (var i=0;i<lFtv.length;i++)
	        if (lFtv[i].match(/^(\d{2})(.{2})([\s\*])(.+)$/)) {
	            tmp1 = Number(RegExp.$1) - firstLM
	            if (tmp1 == -11)  tmp1 = 1
	            if (tmp1 >= 0 && tmp1 < n) {
	                tmp2 = lDPOS[tmp1] + Number(RegExp.$2) - 1
	                if (tmp2 >= 0 && tmp2 < this.length) {
	                    this[tmp2].lunarFestival += RegExp.$4 + '  '
	                    if (RegExp.$3 == '*')  this[tmp2].color = 'red'
	                }
	            }
	        }
	
	    //复活节只出现在3或4月
	    if (m == 2 || m == 3) {
	        var estDay = new easter(y);
	        if (m == estDay.m)
	            this[estDay.d - 1].solarFestival = this[estDay.d - 1].solarFestival + ' 复活节(Easter Sunday)';
	    }

	    //黑色星期五
	    if ((this.firstWeek + 12) % 7 == 5)
	        this[12].solarFestival += '黑色星期五';
	
	    //今日
	    if (y == tY && m == tM) this[tD - 1].isToday = true;
	}
	var cld = new calendar(parseInt(date.year),parseInt(date.month,10)-1,parseInt(date.day));
	return cld[parseInt(date.day,10)-1];
}

/*
 * jQuery MultiSelect UI Widget 1.13
 * Copyright (c) 2012 Eric Hynds
 *
 * http://www.erichynds.com/jquery/jquery-ui-multiselect-widget/
 *
 * Depends:
 *   - jQuery 1.4.2+
 *   - jQuery UI 1.8 widget factory
 *
 * Optional:
 *   - jQuery UI effects
 *   - jQuery UI position utility
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 */
;(function(d){var k=0;d.widget("ech.multiselect",{options:{header:!0,height:175,minWidth:225,classes:"",checkAllText:"Check all",uncheckAllText:"Uncheck all",noneSelectedText:"Select options",selectedText:"# selected",selectedList:0,show:null,hide:null,autoOpen:!1,multiple:!0,position:{}},_create:function(){var a=this.element.hide(),b=this.options;this.speed=d.fx.speeds._default;this._isOpen=!1;a=(this.button=d('<button type="button"><span class="ui-icon ui-icon-triangle-2-n-s"></span></button>')).addClass("ui-multiselect ui-widget ui-state-default ui-corner-all").addClass(b.classes).attr({title:a.attr("title"),"aria-haspopup":!0,tabIndex:a.attr("tabIndex")}).insertAfter(a);(this.buttonlabel=d("<span />")).html(b.noneSelectedText).appendTo(a);var a=(this.menu=d("<div />")).addClass("ui-multiselect-menu ui-widget ui-widget-content ui-corner-all").addClass(b.classes).appendTo(document.body),c=(this.header=d("<div />")).addClass("ui-widget-header ui-corner-all ui-multiselect-header ui-helper-clearfix").appendTo(a);(this.headerLinkContainer=d("<ul />")).addClass("ui-helper-reset").html(function(){return!0===b.header?'<li><a class="ui-multiselect-all" href="#"><span class="ui-icon ui-icon-check"></span><span>'+b.checkAllText+'</span></a></li><li><a class="ui-multiselect-none" href="#"><span class="ui-icon ui-icon-closethick"></span><span>'+b.uncheckAllText+"</span></a></li>":"string"===typeof b.header?"<li>"+b.header+"</li>":""}).append('<li class="ui-multiselect-close"><a href="#" class="ui-multiselect-close"><span class="ui-icon ui-icon-circle-close"></span></a></li>').appendTo(c);(this.checkboxContainer=d("<ul />")).addClass("ui-multiselect-checkboxes ui-helper-reset").appendTo(a);this._bindEvents();this.refresh(!0);b.multiple||a.addClass("ui-multiselect-single")},_init:function(){!1===this.options.header&&this.header.hide();this.options.multiple||this.headerLinkContainer.find(".ui-multiselect-all, .ui-multiselect-none").hide();this.options.autoOpen&&this.open();this.element.is(":disabled")&&this.disable()},refresh:function(a){var b=this.element,c=this.options,f=this.menu,h=this.checkboxContainer,g=[],e="",i=b.attr("id")||k++;b.find("option").each(function(b){d(this);var a=this.parentNode,f=this.innerHTML,h=this.title,k=this.value,b="ui-multiselect-"+(this.id||i+"-option-"+b),l=this.disabled,n=this.selected,m=["ui-corner-all"],o=(l?"ui-multiselect-disabled ":" ")+this.className,j;"OPTGROUP"===a.tagName&&(j=a.getAttribute("label"),-1===d.inArray(j,g)&&(e+='<li class="ui-multiselect-optgroup-label '+a.className+'"><a href="#">'+j+"</a></li>",g.push(j)));l&&m.push("ui-state-disabled");n&&!c.multiple&&m.push("ui-state-active");e+='<li class="'+o+'">';e+='<label for="'+b+'" title="'+h+'" class="'+m.join(" ")+'">';e+='<input id="'+b+'" name="multiselect_'+i+'" type="'+(c.multiple?"checkbox":"radio")+'" value="'+k+'" title="'+f+'"';n&&(e+=' checked="checked"',e+=' aria-selected="true"');l&&(e+=' disabled="disabled"',e+=' aria-disabled="true"');e+=" /><span>"+f+"</span></label></li>"});h.html(e);this.labels=f.find("label");this.inputs=this.labels.children("input");this._setButtonWidth();this._setMenuWidth();this.button[0].defaultValue=this.update();a||this._trigger("refresh")},update:function(){var a=this.options,b=this.inputs,c=b.filter(":checked"),f=c.length,a=0===f?a.noneSelectedText:d.isFunction(a.selectedText)?a.selectedText.call(this,f,b.length,c.get()):/\d/.test(a.selectedList)&&0<a.selectedList&&f<=a.selectedList?c.map(function(){return d(this).next().html()}).get().join(", "):a.selectedText.replace("#",f).replace("#",b.length);this.buttonlabel.html(a);return a},_bindEvents:function(){function a(){b[b._isOpen? "close":"open"]();return!1}var b=this,c=this.button;c.find("span").bind("click.multiselect",a);c.bind({click:a,keypress:function(a){switch(a.which){case 27:case 38:case 37:b.close();break;case 39:case 40:b.open()}},mouseenter:function(){c.hasClass("ui-state-disabled")||d(this).addClass("ui-state-hover")},mouseleave:function(){d(this).removeClass("ui-state-hover")},focus:function(){c.hasClass("ui-state-disabled")||d(this).addClass("ui-state-focus")},blur:function(){d(this).removeClass("ui-state-focus")}});this.header.delegate("a","click.multiselect",function(a){if(d(this).hasClass("ui-multiselect-close"))b.close();else b[d(this).hasClass("ui-multiselect-all")?"checkAll":"uncheckAll"]();a.preventDefault()});this.menu.delegate("li.ui-multiselect-optgroup-label a","click.multiselect",function(a){a.preventDefault();var c=d(this),g=c.parent().nextUntil("li.ui-multiselect-optgroup-label").find("input:visible:not(:disabled)"),e=g.get(),c=c.parent().text();!1!==b._trigger("beforeoptgrouptoggle",a,{inputs:e,label:c})&&(b._toggleChecked(g.filter(":checked").length!==g.length,g),b._trigger("optgrouptoggle",a,{inputs:e,label:c,checked:e[0].checked}))}).delegate("label","mouseenter.multiselect",function(){d(this).hasClass("ui-state-disabled")||(b.labels.removeClass("ui-state-hover"),d(this).addClass("ui-state-hover").find("input").focus())}).delegate("label","keydown.multiselect",function(a){a.preventDefault();switch(a.which){case 9:case 27:b.close();break;case 38:case 40:case 37:case 39:b._traverse(a.which,this);break;case 13:d(this).find("input")[0].click()}}).delegate('input[type="checkbox"], input[type="radio"]',"click.multiselect",function(a){var c=d(this),g=this.value,e=this.checked,i=b.element.find("option");this.disabled||!1===b._trigger("click",a,{value:g,text:this.title,checked:e})?a.preventDefault():(c.focus(),c.attr("aria-selected",e),i.each(function(){this.value===g?this.selected=e:b.options.multiple||(this.selected=!1)}),b.options.multiple||(b.labels.removeClass("ui-state-active"),c.closest("label").toggleClass("ui-state-active",e),b.close()),b.element.trigger("change"),setTimeout(d.proxy(b.update,b),10))});d(document).bind("mousedown.multiselect",function(a){b._isOpen&&(!d.contains(b.menu[0],a.target)&&!d.contains(b.button[0],a.target)&&a.target!==b.button[0])&&b.close()});d(this.element[0].form).bind("reset.multiselect",function(){setTimeout(d.proxy(b.refresh,b),10)})},_setButtonWidth:function(){var a=this.element.outerWidth(),b=this.options;/\d/.test(b.minWidth)&&a<b.minWidth&&(a=b.minWidth);this.button.width(a)},_setMenuWidth:function(){var a=this.menu,b=this.button.outerWidth()-parseInt(a.css("padding-left"),10)-parseInt(a.css("padding-right"),10)-parseInt(a.css("border-right-width"),10)-parseInt(a.css("border-left-width"),10);a.width(b||this.button.outerWidth())},_traverse:function(a,b){var c=d(b),f=38===a||37===a,c=c.parent()[f?"prevAll":"nextAll"]("li:not(.ui-multiselect-disabled, .ui-multiselect-optgroup-label)")[f?"last":"first"]();c.length?c.find("label").trigger("mouseover"):(c=this.menu.find("ul").last(),this.menu.find("label")[f? "last":"first"]().trigger("mouseover"),c.scrollTop(f?c.height():0))},_toggleState:function(a,b){return function(){this.disabled||(this[a]=b);b?this.setAttribute("aria-selected",!0):this.removeAttribute("aria-selected")}},_toggleChecked:function(a,b){var c=b&&b.length?b:this.inputs,f=this;c.each(this._toggleState("checked",a));c.eq(0).focus();this.update();var h=c.map(function(){return this.value}).get();this.element.find("option").each(function(){!this.disabled&&-1<d.inArray(this.value,h)&&f._toggleState("selected",a).call(this)});c.length&&this.element.trigger("change")},_toggleDisabled:function(a){this.button.attr({disabled:a,"aria-disabled":a})[a?"addClass":"removeClass"]("ui-state-disabled");var b=this.menu.find("input"),b=a?b.filter(":enabled").data("ech-multiselect-disabled",!0):b.filter(function(){return!0===d.data(this,"ech-multiselect-disabled")}).removeData("ech-multiselect-disabled");b.attr({disabled:a,"arial-disabled":a}).parent()[a?"addClass":"removeClass"]("ui-state-disabled");this.element.attr({disabled:a,"aria-disabled":a})},open:function(){var a=this.button,b=this.menu,c=this.speed,f=this.options,h=[];if(!(!1===this._trigger("beforeopen")||a.hasClass("ui-state-disabled")||this._isOpen)){var g=b.find("ul").last(),e=f.show,i=a.offset();d.isArray(f.show)&&(e=f.show[0],c=f.show[1]||this.speed);e&&(h=[e,c]);g.scrollTop(0).height(f.height);d.ui.position&&!d.isEmptyObject(f.position)?(f.position.of=f.position.of||a,b.show().position(f.position).hide()):b.css({top:i.top+a.outerHeight(),left:i.left});d.fn.show.apply(b,h);this.labels.eq(0).trigger("mouseover").trigger("mouseenter").find("input").trigger("focus");a.addClass("ui-state-active");this._isOpen=!0;this._trigger("open")}},close:function(){if(!1!==this._trigger("beforeclose")){var a=this.options,b=a.hide,c=this.speed,f=[];d.isArray(a.hide)&&(b=a.hide[0],c=a.hide[1]||this.speed);b&&(f=[b,c]);d.fn.hide.apply(this.menu,f);this.button.removeClass("ui-state-active").trigger("blur").trigger("mouseleave");this._isOpen=!1;this._trigger("close")}},enable:function(){this._toggleDisabled(!1)},disable:function(){this._toggleDisabled(!0)},checkAll:function(){this._toggleChecked(!0);this._trigger("checkAll")},uncheckAll:function(){this._toggleChecked(!1);this._trigger("uncheckAll")},getChecked:function(){return this.menu.find("input").filter(":checked")},destroy:function(){d.Widget.prototype.destroy.call(this);this.button.remove();this.menu.remove();this.element.show();return this},isOpen:function(){return this._isOpen},widget:function(){return this.menu},getButton:function(){return this.button},_setOption:function(a,b){var c=this.menu;switch(a){case "header":c.find("div.ui-multiselect-header")[b?"show":"hide"]();break;case "checkAllText":c.find("a.ui-multiselect-all span").eq(-1).text(b);break;case "uncheckAllText":c.find("a.ui-multiselect-none span").eq(-1).text(b);break;case "height":c.find("ul").last().height(parseInt(b,10));break;case "minWidth":this.options[a]=parseInt(b,10);this._setButtonWidth();this._setMenuWidth();break;case "selectedText":case "selectedList":case "noneSelectedText":this.options[a]=b;this.update();break;case "classes":c.add(this.button).removeClass(this.options.classes).addClass(b);break;case "multiple":c.toggleClass("ui-multiselect-single",!b),this.options.multiple=b,this.element[0].multiple=b,this.refresh()}d.Widget.prototype._setOption.apply(this,arguments)}})})(jQuery);

/**
 * 学校名称自动填充
 */
;(function($) {
	$.fn.extend( {
		schoolAutoComplete:function(option){
			var defaultPostData ={
				rows : 10
			};
			
			$.extend(defaultPostData,option.postData);
			
			var defaultOption={
				delay : 500,
				minLength : 0,
				source : function(request, response) {
					$.ajax({
						url : PATH + "/baseinfo/idleYouthManage/autoCompleteSchoolName.action",
						data : defaultPostData, 
						success : function(_data) {
							response($.map(_data, function(rowData) {
								return {
									label : rowData,
									value : rowData
								};
							}));
						},
						error : function() {
							$.messageBox({message:"数据提交出错！"});
						}
					});
				},
				open:function(){
					$(".ui-autocomplete").css("z-index","100000");
				}
			};
			$.extend(defaultOption,option);
			$(this).autocomplete(defaultOption);
		}
	});
})(jQuery)
/*
 * JavaScript Load Image 1.7
 * https://github.com/blueimp/JavaScript-Load-Image
 *
 * Copyright 2011, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

/*jslint nomen: true */
/*global define, window, document, URL, webkitURL, Blob, File, FileReader */

;(function ($) {
    'use strict';

    // Loads an image for a given File object.
    // Invokes the callback with an img or optional canvas
    // element (if supported by the browser) as parameter:
    var loadImage = function (file, callback, options) {
            var img = document.createElement('img'),
                url,
                oUrl;
            img.onerror = callback;
            img.onload = function () {
                if (oUrl && !(options && options.noRevoke)) {
                    loadImage.revokeObjectURL(oUrl);
                }
                if (callback) {
                    callback(loadImage.scale(img, options));
                }
            };
            if (loadImage.isInstanceOf('Blob', file) ||
                    // Files are also Blob instances, but some browsers
                    // (Firefox 3.6) support the File API but not Blobs:
                    loadImage.isInstanceOf('File', file)) {
                url = oUrl = loadImage.createObjectURL(file);
                // Store the file type for resize processing:
                img._type = file.type;
            } else if (typeof file === 'string') {
                url = file;
                if (options && options.crossOrigin) {
                    img.crossOrigin = options.crossOrigin;
                }
            } else {
                return false;
            }
            if (url) {
                img.src = url;
                return img;
            }
            return loadImage.readFile(file, function (e) {
                var target = e.target;
                if (target && target.result) {
                    img.src = target.result;
                } else {
                    if (callback) {
                        callback(e);
                    }
                }
            });
        },
        // The check for URL.revokeObjectURL fixes an issue with Opera 12,
        // which provides URL.createObjectURL but doesn't properly implement it:
        urlAPI = (window.createObjectURL && window) ||
            (window.URL && URL.revokeObjectURL && URL) ||
            (window.webkitURL && webkitURL);

    loadImage.isInstanceOf = function (type, obj) {
        // Cross-frame instanceof check
        return Object.prototype.toString.call(obj) === '[object ' + type + ']';
    };

    // Transform image orientation based on the given EXIF orientation data:
    loadImage.transformCoordinates = function (canvas, orientation) {
        var ctx = canvas.getContext('2d'),
            width = canvas.width,
            height = canvas.height;
        if (orientation > 4) {
            canvas.width = height;
            canvas.height = width;
        }
        switch (orientation) {
        case 2:
            // horizontal flip
            ctx.translate(width, 0);
            ctx.scale(-1, 1);
            break;
        case 3:
            // 180 rotate left
            ctx.translate(width, height);
            ctx.rotate(Math.PI);
            break;
        case 4:
            // vertical flip
            ctx.translate(0, height);
            ctx.scale(1, -1);
            break;
        case 5:
            // vertical flip + 90 rotate right
            ctx.rotate(0.5 * Math.PI);
            ctx.scale(1, -1);
            break;
        case 6:
            // 90 rotate right
            ctx.rotate(0.5 * Math.PI);
            ctx.translate(0, -height);
            break;
        case 7:
            // horizontal flip + 90 rotate right
            ctx.rotate(0.5 * Math.PI);
            ctx.translate(width, -height);
            ctx.scale(-1, 1);
            break;
        case 8:
            // 90 rotate left
            ctx.rotate(-0.5 * Math.PI);
            ctx.translate(-width, 0);
            break;
        }
    };

    // Canvas render method, allows to override the
    // rendering e.g. to work around issues on iOS:
    loadImage.renderImageToCanvas = function (
        canvas,
        img,
        sourceX,
        sourceY,
        sourceWidth,
        sourceHeight,
        destX,
        destY,
        destWidth,
        destHeight
    ) {
        canvas.getContext('2d').drawImage(
            img,
            sourceX,
            sourceY,
            sourceWidth,
            sourceHeight,
            destX,
            destY,
            destWidth,
            destHeight
        );
        return canvas;
    };

    // Scales the given image (img or canvas HTML element)
    // using the given options.
    // Returns a canvas object if the browser supports canvas
    // and the canvas or crop option is true or a canvas object
    // is passed as image, else the scaled image:
    loadImage.scale = function (img, options) {
        options = options || {};
        var canvas = document.createElement('canvas'),
            useCanvas = img.getContext ||
                ((options.canvas || options.crop || options.orientation) &&
                    canvas.getContext),
            width = img.width,
            height = img.height,
            sourceWidth = width,
            sourceHeight = height,
            sourceX = 0,
            sourceY = 0,
            destX = 0,
            destY = 0,
            maxWidth,
            maxHeight,
            minWidth,
            minHeight,
            destWidth,
            destHeight,
            scale;
        if (useCanvas && options.orientation > 4) {
            maxWidth = options.maxHeight;
            maxHeight = options.maxWidth;
            minWidth = options.minHeight;
            minHeight = options.minWidth;
        } else {
            maxWidth = options.maxWidth;
            maxHeight = options.maxHeight;
            minWidth = options.minWidth;
            minHeight = options.minHeight;
        }
        if (useCanvas && maxWidth && maxHeight && options.crop) {
            destWidth = maxWidth;
            destHeight = maxHeight;
            if (width / height < maxWidth / maxHeight) {
                sourceHeight = maxHeight * width / maxWidth;
                sourceY = (height - sourceHeight) / 2;
            } else {
                sourceWidth = maxWidth * height / maxHeight;
                sourceX = (width - sourceWidth) / 2;
            }
        } else {
            destWidth = width;
            destHeight = height;
            scale = Math.max(
                (minWidth || destWidth) / destWidth,
                (minHeight || destHeight) / destHeight
            );
            if (scale > 1) {
                destWidth = Math.ceil(destWidth * scale);
                destHeight = Math.ceil(destHeight * scale);
            }
            scale = Math.min(
                (maxWidth || destWidth) / destWidth,
                (maxHeight || destHeight) / destHeight
            );
            if (scale < 1) {
                destWidth = Math.ceil(destWidth * scale);
                destHeight = Math.ceil(destHeight * scale);
            }
        }
        if (useCanvas) {
            canvas.width = destWidth;
            canvas.height = destHeight;
            loadImage.transformCoordinates(
                canvas,
                options.orientation
            );
            return loadImage.renderImageToCanvas(
                canvas,
                img,
                sourceX,
                sourceY,
                sourceWidth,
                sourceHeight,
                destX,
                destY,
                destWidth,
                destHeight
            );
        }
        img.width = destWidth;
        img.height = destHeight;
        return img;
    };

    loadImage.createObjectURL = function (file) {
        return urlAPI ? urlAPI.createObjectURL(file) : false;
    };

    loadImage.revokeObjectURL = function (url) {
        return urlAPI ? urlAPI.revokeObjectURL(url) : false;
    };

    // Loads a given File object via FileReader interface,
    // invokes the callback with the event object (load or error).
    // The result can be read via event.target.result:
    loadImage.readFile = function (file, callback, method) {
        if (window.FileReader) {
            var fileReader = new FileReader();
            fileReader.onload = fileReader.onerror = callback;
            method = method || 'readAsDataURL';
            if (fileReader[method]) {
                fileReader[method](file);
                return fileReader;
            }
        }
        return false;
    };

    if (typeof define === 'function' && define.amd) {
        define(function () {
            return loadImage;
        });
    } else {
        $.loadImage = loadImage;
    }
}(this));

!function(e){"use strict";var i=function(e,t,n){var a,o,l=document.createElement("img");if(l.onerror=t,l.onload=function(){!o||n&&n.noRevoke||i.revokeObjectURL(o),t&&t(i.scale(l,n))},i.isInstanceOf("Blob",e)||i.isInstanceOf("File",e))a=o=i.createObjectURL(e),l._type=e.type;else{if("string"!=typeof e)return!1;a=e,n&&n.crossOrigin&&(l.crossOrigin=n.crossOrigin)}return a?(l.src=a,l):i.readFile(e,function(e){var i=e.target;i&&i.result?l.src=i.result:t&&t(e)})},t=window.createObjectURL&&window||window.URL&&URL.revokeObjectURL&&URL||window.webkitURL&&webkitURL;i.isInstanceOf=function(e,i){return Object.prototype.toString.call(i)==="[object "+e+"]"},i.transformCoordinates=function(e,i){var t=e.getContext("2d"),n=e.width,a=e.height;switch(i>4&&(e.width=a,e.height=n),i){case 2:t.translate(n,0),t.scale(-1,1);break;case 3:t.translate(n,a),t.rotate(Math.PI);break;case 4:t.translate(0,a),t.scale(1,-1);break;case 5:t.rotate(.5*Math.PI),t.scale(1,-1);break;case 6:t.rotate(.5*Math.PI),t.translate(0,-a);break;case 7:t.rotate(.5*Math.PI),t.translate(n,-a),t.scale(-1,1);break;case 8:t.rotate(-.5*Math.PI),t.translate(-n,0)}},i.renderImageToCanvas=function(e,i,t,n,a,o,l,s,d,r){return e.getContext("2d").drawImage(i,t,n,a,o,l,s,d,r),e},i.scale=function(e,t){t=t||{};var n,a,o,l,s,d,r,c=document.createElement("canvas"),h=e.getContext||(t.canvas||t.crop||t.orientation)&&c.getContext,u=e.width,_=e.height,g=u,f=_,m=0,p=0,v=0,w=0;return h&&t.orientation>4?(n=t.maxHeight,a=t.maxWidth,o=t.minHeight,l=t.minWidth):(n=t.maxWidth,a=t.maxHeight,o=t.minWidth,l=t.minHeight),h&&n&&a&&t.crop?(s=n,d=a,n/a>u/_?(f=a*u/n,p=(_-f)/2):(g=n*_/a,m=(u-g)/2)):(s=u,d=_,r=Math.max((o||s)/s,(l||d)/d),r>1&&(s=Math.ceil(s*r),d=Math.ceil(d*r)),r=Math.min((n||s)/s,(a||d)/d),1>r&&(s=Math.ceil(s*r),d=Math.ceil(d*r))),h?(c.width=s,c.height=d,i.transformCoordinates(c,t.orientation),i.renderImageToCanvas(c,e,m,p,g,f,v,w,s,d)):(e.width=s,e.height=d,e)},i.createObjectURL=function(e){return t?t.createObjectURL(e):!1},i.revokeObjectURL=function(e){return t?t.revokeObjectURL(e):!1},i.readFile=function(e,i,t){if(window.FileReader){var n=new FileReader;if(n.onload=n.onerror=i,t=t||"readAsDataURL",n[t])return n[t](e),n}return!1},"function"==typeof define&&define.amd?define(function(){return i}):e.loadImage=i}(this),function(e){"use strict";"function"==typeof define&&define.amd?define(["jquery","./load-image.js","jquery-ui"],e):e(window.jQuery,window.loadImage)}(function(e,i){"use strict";e.widget("blueimp.imagegallery",{options:{selector:'a[data-gallery="gallery"]',namespace:"imagegallery",slideshow:0,offsetWidth:100,offsetHeight:100,fullscreen:!1,canvas:!1,imageClickDivision:.5,bodyClass:"gallery-body",loaderId:"gallery-loader",effects:["blind","clip","drop","explode","fade","fold","puff","slide","scale"],modal:!0,resizable:!1,width:"auto",height:"auto",show:"fade",hide:"fade",dialogClass:"gallery-dialog"},_getOverlay:function(){return e(document.body).children(".ui-widget-overlay").last()},_openSibling:function(e){var i=this,t=this._dialog;clearTimeout(this._slideShow),this._slideShow=null,e.href!==this._link.href?t.dialog("widget").hide(this.options.hide,function(){i._overlay=i._getOverlay().clone().appendTo(document.body),t.dialog("option","hide",null).dialog("close"),i._callback=function(){i._overlay.remove(),i._overlay=null},i._open(e)}):t.dialog("close")},_prev:function(){this._openSibling(this._prevLink)},_next:function(){this._openSibling(this._nextLink)},_keyHandler:function(e){var i=e.data.imagegallery;switch(e.which){case 37:case 38:return i._prev(),!1;case 39:case 40:return i._next(),!1}},_wheelHandler:function(e){var i=e.data.imagegallery;return e=e.originalEvent,i._wheelCounter+=e.wheelDelta||e.detail||0,e.wheelDelta&&i._wheelCounter>=120||!e.wheelDelta&&i._wheelCounter<0?(i._prev(),i._wheelCounter=0):(e.wheelDelta&&i._wheelCounter<=-120||!e.wheelDelta&&i._wheelCounter>0)&&(i._next(),i._wheelCounter=0),!1},_clickHandler:function(e){var i=e.data.imagegallery,t=i._dialog;(e.pageX-t.offset().left)/t.width()<i.options.imageClickDivision?i._prev():i._next()},_overlayClickHandler:function(i){var t=i.data.imagegallery;e(this).unbind("click."+t.options.namespace,t._overlayClickHandler),t._dialog.dialog("close")},_openHandler:function(i){var t=i.data.imagegallery,n=t.options;e(document.body).addClass(n.bodyClass),t._getOverlay().bind("click."+n.namespace,i.data,t._overlayClickHandler),t._callback&&(t._callback(),t._callback=null),n.slideshow&&(t._slideShow=setTimeout(function(){t._next()},n.slideshow))},_closeHandler:function(i){var t=i.data.imagegallery,n=t.options;e(document).unbind("keydown."+n.namespace,t._keyHandler).unbind("mousewheel."+n.namespace+", DOMMouseScroll."+n.namespace,t._wheelHandler),clearTimeout(t._slideShow),t._slideShow=null,t._overlay||(e(document.body).removeClass(n.bodyClass),t._position=null),t._dialog.remove(),t._dialog=null},_dragStopHandler:function(e,i){var t=e.data.imagegallery;t._position=i.position},_initDialogHandlers:function(){var i=this,t=this.options,n={imagegallery:this};e(document).bind("keydown."+t.namespace,n,this._keyHandler).bind("mousewheel."+t.namespace+", DOMMouseScroll."+t.namespace,n,this._wheelHandler),i._dialog.bind("click."+t.namespace,n,this._clickHandler).bind("dialogopen."+t.namespace,n,this._openHandler).bind("dialogclose."+t.namespace,n,this._closeHandler).bind("dialogdragstop."+t.namespace,n,this._dragStopHandler)},_loadHandler:function(i){var t=this.options;t.offsetWidth,t.offsetHeight,this._dialog=e("<div></div>"),this._loaded=!0,e(document).unbind("keydown."+t.namespace,this._escapeHandler).unbind("click."+t.namespace,this._documentClickHandler),this._img=null,this._loadingAnimation.hide(),this._initDialogHandlers(),this._position&&(t=e.extend({},t,{position:[this._position.left,this._position.top]})),this._dialog.append(i).appendTo(document.body).toggleClass("gallery-dialog-single",this._prevLink===this._link&&this._nextLink===this._link).dialog(t)},_abortLoading:function(){var i=this.options;this._img.unbind(),e(document).unbind("keydown."+i.namespace,this._escapeHandler).unbind("click."+i.namespace,this._documentClickHandler),this._getOverlay().remove(),this._loadingAnimation.hide(),e(document.body).removeClass(i.bodyClass)},_escapeHandler:function(e){27===e.keyCode&&e.data.imagegallery._abortLoading()},_documentClickHandler:function(i){var t=i.data.imagegallery;e(i.target).closest(t._link).length||t._abortLoading()},_loadImage:function(){var t,n=this,a=this.options,o={imagegallery:this},l=e(window).width(),s=e(window).height();t=a.fullscreen?{minWidth:l,minHeight:s,maxWidth:l,maxHeight:s,canvas:a.canvas}:{maxWidth:l-a.offsetWidth,maxHeight:s-a.offsetHeight,canvas:a.canvas},e(document).bind("keydown."+a.namespace,o,this._escapeHandler).bind("click."+a.namespace,o,this._documentClickHandler),n._loaded=null,this._img=e(i(this._link.href,function(e){n._loadHandler(e)},t)),setTimeout(function(){n._loaded||n._loadingAnimation.show()},100)},_preloadSiblings:function(){e("<img>").prop("src",this._nextLink.href),e("<img>").prop("src",this._prevLink.href)},_initSiblings:function(){var e=this,i=this._link,t=this.element.find(this.options.selector);this._prevLink=null,this._nextLink=null,t.each(function(n){return t[n+1]!==i&&t[n+2]!==i||this.href===i.href||(e._prevLink=this),t[n-1]!==i&&t[n-2]!==i||this.href===i.href?void 0:(e._nextLink=this,!1)}),this._prevLink||(this._prevLink=t[t.length-1]),this._nextLink||(this._nextLink=t[0])},_getRandomEffect:function(){var e=this.options.effects;return e[Math.floor(Math.random()*e.length)]},_initEffects:function(){var e=this.options;("random"===e.show||"random"===this._show)&&(this._show="random",e.show=this._getRandomEffect()),("random"===e.hide||"random"===this._hide)&&(this._hide="random",e.hide=this._getRandomEffect())},_open:function(i){if(this._dialog){var t=e.extend({},this);return t._dialog=null,t._position=null,t._open(i),void 0}this.options.title=i.title||decodeURIComponent(i.href.split("/").pop()),this._link=i,this._initEffects(),this._loadImage(),this._initSiblings(),this._preloadSiblings()},_initFullscreenOptions:function(){var e=this.options;e.fullscreen?(/-fullscreen$/.test(e.dialogClass)||(e.dialogClass+="-fullscreen"),/-fullscreen$/.test(e.bodyClass)||(e.bodyClass+="-fullscreen")):(e.dialogClass=e.dialogClass.replace(/-fullscreen$/,""),e.bodyClass=e.bodyClass.replace(/-fullscreen$/,""))},_initLoadingAnimation:function(){this._loadingAnimation=e('<div id="'+this.options.loaderId+'"></div>').hide().appendTo(document.body)},_destroyLoadingAnimation:function(){this._loadingAnimation.remove(),this._loadingAnimation=null},_delegate:function(){var e=this,i=this.options;this.element.delegate(i.selector,"click."+i.namespace,function(i){i.preventDefault(),e._open(this)})},_undelegate:function(){this.element.undelegate(this.options.selector,"click."+this.options.namespace)},_setOption:function(i,t){this._show=this._hide=null;var n="namespace"===i||"selector"===i;n&&this._undelegate(),e.Widget.prototype._setOption.call(this,i,t),n&&this._delegate(),-1!==e.inArray(i,["fullscreen","dialogClass","bodyClass"])&&this._initFullscreenOptions()},_create:function(){this._wheelCounter=0,this._initLoadingAnimation(),this._initFullscreenOptions(),this._delegate()},destroy:function(){clearTimeout(this._slideShow),this._slideShow=null,this._dialog&&this._dialog.dialog("close"),this._undelegate(),this._destroyLoadingAnimation(),e.Widget.prototype.destroy.call(this)},enable:function(){e.Widget.prototype.enable.call(this),this._delegate()},disable:function(){clearTimeout(this._slideShow),this._slideShow=null,this._undelegate(),e.Widget.prototype.disable.call(this)}})});
$.extend({
	addGisTabs:function(url,tabTitle){
		var tabs=$('#tabmenulist li a').filter(function(index) {
			return $(this).text() == tabTitle.replace('<em>','').replace('</em>','');
		});
		if(tabs[0]){
			var thisIndex=tabs.attr("index");
			$("#gisTabs").tabs("remove",thisIndex);
		}		
		$("#gisInfoBox").hide();
		$("#gisSearchBox").show();
		$("#gisTabs").tabs("add", url, tabTitle);
		$("#gisTabs").tabs("select",$("#gisTabs").tabs("length")-1);
	}
})
$.fn.extend({
	tabPaging:function(o){
		var self = $(this);
		var dfop = {
	        format: "< ncnn >]",
	        perpage: 10,
			lapping: 0,
			page: 1,
	        onSelect: function(page) {
	        	
	        },
	        onFormat: function(type) {
	            switch (type) {
	                case 'block':
	                        if (!this.active)
	                                return '<span class="disabled">' + this.value + '</span>';
	                        else if (this.value != this.page)
	                                return '<em><a href="#' + this.value + '">' + this.value + '</a></em>';
	                        return '<span class="current">' + this.value + '</span>';
	                case 'next':
	                        if (this.active)
	                                return '<a href="#' + this.value + '" class="next">下页</a>';
	                        return '<span class="disabled">Next</span>';
	                case 'prev':
	                        if (this.active)
	                                return '<a href="#' + this.value + '" class="prev">上页</a>';
	                        return '<span class="disabled">Prev</span>';
	                case 'first':
	                        if (this.active)
	                                return '<a href="#' + this.value + '" class="first">首页</a>';
	                        return '<span class="disabled">|<</span>';
	                case 'last':
	                        if (this.active)
	                                return '<div><span class="sum">共'+this.number+'条</span><a href="#' + this.value + '" class="last">尾页</a></div>';
	                        return '<div class="sumbox"><span class="sum">共'+this.number+'条</span></div>';
	                case "leap":
	                        if (this.active)
	                                return "...";
	                        return "";
	                case 'fill':
	                        if (this.active)
	                                return "...";
	                        return "";
	        	}
	        }
		}
	    $.extend(dfop,o);
	    return self.paging(dfop.total,dfop);
	},
	gisTabs:function(o){
		var that=this;
		var self=$(this);
		var selectTabsFun=function(index){
			$("#moreTabs a").eq(index).addClass("cur").siblings().removeClass("cur");
			var thisTabs=$("#tabmenulist li");
			var showIndex=$("#tabmenulist li.ui-tabs-selected:first").index();
			var hideTabsBol=true;
			thisTabs.filter(".showItems").each(function(){
				if($(this).index()==index){
					hideTabsBol=false;
				}
			})
			if(hideTabsBol==true){
				thisTabs.hide().removeClass("showItems");
				for(var i=index;i>index-3;i--){
					thisTabs.eq(i).show().addClass("showItems");
				}
			}
		}
		var dfop={
			tabTemplate: "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close'>删除</span></li>",
			create:function(){
				
			},
			cache:true,
			add: function(event,ui) {
				$("#gisSearchBox").show();
				var tab_content ="检索中,请稍候...";
				$(ui.panel).append( "<div>" + tab_content + "</div>" );
				$(ui.panel).height($("#gisContainer").height()-35);
				$(window).resize(function(){
					$(ui.panel).height($("#gisContainer").height()-35);
				})
				$(ui.tab).attr("index",ui.index).clone().prepend(">>").appendTo("#moreTabs");
			},
			select:function(event,ui){
				$("#moreTabs a").eq(ui.index).addClass("cur").siblings().removeClass("cur");
				selectTabsFun(ui.index);
			},
			remove:function(event,ui){
				var index=$(ui.tab).attr("index");
				var sum=$("#tabmenulist li").size();
				if(sum<=0){
					$("#gisSearchBox").hide();
					$("#gisInfoBox").show();
				}
				$("#moreTabs a").eq(index).remove();
			}
		}
		$.extend(dfop,o);
		var $tabs = self.tabs(dfop);
		$("#tabmenulist").delegate("span.ui-icon-close","click", function() {//移除事件
			var index = $("li",$("#tabmenulist")).index( $( this ).parent());
			$tabs.tabs("remove", index);
		});
		
		$(".gis_tools_sys .more").hover(function(){
			clearTimeout(that.timer);
			that.timer=setTimeout(function(){
				$("#moreTabs").show();
			},100)
		},function(){
			clearTimeout(that.timer);
			that.timer=setTimeout(function(){
				$("#moreTabs").hide();
			},300)
		})
		
		$("#moreTabs").delegate("a","click",function(event){
			event.preventDefault();
			var index=$(this).index();
			self.tabs("select",index);
			$("#moreTabs").hide();
		})
		$(".move_left").on("click",function(){
			var thisIndex=$("#tabmenulist li.ui-tabs-selected").index();
			var sum=$("#tabmenulist li").size()-1;
			if(thisIndex>0){
				$tabs.tabs( "select",thisIndex-1);
			}
		})
		$(".move_right").on("click",function(){
			var thisIndex=$("#tabmenulist li.ui-tabs-selected").index();
			var sum=$("#tabmenulist li").size()-1;
			if(thisIndex<sum){
				$tabs.tabs( "select",thisIndex+1);
			}
		})
		window.tabIndex=0;
		return $tabs;
	},
	scrollBar:function(o){
		var self=$(this);
		var dfop={
			W:"10px",									//设置滚动条宽度
			BgUrl:"",									//设置滚动条背景图片地址
			Bg:"scrollbar_bgy.png",						//设置滚动条背景图片position,颜色等
			Bar:{
				Pos:"",							//设置滚动条初始化位置在底部
				Bd:{Out:"",Hover:""},				//设置滚动滚轴边框颜色：鼠标离开(默认)，经过
				Bg:{Out:"#ccc",Hover:"",Focus:"#000"}		//设置滚动条滚轴背景：鼠标离开(默认)，经过，点击
			},
			Btn:{  btn:false},							//设置下按钮背景：鼠标离开(默认)，经过，点击
			Fn:function(){}								//滚动时候触发的方法
		}
		$.extend(dfop,o);
		self.droppable(dfop);
		self.jscroll(dfop);
	},
	clearField:function(s){//<input type="text" class="clearField" value="请输入用户名" />   调用方法：$('.clearField').clearField();
		s=jQuery.extend({blurClass:'clearFieldBlurred',activeClass:'clearFieldActive',attribute:'rel',value:''},s);
		return $(this).each(function(){
			var el=$(this);
			s.value=el.val();
			if(el.attr(s.attribute)==undefined){
				el.attr(s.attribute,el.val()).addClass(s.blurClass)
			}else{
				s.value=el.attr(s.attribute)
			}
			el.focus(function(){
				if(el.val()==el.attr(s.attribute)){
					el.val('').removeClass(s.blurClass).addClass(s.activeClass)
				}
			});
			el.blur(function(){
				if(el.val()==''){
					el.val(el.attr(s.attribute)).removeClass(s.activeClass).addClass(s.blurClass)
				}
			})
		})
	},
	ajaxResult:function(option){
		var self=jQuery(this);
		var selfId=self.attr("id");
		var timestamp = Date.parse(new Date());
		var selfContent=self.find(".resultCon");
		var timer = null;
		var dfop={
			rowNum:5,
			total:2,
			page:1,
			searchTxt:'',
			type : "get",
	    	paramData : {},
			url:'',
			keyId:'id',
			dataModel:[
				{name:'id',hidden:true},
				{name:'title'},//,hidden:true,formatter:aaa
				{name:'address'}	
   			],
			data:{"page":"1","total":10,"records":"13",
				rows:[
					{id:'111',title:'记录1',address:'地址1'},
					{id:'112',title:'记录2',address:'地址2'},
					{id:'113',title:'记录3',address:'地址3'},
					{id:'114',title:'记录4',address:'地址4'},
					{id:'115',title:'记录5',address:'地址5'},
					{id:'116',title:'记录6',address:'地址6'},
					{id:'117',title:'记录7',address:'地址7'},
					{id:'118',title:'记录8',address:'地址8'},
					{id:'119',title:'记录9',address:'地址9'},
					{id:'110',title:'记录10',address:'地址10'}]},//数据格式
			height: 'auto',
			width:'auto',
		   	sortname: 'id',
		    sortorder: "desc",
			onSelectRow:function(rowId,rowData){},
			pageShowNum:5,
			ondblClickRow:function(rowId,rowData){
				
			},
			loadComplete:function(){}
		}
		jQuery.extend(dfop,option);
		var ajaxFun=function(){
			jQuery.extend(dfop.paramData,{"rows":dfop.rowNum,"page":dfop.page});
			if(dfop.url){
				jQuery.ajax({
					url:dfop.url,
					type:dfop.type,
					data:dfop.paramData,
					success:function(data){
						dfop.total=data.total;
						dfop.data=data;
						dfop.page=data.page;
						init();
						dfop.loadComplete();
					}
				});
			}else{
				init();
				dfop.loadComplete();
			}
		}
		var pageInit=function(){
			var showNum=dfop.pageShowNum;
			var i=1;
			var total;
			if(dfop.data.records%dfop.rowNum==0){
				total=parseInt(dfop.data.records/dfop.rowNum);
			}else{
				total=parseInt(dfop.data.records/dfop.rowNum)+1;
			}
			
			var nowPageLength=(showNum>=total)? total:showNum;
			var resultPager=jQuery('<div />').attr("id","Page"+timestamp).addClass("number");
			if(parseInt(dfop.page/showNum)>=1){//是否生成上一页
				i=parseInt(dfop.page/showNum)*showNum;
				nowPageLength=(i+showNum>=total)? total:i+showNum;
				var thisPage=jQuery('<a href="javascript:;"></a>').text(1).attr("value",1);
				var morePage=jQuery('<a href="javascript:;"></a>').text("…").attr("title","上一组").attr("value",i-1);
				resultPager.append(thisPage).append(morePage);
			}
			
			for(;i<=nowPageLength;i++){//生成页码
				var thisPage;
				if(dfop.page==i || dfop.total<=1){
					thisPage=i;
				}else{
					thisPage=jQuery('<a href="javascript:;"></a>').text(i).attr("value",i);
				}
				resultPager.append(thisPage);
			}
			if(total>nowPageLength){//判断是否显示下一组
				if(nowPageLength>=showNum){
					var morePage=jQuery('<a href="javascript:;"></a>').text("…").attr("title","下一组").attr("value",nowPageLength+1);
					resultPager.append(morePage);
				}
			}
			
			if(dfop.total>=1){
				var nextPage=jQuery('<a href="javascript:;" class="next">下页></a>').attr("value",parseInt(dfop.page)+1);
				var prevPage=jQuery('<a href="javascript:;" class="prev">上页<</a>').attr("value",parseInt(dfop.page)-1);
								
				if(dfop.page>1){
					resultPager.prepend(prevPage);
				}
				
				if(parseInt(dfop.page)<dfop.total){
					resultPager.append(nextPage);
				}
			}
			resultPager.append('<a href="javascript:;">共'+total+'页</a>');
			
			resultPager.delegate("a", "click", function(){
				var thisValue=jQuery(this).attr("value");
				if(thisValue==undefined){return;}
				jQuery(this).after('<span>'+jQuery(this).text()+'</span>').remove();
				dfop.page=thisValue;
				selfContent.empty();
				ajaxFun();
			});
			self.append(resultPager);
		}
		
		//取得数组的值
		var getRowData=function(rowId){
			var thisRowData;
			jQuery.each(dfop.data.rows,function(i, n){
				if(n[dfop.keyId]==rowId){
					thisRowData=n;
				};
			});
			return thisRowData;
		}
		
		var init=function(){
			var showRowNumber=(dfop.data.rows.length>=dfop.rowNum)?dfop.rowNum:dfop.data.rows.length;
			var resultCon=selfContent;
			var resultNum='<div class="resultNum"><span class="plaint"></span>共有<span class="red">'+dfop.data.records+'</span>项符合<span class="red">'+dfop.searchTxt+'</span>的搜索条件</div>';
			var resultList=jQuery("<ul />").addClass("resultlist");//结果列表
			for(var i=0;i<showRowNumber;i++){
				var rowNum=i+1;
				var thisRowData=new Array();
				var rowId;
				var thisRow=jQuery('<li />').data("data",dfop.data.rows[i]);
				for(var j=0;j<dfop.dataModel.length;j++){//根据Model中设置的字段，循环取得结果集的各个字段
					var rowValue = "";
					if(dfop.dataModel[j].name.split(".").length > 1){
						var obj
						for(var index = 0; index < dfop.dataModel[j].name.split(".").length ; index++){
							if(index == (dfop.dataModel[j].name.split(".").length-1)){
								rowValue = obj[dfop.dataModel[j].name.split(".")[index]];
							}else{
								obj = dfop.data.rows[i][dfop.dataModel[j].name.split(".")[index]];
							}
						}
					}else{
						rowValue =dfop.data.rows[i][dfop.dataModel[j].name];
					}
					if(rowValue == "null"){
						rowValue = "";
					}
					var thisAttr=jQuery('<div />').attr("aria-describedby",dfop.dataModel[j].name);
					if(dfop.dataModel[j].key==true){
						thisRow.attr("rowId",rowValue);
					}
					if(dfop.dataModel[j].hidden==true){
						thisAttr.hide();
					}
					if(dfop.dataModel[j].formatter){
						thisAttr.append(dfop.dataModel[j].formatter(thisRow,dfop.dataModel,dfop.data.rows[i]));
					}else{
						thisAttr.append(rowValue);//根据model中的字段取得并添加当前记录
					}
					thisRow.append(thisAttr);
				}
				
				thisRow.bind("click",function(){
					var thisId=jQuery(this).attr("rowId");
					var row=jQuery(this);
					clearTimeout(timer);
					var rowData=getRowData(thisId);
					timer = setTimeout(function() {
						$(".resultBar").data("selectrow", getRowData(thisId));
						dfop.onSelectRow(thisId,rowData);
					}, 250);
				})
				thisRow.bind("dblclick",function(){
					var thisId=jQuery(this).attr("rowId");
					clearTimeout(timer);
					var rowData=getRowData(thisId);
					$(".resultBar").data("selectrow", getRowData(thisId));
					dfop.ondblClickRow(thisId,rowData);
				})
				resultList.append(thisRow);
			}
			//清空
			self.empty();
			resultCon.empty();
			//插入
			selfContent.append(resultList)
			self.append(resultNum).append(resultCon).append();
			//生成页码
			pageInit();
			//高度问题
			jQuery(".resultlist").delegate("li", "click", function(){
				jQuery(".resultlist li").removeClass("cur");
				jQuery(this).addClass("cur");
			})
		}
		
		ajaxFun();
		return this;
		
	}
})

$.fn.extend({
    tablist:function(options){

        var $this=$(this);
        var $gisList=$this.children("ul");
        var $gislistLi=$gisList.children("li");
        var $gisCon=$this.children("div");

        var defultOptions={
                div:'.searchContain',
                updownlist:'.updownList',
                current:'current'
        };

        $.extend(defultOptions,options);

        var searchTab=function(){
            $gislistLi.each(function(index){
                $(this).click(function(){
                    $(this).addClass(defultOptions.current).siblings().removeClass(defultOptions.current);
                    $gisCon.children(defultOptions.div).hide().eq(index).show();

                    if(index==0){
                        $(defultOptions.updownlist).show();
                    }else{
                        $(defultOptions.updownlist).hide();
                    };
                });
            });
        }();
    },
    gisSearchSelect:function(options){

        var $self=$(this);
        var $selfId=$self.attr("id");
        var $options=$self.children("option");
        var $downlist=$("<div class='updownList'></div>");
        var $sarrow=$("<div class='sarrow'></div>");
        var $createDl=$("<dl/>");
        var $createDt=$("<dt/>");
        var $createDd=$("<dd class='updwonlist-Content'></dd>").attr("id",$selfId+"Content");
        var $createUl=$("<ul/>");
        var $c=$options.first().text();

        $self.after($downlist);
        $downlist.append($sarrow);
        $downlist.append($createDl);
        $createDl.append($createDt);
        $createDl.append($createDd);
        $createDd.append($createUl);
        $createDt.text($c);

        var defaultOptions={
                updownlist:'.updownList',
                updownlistC:'.updwonlist-Content',
                current:'cur',
                inputText:'#queryString',
                change:function(){

                },
                linkageFunc:function(){

                }
        };

        $.extend(defaultOptions,options);

        var previewDefault="";

        var init=function(){

            $options.each(function(index){

                var self=$(this);
                var firstOption=$options.eq(0);
                var $createLl=$("<li/>");
                var thisValue=self.attr("value");
                var thisName=self.attr("name");
                var thisTitle=self.attr("title");
                var thisAlt=self.attr("alt");
                var thisDir=self.attr("dir");
                var thisModeType=self.attr("modeType");
                var thisSearchfieldname=self.attr("searchfieldname");
                var thisSearchfield=self.attr("searchfield");

                var $firstTitle=$.trim(firstOption.text());
                var $con=$.trim(self.text());

                var searchfieldaname=$.trim(firstOption.attr("name"));
                var searchfieldbname=$.trim(firstOption.attr("title"));
                var searchfielda=$.trim(firstOption.attr("alt"));
                var searchfieldb=$.trim(firstOption.attr("dir"));
                var modeType=$.trim(firstOption.attr("modeType"));
                var modeValue=$.trim(firstOption.attr("value"));
                var searchfieldname=$.trim(firstOption.attr("searchfieldname"));
                var searchfield=$.trim(firstOption.attr("searchfield"));

                $createUl.append($createLl);

                $createDt.attr({
                    "name":searchfieldaname,
                    "title":$firstTitle,
                    "alt":searchfielda,
                    "dir":searchfieldb,
                    "value":modeValue,
                    "modeType":modeType,
                    "searchfieldname":searchfieldname,
                    "searchfield":searchfield
                });

                $createLl.attr({
                    "title":$con,
                    "value":thisValue,
                    "name":thisName,
                    "alt":thisAlt,
                    "dir":thisDir,
                    "modeType":thisModeType,
                    "searchfieldname":thisSearchfieldname,
                    "searchfield":thisSearchfield
                }).text($con);

                var str="请输入("+$createDt.eq(0).attr("name");
                if(searchfieldbname!="" && thisSearchfieldname==""){
                    str+="/"+searchfieldbname;
                }
                if(thisSearchfieldname!="" && searchfieldbname==""){
                    str+="/"+thisSearchfieldname;
                }
                if(searchfieldbname!="" && thisSearchfieldname!=""){
                    str+="/"+searchfieldbname+"/"+thisSearchfieldname;
                }
                str+=")";
                if($createDt.eq(0).attr("name")==''){
                    str="请输入搜索条件";
                    previewDefault=str;
                    excutefocusFunc(str);
                }

                $(defaultOptions.inputText).attr("value",str);
                //$(defaultOptions.inputText).attr("value","请输入("+$createLl.eq(0).attr("name")+"/"+searchfieldbname+"/"+thisSearchfieldname+")"); //default

                var $defaultValueC=$(defaultOptions.inputText).attr("value");

                excutefocusFunc($defaultValueC);

                previewDefault=$defaultValueC;

                $createLl.bind("click",function(){

                    var thisText=$(this).text();
                    var currentOption=$options.eq(index);

                    var thisFieldaname=$.trim(currentOption.attr("name"));
                    var thisFieldbname=$.trim(currentOption.attr("title"));
                    var thisFielda=$.trim(currentOption.attr("alt"));
                    var thisFieldb=$.trim(currentOption.attr("dir"));
                    var thisModeType=$.trim(currentOption.attr("modeType"));
                    var thisSearchfieldname=$.trim(currentOption.attr("searchfieldname"));
                    var thisSearchfield=$.trim(currentOption.attr("searchfield"));
                    var $defaultValue=$(defaultOptions.inputText).attr("value");

                    var str="请输入("+thisFieldaname;
                    if(thisFieldbname!="" && thisSearchfieldname==""){
                        str+="/"+thisFieldbname;
                    }
                    if(thisSearchfieldname!="" && thisFieldbname=="" ){
                        str+="/"+thisSearchfieldname;
                    }
                    if(thisFieldbname!="" && thisSearchfieldname!=""){
                        str+="/"+thisFieldbname+"/"+thisSearchfieldname;
                    }
                    str+=")";

                    if(thisFieldaname==''){
                        str="请输入搜索条件";
                        excutefocusFunc(str);
                    }
                    if(thisFieldaname==''&&($(defaultOptions.inputText).attr("value")==previewDefault||$(defaultOptions.inputText).attr("value")=='')){
                        $(defaultOptions.inputText).attr("value",str);
                        previewDefault=str;
                    }

                    if($(defaultOptions.inputText).attr("value")==''||$(defaultOptions.inputText).attr("value")==previewDefault){
                        previewDefault=str;
                        $(defaultOptions.inputText).attr("value",str);
                    }
                    excutefocusFunc(str);
                    if($(defaultOptions.inputText).attr("value")!=''&&$(defaultOptions.inputText).attr("value")!=previewDefault){
                        previewDefault=str;
                    }

                    //$(defaultOptions.inputText).attr("value","请输入("+thisFieldaname+"/"+thisFieldbname+"/"+thisSearchfieldname+")");   //click

                    $(this).addClass(defaultOptions.current).siblings().removeClass(defaultOptions.current);
                    $(this).text($(this).text());
                    $createDt.text($(this).text()).attr("title",$con);
                    $self.attr("value",thisValue);
                    $createDt.attr({
                        "name":thisFieldaname,
                        "title":thisFieldbname,
                        "alt":thisFielda,
                        "dir":thisFieldb,
                        "value":thisValue,
                        "modeType":thisModeType,
                        "searchfieldname":thisSearchfieldname,
                        "searchfield":thisSearchfield
                    });

                    defaultOptions.change(thisText,thisValue);
                    defaultOptions.linkageFunc(thisValue);
                    //excutefocusFunc($defaultValue);
                    $createDd.hide();

                });
             });
        }();

        var clickUnit=function(){
            $createDt.bind("click",function(e){
                triggleContent();
            });

            $sarrow.bind("click",function(e){
                triggleContent();
            });

            $(document).on("click",function(e){
                var $target=$(e.target);
                if(!($target.is(defaultOptions.updownlistC) || $target.closest(defaultOptions.updownlistC)[0] || $target.closest(defaultOptions.updownlist)[0])){
                    $createDd.hide();
                }
            });

            function triggleContent(){
                if($createDd.is(":visible")){
                     $createDd.hide();
                }else{
                     $createDd.show();
                }
            }
        }();

        function excutefocusFunc($defaultValue){
            $(defaultOptions.inputText).unbind("focusin").bind("focusin",function(){
                if($(defaultOptions.inputText).attr("value")==$defaultValue){
                    $(defaultOptions.inputText).attr("value","");
                }
            }).unbind("focusout").bind("focusout",function(){
                if($(defaultOptions.inputText).val()!=""){
                    //$(defaultOptions.inputText).attr("value");
                }else{
                    $(defaultOptions.inputText).attr("value",$defaultValue);
                }
            });
        }
    },
    updownlist:function(options){

        var $self=$(this);
        var $options=$self.children("option");
        var $downlist=$("<div class='updownList'></div>");
        var $createDl=$("<dl/>");
        var $createDt=$("<dt/>");
        var $createDd=$("<dd class='updwonlist-Content'></dd>");
        var $createUl=$("<ul/>");
        var $c=$options.first().text();

        $self.after($downlist);
        $downlist.append($createDl);
        $createDl.append($createDt);
        $createDl.append($createDd);
        $createDd.append($createUl);
        $createDt.text($c);

        var init=function(){
            $options.each(function(index){
                var $createLl=$("<li/>");
                var thisValue=$(this).attr("value");
                var $con=$(this).text();

                $createUl.append($createLl);
                $createLl.text($con).attr("value",thisValue);
                    $createLl.click(function(){
                        var thisText=$(this).text();
                        var thisValue=$(this).attr("value");

                        $(this).text($(this).text());
                        $createDt.text($(this).text());
                        $self.attr("value",thisValue);
                        $createDd.hide();
                    });
             })	;
        }();

        $createDt.click(function(){
            if($createDd.is(":visible")){
                 $createDd.hide();
            }else{
                 $createDd.show();
                 $downlist.prevAll().click(function(){
                     $createDd.hide();
                 });
            }
        });

    },
    upDownList:function(options){

        var $this=$(this);
        var $exeContent=$this.children("div");
        var $allSpan=$exeContent.children("h1").children("span");
        var $exeClist=$exeContent.children("div");

        var defaultOptions={
            current:'downCur'
        }

        $.extend(defaultOptions,options);

        $exeContent.each(function(index){

            var $exeCT=$(this).children("h1");
            var $exeCTC=$(this).children("div");
            var $exeCtitleCur=$($exeCT,this);

            var $exeClistCur=$($exeCTC,this);
            var $span=$exeCtitleCur.find("span");

            $exeCtitleCur.find("span").click(function(){
                if($exeClistCur.is(":visible")){
                    $exeClistCur.hide();
                    $span.addClass(defaultOptions.current).removeClass(defaultOptions.current);

                }else{
                    $exeClistCur.show();
                    $span.removeClass(defaultOptions.current).addClass(defaultOptions.current);
                    if($exeClistCur.is(":visible")){
                        showLayer();
                    };
                }
            });

            function showLayer(){
                $exeClistCur.show();
                $allSpan.removeClass(defaultOptions.current);
                $span.addClass(defaultOptions.current);
            }
        });
    },
    MultiLevelMenu:function(options){
        var self=$(this);
        var timer;
        var defaultOption={
                childObj:'showPersonLayer',
                currentHover:'currentPosHover',
                currentClick:'currentPosClick',
                checkedFun:function(){},
                uncheckedFun:function(){},
                hoverFun:function(){},
                removeFunc:function(){}
            };
        $.extend(defaultOption,options);
        function displayMultiLevelMenu(){
            $("#"+defaultOption.childObj+">ul>li").hover(function(){

                if($(this).children("ul")[0]){
                    $(this).removeClass("currentPointer").addClass("currentDefault");
                    $(this).children("ul").children("li").addClass("currentPointer");
                    $(this).children("ul").children("li").find("label").css({
                                                                            cursor:"pointer"
                                                                        });
                }else{
                    $(this).addClass("currentPointer").removeClass("currentDefault");
                    $(this).find("label").css({
                                                cursor:"pointer"
                                            });
                }

                defaultOption.hoverFun(this);
                $("#"+defaultOption.childObj+">ul>li").find("ul").hide();
                $(this).find("ul").show();
                $("#"+defaultOption.childObj+">ul>li").removeClass(defaultOption.currentHover);
                $(this).addClass(defaultOption.currentHover);
                $(this).find("ul").children("li").hover(function(){
                    $(this).addClass(defaultOption.currentHover);
                },function(){
                    $(this).removeClass(defaultOption.currentHover);
                })
                var ul=$(this).find("ul");
                var sublen=ul.find("li").size();
                if(sublen>3&&$(this).index()>5){
                    ul.css({top:-28*(sublen-1)});
                }
            },function(){
                timer=setTimeout(function(){
                    $(this).find("ul").hide();
                },1000)
                $(this).removeClass(defaultOption.currentHover);
            })
        }

        displayMultiLevelMenu();

        function displayEffect(){
            self.find("ul>li").delegate("label","click",function(){

                if($(this).parent().children("ul")[0]){
                    $(this).parent().find("li").removeClass(defaultOption.currentClick);
                    $(this).parent().removeClass(defaultOption.currentClick);
                }else{
                    if($(this).parent("li").hasClass(defaultOption.currentClick)){

                        $(this).parent("li").removeClass(defaultOption.currentClick);
                    }else{
                        $(this).parent("li").addClass(defaultOption.currentClick).siblings().removeClass(defaultOption.currentClick);
                        $(this).parents("li").siblings().find("ul li").removeClass(defaultOption.currentClick);
                    }
                }
            });

        }
        displayEffect();
    },
    scrollWay:function(options){

        var $this=$(this);
        var maxLength= 0,
            upCount  = 0;

        dfop={
            floor:'.displayFloor',
            up:'',
            down:'',
            left:'',
            right:'',
            upLine:'1',
            leftLine:'11',
            speed:'3000',
            num:'1',
            pObject:'table',
            cObject:'tr',
            crObject:'td'
        };
        $.extend(dfop,options);

        /*button*/
        var _btnUp = $("#"+dfop.up),_btnDown = $("#"+dfop.down), _btnLeft = $("#"+dfop.left), _btnRight = $("#"+dfop.right);

        /*elements*/
        var scrollObj =$this.find(dfop.pObject);
        var floorCon=$(dfop.floor);

        var displayFloor=$("<ul/>").addClass("floorList").appendTo(floorCon);

        var marginUpSpace = parseInt(scrollObj
                .find(dfop.cObject+":first")
                .css("marginTop"),10)+parseInt(scrollObj
                        .find(dfop.cObject+":first")
                        .css("marginBottom"),10);

        var marginLeftSpace	= parseInt(scrollObj
                .find(dfop.cObject+":first "+dfop.crObject+":first")
                .css("marginLeft"),10)+parseInt(scrollObj
                        .find(dfop.cObject+":first "+dfop.crObject+":first")
                        .css("marginRight"),10);

        var paddingLeftSpace = parseInt(scrollObj
                .find(dfop.cObject+":first "+dfop.crObject+":first")
                .css("paddingLeft"),10)+parseInt(scrollObj
                        .find(dfop.cObject+":first "+dfop.crObject+":first")
                        .css("paddingRight"),10);

        var paddingUpSpace	= parseInt(scrollObj
                .find(dfop.cObject+":first "+dfop.crObject+":first")
                .css("paddingTop"),10)+parseInt(scrollObj
                        .find(dfop.cObject+":first "+dfop.crObject+":first")
                        .css("paddingBottom"),10);

        var borderTopSpace = parseInt(scrollObj
                .find(dfop.cObject+":first "+dfop.crObject+":first")
                .css("border-bottom-width"),10);


        var trHeight = parseInt(scrollObj
                .find(dfop.cObject+":first")
                .height(),10);

        // 合并前默认宽度
        var tdWidth	= parseInt(scrollObj
                .find(dfop.cObject+":first")
                .find(dfop.crObject)
                .outerWidth(),10);

        var allTdColspan = scrollObj.find(dfop.cObject+":first").find(dfop.crObject).attr("colspan");

        //合并后获取最小宽度
        if(allTdColspan > 1){
            //判断全部被合并的情况下
            tdWidth	=parseInt(scrollObj.find(dfop.cObject+":first").find(dfop.crObject).outerWidth(),10)/allTdColspan;

        }else{
            //判断非全部合并获取最小宽度
//            scrollObj.find(dfop.cObject+":first").find(dfop.crObject).each(function(index){
//                var _allTd = scrollObj.find(dfop.cObject+":first").find(dfop.crObject);
//
//                if(_allTd.eq(index).outerWidth() > _allTd.eq(index-1).outerWidth()){
//                    tdWidth = _allTd.eq(index-1).outerWidth();
//                }
//            })
        }

        var lineTrH=marginUpSpace+trHeight+borderTopSpace;
        var lineTdW=tdWidth;
        var m=trSingleHeightLine = dfop.upLine ? parseInt(dfop.upLine, 10):parseInt($this.height()/lineTrH,10);
        var n=tdSingleWidthLine = dfop.leftLine ? parseInt(dfop.leftLine, 10):parseInt($this.width()/lineTdW,10);

        var upHeight = trSingleHeightLine * lineTrH;
        var leftWidth = tdSingleWidthLine * lineTdW;
        var spd = dfop.speed ? parseInt(dfop.speed,10):600;

        $this.data("len",scrollObj.find(dfop.cObject).length/parseInt(dfop.num,10));

        function getLeftCount(){
        	 /*max length*/
            scrollObj.find(dfop.cObject).each(function(index){
                var thisLength=$(this).children(dfop.crObject).size();
                if(maxLength<thisLength){
                    maxLength=thisLength;
                }
            });
            return maxLength/dfop.num;
        }
        /*create Floor*/
        function screateFloor(){
            var length=scrollObj.find(dfop.cObject).length;
            scrollObj.find(dfop.cObject).each(function(index){
                var index=length-index;
                var createLi=$("<li/>")
                        .html("<div>第</div><div>"+index+"</div><div>层</div>")
                        .appendTo(displayFloor)
                        .height(lineTrH-1);

                createLi.children("div").css("lineHeight",lineTrH/3+'px');

                $(this).find(dfop.crObject).each(function(Tindex){
                    var Tindex=Tindex+1;
                    if(Tindex<10){
                        if($(this).parent().find("td.split")[0]){

                            if($(this).find("input:first").attr("value")==""){
                                $(this).find("input:first").attr("value",(index)+'0'+parseInt(Tindex,10)+"[暂无数据!]");
                            }
                        }else{

                            if($(this).find("input:first").attr("value")==""){
                                $(this).find("input:first").attr("value",index+'0'+parseInt(Tindex,10)+"[暂无数据!]");
                            }
                        }

                    }else{
                        if($(this).parent().find("td.split")[0]){

                            if($(this).find("input:first").attr("value")==""){
                                $(this).find("input:first").attr("value",(index+1)+''+parseInt(Tindex,10)+"[暂无数据!]");
                            }
                        }else{

                            if($(this).find("input:first").attr("value")==""){
                                $(this).find("input:first").attr("value",index+''+parseInt(Tindex,10)+"[暂无数据!]");
                            }
                        }
                    }
                })
            })
        }
        screateFloor();

        /*scroll Floor*/
        function scrollUp() {

            if (!scrollObj.is(":animated")) {
                upCount = $this.data("len");
                if (m < upCount) {

                    m += trSingleHeightLine;
                    scrollObj.animate({ marginTop: "-=" + upHeight + "px" }, spd);
                    displayFloor.animate({
                        marginTop: "-=" + upHeight + "px"
                        }, spd);

                    if(m==upCount){
                        _btnUp.removeClass("upEnable")
                            .addClass("upDisable upHover");
                    }else{
                        _btnUp.addClass("upEnable")
                            .removeClass("upDisable");

                        _btnDown.addClass("downEnable")
                            .removeClass("downDisable");
                    }
                }
            }
        };
        function scrollDown() {
            if (!scrollObj.is(":animated")) {
                if (m > trSingleHeightLine) {
                    m -= trSingleHeightLine;
                    scrollObj.animate({
                            marginTop: "+=" + upHeight + "px"
                        }, spd);
                    displayFloor.animate({
                            marginTop: "+=" + upHeight + "px"
                        }, spd);

                    if(m==trSingleHeightLine){
                        _btnDown.removeClass("downEnable")
                        .addClass("downDisable downHover");

                    }else{
                        _btnDown.addClass("downEnable")
                        .removeClass("downDisable");


                        _btnUp.addClass("upEnable")
                        .removeClass("upDisable");

                    }

                }
            }
        };
        function scrollLeft() {
        	var leftCount=getLeftCount();
            if (!scrollObj.is(":animated")) {
                if (n < leftCount) {
                    n += tdSingleWidthLine;
                    scrollObj.animate({
                            marginLeft:"-="+leftWidth+"px"
                        }, spd);

                    if(n==leftCount){
                        _btnLeft.removeClass("leftEnable")
                            .addClass("leftDisable leftHover");
                    }else{
                        _btnLeft.addClass("leftDisable")
                            .removeClass("leftEnable");
                        _btnRight.addClass("rightEnable")
                            .removeClass("rightDisable");
                    }

                }
            }
        };
        function scrollRight() {
            if (!scrollObj.is(":animated")) {
                if (n > tdSingleWidthLine) {
                    n -= tdSingleWidthLine;
                    scrollObj.animate({
                            marginLeft: "+=" + leftWidth + "px"
                        }, spd);
                    if(n==tdSingleWidthLine){
                        _btnRight.removeClass("rightEnable")
                            .addClass("rightDisable rightHover");
                    }else{
                        _btnRight.addClass("rightEnable")
                            .removeClass("rightDisable");
                        _btnLeft.addClass("leftEnable")
                            .removeClass("leftDisable");
                    }
                }
            }
        };
        function scorllLeftFunc(){
            if(n<getLeftCount()){
                scrollLeft();
                scrollRight();
            }else{
                return false;
            }
        }

        function scorllRightFunc(){
            if(n>dfop.leftLine){
                scrollRight();
            }else{
                return false;
            }
        }

        /*triggle event*/
        function triggleEvent(){
            _btnUp.unbind("click").bind("click", scrollUp);
            _btnDown.unbind("click").bind("click", scrollDown);
            _btnLeft.unbind("click").bind("click", scorllLeftFunc);
            _btnRight.unbind("click").bind("click", scorllRightFunc);
        };
        triggleEvent();
    }
});
$.fn.extend({
	GisList:function(option,updateOption){
		var self=$(this);
		
		if(typeof(option)=="string"){
			switch(option){
				case 'option':
					if(updateOption){
						$.extend(self.data("defaultOption"),updateOption);
					}else{
						return self.data("defaultOption");
					}
					break;
				case 'reload':
					ajaxData(self.data("defaultOption"));
					break;
			}
			return ;
		}
	
		var defaultOption = {
				url:'',data:{},
				rowFormatter:false,
				afterInit:false,
				ajaxLoad:function(data){
					
				}
		};
		$.extend(defaultOption,option);
		self.data("defaultOption",defaultOption);
		function changeRecords(data){
			$("#"+self.attr("id")+"Total").html(data.records);
		}
	
		function formatterRowList(data){
			self.nextAll().remove();
			if(data.records>0){
				var rows=data.rows;
				var listHtml = $("<ul />").addClass("resultlist");
				for(var i =0 ;i<rows.length;i++){
					var rowObj=defaultOption.rowFormatter(rows[i],i);
					listHtml.append(rowObj);
				}
				self.parent().append(listHtml);
			}
		}
		
		function formatterPager(data){
			$("#"+self.attr("id")+"Pager").tabPaging({
				total:data.records,
				perpage: self.data("defaultOption").data.rows,
				page: data.page,
		        onSelect: function(page) {
					if(!self.data("initState")){
						$.extend(self.data("defaultOption").data,{page:page});
						ajaxData(self.data("defaultOption"));
					}
		        }
			});
		}
		
		function ajaxData(defaultOption){
			$.ajax({
				url:defaultOption.url,
				async:true,
				data:defaultOption.data,
				success:function(data){
					changeRecords(data);
					formatterRowList(data);
					if(self.data("initState")){
						formatterPager(data);
					}
					self.data("initState",false);
					if(defaultOption.afterInit){
						self.data("defaultOption").afterInit();
					}
					defaultOption.ajaxLoad(data);
				}
			});
		}
		self.data("initState",true);
		ajaxData(self.data("defaultOption"));
	}
});
var colNames = {
	"LETTING_HOUSE" : [ 'id', '房东', '住房编号', '出租房地址' ],
	"SCHOOL" : [ 'id', '学校名称', '校长', '学校地址' ],
	"OTHER_LOCALE" : [ 'id', '联系人', '场所名称', '场所地址' ],
	"ENTERPRISEKEY" : [ 'id', '法人代表', '企业名称', '企业地址' ],
	"SAFETYPRODUCTIONKEY" : [ 'id', '负责人', '安全生产重点企业名称', '安全生产重点企业地址' ],
	"SECURITYKEY" : [ 'id', '负责人', '治安重点企业名称', '治安重点企业地址' ],
	"FIRESAFETYKEY" : [ 'id', '负责人', '消防重点安全名称', '消防重点安全地址' ],
	"RELIGION" : [ 'id', '负责人', '宗教场所名称', '宗教场所地址' ],
	"HOSPITAL" : [ 'id', '医院院长', '医院名称', '医院地址' ],
	"ADMINISTRATIVE_INSTITUTION" : [ 'id', '法人代表', '单位名称', '单位地址' ]
};
var colModel = {
	"LETTING_HOUSE" : [{
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 120,
		frozen : true
	}, {
		name : 'houseCode',
		index : "houseCode",
		width : 70,
		frozen : true
	}, {
		name : "lettingHouseAddr",
		index : 'lettingHouseAddr',
		sortable : false,
		width : 300
	} ],
	"SCHOOL" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'chineseName',
		index : "chineseName",
		width : 120,
		frozen : true
	}, {
		name : 'president',
		index : "president",
		width : 70,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"OTHER_LOCALE" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'contactPerson',
		index : "contactPerson",
		width : 120,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 70,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"ENTERPRISEKEY" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 120,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 70,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"SAFETYPRODUCTIONKEY" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 70,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 120,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"SECURITYKEY" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 70,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 120,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"FIRESAFETYKEY" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 70,
		frozen : true
	}, {
		name : 'name',
		index : "name",
		width : 120,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"RELIGION" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'dutyPeople',
		index : "dutyPeople",
		width : 100,
		frozen : true
	}, {
		name : 'religiousPlaces',
		index : "religiousPlaces",
		width : 100,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"HOSPITAL" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'director',
		index : "director",
		width : 100,
		frozen : true
	}, {
		name : 'hospitalName',
		index : "hospitalName",
		width : 100,
		frozen : true
	}, {
		name : "address",
		index : 'address',
		sortable : false,
		width : 300
	} ],
	"ADMINISTRATIVE_INSTITUTION" : [ {
		name : "id",
		index : "id",
		hidden : true,
		frozen : true
	}, {
		name : 'legalPerson',
		index : "legalPerson",
		width : 100,
		frozen : true
	}, {
		name : 'unitName',
		index : "unitName",
		width : 100,
		frozen : true
	}, {
		name : "unitAddress",
		index : 'unitAddress',
		sortable : false,
		width : 300
	} ]
};
var urls = {
	"LETTING_HOUSE" : "/baseinfo/lettingHouseManage/getUnboundLettingHouseListByOrgId.action",
	"SCHOOL" : "/school/schoolConteroller/getUnboundSchoolListByOrgId.action",
	"OTHER_LOCALE" : "/baseinfo/otherLocaleManage/getUnboundOtherLocaleListByOrgId.action",
	"ENTERPRISEKEY" : "/baseinfo/enterpriseManage/getUnboundEnterpriseListByOrgId.action",
	"SAFETYPRODUCTIONKEY" : "/baseinfo/enterpriseManage/getUnboundEnterpriseListByOrgIdAndType.action",
	"SECURITYKEY" : "/baseinfo/enterpriseManage/getUnboundEnterpriseListByOrgIdAndType.action",
	"FIRESAFETYKEY" : "/baseinfo/fireSafetyEnterpriseManage/getUnboundFireSafetyEnterpriseListByOrgId.action",
	"RELIGION" : "/baseinfo/religion/getUnboundReligionListByOrgId.action",
	"HOSPITAL" : "/baseinfo/hospital/getUnboundHospitalListByOrgId.action",
	"ADMINISTRATIVE_INSTITUTION" : "/baseinfo/administrativeInstitutionManage/getUnboundAdministrativeInstitutionListByOrgId.action"
};

var addPlaceUrls = {
		"LETTING_HOUSE" : "/baseinfo/lettingHouseManage/dispatch.action?mode=add&organizationId=",
		"SCHOOL" : "/school/schoolConteroller/prepareSchoolAction.action?mode=add&orgId=",
		"OTHER_LOCALE" : "/baseinfo/otherLocaleManage/dispatch.action?mode=add&organization.id=",
		"ENTERPRISEKEY" : "/baseinfo/enterpriseManage/dispatchOperate.action?mode=add&ownerOrg.id=",
		"SAFETYPRODUCTIONKEY" : "/baseinfo/enterpriseManage/dispatchOperate.action?mode=add&ownerOrg.id=",
		"SECURITYKEY" : "/baseinfo/enterpriseManage/dispatchOperate.action?mode=add&ownerOrg.id=",
		"FIRESAFETYKEY" : "/baseinfo/fireSafetyEnterpriseManage/dispatchOperate.action?mode=add&ownerOrg.id=",
		"RELIGION" : "/baseinfo/religion/dispatchOperate.action?mode=add&ownerOrg.id=",
		"HOSPITAL" : "/baseinfo/hospital/dispatch.action?mode=add&orgId=",
		"ADMINISTRATIVE_INSTITUTION" : "/baseinfo/administrativeInstitutionManage/dispatch.action?mode=add&ownerOrg.id="
	};

/*
 jQuery paging plugin v1.0.1 09/04/2011
 http://www.xarg.org/project/jquery-color-plugin-xcolor/

 Copyright (c) 2011, Robert Eisele (robert@xarg.org)
 Dual licensed under the MIT or GPL Version 2 licenses.
*/
;(function(k,n,p){k.fn.paging=function(s,t){function r(a){a.preventDefault();a=a.target;do if("a"===a.nodeName.toLowerCase())break;while(a=a.parentNode);o.setPage(k.data(a,"page"));if(o.n)n.location=a.href}var u=this,o={setOptions:function(a){this.a=k.extend(this.a||{lapping:0,perpage:10,page:1,refresh:{interval:10,url:null},format:"",onFormat:function(){},onSelect:function(){return true},onRefresh:function(){}},a||{});this.a.perpage<1&&(this.a.perpage=10);if(this.a.refresh.url)this.k&&n.clearInterval(this.k),
this.k=n.setInterval(function(a,h){h.ajax({url:a.a.refresh.url,success:function(g){try{var j=h.parseJSON(g)}catch(c){return}a.a.onRefresh(j)}})},1E3*this.a.refresh.interval,this,k);this.l=function(a){for(var h=0,g=0,j=1,c={e:[],h:0,g:0,c:5,d:3,j:0,m:0},f,k=/[*<>pq\[\]().-]|[nc]+!?/g,e={"[":"first","]":"last","<":"prev",">":"next",q:"left",p:"right","-":"fill",".":"leap"},b={};f=k.exec(a);)if(f=""+f,p===e[f])if("("===f)g=++h;else if(")"===f)g=0;else{if(j){if("*"===f)c.h=1,c.g=0;else if(c.h=0,c.g="!"===
f.charAt(f.length-1),c.c=f.length-c.g,!(c.d=1+f.indexOf("c")))c.d=1+c.c>>1;c.e[c.e.length]={f:"block",i:0,b:0};j=0}}else c.e[c.e.length]={f:e[f],i:g,b:p===b[f]?b[f]=1:++b[f]},"q"===f?++c.m:"p"===f&&++c.j;return c}(this.a.format);return this},setNumber:function(a){this.o=p===a||a<0?-1:a;return this},setPage:function(a){if(p===a){if(a=this.a.page,null===a)return this}else if(this.a.page==a)return this;this.a.page=a|=0;var l=this.o,h=this.a,g,j,c,f,n=1,e=this.l,b,d,i,m,o=e.e.length,q=o;h.perpage<=h.lapping&&
(h.lapping=h.perpage-1);m=l<=h.lapping?0:h.lapping|0;l<0?(c=l=-1,g=Math.max(1,a-e.d+1-m),j=g+e.c):(c=1+Math.ceil((l-h.perpage)/(h.perpage-m)),a=Math.max(1,Math.min(a<0?1+c+a:a,c)),e.h?(g=1,j=1+c,e.d=a,e.c=c):(g=Math.max(1,Math.min(a-e.d,c-e.c)+1),j=e.g?g+e.c:Math.min(g+e.c,1+c)));for(;q--;){d=0;i=e.e[q];switch(i.f){case "left":d=i.b<g;break;case "right":d=j<=c-e.j+i.b;break;case "first":d=e.d<a;break;case "last":d=e.c<e.d+c-a;break;case "prev":d=1<a;break;case "next":d=a<c}n|=d<<i.i}b={number:l,lapping:m,
pages:c,perpage:h.perpage,page:a,slice:[(d=a*(h.perpage-m)+m)-h.perpage,Math.min(d,l)]};for(f=k(document.createElement("div"));++q<o;){i=e.e[q];d=n>>i.i&1;switch(i.f){case "block":for(;g<j;++g)b.value=g,b.pos=1+e.c-j+g,b.active=g<=c||l<0,b.first=1===g,b.last=g==c&&0<l,d=document.createElement("div"),d.innerHTML=h.onFormat.call(b,i.f),k("a",d=k(d)).data("page",b.value).click(r),f.append(d.contents());continue;case "left":b.value=b.pos=i.b;b.active=i.b<g;break;case "right":b.value=c-e.j+i.b;b.pos=i.b;
b.active=j<=b.value;break;case "first":b.value=1;b.pos=i.b;b.active=d&&e.d<a;break;case "last":b.value=c;b.pos=i.b;b.active=d&&e.c<e.d+c-a;break;case "prev":b.value=Math.max(1,a-1);b.pos=i.b;b.active=d&&1<a;break;case "next":b.pos=i.b;(b.active=l<0)?b.value=1+a:(b.value=Math.min(1+a,c),b.active=d&&a<c);break;case "leap":case "fill":b.pos=i.b;b.active=d;f.append(h.onFormat.call(b,i.f));continue}b.last=b.first=p;d=document.createElement("div");d.innerHTML=h.onFormat.call(b,i.f);k("a",d=k(d)).data("page",
b.value).click(r);f.append(d.contents())}u.html(f.contents());this.n=h.onSelect.call({number:l,lapping:m,pages:c,slice:b.slice},a);return this}};return o.setNumber(s).setOptions(t).setPage()}})(jQuery,this);

//精度10m
var correct_pts = {};
correct_pts['hh'] = [

                   //20130729最新参考点

                   { j: 106.287494, w: 30.345093, utm_x: 0, utm_y: 0, x: 106.287390, y: 30.344014 },
                    { j: 106.287336, w: 30.347885, utm_x: 0, utm_y: 0, x: 106.290126, y: 30.346180 },
                     { j: 106.283367, w: 30.344955, utm_x: 0, utm_y: 0, x: 106.283527, y: 30.346501 },
                      { j: 106.289577, w: 30.340953, utm_x: 0, utm_y: 0, x: 106.285087, y: 30.339693 },
                       { j: 106.291069, w: 30.345771, utm_x: 0, utm_y: 0, x: 106.291313, y: 30.342315 },
                        { j: 106.282960, w: 30.341329, utm_x: 0, utm_y: 0, x: 106.279508, y: 30.344104 },
                         { j: 106.286098, w: 30.337433, utm_x: 0, utm_y: 0, x: 106.278363, y: 30.339294 },
{ j: 106.297964, w: 30.348508, utm_x: 0, utm_y: 0, x: 106.300306, y: 30.340049 },
                           { j: 106.295850, w: 30.342086, utm_x: 0, utm_y: 0, x: 106.291856, y: 30.336641 },
                           { j: 106.277658, w: 30.345055, utm_x: 0, utm_y: 0, x: 106.278526, y: 30.350127 },
                           { j: 106.283192, w: 30.334256, utm_x: 0, utm_y: 0, x: 106.272545, y: 30.338774 },
                           { j: 106.292784, w: 30.350689, utm_x: 0, utm_y: 0, x: 106.297851, y: 30.344866 },
                           { j: 106.291348, w: 30.353957, utm_x: 0, utm_y: 0, x: 106.299880, y: 30.348153 },
                           { j: 106.287014, w: 30.355851, utm_x: 0, utm_y: 0, x: 106.297937, y: 30.352211 },
                           { j: 106.286323, w: 30.351691, utm_x: 0, utm_y: 0, x: 106.293061, y: 30.349618 },
                           //20131024最新参考点
                           { j: 106.267141, w: 30.341900, utm_x: 0, utm_y: 0, x: 106.265862, y: 30.354336 },
                            { j: 106.274153, w: 30.344097, utm_x: 0, utm_y: 0, x: 106.274373, y: 30.351610 },
                             { j: 106.269808, w: 30.337980, utm_x: 0, utm_y: 0, x: 106.264248, y: 30.349813 },
                              { j: 106.276458, w: 30.336522, utm_x: 0, utm_y: 0, x: 106.268749, y: 30.344628 },
                               { j: 106.276873, w: 30.343291, utm_x: 0, utm_y: 0, x: 106.276009, y: 30.349323 },
                                { j: 106.281986, w: 30.349299, utm_x: 0, utm_y: 0, x: 106.286713, y: 30.350539 },
                                 { j: 106.284344, w: 30.353276, utm_x: 0, utm_y: 0, x: 106.292885, y: 30.352011 },
                                  { j: 106.285371, w: 30.363450, utm_x: 0, utm_y: 0, x: 106.304096, y: 30.358813 },
                                   { j: 106.289469, w: 30.361729, utm_x: 0, utm_y: 0, x: 106.306047, y: 30.355024 },
                                    { j: 106.291001, w: 30.355080, utm_x: 0, utm_y: 0, x: 106.300695, y: 30.349199 }
                           //{ j:  , w:  , utm_x: 0, utm_y: 0, x:   , y:   },
                           // { j:  , w:  , utm_x: 0, utm_y: 0, x:   , y:   },

];
var num = {};
num['hh'] = { num: Math.sin(Math.PI / 4), num2: Math.sin(Math.PI / 4) };

//得到需要转换坐标的类型
function getTypeP(type) {
    if (type == "jw")
        return { strX: 'j', strY: 'w', initValue: 180, minPreX: 0.00005, minPreY: 0.00005 };
    if (type == "utm")
        return { strX: 'utm_x', strY: 'utm_y', initValue: 1294723000, minPreX: 500, minPreY: 500 };
    if (type == "xy")
        return { strX: 'x', strY: 'y', initValue: 1000000000, minPreX: 500, minPreY: 500 };
}

//输入一个点，得到这个点最近却比较合理的两对点。
function getPx_Py(city, x, y, typeP) {
    var pointsIndex = getFourNearIndex(city, x, y, typeP);
    return get2PairPointsX_Y(city, pointsIndex, typeP);
}

//得到输入所有点中，比较合理的两对点。
//一对在X轴上，一对在Y轴上。
function get2PairPointsX_Y(city, pointsIndex, typeP) {
    var pointsX = {};
    var pointsY = {};
    var haveX = false;
    var haveY = false;
    var oldInstanceX = 0;
    var oldInstanceY = 0;
    for (var i = 0; i < pointsIndex.length; i++) {
        for (var j = i + 1; j < pointsIndex.length; j++) {
            var tempInstanceX_Y = getInstanceCommon2P(city, pointsIndex[i], pointsIndex[j], typeP);
            if (tempInstanceX_Y.Ix > oldInstanceX && !haveX) {
                oldInstanceX = tempInstanceX_Y.Ix;
                pointsX.index0 = pointsIndex[i];
                pointsX.index1 = pointsIndex[j];
            }
            if (tempInstanceX_Y.Ix > typeP.minPreX && !haveX) {
                haveX = true;
            }
            if (tempInstanceX_Y.Iy > oldInstanceY && !haveY) {
                oldInstanceY = tempInstanceX_Y.Iy;
                pointsY.index0 = pointsIndex[i];
                pointsY.index1 = pointsIndex[j];
            }
            if (tempInstanceX_Y.Iy > typeP.minPreY && !haveY) {
                haveY = true;
            }
            if (haveY && haveX) {
                return { px: pointsX, py: pointsY };
            }
        }
    }
    return { px: pointsX, py: pointsY };
}

//得到两个点在指定坐标上的距离，兼容了25DMap的点。
function getInstanceCommon2P(city, index0, index1, typeP) {
    if (typeP.strX == "x" && typeP.strY == "y") {
        return getInstance2P_XY(city, index0, index1);
    }
    else {
        return getInstance2P(city, index0, index1, typeP);
    }
}

//得到25DMap两个点在X轴和Y轴上的距离。
function getInstance2P_XY(city, index0, index1) {
    var px_1 = fromMap(city, correct_pts[city][index0].x, correct_pts[city][index0].y);
    var px_2 = fromMap(city, correct_pts[city][index1].x, correct_pts[city][index1].y);
    var instanceX = Math.abs(px_1.x - px_2.x);
    var instanceY = Math.abs(px_1.y - px_2.y);
    return { Ix: instanceX, Iy: instanceY };
}
//得到两个点在指定坐标上的距离。
function getInstance2P(city, index0, index1, typeP) {
    var instanceX = Math.abs(correct_pts[city][index0][typeP.strX] - correct_pts[city][index1][typeP.strX]);
    var instanceY = Math.abs(correct_pts[city][index0][typeP.strY] - correct_pts[city][index1][typeP.strY]);
    return { Ix: instanceX, Iy: instanceY };
}

//得到指定点最近的四个点
function getFourNearIndex(city, x, y, typeP) {
    var p1 = 0;
    var p2 = 0;
    var p3 = 0;
    var p4 = 0;
    var minDis = typeP.initValue, secMinDis = typeP.initValue, thrMinDis = typeP.initValue, fouMinDis = typeP.initValue;
    for (var i = 0; i < correct_pts[city].length; i++) {
        var dis = getDis(correct_pts[city][i][typeP.strX], correct_pts[city][i][typeP.strY], x, y);
        if (dis < minDis) {
            fouMinDis = thrMinDis;
            thrMinDis = secMinDis;
            secMinDis = minDis;
            minDis = dis;
            p4 = p3;
            p3 = p2;
            p2 = p1;
            p1 = i;
        }
        else if (dis > minDis && dis < secMinDis) {
            fouMinDis = thrMinDis;
            thrMinDis = secMinDis;
            sedMinDis = dis;
            p4 = p3;
            p3 = p2;
            p2 = i;
        }
        else if (dis > secMinDis && dis < thrMinDis) {
            fouMinDis = thrMinDis;
            thrMinDis = dis;
            p4 = p3;
            p3 = i;
        }
        else if (dis > thrMinDis && dis < fouMinDis) {
            fouMinDis = dis;
            p4 = i;
        }
    }
    return new Array(p1, p2, p3, p4);
}

//计算坐标之间的距离。
function getDis(x, y, x1, y1) {
    return Math.abs(x - x1) + Math.abs(y - y1);
}
//从标准平面坐标得到地图坐标
function toMap(city, x, y) {
    var x2 = (x - y) * num[city].num;
    var y2 = (x + y) * num[city].num * num[city].num2;

    return { x: x2, y: y2 };
}
//从地图坐标得到标准平面坐标
function fromMap(city, x, y) {
    y = y / num[city].num2;
    var x2 = (x + y) / (num[city].num * 2);
    var y2 = (y - x) / (num[city].num * 2);

    return { x: x2, y: y2 };
}

//得到小范围地图精度
function getDgPix_mm(city, index0, index1) {

    var px_1 = fromMap(city, correct_pts[city][index0].x, correct_pts[city][index0].y);
    var px_2 = fromMap(city, correct_pts[city][index1].x, correct_pts[city][index1].y);

    var x_1 = px_1.x, y_1 = px_1.y;
    var x_2 = px_2.x, y_2 = px_2.y;

    var dj1 = correct_pts[city][index0].utm_x, dw1 = correct_pts[city][index0].utm_y;
    var dj2 = correct_pts[city][index1].utm_x, dw2 = correct_pts[city][index1].utm_y;

    var a = Math.abs((dj2 - dj1) * 100000 / (x_2 - x_1));
    var b = Math.abs((dw2 - dw1) * 100000 / (y_2 - y_1));
    //a,b每十万像素对应的经纬度
    return { j: a, w: b, x: 100000 / a, y: 100000 / b };
}

//得到小范围地图精度
function getDgPix_mm_25DMap(city, indexX0, indexX1, indexY0, indexY1, typeP) {
    var px_1 = fromMap(city, correct_pts[city][indexX0].x, correct_pts[city][indexX0].y);
    var px_2 = fromMap(city, correct_pts[city][indexX1].x, correct_pts[city][indexX1].y);
    var py_1 = fromMap(city, correct_pts[city][indexY0].x, correct_pts[city][indexY0].y);
    var py_2 = fromMap(city, correct_pts[city][indexY1].x, correct_pts[city][indexY1].y);

    var x_1 = px_1.x, x_2 = px_2.x;
    var y_1 = py_1.y, y_2 = py_2.y;

    var dj1 = correct_pts[city][indexX0][typeP.strX], dj2 = correct_pts[city][indexX1][typeP.strX];
    var dw1 = correct_pts[city][indexY0][typeP.strY], dw2 = correct_pts[city][indexY1][typeP.strY];

    var a = Math.abs((dj2 - dj1) * 100000 / (x_2 - x_1));
    var b = Math.abs((dw2 - dw1) * 100000 / (y_2 - y_1));
    //a,b每十万像素对应的经纬度
    return { j: a, w: b, x: 100000 / a, y: 100000 / b };
}

//从经纬度得到地图像素值,如需将地图坐标转换成经纬则反过来算即可
//小范围内地图满足线性关系
function getPx_mm(city, utm_x, utm_y, px, py, typeP) {
    var px_src = correct_pts[city][px.index0];
    var gp_src = correct_pts[city][px.index0];
    var dgPix = getDgPix_mm_25DMap(city, px.index0, px.index1, py.index0, py.index1, typeP);
    var px_1 = fromMap(city, px_src.x, px_src.y);
    var dj1 = gp_src[typeP.strX], dw1 = gp_src[typeP.strY];
    var dj = utm_x, dw = utm_y;

    var x_1 = px_1.x;
    var y_1 = px_1.y;

    var dj_s = dj - dj1, dw_s = dw - dw1;

    var x = dj_s * dgPix.x + x_1;
    var y = -dw_s * dgPix.y + y_1;

    var r = toMap(city, x, y);
    return r;
}
function getJw_mm(city, x, y, px1, py1, typeP) {
    var mappx_src = correct_pts[city][px1.index0];
    var gp_src = correct_pts[city][px1.index0];
    var dgPix = getDgPix_mm_25DMap(city, px1.index0, px1.index1, py1.index0, py1.index1, typeP);

    var px = fromMap(city, x, y);
    var px_src = fromMap(city, mappx_src.x, mappx_src.y);
    //var dj1=gp_src.utm_x,dw1=gp_src.utm_y;
    var dj1 = gp_src[typeP.strX], dw1 = gp_src[typeP.strY];

    var x_1 = px_src.x;
    var y_1 = px_src.y;

    var px_s = px.x - x_1, py_s = y_1 - px.y;

    var gp_j = px_s / dgPix.x + dj1;
    var gp_w = py_s / dgPix.y + dw1;
    return { lng: gp_j, lat: gp_w };
}

//接口函数
function get25DMap_pts(city, pts) {
    if (!pts.px || !pts.py) {
        var typeP = getTypeP("utm");
        var twoIndexs = getPx_Py(city, pts.x, pts.y, typeP);
        pts.px = twoIndexs.px;
        pts.py = twoIndexs.py;
    }
    return get25DMap_index(city, pts.utm_x, pts.utm_y, pts.px, pts.py);
}

//接口函数
function getMapJw_pts(city, pts) {
    if (!pts.px || !pts.py) {
        var typeP = getTypeP("xy");
        var twoIndexs = getPx_Py(city, pts.x, pts.y, typeP);
        pts.px = twoIndexs.px;
        pts.py = twoIndexs.py;
    }
    return getMapJw_index(city, pts.x, pts.y, pts.px, pts.py);
}
function get25DMap_index(city, utm_x, utm_y, px0, py0, typeP) {
    var xy = getPx_mm(city, utm_x, utm_y, px0, py0, typeP);
    return { x: xy.x, y: xy.y, px: px0, py: py0 };
}
function getMapJw_index(city, x, y, px0, py0, typeP) {
    var lnglat = getJw_mm(city, x, y, px0, py0, typeP);
    return { lng: lnglat.lng, lat: lnglat.lat, px: px0, py: py0 };
}
function getMapJw_Array(city, pts) {
    var typeP = getTypeP("xy");
    var twoIndexs = getPx_Py(city, pts[0].x, pts[0].y, typeP);
    //墨卡托坐标坐标的转换
    var typeP1 = getTypeP("utm");
    var lnglat = new Array;
    for (var i = 0; i < pts.length; i++) {
        var tmp = getJw_mm(city, pts[i].x, pts[i].y, twoIndexs.px, twoIndexs.py, typeP1);
        lnglat[i] = { lng: tmp.lng, lat: tmp.lat, px: twoIndexs.px, py: twoIndexs.py };
    }
    return lnglat;
}
function get25DMap_Array(city, pts) {
    var typeP = getTypeP("utm");
    var twoIndexs = getPx_Py(city, pts[0].utm_x, pts[0].utm_y, typeP);
    var xy = new Array;
    for (var i = 0; i < pts.length; i++) {
        var tmp = getPx_mm(city, pts[i].utm_x, pts[i].utm_y, twoIndexs.px, twoIndexs.py, typeP);
        xy[i] = { lng: tmp.x, lat: tmp.y, px: twoIndexs.px, py: twoIndexs.py };
    }
    return xy;
}
//25D坐标得到经纬度
function getMapJw(city, x, y) {
    y = -y;
    var typeP = getTypeP("xy");
    var twoIndexs = getPx_Py(city, x, y, typeP);
    var typeP1 = getTypeP("jw");
    return getMapJw_index(city, x, y, twoIndexs.px, twoIndexs.py, typeP1);
}
//经纬度得到25D坐标
function get25DMap(city, j, w) {
    var typeP = getTypeP("jw");
    var twoIndexs = getPx_Py(city, j, w, typeP);
    var pt = get25DMap_index(city, j, w, twoIndexs.px, twoIndexs.py, typeP);
    pt.y = -pt.y;
    return pt;
}
//UTM坐标得到25D坐标
function get25DMap_mm(city, utm_x, utm_y) {
    var typeP = getTypeP("utm");
    var twoIndexs = getPx_Py(city, utm_x, utm_y, typeP);
    return get25DMap_index(city, utm_x, utm_y, twoIndexs.px, twoIndexs.py, typeP);
}
//25D坐标得到UTM坐标
function getMapJw_mm(city, x, y) {
    var typeP = getTypeP("xy");
    var twoIndexs = getPx_Py(city, x, y, typeP);
    var typeP1 = getTypeP("utm");
    return getMapJw_index(city, x, y, twoIndexs.px, twoIndexs.py, typeP1);
}
function parsePoints() {
    for (var item in correct_pts) {
        var arr = correct_pts[item];
        for (var i = 0; i < arr.length; i++) {
            arr[i].y = -arr[i].y;
        }
    }
}
parsePoints();

;(function ($) {
	$.fn.primaryMemberComplete = function(option){
		var self=$(this);
		var defaultOption={
			postDataFunction	: function(){},
			loadingImg  : RESOURCE_PATH+'/resource/external/selectInPlace/ajax-loader.gif',
			loadingText : "正在加载...",
			multiple	: true,
			url			: "",
			dynamic		: true,
			param 		: "tag",
			height      : "auto",
			width		: "auto",
			htmlMethod	: function (item){
				var detailName=item.detailName;
				if(detailName==null||detailName==undefined){detailName="";}
				return '<li style="width:'+this.width+'px">'+'<div style="padding:0 0 0 0px" class="title">'+item.detailName+'【'+item.displayName+'】'+'</div><div>&nbsp;'+'</div></li>';
			},
			fullMessage : "不能选择更多...",
			valueMethod : function getValue(item){
		    	return item.organizationId;
		    },
			max 		: 20,
			select:function(data){},
			itemClose   : function(data){},
			labelMethod : function(elem){
		    	defaultOption.select($(elem).data('data'));
		    	var itemHtml='<LI class="bit-box">'+elem.children(".title").text()+'<A class="closebutton" href="#"></A>';
		    	return itemHtml;
		    }
		};
		$.extend(defaultOption,option);
		$(this).selectInPlace(defaultOption);
		if(defaultOption.height!="auto"){
			$("#holder_"+self.attr("id")).css({"height":defaultOption.height+"px","overflow":"hidden","overflow-y":"auto"});
		};
	};
	$.fn.setPrimaryMemberCompleteVal = function(option){
		var htmlObj = $('<li class="bit-box">'+option.detailName+'<a class="closebutton" href="#"></a></li>').data('data',option.organizationId);
		var value=$(this).val();
		if(value!=""){
			$(this).val(option.organizationId+","+value);
		}else{
			$(this).val(option.organizationId);
		}
		$(this).next().prepend(htmlObj);
	};
	$.fn.resetPrimaryMemberCompleteVal = function(option){
		var htmlObj = $('<li class="bit-box">'+option.detailName+'<a class="closebutton" href="#"></a></li>').data('data',option.organizationId);
		var value=$(this).val();
		$(this).val(option.organizationId);
		$(this).next().children(".bit-box").remove();
		$(this).next().prepend(htmlObj);
	};
	$.fn.clearPrimaryMemberComplete = function(){
		var value=$(this).val();
		$(this).val("");
		$(this).next().children(".bit-box").remove();
	};
	$.fn.removePrimaryMemberCompeleteVal = function(option){
		this.next().children(":contains('"+option.orgName+"')").remove();
		var value = String($(this).val());
		var temp = value.substring(value.indexOf(option.organizationId));
		if(value.indexOf(',')!=-1 && temp.indexOf(',') != -1){
			var last = temp.substring(value.indexOf(',')+1);
			value = value.substring(0, value.indexOf(option.organizationId)).concat(last);
			$(this).val(value);
		}else if(value.indexOf(',')!=-1 && temp.indexOf(',') == -1){
			$(this).val(value.substring(0, value.indexOf(option.organizationId)-1));
		}else{
			$(this).val("");
		}
	};
	$.fn.removePrimaryMemberCompeleteValue = function(val){
		var self=$(this);
		var valueArr=new Array()
		var thisValue=self.val();
		var newValue="";
		var thisId=val;
		var valueArr=thisValue.split(",");
		var thisIndex=-1;
		for(var i=0;i<valueArr.length;i++){
			if(thisId==valueArr[i]){
				thisIndex=i;
				continue;
			};
			newValue=newValue+valueArr[i]+",";
		};
		self.val(newValue.substring(0,newValue.length-1));
		self.next().children(".bit-box:eq("+thisIndex+")").remove();
	};
})(jQuery);

/*
 * 根据身份证号码获取性别
 * idCardNo 身份证号码
 * dictFieldName字典项的id名称
 * fieldName隐藏域的 id名称
 * isDict 是否是字典项
*/
function fillGenderByIdCardNo(idCardNo,dictFieldName,fieldName,isDict){
    var sex;
    if(idCardNo==null||idCardNo=="" || typeof(idCardNo)=="undefined"){
        return;
    }
    if(idCardNo.length!=15 && idCardNo.length!=18){
        return;
    }

    if (15 == idCardNo.length) { //15位身份证号码
        if (parseInt(idCardNo.charAt(14) / 2) * 2 != idCardNo.charAt(14))
            sex = '男';
        else
            sex = '女';
     }
    if (18 == idCardNo.length) { //18位身份证号码
        if (parseInt(idCardNo.charAt(16) / 2) * 2 != idCardNo.charAt(16))
           sex = '男';
        else
          sex = '女';
    }
    showGender(sex,dictFieldName,fieldName,isDict);
}

function showGender(gender,dictFieldName,fieldName,isDict){
    if(isDict){
        $("#"+dictFieldName + " option").each(function(value){
            if($(this).text()==gender)  {
                $(this).attr("selected",true);
                $("#"+fieldName).val($(this).val())
            }
        });
    }
}
;$(function(){
	$("#topMenu .sysMenuList").width($(window).width()-410)
    var resize = true;

    // 显示隐藏的导航菜单
    var includeOtherMenuItems =  function(){
        var hand = $('#showOtherMenuItem');
            otherMenuBox = $("#otherMenuItems dl"),
            menuBox = $("#topMenu .sysMenuList"),
            num;
            if( $("#sysMenuList dd").find("a").eq(0).css("font-size")=="16px"){ 
            	num = Math.floor( (menuBox.width()-120) / menuBox.find('dd:last').width())
            }else if($("#sysMenuList dd").find("a").eq(0).css("font-size")=="14px"){
            	num = Math.floor( (menuBox.width()-160) / menuBox.find('dd:last').width())
            }else if($("#sysMenuList dd").find("a").eq(0).css("font-size")=="12px"){
            	num = Math.floor( (menuBox.width()-210) / menuBox.find('dd:last').width())
            }
        if( num < menuBox.find('dd').length ){        	
            otherMenuBox.empty().append(
                menuBox.find('dd:gt('+(num-1)+')').clone()
            )            
            hand.removeClass('lockout')
            $(".showOtherMenuItem a").removeClass('noback');
            return true;
        }else{
        	//$(".showOtherMenuItem a").css("background","none");
        	$(".showOtherMenuItem a").addClass('noback');
            hand.addClass('lockout')
            return false;
        }
    }

    // 计算布局高度
    // 布局优化方案
	
    function resetLayout(){
		includeOtherMenuItems()
	    var mainPartHeight = document.documentElement.clientHeight-$(".ui-layout-north").outerHeight(true)-$(".ui-layout-south:visible").outerHeight(true);
    	$('#rdMap').height( mainPartHeight- $('#TaskWrap').height()- 55 );

    	if( document.documentElement.clientWidth < 1100 ){
    		$('#newsColumn').appendTo('#mainView .mainViewInner');
    		$('#newsColumn').css({'overflow':'auto'});
    	}else{
    		$('#newsColumn').prependTo('#mainPart');
    		$('#newsColumn').css({'overflow':'visible'});
    	}
    }
    var timer;
    if($('#product-menu').is(':visible')){
		resetLayout()
    }
    $(window).resize(function(){
    	$("#topMenu .sysMenuList").width($(window).width()-410)
        resize =true;
        clearTimeout(timer);
        timer=setTimeout(function(){ resetLayout()},200);
    })


    function pulldownFun(option){
    	var dfop={
    		onHover:"on"
    	}
        var op=this.op=$.extend(dfop,option||{});
        var on  = $(op.on),
            dlg = $(op.dialog),
            onHov=op.onHover,
            mouseover_tid = [],
            i   = 0;
        
        on.hover(
            function(){
                if(op.offset){
                    var offset=on.offset();
                    dlg.css({'top':offset.top+(+op.offset),'left':offset.left-(+op.offset-13)})
                }

                if(resize && op.resize){
                    // 执行填充方法 如果没有隐藏的项 那么结束
                    var theFun = includeOtherMenuItems()
                    if(!theFun){return false;}
                }
                dlg.slideDown(150)
                clearTimeout(mouseover_tid[i]);
                $(op.on).addClass(onHov);
            }, function(){
                mouseover_tid[i] = setTimeout(function(){
                    dlg.slideUp(150)
                },200)
                $(op.on).removeClass(onHov);
            }
        )
        dlg.hover(
            function(){
                clearTimeout(mouseover_tid[i]);
                $(op.on).addClass(onHov);
            }, function(){
                mouseover_tid[i] = setTimeout(function(){
                    dlg.slideUp(150)
                },200)
                $(op.on).removeClass(onHov);
            }
        )
    }
    

    new pulldownFun({
        resize:true,
        on:"#showOtherMenuItem",
        dialog:"#otherMenuItems",
        onHover:"onHover"
    });
    

    //scroll
    var itemWidth=$(".scrollpos .normal").width()+10;
	var sumWidth=itemWidth*$(".scrollpos .normal").length;
	var temp=$(".scrollpos > span").clone();
	temp.appendTo(".scrollpos");
	$(".scrollpos").width(sumWidth);
	var i=0;
	setInterval(play,50);
	function play(){
		$(".scrollpos").css("left",i)
		i-=1;
		if(Math.abs(parseInt($(".scrollpos").css("left")))>=sumWidth){
			i=0;
		}
	}
	
	
})
$(function(){
	/*height*/
	setTimeout(function(){
		var cheight = $('.ui-layout-center').outerHeight()
		$('.workbenchMain').height(cheight-8)
	},1000)
	/*btnhover*/
	$("#portlet-task").find("li").hover(function(){
		$(this).css("background","#f2f2f2");
		$(this).find(".lookNew").addClass("btnHover")	
	},function(){
		$(this).css("background","#fff");
		$(this).find(".lookNew").removeClass("btnHover")	
	})
	function spewidth(){
		var wwidth = $(window).width()
		if( wwidth <= 1024){
			$('.portlet-task .tasklist li .isNew').addClass('spewidth')
		}else{
			$('.portlet-task .tasklist li .isNew').removeClass('spewidth')
		}
	}
	
	/*scrollnes*/
	$(function(){
        var $this = $(".scrollNews");
        var scrollTimer;
        $this.hover(function(){
              clearInterval(scrollTimer);
         },function(){
           scrollTimer = setInterval(function(){
                         scrollNews( $this );
                    }, 4000 );
        }).trigger("mouseout");
	});
	function scrollNews(){
	   var $self = $(".scrollNews").find("ul");
	   var lineHeight = $self.find("li:first").height();
	   $self.animate({ "margin-top" : -lineHeight +"px" },500 , function(){
			 $self.css({"margin-top":"0px"}).find("li:first").appendTo($self);
	   		})
	}	
	/*tabs*/
	function tabsClick(on,cont){
        var on = $(on), cont = $(cont)
        on.click(function(){
            var index = $(this).index()
			on.removeClass('on');
            $(this).addClass('on');
            cont.eq(index).show().siblings().hide()
        })
	}
	new tabsClick('.portlet-tab .tab a','.portlet-tab .tabcont')
})
	
	