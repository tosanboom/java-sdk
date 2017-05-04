package ir.boommarket.accounts;

import java.util.List;

/**
 * Represents account info response
 *
 * @author Ali Dehghani
 */
public class AccountInfo {
    private String customerNumber;
    private String name;
    private String title;
    private String foreignName;
    private Gender gender;
    private String code;
    private List<String> addresses;

    public String customerNumber() {
        return customerNumber;
    }

    public String name() {
        return name;
    }

    public String title() {
        return title;
    }

    public String foreignName() {
        return foreignName;
    }

    public Gender gender() {
        return gender;
    }

    public String code() {
        return code;
    }

    public List<String> addresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "customerNumber='" + customerNumber + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", foreignName='" + foreignName + '\'' +
                ", gender=" + gender +
                ", code='" + code + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}