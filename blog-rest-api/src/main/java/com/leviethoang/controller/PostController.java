package com.leviethoang.controller;

import com.leviethoang.dto.PostDto;
import com.leviethoang.dto.ResponseApiDto;
import com.leviethoang.service.PostService;
import com.leviethoang.utils.Constants;
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
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs for Post resource"
)
public class PostController {
    private PostService postService;

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @Operation(
            summary = "Create Post Rest Api",
            description = "Create post rest api is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Post By Id Rest Api",
            description = "Get post by id rest api is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @Operation(
            summary = "Search Posts Rest Api",
            description = "Search posts rest api is used to search post by title or description"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> search(@RequestParam String query){
        return ResponseEntity.ok(postService.search(query));
    }

    @Operation(
            summary = "Get All Posts Rest Api",
            description = "Get all posts rest api is used to get all posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<ResponseApiDto> getAllPosts(@RequestParam(value = "pageNo",defaultValue = Constants.DEFAULT_PAGE_NO,required = false) int pageNo,
                                                            @RequestParam(value = "pageSize",defaultValue = Constants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                                            @RequestParam(value = "sortBy",defaultValue = Constants.DEFAULT_SORT_BY,required = false) String sortBy,
                                                            @RequestParam(value = "sortDir",defaultValue = Constants.DEFAULT_SORT_DIR,required = false)String sortDir){
        return ResponseEntity.ok(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir));
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @Operation(
            summary = "Update Post By Id Rest Api",
            description = "Update post by id rest api is used to update particular post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody @Valid PostDto postDto,
                                                  @PathVariable("id") Long postId){
        return ResponseEntity.ok(postService.updatePostById(postDto, postId));
    }

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @Operation(
            summary = "Delete Post By Id Rest Api",
            description = "Delete post by id rest api is used to delete particular post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long postId){
        postService.deletePostById(postId);
        return ResponseEntity.ok("Delete post successfully");
    }



}
