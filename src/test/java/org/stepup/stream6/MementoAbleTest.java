package org.stepup.stream6;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.stepup.stream6.entities.Account;
import org.stepup.stream6.entities.CurTypes;
import org.stepup.stream6.interfaces.CurrRuleAble;
import org.stepup.stream6.interfaces.MementoAble;
import org.stepup.stream6.interfaces.NameRuleAble;

public class MementoAbleTest {
    NameRuleAble nameRule = (x) -> x == null || x.isEmpty();
    CurrRuleAble currRule = (y) -> (y < 0);

    @Test
    void load() {
        Account acc = new Account(nameRule,  currRule);
        acc.setName("initName");
        acc.putCurrency(CurTypes.RUB, 100);
        acc.setType("SuperAccount");
        MementoAble qs1 = acc.save();
        String savedAcc = acc.toString();
        acc.putCurrency(CurTypes.USD, 100);
        acc.setName("modifiedName");
        acc.setType("SimpleAccount");
        qs1.load();
        Assert.assertEquals(savedAcc, acc.toString());
    }

    @Test
    void repeatedLoad() {
        Account acc = new Account(nameRule,  currRule);
        acc.setName("initName");
        acc.putCurrency(CurTypes.RUB, 100);
        acc.setType("SuperAccount");
        MementoAble qs1 = acc.save();
        String savedAcc1 = acc.toString();

        acc.setName("modifiedName");
        acc.putCurrency(CurTypes.USD, 100);
        acc.setType("SimpleAccount");
        MementoAble qs2 = acc.save();
        String savedAcc2 = acc.toString();

        qs1.load();
        Assert.assertEquals(savedAcc1, acc.toString());

        qs2.load();
        Assert.assertEquals(savedAcc2, acc.toString());

    }
}
