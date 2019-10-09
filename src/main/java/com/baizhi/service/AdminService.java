package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    Admin findBynp(String name, String password);
}
