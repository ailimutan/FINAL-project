package com.promineotech.pet.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class UpdateFoodIntakeRequest {
	
	  @NotNull
	  @Positive
	  private Long foodIntakeId;
	 
	  @NotNull
	  @Positive
	  private Long foodId;
	  
	  @NotNull
	  @Positive
	  private Long petId;
	  
}
