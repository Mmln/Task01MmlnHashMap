package org.stepup.stream6;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.stepup.stream6.entities.Account;
import org.stepup.stream6.entities.CurTypes;
import org.stepup.stream6.exceptions.NothingToUndo;
import org.stepup.stream6.interfaces.MementoAble;

import java.math.BigDecimal;

public class AccountTest {
    @Test
    void save() {
        Account acc = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y < 0);
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
    void undo() throws NothingToUndo {
        Account acc = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y < 0);
        acc.setName("initName");
        String oldName = acc.getName();
        acc.setName("modifiedName");
        acc.undo();
        Assert.assertEquals(oldName, acc.getName());
    }

    @Test
    void getName() {
        Account acc = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y < 0);
        acc.setName("initName");
        Assert.assertEquals("initName", acc.getName());
    }

    @Test
    void setName() {
        Account acc = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y < 0);
        acc.setName("initName");
        acc.setName("modifiedName");
        Assert.assertEquals("modifiedName", acc.getName());
    }

    @Test
    void getcurrencies() {
        Account acc1 = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y < 0);
        acc1.setName("initName");
        acc1.putCurrency(CurTypes.USD, 50);
        Account acc2 = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y < 0);
        acc2.setName("initName");
        acc2.putCurrency(CurTypes.USD, 50);
        Assert.assertEquals(acc1.getCurrencies(),acc2.getCurrencies());
    }

    @Test
    void putCurrency() {
        Account acc1 = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y < 0);
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
        Account acc = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y < 0);
        acc.setName("initName");
        acc.putCurrency(CurTypes.USD, 50);
        Assert.assertNotNull(acc.toString());
    }
}
