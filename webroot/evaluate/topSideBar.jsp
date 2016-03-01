<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="getFullOrgById" var="getFullOrgById"
	executeResult="false" namespace="/sysadmin/orgManage">
	<s:param name="organization.id"
		value="#parameters.orgId"></s:param>
</s:action>
<ul class="tabnav">
	<li><span><s:property value="#getFullOrgById.organization.orgName"/></span></li>
</ul>
<div class="tabbox">
	<ul class="subnavbar">
		<s:if test="#getFullOrgById.organization.parentOrg.id.equals(@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId())">
			<pop:JugePermissionTag ename="evaluating">
				<li><a href="javascript:void(0)" 
					onclick="asyncMiddleLevelOpen(this,'${path}/evaluate/evaluate.jsp?orgId=<s:property value="#parameters.orgId"/>')"><span>进行评估</span></a></li>
			</pop:JugePermissionTag>
		</s:if>
		<pop:JugePermissionTag ename="evaluateHistory">
			<li><a href="javascript:void(0)" 
				onclick="asyncMiddleLevelOpen(this,'${path}/evaluate/evaluateResultList.jsp?orgId=<s:property value="#parameters.orgId"/>')"><span>历年评估结果</span></a></li>
		</pop:JugePermissionTag>
	</ul>
</div>
<script>
var _clickSwitch=true;
	$(function() {
		var _box=$('.tabbox ul');
		var _hover='hover';
		var objwidth=0;
		function showBoxMenu(i,n){
			objwidth=objwidth+$(n).innerWidth();
			if(objwidth>screen.width-265){
				$(n).after('<li class="more"><img src="'+RESOURCE_PATH+'/resource/system/images/popmenu2.gif" /></li>');
				$(".popMenu").attr("showMenu","false");
				$(".more").toggle(
						function () {
							$(this).html('<img src="'+RESOURCE_PATH+'/resource/system/images/popmenu1.gif" />');
							var _index=$(this).parent().index();
							$(".popMenu").children().hide();
							$(".test"+_index,$(".popMenu")).show();
							$(".popMenu").attr("showMenu","true");
							$(".ceng").show();
						},
						function () {
							$(this).html('<img src="'+RESOURCE_PATH+'/resource/system/images/popmenu2.gif" />');
							var _index=$(this).parent().index();
							$(".ceng").hide();
							$(".popMenu").children().hide();
							$(".popMenu").attr("showMenu","false");
						}
				);
				return false;
			};

		};

		$(".subnavbar li>a").each(function(){
			var subWidth=$(this).innerWidth()+15;
			$(this).parent().css("width",subWidth);
		});

		var _tab=$('ul.tabnav>li');
		var boxindex=0;
		_tab.click(function(){
			var _index=_tab.index(this);
			boxindex=_index;
			$(this).addClass(_hover).siblings().removeClass(_hover);
			_box.eq(_index).show().siblings().hide();
			//前一次又焦点的box
			if(!$(".test"+_index).attr("afterProccess")){
				var currentBox = _box.eq(_index);
				$.each(currentBox.children(),showBoxMenu);
				objwidth=0;
				$(".popMenu").append($("<div class='test"+_index+"'/>").append($(".more",currentBox).nextAll()));
				$(".test"+_index).attr("afterProccess",true);
			}
			if(_clickSwitch){
				$("a:first",$($(".tabbox>ul")[boxindex])).click();
			}
		});
		$(".ui-layout-center").click(function() {
			if($(".popMenu").attr("showMenu")=="true"){
				_box.eq(boxindex).children(".more").click();
			}
		});

		showFirstMenu();
	});


	function showFirstMenu(){
		var _tab=$('ul.tabnav>li');
		var jstype='<s:property value='#parameters.charttype'/>';
		var jsflag='<s:property value='#parameters.urlflag'/>';
		if(jstype!=null&&jstype!=''&&jstype>=0 &&jsflag!=null&&jsflag!=''){
			_clickSwitch=false;
			$(_tab[jstype]).click();
			$("a[id='"+jsflag+"']").click();
			_clickSwitch=true;
		}else{
			$(_tab[0]).click();
		}
	}

	function isHasClass(){
		if($("#partyIntroduce").hasClass("click") || $("#utonomy").hasClass("click")){
			showFirstMenu();
		}
	}

</script>