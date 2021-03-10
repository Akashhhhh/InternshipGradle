package ecommerce.controller;

import ecommerce.entity.Product;
import ecommerce.exception.ApplicationRuntimeException;
import ecommerce.exception.InvalidInputException;
import ecommerce.model.ExceptionResponseModel;
import ecommerce.model.ProductDisplayResponseModel;
import ecommerce.model.ProductIdResponseModel;
import ecommerce.model.ProductCreateRequestModel;
import ecommerce.service.ProductService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Vector;

/**
 * This class is used to manage add , update and delete product operation.
 *
 * @author Akash Gupta
 */
@RequestMapping("/product")
@RestController
public class ProductController {

    ProductService productService = new ProductService();
    java.sql.Connection con = ecommerce.util.Connection.create();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PostMapping("/createProduct")
    public ResponseEntity add(@Valid @RequestBody ProductCreateRequestModel productCreateRequestModel) {
        ProductIdResponseModel productIdModel;
        Product product = new Product(productCreateRequestModel.getProdName(), productCreateRequestModel.getSellPrice(),
                productCreateRequestModel.getDescription(), productCreateRequestModel.getType(), productCreateRequestModel.getQuantity());
        try {
            productService.addNewProduct(product, con);
            productIdModel = new ProductIdResponseModel(product.getProdId());
        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(productIdModel, HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @PutMapping("/updateProduct/{prodId}/{qt}")
    public ResponseEntity update(@Valid @PathVariable String prodId, @PathVariable int qt) {
        try {
            productService.getProduct(prodId, con);
            productService.updateProduct(qt, prodId, con);

        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("Product updated", HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @DeleteMapping("/deleteProduct/{prodId}")
    public ResponseEntity delete(@Valid @PathVariable String prodId) {

        ProductCreateRequestModel product = null;

        try {
            productService.getProduct(prodId, con);
            productService.deleteProduct(prodId, con);

        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("Product deleted ", HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    @GetMapping("/readProduct/{prodId}")
    public ResponseEntity displayProduct(@Valid @PathVariable String prodId) {
        ProductDisplayResponseModel productDisplayResponseModel;

        try {
            productDisplayResponseModel =  productService.getProduct(prodId, con);

        } catch (InvalidInputException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErroCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.BAD_REQUEST);

        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(productDisplayResponseModel, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found in database!!!"),
            @ApiResponse(code = 500, message = "sql exception")})
    // method for displaying all product(giving error)
    @GetMapping("/readMenu")
    public ResponseEntity showMenu() {
        Vector<ProductDisplayResponseModel> v = new Vector<>();
        try {
            v = productService.getMenu(con);
        } catch (ApplicationRuntimeException e) {
            ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(e.getErrorDesc(),e.getErrorCode());
            return new ResponseEntity(exceptionResponseModel,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(v, HttpStatus.OK);

    }

}
