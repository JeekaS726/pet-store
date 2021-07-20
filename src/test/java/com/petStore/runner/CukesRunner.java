package com.petStore.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber/cucumber.json",
                "html:target/cucumber/cucumber.html"},
        features = "src/test/resources/features",
        glue = {"com/petStore/step", "com/petStore/config"},
        tags = "not @ignore"
)
public class CukesRunner {
}
