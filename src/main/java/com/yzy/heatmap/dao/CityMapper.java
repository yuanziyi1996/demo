package com.yzy.heatmap.dao;



import com.yzy.heatmap.po.City;

import java.util.HashMap;
import java.util.List;

public interface CityMapper {
    List<City> getCityList(String city);

    List<City> getAll(String city);

    List<City> getAllByProvince(String city);

    List<HashMap<String,Integer>>fenShengRenkou();

    List<HashMap<String,Integer>>fenShiRenkouByProvince(String province);

    List<HashMap<String,Integer>>fenQuXianRenKou(String city);

    List<HashMap<String,Integer>>ZfenQuXianRenKou(String city);

    Double getAllRenKou();
}
