package com.wwn.service;

import com.wwn.model.Orders;
import com.wwn.model.Users;

public interface IOrderService {
    void addOrder(Orders orders, Users users);
}
