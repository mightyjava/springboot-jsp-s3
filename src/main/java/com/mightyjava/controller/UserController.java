package com.mightyjava.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mightyjava.model.User;
import com.mightyjava.service.AmazonService;
import com.mightyjava.service.UserService;
import com.mightyjava.utils.ErrorUtils;
import com.mightyjava.utils.MethodUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AmazonService amazonService;
	
	@RequestMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");
		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		return "login";
	}
	
	@GetMapping("/form")
	public String userForm(Model model) {
		model.addAttribute("isNew", true);
		model.addAttribute("userForm", new User());
		model.addAttribute("roles", userService.roleList());
		return "user/form";
	}
	
	@GetMapping("/edit/{id}")
	public String userOne(@PathVariable Long id, Model model) {
		model.addAttribute("isNew", false);
		model.addAttribute("userForm", userService.findOne(id));
		model.addAttribute("roles", userService.roleList());
		return "user/form";
	}
	
	@GetMapping(value = "/delete/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody String userDelete(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
	
	@PostMapping(value="/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public @ResponseBody String userAdd(@Valid @RequestBody User user, BindingResult result) {
		if(result.hasErrors()) {
			return ErrorUtils.customErrors(result.getAllErrors());
		} else {
			return userService.addUser(user);
		}
	}
	
	@GetMapping("/list/{id}")
	public User findOne(@PathVariable Long id) {
		return userService.findOne(id);
	}
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public String userList(Model model, Pageable pageable) {
		Page<User> pages = userService.findAll(pageable);
		model.addAttribute("users", pages.getContent());
		MethodUtils.pageModel(model, pages);
		return "/user/list";
	}
	
	@GetMapping("/refresh")
	public String refreshCache(Model model, Pageable pageable) {
		userService.refreshCache();
		Page<User> pages = userService.findAll(pageable);
		model.addAttribute("users", pages.getContent());
		MethodUtils.pageModel(model, pages);
		return "/user/list";
	}
	
	@PostMapping("/upload")
	public @ResponseBody String fileUpload(@RequestPart(value="file") MultipartFile multipartFile, @RequestParam(value="editUserId") Long editUserId) {
		return amazonService.uploadFile(multipartFile, userService.findOne(editUserId));
	}
}
