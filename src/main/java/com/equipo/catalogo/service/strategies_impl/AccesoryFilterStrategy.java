package com.equipo.catalogo.service.strategies_impl;

import com.equipo.catalogo.dto.AccesoryFilterDTO;
import com.equipo.catalogo.dto.ProductFilterDTO;
import com.equipo.catalogo.service.strategies.FilterStrategy;
import com.equipo.catalogo.utils.ICategoryContants;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class AccesoryFilterStrategy implements FilterStrategy {
    @Override
    public void applyCriteria(Query query, ProductFilterDTO filter) {
        if (filter instanceof AccesoryFilterDTO accesoryFilter) {
            query.addCriteria(Criteria.where("category.name").is(getCategory()));
            if (accesoryFilter.getColor() != null) {
                query.addCriteria(Criteria.where("specifications")
                        .elemMatch(Criteria.where("name").is("Color")
                                .and("values").in(accesoryFilter.getColor())));
            }

            if (accesoryFilter.getSize() != null) {
                query.addCriteria(Criteria.where("specifications")
                        .elemMatch(Criteria.where("name").is("Size")
                                .and("values").in(accesoryFilter.getSize())));
            }
        }
    }

    @Override
    public String getCategory() {
        return ICategoryContants.ACCESORY;
    }
}
