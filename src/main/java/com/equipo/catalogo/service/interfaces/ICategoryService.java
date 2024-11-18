package com.equipo.catalogo.service.interfaces;

import com.equipo.catalogo.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    List<CategoryDTO> getAllCategories();
}
