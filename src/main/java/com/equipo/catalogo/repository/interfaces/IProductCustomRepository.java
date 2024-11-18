package com.equipo.catalogo.repository.interfaces;

import com.equipo.catalogo.dto.ProductFilterDTO;
import com.equipo.catalogo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


public interface IProductCustomRepository {
    Page<Product> getFilteredProducts(ProductFilterDTO filter, Pageable pageable);
}
