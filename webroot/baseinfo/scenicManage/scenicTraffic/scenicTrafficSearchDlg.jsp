<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchScenicTrafficForm">
	<div class="container container_24">
		<div class="grid_4 lable-right">
			<label class="form-lbl">起点：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" name="searchScenicTrafficVo.startAddress" class="form-txt"/>
		</div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">终点：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" name="searchScenicTrafficVo.endAddress" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">类型：</label>
		</div>
		<div class="grid_8">
	    	<select name="searchScenicTrafficVo.trafficType.id" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCENICTRAFFIC_TYPES"/>
			</select>
		</div>    
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchScenicTrafficVo.phone" class="form-txt" />
		</div>    
		<div class="grid_4 lable-right">
			<label class="form-lbl">线路名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="searchScenicTrafficVo.trafficName" class="form-txt" style="width: 99%;" />
		</div>    
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">负责单位：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="searchScenicTrafficVo.managerUnit" class="form-txt" style="width: 99%;" />
		</div>    
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">负责人：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchScenicTrafficVo.managerPeople" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">负责人电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchScenicTrafficVo.managerPeoplePhone" class="form-txt" />
		</div>
		<div class="grid_4 lable-right hidden">
            <label class="form-lbl">最早班车时间：</label>
        </div>
        <div class="grid_8 hidden">
        	<input	type="text" id="searchScenicTrafficVoFirstBusTime" name="searchScenicTrafficVo.firstBusTime" class="form-txt">
        </div>
        <div class="grid_4 lable-right hidden">
            <label class="form-lbl">最晚班车时间：</label>
        </div>
        <div class="grid_8 hidden">
        	<input	type="text" id="searchScenicTrafficVoLastBusTime" name="searchScenicTrafficVo.lastBusTime" class="form-txt">
        </div>
       	<div class="grid_4 lable-right">
  			<label class="form-lb1">关注状态：</label>
	   	</div>
	   	<div class="grid_8">
			<select id="isLock" class="form-txt" >
				<option value="" selected="selected">全部</option>
				<option value="false">现在关注</option>
				<option value="true">曾经关注</option>
			</select>
		</div>
    </div>
</form>