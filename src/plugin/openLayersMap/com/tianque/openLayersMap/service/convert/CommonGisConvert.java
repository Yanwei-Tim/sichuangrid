package com.tianque.openLayersMap.service.convert;

import org.springframework.stereotype.Component;

import com.tianque.openLayersMap.domian.Point;

@Component("commonGisConvert")
public class CommonGisConvert extends GisConvert {

	@Override
	public Double to25DArea(String polygon) {
		return null;
	}

	@Override
	public Double to25DLength(String line) {
		return null;
	}

	@Override
	public Point to25DPoint(Point point) {
		return point;
	}

	@Override
	public Point to2DPoint(Point point) {
		return point;
	}

	@Override
	public String to25DPolygon(String polygon) {
		return polygon;
	}

	@Override
	public String to2DPolygon(String polygon) {
		return polygon;
	}

}
