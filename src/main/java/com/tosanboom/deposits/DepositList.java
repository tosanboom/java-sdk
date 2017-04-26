package com.tosanboom.deposits;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marjan Mehranfar
 */
public class DepositList {
    public List<Deposit> deposits = new ArrayList<>();

    @Override
    public String toString() {
        return "DepositList{" +
                "deposits=" + deposits +
                '}';
    }
}
