package commons;

import ir.boommarket.Bank;
import ir.boommarket.BoomApi;
import ir.boommarket.accounts.Accounts;
import ir.boommarket.accounts.BankLoginRequest;

import java.util.Calendar;
import java.util.Date;

public class Common {
    public static class NetBank {
        public static final String USERNAME = "a7451";
        public static final String PASSWORD = "84436377";

        public static final String USERNAME_WITHDRAW = "cif3211";
        public static final String PASSWORD_WITHDRAW = "62589654";
    }

    public static class Deposit {
        public static final String DEPOSIT_NUMBER = "124-4-750-2";
        public static final String IBAN_NUMBER = "IR100590012481303335585001";
        public static final String OWNER_NAME = "آرمان کامروا";
        public static final String DEPOSIT_NUMBER_WITHDRAW = "110-70-1000000-1";
    }

    public static class Card {
        public static final String PAN = "6393461031211747";
        public static final String PIN = "562970";
        public static final String CVV = "592";
        public static final String EXP = "9911";
    }

    public static class Bill {
        public static final String BILL_ID = "2519128609051";
        public static final String PAY_ID = "48062127";
    }

    public static class Store {
        public static final String DEVICE_ID = "123456789";
        public static final String TOKEN = "08d4032deeb68a719e52d38be8f869c4";
        public static final String APP_KEY = "12374";
    }

    public static class Loan {
        public static final String LOAN_NUMBER = "124.120.7451.1";
    }

    public static class TestBoomApi {
        public static BoomApi forCardService() {
            return BoomApi.newBuilder()
                    .withBoomToken(Store.TOKEN)
                    .withDeviceId(Store.DEVICE_ID)
                    .withAppKey(Store.APP_KEY)
                    .withBank(Bank.ANSAR)
                    .setSandbox(true)
                    .build();
        }

        public static BoomApi withTestSession() {
            return BoomApi.newBuilder()
                    .withBoomToken(Store.TOKEN)
                    .withDeviceId(Store.DEVICE_ID)
                    .withAppKey(Store.APP_KEY)
                    .withBank(Bank.ANSAR)
                    .setSandbox(true)
                    .withSession(getSession(NetBank.USERNAME, NetBank.PASSWORD))
                    .build();
        }

        public static BoomApi withdrawTestSession() {
            return BoomApi.newBuilder()
                    .withBoomToken(Store.TOKEN)
                    .withDeviceId(Store.DEVICE_ID)
                    .withAppKey(Store.APP_KEY)
                    .withBank(Bank.ANSAR)
                    .setSandbox(true)
                    .withSession(getSession(NetBank.USERNAME_WITHDRAW, NetBank.PASSWORD_WITHDRAW))
                    .build();
        }

        public static Date date(int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            return calendar.getTime();
        }

        private static String getSession(String username, String password) {
            BankLoginRequest request = new BankLoginRequest(username, password);

            return Accounts.bankLogin(request, TestBoomApi.forCardService()).sessionId();
        }
    }
}