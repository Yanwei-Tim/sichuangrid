<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="${path}/tqSearch/templateInclude.ftl" />
<div class="zjsSeekMain zjsSeekMainTop">
	<strong><img src="${resource_path }/resource/tqSearch/img/tylogo.png"></strong>
    <div class="zjsSeekNav">
      <div class="zjsSeekMainTopLay"id="tqSearchType">
			<ul class="cf">
          		<@pop.JugePermissionTag ename="peopleManageSystem">
              		<li><a href="javascript:;" key="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@POPULATION_KEY'/>"><@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@POPULATION_NAME'/></a></li>
              	</@pop.JugePermissionTag>
              	<@pop.JugePermissionTag ename="houseManageSystem">
              		<li><a href="javascript:;" key="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_KEY'/>"><@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_NAME'/></a></li>
              	</@pop.JugePermissionTag>
              	<@pop.JugePermissionTag ename="serviceWork">
              		<li><a href="javascript:;" key="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_KEY'/>"><@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_NAME'/></a></li>
              	</@pop.JugePermissionTag>
			</ul>
		</div>
        <div class="zjsSeekTextLay">
			<input id="tqSearchText" type="text" class="zjsSeekText" value="">
			<input id="resultSearchBtn" type="button" class="zjsSeek"  value="结果中搜索">
			<input id="tqSearchBtn" type="button" class="zjsSeek"  value="重新搜索">
			<span id="tqSearchTitle" class="title">请输入姓名、身份证号……</span>
		</div>
    </div>
</div>
<div class="zjsSearchLeft">
      <div class="search zjsHeadSet">
          <strong class="zjsHeadSetTop">热点搜索</strong>
          <div class="weiduduan clearfix">
             <div class="tabMoveLay" id="tqSearchHot">
                <!-- <a href="javascript:;">中国</a> -->
              </div>
          </div>
      </div>
      <div class="searchHistory zjsHeadSet">
          <strong class="zjsHeadSetTop">搜索历史</strong>
         <div class="weiduduan clearfix">
             <div class="tabMoveLay" id="tqSearchHis">
                 <!-- <a href="javascript:;">中国</a> -->
              </div>
          </div>
      </div>
    </div>
<div class="zjsSeekMainLay">
	<div class="zjsSeekLeft">
		    <div class="zjsScreen zjsScreenH">
		        <div class="zjsScreeTitle"><div class="sum">全部搜索结果（共<span id="tqSearchSum">0</span>个结果）</div><b>您选择了：<span id="tqSearchAllText">张三</span></b></div>
		        <div class="areaName">
					<div class="listName"><strong>所属区域：</strong></div>
					<div class="listLay"><span id="searchOrgContainer"><a href="javascript:;" id="searchOrgText">中国</a><b></b></span></div>
				</div>
		        <form id="tqSearchForm">
						<input type="hidden" name="type" id="searchType">
						<input type="hidden" name="tqSearchVo.orgId" id="searchOrgId"
							<@s.if test="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgType().getInternalId()==@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG">
									 text="<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getOrgName()" />" 
									 value='<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getId()"/>' />
							 </@s.if>
							 <@s.else>
								 	text="<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()" />" 
								 	value='<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getId()"/>' 
							</@s.else>
						/>
						<input type="hidden" name="tqSearchVo.searchText" id="searchText">
						<input type="hidden" id="orderFieldInput">
						<div id="tqSearchField"></div>
				</form>
		    </div>
		    <div class="zjsListLay">
		        <div class="zjsLeft" id="tqSearchList"></div>
				<div class="zjsPage" id="tqSearchListPager"></div>
		    </div>
	 <div>
</div>
<div id="detailViewDlg">
<script type="text/javascript">
<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<@pop.formatterProperty name="propertyTypes" domainName="@com.tianque.domain.property.PropertyTypes@PROPERTY_TYPES" />
<@pop.formatterProperty name="rentalType" domainName="@com.tianque.domain.property.PropertyTypes@LETTINGHOUSE_TYPE" />
<@pop.formatterProperty name="builddatasType" domainName="@com.tianque.domain.property.PropertyTypes@BUILDDATAS_TYPE" />

<@pop.formatterProperty name="placeType" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_TYPE" />
<@pop.formatterProperty name="placeClassification" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION" />
<@pop.formatterProperty name="placeCategory" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CATEGORY" />

<@pop.formatterProperty name="partType" domainName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_TYPE" />
<@pop.formatterProperty name="partName" domainName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME" />

<@pop.formatterProperty name="issueKind" domainName="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" />
<@pop.formatterProperty name="partyPosition" domainName="@com.tianque.domain.property.PropertyTypes@PARTY_POSITION" />

$(document).ready(function(){
	var parameters={
		searchText :"${sq}",
		searchType :"${sty}"
	}
	if(parameters.searchText) TQSearch["searchText"] = parameters.searchText;
	if(parameters.searchType) TQSearch["searchType"] = parameters.searchType;
	TQSearch.initTqSearchPanel();
	$("#searchOrgText").text($("#searchOrgId").attr("text"));
});
</script>
