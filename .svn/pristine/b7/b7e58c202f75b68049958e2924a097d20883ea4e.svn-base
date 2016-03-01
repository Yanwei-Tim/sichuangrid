/**
 * 普通工具类
 */
TQUtil = {
	ESP:1e-10,
	INFINITY:-1e10,
	toString:function (obj){
		var str="";
		if(obj==null || obj==undefined) return str;
		if(this.typeOf(obj)=="[object Array]"){
			str += "[";
			for(var i=0;i<obj.length;i++){
				str += this.toString(obj[i]) + ",";
			}
			str = str.substring(0,str.length-1) +"]"
		}else if(this.typeOf(obj)=="[object Object]"){
			str += "{";
			for ( var pro in obj) {
				str += pro+":"+this.toString(obj[pro]) + ",";
			}
			str = str.substring(0,str.length-1) + "}";
		}else{
			str += obj.toString();
		}
		return str;
	},
	typeOf:function(obj){
		return Object.prototype.toString.call(obj);
	},
	clone:function(myObj){
		if(typeof(myObj) != 'object') return myObj;
		if(myObj == null) return myObj;
		var myNewObj = new Object();
		for(var i in myObj){
			myNewObj[i] = this.clone(myObj[i]);
		}
		return myNewObj;
	},
	extend:function(obj1,obj2) {/**属性继承*/
		for ( var property in obj2) {
			obj1[property] = obj2[property];
		}
		return obj1;
	},
	equals:function(obj1,obj2){
		var that = this;
		if(that.typeOf(obj1)=="[object Array]" && that.typeOf(obj2)=="[object Array]" && obj1.length == obj2.length){
			for(var i=0;i<obj1.length;i++){
				if(!that.equals(obj1[i],obj2[i])){
					return false;
				}
			}
		}else if(that.typeOf(obj1)=="[object Object]" && that.typeOf(obj2)=="[object Object]"){
			for ( var pro in obj1) {
				if(!that.equals(obj1[pro],obj2[pro])){
					return false;
				}
			}
			for ( var pro in obj2) {
				if(!that.equals(obj1[pro],obj2[pro])){
					return false;
				}
			}
		}else{
			return obj1==obj2;
		}
		return true;
	},
	evalFunc:function(funcStr,option){/**执行方法字符串方法*/
		var f = funcStr;
		eval(f);
		eval(f.trim().substring(8,f.indexOf("(")).trim()+"(option)");
	},
	distance:function(p1,p2){/**获取两个点的距离*/
		if(p1==null || p2==null) return;
		return Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2))
	},
	separateBeeline:function(p1,p2,deltL,leafL){/**按长度均分直线，获取的坐标集合*/
		//p1：坐标1,p2：坐标2,deltL：均分的长度,leafL：第一次均分时使用的长度
		var that = this;
		var y1,y2,x1,x2;
		var result = new Array();
		if(p1==null || p2==null) return result;
		y1=p1.y , x1=p1.x , y2=p2.y , x2=p2.x;
		var k = (y1-y2)/(x1-x2);
		var b = y1-k*x1;
		var length = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
		var d = (leafL==null||leafL<=0)?deltL:leafL;
		if(leafL!=null && leafL==0){//表示是线的起点
			result.push(p1);
		}
		if(length > d){
			var L = length - d;
			var x = (d*d - L*L + x2*x2 - x1*x1 + y2*y2 - y1*y1 + 2*b*(y1-y2) )/(2*k*(y2-y1) + 2*(x2-x1) );
			var y = k*x + b;
			result.push({x:x,y:y});
			result = result.concat(that.separateBeeline({x:x,y:y}, p2, deltL));
		}else{
			result.push(d - length);
		}
		return result;
	},
	separateLine:function(line,num){/**将直线平分*/
		var that = this;
		var result = new Array();
		if(line!=null && that.typeOf(line)=== '[object Object]' && num>1){
			var deltL = line.getLength()/(num-1);
			var leafL = 0;
			for(var i=1,c=line.components ; c!=null && i<c.length ; i++){
				var array = that.separateBeeline(c[i-1], c[i], deltL, leafL);
				leafL = array.pop();
				result = result.concat(array);
				if(i==c.length-1 && result.length<num){
					result.push(c[i]);
				}
			}
		}
		return result;
	},
	containPoints:function(points1,points2){/**判断点集合points1是否包含点集合points2*/
		var containNum = 0;
		for(var i=0;i<points2.length;i++){
			for(var j=0;j<points1.length;j++){
				if( this.equalsPoint(points1[j], points2[i])){
					containNum++;
					break;
				}
			}
		}
		if(containNum == points2.length){
			return true
		}
		return false;
	},
	equalsPoint:function(p1,p2){/**判断点p1与点p2是否相等*/
		var xlen = ((p1.x+"").length>(p2.x+"").length)?((p2.x+"").length-3):((p1.x+"").length-3);
		var ylen = ((p1.y+"").length>(p2.y+"").length)?((p2.y+"").length-3):((p1.y+"").length-3);
		xlen = (xlen>13)?13:xlen;
		ylen = (ylen>13)?13:ylen;
		if(OpenLayers.Util.toFloat(p1.x,xlen) == OpenLayers.Util.toFloat(p2.x,xlen)
					&& OpenLayers.Util.toFloat(p1.y,ylen) == OpenLayers.Util.toFloat(p2.y,ylen) ){
			return true;
		}
		return false;
	},
	polygonInPolygon:function(polygon1,polygon2){/**面在面上*/
		//polygon is array like [{x:120.12,30.12},{x:120.13,y:30.13},{x:120.15,y:30.15}]
		if(!polygon1 || !polygon2 || polygon1.length<3){
			return false;
		}
		var line = [];
		for(var i=0;i<polygon1.length;i++){
			line = [ polygon1[i] , polygon1[(i+1)%polygon1.length] ];
			if(!this.lineInPolygon(line, polygon2)){
				return false;
			};
		}
		return true;
	},
	lineInPolygon:function(line,polygon){/**线段在面上*/
		//line is array like [{x:120.12,30.12},{x:120.13,y:30.13}]
		if(!polygon || !line || line.length<2){
			return false;
		}
		var p1 = line[0],
			p2 = line[1],
			line2 = [];
		if(!this.pointInPolygon(p1, polygon)) return false;
		if(!this.pointInPolygon(p2, polygon)) return false;
		for(var i=0;i<polygon.length;i++){
	        line2 = [ polygon[i] , polygon[(i+1)%polygon.length] ];
	        if(this.lineCross(line,line2) 
	        		&& !this.pointInLine(p1,line2) && !this.pointInLine(p2,line2)
	        		&& !this.pointInLine(line2[0],line) && !this.pointInLine(line2[1],line) ){
	           return false;
	        }
	    }
	    return true;
	},
	pointInPolygon:function(point,polygon){/**点在面上*/
		var n = polygon.length;
		var count = 0;
		var line = [ point, {x:this.INFINITY, y:point.y}];
		for (var i = 0; i < n; i++) { // 得到多边形的一条边
			var side = [polygon[i],polygon[(i + 1) % n]];
			if (this.pointInLine(point, side)) {
				return true;
			} // 如果side平行x轴则不作考虑
			if (Math.abs(side[0].y - side[1].y) < this.ESP) {
				continue;
			}
			if (this.pointInLine(side[0], line)) {
				if (side[0].y > side[1].y)
					count++;
			} else if (this.pointInLine(side[1], line)) {
				if (side[1].y > side[0].y)
					count++;
			} else if (this.lineCross(line, side)) {
				count++;
			}
		}
		return (count % 2 == 1);
	},
	pointInLine:function(point,line){/**点在线段上*/
		//line is array like [{x:120.12,30.12},{x:120.13,y:30.13}]
		var p1 = line[0];
		var p2 = line[1];
		return ((Math.abs(this.multiply(p1, p2, point)) < this.ESP)
				&& ((point.x - p1.x) * (point.x - p2.x) <= 0) && ((point.y - p1.y)
				* (point.y - p2.y) <= 0));
	},
	lineCross:function(line1,line2){/**判断两条线段是否相交*/
		//line is array like [{x:120.12,30.12},{x:120.13,y:30.13}]
		var p1 = line1[0];
		var p2 = line1[1];
		var p3 = line2[0];
		var p4 = line2[1];
		return ((Math.max(p1.x, p2.x) >= Math.min(p3.x, p4.x))
				&& (Math.max(p3.x, p4.x) >= Math.min(p1.x, p2.x))
				&& (Math.max(p1.y, p2.y) >= Math.min(p3.y, p4.y))
				&& (Math.max(p3.y, p4.y) >= Math.min(p1.y, p2.y))
				&& (this.multiply(p3, p2, p1) * this.multiply(p2, p4, p1) >= 0) && (this.multiply(
				p1, p4, p3) * this.multiply(p4, p2, p3) >= 0));
	},
	multiply:function(p1, p2, p0) {
		return ((p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y));
	}
}