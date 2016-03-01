<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainForm" method="post" action="">
		<pop:token></pop:token>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>所属层级：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="keyWord.org.orgName" class="form-txt"
				value="${keyWord.org.orgName}" readonly /> <input type="hidden"
				name="keyWord.org.id" class="form-txt" value="${keyWord.org.id}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>微信号：</label>
		</div>
		<div class="grid_19">
			<select name="keyWord.weChatUserId" class='form-txt' id="weChatUserId"
				style="width: 510px" readonly >
				<option value="请选择微信号">请选择微信号</option>
				<s:iterator value="listTencentUser">
				<s:if test="#request.weChatUserId==#request.keyWord.weChatUserId">
					<option value="${weChatUserId }" selected>${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
						${name}]</option>
				</s:if>
				<s:else>
				   <option value="${weChatUserId }">${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
						${name}]</option>
				</s:else>
				</s:iterator>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>关键词：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="keyWord.keyName" id="keyName" value="${keyWord.keyName}"
				class='form-txt  {required:true,maxlength:3,isHasKeyWord:true,messages:{required:" 请输入关键字",maxlength:$.format("关键字最多可以输入{0}个字符"),isHasKeyWord:"该关键词已经存在"}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>关键词描述：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="keyWord.keyRemark" value="${keyWord.keyRemark}"
				class='form-txt {required:true,maxlength:10,messages:{required:" 请输入关键词描述",maxlength:$.format("关键词描述最多可以输入{0}个字符")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材类型：</label>
		</div>
		<div class="grid_19">
		<input  type="hidden" name="keyWord.sourceTypeDict.id" value="${keyWord.sourceTypeDict.id}"/>
		<input type="text" id="sourceTypes" class='form-txt' readonly value='<pop:PropertyDictViewTag defaultValue="${keyWord.sourceTypeDict.id}" name="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE"></pop:PropertyDictViewTag>'/>
		
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材编号：</label>
		</div>
		<div class="grid_19">
			      <select name="one"  size=6 multiple="multiple" class="form-txt"  id="select1" style="width: 185px;height:120px;float:left">
					<s:iterator value="listWeChatSources">
							<option value="${id }">${id}[${sourceDescription}]</option>
					</s:iterator>
				  </select>
				 <div class="btnbanner btnbannerData" style="width:50px;float:left;margin-left:20px;border:1px;margin-top:5px" >
					<a id="toRight" href="javascript:void(0)"  ><span>&nbsp>&nbsp</span></a>
					<a id="allToRight" href="javascript:void(0)" ><span>>></span></a>
					<a id="toLeft" href="javascript:void(0)" ><span>&nbsp<&nbsp</span></a>
					<a id="allToLeft" href="javascript:void(0)" ><span><<</span></a>
				 </div> 
				<select name="two" id="select2" multiple="true" class="form-txt"   size=6 style="width: 190px;height:120px;float:left">
					<s:generator separator=","  var="id" val="#request.keyWord.sourceId ">
						<s:iterator status="st">  
					        <option><s:property/></option>
					    </s:iterator> 
					</s:generator>
				</select>
				 <div class="btnbanner btnbannerData" style="width:50px;float:left;margin-left:20px;border:1px;margin-top:5px" >
					<a id="upmoveTop" href="javascript:void(0)" ><span>置顶</span></a>
					<a id="upmove" href="javascript:void(0)" ><span>上移</span></a>
					<a id="downmove" href="javascript:void(0)" ><span>下移</span></a>
					<a id="downmoveBottom" href="javascript:void(0)" ><span>置尾</span></a>
				 </div> 
				<input type="hidden" id="SourceId" value="${keyWord.sourceId }" name="keyWord.sourceId"/>
				<!--<s:iterator value="listWeChatSources">
					<span class="sourceDescriptions" id="${id}">${sourceDescription}</span>
				</s:iterator>-->
			
		</div>
		<div class='clearLine'>&nbsp;</div>
		<p>&nbsp&nbsp&nbsp&nbsp</p><p>&nbsp&nbsp&nbsp</p><p>&nbsp&nbsp&nbsp</p><p>&nbsp&nbsp&nbsp</p>
		<p>&nbsp&nbsp&nbsp&nbsp</p><p>&nbsp&nbsp&nbsp</p><p>&nbsp&nbsp&nbsp</p>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材总描述：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="keyWord.sourceDescription" id="keyWordSourceDescription" value="${keyWord.sourceDescription}" 
				class='form-txt {maxlength:10,messages:{maxlength:$.format("素材总描述最多可以输入{0}个字符")}}'/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<input type="hidden" id="mode" name="mode" value="${mode}"/>
		<input type="hidden" id="keyWordId" name="keyWord.id" value="${keyWord.id}"/>
	</form>

