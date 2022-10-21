package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.module.Comment;

@Repository
public interface TaskDao extends JpaRepository<Comment, Integer>{

}
