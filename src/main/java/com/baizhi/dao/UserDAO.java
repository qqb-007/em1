package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {
    List<User> findBypage(@Param("begin") Integer begin,
                          @Param("end") Integer end,
                          @Param("searchField") String searchField,
                          @Param("searchString") String searchString,
                          @Param("searchOper") String searchOper);
    List<User> findAll();
    void add(User user);
    void delete(String id);
    User findOne(String id);
    void update(User user);
    Integer findCount();

    void deleteBypid(String id);
}
