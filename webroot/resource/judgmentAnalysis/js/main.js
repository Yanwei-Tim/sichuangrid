var PATH = '';//域名或IP
var RESOURCEPATH = '';//资源库前缀
var OAPPID = '';
var ver="1.0";//版本号
var mode="developer";//online
var basePath=RESOURCE_PATH+"/resource/judgmentAnalysis/js/";
var jqwidgets=RESOURCE_PATH+"/resource/judgmentAnalysis/js/jqwidgets/";
var script={
	base:[
		basePath+"jquery.base.js"
	],

	widget:[
		jqwidgets+"jqxcore.js",
		jqwidgets+"jqxdata.js",
		jqwidgets+"jqxbuttons.js",
		jqwidgets+"jqxscrollbar.js",
		jqwidgets+"jqxgrid.js",
		jqwidgets+"jqxgrid.selection.js",
		jqwidgets+"jqxgrid.columnsresize.js",
		jqwidgets+"jqxgrid.pager.js",
		jqwidgets+"jqxdatatable.js",
		jqwidgets+"jqxtreegrid.js",

		basePath+"scripts/demos.js",
		basePath+"echarts/echarts.js",

		basePath+"jqueryui.js",
		basePath+"dialog.js"

	],
	mod:[
		basePath+"widget.js",
		basePath+"base.js",
		basePath+"chartPage.js",
		basePath+"disputesAnalysis.js"
	]
};

// 添加版本号
for(resource in script){
	for(var i=0,len=script[resource].length;i<len;i++){
		script[resource][i]=script[resource][i]+"?ver="+ver;
	}
}

//加载基础库
if(mode!="online"){
	head.load(script.base,function(){
			//组件相关
		head.load(script.widget,function(){
            //框架模块
			head.load(script.mod,function(){
			})
		})
	});
}else{
	head.load(script.base,function(){
		//组件相关
		head.load(["resource/libs/widget.min.js?ver="+ver,"resource/libs/mod.min.js?ver="+ver],function(){
			angular.bootstrap(document, ['mainModule']);
		});

	});
}