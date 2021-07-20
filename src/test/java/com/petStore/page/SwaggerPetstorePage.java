package com.petStore.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SwaggerPetstorePage {

    private WebDriver driver;

    public SwaggerPetstorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
