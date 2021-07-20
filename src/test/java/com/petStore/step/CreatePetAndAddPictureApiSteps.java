package com.petStore.step;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petStore.model.Category;
import com.petStore.model.Pet;
import com.petStore.model.Tag;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePetAndAddPictureApiSteps {

    private static final String PET_STORE_BASE_URL = "https://petstore.swagger.io/v2";
    private static final String USERNAME = "api_key";
    private static final String PASSWORD = "special-key";

    private Response response;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Category category;
    private Tag tag;
    private Pet pet;

    @Given("pet has category details")
    public void pet_has_category_details(Map<String, String> categoryDetails) {
        category = objectMapper.convertValue(categoryDetails, Category.class);
    }

    @And("pet has tag details")
    public void pet_has_tag_details(Map<String, String> tagDetails) {
        tag = objectMapper.convertValue(tagDetails, Tag.class);
    }

    @And("pet has following information")
    public void pet_has_following_information(Pet petDetails) {
        pet = petDetails;
        pet.setCategory(category);
        pet.setTags(Collections.singletonList(tag));
    }

    @When("the pet is created {string}")
    public void the_pet_is_created(String endpoint) {
        given()
                .log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().basic(USERNAME, PASSWORD)
                .body(pet)
                .when()
                .post(PET_STORE_BASE_URL + endpoint)
                .then().assertThat()
                .statusCode(200);
    }

    @Then("pet image will be uploaded {string}")
    public void pet_image_will_be_uploaded(String endpoint, Map<String, String> fileDetails) throws URISyntaxException {

        endpoint = endpoint.replace("{petId}", String.valueOf(pet.getId()));

        Path resourceDirectory = Paths.get("src", "test", "resources", "pictures", fileDetails.get("fileName"));
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        File petImage = new File(absolutePath);

        assertThat(petImage, notNullValue());


        given()
                .log().all()
                .param("petId", pet.getId())
                .multiPart("additionalMetadata", pet.getName())
                .multiPart("file", petImage)
                .auth().basic(USERNAME, PASSWORD)
                .when()
                .put(PET_STORE_BASE_URL + endpoint)
                .then().assertThat()
                .statusCode(200)
                .body("status", is("pending"));
    }

    @Then("search for new pet {string}")
    public void searchForNewPet(String endpoint) {
        endpoint = endpoint.replace("{petId}", String.valueOf(pet.getId()));

        given()
                .log().all()
                .auth().basic(USERNAME, PASSWORD)
                .when()
                .get(PET_STORE_BASE_URL + endpoint)
                .then().assertThat()
                .statusCode(200);
    }

    @And("search for similar pets {string}")
    public void searchForSimilarPets(String endpoint) {

        given()
                .log().all()
                .auth().basic(USERNAME, PASSWORD)
                .when()
                .get(PET_STORE_BASE_URL + endpoint)
                .then().assertThat()
                .statusCode(200);
    }
}
