import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.DashBoardPage;
import pageObject.loginPage;

import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Tests {

    @BeforeEach
    void firstStage() {
        open("http://localhost:9999/");
    }

    void Authorization() {
        val loginPage = new loginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void fromFirstToSecond() {
        Authorization();
        val validAmount = 5000;
        val dashboardPage = new DashBoardPage();
        dashboardPage.dashboardPage();
        val balanceSecondCard = dashboardPage.getSecondCardBalance() + validAmount;
        val remittance = dashboardPage.secondCard();
        remittance.remittance();
        val cardInfo = DataHelper.getFirstCard();
        remittance.getTransaction(cardInfo, validAmount);
        assertEquals(balanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void fromSecondToFirst() {
        Authorization();
        val validAmount = 666;
        val dashboardPage = new DashBoardPage();
        dashboardPage.dashboardPage();
        val balanceFirstCard = dashboardPage.getFirstCardBalance() + validAmount;
        val remittance = dashboardPage.firstCard();
        remittance.remittance();
        val cardInfo = DataHelper.getSecondCard();
        remittance.getTransaction(cardInfo, validAmount);
        assertEquals(balanceFirstCard, dashboardPage.getFirstCardBalance());
    }

    @Test
    void moreThanThereIs() {
        Authorization();
        val invalidAmount = 999999;
        val dashboardPage = new DashBoardPage();
        dashboardPage.dashboardPage();
        val remittance = dashboardPage.firstCard();
        remittance.remittance();
        val cardInfo = DataHelper.getSecondCard();
        remittance.getTransaction(cardInfo, invalidAmount);
    }
}
