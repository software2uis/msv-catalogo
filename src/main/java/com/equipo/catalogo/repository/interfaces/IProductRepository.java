package com.equipo.catalogo.repository.interfaces;

import com.equipo.catalogo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductRepository extends MongoRepository<Product,String> {
}
