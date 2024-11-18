package com.equipo.catalogo.mapper;

import com.equipo.catalogo.dto.CategoryDTO;
import com.equipo.catalogo.model.Category;
import com.equipo.catalogo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toCategoryDTO(Category category);

    List<CategoryDTO> toCategoryDTOList(List<Category> categoryList);
}
