package com.odde.atddv2.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePage {
    @Autowired
    private Browser browser;

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