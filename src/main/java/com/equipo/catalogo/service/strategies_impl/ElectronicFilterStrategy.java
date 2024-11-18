package com.equipo.catalogo.service.strategies_impl;

import com.equipo.catalogo.dto.ElectronicFilterDTO;
import com.equipo.catalogo.dto.ProductFilterDTO;
import com.equipo.catalogo.service.strategies.FilterStrategy;
import com.equipo.catalogo.utils.ICategoryContants;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ElectronicFilterStrategy implements FilterStrategy {
    @Override
    public void applyCriteria(Query query, ProductFilterDTO filter) {
        if (filter instanceof ElectronicFilterDTO electronicFilter) {
            query.addCriteria(Criteria.where("category.name").is(getCategory()));
            if (electronicFilter.getColor() != null) {
                query.addCriteria(Criteria.where("specifications")
                        .elemMatch(Criteria.where("name").is("Color")
                                .and("values").in(electronicFilter.getColor())));
            }

            if (electronicFilter.getStorage() != null) {
                query.addCriteria(Criteria.where("specifications")
                        .elemMatch(Criteria.where("name").is("Storage")
                                .and("values").in(electronicFilter.getStorage())));
            }
        }
    }

    @Override
    public String getCategory() {
        return ICategoryContants.ELECTRONIC;
    }
}
