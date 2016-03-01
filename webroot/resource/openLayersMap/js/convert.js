
//精度10m
var correct_pts = {};
correct_pts['hh'] = [

                   //20130729最新参考点

                   { j: 106.287494, w: 30.345093, utm_x: 0, utm_y: 0, x: 106.287390, y: 30.344014 },
                    { j: 106.287336, w: 30.347885, utm_x: 0, utm_y: 0, x: 106.290126, y: 30.346180 },
                     { j: 106.283367, w: 30.344955, utm_x: 0, utm_y: 0, x: 106.283527, y: 30.346501 },
                      { j: 106.289577, w: 30.340953, utm_x: 0, utm_y: 0, x: 106.285087, y: 30.339693 },
                       { j: 106.291069, w: 30.345771, utm_x: 0, utm_y: 0, x: 106.291313, y: 30.342315 },
                        { j: 106.282960, w: 30.341329, utm_x: 0, utm_y: 0, x: 106.279508, y: 30.344104 },
                         { j: 106.286098, w: 30.337433, utm_x: 0, utm_y: 0, x: 106.278363, y: 30.339294 },
{ j: 106.297964, w: 30.348508, utm_x: 0, utm_y: 0, x: 106.300306, y: 30.340049 },
                           { j: 106.295850, w: 30.342086, utm_x: 0, utm_y: 0, x: 106.291856, y: 30.336641 },
                           { j: 106.277658, w: 30.345055, utm_x: 0, utm_y: 0, x: 106.278526, y: 30.350127 },
                           { j: 106.283192, w: 30.334256, utm_x: 0, utm_y: 0, x: 106.272545, y: 30.338774 },
                           { j: 106.292784, w: 30.350689, utm_x: 0, utm_y: 0, x: 106.297851, y: 30.344866 },
                           { j: 106.291348, w: 30.353957, utm_x: 0, utm_y: 0, x: 106.299880, y: 30.348153 },
                           { j: 106.287014, w: 30.355851, utm_x: 0, utm_y: 0, x: 106.297937, y: 30.352211 },
                           { j: 106.286323, w: 30.351691, utm_x: 0, utm_y: 0, x: 106.293061, y: 30.349618 },
                           //20131024最新参考点
                           { j: 106.267141, w: 30.341900, utm_x: 0, utm_y: 0, x: 106.265862, y: 30.354336 },
                            { j: 106.274153, w: 30.344097, utm_x: 0, utm_y: 0, x: 106.274373, y: 30.351610 },
                             { j: 106.269808, w: 30.337980, utm_x: 0, utm_y: 0, x: 106.264248, y: 30.349813 },
                              { j: 106.276458, w: 30.336522, utm_x: 0, utm_y: 0, x: 106.268749, y: 30.344628 },
                               { j: 106.276873, w: 30.343291, utm_x: 0, utm_y: 0, x: 106.276009, y: 30.349323 },
                                { j: 106.281986, w: 30.349299, utm_x: 0, utm_y: 0, x: 106.286713, y: 30.350539 },
                                 { j: 106.284344, w: 30.353276, utm_x: 0, utm_y: 0, x: 106.292885, y: 30.352011 },
                                  { j: 106.285371, w: 30.363450, utm_x: 0, utm_y: 0, x: 106.304096, y: 30.358813 },
                                   { j: 106.289469, w: 30.361729, utm_x: 0, utm_y: 0, x: 106.306047, y: 30.355024 },
                                    { j: 106.291001, w: 30.355080, utm_x: 0, utm_y: 0, x: 106.300695, y: 30.349199 }
                           //{ j:  , w:  , utm_x: 0, utm_y: 0, x:   , y:   },
                           // { j:  , w:  , utm_x: 0, utm_y: 0, x:   , y:   },

];
var num = {};
num['hh'] = { num: Math.sin(Math.PI / 4), num2: Math.sin(Math.PI / 4) };

//得到需要转换坐标的类型
function getTypeP(type) {
    if (type == "jw")
        return { strX: 'j', strY: 'w', initValue: 180, minPreX: 0.00005, minPreY: 0.00005 };
    if (type == "utm")
        return { strX: 'utm_x', strY: 'utm_y', initValue: 1294723000, minPreX: 500, minPreY: 500 };
    if (type == "xy")
        return { strX: 'x', strY: 'y', initValue: 1000000000, minPreX: 500, minPreY: 500 };
}

