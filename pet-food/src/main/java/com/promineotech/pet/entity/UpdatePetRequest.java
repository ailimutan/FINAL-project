package com.promineotech.pet.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class UpdatePetRequest {
	
	  @NotNull
	  @Positive
	  private Long petId;

	  @NotNull
	  @Positive
	  private Long ownerId;
	  
	  @NotNull
	  @Length(max = 30)
	  @Pattern(regexp = "[\\w\\s]*")
	  private String petName;
	  
	  @NotNull
	  @Length(max = 30)
	  @Pattern(regexp = "[\\w\\s]*")
	  private String petType;
	  
}
