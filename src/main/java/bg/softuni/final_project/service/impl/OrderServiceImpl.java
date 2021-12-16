package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.BookEntity;
import bg.softuni.final_project.model.entity.CartItem;
import bg.softuni.final_project.model.entity.OrderEntity;
import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.repository.OrderRepository;
import bg.softuni.final_project.service.CartService;
import bg.softuni.final_project.service.OrderService;
import bg.softuni.final_project.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderServiceImpl(CartService cartService, OrderRepository orderRepository, UserService userService) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void createOrder(String username, Integer orderSum) {

        List<CartItem> allByUsername = cartService.findAllByUsername(username);

        List<BookEntity> bookItems = new ArrayList<>();
        allByUsername.forEach(cartItem -> bookItems.add(cartItem.getBook()));


        cartService.deleteAll(allByUsername);
        UserEntity user = userService.findByUsername(username);
        OrderEntity newOrder = new OrderEntity(user, orderSum, bookItems);
        user.getOrders().add(newOrder);
        orderRepository.save(newOrder);
    }
}
