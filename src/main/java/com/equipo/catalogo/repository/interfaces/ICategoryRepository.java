package com.equipo.catalogo.repository.interfaces;

import com.equipo.catalogo.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICategoryRepository extends MongoRepository<Category, String> {
}
