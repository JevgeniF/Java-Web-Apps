package app;

import model.Order;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    private final OrderDao dao;

    public OrderController(OrderDao dao) {
        this.dao = dao;
    }

    @GetMapping("orders")
    public List<Order> getOrders() {
        return dao.queryOrder();
    }

    @GetMapping("orders/{id}")
    public Order getOrder(@PathVariable Long id) {
        return dao.queryOrderByID(id);
    }

    @PostMapping("orders")
    public Order postPosts(@RequestBody @Valid Order order) {
        return dao.addOrder(order);
    }

    @DeleteMapping("orders/{id}")
    public void deletePosts(@PathVariable Long id) {
        dao.deleteOrderByID(id);
    }

}