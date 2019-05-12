package com.yzy.heatmap.controller;


import com.yzy.heatmap.Utils.Result;
import com.yzy.heatmap.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "getCityList",method = RequestMethod.GET)
    public Result getCityList(@RequestParam String city){
        Result result = new Result();
        result = cityService.getCityList(city);
        return result;
    }
    @RequestMapping(value = "getRenKou",method = RequestMethod.GET)
    public Result getRenKou(){
        return cityService.getRenKou();
    }
}
