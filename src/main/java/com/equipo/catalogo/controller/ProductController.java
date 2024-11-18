package com.equipo.catalogo.controller;

import com.equipo.catalogo.dto.ProductDTO;
import com.equipo.catalogo.dto.ProductFilterDTO;
import com.equipo.catalogo.service.interfaces.IProductService;
import com.equipo.catalogo.dto.UpdateQuantityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("public/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private IProductService iProductService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(@RequestBody(required = false) ProductFilterDTO productFilterDTO,
                                           Pageable pageable) {
        return new ResponseEntity<>(this.iProductService.getProductsFiltered(productFilterDTO, pageable), HttpStatus.OK);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductDTO>> getProductById(@PathVariable String id) {
        return new ResponseEntity<>(this.iProductService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/suggestions")
    public List<String> getSuggestions(@RequestParam String query) {
        return this.iProductService.getSuggestions(query);
    }

    @GetMapping("/quantity/{id}")
    public ResponseEntity<?> getQuantity(@PathVariable String id) {
        return this.iProductService.getQuantityIfExist(id);
    }

    @PutMapping("/subtractQuantity")
    public ResponseEntity<String> subtractQuantity(@RequestBody UpdateQuantityRequest updateRequest) {
        return this.iProductService.subtractQuantity(updateRequest.getId(), updateRequest.getQuantity());
    }

    @PutMapping("/addQuantity")
    public ResponseEntity<String> addQuantity(@RequestBody UpdateQuantityRequest updateRequest) {
        return this.iProductService.addQuantity(updateRequest.getId(), updateRequest.getQuantity());
    }

    @Autowired
    public void setiProductService(@Qualifier("productServiceImpl") IProductService iProductService) {
        this.iProductService = iProductService;
    }
}
