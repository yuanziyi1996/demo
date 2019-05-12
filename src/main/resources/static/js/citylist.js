var mp = new BMap.Map("allmap");
var point = new BMap.Point(116.404, 39.915);
mp.centerAndZoom(point, 14);
mp.enableScrollWheelZoom();
mp.enableInertialDragging();

mp.enableContinuousZoom();

var size = new BMap.Size(10, 20);
mp.addControl(new BMap.CityListControl({
    anchor: BMAP_ANCHOR_TOP_LEFT,
    offset: size,
    // 切换城市之间事件
    // onChangeBefore: function(){
    //    alert('before');
    // },
    // 切换城市之后事件
    // onChangeAfter:function(){
    //   alert('after');
    // }
}));