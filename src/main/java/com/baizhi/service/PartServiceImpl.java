package com.baizhi.service;

import com.baizhi.dao.PartDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("partService")
@Transactional
public class PartServiceImpl implements PartService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PartDAO partDAO;


    @Override
    public void delete(String id) {
      userDAO.deleteBypid(id);
      partDAO.delete(id);
    }

    @Override
    public Integer findCount() {
        return partDAO.findCount();
    }

    @Override
    public List<Part> findBypage(Integer rows, Integer page, String searchField, String searchString, String searchOper) {
        Integer begin = (page-1)*rows;
        return partDAO.findBypage(begin,rows,searchField,searchString,searchOper);
    }

    @Override
    public List<Part> findAll() {
        return partDAO.findAll();
    }

    @Override
    public void add(Part part) {
    part.setId(UUID.randomUUID().toString());
    partDAO.add(part);
    }

    @Override
    public void update(Part part) {
   partDAO.update(part);
    }
}
