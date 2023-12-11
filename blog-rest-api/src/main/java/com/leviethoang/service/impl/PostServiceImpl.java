package com.leviethoang.service.impl;

import com.leviethoang.dto.PostDto;
import com.leviethoang.dto.ResponseApiDto;
import com.leviethoang.entity.Category;
import com.leviethoang.entity.Post;
import com.leviethoang.exception.ResourceNotFoundException;
import com.leviethoang.repository.CategoryRepository;
import com.leviethoang.repository.PostRepository;
import com.leviethoang.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category","ID",postDto.getCategoryId()));
        Post post = modelMapper.map(postDto,Post.class);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","ID",id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostsByCategoryId(Long id) {
        List<Post> posts = postRepository.findByCategoryId(id);
        return posts.stream().map(post -> modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseApiDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> page = postRepository.findAll(pageable);
        List<Post> posts = page.getContent();
        List<PostDto> content = posts.stream().map(post -> modelMapper.map(post,PostDto.class))
                .toList();
        ResponseApiDto responseApiDto = new ResponseApiDto();
        responseApiDto.setContent(content);
        responseApiDto.setPageNo(pageNo);
        responseApiDto.setPageSize(pageSize);
        responseApiDto.setTotalPage(page.getTotalPages());
        responseApiDto.setTotalElement(page.getTotalElements());
        responseApiDto.setLast(page.isLast());
        return responseApiDto;
    }
    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","ID",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatePost = postRepository.save(post);
        return modelMapper.map(updatePost,PostDto.class);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","ID",id));
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> search(String query) {
        List<Post> posts = postRepository.search(query);
        return posts.stream().map(post -> modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
    }
}
