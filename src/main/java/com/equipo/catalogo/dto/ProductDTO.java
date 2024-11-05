package com.equipo.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String id;  // El ID puede ser opcional si lo generas en el backend

    private CategoryDTO category;

    private String name;

    private String description;

    private Double price;

    private List<ImageDTO> images;
    private List<SpecificationDTO> specifications;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryDTO {
        private String id;
        private String name;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ImageDTO {
        private String color;
        private String url;
        private boolean isMain;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecificationDTO {
        private String name;
        private List<String> values;
    }
}

