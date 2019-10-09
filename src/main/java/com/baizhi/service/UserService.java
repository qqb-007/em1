package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {
    Integer findCount();
    List<User> findAll();
    void add(User user);
    void delete(String id);
    User findOne(String id);
    void update(User user);
    List<User> findByPage(Integer rows,Integer page,
                         String searchField,
                         String searchString,
                         String searchOper);
}
