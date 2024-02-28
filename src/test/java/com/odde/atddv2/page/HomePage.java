package com.odde.atddv2.page;

public class HomePage {
    private final Browser browser = new Browser();

    public void open() {
        browser.open();
    }

    public void login(String userName, String password) {
        browser.inputByPlaceholder("用户名", userName);
        browser.inputByPlaceholder("密码", password);
        browser.clickByText("登录");
    }

    public void shouldHaveText(String text) {
        browser.shouldHaveText(text);
    }

    public void quitWebDriver() {
        browser.quitWebDriver();
    }
}