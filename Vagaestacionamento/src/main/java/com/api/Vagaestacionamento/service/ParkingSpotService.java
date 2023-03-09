package com.api.Vagaestacionamento.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.api.Vagaestacionamento.model.ParkingSpotModel;
import com.api.Vagaestacionamento.repository.ParkingSpotRepository;

import jakarta.transaction.Transactional;


@Service
public class ParkingSpotService {
	
	//depois ver a possibilidade de criar uma interface e depois complementar com essa classe
	//regra de negocio
	//subistitui  a anotacão
	
	final ParkingSpotRepository parkingSpotRepository;
	//criado um construtor
	
	public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
		
	}
	@Transactional //se algo der errado na transação, ele garante que tudo volte como antes, sem alteracoes
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		// TODO Auto-generated method stub
		return parkingSpotRepository.save(parkingSpotModel);
		//necessário para salvar as informacoes no banco
	}
	public boolean existsByLicensePlateCar(String licensePlateCar) {
		// TODO Auto-generated method stub
		return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
	}
	public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
		// TODO Auto-generated method stub
		return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber) ;
	}
	public boolean existsByApartmentAndBlock(String apartment, String block) {
		// TODO Auto-generated method stub
		return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
	}
	public List<ParkingSpotModel> findAll() {
		// TODO Auto-generated method stub
		return  parkingSpotRepository.findAll();
	}
	public Optional<ParkingSpotModel> findById(UUID id) {
		// TODO Auto-generated method stub
		 return parkingSpotRepository.findById(id);
	}
	@Transactional
	public void delete(ParkingSpotModel parkingSpotModel) {
		// TODO Auto-generated method stub
		 parkingSpotRepository.delete(parkingSpotModel);
		 
		 //só deleta ñ tem retorno
		
	}
	
	

}
