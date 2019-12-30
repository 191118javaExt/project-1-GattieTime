package com.revature.repositories;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	public List<User> findAll();
	public User findById(int id);
	public User findByName(String name);
	public User findByEmail(String email);
	public boolean insertUser(User u);
	public boolean updateUser(User u);
}
