package com.boot.repository;

import java.util.List;

import com.boot.model.User;



public interface UserRepository {

	public List<User> findAll();

	public User findById(Integer id);

	public boolean save(User user);

	public boolean remove(Integer id);

	public boolean update(User user);
}
