package org.kelvin.testing.orders.testingorders.controllers;

import org.kelvin.testing.orders.testingorders.models.entities.Product;
import org.kelvin.testing.orders.testingorders.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public List<Product> getAll(){
        return  productService.getAll();
    }

    //why generic? because the content could be a Product if exists, in the other case null if doesn't
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<Product> optionalProduct= productService.getById(id);
        if(optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Product product){
        return  ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id){

        Optional<Product> optionalProduct = productService.getById(id);
        if(optionalProduct.isPresent()){
            Product productUpdate = optionalProduct.get();
            productUpdate.setName(product.getName());
            productUpdate.setPrice(product.getPrice());
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productUpdate));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Product> optionalProduct = productService.getById(id);
        if(optionalProduct.isPresent()){
            productService.delete(id);
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