//输入一个点，得到这个点最近却比较合理的两对点。
function getPx_Py(city, x, y, typeP) {
    var pointsIndex = getFourNearIndex(city, x, y, typeP);
    return get2PairPointsX_Y(city, pointsIndex, typeP);
}

//得到输入所有点中，比较合理的两对点。
//一对在X轴上，一对在Y轴上。
function get2PairPointsX_Y(city, pointsIndex, typeP) {
    var pointsX = {};
    var pointsY = {};
    var haveX = false;
    var haveY = false;
    var oldInstanceX = 0;
    var oldInstanceY = 0;
    for (var i = 0; i < pointsIndex.length; i++) {
        for (var j = i + 1; j < pointsIndex.length; j++) {
            var tempInstanceX_Y = getInstanceCommon2P(city, pointsIndex[i], pointsIndex[j], typeP);
            if (tempInstanceX_Y.Ix > oldInstanceX && !haveX) {
                oldInstanceX = tempInstanceX_Y.Ix;
                pointsX.index0 = pointsIndex[i];
                pointsX.index1 = pointsIndex[j];
            }
            if (tempInstanceX_Y.Ix > typeP.minPreX && !haveX) {
                haveX = true;
            }
            if (tempInstanceX_Y.Iy > oldInstanceY && !haveY) {
                oldInstanceY = tempInstanceX_Y.Iy;
                pointsY.index0 = pointsIndex[i];
                pointsY.index1 = pointsIndex[j];
            }
            if (tempInstanceX_Y.Iy > typeP.minPreY && !haveY) {
                haveY = true;
            }
            if (haveY && haveX) {
                return { px: pointsX, py: pointsY };
            }
        }
    }
    return { px: pointsX, py: pointsY };
}

//得到两个点在指定坐标上的距离，兼容了25DMap的点。
function getInstanceCommon2P(city, index0, index1, typeP) {
    if (typeP.strX == "x" && typeP.strY == "y") {
        return getInstance2P_XY(city, index0, index1);
    }
    else {
        return getInstance2P(city, index0, index1, typeP);
    }
}

//得到25DMap两个点在X轴和Y轴上的距离。
function getInstance2P_XY(city, index0, index1) {
    var px_1 = fromMap(city, correct_pts[city][index0].x, correct_pts[city][index0].y);
    var px_2 = fromMap(city, correct_pts[city][index1].x, correct_pts[city][index1].y);
    var instanceX = Math.abs(px_1.x - px_2.x);
    var instanceY = Math.abs(px_1.y - px_2.y);
    return { Ix: instanceX, Iy: instanceY };
}
//得到两个点在指定坐标上的距离。
function getInstance2P(city, index0, index1, typeP) {
    var instanceX = Math.abs(correct_pts[city][index0][typeP.strX] - correct_pts[city][index1][typeP.strX]);
    var instanceY = Math.abs(correct_pts[city][index0][typeP.strY] - correct_pts[city][index1][typeP.strY]);
    return { Ix: instanceX, Iy: instanceY };
}

//得到指定点最近的四个点
function getFourNearIndex(city, x, y, typeP) {
    var p1 = 0;
    var p2 = 0;
    var p3 = 0;
    var p4 = 0;
    var minDis = typeP.initValue, secMinDis = typeP.initValue, thrMinDis = typeP.initValue, fouMinDis = typeP.initValue;
    for (var i = 0; i < correct_pts[city].length; i++) {
        var dis = getDis(correct_pts[city][i][typeP.strX], correct_pts[city][i][typeP.strY], x, y);
        if (dis < minDis) {
            fouMinDis = thrMinDis;
            thrMinDis = secMinDis;
            secMinDis = minDis;
            minDis = dis;
            p4 = p3;
            p3 = p2;
            p2 = p1;
            p1 = i;
        }
        else if (dis > minDis && dis < secMinDis) {
            fouMinDis = thrMinDis;
            thrMinDis = secMinDis;
            sedMinDis = dis;
            p4 = p3;
            p3 = p2;
            p2 = i;
        }
        else if (dis > secMinDis && dis < thrMinDis) {
            fouMinDis = thrMinDis;
            thrMinDis = dis;
            p4 = p3;
            p3 = i;
        }
        else if (dis > thrMinDis && dis < fouMinDis) {
            fouMinDis = dis;
            p4 = i;
        }
    }
    return new Array(p1, p2, p3, p4);
}

