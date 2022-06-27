package com.promineotech.pet.dao;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

public interface PetFoodDao {
	
	//-- Pet DAO Operations
	List<Pet> fetchPets(String name, String type);
	List<Pet> fetchAllPets();
	Optional<Owner> fetchOwner(String ownerId);
	Pet addPet(PetRequest petRequest);
	Pet updatePet(UpdatePetRequest updatePetRequest);
	String deletePet(Long petId);
	
	//-- Feature DAO Operations
	Features addPetFeatures(@Valid PetFeatureRequest request);
	Features updatePetFeatures(@Valid PetFeatureRequest request);
	List<Features> fetchFeatures(String id);
	String deleteFeature(Long petId);
	
	//-- Food DAO Operations
	Food addFood(@Valid FoodRequest request);
	String deleteFood(Long foodId);
	List<Food> fetchAllFood();
	Food updateFood(@Valid UpdateFoodRequest request);
	
	//-- FoodIntake DAO Operations
	FoodIntake createFoodIntake(@Valid FoodIntakeRequest request);
	String deleteFoodIntake(Long foodId, Long petId);
	List<FoodIntakeWithFoodDetails> fetchAllFoodIntake(Long petId);
	FoodIntake updateFoodIntake(@Valid UpdateFoodIntakeRequest request);
	
	//-- Owner DAO Operations
	Owner addOwner(@Valid OwnerRequest request);
	String deleteOwner(Long ownerId);
	Owner updateOwner(@Valid UpdateOwnerRequest request);
	List<Owner> fetchAllOwners();

}
