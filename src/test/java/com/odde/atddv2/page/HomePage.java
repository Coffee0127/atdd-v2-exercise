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
        inputByPlaceholder("用户名", userName);
        inputByPlaceholder("密码", password);
        clickByText("登录");
    }

    private void inputByPlaceholder(String placeholder, String userName) {
        await().ignoreExceptions().until(() -> getWebDriver().findElement(xpath("//*[@placeholder='" + placeholder + "']")), Objects::nonNull).sendKeys(userName);
    }

    private void clickByText(String text) {
        await().ignoreExceptions().until(() -> getWebDriver().findElement(xpath("//*[text()='" + text + "']")), Objects::nonNull).click();
    }

    public void shouldHaveText(String text) {
        shouldHaveText2(text);
    }

    private void shouldHaveText2(String text) {
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