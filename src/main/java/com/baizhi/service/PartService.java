package com.baizhi.service;

import com.baizhi.entity.Part;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartService {
    void delete(String id);
    Integer findCount();
    List<Part> findBypage(Integer rows,Integer page,
                          String searchField,
                          String searchString,
                          String searchOper);
    List<Part> findAll();
    void add(Part part);
    void update(Part part);
}
