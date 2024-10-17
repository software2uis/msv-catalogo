package com.equipo.catalogo.controller;

import com.equipo.catalogo.dto.ProductDTO;
import com.equipo.catalogo.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("public/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private IProductService iProductService;

    // Obtener todos los productos
    @GetMapping
    public Page<ProductDTO> getAllProducts(@RequestParam(value = "query", required = false) String query,
                                           Pageable pageable) {
        return this.iProductService.getAllProducts(query, pageable);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public Optional<ProductDTO> getProductById(@PathVariable String id) {
        return this.iProductService.getProductById(id);
    }
    @Autowired
    public void setiProductService(@Qualifier("productServiceImpl") IProductService iProductService) {
        this.iProductService = iProductService;
    }
}
