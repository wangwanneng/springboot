package com.wwn.service;

import com.wwn.dao.users.UsersMapper;
import com.wwn.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements IUserservice {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean login(String username,String passwd) {
        Users user = new Users();
        user.setUsername(username);
        user.setPasswd(passwd);
        Users users = usersMapper.findbyUsernameAndPwd(user);
        return users != null;
    }

    @Override
    public boolean register(String username,String passwd) {
        Users user = new Users();
        user.setUsername(username);
        user.setPasswd(passwd);
        int count = usersMapper.insertSelective(user);
        return count > 0;
    }

    @Override
    @Transactional
    public void batchAdd(String username, String passwd) {
        Users users = new Users();
        users.setUsername(username);
        users.setPasswd(passwd);
        usersMapper.insertSelective(users);
        int i = 10 /0;
        users = new Users();
        users.setUsername(username+"2");
        users.setPasswd(passwd);
        usersMapper.insertSelective(users);
    }
}
