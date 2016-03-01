<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %> 
    <jsp:include page="/includes/baseInclude.jsp" />
    <div id="dialog-form" title="图层管理" class="container container_24" style="width:auto;height:auto;">
        <form name="maintainForm" id="maintainForm" method="post" action="">
        	<input type="hidden" id="mode" value="${mode }"/>
            <input type="hidden" name="gis2DLayer.id" value="${ gis2DLayer.id}"/>
            <input type="hidden" name="gis2DLayer.points" value="${ gis2DLayer.points}"/>
            <input type="hidden" name="gis2DLayer.organization.id" value="${gis2DLayer.organization.id }" />
            <input type="hidden" name="gis2DLayer.orgInternalCode" value="${gis2DLayer.orgInternalCode }" />
            <input type="hidden" name="gis2DLayer.zoom" value="${gis2DLayer.zoom }"/>
            <input type="hidden" name="gis2DLayer.isTransformat" value="${gis2DLayer.isTransformat }"/>
            <input type="hidden" name="gis2DLayer.gisType" value="${gis2DLayer.gisType}"/>
            <div style="clear:both"></div>
            <div class="grid_4 lable-right">
            	<label class="form-lbl">中心X坐标：</label>
            </div>
            <div class="grid_6">
                <input type="text" class="form-txt" readonly="readonly" id="centerX" name="gis2DLayer.centerX" value="${gis2DLayer.centerX }" />
            </div>
            <div class="grid_1">
                <s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
            </div>
            <div class="grid_4 lable-right">
            	<label class="form-lbl">中心Y坐标：</label>
            </div>
            <div class="grid_6">
                <input type="text" class="form-txt" readonly="readonly" id="centerY" name="gis2DLayer.centerY" value="${gis2DLayer.centerY }" />
            </div>
            <div class="grid_1">
                <s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
            </div>
            <div style="clear:both"></div>
      
            <div class="grid_4 lable-right">
                <label class="form-lbl">描述：</label>
            </div>
            <div class="grid_17" style="height:100px;">
                <textarea rows="8" style="width:99%;" class="form-txt" cols="40" id="remark" name="gis2DLayer.remark">${gis2DLayer.remark }</textarea>
            </div>
            <div style="clear:both"></div>
        </form>
    </div>
   <script>
    $(function(){
		var isSubmit = false;
        $("#maintainForm").formValidate({
            promptPosition: "bottomLeft",
            submitHandler: function(form){
            	if(isSubmit==true) {
            		$.messageBox({ evel:"error", message:"请不要重复提交！" });
                    return;
            	}
        		isSubmit = true;
                $(form).ajaxSubmit({
                    success:function(data){
                        if(!data.id){
                            $.messageBox({ evel:"error", message:data });
                            return;
                        }
                        $.messageBox({message:"辖区范围保存成功!"});
                        $("#layerMaintanceDialog").dialog("close");
						onLayerOrgChanged();
                    },
                    error:function(XMLHttpRequest, textStatus, errorThrown){
                        alert("提交数据时发生错误");
                    }
                });
            },
            rules:{
                "gis2DLayer.remark":{
                    maxlength:200
                },
                "gis2DLayer.centerX":{
                	required:true,
                	number:true
                },
                "gis2DLayer.centerY":{
                	required:true,
                	number:true
                },
                "gis2DLayer.zoom":{
                	required:true,
                	digits:true,
                	min:0
                }
            },
            messages:{
                "gis2DLayer.remark":{
                    maxlength:"描述最多只能输入200个字符"
                },
                "gis2DLayer.centerX":{
                	required:"请输入地图中心X坐标",
                	number:"地图中心X坐标必须为整数"
                },
                "gis2DLayer.centerY":{
                	required:"请输入地图中心Y坐标",
                	number:"地图中心Y坐标必须为整数"
                },
                "gis2DLayer.zoom":{
                	required:"请输入地图级别",
                   	digits:"地图级别必须输入正整数",
                   	min:$.format("地图级别最小为{0}")
                }
            }
        }); 
       
        <s:if test='"add".equals(mode)'>
        	$("#maintainForm").attr("action",PATH + "/system/gis2DLayerManage/addGis2DLayer.action");
        </s:if>
        <s:if test='"edit".equals(mode)'>
        	$("#maintainForm").attr("action",PATH + "/system/gis2DLayerManage/updateGis2DLayer.action");
        </s:if>
    });
    
</script>