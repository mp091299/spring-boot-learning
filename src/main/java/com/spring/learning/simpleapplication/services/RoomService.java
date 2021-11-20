package com.spring.learning.simpleapplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.learning.simpleapplication.models.Room;
import com.spring.learning.simpleapplication.repositories.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
}