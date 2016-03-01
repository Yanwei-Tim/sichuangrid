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
	
	