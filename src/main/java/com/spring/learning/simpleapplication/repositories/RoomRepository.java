package com.spring.learning.simpleapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.learning.simpleapplication.models.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

}
