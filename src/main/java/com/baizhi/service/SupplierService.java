package com.baizhi.service;

import com.baizhi.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierService {
    List<Supplier> findByPage(Integer rows,Integer page,
                              String searchField,
                              String searchString,
                              String searchOper);
    void add(Supplier supplier);
    void delete(String id);
    void update(Supplier supplier);
    Supplier findOne(String id);
    Integer findCount();
}
