package com.tosanboom.deposits;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marjan Mehranfar
 */
public class DepositList {
    private List<Deposit> deposits = new ArrayList<>();

    /**
     * @return List of {@linkplain Deposit}s
     */
    public List<Deposit> deposits() {
        return deposits;
    }

    @Override
    public String toString() {
        return "DepositList{" +
                "deposits=" + deposits +
                '}';
    }
}