package com.leviethoang.controller;

import com.leviethoang.dto.CommentDto;
import com.leviethoang.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs for Comment resource"
)
public class CommentController {
    private CommentService commentService;

    @Operation(
            summary = "Create Comment Rest Api",
            description = "Create comment rest api is used to save comment into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CommentDto commentDto,
                                                    @PathVariable Long postId){
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Comment By Id Rest Api",
            description = "Get comment by id rest api is used to get single comment from particular post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId,
                                                     @PathVariable Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @Operation(
            summary = "Get All Comments By Post Id Rest Api",
            description = "Get all comment by post id rest api is used to get all comments from particular post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable Long postId){
        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }

    @Operation(
            summary = "Update Comment By Id Rest Api",
            description = "Update comment by id rest api is used to update particular comment from particular post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@RequestBody @Valid CommentDto commentDto,
                                                        @PathVariable Long postId,
                                                        @PathVariable Long commentId){
        return ResponseEntity.ok(commentService.updateCommentById(commentDto, postId, commentId));
    }

    @Operation(
            summary = "Delete Comment By Id Rest Api",
            description = "Delete comment by id rest api is used to delete particular comment from particular post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long postId,
                                                    @PathVariable Long commentId){
        commentService.deleteCommentById(postId, commentId);
        return ResponseEntity.ok("Delete comment successfully");
    }


}
