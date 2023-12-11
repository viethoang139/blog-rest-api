package com.leviethoang.service;

import com.leviethoang.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    List<CommentDto> getAllCommentsByPostId(Long postId);

    CommentDto updateCommentById(CommentDto commentDto, Long postId, Long commentId);

    void deleteCommentById(Long postId, Long commentId);


}
