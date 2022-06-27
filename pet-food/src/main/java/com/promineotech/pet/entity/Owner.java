package com.promineotech.pet.entity;

import lombok.Builder;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Comparator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner implements Comparable<Owner>{
	  private Long ownerPK;
	  private String firstName;
	  private String lastName;
	  private String phone;
	  
	  public Long getOwnerPk() {
		  return ownerPK;
	  }

	@Override
	public int compareTo(Owner that) {
		// @formatter: off
		return Comparator
				.comparing(Owner ::getOwnerPk)
				.thenComparing(Owner :: getFirstName)
				.thenComparing(Owner :: getLastName)
				.thenComparing(Owner :: getPhone)
				.compare(this, that);
		// @formatter: on
	}
}
