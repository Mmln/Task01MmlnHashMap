package org.stepup.stream6;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.stepup.stream6.entities.Account;
import org.stepup.stream6.entities.CurTypes;
import org.stepup.stream6.interfaces.CurrRuleAble;
import org.stepup.stream6.interfaces.NameRuleAble;


public class CommanAbleTest {
    NameRuleAble nameRule = (x) -> x == null || x.isEmpty();
    CurrRuleAble currRule = (y) -> (y < 0) || y == null;
    @Test
    void testExecute() {
        Account acc = new Account(nameRule,  currRule);
        acc.setName("initName");
        acc.putCurrency(CurTypes.RUB, 100);
        acc.setType("SuperAccount");
        String savedAccount = acc.toString();
        acc.setName("modifiedName");
        acc.putCurrency(CurTypes.RUB, 200);
        acc.setType("SimpleAccount");
        if(acc.canUndo()) acc.undo();
        if(acc.canUndo()) acc.undo();
        if(acc.canUndo()) acc.undo();
        Assert.assertEquals(savedAccount,acc.toString());
    }
}
