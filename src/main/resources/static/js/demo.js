var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point(116.403765, 39.914850), 5);
map.enableScrollWheelZoom();

function getBoundary() {
    var bdary = new BMap.Boundary();
    bdary.get("北京市", function (rs) {       //获取行政区域
        map.clearOverlays();        //清除地图覆盖物
        var count = rs.boundaries.length; //行政区域的点有多少个
        if (count === 0) {
            alert('未能获取当前输入行政区域');
            return;
        }
        var pointArray = [];
        for (var i = 0; i < count; i++) {
            var ply = new BMap.Polygon(rs.boundaries[i],
                /*{strokeWeight: 2, //设置多边形边线线粗
                    strokeOpacity: 1, //设置多边形边线透明度0-1
                    StrokeStyle: "solid", //设置多边形边线样式为实线或虚线，取值 solid 或 dashed
                    strokeColor: "#ff0000", //设置多边形边线颜色
                    fillColor: "#00ffff", //设置多边形填充颜色
                    fillOpacity:0.01 //设置多边形填充颜色透明度0-1 注：标红的地放你们可以去掉看一下效果，自己体验一下
                }*/
                {
                    strokeWeight: 2,
                    strokeColor: "#ff0000",
                    fillOpacity:0.01
                }); //建立多边形覆盖物
            map.addOverlay(ply);  //添加覆盖物
            pointArray = pointArray.concat(ply.getPath());
        }

        map.setViewport(pointArray);    //调整视野
    });

}

setTimeout(function () {
    getBoundary();
}, 500);