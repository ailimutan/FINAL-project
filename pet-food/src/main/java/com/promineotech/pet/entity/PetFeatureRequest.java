package com.promineotech.pet.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class PetFeatureRequest {
	
	  @NotNull
	  @Positive
	  private Long petId;
	  
	  @NotNull
	  @Length(max = 30)
	  @Pattern(regexp = "[\\w\\s]*")
	  private String color;
	  
	  @NotNull
	  @Length(max = 30)
	  @Pattern(regexp = "[\\w\\s]*")
	  private String gender;
	  
	  @NotNull
	  @Length(max = 30)
	  @Pattern(regexp = "[\\w\\s]*")
	  private String breed;
	  
	  @NotNull
	  @Positive
	  private int weight;
	  
}
