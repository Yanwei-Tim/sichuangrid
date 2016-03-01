<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style type="text/css">
.wx_menuedit{padding:15px;overflow:auto;margin:0 auto;width:1200px;}
.wx_addmenu a{display:block;width:100px;height:36px;background:url(/resource/images/bg_sprite.png) left -37px no-repeat;font: normal 16px/36px 'microsoft yahei';color:#333;text-indent:2em;}
.wx_addmenu a:hover{background-position:-100px -37px;}
.wx_menu_list{white-space:nowrap;}
.wx_menu_list .menubox{padding:10px 10px 0;margin:15px 15px 0 0;width:350px;border:1px solid #ccc;border-radius:5px;margin-right:15px;display:inline-block;*display:inline;*zoom:1;vertical-align:top; }
.wx_menu_list .menubox li{margin-bottom:20px;}
.wx_menu_list .menubox .inputTxt{height:26px;border:1px solid #999;width:110px;line-height:26px;color:#999;text-indent:1em;font-size:14px;position:relative;}
.wx_menu_list .menubox .inputTxt_right{width:100px;}
.wx_menu_list .menubox .inputTxt_left{width:90px;font-size:12px;}
.wx_menu_list .menubox label{margin:0 3px 0 2px;color:#333;line-height:22px;}
.wx_menu_list .menubox a{display:inline-block;*display:inline;*zoom:1;width:14px;height:14px;background:url(/resource/images/bg_sprite.png) -100px -80px;margin:0 4px;}
.wx_menu_list .menubox .add{background-position:0px -80px;}
.wx_menu_list .menubox .remove,.wx_menu_list .menubox .liremove{background-position:-21px -80px;}
.wx_menuedit .wx_attention{color:#999;text-align:center;margin-top:40px;margin-bottom:20px;}
.wx_btn a{display:block;width:100px;height:37px;font:normal 16px/37px 'microsoft yahei';color:#fff;text-align:center;background:url(/resource/images/bg_sprite.png) no-repeat;margin:0 auto;}
.wx_btn a:hover{background-position:-100px 0;}
.msg-box{height:20px;padding:0 10px;border-radius:2px;border:1px solid #DB0027;background:#fff;position:absolute;z-index:2;text-align:center;line-height:20px;}
.hidden{display:none;}
</style>
  <div class="wx_menuedit">
	<form id="maintainForm" method="post" action="">
	   <pop:token></pop:token>
		<input type="hidden" id="mode" name="mode" value="${mode}"/>
	<div class="wx_addmenu">
    	<a href="javascript:;">主菜单</a> 
		<div class="grid_19" style="position:absolute;left:150px;top:20px;">
    	<select name="weChatMenu.weChatUserId" class='form-txt' id="weChatUserId"
				style="width: 250px;" readonly >
				<option value="请选择微信号">请选择微信号...</option>
				<s:iterator value="listTencentUser" >
				<s:if test="#request.weChatUserId==#request.weChatMenu.weChatUserId">
					<option value="${weChatUserId }" selected>${weChatUserId}&nbsp&nbsp&nbsp&nbsp[昵称：${name}]</option>
				</s:if>
				<s:else>
				   <option value="${weChatUserId }">${weChatUserId}&nbsp&nbsp&nbsp&nbsp[昵称：${name}]</option>
				</s:else>
				</s:iterator>
		</select>
		</div>
    </div>
        <div class="wx_menu_list cf">
        <s:if test='"updateWeChatMenu".equals(mode)'>
	        <s:iterator value="weChatMenuParentList" status='st' id="oneList">
	            <div class='menubox'>
	                <ul>
	                    <li>
	                        <input type='text' placeholder='请输入菜单...' value='${menuName}' id='${id}' class='inputTxt inputTxt_spe inputTxt_left'/>
		                    <label <s:if test="#oneList.isLeaf==2">class='hidden'</s:if>><input type='radio'  name='ra${id}' value=1 <s:if test="#oneList.menuType==1">checked</s:if> <s:if test="#oneList.isLeaf==2">checked</s:if>/>单击</label>
		                    <label <s:if test="#oneList.isLeaf==2">class='hidden'</s:if>><input type='radio'  name='ra${id}' value=2 <s:if test="#oneList.menuType==2">checked</s:if> />网页</label>
		                    <input type='text' class='inputTxt inputTxt_right  <s:if test="#oneList.isLeaf==2">hidden </s:if>' <s:if test="#oneList.menuType==1">value='${menuKey}'</s:if><s:else>value='${url}'</s:else> />
	                        <a href='javascript:;' class='add'></a>
	                        <a href='javascript:;' class='remove'></a>
	                   </li>
	                  <s:iterator value="weChatMenuChildList" status='sta' id="twoList">
		                     <s:if test="#oneList.parentId==#twoList.parentId">
			                    <li id='num' style='padding-left:20px;' >
				                    <input type='text' placeholder='请输入菜单...' value='${menuName }' id='${id}' class='inputTxt inputTxt_left'/>
				                    <label><input type='radio' name='radio${id}' value=1 <s:if test="#twoList.menuType==1">checked</s:if>>单击</label>
				                    <label><input type='radio' name='radio${id}' value=2 <s:if test="#twoList.menuType==2">checked</s:if>>网页</label>
				                    <input type='text' class='inputTxt inputTxt_right' <s:if test="#twoList.menuType==1">value='${menuKey}'</s:if><s:else>value='${url}'</s:else>/>
				                    <a></a><a href='javascript:;' class='liremove'></a>
			                    </li>
			                 </s:if>
	                  </s:iterator>
	                </ul>
	            </div>
	         </s:iterator>
         </s:if>
         <s:else>
            <div class='menubox'>
	                <ul>
	                    <li >
	                        <input type='text' placeholder='请输入菜单...' value='${menuName}' class='inputTxt inputTxt_spe inputTxt_left'/>
	                        <label><input type='radio'  name='ra' value=1 checked/>单击</label>
	                        <label><input type='radio'  name='ra' value=2  />网页</label>
	                        <input type='text' class='inputTxt inputTxt_right' />
	                        <a href='javascript:;' class='add'></a>
	                        <a href='javascript:;' class='remove'></a>
	                   </li>
	                </ul>
	         </div>
         </s:else>
        </div>
        <div class="wx_attention">
			编辑中的菜单不能直接在用户手机上生效，创建后24小时所有的用户都将更新到新的菜单。
        </div>
    </form>
</div>
<script type="text/javascript">
$(function(){
	var n = 1;
	var m=1;
     /**验证框***/
	function msgBoxShow(that,msgBox,len){
		 if(that.val() == '' && !that.hasClass("hidden")||that.val().length>len){
			 var top = that.position().top,left = that.position().left;
				if(that.next(".msg-box")[0]){
					that.next(".msg-box").remove();
				}
			that.after(msgBox);
			that.next(".msg-box").css({"top":top+30,"left":left-5})
			that.addClass("errorInput");	
			return false;
		}else{
			that.next(".msg-box").remove();
			that.removeClass("errorInput");	
			return true;
		}
	}
	/***右边输入框验证*/
    
	function inputRightEvent(that){
   	   if(that.siblings().find(':radio:checked').val()=='1'){
				var patrn=/^[0-9]*[0-9][0-9]*$/;
				if (patrn.exec(that.val().replace(/[ ]/g,""))){
					return msgBoxShow(that,'<div class="msg-box">最多输入3个字符</div>',3);
				}else{
					return  msgBoxShow(that,'<div class="msg-box">请输入数字</div>',0);
				}
		}else{
			var patrn=/[a-zA-z]+:\/\/[^\s]+/;
			if (patrn.exec(that.val().replace(/[ ]/g,""))){
				return msgBoxShow(that,'<div class="msg-box">最多输入300个字符</div>',400);
			}else{
				return  msgBoxShow(that,'<div class="msg-box">请输入正确的网址</div>',0);
			}
		}
   	   return true ;
	}
	/***左边输入框验证*/
	function inputLeftEvent(that){
	    if(that.val().length>4){
	    	if(msgBoxShow(that,'<div class="msg-box">最多输入4个字符</div>',4)==false)
	    		return false;
	    }
		else{
			if(msgBoxShow(that,'<div class="msg-box">请输入菜单内容</div>',4)==false)
				return false;
		}
	    return true;
	}
	/**验证所有文本框**/
	function checkIuput(){
	    var flg=true;
		$(".menubox input.inputTxt_left").each(function(index){
			if(inputLeftEvent($(this))==false){
				flg=false;
			}
		})
		$(".menubox input.inputTxt_right").each(function(index){
		 if(inputRightEvent($(this))==false){
			 flg=false;
		   }
		})
		return flg;
	}
	//校验选中单击后右边文本框的值有没有重复
	function checkRightInputByRadio(){
		var str="";
		$(".menubox input.inputTxt_right").each(function(index){
			if($(this).siblings().find(":radio:checked").val()=='1'&&$(this).val()!=''&&!$(this).hasClass('hidden')){
			   str+=$(this).val()+",";
			}
		})
		if(str!=""){
			var strs=str.substring(0,str.lastIndexOf(',')).split(","); //字符分割 
			for (i=0;i<strs.length ;i++ ) 
			{ 
			   for(j=i+1;j<strs.length;j++){
				   if(strs[i]==strs[j]){
					$.messageBox({level:"warn",message:"右边文本框有重复项，重复项："+strs[j]});
					   return false;
				   }
			   }
			}
			return true;
		}
	}
	$(".wx_addmenu a").click(function(){
		if($("#weChatUserId").val()=="请选择微信号"){
			$.messageBox({level:"warn",message:"请选择要创建菜单的微信号！"});
			return false;
		}
		var len = $(".menubox").length;
		var	innerHtml= "<div class='menubox'><ul><li><input type='text' placeholder='请输入菜单...' value='' class='inputTxt inputTxt_spe inputTxt_left '><label><input type='radio' name='ra"+m+"' value=1 checked>单击</label><label><input type='radio' name='ra"+m+"' value=2>网页</label><input type='text' class='inputTxt inputTxt_right '><a href='javascript:;' class='add'></a><a href='javascript:;' class='remove'></a></li></ul></div>";		
		if(len < 3){
			m=m+1;
			$(".wx_menu_list").append(innerHtml);
		}else{
			$.messageBox({level:"warn",message:"只能创建三个一级菜单！"});
			return false;	
		}	
	})
	$(".wx_menu_list").on("click",".remove",function(){
		$(this).parents(".menubox").remove();
		$('.msg-box').remove();
		$('input').removeClass('errorInput');
		checkIuput();
	})
	$(".wx_menuedit").on("click",".liremove",function(){
		var parentLi=$(this).closest("ul").find("li").eq(0);
		var size = $(this).closest("ul").find("li").size();
		var length = size - 2;
		$(this).closest("li").remove();
		if(length <= 0){
			parentLi.find("label").show().removeClass('hidden').end().find("input").show().removeClass('hidden');
		}
		$('.msg-box').remove();
		$('input').removeClass('errorInput');
		checkIuput();
	})
	$(".wx_menu_list").on("click",".add",function(){
		if($("#weChatUserId").val()=="请选择微信号"){
			$.messageBox({level:"warn",message:"请选择要创建菜单的微信号！"});
			return false;
		}
		$(this).parent().find("input.inputTxt").removeClass("errorInput");
		$(this).parent().find(".inputTxt_spe").siblings().addClass("hidden");
		$(this).parent().find("a").show();
		var name=$(this).parent().find(".inputTxt_spe").siblings('label').children().attr("name")
		var   html ="<li id='num' style='padding-left:20px;' ><input type='text' placeholder='请输入菜单...' value='' class='inputTxt inputTxt_left '><label><input type='radio' name='radio"+n+"' value=1 checked>单击</label><label><input type='radio' name='radio"+n+"' value=2>网页</label><input type='text' class='inputTxt inputTxt_right '><a></a><a href='javascript:;' class='liremove'></a></li>"
		var length = $(this).closest("ul").find("li").length;
		if(length < 6){
			$(this).closest(".menubox ul").append(html)
			n= n+1;
		}else{
			$.messageBox({level:"warn",message:"只能创建五个二级菜单！"});
			return false;	
		}
	})
	
	$(".wx_menu_list").on("focusin",".inputTxt",function(){
		 if( this.value == "" ){
			this.value = "";
		}	
	}).on("focusout",".inputTxt",function(){
		 if( this.value =="" ){
			this.value = "" ;
		}	
	})	
	 $(".wx_menu_list").on("focusout",".inputTxt_left",function(){
		 inputLeftEvent($(this))
     })
     $(".wx_menu_list").on("focusout",".inputTxt_right",function(){
			inputRightEvent($(this));
     })
	//提交

	$("#maintainForm").attr("action", "${path}/weChatMenuManage/addWeChatMenu.action");
	$("#maintainForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
				if($("#weChatUserId").val()=="请选择微信号"){
					$.messageBox({level:"warn",message:"请选择要创建菜单的微信号！"});
					return false;
				}	
				if(checkIuput()==false)
				  return false;
			    if(checkRightInputByRadio()==false)
				  return false;
			    var jsonMenu="[{'button':[";
				var flgJson=false;
				$(".menubox li").each(function(index){
					if($(this).attr("id")!='num'){
					    if(flgJson==true){
					    	  flgJson=false;
					    	  jsonMenu+="]},";
					    }
					    //一级菜单绑链接
						if($(this).find('.inputTxt_right').hasClass('hidden')){
							if($(this).find('.inputTxt_left').attr("id")==null||$(this).find('.inputTxt_left').attr("id").length==0){
								jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','sub_button':[";
							}else{
								jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','id':'"+$(this).find('.inputTxt_left').attr("id")+"','sub_button':[";
							}		
						}else{
							if($(this).find(':radio:checked').val()=='1'){
								if($(this).find('.inputTxt_left').attr("id")==null||$(this).find('.inputTxt_left').attr("id").length==0){
									jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','type':'1','key':'"+$(this).find('.inputTxt_right').val()+"'}";
								}else{
									jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','id':'"+$(this).find('.inputTxt_left').attr("id")+"','type':'1','key':'"+$(this).find('.inputTxt_right').val()+"'}";
								}
								
							}else{
								if($(this).find('.inputTxt_left').attr("id")==null||$(this).find('.inputTxt_left').attr("id").length==0){
									jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','type':'2','url':'"+$(this).find('.inputTxt_right').val()+"'}";
								}else{
									jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','id':'"+$(this).find('.inputTxt_left').attr("id")+"','type':'2','url':'"+$(this).find('.inputTxt_right').val()+"'}";
								}
								
							}
							
							if($('.menubox li').length!=index+1)
								jsonMenu+=","
					   }
					}else{
						flgJson=true;
						
						if($(this).find(':radio:checked').val()=='1'){
							if($(this).find('.inputTxt_left').attr("id")==null||$(this).find('.inputTxt_left').attr("id").length==0){
								jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','type':'1','key':'"+$(this).find('.inputTxt_right').val()+"'}";
							}else{
								jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','id':'"+$(this).find('.inputTxt_left').attr("id")+"','type':'1','key':'"+$(this).find('.inputTxt_right').val()+"'}";
							}
						}else{							
							if($(this).find('.inputTxt_left').attr("id")==null||$(this).find('.inputTxt_left').attr("id").length==0){
								jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','type':'2','url':'"+$(this).find('.inputTxt_right').val()+"'}";
							}else{
								jsonMenu+="{'name':'"+$(this).find('.inputTxt_left').val()+"','id':'"+$(this).find('.inputTxt_left').attr("id")+"','type':'2','url':'"+$(this).find('.inputTxt_right').val()+"'}";
							}
						}
						
						
						if($('.menubox li').eq(index+1).attr('id')=='num')
							jsonMenu+=","
						if($('.menubox li').length==index+1)
						   jsonMenu+="]}";
				   }
				})
				jsonMenu+="]}]";
			  if(jsonMenu=="[{'button':[]}]")
				  jsonMenu="";
				//var jsonStr="[{'button':[{'name':'网站','sub_button':[{'name':'百度','type':'2','url':'http://www.baidu.com'},{'name':'谷歌','type':'2','url':'https://www.google.com'},{'name':'360','type':'2','url':'http://www.3600.com'}]},{'name':'体闲驿站','sub_button':[{'key':'21','name':'歌曲点播','type':'1'},{'key':'22','name':'经典游戏','type':'1'}]},{'key':'31','name':'给我留言','type':'1'}]}]";
		        //var jsonStr="[{'button':[{'name':'辖区动态','sub_button':[{'key':'11','name':'辖区概况','type':'1'},{'key':'12','name':'辖区新闻','type':'1'},{'key':'13','name':'通知公告','type':'1'},{'key':'14','name':'平安简讯','type':'1'},{'key':'15','name':'往期回顾','type':'1'}]},{'name':'便民指南','sub_button':[{'key':'21','name':'办事指南','type':'1'},{'key':'22','name':'联系我们','type':'1'},{'key':'23','name':'常用号码','type':'1'}]},{'name':'互动专栏','sub_button':[{'name':'留言板','type':'2','url':'http://anhaooray.oicp.net:28096/index.html'},{'name':'互帮互助','type':'2','url':'http://anhaooray.oicp.net:28096/index.html'},{'name':'远程申报','type':'2','url':'http://anhaooray.oicp.net:28096/index.html'}]}]}]";
				$(form).ajaxSubmit({
				data:{menuJson:jsonMenu},
				success : function(data) {
					if (data ==true||data=="true") {
						$("#weChatMenuMaintanceDialog").dialog("close");
						<s:if test='"addWeChatMenu".equals(mode)'>
						   $.messageBox({message : "创建成功!"});
						</s:if>
						<s:else>
						  $.messageBox({message : "修改成功!"});
						</s:else>
						$("#weChatMenuList").trigger("reloadGrid");
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
})
</script>

