package com.masai.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.PostException;
import com.masai.Exception.UserException;
import com.masai.module.Post;
import com.masai.service.PostService;

@CrossOrigin(origins = "*")
@RestController("/api")
public class PostControllers {

	@Autowired
	private PostService pService;
	
	@PostMapping("/posts/{id}")
	public ResponseEntity<Post> createNewPost(@RequestBody Post post,@PathVariable("id") Integer userId) throws UserException {
		Post po =  pService.createNewPost(post, userId);
		return new ResponseEntity<Post>(po,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPost(Integer userId) throws UserException {
		List<Post> po =  pService.getAllPost(userId);
		return new ResponseEntity<List<Post>>(po,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<Post>getPostByID(@PathVariable("id") Integer postID) throws UserException, PostException {
		Post po =  pService.getPostById(postID);
		return new ResponseEntity<Post>(po,HttpStatus.OK);
	}
	
	@GetMapping("/posts/")
	public ResponseEntity<List<Post>> getAllPostByPage(Integer userId,@RequestParam Integer PageNo, @RequestParam Integer pageSize,@RequestParam String filer) throws UserException {
		List<Post> po =  pService.getAllPost(userId);
		List<Post> list = new ArrayList<>();
		for(int i = (PageNo-1)*pageSize ; i<PageNo*pageSize ; i++) {
			list.add(po.get(i-1));
		}
		
		if(filer.equals("title")) {
			list.sort((a,b)-> a.getTitle().compareTo(b.getTitle())>0?1:-1);
		}
		
		return new ResponseEntity<List<Post>>(list,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<String> deletePost(@PathVariable("id") Integer postId) throws UserException, PostException {
		String message =  pService.deletePost(postId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<Post> updatePost(@RequestBody Post post,@PathVariable("id") Integer postId) throws UserException, PostException {
		Post po =  pService.updatePost(postId, post);
		return new ResponseEntity<Post>(po,HttpStatus.OK);
	}
	
}
