package com.equipo.catalogo.service.impl;

import com.equipo.catalogo.dto.ProductDTO;
import com.equipo.catalogo.mapper.ProductMapper;
import com.equipo.catalogo.model.Product;
import com.equipo.catalogo.repository.impl.ProductCustomRepositoryImpl;
import com.equipo.catalogo.repository.interfaces.IProductCustomRepository;
import com.equipo.catalogo.repository.interfaces.IProductRepository;
import com.equipo.catalogo.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private IProductRepository iProductRepository;
    private IProductCustomRepository iProductCustomRepository;

    @Override
    public Page<ProductDTO> getAllProducts(String query, Pageable pageable) {
        Page<Product> ProductPage = this.iProductCustomRepository.productsCustomSearch(query, pageable);
        List<ProductDTO> listDTO = ProductPage.stream().map(
                ProductMapper.INSTANCE::toProductDTO
        ).toList();

        return new PageImpl<>(listDTO, pageable, ProductPage.getTotalElements());
    }

    @Override
    public Optional<ProductDTO> getProductById(String id) {
        return this.iProductRepository.findById(id).map(
                ProductMapper.INSTANCE::toProductDTO);
    }

    @Autowired
    public void setiProductRepository(IProductRepository iProductRepository){
        this.iProductRepository = iProductRepository;
    }

    @Autowired
    public void setiProductCustomRepository(IProductCustomRepository iProductCustomRepository) {
        this.iProductCustomRepository = iProductCustomRepository;
    }

    public List<String> getSuggestions(String query) {
        return this.iProductRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(Product::getName)
                .limit(5)
                .collect(Collectors.toList());
    }
}
