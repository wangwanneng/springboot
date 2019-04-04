package com.wwn.service;


import com.wwn.model.Users;

public interface IUserservice {

    boolean login(String username,String passwd);
    boolean register(String username,String passwd);
    void batchAdd(String username,String passwd);

}
