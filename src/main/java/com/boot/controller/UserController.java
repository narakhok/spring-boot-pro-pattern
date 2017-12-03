package com.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.model.User;
import com.boot.service.FileUploadService;
import com.boot.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private FileUploadService fileUpload;

	@RequestMapping(value ={"/","/user"})
	public String userPage(Model model) {

		List<User> users = userService.findAll();

		model.addAttribute("users", users);
		return "user/user";
	}

	@RequestMapping(value = "/user/{id}")
	public String userDetail(@PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userdetail", user);
		return "user/userdetail";
	}

	@RequestMapping(value = "/user/add")
	public String userAddPage(Model model) {
		
		// ==== Use For Check Status for Single Page====//
		model.addAttribute("addStatus", true);		
		model.addAttribute("user", new User());
		return "user/adduser";
	}
// Add vilidator we user @Valid and BindingResult to show resutl of error
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String actionAddUser(@RequestParam("file")MultipartFile file,Model model,@Valid User user, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()){
			for(FieldError error: bindingResult.getFieldErrors()){
				System.out.println(error.getField() + ":"+error.getField());
			}
			
			model.addAttribute("addStatus", true);
			model.addAttribute("user", user);
			return "/user/adduser";
		}
		String filePath = fileUpload.upload(file);
		user.setImage(filePath);
		
		System.out.println(user);
		userService.save(user);
		return "redirect:/user";
	}

	@RequestMapping(value = "/user/remove")
	public String removeUser(@RequestParam("id") Integer id) {
		System.out.println("Id: " + id);
		userService.remove(id);
		return "redirect:/user";
	}

	@RequestMapping(value = "/user/edit")
	public String editUser(Model model, @RequestParam("id") Integer id) {
		System.out.println("Id: " + id);
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("addStatus", false);
		//return "user/edituser";
		return "user/adduser";
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public String updateUser(@RequestParam("file")MultipartFile file,User user) {
		System.out.println(user);
		userService.update(user);
		String filePath = fileUpload.upload(file);
		user.setImage(filePath);		
		return "redirect:/user";
	}
}
