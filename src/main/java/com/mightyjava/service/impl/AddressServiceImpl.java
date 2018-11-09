package com.mightyjava.service.impl;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mightyjava.model.Address;
import com.mightyjava.model.User;
import com.mightyjava.repository.AddressRepository;
import com.mightyjava.repository.UserRepository;
import com.mightyjava.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	private AddressRepository addressRepository;
	private UserRepository userRepository;

	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<Address> addressList() {
		return addressRepository.addressList();
	}
	
	@Override
	public Page<Address> findAll(Pageable pageable) {
		return addressRepository.findAll(new PageRequest(pageable.getPageNumber() - 1, 5));
	}

	@Override
	public Address findOne(Long id) {
		return addressRepository.findOne(id);
	}

	@Override
	public String addAddress(Address address) { 
		String message = null;
		JSONObject jsonObject = new JSONObject();
		try {
			if (address.getId() == null) {
				message = "Added";
			} else {
				message = "Updated";
			}
			User user = userRepository.findOne(address.getUserId());
			address.setUser(user); 
			addressRepository.save(address);
			jsonObject.put("status", "success");
			jsonObject.put("title", message+" Confirmation");
			jsonObject.put("message", "Address for "+user.getFullName() +" "+ message + " successfully.");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	public String deleteAddress(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			addressRepository.delete(id);
			jsonObject.put("message", "Address Deleted Successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	@Override
	public void refreshCache() {
		
	}

}
