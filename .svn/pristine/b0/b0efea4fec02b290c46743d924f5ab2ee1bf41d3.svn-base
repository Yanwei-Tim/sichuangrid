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
</style>

<div id="centerImgBoxCtt" style="overflow: auto;position:relative;height: 470px;">
<div class="zy_cont">
	<div class="level_1 level">
        <div class="person_infor person_infor_spe">
        <c:forEach var="fourTeamsOrg" items="${fourTeamsOrgList}">
            <c:if test="${fourTeamsOrg.leve == 1 && fourTeamsOrg.leveSort == 1 }">
           		<div onmouseover="showMenu(${fourTeamsOrg.id });" onmouseout="hideMenu(${fourTeamsOrg.id });">
           		<div style="position:relative;">
           		<div class="edit_btn cf">
           			<pop:JugePermissionTag ename="editOrganizationChar">
           				<a href="javascript:editFourTeamsOrg(${fourTeamsOrg.id });" class="btn_edit base-button" style="display: none;" id="fourTeamsEdit${fourTeamsOrg.id }"><span>编辑</span></a>
	              	</pop:JugePermissionTag>
	              	<pop:JugePermissionTag ename="emptyOrganizationChar">
	              		<a href="javascript:emptyData(${fourTeamsOrg.id });" class ="btn_reset base-button" style="display: none;" id="fourTeamsEmpty${fourTeamsOrg.id }"><span>清空</span></a>
                	</pop:JugePermissionTag>
                </div>
                <a href="javascript:;" id="view_fourTeams_${fourTeamsOrg.id }">
                <div class="infor_title">${fourTeamsOrg.teamTitle }</div>
                <p class="infor_img">
	                <c:if test="${fourTeamsOrg.headImage !='' && fourTeamsOrg.headImage !=null }">
	                	<img id="img" name="fourTeamsOrg.headImage" src="${path }${fourTeamsOrg.headImage}" width="148px"  />
	                </c:if>
					<c:if test="${fourTeamsOrg.headImage =='' || fourTeamsOrg.headImage == null }">
						<img id="img" name="fourTeamsOrg.headImage" src="${path }/resource/system/images/defaultAvatar.jpg" width="148px"  />
					</c:if>
                <p class="infor_name">姓名：<span>${fourTeamsOrg.userName}</span></p>
                <p class="infor_job cf"  title="${fourTeamsOrg.position }">
                <span class="fl">职务：</span>
                	<span class="fr">
	                	<c:if test="${fn:length(fourTeamsOrg.position)<11 }">${fourTeamsOrg.position }</c:if>
						<c:if test="${fn:length(fourTeamsOrg.position)>11 }">${fn:substring(fourTeamsOrg.position, 0, 11)}...</c:if>
                	</span>
                </p>
                <p class="infor_tel">手机：<span>${fourTeamsOrg.phoneNumber }</span></p>
            </a>
            </div>
            </div>
            </c:if>
        </c:forEach>
          <div class="line"></div>
        </div>
      </div>
      
    <div class="longline"></div>
    <div class="level_2 level">
        <div class="infor_plus infor_plus_spe">
         <c:forEach var="fourTeamsOrg" items="${fourTeamsOrgList}">
             <c:if test="${fourTeamsOrg.leve == 2 && fourTeamsOrg.leveSort == 1}">
              <div class="person_infor">
              <div class="line"></div>
              <div onmouseover="showMenu(${fourTeamsOrg.id });" onmouseout="hideMenu(${fourTeamsOrg.id });">
	          <div style="position:relative;">
           		<div class="edit_btn cf">
           			<pop:JugePermissionTag ename="editOrganizationChar">
           				<a href="javascript:editFourTeamsOrg(${fourTeamsOrg.id });" class="btn_edit base-button" style="display: none;" id="fourTeamsEdit${fourTeamsOrg.id }"><span>编辑</span></a>
	              	</pop:JugePermissionTag>
	              	<pop:JugePermissionTag ename="emptyOrganizationChar">
	              		<a href="javascript:emptyData(${fourTeamsOrg.id });" class ="btn_reset base-button" style="display: none;" id="fourTeamsEmpty${fourTeamsOrg.id }"><span>清空</span></a>
                	</pop:JugePermissionTag>
                </div>
                <a href="javascript:;" id="view_fourTeams_${fourTeamsOrg.id }">
                    <div class="infor_title">${fourTeamsOrg.teamTitle }</div>
	                <p class="infor_img"> 
		               <c:if test="${fourTeamsOrg.headImage !='' && fourTeamsOrg.headImage !=null }">
	                		<img id="img" name="fourTeamsOrg.headImage" src="${path }${fourTeamsOrg.headImage}" width="148px"  />
		                </c:if>
						<c:if test="${fourTeamsOrg.headImage =='' || fourTeamsOrg.headImage == null }">
							<img id="img" name="fourTeamsOrg.headImage" src="${path }/resource/system/images/defaultAvatar.jpg" width="148px"  />
						</c:if>
					</p>
	                <p class="infor_name">姓名：<span>${fourTeamsOrg.userName}</span></p>
               		<p class="infor_job cf"  title="${fourTeamsOrg.position }">
	                <span class="fl">职务：</span>
	                	<span class="fr">
		                	<c:if test="${fn:length(fourTeamsOrg.position)<11 }">${fourTeamsOrg.position }</c:if>
							<c:if test="${fn:length(fourTeamsOrg.position)>11 }">${fn:substring(fourTeamsOrg.position, 0, 11)}...</c:if>
	                	</span>
	                </p>
	                <p class="infor_tel">手机：<span>${fourTeamsOrg.phoneNumber }</span></p>
                </a>
               </div>
            </div>
            </div>
       		 </c:if>
       		 <c:if test="${fourTeamsOrg.leve == 2 && fourTeamsOrg.leveSort == 2}">
              <div class="person_infor">
              <div class="line"></div>
                <div onmouseover="showMenu(${fourTeamsOrg.id });" onmouseout="hideMenu(${fourTeamsOrg.id });">
	            <div style="position:relative;">
           		<div class="edit_btn cf">
           			<pop:JugePermissionTag ename="editOrganizationChar">
           				<a href="javascript:editFourTeamsOrg(${fourTeamsOrg.id });" class="btn_edit base-button" style="display: none;" id="fourTeamsEdit${fourTeamsOrg.id }"><span>编辑</span></a>
	              	</pop:JugePermissionTag>
	              	<pop:JugePermissionTag ename="emptyOrganizationChar">
	              		<a href="javascript:emptyData(${fourTeamsOrg.id });" class ="btn_reset base-button" style="display: none;" id="fourTeamsEmpty${fourTeamsOrg.id }"><span>清空</span></a>
                	</pop:JugePermissionTag>
                </div>
                <a href="javascript:;" id="view_fourTeams_${fourTeamsOrg.id }">
                     <div class="infor_title">${fourTeamsOrg.teamTitle }</div>
	                <p class="infor_img">
	                	<c:if test="${fourTeamsOrg.headImage !='' && fourTeamsOrg.headImage !=null }">
	                		<img id="img" name="fourTeamsOrg.headImage" src="${path }${fourTeamsOrg.headImage}" width="148px"  />
		                </c:if>
						<c:if test="${fourTeamsOrg.headImage =='' || fourTeamsOrg.headImage == null }">
							<img id="img" name="fourTeamsOrg.headImage" src="${path }/resource/system/images/defaultAvatar.jpg" width="148px"  />
						</c:if>
	                </p>
	                <p class="infor_name">姓名：<span>${fourTeamsOrg.userName}</span></p>
                	<p class="infor_job cf"  title="${fourTeamsOrg.position }">
	                	<span class="fl">职务：</span>
	                	<span class="fr">
		                	<c:if test="${fn:length(fourTeamsOrg.position)<11 }">${fourTeamsOrg.position }</c:if>
							<c:if test="${fn:length(fourTeamsOrg.position)>11 }">${fn:substring(fourTeamsOrg.position, 0, 11)}...</c:if>
	                	</span>
	                </p>
	                <p class="infor_tel">手机：<span>${fourTeamsOrg.phoneNumber }</span></p>
                </a>
                </div>
            </div>
            </div>
       		 </c:if>
       		 <c:if test="${fourTeamsOrg.leve == 2 && fourTeamsOrg.leveSort == 3}">
              <div class="person_infor">
              <div class="line"></div>
                <div onmouseover="showMenu(${fourTeamsOrg.id });" onmouseout="hideMenu(${fourTeamsOrg.id });">
	            <div style="position:relative;">
	           		<div class="edit_btn cf">
		           		<pop:JugePermissionTag ename="editOrganizationChar">
	           				<a href="javascript:editFourTeamsOrg(${fourTeamsOrg.id });" class="btn_edit base-button" style="display: none;" id="fourTeamsEdit${fourTeamsOrg.id }"><span>编辑</span></a>
		              	</pop:JugePermissionTag>
		              	<pop:JugePermissionTag ename="emptyOrganizationChar">
		              		<a href="javascript:emptyData(${fourTeamsOrg.id });" class ="btn_reset base-button" style="display: none;" id="fourTeamsEmpty${fourTeamsOrg.id }"><span>清空</span></a>
	                	</pop:JugePermissionTag>
	                </div>
                <a href="javascript:;" id="view_fourTeams_${fourTeamsOrg.id }">
                     <div class="infor_title">${fourTeamsOrg.teamTitle }</div>
	                <p class="infor_img">
	                	<c:if test="${fourTeamsOrg.headImage !='' && fourTeamsOrg.headImage !=null }">
	                		<img id="img" name="fourTeamsOrg.headImage" src="${path }${fourTeamsOrg.headImage}" width="148px"  />
		                </c:if>
						<c:if test="${fourTeamsOrg.headImage =='' || fourTeamsOrg.headImage == null }">
							<img id="img" name="fourTeamsOrg.headImage" src="${path }/resource/system/images/defaultAvatar.jpg" width="148px"  />
						</c:if>
					</p>
	                <p class="infor_name">姓名：<span>${fourTeamsOrg.userName}</span></p>
	                <p class="infor_job cf"  title="${fourTeamsOrg.position }">
	                	<span class="fl">职务：</span>
	                	<span class="fr">
		                	<c:if test="${fn:length(fourTeamsOrg.position)<11 }">${fourTeamsOrg.position }</c:if>
							<c:if test="${fn:length(fourTeamsOrg.position)>11 }">${fn:substring(fourTeamsOrg.position, 0, 11)}...</c:if>
	                	</span>
	                </p>
	                <p class="infor_tel">手机：<span>${fourTeamsOrg.phoneNumber }</span></p>
                </a>
                </div>
                </div>
            </div>
       		 </c:if>
       		 <c:if test="${fourTeamsOrg.leve == 2 && fourTeamsOrg.leveSort == 4}">
              <div class="person_infor">
              <div class="line"></div>
                <div onmouseover="showMenu(${fourTeamsOrg.id });" onmouseout="hideMenu(${fourTeamsOrg.id });">
	             <div style="position:relative;">
	           		<div class="edit_btn cf">
	           			<pop:JugePermissionTag ename="editOrganizationChar">
           					<a href="javascript:editFourTeamsOrg(${fourTeamsOrg.id });" class="btn_edit base-button" style="display: none;" id="fourTeamsEdit${fourTeamsOrg.id }"><span>编辑</span></a>
		              	</pop:JugePermissionTag>
		              	<pop:JugePermissionTag ename="emptyOrganizationChar">
		              		<a href="javascript:emptyData(${fourTeamsOrg.id });" class ="btn_reset base-button" style="display: none;" id="fourTeamsEmpty${fourTeamsOrg.id }"><span>清空</span></a>
	                	</pop:JugePermissionTag>
	                </div>
                <a href="javascript:;" id="view_fourTeams_${fourTeamsOrg.id }">
                    <div class="infor_title">${fourTeamsOrg.teamTitle }</div>
	                <p class="infor_img">
	                	<c:if test="${fourTeamsOrg.headImage !='' && fourTeamsOrg.headImage !=null }">
	                		<img id="img" name="fourTeamsOrg.headImage" src="${path }${fourTeamsOrg.headImage}" width="148px"  />
		                </c:if>
						<c:if test="${fourTeamsOrg.headImage =='' || fourTeamsOrg.headImage == null }">
							<img id="img" name="fourTeamsOrg.headImage" src="${path }/resource/system/images/defaultAvatar.jpg" width="148px"  />
						</c:if>
	                </p>
	                <p class="infor_name">姓名：<span>${fourTeamsOrg.userName}</span></p>
	               <p class="infor_job cf"  title="${fourTeamsOrg.position }">
	                	<span class="fl">职务：</span>
	                	<span class="fr">
		                	<c:if test="${fn:length(fourTeamsOrg.position)<11 }">${fourTeamsOrg.position }</c:if>
							<c:if test="${fn:length(fourTeamsOrg.position)>11 }">${fn:substring(fourTeamsOrg.position, 0, 11)}...</c:if>
	                	</span>
	                </p>
	                <p class="infor_tel">手机：<span>${fourTeamsOrg.phoneNumber }</span></p>
                </a>
                </div>
            </div>
            </div>
       		 </c:if>
        </c:forEach>
    </div>
   </div> 
    
    <div class="level_3 level">
         <c:forEach var="fourTeamsOrg" items="${fourTeamsOrgList}">
             <c:if test="${fourTeamsOrg.leve == 3 && fourTeamsOrg.leveSort == 1}">
              <div class="person_infor">
              <div class="line"></div>
                <div onmouseover="showMenu(${fourTeamsOrg.id });" onmouseout="hideMenu(${fourTeamsOrg.id });">
	            <div style="position:relative;">
	           		<div class="edit_btn cf">
	           			<pop:JugePermissionTag ename="editOrganizationChar">
           					<a href="javascript:editFourTeamsOrg(${fourTeamsOrg.id });" class="btn_edit base-button" style="display: none;" id="fourTeamsEdit${fourTeamsOrg.id }"><span>编辑</span></a>
		              	</pop:JugePermissionTag>
		              	<pop:JugePermissionTag ename="emptyOrganizationChar">
		              		<a href="javascript:emptyData(${fourTeamsOrg.id });" class ="btn_reset base-button" style="display: none;" id="fourTeamsEmpty${fourTeamsOrg.id }"><span>清空</span></a>
	                	</pop:JugePermissionTag>
	                </div>
                <a href="javascript:;" id="view_fourTeams_${fourTeamsOrg.id }">
                     <div class="infor_title">${fourTeamsOrg.teamTitle }</div>
	                <p class="infor_img">
	                	<c:if test="${fourTeamsOrg.headImage !='' && fourTeamsOrg.headImage !=null }">
	                		<img id="img" name="fourTeamsOrg.headImage" src="${path }${fourTeamsOrg.headImage}" width="148px"  />
		                </c:if>
						<c:if test="${fourTeamsOrg.headImage =='' || fourTeamsOrg.headImage == null }">
							<img id="img" name="fourTeamsOrg.headImage" src="${path }/resource/system/images/defaultAvatar.jpg" width="148px"  />
						</c:if>
	                </p>
	                <p class="infor_name">姓名：<span>${fourTeamsOrg.userName}</span></p>
	                <p class="infor_job cf"  title="${fourTeamsOrg.position }">
	                	<span class="fl">职务：</span>
	                	<span class="fr">
		                	<c:if test="${fn:length(fourTeamsOrg.position)<11 }">${fourTeamsOrg.position }</c:if>
							<c:if test="${fn:length(fourTeamsOrg.position)>11 }">${fn:substring(fourTeamsOrg.position, 0, 11)}...</c:if>
	                	</span>
	                </p>
	                <p class="infor_tel">手机：<span>${fourTeamsOrg.phoneNumber }</span></p>
                </a>
                </div>
            </div>
            </div>
       		 </c:if>
       		 <c:if test="${fourTeamsOrg.leve == 3 && fourTeamsOrg.leveSort == 2}">
              <div class="person_infor">
              <div class="line"></div>
                <div onmouseover="showMenu(${fourTeamsOrg.id });" onmouseout="hideMenu(${fourTeamsOrg.id });">
	            <div style="position:relative;">
	           		<div class="edit_btn cf">
	           			<pop:JugePermissionTag ename="editOrganizationChar">
           					<a href="javascript:editFourTeamsOrg(${fourTeamsOrg.id });" class="btn_edit base-button" style="display: none;" id="fourTeamsEdit${fourTeamsOrg.id }"><span>编辑</span></a>
		              	</pop:JugePermissionTag>
		              	<pop:JugePermissionTag ename="emptyOrganizationChar">
		              		<a href="javascript:emptyData(${fourTeamsOrg.id });" class ="btn_reset base-button" style="display: none;" id="fourTeamsEmpty${fourTeamsOrg.id }"><span>清空</span></a>
	                	</pop:JugePermissionTag>
	                </div>
                <a href="javascript:;" id="view_fourTeams_${fourTeamsOrg.id }">
                     <div class="infor_title">${fourTeamsOrg.teamTitle }</div>
	                <p class="infor_img">
	               	 	<c:if test="${fourTeamsOrg.headImage !='' && fourTeamsOrg.headImage !=null }">
	                		<img id="img" name="fourTeamsOrg.headImage" src="${path }${fourTeamsOrg.headImage}" width="148px"  />
		                </c:if>
						<c:if test="${fourTeamsOrg.headImage =='' || fourTeamsOrg.headImage == null }">
							<img id="img" name="fourTeamsOrg.headImage" src="${path }/resource/system/images/defaultAvatar.jpg" width="148px"  />
						</c:if>
	                </p>
	                <p class="infor_name">姓名：<span>${fourTeamsOrg.userName}</span></p>
	                <p class="infor_job cf"  title="${fourTeamsOrg.position }">
	                	<span class="fl">职务：</span>
	                	<span class="fr">
		                	<c:if test="${fn:length(fourTeamsOrg.position)<11 }">${fourTeamsOrg.position }</c:if>
							<c:if test="${fn:length(fourTeamsOrg.position)>11 }">${fn:substring(fourTeamsOrg.position, 0, 11)}...</c:if>
	                	</span>
	                </p>
	                <p class="infor_tel">手机：<span>${fourTeamsOrg.phoneNumber }</span></p>
                </a>
                </div>
            </div>
            </div>
       		 </c:if>
       		 <c:if test="${fourTeamsOrg.leve == 3 && fourTeamsOrg.leveSort == 3}">
              <div class="person_infor">
              <div class="line"></div>
                <div onmouseover="showMenu(${fourTeamsOrg.id });" onmouseout="hideMenu(${fourTeamsOrg.id });">
	            <div style="position:relative;">
	           		<div class="edit_btn cf">
						<pop:JugePermissionTag ename="editOrganizationChar">
           					<a href="javascript:editFourTeamsOrg(${fourTeamsOrg.id });" class="btn_edit base-button" style="display: none;" id="fourTeamsEdit${fourTeamsOrg.id }"><span>编辑</span></a>
		              	</pop:JugePermissionTag>
		              	<pop:JugePermissionTag ename="emptyOrganizationChar">
		              		<a href="javascript:emptyData(${fourTeamsOrg.id });" class ="btn_reset base-button" style="display: none;" id="fourTeamsEmpty${fourTeamsOrg.id }"><span>清空</span></a>
	                	</pop:JugePermissionTag>
	                </div>
                <a href="javascript:;" id="view_fourTeams_${fourTeamsOrg.id }">
                     <div class="infor_title">${fourTeamsOrg.teamTitle }</div>
	                <p class="infor_img">
	                	<c:if test="${fourTeamsOrg.headImage !='' && fourTeamsOrg.headImage !=null }">
	                		<img id="img" name="fourTeamsOrg.headImage" src="${path }${fourTeamsOrg.headImage}" width="148px"  />
		                </c:if>
						<c:if test="${fourTeamsOrg.headImage =='' || fourTeamsOrg.headImage == null }">
							<img id="img" name="fourTeamsOrg.headImage" src="${path }/resource/system/images/defaultAvatar.jpg" width="148px"  />
						</c:if>
	                </p>
	                <p class="infor_name">姓名：<span>${fourTeamsOrg.userName}</span></p>
	                <p class="infor_job cf"  title="${fourTeamsOrg.position }">
	               	 	<span class="fl">职务：</span>
	                	<span class="fr">
		                	<c:if test="${fn:length(fourTeamsOrg.position)<11 }">${fourTeamsOrg.position }</c:if>
							<c:if test="${fn:length(fourTeamsOrg.position)>11 }">${fn:substring(fourTeamsOrg.position, 0, 11)}...</c:if>
	                	</span>
	                </p>
	                <p class="infor_tel">手机：<span>${fourTeamsOrg.phoneNumber }</span></p>
                </a>
                </div>
            </div>
            </div>
       		 </c:if>
       		 <c:if test="${fourTeamsOrg.leve == 3 && fourTeamsOrg.leveSort == 4}">
              <div class="person_infor">
              <div class="line"></div>
               <div onmouseover="showMenu(${fourTeamsOrg.id });" onmouseout="hideMenu(${fourTeamsOrg.id });">
	            <div style="position:relative;">
	           		<div class="edit_btn cf">
	           			<pop:JugePermissionTag ename="editOrganizationChar">
           					<a href="javascript:editFourTeamsOrg(${fourTeamsOrg.id });" class="btn_edit base-button" style="display: none;" id="fourTeamsEdit${fourTeamsOrg.id }"><span>编辑</span></a>
		              	</pop:JugePermissionTag>
		              	<pop:JugePermissionTag ename="emptyOrganizationChar">
		              		<a href="javascript:emptyData(${fourTeamsOrg.id });" class ="btn_reset base-button" style="display: none;" id="fourTeamsEmpty${fourTeamsOrg.id }"><span>清空</span></a>
	                	</pop:JugePermissionTag>
	                </div>
                <a href="javascript:;" id="view_fourTeams_${fourTeamsOrg.id }">
                   <div class="infor_title">${fourTeamsOrg.teamTitle }</div>
	                <p class="infor_img">
	                	<c:if test="${fourTeamsOrg.headImage !='' && fourTeamsOrg.headImage !=null }">
	                		<img id="img" name="fourTeamsOrg.headImage" src="${path }${fourTeamsOrg.headImage}" width="148px"  />
		                </c:if>
						<c:if test="${fourTeamsOrg.headImage =='' || fourTeamsOrg.headImage == null }">
							<img id="img" name="fourTeamsOrg.headImage" src="${path }/resource/system/images/defaultAvatar.jpg" width="148px"  />
						</c:if>
	                </p>
	                <p class="infor_name">姓名：<span>${fourTeamsOrg.userName}</span></p>
	                <p class="infor_job cf"  title="${fourTeamsOrg.position }">
	                	<span class="fl">职务：</span>
	                	<span class="fr">
		                	<c:if test="${fn:length(fourTeamsOrg.position)<11 }">${fourTeamsOrg.position }</c:if>
							<c:if test="${fn:length(fourTeamsOrg.position)>11 }">${fn:substring(fourTeamsOrg.position, 0, 11)}...</c:if>
	                	</span>
	                </p>
	                <p class="infor_tel">手机：<span>${fourTeamsOrg.phoneNumber }</span></p>
                </a>
                </div>
            </div>
            </div>
       		 </c:if>
        </c:forEach>
   </div> 
