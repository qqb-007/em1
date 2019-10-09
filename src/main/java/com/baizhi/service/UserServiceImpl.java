package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public Integer findCount() {
        return userDAO.findCount();
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void add(User user) {
        user.setId(UUID.randomUUID().toString());
     userDAO.add(user);
    }

    @Override
    public void delete(String id) {
      userDAO.delete(id);
    }

    @Override
    public User findOne(String id) {
        return userDAO.findOne(id);
    }

    @Override
    public void update(User user) {
     userDAO.update(user);
    }

    @Override
    /**
     *
     * @param rows  每页展示信息的条数
     * @param page  当前的页码
     * @return
     */
    public List<User> findByPage(Integer rows, Integer page, String searchField, String searchString, String searchOper) {
        Integer begin = (page-1)*rows;

        return userDAO.findBypage(begin,rows,searchField,searchString,searchOper);
    }
}

