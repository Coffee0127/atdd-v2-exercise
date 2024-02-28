package com.odde.atddv2.page;

import com.odde.atddv2.TestSteps;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.openqa.selenium.By.xpath;

public class HomePage {
    private final TestSteps testSteps;

    public HomePage(TestSteps testSteps) {
        this.testSteps = testSteps;
    }

    public void open() {
        testSteps.getWebDriver().get("http://host.docker.internal:10081");
    }

    public void login(String userName, String password, TestSteps testSteps) {
        await().ignoreExceptions().until(() -> testSteps.getWebDriver().findElement(xpath("//*[@placeholder='用户名']")), Objects::nonNull).sendKeys(userName);
        await().ignoreExceptions().until(() -> testSteps.getWebDriver().findElement(xpath("//*[@placeholder='密码']")), Objects::nonNull).sendKeys(password);
        await().ignoreExceptions().until(() -> testSteps.getWebDriver().findElement(xpath("//*[text()='登录']")), Objects::nonNull).click();
    }

    public void shouldHaveText(String text, TestSteps testSteps) {
        await().ignoreExceptions().untilAsserted(() -> assertThat(testSteps.getWebDriver().findElements(xpath("//*[text()='" + text + "']"))).isNotEmpty());
    }
}