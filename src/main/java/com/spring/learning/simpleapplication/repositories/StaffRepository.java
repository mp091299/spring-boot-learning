package com.spring.learning.simpleapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.learning.simpleapplication.models.StaffMember;

public interface StaffRepository extends JpaRepository<StaffMember, String> {

}
