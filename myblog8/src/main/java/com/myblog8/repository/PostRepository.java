package com.myblog8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.myblog8.entity.Post;


public interface PostRepository extends JpaRepository<Post ,Long> {

}
