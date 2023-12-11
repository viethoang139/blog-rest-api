package com.leviethoang.controller;

import com.leviethoang.dto.CategoryDto;
import com.leviethoang.dto.PostDto;
import com.leviethoang.service.CategoryService;
import com.leviethoang.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs for Category resource"
)
public class CategoryController {
    private CategoryService categoryService;
    private PostService postService;

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @Operation(
            summary = "Create Category Rest Api",
            description = "Create category rest api is used to save category into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Category By Id Rest Api",
            description = "Get category by id rest api is used to get single category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @Operation(
            summary = "Get All Categories Rest Api",
            description = "Get all categories rest api is used to get all categories from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(
            summary = "Get All Categories Rest Api",
            description = "Get all categories rest api is used to get all categories from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/posts/{id}")
    public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(postService.getAllPostsByCategoryId(categoryId));
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @Operation(
            summary = "Update Category By Id Rest Api",
            description = "Update category by id rest api is used to update particular category in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@RequestBody @Valid CategoryDto categoryDto,
                                                          @PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategoryById(categoryDto, categoryId));
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @Operation(
            summary = "Delete Category By Id Rest Api",
            description = "Delete category by id rest api is used to delete particular category in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long categoryId){
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok("Delete category successfully");
    }



}
