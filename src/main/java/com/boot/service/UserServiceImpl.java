package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.boot.model.User;
import com.boot.repository.UserRepository;


public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public void save(User user) {
		boolean status = userRepository.save(user);
		if(status)
			System.out.println("-> Added Successfully!");
		else
			System.out.println("-> Added Fail!");
		
	}

	@Override
	public void remove(Integer id) {
		if(userRepository.remove(id))
			System.out.println("-> Removed Successfully!");
		else
			System.out.println("-> Remove Fail!");
	}

	@Override
	public void update(User user) {
		if(userRepository.update(user))
			System.out.println("-> Updated Successfully!");
		else
			System.out.println("-> Update Fail!");
		
	}

}
