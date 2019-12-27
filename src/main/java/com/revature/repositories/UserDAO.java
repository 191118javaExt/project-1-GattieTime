package com.revature.repositories;

import java.util.List;

import com.revatute.models.User;

public interface UserDAO {
	public List<User> findAll();
	public User findById();
	public User findByName();
	public User findByEmail();
	public boolean insertUser();
	public boolean updateUser();
}
