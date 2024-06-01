package org.stepup.stream6;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.stepup.stream6.entities.Account;
import org.stepup.stream6.entities.CurTypes;

import static org.stepup.stream6.interfaces.CurrRuleAble.currRule;
import static org.stepup.stream6.interfaces.NameRuleAble.nameRule;

public class CommanAbleTest {
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
