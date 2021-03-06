package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {


    @Test
    void shouldTransferFromSecondToFirst() {
        int amount =300;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceOfFirstCardBegining = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBegining = DashboardPage.getCurrentBalanceOfSecondCard();
        val transferPage = dashboardPage.firstCard();
        val cardinfo = DataHelper.getSecondCardInfo();
        transferPage.transferCard(cardinfo,amount);
        int balanceAfterTransferFirstCard = DataHelper.balancePlusAmount(balanceOfFirstCardBegining, amount);
        int balanceAfterTransferSecondCard = DataHelper.balanceMinusAmount(balanceOfSecondCardBegining, amount);
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);

    }

    @Test
    void shouldTransferFromFirstToSecond() {
        int amount = 1000;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val transferPage = dashboardPage.secondCard();
        val cardInfo = DataHelper.getFirstCardInfo();
        transferPage.transferCard(cardInfo,amount);
        int balanceAfterTransferFirstCard = DataHelper.balancePlusAmount(balanceOfSecondCardBefore, amount);
        int balanceAfterTransferSecondCard = DataHelper.balanceMinusAmount(balanceOfFirstCardBefore, amount);
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);
    }
/*
    @Test
    void shouldNotTransferMoreThanRestOfBalance() {
        int amount = 15000;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);

        val transferPage = dashboardPage.firstCard();
        val cardInfo = DataHelper.getSecondCardInfo();
        transferPage.transferCard(cardInfo, amount);
        $(withText("На балансе недостаточно средств")).shouldBe(Condition.visible);
    }

 */

    @Test
    void shouldNotTransferMoreThanRestOfBalance() {
        int amount = 15000;
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceOfFirstCardBegining = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBegining = DashboardPage.getCurrentBalanceOfSecondCard();
        val transferPage = dashboardPage.firstCard();
        val cardinfo = DataHelper.getSecondCardInfo();
        transferPage.transferCard(cardinfo,amount);
        int balanceAfterTransferFirstCard = DataHelper.balancePlusAmount(balanceOfFirstCardBegining, amount);
        int balanceAfterTransferSecondCard = DataHelper.balanceMinusAmount(balanceOfSecondCardBegining, amount);
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();
        assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);


    }
}
