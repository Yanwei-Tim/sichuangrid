/**
 * 对于二三维地图的坐标转换
 * 包括：距离转换、面积转换、坐标转换、坐标集合转换
 */
window.TQTransformat={
		type:"default",                                          //(区域)地图类型
		to25DArea:function(option){/**面积转换   获取25维面积*/
			$.ajax({
				type:"post",
				url:PATH+"/gisUtilManage/to25DArea.action",
				data:{
					points:option.geometry+""
				},
				success:function(data){
					var out = null;
					if(data){
						out = data.toFixed(3) + "&nbsp;m<sup>2</sup>" ;
			    		if(data>Math.pow(1000,2)){
			        		out = (data/Math.pow(1000,2)).toFixed(3) + "&nbsp;km<sup>2</sup>" ;
			    		}
					}
					option.success(out);
				}
			})
		},
		to25DLength:function(option){/**距离转换   获取25维距离*/
			$.ajax({
				type:"post",
				url:PATH+"/gisUtilManage/to25DLength.action",
				data:{
					points:option.geometry+""
				},
				success:function(data){
					var out = null;
					if(data){
						out = data.toFixed(3) + "&nbsp;m" ;
			    		if(data>1000){
			        		out = (data/1000).toFixed(3) + "&nbsp;km" ;
			    		}
					}
					option.success(out);
				}
			})
		},
		to25DPoint:function(point){/**坐标转换  获取25维点坐标*/
			var result = point;
			$.ajax({
				async:false,
				type:"post",
				url:PATH+"/gisUtilManage/to25DPoint.action",
				data:{
					"point.lon":point.lon,
					"point.lat":point.lat
				},
				success:function(data){
					result = data;
				}
			})
			return result?result:point;
		},
		to2DPoint:function(point){/**获取2维点坐标*/
			var result = point;
			$.ajax({
				async:false,
				type:"post",
				url:PATH+"/gisUtilManage/to2DPoint.action",
				data:{
					"point.lon":point.lon,
					"point.lat":point.lat
				},
				success:function(data){
					result = data;
				}
			})
			return result?result:point;
		}
}
TQTransformat.Funcs={//所有的坐标转换方法
		"default":{
			"to25DPoint":function(option){
				var x = option.lon;
				var y = option.lat;
				/*同一球面坐标系使用的转换公式
				var scale = 1.3;
			    var yscale = 0.91;
			    var flagx = 106.285828;
			    var flagy = 30.345027;
				var x0 = x - flagx;
				var y0 = y - flagy;
				y0 = y0 * 1.13;
				
				var angle = -1 * Math.PI / 4;
				var rx = Math.cos(angle) * x0 - Math.sin(angle) * y0;
				var ry = Math.cos(angle) * y0 + Math.sin(angle) * x0;
				rx = rx * scale;
				ry = ry * yscale;
				rx = rx + flagx;
			    ry = ry + flagy;
			    */
			    //不同坐标系使用的转换公式
				var point = get25DMap('hh', x, y);
			    return new OpenLayers.LonLat(point.x, point.y);
			},
			"to2DPoint":function(option){
				var x = option.lon;
				var y = option.lat;
				/*同一球面坐标系使用的转换公式
				var scale = 1.3;
			    var yscale = 0.91;
			    var flagx = 106.285828;
			    var flagy = 30.345027;
				var x0 = x - 0 - flagx;
				var y0 = y - 0 - flagy;
				x0 = x0 / scale;
			    y0 = y0 / yscale;

				var angle = -1 * Math.PI / 4;
				var rx = Math.cos(angle) * x0 + Math.sin(angle) * y0;
				var ry = Math.cos(angle) * y0 - Math.sin(angle) * x0;
				
			    ry = ry / 1.13;
				rx = rx + flagx;
				ry = ry + flagy;
				*/
			    //不同坐标系使用的转换公式
			    var point = getMapJw('hh', x, y);
			    return new OpenLayers.LonLat(point.x, point.y);
			}
		},
		"sichuan":{
			
		}
}