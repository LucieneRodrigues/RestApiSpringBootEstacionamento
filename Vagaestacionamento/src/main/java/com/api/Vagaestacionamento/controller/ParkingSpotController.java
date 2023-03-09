package com.api.Vagaestacionamento.controller;

import java.time.LocalDateTime;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Vagaestacionamento.dto.ParkingSpotDto;
import com.api.Vagaestacionamento.model.ParkingSpotModel;
import com.api.Vagaestacionamento.service.ParkingSpotService;

import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parkingspot") //vai service para a classe em geral
public class ParkingSpotController {	
	
		
	final ParkingSpotService parkingSpotService;

	public ParkingSpotController(ParkingSpotService parkingSpotService) {
		super();
		this.parkingSpotService = parkingSpotService;
	}
	@PostMapping
	public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
		//Object pq vai ter diferentes tipos de retorno
		//responseEntity - monta a resposta, status e corpo
		//parametro ParkingSpotDto vai chegar com json por requestbody
		
		//os if diz se ja existe um registro para a placa, vaga ou bloco e ap
		
		if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car already in  use!");		
			
		}
		if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");			
		}
		if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(),parkingSpotDto.getBlock())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartament/block!");
		}
		
		var parkingSpotModel = new ParkingSpotModel();
		BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel); // BeanUtils.copyProperties metodo para converter
		//nesse caso de dto para model
		//antes de salvar no banco de dados converte de dto para model
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
		//se ñ passar na validacao nen vai fazer a requisicao		
		
	}
	@GetMapping
	public ResponseEntity<List<ParkingSpotModel>> getAllParkSpot(){//lista todas as vagas do estacionamento
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneParkingSpot(@PathVariable (value = "id")UUID id){
		Optional <ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if(!parkingSpotModelOptional.isPresent()) {
			// parkingSpotModelOptional estiver presente Parking spot not fund
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not fund");
		}
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteParkingSpot(@PathVariable (value = "id")UUID id){
		
			Optional <ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
			if(!parkingSpotModelOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not fund");
			}
			parkingSpotService.delete(parkingSpotModelOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body("Parking spot deleted sucessfully");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateParkingSpot (@PathVariable (value = "id")UUID id,
			@RequestBody @Valid ParkingSpotDto ParkingSpotDto){
		Optional <ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
		if(!parkingSpotModelOptional.isPresent()) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking spot not found.");
		}
		var parkingSpotModel = new ParkingSpotModel();
		BeanUtils.copyProperties(ParkingSpotDto, parkingSpotModel);
		parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
		parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
		
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
		
		
		
				
	}
	
	
	
	
}
