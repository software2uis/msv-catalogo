package com.equipo.catalogo.repository.interfaces;

import com.equipo.catalogo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IProductRepository extends MongoRepository<Product,String> {

    List<Product> findByNameContainingIgnoreCase(String query);
}
