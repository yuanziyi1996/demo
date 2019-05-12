var mp = new BMap.Map("allmap");
var point = new BMap.Point(116.404, 39.915);
mp.centerAndZoom(point, 14);
mp.enableScrollWheelZoom();
mp.enableInertialDragging();

mp.enableContinuousZoom();


var size = new BMap.Size(150, 70);//控件的水平偏移值
var ChinaProvinces = ["河北省", "山西省",
    "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省",
    "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
    "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区",
    "海南省", "四川省", "贵州省", "云南省", "西藏自治区",
    "陕西省", "甘肃省", "青海省", "宁夏回族自治区", "新疆维吾尔族自治区",
    "香港特别行政区", "澳门特别行政区", "台湾省"];
var zhixiashi = ["北京市", "天津市", "重庆市", "上海市"];
var heatMapData = [];
heatmapOverlay = new BMapLib.HeatmapOverlay({"radius": 20});
mp.addOverlay(heatmapOverlay);
var city; //当前所在的城市或者省份
var cityList = [];  //详细城市，具体到区县
var allProvince = [];
var allCitys = []; //具体到市级
var fenShiRenKou = [];//省份下的分时人口
var fenQuRenkou = [];//直辖市下的分区人口
var fenShengRenKou = [];
var fenQuXianRenKou = [];//地级市的分区县
$.ajax({
    type: "GET",
    url: "getRenKou",
    data: {},
    dataType: "json",
    success: function(data){
        console.log(data);
        city="全国";
        allProvince = data.currentProvinces;
        $("#renkouAmount").html("全国人口： " + data.quanGuoRenkou + "万人");
        data.cityList.forEach(function (item) {
            heatMapData.push({lng: item.lng, lat: item.lat, count: item.renkou});
        });
        heatmapOverlay.setDataSet({data:heatMapData,max:20});
    }
});
mp.addControl(new BMap.CityListControl({
    anchor: BMAP_ANCHOR_TOP_LEFT,
    offset: size,
    // 切换城  市之间事件在·
    onChangeBefore: function () {
    },
    // 切换城市成功响应事件
    onChangeSuccess: function (e) {
        city = [];
        city = e.title;
        console.log(e)
        $.ajax({
            type: "GET",
            url: "getCityList",
            data: {city: city},
            dataType: "json",
            success: function (data) {
                console.log(data);
                mp.clearOverlays();
                cityList = [];
                cityList = data.cityDetail;
                heatMapData = [];
                allProvince = data.currentProvinces;
                allCitys = [];
                allCitys = data.allCitis;
                fenQuRenkou = data.fenQuRenkou;
                fenShiRenKou = data.fenShiRenKou;
                fenShengRenKou = data.fenShengRenKou;
                fenQuXianRenKou = data.fenQuXianRenKou;
                // console.log(cityList);
                console.log(city)

                if (city == "全国" || ChinaProvinces.includes(city)) {
                    if (city == "全国") {
                        $("#renkouAmount").html("全国人口： " + data.quanGuoRenKou + "万人");
                    } else if (zhixiashi.includes(city)) {
                        $("#renkouAmount").html(city+"人口： " + data.quanShengRenKou + "万人");
                    } else {
                        $("#renkouAmount").html(city+"人口： " + data.quanShengRenKou + "万人");
                    }

                    for (var i = 0; i < allProvince.length; i++) {
                        getBoundary(allProvince[i]);
                    }
                } else if (zhixiashi.includes(city)) {
                    $("#renkouAmount").html(city+"人口： " + data.quanShengRenKou + "万人");
                    getBoundary(city);
                } else {
                    // console.log(cityList)
                    $("#renkouAmount").html(allProvince[0]+city+"人口： " + data.quanShiRenKou + "万人");
                    getBoundary(city);
                    /*for (var i = 0; i < cityList.length; i++) {
                        getBoundary(cityList[i], bdary);
                    }*/
                }

                data.cityList.forEach(function (item) {
                    heatMapData.push({lng: item.lng, lat: item.lat, count: item.renkou});
                });
                // console.log(heatMapData)
                //使用热力图：
                useHeatMap();

            }
        })
    }
}));

var geoc = new BMap.Geocoder();

