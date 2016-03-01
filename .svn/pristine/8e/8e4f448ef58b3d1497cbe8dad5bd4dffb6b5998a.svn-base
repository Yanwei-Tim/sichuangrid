<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<style>
.fontClass{
color: red;
font-size: medium;
font-weight: bold;
}
</style>
<div id="dialog-form" class="container container_20">
<img  src="${path }/resource/images/issueStatisticsRole1.png">
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">新增事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">各自层级，本月新增事件数量。（只是统计各自层级新增的数量，不统计流转的事件数量。村统计就是村这一层级全部新增数据）</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">上报事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">各自层级，本月点击上报后的事件数量。（比如：网格层级上报事件，统计的就是网格层级点击上报按钮的事件数量。）</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">上级交办事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">各自层级，本月收到上级交办的事件数量。（比如：村（社区）层级的数量显示是乡镇（街道）层级交办给村（社区）的数量显示。）</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">办理中事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">各自层级截止本月没有结案的事件。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">待验证事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">各自层级本月还没有验证的事件。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">已验证事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">谁新增谁验证。记录的是谁新增并且谁验证事件。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">办结事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">谁点击结案，就统计到谁的层级。（前提条件是点击结案以后，必须由新增事件的层级验证以后才会显示）</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">事件工作总量：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">记录个层级办理事件的工作量。各自层级本月新增、上报、交办、回退、结案的事件操作次数。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">超期在办：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">在超出规定办理时间后还没有结案并验证的事件，（收到黄牌、红牌后还在办理的事件）。同一个事件会有重复统计，因为存在层级流转。比如：村新增事件被红牌了，上报到街道，街道也被红牌了，</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">街道在回退或者交办到村，村再次被红牌，这个时候统计的就是村一条红牌，街道一条红牌，如果交办给另一个村，被红牌了，村这个层级就是2个红牌，因为涉及2个村被发牌。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">超期办结：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">在超出规定办理时间后结案并验证的事件。（村新增事件被红牌了，上报了，乡镇在被红牌以后，结案了，这个时候村这个层级算一条、乡镇这个层级算一条。）</label>
</div>
<div class='clearLine'>&nbsp;</div>
<hr/>
<img  src="${path }/resource/images/issueStatisticsRole2.png">
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">新增事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">本级及下辖，本月新增的事件数量。（不包含相互流转的事件，只是新增。比如街道:看见的就是街道新增的以及下辖新增的总数）</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">上报事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">本级及下辖，本月点击上报的事件数量。（比如XX街道的上报事件就是：这个街道的全部网格、村（社区）、乡镇（街道）点击上报按钮的事件数量。包含同一件事，
   			</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">从网格新增上报到社区在上报到街道再点击上报，这样就算3条上报。）</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">上级交办事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">上级交办到本级及下辖的事件数量。（比如：XX街道的上级交办事件就是：这个街道及这个街道下面的村（社区）、网格收到上级交办的事件数量相加。）</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">办理中事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">本级及下辖新增的事件，截止到本月还没结案的事件数量。 （比如：XX街道的在办事件就是：这个街道及下面的村（社区）、网格新增的事件还没有结案的事件数量相加）  </label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">待验证事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">各自区域截止本月还没有验证的事件。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">已验证事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">谁新增谁验证，前提条件结案后才可以验证。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">办结事件：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">谁点击结案，这个事件就显示在谁的层级。(前提条件，必须谁新增的层级去验证完毕）  </label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">事件工作总量：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">本级及下辖，处理事件的次数。(XX街道的事件总数就是：这个街道及下辖，事件流转的条数。比如:网格新增事件算一条，上报到村，网格算2条，村受理后在办理算一条，</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">这个条事件在上报到街道，街道不做任何办理。
   			此时按各区域统计街道的事件总数就是3条。如果街道受理后回退给村，这个事件总数就算4条。）</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">超期在办：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">本级及下辖，超过办理规定时间收到红、黄牌后的事件在本月还没有结案并验证的事件。所有的超期在办不管怎么流转都按照事件的唯一ID来统计，算一条。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">比如：村新增事件被发牌了，上报到镇上，镇上又被发牌，镇上在交办给村，村又被发牌了，超期在办就算一条。</label>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_2 lable-right">
   			<label class="form-lb1 fontClass">超期办结：</label>
</div>
<div class="grid_18 lable-right">
   			<label class="form-lb1">本级及下辖，超过办理规定时间收到红、黄牌后的事件已经结案的。统计同超期在办是一样的，不管怎么流转都按照事件的唯一ID来统计。</label>
</div>
<div class='clearLine'>&nbsp;</div>
</div>	
