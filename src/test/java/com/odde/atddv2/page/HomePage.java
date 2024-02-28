package com.odde.atddv2.page;

import com.odde.atddv2.TestSteps;

public class HomePage {
    private final TestSteps testSteps;

    public HomePage(TestSteps testSteps) {
        this.testSteps = testSteps;
    }

    public void open() {
        testSteps.getWebDriver().get("http://host.docker.internal:10081");
    }
}