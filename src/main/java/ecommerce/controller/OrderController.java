package ecommerce.controller;

import ecommerce.cache.LruCacheService;
import ecommerce.dao.OrderDao;
import ecommerce.entity.Order;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.ExceptionResponseModel;
import ecommerce.model.OrderDisplayResponseModel;
import ecommerce.model.OrderIdResponseModel;
import ecommerce.model.OrderCreateRequestModel;
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

    @PostMapping("/createOrder") // will be use for adding products to cart
    public ResponseEntity placeOrder(@Valid @RequestBody OrderCreateRequestModel orderCreateRequestModel) {

        LruCacheService lruCacheService = new LruCacheService();
        String email = orderCreateRequestModel.getEmailId();
        UUID custId = null;
        try {
            custId = orderService.CheckEmailId(email, lruCacheService, con);

        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (custId != null) {
            float totalPrice = 0;
            String quantities = orderCreateRequestModel.getQuantity();
            String prodIds = orderCreateRequestModel.getProductIds();
            String name = orderCreateRequestModel.getOrderName();
            int i, j;
            Vector<Integer> v = new Vector<Integer>();

            for (i = 0; i < quantities.length(); i++) {
                String s = "";
                for (j = i; j < quantities.length() && quantities.charAt(j) != ','; j++) {
                    s = s + quantities.charAt(j);
                }
                i = j;
                int n = Integer.parseInt(s);
                v.add(n);

            }
            int k = 0;
            for (i = 0; i < prodIds.length(); i++) {
                String s = "";
                for (j = i; j < prodIds.length() && prodIds.charAt(j) != ','; j++) {
                    s = s + prodIds.charAt(j);
                }

                i = j;
                try {
                    orderService.checkUUID(s);
                    int qt = orderService.getQuantity(UUID.fromString(s), con);
                    orderService.checkQt(v.elementAt(k), qt);
                    totalPrice += orderService.getProductCost(UUID.fromString(s), con) * v.elementAt(k);
                } catch (InvalidInputException e) {
                    ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
                    return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

                } catch (ApplicationRuntimeException e) {
                    ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
                    return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
                }
                k++;
            }


            Order order = new Order(custId, totalPrice, quantities, prodIds);
            OrderIdResponseModel orderIdModel;
            try {
                orderService.addOrder(order, custId, name, con);
                orderIdModel = new OrderIdResponseModel(order.getOrderId());
            } catch (InvalidInputException e) {
                ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
                return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

            } catch (ApplicationRuntimeException e) {
                ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
                return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity(orderIdModel, HttpStatus.OK);
        }
        ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel("Cant placed order as user does not exist",400);
        return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

    }


    /**
     * This class is used when order is deleted from database
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity deleteOrder(@Valid @PathVariable String orderId) {
        try {
            orderService.showOrder(orderId, con);
            orderService.deleteOrder(orderId, con);
        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
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
    @GetMapping("/readOrder/{orderId}")
    public ResponseEntity showOrder(@Valid @PathVariable String orderId) {
        OrderDisplayResponseModel orderDisplayResponseModel;
        try {
            orderDisplayResponseModel = orderService.showOrder(orderId, con);

        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(orderDisplayResponseModel, HttpStatus.OK);

    }


}

