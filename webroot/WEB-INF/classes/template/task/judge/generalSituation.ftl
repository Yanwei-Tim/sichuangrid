<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<style type="text/css">
.mySelect{width:80px;}
.mySelect1{width:180px;}
.myCheckBox{margin-left:10px;}
.myDivTop{margin-top:5px;}
#nav{
	clear:both;
}
#nav a,.btnbanner a{
	background:none;
}
#nav #btnbanner{
	float:left;
}
.dateSelectMod{
	float:left;
	margin:5px 0 0 20px;
}
.dateSelectMod .tit,.dateSelectMod .dateSelectItem,.tit{
	float:left;
	height: 28px;
	line-height: 26px;
	color:#333333;
	font:14px/28px 'Microsoft Yahei'
}
.dateSelectItem .dateItem{
	float:left
}
.dateSelectItem .dateMonth{
	margin-left:10px;
}
.dateSelectMod .dateSelectItem em{
	line-height:26px;
}
.taskMod{
	overflow:hidden;
}

</style>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<@s.include value="/common/orgSelectedTaskListComponent.jsp"/>
		<div class="dateSelectMod">
			<span class="tit">时间选择：</span>
			<div class="dateSelectItem">
				<span class="dateItem">
					<select id="itemSel" class="newChooseDate">
						<option>月</option>
						<option>季度</option>
						<option>年度</option>
					</select>
				</span>
				<span class="dateItem">
					<select id="itemSel2">
						<option>2015</option>
						<option>2014</option>
						<option>2013</option>
					</select>
					<em>年</em>
				</span>
				<span class="dateMonth dateItem">
					<select id="itemSel3">
						<option>9</option>
						<option>8</option>
						<option>10</option>
					</select>
					<em class="itemMonth">月</em>
					<em class="itemQuarter" style="display:none">季度</em>
				</span>
			</div>
			<!-- dateSelectItem End  -->
		</div>
		<!-- dateSelectMod End  -->
		<div class="clearfix"></div>
		<div class="taskMod">
			<span class="tit">任务类别：</span>
			<select id="taskCategory">
				<option>流动人员</option>
				<option>吸毒人员</option>
				<option>刑释人员</option>
			</select>
			
		</div>
		<!-- taskMod End -->
	</div>
</div>
<div class="zt_tabs_style">
	<div id="chartsTabs">
		<ul>
			<li><a href="${path }/task/judge/histogram.ftl">柱状图</a></li>
			<li><a href="${path }/task/judge/pieChart.ftl">饼状图</a></li>
			<li><a href="${path }/task/judge/trendChart.ftl">趋势图</a></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	//select 优化
	$("#itemSel").selFun();
	$("#itemSel2").selFun();
	$("#itemSel3").selFun();
	$("#taskCategory").selFun();



	$("#chartsTabs").tabs();
	// 列表信息 和 柱图 饼图   外层容器计算高度
	$.sigmaReportLayout();
	$.loadingComp("close");
	$("#chartsTabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$("#chartsTabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
});
</script>