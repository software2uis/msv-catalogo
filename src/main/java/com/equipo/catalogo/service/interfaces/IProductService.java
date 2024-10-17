package com.equipo.catalogo.service.interfaces;

import com.equipo.catalogo.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IProductService {

    // Obtener todos los productos
    Page<ProductDTO> getAllProducts(String query, Pageable pageable);

    // Obtener un producto por ID
    Optional<ProductDTO> getProductById(String id);
}