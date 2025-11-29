package com.application.services;


import com.application.entities.ProductEntity;
import com.application.mapper.ProductMapper;
import com.application.repositories.ProductRepository;
import com.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServices {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        ProductEntity productEntity = productRepository.save(productMapper.toEntity(product));
        Product product1 = productMapper.toDto(productEntity);
        return product1;
    }

    public  Product updateProduct(Integer id,Product product){
        ProductEntity pe = productRepository.findById(id).orElseThrow(() ->{return new RuntimeException("No Product with Id "+ id);});
        pe =productMapper.productUpdateById(product,pe);
        pe = productRepository.save(pe);
         Product product1 = productMapper.toDto(pe);
        return  product1;
    }

    public  String deleteProduct(Integer id){
        if(!productRepository.existsById(id)){
            return  "No product availale with Id "+id;
        }
        productRepository.deleteById(id);
        return "Product with Id "+id+" Succesfully Deleted ";
    }

    public List<Product> getAllProducts(){
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<Product> products = productMapper.toDtoList(productEntityList);
        return products;
    }

    public  Product getProductById(Integer id){
       ProductEntity  productEntity = productRepository.findById(id).orElseThrow(()->
       {return new RuntimeException("No Product Exits With Id "+ id);});
       return productMapper.toDto(productEntity);
    }
}
