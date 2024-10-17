package com.equipo.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String id;  // El ID puede ser opcional si lo generas en el backend

    private String catgory;

    private String name;

    private String description;

    private Double price;

    private String image;

    private Map<String, Object> specifications;  // Mantiene el mismo tipo para especificaciones
}
