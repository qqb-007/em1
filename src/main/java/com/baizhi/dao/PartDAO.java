package com.baizhi.dao;

import com.baizhi.entity.Part;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartDAO {
    List<Part> findBypage(@Param("begin") Integer begin,
                          @Param("end") Integer end,
                          @Param("searchField") String searchField,
                          @Param("searchString") String searchString,
                          @Param("searchOper") String searchOper);
    List<Part> findAll();
    Part findOne(String id);
    void add(Part part);
    void update(Part part);
    Integer findCount();
    void delete(String id);
}
