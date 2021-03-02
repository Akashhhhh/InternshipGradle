package ecommerce.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class is used to manage add , update and delete product operation.
 *
 * @author Akash Gupta
 */
@RequestMapping("/user")
@RestController
public class ProductController {

    ProductService productService = new ProductService();
    java.sql.Connection con = ecommerce.util.Connection.create();

    @PostMapping("/addProduct")
    public String add(@Valid @RequestBody Product product)  {
        try {
            productService.addNewProduct(product, con);
            return "Product Added";
        } catch (InvalidInputException e) {
           e.logError();
        } catch (ApplicationRuntimeException e) {
            e.logError();
        }
      return "Product not added";
    }
    @PutMapping("/updateProduct")
    public String update(@RequestBody ObjectNode objectnode)  {

        int qt = objectnode.get("quantity").asInt();
        String prodName = objectnode.get("prodName").asText();
        try {
            productService.updateProduct(qt, prodName, con);
            return "Product Updated";
        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
           e.logError();
        }

      return "Product Not updated";
    }

    @DeleteMapping("/deleteProduct")
    public String delete(@RequestBody ObjectNode objectnode)  {


        String prodName = objectnode.get("prodName").asText();
        try {
            productService.deleteProduct(prodName, con);
            return "Product Deleted";
        } catch (InvalidInputException e) {
            e.logError();
        } catch (ApplicationRuntimeException e) {
            e.logError();
        }

       return "Product Not deleted";
    }




}
