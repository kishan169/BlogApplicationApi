package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.PostException;
import com.masai.module.Post;
import com.masai.module.Comment;
import com.masai.repository.PostDao;
import com.masai.repository.TaskDao;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private TaskDao tDao;
	
	@Autowired
	private PostDao pDao;

	@Override
	public Comment getCommentById(Integer commentId, Integer postId) throws PostException {
		Optional<Post> post=  pDao.findById(postId);
		
		if(!post.isPresent()) {
			throw new PostException("Not found any Post");
		}
		
		Optional<Comment> comment = tDao.findById(commentId);
		return comment.get();
	}



	@Override
	public Comment createNewComment(Comment comment, Integer postId) throws PostException {
		Optional<Post> post=  pDao.findById(postId);
		
		if(!post.isPresent()) {
			throw new PostException("Not found any Post");
		}
		
		comment.setPost(post.get());
		Comment task1 = tDao.save(comment);
		
		post.get().getTasks().add(task1);
		pDao.save(post.get());
		
		
		return task1;
	}

	@Override
	public List<Comment> getAllComments(Integer postId) throws PostException {
		Optional<Post> post=  pDao.findById(postId);
		
		if(!post.isPresent()) {
			throw new PostException("Not found any Post");
		}
		return post.get().getTasks();
	}

	@Override
	public String deleteComment(Integer taskId, Integer postId) throws PostException {
		Optional<Comment> comment=  tDao.findById(taskId);
		
		if(!comment.isPresent()) {
			throw new PostException("Not found any Comments");
		}
		
		Post p = comment.get().getPost();
		p.getTasks().remove(comment.get());
		
		pDao.save(p);
		tDao.delete(comment.get());
		return "deleted";
	}

	@Override
	public Comment updateComment(Comment comm, Integer commentId, Integer postId) throws PostException {
		Optional<Comment> comment=  tDao.findById(commentId);
		
		if(!comment.isPresent()) {
			throw new PostException("Not found any Comments");
		}
		
		Post p = comment.get().getPost();
		comm.setPost(p);
		return tDao.save(comm);
	}

	
}
