package com.equipo.catalogo.service.strategies_impl;

import com.equipo.catalogo.dto.BeverageFilterDTO;
import com.equipo.catalogo.dto.ProductFilterDTO;
import com.equipo.catalogo.dto.ShirtFilterDTO;
import com.equipo.catalogo.service.strategies.FilterStrategy;
import com.equipo.catalogo.utils.ICategoryContants;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class BeverageFilterStrategy implements FilterStrategy {
    @Override
    public void applyCriteria(Query query, ProductFilterDTO filter) {
        if (filter instanceof BeverageFilterDTO beverageFilter) {
            query.addCriteria(Criteria.where("category.name").is(getCategory()));
            if (beverageFilter.getVolume() != null) {
                query.addCriteria(Criteria.where("specifications")
                        .elemMatch(Criteria.where("name").is("Volume")
                                .and("values").in(beverageFilter.getVolume())));
            }

            if (beverageFilter.getAlcoholContent() != null) {
                query.addCriteria(Criteria.where("specifications")
                        .elemMatch(Criteria.where("name").is("Alcohol Content")
                                .and("values").in(beverageFilter.getAlcoholContent())));
            }
        }
    }

    @Override
    public String getCategory() {
        return ICategoryContants.BEVERAGE;
    }
}
