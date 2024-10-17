package com.equipo.catalogo.mapper;

import com.equipo.catalogo.dto.ProductDTO;
import com.equipo.catalogo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Mapea de Product a ProductDTO
    ProductDTO toProductDTO(Product product);

    // Mapea de ProductDTO a Product
    Product toProduct(ProductDTO productDTO);
}