</div>
</div>
<div id="editFourTeamsOrgDlg"></div>
<script type="text/javascript">
	$(function(){
		var districtView = '<s:property value="@com.tianque.fourTeams.fourTeamsManage.util.FourteamsUitl@FOURTEAM_DISTRICT_LEVLE"/>';
		var basicView =  '<s:property value="@com.tianque.fourTeams.fourTeamsManage.util.FourteamsUitl@FOURTEAM_BASIC_LEVLE"/>';
		if('${orgLevel}' == districtView){
			$(".edit_btn").show();
		}else if('${orgLevel}' == basicView){
			$(".edit_btn").hide();
		}
	});

	function editFourTeamsOrg(id){
			$("#editFourTeamsOrgDlg").createDialog({
				width: 430,
				height: 260,
				title:'修改组织结构信息',
				url:'${path}/fourTeamsOrgManage/dispatch.action?mode=edit&fourTeamsOrg.id='+id,
				buttons: {
			   		"保存" : function(event){
				   		$("#updateDetailsForm").submit();
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}

	function showMenu(id){
		$("#fourTeamsEdit"+id).show();
		$("#fourTeamsEmpty"+id).show();
	}
	function hideMenu(id){
		$("#fourTeamsEdit"+id).hide();
		$("#fourTeamsEmpty"+id).hide();
	}
	function refreshData(id){
		$.ajax({
			async:false,
			url:"${path}/fourTeamsOrgManage/getFourTeamsOrgObject.action?fourTeamsOrg.id="+id,
			success:function(data){
				if(data!= false && data!="false" ){
					var imageUrl = stringFormatter(data.headImage);
					if(imageUrl == ""){
						imageUrl = "${path }/resource/system/images/defaultAvatar.jpg";
					}
					$("#view_fourTeams_"+id).empty();
					$("#view_fourTeams_"+id).append("<div class='infor_title'>"+stringFormatter(data.teamTitle)+"</div>");
					$("#view_fourTeams_"+id).append("<p class='infor_img'><img src='"+imageUrl+"'></p>");
					$("#view_fourTeams_"+id).append("<p class='infor_name'>姓名：<span>"+stringFormatter(data.userName)+"</span></p>");
					$("#view_fourTeams_"+id).append("<p class='infor_job cf' title="+stringFormatter(data.position)+"><span class='fl'>职务：</span><span class='fr'>"+jobStrFormatter(data.position)+"</span></p>");
					$("#view_fourTeams_"+id).append("<p class='infor_tel'>手机：<span>"+stringFormatter(data.phoneNumber)+"</span></p>");
				}else{
					$.messageBox({message:"查询超时，请刷新页面！",level:"error"});
				}
			}
		});
	}
	function emptyData(id){
		$.confirm({
			title:"确认清空信息",
			message:"该信息清空后就不能还原，确认清空？",
			width: 300,
			okFunc: function(){
				$.ajax({
					async:false,
					url:"${path}/fourTeamsOrgManage/emptyFourTeamsOrg.action?fourTeamsOrg.id="+id,
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						$.messageBox({message:"操作成功！"});
						refreshData(id)
					}
				});
			}
		});
	}
	function stringFormatter(str){
		if(str==undefined || str=='undefined'){
			return "";
		}
		return str;
	}
	function jobStrFormatter(str){
		if(str==undefined || str=='undefined'){
			return "";
		}else{
			if(str.length > 11){
				str = str.substring(0,11)+"...";   
			}
		}
		return str;
	}
</script>
