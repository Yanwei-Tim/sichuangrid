$(function(){
    var settings = {
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['排查数','调处数','成功数']
        },
        toolbox: {
            show : false
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : function (){
                    var list = [];
                    for (var i = 1; i <= 12; i++) {
                        list.push(i+'月');
                    }
                    return list;
                }()
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'排查数',
                type:'line',
                data:function (){
                    var list = [];
                    for (var i = 1; i <= 300; i++) {
                        list.push(Math.round(Math.random()* 300));
                    }
                    return list;
                }()
            },
            {
                name:'调处数',
                type:'line',
                data:function (){
                    var list = [];
                    for (var i = 1; i <= 30; i++) {
                        list.push(Math.round(Math.random()* 30));
                    }
                    return list;
                }()
            },
            {
                name:'成功数',
                type:'line',
                data:function (){
                    var list = [];
                    for (var i = 1; i <= 900; i++) {
                        list.push(Math.round(Math.random()* 900));
                    }
                    return list;
                }()
            }
        ]
    };

    $('#dataTrends').buildChart(settings);

});