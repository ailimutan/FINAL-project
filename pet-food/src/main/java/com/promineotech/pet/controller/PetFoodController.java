package com.promineotech.pet.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

import com.promineotech.pet.entity.Features;
import com.promineotech.pet.entity.Food;
import com.promineotech.pet.entity.FoodIntake;
import com.promineotech.pet.entity.FoodIntakeRequest;
import com.promineotech.pet.entity.FoodIntakeWithFoodDetails;
import com.promineotech.pet.entity.FoodRequest;
import com.promineotech.pet.entity.Owner;
import com.promineotech.pet.entity.OwnerRequest;
import com.promineotech.pet.entity.Pet;
import com.promineotech.pet.entity.PetFeatureRequest;
import com.promineotech.pet.entity.PetRequest;
import com.promineotech.pet.entity.UpdateFoodIntakeRequest;
import com.promineotech.pet.entity.UpdateFoodRequest;
import com.promineotech.pet.entity.UpdateOwnerRequest;
import com.promineotech.pet.entity.UpdatePetRequest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.servers.Server;
@RequestMapping("/pets")
@OpenAPIDefinition(

  info = @Info(title = "Pet Food Service"),
  servers = {
    @Server(url = "http://localhost:8080", description = "Local Server.")
  })
public interface PetFoodController {
  // @formatter:off
  @Operation(
    tags = "Pet",
    summary = "Returns a list of Pets where name or type is specified.",
    description = "Returns a list of pets given an optimal pet name and/or pet type",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Pet.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    },
    parameters = {
      @Parameter(
        name = "name",
        allowEmptyValue = false,
        required = false,
        description = "Pet Name"),
      @Parameter(
        name = "type",
        allowEmptyValue = false,
        required = false,
        description = "Pet Type (i.e Dog, Cat)")
    }
  )

  // @formatter:on

  @GetMapping("/fetchPetByNameOrType")
  @ResponseStatus(code = HttpStatus.OK)
  List < Pet > fetchPets(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String type);

  // @formatter:off
  @Operation(
    tags = "Pet",
    summary = "Fetch all Pets",
    description = "Returns a list of Pets",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Pet.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )

  // @formatter:on	

  @GetMapping("/fetchAllPets")
  @ResponseStatus(code = HttpStatus.OK)
  List < Pet > fetchAllPets();

  // @formatter:off
  @Operation(
    tags = "Pet",
    summary = "Create new instance of Pet",
    description = "Returns the created Pet",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Pet.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )

  @PostMapping("/createPet")
  @ResponseStatus(code = HttpStatus.CREATED)
  Pet addPet(@Valid @RequestBody PetRequest petRequest);

  // @formatter:off
  @Operation(
    tags = "Pet",
    summary = "Update instance of Pet",
    description = "Returns the updated Pet",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Pet.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )
  @PutMapping("/updatePet")
  @ResponseStatus(code = HttpStatus.OK)
  Pet updatePet(@Valid @RequestBody UpdatePetRequest updatePetRequest);

  // @formatter:off
  @Operation(
    tags = "Pet",
    summary = "Delete an instance of Pet",
    description = "Returns the deleted Pet",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success"),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    },
    parameters = {
      @Parameter(
        name = "petId",
        allowEmptyValue = false,
        required = false,
        description = "Pet Id"),
    }
  )
  @DeleteMapping("/deletePet")
  String deletePet(@RequestParam(required = true) Long petId);

  // ----- FEATURES

  // @formatter:off
  @Operation(
    tags = "Pet Features",
    summary = "Create new instance of Pet Features where Pet Id is specified",
    description = "Returns the created Pet Features",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Features.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )
  @PostMapping("/createFeature")
  @ResponseStatus(code = HttpStatus.CREATED)
  Features addPetFeatures(@Valid @RequestBody PetFeatureRequest request);

  // @formatter:off
  @Operation(
    tags = "Pet Features",
    summary = "Update Pet Features",
    description = "Returns the updated Pet Features",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Pet.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )
  @PutMapping("/updateFeature")
  @ResponseStatus(code = HttpStatus.OK)
  Features updatePetFeatures(@Valid @RequestBody PetFeatureRequest request);

  // @formatter:off
  @Operation(
    tags = "Pet Features",
    summary = "Returns list of all Pet Features if PET ID is not specified.",
    description = "Returns the Features of a Pet if its pet id is specified. Otherwise, returns all the" +
    "List Pet Features",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Features.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    },
    parameters = {
      @Parameter(
        name = "id",
        allowEmptyValue = true,
        required = false,
        description = "Pet Id (if empty value returns all Features in the list"),
    }
  )

  // @formatter:on

  @GetMapping("/fetchFeatures")
  @ResponseStatus(code = HttpStatus.OK)
  List < Features > fetchFeatures(
    @RequestParam(required = false) String id);

  // @formatter:off
  @Operation(
    tags = "Pet Features",
    summary = "Delete an instance of Feature",
    description = "Returns the deleted Feature",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success"),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    },
    parameters = {
      @Parameter(
        name = "petId",
        allowEmptyValue = false,
        required = true,
        description = "Pet Id"),
    }
  )
  @DeleteMapping("/deleteFeature")
  String deleteFeature(@RequestParam(required = true) Long petId);

  // ---- FOOD

  // @formatter:on
  @Operation(
    tags = "Food",
    summary = "Create new instance of Food",
    description = "Returns the created Food instance.",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Food.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )

  // @formatter:on

  @PostMapping("/createFood")
  @ResponseStatus(code = HttpStatus.CREATED)
  Food addFood(@Valid @RequestBody FoodRequest request);

  // @formatter:off
  @Operation(
    tags = "Food",
    summary = "Delete an instance of Food",
    description = "Returns the deleted Food",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success"),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    },
    parameters = {
      @Parameter(
        name = "foodId",
        allowEmptyValue = false,
        required = true,
        description = "Food Id"),
    }
  )
  @DeleteMapping("/deleteFood")
  String deleteFood(@RequestParam(required = true) Long foodId);

  // @formatter:off
  @Operation(
    tags = "Food",
    summary = "Fetch all Food",
    description = "Returns a list of Food",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Food.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )

  // @formatter:on	

  @GetMapping("/fetchAllFood")
  @ResponseStatus(code = HttpStatus.OK)
  List < Food > fetchAllFood();

  // @formatter:off
  @Operation(
    tags = "Food",
    summary = "Update instance of Food",
    description = "Returns the updated Food",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Food.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )
  @PutMapping("/updateFood")
  @ResponseStatus(code = HttpStatus.OK)
  Food updateFood(@Valid @RequestBody UpdateFoodRequest request);

  //---- FOOD INTAKE

  // @formatter:on
  @Operation(
    tags = "Food Intake",
    summary = "Create new instance of Food Intake",
    description = "Returns the created Food Intake instance.",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = FoodIntake.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )

  // @formatter:on

  @PostMapping("/createFoodIntake")
  @ResponseStatus(code = HttpStatus.CREATED)
  FoodIntake addFoodIntake(@Valid @RequestBody FoodIntakeRequest request);

  // @formatter:off
  @Operation(
    tags = "Food Intake",
    summary = "Delete an instance of Food Intake",
    description = "Returns the deleted Food Intake",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success"),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    },
    parameters = {
      @Parameter(
        name = "foodId",
        allowEmptyValue = false,
        required = true,
        description = "Food Id"),
      @Parameter(
        name = "petId",
        allowEmptyValue = false,
        required = true,
        description = "Pet Id")
    }
  )
  @DeleteMapping("/deleteFoodIntake")
  String deleteFoodIntake(@RequestParam(required = true) Long foodId,
    @RequestParam(required = true) Long petId);

  // @formatter:off
  @Operation(
    tags = "Food Intake",
    summary = "Fetch all Food by Pet id",
    description = "Returns a list of Food of the Specific Pet",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = FoodIntakeWithFoodDetails.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))
    },
    parameters = {
      @Parameter(
        name = "petId",
        allowEmptyValue = false,
        required = true,
        description = "Pet Id")
    }
  )

  // @formatter:on	

  @GetMapping("/fetchAllFoodIntakeByPetId")
  @ResponseStatus(code = HttpStatus.OK)
  List < FoodIntakeWithFoodDetails > fetchAllFoodIntake(@RequestParam(required = true) Long petId);

  // @formatter:off
  @Operation(
    tags = "Food Intake",
    summary = "Update instance of Food Intake",
    description = "Returns the updated Food",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = FoodIntake.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )
  @PutMapping("/updateFoodIntake")
  @ResponseStatus(code = HttpStatus.OK)
  FoodIntake updateFoodIntake(@Valid @RequestBody UpdateFoodIntakeRequest request);

  // @formatter:on
  @Operation(
    tags = "Owner",
    summary = "Create new instance of Owner",
    description = "Returns the created Owner instance.",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Owner.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )

  // @formatter:on

  @PostMapping("/createOwner")
  @ResponseStatus(code = HttpStatus.CREATED)
  Owner addOwner(@Valid @RequestBody OwnerRequest request);

  // @formatter:off
  @Operation(
    tags = "Owner",
    summary = "Delete an instance of Owner",
    description = "Returns the deleted Owner",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success"),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    },
    parameters = {
      @Parameter(
        name = "ownerId",
        allowEmptyValue = false,
        required = true,
        description = "Owner Id"),
    }
  )
  @DeleteMapping("/deleteOwner")
  String deleteOwner(@RequestParam(required = true) Long ownerId);

  // @formatter:off
  @Operation(
    tags = "Owner",
    summary = "Update instance of Owner",
    description = "Returns the updated Owner",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Owner.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )
  @PutMapping("/updateOwner")
  @ResponseStatus(code = HttpStatus.OK)
  Owner updateOwner(@Valid @RequestBody UpdateOwnerRequest request);

  // @formatter:off
  @Operation(
    tags = "Owner",
    summary = "Fetch all Owner",
    description = "Returns a list of Owner",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "success",
        content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = Owner.class))),
      @ApiResponse(
        responseCode = "400",
        description = "bad input",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "404",
        description = "not found",
        content = @Content(mediaType = "application/json")),
      @ApiResponse(
        responseCode = "500",
        description = "An unplanned error",
        content = @Content(mediaType = "application/json"))

    }
  )

  // @formatter:on	

  @GetMapping("/fetchAllOwners")
  @ResponseStatus(code = HttpStatus.OK)
  List < Owner > fetchAllOwners();

}