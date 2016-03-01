<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div>
	<div id="orSearchFields">
		<input type="hidden" name="tqSearchVo.searchFields" value="name,idCardNo" title="姓名,身份证号">
	</div>
	<div id="andSearchFields" class="ScreenListLay">
		<div class="zjsScreenList">
			<div class="listName"><strong>人口信息：</strong></div>
			<div class="listLay"><span class="on all" >全部</span>
			<@pop.JugePermissionTag ename="viewHouseholdStaff">
			<span value="householdStaff">户籍人口</span>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="viewFloatingPopulation">	
			<span value="floatingPopulation">流动人口</span>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="viewUnsettledPopulation">	
			<span value="unsettledPopulation">未落户人口</span>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename="viewOverseaPerson">
			<span value="overseaStaff">境外人员</span>
			</@pop.JugePermissionTag>
			<a href="javascript:;" id="showAll">展开</a><a href="javascript:;" id="hideAll" style="display:none;">收缩</a></div>
			<input type="hidden" name="tqSearchVo.searchs.dataType" id="populationDataType">
		</div>
		<div class="zjsScreenList notOverseaStaffSearch">
			<div class="listName"><strong>业务信息：</strong></div>
			<div class="listLay">
				<span class="on">全部</span>
				<@s.iterator value="@com.tianque.plugin.tqSearch.constants.PopulationType@populationType" id="st">
						<span value="*${key}*">${value}</span>
				</@s.iterator>				
				<a href="javascript:;" class="multiSelect">多选</a>
				<a href="javascript:;" class="more">更多</a>
			</div>
			<div class="listLaySelect cf">
				<ul>
					<@s.iterator value="@com.tianque.plugin.tqSearch.constants.PopulationType@populationType" id="st">
					 	<li><label><input type="checkbox" value="*${key}*"><span>${value}</span></label></li>
					</@s.iterator>
				</ul>
				<div class="selectBottom">
				    <input type="button" class="determine" value="确定"/>
				    <input type="button" class="cancel" value="取消"/>
				</div>
			</div>
			<input type="hidden" name="tqSearchVo.searchs.populationTypes">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>状态：</strong></div>
			<div class="listLay"><span class="on">全部</span><span name="tqSearchVo.searchs.logOut" value=1>注销</span><span name="tqSearchVo.searchs.isDeath" value=1>死亡</span></div>
			<input type="hidden" >
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>性别：</strong></div>
			<div class="listLay"><span class="on">全部</span><@pop.PropertyDictShowTag jspNode="<span value='{id}'>{displayName}</span>" domainName="@com.tianque.domain.property.PropertyTypes@GENDER"/></div>
			<input type="hidden" name="tqSearchVo.searchs.gender">
		</div>
		<div class="zjsScreenList">
			<div class="listName"><strong>年龄：</strong></div>
			<div class="listLay">
				<div style="float:left">
					<span  class="on">全部</span><span value="[NOW-6YEAR TO *]">0-6岁</span><span value="[NOW-18YEAR TO NOW-6YEAR]">6-18岁</span><span value="[NOW-25YEAR TO NOW-18YEAR]">18-25岁</span></span><span value="[NOW-35YEAR TO NOW-25YEAR]">25-35岁</span></span><span value="[NOW-44YEAR TO NOW-35YEAR]">35-44岁</span></span><span value="[NOW-59YEAR TO NOW-44YEAR]">44-59岁</span></span><span value="[NOW-89YEAR TO NOWW-59YEAR]">59-89岁</span></span><span value="[NOW-99YEAR TO NOW-89YEAR]">89-99岁</span></span><span value="[* TO NOW-99YEAR]">99岁以上</span>
				</div>
				<div class="range" style="float:left" rangeType="NOW-{}YEAR">
					<input type="text" class="age max" > - <input type="text" class="age min" ><input type="button" class="agesubmit" value="确定">
				</div>
			</div>
			<input type="hidden" name="tqSearchVo.searchs.birthday">
		</div>
		<div class="zjsScreenList notOverseaStaffSearch">
			<div class="listName"><strong>文化程度：</strong></div>
			<div class="listLay"><span class="on">全部</span><@pop.PropertyDictShowTag jspNode="<span value='{id}'>{displayName}</span>" domainName="@com.tianque.domain.property.PropertyTypes@SCHOOLING"/></div>
			<input type="hidden" name="tqSearchVo.searchs.schooling">
		</div>
		<div class="zjsScreenList notOverseaStaffSearch">
			<div class="listName"><strong>婚姻状况：</strong></div>
			<div class="listLay"><span class="on">全部</span><@pop.PropertyDictShowTag jspNode="<span value='{id}'>{displayName}</span>" domainName="@com.tianque.domain.property.PropertyTypes@MARITAL_STATUS"/></div>
			<input type="hidden" name="tqSearchVo.searchs.maritalState">
		</div>
		<div class="zjsScreenList notOverseaStaffSearch">
			<div class="listName"><strong>政治面貌：</strong></div>
			<div class="listLay"><span class="on">全部</span><@pop.PropertyDictShowTag jspNode="<span value='{id}'>{displayName}</span>" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND"/></div>
			<a href="javascript:;" class="more">更多</a>
			<input type="hidden" name="tqSearchVo.searchs.politicalBackground">
		</div>
		<div class="zjsScreenList notOverseaStaffSearch">
			<div class="listName"><strong>户籍地：</strong></div>
			<div class="listLay"><span class="on">全部</span>
				<select id="populationProvince" class='age' />
				<select id="populationCity" class='age' />
				<select id="populationDistrict" class='age' />
				<input type="hidden" name="tqSearchVo.searchs.censusRegister" id="censusRegister">
				</select>
				<div class="listName" style="float:none;display:inline-block;*display:inline;*zoom:1;"><strong>民族：</strong></div>
				<div class="listLay" style="padding-left:10px;display:inline-block;*display:inline;*zoom:1;"><span class="on">全部</span>
					<select name="tqSearchVo.searchs.nation"  class='age'>
				   		<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION"/>
					</select>
				</div>
			</div>
			
		</div>
	</div>
	<div id="orderFields" class="zjsLeftTop">排序：
	  <a name="tqSearchVo.sortFields.birthday" href="javascript:;">年龄</a>
	  <a name="tqSearchVo.sortFields.updateDate" href="javascript:;" >数据跟新时间</a>
	  <a name="tqSearchVo.sortFields.createDate" href="javascript:;" >录入时间</a>
	</div>
</div>
<script language="javascript">
$(document).ready(function(){
	$('.zjsScreenList').eq(0).show().siblings().hide();
	$('.zjsLeftTop').show();
	threeSelect({
		province:'populationProvince',
		city:'populationCity',
		district:'populationDistrict',
		provinceValue:'',
		cityValue:'',
		districtValue:''
	});
});
</script>	