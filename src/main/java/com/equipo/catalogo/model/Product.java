package com.equipo.catalogo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
@Setter
@Getter
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String catgory;

    private String name;

    private String description;

    private Double price;

    private String image;

    private Map<String,Object> specifications;



}
