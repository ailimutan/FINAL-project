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
public class FoodIntakeWithFoodDetails implements Comparable<FoodIntakeWithFoodDetails> {
	  private Long foodId;
	  private Long petId;
	  private String brand;
	  private String description;

	@Override
	public int compareTo(FoodIntakeWithFoodDetails that) {
		// @formatter: off
		return Comparator
				.comparing(FoodIntakeWithFoodDetails ::getFoodId)
				.thenComparing(FoodIntakeWithFoodDetails :: getPetId)
				.thenComparing(FoodIntakeWithFoodDetails :: getBrand)
				.thenComparing(FoodIntakeWithFoodDetails :: getDescription)
				.compare(this, that);
		// @formatter: on
	}
}