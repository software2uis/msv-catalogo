package com.equipo.catalogo.service.impl;

import com.equipo.catalogo.dto.CategoryDTO;
import com.equipo.catalogo.mapper.CategoryMapper;
import com.equipo.catalogo.repository.interfaces.ICategoryRepository;
import com.equipo.catalogo.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private ICategoryRepository iCategoryRepository;
    @Override
    public List<CategoryDTO> getAllCategories() {
        return CategoryMapper.INSTANCE.toCategoryDTOList(this.iCategoryRepository.findAll());
    }
    @Autowired
    public void setiCategoryRepository(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }
}
