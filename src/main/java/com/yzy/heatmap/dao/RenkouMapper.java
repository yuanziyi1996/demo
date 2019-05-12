package com.yzy.heatmap.dao;


import com.yzy.heatmap.po.Renkou;

import java.util.List;

public interface RenkouMapper {
  List<Renkou> getAll();
  int count();
  List<Renkou> getInfo();
}
