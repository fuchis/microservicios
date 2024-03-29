package com.aleal.rooms.controller;

import java.util.List;

import com.aleal.rooms.config.RoomsServiceConfiguration;
import com.aleal.rooms.model.PropertiesRooms;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleal.rooms.model.Room;
import com.aleal.rooms.services.IRoomService;

@RestController
public class RoomController {

	@Autowired
	private IRoomService service;

	@Autowired
	private RoomsServiceConfiguration configRooms;
	
	@GetMapping("rooms")
	public List<Room> search(){
		return (List<Room>) this.service.search();	
	}

	@GetMapping("rooms/read/properties")
	public String getPropertiesRooms() throws JsonProcessingException {

		ObjectWriter owj = new ObjectMapper().writer().withDefaultPrettyPrinter();
		PropertiesRooms propertiesRooms = new PropertiesRooms(
				configRooms.getMsg(),
				configRooms.getBuildVersion(),
				configRooms.getMailDetails()
		);

		return owj.writeValueAsString(propertiesRooms);
	}
}
