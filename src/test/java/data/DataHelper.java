package data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class getCardInfo {
        private String number;
        private String cardBalance;
    }

    public static getCardInfo getFirstCard() {
        return new getCardInfo("5559 0000 0000 0001","10000");
    }

    public static getCardInfo getSecondCard() {
        return new getCardInfo("5559 0000 0000 0002","10000");
    }

}
