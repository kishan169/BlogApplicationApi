package com.masai.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.module.SessionUser;

public interface SessionDao extends JpaRepository<SessionUser, Integer>{
	
	public Optional<SessionUser> findByUserID(Integer userID);
	
	public Optional<SessionUser> findByUuId(String uuId);
}
