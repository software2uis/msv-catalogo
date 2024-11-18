package com.equipo.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private String id;

    private String name;

    private List<SpecificationDTO> specifications;


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecificationDTO {
        private String name;
        private List<String> values;
    }
}
