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
public class Food implements Comparable<Food> {
	  private Long foodId;
	  private String brand;
	  private String description;

	  public Long getFoodId() {
		  return foodId;
	  }

	@Override
	public int compareTo(Food that) {
		// @formatter: off
		return Comparator
				.comparing(Food ::getFoodId)
				.thenComparing(Food :: getBrand)
				.thenComparing(Food :: getDescription)
				.compare(this, that);
		// @formatter: on
	}
}