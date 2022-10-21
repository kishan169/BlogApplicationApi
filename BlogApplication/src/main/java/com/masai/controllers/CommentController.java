package com.masai.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.PostException;
import com.masai.module.Comment;
import com.masai.service.CommentService;

@CrossOrigin(origins = "*")
@RestController("/api/posts")
public class CommentController {
	
	@Autowired
	private CommentService cService;
	
	
	@PostMapping("/{id}/comments")
	public ResponseEntity<Comment> createNewComment(@RequestBody Comment comment,@PathVariable("id") Integer postId) throws PostException {
		Comment comm =  cService.createNewComment(comment, postId);
		return new ResponseEntity<Comment>(comm,HttpStatus.OK);
	}
	
	@GetMapping("/{id}/comments")
	public ResponseEntity<List<Comment>> getAllComments(@PathVariable("id") Integer postId) throws PostException {
		List<Comment> comment =  cService.getAllComments(postId);
		return new ResponseEntity<List<Comment>>(comment,HttpStatus.OK);
	}
	
	@GetMapping("/{id}/comments/{tid}")
	public ResponseEntity<Comment> getCommentByID(@PathVariable("id") Integer postId,@PathVariable("tid") Integer commentId) throws PostException {
		Comment comment =  cService.getCommentById(commentId, postId);
		return new ResponseEntity<Comment>(comment,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/comments/{tid}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable("id") Integer postId,@PathVariable("tid") Integer commentId) throws PostException {
		Comment comm =  cService.updateComment(comment, commentId, postId);
		return new ResponseEntity<Comment>(comm,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/comments/{tid}")
	public ResponseEntity<String> deleteComments(@PathVariable("id") Integer postId ,@PathVariable("tid") Integer commentId) throws PostException {
		String comment =  cService.deleteComment(commentId, postId);
		return new ResponseEntity<String>(comment,HttpStatus.OK);
	}
}
