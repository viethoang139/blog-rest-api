package com.leviethoang.service.impl;

import com.leviethoang.dto.CommentDto;
import com.leviethoang.entity.Comment;
import com.leviethoang.entity.Post;
import com.leviethoang.exception.BlogApiException;
import com.leviethoang.exception.ResourceNotFoundException;
import com.leviethoang.repository.CommentRepository;
import com.leviethoang.repository.PostRepository;
import com.leviethoang.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","ID",postId));
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","ID",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","ID",commentId));
        if(!postId.equals(comment.getPost().getId())){
            throw new BlogApiException("Comment does not belong to post");
        }
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long postId) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","ID",postId));
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> modelMapper.map(comment,CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto updateCommentById(CommentDto commentDto, Long postId, Long commentId) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","ID",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","ID",commentId));
        if(!postId.equals(comment.getPost().getId())){
            throw new BlogApiException("Comment does not belong to post");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepository.save(comment);
        return modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","ID",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","ID",commentId));
        if(!postId.equals(comment.getPost().getId())){
            throw new BlogApiException("Comment does not belong to post");
        }
        commentRepository.deleteById(commentId);
    }
}
