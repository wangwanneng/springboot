package com.wwn.test;

import com.wwn.App;
import com.wwn.model.Orders;
import com.wwn.model.Users;
import com.wwn.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class DataSourceTest {

    @Autowired
    private IOrderService orderService;

    @Test
    public void test1() {
        Users users = new Users();
        users.setUsername("enjoy");
        users.setPasswd("123");
        users.setId(1);

        Orders orders = new Orders();
        orders.setAccount("12");
        orders.setName("娃娃");
        orders.setUserId(1);
        orderService.addOrder(orders,users);
    }

}
