package com.edupilot.backend.service.implementation;

import com.edupilot.backend.custom_exception.DuplicateOrder;
import com.edupilot.backend.custom_exception.OrderNotFound;
import com.edupilot.backend.model.Course;
import com.edupilot.backend.model.Order;
import com.edupilot.backend.model.User;
import com.edupilot.backend.model.enums.OrderStatus;
import com.edupilot.backend.repository.OrderRepository;
import com.edupilot.backend.service.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    /**
     * Place an order
     *
     * @param course
     * @param user
     */
    @Override
    @Transactional
    public Order placeOrder(Course course, User user) {

        Optional<Order> previousOrder = orderRepository.findByCourseAndUser(course, user);
        if (previousOrder.isPresent()) {
            boolean previousOrderInProgress = previousOrder.get().getOrderStatus() == OrderStatus.IN_PROGRESS;
            if (previousOrderInProgress) {
                throw new DuplicateOrder();
            }
        }
        Order order = Order.builder()
                .course(course)
                .user(user)
                .price(999L)
                .orderStatus(OrderStatus.IN_PROGRESS)
                .build();
        return orderRepository.save(order);
    }

    /**
     * Get the order
     *
     * @param orderId
     * @return
     */
    @Override
    public Order getOrderById(Long orderId) {

        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFound(orderId));
    }
}
