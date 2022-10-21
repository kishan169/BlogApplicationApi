package com.masai.service;

import java.util.List;

import com.masai.Exception.PostException;
import com.masai.Exception.UserException;
import com.masai.module.Post;

public interface PostService {
	
	public Post createNewPost(Post post,Integer userID) throws UserException;
	
	public Post getPostById(Integer postId) throws PostException;
	
	public String deletePost(Integer postId) throws PostException;
	
	public Post updatePost(Integer postID, Post post) throws PostException;
	
	public List<Post> getAllPost(Integer userId) throws UserException;
	
}
