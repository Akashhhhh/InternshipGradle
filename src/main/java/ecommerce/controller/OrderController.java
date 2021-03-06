package ecommerce.controller;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.OrderModel;
import ecommerce.model.ProductModel;
import ecommerce.service.OrderService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/order")
@RestController
public class OrderController {

    OrderDao orderDao = new OrderDao();
    OrderService orderService = new OrderService();
    java.sql.Connection con = ecommerce.util.Connection.create();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PostMapping("/placeOrder") // will be use for adding products to cart
    public ResponseEntity placeOrder(@Valid @RequestBody OrderModel orderModel) {

        LruCacheService lruCacheService = new LruCacheService();
        String email = orderModel.getEmailId();
        UUID custId = null;
        try {
            custId = orderService.CheckEmailId(email, lruCacheService, con);

        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);
        }

        if (custId != null) {
            float totalPrice = 0;
            String quantities = orderModel.getQuantity();
            String prodIds = orderModel.getProductIds();
            String name = orderModel.getOrderName();
            int i, j;
            for (i = 0; i < prodIds.length(); i++) {
                String s = "";
                for (j = i; j < prodIds.length() && prodIds.charAt(j) != ','; j++) {
                    s = s + prodIds.charAt(j);
                }

                i = j;
                try {
                    totalPrice += orderService.getProductCost(UUID.fromString(s), con);
                } catch (ApplicationRuntimeException e) {
                    return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

                }
            }

            Order od = new Order(custId, totalPrice, quantities, prodIds);
            try {
                orderService.addOrder(od, custId, name, con);

            } catch (ApplicationRuntimeException e) {
                return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

            } catch (InvalidInputException e) {
                return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

            }
            return new ResponseEntity("Order Placed", HttpStatus.OK);
        }
        return new ResponseEntity("Cant placed order as user does not exist", HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    // method for displaying all product(giving error)
    @PostMapping("/showMenu")
    public ResponseEntity showMenu() {
        Vector<ProductModel> v = new Vector<>();
        try {
            v = orderService.getMenu(con);
        } catch (ApplicationRuntimeException e) {

            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity(v, HttpStatus.OK);

    }

    /**
     * This class is used when order is deleted from database
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @DeleteMapping("/deleteOrder")
    public ResponseEntity deleteOrder(@Valid @RequestBody OrderModel orderModel) {

        String name = orderModel.getOrderName();
        Order order = null;
        try {
            order = orderService.showOrder(name, con);
            if (order != null) {
                orderService.deleteOrder(name, con);
            } else {
                return new ResponseEntity("Order does not exist", HttpStatus.OK);

            }

        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        } catch (InvalidInputException e) {

            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity("Order Deleted", HttpStatus.OK);

    }

    /**
     * This class is used for showing order
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PostMapping("/displayCart")
    public ResponseEntity showOrder(@Valid @RequestBody OrderModel orderModel) {
        String name = orderModel.getOrderName();
        Order order = null;
        try {
            order = orderService.showOrder(name, con);

        } catch (ApplicationRuntimeException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);
        } catch (InvalidInputException e) {
            return new ResponseEntity(e.getErrorDesc(), HttpStatus.BAD_REQUEST);

        }
        if (order != null) {
            return new ResponseEntity(order, HttpStatus.OK);

        }
        return new ResponseEntity("Order does not exist", HttpStatus.OK);

    }


}

