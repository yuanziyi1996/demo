// 百度地图API功能
var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point(112.533643,32.998098), 9);

map.enableScrollWheelZoom();
madeBoundary();


//区域图
function madeBoundary() {

    var datas = new Array("南召县-#665599","方城县-#559955","邓州市-#666ddd","社旗县-#1199cc");
    var bdary = new BMap.Boundary();
    for(var i=0;i<datas.length;i++){
        getBoundary(datas[i],bdary);

    }

}
//设置区域图

function getBoundary(data,bdary){
    data = data.split("-");
    console.log(data)
    bdary.get(data[0], function(rs){       //获取行政区域
        var count = rs.boundaries.length; //行政区域的点有多少个

        var pointArray = [];
        for (var i = 0; i < count; i++) {
            var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 2, strokeColor: "#ff0000",fillOpacity:0.5,fillColor:data[1]}); //建立多边形覆盖物

            map.addOverlay(ply);  //添加覆盖物

        }



    });



}

