$.fn.alpha = function() {
	//初始化背景图
	if($.cookie("selectBg")!=null){
		$("#wallpaper").attr("src",$.cookie("selectBg"));
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
		var index=$(this).index()+1;
		var left=$("#desktop"+index).css("left");
		$(this).addClass("desk_current").siblings().removeClass("desk_current");
		$(".desktop").hide();
		$("#desktop"+index).css({opacity:0,left:700}).animate({opacity:1,left:left},500).show();
	});
	
	//初始化
	var icons=$("#desktop").initicos();
	$("#indicatorContainer li:first").click();
	
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
		var skinDialog = $.deskDialog({
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
		var skinDialog = $.deskDialog({
		    id:"messageBoxDlg",
		    title: '消息盒子',
		    url:'/desktop/system/messageBox.jsp'
		});
	});
	
	//最近使用
	$("#leastRecentlyUsed").leastRecentlyUsed();
	
	//模块设置
	$("#toolBox").on("click",function(){
		$.addIconDlg();
	})
	
	//选择桌面背景
	$("#selectSkin").on("click",function(){
		$.selectBg();
	});
	
	//系统设置
	$("#setting").on("click",function(){
		var skinDialog = $.deskDialog({
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
	/*$.ajax({//登陆界面
		url:'/desktop/system/login.jsp',
		success:function(html){
			$("body").append(html);
		}
	})*/
	
	$("body").on("click",function(){
		$(".childrenBox").each(function(){
			if($(this).css("display")=='block'){
				$(this).hide();
			}
		})
	})
	window.onbeforeunload=function(e){//刷新　关闭等操作提示
   		return (e || window.event).returnValue='点击[确定]系统将刷新，所有打开窗口将被关闭，请检查数据是否已经保存？';
    }
    //时钟天气
    $("#messagesList").load("/desktop/system/messageList.jsp");
    $("#clockWeather").jdigiclock();
};