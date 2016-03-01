package com.tianque.openLayersMap.service.convert;

import org.springframework.stereotype.Component;

import com.tianque.openLayersMap.domian.Point;
import com.tianque.openLayersMap.util.Convert;

@Component("oldSiChuanGisConvert")
public class OldSiChuanGisConvert extends GisConvert {

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
		// 同一球面坐标系使用的转换公式
		// Double scale = 1.3;
		// Double yscale = 0.91;
		// Double flagx = 106.285828;
		// Double flagy = 30.345027;
		// Double x0 = Double.parseDouble(lon) - flagx;
		// Double y0 = Double.parseDouble(lat) - flagy;
		// y0 = y0 * 1.13;
		// Double angle = -1 * Math.PI / 4;
		// Double rx = Math.cos(angle) * x0 - Math.sin(angle) * y0;
		// Double ry = Math.cos(angle) * y0 + Math.sin(angle) * x0;
		// rx = rx * scale;
		// ry = ry * yscale;
		// rx = rx + flagx;
		// ry = ry + flagy;
		// return new String[] { rx.toString(), ry.toString() };

		// 不同坐标系使用的转换公式
		Convert c = new Convert();
		try {
			return c.GetOMap(point.getLon(), point.getLat());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Point to2DPoint(Point point) {
		// 同一球面坐标系使用的转换公式
		// Double scale = 1.3;
		// Double yscale = 0.91;
		// Double flagx = 106.285828;
		// Double flagy = 30.345027;
		// Double x0 = Double.parseDouble(lon) - 0 - flagx;
		// Double y0 = Double.parseDouble(lat) - 0 - flagy;
		// x0 = x0 / scale;
		// y0 = y0 / yscale;
		//
		// Double angle = -1 * Math.PI / 4;
		// Double rx = Math.cos(angle) * x0 + Math.sin(angle) * y0;
		// Double ry = Math.cos(angle) * y0 - Math.sin(angle) * x0;
		//
		// ry = ry / 1.13;
		// rx = rx + flagx;
		// ry = ry + flagy;
		// return new String[] { rx.toString(), ry.toString() };

		// 不同坐标系使用的转换公式
		Convert c = new Convert();
		try {
			return c.GetMapJw(point.getLon(), point.getLat());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
