package com.application.mapper;

import com.application.entities.ProductEntity;
import com.example.model.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;


@Mapper(componentModel = "spring")
public interface ProductMapper {

        @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
        @Mapping(source = "id",target = "id")
        @Mapping(source = "name",target = "name")
        @Mapping(source = "description",target = "description")
        @Mapping(source = "price",target = "price")
        @Mapping(target = "createdAt",expression = "java(java.time.LocalDateTime.now())")
        @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
        @Mapping(source = "stockQuantity",target = "stockQuantity")
        ProductEntity toEntity(Product product);


        @Mapping(source = "id",target = "id")
        @Mapping(source = "name",target = "name")
        @Mapping(source = "description",target = "description")
        @Mapping(source = "price",target = "price")
        @Mapping(source = "stockQuantity",target = "stockQuantity")
        @Mapping(source = "createdAt" ,target = "createdAt")
        @Mapping(source = "updatedAt",target = "updatedAt")
        Product toDto (ProductEntity productEntity);

        @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
        ProductEntity productUpdateById(Product product, @MappingTarget ProductEntity productEntity);

        List<Product> toDtoList (List<ProductEntity> productEntityList);

        List<ProductEntity> toEntityList(List<Product> productList);

}
