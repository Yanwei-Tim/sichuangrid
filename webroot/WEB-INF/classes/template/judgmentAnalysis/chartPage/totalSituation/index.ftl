<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

        <div id="main" class="main">
            <div class="mCrumbs" id="mCrumbs">当前位置：研判分析 &gt; <a href="javascript:;">总况</a></div>
            <div id="mainBody" class="mainBody cf">
            
                <div class="mainTitle cf">
                    <div class="switchViewMode">
                        <a class="icon on" href="javascript:;" id="showGirdView"><span class="icon_list"></span>列表</a>
                        <a class="icon " href="javascript:;" id="showChartView"><span class="icon_pie"></span>图表</a>
                    </div>
                    <strong class="sTit">总况统计</strong>
                    <span class="sDate">（数据统计截止日期：2014-11-07 24:00）</span>
                </div>

                <div class="girdView" id="girdView">
                
                    <div class="modBox">
                        <div class="mHead">
                            <span class="endDate">（数据统计截止日期：2014-11-07 24:00）</span>
                            <strong class="mHeadTit">人口信息</strong>                           
                            <a class="report"  href="/login/judgmentAnalysis/module_two.jsp">查看分析报告</a>
                        </div>
                        <div class="mCont">
                        	<div class="jqGridWarp">
	                            <div id="treeGridRegisteredPopulation"></div>
                            </div>
                            <div class="modBox subModBox hidden">
                                <div class="mHead">
                                    <span class="endDate">（数据统计截止日期：2014-11-07 24:00）</span>
                                    <strong class="mHeadTit" id="houseHeadTit">户籍人口趋势分析</strong>
                                </div>
                                <div class="mCont">
		                        	<div class="jqGridWarp">
	                                    <div id="gridSpecialCrowd"></div>
	                                    <div id="gridCaringPeople"></div>
                                    </div>
                                </div>
                                <a href="javascript:;" class="delSubModBox">×</a>
                            </div>
                        </div>
                    </div> 
                    
                    <div class="modBox">
                        <div class="mHead">
                            <#--<span class="endDate">（数据统计截止日期：2014-11-07 24:00）</span>-->
                            <strong class="mHeadTit">房屋信息</strong>
                            <a class="report" href="javascript:;" >查看分析报告</a>
                        </div>
                        <div class="mCont">正在开发中...
                        <#--
                            <div class="modBox subModBox">
                                <div class="mHead">
                                    <span class="endDate">（数据统计截止日期：2014-11-07 24:00）</span>
                                    <strong class="mHeadTit">户籍人口趋势分析</strong>
                                </div>
                                <div class="mCont">
                                    <div id="treeGridHousing"></div>
                                </div>

                                <a href="javascript:;" class="delSubModBox">×</a>
                            </div>
                            
                          -->
                        </div>                       
                    </div>
                    
                    
                    <div class="modBox">
                        <div class="mHead">
                            <#--<span class="endDate">（数据统计截止日期：2014-11-07 24:00）</span>-->
                            <strong class="mHeadTit">事件处理</strong>
                            <a class="report" href="javascript:;" >查看分析报告</a>
                        </div>
                        <div class="mCont" >正在开发中...
                        <#--
                            <div class="modBox subModBox">
                                <div class="mHead">
                                    <span class="endDate">（数据统计截止日期：2014-11-07 24:00）</span>
                                    <strong class="mHeadTit">户籍人口趋势分析</strong>
                                </div>
                                <div class="mCont">
                                    <div id="treeGridIssue"></div>
                                </div>

                                <a href="javascript:;" class="delSubModBox">×</a>
                            </div>
                            
                          -->
                        </div>                        
                    </div>
                                   
                   <div class="modBox">
                        <div class="mHead">
                            <#--<span class="endDate">（数据统计截止日期：2014-11-07 24:00）</span>-->
                            <strong class="mHeadTit">单位场所</strong>
                            <a class="report" href="javascript:;" >查看分析报告</a>
                        </div>
                        <div class="mCont">正在开发中...
                        <#--
                            <div class="modBox subModBox">
                                <div class="mHead">
                                    <span class="endDate">（数据统计截止日期：2014-11-07 24:00）</span>
                                    <strong class="mHeadTit">户籍人口趋势分析</strong>
                                </div>
                                <div class="mCont">
                                    <div id="treeGridPlace"></div>
                                </div>

                                <a href="javascript:;" class="delSubModBox">×</a>
                            </div>
                            
                          -->
                        </div>                        
                    </div>
                                    
                                      
               </div>

               <div class="chartView hidden" id="chartView">
               
                  <#--
                    <div class="mainTitle">
                        <div class="switchViewMode cf">
                          <a class="icon" href="javascript:;" id="showEditMode"><span class="icon_setting"></span>设置</a>
                        </div>
                    </div>
                  -->
                    
                    <div class="modBox" style='width:100%'>
                        <div class="mHead">
                            <div class="switchViewMode">
                                <a class="icon icon_del" href="javascript:;"></a>
                            </div>
                            <strong class="mHeadTit">人口总况</strong>
                        </div>
                        <div class="mCont">
                            <div id="chartPopulationInfo" class="chartsBox"></div>
                            
                        </div>
                    </div>
                <#-- 
                    <div class="modBox right">
                        <div class="mHead">
                            <div class="switchViewMode">
                                <a class="icon icon_del" href="javascript:;"></a>
                            </div>
                            <strong class="mHeadTit">人口业务信息总况</strong>
                        </div>
                        <div class="mCont">
                            <div id="chartBusPopulationInfo" class="chartsBox"></div>
                        </div>
                    </div>
               -->      
                    <div class="modBox ">
                        <div class="mHead">
                            <div class="switchViewMode">
                                <a class="icon icon_del" href="javascript:;"></a>
                            </div>
                            <strong class="mHeadTit">房屋总况</strong>
                        </div>
                        <div class="mCont">
                            <span class='noData'>正在开发中...</span>
                            <div id="chartHouseInfo" class="chartsBox"></div>
                        </div>
                    </div>

                    <div class="modBox right">
                        <div class="mHead">
                            <div class="switchViewMode">
                                <a class="icon icon_del" href="javascript:;"></a>
                            </div>
                            <strong class="mHeadTit">事件总况</strong>
                        </div>
                        <div class="mCont">
                             <span class='noData'>正在开发中...</span>
                            <div id="chartEventDetails" class="chartsBox"></div>
                        </div>
                    </div>
                    
                    <div class="modBox ">
                        <div class="mHead">
                            <div class="switchViewMode">
                                <a class="icon icon_del" href="javascript:;"></a>
                            </div>
                            <strong class="mHeadTit">单位场所</strong>
                        </div>
                        <div class="mCont" >
                            <span class='noData'>正在开发中...</span>
                            <div id="chartPlaceDetails" class="chartsBox"></div>
                        </div>
                    </div>
                    
                </div>
                
                
            </div>           
        </div>
    
    
    
	<script src="/resource/judgmentAnalysis/js/chartPage.js"></script>
 