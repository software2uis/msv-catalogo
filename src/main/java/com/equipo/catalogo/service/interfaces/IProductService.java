package com.equipo.catalogo.service.interfaces;

import com.equipo.catalogo.dto.ProductDTO;
import com.equipo.catalogo.dto.ProductFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IProductService {

    // Obtener todos los productos
    Page<ProductDTO> getProductsFiltered(ProductFilterDTO productFilterDTO, Pageable pageable);

    // Obtener un producto por ID
    Optional<ProductDTO> getProductById(String id);

    List<ProductDTO> getSuggestions(String query);

    ResponseEntity<?> getQuantityIfExist(String id);

    ResponseEntity<String> subtractQuantity(String id, Integer quantity);

    ResponseEntity<String> addQuantity(String id, Integer quantity);
}