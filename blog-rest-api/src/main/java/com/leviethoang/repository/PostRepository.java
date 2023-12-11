package com.leviethoang.repository;

import com.leviethoang.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByCategoryId(Long categoryId);

    @Query(value = "select p from Post p where "
    + "p.title like concat('%',:query,'%')"
    + "or p.description like concat('%',:query,'%')")
    List<Post> search(String query);

}
