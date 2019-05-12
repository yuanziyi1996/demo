package com.yzy.heatmap.controller;


import com.yzy.heatmap.Utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@RequestMapping({ "", "index" })
	public String getIndexPage() {
		return "index";
	}

	@RequestMapping(value = "demo")
	public String getDemo(){
		System.out.println("demo接口");
		return "demo";
	}
	@RequestMapping(value = "fanxuan")
	public String getFanxuan(){
		System.out.println("fanxuan接口");
		return "fanxuan";
	}

	@RequestMapping(value = "duoge")
	public String getDuoge(){
		System.out.println("duoge接口");
		return "duoge";
	}



	@RequestMapping(value = "test")
	public String getTest(){
		System.out.println( 	"test 接口");
		Result result = new Result();
		result.put("test","test");
		return "test";
	}

	@RequestMapping(value = "relitu")
	public String getRelitu(){
		System.out.println( 	"relitu 接口");
		return "relitu";
	}

	@ResponseBody
	@RequestMapping(value = "hello")
	public Result getHello(){
		System.out.println("hello 接口");
		return new Result();
	}


	@RequestMapping(value = "old")
	public String getHaa(){
		System.out.println("haha 接口");
		return "oldindex";
	}
	@RequestMapping(value = "address")
	public String getFullAddress(){
		System.out.println("address 接口");
		return "fullAddress";
	}



}
