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