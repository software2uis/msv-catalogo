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
@Document(collection = "categories")
public class Category {
    @Id
    private String id;

    private String name;

    private List<Specification> specifications;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Specification {
        private String name;
        private List<String> values;
    }
}
