package com.equipo.catalogo.repository.interfaces;

import com.equipo.catalogo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IProductCustomRepository {
    Page<Product> productsCustomSearch(String query, Pageable pageable);
}
