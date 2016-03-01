$(function(){
    function showSlide(){
        var $handle = $('#handle'),
            $content = $('body');

        $handle.on('click',function(){
            $content.toggleClass('fullLayout')
        })

    }

    function resetHeight(){

        function layoutFun(){
            var documentHeight=document.documentElement.clientHeight;
            $('#content').height(documentHeight - $('#header').outerHeight());
            $('#slideBar').css({'height':'100%'});
            $('#mainBody').css({'height':documentHeight- $('#header').outerHeight() -$('#mCrumbs').height() -10});
        }

        layoutFun();
        $(window).resize(function(){
            clearTimeout(window._layoutTimer);
            window._layoutTimer=setTimeout(function(){
                layoutFun();
            },300);
        });
    }


    // 左侧栏收缩展开
    showSlide();
    // 计算高度
    resetHeight();
});