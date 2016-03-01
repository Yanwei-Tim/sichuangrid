<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<s:set value="#attr.ename" var="topename"/>
<div class="accordionMenuTit">
    <div id="accordionMenuBDT" class="accordionMenuBDT">
        <span class="icon ${ename}"></span>
        <span class="tit">${cname}</span>
    </div>
</div>

<div id="accordion" class="leftAccordion new_leftAccordion" >
    <div class="innerBox" id="leftMenuList">
    </div>
</div>

<script id="leftMenuTpl" type="text/html">
    {{each list as value i}}
		{{if value.baseUrl!=null || value.children.length<=0}}
	        <div class="noSubMenu">
	        	<a href="#{{value.pename}}-{{value.ename}}" 
	            	id="{{value.ename}}" 
	            	baseUrl="{{value.baseUrl}}" 
	            	leaderUrl="{{value.leaderUrl}}"
					gridUrl="{{value.gridUrl}}">
	            		<span>{{value.name}}</span>
	    		</a>
			</div>
		{{else}}
			{{if value.ename!="DigitalCaseManagement"}}
  			  	<h3 class="uiMenuBase">
	            	<div class="newRed"><span class="ui-icon"></span>{{value.name}}</div>
	        	</h3>
			{{/if}}
			<div class="uiContBase">
				<ul>
					{{if value.ename!="DigitalCaseManagement"}}
						{{each value.children as subItem j}}
						{{if subItem.ename!="organsPartyManagement" && subItem.ename!="streetPartyManagement" &&subItem.ename!="taskManagement" &&subItem.ename!="floatTaskListManage" && subItem.ename!="dataCollection"}}
							<li>
								<a href="#{{value.pename}}-{{subItem.ename}}"
									id="{{subItem.ename}}"
									baseUrl="{{subItem.baseUrl}}"
									leaderUrl="{{subItem.leaderUrl}}"
									gridUrl="{{subItem.gridUrl}}">
									<span >{{subItem.name}}</span>
								</a>
							</li>
						{{else if subItem.ename=="organsPartyManagement" || subItem.ename=="streetPartyManagement" || subItem.ename=="taskManagement" || subItem.ename=="floatTaskListManage" || subItem.ename=="dataCollection"}}
							<li class="hasChild">
								<a href="javascript:;" id="childName">
									<span class="ui-icon"></span>{{subItem.name}}</a>
								<dl>
									{{each subItem.children as threeItem m}}
										<dd>
											<a href="#{{value.pename}}-{{threeItem.ename}}"
											id="newEname"
											baseUrl="{{threeItem.baseUrl}}"
											leaderUrl="{{threeItem.leaderUrl}}"
											gridUrl="{{threeItem.gridUrl}}">
											{{threeItem.name}}
											</a>
										</dd>
									{{/each}}
								</dl>
							</li>
						{{/if}}
				    	{{/each}}
					{{/if}}
				</ul>
			</div>
		{{/if}}
    {{/each}}
</script>

<script type="text/javascript">
$(function(){
	//  左侧菜单的json
	var json = <s:if test="menuJson == null">[]</s:if><s:else>${menuJson}</s:else>;
	// 使用模板引生成HTML
    var html = template("leftMenuTpl",{'list':json}).replace(/{#-}/g, "#");
    $("#leftMenuList")["append"](html);
	
	function callback(){
		$.layout();
	    var dfop={
	        accordion : $("#accordion")
	        ,allALabel : $("#accordion").find('a')
	        ,jsflag : '<s:property value='#parameters.urlflag'/>'
	    }
		var leftMenuFun = TQ.use('leftMenuFun',function(){})
		leftMenuFun.init(dfop)
	}
	
	$.cacheScript({
	    url:'/resource/getScript/PublicModuleScript/leftMenuList.js',
	    callback:callback
	})
})

</script>
