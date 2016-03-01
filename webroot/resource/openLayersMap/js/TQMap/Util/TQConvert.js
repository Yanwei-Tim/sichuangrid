/**
 * 转换工具,包括将坐标转换为同一个展示字段
 */
TQConvert={
	notNull:function(obj){
		if(obj!=null){
			if(Object.prototype.toString.call(obj)=== '[object Array]'){
				for(var i=0;i<obj.length;i++){
					if(obj[i]!=null) return obj[i];
				}
			}else  return obj
		}
		return null;
	},
	toPoints:function(obj){/**获取热区信息*/
		var that = this;
		if(obj==null) return;
		if(TQMap.gisType != TQMap.GisType.M3D ){
			obj.points = that.notNull([obj.points2,obj.points]);
			obj.minLon = that.notNull([obj.minLon2,obj.minLon]);
			obj.maxLon = that.notNull([obj.maxLon2,obj.maxLon]);
			obj.minLat = that.notNull([obj.minLat2,obj.minLat]);
			obj.maxLat = that.notNull([obj.maxLat2,obj.maxLat]);
		}else{
			obj.points = that.notNull([obj.points,obj.points2]);
			obj.minLon = that.notNull([obj.minLon,obj.minLon2]);
			obj.maxLon = that.notNull([obj.maxLon,obj.maxLon2]);
			obj.minLat = that.notNull([obj.minLat,obj.minLat2]);
			obj.maxLat = that.notNull([obj.maxLat,obj.maxLat2]);
		}
		obj = that.toLonlat(obj);
		return obj;
	},
	toLonlat:function(obj){/**获取坐标信息*/
		var that = this;
		if(obj==null) return;
		if(TQMap.gisType != TQMap.GisType.M3D){
			obj.lon = that.notNull([obj.lon2,obj.lon]);
			obj.lon = that.notNull([obj.lon,obj.centerX2,obj.centerX]);
			obj.lon = that.notNull([obj.lon,obj.centerLon2,obj.centerLon]);
			obj.lat = that.notNull([obj.lat2,obj.lat]);
			obj.lat = that.notNull([obj.lat,obj.centerY2,obj.centerY]);
			obj.lat = that.notNull([obj.lat,obj.centerLat2,obj.centerLat]);
		}else{
			obj.lon = that.notNull([obj.lon,obj.lon2]);
			obj.lon = that.notNull([obj.lon,obj.centerX,obj.centerX2]);
			obj.lon = that.notNull([obj.lon,obj.centerLon,obj.centerLon2]);
			obj.lat = that.notNull([obj.lat,obj.lat2]);
			obj.lat = that.notNull([obj.lat,obj.centerY,obj.centerY2]);
			obj.lat = that.notNull([obj.lat,obj.centerLat,obj.centerLat2]);
		}
		return obj;
	},
	toXYObj:function(points){
		var obj = new Array();
		if(points!=null&&points!=""){
			var temp = points.substring(points.lastIndexOf("((")+2,points.indexOf("))"));
			temp = temp.split(","); 
			if(temp != null){
				for(var i=0;i<temp.length;i+=1){
					var point = temp[i].trim().split(" ");
					//temp.replace(/[ ]/g, ",");
					obj[obj.length] = {x:point[0],y:point[1]};
				}
			}
		}
		return obj;
	},
	toPolygonFromXYObj:function(points){
		var result = "",point=null;
		for(var i=0; points && i<points.length;i++){
			point = points[i];
			result += point.x +" "+point.y;
			if(i<points.length-1){
				result+=",";
			}
		}
		return "POLYGON(("+result+"))";
	},
	toXYStr:function(points){
		var str = "";
		if(points!=null&&points!=""){
			var temp = points.substring(points.lastIndexOf("((")+2,points.indexOf("))"));
			temp = temp.replace(/[ ]/g, ",").split(","); 
			if(temp != null){
				for(var i=0;i<temp.length - 1;i+=2){
					str += "["+temp[i]+","+temp[i+1]+"],";
				}
				str = "["+ str.substring(0,str.length-1) +"]";
			}
		}
		return str;
	}
}