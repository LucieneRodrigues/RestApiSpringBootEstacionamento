package com.api.Vagaestacionamento.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.Vagaestacionamento.model.ParkingSpotModel;

@Repository  //anotacao ñ é obrigatoria
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID>{
	//JpaRepository classe do banco springdata
	boolean existsByLicensePlateCar(String licensePlateCar); //declarado metodo pois aqui e interface
	//esse metodo e pra verificar se exite ja carro na vaga, ap com carro etc	
	
	boolean existsByParkingSpotNumber(String parkingSpotNumber);
	boolean existsByApartmentAndBlock(String apartment, String block);
}
