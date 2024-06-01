package org.stepup.stream6;

import org.junit.jupiter.api.Test;
import org.stepup.stream6.entities.Account;
import org.stepup.stream6.entities.CurTypes;
import org.stepup.stream6.exceptions.NothingToUndo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.stepup.stream6.interfaces.CurrRuleAble.currRule;
import static org.stepup.stream6.interfaces.NameRuleAble.nameRule;

public class NothingToDoTest {
    @Test
    void generateNothingToUndo(){
        Account acc2 = new Account(nameRule,  currRule);
        acc2.putCurrency(CurTypes.RUB, 100);
        acc2.setName("Vasiliy Ivanov");
        acc2.putCurrency(CurTypes.RUB, 300);
        acc2.setType("PremiumAccount");
        while(acc2.canUndo()){
            acc2.undo();
        }
        assertThrows(NothingToUndo.class, () -> acc2.undo(), "Undo in empty stack should throw NothingToUndo");
    }
}
