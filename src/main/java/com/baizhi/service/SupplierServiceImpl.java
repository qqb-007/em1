package com.baizhi.service;

import com.baizhi.dao.SupplierDAO;
import com.baizhi.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("supplierService")
@Transactional
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDAO supplierDAO;


    @Override
    public List<Supplier> findByPage(Integer rows, Integer page, String searchField, String searchString, String searchOper) {
        Integer begin = (page-1)*rows;
        return supplierDAO.findBypage(begin,rows,searchField,searchString,searchOper);
    }

    @Override
    public void add(Supplier supplier) {
       supplier.setId(UUID.randomUUID().toString());
       supplierDAO.add(supplier);
    }

    @Override
    public void delete(String id) {
    supplierDAO.delete(id);
    }

    @Override
    public void update(Supplier supplier) {
    supplierDAO.update(supplier);
    }

    @Override
    public Supplier findOne(String id) {
        return supplierDAO.findOne(id);
    }

    @Override
    public Integer findCount() {
        return supplierDAO.findCount();
    }
}
