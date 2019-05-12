package com.yzy.heatmap.service;

import com.yzy.heatmap.dao.RenkouMapper;
import com.yzy.heatmap.po.Renkou;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenkouService {
  @Autowired
  RenkouMapper renkouMapper;

  public List<Renkou> getAll() {
    System.out.println("Renkou.getAll()");
    /*List result = new ArrayList();
    result = renkouMapper.getAll();*/
    return renkouMapper.getAll();
  }

  public Integer count(){
    return renkouMapper.count();
  }
  public List<Renkou> getInfo() {
    System.out.println("Renkou.getInfo()");
    /*List result = new ArrayList();
    result = renkouMapper.getAll();*/
    return renkouMapper.getInfo();
  }

}
