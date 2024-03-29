package com.aleal.reservations.controller;

import java.util.List;

import com.aleal.reservations.config.ReservationsServiceConfiguration;
import com.aleal.reservations.model.PropertiesReservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleal.reservations.model.Reservation;
import com.aleal.reservations.services.IReservationService;

@RestController
public class ReservationController {
	
	@Autowired
	private IReservationService service;

	@Autowired
	private ReservationsServiceConfiguration configReservations;
	

	@GetMapping("reservations")
	public List<Reservation> search(){
		return (List<Reservation>) this.service.search();	
	}

	@GetMapping("reservations/read/properties")
	public String getPropertiesReservations() throws JsonProcessingException {
		ObjectWriter owj = new ObjectMapper().writer().withDefaultPrettyPrinter();
		PropertiesReservation propertiesReservations = new PropertiesReservation(
				configReservations.getMsg(),
				configReservations.getBuildVersion(),
				configReservations.getMailDetails()
		);
		return owj.writeValueAsString(propertiesReservations);
	}

}
