package org.stepup.stream6.entities;

import org.stepup.stream6.exceptions.NothingToUndo;
import org.stepup.stream6.interfaces.CommandAble;
import org.stepup.stream6.interfaces.CurrRuleAble;
import org.stepup.stream6.interfaces.MementoAble;
import org.stepup.stream6.interfaces.NameRuleAble;

import java.util.*;

public class Account {
    private String name;
    private Map<CurTypes, Integer> currencies;
    private final NameRuleAble nameRule;
    private final CurrRuleAble currRule;
    private final Deque<CommandAble> commands = new ArrayDeque<>();

    //adding new field: type
    private String type;

    //Part3 save/load implementation start
    public MementoAble save() {return new Snapshot();}
    // this class placed here to simplify using Account class
    private class Snapshot implements MementoAble
    {
        private final String name;
        private final Map<CurTypes, Integer> currencies;

        //adding new field: type
        private final String type;

        public Snapshot ()
        {
            this.name = Account.this.name;
            this.currencies = new TreeMap<>(Account.this.currencies);
            this.type = Account.this.type;
        }
        @Override
        public void load() {
            Account.this.name = this.name;
            Account.this.currencies = new TreeMap<>(this.currencies);
            Account.this.type = this.type;
        }
    }
    //Part3 save/load implementation finish

    public Account(NameRuleAble namerule, CurrRuleAble currrule) {
        this.nameRule = namerule;
        this.currRule = currrule;
        this.currencies = new TreeMap<>();
    }

    //Part2 undo implementation start
    public void undo() throws NothingToUndo {
        if (commands.isEmpty()) throw new NothingToUndo();
        commands.pop().execute();
    }

    public boolean canUndo(){
        return !commands.isEmpty();
    }
    //Part2 undo implementation finish

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (nameRule.check(name)) throw new IllegalArgumentException("The Account.name must not be empty");

        //Part2 undo implementation start
        String oldName = this.name;
        this.commands.push(()->{this.name = oldName;});
        //Part2 undo implementation finish

        this.name = name;
    }

    public TreeMap<CurTypes, Integer> getCurrencies() {
        return new TreeMap<CurTypes, Integer>(this.currencies);
    }

    public void putCurrency(CurTypes curtype, Integer val) {
        if (currRule.check(val)) throw new IllegalArgumentException("The currency value must be greater then zero");

        //Part2 undo implementation start
        if (currencies.containsKey(curtype)){
            Integer oldVal = this.currencies.get(curtype);
            this.commands.push(()->{this.currencies.put(curtype,oldVal);});
        } else {
            this.commands.push(()->{this.currencies.remove(curtype);});
        }
        //Part2 undo implementation finish

        this.currencies.put(curtype, val);
    }

    @Override
    public String toString() {
        return "Account{" + "name='" + name + '\'' + ", currency=" + currencies + ", type=" + type + '}';
    }

    //adding new field: type
    public void setType(String type) {
        //Part2 undo implementation start
        String oldType = this.type;
        this.commands.push(()->{this.type = oldType;});
        //Part2 undo implementation finish

        this.type = type;
    }

    public String getType() {
        return type;
    }
}

