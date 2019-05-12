package com.yzy.heatmap.dao;


import com.yzy.heatmap.po.Point;
import com.yzy.heatmap.po.PointExample;

import java.util.List;


public interface PointMapper {
	List<Point> selectByExample(PointExample example);
}