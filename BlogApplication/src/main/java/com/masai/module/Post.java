package com.masai.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	@NotNull
	@Size(min=2, message = "Minimum size should be 20")
	private String title;
	
	@NotNull
	@Size(min=10, message = "Minimum size should be 10")
	private String description;
	
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	
	@JsonIgnore
	@OneToMany
	private List<Comment> tasks = new ArrayList<>();
	
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Comment> getTasks() {
		return tasks;
	}


	public void setTasks(List<Comment> tasks) {
		this.tasks = tasks;
	}


	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
