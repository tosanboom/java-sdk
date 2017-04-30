package commons;

import com.tosanboom.Bank;
import com.tosanboom.BoomApi;
import com.tosanboom.accounts.Accounts;
import com.tosanboom.accounts.BankLoginRequest;

import java.util.Calendar;
import java.util.Date;

public class Common {
    public static class NetBank {
        public static final String USERNAME = "cif3211";
        public static final String PASSWORD = "62589654";
    }

    public static class Deposit {
        public static final String DEPOSIT_NUMBER = "110-70-1000000-1";
    }

    public static class Card {
        public static final String PAN = "6393461031211713";
        public static final String PIN = "831483";
        public static final String CVV = "917";
        public static final String EXP = "9911";
    }

    public static class Store {
        public static final String DEVICE_ID = "123456789";
        public static final String TOKEN = "08d4032deeb68a719e52d38be8f869c4";
        public static final String APP_KEY = "12374";
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
                    .withSession(getSession())
                    .build();
        }

        public static Date date(int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            return calendar.getTime();
        }

        private static String getSession() {
            BankLoginRequest request = new BankLoginRequest(NetBank.USERNAME, NetBank.PASSWORD);

            return Accounts.bankLogin(request, TestBoomApi.forCardService()).sessionId();
        }
    }
}