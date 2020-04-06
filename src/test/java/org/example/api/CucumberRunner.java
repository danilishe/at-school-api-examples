package org.example.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = "org.example.glue",
        features = "src/test/resources/features/"
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
}
