seajs.config({
	base: '/resource/desktop/js',
	alias: {
	}
});

define(function(require, exports, module) {
	require.async([
		'./jquery.desktop.js'
		],
		function(c, d) {
			$(function(){
				//登陆界面
				$("#loginPanel").load('/desktop/system/login.jsp');
				
				$("#desk_module").load("/desktop/system/oftenUseFunction.jsp",function(){
						$("#commonUseFun").on("click",function(){
							$("#desk_module").show();
							$("#wrapper").append("<div class='loadingmask'></div>")
						})
					}
				);
				$("#bar_bottom").fadeIn(1500);
				$("#wrapper").fadeIn(1500);
				//初始化背景图
				if($.cookie("selectBg")!=null){
					$("#wallpaper").attr("src",$.cookie("selectBg"));
				}else{
					$("#wallpaper").attr("src",'/resource/desktop/images/skins/bg/big/wood1.jpg');
				}
				
				$(".desk_area").hover(function(){
					$(this).addClass("pulse");
				},function(){
					$(this).removeClass("pulse");
				})
				
				//桌面图标
				var dlgWidth=document.documentElement.clientWidth;
				var dlgHeight=document.documentElement.clientHeight-100;
				var dW=document.documentElement.clientWidth-240,dH=dlgHeight;
				
				$("#indicatorContainer li").on("click",function(){
					if($(this).hasClass("desk_current")){return false;}
					var index=$(this).index()+1;
					var curIndex=$("#indicatorContainer li.desk_current:first").index()+1;
					var left=$("#desktop"+index).css("left");
					$(this).addClass("desk_current").siblings().removeClass("desk_current");
					$(".desktop").hide();
					if ($.browser.msie) {
					   $("#desktop"+index).show();
					}
					else{
						$("#desktop"+index).css({opacity:0,left:700}).animate({opacity:1,left:100},300).show();
					}
				});
				
				//初始化
				/*$.ajax({
					url:'/desktopMenu/desktopMenuManage/findDesktopMenusByUserId.action',
					type:'get',
					success:function(data){*/
						//var option={icos:data};
						var icons=$("#desktop").initicos();
				/*	}
				})*/
				$("#indicatorContainer li:first").click();
				
				//全局视图事件
				$("#globalView").one("click",function(){
					$("#wrapper").hide();
					var globalView=$("<div />").addClass("globalView");
					$("body").append(globalView);
					globalView.load("/desktop/system/globalView.jsp",function(){
						$(".globalView_close").on("click",function(){
					    	$(".globalView").hide();
					    	$("#wrapper").show();
					    	$(".desktopGlobalModule").removeClass("globalViewItemTurn")
					    	$("#globalView").on("click",function(){
					    		$("#wrapper").hide();
					    		$(".globalView").show();
					    		setTimeout(function(){
							    	$(".desktopGlobalModule").addClass("globalViewItemTurn")
							    },50)
					    	})
					    })
					    setTimeout(function(){
					    	$(".desktopGlobalModule").addClass("globalViewItemTurn")
					    },50)
					})
				})
				
				/*$("#gisView").one("click",function(){
					$("#wrapper,#wallpaper").hide();
					var globalView=$("<iframe />");
					$("body").append(globalView).append("<a href='javascript:;' class='gisView_close'></a>");
					globalView.attr("src","/gis/gisView.jsp").css({
						width:"100%",
						height:"100%"
					});
					$(".gisView_close").on("click",function(){
				    	$(".gisView").hide();
				    	$("#wrapper,#wallpaper").show();
				    	$("#gisView").on("click",function(){
				    		$("#wrapper,#wallpaper").hide();
				    		$(".gisView").fadeIn();
				    	})
				    })
				})*/
				
				//显示桌面
				$("#show_desktop").on("click",function(){
					var list = $.dialog.list;
					for( var i in list ){
					    list[i].hide();
					    $("#dock"+i).attr("dlgshow",'false');
					     $("#dock"+i).removeClass('selectCur');
					}
				})
				
				/* 右下角弹出
				var dlgTimer;
				art.dialog({
				    content: '欢迎提示欢迎提示欢迎提示欢迎提示欢迎提示<br />欢迎提示欢迎提示欢迎提示..',
				    init: function () {
				    	var that = this, i = 3;
				        var fn = function () {
				            that.title(i + '秒后关闭');
				            !i && that.close();
				            i --;
				        };
				        timer = setInterval(fn, 1000);
				        fn();
				    },
				    id: 'msg',
				    width: 320,
				    height: 100,
				    padding:0,
				    left: '100%',
				    top: '100%',
				    fixed: true,
				    drag: false,
				    resize: false,
				    close: function () {
				    	clearInterval(timer);
				    }
				}).show(); 
				*/
				
				//用户切换
				$("#userChange").on("click",function(){
					var skinDialog = $.sysDialog({
					    id:"userChangeDlg",
					    title: '用户切换',
					    width:320,
					    height:100,
					    url:'/desktop/system/modifyRoleDlg.jsp',
					    close:function(){
					    	$(".tip-error").remove();
					    	$(".tip-yellow").remove();
					    }
					});
				});
				
				//消息盒子
				$("#messageBox").on("click",function(){
					var skinDialog = $.sysDialog({
					    id:"messageBoxDlg",
					    title: '消息盒子',
					    url:'/desktop/system/messageBox.jsp'
					});
				});
				
				//最近使用
				$("#leastRecentlyUsed").leastRecentlyUsed();
				
				//工具箱
				$("#toolsBox").on("click",function(){
					var skinDialog = $.sysDialog({
					    id:"toolsBoxDlg",
					    title: '工具箱',
					    url:'/desktop/system/systemTools.jsp'
					});
				})
				
				//模块设置
				$("#modelsBox").on("click",function(){
					$.addIconDlg();
				})
				
				//选择桌面背景
				$("#selectSkin").on("click",function(){
					$.selectBg();
				});
				
				//系统设置
				$("#setting").on("click",function(){
					var skinDialog = $.sysDialog({
					    id:"settingDlg",
					    title: '系统设置',
					    url:'/desktop/system/setting.jsp',
					    width:800,
					    height:400,
					    content: ''
					});
				})
				//全局搜索
				$("#globalSearch").on("click",function(){
					$(".desk_search").show();
				})
				
				$("body").on("click",function(){
					$(".childrenBox").each(function(){
						if($(this).css("display")=='block'){
							$(this).hide();
						}
					})
				})
				/*try
				{
	  				document.body.onbeforeunload=function(e){//刷新　关闭等操作提示
			   		return (e || window.event).returnValue='点击[确定]系统将刷新，所有打开窗口将被关闭，请检查数据是否已经保存？';
			    	}
			    }
				catch(e){}*/
			    $.miniTools();
			})
			
		});
});
