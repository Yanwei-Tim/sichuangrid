<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>

<div id="accordion">
	<s:iterator value="permissions" var="p">
	    <pop:JugePermissionTag ename="${p.ename}">
		<div>
			  <h1><a  href="javascript:void(0)" class="blurMenuClass">${p.cname}</a></h1>
			  <ul>
			  	<s:iterator value="childMap.get(#p.ename)" var="child">
					<pop:JugePermissionTag ename="${child.ename}">
						<li><a id="${child.ename}" href="javascript:void(0)"
							onclick="asyncOpen(this,'${path}${child.normalUrl}')"><span>${child.cname}</span></a></li>
					</pop:JugePermissionTag>
				</s:iterator>
			 </ul>
		</div>
	 	</pop:JugePermissionTag>
 	</s:iterator>
</div>
<script type="text/javascript">
var bol=true;
	$(function() {
		$("#gridIntroductionManagement").removeAttr("onclick");
		$("#gridIntroductionManagement").click(function(){showJspByOrgLevel($("#gridIntroductionManagement"))});
		var jsflag='<s:property value='#parameters.urlflag'/>';
		if(jsflag==undefined || jsflag=='' || !$("#"+jsflag)[0]){
			$('#accordion > div > ul > li:first > a').click();
		}
		$.selectMapMenu({
			id:'accordion',
			jsflag:jsflag
		});
		$("#accordion").accordionFunction("option",{
			autoHeight:true,
			fillSpace:true,
			animated:false
		});
		var accordionBox=$('#accordion ul');
		accordionBox.each(function(){
			if($(this).find("li").size()>4 && accordionBox.size()>4){
				$(this).addClass('dHeight');
			}
			else{
				$(this).addClass('autoHeight');
			}
		});
	});

	function showJspByOrgLevel(orgIntroduce){
		if(isGrid()){
			asyncOpen(orgIntroduce,'${path}/baseinfo/villageProfile/gridProfileComplete.jsp');
		}else{
			asyncOpen(orgIntroduce,'${path}/baseinfo/villageProfile/villageProfileComplete.jsp');
		}
	}
</script>
