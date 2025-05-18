package com.edupilot.backend.service.interfaces;

import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Order;
import com.edupilot.backend.model.User;

public interface OrderService {

    public Order placeOrder(Course course, User user);

    public Order getOrderById(Long orderId);
}
