package com.promineotech.pet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Features implements Comparable<Features> {
	  private Long featurePk;
	  private Long petFk;
	  private String color;
	  private String gender;
	  private String breed;
	  private int weight;
	  
	  @JsonIgnore
	  public Long getFeaturePK() {
		  return featurePk;
	  }

	@Override
	public int compareTo(Features that) {
		// @formatter: off
		return Comparator
				.comparing(Features ::getFeaturePK)
				.thenComparing(Features :: getPetFk)
				.thenComparing(Features :: getColor)
				.thenComparing(Features :: getWeight)
				.thenComparing(Features :: getGender)
				.thenComparing(Features :: getWeight)
				.compare(this, that);
		// @formatter: on
	}
}