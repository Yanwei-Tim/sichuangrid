<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<@s.include value="/includes/baseInclude.jsp" />
<script type="text/template" id="relationshipTPL">
	<div class="comprehensiveTreeBox">
	  {{each json as list l}}
	  <div class="{{ if list.children.length > 0 }}flLeft{{/if}} rootNode"
	  {{ if list.children.length > 1 }}
	        style="margin-top:{{(list.children.length-1)/2 * 54}}px"
	  {{/if}}
	  >
	    <div class="rootNodes" unitLevel="{{list.unitLevel}}" nodeId="{{list.id}}" nodeType="{{list.type}}">
	        <b> {{list.name}}</b>
	        <p>{{list.message}}</p>
	    </div>
	  </div>
	  {{ if list.children.length > 0 }}
	  <div class="node1ont ovAuto">
	      {{each list.children as obj i}}
	          <div class="node2Item subNode" detailType="{{obj.detailType}}" detailSearchType="{{obj.detailSearchType}}" detailSearchField="{{obj.detailSearchField}}" detailRelationId="{{obj.detailRelationId}}" detailRelationType="{{obj.detailRelationType}}">
	              <div class="node2strong" unitLevel="{{obj.unitLevel}}">
	              	{{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>房屋</span></strong></div>
		                  <div class="node2P">
		                    <p>地址：{{obj.address}} </p>
		                    <p>产权人：{{obj.name}} &nbsp;&nbsp;&nbsp; 产权人证件号码：{{obj.certificateNumbe}}</p>
		                    <a href="javascript:;" class="look" nodeId="{{obj.keyId}}" nodeType="{{obj.detailSearchType}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@BUILDING_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>楼宇</span></strong></div>
		                  <div class="node2P">
		                    <p>楼宇名称：{{obj.buildingName}} &nbsp;&nbsp;&nbsp; 地址：{{obj.address}}</p>
		                    <p>楼宇业主：{{obj.owner}} &nbsp;&nbsp;&nbsp; 楼宇类型：<formatterProperty name="builddatasType" value="{{obj.type}}"></formatterProperty></p>
		                    <a href="javascript:;" class="look" nodeId="{{obj.keyId}}" nodeType="{{obj.detailSearchType}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@SERVICERECORD_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>服务记录</span></strong></div>
		                  <div class="node2P">
		                    <p>服务地点：{{obj.occurPlace}} &nbsp;&nbsp;&nbsp; 服务时间：<formatterProperty format="yyyy-MM-dd" value="{{obj.occurDate}}"></formatterProperty></p>
		                    <p>服务参与人：{{obj.serviceJoiners}} &nbsp;&nbsp;&nbsp; 服务内容：{{obj.serviceContent}}</p>
		                    <a href="javascript:;" class="look" nodeId="{{obj.objectId}}" nodeType="{{obj.detailSearchType}}" populationType="{{obj.objectType}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@PLACE_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>单位场所</span></strong></div>
		                  <div class="node2P">
		                    <p>名称：{{obj.name}} &nbsp;&nbsp;&nbsp; 是否关注：<formatterProperty name="isEmphasis" value="{{obj.isEmphasis}}"></formatterProperty></p>
		                    <p>类型：<formatterProperty name="placeType" value="{{obj.type}}"></formatterProperty>-<formatterProperty name="placeClassification" value="{{obj.classification}}"></formatterProperty>-<formatterProperty name="placeCategory" value="{{obj.category}}"></formatterProperty> &nbsp;&nbsp;&nbsp; 地址：{{obj.address}}</p>
		                    <a href="javascript:;" class="look" nodeId="{{obj.keyId}}" nodeType="{{obj.detailSearchType}}" key="{{obj.classificationen}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@DUSTBIN_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>城市部件</span></strong></div>
		                  <div class="node2P">
		                    <p>部件标识码：{{obj.dustbinCode}} &nbsp;&nbsp;&nbsp; 地址：{{obj.address}}</p>
		                    <p>部件类型：<formatterProperty name="partType" value="{{obj.partType}}"></formatterProperty> &nbsp;&nbsp;&nbsp; 部件名称：<formatterProperty name="partName" value="{{obj.partName}}"></formatterProperty></p>
		                    <a href="javascript:;" class="look" nodeId="{{obj.keyId}}" nodeType="{{obj.detailSearchType}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>事件处理</span></strong></div>
		                  <div class="node2P">
		                    <p>事件名称：{{obj.subject}} &nbsp;&nbsp;&nbsp; 发生地址：{{obj.occurLocation}}</p>
		                    <p>事件编号：{{obj.serialNumber}} &nbsp;&nbsp;&nbsp; 发生时间：<formatterProperty format="yyyy-MM-dd" value="{{obj.occurDate}}"></formatterProperty></p>
		                    <a href="javascript:;" class="look" nodeId="{{obj.keyId}}" nodeType="{{obj.detailSearchType}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUEEVALUATE_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>评分情况</span></strong></div>
		                  <div class="node2P">
		                    <p>评价类型：<formatterProperty name="evaluateType" value="{{obj.evaluateType}}"></formatterProperty> &nbsp;&nbsp;&nbsp; 评价简述：{{obj.evaluateContent}}</p>
		                    <a href="javascript:;" class="look" detailType="list"  nodeId="{{obj.issueId}}" nodeType="{{obj.detailSearchType}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSEHOLDFAMILY_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>户籍信息</span></strong></div>
		                  <div class="node2P">
		                    <p>户号：{{obj.accountNumber}} &nbsp;&nbsp;&nbsp; 户主姓名：{{obj.householdName}} </p>
		                    <p>户主身份证号码：{{obj.keyIdCardNo}} </p>
		                    <a href="javascript:;" class="look" detailType="list"  nodeId="{{obj.keyId}}" nodeType="{{obj.detailSearchType}}" organizationId="{{obj.orgId}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@PRIMARYMEMBERS_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>成员库</span></strong></div>
		                  <div class="node2P">
		                    <p>所在组织名称：{{obj.detailName}} &nbsp;&nbsp;&nbsp; 所在网格：<formatterProperty name="orgName" value="{{obj.orgId}}"></formatterProperty> </p>
		                    <p>姓名：{{obj.name}} &nbsp;&nbsp;&nbsp; 身份证：{{obj.idCardNo}} </p>
		                    <a href="javascript:;" class="look" detailType="list"  nodeId="{{obj.keyId}}" nodeType="{{obj.detailSearchType}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@PARTYMEMBERS_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>党员</span></strong></div>
		                  <div class="node2P">
		                    <p>所在党支部：{{obj.detailName}} &nbsp;&nbsp;&nbsp; 职位：<formatterProperty name="partyPosition" value="{{obj.partyPosition}}"></formatterProperty></p>
		                    <p>姓名：{{obj.name}} &nbsp;&nbsp;&nbsp; 身份证：{{obj.idCardNo}} </p>
		                    <a href="javascript:;" class="look" nodeId="{{obj.keyId}}" nodeType="{{obj.detailSearchType}}" partyorgType="{{obj.partyorgType}}" organizationId="{{obj.orgId}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	 {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ACCOUNT_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>三本台账</span></strong></div>
		                  <div class="node2P">
		                    <p>三本台账编号：{{obj.serialNumber}} &nbsp;&nbsp;&nbsp; </p>
		                    <p>情况描述：{{obj.conditionDescription}} </p>
		                    <a href="javascript:;" class="look" nodeId="{{obj.keyId}}" nodeType="{{obj.detailSearchType}}" partyorgType="{{obj.partyorgType}}" organizationId="{{obj.orgId}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              	  {{ if "<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@POPULATION_KEY'/>"==obj.detailSearchType }}
	              		<div class="node2Content">
		                  <div class="node2Title"><strong><span>人员</span></strong></div>
		                  <div class="node2P">
		                    <p>姓名：{{obj.name}} &nbsp;&nbsp;&nbsp; 性别：<formatterProperty name="gender"  value="{{obj.gender}}"></formatterProperty> &nbsp;&nbsp;&nbsp; 出生日期：<formatterProperty format="yyyy-MM-dd" value="{{obj.birthday}}"></formatterProperty></p>
		                    <p>身份证：{{obj.idCardNo}} &nbsp;&nbsp;&nbsp; 常驻地址：{{obj.address}}</p>
		                    <a href="javascript:;" class="look" detailType="list"  nodeId="{{obj.keyId}}" nodeType="{{obj.dataType}}">查看详情>></a>
		                  </div>
		                </div>
	              	 {{/if}}
	              </div>
	          </div>
	      {{/each}}
	    {{ if list.children.length>1 }}
	      <span class="slideLine"></span>
	    {{/if}}
	  </div>
	  {{/if}}
	  {{/each}}
	</div>
</script>

<script type="text/template" id="houseSearchTPL">
	<ul noImgUrl="${path}/resource/tqSearch/img/noLocationHead.png">
		{{each json as obj l}}
			<li id="{{obj.keyId}}" type="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_KEY'/>" lon="{{obj.lon}}" lat="{{obj.lat}}" lon2="{{obj.lon2}}" lat2="{{obj.lat2}}" featureId="{{obj.featureId}}" showName="{{obj.address}}">
				<a href="javascript:;" class="pic"><img  src="{{obj.imgUrl}}"></a>
                   <div class="zjsScreenLi cf">
                      <div class="zjsinformation">
                            <a href="javascript:;" class="name" id="tqSearchLiTitle">{{obj.address}}</a>
                            <span>居住人数:<span>{{obj.memberNum}}</span></span>
                            <b></b>
                            <span class="sq hj"><formatterProperty name="isRentalHouse" value="{{obj.isRentalHouse}}"></formatterProperty></span>
                      </div>
                      <div class="zjsinformation">
                            <span>录入时间：<span><formatterProperty format="yyyy-MM-dd HH:mm:ss" value="{{obj.createDate}}"></formatterProperty></span></span>
                            所属网格：<span class="sq">{{obj.orgName}}</span>
                      </div>
                      <div class="zjsinformation">
                      		<span>产权人:<span>{{obj.name}}</span></span>
                            <span>产权人类型:<span><formatterProperty name="propertyTypes" value="{{obj.propertyTypes}}"></formatterProperty></span></span>
                            {{ if obj.lon>0 }}
                            <a href="javascript:;" id="tqSearchMap" class="marker">地图</a>
                             {{/if}}
                            <form method="post" id="relationshipForm{{obj.id}}">
                            	<input type="hidden" name="type" value="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_KEY'/>">
                            	<input type="hidden" name="ids" value="{{obj.id}}">
                            	<@pop.JugePermissionTag ename="peopleManageSystem">
                            		<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@POPULATION_KEY'/>" value="housePopulation">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="viewBuilddatas">
									<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@BUILDING_KEY'/>" value="houseBuilding">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="viewServiceRecord">
									<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@SERVICERECORD_KEY'/>" value="houseServiceRecord">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="newViewCompanyPlace">
									<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@PLACE_KEY'/>" value="housePlace">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="viewDustbin">
								<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@DUSTBIN_KEY'/>" value="houseDustbin">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="serviceWork">
									<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_KEY'/>" value="houseIssue">
								</@pop.JugePermissionTag>
                            </form>
                            <a href="javascript:;" id="tqSearchRelationship" class="showBottom">快速预览</a>
                      </div>
                      <div class="administra">
                         <div id="relationshipDiv{{obj.id}}" class="administrativeLevel cf"></div>
                      </div>
                   </div>
                </li>
		{{/each}}
	</ul>
</script>

<script type="text/template" id="houseSearchMapTPL">
	<ul class="resultlist">
		{{each json as obj l}}
			<li dbId="{{obj.id}}" id="{{obj.keyId}}" type="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_KEY'/>" lon="{{obj.lon}}" lat="{{obj.lat}}" lon2="{{obj.lon2}}" lat2="{{obj.lat2}}" featureId="{{obj.featureId}}">
				<div class="resAbc"><img src="<formatterProperty name='imgIcon' value='{{l}}'></formatterProperty>"></div>
				<div class="title clearfix"><a href="javascript:;" id="mapRowTitle">地址：{{obj.address}}</a>|<a href="javascript:;" id="mapRowGoTo">定位</a></div>
				<div>
					<div class="clearfix">
						<p> 是否出租房：<formatterProperty name="isRentalHouse" value="{{obj.isRentalHouse}}"></formatterProperty></p>
						<p> 居住人数：{{obj.memberNum}}</p>
					</div>
				</div>
				<div class="gisBtnList"></div>
			</li>
		{{/each}}
	</ul>
</script>

<script type="text/template" id="issueSearchTPL">
	<ul>
		{{each json as obj l}}
			<li id="{{obj.keyId}}" type="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_KEY'/>" lon="{{obj.lon}}" lat="{{obj.lat}}" lon2="{{obj.lon2}}" lat2="{{obj.lat2}}" featureId="{{obj.featureId}}" showName="{{obj.subject}}">
				<a href="javascript:;" class="pic"><img src="{{obj.imgUrl}}"></a>
                   <div class="zjsScreenLi cf">
                      <div class="zjsinformation">
                            <a href="javascript:;" class="name"  id="tqSearchLiTitle">{{obj.subject}}</a>
                            <span>发生时间:<span><formatterProperty format="yyyy-MM-dd" value="{{obj.occurDate}}"></formatterProperty></span></span>
                            <span>事件编号:<span>{{obj.serialNumber}}</span></span>
                            <b></b>
                            <span class="sq hj">
	                            <formatterProperty name="issueTypeDomain" value="{{obj.issueTypeDomainId}}"></formatterProperty>
	                            ->
	                            <formatterProperty name="issueType" value="{{obj.issueTypeId}}"></formatterProperty>
	                        </span>
                      </div>
                      <div class="zjsinformation">
                      		<span>录入时间：<span><formatterProperty format="yyyy-MM-dd HH:mm:ss" value="{{obj.createDate}}"></formatterProperty></span></span>
                            <span>发生地点：<span>{{obj.occurLocation}}</span></span>
                            <span>发生网格：<span class="sq">{{obj.occurOrgName}}</span></span>
                      </div>
                      <div class="zjsinformation">
                            <span>事件规模：<span><formatterProperty name="issueKind" value="{{obj.issueKind}}"></formatterProperty></span></span>
                            <span>涉及人数：<span>{{obj.relatePeopleCount}}</span></span>
                            <span>处理网格：<span class="sq">{{obj.targetOrgName}}</span></span>
                            {{ if obj.lon>0 }}
                            <a href="javascript:;" id="tqSearchMap" class="marker">地图</a>
                            {{/if}}
                            <form method="post" id="relationshipForm{{obj.id}}">
                            	<input type="hidden" name="ids" value="{{obj.id}}">
                            	<input type="hidden" name="type" value="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_KEY'/>">
                            	<@pop.JugePermissionTag ename="peopleManageSystem">
                            		<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@POPULATION_KEY'/>" value="issuePopulation">
								</@pop.JugePermissionTag>
                            	<@pop.JugePermissionTag ename="viewActualHouse">
									<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_KEY'/>" value="issueHouse">
								</@pop.JugePermissionTag>
                            	<@pop.JugePermissionTag ename="gradeHistoryCheckGradeIssue">
									<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUEEVALUATE_KEY'/>" value="issueEvaluate">
								</@pop.JugePermissionTag>
                            </form>
                            <a href="javascript:;" id="tqSearchRelationship" class="showBottom">快速预览</a>
                      </div>
                      <div class="administra">
                         <div id="relationshipDiv{{obj.id}}" class="administrativeLevel cf"></div>
                      </div>
                   </div>
                </li>
		{{/each}}
	</ul>
</script>

<script type="text/template" id="issueSearchMapTPL">
	<ul class="resultlist">
		{{each json as obj l}}
			<li dbId="{{obj.id}}" id="{{obj.keyId}}" type="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_KEY'/>" lon="{{obj.lon}}" lat="{{obj.lat}}" lon2="{{obj.lon2}}" lat2="{{obj.lat2}}" featureId="{{obj.featureId}}">
				<div class="resAbc"><img src="<formatterProperty name='imgIcon' value='{{l}}'></formatterProperty>"></div>
				<div class="title clearfix"><a href="javascript:;" id="mapRowTitle">名称：{{obj.subject}}</a>|<a href="javascript:;" id="mapRowGoTo">定位</a></div>
				<div>
					<div class="clearfix">
						<p> 发生时间：<formatterProperty format="yyyy-MM-dd" value="{{obj.occurDate}}"></formatterProperty></p>
						<p> 事件编号：{{obj.serialNumber}}</p>
					</div>
				</div>
				<div class="gisBtnList"></div>
			</li>
		{{/each}}
	</ul>
</script>

<script type="text/template" id="placeSearchMapTPL">
	<ul class="resultlist">
		{{each json as obj l}}
			<li dbId="{{obj.id}}" id="{{obj.keyId}}" type="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@PLACE_KEY'/>" lon="{{obj.lon}}" lat="{{obj.lat}}" lon2="{{obj.lon2}}" lat2="{{obj.lat2}}" featureId="{{obj.featureId}}" key="{{obj.type}}">
				<div class="resAbc"><img src="<formatterProperty name='imgIcon' value='{{l}}'></formatterProperty>"></div>
				<div class="title clearfix"><a href="javascript:;" id="mapRowTitle">名称：{{obj.name}}</a>|<a href="javascript:;" id="mapRowGoTo">定位</a></div>
				<div>
					<div class="clearfix">
						<p> 地址：{{obj.address}}</p>
						<p> 类型：<formatterProperty name='placeType' value='{{obj.type}}'></formatterProperty></p>
					</div>
				</div>
				<div class="gisBtnList"></div>
			</li>
		{{/each}}
	</ul>
</script>

<script type="text/template" id="populationSearchTPL">
	<ul noImgUrl="${path}/resource/tqSearch/img/noPopulationHead.png">
		{{each json as obj l}}
			<li id="{{obj.keyId}}" type="{{obj.dataType}}" lon="{{obj.lon}}" lat="{{obj.lat}}" lon2="{{obj.lon2}}" lat2="{{obj.lat2}}" featureId="{{obj.featureId}}" showName="{{obj.name}}">
				<a href="javascript:;" class="pic"><img src="{{obj.imgUrl}}"></a>
                   <div class="zjsScreenLi cf">
                      <div class="zjsinformation">
                      		<a href="javascript:;" class="name" id="tqSearchLiTitle">{{obj.name}}</a>
                            <span>性别:<span><formatterProperty name="gender"  value="{{obj.gender}}"></formatterProperty></span></span>
                            <span>出生日期:<span><formatterProperty format="yyyy-MM-dd" value="{{obj.birthday}}"></formatterProperty></span></span>
                            <span>身份证:<span>{{obj.idCardNo}}</span></span>
                            <b></b>
                            <span class="sq hj"><formatterProperty name="dataType" value="{{obj.dataType}}"></formatterProperty></span>
                      </div>
                      <div class="zjsinformation">
                            <span>常驻地址:<span>{{obj.address}}</span></span>
                            <span>录入时间:<span><formatterProperty format="yyyy-MM-dd HH:mm:ss" value="{{obj.createDate}}"></formatterProperty></span></span>
                            <span class="sq">{{obj.orgName}}</span>
                      </div>
                      <div class="zjsinformation">
                            <span>联系方式:<span>{{obj.mobileNumber}}</span></span>
                            <span><img src="${resource_path }/resource/tqSearch/img/phone.png"><span>{{obj.telephone}}</span></span>
                            {{ if obj.lon>0 }}
                            <a href="javascript:;" id="tqSearchMap" class="marker">地图</a>
                            {{/if}}
                            <form method="post" id="relationshipForm{{obj.id}}">
                            	<input type="hidden" name="type" value="<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@POPULATION_KEY'/>">
                            	<input type="hidden" name="ids" value="{{obj.id}}">
                            	<@pop.JugePermissionTag ename="viewHouseFamilyInfo">
								<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSEHOLDFAMILY_KEY'/>" value="populationHouseholdFamily">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="viewActualHouse">
								<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@HOUSE_KEY'/>" value="populationHouse">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="viewBuilddatas">
								<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@BUILDING_KEY'/>" value="populationBuilding">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="viewServiceRecord">
								<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@SERVICERECORD_KEY'/>" value="populationServiceRecord">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="myIssueListManagement">
								<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ISSUE_KEY'/>" value="populationIssue">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="viewPrimaryMembers">
								<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@PRIMARYMEMBERS_KEY'/>" value="populationPrimaryMember">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="viewOrgansPartyMember">
								<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@PARTYMEMBERS_KEY'/>" value="populationPartyMember">
								</@pop.JugePermissionTag>
								<@pop.JugePermissionTag ename="account">
								<input type="hidden" name="searchs.<@s.property value='@com.tianque.plugin.tqSearch.constants.TqSearchType@ACCOUNT_KEY'/>" value="populationAccount">
								</@pop.JugePermissionTag>
                            </form>
                            <a href="javascript:;" id="tqSearchRelationship" class="showBottom">快速预览</a>
                      </div>
                      <div class="administra">
                         <div id="relationshipDiv{{obj.id}}" class="administrativeLevel cf"></div>
                      </div>
                   </div>
                </li>
		{{/each}}
	</ul>
</script>

<script type="text/template" id="populationSearchMapTPL">
	<ul class="resultlist">
		{{each json as obj l}}
			<li dbId="{{obj.id}}" id="{{obj.keyId}}" type="{{obj.dataType}}" lon="{{obj.lon}}" lat="{{obj.lat}}" lon2="{{obj.lon2}}" lat2="{{obj.lat2}}" featureId="{{obj.featureId}}">
				<div class="resAbc"><img src="<formatterProperty name='imgIcon' value='{{l}}'></formatterProperty>"></div>
				<div class="title clearfix"><a href="javascript:;" id="mapRowTitle">名称：{{obj.name}}</a>|<a href="javascript:;" id="mapRowGoTo">定位</a></div>
				<div>
					<div class="clearfix">
						<p> 身份证：{{obj.idCardNo}}</p>
						<p> 出生日期：<formatterProperty format="yyyy-MM-dd" value="{{obj.birthday}}"></formatterProperty></p>
					</div>
				</div>
				<div class="gisBtnList"></div>
			</li>
		{{/each}}
	</ul>
</script>
