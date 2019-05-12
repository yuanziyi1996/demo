package com.yzy.heatmap.service;


import com.yzy.heatmap.dao.PointMapper;
import com.yzy.heatmap.dao.RenkouMapper;
import com.yzy.heatmap.po.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointService {

	@Autowired
	private PointMapper pointMapper;

	@Autowired
	private RenkouMapper renkouMapper;
	
	@Cacheable(value = "points")
	public List<Point> getAll() {
		System.out.println("PointController.getAll()");
		int a = renkouMapper.count();
		System.out.println(a);
		return pointMapper.selectByExample(null);
	}


}
