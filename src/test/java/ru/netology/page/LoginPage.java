package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import ru.netology.data.DataHelper.*;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginFisld = $("[data-test-id=login] input");
    private SelenideElement passvordField = $("[data-test-id=password] input");
    private SelenideElement continueButton= $("[data-test-id=action-login]");
    public VerificationPage validLogin(AuthInfo info) {
        loginFisld.setValue(info.getLogin());
        passvordField.setValue(info.getPassword());
        continueButton.click();
        return new VerificationPage();
    }
}