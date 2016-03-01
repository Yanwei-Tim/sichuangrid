$.fn.buildChart = function(params,eventCallback){
    var selfId = $(this).selector.substr(1);
    if( !$(this)[0] ){ return false; }
    function callback(ec, defaultTheme){
        var op = {
            tooltip : {},
            legend: {},
            toolbox: {
                show : false
            },
            calculable : false,
            series : []
        };


        var chart = ec.init(document.getElementById(selfId), defaultTheme);
        var option = $.extend({},op,params);
        chart.setOption(option);

        if($.isFunction(eventCallback)){
            chart.on(require('echarts/config').EVENT.CLICK, eventCallback(chart));
        }
    }

    require.config({
        paths: {
            echarts: RESOURCE_PATH+'/resource/judgmentAnalysis/js/echarts/build/dist'
        }
    });
    require(
        [
            'echarts',
            'echarts/theme/macarons',
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/scatter',
            'echarts/chart/k',
            'echarts/chart/pie',
            'echarts/chart/radar',
            'echarts/chart/force',
            'echarts/chart/chord',
            'echarts/chart/gauge',
            'echarts/chart/funnel',
            'echarts/chart/eventRiver'
        ],
        callback
    )
};

$.resetHeight = function(){
    function layoutFun(){
        var documentHeight=document.documentElement.clientHeight;
        $('#content').height(documentHeight - $('#header').outerHeight());
        $('#mainBody').css({'height':documentHeight- $('#header').outerHeight() -$('#mCrumbs').height()-10});
        $('#slideBar').css({'height':documentHeight- $('#header').outerHeight()});
    }

    layoutFun();
    $(window).resize(function(){
        clearTimeout(window._layoutTimer);
        window._layoutTimer=setTimeout(function(){
            layoutFun();
        },300);
    });
	
};
$.fn.serializeJson=function(){ 
    var serializeObj={}; 
    var array= this.serializeArray(); 
    var str= this.serialize(); 
    $(array).each(function(){ 
        if(serializeObj[this.name]){ 
            if($.isArray(serializeObj[this.name])){ 
                serializeObj[ this.name].push(this.value); 
            } else{ 
                serializeObj[this.name]=[serializeObj[this.name],this.value]; 
            } 
        } else{ 
            serializeObj[ this.name]=this .value;  
        } 
    }); 
    return serializeObj; 
};
