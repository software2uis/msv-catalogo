package com.equipo.catalogo.controller;

import com.equipo.catalogo.dto.CategoryDTO;
import com.equipo.catalogo.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("public/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {
    private ICategoryService iCategoryService;
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return new ResponseEntity<>(this.iCategoryService.getAllCategories(), HttpStatus.OK);
    }
    @Autowired
    public void setiCategoryService(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }
}
