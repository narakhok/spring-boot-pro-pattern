package com.boot.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.boot.model.User;
import com.github.javafaker.Faker;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private List<User> users = new ArrayList<>();

	// public UserRepositoryImpl() {
	// for (int i = 0; i < 10; i++) {
	// User user = new User(i + 1, "user-" + i, (i > 5) ? "M" : "F",
	// "User.JPG");
	// users.add(user);
	// }
	// }
	
	public UserRepositoryImpl(){
		Faker faker = new Faker();
		
		faker.cat().name();

	}

	// faker for sample data from library

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public User findById(Integer id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}

	@Override
	public boolean save(User user) {
		return users.add(user);
	}

	@Override
	public boolean remove(Integer id) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				users.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(User user) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == user.getId()) {
				users.set(i, user);
				return true;
			}
		}
		return false;
	}

}
