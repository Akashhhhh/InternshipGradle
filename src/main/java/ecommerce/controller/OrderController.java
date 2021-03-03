package ecommerce.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import ecommerce.cache.LruCacheService;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.Vector;

/**
 * This class is used for managing place,delete and show order operation
 *
 * @author Akash gupta
 */
@RequestMapping("/user")
@RestController
public class OrderController {

    OrderDao orderDao = new OrderDao();
    OrderService orderService = new OrderService();
    java.sql.Connection con = ecommerce.util.Connection.create();

    @PostMapping("/addOrder")
    public String placeOrder(@Valid @RequestBody ObjectNode objectNode)  {


        LruCacheService lruCacheService = new LruCacheService();
        String email = objectNode.get("emailId").asText();
        UUID custId = null;
        try {
            custId = orderService.CheckEmailId(email, lruCacheService, con);
        } catch (ApplicationRuntimeException e) {
            e.logError();
        } catch (InvalidInputException e) {
            e.logError();
        }

        float totalPrice = (float) objectNode.get("totalPrice").asDouble();
        String quantities = objectNode.get("quantity").asText();
        String prodIds = objectNode.get("productIds").asText();
        String name = objectNode.get("name").asText();

        Order od = new Order(custId, totalPrice, quantities, prodIds);
        od.setOrderId(UUID.randomUUID());
        try {
            orderService.addOrder(od, custId, name, con);
            return "Order placed";
        } catch (ApplicationRuntimeException e) {
            e.logError();
        }

        return "Order not placedddd";
    }

    /**
     * This class is used when order is deleted from database
     */
    @DeleteMapping("/deleteOrder")
    public String deleteOrder(@Valid @RequestBody ObjectNode objectNode) {

        String name = objectNode.get("name").asText();
        try {
            orderService.deleteOrder(name, con);
            return "Order Deleted";
        } catch (ApplicationRuntimeException e) {
            e.logError();
        } catch (InvalidInputException e) {
            e.logError();
        }
        return "Order is not deleted";
    }

    /**
     * This class is used for showing order
     */
    @GetMapping("/displayOrder")
    public Vector<Vector> showOrder(@Valid @RequestBody ObjectNode objectNode) {

        String name = objectNode.get("name").asText();
        Vector<Vector>v = new Vector<>();
        try {
             v=orderService.showOrder(name, con);
            return v;
        } catch (ApplicationRuntimeException e) {
            e.logError();
        } catch (InvalidInputException e) {
            e.logError();
        }
     return v;
    }


}

