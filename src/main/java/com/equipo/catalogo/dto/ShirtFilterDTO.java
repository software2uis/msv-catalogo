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
public class ShirtFilterDTO extends ProductFilterDTO{

    private List<String> color;

    private List<String> size;
}