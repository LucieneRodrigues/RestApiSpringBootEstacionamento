package com.api.Vagaestacionamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ParkingSpotDto {
	//responsavel pela validacao dos dados
	//ñ precida do id, é gerado automatico
	//data de registro é gerado por regras especificas
	
	

	//saber se o campo é null ou stringvasia
	
	@NotBlank
	private String parkingSpotNumber;
	@Size(max = 7) //limita o numero de caractere
	@NotBlank
	private String licensePlateCar;	
	@NotBlank
	private String brandCar;	
	@NotBlank
	private String modelCar;	
	@NotBlank
	private String colorCar;
	@NotBlank
	private String responsibleName;	
	@NotBlank
	private String apartment;
	@NotBlank
	private String block;
	
	
	public String getParkingSpotNumber() {
		return parkingSpotNumber;
	}
	public void setParkingSpotNumber(String parkingSpotNumber) {
		this.parkingSpotNumber = parkingSpotNumber;
	}
	public String getLicensePlateCar() {
		return licensePlateCar;
	}
	public void setLicensePlateCar(String licensePlateCar) {
		this.licensePlateCar = licensePlateCar;
	}
	public String getBrandCar() {
		return brandCar;
	}
	public void setBrandCar(String brandCar) {
		this.brandCar = brandCar;
	}
	public String getModelCar() {
		return modelCar;
	}
	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}
	public String getColorCar() {
		return colorCar;
	}
	public void setColorCar(String colorCar) {
		this.colorCar = colorCar;
	}
	public String getResponsibleName() {
		return responsibleName;
	}
	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}

	
	
}
