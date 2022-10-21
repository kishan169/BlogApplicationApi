package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.module.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Integer>{

}
