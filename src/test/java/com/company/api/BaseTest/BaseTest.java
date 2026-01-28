package com.company.api.baseTest;

import com.company.api.utils.ConfigReader;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    //ThreadLocal<Response> response = new ThreadLocal<>();

    @BeforeSuite
    public void suiteSetup() {
        String env = System.getProperty("env", "dev");
        ConfigReader.load("config/" + env + ".properties");
    }

//    @BeforeMethod
//    public void setup() {
//        RequestSpecFactory.initSpec(ConfigReader.get("petBaseUri"));
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        RequestSpecFactory.removeSpec();
//    }

}
