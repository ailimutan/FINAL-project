package com.promineotech.pet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.pet.entity.Features;
import com.promineotech.pet.entity.Food;
import com.promineotech.pet.entity.FoodIntake;
import com.promineotech.pet.entity.FoodIntakeRequest;
import com.promineotech.pet.entity.FoodIntakeWithFoodDetails;
import com.promineotech.pet.entity.FoodRequest;
import com.promineotech.pet.entity.Owner;
import com.promineotech.pet.entity.OwnerRequest;
import com.promineotech.pet.entity.Pet;
import com.promineotech.pet.entity.PetFeatureRequest;
import com.promineotech.pet.entity.PetRequest;
import com.promineotech.pet.entity.UpdateFoodIntakeRequest;
import com.promineotech.pet.entity.UpdateFoodRequest;
import com.promineotech.pet.entity.UpdateOwnerRequest;
import com.promineotech.pet.entity.UpdatePetRequest;
import com.promineotech.pet.service.PetFoodService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultPetFoodController implements PetFoodController{

	@Autowired
	private PetFoodService petFoodService;
	
	//-- Pet Controller Operations
	
	@Override
	public List<Pet> fetchPets(String name, String type) {
		log.info("name={}, type={}",name, type);
		return petFoodService.fetchPets(name, type);
	}

	@Override
	public List<Pet> fetchAllPets() {
		return petFoodService.fetchAllPets();
	}

	@Override
	public Pet addPet(PetRequest petRequest) {
		log.info("Pet={}", petRequest);
		return petFoodService.addPet(petRequest);
	}

	@Override
	public String deletePet(Long petId) {
		log.info("Pet Id={}", petId);
		return petFoodService.deletePet(petId);
	}

	@Override
	public Pet updatePet(@Valid UpdatePetRequest updatePetRequest) {
		log.info("Pet={}", updatePetRequest);
		return petFoodService.updatePet(updatePetRequest);
	}
	
	//-- Feature Controller Operations

	@Override
	public Features addPetFeatures(@Valid PetFeatureRequest request) {
		log.info("Pet Request={}", request);
		return petFoodService.addPetFeatures(request);
	}

	@Override
	public Features updatePetFeatures(@Valid PetFeatureRequest request) {
		log.info("Pet Features={}", request);
		return petFoodService.updatePetFeatures(request);
	}

	@Override
	public List<Features> fetchFeatures(String id) {
		log.info("pet id ={}, type={}", id);
		return petFoodService.fetchFeatures(id);
	}
	

	@Override
	public String deleteFeature(Long petId) {
		return petFoodService.deleteFeature(petId);
	}
	
	//-- Food Controller Operations

	@Override
	public Food addFood(@Valid FoodRequest request) {
		log.info("Food Request={}", request);
		return petFoodService.addFood(request);
	}

	@Override
	public String deleteFood(Long foodId) {
		return petFoodService.deleteFood(foodId);
	}

	@Override
	public List<Food> fetchAllFood() {
		// TODO Auto-generated method stub
		return petFoodService.fetchAllFood();
	}

	@Override
	public Food updateFood(@Valid UpdateFoodRequest request) {
		return petFoodService.updateFood(request);
	}
	
	
	//-- Food Intake Controller Operations

	@Override
	public FoodIntake addFoodIntake(@Valid FoodIntakeRequest request) {
		return petFoodService.createFoodIntake(request);
	}

	@Override
	public String deleteFoodIntake(Long foodId, Long petId) {
		return petFoodService.deleteFoodIntake(foodId,petId);
	}

	@Override
	public List<FoodIntakeWithFoodDetails> fetchAllFoodIntake(Long petId) {
		return petFoodService.fetchAllFoodIntake(petId);
	}

	@Override
	public FoodIntake updateFoodIntake(@Valid UpdateFoodIntakeRequest request) {
		return petFoodService.updateFoodIntake(request);
	}
	
	//-- Owner Controller Operations

	@Override
	public Owner addOwner(@Valid OwnerRequest request) {
		return petFoodService.addOwner(request);
	}

	@Override
	public String deleteOwner(Long ownerId) {
		return petFoodService.deleteOwner(ownerId);
	}

	@Override
	public Owner updateOwner(@Valid UpdateOwnerRequest request) {
		return petFoodService.updateOwner(request);
	}

	@Override
	public List<Owner> fetchAllOwners() {
		return petFoodService.fetchAllOwners();
	}

}
