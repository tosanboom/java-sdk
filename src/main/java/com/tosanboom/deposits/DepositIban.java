package com.tosanboom.deposits;

/**
 * @author Marjan Mehranfar
 */
public class DepositIban {
  private String ibanNumber;

    /**
     * The IBAN of the given deposit number
     */
    public String ibanNumber() {
        return ibanNumber;
    }

    @Override
    public String toString() {
        return "DepositIban{" +
                "ibanNumber='" + ibanNumber + '\'' +
                '}';
    }
}
