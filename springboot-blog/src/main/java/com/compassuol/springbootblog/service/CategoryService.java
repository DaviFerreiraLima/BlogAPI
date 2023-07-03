package com.compassuol.springbootblog.service;

import com.compassuol.springbootblog.entity.Category;
import com.compassuol.springbootblog.exception.ResourceNotFoundException;
import com.compassuol.springbootblog.payload.CategoryDto;
import com.compassuol.springbootblog.repository.CategoryRepository;
import com.compassuol.springbootblog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public CategoryDto addCategory(CategoryDto categoryDto){
        var category = mapper.map(categoryDto, Category.class);
        var newCategory = categoryRepository.save(category);
        return  mapper.map(newCategory,CategoryDto.class);
    }

    public CategoryDto findCategoryById(long categoryId){
        var category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));
        return mapper.map(category,CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(category -> mapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());
    }

    public CategoryDto  updateCategory(long categoryId,CategoryDto categoryDto) {
        var category = categoryRepository.findById(categoryId)
                .orElseThrow( ()-> new ResourceNotFoundException("category","id",categoryId));
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
        category = categoryRepository.save(category);
        return mapper.map(category,CategoryDto.class);
    }

    public void deleteCategory(long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
        categoryRepository.deleteById(categoryId);
    }
}
