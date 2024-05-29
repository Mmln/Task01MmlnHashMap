package org.stepup.stream6.interfaces;

import java.math.BigDecimal;
@FunctionalInterface
public interface CurrRuleAble {
    boolean check(BigDecimal bgnum);
}
