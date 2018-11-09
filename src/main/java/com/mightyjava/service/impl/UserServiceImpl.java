package com.mightyjava.service.impl;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mightyjava.model.Role;
import com.mightyjava.model.User;
import com.mightyjava.repository.RoleRepository;
import com.mightyjava.repository.UserRepository;
import com.mightyjava.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<User> userList() {
		return userRepository.userList();
	}
	
	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(new PageRequest(pageable.getPageNumber() - 1, 5));
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public String addUser(User user) {
		String message = null;
		JSONObject jsonObject = new JSONObject();
		try {
			if(user.getId() == null) {
				message = "Added";
			} else {
				message = "Updated";
			}
			if(user.getProfilePhoto() == null) {
				user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
				user.setRole(roleRepository.findOne(user.getRoleId()));
			}
			jsonObject.put("status", "success");
			jsonObject.put("title", message+" Confirmation");
			jsonObject.put("message", userRepository.save(user).getFullName()+" "+message+" successfully.");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	public String deleteUser(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			userRepository.delete(id);
			jsonObject.put("message", "User Deleted Successfully.");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	@Override
	public List<Role> roleList() {
		return roleRepository.findAll();
	}

	@Override
	public void refreshCache() {
		
	}

}
