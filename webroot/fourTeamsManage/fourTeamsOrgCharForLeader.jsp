<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
@charset "utf-8";
a,abbr,acronym,address,applet,article,aside,audio,b,big,blockquote,body,canvas,caption,center,cite,code,dd,del,details,dfn,dialog,div,dl,dt,em,embed,fieldset,figcaption,figure,font,footer,form,h1,h2,h3,h4,h5,h6,header,hgroup,html,i,iframe,img,ins,kbd,label,legend,li,mark,menu,meter,nav,object,ol,output,p,pre,progress,q,rp,rt,ruby,s,samp,section,small,span,strike,strong,sub,summary,sup,table,tbody,td,tfoot,th,thead,time,tr,tt,u,ul,var,video,xmp {
	border:0;
	margin:0;
	padding:0;
	font-size:12px;
}
html,
body { height: 100%;}
article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section {display:block;}
.fl{float:left;}
.fr{float:right;width:78px;height:44px;overflow:hidden;text-align:left;text-indent:0;}
.cf{clear:both;overflow:hidden;}
b,strong { font-weight: bold;}
img {color: transparent;font-size: 0;vertical-align: middle;-ms-interpolation-mode: bicubic;}
li{display: list-item;list-style: none;}
table{*border-collapse: collapse;border-spacing: 0;}
th,td,caption {font-weight: normal;vertical-align: top;text-align: left;display: table-cell;vertical-align: inherit;}
q {quotes: none;}
q:before,q:after {content: '';content: none;}
sub,sup,small {font-size: 75%;}
sub,sup {line-height: 0;position: relative;vertical-align: baseline;}
sub {bottom: -0.25em;}
sup {top: -0.5em;}
svg {overflow: hidden;}
body{margin:0 auto;padding:0;font:normal 12px arial,tahoma,helvetica,sans-serif;}
input,button,select,textarea{outline:none}
textarea{resize:none}
/* 链接颜色 */
a{text-decoration:none;}
a:hover{text-decoration:none;}
.clearfix:after{content:".";height:0;display:block;clear:both;visibility:hidden;}
.clearfix{*zoom:1;}
.clear:after,.form_row:after,.sq_info:after,.sq_flow li:after{content:'\20';display:block;height:0;clear:both}
.clearLine{overflow:hidden;clear:both;zoom:1;height:0;}
.cf:before, .cf:after {content:"";display:table;}
.cf:after {clear:both;}
.cf {zoom:1;}
.clear{width:0;height:0;clear:both;overflow:hidden;}
.person_infor{width:120px;}
.person_infor_spe{margin:0 auto;}
.person_infor a{color:#333;}
.person_infor .infor_title{text-align:center;font:bold 14px/26px 'microsoft yahei';height:54px;overflow:hidden;text-overflow: ellipsis;}
.person_infor p{font:normal 12px/22px 'microsoft yahei';text-indent:0.5em;}
.person_infor p.infor_job{}
.person_infor .infor_img{padding:3px;border: 1px solid #e7e7e7;text-indent:0;}
.person_infor .infor_img img{width:110px;height:132px;margin:0 auto;}
.zy_cont .line{width:2px;height:29px;background:#e1e1e1;margin:0 auto;}
.zy_cont .longline{height:2px;background:#e1e1e1;margin:0 auto;width:490px;}
.zy_cont .longline_spe{width:248px;}
.level_2,.level_3{text-align:center;height:310px;}
.level_2 .person_infor p,.level_3 .person_infor p{text-align:left;}
.level_2 .person_infor,.level_3 .person_infor,.infor_plus{display:inline-block;*display:inline;*zoom:1;margin:0 20px;vertical-align:top;}
.edit_btn{position:absolute;top:55px;left:10px;}
.sc_process{width:603px;margin:0 auto;}
.sc_process .path{height:42px;width:46px;margin:0 auto;background:url(../resource/images/img_sprite.png) center top no-repeat;}
.sc_process .level a{display:block;height:37px;font:normal 17px/37px 'microsoft yahei';color:#5b5b5b;text-align:center;background:url(../resource/images/img_sprite.png) no-repeat;margin:0 auto;}
.sc_process .level1 a{width:382px;background-position:center -42px;}
.sc_process .level1 a:hover{background-position:center -79px;}
.sc_process .level2 a{width:440px;background-position:center -121px;}
.sc_process .level2 a:hover{background-position:center -158px;}
.sc_process .level3 a{width:194px;background-position:center -210px;}
.sc_process .level3 a:hover{background-position:center -247px;}
.sc_process .pathbox{margin:0 auto;width:440px;}
.sc_process .pathbox .path{display:inline-block;*display:inline;*zoom:1;width:32%}
.sc_process .levelbox .level{display:inline-block;*display:inline;*zoom:1;margin:0 2px;}
</style>
<div id="centerImgBoxCtt" style="overflow: auto;position:relative; height: 470px;">

<div class="sc_process" id="leader_View">
		<div class="level1 level">
	    	<a href="javascript:;">县监管中心</a>
	    </div>
	    <div class="path"></div>
	    <div class="level1 level">
	    	<a href="javascript:;">乡镇/街道监管分中心</a>
	    </div>
	    <div class="path"></div>
	    <div class="level1 level">
	    	<a href="javascript:;">村（社区）管理站</a>
	    </div>
	    <div class="path"></div>
	    <div class="level2 level">
	    	<a href="javascript:<pop:JugePermissionTag ename='gridServiceTeamManagement'>jumpToFourTeams('gridServiceTeamManagement')</pop:JugePermissionTag>;">网格员服务队</a>
	    </div>
	    <div class="pathbox">
	    	<div class="path"></div>
	        <div class="path"></div>
	        <div class="path"></div>
	    </div>
	    <div class="levelbox">
	    	<div class="level3 level">
	    		<a href="javascript:<pop:JugePermissionTag ename='convenienceServiceTeamManagement'>jumpToFourTeams('convenienceServiceTeamManagement')</pop:JugePermissionTag>;">基层便民专业化服务队</a>
	        </div>
	        <div class="level3 level">
	            <a href="javascript:<pop:JugePermissionTag ename='partyMemberPioneerServiceTeamManagement'>jumpToFourTeams('partyMemberPioneerServiceTeamManagement')</pop:JugePermissionTag>;">党员先锋服务队</a>
	        </div>
	        <div class="level3 level">
	            <a href="javascript:<pop:JugePermissionTag ename='communityVolunteerServiceTeamManagement'>jumpToFourTeams('communityVolunteerServiceTeamManagement')</pop:JugePermissionTag>;">社会志愿者服务队</a>
	        </div>
	    </div>
	</div>
</div>
<script type="text/javascript">
	function jumpToFourTeams(str){
		$("#"+str+"").click();
	}
</script>
