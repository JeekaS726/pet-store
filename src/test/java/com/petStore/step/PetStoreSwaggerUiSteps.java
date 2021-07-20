package com.petStore.step;

import com.petStore.config.Browser;
import com.petStore.page.SwaggerPetstorePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PetStoreSwaggerUiSteps {


    private final WebDriver driver = Browser.getDriver();

    @Given("user navigates to {string}")
    public void user_navigates_to(String url){
        driver.get(url);
    }

    @When("user clicks on endpoint with text {string}")
    public void user_clicks_on_endpoint_with_text(String endpoint) {
        String endpointXpath = "//button[@aria-label='" + endpoint + "']";
        driver.findElement(By.xpath(endpointXpath))
                .click();
    }

    @Then("user validates endpoint has {string} and {string} details")
    public void user_validates_endpoint_has_Parameters_and_Response_details(String parameter, String response){
        String parameterXpath = "//h4[.='"+ parameter + "']";
        assertThat(
                driver.findElement(
                        By.xpath(parameterXpath)).isDisplayed(), equalTo(true));
        String responseXpath = "//h4[.='"+ response + "']";
        assertThat(
                driver.findElement(
                        By.xpath(responseXpath)).isDisplayed(), equalTo(true));
    }

    @And("user collapses the {string}")
    public void user_collapses_the(String endpoint) {
        String endpointXpath = "//button[@aria-label='" + endpoint + "']";
        driver.findElement(By.xpath(endpointXpath))
                .click();
    }
}
