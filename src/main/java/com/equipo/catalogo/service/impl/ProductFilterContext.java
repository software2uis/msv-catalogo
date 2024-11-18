package com.equipo.catalogo.service.impl;

import com.equipo.catalogo.dto.ProductFilterDTO;
import com.equipo.catalogo.service.strategies.FilterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductFilterContext {
    private List<FilterStrategy> strategies;

    public Query buildQuery(ProductFilterDTO filter) {
        Query query = new Query();
        // Encuentra la estrategia correcta basada en la categoría
        strategies.forEach(s -> s.applyCriteria(query,filter));
        return query;
    }

    @Autowired
    public void setProductFilterContext(List<FilterStrategy> strategies) {
        this.strategies = strategies;
    }
}
