package com.promineotech.pet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Comparator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet implements Comparable<Pet> {
	  private Long petId;
	  private Long ownerId;
	  private String petName;
	  private String petType;

	  public Long getPetId() {
		  return petId;
	  }

	@Override
	public int compareTo(Pet that) {
		// @formatter: off
		return Comparator
				.comparing(Pet ::getPetId)
				.thenComparing(Pet :: getOwnerId)
				.thenComparing(Pet :: getPetName)
				.thenComparing(Pet :: getPetType)
				.compare(this, that);
		// @formatter: on
	}
}