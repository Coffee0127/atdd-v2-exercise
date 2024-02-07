package com.odde.atddv2;

import com.odde.atddv2.entity.User;
import com.odde.atddv2.repo.UserRepo;
import io.cucumber.java.After;
import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.xpath;

public class TestSteps {

    private final OkHttpClient client = new OkHttpClient().newBuilder().build();

    @Autowired
    private UserRepo userRepo;

    private WebDriver webDriver = null;
    private Response response;

    @SneakyThrows
    public WebDriver createWebDriver() {
        return new RemoteWebDriver(new URL("http://web-driver.tool.net:4444"), DesiredCapabilities.chrome());
    }

    @After
    public void closeBrowser() {
        getWebDriver().quit();
    }

    @当("测试环境")
    public void 测试环境() {
        getWebDriver().get("http://host.docker.internal:10081/");
        assertThat(getWebDriver().findElements(xpath("//*[text()='登录']"))).isNotEmpty();
        getWebDriver().quit();
    }

    @那么("打印Token")
    public void 打印_token() throws IOException {
        assertThat(response.body()).isNotNull();
        System.out.println(response.body().string());
    }

    @那么("打印百度为您找到的相关结果数")
    public void 打印百度为您找到的相关结果数() {
    }

    @假如("存在用户名为{string}和密码为{string}的用户")
    public void 存在用户名为和密码为的用户(String userName, String password) {
        var isUserNotExisted = userRepo.findByUserNameAndPassword(userName, password).isEmpty();
        if (isUserNotExisted) {
            var user = new User()
                .setUserName(userName)
                .setPassword(password);
            userRepo.save(user);
        }
    }

    @当("通过API以用户名为{string}和密码为{string}登录时")
    public void 通过api以用户名为和密码为登录时(String userName, String password) throws IOException {
        var request = new Request.Builder()
            .url("http://localhost:10081/users/login")
            .post(RequestBody.create(
                String.format("{\"userName\":\"%s\",\"password\":\"%s\"}", userName, password),
                MediaType.parse("application/json")))
            .build();
        response = client.newCall(request).execute();
    }

    @当("在百度搜索关键字{string}")
    public void 在百度搜索关键字(String arg0) {
    }

    private WebDriver getWebDriver() {
        if (webDriver == null)
            webDriver = createWebDriver();
        return webDriver;
    }
}