</div>
<div id="selectPersonDialog"></div>
<script type="text/javascript">
/*$(".sourceDescriptions").hide();
	function changeSourceId(value) {
		$(".sourceDescriptions").each(function() {
			var id = $(this).attr("id");
			if (value == "请选择素材编号" || value == null) {
				$("#keyWordSourceDescription").val("");
			}
			if (id == value) {
				$("#keyWordSourceDescription").val($(this).text());
			}
		})
	}*/
	function listSourceId() {
		var a = document.getElementById("select2");
		var sourceId = "";
		for ( var i = 0; i < a.length; i++) {
			sourceId += a.options[i].value + ",";
		}
		document.getElementById("SourceId").value = sourceId;
	}
	function swapOptionProperties(option1,option2){
	    var tempStr=option1.value;
	    option1.value=option2.value;
	    option2.value=tempStr;
	    tempStr=option1.text;
	    option1.text=option2.text;
	    option2.text=tempStr;
	    tempStr=option1.selected;
	    option1.selected=option2.selected;
	    option2.selected=tempStr;
	    
	}
	$(document).ready(function() {
		//置顶
		$("#upmoveTop").click(function(){
			if ($("#select2 option:selected").length <= 0) {
                $.messageBox({level: "warn",message : "请选择需要置顶的选项"});
                return;
            }
			var theObjOptions=$("#select2 option");
			var selectObj=document.getElementById("select2");
			var oOption=null;
		    for(var i=0;i<theObjOptions.length;i++) {
		        if( theObjOptions[i].selected && oOption) {
		            selectObj.insertBefore(theObjOptions[i],oOption);
		        }
		        else if(!oOption && !theObjOptions[i].selected) {
		            oOption=theObjOptions[i];
		        }
		    }
		    listSourceId();
		});
		//置尾
		$("#downmoveBottom").click(function(){
				if ($("#select2 option:selected").length <= 0) {
	                $.messageBox({level: "warn",message : "请选择需要置尾的选项"});
	                return;
	            }
			    var theObjOptions=$("#select2 option");
			    var selectObj=document.getElementById("select2");
			    var oOption=null;
			    for(var i=theObjOptions.length-1;i>-1;i--) {
			        if( theObjOptions[i].selected ) {
			            if(oOption) {
			                oOption=selectObj.insertBefore(theObjOptions[i],oOption);
			            }
			            else oOption=selectObj.appendChild(theObjOptions[i]);
			        }
			    }
			    listSourceId();
		})
		//上移
		$("#upmove").click(function(){
			if ($("#select2 option:selected").length <= 0) {
                $.messageBox({level: "warn",message : "请选择需要上移的选项"});
                return;
            }
			var selectLen=$("#select2 option").length;
			var options=$("#select2 option");
			for(var i=1;i<selectLen;i++){
				if($("#select2 option").get(i).selected && !$("#select2 option").get(i-1).selected ) {
		            swapOptionProperties(options[i],options[i-1]);
		        }
			}
            listSourceId();
		})
		//下移
		$("#downmove").click(function(){
			if ($("#select2 option:selected").length <= 0) {
                $.messageBox({level: "warn",message : "请选择需要下移的选项"});
                return;
            }
			var selectLen=$("#select2 option").length;
			var options=$("#select2 option");
			for(var i=selectLen-2;i>-1;i--){
				if($("#select2 option").get(i).selected && !$("#select2 option").get(i+1).selected ) {
		            swapOptionProperties(options[i],options[i+1]);
		        }
			}
            listSourceId();
		})
		//移到右边
		$('#toRight').click( function() {
			var a = document.getElementById("select2");
			if(a.length>10){
				$.messageBox({level: "warn",message : "最多能选10条素材"});
			}else{
				//获取选中的选项，删除并追加给对方
				$('#select1 option:selected').appendTo('#select2');
				listSourceId();
			}
		});

		//移到左边
		$('#toLeft').click( function() {
			$('#select2 option:selected').appendTo('#select1');
			listSourceId();
		});
		//全部移到右边
		$('#allToRight').click( function() {
			var a = document.getElementById("select1");
			var b = document.getElementById("select2");
			if(a.length>10){
				$.messageBox({level: "warn",message : "最多能选10条素材"});
			}else if(b.length>10){
				$.messageBox({level: "warn",message : "最多能选10条素材"});
			}else{
				//获取全部的选项,删除并追加给对方
				$('#select1 option').appendTo('#select2');
				listSourceId();
			}
		});

		//全部移到左边
		$('#allToLeft').click( function() {
			$('#select2 option').appendTo('#select1');
			listSourceId();
		});
		
		//双击选项(移到右边)
		$('#select1').dblclick( function() { //绑定双击事件
			var a = document.getElementById("select2");
			if(a.length>10){
				$.messageBox({level: "warn",message : "最多能选10条素材"});
			}else{
					//获取全部的选项,删除并追加给对方
					$("option:selected", this).appendTo('#select2'); //追加给对方
					listSourceId();
			}
		});

		//双击选项（移动左边）
		$('#select2').dblclick( function() {
			$("option:selected", this).appendTo('#select1');
			listSourceId();
		});
	
	
		//检验关键词重复
		jQuery.validator.addMethod("isHasKeyWord", function(value, element){
			var flag=false;
			if($("#weChatUserId").val()!="请选择微信号"||$("#weChatUserId").val()!=""){
				$.ajax({
					async:false,
					url:"${path}/keyWordManage/validateKeyWord.action?keyWord.weChatUserId="+$("#weChatUserId").val()+"&keyWord.keyName="+$("#keyName").val(),
					success:function(responseData){
					    if(responseData!=null){
					      if("edit" == $("#mode").val()){
								if($("#weChatUserId").val()==responseData["weChatUserId"]&&$("#keyName").val()==responseData["keyName"]&&$("#keyWordId").val()==responseData["id"]){
									flag=true;
								}
							}
						}
						if(responseData==null){
							flag=true;
						}
					}
				});
			}
			return flag;
		});
		
		<s:if test='"edit".equals(mode)'>
    	  $("#maintainForm").attr("action", "${path}/keyWordManage/updateKeyWord.action");
		</s:if>
		<s:else>
		$("#maintainForm").attr("action", "${path}/keyWordManage/addKeyWord.action");
		</s:else>
		//提交
		$("#maintainForm").formValidate({
			promptPosition : "bottomLeft",
			submitHandler : function(form) {
				if($("#weChatUserId").val()=="请选择微信号"||$("#weChatUserId").val()==""){
					$.messageBox({level: "warn",message : "请选择要绑定的微信号"});
					return false;
					}
				if($("#SourceId").val()=="请选择素材编号"||$("#SourceId").val()==""){	
					$.messageBox({level: "warn",message : "请选择要绑定的素材编号"});
					return false;
					}
				$(form).ajaxSubmit({
					success : function(data) {
						if (data ==true||data=="true") {
							$("#keyWordMaintanceDialog").dialog("close");
							<s:if test='"edit".equals(mode)'>
							$.messageBox({message : "修改成功!"});
							</s:if>
							<s:else>
							$.messageBox({message : "添加成功!"});
							</s:else>
							$("#keyWordList").trigger("reloadGrid");
						} else {
							$.messageBox({
								message : data,
								level : "error"
							});
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
				});
			}
		});

	});
</script>


