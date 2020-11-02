package cu.sacavix.springboot.rabbitmq.message.broker.product.web;

import cu.sacavix.springboot.rabbitmq.message.broker.product.entity.Product;
import cu.sacavix.springboot.rabbitmq.message.broker.product.repository.ProductRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ProductResource {

    @Autowired
    private ProductRepository productRepository ;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/product")
    @ApiOperation(value= "Create product", response = Product.class)
    public ResponseEntity<?> create(@RequestBody Product product) {
        return ResponseEntity.ok(productRepository.save(product)) ;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/product")
    @ApiOperation(value= "Get products", response = Product.class)
    public ResponseEntity<?> create() {
        return ResponseEntity.ok(productRepository.findAll()) ;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/product/{id}")
    @ApiOperation(value= "Get product", response = Product.class)
    public ResponseEntity<?> create(@PathVariable("id") String id) {
        Optional<Product> o = productRepository.findById(id) ;
        if(o.isPresent()) {
            return ResponseEntity.ok(o.get()) ;
        }
        return ResponseEntity.notFound().build() ;
    }


}
