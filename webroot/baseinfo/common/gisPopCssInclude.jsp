<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
body{background:#fff;}
/*tabs*/
.ui-helper-hidden{display:none}
.ui-helper-hidden-accessible{border:0;clip:rect(0 0 0 0);height:1px;margin:-1px;overflow:hidden;padding:0;position:absolute;width:1px}
.ui-helper-reset{margin:0;padding:0;border:0;outline:0;line-height:1.3;text-decoration:none;font-size:100%;list-style:none}
.ui-helper-clearfix:before,.ui-helper-clearfix:after{content:"";display:table}
.ui-helper-clearfix:after{clear:both}
.ui-helper-clearfix{zoom:1}
.ui-helper-zfix{width:100%;height:100%;top:0;left:0;position:absolute;opacity:0;filter:Alpha(Opacity=0)}
.ui-tabs{position:relative;padding:.2em;zoom:1}
.ui-tabs .ui-tabs-nav{margin:0;padding:.2em .2em 0}
.ui-tabs .ui-tabs-nav li{list-style:none;float:left;position:relative;top:0;margin:1px .2em 0 0;border-bottom:0;padding:0;white-space:nowrap}
.ui-tabs .ui-tabs-nav li a{float:left;padding:.5em 1em;text-decoration:none}.ui-tabs .ui-tabs-nav li.ui-tabs-active{margin-bottom:-1px;padding-bottom:1px}
.ui-tabs .ui-tabs-nav li.ui-tabs-active a,.ui-tabs .ui-tabs-nav li.ui-state-disabled a,.ui-tabs .ui-tabs-nav li.ui-tabs-loading a{cursor:text}
.ui-tabs .ui-tabs-nav li a,.ui-tabs-collapsible .ui-tabs-nav li.ui-tabs-active a{cursor:pointer}.ui-tabs .ui-tabs-panel{display:block;border-width:0;padding:1em 1.4em;background:none}

/* table */
.tablelist{width:100%;border-left:1px solid #CCC;border-top:1px solid #CCC;border-collapse:collapse;}
.tablelist td{height:26px;border-right:1px solid #ccc;border-bottom:1px solid #ccc;border-collapse:collapse;word-break:break-all;word-warp:break-word;}
.tablelist .btitle{background:#ECF1F8;font:bold 12px/26px "microsoft yahei";padding:0 0 0 5px;}
.tablelist .title{width:12%;background:#ECF1F8;font:normal 12px/26px "microsoft yahei";color:#000;padding:0 0 0 5px;}
.tablelist .text{width:25%;}
.tablelist .druggytitle{width:22%;background:#EBEBEB;font:bold 12px/26px "microsoft yahei";color:#000;padding:0 0 0 5px;}
.tablelist .content{width:30%;font:normal 12px/26px "microsoft yahei";color:#000;padding:0 0 0 2px;}
.tablelist .content pre,
.tablelist .content span{*width:220px; word-wrap:break-word; word-break:break-all; white-space:-moz-pre-wrap; white-space:pre-wrap; *overflow:hidden;}
.tablelist .imagesTX{width:16%;}


/* jqgrid */
.ui-icon{display:block;text-indent:-99999px;overflow:hidden;background-repeat:no-repeat}
.ui-icon {width: 16px;height: 16px;background-image: url(images/ui-icons_0078ae_256x240.png);}
.ui-jqgrid .ui-jqgrid-pager .ui-pg-div span.ui-icon {float: left;margin: 0 2px;}
.ui-jqgrid .ui-pg-button span{display: block;margin: 1px;float: left;}
.ui-jqgrid tr.jqgrow td {vertical-align:middle;}
.ui-jqgrid .ui-jqgrid-htable th div{height:26px; line-height:24px; *vertical-align:middle; *height:auto;}
.ui-jqgrid .ui-jqgrid-btable a,.ui-jqgrid .ui-state-hover a,.ui-jqgrid .ui-state-hover a:hover,.ui-jqgrid .ui-state-hover a:link{color:#2d62ac;text-decoration:underline;}
.ui-jqgrid .ui-jqgrid-htable th.ui-th-ltr{height:26px; border-right:1px solid #d6d6d6; border-bottom:1px solid #d6d6d6; color:#333; background:url((${resource_path}/resource/system/images/jqGreadThBg.png) 0 0 repeat-x;}
.ui-dialog .ui-dialog-buttonpane .ui-widget-content .ui-state-default{background:-moz-linear-gradient(top, #fafafb, #e6edf1); background:-webkit-gradient(linear, left top, left bottom, from(#fafafb), to(#e6edf1)); filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0,StartColorStr='#fafafb',EndColorStr='#e6edf1'); border:1px solid #c6d8e0; font-weight:normal; color:#014265; border-radius:3px;}
.ui-jqgrid-view .ui-jqgrid-hdiv{background:-moz-linear-gradient(top, #ffffff, #f0f0f0); background:-webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#f0f0f0)); filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr='#ffffff', EndColorStr='#f0f0f0');}
.ui-jqgrid .ui-jqgrid-view .ui-state-default{border-bottom:1px solid #d6d6d6;}
.ui-jqgrid tr.jqgrow td{height:26px;line-height:26px;border-color:#fff #d6d6d6 #d6d6d6 #fff; *border-color:#d6d6d6;}
.ui-jqgrid .s-ico{position:relative; top:5px;}
.ui-jqgrid .ui-icon-asc{margin-left:1px;}
.ui-jqgrid .ui-jqgrid-pager{background:#E6E7E7;border:0;border-top:1px solid #D6D6D6;color:#5D5D5D;}
.ui-jqgrid .ui-jqgrid-pager .ui-icon{background-image:url(${resource_path}/resource/system/images/fixjqGridIcon.png);}
.ui-jqgrid .ui-jqgrid-pager .ui-state-hover{border:1px solid #D6D6D6;}
.ui-jqgrid .ui-row-ltr{background:#f1f1f1;}
.ui-jqgrid .ui-state-hover,
.ui-jqgrid .ui-widget-content .ui-state-hover,
.ui-jqgrid .ui-widget-header .ui-state-hover,
.ui-jqgrid .ui-state-focus,
.ui-jqgrid .ui-widget-content .ui-state-focus,
.ui-jqgrid .ui-widget-header .ui-state-focus{background:#fff;}
.ui-widget-content {border: 1px solid #e6e6e6;background: #ffffff url(${resource_path}/resource/external/jqueryui/default/ui-bg_flat_75_ffffff_40x100.png) 50% 50% repeat-x; color: #333;}
.ui-icon-seek-start{background-position:-80px -160px}
.ui-icon-seek-first{background-position:-80px -160px}
.ui-icon-seek-prev{background-position:-48px -160px}
.ui-icon-seek-next{background-position:-32px -160px}
.ui-icon-seek-end{background-position:-64px -160px}
/* fix jqgrid frozen bug */
.ui-jqgrid .frozen-bdiv{*padding:0 0 15px 0;}

/* ui tabs */
.ui-tabs{background:none;padding:0;border:0;margin-right:5px;}
.ui-tabs .ui-tabs-nav{padding:0 0 0 10px;}
.ui-tabs .ui-tabs-nav li{position:relative;padding:0 15px;margin:0 .2em 0 0;top:3px;height:28px;}
.ui-tabs .ui-tabs-nav li a{font:normal 14px/15px 'microsoft yahei',arial;outline: none;padding: 0;color:#fff;}
.ui-tabs .ui-tabs-nav li a.number{padding:0px;color:#fff;}
.ui-tabs .ui-tabs-nav li.ui-state-highlight{display:inline-block;*display:inline;*zoom:1;width:50px;height:24px;background:none;}
.ui-tabs .ui-widget-header .ui-state-default {background:none;border:0;}
.ui-tabs .ui-tabs-nav li a{padding:5px 10px;}
.ui-tabs .ui-tabs-nav li span{border-radius:5px;cursor:pointer;position:absolute;color:#fff;font:bold 11px/1.5em 'microsoft yahei';top:-8px;right:-8px;background:#F68300;padding:0 5px;z-index:1;}
.ui-tabs .ui-tabs-nav li.ui-tabs-selected{background:#fff;}
.ui-tabs .ui-tabs-nav li.ui-tabs-selected a{color:#333;}
.ui-tabs .ui-tabs-panel{padding:0;border:1px solid #d6d6d6;border-top: 0;background:#fff;}
</style>