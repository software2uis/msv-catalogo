package com.equipo.catalogo.repository.impl;

import com.equipo.catalogo.dto.ProductFilterDTO;
import com.equipo.catalogo.model.Product;
import com.equipo.catalogo.service.impl.ProductFilterContext;
import com.equipo.catalogo.repository.interfaces.IProductCustomRepository;
import com.equipo.catalogo.utils.IProductConstants;
import com.equipo.catalogo.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductCustomRepositoryImpl implements IProductCustomRepository {

    private ProductFilterContext productFilterContext;
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Product> getFilteredProducts(ProductFilterDTO filter, Pageable pageable) {

        pageable = (pageable == null) ? PageRequest.of(0, 10) : pageable;

        Query query = this.productFilterContext.buildQuery(filter);
        if(filter.getQuery() != null){
            String normalizedQuery = ProductUtils.removeAccents(filter.getQuery());
            Criteria nameCriteria = Criteria.where(IProductConstants.NAME)
                    .regex(".*" + normalizedQuery + ".*", "i");
            Criteria descriptionCriteria = Criteria.where(IProductConstants.DESCRIPTION)
                    .regex(".*" + normalizedQuery + ".*", "i");
            query.addCriteria(new Criteria().orOperator(nameCriteria, descriptionCriteria));
        }

        if (filter.getMinPrice() != null || filter.getMaxPrice() != null) {
            Criteria priceCriteria = Criteria.where(IProductConstants.PRICE);

            if (filter.getMinPrice() != null) {
                priceCriteria = priceCriteria.gte(filter.getMinPrice());
            }

            if (filter.getMaxPrice() != null) {
                priceCriteria = priceCriteria.lte(filter.getMaxPrice());
            }

            query.addCriteria(priceCriteria);
        }

        query.with(pageable);

        List<Product> products = mongoTemplate.find(query, Product.class);
        long total = mongoTemplate.count(query.skip(-1).limit(-1), Product.class);

        return new PageImpl<>(products, pageable, total);
    }

    @Autowired
    public void setFilterContext(ProductFilterContext productFilterContext){
        this.productFilterContext = productFilterContext;
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
}
