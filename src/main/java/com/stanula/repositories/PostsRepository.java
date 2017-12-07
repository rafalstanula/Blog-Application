package com.stanula.repositories;

import com.stanula.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAuthor(String autor);
}
