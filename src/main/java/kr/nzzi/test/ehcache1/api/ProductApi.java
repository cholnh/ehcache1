package kr.nzzi.test.ehcache1.api;

import kr.nzzi.test.ehcache1.model.Product;
import kr.nzzi.test.ehcache1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductApi {

    private ProductService productService;

    @Autowired
    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> find(@RequestParam(value = "ids") Set<Long> ids) {

        ResponseEntity responseEntity;

        if (ids.size() == 1) {
            Product result = productService.findOne(ids.iterator().next());
            responseEntity = ResponseEntity.ok(result);
        }
        else {
            Set<Product> resultSet = productService.findMulti(ids);
            responseEntity = ResponseEntity.ok(resultSet);
        }
        return responseEntity;
    }
}
