package com.yzy.heatmap.service;


import com.yzy.heatmap.Utils.Result;
import com.yzy.heatmap.dao.CityMapper;
import com.yzy.heatmap.po.City;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public static List<String> CHINA_PROVINCE = Arrays.asList("河北省", "山西省",
            "内蒙古自治区", "辽宁省", "吉林省", "黑龙江省",
            "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省",
            "河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区",
            "海南省",  "四川省", "贵州省", "云南省", "西藏自治区",
            "陕西省", "甘肃省", "青海省", "宁夏回族自治区", "新疆维吾尔族自治区",
            "香港特别行政区", "澳门特别行政区", "台湾省");
    public static List<String> ZHIXIASHI = Arrays.asList("北京市", "天津市","上海市","重庆市");


    public Result getCityList(String city) {
        Result result = new Result();
        if (StringUtils.isEmpty(city)) {
            return result;
        }
        List<City> cityList = new ArrayList<>();
        if (CHINA_PROVINCE.contains(city)) {
            cityList = cityMapper.getAllByProvince(city);
            double quanShengRenKou = cityList.stream().mapToDouble(city1->city1.renkou).sum();
            result.put("quanShengRenKou",quanShengRenKou);
            //添加该省份下的分地级市的 人口
            if(city.equals("香港特别行政区")||city.equals("澳门特别行政区")){
                result.put("fenShiRenKou",cityMapper.ZfenQuXianRenKou(city));
            }else{
                result.put("fenShiRenKou",cityMapper.fenShiRenkouByProvince(city));
            }
        }else if (ZHIXIASHI.contains(city)) {
            cityList = cityMapper.getAllByProvince(city);
            double quanShengRenKou = cityList.stream().mapToDouble(city1->city1.renkou).sum();
            result.put("quanShengRenKou",quanShengRenKou);
            //添加该省份下的分地级市的 人口
            result.put("fenQuRenkou",cityMapper.ZfenQuXianRenKou(city));
            System.out.println(city);
        }else if (city.equals("全国")) {
            cityList = cityMapper.getAll(city);
            double quanGuoRenKou = cityList.stream().mapToDouble(city1->city1.renkou).sum();
            result.put("quanGuoRenKou",quanGuoRenKou);
            result.put("fenShengRenKou",cityMapper.fenShengRenkou());
            System.out.println(city);
        } else {//这个是地级市
            cityList = cityMapper.getCityList(city);
            //全市人口：
            double quanShiRenKou = cityList.stream().mapToDouble(city1->city1.renkou).sum();
            result.put("quanShiRenKou",quanShiRenKou);
            result.put("fenQuXianRenKou",cityMapper.fenQuXianRenKou(city));
            System.out.println(city);
        }
        List<String> cityAndquxian = new ArrayList<>();
        cityList.stream().forEach(city1 -> {
            String cityDetail = city1.shiji + city1.quxian;
            cityAndquxian.add(cityDetail);
        });
        List<String> currentProvinces = new ArrayList<>();
        List<String> allCities = new ArrayList<>();
        cityList.stream().forEach(
                city1 -> {
                    currentProvinces.add(city1.province);
                    allCities.add(city1.shiji);
                }
        );
        result.put("allCitis", allCities.stream().distinct().collect(Collectors.toList()));
        result.put("currentProvinces", currentProvinces.stream().distinct().collect(Collectors.toList()));
        result.put("cityList", cityList);
        result.put("cityDetail", cityAndquxian);
        return result;
    }

    public Result getRenKou(){
        Result result = new Result();
        List<City> cityList = cityMapper.getAll("全国");
        List<String> currentProvinces = new ArrayList<>();
        cityList.stream().forEach(
                city1 -> {
                    currentProvinces.add(city1.province);
                }
        );
        result.put("quanGuoRenkou",cityMapper.getAllRenKou());
        result.put("cityList", cityList);
        result.put("currentProvinces",currentProvinces.stream().distinct().collect(Collectors.toList()));
        return result;
    }

}
