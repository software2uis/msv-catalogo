package com.equipo.catalogo.repository.impl;

import com.equipo.catalogo.model.Product;
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

    private MongoTemplate mongoTemplate;
    @Override
    public Page<Product> productsCustomSearch(String query, Pageable pageable) {
        Query mongoQuery = new Query();

        if (pageable == null) {
            pageable = PageRequest.of(0, 10);  // Página 0, 10 elementos por página por defecto
        }

        // Si la query es nula o vacía, devolver todos los productos
        if (query == null || query.isEmpty()) {
            mongoQuery.with(pageable);
            long total = mongoTemplate.count(new Query(), Product.class);
            List<Product> products = mongoTemplate.find(mongoQuery, Product.class);
            return new PageImpl<>(products, pageable, total);
        }

        // Construir los criterios de búsqueda
        Criteria criteria = new Criteria();

        // Normalizar la query
        String normalizedQuery = ProductUtils.removeAccents(query);

        // Búsqueda en los campos del producto
        criteria.orOperator(
                Criteria.where(IProductConstants.NAME).regex(".*" + normalizedQuery + ".*", "i"),  // Búsqueda insensible a mayúsculas en nombre
                Criteria.where(IProductConstants.DESCRIPTION).regex(".*" + normalizedQuery + ".*", "i")           // Comparación exacta en el precio si es numérico
        );

        mongoQuery.addCriteria(criteria);

        mongoQuery.with(pageable);

        // Ejecutar la búsqueda
        List<Product> products = mongoTemplate.find(mongoQuery, Product.class);
        long total = mongoTemplate.count(mongoQuery, Product.class);

        // Crear y devolver el objeto Page
        return new PageImpl<>(products, pageable, total);
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
}
