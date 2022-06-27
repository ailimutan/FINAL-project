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
public class FoodIntake implements Comparable<FoodIntake> {
	  private Long foodIntakeId;
	  private Long foodId;
	  private Long petId;

	@Override
	public int compareTo(FoodIntake that) {
		// @formatter: off
		return Comparator
				.comparing(FoodIntake ::getFoodIntakeId)
				.thenComparing(FoodIntake :: getFoodId)
				.thenComparing(FoodIntake :: getPetId)
				.compare(this, that);
		// @formatter: on
	}
}