package com.promineotech.pet.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class OwnerRequest {
	  
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
