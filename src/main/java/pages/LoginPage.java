package pages;

import static com.codeborne.selenide.Selenide.$;
import static data.URLS.LOGIN_URL;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class LoginPage {
    public SelenideElement userNameTxt = $(By.id("userName"));
    public SelenideElement passwordTxt = $(By.id("password"));
    public SelenideElement loginBtn = $(By.id("login"));

    public void login(String userName, String password){
        Selenide.open(LOGIN_URL);
        userNameTxt.setValue(userName);
        passwordTxt.setValue(password);
        loginBtn.click();
    }
}
