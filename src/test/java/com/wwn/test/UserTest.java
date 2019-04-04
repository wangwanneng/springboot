package com.wwn.test;

import com.wwn.App;
import com.wwn.dao.users.UsersMapper;
import com.wwn.model.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testAdd(){

        Users users = new Users();
        users.setUsername("enjoy");
        users.setPasswd("123");
        usersMapper.insert(users);
    }

    @Test
    public void testFindUser(){
        Users users = new Users();
        users.setUsername("enjoy");
        users.setPasswd("123");
        Users user = usersMapper.findbyUsernameAndPwd(users);
        System.out.println(user);

    }


}
