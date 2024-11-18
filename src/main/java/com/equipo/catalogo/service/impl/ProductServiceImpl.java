package com.equipo.catalogo.service.impl;

import com.equipo.catalogo.dto.ProductDTO;
import com.equipo.catalogo.dto.ProductFilterDTO;
import com.equipo.catalogo.mapper.ProductMapper;
import com.equipo.catalogo.model.Product;
import com.equipo.catalogo.repository.interfaces.IProductCustomRepository;
import com.equipo.catalogo.repository.interfaces.IProductRepository;
import com.equipo.catalogo.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private IProductRepository iProductRepository;
    private IProductCustomRepository iProductCustomRepository;

    @Override
    public Page<ProductDTO> getProductsFiltered(ProductFilterDTO productFilterDTO, Pageable pageable) {

        Page<Product> productPage;

        if (productFilterDTO == null) {
            productPage = this.iProductRepository.findAll(pageable);
        }else{
            productPage = this.iProductCustomRepository.getFilteredProducts(productFilterDTO, pageable);
        }

        List<ProductDTO> listDTO = productPage.stream().map(
                ProductMapper.INSTANCE::toProductDTO
        ).toList();

        return new PageImpl<>(listDTO, pageable, productPage.getTotalElements());
    }

    @Override
    public Optional<ProductDTO> getProductById(String id) {
        return this.iProductRepository.findById(id).map(
                ProductMapper.INSTANCE::toProductDTO);
    }

    @Override
    public List<ProductDTO> getSuggestions(String query) {
        if (query == null || query.isEmpty()) {
            return null;
        }else {
            return this.iProductRepository.findByNameContainingIgnoreCase(query)
                    .stream()
                    .limit(5)
                    .map(ProductMapper.INSTANCE::toProductDTO).toList();
        }
    }

    @Override
    public ResponseEntity<?> getQuantityIfExist(String id) {
        Optional<ProductDTO> product = getProductById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get().getQuantity());
        }else {
            return ResponseEntity.status(404).body("El producto no existe");
        }
    }

    @Override
    public ResponseEntity<String> subtractQuantity(String id, Integer quantity) {
        Optional<Product> product = this.iProductRepository.findById(id);

        if (product.isPresent() && quantity>=1 && product.get().getQuantity()>=quantity) {
            product.get().setQuantity((product.get().getQuantity()-quantity));
            this.iProductRepository.save(product.get());
            return ResponseEntity.status(200).body("Cantidad restada correctamente, cantidad restante: "+ product.get().getQuantity());
        }else {
            return ResponseEntity.status(404).body("El producto no existe o la cantidad es mayor al stock");
        }
    }

    @Override
    public ResponseEntity<String> addQuantity(String id, Integer quantity) {
        Optional<Product> product = this.iProductRepository.findById(id);

        if (product.isPresent() && quantity>=1) {
            product.get().setQuantity((product.get().getQuantity()+quantity));
            this.iProductRepository.save(product.get());
            return ResponseEntity.status(200).body("Cantidad sumada correctamente, cantidad restante: "+ product.get().getQuantity());
        }else {
            return ResponseEntity.status(404).body("El producto no existe o la cantidad no es valida");
        }
    }

    @Autowired
    public void setiProductRepository(IProductRepository iProductRepository){
        this.iProductRepository = iProductRepository;
    }

    @Autowired
    public void setiProductCustomRepository(IProductCustomRepository iProductCustomRepository) {
        this.iProductCustomRepository = iProductCustomRepository;
    }
}
