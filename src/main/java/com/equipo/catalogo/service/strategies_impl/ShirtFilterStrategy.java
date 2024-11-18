package com.equipo.catalogo.service.strategies_impl;

import com.equipo.catalogo.dto.ProductFilterDTO;
import com.equipo.catalogo.dto.ShirtFilterDTO;
import com.equipo.catalogo.service.strategies.FilterStrategy;
import com.equipo.catalogo.utils.ICategoryContants;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ShirtFilterStrategy implements FilterStrategy {
    @Override
    public void applyCriteria(Query query, ProductFilterDTO filter) {
        if (filter instanceof ShirtFilterDTO shirtFilter) {
            query.addCriteria(Criteria.where("category.name").is(getCategory()));
            if (shirtFilter.getColor() != null) {
                query.addCriteria(Criteria.where("specifications")
                        .elemMatch(Criteria.where("name").is("Color")
                                .and("values").in(shirtFilter.getColor())));
            }

            if (shirtFilter.getSize() != null) {
                query.addCriteria(Criteria.where("specifications")
                        .elemMatch(Criteria.where("name").is("Size")
                                .and("values").in(shirtFilter.getSize())));
            }
        }
    }

    @Override
    public String getCategory() {
        return ICategoryContants.SHIRT;
    }
}
