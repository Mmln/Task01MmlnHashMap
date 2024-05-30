package org.stepup.stream6.entity;

import org.stepup.stream6.interfaces.CurrRuleAble;
import org.stepup.stream6.interfaces.NameRuleAble;

import java.math.BigDecimal;
import java.util.HashMap;

public class Account {
    private String name;
    private HashMap<CurTypes, BigDecimal> currency; // валюта не может быть целым числом - не надо...
    private NameRuleAble nameRule;
    private CurrRuleAble currRule;

    public Account(NameRuleAble namerule, CurrRuleAble currrule) {
        this.nameRule = namerule;
        this.currRule = currrule;
        this.currency = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (nameRule.check(name)) throw new IllegalArgumentException("The Account.name must not be empty");
        this.name = name;
    }

    public HashMap<CurTypes, BigDecimal> getCurrency() {
        return new HashMap<CurTypes, BigDecimal>(this.currency);
    }

    public void putCurrency(CurTypes curtype, BigDecimal val) {
        if (currRule.check(val)) throw new IllegalArgumentException("The currency value must be greater then zero");
        this.currency.put(curtype, val);
    }

    @Override
    public String toString() {
        return "Account{" + "name='" + name + '\'' + ", currency=" + currency + '}';
    }

}

