package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.PostException;
import com.masai.Exception.UserException;
import com.masai.module.Post;
import com.masai.module.User;
import com.masai.repository.PostDao;
import com.masai.repository.UserDao;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostDao pDao;
	
	@Autowired
	private UserDao uDao;
	
	@Override
	public Post createNewPost(Post post,Integer userId) throws UserException {
		
		Optional<User> user = uDao.findById(userId);
		
		if(!user.isPresent()) {
			throw new UserException("User not found");
		}
		
		User user1 = user.get();
		
		// To save the Post in post table
		post.setUser(user.get());
		Post post1 = pDao.save(post);
	
		
		// To save the post in user List
		user1.getPosts().add(post1);
		uDao.save(user1);
		
		
		return post1;
	}

	@Override
	public String deletePost(Integer postId) throws PostException {
		Optional<Post> post =  pDao.findById(postId);
		if(!post.isPresent()) {
			throw new PostException("Not found any post with these ID");
		}
		User user = post.get().getUser();
		
		user.getPosts().remove(post.get());
		uDao.save(user);
		pDao.delete(post.get());
		return "Deleted";
	}

	@Override
	public Post updatePost(Integer postID, Post post) throws PostException {
		Optional<Post> optionalPost =  pDao.findById(postID);
		if(!optionalPost.isPresent()) {
			throw new PostException("Not found any post with these ID");
		}
		User user =  optionalPost.get().getUser();
		post.setUser(optionalPost.get().getUser());
		return pDao.save(post);
	}

	@Override
	public List<Post> getAllPost(Integer userId) throws UserException {
		Optional<User> user = uDao.findById(userId);
		
		if(!user.isPresent()) {
			throw new UserException("User not found");
		}
		return user.get().getPosts();
	}

	@Override
	public Post getPostById(Integer postId) throws PostException {
		Optional<Post> optionalPost =  pDao.findById(postId);
		if(!optionalPost.isPresent()) {
			throw new PostException("Not found any post with these ID");
		}
		return optionalPost.get();
	}
	
}
	
