package com.odde.atddv2.page;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.openqa.selenium.By.xpath;

public class HomePage {
    private WebDriver webDriver = null;

    public void open() {
        getWebDriver().get("http://host.docker.internal:10081");
    }

    public void login(String userName, String password) {
        await().ignoreExceptions().until(() -> getWebDriver().findElement(xpath("//*[@placeholder='用户名']")), Objects::nonNull).sendKeys(userName);
        await().ignoreExceptions().until(() -> getWebDriver().findElement(xpath("//*[@placeholder='密码']")), Objects::nonNull).sendKeys(password);
        await().ignoreExceptions().until(() -> getWebDriver().findElement(xpath("//*[text()='登录']")), Objects::nonNull).click();
    }

    public void shouldHaveText(String text) {
        await().ignoreExceptions().untilAsserted(() -> assertThat(getWebDriver().findElements(xpath("//*[text()='" + text + "']"))).isNotEmpty());
    }

    public WebDriver getWebDriver() {
        if (webDriver == null)
            webDriver = createWebDriver();
        return webDriver;
    }

    @SneakyThrows
    public WebDriver createWebDriver() {
        return new RemoteWebDriver(new URL("http://web-driver.tool.net:4444"), DesiredCapabilities.chrome());
    }

    public void quitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}