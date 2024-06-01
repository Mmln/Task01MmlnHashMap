package org.stepup.stream6;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.stepup.stream6.entities.Account;
import org.stepup.stream6.entities.CurTypes;
import org.stepup.stream6.exceptions.NothingToUndo;
import org.stepup.stream6.interfaces.MementoAble;

import static org.stepup.stream6.interfaces.CurrRuleAble.currRule;
import static org.stepup.stream6.interfaces.NameRuleAble.nameRule;

public class AccountTest {
    @Test
    void testSave() {
        Account acc = new Account(nameRule,  currRule);
        acc.setName("initName");
        acc.putCurrency(CurTypes.USD, 50);
        MementoAble qs1 = acc.save();
        String savedAcc = acc.toString();
        String savedName = acc.getName();
        acc.putCurrency(CurTypes.USD, 100);
        acc.setName("modifiedName");
        qs1.load();
        Assert.assertEquals(savedName, acc.getName());
        Assert.assertEquals(savedAcc, acc.toString());
    }

    @Test
    void testUndo() throws NothingToUndo {
        Account acc = new Account(nameRule,  currRule);
        acc.setName("initName");
        String oldName = acc.getName();
        acc.setName("modifiedName");
        acc.undo();
        Assert.assertEquals(oldName, acc.getName());
    }

    @Test
    void testGetName() {
        Account acc = new Account(nameRule,  currRule);
        acc.setName("initName");
        Assert.assertEquals("initName", acc.getName());
    }

    @Test
    void testSetName() {
        Account acc = new Account(nameRule,  currRule);
        acc.setName("initName");
        acc.setName("modifiedName");
        Assert.assertEquals("modifiedName", acc.getName());
    }

    @Test
    void testGetCurrencies() {
        Account acc1 = new Account(nameRule,  currRule);
        acc1.setName("initName");
        acc1.putCurrency(CurTypes.USD, 50);
        Account acc2 = new Account(nameRule,  currRule);
        acc2.setName("initName");
        acc2.putCurrency(CurTypes.USD, 50);
        Assert.assertEquals(acc1.getCurrencies(),acc2.getCurrencies());
    }

    @Test
    void testPutCurrency() {
        Account acc1 = new Account(nameRule,  currRule);
        acc1.setName("initName");
        acc1.putCurrency(CurTypes.USD, 50);
        Account acc2 = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y < 0);
        acc2.setName("initName");
        acc2.putCurrency(CurTypes.USD, 50);
        Assert.assertEquals(acc1.toString(),acc2.toString());
    }

    @Test
    void testToString() {
        Account acc = new Account(nameRule,  currRule);
        acc.setName("initName");
        acc.putCurrency(CurTypes.USD, 50);
        Assert.assertNotNull(acc.toString());
    }

    @Test
    void testSetType(){
        Account acc = new Account(nameRule,  currRule);
        acc.setType("SimpleAccount");
        Assert.assertEquals("SimpleAccount", acc.getType());
    }

    @Test
    void testGetType() {
        Account acc = new Account(nameRule,  currRule);
        acc.setType("PremiumAccount");
        Assert.assertEquals("PremiumAccount", acc.getType());
    }
}
