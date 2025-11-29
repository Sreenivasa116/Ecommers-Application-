package com.application.controllers;

import com.application.services.ProductsServices;
import com.example.api.ProductsApi;
import com.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductsApi {

    @Autowired
    private ProductsServices productsServices;

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        Product product1 = productsServices.createProduct(product);
        return ResponseEntity.status(200).body(product1);
    }

    @Override
    public ResponseEntity<Product> updateProductById(Integer id, Product product) {
        product = productsServices.updateProduct(id,product);
        return ResponseEntity.status(201).body(product);
    }

    @Override
    public ResponseEntity<String> deleteProductById(Integer id) {
        String result = productsServices.deleteProduct(id);
        return ResponseEntity.status(200).body(result);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productsServices.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @Override
    public ResponseEntity<Product> getProductById(Integer id) {
        Product product = productsServices.getProductById(id);
        return ResponseEntity.status(200).body(product);
    }

}
