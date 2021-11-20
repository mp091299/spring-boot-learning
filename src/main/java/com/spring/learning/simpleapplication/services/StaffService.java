package com.spring.learning.simpleapplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.learning.simpleapplication.models.StaffMember;
import com.spring.learning.simpleapplication.repositories.StaffRepository;

@Service
public class StaffService {

	@Autowired
	private StaffRepository staffRepository;

	public List<StaffMember> getAllStaff() {
		return staffRepository.findAll();
	}
}