mp.addEventListener("click", function (e) {
    var pt = e.point;
    if (city == "全国") {
        geoc.getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            var province = addComp.province;
            fenShengRenKou.forEach(function (item) {
                if (province == item.province) {
                    $("#renkouAmount").html(province + "人口： " + item.renkou + "万人");
                }
            })
        });
    } else if (ChinaProvinces.includes(city)) {
        //先显示全省人数，点击之后显示各市的人数
        geoc.getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            var chengshi = addComp.city;
            if(city=="澳门特别行政区"||city=="香港特别行政区"){
                fenShiRenKou.forEach(function (item) {
                    if (addComp.district == item.quxian) {
                        $("#renkouAmount").html(chengshi+ addComp.district + "人口： " + item.renkou + "万人");
                    }
                })
            }else{
                fenShiRenKou.forEach(function (item) {
                    if (chengshi == item.shiji) {
                        $("#renkouAmount").html(addComp.province+chengshi + "人口： " + item.renkou + "万人");
                    }
                })
            }

        });
    } else if (zhixiashi.includes(city)) {
        //先显示全市人数，点击之后显示 各区人数
        geoc.getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            var quxian = addComp.district;
            fenQuRenkou.forEach(function (item) {
                if (quxian == item.quxian) {
                    $("#renkouAmount").html(addComp.province+quxian + "人口： " + item.renkou + "万人");
                }
            })
        });
    } else {
        //先显示本事的人数，点击之后显示各区县人数。
        geoc.getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            var chengshi = addComp.district;
            fenQuXianRenKou.forEach(function (item) {
                if (chengshi == item.quxian) {
                    $("#renkouAmount").html(addComp.province+addComp.city+chengshi + "人口： " + item.renkou + "万人");
                }
            })
        });
    }
});


function getBoundary(cityDetail) {
    var bdary = new BMap.Boundary();
    bdary.get(cityDetail, function (rs) {       //获取行政区域
                                                // mp.clearOverlays();        //清除地图覆盖物
        var count = rs.boundaries.length; //行政区域的点有多少个
        // console.log(rs.boundaries)
        if (count === 0) {
            console.log("没有该城市的行政区域", cityDetail);
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
                    strokeWeight: 1,
                    strokeOpacity: 1,
                    strokeColor: "#ff0000",
                    fillOpacity: 0.01,
                    fillColor: "#7bff73",
                    // fillOpacity:0.1
                }); //建立多边形覆盖物
            mp.addOverlay(ply);  //添加覆盖物
            pointArray = pointArray.concat(ply.getPath());
        }

    });

}


function updateTextInput(val) {
    document.getElementById('r').value = val;
}

function updateTextInput1(val) {
    document.getElementById('a').value = val;
}

function Clear() {
    getBoundary(city);
    mp.clearOverlays();
    showHeatMap();
}

function showBoundary() {
    console.log(city)
    console.log(cityList)
    if (city == "全国") {
        mp.clearOverlays();
        for (var i = 0; i < allProvince.length; i++) {
            getBoundary(allProvince[i]);
        }
        showHeatMap();
    } else if (zhixiashi.includes(city)) {
        for (var i = 0; i < cityList.length; i++) {
            getBoundary(cityList[i]);
        }
    } else if (ChinaProvinces.includes(city)) {
        for (var i = 0; i < allCitys.length; i++) {
            getBoundary(allCitys[i]);
        }
    } else {
        for (var i = 0; i < cityList.length; i++) {
            getBoundary(cityList[i]);
        }
    }

}

function useHeatMap() {
    console.log("添加热力图")
    mp.addOverlay(heatmapOverlay);
    heatmapOverlay.setDataSet({data: heatMapData, max: 20});
    heatmapOverlay.show();
}

function showHeatMap() {
    var m1 = document.getElementById("a").value;
    var r1 = document.getElementById("r").value;
    heatmapOverlay.hide();
    heatmapOverlay = new BMapLib.HeatmapOverlay({"radius": r1});
    mp.addOverlay(heatmapOverlay);
    heatmapOverlay.setDataSet({data: heatMapData, max: m1});
    heatmapOverlay.show();
}

function closeHeatmap() {
    heatmapOverlay.hide();
}
