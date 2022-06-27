package com.promineotech.pet.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class UpdateOwnerRequest {
	
	  @NotNull
	  @Positive
	  private Long ownerPk;

	  @NotNull
	  @Length(max = 30)
	  @Pattern(regexp = "[\\w\\s]*")
	  private String firstName;
	  
	  @NotNull
	  @Length(max = 30)
	  @Pattern(regexp = "[\\w\\s]*")
	  private String lastName;
	  
	  @Length(max = 30)
	  @Pattern(regexp = "[\\w\\s]*")
	  private String phone;
	  
	  
}
