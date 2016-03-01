/**
 * 同步加载js/css脚本
 * @param url   js文件的相对路径或绝对路径
 * @return {Boolean}   返回是否加载成功，true代表成功，false代表失败
 */
var LoadResource={
		get:function(url){
			this._load(url, "GET");
		},
		post:function(url){
			this._load(url, "POST");
		},
		getScriptDom:function(){
			var element = document.createElement( "script" );
			element.language = "javascript";
			element.type = "text/javascript";
            return element;
		},
		getCssDom:function(){
			var element = document.createElement( "style" );
            element.type = "text/css";
            return element;
		},
		_load:function(url,method){
			var that = this;
			var  xmlHttp = null;
		    if(window.ActiveXObject){//IE
		        try {
		            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");//IE6以及以后版本中可以使用
		        }catch (e) {
		            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); //IE5.5以及以后版本可以使用
		        }
		    }else if(window.XMLHttpRequest){//Firefox，Opera 8.0+，Safari，Chrome
		        xmlHttp = new XMLHttpRequest();
		    }
		    if(!method){//POST、GET、PUT及PROPFIND
		    	method = "get";//采用同步加载
		    }
		    xmlHttp.open(method,url,false);
		    xmlHttp.send(null);
		    
		    if ( xmlHttp.readyState == 4 ){//4代表数据发送完毕
		        //0为访问的本地，200到300代表访问服务器成功，304代表没做修改访问的是缓存
		        if((xmlHttp.status >= 200 && xmlHttp.status <300) || xmlHttp.status == 0 || xmlHttp.status == 304){
		            var myHead = document.getElementsByTagName("HEAD").item(0);
		            var element = "";
		            if(url.trim().endWith(".js")){
		            	element = that.getScriptDom();
		            }else if(url.trim().endWith(".css")){
		            	element = that.getCssDom();
		            }
		            try{
		                element.appendChild(document.createTextNode(xmlHttp.responseText)); //IE8以及以下不支持这种方式，需要通过text属性来设置
		            }catch (ex){
		                element.text = xmlHttp.responseText;
		            }
		            myHead.appendChild( element );
		            return true;
		        }else{
		            return false;
		        }
		    }else{
		        return false;
		    }
		}
}