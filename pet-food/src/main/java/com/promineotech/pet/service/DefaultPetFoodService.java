package com.promineotech.pet.service;

import java.util.List;

import javax.validation.Valid;

import com.promineotech.pet.dao.PetFoodDao;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultPetFoodService implements PetFoodService {
	
	@Autowired
	private PetFoodDao petFoodDao;
	
	
	//-- Pet Service Operations

	@Override
	public List<Pet> fetchPets(String name, String type) {
		log.info("The fetchPets method was called with name ={} and type ={}",name, type);
		return petFoodDao.fetchPets(name, type);
	}

	@Override
	public List<Pet> fetchAllPets() {
		return petFoodDao.fetchAllPets();
	}

	@Transactional
	@Override
	public Pet addPet(PetRequest petRequest) {
		return petFoodDao.addPet(petRequest);
	}

	@Override
	public Pet updatePet(UpdatePetRequest request) {
		return petFoodDao.updatePet(request);
	}

	@Override
	public String deletePet(Long petId) {
		return petFoodDao.deletePet(petId);
	}
	
	//-- Feature Service Operations

	@Override
	public Features addPetFeatures(@Valid PetFeatureRequest request) {
		return petFoodDao.addPetFeatures(request);
	}

	@Override
	public Features updatePetFeatures(@Valid PetFeatureRequest request) {
		return petFoodDao.updatePetFeatures(request);
	}

	@Override
	public List<Features> fetchFeatures(String id) {
		return petFoodDao.fetchFeatures(id);
	}
	
	@Override
	public String deleteFeature(Long petId) {
		return petFoodDao.deleteFeature(petId);
	}
	
	//-- Food Service Operations

	@Override
	public Food addFood(@Valid FoodRequest request) {
		return petFoodDao.addFood(request);
	}

	@Override
	public String deleteFood(Long foodId) {
		return petFoodDao.deleteFood(foodId);
	}

	@Override
	public List<Food> fetchAllFood() {
		return petFoodDao.fetchAllFood();
	}

	@Override
	public Food updateFood(@Valid UpdateFoodRequest request) {
		return petFoodDao.updateFood(request);
	}
	
	//-- Food Intake Service Operations

	@Override
	public FoodIntake createFoodIntake(@Valid FoodIntakeRequest request) {
		return petFoodDao.createFoodIntake(request);
	}

	@Override
	public String deleteFoodIntake(Long foodId, Long petId) {
		return petFoodDao.deleteFoodIntake(foodId,petId);
	}

	@Override
	public List<FoodIntakeWithFoodDetails> fetchAllFoodIntake(Long petId) {
		return petFoodDao.fetchAllFoodIntake(petId);
	}

	@Override
	public FoodIntake updateFoodIntake(@Valid UpdateFoodIntakeRequest request) {
		return petFoodDao.updateFoodIntake(request);
	}
	
	//-- Owner Service Operations

	@Override
	public Owner addOwner(@Valid OwnerRequest request) {
		return petFoodDao.addOwner(request);
	}

	@Override
	public String deleteOwner(Long ownerId) {
		return petFoodDao.deleteOwner(ownerId);
	}

	@Override
	public Owner updateOwner(@Valid UpdateOwnerRequest request) {
		return petFoodDao.updateOwner(request);
	}

	@Override
	public List<Owner> fetchAllOwners() {
		return petFoodDao.fetchAllOwners();
	}

}
