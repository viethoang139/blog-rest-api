package com.leviethoang.service;

import com.leviethoang.dto.PostDto;
import com.leviethoang.dto.ResponseApiDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(Long id);

    List<PostDto> getAllPostsByCategoryId(Long id);

    ResponseApiDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto updatePostById(PostDto postDto, Long id);

    void deletePostById(Long id);

    List<PostDto> search(String query);


}
