<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

 <div class="header" id="header">
     <h1 id="logo" class="logo"><a href="javascript:;"><img src="${resource_path}/resource/judgmentAnalysis/img/logo.png" alt=""/></a></h1>
     <div id="toolBar" class="toolBar">
         <div class="welcome">
             <div class="pic">
                 <img src="${resource_path}/resource/judgmentAnalysis/pic/world.jpg" alt=""/>
                 <span class="veil"></span>
             </div>
              
                               欢迎您！<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/>
         </div>
         <a class="exit" id="exit" href="${path}/sessionManage/logout.action?isIndexJsp=true&indexPath=<s:property value="#parameters.indexPath[0]"/>" class="sys-exit">退出系统</a>
         
     </div>
     <div class="menu" id="menu">
         <ul>
             <li><a href="/login/judgmentAnalysis/module.jsp"><span class="icon_ypfx"></span>研判分析</a></li>
           <%--<li><a href="javascript:;"><span class="icon_xtsz"></span>系统设置</a></li>  --%>
         </ul>
     </div>
                 
 </div>

<div id="main" class="content" style="padding-bottom:0;">
      <div class="mainMenu" id="mainMenu">
          <ul>                                     
              <li  class="on"><a  href="#chartPageAnalysisManage-houseHoldStaffManagement"  baseurl="/hotModuel/judgmentAnalysis/chartPage/populationInfo/houseHoldStaff/index.ftl">户籍人口</a></li>
              <li><a href="#chartPageAnalysisManage-floatPopulationManagement" baseurl="/hotModuel/judgmentAnalysis/chartPage/populationInfo/floatingPopulation/index.ftl">流动人口</a></li>
              <li><a href="javascript:;">未落户人口</a></li>
              <li><a href="javascript:;">境外人员</a></li>
              <li><a href="#chartPageAnalysisManage-importantPopulation" baseurl="/hotModuel/judgmentAnalysis/chartPage/populationInfo/importantPopulation/index.ftl" >特殊人群</a></li>                    
              <li><a href="javascript:;">关怀对象</a></li>
              <li><a href="javascript:;">失业人员</a></li>
              <li><a href="javascript:;">育龄妇女</a></li>
          </ul>
      </div>
</div>
 

<script type="text/javascript">
    
	function baseLoad(that,baseUrl,leaderUrl){
		asyncOpen(that, baseUrl);
		$.resetHeight()

		$('.mCrumbs').hide();	 
	}

	$(function() {
		$("#main a").click(function(){

			var baseUrl=$(this).attr("baseUrl");
			if(baseUrl==null){
				 $.messageBox({message:"正在开发中..."});
				 return;
			}
			
			$('#main li').removeClass("on");
			$(this).parent().addClass("on");
  			 
			baseLoad(this,baseUrl,null);
		});
 
		 baseLoad($('#main a')[0],$('#main a').eq(0).attr("baseUrl"),null);
	});
	
		
</script>












