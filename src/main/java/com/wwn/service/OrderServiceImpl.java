package com.wwn.service;

import com.wwn.dao.orders.OrdersMapper;
import com.wwn.dao.users.UsersMapper;
import com.wwn.model.Orders;
import com.wwn.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {


    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    @Transactional
    public void addOrder(Orders orders, Users users) {
        usersMapper.insertSelective(users);
//        int i = 10/0;
        ordersMapper.insertSelective(orders);
    }
}
