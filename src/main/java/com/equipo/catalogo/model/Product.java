package com.equipo.catalogo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Document(collection = "products_v2")
public class Product {
    @Id
    private String id;

    private Category category;

    private String name;

    private String description;

    private Double price;

    private List<Image> images;

    private List<Specification> specifications;

    private Integer quantity;


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category {
        private String id;
        private String name;
        private Integer quantity;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Image {
        private String color;
        private String url;
        private Boolean isMain;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Specification {
        private String name;
        private List<String> values;
    }
}
