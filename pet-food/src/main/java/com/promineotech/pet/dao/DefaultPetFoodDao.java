package com.promineotech.pet.dao;

import com.promineotech.pet.entity.Owner;
import com.promineotech.pet.entity.OwnerRequest;
import com.promineotech.pet.entity.Pet;
import com.promineotech.pet.entity.PetFeatureRequest;
import com.promineotech.pet.entity.PetRequest;
import com.promineotech.pet.entity.UpdateFoodIntakeRequest;
import com.promineotech.pet.entity.UpdateFoodRequest;
import com.promineotech.pet.entity.UpdateOwnerRequest;
import com.promineotech.pet.entity.UpdatePetRequest;
import com.promineotech.pet.entity.Features;
import com.promineotech.pet.entity.Food;
import com.promineotech.pet.entity.FoodIntake;
import com.promineotech.pet.entity.FoodIntakeRequest;
import com.promineotech.pet.entity.FoodIntakeWithFoodDetails;
import com.promineotech.pet.entity.FoodRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultPetFoodDao implements PetFoodDao {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Pet> fetchPets(String name, String type) {
		log.debug("DAO: name={}, type={}", name, type);
		
		//@formatter: off
		String sql = ""
				+ "SELECT * "
				+ "FROM pets "
				+ "WHERE pet_name = :pet_name OR pet_type = :pet_type";
		//@formatter: on
		
		Map<String,Object> params = new HashMap<>();
		params.put("pet_name", name);
		params.put("pet_type", type);
		
		return jdbcTemplate.query(sql, params,
				new RowMapper<>() {
			@Override
			public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
				//@formatter: off
				return Pet.builder()
						.petId(rs.getLong("pet_pk"))
						.ownerId(rs.getLong("owner_fk"))
						.petName(rs.getString("pet_name"))
						.petType(rs.getString("pet_type"))
						.build();
				//@formatter: on
			}});
	}

	@Override
	public List<Pet> fetchAllPets() {
		//@formatter: off
		String sql = ""
				+ "SELECT * "
				+ "FROM pets ";
		//@formatter: on
		
		return jdbcTemplate.query(sql,
				new RowMapper<>() {
			@Override
			public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
				//@formatter: off
				return Pet.builder()
						.petId(rs.getLong("pet_pk"))
						.ownerId(rs.getLong("owner_fk"))
						.petName(rs.getString("pet_name"))
						.petType(rs.getString("pet_type"))
						.build();
				//@formatter: on
			}});
	}

	@Override
	public Optional< Owner > fetchOwner(String ownerId) {
	   // @formatter:off
	    String sql = "" +
		      "SELECT * " +
		      "FROM owners " +
		      "WHERE owner_pk = :owner_pk";
		    // @formatter:on

		    Map < String, Object > params = new HashMap < > ();
		    params.put("owner_pk", ownerId);

		    return Optional.ofNullable(
		      jdbcTemplate.query(sql, params, new OwnerResultSetExtractor()));
	}
	
	
	  private SqlParams generateInsertSql(PetRequest petRequest) {
	    // @formatter:off
	    String sql = "" +
	      "INSERT INTO pets (" +
	      "owner_fk, pet_name, pet_type" +
	      ") VALUES (" +
	      ":owner_fk, :pet_name, :pet_type" +
	      ")";
	    // @formatter:on

	    SqlParams params = new SqlParams();

	    params.sql = sql;
	    params.source.addValue("owner_fk", petRequest.getOwnerId());
	    params.source.addValue("pet_name", petRequest.getPetName());
	    params.source.addValue("pet_type", petRequest.getPetType());

	    return params;
	  }
	  
	  private SqlParams generateUpdateSql(UpdatePetRequest petRequest) {
		    // @formatter:off
		    String sql = "" +
		      "UPDATE pets SET " +
		      "owner_fk = :owner_fk, pet_name = :pet_name, pet_type = :pet_type " +
		      "WHERE pet_pk = :pet_pk ";
		    // @formatter:on

		    SqlParams params = new SqlParams();

		    params.sql = sql;
		    params.source.addValue("pet_pk", petRequest.getPetId());
		    params.source.addValue("owner_fk", petRequest.getOwnerId());
		    params.source.addValue("pet_name", petRequest.getPetName());
		    params.source.addValue("pet_type", petRequest.getPetType());

		    return params;
	 }
	  
	  private SqlParams generateDeleteSql(Long petId) {
		    // @formatter:off
		    String sql = "" +
		      "DELETE FROM pets " +
		      "WHERE pet_pk = :pet_pk ";
		    // @formatter:on
		    SqlParams params = new SqlParams();

		    params.sql = sql;
		    params.source.addValue("pet_pk", petId);
		    return params;
	  }

		
    class OwnerResultSetExtractor implements ResultSetExtractor < Owner > {
			    @Override
			    public Owner extractData(ResultSet rs) throws SQLException {
			      rs.next();

			      // @formatter:off
			      return Owner.builder()
			        .ownerPK(rs.getLong("owner_pk"))
			        .firstName(rs.getString("first_name"))
			        .lastName(rs.getString("last_name"))
			        .phone(rs.getString("phone"))
			        .build();
			      // @formatter:on

			    }
			  }
		  
		  class SqlParams {
			    String sql;
			    MapSqlParameterSource source = new MapSqlParameterSource();
			  }

		@Override
		public Pet addPet(PetRequest petRequest) {
		  	SqlParams params = generateInsertSql(petRequest);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);	
		  	
		  	Long petPK = keyHolder.getKey().longValue();
		  	
		  	// @formatter: off
		  	return Pet.builder()
		  			.petId(petPK)
		  			.ownerId(petRequest.getOwnerId())
		  			.petName(petRequest.getPetName())
		  			.petType(petRequest.getPetType())
		  			.build();
			// @formatter: on
		}

		@Override
		public Pet updatePet(UpdatePetRequest updatePetRequest) {
		  	SqlParams params = generateUpdateSql(updatePetRequest);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);
		  			  	
		  	// @formatter: off
		  	return Pet.builder()
		  			.petId(updatePetRequest.getPetId())
		  			.ownerId(updatePetRequest.getOwnerId())
		  			.petName(updatePetRequest.getPetName())
		  			.petType(updatePetRequest.getPetType())
		  			.build();
			// @formatter: on
		}

		@Override
		public String deletePet(Long petId) {
		  	SqlParams params = generateDeleteSql(petId);
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);
		  
		  	// @formatter: off
		  	return "{}";
			// @formatter: on
		}
		

		
		  private SqlParams generateInsertSqlForFeatures(PetFeatureRequest request) {
			    // @formatter:off
			    String sql = "" +
			      "INSERT INTO features (" +
			      "pet_fk, color,breed,gender, weight" +
			      ") VALUES (" +
			      ":pet_fk, :color, :breed, :gender, :weight" +
			      ")";
			    // @formatter:on

			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("pet_fk", request.getPetId());
			    params.source.addValue("color", request.getColor());
			    params.source.addValue("breed", request.getBreed());
			    params.source.addValue("gender", request.getGender());
			    params.source.addValue("weight", request.getWeight());

			    return params;
		}
		  
		private SqlParams generateUpdateSqlForFeatures(PetFeatureRequest request, int choice) {
			String sql = ""; 
		    SqlParams params = new SqlParams();


			if(choice == 0) {
				// @formatter:off
			    sql = "" +
					      "UPDATE features SET " +
					      "color = :color, breed = :breed, gender = :gender, weight = :weight " +
					      "WHERE pet_fk = :pet_fk ";
			    // @formatter:on
			    
			    params.source.addValue("pet_fk", request.getPetId());
			    params.source.addValue("color", request.getColor());
			    params.source.addValue("breed", request.getBreed());
			    params.source.addValue("gender", request.getGender());
			    params.source.addValue("weight", request.getWeight());
			}
			
		    params.sql = sql;

			    return params;
		 }

		@Override
		public Features addPetFeatures(@Valid PetFeatureRequest request) {
		  	SqlParams params = generateInsertSqlForFeatures(request);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);	
		  	
		  	// @formatter: off
		  	return Features.builder()
		  			.petFk(request.getPetId())
		  			.color(request.getColor())
		  			.breed(request.getBreed())
		  			.gender(request.getGender())
		  			.weight(request.getWeight())
		  			.build();
			// @formatter: on
		}

		@Override
		public Features updatePetFeatures(@Valid PetFeatureRequest request) {
		  	SqlParams params = generateUpdateSqlForFeatures(request, 0);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);	
		  	
		  	// @formatter: off
		  	return Features.builder()
		  			.petFk(request.getPetId())
		  			.color(request.getColor())
		  			.breed(request.getBreed())
		  			.gender(request.getGender())
		  			.weight(request.getWeight())
		  			.build();
			// @formatter: on
		}

		@Override
		public List<Features> fetchFeatures(String id) {
			log.debug("DAO: id={}", id);
			String sql = "";
			
			if(id.isEmpty() || id == null) {
				//@formatter: off
				 sql = ""
						+ "SELECT * "
						+ "FROM features ";
				//@formatter: on
			}else {
				//@formatter: off
				 sql = ""
						+ "SELECT * "
						+ "FROM features "
						+ "WHERE pet_fk = :pet_fk";
				//@formatter: on
					
			}
			
			Map<String,Object> params = new HashMap<>();
			params.put("pet_fk", id);
			
			return jdbcTemplate.query(sql, params,
					new RowMapper<>() {
				@Override
				public Features mapRow(ResultSet rs, int rowNum) throws SQLException {
				  	// @formatter: off
				  	return Features.builder()
				  			.petFk(rs.getLong("pet_fk"))
				  			.color(rs.getString("color"))
				  			.breed(rs.getString("breed"))
				  			.gender(rs.getString("gender"))
				  			.weight(rs.getInt("weight"))
				  			.build();
					// @formatter: on
				}});
		}
		
		@Override
		public String deleteFeature(Long petId) {
		  	SqlParams params = generateDeleteSqlForFeature(petId);
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);
		  
		  	// @formatter: off
		  	return "{}";
			// @formatter: on
		}
		
		  private SqlParams generateDeleteSqlForFeature(Long petId) {
			    // @formatter:off
			    String sql = "" +
			      "DELETE FROM features " +
			      "WHERE pet_fk = :pet_fk ";
			    // @formatter:on
			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("pet_fk", petId);
			    return params;
		  }
		
		
		//--- FOOD DATABASE
		
		  private SqlParams generateInsertSqlForFood(FoodRequest request) {
			    // @formatter:off
			    String sql = "" +
			      "INSERT INTO food (" +
			      "brand, food_description" +
			      ") VALUES (" +
			      ":brand, :description" +
			      ")";
			    // @formatter:on

			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("brand", request.getBrand());
			    params.source.addValue("description", request.getDescription());

			    return params;
			  }

		@Override
		public Food addFood(@Valid FoodRequest request) {
		  	SqlParams params = generateInsertSqlForFood(request);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);	
		  	
		  	Long foodId = keyHolder.getKey().longValue();
		  	
		  	// @formatter: off
		  	return Food.builder()
		  			.foodId(foodId)
		  			.brand(request.getBrand())
		  			.description(request.getDescription())
		  			.build();
			// @formatter: on
		}
		
		

		@Override
		public String deleteFood(Long foodId) {
		  	SqlParams params = generateDeleteSqlForFood(foodId);
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);
		  
		  	// @formatter: off
		  	return "{}";
			// @formatter: on
		}
		
		  private SqlParams generateDeleteSqlForFood(Long foodId) {
			    // @formatter:off
			    String sql = "" +
			      "DELETE FROM food " +
			      "WHERE food_pk = :food_pk ";
			    // @formatter:on
			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("food_pk", foodId);
			    return params;
		  }

		@Override
		public List<Food> fetchAllFood() {
			//@formatter: off
			String sql = ""
					+ "SELECT * "
					+ "FROM food ";
			//@formatter: on
			
			return jdbcTemplate.query(sql,
					new RowMapper<>() {
				@Override
				public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
					//@formatter: off
					return Food.builder()
							.foodId(rs.getLong("food_pk"))
							.brand(rs.getString("brand"))
							.description(rs.getString("food_description"))
							.build();
					//@formatter: on
				}});
		}

		@Override
		public Food updateFood(@Valid UpdateFoodRequest request) {
		  	SqlParams params = generateUpdateSqlForFood(request);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);
		  			  	
		  	// @formatter: off
		  	return Food.builder()
		  			.foodId(request.getFoodId())
		  			.brand(request.getBrand())
		  			.description(request.getDescription())
		  			.build();
			// @formatter: on
		}
		
		  private SqlParams generateUpdateSqlForFood(UpdateFoodRequest request) {
			    // @formatter:off
			    String sql = "" +
			      "UPDATE food SET " +
			      "brand = :brand, food_description = :food_description " +
			      "WHERE food_pk = :food_pk ";
			    // @formatter:on

			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("food_pk", request.getFoodId());
			    params.source.addValue("brand", request.getBrand());
			    params.source.addValue("food_description", request.getDescription());

			    return params;
		 }

		@Override
		public FoodIntake createFoodIntake(@Valid FoodIntakeRequest request) {
		  	SqlParams params = generateInsertSqlForFoodIntake(request);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);	
		  	
		  	Long foodIntakeId = keyHolder.getKey().longValue();
		  	
		  	// @formatter: off
		  	return FoodIntake.builder()
		  			.foodIntakeId(foodIntakeId)
		  			.petId(request.getPetId())
		  			.foodId(request.getFoodId())
		  			.build();
			// @formatter: on
		}
		
		  private SqlParams generateInsertSqlForFoodIntake(FoodIntakeRequest request) {
			    // @formatter:off
			    String sql = "" +
			      "INSERT INTO food_intake (" +
			      "pet_fk, food_fk" +
			      ") VALUES (" +
			      ":food_fk, :pet_fk" +
			      ")";
			    // @formatter:on

			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("food_fk", request.getFoodId());
			    params.source.addValue("pet_fk", request.getPetId());

			    return params;
			  }

		@Override
		public String deleteFoodIntake(Long foodId, Long petId) {
		  	
			SqlParams params = generateDeleteSqlForFoodIntake(foodId,petId);
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);
		  
		  	// @formatter: off
		  	return "{}";
			// @formatter: on
		}
		
		  private SqlParams generateDeleteSqlForFoodIntake(Long foodId, Long petId) {
			    // @formatter:off
			    String sql = "" +
			      "DELETE FROM food_intake " +
			      "WHERE food_fk = :food_fk AND pet_fk = :pet_fk  ";
			    // @formatter:on
			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("food_fk", foodId);
			    params.source.addValue("pet_fk", petId);
			    return params;
		  }

		@Override
		public List<FoodIntakeWithFoodDetails> fetchAllFoodIntake(Long petId) {
			//@formatter: off

			String sql ="SELECT SE.pet_fk, SE.food_fk, food.brand, food.food_description "
					+ "FROM (SELECT DISTINCT pet_fk,food_fk FROM food_intake) as SE "
					+ "INNER JOIN food "
					+ "ON SE.food_fk = food.food_pk "
					+"WHERE SE.pet_fk = :pet_fk";
			//@formatter: on
			
			Map<String,Object> params = new HashMap<>();
			params.put("pet_fk", petId);
			
			return jdbcTemplate.query(sql, params,
					new RowMapper<>() {
				@Override
				public FoodIntakeWithFoodDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
					//@formatter: off
					return FoodIntakeWithFoodDetails.builder()
							.petId(rs.getLong("pet_fk"))
							.foodId(rs.getLong("food_fk"))
							.brand(rs.getString("brand"))
							.description(rs.getString("food_description"))
							.build();
					//@formatter: on
				}});
		}

		@Override
		public FoodIntake updateFoodIntake(@Valid UpdateFoodIntakeRequest request) {
		  	SqlParams params = generateUpdateSqlForFoodIntake(request);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);
		  			  	
		  	// @formatter: off
		  	return FoodIntake.builder()
		  			.foodIntakeId(request.getFoodIntakeId())
		  			.foodId(request.getFoodId())
		  			.petId(request.getPetId())
		  			.build();
			// @formatter: on
		}
		
		  private SqlParams generateUpdateSqlForFoodIntake(UpdateFoodIntakeRequest request) {
			    // @formatter:off
			    String sql = "" +
			      "UPDATE food_intake SET " +
			      "pet_fk = :pet_fk, food_fk = :food_fk " +
			      "WHERE food_intake_pk = :food_intake_pk ";
			    // @formatter:on

			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("food_intake_pk", request.getFoodIntakeId());
			    params.source.addValue("food_fk", request.getFoodId());
			    params.source.addValue("pet_fk", request.getPetId());

			    return params;
		 }

		@Override
		public Owner addOwner(@Valid OwnerRequest request) {
		  	SqlParams params = generateInsertSqlForOwner(request);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);	
		  	
		  	Long id = keyHolder.getKey().longValue();
		  	
		  	// @formatter: off
		  	return Owner.builder()
		  			.ownerPK(id)
		  			.firstName(request.getFirstName())
		  			.lastName(request.getLastName())
		  			.phone(request.getPhone())
		  			.build();
			// @formatter: on
		}
		
		  private SqlParams generateInsertSqlForOwner(OwnerRequest request) {
			    // @formatter:off
			    String sql = "" +
			      "INSERT INTO owners (" +
			      "first_name, last_name, phone" +
			      ") VALUES (" +
			      ":first_name, :last_name, :phone" +
			      ")";
			    // @formatter:on

			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("first_name", request.getFirstName());
			    params.source.addValue("last_name", request.getLastName());
			    params.source.addValue("phone", request.getPhone());

			    return params;
			  }

		@Override
		public String deleteOwner(Long ownerId) {
		  	SqlParams params = generateDeleteSqlForOwner(ownerId);
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);
		  
		  	// @formatter: off
		  	return "{}";
			// @formatter: on
		}
		
		  private SqlParams generateDeleteSqlForOwner(Long ownerId) {
			    // @formatter:off
			    String sql = "" +
			      "DELETE FROM owners " +
			      "WHERE owner_pk = :owner_pk ";
			    // @formatter:on
			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("owner_pk", ownerId);
			    return params;
		  }

		@Override
		public Owner updateOwner(@Valid UpdateOwnerRequest request) {
		  	SqlParams params = generateUpdateSqlForOwner(request);
		  	
		  	KeyHolder keyHolder = new GeneratedKeyHolder();
		  	jdbcTemplate.update(params.sql, params.source, keyHolder);
		  			  	
		  	// @formatter: off
		  	return Owner.builder()
		  			.ownerPK(request.getOwnerPk())
		  			.firstName(request.getFirstName())
		  			.lastName(request.getLastName())
		  			.phone(request.getPhone())
		  			.build();
			// @formatter: on
		}
		
		  private SqlParams generateUpdateSqlForOwner(UpdateOwnerRequest request) {
			    // @formatter:off
			    String sql = "" +
			      "UPDATE owners SET " +
			      "first_name = :first_name, last_name = :last_name , phone = :phone " +
			      "WHERE owner_pk = :owner_pk ";
			    // @formatter:on

			    SqlParams params = new SqlParams();

			    params.sql = sql;
			    params.source.addValue("owner_pk", request.getOwnerPk());
			    params.source.addValue("first_name", request.getFirstName());
			    params.source.addValue("last_name", request.getLastName());
			    params.source.addValue("phone", request.getPhone());

			    return params;
		 }

		@Override
		public List<Owner> fetchAllOwners() {
			//@formatter: off
			String sql = ""
					+ "SELECT * "
					+ "FROM owners ";
			//@formatter: on
			
			return jdbcTemplate.query(sql,
					new RowMapper<>() {
				@Override
				public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
					//@formatter: off
					return Owner.builder()
							.ownerPK(rs.getLong("owner_pk"))
							.firstName(rs.getString("first_name"))
							.lastName(rs.getString("last_name"))
							.phone(rs.getString("phone"))
							.build();
					//@formatter: on
				}});
		}
}