//计算坐标之间的距离。
function getDis(x, y, x1, y1) {
    return Math.abs(x - x1) + Math.abs(y - y1);
}
//从标准平面坐标得到地图坐标
function toMap(city, x, y) {
    var x2 = (x - y) * num[city].num;
    var y2 = (x + y) * num[city].num * num[city].num2;

    return { x: x2, y: y2 };
}
//从地图坐标得到标准平面坐标
function fromMap(city, x, y) {
    y = y / num[city].num2;
    var x2 = (x + y) / (num[city].num * 2);
    var y2 = (y - x) / (num[city].num * 2);

    return { x: x2, y: y2 };
}

//得到小范围地图精度
function getDgPix_mm(city, index0, index1) {

    var px_1 = fromMap(city, correct_pts[city][index0].x, correct_pts[city][index0].y);
    var px_2 = fromMap(city, correct_pts[city][index1].x, correct_pts[city][index1].y);

    var x_1 = px_1.x, y_1 = px_1.y;
    var x_2 = px_2.x, y_2 = px_2.y;

    var dj1 = correct_pts[city][index0].utm_x, dw1 = correct_pts[city][index0].utm_y;
    var dj2 = correct_pts[city][index1].utm_x, dw2 = correct_pts[city][index1].utm_y;

    var a = Math.abs((dj2 - dj1) * 100000 / (x_2 - x_1));
    var b = Math.abs((dw2 - dw1) * 100000 / (y_2 - y_1));
    //a,b每十万像素对应的经纬度
    return { j: a, w: b, x: 100000 / a, y: 100000 / b };
}

//得到小范围地图精度
function getDgPix_mm_25DMap(city, indexX0, indexX1, indexY0, indexY1, typeP) {
    var px_1 = fromMap(city, correct_pts[city][indexX0].x, correct_pts[city][indexX0].y);
    var px_2 = fromMap(city, correct_pts[city][indexX1].x, correct_pts[city][indexX1].y);
    var py_1 = fromMap(city, correct_pts[city][indexY0].x, correct_pts[city][indexY0].y);
    var py_2 = fromMap(city, correct_pts[city][indexY1].x, correct_pts[city][indexY1].y);

    var x_1 = px_1.x, x_2 = px_2.x;
    var y_1 = py_1.y, y_2 = py_2.y;

    var dj1 = correct_pts[city][indexX0][typeP.strX], dj2 = correct_pts[city][indexX1][typeP.strX];
    var dw1 = correct_pts[city][indexY0][typeP.strY], dw2 = correct_pts[city][indexY1][typeP.strY];

    var a = Math.abs((dj2 - dj1) * 100000 / (x_2 - x_1));
    var b = Math.abs((dw2 - dw1) * 100000 / (y_2 - y_1));
    //a,b每十万像素对应的经纬度
    return { j: a, w: b, x: 100000 / a, y: 100000 / b };
}

//从经纬度得到地图像素值,如需将地图坐标转换成经纬则反过来算即可
//小范围内地图满足线性关系
function getPx_mm(city, utm_x, utm_y, px, py, typeP) {
    var px_src = correct_pts[city][px.index0];
    var gp_src = correct_pts[city][px.index0];
    var dgPix = getDgPix_mm_25DMap(city, px.index0, px.index1, py.index0, py.index1, typeP);
    var px_1 = fromMap(city, px_src.x, px_src.y);
    var dj1 = gp_src[typeP.strX], dw1 = gp_src[typeP.strY];
    var dj = utm_x, dw = utm_y;

    var x_1 = px_1.x;
    var y_1 = px_1.y;

    var dj_s = dj - dj1, dw_s = dw - dw1;

    var x = dj_s * dgPix.x + x_1;
    var y = -dw_s * dgPix.y + y_1;

    var r = toMap(city, x, y);
    return r;
}
function getJw_mm(city, x, y, px1, py1, typeP) {
    var mappx_src = correct_pts[city][px1.index0];
    var gp_src = correct_pts[city][px1.index0];
    var dgPix = getDgPix_mm_25DMap(city, px1.index0, px1.index1, py1.index0, py1.index1, typeP);

    var px = fromMap(city, x, y);
    var px_src = fromMap(city, mappx_src.x, mappx_src.y);
    //var dj1=gp_src.utm_x,dw1=gp_src.utm_y;
    var dj1 = gp_src[typeP.strX], dw1 = gp_src[typeP.strY];

    var x_1 = px_src.x;
    var y_1 = px_src.y;

    var px_s = px.x - x_1, py_s = y_1 - px.y;

    var gp_j = px_s / dgPix.x + dj1;
    var gp_w = py_s / dgPix.y + dw1;
    return { lng: gp_j, lat: gp_w };
}

