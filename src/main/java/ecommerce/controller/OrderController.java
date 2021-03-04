package ecommerce.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import ecommerce.cache.LruCacheService;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity placeOrder(@Valid @RequestBody ObjectNode objectNode)  {


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
            return  new ResponseEntity("User updated to database", HttpStatus.OK);

        } catch (ApplicationRuntimeException e) {
            e.logError();
        }

        return  new ResponseEntity("User updated to database", HttpStatus.BAD_REQUEST);
    }

    /**
     * This class is used when order is deleted from database
     */
    @DeleteMapping("/deleteOrder")
    public ResponseEntity deleteOrder(@Valid @RequestBody ObjectNode objectNode) {

        String name = objectNode.get("name").asText();
        try {
            orderService.deleteOrder(name, con);
            return  new ResponseEntity("Deleted", HttpStatus.OK);
        } catch (ApplicationRuntimeException e) {
            e.logError();
        } catch (InvalidInputException e) {
            e.logError();
        }
        return  new ResponseEntity("Not deleted", HttpStatus.BAD_REQUEST);
    }

    /**
     * This class is used for showing order
     */
    @GetMapping("/displayOrder")
    public ResponseEntity showOrder(@Valid @RequestBody ObjectNode objectNode) {
        String name = objectNode.get("name").asText();
        Vector<Vector>v = new Vector<>();
        try {
             v=orderService.showOrder(name, con);
            return  new ResponseEntity("Order ", HttpStatus.OK);

        } catch (ApplicationRuntimeException e) {
            e.logError();
        } catch (InvalidInputException e) {
            e.logError();
        }
        return  new ResponseEntity("order deleted", HttpStatus.BAD_REQUEST);

    }


}

