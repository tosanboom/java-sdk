package com.tosanboom.deposits;

/**
 * @author Marjan Mehranfar
 */
public class DepositHolder {
     public String name;
     public String family;
     public String fatherName;

    @Override
    public String toString() {
        return "DepositHolder{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}