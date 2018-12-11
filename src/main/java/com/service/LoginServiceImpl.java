package com.service;

import com.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly=true)
    public boolean login(String userName,String password){
        return userDao.find(userName, password);
    }
}