package pageObject;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Transaction {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement amountTransfer = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement apply = $("[data-test-id=action-transfer]");

    public void remittance() {
        heading.shouldBe(visible);
    }

    public DashBoardPage getTransaction(DataHelper.getCardInfo getCardInfo, int amount) {
        amountTransfer.setValue(String.valueOf(amount));
        from.setValue(getCardInfo.getNumber());
        apply.click();
        return new DashBoardPage();
    }
}

