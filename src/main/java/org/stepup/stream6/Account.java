package org.stepup.stream6;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Account {
    private String name;
    private HashMap<CurTypes, BigDecimal> currency;

    private Account(){}
    public Account(String name) {
        this.setName(name);
        this.currency = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("The Account.name must not be empty");
        this.name = name;
    }

    public HashMap<CurTypes, BigDecimal> getCurrency() {
        return new HashMap<CurTypes, BigDecimal>(this.currency);
    }

    public void putCurrency(CurTypes curtype, BigDecimal val) {
        if (val.signum() != 1) throw new IllegalArgumentException("The currency value must be greater then zero");
        this.currency.put(curtype, val);
    }

    @Override
    public String toString() {
        return "Account{" + "name='" + name + '\'' + ", currency=" + currency + '}';
    }

}

