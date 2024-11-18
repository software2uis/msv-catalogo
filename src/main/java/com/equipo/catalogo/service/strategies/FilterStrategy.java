package com.equipo.catalogo.service.strategies;

import com.equipo.catalogo.dto.ProductFilterDTO;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public interface FilterStrategy {
    void applyCriteria(Query query, ProductFilterDTO filter);
    String getCategory();
}
