package com.baizhi.dao;

import com.baizhi.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierDAO {
    List<Supplier> findBypage(@Param("begin") Integer begin,
                              @Param("end") Integer end,
                              @Param("searchField") String searchField,
                              @Param("searchString") String searchString,
                              @Param("searchOper") String searchOper);
    void add(Supplier supplier);
    void delete(String id);
    void update(Supplier supplier);
    Supplier findOne(String id);
    Integer findCount();

}