//接口函数
function get25DMap_pts(city, pts) {
    if (!pts.px || !pts.py) {
        var typeP = getTypeP("utm");
        var twoIndexs = getPx_Py(city, pts.x, pts.y, typeP);
        pts.px = twoIndexs.px;
        pts.py = twoIndexs.py;
    }
    return get25DMap_index(city, pts.utm_x, pts.utm_y, pts.px, pts.py);
}

//接口函数
function getMapJw_pts(city, pts) {
    if (!pts.px || !pts.py) {
        var typeP = getTypeP("xy");
        var twoIndexs = getPx_Py(city, pts.x, pts.y, typeP);
        pts.px = twoIndexs.px;
        pts.py = twoIndexs.py;
    }
    return getMapJw_index(city, pts.x, pts.y, pts.px, pts.py);
}
function get25DMap_index(city, utm_x, utm_y, px0, py0, typeP) {
    var xy = getPx_mm(city, utm_x, utm_y, px0, py0, typeP);
    return { x: xy.x, y: xy.y, px: px0, py: py0 };
}
function getMapJw_index(city, x, y, px0, py0, typeP) {
    var lnglat = getJw_mm(city, x, y, px0, py0, typeP);
    return { lng: lnglat.lng, lat: lnglat.lat, px: px0, py: py0 };
}
function getMapJw_Array(city, pts) {
    var typeP = getTypeP("xy");
    var twoIndexs = getPx_Py(city, pts[0].x, pts[0].y, typeP);
    //墨卡托坐标坐标的转换
    var typeP1 = getTypeP("utm");
    var lnglat = new Array;
    for (var i = 0; i < pts.length; i++) {
        var tmp = getJw_mm(city, pts[i].x, pts[i].y, twoIndexs.px, twoIndexs.py, typeP1);
        lnglat[i] = { lng: tmp.lng, lat: tmp.lat, px: twoIndexs.px, py: twoIndexs.py };
    }
    return lnglat;
}
function get25DMap_Array(city, pts) {
    var typeP = getTypeP("utm");
    var twoIndexs = getPx_Py(city, pts[0].utm_x, pts[0].utm_y, typeP);
    var xy = new Array;
    for (var i = 0; i < pts.length; i++) {
        var tmp = getPx_mm(city, pts[i].utm_x, pts[i].utm_y, twoIndexs.px, twoIndexs.py, typeP);
        xy[i] = { lng: tmp.x, lat: tmp.y, px: twoIndexs.px, py: twoIndexs.py };
    }
    return xy;
}
//25D坐标得到经纬度
function getMapJw(city, x, y) {
    y = -y;
    var typeP = getTypeP("xy");
    var twoIndexs = getPx_Py(city, x, y, typeP);
    var typeP1 = getTypeP("jw");
    return getMapJw_index(city, x, y, twoIndexs.px, twoIndexs.py, typeP1);
}
//经纬度得到25D坐标
function get25DMap(city, j, w) {
    var typeP = getTypeP("jw");
    var twoIndexs = getPx_Py(city, j, w, typeP);
    var pt = get25DMap_index(city, j, w, twoIndexs.px, twoIndexs.py, typeP);
    pt.y = -pt.y;
    return pt;
}
//UTM坐标得到25D坐标
function get25DMap_mm(city, utm_x, utm_y) {
    var typeP = getTypeP("utm");
    var twoIndexs = getPx_Py(city, utm_x, utm_y, typeP);
    return get25DMap_index(city, utm_x, utm_y, twoIndexs.px, twoIndexs.py, typeP);
}
//25D坐标得到UTM坐标
function getMapJw_mm(city, x, y) {
    var typeP = getTypeP("xy");
    var twoIndexs = getPx_Py(city, x, y, typeP);
    var typeP1 = getTypeP("utm");
    return getMapJw_index(city, x, y, twoIndexs.px, twoIndexs.py, typeP1);
}
function parsePoints() {
    for (var item in correct_pts) {
        var arr = correct_pts[item];
        for (var i = 0; i < arr.length; i++) {
            arr[i].y = -arr[i].y;
        }
    }
}
parsePoints();
