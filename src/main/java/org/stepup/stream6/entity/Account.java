package org.stepup.stream6.entity;

import org.stepup.stream6.exceptions.NothingToUndo;
import org.stepup.stream6.interfaces.CommandAble;
import org.stepup.stream6.interfaces.CurrRuleAble;
import org.stepup.stream6.interfaces.NameRuleAble;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Deque;

public class Account {
    private String name;
    private HashMap<CurTypes, BigDecimal> currencies; // валюта не может быть целым числом - не надо...
    private NameRuleAble nameRule;
    private CurrRuleAble currRule;
    private Deque<CommandAble> commands = new ArrayDeque<>();

    //adding new field: type
    private String type;

    public Account(NameRuleAble namerule, CurrRuleAble currrule) {
        this.nameRule = namerule;
        this.currRule = currrule;
        this.currencies = new HashMap<>();
    }

    //Part2 undo operation start
    public Account undo() throws NothingToUndo {
        if (commands.isEmpty()) throw new NothingToUndo();
        commands.pop().execute();
        return this;
    }

    public boolean canUndo(){
        if (commands.isEmpty()) return false;
        return true;
    }
    //Part2 undo operation finish

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (nameRule.check(name)) throw new IllegalArgumentException("The Account.name must not be empty");

        //Part2 for undo operation start
        String oldName = this.name;
        this.commands.push(()->{this.name = oldName;});
        //Part2 for undo operation finish

        this.name = name;
    }

    public HashMap<CurTypes, BigDecimal> getCurrency() {
        return new HashMap<CurTypes, BigDecimal>(this.currencies);
    }

    public void putCurrency(CurTypes curtype, BigDecimal val) {
        if (currRule.check(val)) throw new IllegalArgumentException("The currency value must be greater then zero");

        //Part2 for undo operation start
        if (currencies.containsKey(curtype)){
            BigDecimal oldVal = this.currencies.get(curtype);
            this.commands.push(()->{this.currencies.put(curtype,oldVal);});
        } else {
            this.commands.push(()->{this.currencies.remove(curtype);});
        }
        //Part2 for undo operation finish

        this.currencies.put(curtype, val);
    }

    @Override
    public String toString() {
        return "Account{" + "name='" + name + '\'' + ", currency=" + currencies + ", type=" + type + '}';
    }

    //adding new field: type
    public void setType(String type) {
        //Part2 for undo operation start
        String oldType = this.type;
        this.commands.push(()->{this.type = oldType;});
        //Part2 for undo operation finish

        this.type = type;
    }
}

