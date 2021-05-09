package pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item [data-test-id]");
    private SelenideElement firstCardBalance= $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']>[data-test-id='action-deposit']");
    private SelenideElement secondCardBalance = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']>[data-test-id='action-deposit']");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public void dashboardPage() {
        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        val cardBalance = $(".list__item [data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").getText();
        return extractBalance(cardBalance);
    }

    public int getSecondCardBalance() {
        val cardBalance = $(".list__item [data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']").getText();
        return extractBalance(cardBalance);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public Transaction firstCard() {
        firstCardBalance.click();
        return new Transaction();
    }

    public Transaction secondCard() {
        secondCardBalance.click();
        return new Transaction();
    }
}
